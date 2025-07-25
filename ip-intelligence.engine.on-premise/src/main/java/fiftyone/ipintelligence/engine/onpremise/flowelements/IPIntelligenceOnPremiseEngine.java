/* *********************************************************************
 * This Original Work is copyright of 51 Degrees Mobile Experts Limited.
 * Copyright 2025 51 Degrees Mobile Experts Limited, Davidson House,
 * Forbury Square, Reading, Berkshire, United Kingdom RG1 3EU.
 *
 * This Original Work is licensed under the European Union Public Licence
 * (EUPL) v.1.2 and is subject to its terms as set out below.
 *
 * If a copy of the EUPL was not distributed with this file, You can obtain
 * one at https://opensource.org/licenses/EUPL-1.2.
 *
 * The 'Compatible Licences' set out in the Appendix to the EUPL (as may be
 * amended by the European Commission) shall be deemed incompatible for
 * the purposes of the Work and the provisions of the compatibility
 * clause in Article 5 of the EUPL shall not apply.
 *
 * If using the Work as, or as part of, a network application, by
 * including the attribution notice(s) required under Article 5 of the EUPL
 * in the end user terms of the application under an appropriate heading,
 * such notice(s) shall fulfill the requirements of that article.
 * ********************************************************************* */

package fiftyone.ipintelligence.engine.onpremise.flowelements;

import fiftyone.ipintelligence.engine.onpremise.data.IPIntelligenceDataHash;
import fiftyone.ipintelligence.engine.onpremise.data.ProfileMetaDataIPI;
import fiftyone.ipintelligence.engine.onpremise.data.PropertyMetaDataIPI;
import fiftyone.ipintelligence.engine.onpremise.data.ValueMetaDataIPI;
import fiftyone.ipintelligence.engine.onpremise.interop.*;
import fiftyone.ipintelligence.engine.onpremise.interop.swig.*;
import fiftyone.ipintelligence.engine.onpremise.interop.swig.Date;
import fiftyone.pipeline.core.data.EvidenceKeyFilter;
import fiftyone.pipeline.core.data.EvidenceKeyFilterWhitelist;
import fiftyone.pipeline.core.data.FlowData;
import fiftyone.pipeline.core.data.factories.ElementDataFactory;
import fiftyone.pipeline.engines.caching.FlowCache;
import fiftyone.pipeline.engines.data.AspectEngineDataFile;
import fiftyone.pipeline.engines.fiftyone.data.*;
import fiftyone.pipeline.engines.fiftyone.flowelements.FiftyOneOnPremiseAspectEngineBase;
import org.slf4j.Logger;

import java.util.*;

import static fiftyone.pipeline.util.Check.notFileExists;
import static org.apache.commons.lang3.BooleanUtils.isFalse;

/**
 * On-premise IP Intelligence engine. This engine takes User-Agents and other
 * relevant HTTP headers and returns properties about the device which produced
 * them e.g. DeviceType or ReleaseDate.
 * @see <a href="https://github.com/51Degrees/specifications/blob/main/ip-intelligence-specification/pipeline-elements/ip-intelligence-on-premise.md">Specification</a>
 */
