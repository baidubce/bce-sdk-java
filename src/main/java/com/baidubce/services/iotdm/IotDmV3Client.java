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
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.model.GenericAccountRequest;
import com.baidubce.services.iotdm.model.v3.device.CreateDeviceRequest;
import com.baidubce.services.iotdm.model.v3.device.DeviceAccessDetailResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceListRequest;
import com.baidubce.services.iotdm.model.v3.device.DeviceListResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceProfileListResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceProfileResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceViewResponse;
import com.baidubce.services.iotdm.model.v3.device.UpdateDeviceProfileRequest;
import com.baidubce.services.iotdm.model.v3.device.UpdateDeviceRegistryRequest;
import com.baidubce.services.iotdm.model.v3.device.UpdateDeviceViewRequest;
import com.baidubce.services.iotdm.model.v3.schema.SchemaCreateRequest;
import com.baidubce.services.iotdm.model.v3.schema.SchemaCreateResponse;
import com.baidubce.services.iotdm.model.v3.schema.SchemaListResponse;
import com.baidubce.services.iotdm.model.v3.schema.SchemaResponse;
import com.baidubce.services.iotdm.model.v3.schema.SchemaUpdateRequest;
import com.google.common.base.Preconditions;

public class IotDmV3Client extends AbstractBceClient {

    private static final String ENDPOINT_HOST = "iotdm.gz.baidubce.com";

    private static final String DEVICE = "device";
    private static final String ACCESS_DETAIL = "accessDetail";
    private static final String REMOVE = "remove";
    private static final String RESET = "reset";
    private static final String UPDATE_PROFILE = "updateProfile";
    private static final String UPDATE_REGISTRY = "updateRegistry";
    private static final String UPDATE_SECRET_KEY = "updateSecretKey";

    private static final String DEVICE_VIEW = "deviceView";
    private static final String UPDATE_VIEW = "updateView";

    private static final String SCHEMA = "schema";

    private static final String NULL_DEVICE_NAME = "device name should not be null.";
    private static final String NULL_REQUEST = "request should not be null.";
    private static final String NULL_SCHEMA_ID = "schema id should not be null.";

