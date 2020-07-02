/*
 * Copyright 2018-2019 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.dugo;

import static com.baidubce.util.Validate.checkIsTrue;
import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkPattern;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.baidubce.util.Validate.checkValidValue;

import com.baidubce.services.dugo.map.DeviceStatusInfoResponse;
import com.baidubce.services.dugo.map.GetDeviceInfoRequest;
import com.baidubce.services.dugo.map.QueryDeviceLogResponse;
import com.baidubce.services.dugo.project.GetBatchListResponse;
import com.baidubce.services.dugo.project.GetProjectByIdResponse;
import com.baidubce.services.dugo.project.GetProjectListResponse;
import com.baidubce.services.dugo.project.QueryInstancesByBatchResponse;
import com.baidubce.services.dugo.project.UpdateBatchDescRequest;
import com.baidubce.services.dugo.project.BatchBindVehicleRequest;
import com.baidubce.services.dugo.project.RandomBindVehiclesRequest;
import com.baidubce.services.dugo.project.BatchUnbindVehiclesRequest;
import com.baidubce.services.dugo.project.QueryMqttBindResultResponse;
import com.baidubce.services.dugo.project.DownloadAuthInfoResponse;
import com.baidubce.services.dugo.project.BatchAddDeviceRequest;
import com.baidubce.services.dugo.project.BatchRemoveDeviceRequest;
import com.baidubce.services.dugo.project.UpdateAliasNameRequest;
import com.baidubce.services.dugo.project.ActivateDeviceRequest;
import com.baidubce.services.dugo.project.QueryDeviceResponse;
import com.baidubce.services.dugo.project.DeviceShadowResponse;
import com.baidubce.services.dugo.project.GroupDeviceShadowResponse;
import com.baidubce.services.dugo.project.QueryDeviceHistoryRequest;
import com.baidubce.services.dugo.project.QueryDeviceHistoryResponse;
import com.baidubce.services.dugo.vehicle.QueryVehicleStatusRequest;
import com.baidubce.services.dugo.vehicle.QueryVehicleStatusResponse;
import com.baidubce.services.dugo.vehicle.UploadDynamicDataRequest;
import com.baidubce.services.dugo.vehicle.UploadStaticDataRequest;
import com.baidubce.services.dugo.vehicle.QuerySingleShadowRequest;
import com.baidubce.services.dugo.vehicle.QuerySingleShadowResponse;
import com.baidubce.services.dugo.vehicle.QueryMultipleShadowRequest;
import com.baidubce.services.dugo.vehicle.QueryMultipleShadowResponse;
import com.baidubce.services.dugo.vehicle.ShadowFilterRequest;
import com.baidubce.services.dugo.vehicle.ShadowFilterResponse;
import com.baidubce.services.dugo.vehicle.HistoryInfoQueryRequest;
import com.baidubce.services.dugo.vehicle.HistoryInfoQueryResponse;
import com.baidubce.services.dugo.vehicle.SchemaDisplayNameResponse;
import com.baidubce.services.dugo.vehicle.SchemaAttributeNameResponse;
import com.baidubce.services.dugo.vehicle.GB32960ParamQueryRequest;
import com.baidubce.services.dugo.vehicle.GB32960ParamQueryResponse;
import com.baidubce.services.dugo.vehicle.GB32960ParamSettingRequest;
import com.baidubce.services.dugo.vehicle.GB32960VehicleControlRequest;
import com.baidubce.services.dugo.alarm.AlarmCreateRequest;
import com.baidubce.services.dugo.alarm.AlarmUpdateRequest;
import com.baidubce.services.dugo.alarm.AlarmBatchRequest;
import com.baidubce.services.dugo.alarm.AlarmDetailsResponse;
import com.baidubce.services.dugo.alarm.AlarmRuleListResponse;
import com.baidubce.services.dugo.map.GeoCodingRequest;
import com.baidubce.services.dugo.map.GeoCodingResponse;
import com.baidubce.services.dugo.map.GeoDecodingRequest;
import com.baidubce.services.dugo.map.GeoDecodingResponse;
import com.baidubce.services.dugo.map.GetLatestPointRequest;
import com.baidubce.services.dugo.map.GetLatestPointResponse;
import com.baidubce.services.dugo.map.GetTrackRequest;
import com.baidubce.services.dugo.map.GetTrackResponse;
import com.baidubce.services.dugo.map.GetDistanceRequest;
import com.baidubce.services.dugo.map.GetDistanceResponse;
import com.baidubce.services.dugo.map.StayPointRequest;
import com.baidubce.services.dugo.map.StayPointResponse;
import com.baidubce.services.dugo.map.DrivingBehaviorRequest;
import com.baidubce.services.dugo.map.DrivingBehaviorResponse;
import com.baidubce.services.dugo.map.CreateFenceRequest;
import com.baidubce.services.dugo.map.CreateFenceResponse;
import com.baidubce.services.dugo.map.UpdateFenceRequest;
import com.baidubce.services.dugo.map.FenceDetailResponse;
import com.baidubce.services.dugo.map.FenceListResponse;
import com.baidubce.services.dugo.map.UpdateFenceAlarmRequest;
import com.baidubce.services.dugo.map.GetFenceAlarmsResponse;
import com.baidubce.services.dugo.map.FenceMonitoredVehicleRequest;
import com.baidubce.services.dugo.map.DeleteMonitoredVehicleRequest;
import com.baidubce.services.dugo.map.MonitoredVehicleListResponse;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.dugo.core.protocol.http.BceIotHttpClient;
import com.baidubce.services.dugo.video.AlarmInfoByTimeRequest;
import com.baidubce.services.dugo.video.AlarmInfoByVehicleIdListRequest;
import com.baidubce.services.dugo.video.AlarmInfoListResponse;
import com.baidubce.services.dugo.video.AlarmVideoInfoByVehicleIdListRequest;
import com.baidubce.services.dugo.video.AlarmVideoInfoByVehicleIdRequest;
import com.baidubce.services.dugo.video.AlarmVideoInfoListResponse;
import com.baidubce.services.dugo.video.FileNameRequest;
import com.baidubce.services.dugo.video.FileUploadResponse;
import com.baidubce.services.dugo.video.GetDownloadUrlResponse;
import com.baidubce.services.dugo.video.GetMediaInfoListResponse;
import com.baidubce.services.dugo.video.GetMediaInfoResponse;
import com.baidubce.services.dugo.video.GetPlayUrlResponse;
import com.baidubce.services.dugo.video.GetTaskStatusResponse;
import com.baidubce.services.dugo.video.MediaInfoByTimeRequest;
import com.baidubce.services.dugo.video.ParameterSettingRequest;
import com.baidubce.services.dugo.video.PlaybackRequest;
import com.baidubce.services.dugo.video.RealTimeRequest;
import com.baidubce.services.dugo.video.TrackAlarmMediaInfoRequest;
import com.baidubce.services.dugo.video.TrackAlarmVideoInfoResponse;
import com.baidubce.services.dugo.video.UploadTaskListRequest;
import com.baidubce.services.dugo.video.UploadTaskListResponse;
import com.baidubce.services.dugo.video.model.RealDataType;
import com.baidubce.services.dugo.video.model.RealStreamType;
import com.baidubce.services.dugo.video.model.VideoType;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import org.apache.commons.lang3.StringUtils;

/**
 * DuGo client
 */
