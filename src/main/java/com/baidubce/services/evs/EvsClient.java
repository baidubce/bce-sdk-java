package com.baidubce.services.evs;

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
import com.baidubce.services.evs.model.BindDeviceRequest;
import com.baidubce.services.evs.model.BindDeviceResponse;
import com.baidubce.services.evs.model.DeviceAiAnalysisListMarkRequest;
import com.baidubce.services.evs.model.DeviceAiAnalysisListMarkResponse;
import com.baidubce.services.evs.model.DeviceChannelListResponse;
import com.baidubce.services.evs.model.DeviceChannelSignedUrlResponse;
import com.baidubce.services.evs.model.DeviceGetResponse;
import com.baidubce.services.evs.model.DeviceMarkListResponse;
import com.baidubce.services.evs.model.DeviceSignedUrlResponse;
import com.baidubce.services.evs.model.DeviceStopRequest;
import com.baidubce.services.evs.model.DeviceTsStorePageListResponse;
import com.baidubce.services.evs.model.DynamicTrafficFrameRequest;
import com.baidubce.services.evs.model.EvsBaseRequest;
import com.baidubce.services.evs.model.EvsBaseResponse;
import com.baidubce.services.evs.model.GbPresetListResponse;
import com.baidubce.services.evs.model.SpaceMarkListResponse;
import com.baidubce.services.evs.model.DeviceChannelGetResponse;
import com.baidubce.services.evs.model.DeviceCountResponse;
import com.baidubce.services.evs.model.DeviceCreateRequest;
import com.baidubce.services.evs.model.DeviceCreateResponse;
import com.baidubce.services.evs.model.DeviceListMarkRequest;
import com.baidubce.services.evs.model.DeviceTsStoreListRequest;
import com.baidubce.services.evs.model.DeviceUpdateRequest;
import com.baidubce.services.evs.model.GBConfigDownloadResponse;
import com.baidubce.services.evs.model.GBDevicePasswordRequest;
import com.baidubce.services.evs.model.GbPresetAddRequest;
import com.baidubce.services.evs.model.GbPresetAddResponse;
import com.baidubce.services.evs.model.SpaceCreateRequest;
import com.baidubce.services.evs.model.SpaceCreateResponse;
import com.baidubce.services.evs.model.SpaceGetResponse;
import com.baidubce.services.evs.model.SpaceListMarkRequest;
import com.baidubce.services.evs.model.SpaceUpdateRequest;
import com.baidubce.util.DateUtils;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkPattern;

/**
 * Provides the client for accessing the Baidu Cloud network Service EVS.
 */
public class EvsClient extends AbstractBceClient {

    private static final String VERSION = "v1";

    private static final String SDK = "sdk";
    private static final String DISABLE = "disable";
    private static final String ENABLE = "enable";
    private static final String MARKER = "marker";
    private static final String PAGE_NO = "pageNo";
    private static final String PAGE_SIZE = "pageSize";
    private static final String STATUS = "status";
    private static final String MAX_SIZE = "maxSize";
    private static final String COUNT = "count";
    private static final String BEGIN = "begin";
    private static final String END = "end";
    private static final String TYPE = "type";
    private static final String RECORDING = "recording";
    private static final String THUMBNAIL = "thumbnail";
    private static final String AI_ANALYSIS = "aiAnalysisByMarker";
    private static final String SIGNED_URL = "signedUrl";
    private static final String PROTOCOL = "protocol";
    private static final String GB_CONFIG_DOWNLOAD = "gbConfigDownload";
    private static final String GB_DEVICE_PASSWORD = "gbDevicePassword";
    private static final String TELE_BOOT = "teleboot";
    private static final String TRAFFIC_FRAME = "trafficFrame";
    private static final String REFRESH = "refresh";
    private static final String STOP = "stop";
    private static final String START = "start";
    private static final String PTZ = "ptz";
    private static final String BIND_DEVICE_BY_SN_CODE = "bindDeviceBySnCode";
    public static final String PTZ_COMMAND = "ptzCommand";
    public static final String SPEED = "speed";
    public static final String FI = "fi";
    public static final String FI_COMMAND = "fiCommand";
    public static final String PRESET = "preset";
    public static final String GOTO = "goto";

    private static final String SPACE_URL = "space";
    private static final String SPACE_ID = "spaceId";
    private static final String SPACE_NAME = "spaceName";

    private static final String DEVICE_URL = "device";
    private static final String DEVICE_NAME = "deviceName";
    private static final String DEVICE_ID = "deviceId";

    private static final String CHANNEL_URL = "device/channel";

