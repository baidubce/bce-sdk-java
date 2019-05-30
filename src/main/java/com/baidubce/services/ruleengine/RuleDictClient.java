/*
 * Copyright 2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law * or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.ruleengine;

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
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.model.GenericAccountRequest;
import com.baidubce.services.ruleengine.model.dict.CreateDictRequest;
import com.baidubce.services.ruleengine.model.dict.Dict;
import com.baidubce.services.ruleengine.model.dict.ListDictRequest;
import com.baidubce.services.ruleengine.model.dict.ListDictResponse;
import com.baidubce.services.ruleengine.model.dict.ResultStatus;
import com.baidubce.services.ruleengine.model.dict.UpdateDictRequest;
import com.baidubce.services.ruleengine.model.dict.UuidResult;
import com.baidubce.services.ruleengine.model.dict.WriteDictDataRequest;
import com.baidubce.services.ruleengine.model.dict.WriteDictDataResult;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Rule dict client
 *
 * Created by liudawei02 on 19/4/3.
 */
public class RuleDictClient extends AbstractBceClient {

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String VERSION = "v1";
    private static final String DICT = "dict";
    private static final String DATA = "data";
    private static final String ENDPOINT = "iotredata.gz.baidubce.com";

    private static final HttpResponseHandler[] HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler() };

    public RuleDictClient(BceClientConfiguration config) {
        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT) : config, HANDLERS);
    }

    public RuleDictClient(String accessKey, String secretKey) {
        this(new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(accessKey, secretKey))
                .withEndpoint(ENDPOINT));
    }

    /**
     * Create rule dict
     */
    public UuidResult createDict(CreateDictRequest request) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.POST, DICT);
        return this.invokeHttpClient(internalRequest, UuidResult.class);
    }

    /**
     * Update rule dict
     */
    public ResultStatus updateDict(UpdateDictRequest request) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.PUT, DICT, request.getUuid());
        return this.invokeHttpClient(internalRequest, ResultStatus.class);
    }

    /**
     * Get a rule dict by dict uuid
     */
    public Dict getDict(String uuid) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.GET, DICT, uuid);
        return this.invokeHttpClient(internalRequest, Dict.class);
    }

    /**
     * Query all rule dict
     */
    public ListDictResponse listDict(ListDictRequest request) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.GET, DICT);
        if (request.getPageNo() > 0) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() > 0) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }
        return this.invokeHttpClient(internalRequest, ListDictResponse.class);
    }

    /**
     * Delete a rule dict
     */
    public void deleteDict(String uuid) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.DELETE, DICT, uuid);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Write data to dict
     */
    public WriteDictDataResult writeDictData(WriteDictDataRequest request) {
        Map<String, String> body = request.getData();
        String jsonStr = JsonUtils.toJsonString(body);

        InternalRequest internalRequest =
                createRequest(jsonStr, new GenericAccountRequest(), HttpMethodName.PUT, DICT, DATA, request.getUuid());

        return this.invokeHttpClient(internalRequest, WriteDictDataResult.class);
    }

    /**
     * Get value by key from a dict
     */
    public ResultStatus getDictData(String dictUuid, String key) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.GET, DICT, DATA, dictUuid);
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("key", key);
        internalRequest.setParameters(parameters);
        return this.invokeHttpClient(internalRequest, ResultStatus.class);
    }

    /**
     * Delete data by key from a dict
     */
    public ResultStatus deleteDictData(String dictUuid, String key) {
        InternalRequest internalRequest =
                createRequest(new GenericAccountRequest(), HttpMethodName.DELETE, DICT, DATA, dictUuid);
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("key", key);
        internalRequest.setParameters(parameters);
        return this.invokeHttpClient(internalRequest, ResultStatus.class);
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

    private InternalRequest createRequest(String body, AbstractBceRequest bceRequest, HttpMethodName httpMethod,
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

            fillInHeadAndBody(body.getBytes(), request);
        }

        return request;
    }

    private void fillInHeadAndBody(AbstractBceRequest bceRequest, InternalRequest request) {
        byte[] content = toJson(bceRequest);
        request.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        request.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE);
        request.setContent(RestartableInputStream.wrap(content));
    }

    private void fillInHeadAndBody(byte[] content, InternalRequest request) {
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
