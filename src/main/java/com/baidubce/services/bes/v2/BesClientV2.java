package com.baidubce.services.bes.v2;

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
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import com.baidubce.services.bes.v2.model.cluster.ClusterAddModuleRequest;
import com.baidubce.services.bes.v2.model.cluster.ClusterAddModuleResponse;
import com.baidubce.services.bes.v2.model.cluster.ClusterAssessmentRequest;
import com.baidubce.services.bes.v2.model.cluster.ClusterAssessmentResponse;
import com.baidubce.services.bes.v2.model.cluster.ClusterDeleteRequest;
import com.baidubce.services.bes.v2.model.cluster.ClusterDeleteResponse;
import com.baidubce.services.bes.v2.model.cluster.ClusterDetailRequest;
import com.baidubce.services.bes.v2.model.cluster.ClusterDetailResponse;
import com.baidubce.services.bes.v2.model.cluster.ClusterEipBindRequest;
import com.baidubce.services.bes.v2.model.cluster.ClusterEipUnbindRequest;
import com.baidubce.services.bes.v2.model.cluster.ClusterHttpsRequest;
import com.baidubce.services.bes.v2.model.cluster.ClusterListRequest;
import com.baidubce.services.bes.v2.model.cluster.ClusterMonitorStateRequest;
import com.baidubce.services.bes.v2.model.cluster.ClusterMonitorStateResponse;
import com.baidubce.services.bes.v2.model.cluster.ClusterPasswordRequest;
import com.baidubce.services.bes.v2.model.cluster.ClusterPasswordResponse;
import com.baidubce.services.bes.v2.model.cluster.ClusterResizeRequest;
import com.baidubce.services.bes.v2.model.cluster.ClusterResizeResponse;
import com.baidubce.services.bes.v2.model.cluster.ClusterRestartRequest;
import com.baidubce.services.bes.v2.model.cluster.ClusterStartRequest;
import com.baidubce.services.bes.v2.model.cluster.ClusterStopRequest;
import com.baidubce.services.bes.v2.model.cluster.ClusterTasksRequest;
import com.baidubce.services.bes.v2.model.cluster.ClusterTasksResponse;
import com.baidubce.services.bes.v2.model.cluster.CommonBesV2Response;
import com.baidubce.services.bes.v2.model.cluster.CouponListResponse;
import com.baidubce.services.bes.v2.model.cluster.CreateClusterRequest;
import com.baidubce.services.bes.v2.model.cluster.CreateClusterResponse;
import com.baidubce.services.bes.v2.model.cluster.DataSizeTendencyRequest;
import com.baidubce.services.bes.v2.model.cluster.DataSizeTendencyResponse;
import com.baidubce.services.bes.v2.model.cluster.ListClustersResponse;
import com.baidubce.services.bes.v2.model.cluster.ModuleInfo;
import com.baidubce.services.bes.v2.model.config.ClusterConfigInfoRequest;
import com.baidubce.services.bes.v2.model.config.ClusterConfigInfoResponse;
import com.baidubce.services.bes.v2.model.config.ClusterConfigUpdateRequest;
import com.baidubce.services.bes.v2.model.config.SynonymDictDeleteRequest;
import com.baidubce.services.bes.v2.model.config.SynonymDictListRequest;
import com.baidubce.services.bes.v2.model.config.SynonymDictListResponse;
import com.baidubce.services.bes.v2.model.config.SynonymDictUploadRequest;
import com.baidubce.services.bes.v2.model.inspect.InspectAuthorizeRequest;
import com.baidubce.services.bes.v2.model.inspect.InspectAutoSwitchRequest;
import com.baidubce.services.bes.v2.model.inspect.InspectCheckAutoRequest;
import com.baidubce.services.bes.v2.model.inspect.InspectCheckAutoResponse;
import com.baidubce.services.bes.v2.model.inspect.InspectCheckBusyRequest;
import com.baidubce.services.bes.v2.model.inspect.InspectCheckBusyResponse;
import com.baidubce.services.bes.v2.model.inspect.InspectCreateManualRequest;
import com.baidubce.services.bes.v2.model.inspect.InspectItemListResponse;
import com.baidubce.services.bes.v2.model.inspect.InspectManualConfigGetRequest;
import com.baidubce.services.bes.v2.model.inspect.InspectManualConfigGetResponse;
import com.baidubce.services.bes.v2.model.inspect.InspectManualConfigUpdateRequest;
import com.baidubce.services.bes.v2.model.inspect.InspectManualCountRequest;
import com.baidubce.services.bes.v2.model.inspect.InspectManualCountResponse;
import com.baidubce.services.bes.v2.model.inspect.InspectOverviewLatestRequest;
import com.baidubce.services.bes.v2.model.inspect.InspectOverviewLatestResponse;
import com.baidubce.services.bes.v2.model.inspect.InspectOverviewWeeklyRequest;
import com.baidubce.services.bes.v2.model.inspect.InspectOverviewWeeklyResponse;
import com.baidubce.services.bes.v2.model.inspect.InspectStringSuccessCommonResponse;
import com.baidubce.services.bes.v2.model.inspect.InspectTaskGetRequest;
import com.baidubce.services.bes.v2.model.inspect.InspectTaskGetResponse;
import com.baidubce.services.bes.v2.model.inspect.InspectTaskListRequest;
import com.baidubce.services.bes.v2.model.inspect.InspectTaskListResponse;
import com.baidubce.services.bes.v2.model.instance.DataMigrationConfirmRequest;
import com.baidubce.services.bes.v2.model.instance.DataMigrationInstancesRequest;
import com.baidubce.services.bes.v2.model.instance.DataMigrationInstancesResponse;
import com.baidubce.services.bes.v2.model.instance.DataMigrationRollbackRequest;
import com.baidubce.services.bes.v2.model.instance.DataMigrationSuggestRequest;
import com.baidubce.services.bes.v2.model.instance.DataMigrationSuggestResponse;
import com.baidubce.services.bes.v2.model.instance.InstanceBatchStartRequest;
import com.baidubce.services.bes.v2.model.instance.InstanceBatchStopRequest;
import com.baidubce.services.bes.v2.model.instance.InstanceDeleteRequest;
import com.baidubce.services.bes.v2.model.instance.InstanceDeleteResponse;
import com.baidubce.services.bes.v2.model.instance.InstanceStartRequest;
import com.baidubce.services.bes.v2.model.instance.InstanceStopRequest;
import com.baidubce.services.bes.v2.model.instance.ScaleInInstancesRequest;
import com.baidubce.services.bes.v2.model.instance.ScaleInInstancesResponse;
import com.baidubce.services.bes.v2.model.log.LogExportRecordRequest;
import com.baidubce.services.bes.v2.model.log.LogExportRecordResponse;
import com.baidubce.services.bes.v2.model.log.LogExportRequest;
import com.baidubce.services.bes.v2.model.log.LogExportResponse;
import com.baidubce.services.bes.v2.model.log.LogSearchRequest;
import com.baidubce.services.bes.v2.model.log.LogSearchResponse;
import com.baidubce.services.bes.v2.model.log.LogSettingsRequest;
import com.baidubce.services.bes.v2.model.log.LogStringResultResponse;
import com.baidubce.services.bes.v2.model.plugin.CustomPluginDeleteRequest;
import com.baidubce.services.bes.v2.model.plugin.CustomPluginInstallRequest;
import com.baidubce.services.bes.v2.model.plugin.CustomPluginUninstallRequest;
import com.baidubce.services.bes.v2.model.plugin.CustomPluginUploadRequest;
import com.baidubce.services.bes.v2.model.plugin.DefaultPluginInstallRequest;
import com.baidubce.services.bes.v2.model.plugin.DefaultPluginUninstallRequest;
import com.baidubce.services.bes.v2.model.plugin.NlpDictDisplayRequest;
import com.baidubce.services.bes.v2.model.plugin.NlpDictDisplayResponse;
import com.baidubce.services.bes.v2.model.plugin.NlpDictUpdateRequest;
import com.baidubce.services.bes.v2.model.plugin.PluginInfoRequest;
import com.baidubce.services.bes.v2.model.plugin.PluginInfoResponse;
import com.baidubce.services.bes.v2.model.plugin.PluginStringResultResponse;
import com.baidubce.services.bes.v2.model.renew.AutoRenewRuleCreateRequest;
import com.baidubce.services.bes.v2.model.renew.AutoRenewRuleCreateResponse;
import com.baidubce.services.bes.v2.model.renew.AutoRenewRuleDeleteRequest;
import com.baidubce.services.bes.v2.model.renew.AutoRenewRuleDeleteResponse;
import com.baidubce.services.bes.v2.model.renew.AutoRenewRuleDetailRequest;
import com.baidubce.services.bes.v2.model.renew.AutoRenewRuleDetailResponse;
import com.baidubce.services.bes.v2.model.renew.AutoRenewRuleListRequest;
import com.baidubce.services.bes.v2.model.renew.AutoRenewRuleListResponse;
import com.baidubce.services.bes.v2.model.renew.AutoRenewRuleUpdateRequest;
import com.baidubce.services.bes.v2.model.renew.ClusterRenewRequest;
import com.baidubce.services.bes.v2.model.renew.ClusterRenewResponse;
import com.baidubce.services.bes.v2.model.renew.RenewListRequest;
import com.baidubce.services.bes.v2.model.renew.RenewListResponse;
import com.baidubce.services.bes.v2.model.renew.RenewStringResultResponse;
import com.baidubce.services.bes.v2.model.schedule.ScheduleCreateRequest;
import com.baidubce.services.bes.v2.model.schedule.ScheduleDeleteRequest;
import com.baidubce.services.bes.v2.model.schedule.ScheduleListRequest;
import com.baidubce.services.bes.v2.model.schedule.ScheduleListResponse;
import com.baidubce.services.bes.v2.model.schedule.ScheduleStringResultResponse;
import com.baidubce.services.bes.v2.model.schedule.ScheduleUpdateRequest;
import com.baidubce.services.bes.v2.model.tag.TagBatchInsertRequest;
import com.baidubce.services.bes.v2.model.tag.TagListResponse;
import com.baidubce.services.bes.v2.model.tag.TagUpdateRequest;
import com.baidubce.util.DateUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.util.Mimetypes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static com.baidubce.util.Validate.checkListNotEmpty;
import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;

