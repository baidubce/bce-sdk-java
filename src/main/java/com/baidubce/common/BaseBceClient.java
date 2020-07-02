/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.common;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.auth.SignOptions;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

/**
 * Base bce client
 *
 * @author zhangquan07
 */
public class BaseBceClient extends AbstractBceClient {
    private String serviceId;
    private HttpRequestHandler[] httpRequestHandlers;

    public BaseBceClient(String serviceId, String ak, String sk, String endpoint) {
        super(ServiceConfigFactory.getClientConfiguration(serviceId).withEndpoint(endpoint)
                        .withCredentials(new DefaultBceCredentials(ak, sk)),
                ServiceConfigFactory.getResponseHandlers(serviceId));
        this.httpRequestHandlers = ServiceConfigFactory.getRequestHandlers(serviceId);
        this.serviceId = serviceId;
    }

    public BaseBceClient(BceClientConfiguration configuration) {
        this(configuration, false);
    }

    public BaseBceClient(BceClientConfiguration configuration, boolean isHttpAsyncPutEnabled) {
        super(configuration, ServiceConfigFactory.getDefaultResponseHandlers(), isHttpAsyncPutEnabled);
        this.serviceId = this.computeServiceId();
        this.httpRequestHandlers = ServiceConfigFactory.getRequestHandlers(serviceId);
    }

    /**
     * Create internal request with path, query, header and body.
     *
     * @param httpMethod http method
     * @param path       path
     * @param queries    queries
     * @param headers    headers
     * @param bceRequest bce request
     * @return Internal request
     */
    protected InternalRequest createRequest(
            HttpMethodName httpMethod,
            String path,
            Map<String, String> queries,
            Map<String, String> headers,
            BaseBceRequest bceRequest) {
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path);
        InternalRequest internalRequest = new InternalRequest(httpMethod, uri);
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(
                new HashSet<String>(Arrays.asList(ServiceConfigFactory.getDefaultHeadersToSign(serviceId))));
        internalRequest.setSignOptions(signOptions);
        if (bceRequest == null) {
            bceRequest = new BaseBceRequest();
        } else {
            internalRequest.setCredentials(bceRequest.getRequestCredentials());
        }
        internalRequest.setHeaders(headers);
        internalRequest.setParameters(queries);

        for (HttpRequestHandler httpRequestHandler : httpRequestHandlers) {
            if (!httpRequestHandler.handle(internalRequest, bceRequest)) {
                break;
            }
        }
        if (internalRequest.getHttpMethod() == HttpMethodName.POST
                || internalRequest.getHttpMethod() == HttpMethodName.PUT) {
            String strJson = JsonUtils.toJsonString(bceRequest);
            byte[] requestJson;
            try {
                requestJson = strJson.getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Unsupported encode.", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
            internalRequest.setContent(RestartableInputStream.wrap(requestJson));
        }
        return internalRequest;
    }
}
