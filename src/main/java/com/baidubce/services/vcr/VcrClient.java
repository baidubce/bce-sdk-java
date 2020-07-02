/*
 * Copyright 2018-2020 Baidu, Inc. All Rights Reserved.
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
import com.baidubce.services.vcr.model.CancelStreamRequest;
import com.baidubce.services.vcr.model.CancelStreamResponse;
import com.baidubce.services.vcr.model.GetAudioRequest;
import com.baidubce.services.vcr.model.GetAudioResponse;
import com.baidubce.services.vcr.model.GetImageAsyncRequest;
import com.baidubce.services.vcr.model.GetImageAsyncResponse;
import com.baidubce.services.vcr.model.GetMediaCharacterResponse;
import com.baidubce.services.vcr.model.GetMediaRequest;
import com.baidubce.services.vcr.model.GetMediaResponse;
import com.baidubce.services.vcr.model.GetMediaSpeechResponse;
import com.baidubce.services.vcr.model.GetStreamCheckTaskListRequest;
import com.baidubce.services.vcr.model.GetStreamCheckTaskListResponse;
import com.baidubce.services.vcr.model.GetStreamRequest;
import com.baidubce.services.vcr.model.GetStreamResponse;
import com.baidubce.services.vcr.model.LibBriefRequest;
import com.baidubce.services.vcr.model.LibBriefResponse;
import com.baidubce.services.vcr.model.LibImageRequest;
import com.baidubce.services.vcr.model.LibImageResponse;
import com.baidubce.services.vcr.model.LibResponse;
import com.baidubce.services.vcr.model.PutAudioRequest;
import com.baidubce.services.vcr.model.PutAudioResponse;
import com.baidubce.services.vcr.model.PutImageAsyncRequest;
import com.baidubce.services.vcr.model.PutImageAsyncResponse;
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

import org.apache.commons.lang3.StringUtils;

/**
 * Provides the client for accessing the BCE Video Content Regulation service.
 */
public class VcrClient extends AbstractBceClient {

    private static final String VERSION = "v1";
    private static final String VERSION_V2 = "v2";
    private static final String MEDIA = "media";
    private static final String AUDIO = "audio";
    private static final String STREAM = "stream";
    private static final String IMAGE = "image";
    private static final String TEXT = "text";
    private static final String FACE_LIB = "face/lib";
    private static final String LOGO_LIB = "logo/lib";
    private static final String PARAM_SOURCE = "source";
    private static final String CHECK = "check";
    private static final String CANCEL = "cancel";
    private static final String LIST = "list";
    private static final String PARAM_MAXKEYS = "maxKeys";
    private static final String PARAM_MARKER = "marker";
    private static final String PARAM_STATUS = "status";

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

    /**
     * Create a media check task with the specified source.
     *
     * @param source The source of media
     *
     * @return PutMediaResponse
     */
    public PutMediaResponse putMedia(String source) {
        PutMediaRequest request = new PutMediaRequest();
        request.setSource(source);
        return putMedia(request);
    }

