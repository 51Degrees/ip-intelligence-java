/* *********************************************************************
 * This Original Work is copyright of 51 Degrees Mobile Experts Limited.
 * Copyright 2023 51 Degrees Mobile Experts Limited, Davidson House,
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

import fiftyone.ipintelligence.engine.onpremise.Enums;
import fiftyone.ipintelligence.engine.onpremise.data.IPIntelligenceDataHash;
import fiftyone.ipintelligence.engine.onpremise.interop.Swig;
import fiftyone.ipintelligence.engine.onpremise.interop.swig.*;
import fiftyone.ipintelligence.shared.IPIntelligenceDataBaseOnPremise;
import fiftyone.pipeline.core.data.FlowData;
import fiftyone.pipeline.core.data.TryGetResult;
import fiftyone.pipeline.core.data.types.JavaScript;
import fiftyone.pipeline.engines.data.AspectPropertyValue;
import fiftyone.pipeline.engines.data.AspectPropertyValueDefault;
import fiftyone.pipeline.engines.services.MissingPropertyService;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static fiftyone.pipeline.util.StringManipulation.stringJoin;

/**
 * Internal implementation of the {@link IPIntelligenceDataHash} interface. This can
 * only be constructed by the {@link IPIntelligenceOnPremiseEngine}.
 * @see <a href="https://github.com/51Degrees/specifications/blob/main/ip-intelligence-specification/pipeline-elements/ip-intelligence-on-premise.md#element-data">Specification</a>
 */
