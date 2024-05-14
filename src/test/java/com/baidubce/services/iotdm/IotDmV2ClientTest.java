/*
 * Copyright 2017 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdm;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iotdm.model.CreateEndpointRequest;
import com.baidubce.services.iotdm.model.CreateEndpointResponse;
import com.baidubce.services.iotdm.model.EndpointInResponse;
import com.baidubce.services.iotdm.model.GetEndpointsResponse;
import com.baidubce.services.iotdm.model.CreateDevicesRequest;
import com.baidubce.services.iotdm.model.CreateDevicesResponse;
import com.baidubce.services.iotdm.model.DeviceAccessDetail;
import com.baidubce.services.iotdm.model.DeviceOperation;
import com.baidubce.services.iotdm.model.DeviceOperationRequest;
import com.baidubce.services.iotdm.model.DeviceProfileResponse;
import com.baidubce.services.iotdm.model.DeviceQueryRequest;
import com.baidubce.services.iotdm.model.DeviceQueryResponse;
import com.baidubce.services.iotdm.model.Page;
import com.baidubce.services.iotdm.model.RemoveDevicesRequest;
import com.baidubce.services.iotdm.model.UpdateDeviceProfileRequest;
import com.baidubce.services.iotdm.model.UpdateDeviceRegistryRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

/**
 * Unit test for iot device management V2.
 */

public class IotDmV2ClientTest {
    // qa-sandbox
    private static final String ACCESSKEY = "";
    private static final String SECRETKEY = "";
    private static final String ENDPOINT = "10.73.203.34:8010";
    private static final String NEW_HUB = "true";
    private static final String CLEAN_HUB = "true";
    private static final String TEST_ENDPOINT_NAME = "7afcbfe82f18416ca079bb73023a65d2";
    private static final String TEST_DEVICE_NAME = "wade_test_device";

