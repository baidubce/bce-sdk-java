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
import com.baidubce.model.GenericAccountRequest;
import com.baidubce.services.bacnet.model.BacnetDevice;
import com.baidubce.services.bacnet.model.BacnetGateway;
import com.baidubce.services.bacnet.model.BacnetGatewayCredential;
import com.baidubce.services.bacnet.model.BacnetObject;
import com.baidubce.services.bacnet.model.CreateBacnetGatewayRequest;
import com.baidubce.services.bacnet.model.ListBacnetDeviceResponse;
import com.baidubce.services.bacnet.model.ListBacnetGatewayResponse;
import com.baidubce.services.bacnet.model.ListBacnetObjectResponse;
import com.baidubce.services.bacnet.model.ListObjectPresentValueRequest;
import com.baidubce.services.bacnet.model.ListObjectPresentValueResponse;
import com.baidubce.services.bacnet.model.ListRequest;
import com.baidubce.services.bacnet.model.SubBacnetObjectRequest;
import com.baidubce.services.bacnet.model.UpdateBacnetGatewayRequest;
import com.baidubce.services.bacnet.model.UpdateDestTopicsRequest;
import com.baidubce.services.bacnet.model.UpdateBacnetObjectPresentValueRequest;
import com.baidubce.services.iotalarm.model.CommonResponse;
import com.baidubce.services.bacnet.model.IdResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanyoujun on 2017/10/18.
 */
public class BacnetClient extends AbstractBceClient {
    public static final String ENDPOINT_GZ = "parser.iot.gz.baidubce.com";
    public static final String ENDPOINT_BJ = "parser.iot.bj.baidubce.com";
    private static final String VERSION = "v1";
    private static final String BACNET = "bacnet";
    private static final String GATEWAY = "gateway";
    private static final String DEVICE = "device";
    private static final String OBJECT = "object";
    private static final String TYPE = "type";
    private static final String INST = "inst";
    private static final String SUB = "sub";
    private static final String UNSUB = "unsub";
    private static final String PRESENT_VALUE = "presentvalue";
    private static final String REFRESH = "refresh";
    private static final String CREDENTIAL = "credential";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    private static final HttpResponseHandler[] HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler() };

    public BacnetClient(BceClientConfiguration config) {
        super(config, HANDLERS);
    }

    // gateways
    public IdResponse createGateway(CreateBacnetGatewayRequest request) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.POST, BACNET, GATEWAY);
        return this.invokeHttpClient(internalRequest, IdResponse.class);
    }

    public BacnetGateway getGateway(long id) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.GET, BACNET, GATEWAY, String.valueOf(id));
        return this.invokeHttpClient(internalRequest, BacnetGateway.class);
    }

    public BacnetGatewayCredential getGatewayCredential(long id) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.GET, BACNET, GATEWAY, String.valueOf(id),
                        CREDENTIAL);
        return this.invokeHttpClient(internalRequest, BacnetGatewayCredential.class);
    }

    public CommonResponse deleteGateway(long id) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.DELETE, BACNET, GATEWAY, String.valueOf(id));
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse updateGateway(UpdateBacnetGatewayRequest request, long id) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.PUT, BACNET, GATEWAY, String.valueOf(id));
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public ListBacnetGatewayResponse listGateway(ListRequest request) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.GET, BACNET, GATEWAY);
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));

        return this.invokeHttpClient(internalRequest, ListBacnetGatewayResponse.class);
    }

    // bacnet devices

    public BacnetDevice getDevice(long gatewayid, long instanceNumber) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.GET, BACNET, DEVICE, GATEWAY,
                        String.valueOf(gatewayid), DEVICE, String.valueOf(instanceNumber));
        return this.invokeHttpClient(internalRequest, BacnetDevice.class);
    }

    public ListBacnetDeviceResponse listDevice(ListRequest request, long gatewayid) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.GET, BACNET, DEVICE, GATEWAY, String.valueOf(gatewayid), DEVICE);
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));

        return this.invokeHttpClient(internalRequest, ListBacnetDeviceResponse.class);
    }

    public CommonResponse deleteDevice(long gatewayid, long instanceNumber) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.DELETE, BACNET, DEVICE, GATEWAY,
                        String.valueOf(gatewayid), DEVICE, String.valueOf(instanceNumber));
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse updateDeviceDestTopic(UpdateDestTopicsRequest request, long gatewayid, long instanceNumber) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.PUT, BACNET, DEVICE, GATEWAY,
                        String.valueOf(gatewayid), DEVICE, String.valueOf(instanceNumber));
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse updateObjectDestTopic(UpdateDestTopicsRequest request, long gatewayid, long instanceNumber,
                                                String objectType, int objectInstance) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.PUT, BACNET, OBJECT, GATEWAY,
                        String.valueOf(gatewayid), DEVICE, String.valueOf(instanceNumber),
                        TYPE, objectType, INST, String.valueOf(objectInstance));
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public BacnetObject getObject(long gatewayid, long instanceNumber, String objectType, int objectInstance) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.GET, BACNET, OBJECT, GATEWAY,
                        String.valueOf(gatewayid), DEVICE, String.valueOf(instanceNumber),
                        TYPE, objectType, INST, String.valueOf(objectInstance));
        return this.invokeHttpClient(internalRequest, BacnetObject.class);
    }

    public ListBacnetObjectResponse listObject(ListRequest request, long gatewayid, long instanceNumber) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.GET, BACNET, OBJECT, GATEWAY, String.valueOf(gatewayid),
                        DEVICE, String.valueOf(instanceNumber));
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));

        return this.invokeHttpClient(internalRequest, ListBacnetObjectResponse.class);
    }

    public CommonResponse deleteObject(long gatewayid, long instanceNumber, String objectType, int objectInstance) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.DELETE, BACNET, OBJECT, GATEWAY,
                        String.valueOf(gatewayid), DEVICE, String.valueOf(instanceNumber),
                        TYPE, objectType, INST, String.valueOf(objectInstance));
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public ListObjectPresentValueResponse listObjectPresentValue(ListObjectPresentValueRequest request,
                                                                 long gatewayid) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.POST, BACNET, OBJECT, GATEWAY,
                        String.valueOf(gatewayid), PRESENT_VALUE);
        return this.invokeHttpClient(internalRequest, ListObjectPresentValueResponse.class);
    }

    public CommonResponse updateObjectPresentValue(UpdateBacnetObjectPresentValueRequest request, long gatewayid) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.PUT, BACNET, OBJECT, GATEWAY,
                        String.valueOf(gatewayid), PRESENT_VALUE);
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse subObjectChanges(SubBacnetObjectRequest request, long gatewayid, int instance) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.POST, BACNET, OBJECT, GATEWAY,
                        String.valueOf(gatewayid), DEVICE, String.valueOf(instance), SUB);
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse unsubObjectChanges(SubBacnetObjectRequest request, long gatewayid, int instance) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.POST, BACNET, OBJECT, GATEWAY,
                        String.valueOf(gatewayid), DEVICE, String.valueOf(instance), UNSUB);
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse refreshObject(long gatewayid, long instanceNumber, String objectType, int objectInstance) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.PUT, BACNET, OBJECT, GATEWAY,
                        String.valueOf(gatewayid), DEVICE, String.valueOf(instanceNumber),
                        TYPE, objectType, INST, String.valueOf(objectInstance), REFRESH);
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
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
