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
package com.baidubce.services.iotdm;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.model.GenericAccountRequest;
import com.baidubce.services.iotdm.model.CreateDevicesRequest;
import com.baidubce.services.iotdm.model.CreateDevicesResponse;
import com.baidubce.services.iotdm.model.CreateGroupRequest;
import com.baidubce.services.iotdm.model.DeviceAccessDetail;
import com.baidubce.services.iotdm.model.DeviceOperationRequest;
import com.baidubce.services.iotdm.model.DeviceProfileResponse;
import com.baidubce.services.iotdm.model.DeviceQueryRequest;
import com.baidubce.services.iotdm.model.DeviceQueryResponse;
import com.baidubce.services.iotdm.model.GroupInfoResponse;
import com.baidubce.services.iotdm.model.GroupListResponse;
import com.baidubce.services.iotdm.model.RemoveDevicesRequest;
import com.baidubce.services.iotdm.model.UpdateDeviceProfileRequest;
import com.baidubce.services.iotdm.model.UpdateDeviceRegistryRequest;
import com.baidubce.services.iotdm.model.UpdateGroupRequest;
import com.google.common.base.Preconditions;

/**
 * Provides the client for accessing the iot device management.
 */
public class IotDmClient extends AbstractBceClient {

    private static final String ENDPOINT_HOST = "iotdm.gz.baidubce.com";
    private static final String DEVICE = "device";
    private static final String GROUP = "group";
    private static final String CHILDREN = "children";
    private static final String ACCESS_DETAIL = "accessDetail";

    private static final String CLIENT_TOKEN = "clientToken";
    private static final String DISABLE = "disable";
    private static final String ENABLE = "enable";
    private static final String QUERY = "query";
    private static final String REBOOT = "reboot";
    private static final String REMOVE = "remove";
    private static final String ROOT = "root";
    private static final String UPDATE_PROFILE = "updateProfile";
    private static final String UPDATE_REGISTRY = "updateRegistry";

    public IotDmClient(BceClientConfiguration config) {
        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT_HOST) : config,
                IotDmClientHelper.IOT_DM_HANDLERS);

    }

    public CreateDevicesResponse createDevices(CreateDevicesRequest createDevicesRequest, String clientToken) {
        return doCreation(createDevicesRequest, clientToken, DEVICE, CreateDevicesResponse.class);
    }

    public void removeDevices(RemoveDevicesRequest removeDevicesRequest) {
        deviceBatchOperation(removeDevicesRequest, REMOVE);
    }

    public DeviceProfileResponse getDeviceProfile(String deviceName) {
        Preconditions.checkNotNull(deviceName, "device name should not be null.");
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, DEVICE, deviceName);

        return this.invokeHttpClient(internalRequest, DeviceProfileResponse.class);
    }

    public DeviceQueryResponse getDeviceProfiles(DeviceQueryRequest deviceQueryRequest) {
        Preconditions.checkNotNull(deviceQueryRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(deviceQueryRequest, HttpMethodName.PUT, DEVICE);
        internalRequest.addParameter(QUERY, null);

        return this.invokeHttpClient(internalRequest, DeviceQueryResponse.class);
    }

    public DeviceAccessDetail getDeviceAccessDetail(String deviceName) {
        Preconditions.checkNotNull(deviceName, "device name should not be null.");
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(), HttpMethodName.GET,
                DEVICE, deviceName, ACCESS_DETAIL);

        return this.invokeHttpClient(internalRequest, DeviceAccessDetail.class);
    }

    public void updateDeviceProfile(UpdateDeviceProfileRequest updateDeviceProfileRequest) {
        deviceBatchOperation(updateDeviceProfileRequest, UPDATE_PROFILE);
    }

    public void updateDeviceRegistry(UpdateDeviceRegistryRequest updateDeviceRegistryRequest) {
        deviceBatchOperation(updateDeviceRegistryRequest, UPDATE_REGISTRY);
    }

    public void disableDevices(DeviceOperationRequest deviceOperationRequest) {
        deviceBatchOperation(deviceOperationRequest, DISABLE);
    }

    public void enableDevices(DeviceOperationRequest deviceOperationRequest) {
        deviceBatchOperation(deviceOperationRequest, ENABLE);
    }

    public void rebootDevices(DeviceOperationRequest deviceOperationRequest) {
        deviceBatchOperation(deviceOperationRequest, REBOOT);
    }

    public GroupInfoResponse createGroup(CreateGroupRequest createGroupRequest, String clientToken) {
        return doCreation(createGroupRequest, clientToken, GROUP, GroupInfoResponse.class);
    }

    public void removeGroup(String groupId) {
        Preconditions.checkNotNull(groupId, "groupId should not be null.");
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.DELETE, GROUP, groupId);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void updateGroup(String groupId, UpdateGroupRequest updateGroupRequest) {
        Preconditions.checkNotNull(groupId, "groupId should not be null.");
        Preconditions.checkNotNull(updateGroupRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(updateGroupRequest, HttpMethodName.PUT, GROUP, groupId);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public GroupInfoResponse getGroup(String groupId) {
        Preconditions.checkNotNull(groupId, "groupId should not be null.");
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, GROUP, groupId);

        return this.invokeHttpClient(internalRequest, GroupInfoResponse.class);
    }

    public GroupListResponse getChildGroups(String groupId) {
        Preconditions.checkNotNull(groupId, "groupId should not be null.");
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, GROUP, groupId, CHILDREN);

        return this.invokeHttpClient(internalRequest, GroupListResponse.class);
    }

    public GroupListResponse getRootGroups() {
        return getGroups(ROOT);
    }

    public GroupListResponse getDeviceGroups() {
        return getGroups(DEVICE);
    }

    private <T extends AbstractBceResponse> T doCreation(AbstractBceRequest request,
            String clientToken, String objectPath, Class<T> responseClass) {
        Preconditions.checkNotNull(request, "request should not be null.");
        Preconditions.checkNotNull(clientToken, "client token should not be null");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, objectPath);
        internalRequest.addParameter(CLIENT_TOKEN, clientToken);

        return this.invokeHttpClient(internalRequest, responseClass);
    }

    private void deviceBatchOperation(AbstractBceRequest request, String parameter) {
        Preconditions.checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, DEVICE);
        internalRequest.addParameter(parameter, null);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    private GroupListResponse getGroups(String param) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(), HttpMethodName.GET, GROUP);
        internalRequest.addParameter(param, null);

        return this.invokeHttpClient(internalRequest, GroupListResponse.class);
    }

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
            String... pathVariables) {
        return IotDmClientHelper.createRequestForV1(bceRequest, httpMethod, this.getEndpoint(), null, pathVariables);
    }

}
