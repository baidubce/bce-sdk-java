/*
 * Copyright 2016 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.iothub;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.model.iotcore.AddPolicyRequest;
import com.baidubce.services.iothub.model.iotcore.AddPolicyResponse;
import com.baidubce.services.iothub.model.iotcore.AuthType;
import com.baidubce.services.iothub.model.iotcore.CreateDeviceRequest;
import com.baidubce.services.iothub.model.iotcore.CreateDeviceResponse;
import com.baidubce.services.iothub.model.iotcore.GetDeviceResponse;
import com.baidubce.services.iothub.model.iotcore.GetDeviceSignatureResponse;
import com.baidubce.services.iothub.model.iotcore.GetTemplateResponse;
import com.baidubce.services.iothub.model.iotcore.PaginationResponse;
import com.baidubce.services.iothub.model.iotcore.Permission;
import com.baidubce.services.iothub.model.iotcore.ResetDeviceSecretResponse;
import com.baidubce.services.iothub.model.iotcore.ScrollPaginationResponse;
import com.baidubce.services.iothub.model.iotcore.UpdateDeviceRequest;
import com.baidubce.services.iothub.model.iotcore.UpdateDeviceResponse;
import com.baidubce.services.iothub.model.iotcore.UpdatePolicyRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for iot hub client.
 */

public class IoTCoreClientTest {

    private static final String AK = "c4324c85e3454b25af6a39becd225365";
    private static final String SK = "99fc53b372d94be38cd965a224b3f6c7";
    private static final String ENDPOINT = "http://10.135.112.3:8589";

    private static final String TEST_IOT_CORE_ID = "ambzykv";

    private IoTCoreClient ioTCoreClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(ENDPOINT);
        ioTCoreClient = new IoTCoreClient(config);
    }

    @Test
    public void deviceTest() {
        // create template
        String templateId = ioTCoreClient.createTemplate(
                TEST_IOT_CORE_ID, RandomStringUtils.randomAlphabetic(8)).getId();
        Assert.assertNotNull(templateId);

        String deviceName = RandomStringUtils.randomAlphabetic(8);

        // create device
        CreateDeviceResponse createDeviceResponse = ioTCoreClient.createDevice(
                TEST_IOT_CORE_ID,
                CreateDeviceRequest.builder()
                        .name(deviceName)
                        .templateId(templateId)
                        .build());
        Assert.assertNotNull(createDeviceResponse.getSecretKey());
        Assert.assertEquals(AuthType.SIGNATURE, createDeviceResponse.getAuthType());
        Assert.assertEquals(deviceName, createDeviceResponse.getName());
        Assert.assertTrue(StringUtils.isEmpty(createDeviceResponse.getDesc()));

        // get signature
        GetDeviceSignatureResponse getDeviceSignatureResponse =
                ioTCoreClient.getDeviceSignature(TEST_IOT_CORE_ID, deviceName);
        Assert.assertNotNull(getDeviceSignatureResponse.getSignature());

        // reset secret
        ResetDeviceSecretResponse resetDeviceSecretResponse = ioTCoreClient.resetDeviceSecret(
                TEST_IOT_CORE_ID, deviceName);
        Assert.assertNotNull(resetDeviceSecretResponse.getSecretKey());

        // update device
        UpdateDeviceResponse updateDeviceResponse = ioTCoreClient.updateDevice(
                TEST_IOT_CORE_ID,
                deviceName,
                UpdateDeviceRequest.builder()
                        .desc("not empty")
                        .build());
        Assert.assertNotNull(updateDeviceResponse.getDesc());

        // get device
        GetDeviceResponse getDeviceResponse = ioTCoreClient.getDevice(TEST_IOT_CORE_ID, deviceName);
        Assert.assertEquals(deviceName, getDeviceResponse.getName());
        Assert.assertTrue(StringUtils.isNotEmpty(getDeviceResponse.getDesc()));

        // get device list
        ScrollPaginationResponse<GetDeviceResponse> listDeviceResponse = ioTCoreClient.getDevices(TEST_IOT_CORE_ID);
        Assert.assertTrue(listDeviceResponse.getData().size() > 0);

        // delete device
        ioTCoreClient.deleteDevice(TEST_IOT_CORE_ID, deviceName);

        // delete template
        ioTCoreClient.deleteTemplate(TEST_IOT_CORE_ID, templateId);
    }

    @Test
    public void templateTest() {
        String templateName = RandomStringUtils.randomAlphabetic(8);

        // create template
        String templateId = ioTCoreClient.createTemplate(TEST_IOT_CORE_ID, templateName).getId();
        Assert.assertNotNull(templateId);

        // get template
        GetTemplateResponse getTemplateResponse = ioTCoreClient.getTemplate(TEST_IOT_CORE_ID, templateId);
        Assert.assertEquals(templateName, getTemplateResponse.getName());

        // list template
        PaginationResponse<GetTemplateResponse> listTemplateResponse = ioTCoreClient.getTemplates(TEST_IOT_CORE_ID);
        Assert.assertTrue(listTemplateResponse.getData().size() > 0);

        // add policy
        AddPolicyResponse addPolicyResponse = ioTCoreClient.addPolicy(
                TEST_IOT_CORE_ID,
                templateId,
                AddPolicyRequest.builder()
                        .permission(Permission.ALL)
                        .topicFilter("test")
                        .build());
        Assert.assertNotNull(addPolicyResponse.getPolicyId());
        Assert.assertEquals(Permission.ALL, Permission.ALL);

        // update policy
        ioTCoreClient.updatePolicy(
                TEST_IOT_CORE_ID,
                templateId,
                addPolicyResponse.getPolicyId(),
                UpdatePolicyRequest.builder()
                        .permission(Permission.SUB)
                        .build());

        // remove policy
        ioTCoreClient.removePolicy(TEST_IOT_CORE_ID, templateId, addPolicyResponse.getPolicyId());

        // delete template
        ioTCoreClient.deleteTemplate(TEST_IOT_CORE_ID, templateId);
    }
}
