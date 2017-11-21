/*
 * Copyright 2017 Baidu, Inc.
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
package com.baidubce.services.vcr;

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
import com.baidubce.services.vcr.model.GetMediaCharacterResponse;
import com.baidubce.services.vcr.model.GetMediaRequest;
import com.baidubce.services.vcr.model.GetMediaResponse;
import com.baidubce.services.vcr.model.GetMediaSpeechResponse;
import com.baidubce.services.vcr.model.GetStreamRequest;
import com.baidubce.services.vcr.model.GetStreamResponse;
import com.baidubce.services.vcr.model.PutImageRequest;
import com.baidubce.services.vcr.model.PutImageResponse;
import com.baidubce.services.vcr.model.PutMediaRequest;
import com.baidubce.services.vcr.model.PutMediaResponse;
import com.baidubce.services.vcr.model.PutStreamRequest;
import com.baidubce.services.vcr.model.PutStreamResponse;
import com.baidubce.services.vcr.model.PutTextRequest;
import com.baidubce.services.vcr.model.PutTextResponse;
import com.baidubce.util.DateUtils;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Provides the client for accessing the BCE Video Content Regulation service.
 */
public class VcrClient extends AbstractBceClient {

    private static final String VERSION = "v1";
    private static final String MEDIA = "media";
    private static final String STREAM = "stream";
    private static final String IMAGE = "image";
    private static final String TEXT = "text";

    private static final long MILLIS_PER_HOUR = 60 * 60 * 1000L;

    private static HttpResponseHandler[] vcrHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public VcrClient() {
        this(new BceClientConfiguration());
    }

    public VcrClient(BceClientConfiguration config) {
        super(config, vcrHandlers);
    }

    public PutMediaResponse putMedia(String source) {
        PutMediaRequest request = new PutMediaRequest();
        request.setSource(source);
        return putMedia(request);
    }

    public PutMediaResponse putMedia(PutMediaRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, MEDIA);
        return this.invokeHttpClient(internalRequest, PutMediaResponse.class);
    }

    public GetMediaResponse getMedia(String source) {
        GetMediaRequest request = new GetMediaRequest();
        request.setSource(source);
        return getMedia(request);
    }

    public GetMediaResponse getMedia(GetMediaRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, MEDIA);
        internalRequest.addParameter("source", request.getSource());
        return this.invokeHttpClient(internalRequest, GetMediaResponse.class);
    }

    public GetMediaSpeechResponse getMediaSpeech(String source) {
        GetMediaRequest request = new GetMediaRequest();
        request.setSource(source);
        return getMediaSpeech(request);
    }

    public GetMediaSpeechResponse getMediaSpeech(GetMediaRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, MEDIA);
        internalRequest.addParameter("source", request.getSource());
        internalRequest.addParameter("speech", "");
        return this.invokeHttpClient(internalRequest, GetMediaSpeechResponse.class);
    }

    public GetMediaCharacterResponse getMediaCharacter(String source) {
        GetMediaRequest request = new GetMediaRequest();
        request.setSource(source);
        return getMediaCharacter(request);
    }

    public GetMediaCharacterResponse getMediaCharacter(GetMediaRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, MEDIA);
        internalRequest.addParameter("source", request.getSource());
        internalRequest.addParameter("character", "");
        return this.invokeHttpClient(internalRequest, GetMediaCharacterResponse.class);
    }

    public PutStreamResponse putStream(String source) {
        PutStreamRequest request = new PutStreamRequest();
        request.setSource(source);
        return putStream(request);
    }

    public PutStreamResponse putStream(PutStreamRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, STREAM);
        return this.invokeHttpClient(internalRequest, PutStreamResponse.class);
    }

    public GetStreamResponse getStream(String source) {
        GetStreamRequest request = new GetStreamRequest();
        Date endTime = new Date();
        Date startTime = new Date(endTime.getTime() - MILLIS_PER_HOUR);
        request.setSource(source);
        request.setEndTime(DateUtils.formatAlternateIso8601Date(endTime));
        request.setStartTime(DateUtils.formatAlternateIso8601Date(startTime));
        return getStream(request);
    }

    public GetStreamResponse getStream(GetStreamRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, STREAM);
        internalRequest.addParameter("source", request.getSource());
        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", request.getStartTime());
        }
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        return this.invokeHttpClient(internalRequest, GetStreamResponse.class);
    }

    public PutImageResponse putImage(String source) {
        PutImageRequest request = new PutImageRequest();
        request.setSource(source);
        return putImage(request);
    }

    public PutImageResponse putImage(PutImageRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, IMAGE);
        return this.invokeHttpClient(internalRequest, PutImageResponse.class);
    }

    public PutTextResponse putText(String text) {
        PutTextRequest request = new PutTextRequest();
        request.setText(text);
        return putText(request);
    }

    public PutTextResponse putText(PutTextRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, TEXT);
        return this.invokeHttpClient(internalRequest, PutTextResponse.class);
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
