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
import fiftyone.ipintelligence.engine.onpremise.interop.swig.ConfigIpiSwig;
import fiftyone.ipintelligence.engine.onpremise.interop.swig.RequiredPropertiesConfigSwig;
import fiftyone.ipintelligence.engine.onpremise.interop.swig.VectorStringSwig;
import fiftyone.ipintelligence.shared.flowelements.OnPremiseIPIntelligenceEngineBuilderBase;
import fiftyone.pipeline.annotations.DefaultValue;
import fiftyone.pipeline.annotations.ElementBuilder;
import fiftyone.pipeline.core.data.FlowData;
import fiftyone.pipeline.core.data.factories.ElementDataFactory;
import fiftyone.pipeline.core.exceptions.PipelineConfigurationException;
import fiftyone.pipeline.core.flowelements.FlowElement;
import fiftyone.pipeline.engines.Constants.PerformanceProfiles;
import fiftyone.pipeline.engines.configuration.CacheConfiguration;
import fiftyone.pipeline.engines.data.AspectEngineDataFile;
import fiftyone.pipeline.engines.services.DataUpdateService;
import fiftyone.pipeline.engines.services.MissingPropertyServiceDefault;
import fiftyone.pipeline.util.Check;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static fiftyone.pipeline.util.StringManipulation.stringJoin;

/**
 * Builder for the {@link IPIntelligenceOnPremiseEngine}. All options for the engine
 * should be set here.
 * <p>
 * Default values are taken from ip-intelligence-cxx/src/hash/hash.c
 */
