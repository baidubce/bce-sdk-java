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
package com.baidubce.services.modbus;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.BceCredentials;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.model.GenericAccountRequest;
import com.baidubce.services.modbus.model.BaseResponse;
import com.baidubce.services.modbus.model.CreateCustomFieldRequest;
import com.baidubce.services.modbus.model.CreateDataDescRequest;
import com.baidubce.services.modbus.model.DataDescRequest;
import com.baidubce.services.modbus.model.ListCustomFieldRespons;
import com.baidubce.services.modbus.model.ListDataDescResponse;
import com.baidubce.services.modbus.model.ListParserObjectResponse;
import com.baidubce.services.modbus.model.ParserObjectRequest;
import com.baidubce.services.modbus.model.QueryDataDescResponse;
import com.baidubce.services.modbus.model.UpdateDataDescRequest;
import com.baidubce.services.modbus.model.device.CreateDevice;
import com.baidubce.services.modbus.model.device.DeviceResponse;
import com.baidubce.services.modbus.model.device.ListDeviceRequest;
import com.baidubce.services.modbus.model.device.ListDeviceResponse;
import com.baidubce.services.modbus.model.device.UpdateDeviceRequest;
import com.baidubce.services.modbus.model.gateway.CreateGatewayRequest;
import com.baidubce.services.modbus.model.gateway.Gateway;
import com.baidubce.services.modbus.model.gateway.GatewayResponse;
import com.baidubce.services.modbus.model.gateway.ListGatewayRequest;
import com.baidubce.services.modbus.model.gateway.ListGatewayResponse;
import com.baidubce.services.modbus.model.gateway.UpdateGatewayRequest;
import com.baidubce.services.modbus.model.parserobject.CreateParserObjectRequest;
import com.baidubce.services.modbus.model.parserobject.ParserObjectResponse;
import com.baidubce.services.modbus.model.parserobject.UpdateParserObject;
import com.baidubce.services.modbus.model.pullrule.CreatePullRuleRequest;
import com.baidubce.services.modbus.model.pullrule.CreatePullRuleResponse;
import com.baidubce.services.modbus.model.pullrule.ListPullRuleRequest;
import com.baidubce.services.modbus.model.pullrule.ListPullRuleResponse;
import com.baidubce.services.modbus.model.pullrule.PullRuleResponse;
import com.baidubce.services.modbus.model.pullrule.PullRuleResponseWithDevice;
import com.baidubce.services.modbus.model.pullrule.UpdatePullRuleRequest;
import com.google.common.base.Preconditions;


/**
 * Provides the client for use iot modbus service.
 */
public class ModbusClient extends AbstractBceClient {

    private static final String ENDPOINT_HOST = "parser.iot.gz.baidubce.com";
    private static final String GATEWAY = "gateway";
    private static final String DEVICE = "device";
    private static final String PARSER_OBJECT = "parser-object";
    private static final String DATA_DESCRIPTION = "data-description";
    private static final String PULL_RULE = "pull-rule";
    private static final String CUSTOM_FIELD = "custom-field";
    private static final String ACTION = "action/deploy-config";

    private static final String PARSEROBJECTUUID = "parserObjectUuid";
    private AbstractBceRequest abstractBceRequest;