    public IotDmV3Client(BceClientConfiguration config) {
        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT_HOST) : config,
                IotDmClientHelper.IOT_DM_HANDLERS);
    }

    public DeviceAccessDetailResponse createDevice(CreateDeviceRequest createDeviceRequest) {
        return doCreation(createDeviceRequest, DeviceAccessDetailResponse.class, DEVICE);
    }

    public DeviceListResponse removeDevices(DeviceListRequest deviceListRequest) {
        return doOperation(deviceListRequest, DeviceListResponse.class, REMOVE, DEVICE);
    }

    public DeviceProfileResponse getDeviceProfile(String deviceName) {
        Preconditions.checkNotNull(deviceName, NULL_DEVICE_NAME);
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, DEVICE, deviceName);
        return this.invokeHttpClient(internalRequest, DeviceProfileResponse.class);
    }

    public DeviceProfileResponse updateDeviceProfile(String deviceName,
            UpdateDeviceProfileRequest updateDeviceProfileRequest) {
        return doOperation(updateDeviceProfileRequest, DeviceProfileResponse.class,
                UPDATE_PROFILE, DEVICE, deviceName);
    }

    public DeviceProfileListResponse getDeviceProfiles(int pageNo, int pageSize,
            String orderBy, String order, String name, String value, String favourite) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(), HttpMethodName.GET, DEVICE);
        addDeviceQueryParas(internalRequest, pageNo, pageSize, orderBy, order, name, value, favourite);
        return this.invokeHttpClient(internalRequest, DeviceProfileListResponse.class);
    }

    public DeviceAccessDetailResponse getDeviceAccessDetail(String deviceName) {
        Preconditions.checkNotNull(deviceName, NULL_DEVICE_NAME);
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, DEVICE, deviceName, ACCESS_DETAIL);
        return this.invokeHttpClient(internalRequest, DeviceAccessDetailResponse.class);
    }

    public DeviceProfileResponse updateDeviceRegistry(String deviceName,
            UpdateDeviceRegistryRequest updateDeviceRegistryRequest) {
        return doOperation(updateDeviceRegistryRequest, DeviceProfileResponse.class,
                UPDATE_REGISTRY, DEVICE, deviceName);
    }

    public DeviceAccessDetailResponse updateDeviceSecretKey(String deviceName) {
        return doOperation(new GenericAccountRequest(), DeviceAccessDetailResponse.class,
                UPDATE_SECRET_KEY, DEVICE, deviceName);
    }

    public DeviceListResponse resetDevices(DeviceListRequest request) {
        return doOperation(request, DeviceListResponse.class, RESET, DEVICE);
    }

    public DeviceViewResponse getDeviceView(String deviceName) {
        Preconditions.checkNotNull(deviceName, NULL_DEVICE_NAME);
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, DEVICE_VIEW, deviceName);
        return this.invokeHttpClient(internalRequest, DeviceViewResponse.class);
    }

    public DeviceViewResponse updateDeviceView(String deviceName,
            UpdateDeviceViewRequest updateDeviceViewRequest) {
        return doOperation(updateDeviceViewRequest, DeviceViewResponse.class,
                UPDATE_VIEW, DEVICE_VIEW, deviceName);
    }

    public SchemaCreateResponse createSchema(SchemaCreateRequest schemaCreateRequest) {
        return doCreation(schemaCreateRequest, SchemaCreateResponse.class, SCHEMA);
    }

    public SchemaResponse getSchema(String schemaId) {
        Preconditions.checkNotNull(schemaId, NULL_SCHEMA_ID);
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, SCHEMA, schemaId);
        return this.invokeHttpClient(internalRequest, SchemaResponse.class);
    }

    public SchemaListResponse getSchemas(int pageNo, int pageSize, String orderBy, String order, String key) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(), HttpMethodName.GET, SCHEMA);
        addSchemaQueryParas(internalRequest, pageNo, pageSize, orderBy, order, key);
        return this.invokeHttpClient(internalRequest, SchemaListResponse.class);
    }

    public void updateSchema(String schemaId,
            SchemaUpdateRequest schemaUpdateRequest) {
        this.doOperation(schemaUpdateRequest, AbstractBceResponse.class, null, SCHEMA, schemaId);
    }

    public void deleteSchema(String schemaId) {
        Preconditions.checkNotNull(schemaId, NULL_SCHEMA_ID);
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(), HttpMethodName.DELETE,
                SCHEMA, schemaId);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);

    }

    private <T extends AbstractBceResponse> T doCreation(AbstractBceRequest request,
            Class<T> responseClass, String objectPath) {
        Preconditions.checkNotNull(request, NULL_REQUEST);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, objectPath);

        return this.invokeHttpClient(internalRequest, responseClass);
    }

    private <T extends AbstractBceResponse> T doOperation(AbstractBceRequest request,
            Class<T> responseClass, String parameter, String... pathVariables) {
        Preconditions.checkNotNull(request, NULL_REQUEST);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, pathVariables);
        if (parameter != null) {
            internalRequest.addParameter(parameter, null);
        }

        return this.invokeHttpClient(internalRequest, responseClass);
    }

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
            String... pathVariables) {
        return IotDmClientHelper.createRequestForV3(bceRequest, httpMethod, this.getEndpoint(), null, pathVariables);
    }

    private void addDeviceQueryParas(InternalRequest internalRequest,
            int pageNo, int pageSize, String orderBy, String order, String name, String value, String favourite) {
        addOrderAndPaginationParas(internalRequest, pageNo, pageSize, orderBy, order);
        if (name != null) {
            internalRequest.addParameter("name", name);
        }
        if (value != null) {
            internalRequest.addParameter("value", value);
        }
        if (favourite != null) {
            internalRequest.addParameter("favourite", favourite);
        }
    }

    private void addSchemaQueryParas(InternalRequest internalRequest,
            int pageNo, int pageSize, String orderBy, String order, String key) {
        addOrderAndPaginationParas(internalRequest, pageNo, pageSize, orderBy, order);
        if (key != null) {
            internalRequest.addParameter("key", key);
        }
    }

    private void addOrderAndPaginationParas(InternalRequest internalRequest,
            int pageNo, int pageSize, String orderBy, String order) {
        internalRequest.addParameter("pageNo", String.valueOf(pageNo));
        internalRequest.addParameter("pageSize", String.valueOf(pageSize));
        if (orderBy != null) {
            internalRequest.addParameter("orderBy", orderBy);
        }
        if (order != null) {
            internalRequest.addParameter("order", order);
        }
    }

}