public class IPIntelligenceDataHashDefault
    extends IPIntelligenceDataBaseOnPremise
    implements IPIntelligenceDataHash, AutoCloseable {

    /**
     * True if the {@link #close()} method has been called.
     */
    private boolean closed = false;

    /**
     * Pre-populated list of match methods. This to avoid calling the
     * '.values()' method every time.
     */
    private static final Enums.MatchMethods[] matchMethods =
            Enums.MatchMethods.values();

    /**
     * List of native results which make up this result instance. Usually, this
     * will only be one.
     */
    private final List<ResultsIpiSwig> resultsList = new ArrayList<>();

    /**
     * Constructs a new instance.
     * @param logger used for logging
     * @param flowData the {@link FlowData} instance this element data will be
     *                 associated with
     * @param engine the engine which created the instance
     * @param missingPropertyService service used to determine the reason for
     *                               a property value being missing
     */
    IPIntelligenceDataHashDefault(
        Logger logger,
        FlowData flowData,
        IPIntelligenceOnPremiseEngine engine,
        MissingPropertyService missingPropertyService) {
        super(logger, flowData, engine, missingPropertyService);
    }
    
    /**
     * Add the native results to the list of results contained in this instance.
     * @param results the results to add
     */
    void setResults(ResultsIpiSwig results) {
        checkState();
        resultsList.add(results);
    }

    /**
     * Get a single native results instance. If there is only one available,
     * that is what is returned. If there are more, then the first one which
     * contains values for the requested property is returned.
     * @param propertyName used to select results from list
     * @return single native results instance
     */
    private ResultsIpiSwig getSingleResults(String propertyName) {
        return resultsList.size() == 1 ?
            resultsList.get(0) : getResultsContainingProperty(propertyName);
    }

    /**
     * Get the first native results in {@link #resultsList} which contains
     * values for the requested property.
     * @param propertyName name of the requested property
     * @return native results containing values, or null if none were found
     */
    private ResultsIpiSwig getResultsContainingProperty(String propertyName) {
        for (ResultsIpiSwig results : resultsList) {
            if (results.containsProperty(Swig.asBytes(propertyName))) {
                return results;
            }
        }
        return null;
    }

    /**
     * Get the device id from the native results.
     * @return device id
     */
    private AspectPropertyValue<String> getDeviceIdInternal() {
        if (resultsList.size() == 1) {
            // Only one Engine has added results, so return the device
            // id from those results.
            return new AspectPropertyValueDefault<>(
                    resultsList.get(0).getDeviceId());
        } else {
            // Multiple Engines have added results, so construct a device
            // id from the results.
            List<String> result = new ArrayList<>();
            List<String[]> deviceIds = new ArrayList<>();
            for (ResultsIpiSwig results : resultsList) {
                deviceIds.add(results.getDeviceId().split("-"));
            }
            int max = 0;
            for (String[] deviceId : deviceIds) {
                if (deviceId.length > max) {
                    max = deviceId.length;
                }
            }
            for (int i = 0; i < max; i++) {
                String profileId = "0";
                for (String[] deviceId : deviceIds) {
                    if (deviceId.length > i && deviceId[i].equals("0") == false) {
                        profileId = deviceId[i];
                    }
                }
                result.add(profileId);
            }
            return new AspectPropertyValueDefault<>(
                    stringJoin(result, "-"));
        }
    }

    /**
     * Get the difference from the native results.
     * @return difference
     */
    private AspectPropertyValue<Integer> getDifferenceInternal() {
        int total = 0;
        for (ResultsIpiSwig results : resultsList) {
            total += results.getDifference();
        }
        return new AspectPropertyValueDefault<>(total);
    }

    /**
     * Get the drift from the native results.
     * @return drift
     */
    private AspectPropertyValue<Integer> getDriftInternal() {
        int result = Integer.MAX_VALUE;
        for (ResultsIpiSwig results : resultsList) {
            if (results.getDrift() < result) {
                result = results.getDrift();
            }
        }
        return new AspectPropertyValueDefault<>(result);
    }

    /**
     * Get the number of iterations from the native results.
     * @return iterations
     */
    private AspectPropertyValue<Integer> getIterationsInternal() {
        int result = 0;
        for (ResultsIpiSwig results : resultsList) {
            result += results.getIterations();
        }
        return new AspectPropertyValueDefault<>(result);
    }

    /**
     * Get the number of matched nodes from the native results.
     * @return matched nodes
     */
    private AspectPropertyValue<Integer> getMatchedNodesInternal() {
        int result = 0;
        for (ResultsIpiSwig results : resultsList) {
            result += results.getMatchedNodes();
        }
        return new AspectPropertyValueDefault<>(result);
    }

    /**
     * Get the match method from the native results.
     * @return match method
     */
    private AspectPropertyValue<String> getMethodInternal() {
        int result = 0;
        for (ResultsIpiSwig results : resultsList) {
            if (results.getMethod() > result) {
                result = results.getMethod();
            }
        }
        return new AspectPropertyValueDefault<>(matchMethods[result].name());
    }

    /**
     * Get the matched User-Agent strings from the native results.
     * @return matched User-Agents
     */
    private AspectPropertyValue<List<String>> getUserAgentsInternal() {
        List<String> result = new ArrayList<>();
        for (ResultsIpiSwig results : resultsList) {
            for (int i = 0; i < results.getUserAgents(); i++) {
                String userAgent = results.getUserAgent(i);
                if (result.contains(userAgent) == false) {
                    result.add(userAgent);
                }
            }
        }
        return new AspectPropertyValueDefault<>(result);
    }

    @Override
    protected boolean propertyIsAvailable(String propertyName) {
        checkState();
        for (ResultsIpiSwig results : resultsList) {
            if (results.containsProperty(Swig.asBytes(propertyName))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public AspectPropertyValue<List<String>> getValues(String propertyName) {
        checkState();
        AspectPropertyValue<List<String>> result =
            new AspectPropertyValueDefault<>();
        ResultsIpiSwig results = getSingleResults(propertyName);
        if (results != null) {
            try (VectorStringValuesSwig value = results.getValues(
                Swig.asBytes(propertyName))) {
                if (value.hasValue()) {
                    try (VectorStringSwig vector = value.getValue()) {
                        result.setValue(Collections.unmodifiableList(
                            Swig.asList(vector)));
                    }
                }
                else {
                    result.setNoValueMessage(value.getNoValueMessage());
                }
            }
        }
        return result;
    }

    @Override
    protected AspectPropertyValue<String> getValueAsString(String propertyName) {
        AspectPropertyValue<String> result = new AspectPropertyValueDefault<>();
        ResultsIpiSwig results = getSingleResults(propertyName);
        if (results != null) {

            try (StringValueSwig value = results.getValueAsString(
                Swig.asBytes(propertyName))) {
                if (value.hasValue()) {
                    result.setValue(value.getValue());
                }
                else {
                    result.setNoValueMessage(value.getNoValueMessage());
                }
            }
        }
        return result;
    }

    @Override
    protected AspectPropertyValue<JavaScript> getValueAsJavaScript(
        String propertyName) {
        AspectPropertyValue<JavaScript> result =
            new AspectPropertyValueDefault<>();
        ResultsIpiSwig results = getSingleResults(propertyName);
        if (results != null) {
            try (StringValueSwig value = results.getValueAsString(
                Swig.asBytes(propertyName))) {
                if (value.hasValue()) {
                    result.setValue(new JavaScript(value.getValue()));
                }
                else {
                    result.setNoValueMessage(value.getNoValueMessage());
                }
            }
        }
        return result;
    }

    @Override
    protected AspectPropertyValue<Integer> getValueAsInteger(
        String propertyName) {
        AspectPropertyValue<Integer> result = new AspectPropertyValueDefault<>();
        ResultsIpiSwig results = getSingleResults(propertyName);
        if (results != null) {
            try (IntegerValueSwig value = results.getValueAsInteger(
                Swig.asBytes(propertyName))) {
                if (value.hasValue()) {
                    result.setValue(value.getValue());
                }
                else {
                    result.setNoValueMessage(value.getNoValueMessage());
                }
            }
        }
        return result;
    }

    @Override
    protected AspectPropertyValue<Boolean> getValueAsBool(String propertyName) {
        AspectPropertyValue<Boolean> result = new AspectPropertyValueDefault<>();
        ResultsIpiSwig results = getSingleResults(propertyName);
        if (results != null) {
            try (BoolValueSwig value = results.getValueAsBool(
                Swig.asBytes(propertyName))) {
                if (value.hasValue()) {
                    result.setValue(value.getValue());
                }
                else {
                    result.setNoValueMessage(value.getNoValueMessage());
                }
            }
        }
        return result;
    }

    @Override
    protected AspectPropertyValue<Double> getValueAsDouble(String propertyName) {
        AspectPropertyValue<Double> result = new AspectPropertyValueDefault<>();
        ResultsIpiSwig results = getSingleResults(propertyName);
        if (results != null) {
            try (DoubleValueSwig value = results.getValueAsDouble(
                Swig.asBytes(propertyName))) {
                if (value.hasValue()) {
                    result.setValue(value.getValue());
                }
                else {
                    result.setNoValueMessage(value.getNoValueMessage());
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
	@Override
    protected <T> TryGetResult<T> tryGetValue(
        String key,
        Class<T> type,
        Class<?>... parameterisedTypes) {
        checkState();
        TryGetResult<T> result = super.tryGetValue(key, type, parameterisedTypes);

        if (result.hasValue() == false) {
            boolean objSet = false;
            Object obj = null;
            if (key.equalsIgnoreCase("DeviceId")) {
                obj = getDeviceIdInternal();
                objSet = true;
            } else if (key.equalsIgnoreCase("Difference")) {
                obj = getDifferenceInternal();
                objSet = true;
            } else if (key.equalsIgnoreCase("UserAgents")) {
                obj = getUserAgentsInternal();
                objSet = true;
            } else if (key.equalsIgnoreCase("Drift")) {
                obj = getDriftInternal();
                objSet = true;
            } else if (key.equalsIgnoreCase("Iterations")) {
                obj = getIterationsInternal();
                objSet = true;
            }
            else if (key.equalsIgnoreCase("MatchedNodes")) {
                obj = getMatchedNodesInternal();
                objSet = true;
            }
            else if (key.equalsIgnoreCase("Method")){
                obj = getMethodInternal();
                objSet = true;
            }
            if (objSet == true) {
                try {
                    T value;
                    if (type.isPrimitive()) {
                        value = (T) primitiveTypes.get(type).cast(obj);
                    } else {
                        value = type.cast(obj);
                    }
                    result.setValue(value);
                } catch (ClassCastException e) {
                    throw new ClassCastException(
                        "Expected property '" + key + "' to be of " +
                            "type '" + type.getSimpleName() + "' but it is " +
                            "'" + obj.getClass().getSimpleName() + "'");
                }
            }
        }
        return result;
    }

    private void checkState() {
        if (closed == true) {
            throw new IllegalStateException("The IPIntelligenceDataHash instance has " +
                "been closed, and cannot be used. Any result processing should " +
                "be carried out within a 'try-with-resource' block which " +
                "closes the FlowData and any AutoCloseable elements.");
        }
    }

    @Override
    public void close() {
        closed = true;
        for (ResultsIpiSwig result : resultsList) {
            try {
                result.close();
            } catch (Exception e) {
                logger.error("Failed to close native results instance. " +
                    "A IPIntelligenceDataHash instance contains native unmanaged " +
                    "memory which needs to be closed. Failing to close " +
                    "could lead to memory leaks.", e);
            }
        }
    }
}