/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotshc;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.GenericAccountRequest;
import com.baidubce.services.iotshc.model.CommonListRequest;
import com.baidubce.services.iotshc.model.CommonResponse;
import com.baidubce.services.iotshc.model.CommonResult;
import com.baidubce.services.iotshc.model.ai.TtsServiceRequest;
import com.baidubce.services.iotshc.model.ai.TtsServiceResponse;
import com.baidubce.services.iotshc.model.ai.UnitServiceRequest;
import com.baidubce.services.iotshc.model.ai.UnitServiceResponse;
import com.baidubce.services.iotshc.model.deivce.DeviceInfo;
import com.baidubce.services.iotshc.model.deivce.DeviceInfoListRequest;
import com.baidubce.services.iotshc.model.deivce.DeviceInfoListResponse;
import com.baidubce.services.iotshc.model.deivce.ImportBatchDevicesRequest;
import com.baidubce.services.iotshc.model.factory.FactoryInfo;
import com.baidubce.services.iotshc.model.factory.FactoryInfoListResponse;
import com.baidubce.services.iotshc.model.factory.UpdateFactoryInfoRequest;
import com.baidubce.services.iotshc.model.mqtt.GetMqttInfoRequest;
import com.baidubce.services.iotshc.model.mqtt.GetMqttInfoResponse;
import com.baidubce.services.iotshc.model.mqtt.SendMessageRequest;
import com.baidubce.services.iotshc.model.product.ProductKeyInfo;
import com.baidubce.services.iotshc.model.product.ProductKeyInfoListRequest;
import com.baidubce.services.iotshc.model.product.ProductKeyInfoListResponse;
import com.baidubce.services.iotshc.model.product.ProductKeyTypeListResponse;
import com.baidubce.services.iotshc.model.product.UpdateProductKeyInfoRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides the client for accessing the IotShc.
 */
public class IotShcClient extends AbstractBceClient {

    private static final String ENDPOINT = "smarthome.baidubce.com";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String VERSION = "v1";
    private static final String MANAGE = "manage";
    private static final String MESSAGE = "message";
    private static final String SERVICE = "service";

    private static final String FACTORY = "factory";

    private static final String PRODUCT = "product";
    private static final String PRODUCT_TYPES = "types";

    private static final String DEVICE = "device";
    private static final String DELETE_BATCH_DEVICE = "delete/batch";
    private static final String DISABLE_DEVICE = "disable";
    private static final String ENABLE_DEVICE = "enable";

    private static final String MQTT = "mqtt";

    private static final String SEND = "send";
    private static final String BROADCAST = "broadcast";

    private static final String UNIT = "unit";
    private static final String TTS = "tts";

