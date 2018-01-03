package com.baidubce.services.iottimer;

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
import com.baidubce.services.iotalarm.model.CommonResponse;
import com.baidubce.services.iotalarm.model.UuidResponse;
import com.baidubce.services.iottimer.model.CreateIotTimerRequest;
import com.baidubce.services.iottimer.model.IotTimer;
import com.baidubce.services.iottimer.model.ListIotTimerRequest;
import com.baidubce.services.iottimer.model.ListIotTimerResponse;
import com.baidubce.services.iottimer.model.UpdateIotTimerRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class IotTimerClient extends AbstractBceClient {
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String VERSION = "v1";
    private static final String TIMER = "timer";
    private static final String ENDPOINT = "re.iot.gz.baidubce.com";

    /**
     * Responsible for handling HttpResponse from all Tsdb service calls.
     */
    private static final HttpResponseHandler[] HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler() };

    public IotTimerClient(BceClientConfiguration config) {

        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT) : config, HANDLERS);
    }

    public IotTimerClient(String accessKey, String secretKey) {
        this(new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(accessKey, secretKey))
                .withEndpoint(ENDPOINT));
    }

    public UuidResponse create(CreateIotTimerRequest request) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.POST, TIMER);
        return this.invokeHttpClient(internalRequest, UuidResponse.class);
    }

    public IotTimer get(String uuid) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.GET, TIMER, uuid);
        return this.invokeHttpClient(internalRequest, IotTimer.class);
    }

    public ListIotTimerResponse list(ListIotTimerRequest request) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.GET, TIMER);
        if (request.getPageNo() > 0) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() > 0) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }
        return this.invokeHttpClient(internalRequest, ListIotTimerResponse.class);
    }

    public CommonResponse update(UpdateIotTimerRequest req, String uuid) {
        InternalRequest internalRequest =
                createRequest(req, HttpMethodName.PUT, TIMER, uuid);
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse delete(String uuid) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.DELETE, TIMER, uuid);
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
