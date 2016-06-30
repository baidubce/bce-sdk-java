/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.lss;

import static com.baidubce.util.Validate.checkIsTrue;
import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
import com.baidubce.services.lss.model.AntiLeech;
import com.baidubce.services.lss.model.Audio;
import com.baidubce.services.lss.model.Auth;
import com.baidubce.services.lss.model.CreateNotificationRequest;
import com.baidubce.services.lss.model.CreateNotificationResponse;
import com.baidubce.services.lss.model.CreatePresetRequest;
import com.baidubce.services.lss.model.CreatePresetResponse;
import com.baidubce.services.lss.model.CreateSessionRequest;
import com.baidubce.services.lss.model.CreateSessionResponse;
import com.baidubce.services.lss.model.DeleteNotificationRequest;
import com.baidubce.services.lss.model.DeleteNotificationResponse;
import com.baidubce.services.lss.model.DeletePresetRequest;
import com.baidubce.services.lss.model.DeletePresetResponse;
import com.baidubce.services.lss.model.DeleteSessionRequest;
import com.baidubce.services.lss.model.DeleteSessionResponse;
import com.baidubce.services.lss.model.Encryption;
import com.baidubce.services.lss.model.GetNotificationRequest;
import com.baidubce.services.lss.model.GetNotificationResponse;
import com.baidubce.services.lss.model.GetPresetRequest;
import com.baidubce.services.lss.model.GetPresetResponse;
import com.baidubce.services.lss.model.GetRecordingRequest;
import com.baidubce.services.lss.model.GetRecordingResponse;
import com.baidubce.services.lss.model.GetSecurityPolicyRequest;
import com.baidubce.services.lss.model.GetSecurityPolicyResponse;
import com.baidubce.services.lss.model.GetSessionRequest;
import com.baidubce.services.lss.model.GetSessionResponse;
import com.baidubce.services.lss.model.GetSessionSourceInfoRequest;
import com.baidubce.services.lss.model.GetSessionSourceInfoResponse;
import com.baidubce.services.lss.model.Hls;
import com.baidubce.services.lss.model.InsertCuePointInnerRequest;
import com.baidubce.services.lss.model.InsertCuePointRequest;
import com.baidubce.services.lss.model.InsertCuePointResponse;
import com.baidubce.services.lss.model.ListNotificationsRequest;
import com.baidubce.services.lss.model.ListNotificationsResponse;
import com.baidubce.services.lss.model.ListPresetsRequest;
import com.baidubce.services.lss.model.ListPresetsResponse;
import com.baidubce.services.lss.model.ListRecordingsResponse;
import com.baidubce.services.lss.model.ListSecurityPoliciesRequest;
import com.baidubce.services.lss.model.ListSecurityPoliciesResponse;
import com.baidubce.services.lss.model.ListSessionsRequest;
import com.baidubce.services.lss.model.ListSessionsResponse;
import com.baidubce.services.lss.model.LivePublishInfo;
import com.baidubce.services.lss.model.LiveThumbnail;
import com.baidubce.services.lss.model.PauseSessionRequest;
import com.baidubce.services.lss.model.PauseSessionResponse;
import com.baidubce.services.lss.model.RefreshSessionRequest;
import com.baidubce.services.lss.model.RefreshSessionResponse;
import com.baidubce.services.lss.model.ResumeSessionRequest;
import com.baidubce.services.lss.model.ResumeSessionResponse;
import com.baidubce.services.lss.model.Rtmp;
import com.baidubce.services.lss.model.StartPullSessionRequest;
import com.baidubce.services.lss.model.StartPullSessionResponse;
import com.baidubce.services.lss.model.StartRecordingRequest;
import com.baidubce.services.lss.model.StartRecordingResponse;
import com.baidubce.services.lss.model.StopRecordingRequest;
import com.baidubce.services.lss.model.StopRecordingResponse;
import com.baidubce.services.lss.model.UpdateSecurityPolicyInnerRequest;
import com.baidubce.services.lss.model.UpdateSecurityPolicyRequest;
import com.baidubce.services.lss.model.UpdateSecurityPolicyResponse;
import com.baidubce.services.lss.model.Video;
import com.baidubce.services.lss.model.Watermarks;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

/**
 * Client for accessing Live Streaming Service. All service calls made
 * using this client are blocking, and will not return until the service call
 * completes.
 */
