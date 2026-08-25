package com.baidubce.services.bes.v3;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.BceCredentials;
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
import com.baidubce.services.bes.v3.model.action.GetOperationRequest;
import com.baidubce.services.bes.v3.model.action.GetOperationResponse;
import com.baidubce.services.bes.v3.model.action.ListActionTypesRequest;
import com.baidubce.services.bes.v3.model.action.ListActionTypesResponse;
import com.baidubce.services.bes.v3.model.action.ListActionsRequest;
import com.baidubce.services.bes.v3.model.action.ListActionsResponse;
import com.baidubce.services.bes.v3.model.action.ListOperationAnalysisDetailsRequest;
import com.baidubce.services.bes.v3.model.action.ListOperationAnalysisDetailsResponse;
import com.baidubce.services.bes.v3.model.action.ListOperationsRequest;
import com.baidubce.services.bes.v3.model.action.ListOperationsResponse;
import com.baidubce.services.bes.v3.model.audit.AuditEventTypesResponse;
import com.baidubce.services.bes.v3.model.audit.AuditStatusResponse;
import com.baidubce.services.bes.v3.model.audit.EnableAuditRequest;
import com.baidubce.services.bes.v3.model.audit.EnableAuditResponse;
import com.baidubce.services.bes.v3.model.audit.GetAuditEventTypesRequest;
import com.baidubce.services.bes.v3.model.audit.GetAuditStatusRequest;
import com.baidubce.services.bes.v3.model.audit.SearchAuditEventsRequest;
import com.baidubce.services.bes.v3.model.audit.SearchAuditEventsResponse;
import com.baidubce.services.bes.v3.model.bct.BctAuthSwitchResponse;
import com.baidubce.services.bes.v3.model.bct.GetBctAuthSwitchRequest;
import com.baidubce.services.bes.v3.model.bct.SearchBctEventsRequest;
import com.baidubce.services.bes.v3.model.bct.SearchBctEventsResponse;
import com.baidubce.services.bes.v3.model.bct.UpdateBctAuthSwitchRequest;
import com.baidubce.services.bes.v3.model.billing.CancelConvertToPostpayRequest;
import com.baidubce.services.bes.v3.model.billing.ConvertToPostpayRequest;
import com.baidubce.services.bes.v3.model.billing.ConvertToPrepayRequest;
import com.baidubce.services.bes.v3.model.billing.OrderIdResponse;
import com.baidubce.services.bes.v3.model.billing.QueryClusterMarginPriceRequest;
import com.baidubce.services.bes.v3.model.billing.QueryClusterMarginPriceResponse;
import com.baidubce.services.bes.v3.model.billing.QueryConfigPriceRequest;
import com.baidubce.services.bes.v3.model.billing.QueryConfigPriceResponse;
import com.baidubce.services.bes.v3.model.billing.RenewClusterRequest;
import com.baidubce.services.bes.v3.model.cluster.AccessWhitelistRequest;
import com.baidubce.services.bes.v3.model.cluster.AccessWhitelistResponse;
import com.baidubce.services.bes.v3.model.cluster.ChangeProtocolRequest;
import com.baidubce.services.bes.v3.model.cluster.ChangeProtocolResponse;
import com.baidubce.services.bes.v3.model.cluster.ClusterDetailResponse;
import com.baidubce.services.bes.v3.model.cluster.CreateClusterRequest;
import com.baidubce.services.bes.v3.model.cluster.CreateClusterResponse;
import com.baidubce.services.bes.v3.model.cluster.DeleteClusterRequest;
import com.baidubce.services.bes.v3.model.cluster.DeleteClusterResponse;
import com.baidubce.services.bes.v3.model.cluster.DownloadCertRequest;
import com.baidubce.services.bes.v3.model.cluster.DownloadCertResponse;
import com.baidubce.services.bes.v3.model.cluster.GetClusterDetailRequest;
import com.baidubce.services.bes.v3.model.cluster.GetNodeListRequest;
import com.baidubce.services.bes.v3.model.cluster.GetNodeListResponse;
import com.baidubce.services.bes.v3.model.cluster.ListAvailableKernelsRequest;
import com.baidubce.services.bes.v3.model.cluster.ListAvailableKernelsResponse;
import com.baidubce.services.bes.v3.model.cluster.ListClustersRequest;
import com.baidubce.services.bes.v3.model.cluster.ListClustersResponse;
import com.baidubce.services.bes.v3.model.cluster.ListMigrationNodesRequest;
import com.baidubce.services.bes.v3.model.cluster.ListMigrationNodesResponse;
import com.baidubce.services.bes.v3.model.cluster.ListUpgradeVersionsRequest;
import com.baidubce.services.bes.v3.model.cluster.ListUpgradeVersionsResponse;
import com.baidubce.services.bes.v3.model.cluster.MigrateDataRequest;
import com.baidubce.services.bes.v3.model.cluster.MigrateDataResponse;
import com.baidubce.services.bes.v3.model.cluster.QuerySpecsRequest;
import com.baidubce.services.bes.v3.model.cluster.QuerySpecsResponse;
import com.baidubce.services.bes.v3.model.cluster.RecoveryClusterRequest;
import com.baidubce.services.bes.v3.model.cluster.RecoveryClusterResponse;
import com.baidubce.services.bes.v3.model.cluster.RenameClusterRequest;
import com.baidubce.services.bes.v3.model.cluster.RenameClusterResponse;
import com.baidubce.services.bes.v3.model.cluster.RestartClusterRequest;
import com.baidubce.services.bes.v3.model.cluster.RestartClusterResponse;
import com.baidubce.services.bes.v3.model.cluster.ResumeClusterRequest;
import com.baidubce.services.bes.v3.model.cluster.ResumeClusterResponse;
import com.baidubce.services.bes.v3.model.cluster.StartNodesRequest;
import com.baidubce.services.bes.v3.model.cluster.StartNodesResponse;
import com.baidubce.services.bes.v3.model.cluster.StopNodesRequest;
import com.baidubce.services.bes.v3.model.cluster.StopNodesResponse;
import com.baidubce.services.bes.v3.model.cluster.SuggestMigrationNodesRequest;
import com.baidubce.services.bes.v3.model.cluster.SuggestMigrationNodesResponse;
import com.baidubce.services.bes.v3.model.cluster.SuspendClusterRequest;
import com.baidubce.services.bes.v3.model.cluster.SuspendClusterResponse;
import com.baidubce.services.bes.v3.model.cluster.SwitchCerebroRequest;
import com.baidubce.services.bes.v3.model.cluster.SwitchCerebroResponse;
import com.baidubce.services.bes.v3.model.cluster.SwitchDeletionProtectionRequest;
import com.baidubce.services.bes.v3.model.cluster.SwitchDeletionProtectionResponse;
import com.baidubce.services.bes.v3.model.cluster.SwitchPublicAccessRequest;
import com.baidubce.services.bes.v3.model.cluster.SwitchPublicAccessResponse;
import com.baidubce.services.bes.v3.model.cluster.UpdateClusterRequest;
import com.baidubce.services.bes.v3.model.cluster.UpdateClusterResponse;
import com.baidubce.services.bes.v3.model.cluster.UpdateMaintenanceDurationRequest;
import com.baidubce.services.bes.v3.model.cluster.UpdateMaintenanceDurationResponse;
import com.baidubce.services.bes.v3.model.cluster.UpgradeClusterRequest;
import com.baidubce.services.bes.v3.model.cluster.UpgradeClusterResponse;
import com.baidubce.services.bes.v3.model.diagnosis.AuthorizeDiagnosisRequest;
import com.baidubce.services.bes.v3.model.diagnosis.AuthorizeDiagnosisResponse;
import com.baidubce.services.bes.v3.model.diagnosis.AutoDiagnosisStatusResponse;
import com.baidubce.services.bes.v3.model.diagnosis.CreateManualDiagnosisRequest;
import com.baidubce.services.bes.v3.model.diagnosis.CreateManualDiagnosisResponse;
import com.baidubce.services.bes.v3.model.diagnosis.DiagnosisAuthorizeStatusResponse;
import com.baidubce.services.bes.v3.model.diagnosis.DiagnosisBusyStatusResponse;
import com.baidubce.services.bes.v3.model.diagnosis.DiagnosisReportDetailResponse;
import com.baidubce.services.bes.v3.model.diagnosis.GetAutoDiagnosisStatusRequest;
import com.baidubce.services.bes.v3.model.diagnosis.GetDiagnosisAuthorizeStatusRequest;
import com.baidubce.services.bes.v3.model.diagnosis.GetDiagnosisBusyStatusRequest;
import com.baidubce.services.bes.v3.model.diagnosis.GetDiagnosisReportDetailRequest;
import com.baidubce.services.bes.v3.model.diagnosis.GetLatestDiagnosisOverviewRequest;
import com.baidubce.services.bes.v3.model.diagnosis.GetManualDiagnosisConfigRequest;
import com.baidubce.services.bes.v3.model.diagnosis.GetManualDiagnosisCountRequest;
import com.baidubce.services.bes.v3.model.diagnosis.GetWeeklyDiagnosisOverviewRequest;
import com.baidubce.services.bes.v3.model.diagnosis.LatestDiagnosisOverviewResponse;
import com.baidubce.services.bes.v3.model.diagnosis.ListDiagnosisItemsRequest;
import com.baidubce.services.bes.v3.model.diagnosis.ListDiagnosisItemsResponse;
import com.baidubce.services.bes.v3.model.diagnosis.ListDiagnosisReportsRequest;
import com.baidubce.services.bes.v3.model.diagnosis.ListDiagnosisReportsResponse;
import com.baidubce.services.bes.v3.model.diagnosis.ManualDiagnosisConfigResponse;
import com.baidubce.services.bes.v3.model.diagnosis.ManualDiagnosisCountResponse;
import com.baidubce.services.bes.v3.model.diagnosis.UpdateAutoDiagnosisStatusRequest;
import com.baidubce.services.bes.v3.model.diagnosis.UpdateManualDiagnosisConfigRequest;
import com.baidubce.services.bes.v3.model.diagnosis.UpdateManualDiagnosisConfigResponse;
import com.baidubce.services.bes.v3.model.diagnosis.WeeklyDiagnosisOverviewResponse;
import com.baidubce.services.bes.v3.model.index.CheckIndexExistRequest;
import com.baidubce.services.bes.v3.model.index.CheckIndexTemplateExistRequest;
import com.baidubce.services.bes.v3.model.index.CheckIsmPolicyExistRequest;
import com.baidubce.services.bes.v3.model.index.ClearCacheIndicesRequest;
import com.baidubce.services.bes.v3.model.index.CloseIndicesRequest;
import com.baidubce.services.bes.v3.model.index.CreateIndexRequest;
import com.baidubce.services.bes.v3.model.index.CreateIndexTemplateRequest;
import com.baidubce.services.bes.v3.model.index.CreateIsmPolicyRequest;
import com.baidubce.services.bes.v3.model.index.DeleteIndexTemplateRequest;
import com.baidubce.services.bes.v3.model.index.DeleteIndicesRequest;
import com.baidubce.services.bes.v3.model.index.DeleteIsmPolicyRequest;
import com.baidubce.services.bes.v3.model.index.FlushIndicesRequest;
import com.baidubce.services.bes.v3.model.index.ForcemergeIndicesRequest;
import com.baidubce.services.bes.v3.model.index.GetIndexDetailRequest;
import com.baidubce.services.bes.v3.model.index.GetIndexFieldTypesRequest;
import com.baidubce.services.bes.v3.model.index.GetIndexStatsRequest;
import com.baidubce.services.bes.v3.model.index.GetIndexTemplateDetailRequest;
import com.baidubce.services.bes.v3.model.index.GetIsmPolicyDetailRequest;
import com.baidubce.services.bes.v3.model.index.IndexDetailResponse;
import com.baidubce.services.bes.v3.model.index.IndexExistResponse;
import com.baidubce.services.bes.v3.model.index.IndexFieldTypesResult;
import com.baidubce.services.bes.v3.model.index.IndexNameResponse;
import com.baidubce.services.bes.v3.model.index.IndexOperationSuccessResponse;
import com.baidubce.services.bes.v3.model.index.IndexStatsResponse;
import com.baidubce.services.bes.v3.model.index.IndexTemplateDetailResponse;
import com.baidubce.services.bes.v3.model.index.IndexTemplateExistResponse;
import com.baidubce.services.bes.v3.model.index.IndexTemplateNameResponse;
import com.baidubce.services.bes.v3.model.index.IsmPolicyDetailResult;
import com.baidubce.services.bes.v3.model.index.IsmPolicyExistResponse;
import com.baidubce.services.bes.v3.model.index.IsmPolicyIdResponse;
import com.baidubce.services.bes.v3.model.index.ListIndexTemplatesRequest;
import com.baidubce.services.bes.v3.model.index.ListIndexTemplatesResponse;
import com.baidubce.services.bes.v3.model.index.ListIndicesRequest;
import com.baidubce.services.bes.v3.model.index.ListIndicesResponse;
import com.baidubce.services.bes.v3.model.index.ListIsmPoliciesRequest;
import com.baidubce.services.bes.v3.model.index.ListIsmPoliciesResult;
import com.baidubce.services.bes.v3.model.index.ListMonitorIndicesRequest;
import com.baidubce.services.bes.v3.model.index.MonitorIndicesResponse;
import com.baidubce.services.bes.v3.model.index.OpenIndicesRequest;
import com.baidubce.services.bes.v3.model.index.RefreshIndicesRequest;
import com.baidubce.services.bes.v3.model.index.UpdateIndexAliasesRequest;
import com.baidubce.services.bes.v3.model.index.UpdateIndexMappingsRequest;
import com.baidubce.services.bes.v3.model.index.UpdateIndexSettingsRequest;
import com.baidubce.services.bes.v3.model.index.UpdateIndexTemplateRequest;
import com.baidubce.services.bes.v3.model.index.UpdateIsmPolicyRequest;
import com.baidubce.services.bes.v3.model.logmanage.GetLogCollectorStatusRequest;
import com.baidubce.services.bes.v3.model.logmanage.GetLogSwitchRequest;
import com.baidubce.services.bes.v3.model.logmanage.GetLogUsageRequest;
import com.baidubce.services.bes.v3.model.logmanage.LogCollectorStatusResponse;
import com.baidubce.services.bes.v3.model.logmanage.LogSwitchResponse;
import com.baidubce.services.bes.v3.model.logmanage.LogUsageResponse;
import com.baidubce.services.bes.v3.model.logmanage.SearchLogsRequest;
import com.baidubce.services.bes.v3.model.logmanage.SearchLogsResponse;
import com.baidubce.services.bes.v3.model.logmanage.UpdateLogSwitchRequest;
import com.baidubce.services.bes.v3.model.plugin.DeleteCustomPluginVersionRequest;
import com.baidubce.services.bes.v3.model.plugin.DeleteCustomPluginVersionResponse;
import com.baidubce.services.bes.v3.model.plugin.ListCustomPluginsRequest;
import com.baidubce.services.bes.v3.model.plugin.ListCustomPluginsResponse;
import com.baidubce.services.bes.v3.model.plugin.ListSystemPluginsRequest;
import com.baidubce.services.bes.v3.model.plugin.ListSystemPluginsResponse;
import com.baidubce.services.bes.v3.model.plugin.UpdateCustomPluginsRequest;
import com.baidubce.services.bes.v3.model.plugin.UpdatePluginResponse;
import com.baidubce.services.bes.v3.model.plugin.UpdateSystemPluginsRequest;
import com.baidubce.services.bes.v3.model.plugin.UploadFileRequest;
import com.baidubce.services.bes.v3.model.plugin.UploadFileResponse;
import com.baidubce.services.bes.v3.model.schedule.CreateScheduleRequest;
import com.baidubce.services.bes.v3.model.schedule.CreateScheduleResponse;
import com.baidubce.services.bes.v3.model.schedule.DeleteScheduleRequest;
import com.baidubce.services.bes.v3.model.schedule.DeleteScheduleResponse;
import com.baidubce.services.bes.v3.model.schedule.ListSchedulesRequest;
import com.baidubce.services.bes.v3.model.schedule.ListSchedulesResponse;
import com.baidubce.services.bes.v3.model.schedule.UpdateScheduleRequest;
import com.baidubce.services.bes.v3.model.schedule.UpdateScheduleResponse;
import com.baidubce.services.bes.v3.model.snapshot.BatchUpdateAutoSnapshotConfigStatusRequest;
import com.baidubce.services.bes.v3.model.snapshot.BatchUpdateAutoSnapshotConfigStatusResponse;
import com.baidubce.services.bes.v3.model.snapshot.ConfigIdResponse;
import com.baidubce.services.bes.v3.model.snapshot.CreateAutoSnapshotConfigRequest;
import com.baidubce.services.bes.v3.model.snapshot.CreateManualSnapshotRequest;
import com.baidubce.services.bes.v3.model.snapshot.CreateSnapshotRestoreRequest;
import com.baidubce.services.bes.v3.model.snapshot.DeleteAutoSnapshotConfigRequest;
import com.baidubce.services.bes.v3.model.snapshot.DeleteSnapshotRequest;
import com.baidubce.services.bes.v3.model.snapshot.GetSnapshotConfigDetailRequest;
import com.baidubce.services.bes.v3.model.snapshot.GetSnapshotDetailRequest;
import com.baidubce.services.bes.v3.model.snapshot.GetSnapshotRuleRequest;
import com.baidubce.services.bes.v3.model.snapshot.ListAutoSnapshotConfigsRequest;
import com.baidubce.services.bes.v3.model.snapshot.ListAutoSnapshotConfigsResponse;
import com.baidubce.services.bes.v3.model.snapshot.ListRestoreClustersRequest;
import com.baidubce.services.bes.v3.model.snapshot.ListRestoreClustersResponse;
import com.baidubce.services.bes.v3.model.snapshot.ListRestoresRequest;
import com.baidubce.services.bes.v3.model.snapshot.ListRestoresResponse;
import com.baidubce.services.bes.v3.model.snapshot.ListSnapshotIndicesByPatternRequest;
import com.baidubce.services.bes.v3.model.snapshot.ListSnapshotIndicesByPatternResponse;
import com.baidubce.services.bes.v3.model.snapshot.ListSnapshotsRequest;
import com.baidubce.services.bes.v3.model.snapshot.ListSnapshotsResponse;
import com.baidubce.services.bes.v3.model.snapshot.RestoreIdResponse;
import com.baidubce.services.bes.v3.model.snapshot.SnapshotConfigDetailResponse;
import com.baidubce.services.bes.v3.model.snapshot.SnapshotDetailResponse;
import com.baidubce.services.bes.v3.model.snapshot.SnapshotIdResponse;
import com.baidubce.services.bes.v3.model.snapshot.SnapshotRuleResponse;
import com.baidubce.services.bes.v3.model.snapshot.UpdateAutoSnapshotConfigRequest;
import com.baidubce.services.bes.v3.model.user.ResetAdminPasswordRequest;
import com.baidubce.services.bes.v3.model.user.ResetAdminPasswordResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.util.Mimetypes;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkIsTrue;
import static com.baidubce.util.Validate.checkListNotEmpty;
import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;

