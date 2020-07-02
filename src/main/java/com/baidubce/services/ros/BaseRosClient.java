/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.ros;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

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
import com.baidubce.util.DateUtils;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

/**
 * Ros base client.
 *
 * @author zhangmengmeng01
 * @date 2019/05/20
 */
public class BaseRosClient extends AbstractBceClient {
    private static final int DEFAULT_SOCKET_TIMEOUT = 20 * 1000;

    private static final String ENDPOINT = "http://ros.baidubce.com";
    private static final String CONTENT_TYPE = "application/json;charset=utf-8";

    private static final String[] HEADERS_TO_SIGN = {Headers.HOST, Headers.BCE_DATE};

    private static HttpResponseHandler[] rosHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Construct a default base ROS client.
     */
    protected BaseRosClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Construct a base ROS client with access key id and secret key.
     *
     * @param accessKey Access key id.
     * @param secretKey Secret key.
     */
    protected BaseRosClient(String accessKey, String secretKey) {
        this(new BceClientConfiguration()
                     .withSocketTimeoutInMillis(DEFAULT_SOCKET_TIMEOUT)
                     .withCredentials(new DefaultBceCredentials(accessKey, secretKey))
                     .withEndpoint(ENDPOINT));
    }

    /**
     * Construct a base ROS client using the specified client configuration options (ex: max retry attempts, proxy
     * settings, etc), and request metric collector.
     *
     * @param config Configuration options specifying how this client will communicate with BCE
     *               (ex: proxy settings, retry count, etc.).
     */
    protected BaseRosClient(BceClientConfiguration config) {
        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT) : config, rosHandlers);
    }

    protected InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                            String path) {
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path);
        InternalRequest internalRequest = new InternalRequest(httpMethod, uri);

        internalRequest.addHeader(Headers.BCE_DATE, DateUtils.formatAlternateIso8601Date(new Date()));

        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        internalRequest.setSignOptions(signOptions);
        internalRequest.setCredentials(this.config.getCredentials());

        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
            fillInHeadAndBody(bceRequest, internalRequest);
        }
        return internalRequest;
    }

    private void fillInHeadAndBody(AbstractBceRequest bceRequest, InternalRequest request) {
        byte[] content = toJson(bceRequest);
        request.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        request.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE);
        request.setContent(RestartableInputStream.wrap(content));
    }

    private byte[] toJson(AbstractBceRequest bceRequest) {
        try {
            String jsonStr = JsonUtils.toJsonString(bceRequest);
            return jsonStr.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        } catch (IllegalStateException e) {
            throw new BceClientException("Fail to convert request to json", e);
        }
    }
}
