/*
 * Copyright (c) 2018-2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.dumap;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.auth.SignOptions;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.dumap.handler.DuMapResponseHandler;
import com.baidubce.services.dumap.model.BaseDuMapResponse;
import com.baidubce.util.DateUtils;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Base DuMap client which does the operations to call dumap server.
 *
 * @author weizhijun
 * @date 2018/10/16
 */
@Slf4j
public class BaseDuMapClient extends AbstractBceClient {

    private static final int DEFAULT_SOCKET_TIMEOUT = 10 * 1000;

    private static final String ENDPOINT = "http://lbs.baidubce.com";
    private static final String CONTENT_TYPE = "application/json;charset=utf-8";
    private static final String ACCEPT_TYPE = "application/json";
    private static final String APP_ID = "x-app-id";
    private static final String ACCEPT = "Accept";

    private static final String[] HEADERS_TO_SIGN = {Headers.HOST, Headers.BCE_DATE};

    private static HttpResponseHandler[] dumapHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new DuMapResponseHandler()
    };

    /**
     * Construct a new base dumap client with the defaul configurations and handlers.
     */
    public BaseDuMapClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Construct a new base dumap client with the specified configurations and default handlers.
     *
     * @param config the client configuration.
     */
    public BaseDuMapClient(BceClientConfiguration config) {
        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT) : config, dumapHandlers);
    }

    /**
     * Construct a new base dumap client with the specified access key ID and secret key.
     *
     * @param accessKey Access key ID.
     * @param secretKey Secret key.
     */
    public BaseDuMapClient(String accessKey, String secretKey) {
        this(new BceClientConfiguration()
                .withSocketTimeoutInMillis(DEFAULT_SOCKET_TIMEOUT)
                .withCredentials(new DefaultBceCredentials(accessKey, secretKey))
                .withEndpoint(ENDPOINT));
    }

    protected BaseDuMapResponse callDuMap(String uri, String appId, AbstractBceRequest request, Object requestParams,
                                          HttpMethodName httpMethodName) {
        return callDuMap(uri, appId, request, requestParams, httpMethodName, BodyType.FORM);
    }

    protected BaseDuMapResponse callDuMap(String uri, String appId, AbstractBceRequest request, Object requestParams,
                                          HttpMethodName httpMethodName, BodyType bodyType) {
        InternalRequest internalRequest = createRequest(request, httpMethodName, bodyType, uri);
        internalRequest.addHeader(APP_ID, appId);
        internalRequest.addHeader(ACCEPT, ACCEPT_TYPE);
        internalRequest.addHeader(Headers.BCE_DATE, DateUtils.formatAlternateIso8601Date(new Date()));

        if (requestParams != null) {
            fillParams(internalRequest, requestParams);
        }
        return this.invokeHttpClient(internalRequest, BaseDuMapResponse.class);
    }

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod, BodyType bodyType,
                                          String... pathVariables) {
        List<String> path = new ArrayList<String>();

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest internalRequest = new InternalRequest(httpMethod, uri);
        // Add sign options.
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        internalRequest.setSignOptions(signOptions);
        internalRequest.setCredentials(this.config.getCredentials());

        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
            fillInHeadAndBody(bceRequest, internalRequest, bodyType);
        }
        return internalRequest;
    }

    protected void fillInHeadAndBody(AbstractBceRequest bceRequest, InternalRequest request, BodyType bodyType) {
        byte[] content = bodyType.equals(BodyType.JSON) ? toJson(bceRequest) : toForm(bceRequest);
        request.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        request.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE);
        request.setContent(RestartableInputStream.wrap(content));
    }

    protected byte[] toJson(AbstractBceRequest bceRequest) {
        String jsonStr = JsonUtils.toJsonString(bceRequest);
        return getBytes(jsonStr);
    }

    protected byte[] toForm(AbstractBceRequest bceRequest) {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<String, Object>();
        Field[] fields = bceRequest.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(bceRequest) != null && !"".equals(field.get(bceRequest).toString())) {
                    map.put(underscoreName(field.getName()), field.get(bceRequest));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        String formStr = toFormString(map);
        return getBytes(formStr);
    }

    private String toFormString(ConcurrentHashMap<String, Object> map) {
        Iterator<Map.Entry<String, Object>> i = map.entrySet().iterator();
        if (!i.hasNext()) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        for (; ; ) {
            Map.Entry e = i.next();
            String key = (String) e.getKey();
            Object value = e.getValue();
            sb.append(key);
            sb.append('=');
            sb.append(value);
            if (!i.hasNext()) {
                return sb.toString();
            }
            sb.append("&");
        }
    }

    private byte[] getBytes(String string) {
        try {
            return string.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
    }

    protected void fillParams(InternalRequest request, Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(obj) != null && !"".equals(field.get(obj).toString())) {
                    safeAddQueryParam(request, underscoreName(field.getName()), field.get(obj).toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    protected void safeAddQueryParam(InternalRequest request, String key, String value) {
        if (StringUtils.isNotBlank(value)) {
            request.addParameter(key, value);
        }
    }

    private String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (StringUtils.isBlank(name)) {
            return "";
        }
        result.append(name.substring(0, 1).toLowerCase());
        for (int i = 1; i < name.length(); i++) {
            String s = name.substring(i, i + 1);
            if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                result.append("_");
            }
            result.append(s.toLowerCase());
        }
        return result.toString();
    }

    public enum BodyType {
        JSON,
        FORM
    }
}
