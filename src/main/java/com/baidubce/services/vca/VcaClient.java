/**
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca;

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
import com.baidubce.services.vca.model.AnalyzeResponse;
import com.baidubce.services.vca.model.QueryResultRequest;
import com.baidubce.services.vca.model.QueryResultResponse;
import com.baidubce.services.vca.model.AnalyzeRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Vca client.
 *
 * @author houshunwei
 */
public class VcaClient extends AbstractBceClient {

    private static final String VERSION = "v2";
    private static final String MEDIA = "media";

    private static HttpResponseHandler[] vcrHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public VcaClient() {
        this(new BceClientConfiguration());
    }

    public VcaClient(BceClientConfiguration config) {
        super(config, vcrHandlers);
    }

    public AnalyzeResponse analyze(String source) {
        AnalyzeRequest request = new AnalyzeRequest();
        request.setSource(source);
        return analyze(request);
    }

    public AnalyzeResponse analyze(AnalyzeRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT,
                request, MEDIA);
        return this.invokeHttpClient(internalRequest, AnalyzeResponse.class);
    }

    public QueryResultResponse queryResult(String source) {
        QueryResultRequest request = new QueryResultRequest();
        request.setSource(source);
        return queryResult(request);
    }

    public QueryResultResponse queryResult(QueryResultRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                request, MEDIA);
        internalRequest.addParameter("source", request.getSource());
        return this.invokeHttpClient(internalRequest, QueryResultResponse.class);
    }


    /**
     * Creates and initializes a new request object for the specified resource.
     * This method is responsible for determining HTTP method, URI path,
     * credentials and request body for POST method.
     * <p>
     * <b>Note: </b> The Query parameters in URL should be specified by caller method.
     * </p>
     *
     * @param httpMethod The HTTP method to use when sending the request.
     * @param request The original request, as created by the user.
     * @param pathVariables The optional variables in URI path.
     * @return A new request object, populated with endpoint, resource path,
     * ready for callers to populate any additional headers or
     * parameters, and execute.
     */
    private InternalRequest createRequest(
            HttpMethodName httpMethod, AbstractBceRequest request, String... pathVariables) {

        // build URL paths
        List<String> pathComponents = new ArrayList<String>();
        pathComponents.add(VERSION);

        // append resourceKeys,pathVariables,
        // For example:/resourcekey1/resourcekey2/../pathVariable1/pathVariable2
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                pathComponents.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(getEndpoint(), pathComponents.toArray(new String[pathComponents.size()]));

        // get a InternalRequest instance and set headers
        InternalRequest internalRequest = new InternalRequest(httpMethod, uri);
        internalRequest.setCredentials(request.getRequestCredentials());

        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
            fillRequestPayload(internalRequest, request);
        }
        return internalRequest;
    }

    private InternalRequest fillRequestPayload(InternalRequest internalRequest, AbstractBceRequest request) {
        String strJson = JsonUtils.toJsonString(request);
        byte[] requestJson = null;
        try {
            requestJson = strJson.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Unsupported encode.", e);
        }

        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        internalRequest.setContent(RestartableInputStream.wrap(requestJson));

        return internalRequest;
    }
}