public class DuGoClient extends AbstractBceClient {
    private static final String ENDPOINT = "https://ivc.gz.baidubce.com";
    private static final String PREFIX_VERSION1 = "/v1/ivc/data";
    private static final String PREFIX_VIDEO_VERSION1 = "/v1/video";
    private static final String PREFIX_REMOTE_DIAGNOSIS_VERSION1 = "/v1/ivc/remote-diagnosis/api";
    private static final String REQUEST = "request";
    private static final String PROJECT_ID = "projectId";
    private static final String BATCH_ID = "batchId";
    private static final String VEHICLE_IDS = "vehicleIds";
    private static final String VEHICLE_ID = "vehicleId";
    private static final String VIN = "vin";
    private static final String ICCID = "iccid";
    private static final String GROUP_ID = "groupId";
    private static final String DEVICE_ID = "deviceId";
    private static final String ALARM_IDS = "alarmIds";
    private static final String ALARM_ID = "alarmId";
    private static final String FENCE_ID = "fenceId";
    private static final String PAGE_NUM = "pageNum";
    private static final String PAGE_NO = "pageNo";
    private static final String PAGE_SIZE = "pageSize";
    private static final String START_TIME = "startTime";
    private static final String END_TIME = "endTime";
    private static final String FILE_NAME = "fileName";

    private static HttpResponseHandler[] handlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public DuGoClient(BceClientConfiguration config) {
        super(StringUtils.isEmpty(config.getEndpoint()) ? config.withEndpoint(ENDPOINT) : config, handlers);
    }

    private InternalRequest buildInternalRequest(String path, HttpMethodName methodName, AbstractDuGoRequest request,
                                                 Map<String, String> param) {
        BceIotHttpClient client = new BceIotHttpClient();
        client.withAuth(config.getCredentials().getAccessKeyId(), config.getCredentials().getSecretKey());
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path);
        client.withMethod(methodName, uri);
        if (methodName == HttpMethodName.POST || methodName == HttpMethodName.PUT) {
            client.withPayload(JsonUtils.toJsonString(request).getBytes());
        }

