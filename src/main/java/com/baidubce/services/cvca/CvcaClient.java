package com.baidubce.services.cvca;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
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
import com.baidubce.services.cvca.model.ChatRequest;
import com.baidubce.services.cvca.model.ChatResponse;
import com.baidubce.util.DateUtils;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.util.Validate;

/**
 * 云秘智能客服client
 *
 * @author wujinlin
 */
public class CvcaClient extends AbstractBceClient {
    public static final int DEFAULT_SOCKET_TIMEOUT = 10 * 1000;

    private static final String ENDPOINT = "apicvca.baidubce.com";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String VERSION = "v1";
    private static final String CHAT = "chat";
    private static final String HEADER_APP_ID = "x-cvca-app-id";
    private static final String VALIDATE_MESSAGE_APP_ID = "appId不能为空";
    private static final String VALIDATE_MESSAGE_QUERY = "query不能为空";

    private static final String[] HEADERS_TO_SIGN = { Headers.HOST, Headers.BCE_DATE };

    private static final HttpResponseHandler[] HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler() };

    public CvcaClient(BceClientConfiguration config) {
        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT) : config, HANDLERS);
    }

    public CvcaClient(String accessKey, String secretKey) {
        this(new BceClientConfiguration()
                .withSocketTimeoutInMillis(DEFAULT_SOCKET_TIMEOUT)
                .withCredentials(new DefaultBceCredentials(accessKey, secretKey))
                .withEndpoint(ENDPOINT));
    }

    public ChatResponse chat(String appId, String query, String sessionId) {
        return chat(appId, query, sessionId, null);
    }

    public ChatResponse chat(String appId, String query, String sessionId, String customerId) {
        Validate.checkStringNotEmpty(appId, VALIDATE_MESSAGE_APP_ID);
        Validate.checkStringNotEmpty(query, VALIDATE_MESSAGE_QUERY);
        ChatRequest request = new ChatRequest();
        request.setQuery(query);
        if (!isStringEmpty(sessionId)) {
            request.setSessionId(sessionId);
        }
        if (!isStringEmpty(customerId)) {
            request.setCustomerId(customerId);
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, CHAT);
        internalRequest.addHeader(HEADER_APP_ID, appId);
        return this.invokeHttpClient(internalRequest, ChatResponse.class);
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
        request.addHeader(Headers.BCE_DATE, DateUtils.formatAlternateIso8601Date(new Date()));
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
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

    private boolean isStringEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