    /**
     * Responsible for handling HttpResponse.
     */
    private static final HttpResponseHandler[] HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler() };

    public IotShcClient(String accessKey, String secretKey) {
        this(new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(accessKey, secretKey))
                .withEndpoint(ENDPOINT));
    }

    public IotShcClient(BceClientConfiguration config) {
        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT) : config, HANDLERS);
    }

    public CommonResult deleteFactoryInfo(String fc) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE,
                MANAGE, FACTORY, fc);
        return invokeHttpClient(internalRequest, CommonResult.class);
    }

    public FactoryInfo updateFactoryInfo(String fc, UpdateFactoryInfoRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                MANAGE, FACTORY, fc);
        return invokeHttpClient(internalRequest, FactoryInfo.class);
    }

    public FactoryInfo getFactoryInfo(String fc) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                MANAGE, FACTORY, fc);
        return invokeHttpClient(internalRequest, FactoryInfo.class);
    }

    public FactoryInfoListResponse listFactoryInfo(CommonListRequest request) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                MANAGE, FACTORY);
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        return invokeHttpClient(internalRequest, FactoryInfoListResponse.class);
    }

    public CommonResult deleteProductKey(String fc, String pk) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE,
                MANAGE, PRODUCT, fc, pk);
        return invokeHttpClient(internalRequest, CommonResult.class);
    }

    public ProductKeyInfo updateProductKeyInfo(String fc, String pk, UpdateProductKeyInfoRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                MANAGE, PRODUCT, fc, pk);
        return invokeHttpClient(internalRequest, ProductKeyInfo.class);
    }

    public ProductKeyInfo getProductKeyInfo(String fc, String pk) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                MANAGE, PRODUCT, fc, pk);
        return invokeHttpClient(internalRequest, ProductKeyInfo.class);
    }

    public ProductKeyInfoListResponse listProductKeyInfo(ProductKeyInfoListRequest request) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                MANAGE, PRODUCT);
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        if (StringUtils.isNotEmpty(request.getFc())) {
            internalRequest.addParameter("fc", request.getFc());
        }
        return invokeHttpClient(internalRequest, ProductKeyInfoListResponse.class);
    }

    public ProductKeyTypeListResponse listProductKeyType(CommonListRequest request) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                MANAGE, PRODUCT, PRODUCT_TYPES);
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        return invokeHttpClient(internalRequest, ProductKeyTypeListResponse.class);
    }

    public CommonResponse importBatchDevices(ImportBatchDevicesRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                MANAGE, DEVICE);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResult deleteDeviceInfo(String fc, String pk, String ak) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE,
                MANAGE, DEVICE, fc, pk, ak);
        return invokeHttpClient(internalRequest, CommonResult.class);
    }

    public CommonResult deleteDevices(String fc, String pk) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE,
                MANAGE, DEVICE, fc, pk, DELETE_BATCH_DEVICE);
        return invokeHttpClient(internalRequest, CommonResult.class);
    }

    public CommonResult banDevice(String fc, String pk, String ak) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.PUT,
                MANAGE, DEVICE, fc, pk, ak, DISABLE_DEVICE);
        return invokeHttpClient(internalRequest, CommonResult.class);
    }

    public CommonResult activeDevice(String fc, String pk, String ak) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.PUT,
                MANAGE, DEVICE, fc, pk, ak, ENABLE_DEVICE);
        return invokeHttpClient(internalRequest, CommonResult.class);
    }

    public DeviceInfo getDeviceInfo(String fc, String pk, String ak) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                MANAGE, DEVICE, fc, pk, ak);
        return invokeHttpClient(internalRequest, DeviceInfo.class);
    }

    public DeviceInfoListResponse listDeviceInfo(DeviceInfoListRequest request) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                MANAGE, DEVICE);
        internalRequest.addParameter("fc", request.getFc());
        internalRequest.addParameter("pk", request.getPk());
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        if (StringUtils.isNotEmpty(request.getState())) {
            internalRequest.addParameter("state", request.getState());
        }
        if (StringUtils.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        return invokeHttpClient(internalRequest, DeviceInfoListResponse.class);
    }

    public GetMqttInfoResponse getMqttInfo(GetMqttInfoRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                MANAGE, MQTT);
        return invokeHttpClient(internalRequest, GetMqttInfoResponse.class);
    }

    public CommonResponse sendMqttMessage(SendMessageRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                MESSAGE, SEND);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse broadcastMqttMessage(SendMessageRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                MESSAGE, BROADCAST);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse sendTtsMessage(SendMessageRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                MESSAGE, TTS, SEND);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse broadcastTtsMessage(SendMessageRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                MESSAGE, TTS, BROADCAST);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public UnitServiceResponse invokeUnit(UnitServiceRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                SERVICE, UNIT);
        return invokeHttpClient(internalRequest, UnitServiceResponse.class);
    }

    public TtsServiceResponse invokeTts(TtsServiceRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                SERVICE, TTS);
        return invokeHttpClient(internalRequest, TtsServiceResponse.class);
    }

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION);
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        request.setCredentials(bceRequest.getRequestCredentials());
        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
            fillInHeadAndBody(bceRequest, request);
        }
        return request;
    }
    private void fillInHeadAndBody(AbstractBceRequest bceRequest, InternalRequest request) {
        byte[] content = toJson(bceRequest);
        request.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        request.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE);
        request.setContent(RestartableInputStream.wrap(content));
    }
    private byte[] toJson(AbstractBceRequest bceRequest) {
        String jsonStr = JsonUtils.toJsonString(bceRequest);
        try {
            return jsonStr.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
    }
}