        if (param != null) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                client.addParams(entry.getKey(), entry.getValue());
            }
        }
        return client.getInternalRequest();
    }

    private InternalRequest buildInternalRequest(String path, HttpMethodName methodName, AbstractDuGoRequest request) {
        return buildInternalRequest(path, methodName, request, null);
    }

    /**
     * Query project list
     *
     * @return
     */
    public GetProjectListResponse getProjects() {
        InternalRequest internalRequest = buildInternalRequest(PREFIX_VERSION1 + "/project",
                HttpMethodName.GET, null);
        return this.invokeHttpClient(internalRequest, GetProjectListResponse.class);
    }

    /**
     * Query project by projectId
     *
     * @param projectId
     * @return
     */
    public GetProjectByIdResponse getProjectById(String projectId) {
        isEmptyCheck(projectId, PROJECT_ID);

        InternalRequest internalRequest = buildInternalRequest(PREFIX_VERSION1 + "/project/" + projectId,
                HttpMethodName.GET, null);
        return this.invokeHttpClient(internalRequest, GetProjectByIdResponse.class);
    }

    /**
     * Query batch list by projectId
     *
     * @param projectId
     * @return
     */
    public GetBatchListResponse getBatchList(String projectId) {
        isEmptyCheck(projectId, PROJECT_ID);

        InternalRequest internalRequest = buildInternalRequest(PREFIX_VERSION1 + "/project/" + projectId + "/batches",
                HttpMethodName.GET, null);
        return this.invokeHttpClient(internalRequest, GetBatchListResponse.class);
    }

    /**
     * Query instance by batchId
     *
     * @param batchId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public QueryInstancesByBatchResponse queryInstanceByBatch(String batchId, Integer pageNum, Integer pageSize) {
        isEmptyCheck(batchId, BATCH_ID);
        isNullCheck(pageNum, PAGE_NUM);
        isNullCheck(pageSize, PAGE_SIZE);

        Map<String, String> param = new HashMap<String, String>();
        param.put(BATCH_ID, batchId);
        param.put(PAGE_NUM, String.valueOf(pageNum));
        param.put(PAGE_SIZE, String.valueOf(pageSize));

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/vehicle/batch", HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, QueryInstancesByBatchResponse.class);
    }

    /**
     * Update description for a batch
     *
     * @param request
     * @param projectId
     * @param batchId
     * @return
     */
    public void updateBatchDesc(UpdateBatchDescRequest request, String projectId, String batchId) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(projectId, PROJECT_ID);
        isEmptyCheck(batchId, BATCH_ID);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/project/" + projectId + "/batch/" + batchId,
                        HttpMethodName.PUT, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Bind vehicles in batch
     *
     * @param request
     * @return
     */
    public void bindVehiclesInBatch(BatchBindVehicleRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getBatchId(), BATCH_ID);
        isEmptyCheck(request.getVehicleIds(), VEHICLE_IDS);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/vehicle/bind/batch", HttpMethodName.POST, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Random bind vehicles in batch
     *
     * @param request
     * @return
     */
    public void bindVehiclesInRandom(RandomBindVehiclesRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getProjectId(), PROJECT_ID);
        isEmptyCheck(request.getVehicleIds(), VEHICLE_IDS);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/vehicle/bind", HttpMethodName.POST, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Unbind vehicles in batch
     *
     * @param request
     */
    public void unbindVehiclesInBatch(BatchUnbindVehiclesRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getProjectId(), PROJECT_ID);
        isEmptyCheck(request.getVehicleIds(), VEHICLE_IDS);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/vehicle/unbind", HttpMethodName.POST, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Query bind result for mqtt protocol vehicles
     *
     * @param projectId
     * @return
     */
    public QueryMqttBindResultResponse queryBindResult(String projectId) {
        isEmptyCheck(projectId, PROJECT_ID);

        Map<String, String> param = new HashMap<String, String>();
        param.put(PROJECT_ID, projectId);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/mqtt/results", HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, QueryMqttBindResultResponse.class);
    }

    /**
     * Download auth info for mqtt protocol vehicles
     *
     * @param downloadUrl
     * @return
     */
    public DownloadAuthInfoResponse downloadAuthInfo(String downloadUrl) {
        isEmptyCheck(downloadUrl, "downloadUrl");

        Map<String, String> param = new HashMap<String, String>();
        param.put("downloadUrl", downloadUrl);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/mqtt/download", HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, DownloadAuthInfoResponse.class);
    }

    /**
     * Batch add devices to user account
     *
     * @param request
     */
    public void batchAddDevice(BatchAddDeviceRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getDeviceBindInfoList(), "device info");
        for (BatchAddDeviceRequest.DeviceBindInfo deviceInfo : request.getDeviceBindInfoList()) {
            isEmptyCheck(deviceInfo.getPk(), "pk");
            isEmptyCheck(deviceInfo.getDn(), "dn");
            isEmptyCheck(deviceInfo.getSign(), "sign");
        }

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/device/batchbind", HttpMethodName.POST, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Batch remove devices from user account
     *
     * @param request
     */
    public void batchRemoveDevice(BatchRemoveDeviceRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getDeviceIds(), "deviceIds");
        for (String deviceId : request.getDeviceIds()) {
            isEmptyCheck(deviceId, DEVICE_ID);
        }

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/device/batchunbind", HttpMethodName.POST, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Update alias for a device
     *
     * @param request
     */
    public void updateDeviceAlias(String deviceId, UpdateAliasNameRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(deviceId, DEVICE_ID);
        isEmptyCheck(request.getAliasName(), "aliasName");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/device/" + deviceId, HttpMethodName.PUT, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Batch active devices
     *
     * @param request
     */
    public void activateDevices(ActivateDeviceRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getDeviceIds(), "deviceIds");
        for (String deviceId : request.getDeviceIds()) {
            isEmptyCheck(deviceId, DEVICE_ID);
        }

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/device/activate", HttpMethodName.POST, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Query device info
     *
     * @param groupId
     * @param deviceId
     * @param aliasName
     * @param pk
     * @param dn
     * @param pageNo
     * @param pageSize
     * @return
     */
    public QueryDeviceResponse queryDeviceInfo(String groupId, String deviceId, String aliasName, String pk,
                                               String dn, Integer pageNo, Integer pageSize) {
        isNullCheck(pageNo, PAGE_NO);
        isNullCheck(pageSize, PAGE_SIZE);

        Map<String, String> params = new HashMap<String, String>();
        params.put(GROUP_ID, groupId);
        params.put(DEVICE_ID, deviceId);
        params.put("aliasName", aliasName);
        params.put("pk", pk);
        params.put("dn", dn);
        params.put(PAGE_NO, String.valueOf(pageNo));
        params.put(PAGE_SIZE, String.valueOf(pageSize));

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/device", HttpMethodName.GET, null, params);
        return this.invokeHttpClient(internalRequest, QueryDeviceResponse.class);
    }

    /**
     * Query device shadow
     *
     * @param deviceId
     * @param needUpdateTime
     * @return
     */
    public DeviceShadowResponse queryDeviceShadow(String deviceId, Boolean needUpdateTime) {
        isEmptyCheck(deviceId, DEVICE_ID);

        Map<String, String> params = new HashMap<String, String>();
        params.put(DEVICE_ID, deviceId);
        params.put("needUpdateTime", String.valueOf(needUpdateTime));

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/device/shadow", HttpMethodName.GET, null, params);
        return this.invokeHttpClient(internalRequest, DeviceShadowResponse.class);
    }

    /**
     * Query shadow for a group of devices
     *
     * @param groupId
     * @param needUpdateTime
     * @param pageNo
     * @param pageSize
     * @return
     */
    public GroupDeviceShadowResponse queryGroupDeviceShadow(String groupId, Boolean needUpdateTime,
                                                            Integer pageNo, Integer pageSize) {
        isEmptyCheck(groupId, GROUP_ID);
        isNullCheck(pageNo, PAGE_NO);
        isNullCheck(pageSize, PAGE_SIZE);

        Map<String, String> params = new HashMap<String, String>();
        params.put(GROUP_ID, groupId);
        params.put("needUpdateTime", String.valueOf(needUpdateTime));
        params.put(PAGE_NO, String.valueOf(pageNo));
        params.put(PAGE_SIZE, String.valueOf(pageSize));

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/device/shadow/group", HttpMethodName.GET, null, params);
        return this.invokeHttpClient(internalRequest, GroupDeviceShadowResponse.class);
    }

    /**
     * Query device history
     *
     * @param request
     * @return
     */
    public QueryDeviceHistoryResponse queryDeviceHistory(QueryDeviceHistoryRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getDeviceId(), DEVICE_ID);
        isEmptyCheck(request.getFields(), "fields");
        for (String field : request.getFields()) {
            isEmptyCheck(field, "field");
        }
        isNullCheck(request.getStart(), "start");
        isNullCheck(request.getEnd(), "end");

        InternalRequest internalRequest =
                buildInternalRequest("/v1/device/history", HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, QueryDeviceHistoryResponse.class);
    }

    /**
     * Query device log
     *
     * @param deviceId
     * @param startHour
     * @param endHour
     * @return
     */
    public QueryDeviceLogResponse queryDeviceLog(String deviceId, String startHour, String endHour) {
        isEmptyCheck(deviceId, DEVICE_ID);
        isEmptyCheck(startHour, "startHour");
        isEmptyCheck(endHour, "endHour");
        Map<String, String> params = new HashMap<String, String>();
        params.put(DEVICE_ID, deviceId);
        params.put("startHour", startHour);
        params.put("endHour", endHour);
        InternalRequest internalRequest = buildInternalRequest(PREFIX_REMOTE_DIAGNOSIS_VERSION1 + "/log/release",
                HttpMethodName.GET, null, params);
        return this.invokeHttpClient(internalRequest, QueryDeviceLogResponse.class);
    }

    /**
     * Query device status info
     *
     * @param deviceId
     * @param request
     * @return
     */
    public DeviceStatusInfoResponse queryDeviceStatusInfo(String deviceId, GetDeviceInfoRequest request) {
        isEmptyCheck(deviceId, DEVICE_ID);
        isNullCheck(request.getFields(), "fields");
        Map<String, String> params = new HashMap<String, String>();
        params.put(DEVICE_ID, deviceId);
        InternalRequest internalRequest = buildInternalRequest(PREFIX_REMOTE_DIAGNOSIS_VERSION1 + "/info/query",
                HttpMethodName.POST, request, params);
        return this.invokeHttpClient(internalRequest, DeviceStatusInfoResponse.class);

    }

    /**
     * Upload vehicle static data
     *
     * @param request
     * @return
     */
    public void uploadStaticData(UploadStaticDataRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVehicleId(), VEHICLE_ID);
        isEmptyCheck(request.getData(), "data");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/static/upload", HttpMethodName.POST, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Upload vehicle dynamic data
     *
     * @param request
     * @return
     */
    public void uploadDynamicData(UploadDynamicDataRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getPoints(), "points");

        InternalRequest internalRequest = buildInternalRequest("/v1/data", HttpMethodName.POST, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Query vehicle online status
     *
     * @param request
     * @return
     */
    public QueryVehicleStatusResponse queryVehicleOnlineStatus(QueryVehicleStatusRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVehicleIds(), VEHICLE_IDS);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/shadow/onlinequery", HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, QueryVehicleStatusResponse.class);
    }

    /**
     * Query single vehicle shadow
     *
     * @param request
     * @return
     */
    public QuerySingleShadowResponse querySingleShadow(QuerySingleShadowRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVehicleId(), VEHICLE_ID);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/shadow/query", HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, QuerySingleShadowResponse.class);
    }

    /**
     * Query multiple vehicle shadows
     *
     * @param request
     * @return
     */
    public QueryMultipleShadowResponse queryMultipleShadow(QueryMultipleShadowRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVehicleIds(), VEHICLE_IDS);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/shadow/batchquery", HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, QueryMultipleShadowResponse.class);
    }

    /**
     * Query vehicle shadow by tag
     *
     * @param request
     * @return
     */
    public ShadowFilterResponse queryShadowByTag(ShadowFilterRequest request) {
        isNullCheck(request, REQUEST);
        isNullCheck(request.getPageNo(), PAGE_NUM);
        isNullCheck(request.getPageSize(), PAGE_SIZE);
        isEmptyCheck(request.getProjectId(), PROJECT_ID);
        isEmptyCheck(request.getTags(), "tags");
        isEmptyCheck(request.getFields(), "fields");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/shadow/filterquery", HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, ShadowFilterResponse.class);
    }

    /**
     * Query vehicle history data
     *
     * @param request
     * @return
     */
    public HistoryInfoQueryResponse queryVehicleHistoryData(HistoryInfoQueryRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVehicleIds(), VEHICLE_IDS);
        isNullCheck(request.getStart(), "start");
        isNullCheck(request.getEnd(), "end");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/historyinfo/query", HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, HistoryInfoQueryResponse.class);
    }

    /**
     * Query display name of given attribute name
     *
     * @param projectId
     * @param attributeName
     * @return
     */
    public SchemaDisplayNameResponse queryDisplayName(String projectId, String attributeName) {
        isEmptyCheck(projectId, PROJECT_ID);
        isEmptyCheck(attributeName, "attributeName");

        Map<String, String> param = new HashMap<String, String>();
        param.put(PROJECT_ID, projectId);
        param.put("attributeName", attributeName);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/schema/displayName", HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, SchemaDisplayNameResponse.class);
    }

    /**
     * Query attribute names of given display name
     *
     * @param projectId
     * @param displayName
     * @return
     */
    public SchemaAttributeNameResponse queryAttributeName(String projectId, String displayName) {
        isEmptyCheck(projectId, PROJECT_ID);
        isEmptyCheck(displayName, "displayName");

        Map<String, String> param = new HashMap<String, String>();
        param.put(PROJECT_ID, projectId);
        param.put("displayName", displayName);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/schema/attributeNames", HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, SchemaAttributeNameResponse.class);
    }

    /**
     * Query vehicle terminal params (GB/T 32960 protocol)
     *
     * @param request
     * @return
     */
    public GB32960ParamQueryResponse queryTerminalParams(GB32960ParamQueryRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVin(), VIN);
        isEmptyCheck(request.getIccid(), ICCID);
        isEmptyCheck(request.getParamIds(), "paramIds");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/32960/param/query", HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, GB32960ParamQueryResponse.class);
    }

    /**
     * Set vehicle terminal params (GB/T 32960 protocol)
     *
     * @param request
     */
    public void settingTerminalParams(GB32960ParamSettingRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVin(), VIN);
        isEmptyCheck(request.getIccid(), ICCID);
        isEmptyCheck(request.getParams(), "params");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/32960/param/setting", HttpMethodName.POST, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Control vehicle terminal (GB/T 32960 protocol)
     *
     * @param request
     */
    public void controlVehicleTerminal(GB32960VehicleControlRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVin(), VIN);
        isEmptyCheck(request.getIccid(), ICCID);
        isNullCheck(request.getCommandId(), "commandId");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/32960/vehicle/control", HttpMethodName.POST, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Create an alarm rule
     *
     * @param request
     * @param projectId
     * @return
     */
    public void createAlarmRule(String projectId, AlarmCreateRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(projectId, PROJECT_ID);
        isEmptyCheck(request.getName(), "name");
        isNullCheck(request.getAlarmRule(), "alarmRule");
        isNullCheck(request.getAlarmPolicy(), "alarmPolicy");

        Map<String, String> param = new HashMap<String, String>();
        param.put(PROJECT_ID, projectId);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/alarm", HttpMethodName.POST, request, param);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Update an alarm rule
     *
     * @param request
     * @param projectId
     */
    public void updateAlarmRule(String projectId, AlarmUpdateRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(projectId, PROJECT_ID);
        isEmptyCheck(request.getName(), "name");
        isNullCheck(request.getAlarmRule(), "alarmRule");
        isNullCheck(request.getAlarmPolicy(), "alarmPolicy");
        isEmptyCheck(request.getAlarmId(), ALARM_ID);

        Map<String, String> param = new HashMap<String, String>();
        param.put(PROJECT_ID, projectId);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/alarm", HttpMethodName.PUT, request, param);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Query details of a alarm rule
     *
     * @param projectId
     * @param alarmId
     * @return
     */
    public AlarmDetailsResponse getAlarmRuleDetails(String projectId, String alarmId) {
        isEmptyCheck(projectId, PROJECT_ID);
        isEmptyCheck(alarmId, ALARM_ID);

        Map<String, String> param = new HashMap<String, String>();
        param.put(PROJECT_ID, projectId);
        param.put(ALARM_ID, alarmId);

        InternalRequest internalRequest = buildInternalRequest(PREFIX_VERSION1 + "/alarm",
                HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, AlarmDetailsResponse.class);
    }

    /**
     * Get alarm rule list
     *
     * @param projectId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public AlarmRuleListResponse getAlarmRuleList(String projectId, Integer pageNum, Integer pageSize) {
        isEmptyCheck(projectId, PROJECT_ID);
        isNullCheck(pageNum, PAGE_NUM);
        isNullCheck(pageSize, PAGE_SIZE);

        Map<String, String> param = new HashMap<String, String>();
        param.put(PROJECT_ID, projectId);
        param.put(PAGE_NUM, String.valueOf(pageNum));
        param.put(PAGE_SIZE, String.valueOf(pageSize));
        param.put("list", "");

        InternalRequest internalRequest = buildInternalRequest(PREFIX_VERSION1 + "/alarm",
                HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, AlarmRuleListResponse.class);
    }

    /**
     * Batch delete alarm rules
     *
     * @param request
     * @param projectId
     */
    public void batchDeleteAlarmRules(String projectId, AlarmBatchRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(projectId, PROJECT_ID);
        isEmptyCheck(request.getAlarmIds(), ALARM_IDS);

        Map<String, String> param = new HashMap<String, String>();
        param.put("delete", "");
        param.put(PROJECT_ID, projectId);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/alarm", HttpMethodName.POST, request, param);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Batch shield alarm rules
     *
     * @param request
     * @param projectId
     */
    public void batchShieldAlarms(AlarmBatchRequest request, String projectId) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(projectId, PROJECT_ID);
        isEmptyCheck(request.getAlarmIds(), ALARM_IDS);

        Map<String, String> param = new HashMap<String, String>();
        param.put("shield", "");
        param.put(PROJECT_ID, projectId);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/alarm", HttpMethodName.POST, request, param);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Batch recover shield alarm rules
     *
     * @param request
     * @param projectId
     */
    public void batchRecoverAlarms(AlarmBatchRequest request, String projectId) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(projectId, PROJECT_ID);
        isEmptyCheck(request.getAlarmIds(), ALARM_IDS);

        Map<String, String> param = new HashMap<String, String>();
        param.put("recover", "");
        param.put(PROJECT_ID, projectId);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/alarm", HttpMethodName.POST, request, param);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Geo coding (transform an address to latitude/longitude pair)
     *
     * @param request
     * @return
     */
    public GeoCodingResponse geoCoding(GeoCodingRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getAddress(), "address");

        InternalRequest internalRequest = buildInternalRequest(PREFIX_VERSION1 + "/geocoder",
                HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, GeoCodingResponse.class);
    }

    /**
     * Geo decode (transform a latitude/longitude pair to an address)
     *
     * @param request
     * @return
     */
    public GeoDecodingResponse geoDecoding(GeoDecodingRequest request) {
        isNullCheck(request, REQUEST);
        isNullCheck(request.getLatitude(), "latitude");
        isNullCheck(request.getLongitude(), "longitude");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/reversegeocoder", HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, GeoDecodingResponse.class);
    }

    /**
     * Query the latest point of a vehicle
     *
     * @param request
     * @return
     */
    public GetLatestPointResponse getLatestPoint(GetLatestPointRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVehicleId(), VEHICLE_ID);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/track/getlatestpoint", HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, GetLatestPointResponse.class);
    }

    /**
     * Query track of a vehicle
     *
     * @param request
     * @return
     */
    public GetTrackResponse getTrack(GetTrackRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVehicleId(), VEHICLE_ID);
        isNullCheck(request.getStartTime(), START_TIME);
        isNullCheck(request.getEndTime(), END_TIME);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/track/gettrack", HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, GetTrackResponse.class);
    }

    /**
     * Get distance of a vehicle track
     *
     * @param request
     * @return
     */
    public GetDistanceResponse getDistance(GetDistanceRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVehicleId(), VEHICLE_ID);
        isNullCheck(request.getStartTime(), START_TIME);
        isNullCheck(request.getEndTime(), END_TIME);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/track/getdistance", HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, GetDistanceResponse.class);
    }

    /**
     * Query vehicle stay points during a period
     *
     * @param request
     * @return
     */
    public StayPointResponse getStayPoint(StayPointRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVehicleId(), VEHICLE_ID);
        isNullCheck(request.getStartTime(), START_TIME);
        isNullCheck(request.getEndTime(), END_TIME);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/analysis/staypoint", HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, StayPointResponse.class);
    }

    /**
     * Query driving behavior of a vehicle
     *
     * @param request
     * @return
     */
    public DrivingBehaviorResponse getDrivingBehavior(DrivingBehaviorRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVehicleId(), VEHICLE_ID);
        isNullCheck(request.getStartTime(), START_TIME);
        isNullCheck(request.getEndTime(), END_TIME);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/analysis/drivingbehavior", HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, DrivingBehaviorResponse.class);
    }

    /**
     * Create a fence
     *
     * @param request
     * @return
     */
    public CreateFenceResponse createFence(CreateFenceRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getProjectId(), PROJECT_ID);
        isEmptyCheck(request.getFenceName(), "fenceName");
        isEmptyCheck(request.getFenceType(), "fenceType");
        isNullCheck(request.getFenceParamsOption(), "fenceParamsOption");
        isEmptyCheck(request.getCoordType(), "coordType");
        isEmptyCheck(request.getMonitoredObjectList(), "monitoredObjectList");
        isEmptyCheck(request.getAlertType(), "alertType");
        isEmptyCheck(request.getAlertSinkList(), "alertSinkList");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/fence", HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, CreateFenceResponse.class);
    }

    /**
     * Update a fence
     *
     * @param request
     */
    public void updateFence(UpdateFenceRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getProjectId(), PROJECT_ID);
        isEmptyCheck(request.getFenceId(), FENCE_ID);
        isEmptyCheck(request.getFenceName(), "fenceName");
        isNullCheck(request.getFenceParamsOption(), "fenceParamsOption");
        isEmptyCheck(request.getCoordType(), "coordType");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/fence", HttpMethodName.PUT, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Get details of a fence
     *
     * @param projectId
     * @param fenceId
     * @return
     */
    public FenceDetailResponse getFenceDetail(String projectId, String fenceId) {
        isEmptyCheck(projectId, PROJECT_ID);
        isEmptyCheck(fenceId, FENCE_ID);

        Map<String, String> param = new HashMap<String, String>();
        param.put(PROJECT_ID, projectId);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/fence/" + fenceId, HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, FenceDetailResponse.class);
    }

    /**
     * Delete a fence
     *
     * @param projectId
     * @param fenceId
     */
    public void deleteFence(String projectId, String fenceId) {
        isEmptyCheck(projectId, PROJECT_ID);
        isEmptyCheck(fenceId, FENCE_ID);

        Map<String, String> param = new HashMap<String, String>();
        param.put(PROJECT_ID, projectId);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/fence/" + fenceId, HttpMethodName.DELETE, null, param);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Get fence list in a project
     *
     * @param projectId
     * @param fenceName
     * @param vehicleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public FenceListResponse getFenceList(String projectId, String fenceName, String vehicleId,
                                          Integer pageNum, Integer pageSize) {
        isEmptyCheck(projectId, PROJECT_ID);
        isNullCheck(pageNum, PAGE_NUM);
        isNullCheck(pageSize, PAGE_SIZE);
        checkIsTrue(pageSize <= 1000, "分页参数不得超过1000");

        Map<String, String> param = new HashMap<String, String>();
        param.put(PROJECT_ID, projectId);
        param.put("fenceName", fenceName);
        param.put(VEHICLE_ID, vehicleId);
        param.put(PAGE_NUM, String.valueOf(pageNum));
        param.put(PAGE_SIZE, String.valueOf(pageSize));

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/fence", HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, FenceListResponse.class);
    }

    /**
     * Update alarm config for a fence
     *
     * @param request
     */
    public void updateFenceAlarmConfig(UpdateFenceAlarmRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getProjectId(), PROJECT_ID);
        isEmptyCheck(request.getFenceId(), FENCE_ID);
        isEmptyCheck(request.getAlertType(), "alertType");
        isEmptyCheck(request.getAlertSinkList(), "alertSinkList");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/fence/alert", HttpMethodName.PUT, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Query alarm list in a fence
     *
     * @param projectId
     * @param fenceId
     * @param pageNum
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param vehicleId
     * @return
     */
    public GetFenceAlarmsResponse getFenceAlarms(String projectId, String fenceId, Integer pageNum, Integer pageSize,
                                                 String startTime, String endTime, String vehicleId) {
        isEmptyCheck(projectId, PROJECT_ID);
        isNullCheck(pageNum, PAGE_NUM);
        isNullCheck(pageSize, PAGE_SIZE);

        Map<String, String> param = new HashMap<String, String>();
        param.put(PROJECT_ID, projectId);
        param.put(PAGE_NUM, String.valueOf(pageNum));
        param.put(PAGE_SIZE, String.valueOf(pageSize));
        param.put(START_TIME, startTime);
        param.put(END_TIME, endTime);
        param.put(VEHICLE_ID, vehicleId);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/fence/" + fenceId + "/alarm", HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, GetFenceAlarmsResponse.class);
    }

    /**
     * Add monitored vehicles to a given fence
     *
     * @param fenceId
     * @param request
     */
    public void addMonitoredVehiclesToFence(String fenceId, FenceMonitoredVehicleRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(fenceId, FENCE_ID);
        isEmptyCheck(request.getProjectId(), PROJECT_ID);
        isEmptyCheck(request.getVehicleDigestList(), "vehicleDigestList");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/fence/" + fenceId + "/vehicle", HttpMethodName.POST, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Update monitored vehicles in a given fence
     *
     * @param fenceId
     * @param request
     */
    public void updateMonitoredVehiclesForFence(String fenceId, FenceMonitoredVehicleRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(fenceId, FENCE_ID);
        isEmptyCheck(request.getProjectId(), PROJECT_ID);
        isEmptyCheck(request.getVehicleDigestList(), "vehicleDigestList");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/fence/" + fenceId + "/vehicle", HttpMethodName.PUT, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Remove monitored vehicles from a given fence
     *
     * @param fenceId
     * @param request
     */
    public void removeVehiclesFromFence(String fenceId, DeleteMonitoredVehicleRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(fenceId, FENCE_ID);
        isEmptyCheck(request.getProjectId(), PROJECT_ID);
        isEmptyCheck(request.getVehicleIdList(), "vehicleIdList");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/fence/" + fenceId + "/vehicle/delete",
                        HttpMethodName.POST, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Query monitored vehicles in a given fence
     *
     * @param projectId
     * @param fenceId
     * @param pageNum
     * @param pageSize
     * @param vehicleId
     * @return
     */
    public MonitoredVehicleListResponse getVehiclesInFence(String projectId, String fenceId,
                                                           Integer pageNum, Integer pageSize, String vehicleId) {
        isEmptyCheck(projectId, PROJECT_ID);
        isEmptyCheck(fenceId, FENCE_ID);
        isNullCheck(pageNum, PAGE_NUM);
        isNullCheck(pageSize, PAGE_SIZE);

        Map<String, String> param = new HashMap<String, String>();
        param.put(PROJECT_ID, projectId);
        param.put(PAGE_NUM, String.valueOf(pageNum));
        param.put(PAGE_SIZE, String.valueOf(pageSize));
        param.put(VEHICLE_ID, vehicleId);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VERSION1 + "/fence/" + fenceId + "/vehicle",
                        HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, MonitoredVehicleListResponse.class);
    }

    /**
     * start real-time play
     *
     * @param vehicleId
     * @param channel
     * @param request
     * @return
     */
    public GetPlayUrlResponse realTimePlay(String vehicleId, Integer channel, RealTimeRequest request) {
        isEmptyCheck(vehicleId, VEHICLE_ID);
        isNullCheck(channel, "channel");
        isNullCheck(request, REQUEST);
        isEnumValue(request.getDataType(), "dataType", RealDataType.class);
        isEnumValue(request.getStreamType(), "streamType", RealStreamType.class);
        isEnumValue(request.getVideoType(), "videoType", VideoType.class);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/real-time/" + vehicleId + "/" + channel,
                        HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, GetPlayUrlResponse.class);
    }

    /**
     * end real-time play
     *
     * @param vehicleId
     * @param channel
     */
    public void endRealTimePlay(String vehicleId, Integer channel) {
        isEmptyCheck(vehicleId, VEHICLE_ID);
        isNullCheck(channel, "channel");

        Map<String, String> param = new HashMap<String, String>();
        param.put("close", "");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/real-time/" + vehicleId + "/" + channel,
                        HttpMethodName.PUT, null, param);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * start history playback
     *
     * @param vehicleId
     * @param request
     * @return
     */
    public GetPlayUrlResponse historyPlayback(String vehicleId, PlaybackRequest request) {
        isEmptyCheck(vehicleId, VEHICLE_ID);
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getFileName(), "fileName");
        isEnumValue(request.getVideoType(), "videoType", VideoType.class);

        Map<String, String> param = new HashMap<String, String>();
        param.put("open", "");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/playback/" + vehicleId,
                        HttpMethodName.POST, request, param);
        return this.invokeHttpClient(internalRequest, GetPlayUrlResponse.class);
    }

    /**
     * end history playback
     *
     * @param vehicleId
     * @param fileName
     */
    public void endHistoryPlayback(String vehicleId, String fileName) {
        isEmptyCheck(vehicleId, VEHICLE_ID);
        isEmptyCheck(fileName, FILE_NAME);

        Map<String, String> param = new HashMap<String, String>();
        param.put(FILE_NAME, fileName);
        param.put("close", "");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/playback/" + vehicleId,
                        HttpMethodName.PUT, null, param);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * upload video by name
     *
     * @param vehicleId
     * @param request
     * @return
     */
    public FileUploadResponse videoUploadByName(String vehicleId, FileNameRequest request) {
        isEmptyCheck(vehicleId, VEHICLE_ID);
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getFileName(), "fileName");

        Map<String, String> param = new HashMap<String, String>();
        param.put("name", "");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/upload/" + vehicleId,
                        HttpMethodName.POST, request, param);
        return this.invokeHttpClient(internalRequest, FileUploadResponse.class);
    }

    /**
     * cancel upload task
     *
     * @param taskUuid
     */
    public void videoUploadCancel(String taskUuid) {
        isEmptyCheck(taskUuid, "taskUuid");

        Map<String, String> param = new HashMap<String, String>();
        param.put("cancel", "");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/upload/tasks/" + taskUuid,
                        HttpMethodName.PUT, null, param);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * get status of upload task
     *
     * @param taskUuid
     * @return
     */
    public GetTaskStatusResponse getTaskStatus(String taskUuid) {
        isEmptyCheck(taskUuid, "taskUuid");

        Map<String, String> param = new HashMap<String, String>();
        param.put("status", "");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/playback/tasks/" + taskUuid,
                        HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, GetTaskStatusResponse.class);
    }

    /**
     * get upload task list
     *
     * @param pageNum
     * @param pageSize
     * @param request
     * @return
     */
    public UploadTaskListResponse getUploadTaskList(Integer pageNum, Integer pageSize, UploadTaskListRequest request) {
        isNullCheck(pageNum, PAGE_NUM);
        isNullCheck(pageSize, PAGE_SIZE);
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVehicleId(), VEHICLE_ID);
        isNullCheck(request.getStartTime(), START_TIME);
        isNullCheck(request.getEndTime(), END_TIME);

        Map<String, String> param = new HashMap<String, String>();
        param.put(PAGE_NUM, String.valueOf(pageNum));
        param.put(PAGE_SIZE, String.valueOf(pageSize));

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/query" + "/upload/tasks",
                        HttpMethodName.POST, request, param);
        return this.invokeHttpClient(internalRequest, UploadTaskListResponse.class);
    }

    /**
     * get play url by upload task
     *
     * @param taskUuid
     * @return
     */
    public GetPlayUrlResponse getPlayUrlByTask(String taskUuid) {
        isEmptyCheck(taskUuid, "taskUuid");

        Map<String, String> param = new HashMap<String, String>();
        param.put("url", "");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/playback/tasks/" + taskUuid,
                        HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, GetPlayUrlResponse.class);
    }

    /**
     * get play url by video name
     *
     * @param vehicleId
     * @param request
     * @return
     */
    public GetPlayUrlResponse getPlayUrlByName(String vehicleId, FileNameRequest request) {
        isEmptyCheck(vehicleId, VEHICLE_ID);
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getFileName(), "fileName");

        Map<String, String> param = new HashMap<String, String>();
        param.put("name", "");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/playback/" + vehicleId,
                        HttpMethodName.POST, request, param);
        return this.invokeHttpClient(internalRequest, GetPlayUrlResponse.class);
    }

    /**
     * get video download url
     *
     * @param vehicleId
     * @param request
     * @return
     */
    public GetDownloadUrlResponse getVideoDownloadUrl(String vehicleId, FileNameRequest request) {
        isEmptyCheck(vehicleId, VEHICLE_ID);
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getFileName(), "fileName");

        Map<String, String> param = new HashMap<String, String>();
        param.put("video", "");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/download/" + vehicleId,
                        HttpMethodName.POST, request, param);
        return this.invokeHttpClient(internalRequest, GetDownloadUrlResponse.class);
    }

    /**
     * get image download url
     *
     * @param vehicleId
     * @param request
     * @return
     */
    public GetDownloadUrlResponse getImageDownloadUrl(String vehicleId, FileNameRequest request) {
        isEmptyCheck(vehicleId, VEHICLE_ID);
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getFileName(), "fileName");

        Map<String, String> param = new HashMap<String, String>();
        param.put("image", "");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/download/" + vehicleId,
                        HttpMethodName.POST, request, param);
        return this.invokeHttpClient(internalRequest, GetDownloadUrlResponse.class);
    }

    /**
     * set param of principle
     *
     * @param vehicleId
     * @param request
     */
    public void setParam(String vehicleId, ParameterSettingRequest request) {
        isEmptyCheck(vehicleId, VEHICLE_ID);
        isNullCheck(request, REQUEST);
        isMatchPattern(request.getParamHex(), "paramHex", "^[A-Fa-f0-9]{1,210}$");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/param-setting/" + vehicleId,
                        HttpMethodName.PUT, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * get media list by time
     *
     * @param vehicleId
     * @param request
     * @return
     */
    public GetMediaInfoListResponse getVideoInfoByTime(String vehicleId, MediaInfoByTimeRequest request) {
        isEmptyCheck(vehicleId, VEHICLE_ID);
        isNullCheck(request, REQUEST);
        isNullCheck(request.getStartTime(), START_TIME);
        isNullCheck(request.getEndTime(), END_TIME);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/query" + "/media/" + vehicleId,
                        HttpMethodName.POST, request);
        return this.invokeHttpClient(internalRequest, GetMediaInfoListResponse.class);
    }

    /**
     * get media by file name
     *
     * @param vehicleId
     * @param fileName
     * @return
     */
    public GetMediaInfoResponse getMediaInfoByFileName(String vehicleId, String fileName) {
        isEmptyCheck(vehicleId, VEHICLE_ID);
        isEmptyCheck(fileName, "fileName");

        Map<String, String> param = new HashMap<String, String>();
        param.put("fileName", fileName);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/query" + "/media/" + vehicleId + "/filename",
                        HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, GetMediaInfoResponse.class);
    }

    /**
     * get media list by alarm
     *
     * @param alarmUuid
     * @return
     */
    public GetMediaInfoListResponse getMediaInfoByAlarmUuid(String alarmUuid) {
        isEmptyCheck(alarmUuid, "alarmUuid");

        Map<String, String> param = new HashMap<String, String>();
        param.put("alarmUuid", alarmUuid);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/query" + "/media/alarm-uuid",
                        HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, GetMediaInfoListResponse.class);
    }

    /**
     * get media list by alarmRefKey
     *
     * @param vehicleId
     * @param alarmRefKey
     * @return
     */
    public GetMediaInfoListResponse getMediaInfoByAlarmRefKey(String vehicleId, String alarmRefKey) {
        isEmptyCheck(vehicleId, VEHICLE_ID);
        isEmptyCheck(alarmRefKey, "alarmRefKey");

        Map<String, String> param = new HashMap<String, String>();
        param.put("alarmRefKey", alarmRefKey);

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/query" + "/media/" + vehicleId + "/alarm-ref-key",
                        HttpMethodName.GET, null, param);
        return this.invokeHttpClient(internalRequest, GetMediaInfoListResponse.class);
    }

    /**
     * get alarm list by vehicle list
     *
     * @param request
     * @return
     */
    public AlarmInfoListResponse getAlarmInfoByVehicleIdList(AlarmInfoByVehicleIdListRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVehicleIdList(), "vehicleIdList");
        isNullCheck(request.getStartTime(), START_TIME);
        isNullCheck(request.getEndTime(), END_TIME);

        Map<String, String> param = new HashMap<String, String>();
        param.put("id", "");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/query" + "/alarm",
                        HttpMethodName.POST, request, param);
        return this.invokeHttpClient(internalRequest, AlarmInfoListResponse.class);
    }

    /**
     * get alarm list by time
     *
     * @param request
     * @return
     */
    public AlarmInfoListResponse getAlarmInfoByTime(AlarmInfoByTimeRequest request) {
        isNullCheck(request, REQUEST);
        isNullCheck(request.getStartTime(), START_TIME);
        isNullCheck(request.getEndTime(), END_TIME);

        Map<String, String> param = new HashMap<String, String>();
        param.put("time", "");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/query" + "/alarm",
                        HttpMethodName.POST, request, param);
        return this.invokeHttpClient(internalRequest, AlarmInfoListResponse.class);
    }

    /**
     * get alarm & media list by vehicle
     *
     * @param request
     * @return
     */
    public AlarmVideoInfoListResponse getAlarmVideoInfoByVehicleId(AlarmVideoInfoByVehicleIdRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVehicleId(), VEHICLE_ID);
        isNullCheck(request.getStartTime(), START_TIME);
        isNullCheck(request.getEndTime(), END_TIME);

        Map<String, String> param = new HashMap<String, String>();
        param.put("id", "");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/query" + "/alarm/video",
                        HttpMethodName.POST, request, param);
        return this.invokeHttpClient(internalRequest, AlarmVideoInfoListResponse.class);
    }

    /**
     * get alarm & media list by vehicle
     *
     * @param request
     * @return
     */
    public AlarmVideoInfoListResponse getAlarmVideoInfoByVehicleIdList(AlarmVideoInfoByVehicleIdListRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVehicleIdList(), "vehicleIdList");
        isNullCheck(request.getStartTime(), START_TIME);
        isNullCheck(request.getEndTime(), END_TIME);

        Map<String, String> param = new HashMap<String, String>();
        param.put("ids", "");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/query" + "/alarm/video",
                        HttpMethodName.POST, request, param);
        return this.invokeHttpClient(internalRequest, AlarmVideoInfoListResponse.class);
    }

    /**
     * get track point & alarm & media list by vehicle
     *
     * @param request
     * @return
     */
    public TrackAlarmVideoInfoResponse getTrackAlarmMediaInfoListByVehicleId(TrackAlarmMediaInfoRequest request) {
        isNullCheck(request, REQUEST);
        isEmptyCheck(request.getVehicleId(), VEHICLE_ID);
        isNullCheck(request.getStartTime(), START_TIME);
        isNullCheck(request.getEndTime(), END_TIME);

        Map<String, String> param = new HashMap<String, String>();
        param.put("id", "");

        InternalRequest internalRequest =
                buildInternalRequest(PREFIX_VIDEO_VERSION1 + "/query" + "/track",
                        HttpMethodName.POST, request, param);
        return this.invokeHttpClient(internalRequest, TrackAlarmVideoInfoResponse.class);
    }

    private void isEmptyCheck(String keyValue, String keyName) {
        checkStringNotEmpty(keyValue, keyName + " should not be empty");
    }

    private void isEmptyCheck(List keyValues, String keyName) {
        isNullCheck(keyValues, keyName);
        checkIsTrue(!keyValues.isEmpty(), keyName + " should not be empty");
    }

    private void isEmptyCheck(Map keyValues, String keyName) {
        isNullCheck(keyValues, keyName);
        checkIsTrue(!keyValues.isEmpty(), keyName + " should not be empty");
    }

    private void isNullCheck(Object keyValue, String keyName) {
        checkNotNull(keyValue, keyName + " should not be null");
    }

    private void isMatchPattern(String keyValue, String keyName, String pattern) {
        checkPattern(keyValue, pattern, keyName + " format should be " + pattern);
    }

    private void isEnumValue(String keyValue, String keyName, Class<?> enumCls) {
        checkValidValue(keyValue, enumCls, "the value of " + keyName + " is not valid");
    }
}
