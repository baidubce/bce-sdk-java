/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo;

import com.baidubce.BceServiceException;
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
import com.baidubce.services.dugo.alarm.AlarmRuleListResponse;
import com.baidubce.services.dugo.alarm.AlarmUpdateRequest;
import com.baidubce.services.dugo.alarm.AlarmDetailsResponse;
import com.baidubce.services.dugo.alarm.AlarmBatchRequest;
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

import com.baidubce.services.dugo.model.AlarmRuleType;
import com.baidubce.services.dugo.model.AlarmSinkType;
import com.baidubce.services.dugo.model.AlarmType;
import com.baidubce.services.dugo.model.MonitoredObjectType;
import com.baidubce.services.dugo.model.FenceAlertCondition;
import com.baidubce.services.dugo.model.FenceType;
import com.baidubce.services.dugo.model.CircleFenceOption;
import com.baidubce.services.dugo.model.CoordType;
import com.baidubce.services.dugo.model.AlertType;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;

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
import com.baidubce.services.dugo.video.MediaInfoByTimeNullAlarmRequest;
import com.baidubce.services.dugo.video.MediaInfoByTimeRequest;
import com.baidubce.services.dugo.video.ParameterSettingRequest;
import com.baidubce.services.dugo.video.PlaybackRequest;
import com.baidubce.services.dugo.video.RealTimeRequest;
import com.baidubce.services.dugo.video.TrackAlarmMediaInfoRequest;
import com.baidubce.services.dugo.video.TrackAlarmVideoInfoResponse;
import com.baidubce.services.dugo.video.UploadTaskListRequest;
import com.baidubce.services.dugo.video.UploadTaskListResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.Random;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * DuGo Client test
 */
@Ignore
public class DuGoClientTest {
    private DuGoClient client;

    private static final String ENDPOINT = "DUGO_ENDPOINT";
    private static final String UPLOAD_ENDPOINT = "UPLOAD_DUGO_ENDPOINT";
    private static final String YOUR_ACCESS_KEY = "YOUR_ACCESS_KEY";
    private static final String YOUR_SECRET_KEY = "YOUR_SECRET_KEY";

