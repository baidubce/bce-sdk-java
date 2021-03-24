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

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.SignOptions;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.iothub.model.BaseRequest;
import com.baidubce.services.iothub.model.BaseResponse;
import com.baidubce.services.iothub.model.iotcore.AddPolicyRequest;
import com.baidubce.services.iothub.model.iotcore.AddPolicyResponse;
import com.baidubce.services.iothub.model.iotcore.AlgorithmType;
import com.baidubce.services.iothub.model.iotcore.CreateDeviceRequest;
import com.baidubce.services.iothub.model.iotcore.CreateDeviceResponse;
import com.baidubce.services.iothub.model.iotcore.CreateTemplateRequest;
import com.baidubce.services.iothub.model.iotcore.CreateTemplateResponse;
import com.baidubce.services.iothub.model.iotcore.GetDeviceResponse;
import com.baidubce.services.iothub.model.iotcore.GetDeviceSignatureResponse;
import com.baidubce.services.iothub.model.iotcore.GetTemplateResponse;
import com.baidubce.services.iothub.model.iotcore.PaginationResponse;
import com.baidubce.services.iothub.model.iotcore.ResetDeviceSecretResponse;
import com.baidubce.services.iothub.model.iotcore.ScrollPaginationResponse;
import com.baidubce.services.iothub.model.iotcore.SecretKeyRequest;
import com.baidubce.services.iothub.model.iotcore.UpdateDeviceRequest;
import com.baidubce.services.iothub.model.iotcore.UpdateDeviceResponse;
import com.baidubce.services.iothub.model.iotcore.UpdatePolicyRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the IoT Core.
 */
public class IoTCoreClient extends AbstractBceClient {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String ENDPOINT_HOST = "iot.baidubce.com";
    private static final String VERSION = "v1";
    private static final String IOT_CORE = "iotcore";
    private static final String[] HEADERS_TO_SIGN = { Headers.HOST, Headers.BCE_DATE };
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    private static final String DEVICE = "device";
    private static final String NEW = "new";
    private static final String SCROLL = "scroll";
    private static final String LIST = "list";
    private static final String SIGNATURE = "signature";
    private static final String SECRET = "secret";
    private static final String TEMPLATE = "template";
    private static final String POLICY = "policy";

