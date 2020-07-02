/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.common;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;

/**
 * Service config of service.
 *
 * @author zhangquan07
 */
public class ServiceConfigFactory {
    /**
     * Service client configuration.
     */
    private static final BceClientConfiguration DEFAULT_CONFIGURATION = new BceClientConfiguration();

    /**
     * Default headers to sign.
     */
    private static final String[] DEFAULT_HEADERS_TO_SIGN = {"host", "x-bce-date"};

    /**
     * Responsible for handling httpRequest from all service calls.
     */
    private static final HttpRequestHandler[] DEFAULT_REQUEST_HANDLERS = new HttpRequestHandler[0];

    /**
     * Responsible for handling httpResponses from all service calls.
     */
    private static final HttpResponseHandler[] DEFAULT_RESPONSE_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public static BceClientConfiguration getClientConfiguration(String serviceName) {
        return DEFAULT_CONFIGURATION;
    }

    public static String[] getDefaultHeadersToSign(String serviceName) {
        return DEFAULT_HEADERS_TO_SIGN;
    }

    public static HttpResponseHandler[] getResponseHandlers(String serviceName) {
        return DEFAULT_RESPONSE_HANDLERS;
    }

    public static HttpRequestHandler[] getRequestHandlers(String serviceName) {
        return DEFAULT_REQUEST_HANDLERS;
    }

    public static HttpResponseHandler[] getDefaultResponseHandlers() {
        return DEFAULT_RESPONSE_HANDLERS;
    }

}
