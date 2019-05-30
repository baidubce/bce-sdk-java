/*
 * Copyright 2018 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.dugo.core.protocol.http;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.BceCredentials;
import com.baidubce.auth.BceV1Signer;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.auth.SignOptions;
import com.baidubce.auth.Signer;
import com.baidubce.http.BceHttpClient;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.DateUtils;

/**
 * the http client for dugo and build the internalRequest
 * Created by liuzhenxing01 on 2018/10/12.
 */
public class BceIotHttpClient {

    private static final String CONTENT_TYPE = "application/json;charset=utf-8";

    private InternalRequest internalRequest;
    private BceCredentials bceCredentials;
    private URI uri;

    public BceIotHttpClient() {
    }

    public URI getUri() {
        return this.uri;
    }

    public BceIotHttpClient withAuth(String ak, String sk) {
        this.initBceCredentials(ak, sk);
        return this;
    }

    public String getHost() {
        return this.uri.getHost();
    }

    public BceIotHttpClient withMethod(HttpMethodName methodName, URI uri) {
        this.uri = uri;
        this.initInternalRequest(methodName, uri);
        return this;
    }

    public InternalRequest getInternalRequest() {
        return this.internalRequest;
    }

    private void initBceCredentials(String ak, String sk) {
        this.bceCredentials = new DefaultBceCredentials(ak, sk);
    }

    private void initInternalRequest(HttpMethodName methodName, URI uri) {
        internalRequest = new InternalRequest(methodName, uri);
        internalRequest.setCredentials(this.bceCredentials);
        internalRequest.setSignOptions(this.initSignOptions());
        internalRequest.setHeaders(initHeaders(uri));
        internalRequest.addHeader("Content-Type", CONTENT_TYPE);
    }

    public void addParams(String key, String value) {
        this.internalRequest.addParameter(key, value);
    }

    private Map<String, String> initHeaders(URI uri) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-bce-date", DateUtils.formatAlternateIso8601Date(new Date()));
        headers.put("Host", uri.getHost());
        return headers;
    }

    public void addHeader(String key, String value) {
        this.internalRequest.addHeader(key, value);
    }

    private SignOptions initSignOptions() {
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(getHeadersToSign());
        signOptions.setExpirationInSeconds(3600);
        return signOptions;
    }

    /**
     * default header sign is Host and x-bce-date
     * @return
     */
    private Set<String> getHeadersToSign() {
        Set<String> signHeaders = new HashSet<String>();
        signHeaders.add("Host");
        signHeaders.add("x-bce-date");
        return signHeaders;
    }

    public BceIotHttpClient withPayload(byte[] payload) {
        if (payload == null) {
            return this;
        }
        this.internalRequest.setContent(RestartableInputStream.wrap(payload));
        this.internalRequest.addHeader("Content-Length", "" + payload.length);

        this.internalRequest.addHeader(Headers.CONTENT_LENGTH, Integer.toString(payload.length));
        this.internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE);
        return this;
    }

    public <T extends AbstractBceResponse> T exec(Class<T> responseClass, HttpResponseHandler[] hadlers) {
        checkClient();
        BceClientConfiguration config = new BceClientConfiguration();
        Signer signer = new BceV1Signer();
        BceHttpClient httpClient = new BceHttpClient(config, signer);
        return (T) httpClient.execute(this.internalRequest, responseClass, hadlers);
    }

    public <T extends AbstractBceResponse> T exec(Class<T> responseClass) {
        HttpResponseHandler[] responseHandlers = { new BceMetadataResponseHandler(), new
                BceJsonResponseHandler(), new BceErrorResponseHandler()};
        return exec(responseClass, responseHandlers);
    }

    private void checkClient() {
        if (bceCredentials == null || bceCredentials.getAccessKeyId() == null
                || bceCredentials.getSecretKey() == null) {
            throw new IllegalArgumentException("accessKey and secretKey should not be null");
        }
    }
}