    private IotDmV2Client client;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ACCESSKEY, SECRETKEY))
                .withEndpoint(ENDPOINT);
        client = new IotDmV2Client(config);
    }

    @Test
    public void testCreateEndpoint() {
        client.removeEndpoint(TEST_ENDPOINT_NAME, CLEAN_HUB);
        CreateEndpointResponse response = client.createEndpoint(new CreateEndpointRequest()
                .withEndpointName(TEST_ENDPOINT_NAME).withNewHub(NEW_HUB), UUID.randomUUID().toString());
        assertThat(response.getEndpointName(), equalTo(TEST_ENDPOINT_NAME));
        assertThat(response.getAccountUuid(), notNullValue());
    }

    @Test
    public void testGetEndpoint() {
        GetEndpointsResponse response = client.getEndpoints();
        for (EndpointInResponse endpoint : response.getEndpointList()) {
            System.out.println(endpoint.getEndpointName());
        }
    }

    @Test
    public void testCreateDevice() {
        clean();
        CreateDevicesResponse response = createDevices(TEST_ENDPOINT_NAME, 2, false, null);
        assertThat(response.getAmount(), equalTo(2));
        assertThat(response.getDevices().size(), equalTo(2));
        assertThat(response.getThings(), nullValue());
        clean();
    }

    @Test
    public void testCreateDevicesWithThing() {
        clean();
        CreateDevicesResponse response = createDevices(TEST_ENDPOINT_NAME, 1, true, Arrays.asList(TEST_DEVICE_NAME));
        assertThat(response.getAmount(), equalTo(1));
        assertThat(response.getDevices().size(), equalTo(1));
        assertThat(response.getThings().size(), equalTo(1));
        removeDevcies(Arrays.asList(TEST_DEVICE_NAME), true);
        DeviceQueryRequest queryRequest = new DeviceQueryRequest();
        queryRequest.withCondition("{}").withPage(new Page());
        DeviceQueryResponse deviceQueryResponse = client.getDeviceProfiles(TEST_ENDPOINT_NAME, queryRequest);
        assertThat(deviceQueryResponse.getAmount(), equalTo(0));
    }

    @Test
    public void testGetDeviceProfile() {
        clean();
        createDevices(TEST_ENDPOINT_NAME, 1, false, Arrays.asList(TEST_DEVICE_NAME));
        DeviceProfileResponse response = client.getDeviceProfile(TEST_ENDPOINT_NAME, TEST_DEVICE_NAME);
        assertThat(response.getName(), equalTo(TEST_DEVICE_NAME));
        assertThat(response.getId(), notNullValue());
        assertThat(response.isEnable(), equalTo(true));
        clean();
    }

    @Test
    public void testGetDeviceProfiles() {
        clean();
        createDevices(TEST_ENDPOINT_NAME, 3, false, null);
        DeviceQueryRequest request = new DeviceQueryRequest();
        request.withCondition("{}");
        DeviceQueryResponse response = client.getDeviceProfiles(TEST_ENDPOINT_NAME, request);
        assertThat(response.getAmount(), equalTo(3));
        assertThat(response.getDeviceProfiles().size(), equalTo(3));
        clean();
    }

    @Test
    public void testGetDeviceAccessDetail() {
        clean();
        createDevices(TEST_ENDPOINT_NAME, 1, false, Arrays.asList(TEST_DEVICE_NAME));
        DeviceAccessDetail detail = client.getDeviceAccessDetail(TEST_ENDPOINT_NAME, TEST_DEVICE_NAME);
        assertThat(detail.getProtocol(), equalTo("mqtt"));
        assertThat(detail.getDeviceName(), equalTo(TEST_DEVICE_NAME));
        clean();
    }

    @Test
    public void testUpdateDeviceProfile() {
        clean();
        createDevices(TEST_ENDPOINT_NAME, 1, false, Arrays.asList(TEST_DEVICE_NAME));
        ObjectNode attributes = new ObjectMapper().createObjectNode();
        attributes.put("test", "123");
        UpdateDeviceProfileRequest request = new UpdateDeviceProfileRequest();
        request.withAttributes(attributes)
                .withDeviceOperation(new DeviceOperation().withDevices(Arrays.asList(TEST_DEVICE_NAME)));
        client.updateDeviceProfile(TEST_ENDPOINT_NAME, request);
        DeviceProfileResponse response = client.getDeviceProfile(TEST_ENDPOINT_NAME, TEST_DEVICE_NAME);
        assertThat(response.getAttributes().get("test").asText(), equalTo("123"));
        clean();
    }

    @Test
    public void testUpdateDeviceRegistry() {
        clean();
        createDevices(TEST_ENDPOINT_NAME, 1, false, Arrays.asList(TEST_DEVICE_NAME));
        UpdateDeviceRegistryRequest request = new UpdateDeviceRegistryRequest();
        request.withDescription("test")
                .withDeviceOperation(new DeviceOperation().withDevices(Arrays.asList(TEST_DEVICE_NAME)));
        client.updateDeviceRegistry(TEST_ENDPOINT_NAME, request);
        DeviceProfileResponse response = client.getDeviceProfile(TEST_ENDPOINT_NAME, TEST_DEVICE_NAME);
        assertThat(response.getDescription(), equalTo("test"));
    }

    @Test
    public void testDisableDevice() {
        clean();
        createDevices(TEST_ENDPOINT_NAME, 1, false, Arrays.asList(TEST_DEVICE_NAME));
        DeviceOperationRequest request = new DeviceOperationRequest();
        request.withDevices(Arrays.asList(TEST_DEVICE_NAME));
        client.disableDevices(TEST_ENDPOINT_NAME, request);
        DeviceProfileResponse response = client.getDeviceProfile(TEST_ENDPOINT_NAME, TEST_DEVICE_NAME);
        assertThat(response.isEnable(), equalTo(false));
        DeviceOperationRequest enableRequest = new DeviceOperationRequest();
        enableRequest.withDevices(Arrays.asList(TEST_DEVICE_NAME));
        client.enableDevices(TEST_ENDPOINT_NAME, enableRequest);
        response = client.getDeviceProfile(TEST_ENDPOINT_NAME, TEST_DEVICE_NAME);
        assertThat(response.isEnable(), equalTo(true));
        clean();
    }

    @Test
    public void testRebootDevice() {
        clean();
        createDevices(TEST_ENDPOINT_NAME, 1, false, Arrays.asList(TEST_DEVICE_NAME));
        DeviceOperationRequest request = new DeviceOperationRequest();
        request.withDevices(Arrays.asList(TEST_DEVICE_NAME));
        client.rebootDevices(TEST_ENDPOINT_NAME, request);
        clean();
    }

    private CreateDevicesResponse createDevices(String endpointName,
                                                int amount, boolean bootstrap, List<String> devices) {
        CreateDevicesRequest request = new CreateDevicesRequest();
        request.withAmount(amount)
                .withBootstrap(bootstrap);
        if (devices != null) {
            request.setDevices(devices);
        }
        return client.createDevices(endpointName, request, UUID.randomUUID().toString());
    }

    private void clean() {
        RemoveDevicesRequest request = new RemoveDevicesRequest();
        request.withDeviceOperation(new DeviceOperation().withCondition("{}"));
        client.removeDevices(TEST_ENDPOINT_NAME, request);
    }

    private void removeDevcies(List<String> devices, boolean cleanThing) {
        RemoveDevicesRequest request = new RemoveDevicesRequest();
        request.withDeviceOperation(new DeviceOperation().withDevices(devices))
                .withCleanThing(cleanThing);
        client.removeDevices(TEST_ENDPOINT_NAME, request);
    }

}
