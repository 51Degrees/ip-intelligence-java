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

package fiftyone.ipintelligence.shared.testhelpers.flowelements;

import fiftyone.ipintelligence.shared.testhelpers.Constants;
import fiftyone.ipintelligence.shared.testhelpers.Wrapper;
import fiftyone.ipintelligence.shared.testhelpers.data.DataValidator;
import fiftyone.pipeline.core.data.FlowData;

import java.util.Arrays;
import java.util.List;

import static fiftyone.pipeline.util.StringManipulation.stringJoin;

public class ProcessTests {

    public static void noEvidence(
        Wrapper wrapper,
        DataValidator validator) throws Exception {
        try (FlowData data = wrapper.getPipeline().createFlowData()) {
            data.process();
            validator.validateData(data, false);
        }
    }

    public static void emptyUserAgent(
        Wrapper wrapper,
        DataValidator validator) throws Exception {
        try (FlowData data = wrapper.getPipeline().createFlowData()) {
            data
                .addEvidence("server.client-ip", "")
                .process();
            validator.validateData(data, false);
        }
    }

    public static void noHeaders(
        Wrapper wrapper,
        DataValidator validator) throws Exception {
        try (FlowData data = wrapper.getPipeline().createFlowData()) {
            data
                .addEvidence("irrelevant.evidence", "some evidence")
                .process();
            validator.validateData(data, false);
        }
    }

    public static void noUsefulHeaders(
        Wrapper wrapper,
        DataValidator validator) throws Exception {
        try (FlowData data = wrapper.getPipeline().createFlowData()) {
            data
                .addEvidence("server.irrelevant-header", "some evidence")
                .process();
            validator.validateData(data, false);           
        }
    }

    public static void caseInsensitiveEvidenceKeys(
        Wrapper wrapper,
        DataValidator validator) throws Exception {
        try (FlowData data = wrapper.getPipeline().createFlowData()) {
            data
                .addEvidence("server.CLIENT-IP", Constants.IPV6_ADDRESS)
                .process();
            validator.validateData(data, true);            
        }
    }
}
