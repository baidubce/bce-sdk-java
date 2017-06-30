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

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.internal.InternalRequest;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.model.GenericAccountRequest;
import com.baidubce.services.iotdm.model.CreateEndpointResponse;
import com.baidubce.services.iotdm.model.CreateEndpointRequest;
import com.baidubce.services.iotdm.model.GetEndpointsResponse;
import com.baidubce.services.iotdm.model.CreateDevicesRequest;
import com.baidubce.services.iotdm.model.CreateDevicesResponse;
import com.baidubce.services.iotdm.model.DeviceAccessDetail;
import com.baidubce.services.iotdm.model.DeviceOperationRequest;
import com.baidubce.services.iotdm.model.DeviceProfileResponse;
import com.baidubce.services.iotdm.model.DeviceQueryRequest;
import com.baidubce.services.iotdm.model.DeviceQueryResponse;
import com.baidubce.services.iotdm.model.RemoveDevicesRequest;
import com.baidubce.services.iotdm.model.UpdateDeviceProfileRequest;
import com.baidubce.services.iotdm.model.UpdateDeviceRegistryRequest;
import com.google.common.base.Preconditions;

public class IotDmV2Client extends AbstractBceClient {

    private static final String ENDPOINT_HOST = "iotdm.gz.baidubce.com";
    private static final String DEVICE = "device";
    private static final String CLIENT_TOKEN = "clientToken";
    private static final String CLEAN_HUB = "cleanHub";
    private static final String ACCESS_DETAIL = "accessDetail";
    private static final String REMOVE = "remove";
    private static final String DISABLE = "disable";
    private static final String ENABLE = "enable";
    private static final String QUERY = "query";
    private static final String REBOOT = "reboot";
    private static final String UPDATE_PROFILE = "updateProfile";
    private static final String UPDATE_REGISTRY = "updateRegistry";

    public IotDmV2Client (BceClientConfiguration config) {

        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT_HOST) : config,
                IotDmClientHelper.IOT_DM_HANDLERS);
    }

    public CreateEndpointResponse createEndpoint(CreateEndpointRequest createEndpointRequest, String clientToken) {
        Preconditions.checkNotNull(createEndpointRequest, "request should not be null.");
        Preconditions.checkNotNull(clientToken, "client token should not be null");
        InternalRequest internalRequest = createRequest(createEndpointRequest, HttpMethodName.POST, null);
        internalRequest.addParameter(CLIENT_TOKEN, clientToken);
        return this.invokeHttpClient(internalRequest, CreateEndpointResponse.class);
    }

    public void removeEndpoint(String endpointName, String cleanHub) {
        Preconditions.checkNotNull(endpointName, "request should not be null.");
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.DELETE, endpointName);
        internalRequest.addParameter(CLEAN_HUB, cleanHub);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public GetEndpointsResponse getEndpoints() {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(), HttpMethodName.GET, null);
        return this.invokeHttpClient(internalRequest, GetEndpointsResponse.class);
    }

    public CreateDevicesResponse createDevices(String endpointName, CreateDevicesRequest createDevicesRequest,
                                               String clientToken) {
        return doCreation(createDevicesRequest, endpointName, DEVICE, clientToken, CreateDevicesResponse.class);
    }

    public void removeDevices(String endpointName, RemoveDevicesRequest removeDevicesRequest) {
        deviceOperation(removeDevicesRequest, endpointName, REMOVE);
    }

    public DeviceProfileResponse getDeviceProfile(String endpointName, String deviceName) {
        Preconditions.checkNotNull(endpointName, "endpoint name should not be null.");
        Preconditions.checkNotNull(deviceName, "device name should not be null.");
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, endpointName, DEVICE, deviceName);
        return this.invokeHttpClient(internalRequest, DeviceProfileResponse.class);
    }

    public DeviceQueryResponse getDeviceProfiles(String endpointName, DeviceQueryRequest deviceQueryRequest) {
        Preconditions.checkNotNull(deviceQueryRequest, "request should not be null.");
        Preconditions.checkNotNull(endpointName, "endpoint name should not be null.");
        InternalRequest internalRequest = createRequest(deviceQueryRequest,
                HttpMethodName.PUT, endpointName, DEVICE);
        internalRequest.addParameter(QUERY, null);
        return this.invokeHttpClient(internalRequest, DeviceQueryResponse.class);
    }

    public DeviceAccessDetail getDeviceAccessDetail(String endpointName, String deviceName) {
        Preconditions.checkNotNull(deviceName, "device name should not be null.");
        Preconditions.checkNotNull(endpointName, "endpoint name should not be null.");

        InternalRequest internalRequest = createRequest(new GenericAccountRequest(), HttpMethodName.GET,
                endpointName, DEVICE, deviceName, ACCESS_DETAIL);

        return this.invokeHttpClient(internalRequest, DeviceAccessDetail.class);
    }

    public void updateDeviceProfile(String endpointName, UpdateDeviceProfileRequest updateDeviceProfileRequest) {
        deviceOperation(updateDeviceProfileRequest, endpointName, UPDATE_PROFILE);
    }

    public void updateDeviceRegistry(String endpointName, UpdateDeviceRegistryRequest updateDeviceRegistryRequest) {
        deviceOperation(updateDeviceRegistryRequest, endpointName, UPDATE_REGISTRY);
    }

    public void disableDevices(String endpointName, DeviceOperationRequest deviceOperationRequest) {
        deviceOperation(deviceOperationRequest, endpointName, DISABLE);
    }

    public void enableDevices(String endpointName, DeviceOperationRequest deviceOperationRequest) {
        deviceOperation(deviceOperationRequest, endpointName, ENABLE);
    }

    public void rebootDevices(String endpointName, DeviceOperationRequest deviceOperationRequest) {
        deviceOperation(deviceOperationRequest, endpointName, REBOOT);
    }

    private <T extends AbstractBceResponse> T doCreation(AbstractBceRequest request,
                                                         String endpointName, String objectPath,
                                                         String clientToken, Class<T> responseClass) {
        Preconditions.checkNotNull(request, "request should not be null.");
        Preconditions.checkNotNull(endpointName, "endpoint name should not be null");
        Preconditions.checkNotNull(clientToken, "client token should not be null");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, endpointName, objectPath);
        internalRequest.addParameter(CLIENT_TOKEN, clientToken);
        return this.invokeHttpClient(internalRequest, responseClass);
    }

    private void deviceOperation(AbstractBceRequest request, String endpointName, String parameter) {
        Preconditions.checkNotNull(request, "request should not be null.");
        Preconditions.checkNotNull(endpointName, "endpoint name should not be null");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, endpointName, DEVICE);
        internalRequest.addParameter(parameter, null);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          String... pathVariables) {
        return IotDmClientHelper.createRequestForV2(bceRequest, httpMethod, this.getEndpoint(), null, pathVariables);
    }
}
