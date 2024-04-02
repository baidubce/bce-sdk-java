package com.baidubce.services.iotdmp;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iotdmp.model.CommonListRequest;
import com.baidubce.services.iotdmp.model.alarm.AlarmAction;
import com.baidubce.services.iotdmp.model.alarm.AlarmNotification;
import com.baidubce.services.iotdmp.model.alarm.AlarmRuleInfo;
import com.baidubce.services.iotdmp.model.alarm.BatchDeleteAlarmRuleRequest;
import com.baidubce.services.iotdmp.model.alarm.BatchProcessAlarmRecordRequest;
import com.baidubce.services.iotdmp.model.alarm.CreateAlarmRuleRequest;
import com.baidubce.services.iotdmp.model.alarm.ListAlarmRecordRequest;
import com.baidubce.services.iotdmp.model.alarm.ListAlarmRuleResponse;
import com.baidubce.services.iotdmp.model.alarm.TriggerAlarmRequest;
import com.baidubce.services.iotdmp.model.alarm.UpdateAlarmRuleRequest;
import com.baidubce.services.iotdmp.model.bie.app.AppResponse;
import com.baidubce.services.iotdmp.model.bie.app.ListNodeAppsResponse;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.*;
import com.baidubce.services.iotdmp.model.bie.node.CreateNodeRequest;
import com.baidubce.services.iotdmp.model.bie.node.DeployBusinessTemplatesRequest;
import com.baidubce.services.iotdmp.model.bie.node.GetInstallNodeInitResponse;
import com.baidubce.services.iotdmp.model.bie.node.GetInstallNodePropertyResponse;
import com.baidubce.services.iotdmp.model.bie.node.InstallMethod;
import com.baidubce.services.iotdmp.model.bie.node.InstallPlatform;
import com.baidubce.services.iotdmp.model.bie.node.ListNodeResponse;
import com.baidubce.services.iotdmp.model.bie.node.ModifyNodeRequest;
import com.baidubce.services.iotdmp.model.bie.node.NodeResponse;
import com.baidubce.services.iotdmp.model.bie.protocol.*;
import com.baidubce.services.iotdmp.model.c2c.ComputationSourceResponse;
import com.baidubce.services.iotdmp.model.component.BindComponentRequest;
import com.baidubce.services.iotdmp.model.component.ListBindComponentResponse;
import com.baidubce.services.iotdmp.model.device.AuthRequest;
import com.baidubce.services.iotdmp.model.device.AuthType;
import com.baidubce.services.iotdmp.model.device.BatchCreateDeviceRequest;
import com.baidubce.services.iotdmp.model.device.BatchDeleteDeviceRequest;
import com.baidubce.services.iotdmp.model.device.CreateDeviceRequest;
import com.baidubce.services.iotdmp.model.device.DeviceInfo;
import com.baidubce.services.iotdmp.model.device.DeviceKey;
import com.baidubce.services.iotdmp.model.device.DeviceResourcesConnectionInfo;
import com.baidubce.services.iotdmp.model.device.DeviceStateType;
import com.baidubce.services.iotdmp.model.device.DeviceType;
import com.baidubce.services.iotdmp.model.device.GetDeviceConnectionInfoRequest;
import com.baidubce.services.iotdmp.model.device.ListDeviceKeyRequest;
import com.baidubce.services.iotdmp.model.device.ListDeviceRequest;
import com.baidubce.services.iotdmp.model.device.ListDeviceResponse;
import com.baidubce.services.iotdmp.model.device.ListDeviceStatesResponse;
import com.baidubce.services.iotdmp.model.device.ResourceType;
import com.baidubce.services.iotdmp.model.device.UpdateDeviceRequest;
import com.baidubce.services.iotdmp.model.device.UpdateDeviceStateRequest;
import com.baidubce.services.iotdmp.model.device.batch.BatchDownloadMqtt;
import com.baidubce.services.iotdmp.model.device.batch.BatchInfoResponse;
import com.baidubce.services.iotdmp.model.device.batch.DeviceTuples;
import com.baidubce.services.iotdmp.model.device.batch.GetBatchPageResponse;
import com.baidubce.services.iotdmp.model.device.batch.GetBatchTuplesResponse;
import com.baidubce.services.iotdmp.model.device.evs.AddEvsDeviceRequest;
import com.baidubce.services.iotdmp.model.device.evs.EvsDeviceInfo;
import com.baidubce.services.iotdmp.model.device.evs.EvsDurationRequest;
import com.baidubce.services.iotdmp.model.device.evs.EvsProtocolRequest;
import com.baidubce.services.iotdmp.model.device.evs.EvsPtzRequest;
import com.baidubce.services.iotdmp.model.device.evs.EvsSpaceType;
import com.baidubce.services.iotdmp.model.device.evs.EvsUrlProtocol;
import com.baidubce.services.iotdmp.model.device.tag.CommonTagInfo;
import com.baidubce.services.iotdmp.model.device.tag.ListTagResponse;
import com.baidubce.services.iotdmp.model.device.topic.ListTopicResponse;
import com.baidubce.services.iotdmp.model.device.topo.DeviceSubsetsFileResponse;
import com.baidubce.services.iotdmp.model.fm.AddConfigRequest;
import com.baidubce.services.iotdmp.model.fm.AddTaskRequest;
import com.baidubce.services.iotdmp.model.fm.ConfigManagementListResponse;
import com.baidubce.services.iotdmp.model.fm.ConfigManagementResponse;
import com.baidubce.services.iotdmp.model.fm.ConfigTaskDetailListResponse;
import com.baidubce.services.iotdmp.model.fm.ConfigTaskListResponse;
import com.baidubce.services.iotdmp.model.fm.ConfigVersionListResponse;
import com.baidubce.services.iotdmp.model.group.CreateGroupRequest;
import com.baidubce.services.iotdmp.model.group.GroupInfo;
import com.baidubce.services.iotdmp.model.group.ListDeviceByGroupResponse;
import com.baidubce.services.iotdmp.model.group.ListGroupRequest;
import com.baidubce.services.iotdmp.model.group.ListGroupResponse;
import com.baidubce.services.iotdmp.model.group.UpdateGroupInfoRequest;
import com.baidubce.services.iotdmp.model.instance.CreateInstanceRequest;
import com.baidubce.services.iotdmp.model.instance.ExtensionResourceResponse;
import com.baidubce.services.iotdmp.model.instance.InstanceInfo;
import com.baidubce.services.iotdmp.model.instance.ListInstanceResourceResponse;
import com.baidubce.services.iotdmp.model.instance.ListInstancesResponse;
import com.baidubce.services.iotdmp.model.instance.ResourceSupplier;
import com.baidubce.services.iotdmp.model.instance.UpdateInstanceRequest;
import com.baidubce.services.iotdmp.model.instance.UpdateInstanceResourcePropertiesRequest;
import com.baidubce.services.iotdmp.model.linkage.ActionVo;
import com.baidubce.services.iotdmp.model.linkage.BatchDeleteLinkageRuleRequest;
import com.baidubce.services.iotdmp.model.linkage.CompareMode;
import com.baidubce.services.iotdmp.model.linkage.ConditionVo;
import com.baidubce.services.iotdmp.model.linkage.CreateLinkageRuleRequest;
import com.baidubce.services.iotdmp.model.linkage.CreateLinkageRuleResponse;
import com.baidubce.services.iotdmp.model.linkage.FeatureType;
import com.baidubce.services.iotdmp.model.linkage.LinkageRuleInfo;
import com.baidubce.services.iotdmp.model.linkage.ListLinkageRuleResponse;
import com.baidubce.services.iotdmp.model.linkage.Mode;
import com.baidubce.services.iotdmp.model.linkage.TriggerVo;
import com.baidubce.services.iotdmp.model.linkage.UpdateLinkageRuleRequest;
import com.baidubce.services.iotdmp.model.ota.CommonOtaListRequest;
import com.baidubce.services.iotdmp.model.ota.device.ListAllTestDeviceForTaskResponse;
import com.baidubce.services.iotdmp.model.ota.device.SearchDeviceForTaskResponse;
import com.baidubce.services.iotdmp.model.ota.device.SearchType;
import com.baidubce.services.iotdmp.model.ota.packages.CheckOtaPackageRequest;
import com.baidubce.services.iotdmp.model.ota.packages.CheckOtaPackageResponse;
import com.baidubce.services.iotdmp.model.ota.packages.ListOtaPackageRequest;
import com.baidubce.services.iotdmp.model.ota.packages.ListOtaPackageResponse;
import com.baidubce.services.iotdmp.model.ota.packages.OSStsResponse;
import com.baidubce.services.iotdmp.model.ota.packages.OtaPackage;
import com.baidubce.services.iotdmp.model.ota.packages.Type;
import com.baidubce.services.iotdmp.model.ota.packing.CancelOtaPackingResponse;
import com.baidubce.services.iotdmp.model.ota.packing.DeleteOtaPackingResponse;
import com.baidubce.services.iotdmp.model.ota.packing.GetOtaPackingStatusResponse;
import com.baidubce.services.iotdmp.model.ota.packing.ListOtaCompletedPackingResponse;
import com.baidubce.services.iotdmp.model.ota.packing.ListOtaUncompletedPackingResponse;
import com.baidubce.services.iotdmp.model.ota.packing.Packing;
import com.baidubce.services.iotdmp.model.ota.product.CreateOtaProductRequest;
import com.baidubce.services.iotdmp.model.ota.product.CreateOtaProductResponse;
import com.baidubce.services.iotdmp.model.ota.product.ListOtaProductOperationRequest;
import com.baidubce.services.iotdmp.model.ota.product.ListOtaProductOperationResponse;
import com.baidubce.services.iotdmp.model.ota.product.ListOtaProductRequest;
import com.baidubce.services.iotdmp.model.ota.product.ListOtaProductResponse;
import com.baidubce.services.iotdmp.model.ota.product.OtaProductConfig;
import com.baidubce.services.iotdmp.model.ota.product.OtaProductDetail;
import com.baidubce.services.iotdmp.model.ota.product.OtaProductOS;
import com.baidubce.services.iotdmp.model.ota.statistics.OtaTaskIssuedFailedStatisticsResponse;
import com.baidubce.services.iotdmp.model.ota.statistics.OtaTaskIssuedFailureInfoStatisticsResponse;
import com.baidubce.services.iotdmp.model.ota.statistics.OtaTaskIssuedStatisticsResponse;
import com.baidubce.services.iotdmp.model.ota.statistics.OtaTaskProductLineWeekStatisticsResponse;
import com.baidubce.services.iotdmp.model.ota.statistics.OtaTaskStageStatisticsResponse;
import com.baidubce.services.iotdmp.model.ota.statistics.OtaTaskStatisticsResponse;
import com.baidubce.services.iotdmp.model.ota.statistics.Stage;
import com.baidubce.services.iotdmp.model.ota.task.CreateOrUpdateGrayTaskRequest;
import com.baidubce.services.iotdmp.model.ota.task.CreateOtaTaskRequest;
import com.baidubce.services.iotdmp.model.ota.task.CreateOtaTaskResponse;
import com.baidubce.services.iotdmp.model.ota.task.DailySchedule;
import com.baidubce.services.iotdmp.model.ota.task.DeleteOtaTaskRequest;
import com.baidubce.services.iotdmp.model.ota.task.DeliveryConfig;
import com.baidubce.services.iotdmp.model.ota.task.GetOtaTaskResponse;
import com.baidubce.services.iotdmp.model.ota.task.GrayTask;
import com.baidubce.services.iotdmp.model.ota.task.ListOtaTaskRequest;
import com.baidubce.services.iotdmp.model.ota.task.ListOtaTaskResponse;
import com.baidubce.services.iotdmp.model.ota.task.Notify;
import com.baidubce.services.iotdmp.model.ota.task.Policy;
import com.baidubce.services.iotdmp.model.ota.task.TargetSet;
import com.baidubce.services.iotdmp.model.ota.task.UpdateOtaTaskRequest;
import com.baidubce.services.iotdmp.model.ota.task.UpdateOtaTaskResponse;
import com.baidubce.services.iotdmp.model.ota.task.UpdateOtaTaskStatusRequest;
import com.baidubce.services.iotdmp.model.ota.task.UpdateOtaTaskStatusResponse;
import com.baidubce.services.iotdmp.model.ota.task.UpgradeTiming;
import com.baidubce.services.iotdmp.model.platform.*;
import com.baidubce.services.iotdmp.model.product.*;
import com.baidubce.services.iotdmp.model.product.evs.CreateEvsSpaceRequest;
import com.baidubce.services.iotdmp.model.product.evs.EvsSpaceInfo;
import com.baidubce.services.iotdmp.model.product.evs.UpdateEvsSpaceRequest;
import com.baidubce.services.iotdmp.model.product.feature.DtmlDetailResponse;
import com.baidubce.services.iotdmp.model.product.feature.command.CreateFeatureCommandRequest;
import com.baidubce.services.iotdmp.model.product.feature.command.ListFeatureCommandResponse;
import com.baidubce.services.iotdmp.model.product.feature.command.ProductFeatureCommandInfo;
import com.baidubce.services.iotdmp.model.product.feature.command.UpdateProductCommandRequest;
import com.baidubce.services.iotdmp.model.product.feature.event.CreateFeatureEventRequest;
import com.baidubce.services.iotdmp.model.product.feature.event.ListFeatureEventResponse;
import com.baidubce.services.iotdmp.model.product.feature.event.ProductFeatureEventInfo;
import com.baidubce.services.iotdmp.model.product.feature.event.ProductFeatureEventType;
import com.baidubce.services.iotdmp.model.product.feature.event.UpdateProductEventRequest;
import com.baidubce.services.iotdmp.model.product.feature.property.CreateFeaturePropertyRequest;
import com.baidubce.services.iotdmp.model.product.feature.property.ListFeaturePropertyResponse;
import com.baidubce.services.iotdmp.model.product.feature.property.ProductFeaturePropertyInfo;
import com.baidubce.services.iotdmp.model.product.feature.property.UpdateProductPropertyRequest;
import com.baidubce.services.iotdmp.model.product.feature.thing.Property;
import com.baidubce.services.iotdmp.model.product.feature.thing.Thing;
import com.baidubce.services.iotdmp.model.product.repositories.*;
import com.baidubce.services.iotdmp.model.product.version.ListVersionResponse;
import com.baidubce.services.iotdmp.model.service.ConsumerGroupQueueInfoResponse;
import com.baidubce.services.iotdmp.model.service.ConsumerGroupUserInfoResponse;
import com.baidubce.services.iotdmp.model.service.CreateConsumerGroupResponse;
import com.baidubce.services.iotdmp.model.service.GetBridgeListResponse;
import com.baidubce.services.iotdmp.model.service.ListConsumerGroupResponse;
import com.baidubce.services.iotdmp.model.service.ListInstanceServiceStateResponse;
import com.baidubce.services.iotdmp.model.service.ListSubResponse;
import com.baidubce.services.iotdmp.model.service.ProductSubscriptionResponse;
import com.baidubce.services.iotdmp.model.service.ResetConsumerGroupUserPwdResponse;
import com.baidubce.services.iotdmp.model.service.SendMessageRequest;
import com.baidubce.services.iotdmp.model.service.ServiceInfoResponse;
import com.baidubce.services.iotdmp.model.service.ServiceResponse;
import com.baidubce.services.iotdmp.model.service.UpdateProductSubscriptionRequest;
import com.baidubce.services.iotdmp.model.service.rulechain.AvailableMessageTypeResponse;
import com.baidubce.services.iotdmp.model.service.rulechain.BlinkDataType;
import com.baidubce.services.iotdmp.model.service.rulechain.BlinkTopicInfo;
import com.baidubce.services.iotdmp.model.service.rulechain.TopicDecodeRequest;
import com.baidubce.services.iotdmp.model.service.rulechain.TopicDecodeResponse;
import com.baidubce.services.iotdmp.model.service.rulechain.TopicEncodeRequest;
import com.baidubce.services.iotdmp.model.service.rulechain.TopicEncodeResponse;
import com.baidubce.services.iotdmp.model.shadow.DeviceShadowResponse;
import com.baidubce.services.iotdmp.model.shadow.ListDeviceShadowRequest;
import com.baidubce.services.iotdmp.model.shadow.ListDeviceShadowSnapshotResponse;
import com.baidubce.services.iotdmp.model.shadow.UpdateDesiredRequest;
import com.baidubce.services.iotdmp.model.statistics.Cycle;
import com.baidubce.services.iotdmp.model.statistics.DeviceStatesStatsResult;
import com.baidubce.services.iotdmp.model.statistics.StatsDeviceMessageResponse;
import com.baidubce.services.iotdmp.model.statistics.StatsDeviceTotalResponse;
import com.baidubce.services.iotdmp.model.statistics.StatsLivelyDeviceResponse;
import com.baidubce.services.iotdmp.model.statistics.StatsProductTotalResponse;
import com.baidubce.services.iotdmp.model.tsdb.TsdbInitRequest;
import com.baidubce.services.iotdmp.model.tsdb.TsdbMappingRequest;
import com.baidubce.services.iotdmp.model.tsdb.TsdbMappingResponse;
import com.baidubce.services.iotdmp.model.tsdb.TsdbQueryRequest;
import com.baidubce.services.iotdmp.model.tsdb.TsdbQueryResponse;
import com.baidubce.services.iotdmp.model.userlog.ListUserLogRequest;
import com.baidubce.services.iotdmp.model.userlog.ListUserLogResponse;
import com.baidubce.util.JsonUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class PlatformClientTest {

    private static final String ENDPOINT = "http://180.76.236.1:8371";
    private static final String Your_AK = "mock";
    private static final String Your_SK = "mock";

    PlatformClient client;

    String instanceId = "mock_instanceId";
    String productKey = "mock_productKey";
    String deviceName = "mock_deviceName";
    String subProductKey = "mock_subProductKey";
    String subDeviceName = "mock_subDeviceName";
    String groupId = "mock_groupId";
    String groupName = "mock_groupName";
    String configName = "mock_configName";
    String configId = "mock_configId";
    String configVersion = "mock_configVersion";
    String filePath = "mock_filePath";
    String modelId = "mock_modelId";
    String ruleId = "mock_ruleId";
    String recordId = "mock_recordId";
    String bindName = "mock_bindName";
    String consumerGroupId = "mock_consumerGroupId";
    String rulechainId = "mock_rulechainId";
    String source = "mock_source";
    String destId = "mock_destId";
    String tagKey = "mock_tagKey";
    String tagValue = "mock_tagValue";
    String propertyName = "mock_propertyName";
    String commandName = "mock_commandName";
    String eventName = "mock_eventName";
    String ruleName = "mock_ruleName";
    String productName = "mock_productName";
    String displayName = "mock_displayName";
    String consumerName = "mock_consumerName";
    String description = "mock_description";
    String alias = "mock_alias";
    String channelId = "mock_channelId";
    String batchId = "mock_batchId";
    String protocolId = "mock_protocolId";
    String registryId = "mock_registryId";
    String nodeId = "mock_nodeId";
    String appId = "mock_appId";
    String edgeGatewayAppName = "mock_EdgeGatewayAppName";
    String edgeGatewayDriverName = "mock_EdgeGatewayDeriverName";
    String edgeGatewayDriverInstName = "mock_EdgeGatewayDeriverInstName";
    String version = "v10";

    @Before
    public void before() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(Your_AK, Your_SK))
                .withEndpoint(ENDPOINT);
        client = new PlatformClient(config);
    }

    // Instance API
    @Test
    @Ignore
    public void createInstance() {
        CreateInstanceRequest createInstanceRequest = new CreateInstanceRequest("InstanceFromSDK");
        InstanceInfo instance = client.createInstance(createInstanceRequest);
        Assert.assertEquals(instanceId, instance.getInstanceId());
    }

    @Test
    @Ignore
    public void updateInstance() {
        UpdateInstanceRequest updateInstanceRequest = new UpdateInstanceRequest();
        updateInstanceRequest.setDescription("description");
        InstanceInfo instance = client.updateInstance(instanceId, updateInstanceRequest);
        Assert.assertEquals(instanceId, instance.getInstanceId());
    }

    @Test
    @Ignore
    public void deleteInstance() {
        try {
            client.deleteInstance(instanceId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getInstance() {
        InstanceInfo instance = client.getInstance(instanceId);
        Assert.assertEquals(instanceId, instance.getInstanceId());
    }

    @Test
    @Ignore
    public void listInstances() {
        ListInstancesResponse response = client.listInstances(1, 2);
        Assert.assertEquals(1, response.getPageNo());
        Assert.assertEquals(2, response.getPageSize());
        if (response.getTotalCount() > 0) {
            assertTrue(!response.getResult().isEmpty());
        }
    }

    // Platform API
    @Test
    @Ignore
    public void createRuleChain() {
        CreateRuleChainRequest request = new CreateRuleChainRequest(ruleName, description);
        CreateRuleChainResponse response = client.createRuleChain(instanceId, request);
        Assert.assertNotNull(response);
    }

    @Test
    @Ignore
    public void deleteRuleChain() {
        HashSet<String> set = new HashSet<String>();
        set.add(rulechainId);
        BatchDeleteRuleChainRequest request = BatchDeleteRuleChainRequest.builder().rulechainIds(set).build();
        client.deleteRuleChain(instanceId, request);
    }

    @Test
    @Ignore
    public void updateRuleChain() {
        UpdatePlatformRuleChainRequest request = new UpdatePlatformRuleChainRequest(source);
        request.setDescription(description);
        RuleChainComputeInfo info = new RuleChainComputeInfo();
        info.setType("JSON_FILTER_MAP");
        request.setCompute(info);
        ArrayList<RuleChainDestinationInfo> list = new ArrayList<RuleChainDestinationInfo>();
        request.setErrorDestinations(list);
        request.setDestinations(list);
        try {
            client.updateRuleChain(instanceId, rulechainId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateRuleChainState() {

        UpdateRuleChainStateRequest request = new UpdateRuleChainStateRequest(RuleChainState.ENABLED);
        client.updateRuleChainState(instanceId, rulechainId, request);

        try {
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getRuleChain() {
        PlatformRuleChainInfo response = client.getRuleChain(instanceId, rulechainId);
        Assert.assertNotNull(response);
    }

    @Test
    @Ignore
    public void listRuleChain() {
        ListRuleChainRequest request = new ListRuleChainRequest();
        request.setName(ruleName);
        ListRuleChainResponse response = client.listRuleChain(instanceId, request);
        Assert.assertEquals(request.getName(), response.getName());
        Assert.assertEquals(1 , response.getTotalCount());

        Assert.assertEquals(request.getPageNo(), response.getPageNo());
        Assert.assertEquals(request.getPageSize(), response.getPageSize());
        if (response.getTotalCount() > 0) {
            assertTrue(!response.getResult().isEmpty());
        }
    }

    @Test
    @Ignore
    public void validateRuleChain() {
        ValidateRuleChainRequest request = new ValidateRuleChainRequest("","","");
        ValidateRuleChainResponse response = client.validateRuleChain(request);
        Assert.assertFalse(response.getFilter().isValid());
        Assert.assertFalse(response.getQuery().isValid());
        Assert.assertTrue(response.getMessage().isValid());
    }

    @Test
    @Ignore
    public void createRuleChainExternalDestination() {
        // kafka
        RuleChainExternalDestinationArgsInfo info = new RuleChainExternalDestinationArgsInfo(
                RuleChainExternalDestinationAuthType.CERT);
        UploadKafkaConfigFileResponse truststoreIdResponse = client
                .uploadKafkaConfigFile(new File("mock_path"));
        UploadKafkaConfigFileResponse keystoreIdResponse = client
                .uploadKafkaConfigFile(new File("mock_path"));
        info.setKeystoreId(keystoreIdResponse.getId());
        info.setKeystorePassword("mock_keystore_password");
        info.setTruststoreId(truststoreIdResponse.getId());
        info.setTruststorePassword("mock_trustsore_password");
        info.setAddress("mock_address");
        info.setTopic("mock_topic");
        CreateRuleChainExternalDestinationRequest request = new CreateRuleChainExternalDestinationRequest(
                RuleChainExternalDestinationType.EXTERNAL_KAFKA, info);

        CreateRuleChainExternalDestinationResponse response = client
                .createRuleChainExternalDestination(instanceId, request);
        client.validateRuleChainDestinationConnect(instanceId, response.getId());

        // HTTP
        info = new RuleChainExternalDestinationArgsInfo();
        info.setAddress("mock_address");
        info.setName("mock_name");
        request = new CreateRuleChainExternalDestinationRequest(RuleChainExternalDestinationType.EXTERNAL_HTTP, info);
        response = client
                .createRuleChainExternalDestination(instanceId, request);
        AuthRuleChainExternalDestinationResponse authResponse = client.authRuleChainExternalDestination(instanceId, response.getId(), RuleChainExternalDestinationType.EXTERNAL_HTTP);
    }

    @Test
    @Ignore
    public void createRuleChainExternalDestinationV2Test() {
        // HTTP
        ExternalHttpArgs externalHttpArgs = new ExternalHttpArgs();
        externalHttpArgs.setName("test");
        externalHttpArgs.setAddress("http://baidu.com");
        CreateRuleChainExternalDestinationRequestV2 request = new CreateRuleChainExternalDestinationRequestV2(externalHttpArgs);
        CreateRuleChainExternalDestinationResponse response = client.createRuleChainExternalDestination(instanceId, request);
        client.validateRuleChainDestinationConnect(instanceId, response.getId());
    }
    @Test
    @Ignore
    public void listRuleChainDestinations() {
        ListRuleChainDestinationRequest request = new ListRuleChainDestinationRequest("INTERNAL");
        ListRuleChainDestinationResponse response = client.listRuleChainDestinations(instanceId, request);
        Assert.assertEquals(request.getPageNo(), response.getPageNo());
        Assert.assertEquals(request.getPageSize(), response.getPageSize());
        if (response.getTotalCount() > 0) {
            assertTrue(!response.getResult().isEmpty());
        }
    }

    // C2C API
    @Test
    @Ignore
    public void getC2CSource() {
        ComputationSourceResponse c2CSource = client.getC2CSource(instanceId);
        Assert.assertEquals(5672, c2CSource.getPort());
    }

    @Test
    @Ignore
    public void updateC2CDownwardState() {
        try {
            client.updateC2CDownwardState(instanceId, true);
        } catch (Exception e) {
            fail();
        }
    }

    // Device API
    @Test
    @Ignore
    public void createDevice() {
        CreateDeviceRequest request = new CreateDeviceRequest(deviceName);
        DeviceInfo device = client.createDevice(instanceId, productKey, request);
        Assert.assertEquals(request.getDeviceName(), device.getDeviceName());
    }

    @Test
    @Ignore
    public void importCsvCreateDevice() {
        try {
            client.importCsvCreateDevice(instanceId, productKey,
                    new File(filePath));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteDevice() {
        try {
            client.deleteDevice(instanceId, productKey, deviceName);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void batchDeleteDevice() {
        try {
            ArrayList<DeviceKey> deviceKeys = new ArrayList<DeviceKey>();
            DeviceKey d1 = new DeviceKey(productKey, deviceName);
            deviceKeys.add(d1);
            BatchDeleteDeviceRequest request = new BatchDeleteDeviceRequest(deviceKeys);
            client.batchDeleteDevice(instanceId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getDevice() {
        DeviceInfo device = client.getDevice("pr6uzysvtg1329h5", "product", "cert");
        Assert.assertEquals(instanceId, device.getInstanceId());
        Assert.assertEquals(productKey, device.getProductKey());
        Assert.assertEquals(deviceName, device.getDeviceName());
    }

    @Test
    @Ignore
    public void getDeviceList() {
        ListDeviceRequest request = ListDeviceRequest.builder().pageSize(15).build();
        ListDeviceResponse response = client.getDeviceList(instanceId, request);
        Assert.assertEquals(request.getProductKey(), response.getProductKey());
        Assert.assertEquals(request.getPageNo(), response.getPageNo());
        Assert.assertEquals(request.getPageSize(), response.getPageSize());
        if (response.getTotalCount() > 0) {
            assertFalse(response.getResult().isEmpty());
        }

        request.setCursor(response.getNext());
        ListDeviceResponse response2 = client.getDeviceList(instanceId, request);
        Assert.assertEquals(response2.getTotalCount(), response.getTotalCount());
        Assert.assertNotEquals(response2.getResult(), response.getResult());
    }

    @Test
    @Ignore
    public void resetDeviceSecret() {
        try {
            client.resetDeviceSecret(instanceId, productKey, deviceName);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void batchCreateDevice() {
        ArrayList<CreateDeviceRequest> list = new ArrayList<CreateDeviceRequest>();
        CreateDeviceRequest sdkDev = new CreateDeviceRequest(deviceName);
        CommonTagInfo commonTagInfo = new CommonTagInfo(tagKey, tagValue);
        ArrayList<CommonTagInfo> commonTagInfos = new ArrayList<CommonTagInfo>();
        commonTagInfos.add(commonTagInfo);
        sdkDev.setTags(commonTagInfos);
        list.add(sdkDev);
        BatchCreateDeviceRequest request = new BatchCreateDeviceRequest(list);
        request.setIsRegisterIotCore(false);
        request.setDescription(description);
        try {
            client.batchCreateDevice(instanceId, productKey, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateDevice() {
        UpdateDeviceRequest request = new UpdateDeviceRequest();
        request.setAlias(alias);
        DeviceInfo deviceInfo = client.updateDevice(instanceId, productKey, deviceName, request);
        Assert.assertEquals(request.getAlias(), deviceInfo.getAlias());
    }

    @Test
    @Ignore
    public void getDeviceStates() {

        ArrayList<DeviceKey> deviceKeys = new ArrayList<DeviceKey>();
        DeviceKey deviceKey = new DeviceKey(productKey, deviceName);
        deviceKeys.add(deviceKey);
        ListDeviceKeyRequest request = new ListDeviceKeyRequest(deviceKeys);
        ListDeviceStatesResponse response = client.getDeviceStates(instanceId, request);
        Assert.assertEquals(1, response.getResult().size());
    }

    @Test
    @Ignore
    public void getDeviceTopic() {
        ListTopicResponse deviceTopic = client.getDeviceTopic(instanceId, productKey, deviceName);
        Assert.assertNotNull(deviceTopic);
    }

    @Test
    @Ignore
    public void createTag() {
        CommonTagInfo commonTagInfo = new CommonTagInfo();
        commonTagInfo.setKey(tagKey);
        commonTagInfo.setValue(tagValue);
        ArrayList<CommonTagInfo> commonTagInfos = new ArrayList<CommonTagInfo>();
        commonTagInfos.add(commonTagInfo);
        CreateTagRequest request = CreateTagRequest.builder().tags(commonTagInfos).build();
        ListTagResponse response = client.createDeviceTag(instanceId, productKey, deviceName, request);
        Assert.assertEquals(commonTagInfo.getKey(), response.getTags().get(0).getKey());
        Assert.assertEquals(commonTagInfo.getValue(), response.getTags().get(0).getValue());
    }

    @Test
    @Ignore
    public void deleteTag() {
        try {
            client.deleteDeviceTag(instanceId, productKey, deviceName, tagKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getTagList() {
        ListTagResponse response = client.getDeviceTagList(instanceId, productKey, deviceName);
        Assert.assertNotNull(response);
    }

    @Test
    @Ignore
    public void getEvs() {
        EvsDeviceInfo evs = client.getEvs(instanceId, productKey, deviceName);
        Assert.assertNotNull(evs);
    }

    @Test
    @Ignore
    public void createEvs() {
        AddEvsDeviceRequest request = new AddEvsDeviceRequest(EvsSpaceType.GB28181);
        try {
            client.createEvs(instanceId, productKey, deviceName, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEvsChannelUrl() {
        try {
            client.getEvsChannelUrl(instanceId, productKey,
                    deviceName, channelId,EvsUrlProtocol.flv);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEvsChannelPtz() {
        EvsPtzRequest request = new EvsPtzRequest(commandName);
        try {
            client.getEvsChannelPtz(instanceId, productKey, deviceName,
                    channelId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEvsChannel() {
        try {
            client.getEvsChannel(instanceId, productKey, deviceName);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEvsThumbnail() {
        EvsDurationRequest request = new EvsDurationRequest(1, 10);
        try {
            client.getEvsThumbnail(instanceId, productKey, deviceName, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEvsRecording() {
        EvsDurationRequest request = new EvsDurationRequest(1, 10);
       client.getEvsRecording(instanceId, productKey, deviceName, request);
    }

    @Test
    @Ignore
    public void auth() {
        AuthRequest request = new AuthRequest("mockSignature", 10L);
        client.auth(instanceId, productKey, deviceName, request);
    }

    @Test
    @Ignore
    public void getResourcesInfo() {
        GetDeviceConnectionInfoRequest request = new GetDeviceConnectionInfoRequest(ResourceType.MQTT);
        request.setAuthType(AuthType.SIGNATURE);
        DeviceResourcesConnectionInfo info = client.getResourcesInfo(instanceId, productKey, deviceName, request);
        DeviceResourcesConnectionInfo.Content content = info.getContent();
        Assert.assertEquals(ResourceType.MQTT, info.getResourceType());
        Assert.assertNotNull(content.getBroker());
        Assert.assertNotNull(content.getClientId());
        Assert.assertNotNull(content.getPort());
        Assert.assertNotNull(content.getUsername());
        Assert.assertNotNull(content.getPassword());

        request.setAuthType(AuthType.CERTIFICATE);
        info = client.getResourcesInfo(instanceId, productKey, deviceName, request);
        content = info.getContent();
        Assert.assertEquals(ResourceType.MQTT, info.getResourceType());
        Assert.assertNotNull(content.getBroker());
        Assert.assertNotNull(content.getClientId());
        Assert.assertNotNull(content.getPort());
        Assert.assertNotNull(content.getClientCert());
        Assert.assertNotNull(content.getPrivateKey());
    }

    @Test
    @Ignore
    public void updateStates() {
        UpdateDeviceStateRequest request = new UpdateDeviceStateRequest(DeviceStateType.ONLINE_STATE, false);
        try {
            client.updateDeviceStates(instanceId, productKey, deviceName, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deviceGetConfigExtensionResourcesTest() {
        ExtensionResourceResponse configExtensionResources = client
                .getConfigExtensionResources(instanceId, productKey, deviceName);
        Assert.assertNotNull(configExtensionResources);
    }

    @Test
    @Ignore
    public void deviceGetEnabledExtensionResourcesTest() {
        ExtensionResourceResponse enabledExtensionResources = client.getEnabledExtensionResources(instanceId, productKey, deviceName);
        Assert.assertNotNull(enabledExtensionResources);
    }

    // DeviceShadow
    @Test
    @Ignore
    public void getDeviceShadow() {
        ListDeviceShadowRequest request = new ListDeviceShadowRequest();
        DeviceShadowResponse response = client.getDeviceShadow(instanceId, productKey, deviceName, request);
        Assert.assertEquals(request.getPageNo(), response.getPageNo());
        Assert.assertEquals(request.getPageSize(), response.getPageSize());
        if (response.getTotalCount() > 0) {
            assertTrue(!response.getResult().isEmpty());
        }

        try {
            response = client.getDeviceShadow(instanceId, productKey, deviceName, request, version);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateDeviceShadowState() {
        try {
            client.updateDeviceShadowState(instanceId, productKey, true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateDesired() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(propertyName, "updateValue");
        map.put("test", "haha");
        UpdateDesiredRequest request = new UpdateDesiredRequest(map);
        try {
            client.updateDesired(instanceId, productKey, deviceName, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listShadow() {
        ArrayList<DeviceKey> listDeviceKey = new ArrayList<DeviceKey>();
        DeviceKey deviceKey = new DeviceKey(productKey, deviceName);
        listDeviceKey.add(deviceKey);
        ListDeviceKeyRequest request = ListDeviceKeyRequest.builder().devices(listDeviceKey).build();
        ListDeviceShadowSnapshotResponse response = client.listShadow(instanceId, request);
        Assert.assertTrue(response.getResult().size() > 0);
    }

    // Evs
    @Test
    @Ignore
    public void getEvsStream() {
        EvsProtocolRequest request = new EvsProtocolRequest(EvsUrlProtocol.hls);
        try {
            client.getEvsStream("bicutucg4mrvnacxw5g.b.e-web.com.cn", "product",
                    "product", EvsUrlProtocol.flv);
        } catch (Exception e) {
            fail();
        }

    }

    // Product
    @Test
    @Ignore
    public void createProduct() {
        ArrayList<ResourceType> extensions = new ArrayList<ResourceType>();
        List<ProductAccessType> accessTypes = new ArrayList<ProductAccessType>();
        accessTypes.add(ProductAccessType.HTTP);
        CreateProductInfoRequest request = CreateProductInfoRequest.builder().accessType(accessTypes)
                .productName(productName).deviceType(DeviceType.SUBDEVICE).build();
        ProductInfo product = client.createProduct(instanceId, request);
        Assert.assertEquals(instanceId, product.getInstanceId());
        Assert.assertEquals(request.getProductName(), product.getProductName());
    }

    @Test
    @Ignore
    public void resetSecret() {
        try {
            client.resetSecret(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    @Ignore
    public void updateProduct() {
        UpdateProductInfoRequest request = new UpdateProductInfoRequest();
        request.setProductName(productName);
        ProductInfo response = client.updateProduct(instanceId, productKey, request);
        Assert.assertEquals(request.getProductName(), response.getProductName());
        Assert.assertEquals(request.getExtensions(), response.getExtensions());
    }

    @Test
    @Ignore
    public void getProductList() {
        ListProductRequest request = new ListProductRequest();
        request.setDeviceType(DeviceType.COMPONENT);
        ListProductResponse response = client.getProductList(instanceId, request);
        Assert.assertEquals(request.getPageNo(), response.getPageNo());
        Assert.assertEquals(request.getPageSize(), response.getPageSize());
        if (response.getTotalCount() > 0) {
            assertFalse(response.getResult().isEmpty());
        }
    }

    @Test
    @Ignore
    public void getProduct() {
        ProductInfo response = client.getProduct(instanceId, productKey);
        Assert.assertEquals(instanceId, response.getInstanceId());
        Assert.assertEquals(productKey, response.getProductKey());
    }

    @Test
    @Ignore
    public void deleteProduct() {
        try {
            client.deleteProduct(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updatePermanentConnect() {
        try {
            client.updatePermanentConnect(instanceId, productKey, false);
        } catch (Exception e) {
            fail();
        }
    }

    // tag
    @Test
    @Ignore
    public void createProductTag() {
        CommonTagInfo commonTagInfo = new CommonTagInfo();
        ArrayList<CommonTagInfo> commonTagInfos = new ArrayList<CommonTagInfo>();
        commonTagInfo.setKey(tagKey);
        commonTagInfo.setValue(tagValue);
        commonTagInfos.add(commonTagInfo);
        CreateTagRequest request = new CreateTagRequest(commonTagInfos);
        ListTagResponse response = client.createProductTag(instanceId, productKey, request);
        Assert.assertEquals(commonTagInfos, response.getTags());
    }

    @Test
    @Ignore
    public void deleteProductTag() {
        try {
            client.deleteProductTag(instanceId, productKey, tagKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getProductTagList() {
        try {
            ListTagResponse response = client.getProductTagList(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }
    }

    // topic
    @Test
    @Ignore
    public void getTopics() {
        try {
            ListTopicResponse response = client.getTopics(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getDTMLDetail() {
        DtmlDetailResponse response;
        try {
            response = client.getDTMLDetail(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }

        try {
            response = client.getDTMLDetail(instanceId, productKey, version);
            System.out.println(response);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void importDTMLDetail() {
        try {
            Thing thing = new Thing();
            HashMap<String, Property> stringPropertyHashMap = new HashMap<String, Property>();
            HashMap<String, Object> dateSchema = new HashMap<String, Object>();
            dateSchema.put("@type", "boolean");
            Property property = new Property();
            property.setDescription(description);
            property.setDisplayName(displayName);
            property.setDataSchema(dateSchema);
            stringPropertyHashMap.put(propertyName, property);

            thing.setProperties(stringPropertyHashMap);
            thing.setDescription(description);
            client.importDTMLDetail(instanceId, productKey, thing);
        } catch (Exception e) {
            fail();
        }
    }

    // feature command
    @Test
    @Ignore
    public void createFeatureCommand() {
        CreateFeatureCommandRequest request = new CreateFeatureCommandRequest(commandName,
                displayName);
        ProductFeatureCommandInfo response = client.createFeatureCommand(instanceId, productKey, request);
        Assert.assertEquals(request.getName(), response.getName());
        Assert.assertEquals(request.getDisplayName(), response.getDisplayName());
    }

    @Test
    @Ignore
    public void updateFeatureCommand() {
        UpdateProductCommandRequest request = new UpdateProductCommandRequest();

        request.setDisplayName(displayName);
        ProductFeatureCommandInfo response = client.updateFeatureCommand(instanceId, productKey,
                commandName, request);
        Assert.assertEquals(displayName, response.getDisplayName());
    }

    @Test
    @Ignore
    public void deleteFeatureCommand() {
        try {
            client.deleteFeatureCommand(instanceId, productKey, commandName);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getFeatureCommand() {
        ProductFeatureCommandInfo response;
        try {
            response = client.getFeatureCommand(instanceId, productKey, commandName);
        } catch (Exception e) {
            fail();
        }

        try {
            response = client.getFeatureCommand(instanceId, productKey, commandName, version);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getFeatureCommandList() {
        ListFeatureCommandResponse response = client.getFeatureCommandList(instanceId, productKey, 1, 10);
        Assert.assertEquals(1, response.getPageNo());
        Assert.assertEquals(10, response.getPageSize());
        if (response.getTotalCount() > 0) {
            assertTrue(!response.getResult().isEmpty());
        }

        try {
            response = client.getFeatureCommandList(instanceId, productKey, 1, 10, version);
            System.out.println(response);
        } catch (Exception e) {
            fail();
        }
    }

    // feature event
    @Test
    @Ignore
    public void createFeatureEvent() {

        CreateFeatureEventRequest request = new CreateFeatureEventRequest(eventName,
                ProductFeatureEventType.InfoEvent, displayName);
        ProductFeatureEventInfo response = client.createFeatureEvent(instanceId, productKey, request);
        Assert.assertEquals(request.getName(), response.getName());
        Assert.assertEquals(request.getDisplayName(), response.getDisplayName());
    }

    @Test
    @Ignore
    public void updateFeatureEvent() {
        UpdateProductEventRequest request = new UpdateProductEventRequest();
        request.setDisplayName(displayName);
        ProductFeatureEventInfo response = client.updateFeatureEvent(instanceId, productKey,
                eventName, request);
        Assert.assertEquals(displayName, response.getDisplayName());
    }

    @Test
    @Ignore
    public void deleteFeatureEvent() {
        try {
            client.deleteFeatureEvent(instanceId, productKey, eventName);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getFeatureEvent() {

        ProductFeatureEventInfo response = client.getFeatureEvent(instanceId, productKey, eventName);
        Assert.assertEquals(eventName, response.getName());

        response = client.getFeatureEvent(instanceId, productKey, eventName, version);
        Assert.assertEquals(eventName, response.getName());
    }

    @Test
    @Ignore
    public void getFeatureEventList() {
        ListFeatureEventResponse response = client.getFeatureEventList(instanceId, productKey, 1, 10);
        Assert.assertEquals(1, response.getPageNo());
        Assert.assertEquals(10, response.getPageSize());
        if (response.getTotalCount() > 0) {
            assertTrue(!response.getResult().isEmpty());
        }

        try {
            response = client.getFeatureEventList(instanceId, productKey, 1, 10, version);
        } catch (Exception e) {
            fail();
        }
    }

    // feature property
    @Test
    @Ignore
    public void createFeatureProperty() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("@type", "boolean");
        CreateFeaturePropertyRequest request = new CreateFeaturePropertyRequest(propertyName, map,
                displayName);
        request.setDescription(null);
        ProductFeaturePropertyInfo response = client.createFeatureProperty(instanceId, productKey, request);
        Assert.assertEquals(request.getName(), response.getName());
        Assert.assertEquals(request.getDisplayName(), response.getDisplayName());
    }

    @Test
    @Ignore
    public void updateFeatureProperty() {
        UpdateProductPropertyRequest request = new UpdateProductPropertyRequest();
        request.setDisplayName("cba");
        ProductFeaturePropertyInfo response = client
                .updateFeatureProperty(instanceId, productKey, "abc", request);
        Assert.assertEquals(request.getDisplayName(), response.getDisplayName());
    }

    @Test
    @Ignore
    public void deleteFeatureProperty() {
        try {
            client.deleteFeatureProperty(instanceId, productKey, "abc");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getFeatureProperty() {
        ProductFeaturePropertyInfo response;
        try {
            response = client.getFeatureProperty(instanceId, productKey, propertyName);
        } catch (Exception e) {
            fail();
        }

        try {
            response = client.getFeatureProperty(instanceId, productKey, propertyName, version);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getFeaturePropertyList() {
        ListFeaturePropertyResponse response = client.getFeaturePropertyList(instanceId, productKey, 1, 10);
        Assert.assertEquals(1, response.getPageNo());
        Assert.assertEquals(10, response.getPageSize());
        if (response.getTotalCount() > 0) {
            assertTrue(!response.getResult().isEmpty());
        }

        try {
            response = client.getFeaturePropertyList(instanceId, productKey, 1, 10, version);
        } catch (Exception e) {
            fail();
        }
    }

    // video
    @Test
    @Ignore
    public void getProductEvs() {
        EvsSpaceInfo response = client.getEvs(instanceId, productKey);
        Assert.assertNotNull(response);
    }

    @Test
    @Ignore
    public void createProductEvs() {
        CreateEvsSpaceRequest request = new CreateEvsSpaceRequest(EvsSpaceType.GB28181);
        try {
            client.createEvs(instanceId, productKey, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateEvs() {
        UpdateEvsSpaceRequest request = new UpdateEvsSpaceRequest();

        client.updateEvs(instanceId, productKey, request);
    }

    @Test
    @Ignore
    public void getProductConfigExtensionResources() {
        try {
            ExtensionResourceResponse response = client.getConfigExtensionResources(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getProductEnabledExtensionResources() {
        try {
            ExtensionResourceResponse response = client.getEnabledExtensionResources(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }
    }

    // topo
    @Test
    @Ignore
    public void getProductSubsets() {
        ListProductResponse response = client
                .getSubsets(instanceId, productKey, 1, 100);
        Assert.assertNotNull(response.getResult());
    }

    @Test
    @Ignore
    public void getDeviceSubsets() {
        ListDeviceResponse response = client
                .getSubsets(instanceId, productKey,
                        deviceName, 1, 100, null);
        Assert.assertNotNull(response.getResult());
    }

    @Test
    @Ignore
    public void deleteProductSubsets() {
        ArrayList<String> listProductKey = new ArrayList<String>();
        listProductKey.add(subProductKey);
        try {
            client.deleteSubsets(instanceId, productKey, listProductKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteDeviceSubsets() {
        DeviceKey device1 = new DeviceKey(productKey, deviceName);
        ArrayList<DeviceKey> listDeviceKey = new ArrayList<DeviceKey>();
        listDeviceKey.add(device1);
        try {
            client.deleteSubsets(instanceId, productKey,
                    deviceName, listDeviceKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getAllProductSubsets() {

        ListProductResponse response = client
                .getAllSubsets(instanceId, productKey,
                        null,1, 10);
        Assert.assertNotNull(response.getResult());
    }

    @Test
    @Ignore
    public void getAllDeviceSubsets() {

        ListDeviceResponse response = client
                .getAllSubsets(instanceId, productKey,
                        productKey, deviceName,null,
                        1, 10);
        Assert.assertNotNull(response.getResult());
    }

    @Test
    @Ignore
    public void addProductSubsets() {
        ArrayList<String> listProductKey = new ArrayList<String>();
        listProductKey.add(subProductKey);
        try {
            client.addSubsets(instanceId, productKey, listProductKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void addDeviceSubsets() {
        DeviceKey device = new DeviceKey(subProductKey, subDeviceName);
        ArrayList<DeviceKey> listDeviceKey = new ArrayList<DeviceKey>();
        listDeviceKey.add(device);
        try {
            client.addSubsets(instanceId, productKey,
                    deviceName, listDeviceKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void importSubsetsTest() {
        try {
            client.importSubsets(instanceId, productKey,
                    deviceName, new File(filePath));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void exportSubsets() {
        DeviceSubsetsFileResponse response = client.exportSubsets(instanceId, productKey,
                deviceName);
        Assert.assertNotNull(response.getLeaf());
        Assert.assertNotNull(response.getLeaf());
    }

    // ruleChain API
    @Test
    @Ignore
    public void topicEncode() {
        TopicEncodeRequest request = TopicEncodeRequest.builder().productKey(productKey).deviceName(deviceName)
                .dataType(BlinkDataType.DEVICE_MESSAGE).subDataType(BlinkTopicInfo.PROPERTY_POST).build();
        TopicEncodeResponse response = client.topicEncode(request);
        Assert.assertEquals("thing/" + productKey + "/" + deviceName + "/property/post",
                response.getTopic());
    }

    @Test
    @Ignore
    public void topicDecode() {
        TopicDecodeRequest request = new TopicDecodeRequest("thing/mockKey/mockName/property/post");
        TopicDecodeResponse response = client.topicDecode(request);
        Assert.assertEquals("mockName", response.getDeviceName());
    }

    @Test
    @Ignore
    public void getSourceTypes() {
        try {
            AvailableMessageTypeResponse response = client.getSourceTypes(instanceId, null);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getSinkTypes() {
        try {
            AvailableMessageTypeResponse response = client.getSinkTypes(instanceId);
        } catch (Exception e) {
            fail();
        }
    }

    // service
    @Test
    @Ignore
    public void getUserInfo() {
        try {
            ConsumerGroupUserInfoResponse response = client.getUserInfo(instanceId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void sendMessage() {
        SendMessageRequest request = new SendMessageRequest("thing/" + productKey
                + "/" + deviceName + "/property/post",
               "test");
        try {
            client.sendMessage(instanceId, productKey, deviceName, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void createConsumerGroup() {
        CreateConsumerGroupResponse response = client.createConsumerGroup(instanceId, consumerName);
        Assert.assertNotNull(response);
    }

    @Test
    @Ignore
    public void deleteConsumerGroup() {
        try {
            client.deleteConsumerGroup(instanceId,
                    consumerGroupId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getConsumerGroupList() {
        CommonListRequest request = new CommonListRequest();
        ListConsumerGroupResponse response = client.getConsumerGroupList(instanceId, request);
        Assert.assertEquals(1, response.getPageNo());
        Assert.assertEquals(10, response.getPageSize());
        if (response.getTotalCount() > 0) {
            assertFalse(response.getResult().isEmpty());
        }
    }

    @Test
    @Ignore
    public void resetUserPwd() {
        String username = "";
        ResetConsumerGroupUserPwdResponse response = client.resetUserPwd(instanceId, username);
    }

    @Test
    @Ignore
    public void listInstanceServiceStateTest() {
        try {
            ListInstanceServiceStateResponse response = client.listInstanceServiceState(instanceId);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getQueueInfo() {
        try {
            ConsumerGroupQueueInfoResponse response = client.getQueueInfo(instanceId,
                    consumerGroupId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getSub() {
        try {
            ProductSubscriptionResponse response = client.getSub(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getSubTopics() {
        try {
            ProductSubscriptionResponse response = client.getSubTopics(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getSubList() {
        CommonListRequest request = new CommonListRequest();
        ListSubResponse response = client.getSubList(instanceId, request);
        Assert.assertEquals(1, response.getPageNo());
        Assert.assertEquals(10, response.getPageSize());
        if (response.getTotalCount() > 0) {
            assertTrue(!response.getResult().isEmpty());
        }
    }

    @Test
    @Ignore
    public void updateSub() {
        UpdateProductSubscriptionRequest request = new UpdateProductSubscriptionRequest();
        try {
            client.updateSub(instanceId, productKey, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateSubTopic() {
        UpdateProductSubscriptionRequest request = new UpdateProductSubscriptionRequest();
        HashSet<BlinkTopicInfo> topicSet = new HashSet<BlinkTopicInfo>();
        topicSet.add(BlinkTopicInfo.COMMAND_INVOKE);
        request.setTopics(topicSet);
        try {
            client.updateSubTopics(instanceId, productKey, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateSubState() {
        try {
            client.updateSubState(instanceId, productKey, true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getMessageType() {
        ProductSubscriptionResponse response = client.getMessageType(instanceId);
        Assert.assertNotNull(response);
    }

    // gateway
    @Test
    @Ignore
    public void getGatewayInfo() {
        ComputationSourceResponse response = client.getGatewayInfo(instanceId, productKey, deviceName);
        Assert.assertNotNull(response);
    }

    @Test
    @Ignore
    public void updateGatewayState() {
        try {
            client.updateGatewayState(instanceId, productKey, deviceName, true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void resetGatewaySecret() {
        try {
            client.resetGatewaySecret(instanceId, productKey, deviceName);
        } catch (Exception e) {
            fail();
        }
    }

    // userlog
    @Test
    @Ignore
    public void getLogList() {
        ListUserLogRequest request = new ListUserLogRequest("服务日志");
        ListUserLogResponse response = client.getLogList(instanceId, request);
        Assert.assertNotNull(response.getResult());

        request.setBeginTime(1637146772457L);
        request.setPageNo(2);
        request.setPageSize(3);
        response = client.getLogList(instanceId, request);
        Assert.assertNotNull(response.getResult());
    }

    // group
    @Test
    @Ignore
    public void createGroup() {
        CreateGroupRequest request = new CreateGroupRequest(groupName);
        request.setGroupDesc(description);
        GroupInfo response = client.createGroup(instanceId, request);
        Assert.assertEquals(request.getGroupName(), response.getGroupName());
    }

    @Test
    @Ignore
    public void getGroupList() {
        ListGroupRequest request = new ListGroupRequest();
        request.setKeyword(groupName);
        try {
            ListGroupResponse response = client.getGroupList(instanceId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteGroup() {
        try {
            client.deleteGroup(instanceId, groupId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateGroup() {
        UpdateGroupInfoRequest request = new UpdateGroupInfoRequest();
        request.setGroupName(groupName);
        GroupInfo groupInfo = client.updateGroup(instanceId, groupId, request);
        Assert.assertEquals(groupName, groupInfo.getGroupName());
    }

    @Test
    @Ignore
    public void getGroup() {
        GroupInfo groupInfo = client.getGroup(instanceId, groupId);
        Assert.assertEquals(groupId, groupInfo.getGroupId());
    }

    @Test
    @Ignore
    public void addDeviceToGroup() {
        DeviceKey device1 = new DeviceKey(productKey, deviceName);
        List<DeviceKey> deviceKeys = new ArrayList<DeviceKey>();
        deviceKeys.add(device1);
        try {
            client.addDeviceToGroup(instanceId, groupId, deviceKeys);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteDeviceFromGroup() {
        DeviceKey device1 = new DeviceKey(productKey, deviceName);
        List<DeviceKey> deviceKeys = new ArrayList<DeviceKey>();
        deviceKeys.add(device1);
        try {
            client.deleteDeviceFromGroup(instanceId, groupId, deviceKeys);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listDeviceByGroupTest() {
        try {
            ListDeviceByGroupResponse ret = client.listDeviceByGroup(instanceId, groupId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void bindProductComponents() {
        try {

            BindComponentRequest.BindInfo bindInfo1 = new BindComponentRequest.BindInfo();
            bindInfo1.setComponent("testzujian");
            bindInfo1.setBindName("component1");
            bindInfo1.setComponentVersion("v1");
            BindComponentRequest.BindInfo bindInfo2 = new BindComponentRequest.BindInfo();
            bindInfo2.setBindName("component2");
            bindInfo2.setComponent("testzujian");
            ArrayList<BindComponentRequest.BindInfo> bindInfos = new ArrayList<BindComponentRequest.BindInfo>();
            bindInfos.add(bindInfo1);
            bindInfos.add(bindInfo2);
            BindComponentRequest request = new BindComponentRequest(bindInfos);

            client.bindProductComponents(instanceId, productKey, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void unbindProductComponent() {
        try {
            client.unbindProductComponent(instanceId, productKey, bindName);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listProductComponents() {
        ListBindComponentResponse response = client.listProductComponents(instanceId, productKey);
        List<Map<String, Object>> result = response.getResult();
        Assert.assertNotNull(result);

        response = client.listProductComponents(instanceId, productKey, version);
        result = response.getResult();
        Assert.assertNotNull(result);
    }

    @Test
    @Ignore
    public void listDeviceComponents() {
        ListBindComponentResponse response = client
                .listDeviceComponents(instanceId, productKey, deviceName, null);
        List<Map<String, Object>> result = response.getResult();
        Assert.assertNotNull(result);

        response = client
                .listDeviceComponents(instanceId, productKey, deviceName, null, version);
        result = response.getResult();
        Assert.assertNotNull(result);
    }

    @Test
    @Ignore
    public void createLinkageRule() {
        TriggerVo triggerVo = TriggerVo.builder().productKey(productKey)
                .deviceName(deviceName)
                .mode(Mode.DURATION_WINDOW)
                .type(FeatureType.PROPERTY)
                .compareMode(CompareMode.EQ)
                .propName(propertyName)
                .opNum("1")
                .windowTime(10).build();
        ConditionVo conditionVo = ConditionVo.builder().productKey(productKey)
                .deviceName(deviceName)
                .mode(Mode.COMPARE)
                .type(FeatureType.PROPERTY)
                .compareMode(CompareMode.EQ)
                .propName(propertyName)
                .opNum("1").build();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(propertyName, "1");
        ActionVo actionVo = ActionVo.builder().mode(Mode.DEVICE).type(FeatureType.PROPERTY)
                .productKey(productKey).deviceName(deviceName).params(params).build();
        ArrayList<TriggerVo> triggerVos = new ArrayList<TriggerVo>();
        triggerVos.add(triggerVo);
        ArrayList<ConditionVo> conditionVos = new ArrayList<ConditionVo>();
        conditionVos.add(conditionVo);
        ArrayList<ActionVo> actionVos = new ArrayList<ActionVo>();
        actionVos.add(actionVo);
        CreateLinkageRuleRequest request = CreateLinkageRuleRequest.builder().name(ruleName)
                .trigger(triggerVos)
                .condition(conditionVos)
                .action(actionVos)
                .build();
        CreateLinkageRuleResponse response = client.createLinkageRule(instanceId, request);
        Assert.assertNotNull(response.getRuleId());
    }

    @Test
    @Ignore
    public void deleteLinkageRule() {
        HashSet<String> ruleIds = new HashSet<String>();
        ruleIds.add(ruleId);
        BatchDeleteLinkageRuleRequest request = BatchDeleteLinkageRuleRequest.builder()
                .ruleIds(ruleIds).build();
        try {
            client.deleteLinkageRule(instanceId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateLinkageRule() {
        UpdateLinkageRuleRequest request = UpdateLinkageRuleRequest.builder().name(ruleName).build();
        try {
            client.updateLinkageRule(instanceId, ruleId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getLinkageRule() {
        try {
            LinkageRuleInfo linkageRule = client.getLinkageRule(instanceId, ruleId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listLinkageRule() {
        ListLinkageRuleResponse response = client.listLinkageRule(instanceId, 1, 1, null);
        Assert.assertTrue(response.getResult().size() > 0);
    }

    @Test
    @Ignore
    public void updateLinkageRuleState() {
        try {
            client.updateLinkageRuleState(instanceId, ruleId, false);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listInstanceResources() {
        try {
            ListInstanceResourceResponse response = client.listInstanceResources(instanceId, ResourceSupplier.SYSTEM);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateResourceProperties() {
        try {
            UpdateInstanceResourcePropertiesRequest request = UpdateInstanceResourcePropertiesRequest.builder()
                    .resourceType(com.baidubce.services.iotdmp.model.instance.ResourceType.MQTT).build();
            client.updateResourceProperties(instanceId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void enableResource() {
        try {
            client.enableResource(instanceId, ResourceSupplier.SYSTEM,
                    com.baidubce.services.iotdmp.model.instance.ResourceType.LINKAGE);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void verifyResourceProperties() {
        try {
            HashMap<String, String> properties = new HashMap<String, String>();
            properties.put("test", "error");
            UpdateInstanceResourcePropertiesRequest request = UpdateInstanceResourcePropertiesRequest.builder()
                    .resourceType(com.baidubce.services.iotdmp.model.instance.ResourceType.RABBITMQ)
                    .properties(properties).build();
            client.verifyResourceProperties(instanceId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listProductCategoryTest() {
        try {
            ListProductCategoryResponse response = client.listProductCategory();
        } catch (Exception e) {
            fail();
        }
    }

    // product repositories
    @Test
    @Ignore
    public void listProductModelTest() {
        ListProductModelRequest request = ListProductModelRequest.builder().productName(productName)
                .pageNo(1).pageSize(10).build();
        ListProductModelResponse response = client.listProductModel(request);
        assertEquals(1, response.getTotalCount());
    }

    @Test
    @Ignore
    public void getMainProductInfoTest() {
        try {
            ProductModelInfo response = client.getMainProductInfo(modelId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getMainProductComponentsTest() {
        ListBindComponentResponse response = client.getMainProductComponents(modelId);
        try {
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getMainProductPropertiesTest() {
        try {
            ListFeaturePropertyResponse response = client.getMainProductProperties(modelId, 1, 10);
            response.getResult().get(0).getName();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getMainProductEventsTest() {
        try {
            ListFeatureEventResponse response = client.getMainProductEvents(modelId, 1, 10);
            response.getResult().get(0).getName();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getMainProductCommandsTest() {
        try {
            ListFeatureCommandResponse response = client.getMainProductCommands(modelId, 1, 10);
            response.getResult().get(0).getName();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getMainProductDTMLDetailTest() {
        try {
            DtmlDetailResponse response = client.getMainProductDTMLDetail(modelId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void saveCustomProductModelTest() {
        try {
            SaveCustomProductModelRequest request = new SaveCustomProductModelRequest();
            request.setDescription("sdk");
            SaveCustomProductModelResponse response = client.saveCustomProductModel(instanceId, productKey, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteCustomProductModel() {
        try {
            client.deleteCustomProductModel(modelId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateCustomProductModelTest() {
        try {
            UpdateCustomProductModelRequest request = new UpdateCustomProductModelRequest();
            request.setDescription(description);

            SimpleProductModelInfo response = client.updateCustomProductModel(modelId, request);
        } catch (Exception e) {
            fail();
        }
    }
    // product models
    @Test
    @Ignore
    public void importProductModelTest() {
        try {
            CreateProductByModelRequest request = new CreateProductByModelRequest();
            request.setDescription("from sdk");
            ArrayList<ProductAccessType> accessTypes = new ArrayList<ProductAccessType>();
            accessTypes.add(ProductAccessType.WIFI);
            request.setAccessType(accessTypes);
            request.setProductKey("sdkProductKey");
            request.setProductName("sdkProductName");
            request.setDeviceType(DeviceType.DIRECT);
            client.importProductModel(instanceId, modelId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void exportProductModelTest() {
        try {
            ProductModelInfo info = client.exportProductModel(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void createAlarmRuleTest() {
        try {
            HashSet<AlarmNotification> alarmNotifications = new HashSet<AlarmNotification>();
            alarmNotifications.add(AlarmNotification.ALARM_CENTRE);
            CreateAlarmRuleRequest request = new CreateAlarmRuleRequest(ruleName, 4, alarmNotifications, "${a, w}");
            AlarmRuleInfo response = client.createAlarmRule(instanceId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateAlarmRuleTest() {
        try {
            HashSet<AlarmNotification> alarmNotifications = new HashSet<AlarmNotification>();
            alarmNotifications.add(AlarmNotification.ALARM_CENTRE);
            UpdateAlarmRuleRequest request = UpdateAlarmRuleRequest.builder().alarmLevel(2).build();
            AlarmRuleInfo response = client.updateAlarmRule(instanceId, ruleId, request);
            Assert.assertEquals(2, response.getAlarmLevel());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getAlarmRuleTest() {
        try {
            AlarmRuleInfo response = client.getAlarmRule(instanceId, ruleId);
            Assert.assertEquals(ruleId, response.getRuleId());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listAlarmRuleTest() {
        try {
            ListAlarmRuleResponse response = client.listAlarmRule(instanceId, ruleName, 1, 4);
            assertEquals(1, response.getTotalCount());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateAlarmRuleActiveStateTest() {
        try {
            client.updateAlarmRuleActiveState(instanceId, ruleId, false);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void triggerAlarmRuleTest() {
        try {
            HashMap<String, Object> alarmContent = new HashMap<String, Object>();
            alarmContent.put("warn", "biubiubiu");
            TriggerAlarmRequest request = TriggerAlarmRequest.builder().alarmContent(alarmContent).build();
            client.triggerAlarmRule(instanceId, ruleId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void batchDeleteAlarmRuleTest() {
        try {
            HashSet<String> rules = new HashSet<String>();
            rules.add(ruleId);
            BatchDeleteAlarmRuleRequest request = BatchDeleteAlarmRuleRequest.builder().rules(rules).build();
            client.batchDeleteAlarmRule(instanceId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteAlarmRecordTest() {
        try {
            client.deleteAlarmRecord(instanceId, recordId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void batchProcessAlarmRecordTest() {
        try {
            HashSet<String> records = new HashSet<String>();
            records.add(recordId);
            BatchProcessAlarmRecordRequest request = BatchProcessAlarmRecordRequest.builder().action(AlarmAction.IGNORE).records(records).ownerName("super").build();
            client.batchProcessAlarmRecord(instanceId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getAlarmRecordTest() {
        try {
            client.getAlarmRecord(instanceId, recordId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listAlarmRecordTest() {
        try {
            ListAlarmRecordRequest request = ListAlarmRecordRequest.builder().name(ruleName).build();
            client.listAlarmRecord(instanceId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getConfigListTest() {

        ConfigManagementListResponse ret = client
                .getConfigList(instanceId, null, null, 1, 10);
        Assert.assertNotNull(ret);
        Assert.assertEquals("test", ret.getResult().get(0).getConfigName());
    }

    @Test
    @Ignore
    public void addConfigTest() {
        AddConfigRequest request = AddConfigRequest.builder()
                .configName(configName).productKey(productKey).description(description).build();
        try {
            client.addConfig(instanceId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteConfigTest() {
        try {
            client.deleteConfig(instanceId, productKey, configId);
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    @Ignore
    public void modifyConfigTest() {
        AddConfigRequest request = AddConfigRequest.builder()
                .configName(configName).productKey(productKey).description(description).build();
        try {
            client.modifyConfig(instanceId, productKey, configId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void addConfigVersionTest() {
        client.addConfigVersion(instanceId, productKey, configId,
                configVersion, new File(filePath));

    }

    @Test
    @Ignore
    public void deleteConfigVersionTest() {
        try {
            client.deleteConfigVersion(instanceId, productKey, configId, configVersion);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void downloadConfigVersionTest() {
        try {
            client.deleteConfigVersion(instanceId, productKey, configId, configVersion);
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    @Ignore
    public void getConfigVersionListTest() {

        ConfigVersionListResponse response = client
                .getConfigVersionList(instanceId, productKey, configId);
        Assert.assertNotNull(response);
    }

    @Test
    @Ignore
    public void getConfigInfoTest() {
        ConfigManagementResponse response = client
                .getConfigInfo(instanceId, productKey, configId);
        Assert.assertNotNull(response);
    }

    @Test
    @Ignore
    public void getConfigTaskListTest() {
        ConfigTaskListResponse response = client
                .getConfigTaskList(instanceId, productKey,
                        configId, 1, 10, null);
        Assert.assertNotNull(response);
        Assert.assertEquals("SINGLE", response.getResult().get(0).getTaskType());
    }

    @Test
    @Ignore
    public void addConfigTaskTest() {
        AddTaskRequest request = AddTaskRequest.builder().configVersion(configVersion).deviceName(deviceName).build();
        try {
            client.addConfigTask(instanceId, productKey,
                    configId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getConfigTaskDetailTest() {
        ConfigTaskDetailListResponse response = client
                .getConfigTaskDetail(instanceId, productKey,
                        configId, 1, 10, null);
        Assert.assertNotNull(response);
        Assert.assertEquals("1.0", response.getResult().get(0).getConfigVersion());
    }

    @Test
    @Ignore
    public void addConfigTaskCsvTest() {
        try {
            client.addConfigTaskCsv(instanceId, productKey,
                    configId, configVersion, new File(filePath));
        } catch (Exception e) {
            fail();
        }
    }

    // birdge
    @Test
    @Ignore
    public void getBridgeListTest() {
        GetBridgeListResponse response = client.getBridgeList("m2zyfza3xj35n17b");
        List<ServiceResponse> result = response.getResult();
        Assert.assertNotNull(response);
        for (ServiceResponse serviceResponse : result) {
            Assert.assertNotNull(serviceResponse.getServiceId());
        }
    }

    @Test
    @Ignore
    public void getBridgeTest() {
        try {
        ServiceInfoResponse bridge = client.getBridge(instanceId, "MQTT-0");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateBridgeTest() {
        try {
        client.updateBridgeState(instanceId, "MQTT-0", true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void bridgeResetTest() {
        try {
        client.bridgeReset(instanceId, "MQTT-0");
        } catch (Exception e) {
            fail();
        }
    }

    // ota product
    @Test
    @Ignore
    public void createOtaProductTest() {
        try {
            CreateOtaProductRequest request = CreateOtaProductRequest.builder().os(OtaProductOS.Linux).build();
            CreateOtaProductResponse response = client.createOtaProduct(instanceId, productKey, request);
            Assert.assertNotNull(response.getId());
            Assert.assertNotNull(response.getPid());
            Assert.assertNotNull(response.getProductSecret());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteOtaProduct() {
        try {
         client.deleteOtaProduct(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getOtaProductDetail() {
        try {
            OtaProductDetail otaProductDetail = client.getOtaProductDetail(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listOtaProductTest() {
        try {
            ListOtaProductRequest request = ListOtaProductRequest.builder().build();
            ListOtaProductResponse response = client.listOtaProduct(instanceId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listOtaProductOperation() {
        try {
            ListOtaProductOperationRequest request = ListOtaProductOperationRequest.builder().page(1).perPage(1).build();
            ListOtaProductOperationResponse response = client.listOtaProductOperation(instanceId, productKey, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getOtaProductConfig() {
        try {
            OtaProductConfig response = client.getOtaProductConfig(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }
    }

    // ota packages
    @Test
    @Ignore
    public void listOtaPackageTest() {
        try {
            ListOtaPackageRequest request = ListOtaPackageRequest.builder().orderBy(ListOtaPackageRequest.OrderBy.id)
                    .orderDirection(ListOtaPackageRequest.OrderDirection.asc).build();
            ListOtaPackageResponse response = client.listOtaPackage(instanceId,
                    productKey, request);
            List<ListOtaPackageResponse.OtaPackageData> packages = response.getPackages();
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteOtaPackageTest() {
        try {
            client.deleteOtaPackage(instanceId,
                productKey, 62);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void checkOtaPackageTest() {
        try {
            CheckOtaPackageRequest request = CheckOtaPackageRequest.builder().showName("test.zip").build();
            CheckOtaPackageResponse response = client.checkOtaPackage(instanceId,
                    productKey, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void stsOtaPackageTest() {
        try {
            OSStsResponse response = client.stsOtaPackage(instanceId,
                    productKey, "mockfile.zip", 1665563351L, Type.ota);
        } catch (Exception e) {
            fail();
        }
    }

    // ota task
    @Test
    @Ignore
    public void addOtaTaskTest() {
        try {

            ListOtaPackageResponse listOtaPackageResponse = client.listOtaPackage(instanceId, productKey, new ListOtaPackageRequest());
            List<ListOtaPackageResponse.OtaPackageData> packages = listOtaPackageResponse.getPackages();
            // Basic
            CreateOtaTaskRequest.Basic basic = CreateOtaTaskRequest.Basic.builder().priority(1)
                    .type(CreateOtaTaskRequest.Basic.Type.online).comment("test comment").tname("test name").build();

            // package_data
            ArrayList<OtaPackage> list = new ArrayList<OtaPackage>();
            OtaPackage otaPackage = OtaPackage.builder().id(180).isDiffPack(true)
                    .url("http://11.11.11.11/xbu-ota-packages/bota-make/90d278188d519cb0ed407f78766285fa1b9ddc6c/14f61328-6f3d-41e8-9363-a47e967d31d9.bdota")
                    .md5("e3aadcd047d3081e8622e19f1e3747da").sha1("90d278188d519cb0ed407f78766285fa1b9ddc6c").insertTime("2022-11-10 14:48:41")
                    .srcSha1("e3991947b96801adc5ef190f794779569834ec1e").fileSource("platform").uploadUser("superuser").srcVersion("1.0.0.0").version("2.2.2.2")
                    .filename("安卓应用差分包").filetype(2).filesize(1421).packageType("app")
                    .showName("安卓应用差分包").packageName("xxxxxs.ss").isEncrypted(false).updatetype(0).label(Collections.<String>emptyList()).build();
            list.add(otaPackage);
            CreateOtaTaskRequest.PackageData packageData = CreateOtaTaskRequest.PackageData.builder().packageType("app").upgradeType("diff")
                    .list(list).build();

            // policy
            Notify notify = Notify.builder().forceUpgrade(false).releaseNote("success").silenceUpdateType(0).build();
            UpgradeTiming upgradeTiming = UpgradeTiming.builder().effectiveTime("2022-11-04 15:26:34").isDelay(true).build();
            DailySchedule dailySchedule = DailySchedule.builder().startTime("01:00").stopTime("02:00").isSwitch(true).autoCheckUpdateOnly(false).build();
            Policy policy = Policy.builder().notify(notify).upgradeTiming(upgradeTiming).dailySchedule(dailySchedule).build();

            // Target set
            TargetSet.DeviceGroup deviceGroup = TargetSet.DeviceGroup.builder().deviceGroupId(0).option("pass").showName("a0yt7sme").url("https://11.11.11.11/iot-platform-ota/ghpjhjrz6qaf9awr/g94npx8g/group/1667460417/a0yt7sme.txt").build();
            TargetSet.Quantity quantity = TargetSet.Quantity.builder().option("all").quotaQuantities(0).build();
            ArrayList<TargetSet.Version.System> systems = new ArrayList<TargetSet.Version.System>();
            systems.add(TargetSet.Version.System.builder().maxVersion("1.0.0.1").minVersion("1.0.0.0").build());
            TargetSet.Version version = TargetSet.Version.builder().system(systems).build();
//            ArrayList<String> channelData = new ArrayList<String>();
//            channelData.add("1");
//            TargetSet.Channel channel = TargetSet.Channel.builder().option("pass").data(channelData).build();
            TargetSet targetSet = TargetSet.builder().quantity(quantity)
                    .deviceGroup(TargetSet.DeviceGroup.builder().deviceGroupId(0).option("pass").build()).testDeviceGroup(deviceGroup).version(version).build();

            // delivery_config
            DeliveryConfig deliveryConfig = DeliveryConfig.builder().enablePcdn(0).build();
            CreateOtaTaskRequest request = CreateOtaTaskRequest.builder().basic(basic).targetSet(targetSet)
                    .packageData(packageData).policy(policy).deliveryConfig(deliveryConfig).build();
            String s = JsonUtils.toJsonString(request);
            CreateOtaTaskResponse response = client.addOtaTask(instanceId,
                    productKey, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteOtaTaskTest() {
        try {
            DeleteOtaTaskRequest request = DeleteOtaTaskRequest.builder().isTrashed(true).build();
            client.deleteOtaTask(instanceId,
                    productKey, 193, request);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateOtaTaskTest() {
        try {
            UpdateOtaTaskRequest request = UpdateOtaTaskRequest.builder().priority(1)
                    .dailySchedule(UpdateOtaTaskRequest.DailySchedule.builder()
                            .autoCheckUpdateOnly(false).isSwitch(true).startTime("04:30").stopTime("05:30").build())
                    .notify(UpdateOtaTaskRequest.Notify.builder().forceUpgrade(false).releaseNote("test").build()).build();
            UpdateOtaTaskResponse response = client.updateOtaTask(instanceId,
                    productKey, 197, request);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateOtaTaskStatusTest() {
        try {
            UpdateOtaTaskStatusRequest request = UpdateOtaTaskStatusRequest.builder()
                    .status(UpdateOtaTaskStatusRequest.Status.status_completed).build();

            UpdateOtaTaskStatusResponse response = client.updateOtaTaskStatus(instanceId,
                    productKey, 197, request);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getOtaTaskTest() {
        try {
            GetOtaTaskResponse response = client.getOtaTask(instanceId,
                    productKey, 197);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listOtaTaskTest() {
        try {
            ListOtaTaskRequest request = ListOtaTaskRequest.builder()
                    .direction(ListOtaTaskRequest.Direction.asc).orderBy(ListOtaTaskRequest.OrderBy.create_time)
                    .status("status_testing").build();
            ListOtaTaskResponse response = client.listOtaTask(instanceId,
                    productKey, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void createOrUpdateGrayTaskTest() {
        try {
            ArrayList<Integer> setting = new ArrayList<Integer>();
            setting.add(50);
            setting.add(60);
            CreateOrUpdateGrayTaskRequest request = CreateOrUpdateGrayTaskRequest.builder().total(2).auto(true)
                    .setting(setting).type(CreateOrUpdateGrayTaskRequest.Type.count).build();
            client.createOrUpdateGrayTask(instanceId,
                    productKey, 198, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateGrayTaskStatusTest() {
        try {
            client.updateGrayTaskStatus(instanceId,
                    productKey, 198);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getGrayTaskTest() {
        try {
            GrayTask response = client.getGrayTask(instanceId,
                    productKey, 198);
        } catch (Exception e) {
            fail();
        }
    }

    // ota device
    @Test
    @Ignore
    public void listAllTestDeviceForTask() {
        try {
            ListAllTestDeviceForTaskResponse response = client.listAllTestDeviceForTask(instanceId,
                    productKey, 166);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void searchDeviceForTaskTask() {
        try {SearchDeviceForTaskResponse response = client.searchDeviceForTask(instanceId,
                productKey, 50, SearchType.devquery_id, "20");

        } catch (Exception e) {
            fail();
        }
    }

    // ota statistics
    @Test
    @Ignore
    public void otaTaskIssuedStatisticsTest() {
        try {
            OtaTaskIssuedStatisticsResponse response =
                    client.otaTaskIssuedStatistics(instanceId, productKey, 50);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void otaTaskStatisticsTest() {
        try {
            OtaTaskStatisticsResponse response = client.otaTaskStatistics(instanceId, productKey, 50);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void otaTaskIssuedFailedStatistics() {
        try {
            OtaTaskIssuedFailedStatisticsResponse response = client
                    .otaTaskIssuedFailedStatistics(instanceId, productKey, 50);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void otaTaskIssuedFailureInfoStatisticsTest() {
        try {
            OtaTaskIssuedFailureInfoStatisticsResponse response = client.otaTaskIssuedFailureInfoStatistics("1n61a66j57zv1vxw", "product", 50, Stage.DOWNLOAD);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void otaTaskStageStatisticsTest() {
        try {
            OtaTaskStageStatisticsResponse response = client.otaTaskStageStatistics(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void otaTaskProductLineWeekStatisticsTest() {
        try {
            OtaTaskProductLineWeekStatisticsResponse response =
                    client.otaTaskProductLineWeekStatistics(instanceId, productKey, "2022-11-02");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listOtaCompletedPackingTest() {
        try {
            CommonOtaListRequest request = new CommonOtaListRequest(2, 3);
            ListOtaCompletedPackingResponse response = client.listOtaCompletedPacking(instanceId, productKey, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listOtaUncompletedPackingTest() {
        try {
            CommonOtaListRequest request = new CommonOtaListRequest();
            ListOtaUncompletedPackingResponse response = client.listOtaUncompletedPacking(instanceId, productKey, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getOtaPackingStatusTest() {
        try {
            GetOtaPackingStatusResponse response = client.getOtaPackingStatus(instanceId, productKey, 87);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void cancelOtaPackingTest() {
        try {
            CancelOtaPackingResponse response = client.cancelOtaPacking(instanceId, productKey,
                    98, "make", true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteOtaPackingTest() {
        try {
            CommonOtaListRequest request = new CommonOtaListRequest();
            ListOtaCompletedPackingResponse listResponse = client.listOtaCompletedPacking(instanceId, productKey, request);
            Packing packing = listResponse.getFileList().get(0);

            DeleteOtaPackingResponse response = client.deleteOtaPacking(instanceId, productKey, packing.getIssueId(), packing.getStepName(), packing.isHasNextStep());
        } catch (Exception e) {
            fail();
        }
    }

    // batch
    @Test
    @Ignore
    public void getBatchPageTest() {
        try {
            CommonListRequest request = CommonListRequest.builder().pageNo(2).pageSize(8).build();
            GetBatchPageResponse response = client.getBatchPage(instanceId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getBatchTest() {
        try {
            CommonListRequest request = CommonListRequest.builder().pageNo(2).pageSize(8).build();
            BatchInfoResponse response = client.getBatch(instanceId, batchId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getBatchMqttTest() {
        try {
            BatchDownloadMqtt response = client.getBatchMqtt(instanceId, batchId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getBatchTuplesTest() {
        GetBatchTuplesResponse response = client.getBatchTuples(instanceId, batchId);
        List<DeviceTuples> result = response.getResult();
        Assert.assertNotNull(response);
        for (DeviceTuples deviceTuples : result) {
            Assert.assertNotNull(deviceTuples.getDeviceName());
        }
    }

    @Test
    @Ignore
    public void statsDeviceMessageTest() {
        try {
            StatsDeviceMessageResponse response = client.statsDeviceMessage(instanceId, Cycle.HOUR,
                    1676973598L, 1677059998L);
            response = client.statsDeviceMessage(instanceId, Cycle.DAY,
                    1676542065L, 1677060465L);
            response = client.statsDeviceMessage(instanceId, Cycle.WEEK,
                    1674468493L, 1677060493L);
            response = client.statsDeviceMessage(instanceId, Cycle.MONTH,
                    1661508524L, 1677060524L);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void statsLivelyDeviceTest() {
        try {
            StatsLivelyDeviceResponse response = client.statsLivelyDevice(instanceId, Cycle.HOUR,
                    1676973598L, 1677059998L);
            response = client.statsLivelyDevice(instanceId, Cycle.DAY,
                    1676542065L, 1677060465L);
            response = client.statsLivelyDevice(instanceId, Cycle.WEEK,
                    1674468493L, 1677060493L);
            response = client.statsLivelyDevice(instanceId, Cycle.MONTH,
                    1661508524L, 1677060524L);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void statsDeviceTotalTest() {
        try {
            StatsDeviceTotalResponse response = client.statsDeviceTotal(instanceId, Cycle.HOUR,
                    1676973598L, 1677059998L);
            response = client.statsDeviceTotal(instanceId, Cycle.DAY,
                    1676542065L, 1677060465L);
            response = client.statsDeviceTotal(instanceId, Cycle.WEEK,
                    1674468493L, 1677060493L);
            response = client.statsDeviceTotal(instanceId, Cycle.MONTH,
                    1661508524L, 1677060524L);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void statsDeviceStatesTest() {
        try {
            DeviceStatesStatsResult response = client.statsDeviceStates(instanceId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void statsProductTotalTest() {
        try {
            StatsProductTotalResponse response = client.statsProductTotal(instanceId, Cycle.HOUR,
                    1676973598L, 1677059998L);
            response = client.statsProductTotal(instanceId, Cycle.DAY,
                    1676542065L, 1677060465L);
            response = client.statsProductTotal(instanceId, Cycle.WEEK,
                    1674468493L, 1677060493L);
            response = client.statsProductTotal(instanceId, Cycle.MONTH,
                    1661508524L, 1677060524L);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void disableResourceTest() {
        try {
            client.disableResource(instanceId, ResourceSupplier.USER,
                    com.baidubce.services.iotdmp.model.instance.ResourceType.RABBITMQ);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void unbindResourceTest() {
        try {
            client.unbindResource(instanceId, ResourceSupplier.USER,
                    com.baidubce.services.iotdmp.model.instance.ResourceType.RABBITMQ);
        } catch (Exception e) {
            fail();
        }
    }

    // tsdb
    @Test
    @Ignore
    public void verifyTsdbResourceTest() {
        try {
            HashMap<String, String> properties = new HashMap<String, String>();
            properties.put("accessKey", "mock");
            properties.put("secretKey", "mock");
            properties.put("writeEndpoint", "mock");
            properties.put("readEndpoint", "mock");
            properties.put("adminEndpoint", "mock");
            properties.put("databaseName", "mock");
            properties.put("databaseId", "mock");
            UpdateInstanceResourcePropertiesRequest request = UpdateInstanceResourcePropertiesRequest.builder()
                    .resourceType(com.baidubce.services.iotdmp.model.instance.ResourceType.TSDB)
                    .properties(properties).build();
            client.verifyResourceProperties(instanceId, request);
            client.updateResourceProperties(instanceId, request);
            client.enableResource(instanceId, ResourceSupplier.USER, com.baidubce.services.iotdmp.model.instance.ResourceType.TSDB);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void tsdbModifyTest() {
        try {
            TsdbInitRequest request = new TsdbInitRequest(true, true);
            client.tsdbModify(instanceId, productKey, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void tsdbQueryPropertyTest() {
        TsdbQueryRequest request = new TsdbQueryRequest("D1");
        request.setPageSize(5);
        try {
            client.tsdbQueryProperty(instanceId, productKey, request);
        } catch (Exception e) {
            fail();
        }

        try {
            client.tsdbQueryProperty(instanceId, productKey, request, version);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void tsdbQueryEventTest() {
        TsdbQueryRequest request = new TsdbQueryRequest(deviceName);
        ArrayList<String> fields = new ArrayList<String>();
        fields.add("param");
        request.setFields(fields);
        request.setPageSize(3);
        try {
            client.tsdbQueryEvent(instanceId, productKey, eventName, request);
        } catch (Exception e) {
            fail();
        }

        try {
            TsdbQueryResponse event = client.tsdbQueryEvent(instanceId, productKey, eventName, request, version);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void tsdbMappingTest() {
        TsdbMappingRequest request = new TsdbMappingRequest();
        ArrayList<String> events = new ArrayList<String>();
        ArrayList<String> properties = new ArrayList<String>();
        events.add(eventName);
        properties.add(propertyName);
        request.setEvents(events);
        request.setProperties(properties);
        try {
            client.tsdbMapping(instanceId, productKey, request);
        } catch (Exception e) {
            fail();
        }

        try {
            client.tsdbMapping(instanceId, productKey, request, version);
        } catch (Exception e) {
            fail();
        }
    }

    // BIE Protocols
    @Test
    @Ignore
    public void getProtocolListTest() {
        try {
            ListProtocolResponse protocolList = client.getProtocolList(ResourceSupplier.USER, "", 2, 5);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void createProtocolTest() {
        try {
            CreateProtocolRequest request = new CreateProtocolRequest();
            request.setName("sdkIpProtocol");
            request.setDescription("from sdk");
            request.setProtocolType(ProtocolType.IP);
            client.createProtocol(request);

            request.setName("sdkNonIpProtocol");
            request.setProtocolType(ProtocolType.NON_IP);
            client.createProtocol(request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void modifyProtocolTest() {
        try {
            BusinessTemplatesAppRequest request = new BusinessTemplatesAppRequest();
            BusinessTemplatesAppService service = new BusinessTemplatesAppService();
            BusinessTemplatesAppService.Env env = new BusinessTemplatesAppService.Env();
            ArrayList<BusinessTemplatesAppService.Env> envs = new ArrayList<BusinessTemplatesAppService.Env>();
            envs.add(env);
            service.setEnv(envs);
            request.setServices(new ArrayList<BusinessTemplatesAppService>(Arrays.asList(service)));
            request.setDescription("modify form sdk");
            client.modifyProtocol(protocolId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getProtocolTest() {
        try {
            ProtocolResponse response = client.getProtocol(protocolId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteProtocolTest() {
        try {
            client.deleteProtocol(protocolId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getProtocolAppTest() {
        try {
            BusinessTemplatesApp response = client.getProtocolsApp(protocolId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getProtocolsRegistryTest() {
        try {
            BusinessTemplatesAppRegistryList response = client.getProtocolsRegistry(protocolId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void addProtocolsRegistryTest() {
        try {
            BusinessTemplatesAppRegistryRequest request = new BusinessTemplatesAppRegistryRequest();
            request.setAddress("mock_address");
            request.setUsername("mock_username");
            request.setPassword("mock_password");
            BusinessTemplatesAppRegistry response = client.addProtocolsRegistry(protocolId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void modifyProtocolsRegistryTest() {
        try {
            BusinessTemplatesAppRegistryRequest request = new BusinessTemplatesAppRegistryRequest();
            request.setAddress("update_address");
            request.setUsername("update_username");
            request.setPassword("update_password");
            client.modifyProtocolsRegistry(protocolId,
                    registryId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteProtocolsRegistryTest() {
        try {
            client.deleteProtocolsRegistry(protocolId,
                    registryId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void modifyProtocolsMarkdownTest() {
        try {
            ProtocolsMarkdownRequest request = new ProtocolsMarkdownRequest();
            request.setContent("form sdk");
            client.modifyProtocolsMarkdown(protocolId, request);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getProtocolsMarkdownTest() {
        try {
            ProtocolsMarkdownResponse response = client.getProtocolsMarkdown(protocolId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listNodeTest() {
        try {
            ListNodeResponse response = client.listNode(null, 2, 2);
            response = client.listNode("mock_node_name", 1, 2);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getNodeTest() {
        try {
            NodeResponse response = client.getNode(nodeId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void createNodeTest() {
        try {
            CreateNodeRequest request = new CreateNodeRequest();
            request.setDescription("fromSDK");
            request.setName("fromSDK");
            NodeResponse response = client.createNode(request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void modifyNodeTest() {
        try {
            ModifyNodeRequest request = new ModifyNodeRequest();
            request.setDescription("update from sdk");
            request.setName("updateFromSDK");
            client.modifyNode(nodeId, request);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteNodeTest() {
        try {
            client.deleteNode(nodeId);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getInstallNodePropertyTest() {
        try {
            GetInstallNodePropertyResponse response =
                    client.getInstallNodeProperty(nodeId, "command-docker-installation");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getInstallNodeInitTest() {
        try {

            GetInstallNodeInitResponse response = client.getInstallNodeInit(nodeId,
                    InstallMethod.curl, InstallPlatform.windows);
            System.out.println(response);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deployProtocolTest() {
        try {
            DeployBusinessTemplatesRequest request = new DeployBusinessTemplatesRequest();
            request.setDmpDeviceApiUrl("mock");
            ArrayList<BusinessTemplatesApp> businessTemplatesApps = new ArrayList<BusinessTemplatesApp>();
            BusinessTemplatesApp businessTemplatesApp = new BusinessTemplatesApp();
            businessTemplatesApp.setName("mock_app_name");
            businessTemplatesApps.add(businessTemplatesApp);
            request.setApplications(businessTemplatesApps);
            client.deployProtocol(nodeId,
                    protocolId, instanceId, "AMQP-0", null, request);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listNodeAppsTest() {
        try {

            ListNodeAppsResponse response = client.listNodeApps(nodeId, 1, 3);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listInstanceAppsTest() {
        try {
            ListNodeAppsResponse response = client.listInstanceApps(instanceId, 1, 3);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void modifyInstanceAppsTest() {
        try {
            BusinessTemplatesAppRequest request = new BusinessTemplatesAppRequest();
            request.setDescription("updateFromSDK");
            client.modifyInstanceApps(appId,
                    nodeId, instanceId, protocolId, false, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteInstanceAppsTest() {
        try {

            client.deleteInstanceApps(appId,
                    nodeId, instanceId, protocolId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getDeployAppTest() {
        try {
            BusinessTemplatesApp response = client.getDeployApp(appId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getAppTest() {
        try {
            AppResponse response = client.getApp(appId);
        } catch (Exception e) {
            fail();
        }
    }

    // BIE
    @Test
    @Ignore
    public void createEdgeGatewayNodeTest() {
        try {
            CreateProductInfoRequest productRequest = new CreateProductInfoRequest(productName,
                    DeviceType.GATEWAY,
                    new ArrayList<ProductAccessType>(Arrays.asList(ProductAccessType.FIFTH_GENERATION)));
            productRequest.setProductKey(productKey);
            productRequest.setDescription("from sdk");
            productRequest.setExtensions(new ArrayList<ResourceType>(Arrays.asList(ResourceType.BIE_GATEWAY)));
            productRequest.setProductType(ProductType.kube);
            ProductInfo productInfo = client.createProduct(instanceId, productRequest);
            Thread.sleep(1000);
            CreateDeviceRequest deviceRequest = CreateDeviceRequest.builder().deviceName(deviceName).build();
            client.createDevice(instanceId, productInfo.getProductKey(), deviceRequest);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEdgeGatewayNodeDetailTest() {
        try {
            GetEdgeGatewayNodeDetailResponse response = client.getEdgeGatewayNodeDetail(instanceId,
                    productKey, deviceName);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listEdgeGatewayNodeTest() {
        try {
            ListEdgeGatewayNodeRequest request = new ListEdgeGatewayNodeRequest();
            request.setProductKey(productKey);
//            request.setPageNo(2);
//            request.setPageSize(3);
            ListEdgeGatewayNodeResponse response = client.listEdgeGatewayNode(instanceId, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEdgeGatewayNodeAppInfoTest() {
        try {
            GetNodeAppInfoResponse response = client.getEdgeGatewayNodeAppInfo(instanceId, productKey, deviceName);
            Assert.assertNotNull(response.getItems());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deployAndUnloadEdgeGatewayAppTest() {
        try {
            String name = edgeGatewayAppName;
            GetAppInfoResponse response = client.deployAndUnloadEdgeGatewayApp(instanceId, productKey,
                    deviceName, name, AppAction.deploy);
            Assert.assertEquals(name, response.getName());
            Assert.assertNotNull(response.getSelector());

            response = client.deployAndUnloadEdgeGatewayApp(instanceId, productKey,
                    deviceName, name, AppAction.unload);
            Assert.assertNull(response.getSelector());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void configEdgeGatewayAppEnvTest() {
        try {
            GetAppInfoResponse response = client.configEdgeGatewayAppEnv(instanceId, productKey,
                    deviceName, edgeGatewayAppName);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEdgeGatewayNodeDriverRefsTest() {
        try {
            GetNodeDriverRefsRequest request = new GetNodeDriverRefsRequest();
            request.setDriverName(edgeGatewayDriverName);

            DriverListResponse response = client.getEdgeGatewayNodeDriverRefs(instanceId, productKey,
                    deviceName, request);
            Assert.assertEquals(edgeGatewayDriverName, response.getItems().get(0).getName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void introduceEdgeGatewayDriversTest() {
        try {
            DriverInfo driverInfo = client.introduceEdgeGatewayDrivers(instanceId, productKey,
                    deviceName, edgeGatewayDriverName);
            Assert.assertEquals(edgeGatewayDriverName, driverInfo.getDriverName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEdgeGatewayDriversTest() {
        try {
            DriverListResponse response = client.getEdgeGatewayDrivers(instanceId, productKey,
                    deviceName);
            for (DriverInfo item : response.getItems()) {
            }
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEdgeGatewayNodeDriverConfigTest() {
        try {
            DriverInfo response = client.getEdgeGatewayNodeDriverConfig(instanceId, productKey,
                    deviceName, edgeGatewayDriverInstName);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateEdgeGatewayNodeDriverConfigTest() {
        try {
            // BACnet Channel
            String driverInstName = "mock_bacnet_driverInstName";
            UpdateEdgeGatewayNodeDriverConfigRequest request = new UpdateEdgeGatewayNodeDriverConfigRequest();

            DriverConfig driverConfig = new DriverConfig();
            ChannelConfig channelConfig = new ChannelConfig();
            ChannelConfig.BacnetChannel bacnetChannel = new ChannelConfig.BacnetChannel();
            bacnetChannel.setAddress("http://baidu.com");
            bacnetChannel.setPort(1234);

            channelConfig.setBacnet(bacnetChannel);
            channelConfig.setChannelId("mock_bacnet_channelId");
            driverConfig.setChannels(new ArrayList<ChannelConfig>(Arrays.asList(channelConfig)));

            request.setDriverConfig(driverConfig);
            DriverInfo response = client.updateEdgeGatewayNodeDriverConfig(instanceId, productKey,
                    deviceName, driverInstName, request);

            // IEC-104
            driverInstName = "mock_iec_104_driverInstName";
            UpdateEdgeGatewayNodeDriverConfigRequest request2 = new UpdateEdgeGatewayNodeDriverConfigRequest();

            DriverConfig driverConfig2 = new DriverConfig();
            ChannelConfig channelConfig2 = new ChannelConfig();
            ChannelConfig.IEC104Channel iec104Channel = new ChannelConfig.IEC104Channel();
            iec104Channel.setAddress("110.112.119.123");
            iec104Channel.setPort(1234);
            iec104Channel.setProtocol("TCP_Server");

            channelConfig2.setIec104(iec104Channel);
            channelConfig2.setChannelId("mock_iec104_channelId");
            driverConfig2.setChannels(new ArrayList<ChannelConfig>(Arrays.asList(channelConfig2)));

            request2.setDriverConfig(driverConfig2);
            response = client.updateEdgeGatewayNodeDriverConfig(instanceId, productKey,
                    deviceName, driverInstName, request2);

            // OPC-DA
            driverInstName = "mock_opc_da_driverInstName";
            UpdateEdgeGatewayNodeDriverConfigRequest request3 = new UpdateEdgeGatewayNodeDriverConfigRequest();

            DriverConfig driverConfig3 = new DriverConfig();
            ChannelConfig channelConfig3 = new ChannelConfig();
            ChannelConfig.OpcdaChannel opcdaChannel = new ChannelConfig.OpcdaChannel();
            opcdaChannel.setHost("110.112.119.123");
            opcdaChannel.setServer("baidu.com");

            channelConfig3.setOpcda(opcdaChannel);
            channelConfig3.setChannelId("mock_opcda_channelId");
            driverConfig3.setChannels(new ArrayList<ChannelConfig>(Arrays.asList(channelConfig3)));

            request3.setDriverConfig(driverConfig3);
            response = client.updateEdgeGatewayNodeDriverConfig(instanceId, productKey,
                    deviceName, driverInstName, request3);

            // OPC-UA
            driverInstName = "mock_opc_ua_driverInstName";
            UpdateEdgeGatewayNodeDriverConfigRequest request4 = new UpdateEdgeGatewayNodeDriverConfigRequest();

            DriverConfig driverConfig4 = new DriverConfig();
            ChannelConfig channelConfig4 = new ChannelConfig();
            ChannelConfig.OpcuaChannel opcuaChannel = new ChannelConfig.OpcuaChannel();
            ChannelConfig.OpcuaAuth opcuaAuth = new ChannelConfig.OpcuaAuth();
            opcuaAuth.setPassword("mock_password");
            opcuaAuth.setUsername("mock_username");
            opcuaChannel.setAuth(opcuaAuth);
            ChannelConfig.OpcuaSecurity opcuaSecurity = new ChannelConfig.OpcuaSecurity();
            opcuaSecurity.setMode("None");
            opcuaSecurity.setPolicy("None");
            opcuaChannel.setSecurity(opcuaSecurity);
            opcuaChannel.setId((byte) 0);
            opcuaChannel.setEndpoint("http://baidu.com");
            opcuaChannel.setTimeout(10);
            channelConfig4.setOpcua(opcuaChannel);
            channelConfig4.setChannelId("mock_opcua_channelId");
            driverConfig4.setChannels(new ArrayList<ChannelConfig>(Arrays.asList(channelConfig4)));

            request4.setDriverConfig(driverConfig4);
            response = client.updateEdgeGatewayNodeDriverConfig(instanceId, productKey,
                    deviceName, driverInstName, request4);
            // Modbus
            driverInstName = "mock_modbus_driverInstName";
            UpdateEdgeGatewayNodeDriverConfigRequest request5 = new UpdateEdgeGatewayNodeDriverConfigRequest();

            DriverConfig driverConfig5 = new DriverConfig();
            ChannelConfig channelConfig5 = new ChannelConfig();
            ModbusChannel modbusChannel = new ModbusChannel();
            ModbusChannel.RtuConfig rtuConfig = new ModbusChannel.RtuConfig();
            rtuConfig.setPort("/dev/test");
            rtuConfig.setParity("N");
            rtuConfig.setStopBit(8);
            rtuConfig.setBaudRate(19200);
            modbusChannel.setRtu(rtuConfig);

            channelConfig5.setModbus(modbusChannel);
            channelConfig5.setChannelId("mock_modbus_channelId");
            driverConfig5.setChannels(new ArrayList<ChannelConfig>(Arrays.asList(channelConfig5)));

            request5.setDriverConfig(driverConfig5);
            response = client.updateEdgeGatewayNodeDriverConfig(instanceId, productKey,
                    deviceName, driverInstName, request5);

            // 自定义
            driverInstName = "mock_custom_driverInstName";
            UpdateEdgeGatewayNodeDriverConfigRequest request6 = new UpdateEdgeGatewayNodeDriverConfigRequest();

            DriverConfig driverConfig6 = new DriverConfig();
            driverConfig6.setCustom("mock_custom");

            request6.setDriverConfig(driverConfig6);
            response = client.updateEdgeGatewayNodeDriverConfig(instanceId, productKey,
                    deviceName, driverInstName, request6);

            // IPC
            driverInstName = "mock_ipc_driverInstName";
            UpdateEdgeGatewayNodeDriverConfigRequest request7 = new UpdateEdgeGatewayNodeDriverConfigRequest();

            DriverConfig driverConfig7 = new DriverConfig();
            IpcServiceConfig ipcServiceConfig = new IpcServiceConfig();
            ipcServiceConfig.setAddress("http://mock.com");
            IpcServiceConfig.IPCBody ipcBody = new IpcServiceConfig.IPCBody();
            ipcBody.setContent("mock_image");
            ipcBody.setImageType("binary");
            ipcServiceConfig.setBody(ipcBody);
            ipcServiceConfig.setCache(true);
            ipcServiceConfig.setFPS(0.1);
            IpcServiceConfig.IPCRequest ipcRequest = new IpcServiceConfig.IPCRequest();
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("mock_key", "mock_value");
            ipcRequest.setParams(params);
            ipcServiceConfig.setRequest(ipcRequest);
            IpcServiceConfig.Scale scale = new IpcServiceConfig.Scale();
            scale.setEnable(true);
            ipcServiceConfig.setScale(scale);
            ipcServiceConfig.setUpload(false);
            ipcServiceConfig.setName("mock_ipc_name");
            driverConfig7.setIpcServices(new ArrayList<IpcServiceConfig>(Arrays.asList(ipcServiceConfig)));

            request7.setDriverConfig(driverConfig7);
            response = client.updateEdgeGatewayNodeDriverConfig(instanceId, productKey,
                    deviceName, driverInstName, request7);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deployEdgeGatewayDriverTest() {
        try {
            client.deployEdgeGatewayDriver(instanceId, productKey,
                    deviceName);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deleteEdgeGatewayDriverTest() {
        try {
            client.deleteEdgeGatewayDriver(instanceId, productKey,
                    deviceName, edgeGatewayDriverInstName);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void syncEdgeGatewaySubDevicesTest() {
        try {
            client.syncEdgeGatewaySubDevices(instanceId, productKey,
                    deviceName);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEdgeGatewayAppListTest() {
        try {
            GetAppListRequest request = new GetAppListRequest();
            request.setName(edgeGatewayAppName);
            AppInfoListResponse response = client.getEdgeGatewayAppList(instanceId, request);
            Assert.assertEquals(1, response.getTotal());
            Assert.assertEquals(edgeGatewayAppName, response.getItems().get(0).getName());

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEdgeGatewaygetBusinessTemplatesTest() {
        try {
            GetBusinessTemplateRequest request = new GetBusinessTemplateRequest();
            request.setName(edgeGatewayAppName);
            BusinessTemplatesListResponse response = client.getEdgeGatewaygetBusinessTemplates(instanceId, request);
            Assert.assertEquals(edgeGatewayAppName, response.getItems().get(0).getName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deviceBindEdgeGatewayDriverTest() {
        try {
            BindSubDeviceRequest request = new BindSubDeviceRequest();
            BindSubDeviceRequest.SubDevicesRequest subDevicesRequest =
                    new BindSubDeviceRequest.SubDevicesRequest(productKey,
                            deviceName);
            request.setDevices(new ArrayList<BindSubDeviceRequest.SubDevicesRequest>(Arrays.asList(subDevicesRequest)));
            request.setDriverInstName(edgeGatewayDriverInstName);
            client.deviceBindEdgeGatewayDriver(instanceId, productKey, deviceName, request);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void deviceUnbindEdgeGatewayDriverTest() {
        try {
            BindSubDeviceRequest request = new BindSubDeviceRequest();
            BindSubDeviceRequest.SubDevicesRequest subDevicesRequest =
                    new BindSubDeviceRequest.SubDevicesRequest(productKey,
                            deviceName);
            request.setDevices(new ArrayList<BindSubDeviceRequest.SubDevicesRequest>(Arrays.asList(subDevicesRequest)));
            request.setDriverInstName(edgeGatewayDriverName);
            client.deviceUnbindEdgeGatewayDriver(instanceId, productKey, deviceName, request);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEdgeGatewayDriverBindSubDeviceListTest() {
        try {
            BindSubDeviceRequest request = new BindSubDeviceRequest();
            BindSubDeviceRequest.SubDevicesRequest subDevicesRequest =
                    new BindSubDeviceRequest.SubDevicesRequest(productKey,
                            deviceName);
            request.setDevices(new ArrayList<BindSubDeviceRequest.SubDevicesRequest>(Arrays.asList(subDevicesRequest)));
            request.setDriverInstName(edgeGatewayDriverInstName);
            GetBieDeviceListResponse response = client
                    .getEdgeGatewayDriverBindSubDeviceList(instanceId, productKey, deviceName,
                            edgeGatewayDriverInstName, 2, 1);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEdgeGatewayAccessTemplatesTest() {
        try {
            GetAccesstemplatesResponse response = client.getEdgeGatewayAccessTemplates(instanceId,
                    productKey, deviceName,
                    edgeGatewayDriverName);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void updateEdgeGatewaySubDeviceConfigTest() {
        try {
            UpdateEdgeGatewaySubDeviceConfigRequest request = new UpdateEdgeGatewaySubDeviceConfigRequest();
            request.setDriverInstName(edgeGatewayDriverInstName);
            request.setAccessTemplate(client.getEdgeGatewayAccessTemplates(instanceId, "sub", "sub_9",
                    edgeGatewayDriverName).getItems().get(0).getName());
            SubDeviceConfigUpdate config = new SubDeviceConfigUpdate();
            SubDeviceConfigUpdate.Modbus modbus = new SubDeviceConfigUpdate.Modbus();
            modbus.setChannelId("mock_modbus_channelId");
            modbus.setInterval(5);
            modbus.setSlaveId(3);
            config.setModbus(modbus);
            request.setConfig(config);
            GetBieDeviceConfigResponse response = client.updateEdgeGatewaySubDeviceConfig(instanceId, productKey,
                    deviceName, subProductKey, subDeviceName, request);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEdgeGatewaySubDeviceConfigTest() {
        try {

            GetBieDeviceConfigResponse response = client.getEdgeGatewaySubDeviceConfig(instanceId, productKey, deviceName,
                    subProductKey, subDeviceName);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void unbindEdgeGatewayDriverSubDeviceListTest() {
        try {
            UnbindEdgeGatewayDriverSubDeviceListRequest request = new UnbindEdgeGatewayDriverSubDeviceListRequest();
            request.setSubDeviceName(subDeviceName);
            UnbindEdgeGatewayDriverSubDeviceListResponse response = client.
                    unbindEdgeGatewayDriverSubDeviceList(instanceId, productKey, deviceName, request);
            Assert.assertEquals(subDeviceName, response.getResult().get(0).getDeviceName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void getEdgeGatewayAppDetailTest() {
        try {
            GetAppInfoResponse response = client.getEdgeGatewayAppDetail(instanceId, edgeGatewayAppName);
        } catch (Exception e) {
            fail();
        }
    }

    // version
    @Test
    @Ignore
    public void publishProductDTMLVersionTest() {
        try {
            client.publishProductDTMLVersion(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Ignore
    public void listVersionTest() {
        try {
            client.listVersion(instanceId, productKey);
        } catch (Exception e) {
            fail();
        }
    }
}