    /**
     * Responsible for handling HttpResponse from all Iothub service calls.
     */
    private static final HttpResponseHandler[] IOTCORE_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler() };

    public IoTCoreClient(BceClientConfiguration config) {
        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT_HOST) : config,
                IOTCORE_HANDLERS);
    }

    public CreateDeviceResponse createDevice(String iotCoreId, CreateDeviceRequest createDeviceRequest) {
        checkNotNull(createDeviceRequest, "iotCoreId should not be null.");
        checkNotNull(createDeviceRequest, "request should not be null.");
        return this.invokeHttpClient(
                createRequest(createDeviceRequest, HttpMethodName.POST, iotCoreId, DEVICE, NEW),
                CreateDeviceResponse.class);
    }

    public GetDeviceResponse getDevice(String iotCoreId, String deviceName) {
        checkNotNull(iotCoreId, "iotCoreId should not be null.");
        checkNotNull(deviceName, "deviceName should not be null.");
        return this.invokeHttpClient(
                createRequest(new BaseRequest(), HttpMethodName.GET, iotCoreId, DEVICE, deviceName),
                GetDeviceResponse.class);
    }

    public UpdateDeviceResponse updateDevice(String iotCoreId, String deviceName,
                                             UpdateDeviceRequest updateDeviceRequest) {
        checkNotNull(iotCoreId, "iotCoreId should not be null.");
        checkNotNull(deviceName, "deviceName should not be null.");
        checkNotNull(updateDeviceRequest, "request should not be null.");
        return this.invokeHttpClient(
                createRequest(updateDeviceRequest, HttpMethodName.PUT, iotCoreId, DEVICE, deviceName),
                UpdateDeviceResponse.class);
    }

    public void deleteDevice(String iotCoreId, String deviceName) {
        checkNotNull(iotCoreId, "iotCoreId should not be null.");
        checkNotNull(deviceName, "deviceName should not be null.");
        this.invokeHttpClient(
                createRequest(new BaseRequest(), HttpMethodName.DELETE, iotCoreId, DEVICE, deviceName),
                BaseResponse.class);
    }

    public ScrollPaginationResponse<GetDeviceResponse> getDevices(String iotCoreId) {
        return getDevices(iotCoreId, System.currentTimeMillis(), 20, "");
    }

    public ScrollPaginationResponse<GetDeviceResponse> getDevices(
            String iotCoreId, long createTimeBefore, int pageSize, String deviceNamePrefix) {
        checkNotNull(iotCoreId, "iotCoreId should not be null.");
        checkNotNull(deviceNamePrefix, "deviceNamePrefix should not be null.");
        checkArgument(createTimeBefore > 0L, "Illegal create time before");
        checkArgument(pageSize > 0 && pageSize < 200, "Illegal page size");

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("createTimeBefore", String.valueOf(createTimeBefore));
        parameters.put("pageSize", String.valueOf(pageSize));
        parameters.put("name", deviceNamePrefix);
        ScrollPaginationResponse response = this.invokeHttpClient(
                createRequest(new BaseRequest(), HttpMethodName.GET, parameters, iotCoreId, DEVICE, SCROLL, LIST),
                ScrollPaginationResponse.class);
        List<GetDeviceResponse> responses = new ArrayList<GetDeviceResponse>();
        for (Object data : response.getData()) {
            responses.add(mapper.convertValue(data, GetDeviceResponse.class));
        }

        return new ScrollPaginationResponse<GetDeviceResponse>(
                responses, response.getTotal(), response.getPageSize(), response.getCreateTimeBefore());
    }

    public GetDeviceSignatureResponse getDeviceSignature(String iotCoreId, String deviceName) {
        return getDeviceSignature(iotCoreId, deviceName, 0L, AlgorithmType.MD5);
    }

    public GetDeviceSignatureResponse getDeviceSignature(String iotCoreId, String deviceName,
                                                         long timestamp, AlgorithmType algorithmType) {
        checkNotNull(iotCoreId, "iotCoreId should not be null.");
        checkNotNull(deviceName, "deviceName should not be null.");
        checkNotNull(algorithmType, "algorithmType should not be null.");
        checkArgument(timestamp >= 0L, "Illegal timestamp");

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("timestamp", String.valueOf(timestamp));
        parameters.put("algorithmType", algorithmType.name());
        return this.invokeHttpClient(
                createRequest(new BaseRequest(), HttpMethodName.GET,
                        parameters, iotCoreId, DEVICE, deviceName, SIGNATURE),
                GetDeviceSignatureResponse.class);
    }

    public ResetDeviceSecretResponse resetDeviceSecret(String iotCoreId, String deviceName) {
        return  resetDeviceSecret(iotCoreId, deviceName, null);
    }

    public ResetDeviceSecretResponse resetDeviceSecret(String iotCoreId, String deviceName, String secretKey) {
        checkNotNull(iotCoreId, "iotCoreId should not be null.");
        checkNotNull(deviceName, "deviceName should not be null.");
        BaseRequest request;
        if (secretKey != null) {
            request = SecretKeyRequest.builder()
                    .secretKey(secretKey)
                    .build();
        } else {
            request = new BaseRequest();
        }
        return this.invokeHttpClient(
                createRequest(request, HttpMethodName.PUT, iotCoreId, DEVICE, deviceName, SECRET),
                ResetDeviceSecretResponse.class);
    }

    // template
    public CreateTemplateResponse createTemplate(String iotCoreId, String templateName) {
        checkNotNull(iotCoreId, "iotCoreId should not be null.");
        checkNotNull(templateName, "templateName should not be null.");

        CreateTemplateRequest request = CreateTemplateRequest.builder()
                .name(templateName)
                .build();
        return this.invokeHttpClient(
                createRequest(request, HttpMethodName.POST, iotCoreId, TEMPLATE),
                CreateTemplateResponse.class);
    }

    public GetTemplateResponse getTemplate(String iotCoreId, String templateId) {
        checkNotNull(iotCoreId, "iotCoreId should not be null.");
        checkNotNull(templateId, "templateId should not be null.");

        return this.invokeHttpClient(
                createRequest(new BaseRequest(), HttpMethodName.GET, iotCoreId, TEMPLATE, templateId),
                GetTemplateResponse.class);
    }

    public PaginationResponse<GetTemplateResponse> getTemplates(String iotCoreId) {
        return getTemplates(iotCoreId, 1, 20);
    }

    public PaginationResponse<GetTemplateResponse> getTemplates(String iotCoreId, int pageNo, int pageSize) {
        checkNotNull(iotCoreId, "iotCoreId should not be null.");
        checkArgument(pageNo > 0, "Illegal page no");
        checkArgument(pageSize > 0 && pageSize < 200, "Illegal page size");

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("pageNo", String.valueOf(pageNo));
        parameters.put("pageSize", String.valueOf(pageSize));
        PaginationResponse response = this.invokeHttpClient(
                createRequest(new BaseRequest(), HttpMethodName.GET, parameters, iotCoreId, TEMPLATE, LIST),
                PaginationResponse.class);
        List<GetTemplateResponse> responses = new ArrayList<GetTemplateResponse>();
        for (Object data : response.getData()) {
            responses.add(mapper.convertValue(data, GetTemplateResponse.class));
        }
        return new PaginationResponse<GetTemplateResponse>(responses, response.getTotal(), response.getPageNo(),
                response.getPageSize(), response.getOrder(), response.getOrderBy());
    }

    public void deleteTemplate(String iotCoreId, String templateId) {
        checkNotNull(iotCoreId, "iotCoreId should not be null.");
        checkNotNull(templateId, "iotCoreId should not be null.");

        this.invokeHttpClient(
                createRequest(new BaseRequest(), HttpMethodName.DELETE, iotCoreId, TEMPLATE, templateId),
                BaseResponse.class);
    }

    public AddPolicyResponse addPolicy(String iotCoreId, String templateId, AddPolicyRequest request) {
        checkNotNull(iotCoreId, "iotCoreId should not be null.");
        checkNotNull(templateId, "iotCoreId should not be null.");
        checkNotNull(request, "request should not be null.");

        return this.invokeHttpClient(
                createRequest(request, HttpMethodName.POST, iotCoreId, TEMPLATE, templateId, POLICY),
                AddPolicyResponse.class);
    }

    public void updatePolicy(String iotCoreId, String templateId, String policyId, UpdatePolicyRequest request) {
        checkNotNull(iotCoreId, "iotCoreId should not be null.");
        checkNotNull(templateId, "iotCoreId should not be null.");
        checkNotNull(policyId, "iotCoreId should not be null.");
        checkNotNull(request, "request should not be null.");

        this.invokeHttpClient(
                createRequest(request, HttpMethodName.PUT, iotCoreId, TEMPLATE, templateId, POLICY, policyId),
                BaseResponse.class);
    }

    public void removePolicy(String iotCoreId, String templateId, String policyId) {
        checkNotNull(iotCoreId, "iotCoreId should not be null.");
        checkNotNull(templateId, "iotCoreId should not be null.");
        checkNotNull(policyId, "iotCoreId should not be null.");

        this.invokeHttpClient(
                createRequest(new BaseRequest(), HttpMethodName.DELETE,
                        iotCoreId, TEMPLATE, templateId, POLICY, policyId),
                BaseResponse.class);
    }

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          Map<String, String> parameters,
                                          String... pathVariables) {
        return createRequest(bceRequest, httpMethod, null, parameters, pathVariables);
    }

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          String... pathVariables) {
        return createRequest(bceRequest, httpMethod, null, null, pathVariables);
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest The original BCE request created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param signOptions The options for signature.
     * @param parameters The http query params.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object populated with endpoint, resource path and specific parameters to send.
     */

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          SignOptions signOptions, Map<String, String> parameters,
                                          String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION);
        path.add(IOT_CORE);

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        if (signOptions == null) {
            signOptions = new SignOptions();
            signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        }
        if (parameters != null) {
            request.setParameters(parameters);
        }
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());

        fillInHeadAndBody(bceRequest, request);
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
