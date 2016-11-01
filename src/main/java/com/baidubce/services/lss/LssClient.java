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
import com.baidubce.services.lss.model.CreateStreamRequest;
import com.baidubce.services.lss.model.CreateStreamResponse;
import com.baidubce.services.lss.model.DeleteNotificationRequest;
import com.baidubce.services.lss.model.DeleteNotificationResponse;
import com.baidubce.services.lss.model.DeletePresetRequest;
import com.baidubce.services.lss.model.DeletePresetResponse;
import com.baidubce.services.lss.model.DeleteSessionRequest;
import com.baidubce.services.lss.model.DeleteSessionResponse;
import com.baidubce.services.lss.model.Encryption;
import com.baidubce.services.lss.model.GetAllDomainsBandwidthResponse;
import com.baidubce.services.lss.model.GetAllDomainsPlayCountResponse;
import com.baidubce.services.lss.model.GetAllDomainsStatisticsRequest;
import com.baidubce.services.lss.model.GetAllDomainsTrafficResponse;
import com.baidubce.services.lss.model.GetDomainStatisticsRequest;
import com.baidubce.services.lss.model.GetDomainStatisticsResponse;
import com.baidubce.services.lss.model.GetDomainSummaryStatisticsRequest;
import com.baidubce.services.lss.model.GetDomainSummaryStatisticsResponse;
import com.baidubce.services.lss.model.GetOneDomainTrafficResponse;
import com.baidubce.services.lss.model.ListDomainStatisticsResponse;
import com.baidubce.services.lss.model.GetStreamStatisticsResponse;
import com.baidubce.services.lss.model.ListStreamStatisticsResponse;
import com.baidubce.services.lss.model.ListDomainStatisticsRequest;
import com.baidubce.services.lss.model.ListStreamStatisticsRequest;
import com.baidubce.services.lss.model.GetStreamStatisticsRequest;
import com.baidubce.services.lss.model.GetNotificationRequest;
import com.baidubce.services.lss.model.GetNotificationResponse;
import com.baidubce.services.lss.model.GetOneDomainBandwidthResponse;
import com.baidubce.services.lss.model.GetOneDomainPlayCountResponse;
import com.baidubce.services.lss.model.GetOneDomainStatisticsRequest;
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
import com.baidubce.services.lss.model.GetStreamRequest;
import com.baidubce.services.lss.model.GetStreamResponse;
import com.baidubce.services.lss.model.Hls;
import com.baidubce.services.lss.model.InsertCuePointInnerRequest;
import com.baidubce.services.lss.model.InsertCuePointRequest;
import com.baidubce.services.lss.model.InsertCuePointResponse;
import com.baidubce.services.lss.model.ListDomainAppRequest;
import com.baidubce.services.lss.model.ListDomainAppResponse;
import com.baidubce.services.lss.model.ListNotificationsRequest;
import com.baidubce.services.lss.model.ListNotificationsResponse;
import com.baidubce.services.lss.model.ListPresetsRequest;
import com.baidubce.services.lss.model.ListPresetsResponse;
import com.baidubce.services.lss.model.ListRecordingsResponse;
import com.baidubce.services.lss.model.ListSecurityPoliciesRequest;
import com.baidubce.services.lss.model.ListSecurityPoliciesResponse;
import com.baidubce.services.lss.model.ListSessionsRequest;
import com.baidubce.services.lss.model.ListSessionsResponse;
import com.baidubce.services.lss.model.ListStreamRequest;
import com.baidubce.services.lss.model.ListStreamResponse;
import com.baidubce.services.lss.model.LivePublishInfo;
import com.baidubce.services.lss.model.LiveThumbnail;
import com.baidubce.services.lss.model.PauseAppStreamRequest;
import com.baidubce.services.lss.model.PauseAppStreamResponse;
import com.baidubce.services.lss.model.PauseDomainStreamRequest;
import com.baidubce.services.lss.model.PauseDomainStreamResponse;
import com.baidubce.services.lss.model.PauseSessionRequest;
import com.baidubce.services.lss.model.PauseSessionResponse;
import com.baidubce.services.lss.model.RefreshSessionRequest;
import com.baidubce.services.lss.model.RefreshSessionResponse;
import com.baidubce.services.lss.model.ResumeAppStreamRequest;
import com.baidubce.services.lss.model.ResumeAppStreamResponse;
import com.baidubce.services.lss.model.ResumeSessionRequest;
import com.baidubce.services.lss.model.ResumeSessionResponse;
import com.baidubce.services.lss.model.ResumeDomainStreamRequest;
import com.baidubce.services.lss.model.ResumeDomainStreamResponse;
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
import com.baidubce.services.lss.model.GetAppResponse;
import com.baidubce.services.lss.model.GetAppRequest;
import com.baidubce.services.lss.model.GetAppStreamResponse;
import com.baidubce.services.lss.model.GetAppStreamRequest;
import com.baidubce.services.lss.model.ListAppResponse;
import com.baidubce.services.lss.model.ListAppRequest;
import com.baidubce.services.lss.model.ListAppStreamsResponse;
import com.baidubce.services.lss.model.ListAppStreamsRequest;
import com.baidubce.services.lss.model.GetSessionStatisticsRequest;
import com.baidubce.services.lss.model.GetSessionStatisticsResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.baidubce.util.Validate.checkIsTrue;
import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;

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
     * The common URI prefix for live domain services.
     */
    private static final String LIVE_DOMAIN = "domain";

    /**
     * The common URI prefix for live stream services.
     */
    private static final String LIVE_STREAM = "stream";

    /**
     * The common URI prefix for live app services.
     */
    private static final String LIVE_APP = "app";

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
     * Parameter for statistics.
     */
    private static final String STATISTICS = "statistics";

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
     * @param description The description of the new live session.
     * @param presets  The name of the new live session.
     * @param notification The notification of the new live session.
     * @param securityPolicy The security policy of the new live session.
     * @param recording The recording preset of the new live session.
     * @param publish       Specify the LivePublishInfo of live session.
     *
     */
    public CreateSessionResponse createSession(String description, List<String> presets, String notification,
                                               String securityPolicy, String recording, LivePublishInfo publish) {
        CreateSessionRequest request = new CreateSessionRequest();
        Map<String, String> presetMap = new HashMap<String, String>();
        for (int i = 0; i < presets.size(); i++) {
            presetMap.put("L" + i, presets.get(i));
        }
        request.withPresets(presetMap).withDescription(description).withNotification(notification);
        request.withSecurityPolicy(securityPolicy).withPublish(publish).withRecording(recording);
        return createSession(request);
    }

    /**
     * Create a live session in the live stream service.
     *
     * @param description The description of the new live session.
     * @param presets  The name of the new live session.
     * @param notification The notification of the new live session.
     * @param securityPolicy The security policy of the new live session.
     * @param recording The recording preset of the new live session.
     * @param publish       Specify the LivePublishInfo of live session.
     * @param thumbnail The thumbnail of new live session
     * @param watermarks The watermarks of new live session
     *
     */
    public CreateSessionResponse createSession(String description, List<String> presets, String notification,
                                               String securityPolicy, String recording, LivePublishInfo publish,
                                               String thumbnail, Watermarks watermarks) {
        CreateSessionRequest request = new CreateSessionRequest();
        Map<String, String> presetMap = new HashMap<String, String>();
        for (int i = 0; i < presets.size(); i++) {
            presetMap.put("L" + i, presets.get(i));
        }
        request.withPresets(presetMap).withDescription(description).withNotification(notification);
        request.withSecurityPolicy(securityPolicy).withPublish(publish).withRecording(recording);
        request.withThumbnail(thumbnail).withWatermarks(watermarks);
        return createSession(request);
    }

    /**
     * Create a live session in the live stream service.
     *
     * @param request The request object containing all options for creating live session.
     */
    public CreateSessionResponse createSession(CreateSessionRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        if (request.getPreset() == null && request.getPresets() == null) {
            throw new IllegalArgumentException("The parameter preset and presets should NOT both be null or empty.");
        }

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
        Map<String, String> hlsUrls = new HashMap<String, String>();
        Map<String, String> rtmpUrls = new HashMap<String, String>();
        Map<String, String> flvUrls = new HashMap<String, String>();
        if (getSecurityPolicyResponse.getAuth().getPlay()) {
            if (getSessionResponse.getPlay().getHlsUrls() != null) {
                for (Map.Entry<String, String> entry : getSessionResponse.getPlay().getHlsUrls().entrySet()) {
                    String line = entry.getKey();
                    String hlsUrl = entry.getValue();
                    if (hlsUrl != null) {
                        String hlsToken = null;
                        if (line.equals("L0")) {
                            hlsToken = LssUtils.hmacSha256(
                                    String.format("/%s/live.m3u8;%s", sessionId, expire),
                                    getSecurityPolicyResponse.getAuth().getKey());
                        } else {
                            hlsToken = LssUtils.hmacSha256(
                                    String.format("/%s-%s/live.m3u8;%s", sessionId, line, expire),
                                    getSecurityPolicyResponse.getAuth().getKey());
                        }
                        if (hlsUrl.lastIndexOf('?') == -1) {
                            hlsUrl += String.format("?token=%s&expire=%s", hlsToken, expire);
                        } else {
                            hlsUrl += String.format("&token=%s&expire=%s", hlsToken, expire);
                        }
                        hlsUrls.put(line, hlsUrl);
                    }
                }

                getSessionResponse.getPlay().setHlsUrls(hlsUrls);
            } else if (getSessionResponse.getPlay().getHlsUrl() != null) {
                String hlsUrl = getSessionResponse.getPlay().getHlsUrl();
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

            if (getSessionResponse.getPlay().getRtmpUrls() != null) {
                for (Map.Entry<String, String> entry : getSessionResponse.getPlay().getRtmpUrls().entrySet()) {
                    String line = entry.getKey();
                    String rtmpUrl = entry.getValue();
                    if (rtmpUrl != null) {
                        String rtmpToken = LssUtils.hmacSha256(
                                String.format("%s;%s", sessionId, expire),
                                getSecurityPolicyResponse.getAuth().getKey());
                        rtmpUrl += String.format("?token=%s&expire=%s", rtmpToken, expire);
                    }
                    rtmpUrls.put(line, rtmpUrl);
                }

                getSessionResponse.getPlay().setRtmpUrls(rtmpUrls);
            } else if (getSessionResponse.getPlay().getRtmpUrl() != null) {
                String rtmpUrl = getSessionResponse.getPlay().getRtmpUrl();
                String rtmpToken = LssUtils.hmacSha256(
                        String.format("%s;%s", sessionId, expire),
                        getSecurityPolicyResponse.getAuth().getKey());
                rtmpUrl += String.format("?token=%s&expire=%s", rtmpToken, expire);

                getSessionResponse.getPlay().setRtmpUrl(rtmpUrl);
            }

            if (getSessionResponse.getPlay().getFlvUrls() != null) {
                for (Map.Entry<String, String> entry : getSessionResponse.getPlay().getFlvUrls().entrySet()) {
                    String line = entry.getKey();
                    String flvUrl = entry.getValue();
                    if (flvUrl != null) {
                        String flvToken = LssUtils.hmacSha256(
                                String.format("%s;%s", flvUrl, expire),
                                getSecurityPolicyResponse.getAuth().getKey());
                        flvUrl += String.format("?token=%s&expire=%s", flvToken, expire);
                    }
                    flvUrls.put(line, flvUrl);
                }

                getSessionResponse.getPlay().setFlvUrls(flvUrls);
            } else if (getSessionResponse.getPlay().getFlvUrl() != null) {
                String flvUrl = getSessionResponse.getPlay().getFlvUrl();
                String flvToken = LssUtils.hmacSha256(
                        String.format("%s;%s", flvUrl, expire),
                        getSecurityPolicyResponse.getAuth().getKey());
                flvUrl += String.format("?token=%s&expire=%s", flvToken, expire);

                getSessionResponse.getPlay().setFlvUrl(flvUrl);

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
     * get detail of your app by name
     *
     * @param app  app name
     *
     */
    public GetAppResponse queryApp(String app) {
        GetAppRequest request = new GetAppRequest();
        request.setApp(app);
        return queryApp(request);
    }

    /**
     * get detail of your app by name
     *
     * @param request  The request object containing all parameters for querying app.
     *
     */
    public GetAppResponse queryApp(GetAppRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getApp(), "The parameter app should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, LIVE_APP,
                request.getApp());
        return invokeHttpClient(internalRequest, GetAppResponse.class);
    }

    /**
     * list all your apps
     *
     */
    public ListAppResponse listApp() {
        ListAppRequest request = new ListAppRequest();
        return listApp(request);
    }

    /**
     * list all your apps
     *
     * @param request  The request object containing all parameters for list all apps.
     *
     */
    public ListAppResponse listApp(ListAppRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, LIVE_APP);
        return invokeHttpClient(internalRequest, ListAppResponse.class);
    }

    /**
     * get detail of your stream by app name and stream name
     *
     * @param app  app name
     * @param stream  stream name
     *
     */
    public GetAppStreamResponse queryAppStream(String app, String stream) {
        GetAppStreamRequest request = new GetAppStreamRequest();
        request.setApp(app);
        request.setStream(stream);
        return queryAppStream(request);
    }

    /**
     * get detail of your stream by app name and stream name
     *
     *  @param request The request object containing all parameters for query app stream.
     *
     */
    public GetAppStreamResponse queryAppStream(GetAppStreamRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getApp(), "The parameter app should NOT be null or empty string.");
        checkStringNotEmpty(request.getStream(), "The parameter stream should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, LIVE_APP,
                request.getApp(), LIVE_SESSION, request.getStream());
        return invokeHttpClient(internalRequest, GetAppStreamResponse.class);
    }

    /**
     * list your streams by app name and stream status
     *
     * @param app  app name
     * @param status stream status
     *
     */
    public ListAppStreamsResponse listAppStreams(String app, String status) {
        ListAppStreamsRequest request = new ListAppStreamsRequest();
        request.setApp(app);
        request.setStatus(status);
        return listAppStreams(request);
    }


    /**
     * list your streams by app name
     *
     * @param app  app name
     *
     */
    public ListAppStreamsResponse listAppStreams(String app) {
        ListAppStreamsRequest request = new ListAppStreamsRequest();
        request.setApp(app);
        return listAppStreams(request);
    }

    /**
     * list your streams by app name and stream status
     *
     * @param request The request object containing all parameters for list app streams.
     *
     */
    public ListAppStreamsResponse listAppStreams(ListAppStreamsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getApp(), "The parameter app should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, LIVE_APP,
                request.getApp(), LIVE_SESSION);
        if (request.getStatus() != null) {
            internalRequest.addParameter("status", request.getStatus());
        }
        return invokeHttpClient(internalRequest, ListAppStreamsResponse.class);
    }

    /**
     * Pause your app stream by app name and stream name
     *
     * @param app  app name
     * @param stream  stream name
     *
     */
    public PauseAppStreamResponse pauseAppStream(String app, String stream) {
        PauseAppStreamRequest pauseAppStreamRequest = new PauseAppStreamRequest();
        pauseAppStreamRequest.setApp(app);
        pauseAppStreamRequest.setStream(stream);
        return pauseAppStream(pauseAppStreamRequest);
    }

    /**
     * Pause your app stream by app name and stream name
     *
     *  @param request The request object containing all parameters for pausing app session.
     *
     */
    public PauseAppStreamResponse pauseAppStream(PauseAppStreamRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getApp(), "The parameter app should NOT be null or empty string.");
        checkStringNotEmpty(request.getStream(), "The parameter stream should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, LIVE_APP,
                request.getApp(), LIVE_SESSION, request.getStream());
        internalRequest.addParameter(PAUSE, null);
        return invokeHttpClient(internalRequest, PauseAppStreamResponse.class);
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
     * Resume your app stream by app name and stream name
     *
     * @param app  app name
     * @param stream  stream name
     *
     */
    public ResumeAppStreamResponse resumeAppStream(String app, String stream) {
        ResumeAppStreamRequest request = new ResumeAppStreamRequest();
        request.setApp(app);
        request.setStream(stream);
        return resumeAppStream(request);
    }

    /**
     * Resume your app stream by app name and stream name
     *
     *  @param request The request object containing all parameters for resuming app session.
     *
     */
    public ResumeAppStreamResponse resumeAppStream(ResumeAppStreamRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getApp(), "The parameter app should NOT be null or empty string.");
        checkStringNotEmpty(request.getStream(), "The parameter stream should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.PUT, request, LIVE_APP,
                request.getApp(), LIVE_SESSION, request.getStream());
        internalRequest.addParameter(RESUME, null);
        return invokeHttpClient(internalRequest, ResumeAppStreamResponse.class);
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

    public GetSessionStatisticsResponse getSessionStatistics(String sessionId, String startDate,
                                                             String endDate, Boolean aggregate) {

        GetSessionStatisticsRequest request = new GetSessionStatisticsRequest();
        request.withSessionId(sessionId).withStartDate(startDate).withEndDate(endDate).withAggregate(aggregate);
        return getSessionStatistics(request);
    }

    public GetSessionStatisticsResponse getSessionStatistics(GetSessionStatisticsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkStringNotEmpty(request.getSessionId(), "The parameter sessionId should NOT be null or empty string.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, STATISTICS, LIVE_SESSION,
                request.getSessionId());
        if (request.getStartDate() != null) {
            internalRequest.addParameter("startDate", request.getStartDate());
        }
        if (request.getEndDate() != null) {
            internalRequest.addParameter("endDate", request.getEndDate());
        }
        if (request.getAggregate() != null) {
            internalRequest.addParameter("aggregate", request.getAggregate().toString());
        }
        return invokeHttpClient(internalRequest, GetSessionStatisticsResponse.class);
    }

    /**
     * Create a domain stream in the live stream service.
     *
     * @param request The request object containing all options for creating domain stream
     */
    public CreateStreamResponse createStream(CreateStreamRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getPlayDomain(), "playDomain should NOT be empty.");
        checkStringNotEmpty(request.getApp(), "app should NOT be empty.");
        checkNotNull(request.getPublish(), "publish should NOT be null.");
        checkStringNotEmpty(request.getPublish().getPushStream(), "pushStream should NOT be empty.");

        InternalRequest internalRequest = createRequest(HttpMethodName.POST,
                request, LIVE_DOMAIN, request.getPlayDomain(), LIVE_STREAM);
        return invokeHttpClient(internalRequest, CreateStreamResponse.class);
    }

    /**
     * Create a domain stream in the live stream service.
     *
     * @param playDomain The domain which this stream belongs to
     * @param app The app which this stream belongs to, may not exist when create stream
     * @param pushStream, name of this stream
     */
    public CreateStreamResponse createStream(String playDomain, String app, String pushStream) {
        CreateStreamRequest request = new CreateStreamRequest();
        request.withPlayDomain(playDomain)
                .withApp(app)
                .withPublish(new CreateStreamRequest.PublishInfo().withPushStream(pushStream));
        return createStream(request);
    }

    /**
     * List a domain's streams in the live stream service.
     *
     * @param request The request object containing all options for listing domain's streams
     */
    public ListStreamResponse listStream(ListStreamRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getPlayDomain(), "playDomain should NOT be empty.");

        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                request, LIVE_DOMAIN, request.getPlayDomain(), LIVE_STREAM);
        if (request.getStatus() != null) {
            internalRequest.addParameter("status", request.getStatus());
        }

        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }

        if (request.getMaxSize() != null) {
            internalRequest.addParameter("maxSize", request.getMaxSize().toString());
        }

        return invokeHttpClient(internalRequest, ListStreamResponse.class);

    }

    /**
     * List a domain's streams in the live stream service.
     *
     * @param playDomain The requested domain
     */
    public ListStreamResponse listStream(String playDomain) {
        ListStreamRequest request = new ListStreamRequest();
        request.setPlayDomain(playDomain);
        return listStream(request);
    }


    /**
     * List a domain's app in the live stream service.
     *
     * @param request The request object containing all options for listing domain's app
     */
    public ListDomainAppResponse listDomainApp(ListDomainAppRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getPlayDomain(), "playDomain should NOT be empty.");

        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                request, LIVE_DOMAIN, request.getPlayDomain(), LIVE_APP);
        return invokeHttpClient(internalRequest, ListDomainAppResponse.class);
    }

    /**
     * List a domain's streams in the live stream service.
     *
     * @param playDomain The requested domain name
     */
    public ListDomainAppResponse listDomainApp(String playDomain) {
        ListDomainAppRequest request = new ListDomainAppRequest();
        request.setPlayDomain(playDomain);
        return listDomainApp(request);
    }

    /**
     * Get detail of stream in the live stream service.
     *
     * @param request The request object containing all options for querying detail of stream
     */
    public GetStreamResponse getStream(GetStreamRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getPlayDomain(), "playDomain should NOT be empty.");
        checkStringNotEmpty(request.getApp(), "App should NOT be empty.");
        checkStringNotEmpty(request.getStream(), "Stream should NOT be empty.");

        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                request, LIVE_DOMAIN, request.getPlayDomain(),
                LIVE_APP, request.getApp(),
                LIVE_STREAM, request.getStream());
        return invokeHttpClient(internalRequest, GetStreamResponse.class);
    }

    /**
     * Get detail of stream in the live stream service.
     *
     * @param domain The requested domain
     * @param app The requested app
     * @param stream The requested stream
     */
    public GetStreamResponse getStream(String domain, String app, String stream) {
        GetStreamRequest request = new GetStreamRequest();
        request.withPlayDomain(domain).withApp(app).withStream(stream);
        return getStream(request);
    }


    /**
     * pause domain's stream in the live stream service.
     *
     * @param request The request object containing all options for pause a domain's stream
     */
    public PauseDomainStreamResponse pauseDomainStream(PauseDomainStreamRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getDomain(), "Domain should NOT be empty.");
        checkStringNotEmpty(request.getApp(), "App should NOT be empty.");
        checkStringNotEmpty(request.getStream(), "Stream should NOT be empty.");

        InternalRequest internalRequest = createRequest(HttpMethodName.PUT,
                request, LIVE_DOMAIN, request.getDomain(),
                LIVE_APP, request.getApp(),
                LIVE_STREAM, request.getStream());
        internalRequest.addParameter(PAUSE, null);
        return invokeHttpClient(internalRequest, PauseDomainStreamResponse.class);
    }

    /**
     * Get detail of stream in the live stream service.
     *
     * @param domain The requested domain which the specific stream belongs to
     * @param app The requested app which the specific stream belongs to
     * @param stream The requested stream to pause
     */
    public PauseDomainStreamResponse pauseDomainStream(String domain, String app, String stream) {
        PauseDomainStreamRequest request = new PauseDomainStreamRequest();
        request.withDomain(domain).withApp(app).withStream(stream);
        return pauseDomainStream(request);
    }

    /**
     * pause domain's stream in the live stream service.
     *
     * @param request The request object containing all options for pause a domain's stream
     */
    public ResumeDomainStreamResponse resumeDomainStream(ResumeDomainStreamRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getDomain(), "Domain should NOT be empty.");
        checkStringNotEmpty(request.getApp(), "App should NOT be empty.");
        checkStringNotEmpty(request.getStream(), "Stream should NOT be empty.");

        InternalRequest internalRequest = createRequest(HttpMethodName.PUT,
                request, LIVE_DOMAIN, request.getDomain(),
                LIVE_APP, request.getApp(),
                LIVE_STREAM, request.getStream());
        internalRequest.addParameter(RESUME, null);
        return invokeHttpClient(internalRequest, ResumeDomainStreamResponse.class);
    }

    /**
     * Get detail of stream in the live stream service.
     *
     * @param domain The requested domain which the specific stream belongs to
     * @param app The requested app which the specific stream belongs to
     * @param stream The requested stream to resume
     */
    public ResumeDomainStreamResponse resumeDomainStream(String domain, String app, String stream) {
        ResumeDomainStreamRequest request = new ResumeDomainStreamRequest();
        request.withDomain(domain).withApp(app).withStream(stream);
        return resumeDomainStream(request);
    }


    /**
     * get domain's statistics in the live stream service.
     *
     * @param request The request object containing all options for getting domain's statistics
     */
    public GetDomainStatisticsResponse getDomainStatistics(GetDomainStatisticsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getDomain(), "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request, STATISTICS,
                LIVE_DOMAIN, request.getDomain());
        if (request.getStartDate() != null) {
            internalRequest.addParameter("startDate", request.getStartDate());
        }
        if (request.getEndDate() != null) {
            internalRequest.addParameter("endDate", request.getEndDate());
        }
        if (request.getAggregate() != null) {
            internalRequest.addParameter("aggregate", request.getAggregate().toString());
        }
        return invokeHttpClient(internalRequest, GetDomainStatisticsResponse.class);
    }

    /**
     * Get domain's statistics in the live stream service.
     *
     * @param domain The requested domain
     */
    public GetDomainStatisticsResponse getDomainStatistics(String domain) {
        GetDomainStatisticsRequest request = new GetDomainStatisticsRequest();
        request.setDomain(domain);
        return getDomainStatistics(request);
    }


    /**
     * get all domains' summary statistics in the live stream service.
     *
     * @param request The request object containing all options for getting all domains' summary statistics
     */
    public GetDomainSummaryStatisticsResponse getDomainSummaryStatistics(GetDomainSummaryStatisticsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getStartTime(), "startTime should NOT be empty.");
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request,
                STATISTICS, LIVE_DOMAIN, "summary");
        internalRequest.addParameter("startTime", request.getStartTime());
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        return invokeHttpClient(internalRequest, GetDomainSummaryStatisticsResponse.class);
    }

    /**
     * get all domains' summary statistics in the live stream service.
     *
     * @param startTime start time
     * @param endTime start time
     */
    public GetDomainSummaryStatisticsResponse getDomainSummaryStatistics(String startTime, String endTime) {
        GetDomainSummaryStatisticsRequest request = new GetDomainSummaryStatisticsRequest();
        request.withStartTime(startTime).withEndTime(endTime);
        return getDomainSummaryStatistics(request);
    }


    /**
     * get all domains' total play count statistics in the live stream service.
     *
     * @param request The request object containing all options for getting all domains' play count statistics
     */
    public GetAllDomainsPlayCountResponse getAllDomainsPlayCount(GetAllDomainsStatisticsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getTimeInterval(), "timeInterval should NOT be null");
        checkStringNotEmpty(request.getStartTime(), "startTime should NOT be null");

        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request,
                STATISTICS, LIVE_DOMAIN, "playcount");
        internalRequest.addParameter("timeInterval", request.getTimeInterval());
        internalRequest.addParameter("startTime", request.getStartTime());
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        return invokeHttpClient(internalRequest, GetAllDomainsPlayCountResponse.class);
    }

    /**
     * get one domain's play count statistics in the live stream service.
     *
     * @param request The request object containing all options for getting one domain's play count statistics
     */
    public GetOneDomainPlayCountResponse getOneDomainPlayCount(GetOneDomainStatisticsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkNotNull(request.getDomain(), "The domain parameter can not be null");
        checkStringNotEmpty(request.getTimeInterval(), "timeInterval should NOT be null");
        checkStringNotEmpty(request.getStartTime(), "startTime should NOT be null");

        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request,
                STATISTICS, LIVE_DOMAIN, request.getDomain(), "playcount");

        internalRequest.addParameter("timeInterval", request.getTimeInterval());
        internalRequest.addParameter("startTime", request.getStartTime());
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        return invokeHttpClient(internalRequest, GetOneDomainPlayCountResponse.class);
    }

    /**
     * get all domains' bandwidth statistics in the live stream service.
     *
     * @param request The request object containing all options for getting all domains' total bandwidth statistics
     */
    public GetAllDomainsBandwidthResponse getAllDomainsBandwidth(GetAllDomainsStatisticsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getTimeInterval(), "timeInterval should NOT be null");
        checkStringNotEmpty(request.getStartTime(), "startTime should NOT be null");

        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request,
                STATISTICS, LIVE_DOMAIN, "bandwidth");
        internalRequest.addParameter("timeInterval", request.getTimeInterval());
        internalRequest.addParameter("startTime", request.getStartTime());
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        return invokeHttpClient(internalRequest, GetAllDomainsBandwidthResponse.class);
    }

    /**
     * get one domain's bandwidth statistics in the live stream service.
     *
     * @param request The request object containing all options for getting one domain's bandwidth statistics
     */
    public GetOneDomainBandwidthResponse getOneDomainBandwidth(GetOneDomainStatisticsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getDomain(), "The domain parameter can not be null");
        checkStringNotEmpty(request.getTimeInterval(), "timeInterval should NOT be null");
        checkStringNotEmpty(request.getStartTime(), "startTime should NOT be null");

        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request,
                STATISTICS, LIVE_DOMAIN, request.getDomain(), "bandwidth");
        internalRequest.addParameter("timeInterval", request.getTimeInterval());
        internalRequest.addParameter("startTime", request.getStartTime());
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        return invokeHttpClient(internalRequest, GetOneDomainBandwidthResponse.class);
    }

    /**
     * get all domains' traffic statistics in the live stream service.
     *
     * @param request The request object containing all options for getting all domains' total traffic statistics
     */
    public GetAllDomainsTrafficResponse getAllDomainsTraffic(GetAllDomainsStatisticsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getTimeInterval(), "timeInterval should NOT be null");
        checkStringNotEmpty(request.getStartTime(), "startTime should NOT be null");

        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request,
                STATISTICS, LIVE_DOMAIN, "traffic");
        internalRequest.addParameter("timeInterval", request.getTimeInterval());
        internalRequest.addParameter("startTime", request.getStartTime());
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        return invokeHttpClient(internalRequest, GetAllDomainsTrafficResponse.class);
    }

    /**
     * get one domain's traffic statistics in the live stream service.
     *
     * @param request The request object containing all options for getting one domain's traffic statistics
     */
    public GetOneDomainTrafficResponse getOneDomainTraffic(GetOneDomainStatisticsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getDomain(), "The domain parameter can not be null");
        checkStringNotEmpty(request.getTimeInterval(), "timeInterval should NOT be null");
        checkStringNotEmpty(request.getStartTime(), "startTime should NOT be null");

        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request,
                STATISTICS, LIVE_DOMAIN, request.getDomain(), "traffic");
        internalRequest.addParameter("timeInterval", request.getTimeInterval());
        internalRequest.addParameter("startTime", request.getStartTime());
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        return invokeHttpClient(internalRequest, GetOneDomainTrafficResponse.class);
    }

    /**
     * list domain's statistics in the live stream service.
     *
     * @param request The request object containing all options for listing domain's traffic statistics
     */
    public ListDomainStatisticsResponse listDomainStatistics(ListDomainStatisticsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getStartTime(), "startTime should NOT be empty");

        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request,
                STATISTICS, LIVE_DOMAIN, "list");
        internalRequest.addParameter("startTime", request.getStartTime());
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        if (request.getKeyword() != null) {
            internalRequest.addParameter("keyword", request.getKeyword());
        }
        if (request.getKeywordType() != null) {
            internalRequest.addParameter("keywordType", request.getKeywordType());
        }
        if (request.getOrderBy() != null) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        return invokeHttpClient(internalRequest, ListDomainStatisticsResponse.class);
    }

    /**
     * list domain's all streams statistics in the live stream service.
     *
     * @param request The request object containing all options for listing domain's all streams traffic statistics
     */
    public ListStreamStatisticsResponse listStreamStatistics(ListStreamStatisticsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getDomain(), "Domain should NOT be null");
        checkStringNotEmpty(request.getApp(), "App should NOT be null");
        checkStringNotEmpty(request.getStartTime(), "StartTime should NOT be null");

        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request,
                STATISTICS, LIVE_DOMAIN, request.getDomain(), LIVE_STREAM);

        internalRequest.addParameter("app", request.getApp());
        internalRequest.addParameter("startTime", request.getStartTime());
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        if (request.getKeyword() != null) {
            internalRequest.addParameter("keyword", request.getKeyword());
        }
        if (request.getKeywordType() != null) {
            internalRequest.addParameter("keywordType", request.getKeywordType());
        }
        if (request.getOrderBy() != null) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        if (request.getPageNo() != null) {
            internalRequest.addParameter("pageNo", request.getPageNo().toString());
        }
        if (request.getPageSize() != null) {
            internalRequest.addParameter("pageSize", request.getPageSize().toString());
        }
        return invokeHttpClient(internalRequest, ListStreamStatisticsResponse.class);
    }

    /**
     * get a domain's all streams statistics in the live stream service.
     *
     * @param request The request object containing all options for getting a domain's all streams
     */
    public GetStreamStatisticsResponse getStreamStatistics(GetStreamStatisticsRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getDomain(), "Domain should NOT be null");
        checkStringNotEmpty(request.getApp(), "App should NOT be null");
        checkStringNotEmpty(request.getStream(), "Stream should NOT be null");

        InternalRequest internalRequest = createRequest(HttpMethodName.GET, request,
                STATISTICS, LIVE_DOMAIN, request.getDomain(), LIVE_APP, request.getApp(),
                LIVE_STREAM, request.getStream());
        if (request.getStartDate() != null) {
            internalRequest.addParameter("startDate", request.getStartDate());
        }
        if (request.getEndDate() != null) {
            internalRequest.addParameter("endDate", request.getEndDate());
        }
        if (request.getAggregate() != null) {
            internalRequest.addParameter("aggregate", request.getAggregate().toString());
        }
        return invokeHttpClient(internalRequest, GetStreamStatisticsResponse.class);
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