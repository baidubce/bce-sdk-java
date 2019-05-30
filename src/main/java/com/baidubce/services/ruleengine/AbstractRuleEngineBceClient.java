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
import com.baidubce.services.ruleengine.model.FeedMessageRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 * sub-class for AbstractBceClient, provide gzip method
 *
 * Created by huangjiatian on 2019/03/07.
 */
public abstract class AbstractRuleEngineBceClient extends AbstractBceClient {

    private static Logger logger = LoggerFactory.getLogger(AbstractRuleEngineBceClient.class);

    private static final String VERSION = "v1";
    private static final String[] HEADERS_TO_SIGN = { Headers.HOST };
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String GZIP = "gzip";
    private static final String UTF8 = "UTF-8";

    /**
     * Responsible for handling HttpResponse from all rule engine service calls.
     */
    protected static final HttpResponseHandler[] HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler() };

    public AbstractRuleEngineBceClient(BceClientConfiguration configuration, HttpResponseHandler[] responseHandlers) {
        super(configuration, responseHandlers);
    }

    protected byte[] toGzipBytes(FeedMessageRequest request) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = null;
        try {
            gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(JsonUtils.toJsonString(request).getBytes(UTF8));
        } catch (IOException e) {
            throw new BceClientException("Create gzip bytes failed.", e);
        } finally {
            if (gzipOutputStream != null) {
                try {
                    gzipOutputStream.close();
                } catch (IOException e) {
                    logger.error("gzip output stream close failed.", e);
                }
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                logger.error("byte array output stream close failed.", e);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    protected InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                            String... pathVariables) {
        return createRequest(this.getEndpoint(), bceRequest, httpMethod, null, pathVariables);
    }

    protected InternalRequest createRequest(URI endpoint, AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                            SignOptions signOptions, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION);

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(endpoint, path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);

        if (signOptions == null) {
            signOptions = new SignOptions();
            signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        }
        signOptions.setTimestamp(getTimestampInThisMinute());

        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());

        return request;
    }

    protected Date getTimestampInThisMinute() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    protected void fillInHeadAndBody(AbstractBceRequest bceRequest, InternalRequest request) {
        byte[] content = toJson(bceRequest);
        request.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        request.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE);
        request.setContent(RestartableInputStream.wrap(content));
    }

    protected void fillInHeadAndBodyForGzip(InternalRequest request, byte[] bytes) {
        request.addHeader(Headers.CONTENT_LENGTH, String.valueOf(bytes.length));
        request.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE);
        request.setContent(RestartableInputStream.wrap(bytes));
        request.addHeader(Headers.CONTENT_ENCODING, GZIP);
    }

    protected byte[] toJson(AbstractBceRequest bceRequest) {
        String jsonStr = JsonUtils.toJsonString(bceRequest);
        try {
            return jsonStr.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
    }
}
