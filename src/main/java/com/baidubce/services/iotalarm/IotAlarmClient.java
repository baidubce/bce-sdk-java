package com.baidubce.services.iotalarm;

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
import com.baidubce.services.iotalarm.model.Alarm;
import com.baidubce.services.iotalarm.model.BatchIds;
import com.baidubce.services.iotalarm.model.CommonResponse;
import com.baidubce.services.iotalarm.model.CreateAlarmRequest;
import com.baidubce.services.iotalarm.model.ListAlarmRequest;
import com.baidubce.services.iotalarm.model.ListAlarmResponse;
import com.baidubce.services.iotalarm.model.UpdateAlarmRequest;
import com.baidubce.services.iotalarm.model.UuidResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanyoujun on 2017/6/20.
 */
public class IotAlarmClient extends AbstractBceClient {
    private static final String ENDPOINT = "re.iot.gz.baidubce.com";
    private static final String VERSION = "v1";
    private static final String ALARM = "alarm";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String DISABLE = "disable";
    private static final String ENABLE = "enable";
    private static final String RECOVER = "recover";
    private static final String BATCH = "batch";
    private static final String DELETE = "delete";

    private static final HttpResponseHandler[] HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler() };

    public IotAlarmClient(BceClientConfiguration config) {

        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT) : config, HANDLERS);
    }

    public IotAlarmClient(String accessKey, String secretKey) {
        this(new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(accessKey, secretKey))
                .withEndpoint(ENDPOINT));
    }

    public ListAlarmResponse listAlarms(ListAlarmRequest request) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.GET, ALARM);
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        if (request.getAlarmState() != null) {
            internalRequest.addParameter("alarmState", request.getAlarmState());
        }
        if (request.getDisabled() != null) {
            internalRequest.addParameter("disabled", request.getDisabled());
        }
        if (request.getServerity() != null) {
            internalRequest.addParameter("severity", request.getServerity());
        }
        return this.invokeHttpClient(internalRequest, ListAlarmResponse.class);
    }

    public Alarm getAlarm(String uuid) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.GET, ALARM, uuid);
        return this.invokeHttpClient(internalRequest, Alarm.class);
    }

    public UuidResponse createAlarm(CreateAlarmRequest request) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.POST, ALARM);
        return this.invokeHttpClient(internalRequest, UuidResponse.class);
    }

    public CommonResponse deleteAlarm(String uuid) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.DELETE, ALARM, uuid);
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse deleteAlarmBatch(List<String> alarmIds) {
        BatchIds ids = new BatchIds();
        ids.setIds(alarmIds);
        InternalRequest internalRequest =
                createRequest(ids, HttpMethodName.POST, ALARM, BATCH, DELETE);
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse updateAlarm(UpdateAlarmRequest req, String uuid) {
        InternalRequest internalRequest =
                createRequest(req, HttpMethodName.PUT, ALARM, uuid);
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse disableAlarm(String alarmId) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.PUT, ALARM, alarmId, DISABLE);
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse enableAlarm(String alarmId) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.PUT, ALARM, alarmId, ENABLE);
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse recoverAlarm(String alarmId) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.PUT, ALARM, alarmId, RECOVER);
        return this.invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public CommonResponse recoverAlarmBatch(List<String> alarmIds) {
        BatchIds ids = new BatchIds();
        ids.setIds(alarmIds);
        InternalRequest internalRequest =
                createRequest(ids, HttpMethodName.POST, ALARM, BATCH, RECOVER);
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