    private static final String MILLISECONDS_TIMESTAMP = "\\d{13}";
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    private static final HttpResponseHandler[] EVS_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public EvsClient() {
        this(new EvsClientConfiguration());
    }

    public EvsClient(BceClientConfiguration clientConfiguration) {
        this(clientConfiguration, EVS_HANDLERS);
    }

    public EvsClient(BceClientConfiguration config, HttpResponseHandler[] responseHandlers) {
        super(config, responseHandlers);
    }

    public EvsClient(BceClientConfiguration config,
                     HttpResponseHandler[] responseHandlers, boolean isHttpAsyncPutEnabled) {
        super(config, responseHandlers, isHttpAsyncPutEnabled);
    }

    private void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() == HttpMethodName.POST
                || internalRequest.getHttpMethod() == HttpMethodName.PUT) {
            String strJson = JsonUtils.toJsonString(bceRequest);
            byte[] requestJson = null;
            try {
                requestJson = strJson.getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Unsupported encode.", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
            internalRequest.setContent(RestartableInputStream.wrap(requestJson));
        }
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
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());
        return request;
    }

    /**
     * Used to create EVS space
     *
     * @param request Create the required data
     * @return spaceId 、Upstream and Downstream domain
     */
    public SpaceCreateResponse createSpace(SpaceCreateRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, SPACE_URL);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, SpaceCreateResponse.class);
    }

    /**
     * Used to delete EVS space. Delete the specified space according to the space ID.
     * After the space is deleted, all devices in the space will also be deleted.
     *
     * @param spaceId space id
     */
    public void deleteSpace(long spaceId) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(), HttpMethodName.DELETE,
                SPACE_URL, String.valueOf(spaceId));
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to modify EVS space configuration.
     *
     * @param spaceId space id
     * @param request Update the required data
     */
    public void updateSpace(long spaceId, SpaceUpdateRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, SPACE_URL,
                String.valueOf(spaceId));
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to query EVS space configuration information.
     *
     * @param spaceId space id
     * @return matching space details
     */
    public SpaceGetResponse getSpace(long spaceId) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.GET, SPACE_URL,
                String.valueOf(spaceId));
        return invokeHttpClient(internalRequest, SpaceGetResponse.class);
    }

    /**
     * Query EVS list of all Spaces under the user account in the way of Marker paging.
     *
     * @param request Criteria used to filter at query time
     * @returnSpace list of marker data
     */
    public SpaceMarkListResponse listSpaceByMarker(SpaceListMarkRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, SPACE_URL);

        internalRequest.addParameter(MARKER, null);
        internalRequest.addParameter(MAX_SIZE, String.valueOf(request.getMaxSize()));
        if (request.getMarker() != null) {
            internalRequest.addParameter(MARKER, String.valueOf(request.getMarker()));
        }
        if (StringUtils.isNotEmpty(request.getSpaceName())) {
            internalRequest.addParameter(SPACE_NAME, request.getSpaceName());
        }
        if (request.getStatus() != null) {
            internalRequest.addParameter(STATUS, request.getStatus());
        }
        if (request.getType() != null) {
            internalRequest.addParameter(TYPE, request.getType());
        }
        return invokeHttpClient(internalRequest, SpaceMarkListResponse.class);
    }

    /**
     * Used to deactivate the EVS space.
     *
     * @param spaceId The space ID to be stopped
     */
    public void disableSpace(long spaceId) {
        EvsBaseRequest request = new EvsBaseRequest();
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                SPACE_URL, String.valueOf(spaceId));
        internalRequest.addParameter(DISABLE, null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to enable the EVS space.
     *
     * @param spaceId The space ID to be started
     */
    public void enableSpace(long spaceId) {
        EvsBaseRequest request = new EvsBaseRequest();
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.PUT, SPACE_URL,
                String.valueOf(spaceId));
        internalRequest.addParameter(ENABLE, null);

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Use to add EVS device.
     *
     * @param request Create the required data
     * @return device id
     */
    public DeviceCreateResponse createDevice(DeviceCreateRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, DEVICE_URL);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, DeviceCreateResponse.class);
    }

    /**
     * Used to delete EVS device.
     *
     * @param deviceId device id
     */
    public void deleteDevice(long deviceId) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.DELETE, DEVICE_URL,
                String.valueOf(deviceId));
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to modify EVS device configuration.
     *
     * @param deviceId device id
     * @param request  Modified information
     */
    public void updateDevice(long deviceId, DeviceUpdateRequest request) {
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.PUT, DEVICE_URL, String.valueOf(deviceId));
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to query EVS devices.
     *
     * @param deviceId device id
     * @return Matching device details
     */
    public DeviceGetResponse getDevice(long deviceId) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.GET, DEVICE_URL,
                String.valueOf(deviceId));
        return invokeHttpClient(internalRequest, DeviceGetResponse.class);
    }

    /**
     * Use to query all the devices in user space below the list, by marker query.
     *
     * @param request Conditions for filtering by paging queries
     * @return Device list of marker data
     */
    public DeviceMarkListResponse listMarkDevice(DeviceListMarkRequest request) {
        checkNotNull(request.getSpaceId(), "spaceId parameter request should not be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, DEVICE_URL);
        internalRequest.addParameter(MARKER, null);

        internalRequest.addParameter(MAX_SIZE, String.valueOf(request.getMaxSize()));
        internalRequest.addParameter(SPACE_ID, String.valueOf(request.getSpaceId()));
        if (request.getMarker() != null) {
            internalRequest.addParameter(MARKER, String.valueOf(request.getMarker()));
        }
        if (StringUtils.isNotEmpty(request.getDeviceName())) {
            internalRequest.addParameter(DEVICE_NAME, request.getDeviceName());
        }
        if (request.getStatus() != null) {
            internalRequest.addParameter(STATUS, request.getStatus());
        }

        return invokeHttpClient(internalRequest, DeviceMarkListResponse.class);
    }

    /**
     * Used to deactivate EVS devices.
     *
     * @param deviceId The device ID that needs to be deactivated
     * @param request stop time，
     *                If request is null, the device is permanently deactivated. If the request body contains
     *               a recoverTime parameter, deactivate the device until the recoverTime time is specified.
     */
    public void disableDevice(long deviceId, DeviceStopRequest request) {
        InternalRequest internalRequest;
        if (request != null) {
            internalRequest = createRequest(request,
                    HttpMethodName.PUT, DEVICE_URL, String.valueOf(deviceId));
            fillPayload(internalRequest, request);
        } else {
            EvsBaseRequest evsBaseRequest = new EvsBaseRequest();
            internalRequest = createRequest(evsBaseRequest,
                    HttpMethodName.PUT, DEVICE_URL, String.valueOf(deviceId));
            fillPayload(internalRequest, evsBaseRequest);
        }

        internalRequest.addParameter(DISABLE, null);

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to enable EVS devices.
     *
     * @param deviceId The device ID that needs to be enabled
     */
    public void enableDevice(long deviceId) {
        EvsBaseRequest evsBaseRequest = new EvsBaseRequest();
        InternalRequest internalRequest = createRequest(evsBaseRequest,
                HttpMethodName.PUT, DEVICE_URL, String.valueOf(deviceId));
        fillPayload(internalRequest, evsBaseRequest);

        internalRequest.addParameter(ENABLE, null);
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to query the device's video file list
     *
     * @param deviceId device id
     * @param request  Query required conditions：Recording start and end time (Unix timestamp) 、pageNo、pageSize
     * @return Matching video file paging data
     */
    public DeviceTsStorePageListResponse listDeviceRecord(
            long deviceId, DeviceTsStoreListRequest request) {
        checkNotNull(request.getBegin(), "DeviceTsStoreListRequest.begin parameter request should not be null.");
        checkNotNull(request.getEnd(), "DeviceTsStoreListRequest.end parameter request should not be null.");
        checkNotNull(request.getPageNo(), "DeviceTsStoreListRequest.pageNo parameter request should not be null.");
        checkNotNull(request.getPageSize(), "DeviceTsStoreListRequest.pageSize parameter request should not be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, DEVICE_URL,
                String.valueOf(deviceId), RECORDING);
        internalRequest.addParameter(BEGIN, String.valueOf(request.getBegin()));
        internalRequest.addParameter(END, String.valueOf(request.getEnd()));
        internalRequest.addParameter(PAGE_NO, String.valueOf(request.getPageNo()));
        internalRequest.addParameter(PAGE_SIZE, String.valueOf(request.getPageSize()));
        internalRequest.addParameter(TYPE, String.valueOf(request.getType()));

        return invokeHttpClient(internalRequest, DeviceTsStorePageListResponse.class);
    }

    /**
     * Used to query the number of video files of the RTMP device.
     *
     * @param deviceId device id
     * @param begin    Recording start time, Unix timestamp, in seconds.
     * @param end      Recording end time, Unix timestamp, in seconds,
     * @return Recording count
     */
    public DeviceCountResponse countDeviceRecoding(long deviceId, long begin, long end) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.GET, DEVICE_URL, String.valueOf(deviceId),
                RECORDING);
        internalRequest.addParameter(BEGIN, String.valueOf(begin));
        internalRequest.addParameter(END, String.valueOf(end));

        internalRequest.addParameter(COUNT, null);
        return invokeHttpClient(internalRequest, DeviceCountResponse.class);
    }

    /**
     * Used to delete the video files of the RTMP device.
     *
     * @param deviceId device id
     * @param begin    Recording start time, Unix timestamp, in seconds.
     * @param end      Recording end time, Unix timestamp, in seconds,
     */
    public void removeDeviceRecord(long deviceId, long begin, long end) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.DELETE, DEVICE_URL,
                String.valueOf(deviceId), RECORDING);
        internalRequest.addParameter(BEGIN, String.valueOf(begin));
        internalRequest.addParameter(END, String.valueOf(end));

        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to query the device's screenshot file list
     *
     * @param deviceId device id
     * @param request  Query required conditions：Thumbnail start and end time (Unix timestamp) 、pageNo、pageSize
     * @return Matching screenshot file paging data
     */
    public DeviceTsStorePageListResponse listDeviceThumbnail(
            long deviceId, DeviceTsStoreListRequest request) {
        checkNotNull(request.getBegin(), "DeviceTsStoreListRequest.begin parameter request should not be null.");
        checkNotNull(request.getEnd(), "DeviceTsStoreListRequest.end parameter request should not be null.");
        checkNotNull(request.getPageNo(), "DeviceTsStoreListRequest.pageNo parameter request should not be null.");
        checkNotNull(request.getPageSize(), "DeviceTsStoreListRequest.pageSize parameter request should not be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, DEVICE_URL,
                String.valueOf(deviceId), THUMBNAIL);
        internalRequest.addParameter(BEGIN, String.valueOf(request.getBegin()));
        internalRequest.addParameter(END, String.valueOf(request.getEnd()));
        internalRequest.addParameter(PAGE_NO, String.valueOf(request.getPageNo()));
        internalRequest.addParameter(PAGE_SIZE, String.valueOf(request.getPageSize()));

        return invokeHttpClient(internalRequest, DeviceTsStorePageListResponse.class);
    }

    /**
     * Used to query the number of screenshot files of the RTMP device.
     *
     * @param deviceId device id
     * @param begin    Thumbnail start time, Unix timestamp, in seconds.
     * @param end      Thumbnail end time, Unix timestamp, in seconds,
     * @return Thumbnail count
     */
    public DeviceCountResponse countDevicehumbnail(long deviceId, long begin, long end) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.GET, DEVICE_URL, String.valueOf(deviceId), THUMBNAIL);
        internalRequest.addParameter(BEGIN, String.valueOf(begin));
        internalRequest.addParameter(END, String.valueOf(end));

        internalRequest.addParameter(COUNT, null);
        return invokeHttpClient(internalRequest, DeviceCountResponse.class);
    }

    /**
     * Used to delete the screenshot files of the RTMP device.
     *
     * @param deviceId device id
     * @param begin    Thumbnail start time, Unix timestamp, in seconds.
     * @param end      Thumbnail end time, Unix timestamp, in seconds,
     */
    public void removeDeviceThumbnail(long deviceId, long begin, long end) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.DELETE, DEVICE_URL, String.valueOf(deviceId), THUMBNAIL);
        internalRequest.addParameter(BEGIN, String.valueOf(begin));
        internalRequest.addParameter(END, String.valueOf(end));

        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to query the AI analysis results of RTMP devices. It is only used for devices with AI skill
     * configuration enabled in the space configuration.
     *
     * @param deviceId device id
     * @param aiType   AI detection type，Support:
     *                 FACE、BODY、ELECTRIC、CAR_ATTRIBUTE、CAR_MODEL、CAR_PLATE，STATIC_HUMAN_TRAFFIC、STATIC_CAR_TRAFFIC、
     *                 QUALITY_BRIGHT、QUALITY_COLOR、QUALITY_COVER、QUALITY_BLUR、QUALITY_NOISE
     * @param request  Query required conditions：Recording start and end time (Unix timestamp) 、pageNo、pageSize
     * @return Matching screenshot file paging data
     */
    public DeviceAiAnalysisListMarkResponse listDeviceAiAnalysisResultByMarker(
            long deviceId, String aiType, DeviceAiAnalysisListMarkRequest request) {
        checkNotNull(request.getBegin(), "DeviceTsStoreListRequest.begin parameter request should not be null.");
        checkNotNull(request.getEnd(), "DeviceTsStoreListRequest.end parameter request should not be null.");
        checkNotNull(aiType, "type parameter request should not be null.");
        checkPattern(String.valueOf(request.getBegin()), MILLISECONDS_TIMESTAMP,
                "DeviceTsStoreListRequest.begin parameter request should have a millisecond timestamp.");
        checkPattern(String.valueOf(request.getEnd()), MILLISECONDS_TIMESTAMP,
                "DeviceTsStoreListRequest.end parameter request should have a millisecond timestamp.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, DEVICE_URL,
                String.valueOf(deviceId), AI_ANALYSIS);
        internalRequest.addParameter(TYPE, aiType);
        internalRequest.addParameter(MARKER, null);
        internalRequest.addParameter(MAX_SIZE, String.valueOf(request.getMaxSize()));
        internalRequest.addParameter(BEGIN, String.valueOf(DateUtils.formatIso8601Date(new Date(request.getBegin()))));
        internalRequest.addParameter(END, String.valueOf(DateUtils.formatIso8601Date(new Date(request.getEnd()))));
        if (request.getMarker() != null) {
            internalRequest.addParameter(MARKER, String.valueOf(request.getMarker()));
        }
        return invokeHttpClient(internalRequest, DeviceAiAnalysisListMarkResponse.class);
    }

    /**
     * Used to obtain the device broadcast signature address according to the domain name
     *
     * @param domain   domain name
     * @param app      device app
     * @param stream   device stream
     * @param protocol Agreement name
     * @return url string
     */
    public DeviceSignedUrlResponse generateDeviceSignedUrl(String domain,
                                                           String app,
                                                           String stream,
                                                           String protocol) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.GET, DEVICE_URL, domain, app, stream, SIGNED_URL);
        if (StringUtils.isNotBlank(protocol)) {
            internalRequest.addParameter(PROTOCOL, protocol);
        }
        internalRequest.addParameter(SDK, null);

        return invokeHttpClient(internalRequest, DeviceSignedUrlResponse.class);
    }

    /**
     * Used to query national standard equipment configuration information.
     *
     * @param deviceId gb device id
     * @return The configuration details of the designated national standard equipment
     */
    public GBConfigDownloadResponse getDeviceGbConfigDownload(long deviceId) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.GET, DEVICE_URL, String.valueOf(deviceId), GB_CONFIG_DOWNLOAD);

        return invokeHttpClient(internalRequest, GBConfigDownloadResponse.class);
    }

    /**
     * Used to restart the GB28181 device.
     *
     * @param deviceId gb device id
     */
    public void teleBootDevice(long deviceId) {
        EvsBaseRequest request = new EvsBaseRequest();
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.PUT, DEVICE_URL, String.valueOf(deviceId));
        internalRequest.addParameter(TELE_BOOT, null);

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to set the size of the equipment monitoring frame
     *
     * @param channelId           channelId ID
     * @param dynamicTrafficFrame equipment monitoring frame config info
     * @return channelId id
     */
    public DeviceChannelGetResponse trafficFrameDevice(long channelId, DynamicTrafficFrameRequest dynamicTrafficFrame) {
        DynamicTrafficFrameRequest.Configuration configuration = dynamicTrafficFrame.getConfiguration();
        checkNotNull(configuration,
                "DynamicTrafficFrameRequest.configuration parameter request should NOT be null.");
        DynamicTrafficFrameRequest.Configuration.FrameLocation frameLocation = configuration.getFrameLocation();
        checkNotNull(frameLocation, "Configuration.frameLocation parameter request should not be null.");
        checkNotNull(frameLocation.getHorizontal(), "FrameLocation.horizontal parameter request should not be null.");
        checkNotNull(frameLocation.getVertical(), "FrameLocation.vertical parameter request should not be null.");
        checkNotNull(frameLocation.getHeight(), "FrameLocation.height parameter request should not be null.");
        checkNotNull(frameLocation.getWidth(), "FrameLocation.width parameter request should not be null.");
        InternalRequest internalRequest = createRequest(dynamicTrafficFrame,
                HttpMethodName.PUT, CHANNEL_URL, String.valueOf(channelId), TRAFFIC_FRAME);

        fillPayload(internalRequest, dynamicTrafficFrame);
        return invokeHttpClient(internalRequest, DeviceChannelGetResponse.class);
    }

    /**
     * Used to modify the user password of the GB28181 device.
     *
     * @param deviceId gb device id
     * @param request  new password
     */
    public void modifyDeviceGbPassword(long deviceId, GBDevicePasswordRequest request) {
        checkNotNull(request.getPassword(), "GBDevicePasswordRequest.password parameter request should not be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DEVICE_URL, String.valueOf(deviceId), GB_DEVICE_PASSWORD);

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to view the channel list of the GB28181 device.
     *
     * @param deviceId gb device id
     * @return Channel list details of designated national standard equipment
     */
    public DeviceChannelListResponse listChannel(long deviceId) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.GET, CHANNEL_URL);
        internalRequest.addParameter(SDK, null);
        internalRequest.addParameter(DEVICE_ID, String.valueOf(deviceId));
        return invokeHttpClient(internalRequest, DeviceChannelListResponse.class);
    }

    /**
     * Used to refresh the channel of the specified GB28181 device.
     *
     * @param deviceId gb device id
     */
    public void refreshChannels(long deviceId) {
        EvsBaseRequest request = new EvsBaseRequest();
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.PUT, CHANNEL_URL);
        internalRequest.addParameter(REFRESH, null);
        internalRequest.addParameter(DEVICE_ID, String.valueOf(deviceId));

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to view the specified GB28181 device channel.
     *
     * @param channelId channel id
     * @return Matching channel details
     */
    public DeviceChannelGetResponse getChannel(long channelId) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.GET, CHANNEL_URL,
                String.valueOf(channelId));
        internalRequest.addParameter(SDK, null);
        return invokeHttpClient(internalRequest, DeviceChannelGetResponse.class);
    }

    /**
     * Used to deactivate the designated channel of the GB28181 device.
     *
     * @param channelId channel id
     * @param request   UTZ time, If it is empty, the device will be disabled permanently, if it is not empty, the
     *                  device will be disabled until the recoverTime is specified
     */
    public void stopChannel(long channelId, DeviceStopRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                CHANNEL_URL, String.valueOf(channelId));
        internalRequest.addParameter(STOP, null);

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to enable the specified channel of the GB28181 device.
     *
     * @param channelId channel id
     */
    public void startChannel(long channelId) {
        EvsBaseRequest request = new EvsBaseRequest();
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.PUT, CHANNEL_URL,
                String.valueOf(channelId));
        internalRequest.addParameter(START, null);

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to obtain the channel broadcast signature address according to the domain name
     *
     * @param channelId channel id
     * @param protocol  Agreement name
     * @return url string
     */
    public DeviceChannelSignedUrlResponse generateChannelSignedUrl(
            long channelId, String protocol) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.GET, CHANNEL_URL, String.valueOf(channelId), SIGNED_URL);
        internalRequest.addParameter(SDK, null);
        internalRequest.addParameter(PROTOCOL, protocol);
        return invokeHttpClient(internalRequest, DeviceChannelSignedUrlResponse.class);
    }

    /**
     * Used to query the video file list of GB28181 device channel.
     *
     * @param channelId channel id
     * @param request   Query required conditions：Recording start and end time (Unix timestamp) 、pageNo、pageSize
     * @return Matching video file paging data
     */
    public DeviceTsStorePageListResponse listChannelRecord(long channelId, DeviceTsStoreListRequest request) {
        checkNotNull(request.getBegin(), "DeviceTsStoreListRequest.begin parameter request should not be null.");
        checkNotNull(request.getEnd(), "DeviceTsStoreListRequest.end parameter request should not be null.");
        checkNotNull(request.getPageNo(), "DeviceTsStoreListRequest.pageNo parameter request should not be null.");
        checkNotNull(request.getPageSize(), "DeviceTsStoreListRequest.pageSize parameter request should not be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
                CHANNEL_URL, String.valueOf(channelId), RECORDING);
        internalRequest.addParameter(BEGIN, String.valueOf(request.getBegin()));
        internalRequest.addParameter(END, String.valueOf(request.getEnd()));
        internalRequest.addParameter(PAGE_NO, String.valueOf(request.getPageNo()));
        internalRequest.addParameter(PAGE_SIZE, String.valueOf(request.getPageSize()));

        return invokeHttpClient(internalRequest, DeviceTsStorePageListResponse.class);
    }

    /**
     * Used to query the number of video files of GB28181 device channel
     *
     * @param channelId channel id
     * @param begin     Recording start time, Unix timestamp, in seconds.
     * @param end       Recording end time, Unix timestamp, in seconds,
     * @return Recording count
     */
    public DeviceCountResponse countChannelRecoding(long channelId, long begin, long end) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.GET, CHANNEL_URL, String.valueOf(channelId),
                RECORDING);
        internalRequest.addParameter(BEGIN, String.valueOf(begin));
        internalRequest.addParameter(END, String.valueOf(end));

        internalRequest.addParameter(COUNT, null);
        return invokeHttpClient(internalRequest, DeviceCountResponse.class);
    }

    /**
     * Used to delete the video files of the GB28181 device channel.
     *
     * @param channelId channelId id
     * @param begin     Recording start time, Unix timestamp, in seconds.
     * @param end       Recording end time, Unix timestamp, in seconds,
     */
    public void removeChannelRecord(long channelId, long begin, long end) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.DELETE, CHANNEL_URL,
                String.valueOf(channelId), RECORDING);
        internalRequest.addParameter(BEGIN, String.valueOf(begin));
        internalRequest.addParameter(END, String.valueOf(end));

        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to query the list of screenshot files of GB28181 device channel.
     *
     * @param channelId channelId id
     * @param request   Query required conditions：Thumbnail start and end time (Unix timestamp) 、pageNo、pageSize
     * @return Matching screenshot file paging data
     */
    public DeviceTsStorePageListResponse listChannelThumbnail(
            long channelId, DeviceTsStoreListRequest request) {
        checkNotNull(request.getBegin(), "DeviceTsStoreListRequest.begin parameter request should not be null.");
        checkNotNull(request.getEnd(), "DeviceTsStoreListRequest.end parameter request should not be null.");
        checkNotNull(request.getPageNo(), "DeviceTsStoreListRequest.pageNo parameter request should not be null.");
        checkNotNull(request.getPageSize(), "DeviceTsStoreListRequest.pageSize parameter request should not be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, CHANNEL_URL,
                String.valueOf(channelId), THUMBNAIL);
        internalRequest.addParameter(BEGIN, String.valueOf(request.getBegin()));
        internalRequest.addParameter(END, String.valueOf(request.getEnd()));
        internalRequest.addParameter(PAGE_NO, String.valueOf(request.getPageNo()));
        internalRequest.addParameter(PAGE_SIZE, String.valueOf(request.getPageSize()));
        return invokeHttpClient(internalRequest, DeviceTsStorePageListResponse.class);
    }

    /**
     * Used to query the number of screenshot files of the Gb28181 device channel.
     *
     * @param channelId channel id
     * @param begin     Thumbnail start time, Unix timestamp, in seconds.
     * @param end       Thumbnail end time, Unix timestamp, in seconds,
     * @return Thumbnail count
     */
    public DeviceCountResponse countChannelThumbnail(long channelId, long begin, long end) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.GET, CHANNEL_URL, String.valueOf(channelId), THUMBNAIL);
        internalRequest.addParameter(BEGIN, String.valueOf(begin));
        internalRequest.addParameter(END, String.valueOf(end));

        internalRequest.addParameter(COUNT, null);
        return invokeHttpClient(internalRequest, DeviceCountResponse.class);
    }

    /**
     * Used to delete the screenshot file of the GB28181 device channel.
     *
     * @param channelId channel id
     * @param begin     Thumbnail start time, Unix timestamp, in seconds.
     * @param end       Thumbnail end time, Unix timestamp, in seconds,
     */
    public void removeChannelThumbnail(long channelId, long begin, long end) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.DELETE, CHANNEL_URL, String.valueOf(channelId), THUMBNAIL);
        internalRequest.addParameter(BEGIN, String.valueOf(begin));
        internalRequest.addParameter(END, String.valueOf(end));

        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * It is used to query the AI analysis results of GB28181 device channels.
     * It is only used for the device channels with AI skill configuration enabled in the space configuration.
     *
     * @param channelId channel id
     * @param aiType    AI detection type，Support:
     *                  FACE、BODY、ELECTRIC、CAR_ATTRIBUTE、CAR_MODEL、CAR_PLATE，STATIC_HUMAN_TRAFFIC、STATIC_CAR_TRAFFIC、
     *                  QUALITY_BRIGHT、QUALITY_COLOR、QUALITY_COVER、QUALITY_BLUR、QUALITY_NOISE
     * @param request   Query required conditions：Recording start and end time (Unix timestamp) 、pageNo、pageSize
     * @return Matching screenshot file paging data
     */
    public DeviceAiAnalysisListMarkResponse listChannelAiAnalysisResultByMarker(
            long channelId, String aiType, DeviceAiAnalysisListMarkRequest request) {
        checkNotNull(request.getBegin(), "DeviceTsStoreListRequest.begin parameter request should not be null.");
        checkNotNull(request.getEnd(), "DeviceTsStoreListRequest.end parameter request should not be null.");
        checkNotNull(aiType, "type parameter request should not be null.");
        checkPattern(String.valueOf(request.getBegin()), MILLISECONDS_TIMESTAMP,
                "DeviceTsStoreListRequest.begin parameter request should have a millisecond timestamp.");
        checkPattern(String.valueOf(request.getEnd()), MILLISECONDS_TIMESTAMP,
                "DeviceTsStoreListRequest.end parameter request should have a millisecond timestamp.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, CHANNEL_URL,
                String.valueOf(channelId), AI_ANALYSIS);
        internalRequest.addParameter(TYPE, aiType);
        internalRequest.addParameter(MARKER, null);
        internalRequest.addParameter(MAX_SIZE, String.valueOf(request.getMaxSize()));
        if (request.getMarker() != null) {
            internalRequest.addParameter(MARKER, String.valueOf(request.getMarker()));
        }
        internalRequest.addParameter(BEGIN, String.valueOf(DateUtils.formatIso8601Date(new Date(request.getBegin()))));
        internalRequest.addParameter(END, String.valueOf(DateUtils.formatIso8601Date(new Date(request.getEnd()))));
        return invokeHttpClient(internalRequest, DeviceAiAnalysisListMarkResponse.class);
    }

    /**
     * Used to perform PTZ operation on the national standard equipment channel.
     *
     * @param channelId  channel id
     * @param ptzCommand ptz command, support：
     *                   stop/left/right/up/down/zoomin/zoomout/left_up/left_down/right_up/right_down
     * @param speed      Adjust the speed, support 1-255 for up, down,
     *                   left and right operation, and 1-15 for zoom operation
     */
    public void modifyChannelPtz(long channelId, String ptzCommand, int speed) {
        EvsBaseRequest request = new EvsBaseRequest();
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.PUT, CHANNEL_URL, String.valueOf(channelId));
        internalRequest.addParameter(PTZ_COMMAND, ptzCommand.toString());
        internalRequest.addParameter(SPEED, String.valueOf(speed));
        internalRequest.addParameter(PTZ, null);

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to adjust the aperture and focus of the national standard equipment channel.
     *
     * @param channelId channel id
     * @param fiCommand support：stop/irisin/irisout/focusnear/focusfar
     * @param speed     Adjust the speed, support 1-255
     */
    public void modifyChannelFi(long channelId, String fiCommand, int speed) {
        EvsBaseRequest request = new EvsBaseRequest();
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.PUT, CHANNEL_URL, String.valueOf(channelId));
        internalRequest.addParameter(FI_COMMAND, fiCommand.toString());
        internalRequest.addParameter(SPEED, String.valueOf(speed));
        internalRequest.addParameter(FI, null);

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to add presets to the national standard equipment channel.
     *
     * @param channelId channel id
     * @param request   Preset position name, the maximum length is 100
     * @return Preset Id
     * <p>
     * Note: Before initiating the request to add a preset position,
     * make sure that the camera has been adjusted to the expected position and angle through the ptz control.
     */
    public GbPresetAddResponse addChannelPreset(long channelId, GbPresetAddRequest request) {
        checkNotNull(request.getName(), "GbPresetAddRequest.name parameter request should not be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST,
                CHANNEL_URL, String.valueOf(channelId), PRESET);

        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, GbPresetAddResponse.class);
    }

    /**
     * Used to delete the preset position of the national standard equipment channel.
     *
     * @param channelId channel id
     * @param presetId  preset Id
     */
    public void deleteChannelPreset(long channelId, int presetId) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.DELETE, CHANNEL_URL, String.valueOf(channelId),
                PRESET, String.valueOf(presetId));
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to query the preset position list of the national standard equipment channel.
     *
     * @param channelId channel id
     * @return Specify the list of all preset positions of GBT
     */
    public GbPresetListResponse listChannelPreset(long channelId) {
        InternalRequest internalRequest = createRequest(new EvsBaseRequest(),
                HttpMethodName.GET, CHANNEL_URL, String.valueOf(channelId), PRESET);
        internalRequest.addParameter(SDK, null);
        return invokeHttpClient(internalRequest, GbPresetListResponse.class);
    }

    /**
     * Used to call the preset position of the national standard equipment channel, and quickly move the camera to
     * the preset position.
     *
     * @param channelId channel id
     * @param presetId  preset Id
     */
    public void gotoChannelPreset(long channelId, int presetId) {
        EvsBaseRequest request = new EvsBaseRequest();
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.PUT, CHANNEL_URL,
                String.valueOf(channelId), PRESET,
                String.valueOf(presetId));

        internalRequest.addParameter(GOTO, null);

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, EvsBaseResponse.class);
    }

    /**
     * Used to SnCode for binding bvcp equipment
     *
     * @param request Bind the required data
     * @return deviceId and snCode
     */
    public BindDeviceResponse bindDeviceBySnCode(BindDeviceRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST,
                DEVICE_URL);
        internalRequest.addParameter(BIND_DEVICE_BY_SN_CODE, null);
        checkNotNull(request.getSnCode(), "BindDeviceRequest.snCode parameter request should not be null.");
        checkNotNull(request.getDeviceMode(), "BindDeviceRequest.deviceMode parameter request should not be null.");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, BindDeviceResponse.class);
    }

}