public class IPIntelligenceOnPremiseEngine
    extends FiftyOneOnPremiseAspectEngineBase<IPIntelligenceDataHash,
    FiftyOneAspectPropertyMetaData> {
    private EngineIpiSwig engine = null;
    private final List<FiftyOneAspectPropertyMetaData> properties = new ArrayList<>();
    private final ConfigIpiSwig config;
    private final RequiredPropertiesConfigSwig propertiesConfigSwig;
    private List<String> evidenceKeys;
    private EvidenceKeyFilter evidenceKeyFilter;
    private volatile boolean propertiesPopulated = false;
    private final Random rand = new Random();

    /**
     * Construct a new instance of the {@link IPIntelligenceOnPremiseEngine}.
     * @param logger logger instance to use for logging
     * @param dataFile data file to read the data set from
     * @param config native configuration which was configured by the builder
     * @param properties native required properties configuration which define
     *                   the properties which the engine should be initialised
     *                   with
     * @param IPIDataFactory the factory to use when creating a
     *                          {@link IPIntelligenceDataHash} instance
     * @param tempDataFileDir the file where a temporary data file copy
     *                        will be stored if one is created
     */
    IPIntelligenceOnPremiseEngine(
        Logger logger,
        AspectEngineDataFile dataFile,
        ConfigIpiSwig config,
        RequiredPropertiesConfigSwig properties,
        ElementDataFactory<IPIntelligenceDataHash> IPIDataFactory,
        String tempDataFileDir) {
        super(logger, IPIDataFactory, tempDataFileDir);
        this.config = config;
        this.propertiesConfigSwig = properties;
        addDataFile(dataFile);
    }

    /**
     * Get the evidence keys from the native engine and add to a {@link List}.
     * @param engine to get the keys from
     * @return evidence keys list
     */
    private static List<String> getKeysFromEngine(EngineIpiSwig engine) {
        // In this case, the vector does not need to be closed.
        // The vector is a pointer to memory owned by the native engine, so the
        // delete method actually doesn't call down to the native layer.
        return new ArrayList<>(engine.getKeys());
    }

    @Override
    public String getElementDataKey() {
        return "ip-intelligence";
    }

    /**
     * Get the native meta data instance for this engine.
     * @return native meta data
     */
    public MetaDataSwig getMetaData() {
        return engine.getMetaData();
    }

    @Override
    public List<FiftyOneAspectPropertyMetaData> getProperties() {
        if (isFalse(propertiesPopulated)) {
            synchronized (properties) {
                if (isFalse(propertiesPopulated)) {
                    properties.clear();
                    List<FiftyOneAspectPropertyMetaData> newProperties =
                        new ArrayList<>();

                    try (PropertyIterable iterable = new PropertyIterable(
                            this,
                            newProperties,
                            engine.getMetaData().getProperties())) {
                        for (FiftyOneAspectPropertyMetaData property :
                            iterable) {
                            properties.add(property);
                        }
                    } catch (Exception e) {
                        logger.error(
                            "Exception occurred while constructing properties.",
                            e);
                    }

                    propertiesPopulated = true;
                }
            }
        }
        return properties;
    }

    @Override
    public FiftyOneAspectPropertyMetaData getProperty(String name) {
        FiftyOneAspectPropertyMetaData result = null;
        PropertyMetaDataCollectionSwig properties =
            engine.getMetaData().getProperties();
        PropertyMetaDataSwig swigProperty = properties.getByKey(name);
        if (swigProperty != null) {
            result = new PropertyMetaDataIPI(this, swigProperty);
        }
        properties.delete();

        return result;
    }

    @Override
    public CloseableIterable<ProfileMetaData> getProfiles() {
        return new ProfileIterable(
            this,
            engine.getMetaData().getProfiles());
    }

    @Override
    public ProfileMetaData getProfile(int profileId) {
        ProfileMetaDataCollectionSwig profiles =
            engine.getMetaData().getProfiles();
        ProfileMetaDataSwig profile = profiles.getByKey(profileId);
        profiles.delete();
        return profile == null ?
            null : new ProfileMetaDataIPI(this, profile);
    }

    @Override
    public CloseableIterable<ComponentMetaData> getComponents() {
        return new ComponentIterable(
            this,
            engine.getMetaData().getComponents());
    }

    @Override
    public CloseableIterable<ValueMetaData> getValues() {
        return new ValueIterable(
            this,
            engine.getMetaData().getValues());
    }

    @Override
    public ValueMetaData getValue(String propertyName, String valueName) {
        ValueMetaDataKeySwig key = new ValueMetaDataKeySwig(propertyName, valueName);
        ValueMetaDataCollectionSwig values = engine.getMetaData().getValues();
        ValueMetaDataIPI result = new ValueMetaDataIPI(this, values.getByKey(key));
        values.delete();
        key.delete();
        return result;
    }

    @Override
    public java.util.Date getDataFilePublishedDate(String dataFileIdentifier) {
        Calendar calendar = Calendar.getInstance();
        Date value = engine.getPublishedTime();
        // java.util.Calendar month is 0 based where January = 0
        calendar.set(
            value.getYear(),
            value.getMonth() - 1,
            value.getDay());
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        return calendar.getTime();
    }

    @Override
    public java.util.Date getDataFileUpdateAvailableTime(String dataFileIdentifier) {
        Calendar calendar = Calendar.getInstance();
        Date value = engine.getUpdateAvailableTime();
        // java.util.Calendar month is 0 based where January = 0
        calendar.set(
            value.getYear(),
            value.getMonth() - 1,
            value.getDay());
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, rand.nextInt(60));
        calendar.set(Calendar.HOUR, 12);
        return calendar.getTime();
    }

    @Override
    public String getDataSourceTier() {
        return engine.getProduct();
    }

    /**
     * Used internally to populate the meta data returned by
     * {@link #getDataFileMetaData()}.
     * @return temp path
     */
    private String getDataFileTempPath() {
        return engine == null ? null : engine.getDataFileTempPath();
    }

    @Override
    public EvidenceKeyFilter getEvidenceKeyFilter() {
        return evidenceKeyFilter;
    }

    @Override
    public void refreshData(String dataFileIdentifier) {
        AspectEngineDataFile dataFile = getDataFiles().get(0);

        if (Objects.isNull(engine)) {
            if (notFileExists(dataFile.getDataFilePath())) {
                throw new IllegalStateException("Data file must exist for refresh data " + dataFile.getDataFilePath());
            }
            // sometimes the engine can't read data file, but succeeds on retry
            int tries = 0;
            String lastMessage = "";
            while (tries < 10) {
                try {
                engine = new EngineIpiSwig(dataFile.getDataFilePath(), config, propertiesConfigSwig);
                } catch (Throwable t) {
                    lastMessage = t.getMessage();
                    logger.warn("Creation of Swig Engine failed: " + lastMessage);
                    tries++;
                }
                if (Objects.nonNull(engine)){
                    break;
                }
            }
            if (tries >= 10) {
                throw new IllegalStateException("Failed to create SwigEngine after " + tries +
                        " retries, last exception was " + lastMessage);
            }
        } else {
            engine.refreshData();
        }
        setEngineMetaData();
    }

    @Override
    public void refreshData(String dataFileIdentifier, byte[] data) {
        if (engine == null) {
            engine = new EngineIpiSwig(data, config, propertiesConfigSwig);
        } else {
            engine.refreshData(data);
        }
        setEngineMetaData();
    }

    @Override
    protected void processEngine(FlowData flowData, IPIntelligenceDataHash IPIData) {
        try (EvidenceIpiSwig relevantEvidence =
            new EvidenceIpiSwig()) {
            List<String> keys = evidenceKeys;
            for (Map.Entry<String, Object> evidenceItem :
                flowData.getEvidence().asKeyMap().entrySet()) {
                boolean containsKey = false;
                for (String key : keys) {
                    if (key.equalsIgnoreCase(evidenceItem.getKey())) {
                        containsKey = true;
                        break;
                    }
                }
                if (containsKey) {
                    relevantEvidence
                        .put(
                                evidenceItem.getKey(),
                                evidenceItem.getValue().toString());
                }
            }
            ((IPIntelligenceDataHashDefault) IPIData).setResults(
                engine.process(relevantEvidence));
        }
    }

    @Override
    protected void unmanagedResourcesCleanup() {
        if (propertiesPopulated) {
            synchronized (properties) {
                if (propertiesPopulated) {
                    properties.clear();
                }
            }
        }
        if (config != null) {
            config.delete();
        }
        if (propertiesConfigSwig != null) {
            propertiesConfigSwig.delete();
        }
        if (engine != null) {
            engine.delete();
        }
    }

    private void setEngineMetaData() {
        evidenceKeys = getKeysFromEngine(engine);
        evidenceKeyFilter = new EvidenceKeyFilterWhitelist(
            evidenceKeys,
            String.CASE_INSENSITIVE_ORDER);
        propertiesPopulated = false;
        // Populate these data file properties from the native engine.
        FiftyOneDataFile dataFileMetaData =
            (FiftyOneDataFile)getDataFileMetaData();
        if (dataFileMetaData != null) {
            dataFileMetaData.setDataPublishedDateTime(
                getDataFilePublishedDate());
            dataFileMetaData.setUpdateAvailableTime(
                getDataFileUpdateAvailableTime());
            dataFileMetaData.setTempDataFilePath(getDataFileTempPath());
        }
    }

    @Override
    public void addDataFile(AspectEngineDataFile dataFile) {
        if (getDataFiles().size() >  0) {
            throw new IllegalArgumentException(
                "IPIntelligenceOnPremiseEngine already has a configured data " +
                "source.");
        }
        super.addDataFile(dataFile);
        if (dataFile instanceof  FiftyOneDataFile) {
            FiftyOneDataFile fiftyOneDataFile = (FiftyOneDataFile)dataFile;
            fiftyOneDataFile.setDataUpdateDownloadType("HashV41");
        }
    }

    @Override
    public void setCache(FlowCache cache) {
        throw new UnsupportedOperationException(
            "A results cache cannot be configured in the on-premise IP Intelligence engine. " +
                "The overhead of having to manage native object lifetimes when " +
                "a cache is enabled outweighs the benefit of the cache.");
    }
}
