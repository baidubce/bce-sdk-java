/*
 * Copyright 2016 Baidu, Inc. All Rights Reserved.
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iotdm.model.CreateDevicesRequest;
import com.baidubce.services.iotdm.model.CreateDevicesResponse;
import com.baidubce.services.iotdm.model.CreateGroupRequest;
import com.baidubce.services.iotdm.model.DeviceAccessDetail;
import com.baidubce.services.iotdm.model.DeviceOperation;
import com.baidubce.services.iotdm.model.DeviceOperationRequest;
import com.baidubce.services.iotdm.model.DeviceProfileResponse;
import com.baidubce.services.iotdm.model.DeviceQueryRequest;
import com.baidubce.services.iotdm.model.DeviceQueryResponse;
import com.baidubce.services.iotdm.model.GroupInfoResponse;
import com.baidubce.services.iotdm.model.GroupListResponse;
import com.baidubce.services.iotdm.model.Page;
import com.baidubce.services.iotdm.model.RemoveDevicesRequest;
import com.baidubce.services.iotdm.model.UpdateDeviceProfileRequest;
import com.baidubce.services.iotdm.model.UpdateDeviceRegistryRequest;
import com.baidubce.services.iotdm.model.UpdateGroupRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Unit test for iot device management client.
 */
public class IotDmClientTest {

    // qa-sandbox
    private static final String ACCESSKEY = "30dce1fd5b584210905fe8ef3042bc46";
    private static final String SECRETKEY = "51dfc469a93249d88b36be3249412a2a";
    private static final String ENDPOINT = "10.73.203.34:8010";

    private static final String TEST_DEVICE_NAME = "my_test_device";
    private static final String TEST_GROUP_NAME = "my_test_group";