    public PutMediaResponse putMedia(PutMediaRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, VERSION, MEDIA);
        return this.invokeHttpClient(internalRequest, PutMediaResponse.class);
    }

    /**
     * Get a media check task result with the specified source.
     *
     * @param source The source of media
     *
     * @return result of the media check task
     */
    public GetMediaResponse getMedia(String source) {
        GetMediaRequest request = new GetMediaRequest();
        request.setSource(source);
        return getMedia(request);
    }

    public GetMediaResponse getMedia(GetMediaRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, VERSION, MEDIA);
        internalRequest.addParameter(PARAM_SOURCE, request.getSource());
        return this.invokeHttpClient(internalRequest, GetMediaResponse.class);
    }

    public GetMediaSpeechResponse getMediaSpeech(String source) {
        GetMediaRequest request = new GetMediaRequest();
        request.setSource(source);
        return getMediaSpeech(request);
    }

    public GetMediaSpeechResponse getMediaSpeech(GetMediaRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, VERSION, MEDIA);
        internalRequest.addParameter(PARAM_SOURCE, request.getSource());
        internalRequest.addParameter("speech", "");
        return this.invokeHttpClient(internalRequest, GetMediaSpeechResponse.class);
    }

    public GetMediaCharacterResponse getMediaCharacter(String source) {
        GetMediaRequest request = new GetMediaRequest();
        request.setSource(source);
        return getMediaCharacter(request);
    }

    public GetMediaCharacterResponse getMediaCharacter(GetMediaRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, VERSION, MEDIA);
        internalRequest.addParameter(PARAM_SOURCE, request.getSource());
        internalRequest.addParameter("character", "");
        return this.invokeHttpClient(internalRequest, GetMediaCharacterResponse.class);
    }

    /**
     * This interface is no longer supported, please use new interface putStreamV2.
     **/
    @Deprecated
    public PutStreamResponse putStream(String source) {
        PutStreamRequest request = new PutStreamRequest();
        request.setSource(source);
        return putStream(request);
    }

    /**
     * This interface is no longer supported, please use new interface putStreamV2.
     **/
    @Deprecated
    public PutStreamResponse putStream(PutStreamRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, VERSION, STREAM);
        return this.invokeHttpClient(internalRequest, PutStreamResponse.class);
    }

    /**
     * Create an stream check task with the request.
     *
     * @param request: The request when check stream
     *
     * @return PutStreamResponse
     **/
    public PutStreamResponse putStreamV2(PutStreamRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, VERSION_V2, STREAM, CHECK);
        return this.invokeHttpClient(internalRequest, PutStreamResponse.class);
    }

    /**
     * Cancel an stream check task with the request.
     *
     * @param request: The request when cancel stream
     *
     * @return CancelStreamResponse
     **/
    public CancelStreamResponse cancelStreamV2(CancelStreamRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, VERSION_V2, STREAM, CANCEL);
        return this.invokeHttpClient(internalRequest, CancelStreamResponse.class);
    }

    /**
     * This interface is no longer supported, please use new interface getStreamV2.
     **/
    @Deprecated
    public GetStreamResponse getStream(String source) {
        GetStreamRequest request = new GetStreamRequest();
        Date endTime = new Date();
        Date startTime = new Date(endTime.getTime() - MILLIS_PER_HOUR);
        request.setSource(source);
        request.setEndTime(DateUtils.formatAlternateIso8601Date(endTime));
        request.setStartTime(DateUtils.formatAlternateIso8601Date(startTime));
        return getStream(request);
    }

    /**
     * This interface is no longer supported, please use new interface getStreamV2.
     **/
    @Deprecated
    public GetStreamResponse getStream(GetStreamRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, VERSION, STREAM);
        internalRequest.addParameter(PARAM_SOURCE, request.getSource());
        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", request.getStartTime());
        }
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        return this.invokeHttpClient(internalRequest, GetStreamResponse.class);
    }

    /**
     * Get a stream check task result with the specified source.
     *
     * @param source: The source of stream
     *
     * @return GetStreamResponse
     **/
    public GetStreamResponse getStreamV2(String source) {
        GetStreamRequest request = new GetStreamRequest();
        request.setSource(source);
        return getStreamV2(request);
    }

    /**
     * Get a stream check task result with GetStreamRequest.
     *
     * @param request: The request when get stream response
     *
     * @return GetStreamResponse
     **/
    public GetStreamResponse getStreamV2(GetStreamRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, VERSION_V2, STREAM);
        internalRequest.addParameter(PARAM_SOURCE, request.getSource());
        return this.invokeHttpClient(internalRequest, GetStreamResponse.class);
    }

    /**
     * Get a stream check task list with the specified maxKeys, marker and status.
     *
     * @param maxKeys: The stream check task number when return, max value is 100
     * @param marker: Starting position of this query, first query not use this value
     * @param status: The stream check status of stream
     *
     * @return GetStreamCheckTaskListResponse
     **/
    public GetStreamCheckTaskListResponse getStreamCheckTaskListV2(Integer maxKeys, String marker, String status) {
        GetStreamCheckTaskListRequest request = new GetStreamCheckTaskListRequest();
        request.setMaxKeys(maxKeys);
        request.setMarker(marker);
        request.setStatus(status);
        return getStreamCheckTaskListV2(request);
    }

    /**
     * Get a stream check task list with the specified maxKeys, marker and status.
     *
     * @param request: The request when get stream check task list
     *
     * @return GetStreamCheckTaskListResponse
     **/
    public GetStreamCheckTaskListResponse getStreamCheckTaskListV2(GetStreamCheckTaskListRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, VERSION_V2, STREAM, LIST);
        internalRequest.addParameter(PARAM_MAXKEYS, String.valueOf(request.getMaxKeys()));
        internalRequest.addParameter(PARAM_MARKER, request.getMarker());
        internalRequest.addParameter(PARAM_STATUS, request.getStatus());
        return this.invokeHttpClient(internalRequest, GetStreamCheckTaskListResponse.class);
    }

    /**
     * Create an audio check task with the specified source.
     *
     * @param source The source of audio
     *
     * @return PutAudioResponse
     */
    public PutAudioResponse putAudio(String source) {
        PutAudioRequest request = new PutAudioRequest();
        request.setSource(source);
        return putAudio(request);
    }

    public PutAudioResponse putAudio(PutAudioRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, VERSION_V2, AUDIO);
        return this.invokeHttpClient(internalRequest, PutAudioResponse.class);
    }

    /**
     * Get an audio check task result with the specified source.
     *
     * @param source The source of audio
     *
     * @return result of the audio check task
     */
    public GetAudioResponse getAudio(String source) {
        GetAudioRequest request = new GetAudioRequest();
        request.setSource(source);
        return getAudio(request);
    }

    public GetAudioResponse getAudio(GetAudioRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, VERSION_V2, AUDIO);
        internalRequest.addParameter(PARAM_SOURCE, request.getSource());
        return this.invokeHttpClient(internalRequest, GetAudioResponse.class);
    }

    /**
     * Create a image check task with the specified source.
     *
     * @param source The source of image
     *
     * @return result of the image check task
     */
    public PutImageResponse putImage(String source) {
        return putImage(source, null);
    }

    public PutImageResponse putImage(String source, String preset) {
        PutImageRequest request = new PutImageRequest();
        request.setSource(source);
        request.setPreset(preset);
        return putImage(request);
    }

    public PutImageResponse putImage(PutImageRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, VERSION, IMAGE);
        return this.invokeHttpClient(internalRequest, PutImageResponse.class);
    }

    /**
     * Create a image async check task, the result will callback through notification service.
     *
     * @param source       The source of image
     * @param preset       The preset of check, can be null
     * @param notification The name of notification, can be null
     *
     * @return PutImageAsyncResponse
     */
    public PutImageAsyncResponse putImageAsync(String source, String preset, String notification) {
        PutImageAsyncRequest request = new PutImageAsyncRequest();
        request.setSource(source);
        request.setPreset(preset);
        request.setNotification(notification);
        return putImageAsync(request);
    }

    public PutImageAsyncResponse putImageAsync(PutImageAsyncRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, VERSION_V2, IMAGE);
        return this.invokeHttpClient(internalRequest, PutImageAsyncResponse.class);
    }

    /**
     * Get a image async check task result temporary with the specified source and preset.
     *
     * @param source The source of image
     * @param preset The preset of check, should be same with the preset in putImageAsync
     *
     * @return result of the image async check task
     */
    public GetImageAsyncResponse getImageAsync(String source, String preset) {
        GetImageAsyncRequest request = new GetImageAsyncRequest();
        request.setSource(source);
        request.setPreset(preset);
        return getImageAsync(request);
    }

    public GetImageAsyncResponse getImageAsync(GetImageAsyncRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, VERSION_V2, IMAGE);
        internalRequest.addParameter(PARAM_SOURCE, request.getSource());
        internalRequest.addParameter("preset", StringUtils.isEmpty(request.getPreset()) ? "" : request.getPreset());
        return this.invokeHttpClient(internalRequest, GetImageAsyncResponse.class);
    }

    /**
     * Create a text check task with content.
     *
     * @param text The content of text
     *
     * @return result of the text check task
     */
    public PutTextResponse putText(String text) {
        PutTextRequest request = new PutTextRequest();
        request.setText(text);
        return putText(request);
    }

    public PutTextResponse putText(PutTextRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, VERSION, TEXT);
        return this.invokeHttpClient(internalRequest, PutTextResponse.class);
    }

    /**
     * add face lib image
     *
     * @param lib   lib name.
     * @param brief brief name.
     * @param image image url.
     *
     * @return the final response object
     */
    public LibResponse addFaceImage(String lib, String brief, String image) {
        return addFaceImage(new LibImageRequest(lib, brief, image));
    }

    /**
     * add face lib image
     *
     * @param request image request object.
     *
     * @return the final response object
     */
    public LibResponse addFaceImage(LibImageRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.POST,
                request, VERSION, FACE_LIB, request.getLib());
        return this.invokeHttpClient(internalRequest, LibResponse.class);
    }

    /**
     * delete face lib brief
     *
     * @param lib   lib name.
     * @param brief brief name.
     *
     * @return the final response object
     */
    public LibResponse delFaceBrief(String lib, String brief) {
        return delFaceBrief(new LibBriefRequest(lib, brief));
    }

    /**
     * delete face lib brief
     *
     * @param request brief request object.
     *
     * @return the final response object
     */
    public LibResponse delFaceBrief(LibBriefRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.DELETE,
                request, VERSION, FACE_LIB, request.getLib());
        internalRequest.addParameter("brief", request.getBrief());
        return this.invokeHttpClient(internalRequest, LibResponse.class);
    }

    /**
     * delete face lib image
     *
     * @param lib   lib name.
     * @param brief brief name.
     * @param image image url.
     *
     * @return the final response object
     */
    public LibResponse delFaceImage(String lib, String brief, String image) {
        return delFaceImage(new LibImageRequest(lib, brief, image));
    }

    /**
     * delete face lib image
     *
     * @param request face lib image request object.
     *
     * @return the final response object
     */
    public LibResponse delFaceImage(LibImageRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.DELETE,
                request, VERSION, FACE_LIB, request.getLib());
        internalRequest.addParameter("brief", request.getBrief());
        internalRequest.addParameter("image", request.getImage());
        return this.invokeHttpClient(internalRequest, LibResponse.class);
    }

    /**
     * get face lib brief list
     *
     * @param lib lib name.
     *
     * @return brief list object.
     */
    public LibBriefResponse getFaceLib(String lib) {
        LibBriefRequest request = new LibBriefRequest();
        request.setLib(lib);
        return getFaceLib(request);
    }

    /**
     * get face lib brief list
     *
     * @param request brief request object.
     *
     * @return brief list object.
     */
    public LibBriefResponse getFaceLib(LibBriefRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                request, VERSION, FACE_LIB, request.getLib());
        return this.invokeHttpClient(internalRequest, LibBriefResponse.class);
    }

    /**
     * get face lib image
     *
     * @param lib   lib name.
     * @param brief brief name.
     *
     * @return image list object
     */
    public LibImageResponse getFaceBrief(String lib, String brief) {
        return getFaceBrief(new LibBriefRequest(lib, brief));
    }

    /**
     * get face lib image
     *
     * @param request face lib image request object.
     *
     * @return image list object
     */
    public LibImageResponse getFaceBrief(LibBriefRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                request, VERSION, FACE_LIB, request.getLib());
        internalRequest.addParameter("brief", request.getBrief());
        return this.invokeHttpClient(internalRequest, LibImageResponse.class);
    }

    /**
     * add logo lib image
     *
     * @param lib   lib name.
     * @param brief brief name.
     * @param image image url.
     *
     * @return the final response object
     */
    public LibResponse addLogoImage(String lib, String brief, String image) {
        return this.addLogoImage(new LibImageRequest(lib, brief, image));
    }

    /**
     * add logo lib image
     *
     * @param request image request object.
     *
     * @return the final response object
     */
    public LibResponse addLogoImage(LibImageRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.POST,
                request, VERSION, LOGO_LIB, request.getLib());
        return this.invokeHttpClient(internalRequest, LibResponse.class);
    }

    /**
     * delete logo lib brief
     *
     * @param lib   lib name.
     * @param brief brief name.
     *
     * @return the final response object
     */
    public LibResponse delLogoBrief(String lib, String brief) {
        return delLogoBrief(new LibBriefRequest(lib, brief));
    }

    /**
     * delete logo brief
     *
     * @param request brief request object.
     *
     * @return the final response object
     */
    public LibResponse delLogoBrief(LibBriefRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.DELETE,
                request, VERSION, LOGO_LIB, request.getLib());
        internalRequest.addParameter("brief", request.getBrief());
        return this.invokeHttpClient(internalRequest, LibResponse.class);
    }

    /**
     * delete logo lib image
     *
     * @param lib   lib name.
     * @param image image url.
     *
     * @return the final response object
     */
    public LibResponse delLogoImage(String lib, String image) {
        return delLogoImage(new LibImageRequest(lib, "", image));
    }

    /**
     * delete logo lib image
     *
     * @param request image request object
     *
     * @return the final response object
     */
    public LibResponse delLogoImage(LibImageRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.DELETE,
                request, VERSION, LOGO_LIB, request.getLib());
        internalRequest.addParameter("image", request.getImage());
        return this.invokeHttpClient(internalRequest, LibResponse.class);
    }

    /**
     * get logo lib brief list
     *
     * @param lib lib name
     *
     * @return brief list object
     */
    public LibBriefResponse getLogoLib(String lib) {
        LibBriefRequest request = new LibBriefRequest();
        request.setLib(lib);
        return getLogoLib(request);
    }

    /**
     * get logo lib brief list
     *
     * @param request brief request object
     *
     * @return brief list object
     */
    public LibBriefResponse getLogoLib(LibBriefRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                request, VERSION, LOGO_LIB, request.getLib());
        return this.invokeHttpClient(internalRequest, LibBriefResponse.class);
    }

    /**
     * get logo lib image list
     *
     * @param lib   lib name.
     * @param brief brief name.
     *
     * @return image response object
     */
    public LibImageResponse getLogoBrief(String lib, String brief) {
        return getLogoBrief(new LibBriefRequest(lib, brief));
    }

    /**
     * get logo lib image list
     *
     * @param request brief request object.
     *
     * @return image response object
     */
    public LibImageResponse getLogoBrief(LibBriefRequest request) {
        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                request, VERSION, LOGO_LIB, request.getLib());
        internalRequest.addParameter("brief", request.getBrief());
        return this.invokeHttpClient(internalRequest, LibImageResponse.class);
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     * This method is responsible for determining HTTP method, URI path,
     * credentials and request body for POST method.
     * <p>
     * <b>Note: </b> The Query parameters in URL should be specified by caller method.
     * </p>
     *
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param request       The original request, as created by the user.
     * @param pathVariables The optional variables in URI path.
     *
     * @return A new request object, populated with endpoint, resource path,
     * ready for callers to populate any additional headers or
     * parameters, and execute.
     */
    private InternalRequest createRequest(
            HttpMethodName httpMethod, AbstractBceRequest request, String... pathVariables) {

        // build URL paths
        List<String> pathComponents = new ArrayList<String>();

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