/**
 * BES v2 client.
 */
public class BesClientV2 extends AbstractBceClient {
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};
    private static final HttpResponseHandler[] BES_V2_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };
    private static final String X_REGION = "x-Region";
    private static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
    private static final String MULTIPART_CONTENT_TYPE_PREFIX = "multipart/form-data; boundary=";
    private static final String BASE_PATH = "/api/bes/cluster";
    private static final String V2 = "/v2";
    private static final String MIGRATE_V1 = "/migrate/v1";
    private static final String CLUSTER_ID = "clusterId";
    private static final String MODULES = "modules";
    private static final String PAYMENT_TYPE = "paymentType";
    private static final String RESIZE_MODE = "resizeMode";
    private static final String ENABLE_MONITOR = "enableMonitor";
    private static final String INSTANCE_ID = "instanceId";
    private static final String MODULE_TYPE = "moduleType";
    private static final String PLUGIN_NAME = "pluginName";
    private static final String FILE = "file";
    private static final long MAX_UPLOAD_FILE_SIZE = 5L * 1024 * 1024 * 1024;
    private static final String NOT_NULL_MESSAGE = "request should not be null.";
    private static final String NOT_EMPTY_PARAMETER_MESSAGE = "The parameter %s should not be null or empty string.";
    private static final String NOT_NULL_PARAMETER_MESSAGE = "The parameter %s should not be null.";
    private final String region;

    public BesClientV2() {
        this(new BceClientConfiguration(), "bj");
    }

    public BesClientV2(BceClientConfiguration clientConfiguration) {
        this(clientConfiguration, "bj");
    }

    public BesClientV2(BceClientConfiguration clientConfiguration, String region) {
        super(clientConfiguration, BES_V2_HANDLERS);
        this.region = region;
    }

    @Override
    public String computeServiceId() {
        return "bes";
    }

    /** ========================================= Cluster API ================================================================ */

    /**
     * 创建集群
     * @param request 创建集群请求
     * @return 创建集群响应
     */
    public CreateClusterResponse createCluster(CreateClusterRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getName(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "name"));
        checkStringNotEmpty(request.getPassword(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "password"));
        checkStringNotEmpty(request.getSecurityGroupId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "securityGroupId"));
        checkStringNotEmpty(request.getSubnetUuid(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "subnetUuid"));
        checkStringNotEmpty(request.getAvailableZone(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "availableZone"));
        checkStringNotEmpty(request.getVpcId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "vpcId"));
        checkStringNotEmpty(request.getVersion(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "version"));
        checkListNotEmpty(request.getModules(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, MODULES));
        for (ModuleInfo moduleInfo : request.getModules()) {
            checkNotNull(moduleInfo, String.format(NOT_NULL_PARAMETER_MESSAGE, MODULES));
            checkStringNotEmpty(moduleInfo.getType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "modules.type"));
            checkNotNull(moduleInfo.getInstanceNum(), String.format(NOT_NULL_PARAMETER_MESSAGE, "modules.instanceNum"));
            checkStringNotEmpty(moduleInfo.getSlotType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "modules.slotType"));
            checkNotNull(moduleInfo.getDiskSlotInfo(), String.format(NOT_NULL_PARAMETER_MESSAGE, "modules.diskSlotInfo"));
            checkNotNull(moduleInfo.getDiskSlotInfo().getSize(),
                    String.format(NOT_NULL_PARAMETER_MESSAGE, "modules.diskSlotInfo.size"));
            checkStringNotEmpty(moduleInfo.getDiskSlotInfo().getType(),
                    String.format(NOT_EMPTY_PARAMETER_MESSAGE, "modules.diskSlotInfo.type"));
            checkNotNull(moduleInfo.getDiskSlotInfo().getCdsExtraIo(),
                    String.format(NOT_NULL_PARAMETER_MESSAGE, "modules.diskSlotInfo.cdsExtraIo"));
        }
        checkNotNull(request.getBilling(), String.format(NOT_NULL_PARAMETER_MESSAGE, "billing"));
        checkStringNotEmpty(request.getBilling().getPaymentType(),
                String.format(NOT_EMPTY_PARAMETER_MESSAGE, "billing.paymentType"));
        return post(request, CreateClusterResponse.class, BASE_PATH, "/create");
    }

    /**
     * 查看集群列表
     * @param request 查看集群列表请求
     * @return 查看集群列表响应
     */
    public ListClustersResponse listClusters(ClusterListRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkNotNull(request.getPageNo(), String.format(NOT_NULL_PARAMETER_MESSAGE, "pageNo"));
        checkNotNull(request.getPageSize(), String.format(NOT_NULL_PARAMETER_MESSAGE, "pageSize"));
        return post(request, ListClustersResponse.class, BASE_PATH, V2, "/list");
    }

    /**
     * 查看集群详情信息
     * @param request 查看集群详情请求
     * @return 查看集群详情响应
     */
    public ClusterDetailResponse getClusterDetail(ClusterDetailRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, ClusterDetailResponse.class, BASE_PATH, V2, "/detail");
    }

    /**
     * 删除集群
     * @param request 删除集群请求
     * @return 删除集群响应
     */
    public ClusterDeleteResponse deleteCluster(ClusterDeleteRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, ClusterDeleteResponse.class, BASE_PATH, V2, "/delete");
    }

    /**
     * 启动集群
     * @param request 启动集群请求
     * @return 启动集群响应
     */
    public CommonBesV2Response startCluster(ClusterStartRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, CommonBesV2Response.class, BASE_PATH, "/start");
    }

    /**
     * 停止集群
     * @param request 停止集群请求
     * @return 停止集群响应
     */
    public CommonBesV2Response stopCluster(ClusterStopRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, CommonBesV2Response.class, BASE_PATH, "/stop");
    }

    /**
     * 重启集群
     * @param request 重启集群请求
     * @return 重启集群响应
     */
    public CommonBesV2Response restartCluster(ClusterRestartRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, CommonBesV2Response.class, BASE_PATH, "/restart");
    }

    /**
     * 重置密码
     * @param request 重置密码请求
     * @return 重置密码响应
     */
    public ClusterPasswordResponse resetClusterPassword(ClusterPasswordRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getNewPassword(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "newPassword"));
        checkStringNotEmpty(request.getConfirmPassword(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "confirmPassword"));
        return post(request, ClusterPasswordResponse.class, BASE_PATH, "/password", "/reset");
    }

    /**
     * EIP绑定
     * @param request EIP绑定请求
     * @return EIP绑定响应
     */
    public CommonBesV2Response bindEip(ClusterEipBindRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "instanceId"));
        checkStringNotEmpty(request.getInstanceType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "instanceType"));
        checkStringNotEmpty(request.getEip(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "eip"));
        return post(request, CommonBesV2Response.class, BASE_PATH, "/eip", "/bind");
    }

    /**
     * EIP解绑
     * @param request EIP解绑请求
     * @return EIP解绑响应
     */
    public CommonBesV2Response unbindEip(ClusterEipUnbindRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getDeployId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "deployId"));
        checkStringNotEmpty(request.getModuleTemplateName(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "moduleTemplateName"));
        return post(request, CommonBesV2Response.class, BASE_PATH, "/eip", "/unbind");
    }

    /**
     * 开启和关闭https
     * @param request 开启和关闭https请求
     * @return 开启和关闭https响应
     */
    public CommonBesV2Response transformClusterHttps(ClusterHttpsRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkNotNull(request.getEnableSSL(), String.format(NOT_NULL_PARAMETER_MESSAGE, "enableSSL"));
        return post(request, CommonBesV2Response.class, BASE_PATH, V2, "/esProtocolTransform");
    }

    /**
     * 扩容集群
     * @param request 扩容集群请求
     * @return 扩容集群响应
     */
    public ClusterResizeResponse resizeCluster(ClusterResizeRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkListNotEmpty(request.getModules(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, MODULES));
        checkStringNotEmpty(request.getPaymentType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, PAYMENT_TYPE));
        checkStringNotEmpty(request.getResizeMode(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, RESIZE_MODE));
        return post(request, ClusterResizeResponse.class, BASE_PATH, "/resize");
    }

    /**
     * 新增节点类型
     * @param request 新增节点类型请求
     * @return 新增节点类型响应
     */
    public ClusterAddModuleResponse addModule(ClusterAddModuleRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkListNotEmpty(request.getModules(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, MODULES));
        checkStringNotEmpty(request.getResizeMode(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, RESIZE_MODE));
        checkStringNotEmpty(request.getPaymentType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, PAYMENT_TYPE));
        return post(request, ClusterAddModuleResponse.class, BASE_PATH, "/addModule");
    }

    /**
     * 是否开启Grafana监控
     * @param request 是否开启Grafana监控请求
     * @return 是否开启Grafana监控响应
     */
    public ClusterMonitorStateResponse setGrafanaMonitorState(ClusterMonitorStateRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkNotNull(request.getEnableMonitor(), String.format(NOT_NULL_PARAMETER_MESSAGE, ENABLE_MONITOR));
        return post(request, ClusterMonitorStateResponse.class, BASE_PATH, "/monitor_state");
    }

    /**
     * 智能评估
     * @param request 智能评估请求
     * @return 智能评估响应
     */
    public ClusterAssessmentResponse sourceAssess(ClusterAssessmentRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getUseType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "useType"));
        checkNotNull(request.getOriginDataSize(), String.format(NOT_NULL_PARAMETER_MESSAGE, "originDataSize"));
        checkStringNotEmpty(request.getOriginDataSizeUnit(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "originDataSizeUnit"));
        checkNotNull(request.getDataAdd(), String.format(NOT_NULL_PARAMETER_MESSAGE, "dataAdd"));
        checkStringNotEmpty(request.getDataAddUnit(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "dataAddUnit"));
        checkNotNull(request.getStorageDays(), String.format(NOT_NULL_PARAMETER_MESSAGE, "storageDays"));
        checkNotNull(request.getWriteThroughput(), String.format(NOT_NULL_PARAMETER_MESSAGE, "writeThroughput"));
        checkStringNotEmpty(request.getReplica(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "replica"));
        return post(request, ClusterAssessmentResponse.class, BASE_PATH, "/source_assess");
    }

    /**
     * 用户可用代金券列表
     * @return 用户可用代金券列表响应
     */
    public CouponListResponse listAvailableCoupons() {
        return post(new EmptyBesV2Request(), CouponListResponse.class, BASE_PATH, "/coupon", "/avail");
    }

    /**
     * 获取操作历史信息
     * @param request 获取操作历史信息请求
     * @return 获取操作历史信息响应
     */
    public ClusterTasksResponse getClusterTasks(ClusterTasksRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, ClusterTasksResponse.class, BASE_PATH, "/cluster_tasks");
    }

    /**
     * 获取数据量观测数据
     * @param request 获取数据量观测数据请求
     * @return 获取数据量观测数据响应
     */
    public DataSizeTendencyResponse getDataSizeTendency(DataSizeTendencyRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getDatePattern(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "datePattern"));
        checkNotNull(request.getTimes(), String.format(NOT_NULL_PARAMETER_MESSAGE, "times"));
        checkStringNotEmpty(request.getTimeUnit(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "timeUnit"));
        return post(request, DataSizeTendencyResponse.class, BASE_PATH, "/data_size_tendency");
    }

    /** ========================================= Instance API ================================================================ */

    /**
     * 停止实例V2
     * @param request 停止实例请求
     * @return 停止实例响应
     */
    public CommonBesV2Response stopInstance(InstanceStopRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getInstanceId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, INSTANCE_ID));
        return post(request, CommonBesV2Response.class, BASE_PATH, V2, "/instance", "/stop");
    }

    /**
     * 启动实例V2
     * @param request 启动实例请求
     * @return 启动实例响应
     */
    public CommonBesV2Response startInstance(InstanceStartRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getInstanceId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, INSTANCE_ID));
        return post(request, CommonBesV2Response.class, BASE_PATH, V2, "/instance", "/start");
    }

    /**
     * 批量停止实例
     * @param request 批量停止实例请求
     * @return 批量停止实例响应
     */
    public CommonBesV2Response batchStopInstance(InstanceBatchStopRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkListNotEmpty(request.getInstanceIdList(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "instanceIdList"));
        return post(request, CommonBesV2Response.class, BASE_PATH, V2, "/instance", "/batchStop");
    }

    /**
     * 批量启动实例
     * @param request 批量启动实例请求
     * @return 批量启动实例响应
     */
    public CommonBesV2Response batchStartInstance(InstanceBatchStartRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkListNotEmpty(request.getInstanceIdList(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "instanceIdList"));
        return post(request, CommonBesV2Response.class, BASE_PATH, V2, "/instance", "/batchStart");
    }

    /**
     * 删除实例
     * @param request 删除实例请求
     * @return 删除实例响应
     */
    public InstanceDeleteResponse deleteInstance(InstanceDeleteRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkListNotEmpty(request.getInstanceIdList(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "instanceIdList"));
        checkStringNotEmpty(request.getModuleType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, MODULE_TYPE));
        return post(request, InstanceDeleteResponse.class, BASE_PATH, V2, "/instance", "/delete");
    }

    /**
     * 查看缩容节点列表
     * @param request 查看缩容节点列表请求
     * @return 查看缩容节点列表响应
     */
    public ScaleInInstancesResponse getScaleInInstances(ScaleInInstancesRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getModuleType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, MODULE_TYPE));
        return post(request, ScaleInInstancesResponse.class, BASE_PATH, V2, "/scalein", "/instances");
    }

    /**
     * 数据迁移
     * @param request 数据迁移请求
     * @return 数据迁移响应
     */
    public CommonBesV2Response confirmDataMigration(DataMigrationConfirmRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getModuleType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, MODULE_TYPE));
        checkListNotEmpty(request.getInstanceIdList(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "instanceIdList"));
        return post(request, CommonBesV2Response.class, BASE_PATH, MIGRATE_V1, "/confirm");
    }

    /**
     * 数据迁移回滚
     * @param request 数据迁移回滚请求
     * @return 数据迁移回滚响应
     */
    public CommonBesV2Response rollbackDataMigration(DataMigrationRollbackRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getRequestId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "requestId"));
        return post(request, CommonBesV2Response.class, BASE_PATH, MIGRATE_V1, "/rollback");
    }

    /**
     * 数据迁移节点列表
     * @param request 数据迁移节点列表请求
     * @return 数据迁移节点列表响应
     */
    public DataMigrationInstancesResponse getMigrationInstances(DataMigrationInstancesRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getModuleType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, MODULE_TYPE));
        return post(request, DataMigrationInstancesResponse.class, BASE_PATH, MIGRATE_V1, "/instances");
    }

    /**
     * 数据迁移系统建议
     * @param request 数据迁移系统建议请求
     * @return 数据迁移系统建议响应
     */
    public DataMigrationSuggestResponse suggestMigrationInstances(DataMigrationSuggestRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getModuleType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, MODULE_TYPE));
        checkNotNull(request.getMigrateCount(), String.format(NOT_NULL_PARAMETER_MESSAGE, "migrateCount"));
        return post(request, DataMigrationSuggestResponse.class, BASE_PATH, MIGRATE_V1, "/suggestInstances");
    }


    /** ========================================= Log API ===================================================================== */

    /**
     * 日志设置
     * @param request 日志设置请求
     * @return 日志设置响应
     */
    public LogStringResultResponse updateLogSettings(LogSettingsRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkNotNull(request.getIndexSlowLog(), String.format(NOT_NULL_PARAMETER_MESSAGE, "indexSlowLog"));
        checkNotNull(request.getSearchSlowLog(), String.format(NOT_NULL_PARAMETER_MESSAGE, "searchSlowLog"));
        checkNotNull(request.getAuditLog(), String.format(NOT_NULL_PARAMETER_MESSAGE, "auditLog"));
        return post(request, LogStringResultResponse.class, BASE_PATH, "/es_log_view_new", "/settings");
    }

    /**
     * 日志查询
     * @param request 日志查询请求
     * @return 日志查询响应
     */
    public LogSearchResponse searchLog(LogSearchRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkNotNull(request.getPageNo(), String.format(NOT_NULL_PARAMETER_MESSAGE, "pageNo"));
        checkNotNull(request.getPageSize(), String.format(NOT_NULL_PARAMETER_MESSAGE, "pageSize"));
        checkStringNotEmpty(request.getStartTime(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "startTime"));
        checkStringNotEmpty(request.getEndTime(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "endTime"));
        checkStringNotEmpty(request.getType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "type"));
        return post(request, LogSearchResponse.class, BASE_PATH, "/es_log_view_new", "/search");
    }

    /**
     * 创建日志导出任务
     * @param request 创建日志导出任务请求
     * @return 创建日志导出任务响应
     */
    public LogExportResponse createLogExportTask(LogExportRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        return post(request, LogExportResponse.class, BASE_PATH, "/es_log_view_new", "/export");
    }

    /**
     * 查看日志导出任务记录
     * @param request 查看日志导出任务记录请求
     * @return 查看日志导出任务记录响应
     */
    public LogExportRecordResponse getLogExportRecord(LogExportRecordRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        return post(request, LogExportRecordResponse.class, BASE_PATH, "/es_log_view_new", "/export_record");
    }


    /** ========================================= Config API ================================================================== */

    /**
     * 查看集群配置
     * @param request 查看集群配置请求
     * @return 查看集群配置响应
     */
    public ClusterConfigInfoResponse getClusterConfig(ClusterConfigInfoRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, ClusterConfigInfoResponse.class, BASE_PATH, "/config", "/info");
    }

    /**
     * 配置修改
     * @param request 配置修改请求
     * @return 配置修改响应
     */
    public CommonBesV2Response updateClusterConfig(ClusterConfigUpdateRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkNotNull(request.getEsConfigsMap(), String.format(NOT_NULL_PARAMETER_MESSAGE, "esConfigsMap"));
        checkNotNull(request.getKibanaConfigsMap(), String.format(NOT_NULL_PARAMETER_MESSAGE, "kibanaConfigsMap"));
        return post(request, CommonBesV2Response.class, BASE_PATH, "/config", "/update");
    }

    /**
     * 获取同义词文件列表
     * @param request 获取同义词文件列表请求
     * @return 获取同义词文件列表响应
     */
    public SynonymDictListResponse listSynonymDicts(SynonymDictListRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, SynonymDictListResponse.class, BASE_PATH, "/synonym_dict", "/list");
    }

    /**
     * 删除同义词配置文件
     * @param request 删除同义词配置文件请求
     * @return 删除同义词配置文件响应
     */
    public CommonBesV2Response deleteSynonymDict(SynonymDictDeleteRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getSynonymDictName(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "synonymDictName"));
        return post(request, CommonBesV2Response.class, BASE_PATH, "/synonym_dict", "/delete");
    }

    /**
     * 上传同义词配置文件
     * @param request 上传同义词配置文件请求
     * @return 上传同义词配置文件响应
     */
    public CommonBesV2Response uploadSynonymDict(SynonymDictUploadRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkNotNull(request.getFile(), String.format(NOT_NULL_PARAMETER_MESSAGE, FILE));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST,
                BASE_PATH, request.getClusterId(), "/synonym_dict", "/upload");
        addMultipartBody(internalRequest, buildFileOnlyMultipartBody(request.getFile()));
        try {
            return invokeHttpClient(internalRequest, CommonBesV2Response.class);
        } finally {
            closeQuietly(internalRequest.getContent());
        }
    }


    /** ========================================= Renew API =================================================================== */

    /**
     * 创建自动续费规则
     * @param request 创建自动续费规则请求
     * @return 创建自动续费规则响应
     */
    public AutoRenewRuleCreateResponse createAutoRenewRule(AutoRenewRuleCreateRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkListNotEmpty(request.getClusterIds(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "clusterIds"));
        checkStringNotEmpty(request.getRenewTimeUnit(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "renewTimeUnit"));
        checkNotNull(request.getRenewTime(), String.format(NOT_NULL_PARAMETER_MESSAGE, "renewTime"));
        checkStringNotEmpty(request.getServiceType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "serviceType"));
        return post(request, AutoRenewRuleCreateResponse.class, BASE_PATH, "/auto_renew_rule", "/create");
    }

    /**
     * 查看自动续费
     * @param request 查看自动续费请求
     * @return 查看自动续费响应
     */
    public AutoRenewRuleDetailResponse getAutoRenewRuleDetail(AutoRenewRuleDetailRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, AutoRenewRuleDetailResponse.class, BASE_PATH, "/auto_renew_rule", "/detail");
    }

    /**
     * 查看自动续费规则列表
     * @param request 查看自动续费规则列表请求
     * @return 查看自动续费规则列表响应
     */
    public AutoRenewRuleListResponse listAutoRenewRules(AutoRenewRuleListRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getServiceType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "serviceType"));
        return post(request, AutoRenewRuleListResponse.class, BASE_PATH, "/auto_renew_rule", "/list");
    }

    /**
     * 更新自动续费规则
     * @param request 更新自动续费规则请求
     * @return 更新自动续费规则响应
     */
    public RenewStringResultResponse updateAutoRenewRule(AutoRenewRuleUpdateRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getRenewTimeUnit(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "renewTimeUnit"));
        checkNotNull(request.getRenewTime(), String.format(NOT_NULL_PARAMETER_MESSAGE, "renewTime"));
        return post(request, RenewStringResultResponse.class, BASE_PATH, "/auto_renew_rule", "/update");
    }

    /**
     * 删除自动续费规则
     * @param request 删除自动续费规则请求
     * @return 删除自动续费规则响应
     */
    public AutoRenewRuleDeleteResponse deleteAutoRenewRule(AutoRenewRuleDeleteRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        return post(request, AutoRenewRuleDeleteResponse.class, BASE_PATH, "/auto_renew_rule", "/delete");
    }

    /**
     * 续费操作
     * @param request 续费操作请求
     * @return 续费操作响应
     */
    public ClusterRenewResponse renewCluster(ClusterRenewRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkNotNull(request.getTime(), String.format(NOT_NULL_PARAMETER_MESSAGE, "time"));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, BASE_PATH, "/renew");
        internalRequest.addParameter("orderType", "RENEW");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, ClusterRenewResponse.class);
    }

    /**
     * 获取续费列表
     * @param request 获取续费列表请求
     * @return 获取续费列表响应
     */
    public RenewListResponse listRenewals(RenewListRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getOrder(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "order"));
        checkStringNotEmpty(request.getOrderBy(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "orderBy"));
        checkNotNull(request.getPageNo(), String.format(NOT_NULL_PARAMETER_MESSAGE, "pageNo"));
        checkNotNull(request.getPageSize(), String.format(NOT_NULL_PARAMETER_MESSAGE, "pageSize"));
        checkNotNull(request.getDaysToExpiration(), String.format(NOT_NULL_PARAMETER_MESSAGE, "daysToExpiration"));
        return post(request, RenewListResponse.class, BASE_PATH, "/renew", "/list");
    }


    /** ========================================= Inspect API ================================================================= */

    /**
     * 集群巡检授权
     * @param request 集群巡检授权请求
     * @return 集群巡检授权响应
     */
    public CommonBesV2Response authorizeInspect(InspectAuthorizeRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, CommonBesV2Response.class, BASE_PATH, "/inspect", "/authorize");
    }

    /**
     * 开启或关闭自动巡检
     * @param request 开启或关闭自动巡检请求
     * @return 开启或关闭自动巡检响应
     */
    public InspectStringSuccessCommonResponse switchAutoInspect(InspectAutoSwitchRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkNotNull(request.getSwitchOn(), String.format(NOT_NULL_PARAMETER_MESSAGE, "switchOn"));
        return post(request, InspectStringSuccessCommonResponse.class, BASE_PATH, "/inspect", "/switch_auto");
    }

    /**
     * 查看集群是否开启自动巡检
     * @param request 查看集群是否开启自动巡检请求
     * @return 查看集群是否开启自动巡检响应
     */
    public InspectCheckAutoResponse checkAutoInspect(InspectCheckAutoRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, InspectCheckAutoResponse.class, BASE_PATH, "/inspect", "/check_auto");
    }

    /**
     * 提交手动巡检任务
     * @param request 提交手动巡检任务请求
     * @return 提交手动巡检任务响应
     */
    public InspectStringSuccessCommonResponse createManualInspectTask(InspectCreateManualRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, InspectStringSuccessCommonResponse.class, BASE_PATH, "/inspect", "/create_manual");
    }

    /**
     * 查询是否可以提交巡检任务
     * @param request 查询是否可以提交巡检任务请求
     * @return 查询是否可以提交巡检任务响应
     */
    public InspectCheckBusyResponse checkInspectBusy(InspectCheckBusyRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, InspectCheckBusyResponse.class, BASE_PATH, "/inspect", "/check_busy");
    }

    /**
     * 查询今日已执行完成的手动巡检次数
     * @param request 查询今日已执行完成的手动巡检次数请求
     * @return 查询今日已执行完成的手动巡检次数响应
     */
    public InspectManualCountResponse getManualInspectCount(InspectManualCountRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, InspectManualCountResponse.class, BASE_PATH, "/inspect", "/manual_count");
    }

    /**
     * 查看手动巡检任务的配置
     * @param request 查看手动巡检任务的配置请求
     * @return 查看手动巡检任务的配置响应
     */
    public InspectManualConfigGetResponse getManualInspectConfig(InspectManualConfigGetRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, InspectManualConfigGetResponse.class, BASE_PATH, "/inspect", "/get_manual_conf");
    }

    /**
     * 修改手动巡检任务的配置
     * @param request 修改手动巡检任务的配置请求
     * @return 修改手动巡检任务的配置响应
     */
    public InspectStringSuccessCommonResponse updateManualInspectConfig(InspectManualConfigUpdateRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getIndices(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "indices"));
        return post(request, InspectStringSuccessCommonResponse.class, BASE_PATH, "/inspect", "/update_manual_conf");
    }

    /**
     * 列举所有可选巡检项
     * @return 列举所有可选巡检项响应
     */
    public InspectItemListResponse listInspectItems() {
        return post(new EmptyBesV2Request(), InspectItemListResponse.class, BASE_PATH, "/inspect", "/list_items");
    }

    /**
     * 查看某巡检任务的执行状态和结果
     * @param request 查看某巡检任务的执行状态和结果请求
     * @return 查看某巡检任务的执行状态和结果响应
     */
    public InspectTaskGetResponse getInspectTask(InspectTaskGetRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getTaskId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "taskId"));
        return post(request, InspectTaskGetResponse.class, BASE_PATH, "/inspect", "/get_task");
    }

    /**
     * 查看近7天已完成的巡检任务列表
     * @param request 查看近7天已完成的巡检任务列表请求
     * @return 查看近7天已完成的巡检任务列表响应
     */
    public InspectTaskListResponse listInspectTasks(InspectTaskListRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkNotNull(request.getPageNo(), String.format(NOT_NULL_PARAMETER_MESSAGE, "pageNo"));
        checkNotNull(request.getPageSize(), String.format(NOT_NULL_PARAMETER_MESSAGE, "pageSize"));
        return post(request, InspectTaskListResponse.class, BASE_PATH, "/inspect", "/list_tasks");
    }

    /**
     * 最新一次巡检概况
     * @param request 最新一次巡检概况请求
     * @return 最新一次巡检概况响应
     */
    public InspectOverviewLatestResponse getLatestInspectOverview(InspectOverviewLatestRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, InspectOverviewLatestResponse.class, BASE_PATH, "/inspect", "/overview", "/latest");
    }

    /**
     * 近七天巡检概况
     * @param request 近七天巡检概况请求
     * @return 近七天巡检概况响应
     */
    public InspectOverviewWeeklyResponse getWeeklyInspectOverview(InspectOverviewWeeklyRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, InspectOverviewWeeklyResponse.class, BASE_PATH, "/inspect", "/overview", "/weekly");
    }


    /** ========================================= Schedule API ================================================================ */

    /**
     * 查看定时调度任务
     * @param request 查看定时调度任务请求
     * @return 查看定时调度任务响应
     */
    public ScheduleListResponse listSchedules(ScheduleListRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, ScheduleListResponse.class, BASE_PATH, "/schedule", "/list");
    }

    /**
     * 创建定时调度任务
     * @param request 创建定时调度任务请求
     * @return 创建定时调度任务响应
     */
    public ScheduleStringResultResponse createSchedule(ScheduleCreateRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getSchedule(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "schedule"));
        checkStringNotEmpty(request.getScheduleName(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "scheduleName"));
        checkStringNotEmpty(request.getTaskType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "taskType"));
        checkNotNull(request.getTask(), String.format(NOT_NULL_PARAMETER_MESSAGE, "task"));
        return post(request, ScheduleStringResultResponse.class, BASE_PATH, "/schedule", "/create");
    }

    /**
     * 更新定时调度任务
     * @param request 更新定时调度任务请求
     * @return 更新定时调度任务响应
     */
    public ScheduleStringResultResponse updateSchedule(ScheduleUpdateRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getSchedule(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "schedule"));
        checkStringNotEmpty(request.getScheduleName(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "scheduleName"));
        checkStringNotEmpty(request.getTaskType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "taskType"));
        checkNotNull(request.getTask(), String.format(NOT_NULL_PARAMETER_MESSAGE, "task"));
        return post(request, ScheduleStringResultResponse.class, BASE_PATH, "/schedule", "/update");
    }

    /**
     * 删除定时调度任务
     * @param request 删除定时调度任务请求
     * @return 删除定时调度任务响应
     */
    public ScheduleStringResultResponse deleteSchedule(ScheduleDeleteRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getScheduleName(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "scheduleName"));
        return post(request, ScheduleStringResultResponse.class, BASE_PATH, "/schedule", "/delete");
    }


    /** ========================================= Tag API ===================================================================== */

    /**
     * 批量新增标签
     * @param request 批量新增标签请求
     * @return 批量新增标签响应
     */
    public CommonBesV2Response batchInsertTags(TagBatchInsertRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkListNotEmpty(request.getClusterIdList(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "clusterIdList"));
        checkListNotEmpty(request.getInsertTags(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "insertTags"));
        return post(request, CommonBesV2Response.class, BASE_PATH, "/batchInsertTags");
    }

    /**
     * 单个集群更新标签
     * @param request 单个集群更新标签请求
     * @return 单个集群更新标签响应
     */
    public CommonBesV2Response updateClusterTags(TagUpdateRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkNotNull(request.getTags(), String.format(NOT_NULL_PARAMETER_MESSAGE, "tags"));
        return post(request, CommonBesV2Response.class, BASE_PATH, "/updateTags");
    }

    /**
     * 查询标签列表
     * @return 查询标签列表响应
     */
    public TagListResponse listTags() {
        return post(new EmptyBesV2Request(), TagListResponse.class, BASE_PATH, "/tagList");
    }


    /** ========================================= Plugin API ================================================================== */

    /**
     * 安装系统默认插件
     * @param request 安装系统默认插件请求
     * @return 安装系统默认插件响应
     */
    public PluginStringResultResponse installDefaultPlugin(DefaultPluginInstallRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getPluginName(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, PLUGIN_NAME));
        checkStringNotEmpty(request.getModuleType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, MODULE_TYPE));
        return post(request, PluginStringResultResponse.class, BASE_PATH, "/default_plugin", "/install");
    }

    /**
     * 卸载系统默认插件
     * @param request 卸载系统默认插件请求
     * @return 卸载系统默认插件响应
     */
    public PluginStringResultResponse uninstallDefaultPlugin(DefaultPluginUninstallRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getPluginName(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, PLUGIN_NAME));
        checkStringNotEmpty(request.getModuleType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, MODULE_TYPE));
        return post(request, PluginStringResultResponse.class, BASE_PATH, "/default_plugin", "/uninstall");
    }

    /**
     * 安装自定义插件
     * @param request 安装自定义插件请求
     * @return 安装自定义插件响应
     */
    public CommonBesV2Response installCustomPlugin(CustomPluginInstallRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getPluginName(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, PLUGIN_NAME));
        checkStringNotEmpty(request.getModuleType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, MODULE_TYPE));
        return post(request, CommonBesV2Response.class, BASE_PATH, "/plugin", "/install");
    }

    /**
     * 卸载自定义插件
     * @param request 卸载自定义插件请求
     * @return 卸载自定义插件响应
     */
    public CommonBesV2Response uninstallCustomPlugin(CustomPluginUninstallRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getPluginName(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, PLUGIN_NAME));
        checkStringNotEmpty(request.getModuleType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, MODULE_TYPE));
        return post(request, CommonBesV2Response.class, BASE_PATH, "/plugin", "/uninstall");
    }

    /**
     * 删除自定义插件
     * @param request 删除自定义插件请求
     * @return 删除自定义插件响应
     */
    public PluginStringResultResponse deleteCustomPlugin(CustomPluginDeleteRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getPluginName(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, PLUGIN_NAME));
        checkStringNotEmpty(request.getModuleType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, MODULE_TYPE));
        return post(request, PluginStringResultResponse.class, BASE_PATH, "/plugin", "/delete");
    }

    /**
     * 获取默认和自定义插件列表
     * @param request 获取默认和自定义插件列表请求
     * @return 默认和自定义插件列表响应
     */
    public PluginInfoResponse getPluginInfo(PluginInfoRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, PluginInfoResponse.class, BASE_PATH, "/plugin", "/info");
    }

    /**
     * 上传自定义插件
     * @param request 上传自定义插件请求
     * @return 上传自定义插件响应
     */
    public PluginStringResultResponse uploadCustomPlugin(CustomPluginUploadRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getModuleType(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, MODULE_TYPE));
        checkNotNull(request.getFile(), String.format(NOT_NULL_PARAMETER_MESSAGE, FILE));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST,
                BASE_PATH, "/plugin", "/upload", request.getClusterId(), request.getModuleType());
        addMultipartBody(internalRequest, buildFileOnlyMultipartBody(request.getFile()));
        try {
            return invokeHttpClient(internalRequest, PluginStringResultResponse.class);
        } finally {
            closeQuietly(internalRequest.getContent());
        }
    }

    /**
     * 查看nlp词典
     * @param request 查看nlp词典请求
     * @return 查看nlp词典响应
     */
    public NlpDictDisplayResponse getNlpDict(NlpDictDisplayRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        return post(request, NlpDictDisplayResponse.class, BASE_PATH, "/nlp_dict", "/display");
    }

    /**
     * 上传nlp词典
     * @param request 上传nlp词典请求
     * @return 上传nlp词典响应
     */
    public PluginStringResultResponse updateNlpDict(NlpDictUpdateRequest request) {
        checkNotNull(request, NOT_NULL_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, CLUSTER_ID));
        checkStringNotEmpty(request.getSeparator(), String.format(NOT_EMPTY_PARAMETER_MESSAGE, "separator"));
        checkNotNull(request.getFile(), String.format(NOT_NULL_PARAMETER_MESSAGE, FILE));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, BASE_PATH, V2, "/nlp_dict", "/update");
        addMultipartBody(internalRequest, buildNlpDictUpdateMultipartBody(request));
        try {
            return invokeHttpClient(internalRequest, PluginStringResultResponse.class);
        } finally {
            closeQuietly(internalRequest.getContent());
        }
    }

    /** ========================================= General Function ============================================================ */


    private <T extends AbstractBceResponse> T post(AbstractBceRequest request, Class<T> responseClass, String... pathVariables) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, pathVariables);
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, responseClass);
    }

    private InternalRequest createRequest(AbstractBceRequest bceRequest,
                                          HttpMethodName httpMethod,
                                          String... pathVariables) {
        List<String> path = new ArrayList<String>();
        if (pathVariables != null) {
            path.addAll(Arrays.asList(pathVariables));
        }

        URI uri = com.baidubce.util.HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[0]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());
        request.addHeader(X_REGION, region);
        // HEADERS_TO_SIGN 声明了 x-bce-date 要参与签名，但此前没有任何代码真正设置这个头，
        // 导致 CanonicalRequest 里只有 host 一行；multipart 上传类接口（如 uploadSynonymDict）
        // 的网关会校验这个头必须存在且签名一致，缺失时报 accesskey 签字校验失败
        request.addHeader(Headers.BCE_DATE, DateUtils.formatAlternateIso8601Date(new Date()));
        return request;
    }

    private void addJsonBody(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() == HttpMethodName.POST || internalRequest.getHttpMethod() == HttpMethodName.PUT) {
            String json = JsonUtils.toJsonString(bceRequest);
            byte[] requestJson;
            try {
                requestJson = json.getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Unsupported encode.", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
            internalRequest.setContent(RestartableInputStream.wrap(requestJson));
        }
    }

    private void addMultipartBody(InternalRequest internalRequest, MultipartFormBody body) {
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(body.contentLength));
        internalRequest.addHeader(Headers.CONTENT_TYPE, MULTIPART_CONTENT_TYPE_PREFIX + body.boundary);
        internalRequest.setContent(body.content);
    }

    private void closeQuietly(RestartableInputStream content) {
        if (content == null) {
            return;
        }
        try {
            content.close();
        } catch (IOException e) {
            // best-effort close; upload result already determined by invokeHttpClient
        }
    }

    private MultipartFormBody buildFileOnlyMultipartBody(File file) {
        return buildMultipartFormBody(Collections.<NameValue>emptyList(), file);
    }

    private MultipartFormBody buildNlpDictUpdateMultipartBody(NlpDictUpdateRequest request) {
        List<NameValue> fields = Arrays.asList(
                new NameValue(CLUSTER_ID, request.getClusterId()),
                new NameValue("separator", request.getSeparator()));
        return buildMultipartFormBody(fields, request.getFile());
    }

    private MultipartFormBody buildMultipartFormBody(List<NameValue> formFields, File file) {
        validateUploadFile(file);
        String boundary = UUID.randomUUID().toString().replace("-", "");
        try {
            ByteArrayOutputStream preambleStream = new ByteArrayOutputStream();
            for (NameValue field : formFields) {
                writeMultipartFormField(preambleStream, boundary, field.name, field.value);
            }
            writeMultipartFilePartHeader(preambleStream, boundary, FILE, file);
            byte[] preamble = preambleStream.toByteArray();
            byte[] trailer = ("\r\n--" + boundary + "--\r\n").getBytes(DEFAULT_ENCODING);
            long contentLength = (long) preamble.length + file.length() + trailer.length;
            MultipartFormStream content = new MultipartFormStream(preamble, file, trailer);
            return new MultipartFormBody(boundary, contentLength, content);
        } catch (IOException e) {
            throw new BceClientException("Fail to encode multipart request body", e);
        }
    }

    private void validateUploadFile(File file) {
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException(
                    String.format("The file %s does not exist or is not a regular file.", file.getPath()));
        }
        if (file.length() > MAX_UPLOAD_FILE_SIZE) {
            throw new IllegalArgumentException(
                    String.format("The file %s exceeds the maximum allowed size of %d bytes.",
                            file.getPath(), MAX_UPLOAD_FILE_SIZE));
        }
    }

    private void writeMultipartFormField(ByteArrayOutputStream outputStream, String boundary,
            String name, String value) throws IOException {
        if (value == null) {
            return;
        }
        outputStream.write(("--" + boundary + "\r\n").getBytes(DEFAULT_ENCODING));
        outputStream.write(("Content-Disposition: form-data; name=\"" + name + "\"\r\n\r\n")
                .getBytes(DEFAULT_ENCODING));
        outputStream.write(value.getBytes(DEFAULT_ENCODING));
        outputStream.write("\r\n".getBytes(DEFAULT_ENCODING));
    }

    private void writeMultipartFilePartHeader(ByteArrayOutputStream outputStream, String boundary,
            String name, File file) throws IOException {
        outputStream.write(("--" + boundary + "\r\n").getBytes(DEFAULT_ENCODING));
        outputStream.write(("Content-Disposition: form-data; name=\"" + name + "\"; filename=\""
                + sanitizeFilename(file.getName()) + "\"\r\n").getBytes(DEFAULT_ENCODING));
        outputStream.write((Headers.CONTENT_TYPE + ": " + Mimetypes.getInstance().getMimetype(file) + "\r\n\r\n")
                .getBytes(DEFAULT_ENCODING));
    }

    private static String sanitizeFilename(String filename) {
        return filename.replace("\\", "\\\\").replace("\"", "\\\"").replaceAll("[\\r\\n]", "");
    }

    private static final class MultipartFormBody {
        private final String boundary;
        private final long contentLength;
        private final MultipartFormStream content;

        private MultipartFormBody(String boundary, long contentLength, MultipartFormStream content) {
            this.boundary = boundary;
            this.contentLength = contentLength;
            this.content = content;
        }
    }

    private static final class NameValue {
        private final String name;
        private final String value;

        private NameValue(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

    private static class EmptyBesV2Request extends AbstractBesV2Request {
    }
}