public class LssClient extends AbstractBceClient {
    /**
     * The version information for Lss service APIs as URI prefix.
     */
    private static final String VERSION = "v5";

    /**
     * The common URI prefix for live preset services.
     */
    private static final String LIVE_PRESET = "preset";

    /**
     * The common URI prefix for live session services.
     */
    private static final String LIVE_SESSION = "session";

    /**
     * The common URI prefix for live notification services.
     */
    private static final String LIVE_NOTIFICATION = "notification";

    /**
     * The common URI prefix for live security policy services.
     */
    private static final String LIVE_SECURITY_POLICY = "securitypolicy";

    /**
     * The common URI prefix for live recording services.
     */
    private static final String RECORDING = "recording";

    /**
     * Parameter for list live session with status.
     */
    private static final String STATUS = "status";

    /**
     * Parameter for pausing live session.
     */
    private static final String PAUSE = "pause";

    /**
     * Parameter for resuming live session.
     */
    private static final String RESUME = "resume";

    /**
     * Parameter for refreshing live session.
     */
    private static final String REFRESH = "refresh";

    /**
     * Parameter for starting pulling live session.
     */
    private static final String PULL = "pull";

    /**
     * Parameter for getting live source info.
     */
    private static final String SOURCE_INFO = "sourceInfo";

    /**
     * Parameter for inserting cue point.
     */
    private static final String CUE_POINT = "cuepoint";

