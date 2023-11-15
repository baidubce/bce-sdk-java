/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdmp;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.model.GenericAccountRequest;
import com.baidubce.services.iotdmp.model.CommonListRequest;
import com.baidubce.services.iotdmp.model.CommonResult;
import com.baidubce.services.iotdmp.model.alarm.AlarmRecordInfo;
import com.baidubce.services.iotdmp.model.alarm.AlarmRuleInfo;
import com.baidubce.services.iotdmp.model.alarm.BatchDeleteAlarmRuleRequest;
import com.baidubce.services.iotdmp.model.alarm.BatchProcessAlarmRecordRequest;
import com.baidubce.services.iotdmp.model.alarm.CreateAlarmRuleRequest;
import com.baidubce.services.iotdmp.model.alarm.ListAlarmRecordRequest;
import com.baidubce.services.iotdmp.model.alarm.ListAlarmRecordResponse;
import com.baidubce.services.iotdmp.model.alarm.ListAlarmRuleResponse;
import com.baidubce.services.iotdmp.model.alarm.TriggerAlarmRequest;
import com.baidubce.services.iotdmp.model.alarm.UpdateAlarmRuleRequest;
import com.baidubce.services.iotdmp.model.bie.app.AppResponse;
import com.baidubce.services.iotdmp.model.bie.app.ListNodeAppsResponse;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.AppAction;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.AppInfoListResponse;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.BindSubDeviceRequest;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.BusinessTemplatesListResponse;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.DriverInfo;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.DriverListResponse;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.GetAccesstemplatesResponse;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.GetAppInfoResponse;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.GetAppListRequest;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.GetBieDeviceConfigResponse;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.GetBieDeviceListResponse;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.GetBusinessTemplateRequest;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.GetEdgeGatewayNodeDetailResponse;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.GetNodeAppInfoResponse;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.GetNodeDriverRefsRequest;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.ListEdgeGatewayNodeRequest;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.ListEdgeGatewayNodeResponse;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.UnbindEdgeGatewayDriverSubDeviceListRequest;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.UnbindEdgeGatewayDriverSubDeviceListResponse;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.UpdateEdgeGatewayNodeDriverConfigRequest;
import com.baidubce.services.iotdmp.model.bie.edge.gateway.UpdateEdgeGatewaySubDeviceConfigRequest;
import com.baidubce.services.iotdmp.model.bie.node.CreateNodeRequest;
import com.baidubce.services.iotdmp.model.bie.node.DeployBusinessTemplatesRequest;
import com.baidubce.services.iotdmp.model.bie.node.GetInstallNodeInitResponse;
import com.baidubce.services.iotdmp.model.bie.node.GetInstallNodePropertyResponse;
import com.baidubce.services.iotdmp.model.bie.node.InstallMethod;
import com.baidubce.services.iotdmp.model.bie.node.InstallPlatform;
import com.baidubce.services.iotdmp.model.bie.node.ListNodeResponse;
import com.baidubce.services.iotdmp.model.bie.node.ModifyNodeRequest;
import com.baidubce.services.iotdmp.model.bie.node.NodeResponse;
import com.baidubce.services.iotdmp.model.bie.protocol.BusinessTemplatesApp;
import com.baidubce.services.iotdmp.model.bie.protocol.BusinessTemplatesAppRegistry;
import com.baidubce.services.iotdmp.model.bie.protocol.BusinessTemplatesAppRegistryList;
import com.baidubce.services.iotdmp.model.bie.protocol.BusinessTemplatesAppRegistryRequest;
import com.baidubce.services.iotdmp.model.bie.protocol.BusinessTemplatesAppRequest;
import com.baidubce.services.iotdmp.model.bie.protocol.CreateProtocolRequest;
import com.baidubce.services.iotdmp.model.bie.protocol.ListProtocolResponse;
import com.baidubce.services.iotdmp.model.bie.protocol.ProtocolResponse;
import com.baidubce.services.iotdmp.model.bie.protocol.ProtocolsMarkdownRequest;
import com.baidubce.services.iotdmp.model.bie.protocol.ProtocolsMarkdownResponse;
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
import com.baidubce.services.iotdmp.model.device.GetDeviceConnectionInfoRequest;
import com.baidubce.services.iotdmp.model.device.ListDeviceKeyRequest;
import com.baidubce.services.iotdmp.model.device.ListDeviceRequest;
import com.baidubce.services.iotdmp.model.device.ListDeviceResponse;
import com.baidubce.services.iotdmp.model.device.ListDeviceStatesResponse;
import com.baidubce.services.iotdmp.model.device.UpdateDeviceRequest;
import com.baidubce.services.iotdmp.model.device.UpdateDeviceStateRequest;
import com.baidubce.services.iotdmp.model.device.batch.BatchDownloadMqtt;
import com.baidubce.services.iotdmp.model.device.batch.BatchInfoResponse;
import com.baidubce.services.iotdmp.model.device.batch.GetBatchPageResponse;
import com.baidubce.services.iotdmp.model.device.batch.GetBatchTuplesResponse;
import com.baidubce.services.iotdmp.model.device.evs.AddEvsDeviceRequest;
import com.baidubce.services.iotdmp.model.device.evs.EvsDeviceInfo;
import com.baidubce.services.iotdmp.model.device.evs.EvsDurationRequest;
import com.baidubce.services.iotdmp.model.device.evs.EvsPtzRequest;
import com.baidubce.services.iotdmp.model.device.evs.EvsUrlProtocol;
import com.baidubce.services.iotdmp.model.device.evs.GetEvsChannelResponse;
import com.baidubce.services.iotdmp.model.device.evs.GetEvsChannelUrlResponse;
import com.baidubce.services.iotdmp.model.device.evs.GetEvsRecordingResponse;
import com.baidubce.services.iotdmp.model.device.evs.GetEvsThumbnailResponse;
import com.baidubce.services.iotdmp.model.device.evs.UpdateEvsDeviceRequest;
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
import com.baidubce.services.iotdmp.model.fm.GetConfigVersionResponse;
import com.baidubce.services.iotdmp.model.group.CreateGroupRequest;
import com.baidubce.services.iotdmp.model.group.GroupInfo;
import com.baidubce.services.iotdmp.model.group.ListDeviceByGroupResponse;
import com.baidubce.services.iotdmp.model.group.ListGroupRequest;
import com.baidubce.services.iotdmp.model.group.ListGroupResponse;
import com.baidubce.services.iotdmp.model.group.UpdateGroupInfoRequest;
import com.baidubce.services.iotdmp.model.handler.BceDmpHandler;
import com.baidubce.services.iotdmp.model.instance.CreateInstanceRequest;
import com.baidubce.services.iotdmp.model.instance.ExtensionResourceResponse;
import com.baidubce.services.iotdmp.model.instance.InstanceInfo;
import com.baidubce.services.iotdmp.model.instance.ListInstanceResourceResponse;
import com.baidubce.services.iotdmp.model.instance.ListInstancesResponse;
import com.baidubce.services.iotdmp.model.instance.ResourceSupplier;
import com.baidubce.services.iotdmp.model.instance.ResourceType;
import com.baidubce.services.iotdmp.model.instance.UpdateInstanceRequest;
import com.baidubce.services.iotdmp.model.instance.UpdateInstanceResourcePropertiesRequest;
import com.baidubce.services.iotdmp.model.linkage.BatchDeleteLinkageRuleRequest;
import com.baidubce.services.iotdmp.model.linkage.CreateLinkageRuleRequest;
import com.baidubce.services.iotdmp.model.linkage.CreateLinkageRuleResponse;
import com.baidubce.services.iotdmp.model.linkage.LinkageRuleInfo;
import com.baidubce.services.iotdmp.model.linkage.ListLinkageRuleResponse;
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
import com.baidubce.services.iotdmp.model.ota.packages.Type;
import com.baidubce.services.iotdmp.model.ota.packages.UploadOtaPackageRequest;
import com.baidubce.services.iotdmp.model.ota.packages.UploadOtaPackageResponse;
import com.baidubce.services.iotdmp.model.ota.packing.CancelOtaPackingResponse;
import com.baidubce.services.iotdmp.model.ota.packing.CreateOtaPackingRequest;
import com.baidubce.services.iotdmp.model.ota.packing.CreateOtaPackingResponse;
import com.baidubce.services.iotdmp.model.ota.packing.DeleteOtaPackingResponse;
import com.baidubce.services.iotdmp.model.ota.packing.GetOtaPackingStatusResponse;
import com.baidubce.services.iotdmp.model.ota.packing.ListOtaCompletedPackingResponse;
import com.baidubce.services.iotdmp.model.ota.packing.ListOtaUncompletedPackingResponse;
import com.baidubce.services.iotdmp.model.ota.product.CreateOtaProductRequest;
import com.baidubce.services.iotdmp.model.ota.product.CreateOtaProductResponse;
import com.baidubce.services.iotdmp.model.ota.product.ListOtaProductOperationRequest;
import com.baidubce.services.iotdmp.model.ota.product.ListOtaProductOperationResponse;
import com.baidubce.services.iotdmp.model.ota.product.ListOtaProductRequest;
import com.baidubce.services.iotdmp.model.ota.product.ListOtaProductResponse;
import com.baidubce.services.iotdmp.model.ota.product.OtaProductConfig;
import com.baidubce.services.iotdmp.model.ota.product.OtaProductDetail;
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
import com.baidubce.services.iotdmp.model.ota.task.DeleteOtaTaskRequest;
import com.baidubce.services.iotdmp.model.ota.task.GetOtaTaskResponse;
import com.baidubce.services.iotdmp.model.ota.task.GrayTask;
import com.baidubce.services.iotdmp.model.ota.task.ListOtaTaskRequest;
import com.baidubce.services.iotdmp.model.ota.task.UpdateOtaTaskRequest;
import com.baidubce.services.iotdmp.model.ota.task.UpdateOtaTaskResponse;
import com.baidubce.services.iotdmp.model.ota.task.UpdateOtaTaskStatusRequest;
import com.baidubce.services.iotdmp.model.ota.task.UpdateOtaTaskStatusResponse;
import com.baidubce.services.iotdmp.model.ota.task.ListOtaTaskResponse;
import com.baidubce.services.iotdmp.model.platform.AuthRuleChainExternalDestinationResponse;
import com.baidubce.services.iotdmp.model.platform.BatchDeleteRuleChainExternalDestinationRequest;
import com.baidubce.services.iotdmp.model.platform.BatchDeleteRuleChainRequest;
import com.baidubce.services.iotdmp.model.platform.CreateRuleChainExternalDestinationRequest;
import com.baidubce.services.iotdmp.model.platform.CreateRuleChainExternalDestinationRequestV2;
import com.baidubce.services.iotdmp.model.platform.CreateRuleChainExternalDestinationResponse;
import com.baidubce.services.iotdmp.model.platform.CreateRuleChainRequest;
import com.baidubce.services.iotdmp.model.platform.CreateRuleChainResponse;
import com.baidubce.services.iotdmp.model.platform.ListRuleChainDestinationRequest;
import com.baidubce.services.iotdmp.model.platform.ListRuleChainDestinationResponse;
import com.baidubce.services.iotdmp.model.platform.ListRuleChainRequest;
import com.baidubce.services.iotdmp.model.platform.ListRuleChainResponse;
import com.baidubce.services.iotdmp.model.platform.PlatformRuleChainInfo;
import com.baidubce.services.iotdmp.model.platform.RuleChainExternalDestinationType;
import com.baidubce.services.iotdmp.model.platform.UpdatePlatformRuleChainRequest;
import com.baidubce.services.iotdmp.model.platform.UpdateRuleChainStateRequest;
import com.baidubce.services.iotdmp.model.platform.UploadKafkaConfigFileResponse;
import com.baidubce.services.iotdmp.model.platform.ValidateRuleChainRequest;
import com.baidubce.services.iotdmp.model.platform.ValidateRuleChainResponse;
import com.baidubce.services.iotdmp.model.product.CreateProductByModelRequest;
import com.baidubce.services.iotdmp.model.product.CreateProductInfoRequest;
import com.baidubce.services.iotdmp.model.product.CreateTagRequest;
import com.baidubce.services.iotdmp.model.product.ImportProductModelRequest;
import com.baidubce.services.iotdmp.model.product.ListProductCategoryResponse;
import com.baidubce.services.iotdmp.model.product.ListProductModelRequest;
import com.baidubce.services.iotdmp.model.product.ListProductModelResponse;
import com.baidubce.services.iotdmp.model.product.ListProductRequest;
import com.baidubce.services.iotdmp.model.product.ListProductResponse;
import com.baidubce.services.iotdmp.model.product.PermanentConnectRequest;
import com.baidubce.services.iotdmp.model.product.ProductInfo;
import com.baidubce.services.iotdmp.model.product.ProductModelInfo;
import com.baidubce.services.iotdmp.model.product.UpdateProductInfoRequest;
import com.baidubce.services.iotdmp.model.product.evs.CreateEvsSpaceRequest;
import com.baidubce.services.iotdmp.model.product.evs.EvsSpaceInfo;
import com.baidubce.services.iotdmp.model.product.evs.GetEvsStreamResponse;
import com.baidubce.services.iotdmp.model.product.evs.UpdateEvsSpaceRequest;
import com.baidubce.services.iotdmp.model.product.feature.DtmlDetailResponse;
import com.baidubce.services.iotdmp.model.product.feature.command.CreateFeatureCommandRequest;
import com.baidubce.services.iotdmp.model.product.feature.command.ListFeatureCommandResponse;
import com.baidubce.services.iotdmp.model.product.feature.command.ProductFeatureCommandInfo;
import com.baidubce.services.iotdmp.model.product.feature.command.UpdateProductCommandRequest;
import com.baidubce.services.iotdmp.model.product.feature.event.CreateFeatureEventRequest;
import com.baidubce.services.iotdmp.model.product.feature.event.ListFeatureEventResponse;
import com.baidubce.services.iotdmp.model.product.feature.event.ProductFeatureEventInfo;
import com.baidubce.services.iotdmp.model.product.feature.event.UpdateProductEventRequest;
import com.baidubce.services.iotdmp.model.product.feature.property.CreateFeaturePropertyRequest;
import com.baidubce.services.iotdmp.model.product.feature.property.ListFeaturePropertyResponse;
import com.baidubce.services.iotdmp.model.product.feature.property.ProductFeaturePropertyInfo;
import com.baidubce.services.iotdmp.model.product.feature.property.UpdateProductPropertyRequest;
import com.baidubce.services.iotdmp.model.product.feature.thing.Thing;
import com.baidubce.services.iotdmp.model.product.repositories.SaveCustomProductModelRequest;
import com.baidubce.services.iotdmp.model.product.repositories.SaveCustomProductModelResponse;
import com.baidubce.services.iotdmp.model.product.repositories.SimpleProductModelInfo;
import com.baidubce.services.iotdmp.model.product.repositories.UpdateCustomProductModelRequest;
import com.baidubce.services.iotdmp.model.product.version.ListVersionResponse;
import com.baidubce.services.iotdmp.model.service.ConsumerGroupQueueInfoResponse;
import com.baidubce.services.iotdmp.model.service.ConsumerGroupUserInfoResponse;
import com.baidubce.services.iotdmp.model.service.CreateConsumerGroupRequest;
import com.baidubce.services.iotdmp.model.service.CreateConsumerGroupResponse;
import com.baidubce.services.iotdmp.model.service.GetBridgeListResponse;
import com.baidubce.services.iotdmp.model.service.ListConsumerGroupResponse;
import com.baidubce.services.iotdmp.model.service.ListInstanceServiceStateResponse;
import com.baidubce.services.iotdmp.model.service.ListSubResponse;
import com.baidubce.services.iotdmp.model.service.ProductSubscriptionResponse;
import com.baidubce.services.iotdmp.model.service.ResetConsumerGroupUserPwdResponse;
import com.baidubce.services.iotdmp.model.service.SendMessageRequest;
import com.baidubce.services.iotdmp.model.service.ServiceInfoResponse;
import com.baidubce.services.iotdmp.model.service.UpdateProductSubscriptionRequest;
import com.baidubce.services.iotdmp.model.service.rulechain.AvailableMessageTypeResponse;
import com.baidubce.services.iotdmp.model.service.rulechain.BlinkDataPermission;
import com.baidubce.services.iotdmp.model.service.rulechain.TopicDecodeRequest;
import com.baidubce.services.iotdmp.model.service.rulechain.TopicDecodeResponse;
import com.baidubce.services.iotdmp.model.service.rulechain.TopicEncodeRequest;
import com.baidubce.services.iotdmp.model.service.rulechain.TopicEncodeResponse;
import com.baidubce.services.iotdmp.model.shadow.DeviceShadowResponse;
import com.baidubce.services.iotdmp.model.shadow.ListDeviceShadowRequest;
import com.baidubce.services.iotdmp.model.shadow.ListDeviceShadowSnapshotResponse;
import com.baidubce.services.iotdmp.model.shadow.ShadowStatesRequest;
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
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class PlatformClient extends AbstractBceClient {

    private static final String ENDPOINT = "TBD";
    private static final String VERSION = "v1";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String INSTANCE = "instances";
    private static final String EXTENSION = "extensions";
    private static final String AVAILABLE = "available";
    private static final String CONFIG = "config";
    private static final String ENABLED = "enabled";
    private static final String ENABLE = "enable";
    private static final String PLATFORM_RULECHAINS = "platform/rulechains";
    private static final String PLATFORM_LINKAGES = "platform/linkages";
    private static final String BATCH_DELETE = "batchDelete";
    private static final String UPDATE_STATE = "updateState";
    private static final String COMPUTE = "compute";
    private static final String DESTINATIONS = "destinations";
    private static final String SERVICE = "services";
    private static final String C2C = "c2c";
    private static final String DEVICES = "devices";
    private static final String SECRET = "secret";
    private static final String TOPICS = "topics";
    private static final String TAGS = "tags";
    private static final String KEY = "key";
    private static final String EVS = "evs";
    private static final String CHANNEL = "channel";
    private static final String PTZ = "ptz";
    private static final String THUMBNAIL = "thumbnail";
    private static final String RECORDING = "recording";
    private static final String RESOURCES = "resources";
    private static final String STATES = "states";
    private static final String STATE = "state";
    private static final String AUTH = "auth";
    private static final String SHADOWS = "shadows";
    private static final String SHADOW = "shadow";
    private static final String DESIRED = "desired";
    private static final String SIGNED_URL = "signedUrl";
    private static final String PRODUCTS = "products";
    private static final String PRODUCT = "product";
    private static final String PERMANENT_CONNECT = "permanentConnect";
    private static final String FEATURE = "features";
    private static final String DETAIL = "detail";
    private static final String COMMAND = "commands";
    private static final String EVENT = "events";
    private static final String PROPERTIES = "properties";
    private static final String MESSAGES = "messages";
    private static final String BLINK = "blink";
    private static final String ENCODE = "encode";
    private static final String DECODE = "decode";
    private static final String MESSAGE = "message";
    private static final String TYPES = "types";
    private static final String TYPE = "type";
    private static final String SOURCE = "sources";
    private static final String SINK = "sinks";
    private static final String CONSUMER = "consumers";
    private static final String USER = "user";
    private static final String RESET = "reset";
    private static final String SUBSCRIPTIONS = "subscriptions";
    private static final String BATCH = "batch";
    private static final String SUBSET = "subsets";
    private static final String DELETE = "delete";
    private static final String GATEWAY = "gateway";
    private static final String DOWNWARD = "downward";
    private static final String LOG = "logs";
    private static final String GROUPS = "groups";
    private static final String EXPORT = "export";
    private static final String COMPONENTS = "components";
    private static final String VERIFY = "verify";
    private static final String CATEGORIES = "categories";
    private static final String REPOSITORIES = "repositories";
    private static final String MODELS = "models";
    private static final String INFO = "info";
    private static final String ALARMS = "alarms";
    private static final String RULES = "rules";
    private static final String TRIGGER = "trigger";
    private static final String RECORDS = "records";
    private static final String PROCESS = "process";
    private static final String FM = "fm";
    private static final String CONFIG_VERSION = "version";
    private static final String URL = "url";
    private static final String VERSIONS = "versions";
    private static final String TASK = "task";
    private static final String CSV = "csv";
    private static final String IMPORT = "import";
    private static final String TEST = "test";
    private static final String OTA = "ota";
    private static final String OPERATION = "operation";
    private static final String PACKAGES = "packages";
    private static final String CHECK = "check";
    private static final String STS = "sts";
    private static final String STATISTICS = "statistics";
    private static final String ISSUED = "issued";
    private static final String FAILED = "failed";
    private static final String FAILURES = "failures";
    private static final String STATUS = "status";
    private static final String GRAY = "gray";
    private static final String STAGE = "stage";
    private static final String WEEK = "week";
    private static final String COMPLETED = "completed";
    private static final String UNCOMPLETED = "uncompleted";
    private static final String PACKING = "packing";
    private static final String CANCEL = "cancel";
    private static final String MQTT = "mqtt";
    private static final String DOWNLOAD = "download";
    private static final String LIVELY = "lively";
    private static final String TOTAL = "total";
    private static final String DISABLE = "disable";
    private static final String UNBIND = "unbind";
    private static final String FILE = "file";
    private static final String EXTERNAL_KAFKA = "external-kafka";
    private static final String TSDB = "tsdb";
    private static final String QUERY = "query";
    private static final String MAPPING = "mapping";
    private static final String CUSTOM = "custom";
    private static final String PROTOCOLS = "protocols";
    private static final String APP = "app";
    private static final String REGISTRIES = "registries";
    private static final String MARKDOWN = "markdown";
    private static final String NODES = "nodes";
    private static final String INIT = "init";
    private static final String DEPLOY = "deploy";
    private static final String APPS = "apps";
    private static final String EDGE = "edge";
    private static final String ENV = "env";
    private static final String DRIVERREFS = "driverrefs";
    private static final String INTRODUCE = "introduce";
    private static final String DRIVER = "driver";
    private static final String DRIVERS = "drivers";
    private static final String SYNC = "sync";
    private static final String DEVICE = "device";
    private static final String BIND = "bind";
    private static final String ACCESSTEMPLATES = "accesstemplates";
    private static final String DEVICEMODELS = "devicemodels";
    private static final String NODEDEVICES = "nodedevices";
    private static final String SUB_DEVICE = "subDevice";
    private static final String BUSINESSTEMPLATES = "businesstemplates";
    private static final String VERSION_PATH = "version";
    private static final String PUBLISH = "publish";

    private static final HttpResponseHandler[] HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceDmpHandler() };

    public PlatformClient(String accessKey, String secretKey) {
        this(new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(accessKey, secretKey))
                .withEndpoint(ENDPOINT));
    }

    public PlatformClient(BceClientConfiguration config) {
        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT) : config, HANDLERS);
    }

    // Instance API
    public InstanceInfo getInstance(String instanceId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, INSTANCE, instanceId);
        return invokeHttpClient(internalRequest, InstanceInfo.class);
    }

    public ListInstancesResponse listInstances(int pageNo, int pageSize) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, INSTANCE);
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        return invokeHttpClient(internalRequest, ListInstancesResponse.class);
    }

    public InstanceInfo createInstance(CreateInstanceRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, INSTANCE);
        return invokeHttpClient(internalRequest, InstanceInfo.class);
    }

    public CommonResult deleteInstance(String instanceId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE, INSTANCE, instanceId);
        return invokeHttpClient(internalRequest, CommonResult.class);
    }

    public InstanceInfo updateInstance(String instanceId, UpdateInstanceRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, INSTANCE, instanceId);
        return  invokeHttpClient(internalRequest, InstanceInfo.class);
    }

    // extensions
    public ExtensionResourceResponse getConfigExtensionResources(String instanceId, String productKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PRODUCTS, instanceId, productKey, EXTENSION, CONFIG);
        return invokeHttpClient(internalRequest, ExtensionResourceResponse.class);
    }

    public ExtensionResourceResponse getConfigExtensionResources(String instanceId, String productKey,
                                                                 String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                DEVICES, instanceId, productKey, deviceName, EXTENSION, CONFIG);
        return invokeHttpClient(internalRequest, ExtensionResourceResponse.class);
    }

    public ExtensionResourceResponse getEnabledExtensionResources(String instanceId, String productKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, PRODUCTS,
                instanceId, productKey, EXTENSION, ENABLED);
        return invokeHttpClient(internalRequest, ExtensionResourceResponse.class);
    }


    public ExtensionResourceResponse getEnabledExtensionResources(String instanceId, String productKey,
                                                                  String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                DEVICES, instanceId, productKey, deviceName, EXTENSION, ENABLED);
        return invokeHttpClient(internalRequest, ExtensionResourceResponse.class);
    }

    public ListInstanceResourceResponse listInstanceResources(String instanceId, ResourceSupplier supplier) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                INSTANCE, instanceId, RESOURCES);
        internalRequest.addParameter("supplier", supplier.name());
        return invokeHttpClient(internalRequest, ListInstanceResourceResponse.class);
    }

    public void updateResourceProperties(String instanceId, UpdateInstanceResourcePropertiesRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                INSTANCE, instanceId, RESOURCES);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void enableResource(String instanceId, ResourceSupplier supplier, ResourceType resourceType) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST,
                INSTANCE, instanceId, RESOURCES, ENABLE);
        internalRequest.addParameter("supplier", supplier.name());
        internalRequest.addParameter("resourceType", resourceType.name());

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void verifyResourceProperties(String instanceId, UpdateInstanceResourcePropertiesRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                INSTANCE, instanceId, RESOURCES, VERIFY);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // Platform API
    public CreateRuleChainResponse createRuleChain (String instanceId, CreateRuleChainRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, PLATFORM_RULECHAINS, instanceId);
        return invokeHttpClient(internalRequest, CreateRuleChainResponse.class);
    }

    public void deleteRuleChain (String instanceId, BatchDeleteRuleChainRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, PLATFORM_RULECHAINS, instanceId, BATCH_DELETE);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void updateRuleChain (String instanceId, String rulechainId,
                                         UpdatePlatformRuleChainRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, PLATFORM_RULECHAINS, instanceId, rulechainId);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void updateRuleChainState (String instanceId, String rulechainId,
                                              UpdateRuleChainStateRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, PLATFORM_RULECHAINS,
                instanceId, rulechainId, UPDATE_STATE);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public PlatformRuleChainInfo getRuleChain (String instanceId, String rulechainId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, PLATFORM_RULECHAINS, instanceId, rulechainId);
        return invokeHttpClient(internalRequest, PlatformRuleChainInfo.class);
    }

    public ListRuleChainResponse listRuleChain (String instanceId, ListRuleChainRequest request) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, PLATFORM_RULECHAINS, instanceId);
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        if (StringUtils.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        if (StringUtils.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        if (StringUtils.isNotEmpty(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        if (StringUtils.isNotEmpty(request.getState())) {
            internalRequest.addParameter("state", request.getState());
        }
        if (StringUtils.isNotEmpty(request.getStatus())) {
            internalRequest.addParameter("status", request.getStatus());
        }
        return invokeHttpClient(internalRequest, ListRuleChainResponse.class);
    }

    public ValidateRuleChainResponse validateRuleChain (ValidateRuleChainRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, PLATFORM_RULECHAINS, COMPUTE);
        return invokeHttpClient(internalRequest, ValidateRuleChainResponse.class);
    }

    /**
     * @Deprecated As of release v0.10.281,
     * replaced by {@link #createRuleChainExternalDestination(String, CreateRuleChainExternalDestinationRequestV2)}
     */
    @Deprecated
    public CreateRuleChainExternalDestinationResponse createRuleChainExternalDestination (
            String instanceId,
            CreateRuleChainExternalDestinationRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, PLATFORM_RULECHAINS, instanceId, DESTINATIONS);
        return invokeHttpClient(internalRequest, CreateRuleChainExternalDestinationResponse.class);
    }

    public CreateRuleChainExternalDestinationResponse createRuleChainExternalDestination (
            String instanceId,
            CreateRuleChainExternalDestinationRequestV2 request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, PLATFORM_RULECHAINS, instanceId, DESTINATIONS);
        return invokeHttpClient(internalRequest, CreateRuleChainExternalDestinationResponse.class);
    }

    public ListRuleChainDestinationResponse listRuleChainDestinations (String instanceId,
                                              ListRuleChainDestinationRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, PLATFORM_RULECHAINS, instanceId, DESTINATIONS);
        internalRequest.addParameter("sourceType", request.getSourceType());
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        if (StringUtils.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        if (StringUtils.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        if (StringUtils.isNotEmpty(request.getRegion())) {
            internalRequest.addParameter("region", request.getRegion());
        }
        if (StringUtils.isNotEmpty(request.getType())) {
            internalRequest.addParameter("type", request.getType());
        }
        return invokeHttpClient(internalRequest, ListRuleChainDestinationResponse.class);
    }

    public void batchDeleteRuleChainExternalDestinations (String instanceId,
                                                          BatchDeleteRuleChainExternalDestinationRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, PLATFORM_RULECHAINS, instanceId, DESTINATIONS, BATCH_DELETE);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void validateRuleChainDestinationConnect (String instanceId, String destId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST, PLATFORM_RULECHAINS,
                instanceId, DESTINATIONS, destId);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public AuthRuleChainExternalDestinationResponse authRuleChainExternalDestination(String instanceId,
                                                                                     String destinationId,
                                                 RuleChainExternalDestinationType type) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, PLATFORM_RULECHAINS,
                instanceId, DESTINATIONS, destinationId, AUTH);
        internalRequest.addParameter("type", type.name());

        return invokeHttpClient(internalRequest, AuthRuleChainExternalDestinationResponse.class);
    }

    // C2C API
    public ComputationSourceResponse getC2CSource(String instanceId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, SERVICE, instanceId, C2C);
        return invokeHttpClient(internalRequest, ComputationSourceResponse.class);
    }

    public void updateC2CDownwardState(String instanceId, boolean state) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.PUT, SERVICE, instanceId, C2C, DOWNWARD);
        internalRequest.addParameter("state", Boolean.toString(state));
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // Device API
    public DeviceInfo createDevice(String instanceId, String productKey, CreateDeviceRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, DEVICES, instanceId, productKey);
        return invokeHttpClient(internalRequest, DeviceInfo.class);
    }

    public void importCsvCreateDevice(String instanceId, String productKey, File file) {
        InternalRequest internalRequest = createUploadRequest(
                "importFile", file, HttpMethodName.POST, DEVICES, instanceId, productKey, CSV, IMPORT);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void importCsvCreateDevice(String instanceId, String productKey, File file, AuthType authType) {
        InternalRequest internalRequest = createUploadRequest(
                "importFile", file, HttpMethodName.POST, DEVICES, instanceId, productKey, CSV, IMPORT);
        internalRequest.addParameter("authType", authType.name());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void importCsvCreateDevice(String instanceId, String productKey, File file,
                                      AuthType authType, String description) {
        InternalRequest internalRequest = createUploadRequest(
                "importFile", file, HttpMethodName.POST, DEVICES, instanceId, productKey, CSV, IMPORT);
        internalRequest.addParameter("authType", authType.name());
        internalRequest.addParameter("description", description);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void importCsvCreateDevice(String instanceId, String productKey, File file,
                                      AuthType authType, String description, boolean isRegisterIotCore) {
        InternalRequest internalRequest = createUploadRequest(
                "importFile", file, HttpMethodName.POST, DEVICES, instanceId, productKey, CSV, IMPORT);
        internalRequest.addParameter("authType", authType.name());
        internalRequest.addParameter("description", description);
        internalRequest.addParameter("isRegisterIotCore", Boolean.toString(isRegisterIotCore));
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void deleteDevice(String instanceId, String productKey, String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE, DEVICES, instanceId, productKey, deviceName);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void batchDeleteDevice(String instanceId, BatchDeleteDeviceRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, DEVICES, instanceId, BATCH, DELETE);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public DeviceInfo getDevice(String instanceId, String productKey, String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, DEVICES, instanceId, productKey, deviceName);
        return invokeHttpClient(internalRequest, DeviceInfo.class);
    }

    public ListDeviceResponse getDeviceList(String instanceId, ListDeviceRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, DEVICES, instanceId);
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        internalRequest.addParameter("cursor", request.getCursor());
        if (StringUtils.isNotEmpty(request.getProductKey())) {
            internalRequest.addParameter("productKey", request.getProductKey());
        }
        if (StringUtils.isNotEmpty(request.getAlias())) {
            internalRequest.addParameter("alias", request.getAlias());
        }
        return invokeHttpClient(internalRequest, ListDeviceResponse.class);
    }

    public void resetDeviceSecret(String instanceId, String productKey, String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.PUT, DEVICES,
                instanceId, productKey, deviceName, SECRET);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void batchCreateDevice(String instanceId, String productKey, BatchCreateDeviceRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, DEVICES,
                instanceId, productKey, BATCH);
        if (request.getAuthType() != null) {
            internalRequest.addParameter("authType", request.getAuthType().name());
        }
        if (StringUtils.isNotEmpty(request.getDescription())) {
            internalRequest.addParameter("description", request.getDescription());
        }
        if (request.getIsRegisterIotCore() != null) {
            internalRequest.addParameter("isRegisterIotCore", Boolean.toString(request.getIsRegisterIotCore()));
        }
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public DeviceInfo updateDevice(String instanceId, String productKey,
                                   String deviceName, UpdateDeviceRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, DEVICES, instanceId, productKey, deviceName);
        return invokeHttpClient(internalRequest, DeviceInfo.class);
    }

    public ListDeviceStatesResponse getDeviceStates(String instanceId, ListDeviceKeyRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, DEVICES, instanceId, STATES);
        return invokeHttpClient(internalRequest, ListDeviceStatesResponse.class);
    }

    // topic
    public ListTopicResponse getDeviceTopic(String instanceId, String productKey, String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, DEVICES,
                instanceId, productKey, deviceName, TOPICS);
        return invokeHttpClient(internalRequest, ListTopicResponse.class);
    }

    // tag
    public ListTagResponse createDeviceTag(String instanceId, String productKey,
                                     String deviceName, CreateTagRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, DEVICES,
                instanceId, productKey, deviceName, TAGS);
        return invokeHttpClient(internalRequest, ListTagResponse.class);
    }

    public void deleteDeviceTag(String instanceId, String productKey,
                                     String deviceName, String key) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE, DEVICES,
                instanceId, productKey, deviceName, TAGS, key);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ListTagResponse getDeviceTagList(String instanceId, String productKey, String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, DEVICES,
                instanceId, productKey, deviceName, TAGS);
        return invokeHttpClient(internalRequest, ListTagResponse.class);
    }

    // video
    public EvsSpaceInfo getEvs(String instanceId, String productKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PRODUCTS, instanceId, productKey, EVS);
        return invokeHttpClient(internalRequest, EvsSpaceInfo.class);
    }

    public EvsDeviceInfo getEvs(String instanceId, String productKey, String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, DEVICES,
                instanceId, productKey, deviceName, EVS);
        return invokeHttpClient(internalRequest, EvsDeviceInfo.class);
    }

    public void createEvs(String instanceId, String productKey,
                                    CreateEvsSpaceRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                PRODUCTS, instanceId, productKey, EVS);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void createEvs(String instanceId, String productKey,
                                   String deviceName, AddEvsDeviceRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, DEVICES,
                instanceId, productKey, deviceName, EVS);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public GetEvsChannelUrlResponse getEvsChannelUrl(String instanceId, String productKey,
                                    String deviceName, String channelId, EvsUrlProtocol protocol) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, DEVICES,
                instanceId, productKey, deviceName, EVS, CHANNEL, channelId);

        internalRequest.addParameter("protocol", protocol.name());

        return invokeHttpClient(internalRequest, GetEvsChannelUrlResponse.class);
    }

    public void getEvsChannelPtz(String instanceId, String productKey,
                                           String deviceName, String channelId,
                                           EvsPtzRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, DEVICES,
                instanceId, productKey, deviceName, EVS, CHANNEL, channelId, PTZ);
        internalRequest.addParameter("ptz", request.getPtzCommand());
        if (request.getSpeed() != null) {
            internalRequest.addParameter("speed", Integer.toString(request.getSpeed()));
        }
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public GetEvsChannelResponse getEvsChannel(String instanceId, String productKey, String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, DEVICES,
                instanceId, productKey, deviceName, EVS, CHANNEL);
        return invokeHttpClient(internalRequest, GetEvsChannelResponse.class);
    }

    public GetEvsThumbnailResponse getEvsThumbnail(String instanceId, String productKey,
                                                   String deviceName, EvsDurationRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, DEVICES,
                instanceId, productKey, deviceName, EVS, THUMBNAIL);
        internalRequest.addParameter("begin", Integer.toString(request.getBegin()));
        internalRequest.addParameter("end", Integer.toString(request.getEnd()));
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        return invokeHttpClient(internalRequest, GetEvsThumbnailResponse.class);
    }

    public GetEvsRecordingResponse getEvsRecording(String instanceId, String productKey,
                                        String deviceName, EvsDurationRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, DEVICES,
                instanceId, productKey, deviceName, EVS, RECORDING);
        internalRequest.addParameter("begin", Integer.toString(request.getBegin()));
        internalRequest.addParameter("end", Integer.toString(request.getEnd()));
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        return invokeHttpClient(internalRequest, GetEvsRecordingResponse.class);
    }

    public void auth(String instanceId, String productKey,
                                              String deviceName, AuthRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, DEVICES,
                instanceId, productKey, deviceName, AUTH);
        internalRequest.addHeader("signature", request.getSignature());
        internalRequest.addHeader("expiryTime", Long.toString(request.getExpiryTime()));
        if (request.getAlgorithmType() != null) {
            internalRequest.addHeader("algorithmType", request.getAlgorithmType().name());
        }
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public DeviceResourcesConnectionInfo getResourcesInfo(String instanceId, String productKey,
                                                          String deviceName, GetDeviceConnectionInfoRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, DEVICES,
                instanceId, productKey, deviceName, RESOURCES);
        return invokeHttpClient(internalRequest, DeviceResourcesConnectionInfo.class);
    }

    public void updateDeviceStates(String instanceId, String productKey,
                                       String deviceName, UpdateDeviceStateRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, DEVICES,
                instanceId, productKey, deviceName, STATES);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // DeviceShadow API
    public DeviceShadowResponse getDeviceShadow(String instanceId, String productKey,
                                                String deviceName, ListDeviceShadowRequest request) {
        return getDeviceShadow(instanceId, productKey, deviceName, request, null);
    }

    public DeviceShadowResponse getDeviceShadow(String instanceId, String productKey,
                                                String deviceName, ListDeviceShadowRequest request, String version) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET,
                SHADOWS, instanceId, productKey, deviceName);
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        if (StringUtils.isNotEmpty(request.getPropertyName())) {
            internalRequest.addParameter("propertyName", request.getPropertyName());
        }
        if (StringUtils.isNotBlank(version)) {
            internalRequest.addParameter("version", version);
        }
        return invokeHttpClient(internalRequest, DeviceShadowResponse.class);
    }

    public void updateDeviceShadowState(String instanceId, String productKey,
                                                        boolean shadowState) {
        ShadowStatesRequest request = new ShadowStatesRequest();
        request.setShadowState(shadowState);
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                SHADOWS, instanceId, productKey, STATES);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void updateDesired(String instanceId, String productKey,
                              String deviceName,  UpdateDesiredRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                SHADOWS, instanceId, productKey, deviceName, DESIRED);
        internalRequest.addParameter("bindName", request.getBindName());
        String jsonStr = JsonUtils.toJsonString(request.getDesired());
        try {
            byte[] content = jsonStr.getBytes(DEFAULT_ENCODING);
            internalRequest.setContent(RestartableInputStream.wrap(content));
            internalRequest.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ListDeviceShadowSnapshotResponse listShadow(String instanceId, ListDeviceKeyRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                SHADOWS, instanceId, SHADOW, BATCH);
        return invokeHttpClient(internalRequest, ListDeviceShadowSnapshotResponse.class);
    }

    // EVS
    public GetEvsStreamResponse getEvsStream(String domain, String app,
                                             String stream, EvsUrlProtocol protocol) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest() , HttpMethodName.GET,
                EVS, domain, app, stream, SIGNED_URL);
        internalRequest.addParameter("protocol", protocol.name());
        return invokeHttpClient(internalRequest, GetEvsStreamResponse.class);
    }

    // Product API
    public ProductInfo createProduct(String instanceId, CreateProductInfoRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                PRODUCTS, instanceId);
        return invokeHttpClient(internalRequest, ProductInfo.class);
    }

    public void resetSecret(String instanceId, String productKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.PUT,
                PRODUCTS, instanceId, productKey, SECRET);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ProductInfo updateProduct(String instanceId, String productKey,
                                        UpdateProductInfoRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                PRODUCTS, instanceId, productKey);
        return invokeHttpClient(internalRequest, ProductInfo.class);
    }

    public ListProductResponse getProductList(String instanceId, ListProductRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, PRODUCTS, instanceId);
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        if (StringUtils.isNotEmpty(request.getProductName())) {
            internalRequest.addParameter("productName", request.getProductName());
        }
        if (StringUtils.isNotEmpty(request.getTagKey())) {
            internalRequest.addParameter("tagKey", request.getTagKey());
        }
        if (StringUtils.isNotEmpty(request.getTagValue())) {
            internalRequest.addParameter("tagValue", request.getTagValue());
        }
        if (request.getDeviceType() != null) {
            internalRequest.addParameter("deviceType", request.getDeviceType().toString());
        }
        return invokeHttpClient(internalRequest, ListProductResponse.class);
    }

    public ProductInfo getProduct(String instanceId,  String productKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PRODUCTS, instanceId, productKey);
        return invokeHttpClient(internalRequest, ProductInfo.class);
    }

    public void deleteProduct(String instanceId,  String productKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE,
                PRODUCTS, instanceId, productKey);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void updatePermanentConnect(String instanceId, String productKey,
                                                 boolean permanentConnect) {
        PermanentConnectRequest request = new PermanentConnectRequest(permanentConnect);
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                PRODUCTS, instanceId, productKey, PERMANENT_CONNECT);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // tag
    public ListTagResponse createProductTag(String instanceId, String productKey,
                                            CreateTagRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                PRODUCTS, instanceId, productKey, TAGS);
        return invokeHttpClient(internalRequest, ListTagResponse.class);
    }

    public void deleteProductTag(String instanceId, String productKey,
                                            String key) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE,
                PRODUCTS, instanceId, productKey, TAGS, key);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ListTagResponse getProductTagList(String instanceId, String productKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PRODUCTS, instanceId, productKey, TAGS);
        return invokeHttpClient(internalRequest, ListTagResponse.class);
    }

    // topic
    public ListTopicResponse getTopics(String instanceId, String productKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PRODUCTS, instanceId, productKey, TOPICS);
        return invokeHttpClient(internalRequest, ListTopicResponse.class);
    }

    // function
    public DtmlDetailResponse getDTMLDetail(String instanceId, String productKey) {
        return getDTMLDetail(instanceId, productKey, null);
    }

    public DtmlDetailResponse getDTMLDetail(String instanceId, String productKey, String version) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST,
                PRODUCTS, instanceId, productKey, FEATURE, DETAIL);
        if (StringUtils.isNotBlank(version)) {
            internalRequest.addParameter("version", version);
        }
        return invokeHttpClient(internalRequest, DtmlDetailResponse.class);
    }

    public void importDTMLDetail(String instanceId, String productKey, Thing thing) {
        InternalRequest internalRequest = createRequest(
                thing, HttpMethodName.POST,
                PRODUCTS, instanceId, productKey, FEATURE, BATCH);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // feature command
    public ProductFeatureCommandInfo createFeatureCommand(String instanceId, String productKey,
                                                          CreateFeatureCommandRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                PRODUCTS, instanceId, productKey, FEATURE, COMMAND);
        return invokeHttpClient(internalRequest, ProductFeatureCommandInfo.class);
    }

    public ProductFeatureCommandInfo updateFeatureCommand(String instanceId, String productKey,
                                                          String name, UpdateProductCommandRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                PRODUCTS, instanceId, productKey, FEATURE, COMMAND, name);
        return invokeHttpClient(internalRequest, ProductFeatureCommandInfo.class);
    }

    public void deleteFeatureCommand(String instanceId, String productKey,
                                                          String name) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE,
                PRODUCTS, instanceId, productKey, FEATURE, COMMAND, name);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ProductFeatureCommandInfo getFeatureCommand(String instanceId, String productKey,
                                               String name) {
        return getFeatureCommand(instanceId, productKey, name, null);
    }

    public ProductFeatureCommandInfo getFeatureCommand(String instanceId, String productKey,
                                                       String name, String version) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PRODUCTS, instanceId, productKey, FEATURE, COMMAND, name);
        if (StringUtils.isNotBlank(version)) {
            internalRequest.addParameter("version", version);
        }
        return invokeHttpClient(internalRequest, ProductFeatureCommandInfo.class);
    }

    public ListFeatureCommandResponse getFeatureCommandList(String instanceId, String productKey,
                                                            int pageNo, int pageSize) {
        return getFeatureCommandList(instanceId, productKey, pageNo, pageSize, null);
    }

    public ListFeatureCommandResponse getFeatureCommandList(String instanceId, String productKey,
                                                            int pageNo, int pageSize, String version) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PRODUCTS, instanceId, productKey, FEATURE, COMMAND);
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        if (StringUtils.isNotBlank(version)) {
            internalRequest.addParameter("version", version);
        }
        return invokeHttpClient(internalRequest, ListFeatureCommandResponse.class);
    }

    // feature event
    public ProductFeatureEventInfo createFeatureEvent(String instanceId, String productKey,
                                                      CreateFeatureEventRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                PRODUCTS, instanceId, productKey, FEATURE, EVENT);
        return invokeHttpClient(internalRequest, ProductFeatureEventInfo.class);
    }

    public ProductFeatureEventInfo updateFeatureEvent(String instanceId, String productKey,
                                                      String name, UpdateProductEventRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                PRODUCTS, instanceId, productKey, FEATURE, EVENT, name);
        return invokeHttpClient(internalRequest, ProductFeatureEventInfo.class);
    }

    public void deleteFeatureEvent(String instanceId, String productKey,
                                             String name) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE,
                PRODUCTS, instanceId, productKey, FEATURE, EVENT, name);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ProductFeatureEventInfo getFeatureEvent(String instanceId, String productKey, String name) {
        return getFeatureEvent(instanceId, productKey, name, null);
    }

    public ProductFeatureEventInfo getFeatureEvent(String instanceId, String productKey, String name, String version) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PRODUCTS, instanceId, productKey, FEATURE, EVENT, name);
        if (StringUtils.isNotBlank(version)) {
            internalRequest.addParameter("version", version);
        }
        return invokeHttpClient(internalRequest, ProductFeatureEventInfo.class);
    }

    public ListFeatureEventResponse getFeatureEventList(String instanceId, String productKey,
                                                        int pageNo, int pageSize) {
        return getFeatureEventList(instanceId, productKey, pageNo, pageSize, null);
    }

    public ListFeatureEventResponse getFeatureEventList(String instanceId, String productKey,
                                                        int pageNo, int pageSize, String version) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PRODUCTS, instanceId, productKey, FEATURE, EVENT);
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        if (StringUtils.isNotBlank(version)) {
            internalRequest.addParameter("version", version);
        }
        return invokeHttpClient(internalRequest, ListFeatureEventResponse.class);
    }

    // feature property
    public ProductFeaturePropertyInfo createFeatureProperty(String instanceId, String productKey,
                                                            CreateFeaturePropertyRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                PRODUCTS, instanceId, productKey, FEATURE, PROPERTIES);
        return invokeHttpClient(internalRequest, ProductFeaturePropertyInfo.class);
    }

    public ProductFeaturePropertyInfo updateFeatureProperty(String instanceId, String productKey,
                                                      String name, UpdateProductPropertyRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                PRODUCTS, instanceId, productKey, FEATURE, PROPERTIES, name);
        return invokeHttpClient(internalRequest, ProductFeaturePropertyInfo.class);
    }

    public void deleteFeatureProperty(String instanceId, String productKey,
                                             String name) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE,
                PRODUCTS, instanceId, productKey, FEATURE, PROPERTIES, name);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ProductFeaturePropertyInfo getFeatureProperty(String instanceId, String productKey,
                                                   String name) {
        return getFeatureProperty(instanceId, productKey, name, null);
    }

    public ProductFeaturePropertyInfo getFeatureProperty(String instanceId, String productKey,
                                                         String name, String version) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PRODUCTS, instanceId, productKey, FEATURE, PROPERTIES, name);
        if (StringUtils.isNotBlank(version)) {
            internalRequest.addParameter("version", version);
        }
        return invokeHttpClient(internalRequest, ProductFeaturePropertyInfo.class);
    }

    public ListFeaturePropertyResponse getFeaturePropertyList(String instanceId, String productKey,
                                                                       int pageNo, int pageSize) {
        return getFeaturePropertyList(instanceId, productKey, pageNo, pageSize, null);
    }

    public ListFeaturePropertyResponse getFeaturePropertyList(String instanceId, String productKey,
                                                              int pageNo, int pageSize, String version) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PRODUCTS, instanceId, productKey, FEATURE, PROPERTIES);
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        if (StringUtils.isNotBlank(version)) {
            internalRequest.addParameter("version", version);
        }
        return invokeHttpClient(internalRequest, ListFeaturePropertyResponse.class);
    }

    // video
    public void updateEvs(String instanceId, String productKey,
                                    UpdateEvsSpaceRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                PRODUCTS, instanceId, productKey, EVS);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void updateEvs(String instanceId, String productKey, String deviceName,
                          UpdateEvsDeviceRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                DEVICES, instanceId, productKey, deviceName, EVS);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // topo

    public ListProductResponse getSubsets(String instanceId, String productKey,
                                                       int pageNo, int pageSize) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PRODUCTS, instanceId, SUBSET, productKey);
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        return invokeHttpClient(internalRequest, ListProductResponse.class);
    }

    public ListDeviceResponse getSubsets(String instanceId, String productKey, String deviceName,
                           int pageNo, int pageSize, String name) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                DEVICES, instanceId, productKey, deviceName, SUBSET);
        if (StringUtils.isNotBlank(name)) {
            internalRequest.addParameter("name", name);
        }
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        return invokeHttpClient(internalRequest, ListDeviceResponse.class);
    }

    public void deleteSubsets(String instanceId, String productKey,
                                                       List<String> subProductKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST,
                PRODUCTS, instanceId, SUBSET, productKey, DELETE);
        String jsonStr = JsonUtils.toJsonString(subProductKey);
        try {
            byte[] content = jsonStr.getBytes(DEFAULT_ENCODING);
            internalRequest.setContent(RestartableInputStream.wrap(content));
            internalRequest.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void deleteSubsets(String instanceId, String productKey, String deviceName,
                              List<DeviceKey> subDeviceKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST,
                DEVICES, instanceId, productKey, deviceName, SUBSET, DELETE);
        String jsonStr = JsonUtils.toJsonString(subDeviceKey);
        try {
            byte[] content = jsonStr.getBytes(DEFAULT_ENCODING);
            internalRequest.setContent(RestartableInputStream.wrap(content));
            internalRequest.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ListProductResponse getAllSubsets(String instanceId, String productKey, String subProductName,
                                             int pageNo, int pageSize) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PRODUCTS, instanceId, SUBSET);

        internalRequest.addParameter("productKey", productKey);
        if (StringUtils.isNotBlank(subProductName)) {
            internalRequest.addParameter("subProductName", subProductName);
        }
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        return invokeHttpClient(internalRequest, ListProductResponse.class);
    }

    public ListDeviceResponse getAllSubsets(String instanceId, String productKey,
                                             String subProductKey, String deviceName, String name,
                                             int pageNo, int pageSize) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                DEVICES, instanceId, productKey, SUBSET);

        internalRequest.addParameter("subProductKey", subProductKey);
        internalRequest.addParameter("deviceName", deviceName);
        if (StringUtils.isNotBlank(name)) {
            internalRequest.addParameter("name", name);
        }
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        return invokeHttpClient(internalRequest, ListDeviceResponse.class);
    }

    public void addSubsets(String instanceId, String productKey,
                                     List<String> subProductKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST,
                PRODUCTS, instanceId, SUBSET, productKey);
        String jsonStr = JsonUtils.toJsonString(subProductKey);
        try {
            byte[] content = jsonStr.getBytes(DEFAULT_ENCODING);
            internalRequest.setContent(RestartableInputStream.wrap(content));
            internalRequest.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void addSubsets(String instanceId, String productKey, String deviceName,
                           List<DeviceKey> subDeviceKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST,
                DEVICES, instanceId, productKey, deviceName, SUBSET);
        String jsonStr = JsonUtils.toJsonString(subDeviceKey);
        try {
            byte[] content = jsonStr.getBytes(DEFAULT_ENCODING);
            internalRequest.setContent(RestartableInputStream.wrap(content));
            internalRequest.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void importSubsets(String instanceId, String productKey, String deviceName,
                                                   File file) {
        InternalRequest internalRequest = createUploadRequest(
                "importFile", file, HttpMethodName.POST,
                DEVICES, instanceId, productKey, deviceName, SUBSET, IMPORT);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public DeviceSubsetsFileResponse exportSubsets(String instanceId, String productKey, String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST,
                DEVICES, instanceId, productKey, deviceName, SUBSET, EXPORT);
        return invokeHttpClient(internalRequest, DeviceSubsetsFileResponse.class);
    }

    // ruleChain API
    public TopicEncodeResponse topicEncode(TopicEncodeRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, SERVICE,
                BLINK, TOPICS, ENCODE);
        return invokeHttpClient(internalRequest, TopicEncodeResponse.class);
    }

    public TopicDecodeResponse topicDecode(TopicDecodeRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, SERVICE,
                BLINK, TOPICS, DECODE);
        return invokeHttpClient(internalRequest, TopicDecodeResponse.class);
    }

    public AvailableMessageTypeResponse getSourceTypes(String instanceId, BlinkDataPermission permission) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, SERVICE,
                instanceId, MESSAGES, TYPES, SOURCE);
        if (permission != null) {
            internalRequest.addParameter("permission", permission.name());
        }
        return invokeHttpClient(internalRequest, AvailableMessageTypeResponse.class);
    }

    public AvailableMessageTypeResponse getSinkTypes(String instanceId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, SERVICE,
                instanceId, MESSAGES, TYPES, SINK);
        return invokeHttpClient(internalRequest, AvailableMessageTypeResponse.class);
    }

    // service API
    public ConsumerGroupUserInfoResponse getUserInfo(String instanceId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, SERVICE,
                instanceId, CONSUMER, USER);
        return invokeHttpClient(internalRequest, ConsumerGroupUserInfoResponse.class);
    }

    public void sendMessage(String instanceId, String productKey,
                                      String deviceName, SendMessageRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, SERVICE,
                instanceId, productKey, deviceName, MESSAGE);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public CreateConsumerGroupResponse createConsumerGroup(String instanceId, String name) {
        CreateConsumerGroupRequest request = new CreateConsumerGroupRequest(name);
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, SERVICE,
                instanceId, CONSUMER);
        return invokeHttpClient(internalRequest, CreateConsumerGroupResponse.class);
    }

    public void deleteConsumerGroup(String instanceId, String consumerGroupId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE, SERVICE,
                instanceId, CONSUMER, consumerGroupId);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ListConsumerGroupResponse getConsumerGroupList(String instanceId,
                                                          CommonListRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET,
                SERVICE, instanceId, CONSUMER);
        if (request.getPageNo() != null) {
            internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        }
        if (request.getPageSize() != null) {
            internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        }
        if (StringUtils.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        if (StringUtils.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        return invokeHttpClient(internalRequest, ListConsumerGroupResponse.class);
    }

    public ResetConsumerGroupUserPwdResponse resetUserPwd(String instanceId, String username) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.PUT, SERVICE,
                instanceId, CONSUMER, username, RESET);
        return invokeHttpClient(internalRequest, ResetConsumerGroupUserPwdResponse.class);
    }

    public ListInstanceServiceStateResponse listInstanceServiceState(String instanceId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, SERVICE,
                instanceId, STATE);
        return invokeHttpClient(internalRequest, ListInstanceServiceStateResponse.class);
    }

    public ConsumerGroupQueueInfoResponse getQueueInfo(String instanceId, String consumerGroupId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, SERVICE,
                instanceId, CONSUMER, consumerGroupId);
        return invokeHttpClient(internalRequest, ConsumerGroupQueueInfoResponse.class);
    }

    public ListSubResponse getSubList(String instanceId, CommonListRequest request) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, SERVICE,
                instanceId, SUBSCRIPTIONS);
        if (request.getPageNo() != null) {
            internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        }
        if (request.getPageSize() != null) {
            internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        }
        if (StringUtils.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        if (StringUtils.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        return invokeHttpClient(internalRequest, ListSubResponse.class);
    }

    @Deprecated
    public ProductSubscriptionResponse getSub(String instanceId, String productKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, SERVICE,
                instanceId, productKey, SUBSCRIPTIONS);
        return invokeHttpClient(internalRequest, ProductSubscriptionResponse.class);
    }

    public ProductSubscriptionResponse getSubTopics(String instanceId, String productKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, SERVICE,
                instanceId, productKey, SUBSCRIPTIONS, TOPICS);
        return invokeHttpClient(internalRequest, ProductSubscriptionResponse.class);
    }

    @Deprecated
    public void updateSub(String instanceId, String productKey,
                                    UpdateProductSubscriptionRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, SERVICE,
                instanceId, productKey, SUBSCRIPTIONS);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void updateSubTopics(String instanceId, String productKey,
                          UpdateProductSubscriptionRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, SERVICE,
                instanceId, productKey, SUBSCRIPTIONS, TOPICS);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void updateSubState(String instanceId, String productKey,
                                         boolean state) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST, SERVICE,
                instanceId, productKey, SUBSCRIPTIONS);
        internalRequest.addParameter("state", Boolean.toString(state));
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ProductSubscriptionResponse getMessageType(String instanceId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, SERVICE,
                instanceId, MESSAGE, TYPE);
        return invokeHttpClient(internalRequest, ProductSubscriptionResponse.class);
    }

    // gateway
    public void updateGatewayState(String instanceId, String productKey, String deviceName, boolean state) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.PUT, SERVICE,
                instanceId, productKey, deviceName, GATEWAY);
        internalRequest.addParameter("state", Boolean.toString(state));
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ComputationSourceResponse getGatewayInfo(String instanceId, String productKey, String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, SERVICE,
                instanceId, productKey, deviceName, GATEWAY);
        return invokeHttpClient(internalRequest, ComputationSourceResponse.class);
    }

    public void resetGatewaySecret(String instanceId, String productKey, String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.PUT, SERVICE,
                instanceId, productKey, deviceName, GATEWAY, RESET);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // userlog
    public ListUserLogResponse getLogList(String instanceId, ListUserLogRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, LOG, instanceId);

        internalRequest.addParameter("logType", request.getLogType());
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        if (StringUtils.isNotEmpty(request.getLogSubType())) {
            internalRequest.addParameter("logSubType", request.getLogSubType());
        }
        if (StringUtils.isNotEmpty(request.getFlag())) {
            internalRequest.addParameter("flag", request.getFlag());
        }
        if (StringUtils.isNotEmpty(request.getProductKey())) {
            internalRequest.addParameter("productKey", request.getProductKey());
        }
        if (StringUtils.isNotEmpty(request.getDeviceName())) {
            internalRequest.addParameter("deviceName", request.getDeviceName());
        }
        if (StringUtils.isNotEmpty(request.getKeyword())) {
            internalRequest.addParameter("keyword", request.getKeyword());
        }
        if (request.getBeginTime() != null) {
            internalRequest.addParameter("beginTime", Long.toString(request.getBeginTime()));
        }
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", Long.toString(request.getEndTime()));
        }
        return invokeHttpClient(internalRequest, ListUserLogResponse.class);
    }

    // group
    public GroupInfo createGroup(String instanceId, CreateGroupRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, GROUPS, instanceId);
        return invokeHttpClient(internalRequest, GroupInfo.class);
    }

    public ListGroupResponse getGroupList(String instanceId, ListGroupRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, GROUPS, instanceId);
        if (StringUtils.isNotEmpty(request.getSuperGroupId())) {
            internalRequest.addParameter("superGroupId", request.getSuperGroupId());
        }
        if (StringUtils.isNotEmpty(request.getRootGroupId())) {
            internalRequest.addParameter("rootGroupId", request.getRootGroupId());
        }
        if (StringUtils.isNotEmpty(request.getKeyword())) {
            internalRequest.addParameter("keyword", request.getKeyword());
        }
        if (request.getPageNo() != null) {
            internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        }
        if (request.getPageSize() != null) {
            internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        }
        return invokeHttpClient(internalRequest, ListGroupResponse.class);
    }

    public void deleteGroup(String instanceId, String groupId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE, GROUPS, instanceId, groupId);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public GroupInfo updateGroup(String instanceId, String groupId, UpdateGroupInfoRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, GROUPS, instanceId, groupId);
        return invokeHttpClient(internalRequest, GroupInfo.class);
    }

    public GroupInfo getGroup(String instanceId, String groupId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, GROUPS, instanceId, groupId);
        return invokeHttpClient(internalRequest, GroupInfo.class);
    }

    public void addDeviceToGroup(String instanceId, String groupId, List<DeviceKey> listDeviceKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST, GROUPS, instanceId, groupId, DEVICES);
        String jsonStr = JsonUtils.toJsonString(listDeviceKey);
        try {
            byte[] content = jsonStr.getBytes(DEFAULT_ENCODING);
            internalRequest.setContent(RestartableInputStream.wrap(content));
            internalRequest.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void deleteDeviceFromGroup(String instanceId, String groupId, List<DeviceKey> listDeviceKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.PUT, GROUPS, instanceId, groupId, DEVICES, DELETE);
        String jsonStr = JsonUtils.toJsonString(listDeviceKey);
        try {
            byte[] content = jsonStr.getBytes(DEFAULT_ENCODING);
            internalRequest.setContent(RestartableInputStream.wrap(content));
            internalRequest.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ListDeviceByGroupResponse listDeviceByGroup(String instanceId, String groupId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, GROUPS, instanceId, groupId, DEVICES);
        return invokeHttpClient(internalRequest, ListDeviceByGroupResponse.class);
    }

    // component
    public ListBindComponentResponse bindProductComponents(String instanceId, String productKey,
                                                           BindComponentRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, PRODUCTS, instanceId, productKey, COMPONENTS);
        return invokeHttpClient(internalRequest, ListBindComponentResponse.class);
    }

    /**
     * @Deprecated As of release v0.10.281,
     * replaced by {@link #unbindProductComponent(String, String, String)}
     */
    @Deprecated
    public void unbindProductComponent(String instanceId, String productKey,
                                                     String bindName, BindComponentRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.DELETE, PRODUCTS, instanceId, productKey, COMPONENTS, bindName);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void unbindProductComponent(String instanceId, String productKey,
                                       String bindName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE, PRODUCTS, instanceId, productKey, COMPONENTS,
                bindName);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ListBindComponentResponse listProductComponents(String instanceId, String productKey) {

        return listProductComponents(instanceId, productKey, null);
    }

    public ListBindComponentResponse listProductComponents(String instanceId, String productKey, String version) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, PRODUCTS, instanceId, productKey, COMPONENTS);
        if (StringUtils.isNotBlank(version)) {
            internalRequest.addParameter("version", version);
        }
        return invokeHttpClient(internalRequest, ListBindComponentResponse.class);
    }

    public ListBindComponentResponse listDeviceComponents(String instanceId, String productKey,
                                                         String deviceName, String bindName) {
        return listDeviceComponents(instanceId, productKey, deviceName, bindName, null);
    }

    public ListBindComponentResponse listDeviceComponents(String instanceId, String productKey,
                                                          String deviceName, String bindName, String version) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, DEVICES,
                instanceId, productKey, deviceName, COMPONENTS);
        if (StringUtils.isNotEmpty(bindName)) {
            internalRequest.addParameter("bindName", bindName);
        }

        if (StringUtils.isNotBlank(version)) {
            internalRequest.addParameter("version", version);
        }
        return invokeHttpClient(internalRequest, ListBindComponentResponse.class);
    }

    // linkage
    public CreateLinkageRuleResponse createLinkageRule(String instanceId, CreateLinkageRuleRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, PLATFORM_LINKAGES, instanceId);
        return invokeHttpClient(internalRequest, CreateLinkageRuleResponse.class);
    }

    public void deleteLinkageRule(String instanceId, BatchDeleteLinkageRuleRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, PLATFORM_LINKAGES, instanceId, BATCH_DELETE);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void updateLinkageRule(String instanceId, String ruleId, UpdateLinkageRuleRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, PLATFORM_LINKAGES, instanceId, ruleId);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public LinkageRuleInfo getLinkageRule(String instanceId, String ruleId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, PLATFORM_LINKAGES, instanceId, ruleId);
        return invokeHttpClient(internalRequest, LinkageRuleInfo.class);
    }

    public ListLinkageRuleResponse listLinkageRule(String instanceId, int pageNo, int pageSize, String name) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, PLATFORM_LINKAGES, instanceId);
        if (StringUtils.isNotEmpty(name)) {
            internalRequest.addParameter("name", name);
        }
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        return invokeHttpClient(internalRequest, ListLinkageRuleResponse.class);
    }

    public void updateLinkageRuleState(String instanceId, String ruleId, boolean state) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.PUT, PLATFORM_LINKAGES, instanceId, ruleId, STATE);
        internalRequest.addParameter("state", Boolean.toString(state));

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // products categories
    public ListProductCategoryResponse listProductCategory() {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, PRODUCTS, CATEGORIES);

        return invokeHttpClient(internalRequest, ListProductCategoryResponse.class);
    }

    // products repositories
    public ListProductModelResponse listProductModel(ListProductModelRequest request) {

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, PRODUCTS, REPOSITORIES, MODELS);
        if (StringUtils.isNotEmpty(request.getProductName())) {
            internalRequest.addParameter("productName", request.getProductName());
        }
        if (StringUtils.isNotEmpty(request.getKeyword())) {
            internalRequest.addParameter("keyword", request.getKeyword());
        }
        if (request.getDeviceType() != null) {
            internalRequest.addParameter("deviceType", request.getDeviceType().name());
        }
        if (request.getType() != null) {
            internalRequest.addParameter("type", request.getType().name());
        }
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        return invokeHttpClient(internalRequest, ListProductModelResponse.class);
    }

    public ProductModelInfo getMainProductInfo(String modelId) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, PRODUCTS, REPOSITORIES, MODELS, modelId, INFO);

        return invokeHttpClient(internalRequest, ProductModelInfo.class);
    }

    public ListBindComponentResponse getMainProductComponents(String modelId) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, PRODUCTS, REPOSITORIES, MODELS, modelId, COMPONENTS);

        return invokeHttpClient(internalRequest, ListBindComponentResponse.class);
    }

    public ListFeaturePropertyResponse getMainProductProperties(String modelId,
                                                                                   int pageNo, int pageSize) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, PRODUCTS, REPOSITORIES, MODELS,
                modelId, FEATURE, PROPERTIES);
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        return invokeHttpClient(internalRequest, ListFeaturePropertyResponse.class);
    }

    public ListFeatureEventResponse getMainProductEvents(String modelId, int pageNo, int pageSize) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, PRODUCTS, REPOSITORIES, MODELS,
                modelId, FEATURE, EVENT);
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        return invokeHttpClient(internalRequest, ListFeatureEventResponse.class);
    }

    public ListFeatureCommandResponse getMainProductCommands(String modelId,
                                                                                int pageNo, int pageSize) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, PRODUCTS, REPOSITORIES, MODELS,
                modelId, FEATURE, COMMAND);
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        return invokeHttpClient(internalRequest, ListFeatureCommandResponse.class);
    }

    public DtmlDetailResponse getMainProductDTMLDetail(String modelId) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, PRODUCTS, REPOSITORIES, MODELS,
                modelId, FEATURE, DETAIL);

        return invokeHttpClient(internalRequest, DtmlDetailResponse.class);
    }

    public SaveCustomProductModelResponse saveCustomProductModel(String instanceId, String productKey,
                                                                 SaveCustomProductModelRequest request) {

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, PRODUCTS, REPOSITORIES, MODELS,
                CUSTOM, instanceId, productKey);

        return invokeHttpClient(internalRequest, SaveCustomProductModelResponse.class);
    }

    public void deleteCustomProductModel(String modelId) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE, PRODUCTS, REPOSITORIES, MODELS,
                CUSTOM, modelId);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public SimpleProductModelInfo updateCustomProductModel(String modelId, UpdateCustomProductModelRequest request) {

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, PRODUCTS, REPOSITORIES, MODELS,
                CUSTOM, modelId);

        return invokeHttpClient(internalRequest, SimpleProductModelInfo.class);
    }

    // product models
    public ProductModelInfo exportProductModel(String instanceId, String productKey) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, PRODUCTS, MODELS, instanceId, productKey);

        return invokeHttpClient(internalRequest, ProductModelInfo.class);
    }

    public void importProductModel(String instanceId, ImportProductModelRequest request) {

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, PRODUCTS, MODELS, instanceId);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void importProductModel(String instanceId, String modelId, CreateProductByModelRequest request) {

        InternalRequest internalRequest = createRequest(
                 request, HttpMethodName.POST, PRODUCTS, MODELS, instanceId, modelId);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // Alarm rule
    public AlarmRuleInfo createAlarmRule(String instanceId, CreateAlarmRuleRequest request) {

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, ALARMS, RULES, instanceId);

        return invokeHttpClient(internalRequest, AlarmRuleInfo.class);
    }

    public AlarmRuleInfo updateAlarmRule(String instanceId, String ruleId, UpdateAlarmRuleRequest request) {

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, ALARMS, RULES, instanceId, ruleId);

        return invokeHttpClient(internalRequest, AlarmRuleInfo.class);
    }

    public AlarmRuleInfo getAlarmRule(String instanceId, String ruleId) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, ALARMS, RULES, instanceId, ruleId);

        return invokeHttpClient(internalRequest, AlarmRuleInfo.class);
    }

    public ListAlarmRuleResponse listAlarmRule(String instanceId, String name, int pageNo, int pageSize) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, ALARMS, RULES, instanceId);
        if (StringUtils.isNotEmpty(name)) {
            internalRequest.addParameter("name", name);
        }
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        return invokeHttpClient(internalRequest, ListAlarmRuleResponse.class);
    }

    public void updateAlarmRuleActiveState(String instanceId, String ruleId, boolean active) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.PUT, ALARMS, RULES, instanceId, ruleId, STATES);
        internalRequest.addParameter("active", Boolean.toString(active));

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void triggerAlarmRule(String instanceId, String ruleId, TriggerAlarmRequest request) {

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, ALARMS, RULES, instanceId, ruleId, TRIGGER);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void batchDeleteAlarmRule(String instanceId, BatchDeleteAlarmRuleRequest request) {

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, ALARMS, RULES, instanceId, BATCH, DELETE);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // Alarm records
    public void deleteAlarmRecord(String instanceId, String recordId) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE, ALARMS, RECORDS, instanceId, recordId);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void batchProcessAlarmRecord(String instanceId, BatchProcessAlarmRecordRequest request) {

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, ALARMS, RECORDS, instanceId, BATCH, PROCESS);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public AlarmRecordInfo getAlarmRecord(String instanceId, String recordId) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, ALARMS, RECORDS, instanceId, recordId);

        return invokeHttpClient(internalRequest, AlarmRecordInfo.class);
    }

    public ListAlarmRecordResponse listAlarmRecord(String instanceId, ListAlarmRecordRequest request) {

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, ALARMS, RECORDS, instanceId);
        if (request.getPageSize() != null) {
            internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        }
        if (StringUtils.isNotEmpty(request.getCursor())) {
            internalRequest.addParameter("cursor", request.getCursor());
        }
        if (StringUtils.isNotEmpty(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        if (request.getAlarmLevel() != null) {
            internalRequest.addParameter("alarmLevel", Integer.toString(request.getAlarmLevel()));
        }
        if (request.getStatus() != null) {
            internalRequest.addParameter("status", request.getStatus().name());
        }
        return invokeHttpClient(internalRequest, ListAlarmRecordResponse.class);
    }

    // 配置管理 fm
    public ConfigManagementListResponse getConfigList(String instanceId, String productKey,
                                                      String configName, Integer pageNo,
                                                      Integer pageSize) {

        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET, FM, CONFIG, instanceId);
        if (StringUtils.isNotEmpty(productKey)) {
            internalRequest.addParameter("productKey", productKey);
        }
        if (StringUtils.isNotEmpty(configName)) {
            internalRequest.addParameter("configName", configName);
        }
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));

        return invokeHttpClient(internalRequest, ConfigManagementListResponse.class);
    }

    public void addConfig(String instanceId, AddConfigRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, FM, CONFIG, instanceId);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void deleteConfig(String instanceId, String productKey, String configId) {

        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.DELETE, FM, CONFIG, instanceId, productKey, configId);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void modifyConfig(String instanceId, String productKey, String configId, AddConfigRequest request) {

        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.PUT, FM, CONFIG, instanceId, productKey, configId);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void addConfigVersion(String instanceId, String productKey, String configId, String version, File file) {
        InternalRequest internalRequest = createUploadRequest("importFile", file,
                HttpMethodName.POST, FM, CONFIG, instanceId, productKey, configId);
        internalRequest.addParameter("version", version);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void deleteConfigVersion(String instanceId, String productKey,
                                    String configId, String configVersion) {

        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.DELETE, FM, CONFIG, instanceId, productKey, configId, CONFIG_VERSION);
        internalRequest.addParameter("configVersion", configVersion);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public GetConfigVersionResponse downloadConfigVersion(String instanceId, String productKey,
                                                          String configId, String configVersion) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, FM, CONFIG, instanceId, productKey, configId, URL);
        internalRequest.addParameter("configVersion", configVersion);
        return invokeHttpClient(internalRequest, GetConfigVersionResponse.class);
    }

    public ConfigVersionListResponse getConfigVersionList(String instanceId, String productKey, String configId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, FM, CONFIG, instanceId, productKey, configId, VERSIONS);
        return invokeHttpClient(internalRequest, ConfigVersionListResponse.class);
    }

    public ConfigManagementResponse getConfigInfo(String instanceId, String productKey, String configId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, FM, CONFIG, instanceId, productKey, configId);
        return invokeHttpClient(internalRequest, ConfigManagementResponse.class);
    }

    /**
     * @deprecated As of release v0.10.239,
     * replaced by {@link #getConfigTaskList(String, String, String, int, int, String)}
     */
    @Deprecated
    public ConfigTaskListResponse getTaskList(String instanceId, String productKey,
                                                    String configId, int pageNo, int pageSize,
                                                    String taskId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, FM, CONFIG, instanceId, productKey, configId, TASK);

        if (StringUtils.isNotEmpty(taskId)) {
            internalRequest.addParameter("taskId", taskId);
        }
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));

        return invokeHttpClient(internalRequest, ConfigTaskListResponse.class);
    }

    public ConfigTaskListResponse getConfigTaskList(String instanceId, String productKey,
                                              String configId, int pageNo, int pageSize,
                                              String taskId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, FM, CONFIG, instanceId, productKey, configId, TASK);

        if (StringUtils.isNotEmpty(taskId)) {
            internalRequest.addParameter("taskId", taskId);
        }
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));

        return invokeHttpClient(internalRequest, ConfigTaskListResponse.class);
    }

    /**
     * @deprecated As of release v0.10.239,
     * replaced by {@link #addConfigTask(String, String, String, AddTaskRequest)}
     */
    @Deprecated
    public void addTask(String instanceId, String productKey,
                              String configId, AddTaskRequest request) {
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.POST, FM, CONFIG, instanceId, productKey, configId, TASK);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void addConfigTask(String instanceId, String productKey,
                        String configId, AddTaskRequest request) {
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.POST, FM, CONFIG, instanceId, productKey, configId, TASK);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * @deprecated As of release v0.10.239,
     * replaced by {@link #getConfigTaskDetail(String, String,String, int, int, String)}
     */
    @Deprecated
    public ConfigTaskDetailListResponse getTaskDetail(String instanceId, String productKey,
                                                            String configId, int pageNo,
                                                            int pageSize, String keyword) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, FM, CONFIG, instanceId, productKey, configId, TASK, DETAIL);
        if (StringUtils.isNotEmpty(keyword)) {
            internalRequest.addParameter("keyword", keyword);
        }
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        return invokeHttpClient(internalRequest, ConfigTaskDetailListResponse.class);
    }

    public ConfigTaskDetailListResponse getConfigTaskDetail(String instanceId, String productKey,
                                                      String configId, int pageNo,
                                                      int pageSize, String keyword) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, FM, CONFIG, instanceId, productKey, configId, TASK, DETAIL);
        if (StringUtils.isNotEmpty(keyword)) {
            internalRequest.addParameter("keyword", keyword);
        }
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        return invokeHttpClient(internalRequest, ConfigTaskDetailListResponse.class);
    }

    /**
     * @deprecated As of release v0.10.239,
     * replaced by {@link #addConfigTaskCsv(String, String, String, String, File)}
     */
    @Deprecated
    public void addTaskCsv(String instanceId, String productKey,
                                 String configId, String configVersion, File file) {
        InternalRequest internalRequest = createUploadRequest("importFile", file,
                HttpMethodName.POST, FM, CONFIG, instanceId, productKey, configId, TASK, CSV);
        internalRequest.addParameter("configVersion", configVersion);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void addConfigTaskCsv(String instanceId, String productKey,
                           String configId, String configVersion, File file) {
        InternalRequest internalRequest = createUploadRequest("importFile", file,
                HttpMethodName.POST, FM, CONFIG, instanceId, productKey, configId, TASK, CSV);
        internalRequest.addParameter("configVersion", configVersion);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // bridge
    public GetBridgeListResponse getBridgeList(String instanceId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, SERVICE, instanceId);
        return invokeHttpClient(internalRequest, GetBridgeListResponse.class);
    }

    public ServiceInfoResponse getBridge(String instanceId, String serviceId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, SERVICE, instanceId, serviceId);
        return invokeHttpClient(internalRequest, ServiceInfoResponse.class);
    }

    public void updateBridgeState(String instanceId, String serviceId, boolean state) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.PUT, SERVICE, instanceId, serviceId);
        internalRequest.addParameter("state", String.valueOf(state));
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void bridgeReset(String instanceId, String serviceId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.PUT, SERVICE, instanceId, serviceId, RESET);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // ota products
    public CreateOtaProductResponse createOtaProduct(String instanceId, String productKey,
                                                     CreateOtaProductRequest request) {
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.POST, OTA, PRODUCT, instanceId, productKey);

        return invokeHttpClient(internalRequest, CreateOtaProductResponse.class);
    }

    public void deleteOtaProduct(String instanceId, String productKey) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.DELETE, OTA, PRODUCT, instanceId, productKey);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public OtaProductDetail getOtaProductDetail(String instanceId, String productKey) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, PRODUCT, instanceId, productKey);

        return invokeHttpClient(internalRequest, OtaProductDetail.class);
    }

    public ListOtaProductResponse listOtaProduct(String instanceId, ListOtaProductRequest request) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, PRODUCT, instanceId);

        internalRequest.addParameter("page", Integer.toString(request.getPage()));
        internalRequest.addParameter("perPage", Integer.toString(request.getPerPage()));
        if (StringUtils.isNotBlank(request.getSearch())) {
            internalRequest.addParameter("search", request.getSearch());
        }
        return invokeHttpClient(internalRequest, ListOtaProductResponse.class);
    }

    public ListOtaProductOperationResponse listOtaProductOperation(String instanceId, String productKey,
                                                                   ListOtaProductOperationRequest request) {
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.GET, OTA, PRODUCT, instanceId, productKey, OPERATION);

        internalRequest.addParameter("page", Integer.toString(request.getPage()));
        internalRequest.addParameter("perPage", Integer.toString(request.getPerPage()));
        if (StringUtils.isNotBlank(request.getSearch())) {
            internalRequest.addParameter("search", request.getSearch());
        }

        if (StringUtils.isNotBlank(request.getSearch())) {
            internalRequest.addParameter("type", Integer.toString(request.getType()));
        }

        if (StringUtils.isNotBlank(request.getSearch())) {
            internalRequest.addParameter("date", request.getDate());
        }
        return invokeHttpClient(internalRequest, ListOtaProductOperationResponse.class);
    }

    public OtaProductConfig getOtaProductConfig(String instanceId, String productKey) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, PRODUCT, instanceId, productKey, CONFIG);

        return invokeHttpClient(internalRequest, OtaProductConfig.class);
    }

    // ota packages
    public UploadOtaPackageResponse uploadOtaPackage(String instanceId, String productKey,
                                                     UploadOtaPackageRequest request) {
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.POST, OTA, PACKAGES, instanceId, productKey);

        return invokeHttpClient(internalRequest, UploadOtaPackageResponse.class);
    }

    public ListOtaPackageResponse listOtaPackage(String instanceId, String productKey,
                                                 ListOtaPackageRequest request) {
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.GET, OTA, PACKAGES, instanceId, productKey);
        internalRequest.addParameter("page", Integer.toString(request.getPage()));
        internalRequest.addParameter("perPage", Integer.toString(request.getPerPage()));
        if (StringUtils.isNotBlank(request.getSearchStr())) {
            internalRequest.addParameter("searchStr", request.getSearchStr());
        }

        if (StringUtils.isNotBlank(request.getLabel())) {
            internalRequest.addParameter("label", request.getLabel());
        }

        if (request.getOrderBy() != null) {
            internalRequest.addParameter("orderBy", request.getOrderBy().name());
        }

        if (request.getOrderDirection() != null) {
            internalRequest.addParameter("orderDirection", request.getOrderDirection().name());
        }
        return invokeHttpClient(internalRequest, ListOtaPackageResponse.class);
    }

    public void deleteOtaPackage(String instanceId, String productKey, int packageId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.DELETE, OTA, PACKAGES, instanceId, productKey, Integer.toString(packageId));

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public CheckOtaPackageResponse checkOtaPackage(String instanceId, String productKey,
                                                   CheckOtaPackageRequest request) {
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.POST, OTA, PACKAGES, instanceId, productKey, CHECK);

        return invokeHttpClient(internalRequest, CheckOtaPackageResponse.class);
    }

    public OSStsResponse stsOtaPackage(String instanceId, String productKey,
                                       String fileName, Long timestamp, Type type) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.POST, OTA, PACKAGES, instanceId, productKey, STS);
        internalRequest.addParameter("fileName", fileName);
        internalRequest.addParameter("timestamp", Long.toString(timestamp));
        internalRequest.addParameter("type", type.name());

        return invokeHttpClient(internalRequest, OSStsResponse.class);
    }

    // ota task
    public CreateOtaTaskResponse addOtaTask(String instanceId, String productKey, CreateOtaTaskRequest request) {
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.POST, OTA, TASK, instanceId, productKey);

        return invokeHttpClient(internalRequest, CreateOtaTaskResponse.class);
    }

    public void deleteOtaTask(String instanceId, String productKey,
                                     int taskId, DeleteOtaTaskRequest request) {
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.DELETE, OTA, TASK, instanceId,
                productKey, String.valueOf(taskId));
        internalRequest.addParameter("isTrashed", request.getIsTrashed().toString());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public UpdateOtaTaskResponse updateOtaTask(String instanceId, String productKey,
                                               int taskId, UpdateOtaTaskRequest request) {
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.PUT, OTA, TASK, instanceId, productKey, String.valueOf(taskId));

        return invokeHttpClient(internalRequest, UpdateOtaTaskResponse.class);
    }

    public UpdateOtaTaskStatusResponse updateOtaTaskStatus(String instanceId, String productKey,
                                                           int taskId, UpdateOtaTaskStatusRequest request) {
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.PUT, OTA, TASK,
                instanceId, productKey, String.valueOf(taskId), STATUS);

        return invokeHttpClient(internalRequest, UpdateOtaTaskStatusResponse.class);
    }

    public GetOtaTaskResponse getOtaTask(String instanceId, String productKey, int taskId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, TASK,
                instanceId, productKey, String.valueOf(taskId));

        return invokeHttpClient(internalRequest, GetOtaTaskResponse.class);
    }

    public ListOtaTaskResponse listOtaTask(String instanceId, String productKey, ListOtaTaskRequest request) {
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.GET, OTA, TASK, instanceId, productKey);
        internalRequest.addParameter("page", Integer.toString(request.getPage()));
        internalRequest.addParameter("perPage", Integer.toString(request.getPerPage()));
        internalRequest.addParameter("status", request.getStatus());
        if (request.getOrderBy() != null) {
            internalRequest.addParameter("orderBy", request.getOrderBy().name());
        }

        if (request.getDirection() != null) {
            internalRequest.addParameter("direction", request.getDirection().name());
        }

        if (StringUtils.isNotBlank(request.getKeyword())) {
            internalRequest.addParameter("keyword", request.getKeyword());
        }

        if (request.getIsTrashed() != null) {
            internalRequest.addParameter("isTrashed", request.getIsTrashed().toString());
        }

        return invokeHttpClient(internalRequest, ListOtaTaskResponse.class);
    }

    public void createOrUpdateGrayTask(String instanceId, String productKey,
                                                     int taskId, CreateOrUpdateGrayTaskRequest request) {
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.POST, OTA, TASK,
                instanceId, productKey, String.valueOf(taskId), GRAY);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void updateGrayTaskStatus(String instanceId, String productKey, int taskId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.PUT, OTA, TASK,
                instanceId, productKey, String.valueOf(taskId), GRAY, STATUS);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public GrayTask getGrayTask(String instanceId, String productKey, int taskId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, TASK,
                instanceId, productKey, String.valueOf(taskId), GRAY);

        return invokeHttpClient(internalRequest, GrayTask.class);
    }

    // ota device
    public ListAllTestDeviceForTaskResponse listAllTestDeviceForTask(String instanceId, String productKey, int taskId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, DEVICES, instanceId, productKey, TASK, String.valueOf(taskId), TEST);
        return invokeHttpClient(internalRequest, ListAllTestDeviceForTaskResponse.class);
    }

    public SearchDeviceForTaskResponse searchDeviceForTask(String instanceId, String productKey, int taskId,
                                                           SearchType type, String param) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, DEVICES, instanceId, productKey, TASK, String.valueOf(taskId));
        internalRequest.addParameter("type", type.name());
        internalRequest.addParameter("param", param);
        return invokeHttpClient(internalRequest, SearchDeviceForTaskResponse.class);
    }

    // ota statistics
    public OtaTaskIssuedStatisticsResponse otaTaskIssuedStatistics(String instanceId, String productKey, int taskId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, STATISTICS, instanceId, productKey,
                TASK, String.valueOf(taskId), ISSUED);

        return invokeHttpClient(internalRequest, OtaTaskIssuedStatisticsResponse.class);
    }

    public OtaTaskStatisticsResponse otaTaskStatistics(String instanceId, String productKey, int taskId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, STATISTICS, instanceId, productKey,
                TASK, String.valueOf(taskId));

        return invokeHttpClient(internalRequest, OtaTaskStatisticsResponse.class);
    }

    public OtaTaskIssuedFailedStatisticsResponse otaTaskIssuedFailedStatistics(String instanceId, String productKey,
                                                                               int taskId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, STATISTICS, instanceId, productKey,
                TASK, String.valueOf(taskId), ISSUED, FAILED);

        return invokeHttpClient(internalRequest, OtaTaskIssuedFailedStatisticsResponse.class);
    }

    public OtaTaskIssuedFailureInfoStatisticsResponse otaTaskIssuedFailureInfoStatistics(String instanceId,
                                                                                         String productKey,
                                                                                         int taskId,
                                                                                         Stage stage) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, STATISTICS, instanceId, productKey,
                TASK, String.valueOf(taskId), ISSUED, FAILURES);
        internalRequest.addParameter("stage", stage.getStage());
        return invokeHttpClient(internalRequest, OtaTaskIssuedFailureInfoStatisticsResponse.class);
    }

    public OtaTaskStageStatisticsResponse otaTaskStageStatistics(String instanceId, String productKey) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, STATISTICS, instanceId, productKey,
                TASK, STAGE);
        return invokeHttpClient(internalRequest, OtaTaskStageStatisticsResponse.class);
    }

    public OtaTaskProductLineWeekStatisticsResponse otaTaskProductLineWeekStatistics(String instanceId,
                                                                                     String productKey, String date) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, STATISTICS, instanceId, productKey,
                WEEK);
        internalRequest.addParameter("date", date);
        return invokeHttpClient(internalRequest, OtaTaskProductLineWeekStatisticsResponse.class);
    }

    // ota packing
    public ListOtaCompletedPackingResponse listOtaCompletedPacking(String instanceId, String productKey,
                                                                   CommonOtaListRequest request) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, PACKING, instanceId, productKey,
                COMPLETED);
        internalRequest.addParameter("page", Integer.toString(request.getPage()));
        internalRequest.addParameter("perPage", Integer.toString(request.getPerPage()));
        return invokeHttpClient(internalRequest, ListOtaCompletedPackingResponse.class);
    }

    public ListOtaUncompletedPackingResponse listOtaUncompletedPacking(String instanceId, String productKey,
                                                                       CommonOtaListRequest request) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, PACKING, instanceId, productKey,
                UNCOMPLETED);
        internalRequest.addParameter("page", Integer.toString(request.getPage()));
        internalRequest.addParameter("perPage", Integer.toString(request.getPerPage()));
        return invokeHttpClient(internalRequest, ListOtaUncompletedPackingResponse.class);
    }

    public CreateOtaPackingResponse createOtaPacking(String instanceId, String productKey,
                                                     CreateOtaPackingRequest request) {
        InternalRequest internalRequest = createRequest(request,
                HttpMethodName.POST, OTA, PACKING, instanceId, productKey);
        return invokeHttpClient(internalRequest, CreateOtaPackingResponse.class);
    }

    public GetOtaPackingStatusResponse getOtaPackingStatus(String instanceId, String productKey,
                                                           int issueId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, OTA, PACKING, instanceId, productKey, String.valueOf(issueId), STATUS);
        return invokeHttpClient(internalRequest, GetOtaPackingStatusResponse.class);
    }

    public CancelOtaPackingResponse cancelOtaPacking(String instanceId, String productKey,
                                                     int issueId, String stepName, boolean hasNextStep) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.PUT, OTA, PACKING, instanceId, productKey, String.valueOf(issueId), CANCEL);
        internalRequest.addParameter("stepName", stepName);
        internalRequest.addParameter("hasNextStep", Boolean.toString(hasNextStep));
        return invokeHttpClient(internalRequest, CancelOtaPackingResponse.class);
    }

    public DeleteOtaPackingResponse deleteOtaPacking(String instanceId, String productKey,
                                                     int issueId, String stepName, boolean hasNextStep) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.DELETE, OTA, PACKING, instanceId, productKey, String.valueOf(issueId));
        internalRequest.addParameter("stepName", stepName);
        internalRequest.addParameter("hasNextStep", Boolean.toString(hasNextStep));
        return invokeHttpClient(internalRequest, DeleteOtaPackingResponse.class);
    }

    // batch device
    public GetBatchPageResponse getBatchPage(String instanceId, CommonListRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, BATCH, instanceId);
        if (request.getPageNo() != null) {
            internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        }
        if (request.getPageSize() != null) {
            internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        }
        return invokeHttpClient(internalRequest, GetBatchPageResponse.class);
    }

    public BatchInfoResponse getBatch(String instanceId, String batchId, CommonListRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, BATCH, instanceId, batchId);
        if (request.getPageNo() != null) {
            internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        }
        if (request.getPageSize() != null) {
            internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        }
        return invokeHttpClient(internalRequest, BatchInfoResponse.class);
    }

    public BatchDownloadMqtt getBatchMqtt(String instanceId, String batchId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, BATCH, instanceId, batchId, MQTT);

        return invokeHttpClient(internalRequest, BatchDownloadMqtt.class);
    }

    public GetBatchTuplesResponse getBatchTuples(String instanceId, String batchId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, BATCH, instanceId, batchId, DOWNLOAD);

        return invokeHttpClient(internalRequest, GetBatchTuplesResponse.class);
    }

    //statistics
    public StatsDeviceMessageResponse statsDeviceMessage(String instanceId, Cycle cycle,
                                                         long beginTime, long endTime) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, STATISTICS, instanceId, DEVICES, MESSAGE);
        internalRequest.addParameter("cycle", cycle.name());
        internalRequest.addParameter("beginTime", Long.toString(beginTime));
        internalRequest.addParameter("endTime", Long.toString(endTime));

        return invokeHttpClient(internalRequest, StatsDeviceMessageResponse.class);
    }

    public StatsLivelyDeviceResponse statsLivelyDevice(String instanceId, Cycle cycle,
                                                       long beginTime, long endTime) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, STATISTICS, instanceId, DEVICES, LIVELY);
        internalRequest.addParameter("cycle", cycle.name());
        internalRequest.addParameter("beginTime", Long.toString(beginTime));
        internalRequest.addParameter("endTime", Long.toString(endTime));

        return invokeHttpClient(internalRequest, StatsLivelyDeviceResponse.class);
    }

    public StatsDeviceTotalResponse statsDeviceTotal(String instanceId, Cycle cycle,
                                                     long beginTime, long endTime) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, STATISTICS, instanceId, DEVICES, TOTAL);
        internalRequest.addParameter("cycle", cycle.name());
        internalRequest.addParameter("beginTime", Long.toString(beginTime));
        internalRequest.addParameter("endTime", Long.toString(endTime));

        return invokeHttpClient(internalRequest, StatsDeviceTotalResponse.class);
    }

    public DeviceStatesStatsResult statsDeviceStates(String instanceId) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, STATISTICS, instanceId, DEVICES, STATES);

        return invokeHttpClient(internalRequest, DeviceStatesStatsResult.class);
    }

    public StatsProductTotalResponse statsProductTotal(String instanceId, Cycle cycle,
                                                       long beginTime, long endTime) {
        InternalRequest internalRequest = createRequest(new GenericAccountRequest(),
                HttpMethodName.GET, STATISTICS, instanceId, PRODUCTS, TOTAL);
        internalRequest.addParameter("cycle", cycle.name());
        internalRequest.addParameter("beginTime", Long.toString(beginTime));
        internalRequest.addParameter("endTime", Long.toString(endTime));

        return invokeHttpClient(internalRequest, StatsProductTotalResponse.class);
    }

    // instance extensions
    public void disableResource(String instanceId, ResourceSupplier supplier, ResourceType resourceType) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST,
                INSTANCE, instanceId, RESOURCES, DISABLE);
        internalRequest.addParameter("supplier", supplier.name());
        internalRequest.addParameter("resourceType", resourceType.name());

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void unbindResource(String instanceId, ResourceSupplier supplier, ResourceType resourceType) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST,
                INSTANCE, instanceId, RESOURCES, UNBIND);
        internalRequest.addParameter("supplier", supplier.name());
        internalRequest.addParameter("resourceType", resourceType.name());

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public UploadKafkaConfigFileResponse uploadKafkaConfigFile(File file) {
        InternalRequest internalRequest = createUploadRequest("file", file,
                HttpMethodName.POST, PLATFORM_RULECHAINS, FILE, EXTERNAL_KAFKA);
        return invokeHttpClient(internalRequest, UploadKafkaConfigFileResponse.class);
    }

    // TSDB
    public void tsdbModify(String instanceId, String productKey, TsdbInitRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                TSDB, instanceId, productKey);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public TsdbQueryResponse tsdbQueryProperty(String instanceId, String productKey, TsdbQueryRequest request) {

        return tsdbQueryProperty(instanceId, productKey, request, null);
    }

    public TsdbQueryResponse tsdbQueryProperty(String instanceId, String productKey, TsdbQueryRequest request,
                                               String version) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                TSDB, instanceId, productKey, QUERY);
        if (StringUtils.isNotBlank(version)) {
            internalRequest.addParameter("version", version);
        }
        return invokeHttpClient(internalRequest, TsdbQueryResponse.class);
    }

    public TsdbQueryResponse tsdbQueryEvent(String instanceId, String productKey, String event,
                                            TsdbQueryRequest request) {
        return tsdbQueryEvent(instanceId, productKey, event, request, null);
    }

    public TsdbQueryResponse tsdbQueryEvent(String instanceId, String productKey, String event,
                                            TsdbQueryRequest request, String version) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                TSDB, instanceId, productKey, QUERY, event);
        if (StringUtils.isNotBlank(version)) {
            internalRequest.addParameter("version", version);
        }
        return invokeHttpClient(internalRequest, TsdbQueryResponse.class);
    }

    public TsdbMappingResponse tsdbMapping(String instanceId, String productKey, TsdbMappingRequest request) {
        return tsdbMapping(instanceId, productKey, request, null);
    }

    public TsdbMappingResponse tsdbMapping(String instanceId, String productKey, TsdbMappingRequest request,
                                           String version) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                TSDB, instanceId, productKey, MAPPING);
        if (StringUtils.isNotBlank(version)) {
            internalRequest.addParameter("version", version);
        }
        return invokeHttpClient(internalRequest, TsdbMappingResponse.class);
    }

    // BIE Protocols
    public ListProtocolResponse getProtocolList(ResourceSupplier supplier, String protocolName,
                                                int pageNo, int pageSize) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PROTOCOLS);
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));

        if (supplier != null) {
            internalRequest.addParameter("supplier", supplier.name());
        }

        if (StringUtils.isNotBlank(protocolName)) {
            internalRequest.addParameter("protocolName", protocolName);
        }
        return invokeHttpClient(internalRequest, ListProtocolResponse.class);
    }

    public ProtocolResponse createProtocol(CreateProtocolRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                PROTOCOLS);

        return invokeHttpClient(internalRequest, ProtocolResponse.class);
    }

    public void modifyProtocol(String protocolId, BusinessTemplatesAppRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                PROTOCOLS, protocolId);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ProtocolResponse getProtocol(String protocolId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PROTOCOLS, protocolId);

        return invokeHttpClient(internalRequest, ProtocolResponse.class);
    }

    public void deleteProtocol(String protocolId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE,
                PROTOCOLS, protocolId);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public BusinessTemplatesApp getProtocolsApp(String protocolId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PROTOCOLS, protocolId, APP);

        return invokeHttpClient(internalRequest, BusinessTemplatesApp.class);
    }

    public BusinessTemplatesAppRegistryList getProtocolsRegistry(String protocolId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PROTOCOLS, protocolId, REGISTRIES);

        return invokeHttpClient(internalRequest, BusinessTemplatesAppRegistryList.class);
    }

    public BusinessTemplatesAppRegistry addProtocolsRegistry(String protocolId,
                                                             BusinessTemplatesAppRegistryRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                PROTOCOLS, protocolId, REGISTRIES);

        return invokeHttpClient(internalRequest, BusinessTemplatesAppRegistry.class);
    }

    public void modifyProtocolsRegistry(String protocolId, String registryId,
                                        BusinessTemplatesAppRegistryRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                PROTOCOLS, protocolId, REGISTRIES, registryId);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void deleteProtocolsRegistry(String protocolId, String registryId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE,
                PROTOCOLS, protocolId, REGISTRIES, registryId);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }


    public ProtocolsMarkdownResponse modifyProtocolsMarkdown(String protocolId,
                                                             ProtocolsMarkdownRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                PROTOCOLS, protocolId, MARKDOWN);

        return invokeHttpClient(internalRequest, ProtocolsMarkdownResponse.class);
    }

    public ProtocolsMarkdownResponse getProtocolsMarkdown(String protocolId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PROTOCOLS, protocolId, MARKDOWN);

        return invokeHttpClient(internalRequest, ProtocolsMarkdownResponse.class);
    }

    // BIE Nodes
    public ListNodeResponse listNode(String name, int pageNo, int pageSize) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                NODES);

        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));

        if (StringUtils.isNotBlank(name)) {
            internalRequest.addParameter("name", name);
        }
        return invokeHttpClient(internalRequest, ListNodeResponse.class);
    }

    public NodeResponse getNode(String nodeId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                NODES, nodeId);

        return invokeHttpClient(internalRequest, NodeResponse.class);
    }

    public NodeResponse createNode(CreateNodeRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                NODES);

        return invokeHttpClient(internalRequest, NodeResponse.class);
    }

    public void modifyNode(String nodeId, ModifyNodeRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                NODES, nodeId);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void deleteNode(String nodeId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE,
                NODES, nodeId);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public GetInstallNodePropertyResponse getInstallNodeProperty(String nodeId, String property) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                NODES, nodeId, PROPERTIES, property);

        return invokeHttpClient(internalRequest, GetInstallNodePropertyResponse.class);
    }

    public GetInstallNodeInitResponse getInstallNodeInit(String nodeId, InstallMethod method, InstallPlatform platform) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                NODES, nodeId, INIT);

        if (method != null) {
            internalRequest.addParameter("method", method.name());
        }
        if (platform != null) {
            internalRequest.addParameter("platform", platform.name());
        }
        return invokeHttpClient(internalRequest, GetInstallNodeInitResponse.class);
    }

    public void deployProtocol(String nodeId, String protocolId, String instanceId, String amqp, String mqtt,
                               DeployBusinessTemplatesRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                NODES, nodeId, DEPLOY, protocolId, instanceId);

        if (StringUtils.isNotBlank(amqp)) {
            internalRequest.addParameter("amqp", amqp);
        }
        if (StringUtils.isNotBlank(mqtt)) {
            internalRequest.addParameter("mqtt", mqtt);
        }
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // BIE Apps
    public ListNodeAppsResponse listNodeApps(String nodeId, int pageNo, int pageSize) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PROTOCOLS, APPS, NODES, nodeId);

        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));


        return invokeHttpClient(internalRequest, ListNodeAppsResponse.class);
    }

    public ListNodeAppsResponse listInstanceApps(String instanceId, int pageNo, int pageSize) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PROTOCOLS, APPS, INSTANCE, instanceId);

        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));


        return invokeHttpClient(internalRequest, ListNodeAppsResponse.class);
    }

    public void modifyInstanceApps(String appId, String nodeId, String instanceId,
                                   String protocolId, boolean upgrade, BusinessTemplatesAppRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                PROTOCOLS, APPS, appId, nodeId, protocolId, instanceId);

        internalRequest.addParameter("upgrade", Boolean.toString(upgrade));


        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void deleteInstanceApps(String appId, String nodeId, String instanceId, String protocolId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE,
                PROTOCOLS, APPS, appId, nodeId, protocolId, instanceId);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public BusinessTemplatesApp getDeployApp(String appId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PROTOCOLS, APPS, appId, APP);

        return invokeHttpClient(internalRequest, BusinessTemplatesApp.class);
    }

    public AppResponse getApp(String appId) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PROTOCOLS, APPS, appId);

        return invokeHttpClient(internalRequest, AppResponse.class);
    }

    // BIE
    public GetEdgeGatewayNodeDetailResponse getEdgeGatewayNodeDetail(String instanceId, String productKey,
                                                                     String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                EDGE, GATEWAY, instanceId, productKey, deviceName);

        return invokeHttpClient(internalRequest, GetEdgeGatewayNodeDetailResponse.class);
    }

    public ListEdgeGatewayNodeResponse listEdgeGatewayNode(String instanceId, ListEdgeGatewayNodeRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET,
                EDGE, GATEWAY, instanceId);
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        if (StringUtils.isNotEmpty(request.getProductKey())) {
            internalRequest.addParameter("productKey", request.getProductKey());
        }
        if (StringUtils.isNotEmpty(request.getAlias())) {
            internalRequest.addParameter("alias", request.getAlias());
        }
        return invokeHttpClient(internalRequest, ListEdgeGatewayNodeResponse.class);
    }

    public GetNodeAppInfoResponse getEdgeGatewayNodeAppInfo(String instanceId, String productKey,
                                                            String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                EDGE, GATEWAY, instanceId, productKey, deviceName, APPS);

        return invokeHttpClient(internalRequest, GetNodeAppInfoResponse.class);
    }

    public GetAppInfoResponse deployAndUnloadEdgeGatewayApp(String instanceId, String productKey,
                                                            String deviceName, String name,
                                                            AppAction appAction) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.PUT,
                EDGE, GATEWAY, instanceId, productKey, deviceName, APPS);
        internalRequest.addParameter("name", name);
        internalRequest.addParameter("deploy", appAction.name());
        return invokeHttpClient(internalRequest, GetAppInfoResponse.class);
    }

    public GetAppInfoResponse configEdgeGatewayAppEnv(String instanceId, String productKey,
                                                                       String deviceName, String name) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.PUT,
                EDGE, GATEWAY, instanceId, productKey, deviceName, APP, ENV);
        internalRequest.addParameter("name", name);
        return invokeHttpClient(internalRequest, GetAppInfoResponse.class);
    }

    public DriverListResponse getEdgeGatewayNodeDriverRefs(String instanceId, String productKey,
                                                           String deviceName,
                                                           GetNodeDriverRefsRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET,
                EDGE, GATEWAY, instanceId, productKey, deviceName, DRIVERREFS);
        internalRequest.addParameter("driverName", request.getDriverName());
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));

        return invokeHttpClient(internalRequest, DriverListResponse.class);
    }

    public DriverInfo introduceEdgeGatewayDrivers(String instanceId, String productKey,
                                                                      String deviceName,
                                                                      String driverName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST,
                EDGE, GATEWAY, instanceId, productKey, deviceName, INTRODUCE, DRIVER);
        internalRequest.addParameter("driverName", driverName);

        return invokeHttpClient(internalRequest, DriverInfo.class);
    }

    public DriverListResponse getEdgeGatewayDrivers(String instanceId, String productKey,
                                                  String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                EDGE, GATEWAY, instanceId, productKey, deviceName, DRIVERS);

        return invokeHttpClient(internalRequest, DriverListResponse.class);
    }

    public DriverInfo getEdgeGatewayNodeDriverConfig(String instanceId, String productKey,
                                                             String deviceName,
                                                             String driverInstName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                EDGE, GATEWAY, instanceId, productKey, deviceName, DRIVER, CONFIG);
        internalRequest.addParameter("driverInstName", driverInstName);

        return invokeHttpClient(internalRequest, DriverInfo.class);
    }

    public DriverInfo updateEdgeGatewayNodeDriverConfig(String instanceId, String productKey,
                                                        String deviceName,
                                                        String driverInstName,
                                                        UpdateEdgeGatewayNodeDriverConfigRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                EDGE, GATEWAY, instanceId, productKey, deviceName, DRIVER, CONFIG);
        internalRequest.addParameter("driverInstName", driverInstName);

        return invokeHttpClient(internalRequest, DriverInfo.class);
    }

    public void deployEdgeGatewayDriver(String instanceId, String productKey,
                                                        String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST,
                EDGE, GATEWAY, instanceId, productKey, deviceName, DRIVER, DEPLOY);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void deleteEdgeGatewayDriver(String instanceId, String productKey,
                                        String deviceName, String driverInstName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.DELETE,
                EDGE, GATEWAY, instanceId, productKey, deviceName, DRIVER, DELETE);
        internalRequest.addParameter("driverInstName", driverInstName);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void syncEdgeGatewaySubDevices(String instanceId, String productKey,
                                        String deviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST,
                EDGE, GATEWAY, instanceId, productKey, deviceName, SYNC, DEVICE);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public AppInfoListResponse getEdgeGatewayAppList(String instanceId, GetAppListRequest request) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                EDGE, GATEWAY, instanceId, APPS);

        internalRequest.addParameter("name", request.getName());
        internalRequest.addParameter("selector", request.getSelector());
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        return invokeHttpClient(internalRequest, AppInfoListResponse.class);
    }

    public BusinessTemplatesListResponse getEdgeGatewaygetBusinessTemplates(String instanceId,
                                                                            GetBusinessTemplateRequest request) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                EDGE, GATEWAY, instanceId, BUSINESSTEMPLATES);

        internalRequest.addParameter("name", request.getName());
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        return invokeHttpClient(internalRequest, BusinessTemplatesListResponse.class);
    }

    public void deviceBindEdgeGatewayDriver(String instanceId, String productKey,
                                                     String deviceName, BindSubDeviceRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                EDGE, GATEWAY, instanceId, productKey, deviceName, DRIVERS, DEVICE, BIND);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void deviceUnbindEdgeGatewayDriver(String instanceId, String productKey,
                                            String deviceName, BindSubDeviceRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST,
                EDGE, GATEWAY, instanceId, productKey, deviceName, DRIVERS, DEVICE, UNBIND);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public GetBieDeviceListResponse getEdgeGatewayDriverBindSubDeviceList(String instanceId, String productKey,
                                                                          String deviceName, String driverInstName,
                                                                          int pageNo, int pageSize) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                EDGE, GATEWAY, instanceId, productKey, deviceName, driverInstName, DRIVER, DEVICES);
        internalRequest.addParameter("pageNo", Integer.toString(pageNo));
        internalRequest.addParameter("pageSize", Integer.toString(pageSize));
        return invokeHttpClient(internalRequest, GetBieDeviceListResponse.class);
    }

    public GetAccesstemplatesResponse getEdgeGatewayAccessTemplates(String instanceId, String productKey,
                                                                    String deviceName, String driverName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                EDGE, GATEWAY, instanceId, productKey, deviceName, ACCESSTEMPLATES, DEVICEMODELS);
        internalRequest.addParameter("driverName", driverName);

        return invokeHttpClient(internalRequest, GetAccesstemplatesResponse.class);
    }

    public GetBieDeviceConfigResponse updateEdgeGatewaySubDeviceConfig(String instanceId, String productKey,
                                                                    String deviceName, String subProductKey,
                                                                    String subDeviceName,
                                                                    UpdateEdgeGatewaySubDeviceConfigRequest request) {
        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT,
                EDGE, GATEWAY, instanceId, productKey, deviceName, NODEDEVICES, CONFIG);
        internalRequest.addParameter("subProductKey", subProductKey);
        internalRequest.addParameter("subDeviceName", subDeviceName);
        return invokeHttpClient(internalRequest, GetBieDeviceConfigResponse.class);
    }

    public GetBieDeviceConfigResponse getEdgeGatewaySubDeviceConfig(String instanceId, String productKey,
                                                                    String deviceName, String subProductKey,
                                                                    String subDeviceName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                EDGE, GATEWAY, instanceId, productKey, deviceName, NODEDEVICES, CONFIG);
        internalRequest.addParameter("subProductKey", subProductKey);
        internalRequest.addParameter("subDeviceName", subDeviceName);
        return invokeHttpClient(internalRequest, GetBieDeviceConfigResponse.class);
    }

    public UnbindEdgeGatewayDriverSubDeviceListResponse unbindEdgeGatewayDriverSubDeviceList(String instanceId,
                                                                                             String productKey,
                                                                                             String deviceName,
                                                      UnbindEdgeGatewayDriverSubDeviceListRequest request) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                EDGE, GATEWAY, instanceId, productKey, deviceName, UNBIND, DRIVER, SUB_DEVICE);
        internalRequest.addParameter("subDeviceName", request.getSubDeviceName());
        internalRequest.addParameter("pageNo", Integer.toString(request.getPageNo()));
        internalRequest.addParameter("pageSize", Integer.toString(request.getPageSize()));
        return invokeHttpClient(internalRequest, UnbindEdgeGatewayDriverSubDeviceListResponse.class);
    }

    public GetAppInfoResponse getEdgeGatewayAppDetail(String instanceId, String appName) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                EDGE, GATEWAY, instanceId, APP, DETAIL);
        internalRequest.addParameter("appName", appName);

        return invokeHttpClient(internalRequest, GetAppInfoResponse.class);
    }

    // version
    public void publishProductDTMLVersion(String instanceId, String productKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.POST,
                PRODUCTS, VERSION_PATH, instanceId, productKey, PUBLISH);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ListVersionResponse listVersion(String instanceId, String productKey) {
        InternalRequest internalRequest = createRequest(
                new GenericAccountRequest(), HttpMethodName.GET,
                PRODUCTS, VERSION_PATH, instanceId, productKey);
        return invokeHttpClient(internalRequest, ListVersionResponse.class);
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
        request.setCredentials(bceRequest.getRequestCredentials());
        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
            fillInHeadAndBody(bceRequest, request);
        }
        return request;
    }

    private InternalRequest createUploadRequest(String name, File file,
                                                HttpMethodName httpMethod, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION);
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
            FilePart fp;
            try {
                fp = new FilePart(name, file);
            } catch (FileNotFoundException e) {
                throw new BceClientException("文件不存在或路径错误.");
            }
            Part[] parts = {fp};
            MultipartRequestEntity content = new MultipartRequestEntity(parts, new HttpMethodParams());
            request.addHeader(Headers.CONTENT_LENGTH, Long.toString(content.getContentLength()));
            request.addHeader(Headers.CONTENT_TYPE, content.getContentType());
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                content.writeRequest(os);
                request.setContent(RestartableInputStream.wrap(os.toByteArray()));
            } catch (IOException e) {
                throw new BceClientException("内部错误.", e);
            }
            return request;
        }
        throw new BceClientException("上传文件只能使用post或put请求方法.");
    }
    private void fillInHeadAndBody(AbstractBceRequest bceRequest, InternalRequest request) {
        byte[] content = toJson(bceRequest);
        request.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        request.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE);
        request.setContent(RestartableInputStream.wrap(content));
    }
    private byte[] toJson(AbstractBceRequest bceRequest) {
        String jsonStr = JsonUtils.toJsonString(bceRequest);
        try {
            return jsonStr.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
    }
}
