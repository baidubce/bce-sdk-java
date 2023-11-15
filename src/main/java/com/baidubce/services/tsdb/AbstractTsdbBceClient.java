/*
 * Copyright (c) 2019-2020 Baidu. All Rights Reserved.
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
package com.baidubce.services.tsdb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.SignOptions;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.tsdb.handler.TsdbJsonResponseHandler;
import com.baidubce.services.tsdb.model.WriteDatapointsRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

/**
 * Provides the client for accessing the Tsdb(Time series database) api.
 */
public abstract class AbstractTsdbBceClient extends AbstractBceClient {

    protected static final String CONTENT_TYPE = "application/json;charset=UTF-8";

    private static final String VERSION = "v1";
    private static final String[] HEADERS_TO_SIGN = { Headers.HOST };
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String GZIP = "gzip";
    private static final String UTF8 = "UTF-8";

    /**
     * Responsible for handling HttpResponse from all Tsdb service calls.
     */
    protected static final HttpResponseHandler[] TSDB_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new TsdbJsonResponseHandler() };

    public AbstractTsdbBceClient(BceClientConfiguration config, HttpResponseHandler[] responseHandlers) {
        super(config, responseHandlers);
    }

    protected byte[] toGzipBytes(WriteDatapointsRequest writeDatapointsRequest) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = null;
        try {
            gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gzipOutputStream.write(JsonUtils.toJsonString(writeDatapointsRequest).getBytes(UTF8));
        } catch (IOException e) {
            throw new BceClientException("Create gzip bytes failed.", e);
        } finally {
            if (gzipOutputStream != null) {
                try {
                    gzipOutputStream.close();
                } catch (IOException e) {
                    throw new BceClientException("Close gzip stream failed.", e);
                }
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                throw new BceClientException("Close byte stream failed.", e);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    protected InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
            String... pathVariables) {
        return createRequest(this.getEndpoint(), bceRequest, httpMethod, null, pathVariables);
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param endpoint The endpoint to request.
     * @param bceRequest The original BCE request created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param signOptions The options for signature.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object populated with endpoint, resource path and specific parameters to send.
     */
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

    /**
     * Converts the specified request object into a URL, containing all the
     * specified parameters, the specified request endpoint, etc.
     *
     * @param request The request to convert into a URL.
     * @return A new URL representing the specified request.
     */
    protected URL convertRequestToUrl(InternalRequest request) {
        String resourcePath = HttpUtils.normalizePath(request.getUri().getPath());

        // Removed the padding "/" that was already added into the request's resource path.
        if (resourcePath.startsWith("/")) {
            resourcePath = resourcePath.substring(1);
        }

        // Some http client libraries (e.g. Apache HttpClient) cannot handle
        // consecutive "/"s between URL authority and path components.
        // So we escape "////..." into "/%2F%2F%2F...", in the same way as how
        // we treat consecutive "/"s in AmazonS3Client#presignRequest(...)
        String urlPath = "/" + resourcePath;
        urlPath = urlPath.replaceAll("(?<=/)/", "%2F");
        String urlString = this.getEndpoint() + urlPath;

        boolean firstParam = true;
        for (String param : request.getParameters().keySet()) {
            if (firstParam) {
                urlString += "?";
                firstParam = false;
            } else {
                urlString += "&";
            }

            String value = request.getParameters().get(param);
            urlString += param + "=" + HttpUtils.normalize(value);
        }

        String authorization = request.getHeaders().get(Headers.AUTHORIZATION);
        if (authorization != null) {
            if (firstParam) {
                urlString += "?";
            } else {
                urlString += "&";
            }
            urlString += "authorization" + "=" + HttpUtils.normalize(authorization);
        }

        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new BceClientException("Unable to convert request to well formed URL: " + e.getMessage(), e);
        }
    }

}