public class BesClientV3 extends AbstractBceClient {
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};
    private static final String X_REGION = "X-Region";
    private static final String X_BCE_ACCESSKEY = "x-bce-accesskey";
    private static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
    private static final String CONTENT_TYPE_ZIP = "application/zip";
    private static final String ACCEPT = "Accept";
    private static final String MULTIPART_CONTENT_TYPE_PREFIX = "multipart/form-data; boundary=";
    private static final long MAX_UPLOAD_FILE_SIZE = 5L * 1024 * 1024 * 1024;
    private static final String V3_PREFIX = "/v3";
    private static final String CLUSTERS = "clusters";
    private static final String INDEX_MANAGEMENT = "index-management";
    private static final String INDICES = "indices";
    private static final String INDEX_TEMPLATES = "index-templates";
    private static final String RECYCLE = "recycle";
    private static final String PAGE_NO = "pageNo";
    private static final String PAGE_SIZE = "pageSize";
    private static final String ORDER = "order";
    private static final String ORDER_BY = "orderBy";
    private static final String PAYMENT = "payment";
    private static final String ENGINE = "engine";
    private static final String STATUS = "status";
    private static final String CLUSTER_HEALTH = "clusterHealth";
    private static final String LOGICAL_ZONE = "logicalZone";
    private static final String TAG_KEY = "tagKey";
    private static final String TAG_VALUE = "tagValue";
    private static final String ACTIONS = "actions";
    private static final String ACTION_TYPES = "action-types";
    private static final String OPERATIONS = "operations";
    private static final String GROUPS = "groups";
    private static final String CLUSTER_NAME = "clusterName";
    private static final String CLUSTER_ID = "clusterId";
    private static final String LOGS = "logs";
    private static final String LOG_SWITCH = "switch";
    private static final String SYSTEM_PLUGINS = "system-plugins";
    private static final String CUSTOM_PLUGINS = "custom-plugins";
    private static final String FILES = "files";
    private static final String BCT = "bct";
    private static final String AUDIT = "audit";
    private static final String EVENT_TYPES = "event-types";
    private static final String EVENTS = "events";
    private static final String START_TIME = "startTime";
    private static final String END_TIME = "endTime";
    private static final String CURSOR = "cursor";
    private static final String CATEGORY = "category";
    private static final String ORDERS = "orders";
    private static final String TO_POSTPAY = "to-postpay";
    private static final String BACKUP = "backup";
    private static final String SNAPSHOTS = "snapshots";
    private static final String AUTO_SNAPSHOT_CONFIGS = "auto-snapshot-configs";
    private static final String SNAPSHOT_CONFIGS = "snapshot-configs";
    private static final String RESTORES = "restores";
    private static final String DIAGNOSIS = "diagnosis";
    private static final String AUTO = "auto";
    private static final String MANUAL = "manual";
    private static final String AUTHORIZE = "authorize";
    private static final String OVERVIEW = "overview";
    private static final String REPORTS = "reports";
    private static final String SCHEDULES = "schedules";

    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    private static final String CLUSTER_ID_MESSAGE_KEY = "clusterId";
    private static final String NAME_MESSAGE_KEY = "name";
    private static final String SCHEDULE_ID_MESSAGE_KEY = "scheduleId";

    private static final HttpResponseHandler[] BES_V3_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BesCertResponseHandler(),
            new BceJsonResponseHandler()
    };

    private final String region;

    public BesClientV3() {
        this(new BceClientConfiguration(), "bj");
    }

    public BesClientV3(BceClientConfiguration clientConfiguration) {
        this(clientConfiguration, "bj");
    }

    public BesClientV3(BceClientConfiguration clientConfiguration, String region) {
        super(clientConfiguration, BES_V3_HANDLERS);
        this.region = region;
    }

    @Override
    public String computeServiceId() {
        return "bes";
    }

    /** ========================================= Cluster API ======================================================== */

    /**
     * 创建集群
     * @param request 创建集群请求
     * @return 创建集群响应
     */
    public CreateClusterResponse createCluster(CreateClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, V3_PREFIX, CLUSTERS);
        if (request.getAdminPassword() != null) {
            BceCredentials credentials = internalRequest.getCredentials() != null
                    ? internalRequest.getCredentials() : this.config.getCredentials();
            try {
                request.setAdminPassword(
                        aes128WithFirst16Char(request.getAdminPassword(), credentials.getSecretKey()));
            } catch (GeneralSecurityException e) {
                throw new BceClientException("Encryption procedure exception", e);
            }
            internalRequest.addHeader(X_BCE_ACCESSKEY, credentials.getAccessKeyId());
        }
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateClusterResponse.class);
    }

    /**
     * 删除集群
     * @param request 删除集群请求
     * @return 是否删除成功
     */
    public DeleteClusterResponse deleteCluster(DeleteClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.DELETE, V3_PREFIX, CLUSTERS, request.getClusterId());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        addParameterIfNotNull(internalRequest, RECYCLE, request.getRecycle());
        return invokeHttpClient(internalRequest, DeleteClusterResponse.class);
    }

    /**
     * 查询集群列表
     * @param request 查询集群列表请求
     * @return 查询集群列表响应
     */
    public ListClustersResponse listClusters(ListClustersRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, V3_PREFIX, CLUSTERS);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        addParameterIfNotNull(internalRequest, PAGE_NO, request.getPageNo());
        addParameterIfNotNull(internalRequest, PAGE_SIZE, request.getPageSize());
        addParameterIfNotEmpty(internalRequest, ORDER, request.getOrder());
        addParameterIfNotEmpty(internalRequest, ORDER_BY, request.getOrderBy());
        addParameterIfNotEmpty(internalRequest, PAYMENT, request.getPayment());
        addParameterIfNotEmpty(internalRequest, ENGINE, request.getEngine());
        addParameterIfNotEmpty(internalRequest, STATUS, request.getStatus());
        addParameterIfNotEmpty(internalRequest, CLUSTER_HEALTH, request.getClusterHealth());
        addParameterIfNotEmpty(internalRequest, LOGICAL_ZONE, request.getLogicalZone());
        addParameterIfNotEmpty(internalRequest, CLUSTER_NAME, request.getClusterName());
        addParameterIfNotEmpty(internalRequest, CLUSTER_ID, request.getClusterId());
        addParameterIfNotEmpty(internalRequest, TAG_KEY, request.getTagKey());
        addParameterIfNotEmpty(internalRequest, TAG_VALUE, request.getTagValue());
        return invokeHttpClient(internalRequest, ListClustersResponse.class);
    }

    /**
     * 获取集群详情
     * @param request 获取集群详情请求
     * @return 获取集群详情响应
     */
    public ClusterDetailResponse getClusterDetail(GetClusterDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ClusterDetailResponse.class);
    }

    /**
     * 查询集群节点列表
     * @param request 查询集群节点列表请求
     * @return 查询集群节点列表响应
     */
    public GetNodeListResponse getNodeList(GetNodeListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), "nodes");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, GetNodeListResponse.class);
    }

    /**
     * 下载证书
     * @param request 下载证书请求
     * @return 下载证书响应
     */
    public DownloadCertResponse downloadCert(DownloadCertRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), "certs");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_ZIP);
        internalRequest.addHeader(ACCEPT, CONTENT_TYPE_ZIP);
        return invokeHttpClient(internalRequest, DownloadCertResponse.class);
    }

    /**
     * 修改集群名称
     * @param request 修改集群名称请求
     * @return 是否修改成功
     */
    public RenameClusterResponse renameCluster(RenameClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getNewName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), "name");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, RenameClusterResponse.class);
    }

    /**
     * 修改删除保护
     * @param request 修改删除保护请求
     * @return 修改删除保护响应
     */
    public SwitchDeletionProtectionResponse switchDeletionProtection(SwitchDeletionProtectionRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getDeletionProtection(), "deletionProtection should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), "deletion-protection");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, SwitchDeletionProtectionResponse.class);
    }

    /**
     * 修改可维护时间
     * @param request 修改可维护时间请求
     * @return 修改可维护时间响应
     */
    public UpdateMaintenanceDurationResponse updateMaintenanceDuration(UpdateMaintenanceDurationRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getMaintenanceTimeZone(), "maintenanceTimeZone should not be empty.");
        checkStringNotEmpty(request.getMaintenanceStartTime(), "maintenanceStartTime should not be empty.");
        checkStringNotEmpty(request.getMaintenanceEndTime(), "maintenanceEndTime should not be empty.");
        checkNotNull(request.getMaintenancePeriods(), "maintenancePeriods should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), "maintenance-duration");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateMaintenanceDurationResponse.class);
    }

    /**
     * 恢复集群
     * @param request 恢复集群请求
     * @return 恢复集群响应
     */
    public RecoveryClusterResponse recoverCluster(RecoveryClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), "recovery");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, RecoveryClusterResponse.class);
    }

    /**
     * 查询可用内核
     * @param request 查询可用内核请求
     * @return 查询可用内核响应
     */
    public ListAvailableKernelsResponse listAvailableKernels(ListAvailableKernelsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, "kernels");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListAvailableKernelsResponse.class);
    }

    /**
     * 查询可用规格
     * @param request 查询可用规格请求
     * @return 查询可用规格响应
     */
    public QuerySpecsResponse querySpecs(QuerySpecsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEngine(), "engine should not be empty.");
        checkStringNotEmpty(request.getEngineType(), "engineType should not be empty.");
        checkStringNotEmpty(request.getVersion(), "version should not be empty.");
        checkNotNull(request.getLogicalZones(), "logicalZones should not be null.");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, "specs");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, QuerySpecsResponse.class);
    }

    /**
     * 修改访问协议
     * @param request 修改访问协议请求
     * @return 修改访问协议响应
     */
    public ChangeProtocolResponse changeProtocol(ChangeProtocolRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getEnableHttps(), "enableHttps should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), "protocol");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, ChangeProtocolResponse.class);
    }

    /**
     * 停止节点
     * @param request 停止节点请求
     * @return 停止节点响应
     */
    public StopNodesResponse stopNodes(StopNodesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getNodes(), "nodes should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), "nodes", "stop");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, StopNodesResponse.class);
    }

    /**
     * 停止集群
     * @param request 停止集群请求
     * @return 停止集群响应
     */
    public SuspendClusterResponse suspendCluster(SuspendClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), "stop");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, SuspendClusterResponse.class);
    }

    /**
     * 升级集群
     * @param request 升级集群请求
     * @return 升级集群响应
     */
    public UpgradeClusterResponse upgrade(UpgradeClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getUpgradeType(), "upgradeType should not be empty.");
        checkStringNotEmpty(request.getOperation(), "operation should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), "upgrades");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, UpgradeClusterResponse.class);
    }

    /**
     * 变配集群
     * @param request 变配集群请求
     * @return 变配集群响应
     */
    public UpdateClusterResponse updateCluster(UpdateClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getMode(), "mode should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId());
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateClusterResponse.class);
    }

    /**
     * 启动节点
     * @param request 启动节点请求
     * @return 启动节点响应
     */
    public StartNodesResponse startNodes(StartNodesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getNodes(), "nodes should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), "nodes", "start");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, StartNodesResponse.class);
    }

    /**
     * 启动集群
     * @param request 启动集群请求
     * @return 启动集群响应
     */
    public ResumeClusterResponse resumeCluster(ResumeClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), "start");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ResumeClusterResponse.class);
    }

    /**
     * 开关公网访问
     * @param request 开关公网访问请求
     * @return 开关公网访问响应
     */
    public SwitchPublicAccessResponse switchPublicAccess(SwitchPublicAccessRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getEnabled(), "enabled should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), "public-access");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, SwitchPublicAccessResponse.class);
    }

    /**
     * 开启 Cerebro
     * @param request 开启 Cerebro 请求
     * @return 是否开启成功
     */
    public SwitchCerebroResponse enableCerebro(SwitchCerebroRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getEnabled(), "enabled should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), "cerebro");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, SwitchCerebroResponse.class);
    }

    /**
     * 查询可升级版本
     * @param request 查询可升级版本请求
     * @return 查询可升级版本响应
     */
    public ListUpgradeVersionsResponse listUpgradeVersions(ListUpgradeVersionsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), "upgrades", "versions");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListUpgradeVersionsResponse.class);
    }

    /**
     * 查询可迁移节点
     * @param request 查询可迁移节点请求
     * @return 查询可迁移节点响应
     */
    public ListMigrationNodesResponse listMigrationNodes(ListMigrationNodesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getType(), "type should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), "migrations", "nodes");
        addParameterIfNotEmpty(internalRequest, "type", request.getType());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListMigrationNodesResponse.class);
    }

    /**
     * 查询建议迁移节点
     * @param request 查询建议迁移节点请求
     * @return 查询建议迁移节点响应
     */
    public SuggestMigrationNodesResponse suggestMigrationNodes(SuggestMigrationNodesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getType(), "type should not be empty.");
        checkNotNull(request.getMigrateCount(), "migrateCount should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), "migrations",
                "suggested-nodes");
        addParameterIfNotEmpty(internalRequest, "type", request.getType());
        addParameterIfNotNull(internalRequest, "migrateCount", request.getMigrateCount());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, SuggestMigrationNodesResponse.class);
    }

    /**
     * 设置访问白名单
     * @param request 设置访问白名单请求
     * @return 设置访问白名单响应
     */
    public AccessWhitelistResponse setAccessWhitelist(AccessWhitelistRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAccessType(), "accessType should not be empty.");
        checkStringNotEmpty(request.getNetworkType(), "networkType should not be empty.");
        checkNotNull(request.getIpWhitelist(), "ipWhitelist should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), "access-white-ips");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, AccessWhitelistResponse.class);
    }

    /**
     * 迁移节点数据
     * @param request 迁移节点数据请求
     * @return 迁移节点数据响应
     */
    public MigrateDataResponse migrateData(MigrateDataRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getNodes(), "nodes should not be null.");
        checkStringNotEmpty(request.getType(), "type should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), "migrations");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, MigrateDataResponse.class);
    }

    /**
     * 重启集群
     * @param request 重启集群请求
     * @return 重启集群响应
     */
    public RestartClusterResponse restart(RestartClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getRestartType(), "restartType should not be empty.");
        checkStringNotEmpty(request.getMode(), "mode should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), "restart");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, RestartClusterResponse.class);
    }

    /** ========================================= Index API ================================================================ */

    /**
     * 查询索引列表
     * @param request 查询索引列表请求
     * @return 查询索引列表响应
     */
    public ListIndicesResponse listIndices(ListIndicesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT, INDICES);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        addParameterIfNotEmpty(internalRequest, "indexName", request.getIndexName());
        addParameterIfNotEmpty(internalRequest, "health", request.getHealth());
        addParameterIfNotNull(internalRequest, "includeSystem", request.getIncludeSystem());
        addParameterIfNotNull(internalRequest, PAGE_NO, request.getPageNo());
        addParameterIfNotNull(internalRequest, PAGE_SIZE, request.getPageSize());
        addParameterIfNotEmpty(internalRequest, ORDER_BY, request.getOrderBy());
        addParameterIfNotEmpty(internalRequest, ORDER, request.getOrder());
        return invokeHttpClient(internalRequest, ListIndicesResponse.class);
    }

    /**
     * 查询索引详情
     * @param request 查询索引详情请求
     * @return 查询索引详情响应
     */
    public IndexDetailResponse getIndexDetail(GetIndexDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIndexName(), "indexName should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDICES, request.getIndexName());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, IndexDetailResponse.class);
    }

    /**
     * 创建索引
     * @param request 创建索引请求
     * @return 是否创建成功
     */
    public IndexNameResponse createIndex(CreateIndexRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIndexName(), "indexName should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDICES, request.getIndexName());
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, IndexNameResponse.class);
    }

    /**
     * 批量删除索引
     * @param request 批量删除索引请求
     * @return 是否删除成功
     */
    public IndexOperationSuccessResponse deleteIndices(DeleteIndicesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getIndexNames(), "indexNames should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDICES, "_delete");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, IndexOperationSuccessResponse.class);
    }

    /**
     * 打开索引
     * @param request 打开索引请求
     * @return 是否打开成功
     */
    public IndexOperationSuccessResponse openIndices(OpenIndicesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getIndexNames(), "indexNames should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDICES, "_open");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, IndexOperationSuccessResponse.class);
    }

    /**
     * 关闭索引
     * @param request 关闭索引请求
     * @return 是否关闭成功
     */
    public IndexOperationSuccessResponse closeIndices(CloseIndicesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getIndexNames(), "indexNames should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDICES, "_close");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, IndexOperationSuccessResponse.class);
    }

    /**
     * 刷新索引
     * @param request 刷新索引请求
     * @return 是否刷新成功
     */
    public IndexOperationSuccessResponse refreshIndices(RefreshIndicesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getIndexNames(), "indexNames should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDICES, "_refresh");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, IndexOperationSuccessResponse.class);
    }

    /**
     * Flush索引
     * @param request Flush索引请求
     * @return 是否Flush成功
     */
    public IndexOperationSuccessResponse flushIndices(FlushIndicesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getIndexNames(), "indexNames should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDICES, "_flush");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, IndexOperationSuccessResponse.class);
    }

    /**
     * 强制合并索引
     * @param request 强制合并索引请求
     * @return 是否合并成功
     */
    public IndexOperationSuccessResponse forcemergeIndices(ForcemergeIndicesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getIndexNames(), "indexNames should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDICES, "_forcemerge");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, IndexOperationSuccessResponse.class);
    }

    /**
     * 清理索引缓存
     * @param request 清理索引缓存请求
     * @return 是否清理成功
     */
    public IndexOperationSuccessResponse clearCacheIndices(ClearCacheIndicesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getIndexNames(), "indexNames should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDICES, "_clear_cache");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, IndexOperationSuccessResponse.class);
    }

    /**
     * 更新索引设置
     * @param request 更新索引设置请求
     * @return 是否更新成功
     */
    public IndexNameResponse updateIndexSettings(UpdateIndexSettingsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIndexName(), "indexName should not be empty.");
        checkNotNull(request.getSettings(), "settings should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDICES, request.getIndexName(), "settings");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, IndexNameResponse.class);
    }

    /**
     * 更新索引映射
     * @param request 更新索引映射请求
     * @return 是否更新成功
     */
    public IndexNameResponse updateIndexMappings(UpdateIndexMappingsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIndexName(), "indexName should not be empty.");
        checkNotNull(request.getMappings(), "mappings should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDICES, request.getIndexName(), "mappings");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, IndexNameResponse.class);
    }

    /**
     * 更新索引别名
     * @param request 更新索引别名请求
     * @return 是否更新成功
     */
    public IndexNameResponse updateIndexAliases(UpdateIndexAliasesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIndexName(), "indexName should not be empty.");
        checkNotNull(request.getActions(), "actions should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDICES, request.getIndexName(), "aliases");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, IndexNameResponse.class);
    }

    /**
     * 查询索引统计
     * @param request 查询索引统计请求
     * @return 查询索引统计响应
     */
    public IndexStatsResponse getIndexStats(GetIndexStatsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIndexName(), "indexName should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDICES, request.getIndexName(), "stats");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, IndexStatsResponse.class);
    }

    /**
     * 查询索引模板列表
     * @param request 查询索引模板列表请求
     * @return 查询索引模板列表响应
     */
    public ListIndexTemplatesResponse listIndexTemplates(ListIndexTemplatesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDEX_TEMPLATES);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListIndexTemplatesResponse.class);
    }

    /**
     * 查询索引模板详情
     * @param request 查询索引模板详情请求
     * @return 查询索引模板详情响应
     */
    public IndexTemplateDetailResponse getIndexTemplateDetail(GetIndexTemplateDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTemplateName(), "templateName should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDEX_TEMPLATES, request.getTemplateName());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, IndexTemplateDetailResponse.class);
    }

    /**
     * 创建索引模板
     * @param request 创建索引模板请求
     * @return 是否创建成功
     */
    public IndexTemplateNameResponse createIndexTemplate(CreateIndexTemplateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTemplateName(), "templateName should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDEX_TEMPLATES);
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, IndexTemplateNameResponse.class);
    }

    /**
     * 更新索引模板
     * @param request 更新索引模板请求
     * @return 是否更新成功
     */
    public IndexTemplateNameResponse updateIndexTemplate(UpdateIndexTemplateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTemplateName(), "templateName should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDEX_TEMPLATES, request.getTemplateName());
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, IndexTemplateNameResponse.class);
    }

    /**
     * 删除索引模板
     * @param request 删除索引模板请求
     * @return 是否删除成功
     */
    public IndexTemplateNameResponse deleteIndexTemplate(DeleteIndexTemplateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTemplateName(), "templateName should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.DELETE, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDEX_TEMPLATES, request.getTemplateName());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        addParameterIfNotEmpty(internalRequest, "isLegacy", request.getIsLegacy());
        return invokeHttpClient(internalRequest, IndexTemplateNameResponse.class);
    }

    /**
     * 查询索引是否存在
     * @param request 查询索引是否存在请求
     * @return 查询索引是否存在响应
     */
    public IndexExistResponse checkIndexExist(CheckIndexExistRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getIndexName(), "indexName should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDICES, request.getIndexName(), "_exist");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, IndexExistResponse.class);
    }

    /**
     * 查询索引模板是否存在
     * @param request 查询索引模板是否存在请求
     * @return 查询索引模板是否存在响应
     */
    public IndexTemplateExistResponse isIndexTemplateExists(CheckIndexTemplateExistRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTemplateName(), "templateName should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                INDEX_TEMPLATES, request.getTemplateName(), "_exist");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, IndexTemplateExistResponse.class);
    }

    /**
     * 查询索引字段类型
     * @param request 查询索引字段类型请求
     * @return 查询索引字段类型响应
     */
    public IndexFieldTypesResult getIndexFieldTypes(GetIndexFieldTypesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                "index-field-types");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, IndexFieldTypesResult.class);
    }

    /**
     * 查询监控索引列表
     * @param request 查询监控索引列表请求
     * @return 查询监控索引列表响应
     */
    public MonitorIndicesResponse listMonitorIndices(ListMonitorIndicesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), "monitor", INDICES);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        addParameterIfNotEmpty(internalRequest, "indexName", request.getIndexName());
        addParameterIfNotNull(internalRequest, "includeSystem", request.getIncludeSystem());
        addParameterIfNotNull(internalRequest, PAGE_NO, request.getPageNo());
        addParameterIfNotNull(internalRequest, PAGE_SIZE, request.getPageSize());
        return invokeHttpClient(internalRequest, MonitorIndicesResponse.class);
    }

    /**
     * 创建ISM策略
     * @param request 创建ISM策略请求
     * @return 是否创建成功
     */
    public IsmPolicyIdResponse createIsmPolicy(CreateIsmPolicyRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getPolicyId(), "policyId should not be empty.");
        checkStringNotEmpty(request.getDescription(), "description should not be empty.");
        checkNotNull(request.getIsmTemplate(), "ismTemplate should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                "ism-policies", request.getPolicyId());
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, IsmPolicyIdResponse.class);
    }

    /**
     * 更新ISM策略
     * @param request 更新ISM策略请求
     * @return 是否更新成功
     */
    public IsmPolicyIdResponse updateIsmPolicy(UpdateIsmPolicyRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getPolicyId(), "policyId should not be empty.");
        checkStringNotEmpty(request.getDescription(), "description should not be empty.");
        checkNotNull(request.getIsmTemplate(), "ismTemplate should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                "ism-policies", request.getPolicyId());
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, IsmPolicyIdResponse.class);
    }

    /**
     * 删除ISM策略
     * @param request 删除ISM策略请求
     * @return 是否删除成功
     */
    public IsmPolicyIdResponse deleteIsmPolicy(DeleteIsmPolicyRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getPolicyId(), "policyId should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.DELETE, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                "ism-policies", request.getPolicyId());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, IsmPolicyIdResponse.class);
    }

    /**
     * 查询ISM策略列表
     * @param request 查询ISM策略列表请求
     * @return 查询ISM策略列表响应
     */
    public ListIsmPoliciesResult listIsmPolicies(ListIsmPoliciesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                "ism-policies");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListIsmPoliciesResult.class);
    }

    /**
     * 查询ISM策略详情
     * @param request 查询ISM策略详情请求
     * @return 查询ISM策略详情响应
     */
    public IsmPolicyDetailResult getIsmPolicyDetail(GetIsmPolicyDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getPolicyId(), "policyId should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                "ism-policies", request.getPolicyId());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, IsmPolicyDetailResult.class);
    }

    /**
     * 查询ISM策略是否存在
     * @param request 查询ISM策略是否存在请求
     * @return 查询ISM策略是否存在响应
     */
    public IsmPolicyExistResponse checkIsmPolicyExist(CheckIsmPolicyExistRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getPolicyId(), "policyId should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), INDEX_MANAGEMENT,
                "ism-policies", request.getPolicyId(), "_exist");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, IsmPolicyExistResponse.class);
    }

    /** ========================================= Actions API ============================================================== */

    /**
     * 查询任务列表
     * @param request 查询任务列表请求
     * @return 查询任务列表响应
     */
    public ListActionsResponse listActions(ListActionsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, ACTIONS);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        addParameterIfNotNull(internalRequest, PAGE_NO, request.getPageNo());
        addParameterIfNotNull(internalRequest, PAGE_SIZE, request.getPageSize());
        addParameterIfNotEmpty(internalRequest, NAME_MESSAGE_KEY, request.getName());
        addParameterIfNotEmpty(internalRequest, STATUS, request.getStatus());
        addParameterIfNotEmpty(internalRequest, ORDER_BY, request.getOrderBy());
        addParameterIfNotEmpty(internalRequest, ORDER, request.getOrder());
        addParameterIfNotEmpty(internalRequest, CLUSTER_NAME, request.getClusterName());
        addParameterIfNotEmpty(internalRequest, CLUSTER_ID_MESSAGE_KEY, request.getClusterId());
        return invokeHttpClient(internalRequest, ListActionsResponse.class);
    }

    /**
     * 查询任务类型
     * @param request 查询任务类型请求
     * @return 查询任务类型响应
     */
    public ListActionTypesResponse listActionTypes(ListActionTypesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, ACTIONS, ACTION_TYPES);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListActionTypesResponse.class);
    }

    /**
     * 查询操作列表
     * @param request 查询操作列表请求
     * @return 查询操作列表响应
     */
    public ListOperationsResponse listOperations(ListOperationsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getActionId(), "actionId should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), ACTIONS,
                request.getActionId(), OPERATIONS);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListOperationsResponse.class);
    }

    /**
     * 查询操作详情
     * @param request 查询操作详情请求
     * @return 查询操作详情响应
     */
    public GetOperationResponse getOperation(GetOperationRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getActionId(), "actionId should not be empty.");
        checkStringNotEmpty(request.getOperationId(), "operationId should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), ACTIONS,
                request.getActionId(), OPERATIONS, request.getOperationId());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, GetOperationResponse.class);
    }

    /**
     * 查询子操作分析详情
     * @param request 查询子操作分析详情请求
     * @return 查询子操作分析详情响应
     */
    public ListOperationAnalysisDetailsResponse listOperationAnalysisDetails(
            ListOperationAnalysisDetailsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getActionId(), "actionId should not be empty.");
        checkStringNotEmpty(request.getOperationId(), "operationId should not be empty.");
        checkStringNotEmpty(request.getGroupNames(), "groupNames should not be empty.");
        checkNotNull(request.getPageNo(), "pageNo should not be null.");
        checkNotNull(request.getPageSize(), "pageSize should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), ACTIONS,
                request.getActionId(), OPERATIONS, request.getOperationId(), GROUPS, request.getGroupNames());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        addParameterIfNotNull(internalRequest, PAGE_NO, request.getPageNo());
        addParameterIfNotNull(internalRequest, PAGE_SIZE, request.getPageSize());
        return invokeHttpClient(internalRequest, ListOperationAnalysisDetailsResponse.class);
    }

    /** ========================================= Log API ================================================================== */

    /**
     * 查询日志采集开关
     * @param request 查询日志采集开关请求
     * @return 查询日志采集开关响应
     */
    public LogSwitchResponse getLogSwitch(GetLogSwitchRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), LOGS, LOG_SWITCH);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, LogSwitchResponse.class);
    }

    /**
     * 更新日志采集开关
     * @param request 更新日志采集开关请求
     * @return 更新日志采集开关响应
     */
    public LogSwitchResponse updateLogSwitch(UpdateLogSwitchRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), LOGS, LOG_SWITCH);
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, LogSwitchResponse.class);
    }

    /**
     * 查询日志使用量
     * @param request 查询日志使用量请求
     * @return 查询日志使用量响应
     */
    public LogUsageResponse getLogUsage(GetLogUsageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), LOGS, "usage");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, LogUsageResponse.class);
    }

    /**
     * 查询日志采集器状态
     * @param request 查询日志采集器状态请求
     * @return 查询日志采集器状态响应
     */
    public LogCollectorStatusResponse getLogCollectorStatus(GetLogCollectorStatusRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), LOGS,
                "collector", "status");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, LogCollectorStatusResponse.class);
    }

    /**
     * 搜索日志
     * @param request 搜索日志请求
     * @return 搜索日志响应
     */
    public SearchLogsResponse searchLogs(SearchLogsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getLogType(), "logType should not be empty.");
        checkStringNotEmpty(request.getStartTime(), "startTime should not be empty.");
        checkStringNotEmpty(request.getEndTime(), "endTime should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), LOGS, "search");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, SearchLogsResponse.class);
    }

    /** ========================================= User Management API ====================================================== */

    /**
     * 重置集群管理员密码
     * @param request 重置管理员密码请求
     * @return 是否重置成功
     */
    public ResetAdminPasswordResponse resetAdminPassword(ResetAdminPasswordRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getNewPassword(), "newPassword should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(),
                "users", "admins", "passwords");
        BceCredentials credentials = internalRequest.getCredentials() != null
                ? internalRequest.getCredentials() : this.config.getCredentials();
        String encryptedPassword;
        try {
            encryptedPassword = aes128WithFirst16Char(request.getNewPassword(), credentials.getSecretKey());
        } catch (GeneralSecurityException e) {
            throw new BceClientException("Encryption procedure exception", e);
        }
        internalRequest.addHeader(X_BCE_ACCESSKEY, credentials.getAccessKeyId());
        addJsonBody(internalRequest, Collections.singletonMap("newPassword", encryptedPassword));
        return invokeHttpClient(internalRequest, ResetAdminPasswordResponse.class);
    }

    /** ========================================= Snapshot API ============================================================= */

    /**
     * 创建手工快照
     * @param request 创建手工快照请求
     * @return 快照配置 ID 响应
     */
    public ConfigIdResponse createManualSnapshot(CreateManualSnapshotRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(),
                BACKUP, "manual-snapshot-configs");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, ConfigIdResponse.class);
    }

    /**
     * 查询快照列表
     * @param request 查询快照列表请求
     * @return 快照列表响应
     */
    public ListSnapshotsResponse listSnapshots(ListSnapshotsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), BACKUP, SNAPSHOTS);
        addParameterIfNotNull(internalRequest, PAGE_NO, request.getPageNo());
        addParameterIfNotNull(internalRequest, PAGE_SIZE, request.getPageSize());
        addParameterIfNotEmpty(internalRequest, ORDER, request.getOrder());
        addParameterIfNotEmpty(internalRequest, ORDER_BY, request.getOrderBy());
        addParameterIfNotEmpty(internalRequest, "snapshotName", request.getSnapshotName());
        addParameterIfNotEmpty(internalRequest, "type", request.getType());
        addParameterIfNotNull(internalRequest, "startTimestamp", request.getStartTimestamp());
        addParameterIfNotNull(internalRequest, "endTimestamp", request.getEndTimestamp());
        addParameterIfNotEmpty(internalRequest, "snapshotStatus", request.getSnapshotStatus());
        addParameterIfNotEmpty(internalRequest, "restoreStatus", request.getRestoreStatus());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListSnapshotsResponse.class);
    }

    /**
     * 查询快照详情
     * @param request 查询快照详情请求
     * @return 快照详情响应
     */
    public SnapshotDetailResponse getSnapshotDetail(GetSnapshotDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getSnapshotId(), "snapshotId should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), BACKUP, SNAPSHOTS,
                String.valueOf(request.getSnapshotId()));
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, SnapshotDetailResponse.class);
    }

    /**
     * 删除快照
     * @param request 删除快照请求
     * @return 被删除的快照 ID 响应
     */
    public SnapshotIdResponse deleteSnapshot(DeleteSnapshotRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getSnapshotId(), "snapshotId should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.DELETE, V3_PREFIX, CLUSTERS, request.getClusterId(), BACKUP, SNAPSHOTS,
                String.valueOf(request.getSnapshotId()));
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, SnapshotIdResponse.class);
    }

    /**
     * 查询快照规则
     * @param request 查询快照规则请求
     * @return 快照规则响应
     */
    public SnapshotRuleResponse getSnapshotRule(GetSnapshotRuleRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getSnapshotId(), "snapshotId should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), BACKUP, SNAPSHOTS,
                String.valueOf(request.getSnapshotId()), "rule");
        addParameterIfNotNull(internalRequest, PAGE_NO, request.getPageNo());
        addParameterIfNotNull(internalRequest, PAGE_SIZE, request.getPageSize());
        addParameterIfNotEmpty(internalRequest, ORDER_BY, request.getOrderBy());
        addParameterIfNotEmpty(internalRequest, ORDER, request.getOrder());
        addParameterIfNotEmpty(internalRequest, "indexName", request.getIndexName());
        addParameterIfNotEmpty(internalRequest, "restoreIndexPattern", request.getRestoreIndexPattern());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, SnapshotRuleResponse.class);
    }

    /**
     * 按模式查询快照索引
     * @param request 按模式查询快照索引请求
     * @return 快照索引列表响应
     */
    public ListSnapshotIndicesByPatternResponse listSnapshotIndicesByPattern(
            ListSnapshotIndicesByPatternRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(),
                BACKUP, INDICES, "_list_by_pattern");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, ListSnapshotIndicesByPatternResponse.class);
    }

    /**
     * 创建快照恢复
     * @param request 创建快照恢复请求
     * @return 恢复任务 ID 响应
     */
    public RestoreIdResponse createSnapshotRestore(CreateSnapshotRestoreRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getSnapshotId(), "snapshotId should not be null.");
        checkStringNotEmpty(request.getDestClusterId(), checkEmptyExceptionMessageFormat("destClusterId"));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), BACKUP, SNAPSHOTS,
                String.valueOf(request.getSnapshotId()), RESTORES);
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, RestoreIdResponse.class);
    }

    /**
     * 查询恢复任务列表
     * @param request 查询恢复任务列表请求
     * @return 恢复任务列表响应
     */
    public ListRestoresResponse listRestores(ListRestoresRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getSnapshotId(), "snapshotId should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), BACKUP, SNAPSHOTS,
                String.valueOf(request.getSnapshotId()), RESTORES);
        addParameterIfNotNull(internalRequest, PAGE_NO, request.getPageNo());
        addParameterIfNotNull(internalRequest, PAGE_SIZE, request.getPageSize());
        addParameterIfNotEmpty(internalRequest, ORDER, request.getOrder());
        addParameterIfNotEmpty(internalRequest, ORDER_BY, request.getOrderBy());
        addParameterIfNotEmpty(internalRequest, "restoreStatus", request.getRestoreStatus());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListRestoresResponse.class);
    }

    /**
     * 查询可恢复集群
     * @param request 查询可恢复集群请求
     * @return 可恢复集群列表响应
     */
    public ListRestoreClustersResponse listRestoreClusters(ListRestoreClustersRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getSnapshotId(), "snapshotId should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), BACKUP, SNAPSHOTS,
                String.valueOf(request.getSnapshotId()), "restore-clusters");
        addParameterIfNotNull(internalRequest, "includeSelf", request.getIncludeSelf());
        addParameterIfNotEmpty(internalRequest, "keyword", request.getKeyword());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListRestoreClustersResponse.class);
    }

    /**
     * 创建自动快照配置
     * @param request 创建自动快照配置请求
     * @return 快照配置 ID 响应
     */
    public ConfigIdResponse createAutoSnapshotConfig(CreateAutoSnapshotConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));
        checkStringNotEmpty(request.getPeriod(), checkEmptyExceptionMessageFormat("period"));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(),
                BACKUP, AUTO_SNAPSHOT_CONFIGS);
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, ConfigIdResponse.class);
    }

    /**
     * 更新自动快照配置
     * @param request 更新自动快照配置请求
     * @return 快照配置 ID 响应
     */
    public ConfigIdResponse updateAutoSnapshotConfig(UpdateAutoSnapshotConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getConfigId(), "configId should not be null.");
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));
        checkStringNotEmpty(request.getPeriod(), checkEmptyExceptionMessageFormat("period"));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(),
                BACKUP, AUTO_SNAPSHOT_CONFIGS, String.valueOf(request.getConfigId()));
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, ConfigIdResponse.class);
    }

    /**
     * 删除自动快照配置
     * @param request 删除自动快照配置请求
     * @return 被删除的快照配置 ID 响应
     */
    public ConfigIdResponse deleteAutoSnapshotConfig(DeleteAutoSnapshotConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getConfigId(), "configId should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.DELETE, V3_PREFIX, CLUSTERS, request.getClusterId(),
                BACKUP, AUTO_SNAPSHOT_CONFIGS, String.valueOf(request.getConfigId()));
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ConfigIdResponse.class);
    }

    /**
     * 查询自动快照配置列表
     * @param request 查询自动快照配置列表请求
     * @return 自动快照配置列表响应
     */
    public ListAutoSnapshotConfigsResponse listAutoSnapshotConfigs(ListAutoSnapshotConfigsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(),
                BACKUP, AUTO_SNAPSHOT_CONFIGS);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListAutoSnapshotConfigsResponse.class);
    }

    /**
     * 批量启用/禁用自动快照配置
     * @param request 批量启用/禁用自动快照配置请求
     * @return 批量启用/禁用自动快照配置响应
     */
    public BatchUpdateAutoSnapshotConfigStatusResponse batchUpdateAutoSnapshotConfigStatus(
            BatchUpdateAutoSnapshotConfigStatusRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getEnabled(), "enabled should not be null.");
        checkListNotEmpty(request.getConfigIdList(), "configIdList should not be empty.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PATCH, V3_PREFIX, CLUSTERS, request.getClusterId(),
                BACKUP, AUTO_SNAPSHOT_CONFIGS);
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, BatchUpdateAutoSnapshotConfigStatusResponse.class);
    }

    /**
     * 查询快照配置详情
     * @param request 查询快照配置详情请求
     * @return 快照配置详情响应
     */
    public SnapshotConfigDetailResponse getSnapshotConfigDetail(GetSnapshotConfigDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getConfigId(), "configId should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(),
                BACKUP, SNAPSHOT_CONFIGS, String.valueOf(request.getConfigId()));
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, SnapshotConfigDetailResponse.class);
    }

    /** ========================================= BCT（云审计/控制面审计） API ==================================================== */

    /**
     * 查询BCT授权开关
     * @param request 查询BCT授权开关请求
     * @return 查询BCT授权开关响应
     */
    public BctAuthSwitchResponse getBctAuthSwitch(GetBctAuthSwitchRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, BCT, "auth", "switch");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, BctAuthSwitchResponse.class);
    }

    /**
     * 更新BCT授权开关
     * @param request 更新BCT授权开关请求
     * @return 更新BCT授权开关响应
     */
    public BctAuthSwitchResponse updateBctAuthSwitch(UpdateBctAuthSwitchRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkNotNull(request.getEnabled(), "enabled should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, BCT, "auth", "switch");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, BctAuthSwitchResponse.class);
    }

    /**
     * 搜索云审计事件
     * @param request 搜索云审计事件请求
     * @return 搜索云审计事件响应
     */
    public SearchBctEventsResponse searchBctEvents(SearchBctEventsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkNotNull(request.getStartTime(), checkEmptyExceptionMessageFormat(START_TIME));
        checkNotNull(request.getEndTime(), checkEmptyExceptionMessageFormat(END_TIME));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, BCT, EVENTS, "query");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, SearchBctEventsResponse.class);
    }

    /** ========================================= Audit API ================================================================ */

    /**
     * 查询数据面审计状态
     * @param request 查询审计状态请求
     * @return 审计状态响应
     */
    public AuditStatusResponse getAuditStatus(GetAuditStatusRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), AUDIT);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, AuditStatusResponse.class);
    }

    /**
     * 开启数据面审计
     * @param request 开启审计请求
     * @return 审计状态响应
     */
    public EnableAuditResponse enableAudit(EnableAuditRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), AUDIT);
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, EnableAuditResponse.class);
    }

    /**
     * 查询审计事件类型
     * @param request 查询审计事件类型请求
     * @return 审计事件类型响应
     */
    public AuditEventTypesResponse getAuditEventTypes(GetAuditEventTypesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), AUDIT, EVENT_TYPES);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, AuditEventTypesResponse.class);
    }

    /**
     * 搜索审计事件
     * @param request 搜索审计事件请求
     * @return 搜索审计事件响应
     */
    public SearchAuditEventsResponse searchAuditEvents(SearchAuditEventsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), AUDIT, EVENTS);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        addParameterIfNotEmpty(internalRequest, START_TIME, request.getStartTime());
        addParameterIfNotEmpty(internalRequest, END_TIME, request.getEndTime());
        addParameterIfNotEmpty(internalRequest, CATEGORY, request.getCategory());
        addParameterIfNotEmpty(internalRequest, "indexName", request.getIndexName());
        addParameterIfNotNull(internalRequest, PAGE_SIZE, request.getPageSize());
        addParameterIfNotEmpty(internalRequest, CURSOR, request.getCursor());
        return invokeHttpClient(internalRequest, SearchAuditEventsResponse.class);
    }

    /** ========================================= Plugins API ============================================================== */

    /**
     * 查询系统插件列表
     * @param request 查询系统插件列表请求
     * @return 查询系统插件列表响应
     */
    public ListSystemPluginsResponse listSystemPlugins(ListSystemPluginsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), SYSTEM_PLUGINS);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListSystemPluginsResponse.class);
    }

    /**
     * 查询自定义插件列表
     * @param request 查询自定义插件列表请求
     * @return 查询自定义插件列表响应
     */
    public ListCustomPluginsResponse listCustomPlugins(ListCustomPluginsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), CUSTOM_PLUGINS);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListCustomPluginsResponse.class);
    }

    /**
     * 更新系统插件
     * @param request 更新系统插件请求
     * @return 更新系统插件响应
     */
    public UpdatePluginResponse updateSystemPlugins(UpdateSystemPluginsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), SYSTEM_PLUGINS);
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdatePluginResponse.class);
    }

    /**
     * 更新自定义插件
     * @param request 更新自定义插件请求
     * @return 更新自定义插件响应
     */
    public UpdatePluginResponse updateCustomPlugins(UpdateCustomPluginsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), CUSTOM_PLUGINS);
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdatePluginResponse.class);
    }

    /**
     * 删除自定义插件版本
     * @param request 删除自定义插件版本请求
     * @return 是否删除成功
     */
    public DeleteCustomPluginVersionResponse deleteCustomPluginVersion(DeleteCustomPluginVersionRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getPluginName(), checkEmptyExceptionMessageFormat("pluginName"));
        checkStringNotEmpty(request.getVersion(), checkEmptyExceptionMessageFormat("version"));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.DELETE, V3_PREFIX, CLUSTERS, request.getClusterId(),
                CUSTOM_PLUGINS, request.getPluginName(), "versions", request.getVersion());
        return invokeHttpClient(internalRequest, DeleteCustomPluginVersionResponse.class);
    }

    /**
     * 上传文件
     * @param request 上传文件请求
     * @return 上传文件响应
     */
    public UploadFileResponse uploadFile(UploadFileRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getFile(), "file should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), FILES);
        addMultipartBody(internalRequest, request);
        try {
            return invokeHttpClient(internalRequest, UploadFileResponse.class);
        } finally {
            closeQuietly(internalRequest.getContent());
        }
    }

    /** ========================================= Billing API ================================================================ */

    /**
     * 后付费转预付费
     * @param request 后付费转预付费请求
     * @return 转预付费订单响应
     */
    public OrderIdResponse convertToPrepay(ConvertToPrepayRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getTimeLength(), "timeLength should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, ORDERS, "to-prepay");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, OrderIdResponse.class);
    }

    /**
     * 预付费转后付费
     * @param request 预付费转后付费请求
     * @return 转后付费订单响应
     */
    public OrderIdResponse convertToPostpay(ConvertToPostpayRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, ORDERS, TO_POSTPAY);
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, OrderIdResponse.class);
    }

    /**
     * 取消预付费转后付费订单
     * @param request 取消转后付费订单请求
     * @return 被取消的转后付费订单响应
     */
    public OrderIdResponse cancelConvertToPostpay(CancelConvertToPostpayRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, ORDERS, TO_POSTPAY, "cancel");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, OrderIdResponse.class);
    }

    /**
     * 集群续费
     * @param request 集群续费请求
     * @return 续费订单响应
     */
    public OrderIdResponse renewCluster(RenewClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getTimeLength(), "timeLength should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, ORDER, "renew", "confirm");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, OrderIdResponse.class);
    }

    /**
     * 查询配置价格
     * @param requestx 查询配置价格请求
     * @return 配置价格响应
     */
    public QueryConfigPriceResponse queryConfigPrice(QueryConfigPriceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getPayment(), checkEmptyExceptionMessageFormat(PAYMENT));
        checkNotNull(request.getNodeSpecs(), "nodeSpecs should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, ORDERS, "prices");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, QueryConfigPriceResponse.class);
    }

    /**
     * 查询集群变配价格
     * @param request 查询集群变配价格请求
     * @return 集群变配价格响应
     */
    public QueryClusterMarginPriceResponse queryClusterMarginPrice(QueryClusterMarginPriceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getNodeSpecs(), "nodeSpecs should not be null.");
        checkStringNotEmpty(request.getMode(), checkEmptyExceptionMessageFormat("mode"));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, "margin-prices");
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, QueryClusterMarginPriceResponse.class);
    }

    /** ========================================= Diagnosis API ============================================================ */

    /**
     * 查询自动巡检状态
     * @param request 查询自动巡检状态请求
     * @return 自动巡检状态响应
     */
    public AutoDiagnosisStatusResponse getAutoDiagnosisStatus(GetAutoDiagnosisStatusRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), DIAGNOSIS, AUTO);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, AutoDiagnosisStatusResponse.class);
    }

    /**
     * 更新自动巡检开关
     * @param request 更新自动巡检开关请求
     * @return 自动巡检状态响应
     */
    public AutoDiagnosisStatusResponse updateAutoDiagnosisStatus(UpdateAutoDiagnosisStatusRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getEnabled(), "enabled should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), DIAGNOSIS, AUTO);
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, AutoDiagnosisStatusResponse.class);
    }

    /**
     * 查询手动巡检配置
     * @param request 查询手动巡检配置请求
     * @return 手动巡检配置响应
     */
    public ManualDiagnosisConfigResponse getManualDiagnosisConfig(GetManualDiagnosisConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), DIAGNOSIS, MANUAL);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ManualDiagnosisConfigResponse.class);
    }

    /**
     * 更新手动巡检配置
     * @param request 更新手动巡检配置请求
     * @return 更新手动巡检配置响应
     */
    public UpdateManualDiagnosisConfigResponse updateManualDiagnosisConfig(UpdateManualDiagnosisConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkNotNull(request.getItems(), "items should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(), DIAGNOSIS, MANUAL);
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateManualDiagnosisConfigResponse.class);
    }

    /**
     * 创建手动巡检
     * @param request 创建手动巡检请求
     * @return 创建手动巡检响应
     */
    public CreateManualDiagnosisResponse createManualDiagnosis(CreateManualDiagnosisRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), DIAGNOSIS, MANUAL);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, CreateManualDiagnosisResponse.class);
    }

    /**
     * 查询手动巡检次数
     * @param request 查询手动巡检次数请求
     * @return 手动巡检次数响应
     */
    public ManualDiagnosisCountResponse getManualDiagnosisCount(GetManualDiagnosisCountRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), DIAGNOSIS, MANUAL, "count");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ManualDiagnosisCountResponse.class);
    }

    /**
     * 查询巡检授权状态
     * @param request 查询巡检授权状态请求
     * @return 巡检授权状态响应
     */
    public DiagnosisAuthorizeStatusResponse getDiagnosisAuthorizeStatus(GetDiagnosisAuthorizeStatusRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), DIAGNOSIS, AUTHORIZE);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, DiagnosisAuthorizeStatusResponse.class);
    }

    /**
     * 巡检授权
     * @param request 巡检授权请求
     * @return 巡检授权响应
     */
    public AuthorizeDiagnosisResponse authorizeDiagnosis(AuthorizeDiagnosisRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), DIAGNOSIS, AUTHORIZE);
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, AuthorizeDiagnosisResponse.class);
    }

    /**
     * 查询巡检忙碌状态
     * @param request 查询巡检忙碌状态请求
     * @return 巡检忙碌状态响应
     */
    public DiagnosisBusyStatusResponse getDiagnosisBusyStatus(GetDiagnosisBusyStatusRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), DIAGNOSIS, "busy");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, DiagnosisBusyStatusResponse.class);
    }

    /**
     * 查询巡检项列表
     * @param request 查询巡检项列表请求
     * @return 巡检项列表响应
     */
    public ListDiagnosisItemsResponse listDiagnosisItems(ListDiagnosisItemsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), DIAGNOSIS, "items");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListDiagnosisItemsResponse.class);
    }

    /**
     * 查询每周巡检概览
     * @param request 查询每周巡检概览请求
     * @return 每周巡检概览响应
     */
    public WeeklyDiagnosisOverviewResponse getWeeklyDiagnosisOverview(GetWeeklyDiagnosisOverviewRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(),
                DIAGNOSIS, OVERVIEW, "weekly");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, WeeklyDiagnosisOverviewResponse.class);
    }

    /**
     * 查询最新巡检概览
     * @param request 查询最新巡检概览请求
     * @return 最新巡检概览响应
     */
    public LatestDiagnosisOverviewResponse getLatestDiagnosisOverview(GetLatestDiagnosisOverviewRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(),
                DIAGNOSIS, OVERVIEW, "latest");
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, LatestDiagnosisOverviewResponse.class);
    }

    /**
     * 查询巡检报告列表
     * @param request 查询巡检报告列表请求
     * @return 巡检报告列表响应
     */
    public ListDiagnosisReportsResponse listDiagnosisReports(ListDiagnosisReportsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), DIAGNOSIS, REPORTS);
        addParameterIfNotNull(internalRequest, PAGE_NO, request.getPageNo());
        addParameterIfNotNull(internalRequest, PAGE_SIZE, request.getPageSize());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListDiagnosisReportsResponse.class);
    }

    /**
     * 查询巡检报告详情
     * @param request 查询巡检报告详情请求
     * @return 巡检报告详情响应
     */
    public DiagnosisReportDetailResponse getDiagnosisReportDetail(GetDiagnosisReportDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getReportId(), checkEmptyExceptionMessageFormat("reportId"));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(),
                DIAGNOSIS, REPORTS, request.getReportId());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, DiagnosisReportDetailResponse.class);
    }

    /** ========================================= Schedule API ============================================================= */

    /**
     * 创建定时任务
     * @param request 创建定时任务请求
     * @return 创建定时任务响应
     */
    public CreateScheduleResponse createSchedule(CreateScheduleRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getScheduleName(), checkEmptyExceptionMessageFormat("scheduleName"));
        checkStringNotEmpty(request.getScheduleType(), checkEmptyExceptionMessageFormat("scheduleType"));
        checkStringNotEmpty(request.getCronExpr(), checkEmptyExceptionMessageFormat("cronExpr"));
        checkNotNull(request.getParams(), "params should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.POST, V3_PREFIX, CLUSTERS, request.getClusterId(), SCHEDULES);
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateScheduleResponse.class);
    }

    /**
     * 查询定时任务列表
     * @param request 查询定时任务列表请求
     * @return 定时任务列表响应
     */
    public ListSchedulesResponse listSchedules(ListSchedulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.GET, V3_PREFIX, CLUSTERS, request.getClusterId(), SCHEDULES);
        addParameterIfNotNull(internalRequest, PAGE_NO, request.getPageNo());
        addParameterIfNotNull(internalRequest, PAGE_SIZE, request.getPageSize());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, ListSchedulesResponse.class);
    }

    /**
     * 修改定时任务
     * @param request 修改定时任务请求
     * @return 修改定时任务响应
     */
    public UpdateScheduleResponse updateSchedule(UpdateScheduleRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getScheduleId(), checkEmptyExceptionMessageFormat(SCHEDULE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getCronExpr(), checkEmptyExceptionMessageFormat("cronExpr"));
        checkNotNull(request.getParams(), "params should not be null.");

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.PUT, V3_PREFIX, CLUSTERS, request.getClusterId(),
                SCHEDULES, request.getScheduleId());
        addJsonBody(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateScheduleResponse.class);
    }

    /**
     * 删除定时任务
     * @param request 删除定时任务请求
     * @return 删除定时任务响应
     */
    public DeleteScheduleResponse deleteSchedule(DeleteScheduleRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getScheduleId(), checkEmptyExceptionMessageFormat(SCHEDULE_ID_MESSAGE_KEY));

        InternalRequest internalRequest = createRequest(
                request, HttpMethodName.DELETE, V3_PREFIX, CLUSTERS, request.getClusterId(),
                SCHEDULES, request.getScheduleId());
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        return invokeHttpClient(internalRequest, DeleteScheduleResponse.class);
    }

    /** ========================================= General Functions ======================================================== */

    private InternalRequest createRequest(
            AbstractBceRequest bceRequest, HttpMethodName httpMethod, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        if (pathVariables != null) {
            path.addAll(Arrays.asList(pathVariables));
        }

        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());
        if (region != null) {
            request.addHeader(X_REGION, region);
        }
        return request;
    }

    /**
     * The encryption implement for AES-128 algorithm for BCE password encryption.
     * Only the first 16 bytes of privateKey will be used to encrypt the content.
     *
     * @param content    The content String to encrypt.
     * @param privateKey The security key to encrypt.
     *                   Only the first 16 bytes of privateKey will be used to encrypt the content.
     * @return The encrypted string of the original content with AES-128 algorithm.
     * @throws GeneralSecurityException
     */
    private String aes128WithFirst16Char(String content, String privateKey) throws GeneralSecurityException {
        checkStringNotEmpty(privateKey, "privateKey should not be empty.");
        checkIsTrue(privateKey.length() >= 16, "privateKey length should be at least 16.");
        try {
            SecretKeySpec skey = new SecretKeySpec(
                    privateKey.substring(0, 16).getBytes(DEFAULT_ENCODING), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            byte[] crypted = cipher.doFinal(content.getBytes(DEFAULT_ENCODING));
            return new String(Hex.encodeHex(crypted, false));
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Encryption procedure exception", e);
        }
    }

    private void addParameterIfNotNull(InternalRequest request, String name, Object value) {
        if (value != null) {
            request.addParameter(name, String.valueOf(value));
        }
    }

    private void addParameterIfNotEmpty(InternalRequest request, String name, String value) {
        if (value != null && !value.trim().isEmpty()) {
            request.addParameter(name, value);
        }
    }

    private void addJsonBody(InternalRequest internalRequest, Object payload) {
        byte[] bytes = toJsonBytes(payload);
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(bytes.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE_JSON);
        internalRequest.setContent(RestartableInputStream.wrap(bytes));
    }

    private byte[] toJsonBytes(Object payload) {
        try {
            return JsonUtils.toJsonString(payload).getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to encode json request body", e);
        }
    }

    private void addMultipartBody(InternalRequest internalRequest, UploadFileRequest request) {
        MultipartFormBody body = buildMultipartFormBody(request);
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

    private MultipartFormBody buildMultipartFormBody(UploadFileRequest request) {
        File file = request.getFile();
        validateUploadFile(file);
        String boundary = UUID.randomUUID().toString().replace("-", "");
        try {
            ByteArrayOutputStream preambleStream = new ByteArrayOutputStream();
            writeMultipartFormField(preambleStream, boundary, "type", request.getType());
            writeMultipartFormField(preambleStream, boundary, "subtype", request.getSubtype());
            writeMultipartFilePartHeader(preambleStream, boundary, "file", file);
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

}
