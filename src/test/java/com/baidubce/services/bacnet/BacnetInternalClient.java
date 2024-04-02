package com.baidubce.services.bacnet;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bacnet.model.ListRequest;
import com.baidubce.services.bacnet.internal.CreateBacnetDeviceRequest;
import com.baidubce.services.bacnet.internal.CreateBacnetObjectRequest;
import com.baidubce.services.bacnet.internal.ListBacnetObjectInternalResponse;
import com.baidubce.services.bacnet.internal.UpdateBacnetDeviceRequest;
import com.baidubce.services.bacnet.internal.UpdateBacnetObjectBatchRequest;
import com.baidubce.services.bacnet.internal.UpdateLastActiveTimeRequest;
import com.baidubce.services.iotalarm.model.CommonResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanyoujun on 2017/10/19.
 */
public class BacnetInternalClient extends AbstractBceClient {
    private static final String VERSION = "v1";
    private static final String BACNET = "bacnet";
    private static final String GATEWAY = "gateway";
    private static final String DEVICE = "device";
    private static final String INTERNAL = "internal";
    private static final String OBJECT = "object";
    private static final String TYPE = "type";
    private static final String CREDENTIAL = "credential";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    private static final HttpResponseHandler[] HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler() };

    public BacnetInternalClient(BceClientConfiguration config) {
        super(config, HANDLERS);
    }

    // gateways
    public CommonResponse updateGatewayLastActiveTime(UpdateLastActiveTimeRequest request, long id) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.PUT, BACNET, INTERNAL, GATEWAY, String.valueOf(id));
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    // devices
    public CommonResponse createDevice(CreateBacnetDeviceRequest request, long gatewayid) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.POST, BACNET, INTERNAL, DEVICE, GATEWAY,
                        String.valueOf(gatewayid), DEVICE);
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse updateDevice(UpdateBacnetDeviceRequest request, long gatewayid, long instanceNumber) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.PUT, BACNET, INTERNAL, DEVICE, GATEWAY,
                        String.valueOf(gatewayid), DEVICE, String.valueOf(instanceNumber));
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    // objects
    public CommonResponse createObject(CreateBacnetObjectRequest request, long gatewayid, int instanceNumber) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.POST, BACNET, INTERNAL, OBJECT, GATEWAY,
                        String.valueOf(gatewayid), DEVICE, String.valueOf(instanceNumber));
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse updateObjectBatch(UpdateBacnetObjectBatchRequest request,
                                            long gatewayid, int instanceNumber) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.PUT, BACNET, INTERNAL, OBJECT, GATEWAY,
                        String.valueOf(gatewayid), DEVICE, String.valueOf(instanceNumber));
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public ListBacnetObjectInternalResponse listBacnetObject(ListRequest request, long gatewayid,
                                                             int instanceNumber, int objectType) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.GET, BACNET, INTERNAL, OBJECT, GATEWAY,
                        String.valueOf(gatewayid), DEVICE, String.valueOf(instanceNumber),
                        TYPE, String.valueOf(objectType));
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));

        return this.invokeHttpClient(internalRequest, ListBacnetObjectInternalResponse.class);
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