    /**
     * Responsible for handling httpResponses from all service calls.
     */
    private static HttpResponseHandler[] lssHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new Lss client.
     */
    public LssClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new client using the client configuration.
     *
     * @param clientConfiguration The client configuration options controlling how this client
     *                            connects to Lss services (e.g. proxy settings, retry counts, etc).
     */
    public LssClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, lssHandlers);
    }

    /**
     * Create a live preset which contains parameters needed in the live stream service.
     *
     * @param request The request object containing all options for creating presets.
     */
    public CreatePresetResponse createPreset(CreatePresetRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getName(),
                "The parameter name should NOT be null or empty string.");

        if (request.getAudio() != null) {
            checkNotNull(request.getAudio().getBitRateInBps(),
                    "The parameter bitRateInBps in audio should NOT be null.");
            checkIsTrue(request.getAudio().getBitRateInBps() > 0,
                    "The audio's parameter bitRateInBps should be greater than zero.");
        }
        if (request.getVideo() != null) {
            checkNotNull(request.getVideo().getBitRateInBps(),
                    "The parameter bitRateInBps in video should NOT be null.");
            checkIsTrue(request.getVideo().getBitRateInBps() > 0,
                    "The video's parameter bitRateInBps should be greater than zero.");
            checkNotNull(request.getVideo().getMaxFrameRate(),
                    "The parameter maxFrameRate in video should NOT be null.");
            checkIsTrue(request.getVideo().getMaxFrameRate() > 0,
                    "The video's parameter maxFrameRate should be greater than zero.");
            checkNotNull(request.getVideo().getMaxWidthInPixel(),
                    "The parameter maxWidthInPixel in video should NOT be null.");
            checkIsTrue(request.getVideo().getMaxWidthInPixel() > 0,
                    "The video's parameter maxWidthInPixel should be greater than zero.");
            checkNotNull(request.getVideo().getMaxHeightInPixel(),
                    "The parameter maxHeightInPixel in video should NOT be null.");
            checkIsTrue(request.getVideo().getMaxHeightInPixel() > 0,
                    "The video's parameter maxHeightInPixel should be greater than zero.");
        }

        // not support yet
        request.setThumbnail(null);
        request.setWatermarks(null);

        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, LIVE_PRESET);
        return invokeHttpClient(internalRequest, CreatePresetResponse.class);
    }


    /**
     * Create a live preset which contains parameters needed in the live stream service, and not in forward only
     * mode, so that the input stream will be transcoded according to audio and video parameters.
     *
     * @param name  The name of the new live preset.
     * @param description The description of the new live preset
     * @param audio        Specify the audio parameters of live stream.
     * @param video        Specify the video parameters of live stream.
     * @param hls          Specify the hls parameters of live stream.
     * @param rtmp         Specify the rtmp parameters of live stream.
     * @param thumbnail   Specify the thumbnail parameters of live stream.
     * @param watermarks  Specify the watermarks parameters of live stream.
     *
     */
    public CreatePresetResponse createPreset(String name, String description, Audio audio, Video video,
                                             Hls hls, Rtmp rtmp, LiveThumbnail thumbnail, Watermarks watermarks) {
        CreatePresetRequest request = new CreatePresetRequest();
        request.setForwardOnly(false);
        request.setName(name);
        request.setDescription(description);
        request.setAudio(audio);
        request.setVideo(video);
        request.setHls(hls);
        request.setRtmp(rtmp);
        request.setThumbnail(thumbnail);
        request.setWatermarks(watermarks);

        return createPreset(request);
    }


    /**
     * Create a live preset which contains parameters needed in the live stream service, and in forward only mode, in
     * which the input stream's  resolution ratio and code rate will be kept unchanged.
     *
     * @param name  The name of the new live preset.
     * @param description The description of the new live preset
     * @param hls          Specify the hls parameters of live stream.
     * @param rtmp         Specify the rtmp parameters of live stream.
     * @param thumbnail   Specify the thumbnail parameters of live stream.
     * @param watermarks  Specify the watermarks parameters of live stream.
     *
     */
    public CreatePresetResponse createForwardOnlyPreset(String name, String description, Hls hls, Rtmp rtmp,
                                                        LiveThumbnail thumbnail, Watermarks watermarks) {
        CreatePresetRequest request = new CreatePresetRequest();
        request.setForwardOnly(true);
        request.setName(name);
        request.setDescription(description);
        request.setHls(hls);
        request.setRtmp(rtmp);
        request.setThumbnail(thumbnail);
        request.setWatermarks(watermarks);

        return createPreset(request);
    }


    /**
     * List all your live presets.
     *
     * @return The list of all your live presets
     */
    public ListPresetsResponse listPresets() {
        ListPresetsRequest request = new ListPresetsRequest();
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, LIVE_PRESET);
        return invokeHttpClient(internalRequest, ListPresetsResponse.class);
    }

    /**
     * Get your live preset by live preset name.
     *
     * @param name  Live preset name.
     *
     * @return Your live preset
     */
    public GetPresetResponse getPreset(String name) {
        GetPresetRequest request = new GetPresetRequest();
        request.setName(name);
        return getPreset(request);
    }

    /**
     * Get your live preset by live preset name.
     *
     * @param request The request object containing all parameters for getting live preset.
     *
     * @return Your live preset
     */
    public GetPresetResponse getPreset(GetPresetRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getName(), "The parameter name should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, LIVE_PRESET,
                request.getName());
        return invokeHttpClient(internalRequest, GetPresetResponse.class);
    }

    /**
     * Delete your live presets by live preset name.
     *
     * @param name  Live preset name.
     *
     */
    public DeletePresetResponse deletePreset(String name) {
        DeletePresetRequest request = new DeletePresetRequest();
        request.setName(name);
        return deletePreset(request);
    }


    /**
     * Delete your live presets by live preset name.
     *
     * @param request The request object containing all parameters for deleting live preset.
     *
     */
    public DeletePresetResponse deletePreset(DeletePresetRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getName(), "The parameter name should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.DELETE, request, LIVE_PRESET,
                request.getName());
        return invokeHttpClient(internalRequest, DeletePresetResponse.class);
    }



    /**
     * Create a live session in the live stream service.
     *
     * @param description The description of the new live session.
     * @param preset  The name of the new live session.
     * @param notification The notification of the new live session.
     * @param securityPolicy The security policy of the new live session.
     * @param recording The recording preset of the new live session.
     * @param publish       Specify the LivePublishInfo of live session.
     *
     */
    public CreateSessionResponse createSession(String description, String preset, String notification,
                                               String securityPolicy, String recording, LivePublishInfo publish) {
        CreateSessionRequest request = new CreateSessionRequest();
        request.withPreset(preset).withDescription(description).withNotification(notification);
        request.withSecurityPolicy(securityPolicy).withPublish(publish).withRecording(recording);
        return createSession(request);
    }

    /**
     * Create a live session in the live stream service.
     *
     * @param request The request object containing all options for creating live session.
     */
    public CreateSessionResponse createSession(CreateSessionRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getPreset(),
                "The parameter preset should NOT be null or empty string.");

        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, LIVE_SESSION);
        return invokeHttpClient(internalRequest, CreateSessionResponse.class);
    }


    /**
     * List all your live sessions.
     *
     * @return The list of all your live sessions.
     */
    public ListSessionsResponse listSessions() {
        ListSessionsRequest request = new ListSessionsRequest();
        return listSessions(request);
    }

    /**
     * List all your live sessions with given status.
     *
     * @param status  Live session status.
     *
     * @return The list of all your live sessions.
     */
    public ListSessionsResponse listSessions(String status) {
        ListSessionsRequest request = new ListSessionsRequest().withStatus(status);
        return listSessions(request);
    }

    /**
     * List all your live sessions.
     *
     * @param request The request object containing all parameters for listing live sessions.
     *
     * @return The list of all your live sessions.
     */
    public ListSessionsResponse listSessions(ListSessionsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, LIVE_SESSION);
        if (request.getStatus() != null) {
            checkStringNotEmpty(request.getStatus(), "The parameter status should NOT be empty string.");
            internalRequest.addParameter(STATUS, request.getStatus());
        }
        return invokeHttpClient(internalRequest, ListSessionsResponse.class);
    }

    /**
     * Get your live session by live session id.
     *
     * @param sessionId  Live session id.
     *
     * @return Your live session.
     */
    public GetSessionResponse getSession(String sessionId) {
        GetSessionRequest request = new GetSessionRequest();
        request.setSessionId(sessionId);
        return getSession(request);
    }

    /**
     * Get your live session with token by live session id.
     *
     * @param sessionId  Live session id.
     * @param timeoutInMinute  Timeout of token.
     *
     * @return Your live session with token.
     */
    public GetSessionResponse getSessionWithToken(String sessionId, Integer timeoutInMinute) {
        GetSessionResponse getSessionResponse = getSession(sessionId);
        if (timeoutInMinute == null) {
            return getSessionResponse;
        }
        DateTime dateTime = new DateTime(DateTimeZone.UTC);
        DateTime expireTime = dateTime.plusMinutes(timeoutInMinute);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String expire = formatter.print(expireTime);

        GetSecurityPolicyResponse getSecurityPolicyResponse = getSecurityPolicy(getSessionResponse.getSecurityPolicy());
        if (getSecurityPolicyResponse.getAuth().getPlay()) {
            String hlsUrl = getSessionResponse.getPlay().getHlsUrl();
            String rtmpUrl = getSessionResponse.getPlay().getRtmpUrl();
            if (hlsUrl != null) {
                String hlsToken = LssUtils.hmacSha256(
                        String.format("/%s/live.m3u8;%s", sessionId, expire),
                        getSecurityPolicyResponse.getAuth().getKey());
                if (hlsUrl.lastIndexOf('?') == -1) {
                    hlsUrl += String.format("?token=%s&expire=%s", hlsToken, expire);
                } else {
                    hlsUrl += String.format("&token=%s&expire=%s", hlsToken, expire);
                }
                getSessionResponse.getPlay().setHlsUrl(hlsUrl);
            }
            if (rtmpUrl != null) {
                String rtmpToken = LssUtils.hmacSha256(
                        String.format("%s;%s", sessionId, expire),
                        getSecurityPolicyResponse.getAuth().getKey());
                rtmpUrl += String.format("?token=%s&expire=%s", rtmpToken, expire);
                getSessionResponse.getPlay().setRtmpUrl(rtmpUrl);
            }
        }

        if (getSecurityPolicyResponse.getAuth().getPush()) {
            String pushUrl = getSessionResponse.getPublish().getPushUrl();
            String pushToken = LssUtils.hmacSha256(
                    String.format("%s;%s", getSessionResponse.getPublish().getPushStream(), expire),
                    getSecurityPolicyResponse.getAuth().getKey());
            pushUrl += String.format("?token=%s&expire=%s", pushToken, expire);
            getSessionResponse.getPublish().setPushUrl(pushUrl);
        }
        return getSessionResponse;
    }

    /**
     * Get your live session by live session id.
     *
     * @param request The request object containing all parameters for getting live session.
     *
     * @return Your live session.
     */
    public GetSessionResponse getSession(GetSessionRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getSessionId(), "The parameter sessionId should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, LIVE_SESSION,
                request.getSessionId());
        return invokeHttpClient(internalRequest, GetSessionResponse.class);
    }

    /**
     * Delete your live session by live session id.
     *
     * @param sessionId  Live session id.
     *
     */
    public DeleteSessionResponse deleteSession(String sessionId) {
        DeleteSessionRequest request = new DeleteSessionRequest();
        request.setSessionId(sessionId);
        return deleteSession(request);
    }

    /**
     * Delete your live session by live session id.
     *
     * @param request The request object containing all parameters for deleting live session.
     *
     */
    public DeleteSessionResponse deleteSession(DeleteSessionRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getSessionId(), "The parameter sessionId should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.DELETE, request, LIVE_SESSION,
                request.getSessionId());
        return invokeHttpClient(internalRequest, DeleteSessionResponse.class);
    }

    /**
     * Pause your live session by live session id.
     *
     * @param sessionId  Live session id.
     *
     */
    public PauseSessionResponse pauseSession(String sessionId) {
        PauseSessionRequest request = new PauseSessionRequest();
        request.setSessionId(sessionId);
        return pauseSession(request);
    }

    /**
     * Pause your live session by live session id.
     *
     * @param request The request object containing all parameters for pausing live session.
     *
     */
    public PauseSessionResponse pauseSession(PauseSessionRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getSessionId(), "The parameter sessionId should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, LIVE_SESSION,
                request.getSessionId());
        internalRequest.addParameter(PAUSE, null);
        return invokeHttpClient(internalRequest, PauseSessionResponse.class);
    }


    /**
     * Resume your live session by live session id.
     *
     * @param sessionId  Live session id.
     *
     */
    public ResumeSessionResponse resumeSession(String sessionId) {
        ResumeSessionRequest request = new ResumeSessionRequest();
        request.setSessionId(sessionId);
        return resumeSession(request);
    }

    /**
     * Resume your live session by live session id.
     *
     * @param request The request object containing all parameters for resuming live session.
     *
     */
    public ResumeSessionResponse resumeSession(ResumeSessionRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getSessionId(), "The parameter sessionId should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, LIVE_SESSION,
                request.getSessionId());
        internalRequest.addParameter(RESUME, null);
        return invokeHttpClient(internalRequest, ResumeSessionResponse.class);
    }

    /**
     * Refresh your live session by live session id.
     *
     * @param sessionId  Live session id.
     *
     */
    public RefreshSessionResponse refreshSession(String sessionId) {
        RefreshSessionRequest request = new RefreshSessionRequest();
        request.setSessionId(sessionId);
        return refreshSession(request);
    }

    /**
     * Refresh your live session by live session id.
     *
     * @param request The request object containing all parameters for refreshing live session.
     *
     */
    public RefreshSessionResponse refreshSession(RefreshSessionRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getSessionId(), "The parameter sessionId should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, LIVE_SESSION,
                request.getSessionId());
        internalRequest.addParameter(REFRESH, null);
        return invokeHttpClient(internalRequest, RefreshSessionResponse.class);
    }

    /**
     * Start your pulling live session by live session id.
     *
     * @param sessionId  Live session id.
     *
     */
    public StartPullSessionResponse startPullSession(String sessionId) {
        StartPullSessionRequest request = new StartPullSessionRequest().withSessionId(sessionId);
        return startPullSession(request);
    }

    /**
     * Start your pulling live session by live session id.
     *
     * @param request The request object containing all parameters for starting pulling live session.
     *
     */
    public StartPullSessionResponse startPullSession(StartPullSessionRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getSessionId(), "The parameter sessionId should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, LIVE_SESSION,
                request.getSessionId());
        internalRequest.addParameter(PULL, null);
        return invokeHttpClient(internalRequest, StartPullSessionResponse.class);
    }

    /**
     * Start live session recording.
     *
     * @param sessionId Live session id.
     * @param recording Live recording preset name.
     */
    public StartRecordingResponse startRecording(String sessionId, String recording) {
        checkStringNotEmpty(sessionId, "The parameter sessionId should NOT be null or empty string.");
        checkStringNotEmpty(recording, "The parameter recording should NOT be null or empty string.");
        StartRecordingRequest request = new StartRecordingRequest().withSessionId(sessionId);
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, LIVE_SESSION, sessionId);
        internalRequest.addParameter(RECORDING, recording);
        return invokeHttpClient(internalRequest, StartRecordingResponse.class);
    }

    /**
     * Stop live session recording.
     *
     * @param sessionId Live session id.
     */
    public StopRecordingResponse stopRecording(String sessionId) {
        checkStringNotEmpty(sessionId, "The parameter sessionId should NOT be null or empty string.");
        StopRecordingRequest request = new StopRecordingRequest().withSessionId(sessionId);
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, LIVE_SESSION, sessionId);
        internalRequest.addParameter(RECORDING, null);
        return invokeHttpClient(internalRequest, StopRecordingResponse.class);
    }

    /**
     * Get your live session source info by live session id.
     *
     * @param sessionId Live session id.
     *
     * @return Your live session source info
     */
    public GetSessionSourceInfoResponse getSessionSourceInfo(String sessionId) {
        checkStringNotEmpty(sessionId, "The parameter sessionId should NOT be null or empty string.");
        GetSessionSourceInfoRequest request = new GetSessionSourceInfoRequest();
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, LIVE_SESSION, sessionId);
        internalRequest.addParameter(SOURCE_INFO, null);
        return invokeHttpClient(internalRequest, GetSessionSourceInfoResponse.class);
    }

    /**
     * Insert a cue point into your live session by live session id.
     *
     * @param sessionId  Live session id.
     * @param callback  Call back method name.
     * @param arguments  Call back method arguments.
     *
     */
    public InsertCuePointResponse insertCuePoint(String sessionId, String callback, Map<String, String> arguments) {
        InsertCuePointRequest request = new InsertCuePointRequest()
                .withSessionId(sessionId).withCallback(callback).withArguments(arguments);
        return insertCuePoint(request);
    }

    /**
     * Insert a cue point into your live session by live session id.
     *
     * @param request The request object containing all parameters for inserting a cue point into session.
     *
     */
    public InsertCuePointResponse insertCuePoint(InsertCuePointRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getSessionId(),
                "The parameter sessionId should NOT be null or empty string.");
        checkStringNotEmpty(request.getCallback(),
                "The parameter callback should NOT be null or empty string.");
        InsertCuePointInnerRequest innerRequest = new InsertCuePointInnerRequest()
                .withArguments(request.getArguments()).withCallback(request.getCallback());
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, innerRequest, LIVE_SESSION,
                request.getSessionId());
        internalRequest.addParameter(CUE_POINT, null);
        return invokeHttpClient(internalRequest, InsertCuePointResponse.class);
    }



    /**
     * Get your live recording preset by live recording preset name.
     *
     * @param recording Live recording preset name.
     *
     * @return Your live recording preset
     */
    public GetRecordingResponse getRecording(String recording) {
        checkStringNotEmpty(recording, "The parameter recording should NOT be null or empty string.");
        GetRecordingRequest request = new GetRecordingRequest();
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, RECORDING, recording);
        return invokeHttpClient(internalRequest, GetRecordingResponse.class);
    }

    /**
     * List all your live recording presets.
     *
     * @return The list of all your live recording preset.
     */
    public ListRecordingsResponse listRecordings() {
        GetRecordingRequest request = new GetRecordingRequest();
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, RECORDING);
        return invokeHttpClient(internalRequest, ListRecordingsResponse.class);
    }


    /**
     * List all your live notifications.
     *
     * @return The list of all your live notifications
     */
    public ListNotificationsResponse listNotifications() {
        ListNotificationsRequest request = new ListNotificationsRequest();
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, LIVE_NOTIFICATION);
        return invokeHttpClient(internalRequest, ListNotificationsResponse.class);
    }

    /**
     * Delete your live notification by live notification name.
     *
     * @param name  Live notification name.
     *
     */
    public DeleteNotificationResponse deleteNotification(String name) {
        DeleteNotificationRequest request = new DeleteNotificationRequest();
        request.setName(name);
        return deleteNotification(request);
    }

    /**
     * Delete your live notification by live notification name.
     *
     * @param request The request object containing all parameters for deleting live notification.
     *
     */
    public DeleteNotificationResponse deleteNotification(DeleteNotificationRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getName(), "The parameter name should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.DELETE, request, LIVE_NOTIFICATION, request
                .getName());
        return invokeHttpClient(internalRequest, DeleteNotificationResponse.class);
    }

    /**
     * Get your live notification by live notification name.
     *
     * @param name  Live notification name.
     *
     * @return Your live notification.
     */
    public GetNotificationResponse getNotification(String name) {
        GetNotificationRequest request = new GetNotificationRequest();
        request.setName(name);
        return getNotification(request);
    }

    /**
     * Get your live notification by live notification name.
     *
     * @param request The request object containing all parameters for getting live notification.
     *
     * @return Your live notification.
     */
    public GetNotificationResponse getNotification(GetNotificationRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getName(), "The parameter name should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, LIVE_NOTIFICATION,
                request.getName());
        return invokeHttpClient(internalRequest, GetNotificationResponse.class);
    }

    /**
     * Create a live notification in the live stream service.
     *
     * @param name  The name of notification.
     * @param endpoint The address to receive notification message.
     *
     */
    public CreateNotificationResponse createNotification(String name, String endpoint) {
        CreateNotificationRequest request = new CreateNotificationRequest();
        request.withName(name).withEndpoint(endpoint);
        return createNotification(request);
    }

    /**
     * Create a live notification in the live stream service.
     *
     * @param request The request object containing all options for creating live notification.
     */
    public CreateNotificationResponse createNotification(CreateNotificationRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getName(),
                "The parameter name should NOT be null or empty string.");
        checkStringNotEmpty(request.getEndpoint(),
                "The parameter endpoint should NOT be null or empty string.");

        InternalRequest internalRequest = createRequest(HttpMethodName.POST, request, LIVE_NOTIFICATION);
        return invokeHttpClient(internalRequest, CreateNotificationResponse.class);
    }

    /**
     * List all your live security policys.
     *
     * @return The list of all your live security policys
     */
    public ListSecurityPoliciesResponse listSecurityPolicies() {
        ListSecurityPoliciesRequest request = new ListSecurityPoliciesRequest();
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, LIVE_SECURITY_POLICY);
        return invokeHttpClient(internalRequest, ListSecurityPoliciesResponse.class);
    }

    /**
     * Get your live security policy by live security policy name.
     *
     * @param name  Live security policy name.
     *
     * @return Your live security policy.
     */
    public GetSecurityPolicyResponse getSecurityPolicy(String name) {
        GetSecurityPolicyRequest request = new GetSecurityPolicyRequest();
        request.setName(name);
        return getSecurityPolicy(request);
    }

    /**
     * Get your live security policy by live security policy name.
     *
     * @param request The request object containing all parameters for getting live security policy.
     *
     * @return Your live security policy.
     */
    public GetSecurityPolicyResponse getSecurityPolicy(GetSecurityPolicyRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getName(),
                "The parameter name should NOT be null or empty string.");

        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, LIVE_SECURITY_POLICY,
                request.getName());
        return invokeHttpClient(internalRequest, GetSecurityPolicyResponse.class);
    }

    /**
     * Update your live security policy by live security policy name.
     *
     * @param name  Live security policy name.
     * @param auth  Configuration for authentication.
     * @param antiLeech  Configuration for anti-leech.
     * @param encryption  Configuration for encryption.
     */
    public UpdateSecurityPolicyResponse updateSecurityPolicy(String name, Auth auth, AntiLeech antiLeech,
                                                             Encryption encryption) {
        UpdateSecurityPolicyRequest request = new UpdateSecurityPolicyRequest();
        request.withName(name).withAuth(auth).withAntiLeech(antiLeech).withEncryption(encryption);
        return updateSecurityPolicy(request);

    }

    /**
     * Update your live security policy by live security policy name.
     *
     * @param request  The request object containing all parameters for updating live security policy.
     */
    public UpdateSecurityPolicyResponse updateSecurityPolicy(UpdateSecurityPolicyRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getName(), "The parameter name should NOT be null or empty string.");
        UpdateSecurityPolicyInnerRequest innerRequest = new UpdateSecurityPolicyInnerRequest();
        innerRequest.withAuth(request.getAuth()).withAntiLeech(request.getAntiLeech())
                .withEncryption(request.getEncryption());
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, innerRequest, LIVE_SECURITY_POLICY,
                request.getName());
        return invokeHttpClient(internalRequest, UpdateSecurityPolicyResponse.class);

    }


    /**
     * Creates and initializes a new request object for the specified resource.
     * This method is responsible for determining HTTP method, URI path,
     * credentials and request body for POST method.
     * <p>
     * <b>Note: </b> The Query parameters in URL should be specified by caller method.
     * </p>
     * @param httpMethod
     *            The HTTP method to use when sending the request.
     * @param request
     *            The original request, as created by the user.
     * @param pathVariables
     *            The optional variables in URI path.
     * @return A new request object, populated with endpoint, resource path,
     *         ready for callers to populate any additional headers or
     *         parameters, and execute.
     */
    private InternalRequest createRequest(
            HttpMethodName httpMethod, AbstractBceRequest request, String...pathVariables) {

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