    private static final String YOUR_HTTP_PROJECT_NAME = "YOUR_HTTP_PROJECT_NAME";
    private static final String YOUR_HTTP_PROJECT_ID = "YOUR_HTTP_PROJECT_ID";
    private static final String YOUR_HTTP_BATCH_ID = "YOUR_HTTP_BATCH_ID";
    private static final String YOUR_HTTP_VEHICLE_ID1 = "YOUR_HTTP_VEHICLE_ID1";
    private static final String YOUR_HTTP_VEHICLE_ID2 = "YOUR_HTTP_VEHICLE_ID2";
    private static final List<String> YOUR_HTTP_VEHICLE_IDS =
            Arrays.asList(YOUR_HTTP_VEHICLE_ID1, YOUR_HTTP_VEHICLE_ID2);
    private static final String YOUR_GB32960_PROJECT_NAME = "YOUR_GB32960_PROJECT_NAME";
    private static final String YOUR_GB32960_PROJECT_ID = "YOUR_GB32960_PROJECT_ID";
    private static final String YOUR_GB32960_BATCH_ID = "YOUR_GB32960_BATCH_ID";
    private static final String YOUR_GB32960_VIN1 = "YOUR_GB32960_VIN1";
    private static final String YOUR_GB32960_ICCID1 = "YOUR_GB32960_ICCID1";
    private static final String YOUR_GB32960_VIN2 = "YOUR_GB32960_VIN2";
    private static final String YOUR_GB32960_ICCID2 = "YOUR_GB32960_ICCID2";
    private static final List<String> YOUR_GB32960_VINS = Arrays.asList(YOUR_GB32960_VIN1, YOUR_GB32960_VIN2);
    private static final String YOUR_MQTT_PROJECT_NAME = "YOUR_MQTT_PROJECT_NAME";
    private static final String YOUR_MQTT_PROJECT_ID = "YOUR_MQTT_PROJECT_ID";
    private static final String YOUR_MQTT_BATCH_ID = "YOUR_MQTT_BATCH_ID";
    private static final String YOUR_MQTT_VEHICLE_ID1 = "YOUR_MQTT_VEHICLE_ID1";
    private static final String YOUR_MQTT_VEHICLE_ID2 = "YOUR_MQTT_VEHICLE_ID2";
    private static final String YOUR_PK = "YOUR_PK";
    private static final String YOUR_DN = "YOUR_DN";
    private static final String YOUR_SIGN = "YOUR_SIGN";
    private static final String YOUR_GROUP_ID = "YOUR_GROUP_ID";
    private static final String YOUR_DEVICE_ID = "YOUR_DEVICE_ID";
    private static final String YOUR_ALARM_ID = "YOUR_ALARM_ID";
    private static final String YOUR_FENCE_ID = "YOUR_FENCE_ID";
    private static final String YOUR_KAFKA_TOPIC = "YOUR_KAFKA_TOPIC";

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(YOUR_ACCESS_KEY, YOUR_SECRET_KEY));
        config.setEndpoint(ENDPOINT);
        client = new DuGoClient(config);
    }

    @Test
    public void queryDeviceLog() {
        String startHour = "2020-03-20-00";
        String endHour = "2020-03-21-00";
        QueryDeviceLogResponse response = client.queryDeviceLog(YOUR_DEVICE_ID, startHour, endHour);
        Assert.assertNotNull(response.getLogFileUrlList());
        Assert.assertTrue(response.getLogFileUrlList().size() > 0);
    }

    @Test
    public void queryDeviceStatusInfo() {
        GetDeviceInfoRequest request = new GetDeviceInfoRequest();
        request.setFields(new ArrayList<String>());
        DeviceStatusInfoResponse response = client.queryDeviceStatusInfo(YOUR_DEVICE_ID, request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getInfo());
        Assert.assertNotNull(response.getInfo().getDevice());
        Assert.assertNotNull(response.getInfo().getOta());
        Assert.assertNotNull(response.getInfo().getVersion());
        Assert.assertNotNull(response.getInfo().getSim());
    }

    @Test
    public void getProjects() {
        GetProjectListResponse response = client.getProjects();
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getMeta());
        Assert.assertTrue(response.getData() != null && !response.getData().isEmpty());
        boolean exist = false;
        for (GetProjectListResponse.ProjectInfoVo project : response.getData()) {
            if (YOUR_HTTP_PROJECT_ID.equals(project.getId()) && YOUR_HTTP_PROJECT_NAME.equals(project.getName())) {
                exist = true;
            }
        }
        Assert.assertTrue(exist);
    }

    @Test
    public void getProjectById() {
        GetProjectByIdResponse response = client.getProjectById(YOUR_HTTP_PROJECT_ID);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(YOUR_HTTP_PROJECT_ID, response.getData().getId());
        Assert.assertEquals(YOUR_HTTP_PROJECT_NAME, response.getData().getName());
        Assert.assertEquals("HTTP", response.getData().getProtocol());
    }

    @Test
    public void getBatchList() {
        GetBatchListResponse response = client.getBatchList(YOUR_HTTP_PROJECT_ID);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getMeta());
        Assert.assertTrue(response.getData() != null && !response.getData().isEmpty());
        boolean exist = false;
        for (GetBatchListResponse.BatchInfoVo batch : response.getData()) {
            if (YOUR_HTTP_BATCH_ID.equals(batch.getId())) {
                exist = true;
            }
        }
        Assert.assertTrue(exist);
    }

    @Test
    public void queryInstanceByBatch() {
        QueryInstancesByBatchResponse response = client.queryInstanceByBatch(YOUR_HTTP_BATCH_ID, 1, 10);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getMeta());
        Assert.assertTrue(response.getMeta().getTotal() >= 2);
        Assert.assertTrue(response.getInstanceInfos() != null && !response.getInstanceInfos().isEmpty());
    }

    @Test
    public void updateBatchDesc() {
        UpdateBatchDescRequest request1 = new UpdateBatchDescRequest();
        request1.setDescription("");
        client.updateBatchDesc(request1, YOUR_HTTP_PROJECT_ID, YOUR_HTTP_BATCH_ID);
        GetBatchListResponse response1 = client.getBatchList(YOUR_HTTP_PROJECT_ID);
        boolean empty = false;
        for (GetBatchListResponse.BatchInfoVo batch : response1.getData()) {
            if (YOUR_HTTP_BATCH_ID.equals(batch.getId()) && "".equals(batch.getDescription())) {
                empty = true;
            }
        }
        Assert.assertTrue(empty);

        UpdateBatchDescRequest request2 = new UpdateBatchDescRequest();
        request2.setDescription("Batch Description");
        client.updateBatchDesc(request2, YOUR_HTTP_PROJECT_ID, YOUR_HTTP_BATCH_ID);
        GetBatchListResponse response2 = client.getBatchList(YOUR_HTTP_PROJECT_ID);
        boolean updated = false;
        for (GetBatchListResponse.BatchInfoVo batch : response2.getData()) {
            if (YOUR_HTTP_BATCH_ID.equals(batch.getId()) && "Batch Description".equals(batch.getDescription())) {
                updated = true;
            }
        }
        Assert.assertTrue(updated);
    }

    @Test
    @Ignore
    public void bindVehiclesInBatch() {
        // unbind vehicles
        BatchUnbindVehiclesRequest unbindRequest = new BatchUnbindVehiclesRequest();
        unbindRequest.setProjectId(YOUR_HTTP_PROJECT_ID);
        unbindRequest.setVehicleIds(YOUR_HTTP_VEHICLE_IDS);
        client.unbindVehiclesInBatch(unbindRequest);

        // check status before binding
        QueryInstancesByBatchResponse response1 = client.queryInstanceByBatch(YOUR_HTTP_BATCH_ID, 1, 10);
        for (QueryInstancesByBatchResponse.InstanceInfo instance : response1.getInstanceInfos()) {
            if (YOUR_HTTP_VEHICLE_ID1.equals(instance.getVehicleId())) {
                Assert.assertTrue("0".equals(instance.getStatus()));
            }
            if (YOUR_HTTP_VEHICLE_ID2.equals(instance.getVehicleId())) {
                Assert.assertTrue("0".equals(instance.getStatus()));
            }
        }

        // bind vehicles
        BatchBindVehicleRequest request = new BatchBindVehicleRequest();
        request.setBatchId(YOUR_HTTP_BATCH_ID);
        request.setVehicleIds(YOUR_HTTP_VEHICLE_IDS);
        client.bindVehiclesInBatch(request);

        // check status after bind
        QueryInstancesByBatchResponse response2 = client.queryInstanceByBatch(YOUR_HTTP_BATCH_ID, 1, 10);
        for (QueryInstancesByBatchResponse.InstanceInfo instance : response2.getInstanceInfos()) {
            if (YOUR_HTTP_VEHICLE_ID1.equals(instance.getVehicleId())) {
                Assert.assertTrue("4".equals(instance.getStatus()));
            }
            if (YOUR_HTTP_VEHICLE_ID2.equals(instance.getVehicleId())) {
                Assert.assertTrue("4".equals(instance.getStatus()));
            }
        }
    }

    @Test
    @Ignore
    public void bindVehiclesInRandom() {
        // unbind vehicles
        BatchUnbindVehiclesRequest unbindRequest = new BatchUnbindVehiclesRequest();
        unbindRequest.setProjectId(YOUR_HTTP_PROJECT_ID);
        unbindRequest.setVehicleIds(YOUR_HTTP_VEHICLE_IDS);
        client.unbindVehiclesInBatch(unbindRequest);

        // check status before binding
        QueryInstancesByBatchResponse response1 = client.queryInstanceByBatch(YOUR_HTTP_BATCH_ID, 1, 10);
        for (QueryInstancesByBatchResponse.InstanceInfo instance : response1.getInstanceInfos()) {
            if (YOUR_HTTP_VEHICLE_ID1.equals(instance.getVehicleId())) {
                Assert.assertTrue("0".equals(instance.getStatus()));
            }
            if (YOUR_HTTP_VEHICLE_ID2.equals(instance.getVehicleId())) {
                Assert.assertTrue("0".equals(instance.getStatus()));
            }
        }

        // bind vehicles
        RandomBindVehiclesRequest request = new RandomBindVehiclesRequest();
        request.setProjectId(YOUR_HTTP_PROJECT_ID);
        request.setVehicleIds(YOUR_HTTP_VEHICLE_IDS);
        client.bindVehiclesInRandom(request);

        // check status after bind
        QueryInstancesByBatchResponse response2 = client.queryInstanceByBatch(YOUR_HTTP_BATCH_ID, 1, 10);
        for (QueryInstancesByBatchResponse.InstanceInfo instance : response2.getInstanceInfos()) {
            if (YOUR_HTTP_VEHICLE_ID1.equals(instance.getVehicleId())) {
                Assert.assertTrue("4".equals(instance.getStatus()));
            }
            if (YOUR_HTTP_VEHICLE_ID2.equals(instance.getVehicleId())) {
                Assert.assertTrue("4".equals(instance.getStatus()));
            }
        }
    }

    @Test
    @Ignore
    public void unbindVehiclesInBatch() {
        // check status before unbind
        QueryInstancesByBatchResponse response2 = client.queryInstanceByBatch(YOUR_HTTP_BATCH_ID, 1, 10);
        for (QueryInstancesByBatchResponse.InstanceInfo instance : response2.getInstanceInfos()) {
            if (YOUR_HTTP_VEHICLE_ID1.equals(instance.getVehicleId())) {
                Assert.assertTrue("4".equals(instance.getStatus()));
            }
            if (YOUR_HTTP_VEHICLE_ID2.equals(instance.getVehicleId())) {
                Assert.assertTrue("4".equals(instance.getStatus()));
            }
        }

        // unbind vehicles
        BatchUnbindVehiclesRequest unbindRequest = new BatchUnbindVehiclesRequest();
        unbindRequest.setProjectId(YOUR_HTTP_PROJECT_ID);
        unbindRequest.setVehicleIds(YOUR_HTTP_VEHICLE_IDS);
        client.unbindVehiclesInBatch(unbindRequest);

        // check status after unbinding
        QueryInstancesByBatchResponse response1 = client.queryInstanceByBatch(YOUR_HTTP_BATCH_ID, 1, 10);
        for (QueryInstancesByBatchResponse.InstanceInfo instance : response1.getInstanceInfos()) {
            if (YOUR_HTTP_VEHICLE_ID1.equals(instance.getVehicleId())) {
                Assert.assertTrue("0".equals(instance.getStatus()));
            }
            if (YOUR_HTTP_VEHICLE_ID2.equals(instance.getVehicleId())) {
                Assert.assertTrue("0".equals(instance.getStatus()));
            }
        }

        // bind vehicles
        RandomBindVehiclesRequest request = new RandomBindVehiclesRequest();
        request.setProjectId(YOUR_HTTP_PROJECT_ID);
        request.setVehicleIds(YOUR_HTTP_VEHICLE_IDS);
        client.bindVehiclesInRandom(request);
    }

    @Test
    public void queryBindResult() {
        QueryMqttBindResultResponse response = client.queryBindResult(YOUR_MQTT_PROJECT_ID);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData() != null && !response.getData().isEmpty());
    }

    @Test
    @Ignore
    public void downloadAuthInfo() {
        DownloadAuthInfoResponse response = client.downloadAuthInfo("YourDownloadUrl");
        Assert.assertNotNull(response);
        Assert.assertTrue(StringUtils.isNotEmpty(response.getDownloadUrl()));
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    public void batchAddDevice() {
        List<BatchAddDeviceRequest.DeviceBindInfo> deviceBindInfos = new ArrayList();
        deviceBindInfos.add(new BatchAddDeviceRequest.DeviceBindInfo(YOUR_PK, YOUR_DN, YOUR_SIGN));

        BatchAddDeviceRequest request = new BatchAddDeviceRequest(deviceBindInfos);
        client.batchAddDevice(request);
    }

    @Test
    public void batchRemoveDevice() {
        List<String> deviceIds = Arrays.asList(YOUR_DEVICE_ID);
        BatchRemoveDeviceRequest request = new BatchRemoveDeviceRequest(deviceIds);
        client.batchRemoveDevice(request);
    }

    @Test
    public void updateDeviceAlias() {
        UpdateAliasNameRequest request = new UpdateAliasNameRequest("alias");
        client.updateDeviceAlias(YOUR_DEVICE_ID, request);
    }

    @Test
    public void activateDevices() {
        ActivateDeviceRequest request = new ActivateDeviceRequest(Arrays.asList(YOUR_DEVICE_ID));
        client.activateDevices(request);
    }

    @Test
    public void queryDeviceInfo() {
        QueryDeviceResponse response = client.queryDeviceInfo(YOUR_GROUP_ID, YOUR_DEVICE_ID, "",
                YOUR_PK, YOUR_DN, 1, 2);
        System.out.println(response.toString());
    }

    @Test
    public void queryDeviceShadow() {
        DeviceShadowResponse response = client.queryDeviceShadow(YOUR_DEVICE_ID, true);
        System.out.println(response.getData());
        System.out.println(response.getLastUpdatedTime());
    }

    @Test
    public void queryGroupDeviceShadow() {
        GroupDeviceShadowResponse response = client.queryGroupDeviceShadow(YOUR_GROUP_ID, true, 1, 2);
        System.out.println(response.getAmount());
        System.out.println(response.getPageNo());
        System.out.println(response.getPageSize());
        for (GroupDeviceShadowResponse.DeviceShadow deviceShadow : response.getList()) {
            System.out.println(deviceShadow.getData());
            System.out.println(deviceShadow.getLastUpdatedTime());
        }
    }

    @Test
    public void queryDeviceHistory() {
        QueryDeviceHistoryRequest request = new QueryDeviceHistoryRequest();
        request.setDeviceId(YOUR_DEVICE_ID);
        request.setFields(Arrays.asList("acceleratez_2"));
        request.setStart(0L);
        request.setEnd(System.currentTimeMillis());
        QueryDeviceHistoryResponse response = client.queryDeviceHistory(request);
        System.out.println(response.getMarker());
        for (QueryDeviceHistoryResponse.IvcTsdbDataNode node : response.getDataList()) {
            System.out.println(node.getVehicleId());
            System.out.println(node.getTimestamp());
            System.out.println(node.getData());
        }
    }

    @Test
    public void uploadStaticData() {
        String staticValue = String.valueOf(new Random().nextInt(100000));
        Map<String, String> data = new HashMap<String, String>();
        data.put("static_filed1", "value1");
        data.put("static_filed2", staticValue);

        UploadStaticDataRequest request = new UploadStaticDataRequest();
        request.setVehicleId(YOUR_HTTP_VEHICLE_ID2);
        request.setData(data);
        client.uploadStaticData(request);

        QuerySingleShadowRequest shadowRequest = new QuerySingleShadowRequest();
        shadowRequest.setVehicleId(YOUR_HTTP_VEHICLE_ID2);
        shadowRequest.setNeedUpdateTime(false);
        shadowRequest.setFields(new ArrayList<String>());
        QuerySingleShadowResponse response = client.querySingleShadow(shadowRequest);

        Assert.assertTrue(response.getData().has("static_filed2"));
        Assert.assertEquals(staticValue, response.getData().get("static_filed2").asText());
    }

    @Test
    public void uploadDynamicData() {
        UploadDynamicDataRequest.PointData pointData = new UploadDynamicDataRequest.PointData();
        pointData.setCoordTypeInput("wgs84");
        pointData.setLocTime(System.currentTimeMillis());
        pointData.setVehicleId(YOUR_HTTP_VEHICLE_ID2);
        pointData.setLatitude(12.0000);
        pointData.setLongitude(12.0001);
        pointData.setDirection(90);
        pointData.setSpeed(48.977169);
        pointData.getData().put("dynamic_field1", "value1");
        pointData.getData().put("dynamic_field2", "value2");
        pointData.getData().put("dynamic_field3", 10000000);

        UploadDynamicDataRequest request = new UploadDynamicDataRequest();
        request.setPoints(Arrays.asList(pointData));

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(YOUR_ACCESS_KEY, YOUR_SECRET_KEY));
        config.setEndpoint(UPLOAD_ENDPOINT);
        DuGoClient adaptorClient = new DuGoClient(config);
        adaptorClient.uploadDynamicData(request);

        QuerySingleShadowRequest shadowRequest = new QuerySingleShadowRequest();
        shadowRequest.setVehicleId(YOUR_HTTP_VEHICLE_ID1);
        shadowRequest.setNeedUpdateTime(false);
        shadowRequest.setFields(new ArrayList<String>());
        QuerySingleShadowResponse response = client.querySingleShadow(shadowRequest);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(YOUR_HTTP_VEHICLE_ID1, response.getData().get("vehicleId").asText());
        Assert.assertTrue(response.getData().has("dynamic_field1"));
        Assert.assertTrue(response.getData().has("dynamic_field2"));
        Assert.assertTrue(response.getData().has("dynamic_field3"));
    }

    @Test
    public void queryVehicleOnlineStatus() {
        UploadDynamicDataRequest.PointData pointData = new UploadDynamicDataRequest.PointData();
        pointData.setCoordTypeInput("bd09ll");
        pointData.setLocTime(System.currentTimeMillis() / 1000);
        pointData.setVehicleId(YOUR_HTTP_VEHICLE_ID2);
        pointData.setLatitude(30.20);
        pointData.setLongitude(120.546);
        pointData.getData().put("dynamic_field1", "value1");
        pointData.getData().put("dynamic_field2", "value2");
        pointData.getData().put("dynamic_field3", 10000000);
        UploadDynamicDataRequest request = new UploadDynamicDataRequest();
        request.setPoints(Arrays.asList(pointData));

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(YOUR_ACCESS_KEY, YOUR_SECRET_KEY));
        config.setEndpoint(UPLOAD_ENDPOINT);
        DuGoClient adaptorClient = new DuGoClient(config);
        adaptorClient.uploadDynamicData(request);

        QueryVehicleStatusRequest statusRequest = new QueryVehicleStatusRequest();
        statusRequest.setVehicleIds(YOUR_HTTP_VEHICLE_IDS);
        QueryVehicleStatusResponse response = client.queryVehicleOnlineStatus(statusRequest);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData() != null && !response.getData().isEmpty());
        boolean found = false;
        for (QueryVehicleStatusResponse.Vehicle vehicle : response.getData()) {
            if (YOUR_HTTP_VEHICLE_ID2.equals(vehicle.getVehicleId())) {
                found = true;
                Assert.assertTrue("CONNECT".equals(vehicle.getStatus()));
            }
        }
        Assert.assertTrue(found);
    }

    @Test
    public void querySingleShadow() {
        QuerySingleShadowRequest request = new QuerySingleShadowRequest();
        request.setVehicleId(YOUR_HTTP_VEHICLE_ID2);
        request.setNeedUpdateTime(false);
        request.setFields(new ArrayList<String>());
        request.setNeedUpdateTime(true);

        QuerySingleShadowResponse response = client.querySingleShadow(request);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertEquals(YOUR_HTTP_VEHICLE_ID2, response.getData().get("vehicleId").asText());
        Assert.assertTrue(response.getData().has("static_filed1"));
        Assert.assertTrue(response.getData().has("static_filed2"));
        Assert.assertTrue(response.getData().has("dynamic_field1"));
        Assert.assertTrue(response.getData().has("dynamic_field2"));
        Assert.assertTrue(response.getData().has("dynamic_field3"));
    }

    @Test
    public void queryMultipleShadow() {
        QueryMultipleShadowRequest request = new QueryMultipleShadowRequest();
        request.setFields(new ArrayList<String>());
        request.setVehicleIds(YOUR_HTTP_VEHICLE_IDS);

        QueryMultipleShadowResponse response = client.queryMultipleShadow(request);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getData());
        Assert.assertFalse(response.getData().isEmpty());
        for (String vehicleId : YOUR_HTTP_VEHICLE_IDS) {
            boolean found = false;
            for (JsonNode jsonNode : response.getData()) {
                if (vehicleId.equals(jsonNode.get("vehicleId").asText())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void queryShadowByTag() {
        ShadowFilterRequest request = new ShadowFilterRequest();
        request.setPageNo(1);
        request.setPageSize(10);
        request.setProjectId(YOUR_HTTP_PROJECT_ID);
        request.setFields(Arrays.asList("dynamic_field1"));
        request.setTags(new HashMap<String, String>() {
            {
                put("static_filed1", "value1");
            }
        });

        ShadowFilterResponse response = client.queryShadowByTag(request);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData() != null && !response.getData().isEmpty());
    }

    @Test
    public void queryVehicleHistoryData() {
        HistoryInfoQueryRequest request = new HistoryInfoQueryRequest();
        request.setVehicleIds(YOUR_HTTP_VEHICLE_IDS);
        request.setStart(0);
        request.setEnd(System.currentTimeMillis());
        request.setFields(Arrays.asList("latitude"));

        HistoryInfoQueryResponse response = client.queryVehicleHistoryData(request);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getDataList() != null && !response.getDataList().isEmpty());
    }

    @Test
    public void queryDisplayName() {
        SchemaDisplayNameResponse response = client.queryDisplayName(YOUR_HTTP_PROJECT_ID, "dynamic_field1");
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertEquals("dynamic_field1", response.getAttributeName());
        Assert.assertEquals("动态属性1", response.getDisplayName());
    }

    @Test
    public void queryAttributeName() {
        SchemaAttributeNameResponse response = client.queryAttributeName(YOUR_HTTP_PROJECT_ID, "动态属性1");
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getAttributeNames() != null && !response.getAttributeNames().isEmpty());
        Assert.assertTrue(response.getAttributeNames().contains("dynamic_field1"));
        Assert.assertEquals("动态属性1", response.getDisplayName());
    }

    @Test
    public void queryTerminalParams() {
        GB32960ParamQueryRequest request = new GB32960ParamQueryRequest();
        request.setVin(YOUR_GB32960_VIN1);
        request.setIccid(YOUR_GB32960_ICCID1);
        request.setParamIds(Arrays.asList(1, 2));

        boolean notConnected = false;
        try {
            GB32960ParamQueryResponse response = client.queryTerminalParams(request);
        } catch (BceServiceException e) {
            if ("VehicleNotConnected".equals(e.getErrorCode())) {
                notConnected = true;
            }
        }
        Assert.assertTrue(notConnected);
    }

    @Test
    public void settingTerminalParams() {
        GB32960ParamSettingRequest request = new GB32960ParamSettingRequest();
        request.setVin(YOUR_GB32960_VIN1);
        request.setIccid(YOUR_GB32960_ICCID1);
        request.setParams(new HashMap<Integer, Object>() {
            {
                put(1, 1234);
            }
        });

        boolean notConnected = false;
        try {
            client.settingTerminalParams(request);
        } catch (BceServiceException e) {
            if ("VehicleNotConnected".equals(e.getErrorCode())) {
                notConnected = true;
            }
        }
        Assert.assertTrue(notConnected);
    }

    @Test
    public void controlVehicleTerminalRemoteUpgrade() {
        GB32960VehicleControlRequest.RemoteUpgradeCommand command =
                new GB32960VehicleControlRequest.RemoteUpgradeCommand();
        command.setDialPointName("dialPointName");
        command.setDialUsername("dialUserName");
        command.setDialPassword("dialPassword");
        command.setServerAddress("serverAddress");
        command.setServerPort(1024);
        command.setTerminalManufacturer("AAAA");
        command.setHardwareVersion("1.1.1");
        command.setFirmwareVersion("2.2.2");
        command.setUpgradeUrl("upgradeUrl");
        command.setConnectionTimeout(60000);

        GB32960VehicleControlRequest request = new GB32960VehicleControlRequest();
        request.setVin(YOUR_GB32960_VIN1);
        request.setIccid(YOUR_GB32960_ICCID1);
        request.setCommandId(1);
        request.setCommandParam(command);

        boolean notConnected = false;
        try {
            client.controlVehicleTerminal(request);
        } catch (BceServiceException e) {
            if ("VehicleNotConnected".equals(e.getErrorCode())) {
                notConnected = true;
            }
        }
        Assert.assertTrue(notConnected);
    }

    @Test
    public void controlVehicleTerminalAlarmControl() {
        GB32960VehicleControlRequest.AlarmCommand command = new GB32960VehicleControlRequest.AlarmCommand();
        command.setAlarmLevel(1);
        command.setAlarmInfo(123);

        GB32960VehicleControlRequest request = new GB32960VehicleControlRequest();
        request.setVin(YOUR_GB32960_VIN1);
        request.setIccid(YOUR_GB32960_ICCID1);
        request.setCommandId(6);
        request.setCommandParam(command);

        boolean notConnected = false;
        try {
            client.controlVehicleTerminal(request);
        } catch (BceServiceException e) {
            if ("VehicleNotConnected".equals(e.getErrorCode())) {
                notConnected = true;
            }
        }
        Assert.assertTrue(notConnected);
    }

    @Test
    public void controlVehicleTerminalOtherCommand() {
        GB32960VehicleControlRequest request = new GB32960VehicleControlRequest();
        request.setVin(YOUR_GB32960_VIN1);
        request.setIccid(YOUR_GB32960_ICCID1);
        request.setCommandId(2);

        boolean notConnected = false;
        try {
            client.controlVehicleTerminal(request);
        } catch (BceServiceException e) {
            if ("VehicleNotConnected".equals(e.getErrorCode())) {
                notConnected = true;
            }
        }
        Assert.assertTrue(notConnected);
    }

    @Test
    @Ignore
    public void createAlarmRule() {
        List<AlarmCreateRequest.SinkType> sinkTypes = Arrays.asList(
                new AlarmCreateRequest.SinkType(AlarmSinkType.KAFKA.getValue(), YOUR_KAFKA_TOPIC));

        AlarmCreateRequest request = new AlarmCreateRequest();
        request.setName("HighSpeedAlarm");
        request.setDes("超速告警");
        request.setBatchIds(Arrays.asList(YOUR_HTTP_BATCH_ID));
        request.setVehicleIds(Arrays.asList(YOUR_HTTP_VEHICLE_ID1));
        request.setAlarmPolicy(new AlarmCreateRequest.AlarmPolicy(sinkTypes, AlarmType.AT_ONCE.getAlarmType(), null));
        request.setAlarmRule(new AlarmCreateRequest.AlarmRule("dynamic_field3", AlarmRuleType.R1.getValue(), 100.0));
        client.createAlarmRule(YOUR_HTTP_PROJECT_ID, request);

        AlarmRuleListResponse response = client.getAlarmRuleList(YOUR_HTTP_PROJECT_ID, 1, 10);
        System.out.println(JsonUtils.toJsonString(response));
        boolean exist = false;
        for (AlarmRuleListResponse.AlarmDigest alarmDigest : response.getData()) {
            if ("HighSpeedAlarm".equals(alarmDigest.getName())) {
                exist = true;
            }
        }
        Assert.assertTrue(exist);
    }

    @Test
    public void updateAlarmRule() {
        AlarmUpdateRequest.SinkType sinkType =
                new AlarmUpdateRequest.SinkType(AlarmSinkType.KAFKA.getValue(), YOUR_KAFKA_TOPIC);

        AlarmUpdateRequest request = new AlarmUpdateRequest();
        request.setAlarmId(YOUR_ALARM_ID);
        request.setName("HighSpeedAlarm");
        request.setDes("新超速告警");
        request.setBatchIds(Arrays.asList(YOUR_HTTP_BATCH_ID));
        request.setAlarmPolicy(new AlarmUpdateRequest.AlarmPolicy(Arrays.asList(sinkType),
                AlarmType.AT_ONCE.getAlarmType(), null));
        request.setAlarmRule(new AlarmUpdateRequest.AlarmRule("dynamic_field3",
                AlarmRuleType.R1.getValue(), 200.0));
        client.updateAlarmRule(YOUR_HTTP_PROJECT_ID, request);

        AlarmRuleListResponse response = client.getAlarmRuleList(YOUR_HTTP_PROJECT_ID, 1, 10);
        System.out.println(JsonUtils.toJsonString(response));
        boolean exist = false;
        for (AlarmRuleListResponse.AlarmDigest alarmDigest : response.getData()) {
            if ("新超速告警".equals(alarmDigest.getDes())) {
                exist = true;
            }
        }
        Assert.assertTrue(exist);
    }

    @Test
    public void getAlarmRuleDetails() {
        AlarmDetailsResponse response = client.getAlarmRuleDetails(YOUR_HTTP_PROJECT_ID, YOUR_ALARM_ID);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertEquals(YOUR_ALARM_ID, response.getAlarmId());
    }

    @Test
    public void getAlarmRuleList() {
        AlarmRuleListResponse response = client.getAlarmRuleList(YOUR_HTTP_PROJECT_ID, 1, 10);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getData() != null && !response.getData().isEmpty());
    }

    @Test
    @Ignore
    public void batchDeleteAlarmRules() {
        AlarmBatchRequest request = new AlarmBatchRequest();
        request.setAlarmIds(Arrays.asList(YOUR_ALARM_ID));
        client.batchDeleteAlarmRules(YOUR_HTTP_PROJECT_ID, request);

        AlarmRuleListResponse response = client.getAlarmRuleList(YOUR_HTTP_PROJECT_ID, 1, 10);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        boolean exist = false;
        for (AlarmRuleListResponse.AlarmDigest alarmDigest : response.getData()) {
            if (YOUR_ALARM_ID.equals(alarmDigest.getAlarmId())) {
                exist = true;
            }
        }
        Assert.assertFalse(exist);
    }

    @Test
    @Ignore
    public void batchShieldAlarms() {
        AlarmBatchRequest request = new AlarmBatchRequest();
        request.setAlarmIds(Arrays.asList(YOUR_ALARM_ID));
        client.batchShieldAlarms(request, YOUR_HTTP_PROJECT_ID);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AlarmDetailsResponse response = client.getAlarmRuleDetails(YOUR_HTTP_PROJECT_ID, YOUR_ALARM_ID);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertEquals("STOPPED", response.getStatus());
    }

    @Test
    @Ignore
    public void batchRecoverAlarms() {
        AlarmBatchRequest request = new AlarmBatchRequest();
        request.setAlarmIds(Arrays.asList(YOUR_ALARM_ID));
        client.batchRecoverAlarms(request, YOUR_HTTP_PROJECT_ID);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AlarmDetailsResponse response = client.getAlarmRuleDetails(YOUR_HTTP_PROJECT_ID, YOUR_ALARM_ID);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertEquals("RUNNING", response.getStatus());
    }

    @Test
    public void geoCoding() {
        GeoCodingRequest request = new GeoCodingRequest();
        request.setAddress("北京市海淀区上地十街10号");

        GeoCodingResponse response = client.geoCoding(request);
        Assert.assertNotNull(response);
        Assert.assertTrue(Math.abs(40.05703033345938 - Double.valueOf(response.getLatitude())) < 1);
        Assert.assertTrue(Math.abs(116.3084202915042 - Double.valueOf(response.getLongitude())) < 1);
    }

    @Test
    public void geoDecoding() {
        GeoDecodingRequest request = new GeoDecodingRequest();
        request.setLatitude(40.05703033345938);
        request.setLongitude(116.3084202915042);

        GeoDecodingResponse response = client.geoDecoding(request);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getAddress().contains("海淀区"));
    }

    @Test
    public void getLatestPointTest() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(YOUR_ACCESS_KEY, YOUR_SECRET_KEY));
        config.setEndpoint(UPLOAD_ENDPOINT);
        DuGoClient adaptorClient = new DuGoClient(config);

        UploadDynamicDataRequest.PointData pointData = new UploadDynamicDataRequest.PointData();
        pointData.setCoordTypeInput("bd09ll");
        pointData.setLocTime(System.currentTimeMillis());
        pointData.setVehicleId(YOUR_HTTP_VEHICLE_ID1);
        pointData.getData().put("dynamic_field1", "value1");
        pointData.getData().put("dynamic_field2", "value2");
        pointData.getData().put("dynamic_field3", 1000000);

        for (int i = 0; i < 90; i++) {
            pointData.setLatitude(Double.valueOf(new Random().nextInt(90)));
            pointData.setLongitude(Double.valueOf(new Random().nextInt(90)));

            UploadDynamicDataRequest uploadRequest = new UploadDynamicDataRequest();
            uploadRequest.setPoints(Arrays.asList(pointData));

            adaptorClient.uploadDynamicData(uploadRequest);
        }

        GetLatestPointRequest request = new GetLatestPointRequest();
        request.setVehicleId(YOUR_HTTP_VEHICLE_ID1);
        GetLatestPointResponse response = client.getLatestPoint(request);
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    public void getTrack() {
        GetTrackRequest request = new GetTrackRequest();
        request.setVehicleId(YOUR_HTTP_VEHICLE_ID2);
        request.setStartTime(1550646450L);
        request.setEndTime(1550646453L);

        GetTrackResponse response = client.getTrack(request);
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    public void getDistance() {
        GetDistanceRequest request = new GetDistanceRequest();
        request.setVehicleId(YOUR_HTTP_VEHICLE_ID2);
        request.setStartTime(1550646450L);
        request.setEndTime(1550646453L);

        GetDistanceResponse response = client.getDistance(request);
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    public void stayPointTest() {
        StayPointRequest request = new StayPointRequest();
        request.setVehicleId(YOUR_HTTP_VEHICLE_ID2);
        request.setStartTime(1550646430L);
        request.setEndTime(1550646453L);

        StayPointResponse response = client.getStayPoint(request);
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    public void DrivingBehaviorTest() {
        DrivingBehaviorRequest request = new DrivingBehaviorRequest();
        request.setVehicleId(YOUR_HTTP_VEHICLE_ID2);
        request.setStartTime(1550646430L);
        request.setEndTime(1550646453L);

        DrivingBehaviorResponse response = client.getDrivingBehavior(request);
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    @Ignore
    public void createFence() {
        CreateFenceRequest.MonitoredObject monitoredObjectVehicle = new CreateFenceRequest.MonitoredObject(
                MonitoredObjectType.VEHICLE.getValue(), YOUR_HTTP_VEHICLE_ID1,
                FenceAlertCondition.IN.getAlertCondition());
        CreateFenceRequest.MonitoredObject monitoredObjectBatch = new CreateFenceRequest.MonitoredObject(
                MonitoredObjectType.BATCH.getValue(), YOUR_HTTP_BATCH_ID, FenceAlertCondition.IN.getAlertCondition());

        CreateFenceRequest request = new CreateFenceRequest();
        request.setProjectId(YOUR_HTTP_PROJECT_ID);
        request.setFenceName("FenceName");
        request.setFenceType(FenceType.CIRCLE.getFenceType());
        request.setFenceParamsOption(new CircleFenceOption(12.0, 12.0, 10.0));
        request.setCoordType(CoordType.bd09ll.getValue());
        request.setAlertType(AlertType.EVERY_TIME.getValue());
        request.setAlertSinkList(Arrays.asList(
                new CreateFenceRequest.AlertSink(AlarmSinkType.KAFKA.getValue(), YOUR_KAFKA_TOPIC)));
        request.setMonitoredObjectList(Arrays.asList(monitoredObjectVehicle, monitoredObjectBatch));

        CreateFenceResponse response = client.createFence(request);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertTrue(StringUtils.isNotEmpty(response.getFenceId()));
    }

    @Test
    public void updateFence() {
        UpdateFenceRequest request = new UpdateFenceRequest();
        request.setProjectId(YOUR_HTTP_PROJECT_ID);
        request.setFenceId(YOUR_FENCE_ID);
        request.setFenceName("FenceName");
        request.setCoordType(CoordType.bd09ll.getValue());
        request.setFenceParamsOption(new CircleFenceOption(12.0, 12.0, 10.0));
        client.updateFence(request);

        FenceDetailResponse response = client.getFenceDetail(YOUR_HTTP_PROJECT_ID, YOUR_FENCE_ID);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertEquals(CoordType.bd09ll.getValue(), response.getCoordType());
    }

    @Test
    public void getFenceDetail() {
        FenceDetailResponse response = client.getFenceDetail(YOUR_HTTP_PROJECT_ID, YOUR_FENCE_ID);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertTrue(StringUtils.isNotEmpty(response.getFenceId()));
        Assert.assertTrue(StringUtils.isNotEmpty(response.getFenceName()));
        Assert.assertTrue(StringUtils.isNotEmpty(response.getFenceType()));
        Assert.assertNotNull(response.getFenceParamsOption());
        Assert.assertTrue(StringUtils.isNotEmpty(response.getCoordType()));
        Assert.assertTrue(StringUtils.isNotEmpty(response.getAlertType()));
        Assert.assertTrue(response.getAlertSinkList() != null && !response.getAlertSinkList().isEmpty());
    }

    @Test
    @Ignore
    public void deleteFence() {
        client.deleteFence(YOUR_HTTP_PROJECT_ID, YOUR_FENCE_ID);

        FenceListResponse response = client.getFenceList(YOUR_HTTP_PROJECT_ID, null, null, 1, 10);
        System.out.println(JsonUtils.toJsonString(response));
        boolean exists = false;
        for (FenceListResponse.FenceInfoDigest fenceInfoDigest : response.getFenceInfoDigestList()) {
            if (YOUR_FENCE_ID.equals(fenceInfoDigest.getFenceId())) {
                exists = true;
            }
        }
        Assert.assertFalse(exists);
    }

    @Test
    public void getFenceList() {
        FenceListResponse response = client.getFenceList(YOUR_HTTP_PROJECT_ID, null, null, 1, 10);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertTrue(response.getTotal() >= 1);
        Assert.assertTrue(response.getFenceInfoDigestList() != null && !response.getFenceInfoDigestList().isEmpty());
    }

    @Test
    public void updateFenceAlarmConfig() {
        UpdateFenceAlarmRequest request = new UpdateFenceAlarmRequest();
        request.setProjectId(YOUR_HTTP_PROJECT_ID);
        request.setFenceId(YOUR_FENCE_ID);
        request.setAlertType(AlertType.EVERY_TIME.getValue());
        request.setAlertSinkList(Arrays.asList(
                new UpdateFenceAlarmRequest.AlertSink(AlarmSinkType.KAFKA.getValue(), YOUR_KAFKA_TOPIC)));
        client.updateFenceAlarmConfig(request);

        FenceDetailResponse response = client.getFenceDetail(YOUR_HTTP_PROJECT_ID, YOUR_FENCE_ID);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertEquals(AlertType.EVERY_TIME.getValue(), response.getAlertType());
    }

    @Test
    public void getFenceAlarms() {
        GetFenceAlarmsResponse response = client.getFenceAlarms(YOUR_HTTP_PROJECT_ID, YOUR_FENCE_ID,
                1, 100, null, null, null);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getMeta());
        Assert.assertNotNull(response.getAlarmPointList());
    }

    @Test
    @Ignore
    public void addMonitoredVehiclesToFence() {
        FenceMonitoredVehicleRequest request = new FenceMonitoredVehicleRequest();
        request.setProjectId(YOUR_HTTP_PROJECT_ID);
        request.setVehicleDigestList(Arrays.asList(
                new FenceMonitoredVehicleRequest.VehicleDigest(YOUR_HTTP_VEHICLE_ID1, "IN"),
                new FenceMonitoredVehicleRequest.VehicleDigest(YOUR_HTTP_VEHICLE_ID2, "OUT")));
        client.addMonitoredVehiclesToFence(YOUR_FENCE_ID, request);

        MonitoredVehicleListResponse response = client.getVehiclesInFence(YOUR_HTTP_PROJECT_ID, YOUR_FENCE_ID,
                1, 100, null);
        System.out.println(JsonUtils.toJsonString(response));
        boolean found1 = false;
        boolean found2 = false;
        for (MonitoredVehicleListResponse.VehicleDigest vehicleDigest : response.getVehicleDigestList()) {
            if (YOUR_HTTP_VEHICLE_ID1.equals(vehicleDigest.getVehicleId())) {
                found1 = true;
            }
            if (YOUR_HTTP_VEHICLE_ID2.equals(vehicleDigest.getVehicleId())) {
                found2 = true;
            }
        }
        Assert.assertTrue(found1);
        Assert.assertTrue(found2);
    }

    @Test
    public void updateMonitoredVehiclesForFence() {
        FenceMonitoredVehicleRequest request = new FenceMonitoredVehicleRequest();
        request.setProjectId(YOUR_HTTP_PROJECT_ID);
        request.setVehicleDigestList(Arrays.asList(
                new FenceMonitoredVehicleRequest.VehicleDigest(YOUR_HTTP_VEHICLE_ID1, "IN"),
                new FenceMonitoredVehicleRequest.VehicleDigest(YOUR_HTTP_VEHICLE_ID2, "OUT")));
        client.updateMonitoredVehiclesForFence(YOUR_FENCE_ID, request);

        MonitoredVehicleListResponse response = client.getVehiclesInFence(YOUR_HTTP_PROJECT_ID, YOUR_FENCE_ID,
                1, 100, null);
        System.out.println(JsonUtils.toJsonString(response));
        boolean found = false;
        for (MonitoredVehicleListResponse.VehicleDigest vehicleDigest : response.getVehicleDigestList()) {
            if (YOUR_HTTP_VEHICLE_ID2.equals(vehicleDigest.getVehicleId())) {
                found = true;
                Assert.assertEquals("OUT", vehicleDigest.getAlertCondition());
            }
        }
        Assert.assertTrue(found);
    }

    @Test
    @Ignore
    public void removeVehiclesFromFence() {
        DeleteMonitoredVehicleRequest request = new DeleteMonitoredVehicleRequest();
        request.setProjectId(YOUR_HTTP_PROJECT_ID);
        request.setVehicleIdList(Arrays.asList(YOUR_HTTP_VEHICLE_ID1));
        client.removeVehiclesFromFence(YOUR_FENCE_ID, request);

        MonitoredVehicleListResponse response = client.getVehiclesInFence(YOUR_HTTP_PROJECT_ID, YOUR_FENCE_ID,
                1, 100, null);
        System.out.println(JsonUtils.toJsonString(response));
        boolean exists = false;
        for (MonitoredVehicleListResponse.VehicleDigest vehicleDigest : response.getVehicleDigestList()) {
            if (YOUR_HTTP_VEHICLE_ID1.equals(vehicleDigest.getVehicleId())) {
                exists = true;
            }
        }
        Assert.assertFalse(exists);
    }

    @Test
    public void getVehiclesInFence() {
        MonitoredVehicleListResponse response = client.getVehiclesInFence(YOUR_HTTP_PROJECT_ID, YOUR_FENCE_ID,
                1, 100, null);
        System.out.println(JsonUtils.toJsonString(response));
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getTotal() >= 1);
        Assert.assertTrue(response.getVehicleDigestList() != null && !response.getVehicleDigestList().isEmpty());
    }

    private static final String YOUR_VIDEO_DEVICE_ID01 = "YOUR_VIDEO_DEVICE_ID01";
    private static final String YOUR_VIDEO_DEVICE_ID02 = "YOUR_VIDEO_DEVICE_ID02";
    private static final List<String> YOUR_VIDEO_DEVICE_IDS =
            Arrays.asList(YOUR_VIDEO_DEVICE_ID01, YOUR_VIDEO_DEVICE_ID02);
    private static final int YOUR_CHANNEL_ID = 1;
    private static final String YOUR_VIDEO_FILE_NAME = "YOUR_VIDEO_FILE_NAME";
    private static final String YOUR_TASK_UUID = "YOUR_TASK_UUID";
    private static final String YOUR_PRINCIPLE_PARAM_HEX = "YOUR_PRINCIPLE_PARAM_HEX";
    private static final String YOUR_ALARM_UUID = "YOUR_ALARM_UUID";
    private static final String YOUR_ALARM_REF_KEY = "YOUR_ALARM_REF_KEY";

    @Test
    public void testRealTimePlay() {
        RealTimeRequest request = new RealTimeRequest();
        GetPlayUrlResponse response = client.realTimePlay(YOUR_VIDEO_DEVICE_ID01, YOUR_CHANNEL_ID, request);
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    public void testEndRealTimePlay() {
        client.endRealTimePlay(YOUR_VIDEO_DEVICE_ID01, YOUR_CHANNEL_ID);
    }

    @Test
    public void testHistoryPlayback() {
        PlaybackRequest request = new PlaybackRequest();
        request.setFileName(YOUR_VIDEO_FILE_NAME);

        GetPlayUrlResponse response = client.historyPlayback(YOUR_VIDEO_DEVICE_ID01, request);
        Assert.assertNotNull(response.getAddress());
        System.out.println(response.getAddress());
    }

    @Test
    public void testEndHistoryPlayback() {
        client.endHistoryPlayback(YOUR_VIDEO_DEVICE_ID01, YOUR_VIDEO_FILE_NAME);
    }

    @Test
    public void testVideoUploadByName() {
        FileNameRequest request = new FileNameRequest();
        request.setFileName(YOUR_VIDEO_FILE_NAME);
        FileUploadResponse response = client.videoUploadByName(YOUR_VIDEO_DEVICE_ID01, request);
        Assert.assertNotNull(response.getTaskId());
        System.out.println(response.getTaskId());
    }

    @Test
    public void testVideoUploadCancel() {
        client.videoUploadCancel(YOUR_TASK_UUID);
    }

    @Test
    public void testGetTaskStatus() {
        GetTaskStatusResponse response = client.getTaskStatus(YOUR_TASK_UUID);
        Assert.assertNotNull(response.getStatus());
        System.out.println(response.getStatus());
    }

    @Test
    public void testGetPlayUrlByTask() {
        GetPlayUrlResponse response = client.getPlayUrlByTask(YOUR_TASK_UUID);
        System.out.println(response.getAddress());
    }

    @Test
    public void testGetPlayUrlByName() {
        FileNameRequest request = new FileNameRequest();
        request.setFileName(YOUR_VIDEO_FILE_NAME);
        GetPlayUrlResponse response = client.getPlayUrlByName(YOUR_VIDEO_DEVICE_ID01, request);
        System.out.println(response.getAddress());
    }

    @Test
    public void testParamSetting() {
        ParameterSettingRequest request = new ParameterSettingRequest();
        request.setParamHex(YOUR_PRINCIPLE_PARAM_HEX);
        client.setParam(YOUR_VIDEO_DEVICE_ID01, new ParameterSettingRequest());
    }

    @Test
    public void testGetVideoDownloadUrl() {
        FileNameRequest request = new FileNameRequest();
        request.setFileName(YOUR_VIDEO_FILE_NAME);

        GetDownloadUrlResponse response = client.getVideoDownloadUrl(YOUR_VIDEO_DEVICE_ID01, request);
        System.out.println(response.getAddress());
    }

    @Test
    public void testGetImageDownloadUrl() {
        FileNameRequest request = new FileNameRequest();
        request.setFileName(YOUR_VIDEO_FILE_NAME);

        GetDownloadUrlResponse response = client.getImageDownloadUrl(YOUR_VIDEO_DEVICE_ID01, request);
        System.out.println(response.getAddress());
    }

    @Test
    public void testGetUploadTaskList() {
        UploadTaskListRequest request = new UploadTaskListRequest();
        request.setVehicleId(YOUR_VIDEO_DEVICE_ID01);
        request.setStartTime(new Date());
        request.setEndTime(new Date());
        UploadTaskListResponse response = client.getUploadTaskList(1, 4, request);
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    public void testGetVideoInfoByTime() {
        MediaInfoByTimeRequest request = new MediaInfoByTimeRequest();
        request.setStartTime(1565770028000L);
        request.setEndTime(1565771028000L);
        GetMediaInfoListResponse response = client.getVideoInfoByTime(YOUR_VIDEO_DEVICE_ID01, request);
        System.out.println(JsonUtils.toJsonString(response));

        request.setChannelList(Lists.newArrayList(1, 2, 3));
        response = client.getVideoInfoByTime(YOUR_VIDEO_DEVICE_ID01, request);
        System.out.println(JsonUtils.toJsonString(response));

        request.setAlarmTypeList(Lists.newArrayList("SMOKING"));
        response = client.getVideoInfoByTime(YOUR_VIDEO_DEVICE_ID01, request);
        System.out.println(JsonUtils.toJsonString(response));

        request.setChannelList(Lists.<Integer>newArrayList());
        response = client.getVideoInfoByTime(YOUR_VIDEO_DEVICE_ID01, request);
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    public void testGetAlarmInfoByVehicleIdList() {
        AlarmInfoByVehicleIdListRequest request = new AlarmInfoByVehicleIdListRequest();
        request.setStartTime(1565770028000L);
        request.setEndTime(1565771028000L);
        request.setVehicleIdList(YOUR_VIDEO_DEVICE_IDS);
        AlarmInfoListResponse response = client.getAlarmInfoByVehicleIdList(request);
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    public void testGetAlarmInfoByTime() {
        AlarmInfoByTimeRequest request = new AlarmInfoByTimeRequest();
        request.setStartTime(1565770028000L);
        request.setEndTime(1565771028000L);
        AlarmInfoListResponse response = client.getAlarmInfoByTime(request);
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    public void testGetAlarmVideoInfoByVehicleId() {
        AlarmVideoInfoByVehicleIdRequest request = new AlarmVideoInfoByVehicleIdRequest();
        request.setStartTime(1565770028000L);
        request.setEndTime(1565771028000L);
        request.setVehicleId(YOUR_VIDEO_DEVICE_ID01);
        AlarmVideoInfoListResponse response = client.getAlarmVideoInfoByVehicleId(request);
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    public void testGetAlarmVideoInfoByVehicleIdList() {
        AlarmVideoInfoByVehicleIdListRequest request = new AlarmVideoInfoByVehicleIdListRequest();
        request.setStartTime(1565770028000L);
        request.setEndTime(1565771028000L);
        request.setVehicleIdList(YOUR_VIDEO_DEVICE_IDS);
        AlarmVideoInfoListResponse response = client.getAlarmVideoInfoByVehicleIdList(request);
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    public void testGetTrackAlarmMediaInfoListByVehicleId() {
        TrackAlarmMediaInfoRequest request = new TrackAlarmMediaInfoRequest();
        request.setStartTime(1565770028000L);
        request.setEndTime(1565771028000L);
        request.setVehicleId(YOUR_VIDEO_DEVICE_ID01);
        TrackAlarmVideoInfoResponse response = client.getTrackAlarmMediaInfoListByVehicleId(request);
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    public void testGetMediaInfoByFileName() {
        GetMediaInfoResponse response = client.getMediaInfoByFileName(YOUR_VIDEO_DEVICE_ID01, YOUR_VIDEO_FILE_NAME);
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    public void testGetMediaInfoByAlarmUuid() {
        GetMediaInfoListResponse response = client.getMediaInfoByAlarmUuid(YOUR_ALARM_UUID);
        System.out.println(JsonUtils.toJsonString(response));
    }

    @Test
    public void testGetMediaInfoByAlarmRefKey() {
        GetMediaInfoListResponse response =
                client.getMediaInfoByAlarmRefKey(YOUR_VIDEO_DEVICE_ID01, YOUR_ALARM_REF_KEY);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