@ElementBuilder(alternateName = "HashIPIntelligence")
public class IPIntelligenceOnPremiseEngineBuilder
    extends OnPremiseIPIntelligenceEngineBuilderBase<
    IPIntelligenceOnPremiseEngineBuilder,
    IPIntelligenceOnPremiseEngine> {

    private final String dataDownloadType = "HashV41";
   
    /**
     * Native configuration instance for this engine.
     */
    private final ConfigIpiSwig config = new ConfigIpiSwig();

    /**
     * Default constructor which uses the {@link ILoggerFactory} implementation
     * returned by {@link LoggerFactory#getILoggerFactory()}.
     */
    public IPIntelligenceOnPremiseEngineBuilder() {
        this(LoggerFactory.getILoggerFactory());
    }

    /**
     * Construct a new instance using the {@link ILoggerFactory} supplied.
     * @param loggerFactory the logger factory to use
     */
    public IPIntelligenceOnPremiseEngineBuilder(ILoggerFactory loggerFactory) {
        this(loggerFactory, null);
    }

    /**
     * Construct a new instance using the {@link ILoggerFactory} and
     * {@link DataUpdateService} supplied.
     * @param loggerFactory the logger factory to use
     * @param dataUpdateService the {@link DataUpdateService} to use when
     *                          automatic updates happen on the data file
     */
    public IPIntelligenceOnPremiseEngineBuilder(
        ILoggerFactory loggerFactory,
        DataUpdateService dataUpdateService) {
        super(loggerFactory, dataUpdateService);
        config.setConcurrency(Runtime.getRuntime().availableProcessors());
    }

    /**
     * Set whether an existing temp file should be used if one is found
     * in the temp directory.
     * <p>
     * Default is false.
     * @param reuse true if an existing file should be used
     * @return this builder
     */
    @DefaultValue("false")
    public IPIntelligenceOnPremiseEngineBuilder setReuseTempFile(boolean reuse) {
        config.setReuseTempFile(reuse);
        return this;
    }

    /**
     * Set the performance profile to use when constructing the data set.
     * <p>
     * Default value is Balanced.
     * @param profileName name of the profile to use
     * @return this builder
     */
    @DefaultValue("Balanced")
    public IPIntelligenceOnPremiseEngineBuilder setPerformanceProfile(
        String profileName) {
        PerformanceProfiles profile;
        try {
            profile = PerformanceProfiles.valueOf(profileName);
        } catch (IllegalArgumentException e) {
            profile = null;
        }

        if (profile != null) {
            return setPerformanceProfile(profile);
        } else {
            List<String> available = new ArrayList<>();
            for (PerformanceProfiles p : PerformanceProfiles.values()) {
                available.add("'" + p.name() + "'");
            }
            throw new IllegalArgumentException(
                "'" + profileName + "' is not a valid performance profile. " +
                    "Available profiles are " +
                    stringJoin(available, ", ") + ".");
        }
    }

    @Override
    public IPIntelligenceOnPremiseEngineBuilder setPerformanceProfile(
        PerformanceProfiles profile) {
        switch (profile) {
            case LowMemory:
                config.setLowMemory();
                break;
            case MaxPerformance:
                config.setMaxPerformance();
                break;
            case Balanced:
                config.setBalanced();
                break;
            case BalancedTemp:
                config.setBalancedTemp();
                break;
            case HighPerformance:
                config.setHighPerformance();
                break;
            default:
                throw new IllegalArgumentException(
                    "The performance profile '" + profile.name() +
                        "' is not valid for a IPIntelligenceOnPremiseEngine.");
        }
        return this;
    }

    /**
     * Provide a hint as to how many threads will access the pipeline simultaneously
     * <p>
     * Default is the result of {@link Runtime#getRuntime()#getAvailableProcessors()}
     * @see <a href="https://51degrees.com/documentation/_device_detection__features__concurrent_processing.html">Concurrent processing</a>
     * @param concurrency expected concurrent accesses
     * @return this builder
     */
    @DefaultValue("The result of Runtime#getRuntime().getAvailableProcessors()")
    @Override
    public IPIntelligenceOnPremiseEngineBuilder setConcurrency(int concurrency) {
        config.setConcurrency(concurrency);
        return this;
    }
    
    @Override
    public IPIntelligenceOnPremiseEngineBuilder setCache(
        CacheConfiguration cacheConfiguration) {
        throw new UnsupportedOperationException(
            "A results cache cannot be configured in the on-premise IP Intelligence " +
            "engine. The overhead of having to manage native object " + 
            "lifetimes when a cache is enabled outweighs the benefit of the " +
            "cache.");
    }
    
    /**
     * The default value to use for the 'Type' parameter when sending
     * a request to the Distributor
     * @return default data download type;
     */
    @Override
    protected String getDefaultDataDownloadType() {
        return dataDownloadType;
    }

    @Override
    protected IPIntelligenceOnPremiseEngine newEngine(List<String> properties) {
        if (dataFiles.size() != 1) {
            throw new PipelineConfigurationException(
                "This builder requires one and only one configured file " +
                    "but it has " + dataFiles.size());
        }
        AspectEngineDataFile dataFile = dataFiles.get(0);
        // We remove the data file configuration from the list.
        // This is because the on-premise engine builder base class
        // adds all the data file configs after engine creation.
        // However, the IP Intelligence data files are supplied
        // directly to the constructor.
        // Consequently, we remove it here to stop it from being added
        // again by the base class.
        dataFiles.remove(0);

        // Update the swig configuration object.
        config.setUseUpperPrefixHeaders(false);
        if (dataFile.getConfiguration().getCreateTempDataCopy() && Check.notNullOrBlank(tempDir)) {
            try (VectorStringSwig tempDirs = new VectorStringSwig()) {
                tempDirs.add(tempDir);
                config.setTempDirectories(tempDirs);
                config.setUseTempFile(true);
            }
        }
        RequiredPropertiesConfigSwig requiredProperties;
        try (VectorStringSwig propertiesSwig = new VectorStringSwig()) {
            propertiesSwig.addAll(properties);
            requiredProperties = new RequiredPropertiesConfigSwig(propertiesSwig);
        }
        return new IPIntelligenceOnPremiseEngine(
            loggerFactory.getLogger(IPIntelligenceOnPremiseEngine.class.getName()),
            dataFile,
            config,
            requiredProperties,
            new HashDataFactory(loggerFactory),
            tempDir);
    }

    private static class HashDataFactory implements
        ElementDataFactory<IPIntelligenceDataHash> {

        private final ILoggerFactory loggerFactory;

        public HashDataFactory(ILoggerFactory loggerFactory) {
            this.loggerFactory = loggerFactory;
        }

        @Override
        public IPIntelligenceDataHash create(
            FlowData flowData,
            FlowElement<IPIntelligenceDataHash, ?> engine) {
            return new IPIntelligenceDataHashDefault(
                loggerFactory.getLogger(IPIntelligenceDataHash.class.getName()),
                flowData,
                (IPIntelligenceOnPremiseEngine) engine,
                MissingPropertyServiceDefault.getInstance());
        }
    }
}