    private IotDmClient client;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ACCESSKEY, SECRETKEY))
                .withEndpoint(ENDPOINT);
        client = new IotDmClient(config);
    }

    @Test
    public void testCreateDevices() {
        clean();
        CreateDevicesResponse response = createDevices(2, false, null);
        assertThat(response.getAmount(), equalTo(2));
        assertThat(response.getDevices().size(), equalTo(2));
        assertThat(response.getThings(), nullValue());
        clean();
    }

    @Test
    public void testCreateDevicesWithThing() {
        clean();
        CreateDevicesResponse response = createDevices(1, true, Arrays.asList(TEST_DEVICE_NAME));
        assertThat(response.getAmount(), equalTo(1));
        assertThat(response.getDevices().size(), equalTo(1));
        assertThat(response.getThings().size(), equalTo(1));
        removeDevcies(Arrays.asList(TEST_DEVICE_NAME), true);

        DeviceQueryResponse deviceQueryResponse = client.getDeviceProfiles(new DeviceQueryRequest()
                .withCondition("{}")
                .withPage(new Page()));
        assertThat(deviceQueryResponse.getAmount(), equalTo(0));
    }

    @Test
    public void testGetDeviceProfile() {
        clean();
        createDevices(1, false, Arrays.asList(TEST_DEVICE_NAME));
        DeviceProfileResponse response = client.getDeviceProfile(TEST_DEVICE_NAME);
        assertThat(response.getName(), equalTo(TEST_DEVICE_NAME));
        assertThat(response.getId(), notNullValue());
        assertThat(response.isEnable(), equalTo(true));
        clean();
    }

    @Test
    public void testGetDeviceProfiles() {
        clean();
        createDevices(3, false, null);
        DeviceQueryResponse response = client.getDeviceProfiles(new DeviceQueryRequest().withCondition("{}"));
        assertThat(response.getAmount(), equalTo(3));
        assertThat(response.getDeviceProfiles().size(), equalTo(3));
        clean();
    }

    @Test
    public void testGetDeviceAccessDetail() {
        clean();
        createDevices(1, false, Arrays.asList(TEST_DEVICE_NAME));
        DeviceAccessDetail detail = client.getDeviceAccessDetail(TEST_DEVICE_NAME);
        assertThat(detail.getProtocol(), equalTo("mqtt"));
        assertThat(detail.getDeviceName(), equalTo(TEST_DEVICE_NAME));
        clean();
    }

    @Test
    public void testUpdateDeviceProfile() {
        clean();
        createDevices(1, false, Arrays.asList(TEST_DEVICE_NAME));
        ObjectNode attributes = new ObjectMapper().createObjectNode();
        attributes.put("test", "123");
        client.updateDeviceProfile(new UpdateDeviceProfileRequest()
                .withAttributes(attributes)
                .withDeviceOperation(new DeviceOperation().withDevices(Arrays.asList(TEST_DEVICE_NAME))));
        DeviceProfileResponse response = client.getDeviceProfile(TEST_DEVICE_NAME);
        assertThat(response.getAttributes().get("test").asText(), equalTo("123"));
        clean();
    }

    @Test
    public void testUpdateDeviceRegistry() {
        clean();
        createDevices(1, false, Arrays.asList(TEST_DEVICE_NAME));
        client.updateDeviceRegistry(new UpdateDeviceRegistryRequest()
                .withDescription("test")
                .withDeviceOperation(new DeviceOperation().withDevices(Arrays.asList(TEST_DEVICE_NAME))));
        DeviceProfileResponse response = client.getDeviceProfile(TEST_DEVICE_NAME);
        assertThat(response.getDescription(), equalTo("test"));
    }

    @Test
    public void testDisableDevice() {
        clean();
        createDevices(1, false, Arrays.asList(TEST_DEVICE_NAME));
        client.disableDevices(new DeviceOperationRequest().withDevices(Arrays.asList(TEST_DEVICE_NAME)));
        DeviceProfileResponse response = client.getDeviceProfile(TEST_DEVICE_NAME);
        assertThat(response.isEnable(), equalTo(false));

        client.enableDevices(new DeviceOperationRequest().withDevices(Arrays.asList(TEST_DEVICE_NAME)));
        response = client.getDeviceProfile(TEST_DEVICE_NAME);
        assertThat(response.isEnable(), equalTo(true));
        clean();
    }

    @Test
    public void testRebootDevice() {
        clean();
        createDevices(1, false, Arrays.asList(TEST_DEVICE_NAME));
        client.rebootDevices(new DeviceOperationRequest().withDevices(Arrays.asList(TEST_DEVICE_NAME)));
        clean();
    }

    @Test
    public void testCreateGroup() {
        GroupInfoResponse response = client.createGroup(
                new CreateGroupRequest().withUri(TEST_GROUP_NAME), UUID.randomUUID().toString());
        assertThat(response.getName(), equalTo(TEST_GROUP_NAME));
        assertThat(response.getId(), notNullValue());
        client.removeGroup(response.getId());
    }

    @Test
    public void testUpdateGroup() {
        GroupInfoResponse groupInfo = client.createGroup(
                new CreateGroupRequest().withUri(TEST_GROUP_NAME), UUID.randomUUID().toString());
        client.updateGroup(groupInfo.getId(), new UpdateGroupRequest().withDescription("group description"));
        GroupInfoResponse response = client.getGroup(groupInfo.getId());
        assertThat(response.getDescription(), equalTo("group description"));
        client.removeGroup(groupInfo.getId());
    }

    @Test
    public void testGetGroup() {
        GroupInfoResponse groupInfo = client.createGroup(
                new CreateGroupRequest().withUri(TEST_GROUP_NAME), UUID.randomUUID().toString());
        GroupInfoResponse response = client.getGroup(groupInfo.getId());
        assertThat(response.getId(), equalTo(groupInfo.getId()));
        assertThat(response.getName(), equalTo(groupInfo.getName()));
        assertThat(response.getUri(), equalTo(groupInfo.getUri()));
        assertThat(response.getDevices(), equalTo(groupInfo.getDevices()));
        assertThat(response.getParent(), equalTo(groupInfo.getParent()));
        assertThat(response.getDescription(), equalTo(groupInfo.getDescription()));
        client.removeGroup(groupInfo.getId());
    }

    @Test
    public void testGetChildGroups() {
        GroupInfoResponse groupInfo = client.createGroup(
                new CreateGroupRequest().withUri(TEST_GROUP_NAME), UUID.randomUUID().toString());
        GroupListResponse response = client.getChildGroups(groupInfo.getId());
        assertThat(response.getGroups().size(), equalTo(0));
        client.removeGroup(groupInfo.getId());
    }

    @Test
    public void testGetRootGroups() {
        GroupInfoResponse groupInfo = client.createGroup(
                new CreateGroupRequest().withUri(TEST_GROUP_NAME), UUID.randomUUID().toString());
        GroupListResponse response = client.getRootGroups();
        assertThat(response.getGroups().size(), greaterThanOrEqualTo(1));
        client.removeGroup(groupInfo.getId());
    }

    @Test
    public void testGetDeviceGroups() {
        GroupInfoResponse groupInfo = client.createGroup(
                new CreateGroupRequest().withUri(TEST_GROUP_NAME), UUID.randomUUID().toString());
        GroupListResponse response = client.getDeviceGroups();
        assertThat(response.getGroups().size(), greaterThanOrEqualTo(1));
        client.removeGroup(groupInfo.getId());
    }

    private CreateDevicesResponse createDevices(int amount, boolean bootstrap, List<String> devices) {
        CreateDevicesRequest request = new CreateDevicesRequest()
                .withAmount(amount)
                .withBootstrap(bootstrap);
        if (devices != null) {
            request.setDevices(devices);
        }

        return client.createDevices(request, UUID.randomUUID().toString());
    }

    private void removeDevcies(List<String> devices, boolean cleanThing) {
        RemoveDevicesRequest request = new RemoveDevicesRequest()
                .withDeviceOperation(new DeviceOperation().withDevices(devices))
                .withCleanThing(cleanThing);
        client.removeDevices(request);
    }

    private void clean() {
        RemoveDevicesRequest request = new RemoveDevicesRequest()
            .withDeviceOperation(new DeviceOperation().withCondition("{}"));
        client.removeDevices(request);
    }

}