    public ModbusClient(BceClientConfiguration config) {
        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT_HOST) : config,
                ModbusClientHelper.MODBUS_HANDLERS);
        abstractBceRequest = new AbstractBceRequest() {
            @Override
            public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
                return null;
            }
        };
    }

    // 网关
    public GatewayResponse createGateway(CreateGatewayRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, GATEWAY);
        return this.invokeHttpClient(internalRequest, GatewayResponse.class);
    }

    public ListGatewayResponse listGateway(ListGatewayRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, GATEWAY);
        if (request.getState() != null) {
            internalRequest.addParameter("state", request.getState());
        }
        orderAndPagination(
                internalRequest,
                request.getOrder(),
                request.getOrderBy(),
                request.getPageNo(),
                request.getPageSize());
        ListGatewayResponse response = this.invokeHttpClient(internalRequest, ListGatewayResponse.class);
        for (Gateway gateway : response.getResult()) {
            gateway.setDeviceNum(getDeviceNumByGatewayUuid(gateway.getUuid()));
        }
        return response;
    }

    public GatewayResponse getGateway(String uuid) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(), HttpMethodName.GET, GATEWAY, uuid);
        GatewayResponse response = this.invokeHttpClient(internalRequest, GatewayResponse.class);
        response.setDeviceNum(getDeviceNumByGatewayUuid(response.getUuid()));
        return response;
    }

    public GatewayResponse updateGateway(String uuid, UpdateGatewayRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, GATEWAY, uuid);
        GatewayResponse response = this.invokeHttpClient(internalRequest, GatewayResponse.class);
        response.setDeviceNum(getDeviceNumByGatewayUuid(response.getUuid()));
        return response;
    }

    public GatewayResponse regenGatewayPassword(String uuid) {
        InternalRequest internalRequest = createRequest(
                abstractBceRequest,
                HttpMethodName.PUT,
                GATEWAY, uuid, "regen");
        GatewayResponse response = this.invokeHttpClient(internalRequest, GatewayResponse.class);
        response.setDeviceNum(getDeviceNumByGatewayUuid(response.getUuid()));
        return response;
    }

    public void deleteGateway(String uuid) {
        InternalRequest internalRequest = createRequest(
                abstractBceRequest,
                HttpMethodName.DELETE,
                GATEWAY, uuid);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public int getDeviceNumByGatewayUuid(String uuid) {
        ListDeviceRequest deviceRequest = new ListDeviceRequest();
        deviceRequest.setGatewayUuid(uuid);
        deviceRequest.setPageSize(5);
        ListDeviceResponse deviceResponse = this.listDevice(deviceRequest);
        return deviceResponse.getTotalCount();
    }

    // 子设备
    public DeviceResponse createDevice(CreateDevice request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, DEVICE);
        return this.invokeHttpClient(internalRequest, DeviceResponse.class);
    }

    public ListDeviceResponse listDevice(ListDeviceRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, DEVICE);
        if (request.getState() != null) {
            internalRequest.addParameter("state", request.getState());
        }
        if (request.getGatewayUuid() != null) {
            internalRequest.addParameter("gatewayUuid", request.getGatewayUuid());
        }
        if (request.getStatus() != null) {
            internalRequest.addParameter("status", request.getStatus());
        }
        orderAndPagination(
                internalRequest,
                request.getOrder(),
                request.getOrderBy(),
                request.getPageNo(),
                request.getPageSize());
        return this.invokeHttpClient(internalRequest, ListDeviceResponse.class);
    }

    public DeviceResponse getDevice(String uuid) {
        InternalRequest internalRequest = createRequest(abstractBceRequest, HttpMethodName.GET, DEVICE, uuid);
        return this.invokeHttpClient(internalRequest, DeviceResponse.class);
    }

    public DeviceResponse updateDevice(String uuid, UpdateDeviceRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, DEVICE, uuid);
        return this.invokeHttpClient(internalRequest, DeviceResponse.class);
    }

    public void deleteDevice(String uuid) {
        InternalRequest internalRequest = createRequest(
                abstractBceRequest,
                HttpMethodName.DELETE,
                DEVICE, uuid);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // ParserObject
    public ParserObjectResponse createParserObject(CreateParserObjectRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, PARSER_OBJECT);
        return this.invokeHttpClient(internalRequest, ParserObjectResponse.class);
    }
    public ListParserObjectResponse listParserObject(ParserObjectRequest parserObjectRequest) {
        return listParserObject(parserObjectRequest, null, null, null, null, null);
    }
    public ListParserObjectResponse listParserObject(ParserObjectRequest parserObjectRequest,
                                                       String order,
                                                       String orderBy,
                                                       String pageNo,
                                                       String pageSize,
                                                       String q) {
        InternalRequest internalRequest = createRequest(parserObjectRequest, HttpMethodName.GET, PARSER_OBJECT);
        if (parserObjectRequest.getState() != null) {
            internalRequest.addParameter("state", parserObjectRequest.getState());
        }
        if (parserObjectRequest.getGatewayUuid() != null) {
            internalRequest.addParameter("gatewayUuid", parserObjectRequest.getGatewayUuid());
        }
        orderAndPagination(internalRequest, order, orderBy, pageNo, pageSize, q);
        return this.invokeHttpClient(internalRequest, ListParserObjectResponse.class);
    }

    public ParserObjectResponse getParserObject(String uuid) {
        InternalRequest internalRequest = createRequest(abstractBceRequest, HttpMethodName.GET, PARSER_OBJECT, uuid);
        return this.invokeHttpClient(internalRequest, ParserObjectResponse.class);
    }

    public ParserObjectResponse updateParserObject(String uuid, UpdateParserObject request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, PARSER_OBJECT, uuid);
        return this.invokeHttpClient(internalRequest, ParserObjectResponse.class);
    }

    public void deleteParserObject(String uuid) {
        InternalRequest internalRequest = createRequest(
                abstractBceRequest,
                HttpMethodName.DELETE,
                PARSER_OBJECT, uuid);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // DataDescription 通讯地址表
    public QueryDataDescResponse createDataDesc(CreateDataDescRequest createDataDescRequest) {
        Preconditions.checkNotNull(createDataDescRequest.getParserObjectUuid(), "ParserObjectUuid should not be null.");
        Preconditions.checkNotNull(createDataDescRequest.getLength(), "Length should not be null.");
        Preconditions.checkNotNull(createDataDescRequest.getAddress(), "Address should not be null.");
        Preconditions.checkNotNull(createDataDescRequest.getKind(), "Kind should not be null.");
        Preconditions.checkNotNull(createDataDescRequest.getName(), "Name should not be null.");
        if (createDataDescRequest.getBit() == null) {
            createDataDescRequest.setBit(-1);
        }
        if (createDataDescRequest.getUnit() == "N/A") {
            createDataDescRequest.setUnit("");
        }
        InternalRequest internalRequest = createRequest(createDataDescRequest, HttpMethodName.POST, DATA_DESCRIPTION);
        return this.invokeHttpClient(internalRequest, QueryDataDescResponse.class);
    }

    public ListDataDescResponse listDataDesc(DataDescRequest dataDescRequest) {
        return listDataDesc(dataDescRequest, null, null, null, null, null);
    }
    public ListDataDescResponse listDataDesc(DataDescRequest dataDescRequest,
                                                      String order,
                                                      String orderBy,
                                                      String pageNo,
                                                      String pageSize,
                                                      String q) {
        Preconditions.checkNotNull(dataDescRequest.getParserObjectUuid(), "ParserObjectUuid should not be null.");
        InternalRequest internalRequest = createRequest(dataDescRequest, HttpMethodName.GET, DATA_DESCRIPTION);
        internalRequest.addParameter(PARSEROBJECTUUID, dataDescRequest.getParserObjectUuid());
        if (dataDescRequest.getState() != null) {
            internalRequest.addParameter("state", dataDescRequest.getState());
        }
        if (dataDescRequest.getAddressStart() != null) {
            internalRequest.addParameter("addressStart", dataDescRequest.getAddressStart().toString());
        }
        orderAndPagination(internalRequest, order, orderBy, pageNo, pageSize, q);
        return this.invokeHttpClient(internalRequest, ListDataDescResponse.class);
    }

    public QueryDataDescResponse queryDataDesc(String dataDescriptionUuid) {
        Preconditions.checkNotNull(dataDescriptionUuid, "dataDescriptionUuid should not be null.");
        InternalRequest internalRequest = createRequest(abstractBceRequest,
                HttpMethodName.GET,
                DATA_DESCRIPTION,
                dataDescriptionUuid);
        return this.invokeHttpClient(internalRequest, QueryDataDescResponse.class);
    }

    public QueryDataDescResponse updateDataDesc(UpdateDataDescRequest updateDataDescRequest) {
        Preconditions.checkNotNull(updateDataDescRequest.getDataDescriptionUuid(),
                "DataDescriptionUuid should not be null.");
        InternalRequest internalRequest = createRequest(updateDataDescRequest,
                HttpMethodName.PUT,
                DATA_DESCRIPTION,
                updateDataDescRequest.getDataDescriptionUuid());
        return this.invokeHttpClient(internalRequest, QueryDataDescResponse.class);
    }

    public AbstractBceResponse deleteDataDesc(String dataDescriptionUuid) {
        Preconditions.checkNotNull(dataDescriptionUuid, "DataDescriptionUuid should not be null.");
        InternalRequest internalRequest = createRequest(abstractBceRequest,
                HttpMethodName.DELETE,
                DATA_DESCRIPTION,
                dataDescriptionUuid);
        return this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // pull rule
    public CreatePullRuleResponse createPullRule(CreatePullRuleRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, PULL_RULE);
        return this.invokeHttpClient(internalRequest, CreatePullRuleResponse.class);
    }

    public ListPullRuleResponse listPullRule(ListPullRuleRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, PULL_RULE);
        if (request.getParserObjectUuid() != null) {
            internalRequest.addParameter("parserObjectUuid", request.getParserObjectUuid());
        }
        if (request.isWithDevice() != null) {
            internalRequest.addParameter("withDevice", Boolean.toString(request.isWithDevice()));
        }
        orderAndPagination(
                internalRequest,
                request.getOrder(),
                request.getOrderBy(),
                request.getPageNo(),
                request.getPageSize());
        return this.invokeHttpClient(internalRequest, ListPullRuleResponse.class);
    }

    public PullRuleResponseWithDevice getPullRule(String uuid) {
        InternalRequest internalRequest = createRequest(abstractBceRequest, HttpMethodName.GET, PULL_RULE, uuid);
        internalRequest.addParameter("withDevice", Boolean.toString(true));
        return this.invokeHttpClient(internalRequest, PullRuleResponseWithDevice.class);
    }

    public PullRuleResponse updatePullRule(String uuid, UpdatePullRuleRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, PULL_RULE, uuid);
        return this.invokeHttpClient(internalRequest, PullRuleResponse.class);
    }

    public void deletePullRule(String uuid) {
        InternalRequest internalRequest = createRequest(
                abstractBceRequest,
                HttpMethodName.DELETE,
                PULL_RULE, uuid);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // 下发配置
    public void deployGateway() {
        InternalRequest internalRequest = createRequest(
                abstractBceRequest,
                HttpMethodName.POST,
                ACTION);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // 高级设置
    public BaseResponse createCustomField(String name) {
        Preconditions.checkNotNull(name, "name should not be null.");
        CreateCustomFieldRequest createCustomFieldRequest = (new CreateCustomFieldRequest()).withName(name);
        InternalRequest internalRequest = createRequest(createCustomFieldRequest,
                HttpMethodName.POST,
                CUSTOM_FIELD);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    public ListCustomFieldRespons listCustomField() {
        InternalRequest internalRequest = createRequest(abstractBceRequest,
                HttpMethodName.GET,
                CUSTOM_FIELD);
        return this.invokeHttpClient(internalRequest, ListCustomFieldRespons.class);
    }

    public BaseResponse deleteCustomField(String name) {
        Preconditions.checkNotNull(name, "name should not be null.");
        CreateCustomFieldRequest createCustomFieldRequest = (new CreateCustomFieldRequest()).withName(name);
        InternalRequest internalRequest = createRequest(createCustomFieldRequest,
                HttpMethodName.PUT,
                CUSTOM_FIELD,
                "delete");
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          String... pathVariables) {
        return ModbusClientHelper.createRequest(bceRequest, httpMethod, this.getEndpoint(), null, pathVariables);
    }

    private void orderAndPagination(InternalRequest internalRequest,
                                     String order,
                                     String orderBy,
                                     String pageNo,
                                     String pageSize,
                                     String q) {
        if (order != null) {
            internalRequest.addParameter("order", order);
        }
        if (orderBy != null) {
            internalRequest.addParameter("orderBy", orderBy);
        }
        if (pageNo != null) {
            internalRequest.addParameter("pageNo", pageNo);
        }
        if (pageSize != null) {
            internalRequest.addParameter("pageSize", pageSize);
        }
        if (q != null) {
            internalRequest.addParameter("q", q);
        }
    }

    private void orderAndPagination(InternalRequest internalRequest,
                                    String order,
                                    String orderBy,
                                    int pageNo,
                                    int pageSize) {
        if (order != null) {
            internalRequest.addParameter("order", order);
        }
        if (orderBy != null) {
            internalRequest.addParameter("orderBy", orderBy);
        }
        if (pageNo > 0) {
            internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        }
        if (pageSize > 0) {
            internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        }
    }

}
