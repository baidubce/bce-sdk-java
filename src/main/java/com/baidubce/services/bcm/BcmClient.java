package com.baidubce.services.bcm;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.SignOptions;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcm.handler.BcmJsonResponseHandler;
import com.baidubce.services.bcm.model.Dimension;
import com.baidubce.services.bcm.model.EmptyResponse;
import com.baidubce.services.bcm.model.ListMetricDataRequest;
import com.baidubce.services.bcm.model.ListMetricDataResponse;
import com.baidubce.services.bcm.model.ListResponse;
import com.baidubce.services.bcm.model.MapListResponse;
import com.baidubce.services.bcm.model.MetricDataRequest;
import com.baidubce.services.bcm.model.MetricDataResponse;
import com.baidubce.services.bcm.model.Page;
import com.baidubce.services.bcm.model.PushCustomMetricDataRequest;
import com.baidubce.services.bcm.model.PushMetricDataResponse;
import com.baidubce.services.bcm.model.Statistics;
import com.baidubce.services.bcm.model.action.CreateAndUpdateActionRequest;
import com.baidubce.services.bcm.model.action.DeleteActionRequest;
import com.baidubce.services.bcm.model.action.ListActionsRequest;
import com.baidubce.services.bcm.model.action.ListActionsResponse;
import com.baidubce.services.bcm.model.action.ListNotifyGroupsResponse;
import com.baidubce.services.bcm.model.action.ListNotifyPartiesResponse;
import com.baidubce.services.bcm.model.action.NotifyRequest;
import com.baidubce.services.bcm.model.alarm.AlarmConfig;
import com.baidubce.services.bcm.model.alarm.AlarmConfigV2;
import com.baidubce.services.bcm.model.alarm.AlarmMetric;
import com.baidubce.services.bcm.model.alarm.TargetType;
import com.baidubce.services.bcm.model.alarm.request.CommonAlarmConfigRequest;
import com.baidubce.services.bcm.model.alarm.request.CreateOrUpdateAlarmConfigRequest;
import com.baidubce.services.bcm.model.alarm.request.CreateOrUpdateAlarmConfigV2Request;
import com.baidubce.services.bcm.model.alarm.request.ListAlarmMetricsRequest;
import com.baidubce.services.bcm.model.alarm.request.ListSingleInstanceAlarmConfigsRequest;
import com.baidubce.services.bcm.model.alarm.response.CreateAlarmConfigV2Response;
import com.baidubce.services.bcm.model.application.ApplicationAlarmConfig;
import com.baidubce.services.bcm.model.application.ApplicationDataListRequest;
import com.baidubce.services.bcm.model.application.ApplicationDataListResponse;
import com.baidubce.services.bcm.model.application.ApplicationDimensionTableDeleteRequest;
import com.baidubce.services.bcm.model.application.ApplicationDimensionTableInfoRequest;
import com.baidubce.services.bcm.model.application.ApplicationDimensionTableInfoResponse;
import com.baidubce.services.bcm.model.application.ApplicationDimensionTableListRequest;
import com.baidubce.services.bcm.model.application.ApplicationInfoDetaleRequest;
import com.baidubce.services.bcm.model.application.ApplicationInfoRequest;
import com.baidubce.services.bcm.model.application.ApplicationInfoResponse;
import com.baidubce.services.bcm.model.application.ApplicationInfoUpdateRequest;
import com.baidubce.services.bcm.model.application.ApplicationInfoUpdateResponse;
import com.baidubce.services.bcm.model.application.ApplicationInstanceCreateRequest;
import com.baidubce.services.bcm.model.application.ApplicationInstanceCreatedListRequest;
import com.baidubce.services.bcm.model.application.ApplicationInstanceDeleteRequest;
import com.baidubce.services.bcm.model.application.ApplicationInstanceListRequest;
import com.baidubce.services.bcm.model.application.ApplicationInstanceListResponse;
import com.baidubce.services.bcm.model.application.ApplicationMetric;
import com.baidubce.services.bcm.model.application.ApplicationMonitorResponse;
import com.baidubce.services.bcm.model.application.ApplicationMonitorTaskDeleteRequest;
import com.baidubce.services.bcm.model.application.ApplicationMonitorTaskDetailRequest;
import com.baidubce.services.bcm.model.application.ApplicationMonitorTaskInfoRequest;
import com.baidubce.services.bcm.model.application.ApplicationMonitorTaskListRequest;
import com.baidubce.services.bcm.model.application.ApplicationMonitorTaskResponse;
import com.baidubce.services.bcm.model.application.CreateOrUpdateAlarmConfigForApplicationRequest;
import com.baidubce.services.bcm.model.application.DeleteAlarmConfigForApplicationRequest;
import com.baidubce.services.bcm.model.application.GetAlarmConfigForApplicationRequest;
import com.baidubce.services.bcm.model.application.GetMetricDataForApplicationRequest;
import com.baidubce.services.bcm.model.application.GetMetricMetaForApplicationRequest;
import com.baidubce.services.bcm.model.application.ListAlarmConfigForApplicationRequest;
import com.baidubce.services.bcm.model.application.ListAlarmMetricsForApplicationRequest;
import com.baidubce.services.bcm.model.application.LogExtractRequest;
import com.baidubce.services.bcm.model.application.LogExtractResult;
import com.baidubce.services.bcm.model.application.MetricDataForApplication;
import com.baidubce.services.bcm.model.application.MonitorObjectType;
import com.baidubce.services.bcm.model.custom.AlarmPolicyBatch;
import com.baidubce.services.bcm.model.custom.AlarmPolicyBatchListRequest;
import com.baidubce.services.bcm.model.custom.BatchDeleteNamespaceEventsRequest;
import com.baidubce.services.bcm.model.custom.BatchDeleteNamespaceMetricsRequest;
import com.baidubce.services.bcm.model.custom.BatchDeleteNamespacesRequest;
import com.baidubce.services.bcm.model.custom.CustomAlarmConfigRequest;
import com.baidubce.services.bcm.model.custom.CustomAlarmConfigResponse;
import com.baidubce.services.bcm.model.custom.CustomAlarmRule;
import com.baidubce.services.bcm.model.custom.CustomMonitorResponse;
import com.baidubce.services.bcm.model.custom.DetailCustomAlarmConfigRequest;
import com.baidubce.services.bcm.model.custom.GetCustomEventRequest;
import com.baidubce.services.bcm.model.custom.GetCustomEventResponse;
import com.baidubce.services.bcm.model.custom.GetCustomMetricRequest;
import com.baidubce.services.bcm.model.custom.GetCustomMetricResponse;
import com.baidubce.services.bcm.model.custom.ListCustomAlarmConfigRequest;
import com.baidubce.services.bcm.model.custom.ListCustomConfigResponse;
import com.baidubce.services.bcm.model.custom.ListNamespaceEventsRequest;
import com.baidubce.services.bcm.model.custom.ListNamespaceEventsResponse;
import com.baidubce.services.bcm.model.custom.ListNamespaceMetricsRequest;
import com.baidubce.services.bcm.model.custom.ListNamespaceMetricsResponse;
import com.baidubce.services.bcm.model.custom.ListNamespacesRequest;
import com.baidubce.services.bcm.model.custom.ListNamespacesResponse;
import com.baidubce.services.bcm.model.custom.NamespaceEventRequest;
import com.baidubce.services.bcm.model.custom.NamespaceMetricDimension;
import com.baidubce.services.bcm.model.custom.NamespaceMetricRequest;
import com.baidubce.services.bcm.model.custom.NamespaceRequest;
import com.baidubce.services.bcm.model.dashboard.DashboardBaseRequest;
import com.baidubce.services.bcm.model.dashboard.DashboardBillboardDataResponse;
import com.baidubce.services.bcm.model.dashboard.DashboardCreateResponse;
import com.baidubce.services.bcm.model.dashboard.DashboardDataRequest;
import com.baidubce.services.bcm.model.dashboard.DashboardDimensionsRequest;
import com.baidubce.services.bcm.model.dashboard.DashboardReportDataResponse;
import com.baidubce.services.bcm.model.dashboard.DashboardResponse;
import com.baidubce.services.bcm.model.dashboard.DashboardTrendDataResponse;
import com.baidubce.services.bcm.model.dashboard.DashboardTrendSeniorDataResponse;
import com.baidubce.services.bcm.model.dashboard.DashboardWidgetRequest;
import com.baidubce.services.bcm.model.event.CloudEventResponse;
import com.baidubce.services.bcm.model.event.EventDataRequest;
import com.baidubce.services.bcm.model.event.EventFilter;
import com.baidubce.services.bcm.model.event.EventLevel;
import com.baidubce.services.bcm.model.event.EventPolicy;
import com.baidubce.services.bcm.model.event.EventPolicyResponse;
import com.baidubce.services.bcm.model.event.EventResourceFilter;
import com.baidubce.services.bcm.model.event.EventType;
import com.baidubce.services.bcm.model.event.PlatformEventResponse;
import com.baidubce.services.bcm.model.group.IGInstanceListResponse;
import com.baidubce.services.bcm.model.group.IGInstanceQuery;
import com.baidubce.services.bcm.model.group.IGInstanceQueryType;
import com.baidubce.services.bcm.model.group.InstanceGroup;
import com.baidubce.services.bcm.model.group.InstanceGroupBase;
import com.baidubce.services.bcm.model.group.InstanceGroupListResponse;
import com.baidubce.services.bcm.model.group.InstanceGroupQuery;
import com.baidubce.services.bcm.model.group.InstanceGroupResponse;
import com.baidubce.services.bcm.model.group.MergedGroup;
import com.baidubce.services.bcm.model.metrics.MultiDimensionalMetricsRequest;
import com.baidubce.services.bcm.model.metrics.PartialDimensionsMetricsRequest;
import com.baidubce.services.bcm.model.metrics.TsdbMetricAllDataResult;
import com.baidubce.services.bcm.model.metrics.TsdbMetricResult;
import com.baidubce.services.bcm.model.site.DnsTaskRequest;
import com.baidubce.services.bcm.model.site.DnsTaskResponse;
import com.baidubce.services.bcm.model.site.FtpTaskRequest;
import com.baidubce.services.bcm.model.site.FtpTaskResponse;
import com.baidubce.services.bcm.model.site.HttpTaskRequest;
import com.baidubce.services.bcm.model.site.HttpTaskResponse;
import com.baidubce.services.bcm.model.site.HttpsTaskRequest;
import com.baidubce.services.bcm.model.site.HttpsTaskResponse;
import com.baidubce.services.bcm.model.site.IdcIspResponse;
import com.baidubce.services.bcm.model.site.PageData;
import com.baidubce.services.bcm.model.site.PageResultResponse;
import com.baidubce.services.bcm.model.site.PingTaskRequest;
import com.baidubce.services.bcm.model.site.PingTaskResponse;
import com.baidubce.services.bcm.model.site.SiteAgentRequest;
import com.baidubce.services.bcm.model.site.SiteAgentResponse;
import com.baidubce.services.bcm.model.site.SiteAlarmConfigDetailResponse;
import com.baidubce.services.bcm.model.site.SiteAlarmConfigListRequest;
import com.baidubce.services.bcm.model.site.SiteAlarmConfigRequest;
import com.baidubce.services.bcm.model.site.SiteAlarmRule;
import com.baidubce.services.bcm.model.site.SiteAlarmUserIdRequest;
import com.baidubce.services.bcm.model.site.SiteBasicResponse;
import com.baidubce.services.bcm.model.site.SiteInfoResponse;
import com.baidubce.services.bcm.model.site.SiteMetricDataQueryRequest;
import com.baidubce.services.bcm.model.site.SiteMetricDataQueryResponse;
import com.baidubce.services.bcm.model.site.SiteTaskIspRequest;
import com.baidubce.services.bcm.model.site.SiteTaskRequest;
import com.baidubce.services.bcm.model.site.SiteViewResponse;
import com.baidubce.services.bcm.model.site.TaskDetailRequest;
import com.baidubce.services.bcm.model.site.TaskResponse;
import com.baidubce.services.bcm.model.site.TaskSummaryRequest;
import com.baidubce.services.bcm.model.site.TaskSummaryResponse;
import com.baidubce.services.bcm.model.site.TcpTaskRequest;
import com.baidubce.services.bcm.model.site.TcpTaskResponse;
import com.baidubce.services.bcm.model.site.UdpTaskRequest;
import com.baidubce.services.bcm.model.site.UdpTaskResponse;
import com.baidubce.services.bcm.model.siteonce.EmptyRequest;
import com.baidubce.services.bcm.model.siteonce.HttpResponseWrapper;
import com.baidubce.services.bcm.model.siteonce.SiteOnceAgent;
import com.baidubce.services.bcm.model.siteonce.SiteOnceGroupTask;
import com.baidubce.services.bcm.model.siteonce.SiteOnceRequest;
import com.baidubce.services.bcm.model.siteonce.SiteOnceTaskList;
import com.baidubce.services.bcm.model.siteonce.SiteOnceTaskRequest;
import com.baidubce.services.bcm.model.siteonce.SiteOnceTaskResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.directory.api.util.Strings;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.StringFormatUtils.stringFormat;
import static com.baidubce.util.Validate.checkIsTrue;
import static com.baidubce.util.Validate.checkListSizeInRange;
import static com.baidubce.util.Validate.checkPattern;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Baidu Cloud Compute Service(bcm).
 */
public class BcmClient extends AbstractBceClient {

    /**
     * Parameters
     */
    private static final String PREFIX = "json-api";
    private static final String V1 = "v1";
    private static final String V3 = "v3";
    private static final String METRIC_DATA = "metricdata";
    private static final String METRIC_NAME = "metricName";
    private static final String USER_ID = "userId";
    private static final String NAME = "name";
    private static final String NAMESPACE = "namespace";
    private static final String PAGE_NO = "pageNo";
    private static final String PAGE_SIZE = "pageSize";
    private static final String EVENT_NAME = "eventName";
    private static final String EVENT_LEVEL = "eventLevel";
    private static final Integer MAX_DIMENSIONS_SIZE = 100;

    private static final String PUSH_CUSTOM_METRIC_DATA_FORMAT = "/csm/api/v1/userId/%s/custom/data";
    private static final String CREATE_NAMESPACE = "/csm/api/v1/userId/%s/custom/namespaces/create";
    private static final String BATCH_DELETE_NAMESPACES = "/csm/api/v1/userId/%s/custom/namespaces/delete";
    private static final String UPDATE_NAMESPACE = "/csm/api/v1/userId/%s/custom/namespaces/update";
    private static final String LIST_NAMESPACES = "/csm/api/v1/userId/%s/custom/namespaces/list";
    private static final String CREATE_NAMESPACE_METRIC = "/csm/api/v1/userId/%s/custom/namespaces/%s/metrics/create";
    private static final String BATCH_DELETE_NAMESPACE_METRICS = "/csm/api/v1/userId/%s/custom/namespaces/%s/metrics/delete";
    private static final String UPDATE_NAMESPACE_METRIC = "/csm/api/v1/userId/%s/custom/namespaces/%s/metrics/%s";
    private static final String LIST_NAMESPACE_METRICS = "/csm/api/v1/userId/%s/custom/namespaces/metrics";
    private static final String GET_CUSTOM_METRIC = "/csm/api/v1/userId/%s/custom/namespaces/%s/metrics/%s";
    private static final String CREATE_NAMESPACE_EVENT = "/csm/api/v1/custom/event/configs/create";
    private static final String BATCH_DELETE_NAMESPACE_EVENTS = "/csm/api/v1/custom/event/configs/delete";
    private static final String UPDATE_NAMESPACE_EVENT = "/csm/api/v1/custom/event/configs/update";
    private static final String LIST_NAMESPACE_EVENTS = "/csm/api/v1/custom/event/configs/list";
    private static final String GET_CUSTOM_EVENT = "/csm/api/v1/custom/event/configs/detail";

    private static final String CREATE_DASHBOARD_PATH = "/csm/api/v1/dashboard/products/%s/dashboards";
    private static final String DASHBOARD_PATH = "/csm/api/v1/dashboard/products/%s/dashboards/%s";
    private static final String DUPLICATE_DASHBOARD_PATH = "/csm/api/v1/dashboard/products/%s/dashboards/%s/duplicate";
    private static final String DASHBOARD_WIDGET_PATH = "/csm/api/v1/dashboard/products/%s/dashboards/%s/widgets/%s";
    private static final String CREATE_DASHBOARD_WIDGET_PATH = "/csm/api/v1/dashboard/products/%s/dashboards/%s/widgets";
    private static final String DUPLICATE_DASHBOARD_WIDGET_PATH =
            "/csm/api/v1/dashboard/products/%s/dashboards/%s/widgets/%s/duplicate";
    private static final String DASHBOARD_REPORT_DATA_PATH = "/csm/api/v1/dashboard/metric/report";
    private static final String DASHBOARD_TREND_DATA_PATH = "/csm/api/v1/dashboard/metric/trend";
    private static final String DASHBOARD_GAUGE_CHART_DATA_PATH = "/csm/api/v1/dashboard/metric/gaugechart";
    private static final String DASHBOARD_BILLBOARD_DATA_PATH = "/csm/api/v1/dashboard/metric/billboard";
    private static final String DASHBOARD_TREND_SENIOR_DATA_PATH = "/csm/api/v1/dashboard/metric/trend/senior";
    private static final String DASHBOARD_DIMENSIONS_PATH = "/csm/api/v1/userId/%s/services/%s/region/%s/metric/dimensions";

    private static final String CLOUD_EVENT_LIST_PATH = "/event-api/v1/bce-event/list";

    private static final String PLATFORM_EVENT_LIST_PATH = "/event-api/v1/platform-event/list";

    private static final String EVENT_POLICY_PATH = "/event-api/v1/accounts/%s/services/%s/alarm-policies";

    private static final String INSTANCE_GROUP_PATH = "/csm/api/v1/userId/%s/instance-group";

    private static final String INSTANCE_GROUP_ID_PATH = "/csm/api/v1/userId/%s/instance-group/%s";

    private static final String INSTANCE_GROUP_LIST_PATH = "/csm/api/v1/userId/%s/instance-group/list";

    private static final String IG_INSTANCE_ADD = "/csm/api/v1/userId/%s/instance-group/%s/instance/add";

    private static final String IG_INSTANCE_REMOVE = "/csm/api/v1/userId/%s/instance-group/%s/instance/remove";

    private static final String IG_INSTANCE_LIST = "/csm/api/v1/userId/%s/instance-group/instance/list";

    private static final String IG_QUERY_INSTANCE_LIST = "/csm/api/v1/userId/%s/instance/list";

    private static final String IG_QUERY_INSTANCE_LIST_FILTER = "/csm/api/v1/userId/%s/instance/filteredList";

    private static final String SITE_CREATE_HTTP_TASK_PATH = "/csm/api/v1/userId/%s/site/http/create";
    private static final String SITE_UPDATE_HTTP_TASK_PATH = "/csm/api/v1/userId/%s/site/http/update";
    private static final String SITE_GET_HTTP_TASK_PATH = "/csm/api/v1/userId/%s/site/http/detail";
    private static final String SITE_CREATE_HTTPS_TASK_PATH = "/csm/api/v1/userId/%s/site/https/create";
    private static final String SITE_UPDATE_HTTPS_TASK_PATH = "/csm/api/v1/userId/%s/site/https/update";
    private static final String SITE_GET_HTTPS_TASK_PATH = "/csm/api/v1/userId/%s/site/https/detail";
    private static final String SITE_CREATE_PING_TASK_PATH = "/csm/api/v1/userId/%s/site/ping/create";
    private static final String SITE_UPDATE_PING_TASK_PATH = "/csm/api/v1/userId/%s/site/ping/update";
    private static final String SITE_GET_PING_TASK_PATH = "/csm/api/v1/userId/%s/site/ping/detail";
    private static final String SITE_CREATE_TCP_TASK_PATH = "/csm/api/v1/userId/%s/site/tcp/create";
    private static final String SITE_UPDATE_TCP_TASK_PATH = "/csm/api/v1/userId/%s/site/tcp/update";
    private static final String SITE_GET_TCP_TASK_PATH = "/csm/api/v1/userId/%s/site/tcp/detail";
    private static final String SITE_CREATE_UDP_TASK_PATH = "/csm/api/v1/userId/%s/site/udp/create";
    private static final String SITE_UPDATE_UDP_TASK_PATH = "/csm/api/v1/userId/%s/site/udp/update";
    private static final String SITE_GET_UDP_TASK_PATH = "/csm/api/v1/userId/%s/site/udp/detail";
    private static final String SITE_CREATE_FTP_TASK_PATH = "/csm/api/v1/userId/%s/site/ftp/create";
    private static final String SITE_UPDATE_FTP_TASK_PATH = "/csm/api/v1/userId/%s/site/ftp/update";
    private static final String SITE_GET_FTP_TASK_PATH = "/csm/api/v1/userId/%s/site/ftp/detail";
    private static final String SITE_CREATE_DNS_TASK_PATH = "/csm/api/v1/userId/%s/site/dns/create";
    private static final String SITE_UPDATE_DNS_TASK_PATH = "/csm/api/v1/userId/%s/site/dns/update";
    private static final String SITE_GET_DNS_TASK_PATH = "/csm/api/v1/userId/%s/site/dns/detail";
    private static final String SITE_GET_TASK_LIST_PATH = "/csm/api/v1/userId/%s/site/list";
    private static final String SITE_DELETE_TASK_PATH = "/csm/api/v1/userId/%s/site/delete";
    private static final String SITE_GET_TASK_DETAIL_PATH = "/csm/api/v1/userId/%s/site/%s";
    private static final String SITE_CREATE_ALARM_CONFIG_PATH = "/csm/api/v1/userId/%s/site/alarm/config/create";
    private static final String SITE_UPDATE_ALARM_CONFIG_PATH = "/csm/api/v1/userId/%s/site/alarm/config/update";
    private static final String SITE_DELETE_ALARM_CONFIG_PATH = "/csm/api/v1/userId/%s/site/alarm/config/delete";
    private static final String SITE_GET_ALARM_CONFIG_DETAIL_PATH = "/csm/api/v1/userId/%s/site/alarm/config/detail";
    private static final String SITE_GE_TALARM_CONFIG_LIST_PATH = "/csm/api/v1/userId/%s/site/alarm/config/list";
    private static final String SITE_ALARM_BLOCK_PATH = "/csm/api/v1/userId/%s/site/alarm/config/block";
    private static final String SITE_ALARM_UNBLOCK_PATH = "/csm/api/v1/userId/%s/site/alarm/config/unblock";
    private static final String SITE_GET_TASK_BY_ALARMNAME_PATH = "/csm/api/v1/userId/%s/site/alarm/config/%s";
    private static final String SITE_GET_METRIC_DATA_PATH = "/csm/api/v1/userId/%s/site/metricSiteData";
    private static final String SITE_GET_OVERALL_VIEW_PATH = "/csm/api/v1/userId/%s/site/idc/overallView";
    private static final String SITE_GET_PROVINCIAL_VIEW_PATH = "/csm/api/v1/userId/%s/site/idc/provincialView";
    private static final String SITE_AGENT_LIST_PATH = "/csm/api/v1/userId/%s/site/agent/list";
    private static final String SITE_GET_AGENT_BY_TASKID_PATH = "/csm/api/v1/userId/%s/site/agent/idcIsp";

    private static final String APPLICATION_INFO_PATH = "/csm/api/v1/userId/%s/application";
    private static final String APPLICATION_INFO_DELETE_PATH = "/csm/api/v1/userId/%s/application/%s/delete";
    private static final String APPLICATION_INFO_LIST_PATH = "/csm/api/v1/userId/%s/instances/all";
    private static final String APPLICATION_INSTANCE_CREATE_PATH = "/csm/api/v1/userId/%s/application/instance/bind";
    private static final String APPLICATION_INSTANCE_CREATED_LIST_PATH = "/csm/api/v1/userId/%s/application/%s/instance/list";
    private static final String APPLICATION_INSTANCE_DELETE_PATH = "/csm/api/v1/userId/%s/application/%s/instance/%s/delete";
    private static final String APPLICATION_TASK_CREATE_PATH = "/csm/api/v1/userId/%s/application/task/create";
    private static final String APPLICATION_TASK_DETAIL_PATH = "/csm/api/v1/userId/%s/application/%s/task/%s";
    private static final String APPLICATION_TASK_LIST_PATH = "/csm/api/v1/userId/%s/application/%s/task/list";
    private static final String APPLICATION_TASK_DELETE_PATH = "/csm/api/v1/userId/%s/application/%s/task/%s/delete";
    private static final String APPLICATION_TASK_UPDATE_PATH = "/csm/api/v1/userId/%s/application/task/update";
    private static final String APPLICATION_DIMENSION_TABLE_CREATE_PATH = "/csm/api/v1/userId/%s/application/dimensionMap/create";
    private static final String APPLICATION_DIMENSION_TABLE_LIST_PATH = "/csm/api/v1/userId/%s/application/%s/dimensionMap/list";
    private static final String APPLICATION_DIMENSION_TABLE_DELETE_PATH = "/csm/api/v1/userId/%s/application/%s/dimensionMap/%s/delete";
    private static final String APPLICATION_DIMENSION_TABLE_UPDATE_PATH = "/csm/api/v1/userId/%s/application/dimensionMap/update";
    private static final String MULTI_DIMENSIONAL_LATEST_METRICS_PATH = "/csm/api/v2/userId/%s/services/%s/data/metricData/latest/batch";
    private static final String METRICS_BY_PARTIAL_DIMENSIONS_PATH = "/csm/api/v2/userId/%s/services/%s/data/metricData/PartialDimension";
    private static final String BATCH_GET_METRICS_PATH = "/csm/api/v2/data/metricAllData/batch";
    private static final String ALL_DATA_METRIC_V2_PATH = "/csm/api/v2/data/metricAllData";
    /**
     * Exceptions
     */
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    private static final String USER_NULL_ERROR_MESSAGE = "userId should not be null";
    private static final String REQUEST_PARAM_NULL_ERROR_MESSAGE = "param %s should not be null";
    private static final String PERIOD_ERROR_MESSAGE = "request %s should be a multiple of 60.";
    private static final String USER_ID_MESSAGE_KEY = "userId";
    private static final String ACCOUNT_ID_MESSAGE_KEY = "accountId";
    private static final String PAGE_NO_MESSAGE_KEY = "pageNo";
    private static final String PAGE_SIZE_MESSAGE_KEY = "pageSize";
    private static final String SCOPE_MESSAGE_KEY = "scope";
    private static final String STATISTICS_ARR_MESSAGE_KEY = "statistics[]";
    private static final String START_TIME_MESSAGE_KEY = "startTime";
    private static final String END_TIME_MESSAGE_KEY = "endTime";
    private static final String TIMESTAMP_MESSAGE_KEY = "timestamp";
    private static final String PERIOD_MESSAGE_KEY = "periodInSecond";

    private static final String DIMENSIONS_MESSAGE_KEY = "dimensions";
    private static final String METRIC_NAME_MESSAGE_KEY = "metricName";
    private static final String METRIC_NAMES_MESSAGE_KEY = "metricName[]";
    private static final String NAMESPACE_MESSAGE_KEY = "namespace";
    private static final String NAME_MESSAGE_KEY = "name";

    private static final String TITLE = "title";
    private static final String CONFIGURE = "configure";
    private static final String TYPE = "type";
    private static final String DASHBOARD_NAME = "dashboardName";
    private static final String WIDGET_NAME = "widgetName";

    private static final String REGION = "region";
    private static final String SERVICE = "service";
    private static final String SHOW_ID = "showId";

    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    /**
     * Responsible for handling httpResponses from all bcm service calls.
     */
    private static final HttpResponseHandler[] BCM_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BcmJsonResponseHandler()
    };


    /**
     * Constructs a new client to invoke service methods on bcm.
     */
    public BcmClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new bbc client using the client configuration to access bcm.
     *
     * @param clientConfiguration The bcc client configuration options controlling how this client
     *                            connects to bbc (e.g. proxy settings, retry counts, etc).
     */
    public BcmClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, BCM_HANDLERS);
    }

    /**
     * Creates and initializes a new request object for the specified bcc resource. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest    The original request, as created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     */
    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(PREFIX);
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
     * create get request with url
     *
     * @param bceRequest request body object
     * @param httpMethod method name，such as put, post, delete et
     * @param url        method url
     * @return InternalRequest
     */
    private InternalRequest createRequestWithUrl(AbstractBceRequest bceRequest, HttpMethodName httpMethod, String url) {
        URI uri = HttpUtils.appendUri(this.getEndpoint(), url);
        InternalRequest request = new InternalRequest(httpMethod, uri);
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());
        return request;
    }

    /**
     * create request with json format body
     *
     * @param bceRequest request body object
     * @param httpMethod method name，such as put, post, delete et
     * @param url        method url
     * @return InternalRequest
     */
    private InternalRequest createBodyRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod, String url) {
        URI uri = HttpUtils.appendUri(this.getEndpoint(), url);
        InternalRequest request = new InternalRequest(httpMethod, uri);
        fillPayload(request, bceRequest);
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());
        return request;
    }

    /**
     * the method to fill the internalRequest's content field with bceRequest
     * only support HttpMethodName.POST or HttpMethodName.PUT
     *
     * @param internalRequest A request object, populated with endpoint, resource path, ready for callers to populate
     *                        any additional headers or parameters, and execute.
     * @param bceRequest      The original request, as created by the user.
     */
    protected void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() == HttpMethodName.POST
                || internalRequest.getHttpMethod() == HttpMethodName.PUT
                || internalRequest.getHttpMethod() == HttpMethodName.PATCH) {
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

    /**
     * check v1 request and warp
     *
     * @param request
     */
    private void checkV1Request(AbstractBceRequest request, InternalRequest internalRequest,
                                String userId, String scope,
                                Statistics[] statistics, String startTime, String endTime, Integer periodInSecond) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(userId, checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check service
        checkStringNotEmpty(scope, checkEmptyExceptionMessageFormat(SCOPE_MESSAGE_KEY));
        // check statistics
        checkNotNull(statistics, checkEmptyExceptionMessageFormat(STATISTICS_ARR_MESSAGE_KEY));
        checkArgument(statistics.length != 0, checkEmptyExceptionMessageFormat(STATISTICS_ARR_MESSAGE_KEY));
        // check startTime
        checkStringNotEmpty(startTime, checkEmptyExceptionMessageFormat(START_TIME_MESSAGE_KEY));
        // check endTime
        checkStringNotEmpty(endTime, checkEmptyExceptionMessageFormat(END_TIME_MESSAGE_KEY));
        // check periodInSecond
        checkNotNull(periodInSecond, checkEmptyExceptionMessageFormat(PERIOD_MESSAGE_KEY));
        checkArgument((periodInSecond / 60) != 0, stringFormat(PERIOD_ERROR_MESSAGE, PERIOD_MESSAGE_KEY));
        // warp parameters
        internalRequest.addParameter(STATISTICS_ARR_MESSAGE_KEY, StringUtils.join(statistics, ","));
        internalRequest.addParameter(START_TIME_MESSAGE_KEY, startTime);
        internalRequest.addParameter(END_TIME_MESSAGE_KEY, endTime);
        internalRequest.addParameter(PERIOD_MESSAGE_KEY, String.valueOf(periodInSecond));
    }

    /**
     * Get Metric Data.
     *
     * @param request metric data request.
     * @return
     */
    public MetricDataResponse getMetricData(MetricDataRequest request) {
        // check metricName
        checkStringNotEmpty(request.getMetricName(), checkEmptyExceptionMessageFormat(METRIC_NAME_MESSAGE_KEY));
        // Internal Request
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, V1, METRIC_DATA,
                request.getUserId(), request.getScope(), request.getMetricName());
        checkV1Request(request, internalRequest, request.getUserId(), request.getScope(), request.getStatistics(),
                request.getStartTime(), request.getEndTime(), request.getPeriodInSecond());
        // check dimensions
        checkStringNotEmpty(request.getDimensions(), checkEmptyExceptionMessageFormat(DIMENSIONS_MESSAGE_KEY));
        internalRequest.addParameter(DIMENSIONS_MESSAGE_KEY, request.getDimensions());
        return invokeHttpClient(internalRequest, MetricDataResponse.class);
    }

    /**
     * Get List Metric Data.
     *
     * @param request List Metric Data request.
     * @return
     */
    public ListMetricDataResponse getMetricData(ListMetricDataRequest request) {
        // Internal Request
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, V1, METRIC_DATA,
                METRIC_NAME, request.getUserId(), request.getScope());
        checkV1Request(request, internalRequest, request.getUserId(), request.getScope(), request.getStatistics(),
                request.getStartTime(), request.getEndTime(), request.getPeriodInSecond());
        // check metricName
        checkNotNull(request.getMetricNames(), checkEmptyExceptionMessageFormat(METRIC_NAMES_MESSAGE_KEY));
        checkArgument(request.getMetricNames().length != 0,
                checkEmptyExceptionMessageFormat(METRIC_NAMES_MESSAGE_KEY));
        internalRequest.addParameter(METRIC_NAMES_MESSAGE_KEY, StringUtils.join(request.getMetricNames(), ","));
        // check dimensions
        checkStringNotEmpty(request.getDimensions(), checkEmptyExceptionMessageFormat(DIMENSIONS_MESSAGE_KEY));
        internalRequest.addParameter(DIMENSIONS_MESSAGE_KEY, request.getDimensions());
        return invokeHttpClient(internalRequest, ListMetricDataResponse.class);
    }

    /**
     * push custom monitor metric data api
     *
     * @param request request
     * @return PushMetricDataResponse
     */
    public PushMetricDataResponse pushCustomMonitorMetricData(PushCustomMetricDataRequest request) {
        checkAndFormatPushCustomMetricDataRequest(request);
        String url = String.format(PUSH_CUSTOM_METRIC_DATA_FORMAT, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, PushMetricDataResponse.class);
    }

    /**
     * check and format request
     *
     * @param request push custom metric reqeuest
     */
    private void checkAndFormatPushCustomMetricDataRequest(PushCustomMetricDataRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check namespace
        checkStringNotEmpty(request.getTimestamp(), checkEmptyExceptionMessageFormat(NAMESPACE_MESSAGE_KEY));
        // check metric name
        checkStringNotEmpty(request.getMetricName(), checkEmptyExceptionMessageFormat(METRIC_NAME_MESSAGE_KEY));
        // check timestamp
        checkStringNotEmpty(request.getTimestamp(), checkEmptyExceptionMessageFormat(TIMESTAMP_MESSAGE_KEY));
        // check value
        if (null == request.getValue() && null == request.getStatisticValues()) {
            throw new IllegalArgumentException("value and statistics all should not be null.");
        }

        if (null == request.getDimensions()) {
            request.setDimensions(Collections.<Dimension>emptyList());
        }
    }

    /**
     * 创建自定义空间
     *
     * @param request 请求对象
     * @return CustomMonitorResponse
     */
    public CustomMonitorResponse createNamespace(NamespaceRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check name
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));

        String url = String.format(CREATE_NAMESPACE, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, CustomMonitorResponse.class);
    }

    /**
     * 批量删除自定义空间
     *
     * @param request 请求对象
     * @return CustomMonitorResponse
     */
    public CustomMonitorResponse batchDeleteNamespaces(BatchDeleteNamespacesRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check name
        checkIsTrue(CollectionUtils.isNotEmpty(request.getNames()), "names should not be empty");

        String url = String.format(BATCH_DELETE_NAMESPACES, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, CustomMonitorResponse.class);
    }

    /**
     * 更新自定义空间
     *
     * @param request 请求对象
     * @return CustomMonitorResponse
     */
    public CustomMonitorResponse updateNamespace(NamespaceRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check name
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));

        String url = String.format(UPDATE_NAMESPACE, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT, url);
        return invokeHttpClient(internalRequest, CustomMonitorResponse.class);
    }

    /**
     * 获取或者搜索自定义空间
     *
     * @param request 请求对象
     * @return ListNamespacesResponse
     */
    public ListNamespacesResponse listNamespaces(ListNamespacesRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        if (null == request.getPageNo() || request.getPageNo() <= 0) {
            request.setPageNo(1);
        }
        if (null == request.getPageSize() || request.getPageSize() <= 0) {
            request.setPageSize(10);
        }

        String url = String.format(LIST_NAMESPACES, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        internalRequest.addParameter("name", request.getName());
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        return invokeHttpClient(internalRequest, ListNamespacesResponse.class);
    }

    /**
     * 创建自定义指标
     *
     * @param request 请求对象
     * @return CustomMonitorResponse
     */
    public CustomMonitorResponse createNamespaceMetric(NamespaceMetricRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check namespace
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat(NAMESPACE_MESSAGE_KEY));
        // check metricName
        checkStringNotEmpty(request.getMetricName(), checkEmptyExceptionMessageFormat(METRIC_NAME));
        // check name
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (CollectionUtils.isEmpty(request.getDimensions())) {
            request.setDimensions(Collections.<NamespaceMetricDimension>emptyList());
        }

        String url = String.format(CREATE_NAMESPACE_METRIC, request.getUserId(), request.getNamespace());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, CustomMonitorResponse.class);
    }

    /**
     * 批量删除自定义指标
     *
     * @param request 请求对象
     * @return CustomMonitorResponse
     */
    public CustomMonitorResponse batchDeleteNamespaceMetrics(BatchDeleteNamespaceMetricsRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check ids
        checkIsTrue(CollectionUtils.isNotEmpty(request.getIds()), "ids should not be empty");

        String url = String.format(BATCH_DELETE_NAMESPACE_METRICS, request.getUserId(), request.getNamespace());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, CustomMonitorResponse.class);
    }

    /**
     * 更新自定义指标
     *
     * @param request 请求对象
     * @return CustomMonitorResponse
     */
    public CustomMonitorResponse updateNamespaceMetric(NamespaceMetricRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check namespace
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat(NAMESPACE_MESSAGE_KEY));
        // check metricName
        checkStringNotEmpty(request.getMetricName(), checkEmptyExceptionMessageFormat(METRIC_NAME));
        // check cycle
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (CollectionUtils.isEmpty(request.getDimensions())) {
            request.setDimensions(Collections.<NamespaceMetricDimension>emptyList());
        }

        String url = String.format(UPDATE_NAMESPACE_METRIC,
                request.getUserId(), request.getNamespace(), request.getMetricName());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT, url);
        return invokeHttpClient(internalRequest, CustomMonitorResponse.class);
    }

    /**
     * 获取或者搜索自定义指标
     *
     * @param request 请求对象
     * @return ListNamespaceMetricsResponse
     */
    public ListNamespaceMetricsResponse listNamespaceMetrics(ListNamespaceMetricsRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check namespace
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat(NAMESPACE_MESSAGE_KEY));
        if (null == request.getPageNo() || request.getPageNo() <= 0) {
            request.setPageNo(1);
        }
        if (null == request.getPageSize() || request.getPageSize() <= 0) {
            request.setPageSize(10);
        }

        String url = String.format(LIST_NAMESPACE_METRICS, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        internalRequest.addParameter("namespace", request.getNamespace());
        internalRequest.addParameter("metricName", request.getMetricName());
        internalRequest.addParameter("metricAlias", request.getMetricAlias());
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        return invokeHttpClient(internalRequest, ListNamespaceMetricsResponse.class);
    }

    /**
     * 获取某个自定义指标
     *
     * @param request 请求对象
     * @return GetCustomMetricResponse
     */
    public GetCustomMetricResponse getCustomMetric(GetCustomMetricRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check namespace
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat(NAMESPACE_MESSAGE_KEY));
        // check metricName
        checkStringNotEmpty(request.getMetricName(), checkEmptyExceptionMessageFormat(METRIC_NAME));

        String url = String.format(GET_CUSTOM_METRIC,
                request.getUserId(), request.getNamespace(), request.getMetricName());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        return invokeHttpClient(internalRequest, GetCustomMetricResponse.class);
    }

    /**
     * 创建自定义事件
     *
     * @param request 请求对象
     * @return CustomMonitorResponse
     */
    public CustomMonitorResponse createNamespaceEvent(NamespaceEventRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check namespace
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat(NAMESPACE_MESSAGE_KEY));
        // check eventName
        checkStringNotEmpty(request.getEventName(), checkEmptyExceptionMessageFormat(EVENT_NAME));
        // check event level
        checkNotNull(request.getEventLevel(), "eventLevel should not be null");

        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, CREATE_NAMESPACE_EVENT);
        return invokeHttpClient(internalRequest, CustomMonitorResponse.class);
    }

    /**
     * 批量删除自定义事件
     *
     * @param request 请求对象
     * @return CustomMonitorResponse
     */
    public CustomMonitorResponse batchDeleteNamespaceEvents(BatchDeleteNamespaceEventsRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check namespace
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat(NAMESPACE_MESSAGE_KEY));
        // check names
        checkIsTrue(CollectionUtils.isNotEmpty(request.getNames()), "names should not be empty");

        InternalRequest internalRequest = this.createBodyRequest(
                request, HttpMethodName.POST, BATCH_DELETE_NAMESPACE_EVENTS);
        return invokeHttpClient(internalRequest, CustomMonitorResponse.class);
    }

    /**
     * 更新自定义事件
     *
     * @param request 请求对象
     * @return CustomMonitorResponse
     */
    public CustomMonitorResponse updateNamespaceEvent(NamespaceEventRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check namespace
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat(NAMESPACE_MESSAGE_KEY));
        // check eventName
        checkStringNotEmpty(request.getEventName(), checkEmptyExceptionMessageFormat(EVENT_NAME));
        // check event level
        checkNotNull(request.getEventLevel(), "eventLevel should not be null");

        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, UPDATE_NAMESPACE_EVENT);
        return invokeHttpClient(internalRequest, CustomMonitorResponse.class);
    }

    /**
     * 获取或者搜索自定义事件
     *
     * @param request 请求对象
     * @return ListNamespaceEventsResponse
     */
    public ListNamespaceEventsResponse listNamespaceEvents(ListNamespaceEventsRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check namespace
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat(NAMESPACE_MESSAGE_KEY));
        if (null == request.getPageNo() || request.getPageNo() <= 0) {
            request.setPageNo(1);
        }
        if (null == request.getPageSize() || request.getPageSize() <= 0) {
            request.setPageSize(10);
        }

        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, LIST_NAMESPACE_EVENTS);
        internalRequest.addParameter(USER_ID, request.getUserId());
        internalRequest.addParameter(NAMESPACE, request.getNamespace());
        internalRequest.addParameter(NAME, request.getName());
        if (null != request.getEventLevel()) {
            internalRequest.addParameter(EVENT_LEVEL, request.getEventLevel().name());
        }
        internalRequest.addParameter(PAGE_NO, String.valueOf(request.getPageNo()));
        internalRequest.addParameter(PAGE_SIZE, String.valueOf(request.getPageSize()));
        return invokeHttpClient(internalRequest, ListNamespaceEventsResponse.class);
    }

    /**
     * 获取某个自定义事件
     *
     * @param request 请求对象
     * @return GetCustomEventResponse
     */
    public GetCustomEventResponse getCustomEvent(GetCustomEventRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check namespace
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat(NAMESPACE_MESSAGE_KEY));
        // check eventName
        checkStringNotEmpty(request.getEventName(), checkEmptyExceptionMessageFormat(EVENT_NAME));

        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, GET_CUSTOM_EVENT);
        internalRequest.addParameter(USER_ID, request.getUserId());
        internalRequest.addParameter(NAMESPACE, request.getNamespace());
        internalRequest.addParameter(EVENT_NAME, request.getEventName());
        return invokeHttpClient(internalRequest, GetCustomEventResponse.class);
    }

    /**
     * 创建仪表盘
     *
     * @param request 创建仪表盘请求参数对象
     * @return 创建仪表盘返回结果对象
     */
    public DashboardCreateResponse createDashboard(DashboardBaseRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTitle(), checkEmptyExceptionMessageFormat(TITLE));
        checkStringNotEmpty(request.getConfigure(), checkEmptyExceptionMessageFormat(CONFIGURE));
        checkStringNotEmpty(request.getType(), checkEmptyExceptionMessageFormat(TYPE));

        String url = String.format(CREATE_DASHBOARD_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, DashboardCreateResponse.class);
    }

    /**
     * 获取仪表盘
     *
     * @param request 仪表盘请求对象
     * @return DashboardCreateResponse 仪表盘创建响应对象
     * @throws NullPointerException     如果request为null，抛出NullPointerException异常
     * @throws IllegalArgumentException 如果request中的userId或dashboardName为空，抛出IllegalArgumentException异常
     */
    public DashboardCreateResponse getDashboard(DashboardBaseRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDashboardName(), checkEmptyExceptionMessageFormat(DASHBOARD_NAME));

        String url = String.format(DASHBOARD_PATH, request.getUserId(), request.getDashboardName());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET, url);
        return invokeHttpClient(internalRequest, DashboardCreateResponse.class);
    }

    /**
     * 更新仪表盘
     *
     * @param request 更新仪表盘请求参数
     * @return 更新仪表盘返回结果
     */
    public DashboardResponse updateDashboard(DashboardBaseRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDashboardName(), checkEmptyExceptionMessageFormat(DASHBOARD_NAME));
        checkStringNotEmpty(request.getConfigure(), checkEmptyExceptionMessageFormat(CONFIGURE));
        String url = String.format(DASHBOARD_PATH, request.getUserId(), request.getDashboardName());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT, url);
        return invokeHttpClient(internalRequest, DashboardResponse.class);
    }

    /**
     * 删除仪表盘
     *
     * @param request 仪表盘请求对象
     * @return 返回删除结果
     */
    public DashboardResponse deleteDashboard(DashboardBaseRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDashboardName(), checkEmptyExceptionMessageFormat(DASHBOARD_NAME));
        String url = String.format(DASHBOARD_PATH, request.getUserId(), request.getDashboardName());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.DELETE, url);
        return invokeHttpClient(internalRequest, DashboardResponse.class);
    }

    /**
     * 复制仪表盘
     *
     * @param request 仪表盘复制请求对象
     * @return 复制后的仪表盘响应对象
     * @throws NullPointerException     如果request为null，抛出NullPointerException异常
     * @throws IllegalArgumentException 如果userId或dashboardName为空，抛出IllegalArgumentException异常
     */
    public DashboardResponse duplicateDashboard(DashboardBaseRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDashboardName(), checkEmptyExceptionMessageFormat(DASHBOARD_NAME));
        String url = String.format(DUPLICATE_DASHBOARD_PATH, request.getUserId(), request.getDashboardName());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, DashboardResponse.class);
    }

    /**
     * 获取仪表盘组件
     *
     * @param request 仪表盘组件请求对象
     * @return 获取仪表盘组件的响应对象
     * @throws NullPointerException     如果请求对象为空，则抛出该异常
     * @throws IllegalArgumentException 如果用户ID、仪表盘名称或组件名称为空，则抛出该异常
     */
    public DashboardCreateResponse getDashboardWidget(DashboardWidgetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDashboardName(), checkEmptyExceptionMessageFormat(DASHBOARD_NAME));
        checkStringNotEmpty(request.getWidgetName(), checkEmptyExceptionMessageFormat(WIDGET_NAME));
        String url = String.format(DASHBOARD_WIDGET_PATH,
                request.getUserId(), request.getDashboardName(), request.getWidgetName());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET, url);
        return invokeHttpClient(internalRequest, DashboardCreateResponse.class);
    }

    /**
     * 创建仪表盘组件
     *
     * @param request 包含创建组件请求信息的对象
     * @return 包含创建组件响应信息的对象
     * @throws NullPointerException     如果请求对象为空，抛出此异常
     * @throws IllegalArgumentException 如果用户ID或仪表盘名为空，抛出此异常
     */
    public DashboardResponse createDashboardWidget(DashboardBaseRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDashboardName(), checkEmptyExceptionMessageFormat(DASHBOARD_NAME));
        String url = String.format(CREATE_DASHBOARD_WIDGET_PATH, request.getUserId(), request.getDashboardName());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, DashboardResponse.class);
    }

    /**
     * 更新仪表盘组件
     *
     * @param request 更新组件请求
     * @return 更新后的仪表盘响应
     * @throws NullPointerException     如果请求为空，则抛出此异常
     * @throws IllegalArgumentException 如果用户ID、仪表盘名称或组件名称是空的，则抛出此异常
     */
    public DashboardResponse updateDashboardWidget(DashboardWidgetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDashboardName(), checkEmptyExceptionMessageFormat(DASHBOARD_NAME));
        checkStringNotEmpty(request.getWidgetName(), checkEmptyExceptionMessageFormat(WIDGET_NAME));
        String url = String.format(DASHBOARD_WIDGET_PATH,
                request.getUserId(), request.getDashboardName(), request.getWidgetName());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT, url);
        return invokeHttpClient(internalRequest, DashboardResponse.class);
    }

    /**
     * 删除仪表盘组件
     *
     * @param request 包含用户ID、仪表盘名称和组件名称的请求对象
     * @return 返回操作结果
     * @throws IllegalArgumentException 如果请求对象为空，抛出异常并提示错误信息
     * @throws IllegalArgumentException 如果用户ID、仪表盘名称或组件名称不为空，抛出异常并提示错误信息
     */
    public DashboardResponse deleteDashboardWidget(DashboardWidgetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDashboardName(), checkEmptyExceptionMessageFormat(DASHBOARD_NAME));
        checkStringNotEmpty(request.getWidgetName(), checkEmptyExceptionMessageFormat(WIDGET_NAME));
        String url = String.format(DASHBOARD_WIDGET_PATH,
                request.getUserId(), request.getDashboardName(), request.getWidgetName());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.DELETE, url);
        return invokeHttpClient(internalRequest, DashboardResponse.class);
    }

    /**
     * 复制仪表盘组件
     *
     * @param request 复制请求
     * @return 复制后的仪表盘组件响应
     * @throws NullPointerException     如果请求为空，则抛出此异常
     * @throws IllegalArgumentException 如果用户ID、仪表盘名称或组件名称为空，则抛出此异常
     */
    public DashboardResponse duplicateDashboardWidget(DashboardWidgetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDashboardName(), checkEmptyExceptionMessageFormat(DASHBOARD_NAME));
        checkStringNotEmpty(request.getWidgetName(), checkEmptyExceptionMessageFormat(WIDGET_NAME));
        String url = String.format(DUPLICATE_DASHBOARD_WIDGET_PATH,
                request.getUserId(), request.getDashboardName(), request.getWidgetName());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, DashboardResponse.class);
    }

    /**
     * 获取仪表盘报告数据
     *
     * @param request 获取仪表盘报告数据请求参数
     * @return 返回获取仪表盘报告数据响应结果
     */
    public DashboardReportDataResponse getDashboardReportData(DashboardDataRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createBodyRequest(request, HttpMethodName.POST, DASHBOARD_REPORT_DATA_PATH);
        return invokeHttpClient(internalRequest, DashboardReportDataResponse.class);
    }

    /**
     * 获取仪表盘趋势数据
     *
     * @param request 获取趋势数据请求参数
     * @return DashboardTrendDataResponse 返回仪表盘趋势数据响应对象
     * @throws NullPointerException 如果request为null，抛出该异常
     */
    public DashboardTrendDataResponse getDashboardTrendData(DashboardDataRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createBodyRequest(request, HttpMethodName.POST, DASHBOARD_TREND_DATA_PATH);
        return invokeHttpClient(internalRequest, DashboardTrendDataResponse.class);
    }

    /**
     * 获取仪表盘仪表图数据
     *
     * @param request 数据请求对象
     * @return DashboardBillboardDataResponse 返回仪表盘数据响应对象
     * @throws NullPointerException 如果request为null，则抛出空指针异常
     */
    public DashboardBillboardDataResponse getDashboardGaugeChartData(DashboardDataRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createBodyRequest(request, HttpMethodName.POST, DASHBOARD_GAUGE_CHART_DATA_PATH);
        return invokeHttpClient(internalRequest, DashboardBillboardDataResponse.class);
    }

    /**
     * 获取仪表盘数据面板数据
     *
     * @param request 获取数据的请求参数
     * @return DashboardBillboardDataResponse 包含数据面板数据的响应对象
     * @throws NullPointerException 如果请求参数为空，则抛出此异常
     */
    public DashboardBillboardDataResponse getDashboardBillboardData(DashboardDataRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createBodyRequest(request, HttpMethodName.POST, DASHBOARD_BILLBOARD_DATA_PATH);
        return invokeHttpClient(internalRequest, DashboardBillboardDataResponse.class);
    }

    /**
     * 获取仪表盘趋势高级数据响应
     *
     * @param request 仪表盘数据请求
     * @return DashboardTrendSeniorDataResponse 仪表盘趋势高级数据响应对象
     */
    public DashboardTrendSeniorDataResponse getDashboardTrendSeniorData(DashboardDataRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createBodyRequest(request, HttpMethodName.POST, DASHBOARD_TREND_SENIOR_DATA_PATH);
        return invokeHttpClient(internalRequest, DashboardTrendSeniorDataResponse.class);
    }

    /**
     * 获取仪表盘维度
     *
     * @param request 包含用户ID的请求对象
     * @return 包含仪表盘维度的响应对象
     * @throws NullPointerException 如果请求对象为空，抛出该异常
     */
    public Map<String, List<String>> getDashboardDimensions(DashboardDimensionsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        String url = String.format(DASHBOARD_DIMENSIONS_PATH,
                request.getUserId(), request.getService(), request.getRegion());
        InternalRequest internalRequest =
                this.createRequestWithUrl(request, HttpMethodName.GET, url);
        internalRequest.addParameter(DIMENSIONS_MESSAGE_KEY, request.getDimensions());
        internalRequest.addParameter(METRIC_NAME, request.getMetricName());
        internalRequest.addParameter(REGION, request.getRegion());
        internalRequest.addParameter(SERVICE, request.getService());
        internalRequest.addParameter(SHOW_ID, request.getShowId());
        return invokeHttpClient(internalRequest, MapListResponse.class).getResult();
    }

    /**
     * 查询用户组列表
     *
     * @param request
     * @return
     */
    public ListNotifyGroupsResponse listNotifyGroups(NotifyRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/json-api/v1/alarm/notify/group/list");
        return invokeHttpClient(internalRequest, ListNotifyGroupsResponse.class);
    }

    /**
     * 查询用户列表
     *
     * @param request
     * @return
     */
    public ListNotifyPartiesResponse listNotifyParties(NotifyRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/json-api/v1/alarm/notify/party/list");
        return invokeHttpClient(internalRequest, ListNotifyPartiesResponse.class);
    }

    /**
     * 新建通知模版
     *
     * @param request
     */
    public void createAction(CreateAndUpdateActionRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkNotNull(request.getMembers(), checkEmptyExceptionMessageFormat("members"));
        checkStringNotEmpty(request.getAlias(), checkEmptyExceptionMessageFormat("alias"));
        checkIsTrue(CollectionUtils.isNotEmpty(request.getNotifications()),
                checkEmptyExceptionMessageFormat("notifications"));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                String.format("/csm/api/v1/userId/%s/action/create", request.getUserId()));
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 删除通知模版
     *
     * @param request
     */
    public void deleteAction(DeleteActionRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat("name"));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.DELETE,
                String.format("/csm/api/v1/userId/%s/action/delete", request.getUserId()));
        internalRequest.addParameter("name", request.getName());
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 编辑通知模版
     *
     * @param request
     */
    public void updateAction(CreateAndUpdateActionRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        request.setProductName(request.getUserId());
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkNotNull(request.getMembers(), checkEmptyExceptionMessageFormat("members"));
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat("name"));
        checkIsTrue(CollectionUtils.isNotEmpty(request.getNotifications()),
                checkEmptyExceptionMessageFormat("notifications"));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT,
                String.format("/csm/api/v1/userId/%s/action/update", request.getUserId()));
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 查询通知模版列表
     *
     * @param request
     * @return
     */
    public ListActionsResponse listActions(ListActionsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkIsTrue(request.getPageNo() > 0, "pageNo should be greater than 0");
        checkIsTrue(request.getPageSize() > 0, "pageSize should be greater than 0");
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                String.format("/csm/api/v1/userId/%s/action/actionList", request.getUserId()));
        return invokeHttpClient(internalRequest, ListActionsResponse.class);
    }

    /**
     * 日志提取
     *
     * @param request
     * @return
     */
    public List<LogExtractResult> logExtract(LogExtractRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getExtractRule(), checkEmptyExceptionMessageFormat("extractRule"));
        checkStringNotEmpty(request.getLogExample(), checkEmptyExceptionMessageFormat("logExample"));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                String.format("/csm/api/v1/userId/%s/application/logextract", request.getUserId()));
        return invokeHttpClient(internalRequest, ListResponse.class).getResult();
    }

    /**
     * 应用监控数据查询接口-维度值查询接口
     *
     * @param request
     * @return
     */
    public Map<String, List<String>> getMetricMetaForApplication(GetMetricMetaForApplicationRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAppName(), checkEmptyExceptionMessageFormat("appName"));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getMetricName(), checkEmptyExceptionMessageFormat("metricName"));
        checkIsTrue(request.getInstances() != null && !request.getInstances().isEmpty(),
                "instances should not be empty");
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET,
                String.format("/csm/api/v1/userId/%s/application/%s/task/%s/metricMeta", request.getUserId(),
                        request.getAppName(), request.getTaskName()));
        internalRequest.addParameter("metricName", request.getMetricName());
        internalRequest.addParameter("instances", StringUtils.join(request.getInstances(), ","));
        internalRequest.addParameter("dimensionKeys", StringUtils.join(request.getDimensionKeys(), ","));
        return invokeHttpClient(internalRequest, MapListResponse.class).getResult();
    }

    /**
     * 应用监控监控数据查询接口-多监控对象，单指标查询接口
     *
     * @param request
     * @return
     */
    public List<MetricDataForApplication> getMetricDataForApplication(GetMetricDataForApplicationRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAppName(), checkEmptyExceptionMessageFormat("appName"));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getMetricName(), checkEmptyExceptionMessageFormat("metricName"));
        checkStringNotEmpty(request.getStartTime(), checkEmptyExceptionMessageFormat("startTime"));
        checkStringNotEmpty(request.getEndTime(), checkEmptyExceptionMessageFormat("endtTime"));
        checkIsTrue(!(CollectionUtils.isEmpty(request.getInstances()) && BooleanUtils.isNotTrue(request.getAggrData())),
                "instances can't be empty when aggrData is false or default");

        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET,
                String.format("/csm/api/v1/userId/%s/application/%s/task/%s/metricData", request.getUserId(),
                        request.getAppName(), request.getTaskName()));
        Map<String, String> params = new HashMap<String, String>();
        params.put("startTime", request.getStartTime());
        params.put("endTime", request.getEndTime());
        params.put("metricName", request.getMetricName());
        if (CollectionUtils.isNotEmpty(request.getInstances())) {
            params.put("instances", StringUtils.join(request.getInstances(), ","));
        }
        if (request.getCycle() > 0) {
            params.put("cycle", String.valueOf(request.getCycle()));
        }
        if (request.getAggrData()) {
            params.put("aggrData", String.valueOf(true));
        }
        if (null != request.getStatistics()) {
            params.put("statistics", request.getStatistics().name());
        }
        if (MapUtils.isNotEmpty(request.getDimensions())) {
            List<String> dimensions = new ArrayList<String>();
            for (String key : request.getDimensions().keySet()) {
                List<String> values = request.getDimensions().get(key);
                dimensions.add("key" + ":" + StringUtils.join(values, "___"));
            }
            params.put("dimensions", StringUtils.join(dimensions, ","));
        }
        internalRequest.setParameters(params);
        return invokeHttpClient(internalRequest, ListResponse.class).getResult();
    }

    /**
     * 报警策略创建接口
     *
     * @param request
     * @return
     */
    public ApplicationAlarmConfig createAlarmConfigForApplication(CreateOrUpdateAlarmConfigForApplicationRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAppName(), checkEmptyExceptionMessageFormat("appName"));
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
        checkIsTrue(request.getMonitorObjectType() == MonitorObjectType.APP
                || request.getMonitorObjectType() == MonitorObjectType.SERVICE, "monitorObjectType error");
        checkNotNull(request.getMonitorObject(), "monitor object should not be null");
        checkNotNull(request.getType(), "type should not be null");
        checkNotNull(request.getLevel(), "level should not be null");
        checkNotNull(request.getActionEnabled(), "actionEnabled should not be null");
        checkIsTrue(CollectionUtils.isNotEmpty(request.getRules()), "rules should not be null");
        checkStringNotEmpty(request.getSrcName(), checkEmptyExceptionMessageFormat("srcName"));
        checkStringNotEmpty(request.getSrcType(), checkEmptyExceptionMessageFormat("srcType"));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                String.format("/csm/api/v1/userId/%s/application/alarm/config/create", request.getUserId()));
        return invokeHttpClient(internalRequest, ApplicationAlarmConfig.class);
    }

    /**
     * 报警策略更新接口
     *
     * @param request
     * @return
     */
    public ApplicationAlarmConfig updateAlarmConfigForApplication(CreateOrUpdateAlarmConfigForApplicationRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAppName(), checkEmptyExceptionMessageFormat("appName"));
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
        checkIsTrue(request.getMonitorObjectType() == MonitorObjectType.APP
                || request.getMonitorObjectType() == MonitorObjectType.SERVICE, "monitorObjectType error");
        checkNotNull(request.getMonitorObject(), "monitor object should not be null");
        checkNotNull(request.getType(), "type should not be null");
        checkNotNull(request.getLevel(), "level should not be null");
        checkNotNull(request.getActionEnabled(), "actionEnabled should not be null");
        checkIsTrue(CollectionUtils.isNotEmpty(request.getRules()), "rules should not be null");
        checkStringNotEmpty(request.getSrcName(), checkEmptyExceptionMessageFormat("srcName"));
        checkStringNotEmpty(request.getSrcType(), checkEmptyExceptionMessageFormat("srcType"));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT,
                String.format("/csm/api/v1/userId/%s/application/alarm/config/update", request.getUserId()));
        return invokeHttpClient(internalRequest, ApplicationAlarmConfig.class);
    }

    /**
     * 报警策略列表接口
     *
     * @param request
     * @return
     */
    public Page<ApplicationAlarmConfig> listAlarmConfigForApplication(ListAlarmConfigForApplicationRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkIsTrue(request.getPageNo() > 0, "pageNo should be greater than 0");
        Map<String, String> params = new HashMap<String, String>();
        params.put("appName", request.getAppName());
        params.put("alarmName", request.getAlarmName());
        params.put("actionEnabled", BooleanUtils.toString(request.getActionEnabled(), "true", "false", ""));
        params.put("srcType", request.getSrcType());
        params.put("taskName", request.getTaskName());
        params.put("pageNo", String.valueOf(request.getPageNo()));
        params.put("pageSize", String.valueOf(request.getPageSize()));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET,
                String.format("/csm/api/v1/userId/%s/application/alarm/config/list", request.getUserId()));
        internalRequest.setParameters(params);
        return invokeHttpClient(internalRequest, Page.class);
    }

    /**
     * 报警策略删除接口
     *
     * @param request
     */
    public void deleteAlarmConfigForApplication(DeleteAlarmConfigForApplicationRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAppName(), checkEmptyExceptionMessageFormat("appName"));
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.DELETE,
                String.format("/csm/api/v1/userId/%s/application/alarm/config/delete", request.getUserId()));
        internalRequest.addParameter("appName", request.getAppName());
        internalRequest.addParameter("alarmName", request.getAlarmName());
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 报警策略详情接口
     *
     * @param request
     * @return
     */
    public ApplicationAlarmConfig getAlarmConfigForApplication(GetAlarmConfigForApplicationRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAppName(), checkEmptyExceptionMessageFormat("appName"));
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET,
                String.format("/csm/api/v1/userId/%s/application/alarm/%s/config/", request.getUserId(),
                        request.getAlarmName()));
        internalRequest.addParameter("appName", request.getAppName());
        return invokeHttpClient(internalRequest, ApplicationAlarmConfig.class);
    }

    /**
     * 报警指标列表接口
     *
     * @param request
     * @return
     */
    public List<ApplicationMetric> listAlarmMetricsForApplication(ListAlarmMetricsForApplicationRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAppName(), checkEmptyExceptionMessageFormat("appName"));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET,
                String.format("/csm/api/v1/userId/%s/application/%s/%s/alarm/metrics", request.getUserId(),
                        request.getAppName(), request.getTaskName()));
        if (null != request.getSearchName()) {
            internalRequest.addParameter("searchName", request.getSearchName());
        }
        return invokeHttpClient(internalRequest, ListResponse.class).getResult();
    }

    /**
     * 创建报警策略接口
     *
     * @param request
     * @return
     */
    public void createAlarmConfig(CreateOrUpdateAlarmConfigRequest request) {
        checkCreateOrUpdateAlarmConfigRequest(request);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/csm/api/v1/services/alarm/config/create");
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 更新报警策略接口
     *
     * @param request
     * @return
     */
    public void updateAlarmConfig(CreateOrUpdateAlarmConfigRequest request) {
        checkCreateOrUpdateAlarmConfigRequest(request);
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/csm/api/v1/services/alarm/config/update");
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 删除报警策略接口
     *
     * @param request
     * @return
     */
    public void deleteAlarmConfig(CommonAlarmConfigRequest request) {
        checkCommonAlarmConfigRequest(request);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/csm/api/v1/services/alarm/config/delete");
        internalRequest.addParameter("userId", request.getUserId());
        internalRequest.addParameter("scope", request.getScope());
        internalRequest.addParameter("alarmName", request.getAlarmName());
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 屏蔽报警策略接口
     *
     * @param request
     * @return
     */
    public void blockAlarmConfig(CommonAlarmConfigRequest request) {
        checkCommonAlarmConfigRequest(request);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/csm/api/v1/services/alarm/config/block");
        internalRequest.addParameter("userId", request.getUserId());
        internalRequest.addParameter("scope", request.getScope());
        internalRequest.addParameter("alarmName", request.getAlarmName());
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 接触屏蔽报警策略接口
     *
     * @param request
     * @return
     */
    public void unblockAlarmConfig(CommonAlarmConfigRequest request) {
        checkCommonAlarmConfigRequest(request);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/csm/api/v1/services/alarm/config/unblock");
        internalRequest.addParameter("userId", request.getUserId());
        internalRequest.addParameter("scope", request.getScope());
        internalRequest.addParameter("alarmName", request.getAlarmName());
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 获取报警策略详情接口
     *
     * @param request
     * @return
     */
    public AlarmConfig getAlarmConfigDetail(CommonAlarmConfigRequest request) {
        checkCommonAlarmConfigRequest(request);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET,
                "/csm/api/v1/services/alarm/config");
        internalRequest.addParameter("userId", request.getUserId());
        internalRequest.addParameter("scope", request.getScope());
        internalRequest.addParameter("alarmName", request.getAlarmName());
        return invokeHttpClient(internalRequest, AlarmConfig.class);
    }

    /**
     * 获取单一实例报警策略列表接口
     *
     * @param request
     * @return
     */
    public Page<AlarmConfig> listSingleInstanceAlarmConfigs(ListSingleInstanceAlarmConfigsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getScope(), checkEmptyExceptionMessageFormat(SCOPE_MESSAGE_KEY));
        checkIsTrue(request.getPageNo() > 0, "pageNo should be greater than 0");
        checkIsTrue(request.getPageSize() > 0, "pageSize should be greater than 0");
        if (StringUtils.isEmpty(request.getRegion())) {
            request.setRegion("bj");
        }
        if (StringUtils.isEmpty(request.getOrder())) {
            request.setOrder("desc");
        }
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET,
                "/csm/api/v1/services/alarm/config/list");
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", request.getUserId());
        params.put("scope", request.getScope());
        params.put("region", request.getRegion());
        params.put("dimensions", request.getDimensions());
        params.put("order", request.getOrder());
        params.put("alarmNamePrefix", request.getAlarmNamePrefix());
        params.put("pageNo", String.valueOf(request.getPageNo()));
        params.put("pageSize", String.valueOf(request.getPageSize()));
        if (null != request.getActionEnabled()) {
            params.put("actionEnabled", String.valueOf(request.getActionEnabled()));
        }
        internalRequest.setParameters(params);
        return invokeHttpClient(internalRequest, Page.class);
    }

    /**
     * 获取监控项列表接口
     *
     * @param request
     * @return
     */
    public List<AlarmMetric> listAlarmMetrics(ListAlarmMetricsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getScope(), checkEmptyExceptionMessageFormat(SCOPE_MESSAGE_KEY));
        checkStringNotEmpty(request.getRegion(), "region should not be empty");
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET,
                "/csm/api/v1/services/alarm/config/metrics");
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", request.getUserId());
        params.put("scope", request.getScope());
        params.put("region", request.getRegion());
        params.put("dimensions", request.getDimensions());
        params.put("type", request.getType());
        params.put("locale", request.getLocale());
        internalRequest.setParameters(params);
        return invokeHttpClient(internalRequest, ListResponse.class).getResult();
    }

    /**
     * 创建报警策略v2接口
     *
     * @param request
     * @return
     */
    public CreateAlarmConfigV2Response createAlarmPolicyV2(CreateOrUpdateAlarmConfigV2Request request) {
        checkCreateOrUpdateAlarmConfigV2Request(request);

        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                String.format("/csm/api/v2/userId/%s/services/%s/alarm/config/create",
                        request.getUserId(), request.getScope()));
        return invokeHttpClient(internalRequest, CreateAlarmConfigV2Response.class);
    }

    /**
     * 更新报警策略v2接口
     *
     * @param request
     * @return
     */
    public void updateAlarmPolicyV2(CreateOrUpdateAlarmConfigV2Request request) {
        checkCreateOrUpdateAlarmConfigV2Request(request);
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));

        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT,
                String.format("/csm/api/v2/userId/%s/services/%s/alarm/config/update",
                        request.getUserId(), request.getScope()));
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 屏蔽报警策略v2接口
     *
     * @param request
     * @return
     */
    public void blockAlarmConfigV2(CommonAlarmConfigRequest request) {
        checkCommonAlarmConfigRequest(request);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                String.format("/csm/api/v2/userId/%s/services/%s/alarm/config/block",
                        request.getUserId(), request.getScope()));
        internalRequest.addParameter("alarmName", request.getAlarmName());
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 解除屏蔽报警策略v2接口
     *
     * @param request
     * @return
     */
    public void unblockAlarmConfigV2(CommonAlarmConfigRequest request) {
        checkCommonAlarmConfigRequest(request);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                String.format("/csm/api/v2/userId/%s/services/%s/alarm/config/unblock",
                        request.getUserId(), request.getScope()));
        internalRequest.addParameter("alarmName", request.getAlarmName());
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 获取报警策略详情v2接口
     *
     * @param request
     * @return
     */
    public AlarmConfigV2 getAlarmPolicyDetailV2(CommonAlarmConfigRequest request) {
        checkCommonAlarmConfigRequest(request);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET,
                String.format("/csm/api/v2/userId/%s/services/%s/alarm/config",
                        request.getUserId(), request.getScope()));
        internalRequest.addParameter("alarmName", request.getAlarmName());
        return invokeHttpClient(internalRequest, AlarmConfigV2.class);
    }

    private void checkCreateOrUpdateAlarmConfigV2Request(CreateOrUpdateAlarmConfigV2Request request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getScope(), checkEmptyExceptionMessageFormat(SCOPE_MESSAGE_KEY));
        checkStringNotEmpty(request.getAliasName(), checkEmptyExceptionMessageFormat("aliasName"));
        TargetType targetType = request.getTargetType();
        checkIsTrue(TargetType.TARGET_TYPE_ALL_INSTANCES == targetType ||
                TargetType.TARGET_TYPE_INSTANCE_GROUP == targetType ||
                TargetType.TARGET_TYPE_MULTI_INSTANCES == targetType ||
                TargetType.TARGET_TYPE_INSTANCE_TAGS == targetType, "targetType is invalid");
        if (TargetType.TARGET_TYPE_INSTANCE_GROUP == targetType) {
            checkIsTrue(CollectionUtils.isNotEmpty(request.getTargetInstanceGroups()),
                    "targetInstanceGroups should not be empty");
        }
        if (TargetType.TARGET_TYPE_MULTI_INSTANCES == targetType) {
            checkIsTrue(CollectionUtils.isNotEmpty(request.getTargetInstances()),
                    "targetInstances should not be empty");
        }
        if (TargetType.TARGET_TYPE_INSTANCE_TAGS == targetType) {
            checkIsTrue(CollectionUtils.isNotEmpty(request.getTargetInstanceTags()),
                    "targetInstanceTags should not be empty");
        }
        checkIsTrue(CollectionUtils.isNotEmpty(request.getPolicies()), "policies should not be empty");
        checkIsTrue(CollectionUtils.isNotEmpty(request.getActions()), "actions should not be empty");
    }

    private void checkCreateOrUpdateAlarmConfigRequest(CreateOrUpdateAlarmConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getScope(), checkEmptyExceptionMessageFormat(SCOPE_MESSAGE_KEY));
        checkStringNotEmpty(request.getAliasName(), checkEmptyExceptionMessageFormat("aliasName"));
        checkNotNull(request.getMonitorObject(), "monitorObject should not be null");
        checkIsTrue(CollectionUtils.isNotEmpty(request.getAlarmActions()), "alarmActions should not be empty");
        checkIsTrue(CollectionUtils.isNotEmpty(request.getRules()), "rules should not be empty");
    }

    private void checkCommonAlarmConfigRequest(CommonAlarmConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getScope(), checkEmptyExceptionMessageFormat(SCOPE_MESSAGE_KEY));
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
    }


    /**
     * Get Cloud Event Data.
     *
     * @param request Event Data Request.
     * @return CloudEventResponse
     */
    public CloudEventResponse getCloudEventData(EventDataRequest request) {
        // Internal Request
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET, CLOUD_EVENT_LIST_PATH);
        // check request params
        checkEventDataRequest(request, internalRequest, EventType.Cloud);
        return invokeHttpClient(internalRequest, CloudEventResponse.class);
    }

    /**
     * Get Platform Event Data.
     *
     * @param request Event Data Request.
     * @return PlatformEventResponse
     */
    public PlatformEventResponse getPlatformEventData(EventDataRequest request) {
        // Internal Request
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET, PLATFORM_EVENT_LIST_PATH);
        // check request params
        checkEventDataRequest(request, internalRequest, EventType.Platform);
        return invokeHttpClient(internalRequest, PlatformEventResponse.class);
    }

    /**
     * Create Event Policy.
     *
     * @param eventPolicy Event Policy.
     * @return EventPolicyResponse
     */
    public EventPolicyResponse createEventPolicy(EventPolicy eventPolicy) {
        checkEventPolicy(eventPolicy);
        String url = String.format(EVENT_POLICY_PATH, eventPolicy.getAccountId(), eventPolicy.getServiceName());
        // Internal Request
        InternalRequest internalRequest = this.createBodyRequest(eventPolicy, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, EventPolicyResponse.class);
    }


    /**
     * Create Instance Group.
     *
     * @param mergedGroup Merged Group.
     * @return InstanceGroupResponse
     */
    public InstanceGroupResponse instanceGroupCreate(MergedGroup mergedGroup) {
        checkMergedGroup(mergedGroup);
        String url = String.format(INSTANCE_GROUP_PATH, mergedGroup.getUserId());
        // Internal Request
        InternalRequest internalRequest = this.createBodyRequest(mergedGroup, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, InstanceGroupResponse.class);
    }

    /**
     * Update Instance Group.
     *
     * @param instanceGroup Instance Group.
     * @return InstanceGroupResponse
     */
    public InstanceGroupResponse instanceGroupUpdate(InstanceGroup instanceGroup) {
        checkInstanceGroup(instanceGroup);
        String url = String.format(INSTANCE_GROUP_PATH, instanceGroup.getUserId());
        // Internal Request
        InternalRequest internalRequest = this.createBodyRequest(instanceGroup, HttpMethodName.PATCH, url);
        return invokeHttpClient(internalRequest, InstanceGroupResponse.class);
    }

    /**
     * Delete Instance Group.
     *
     * @param instanceGroupBase Instance Group Base.
     * @return InstanceGroupResponse
     */
    public InstanceGroupResponse instanceGroupDelete(InstanceGroupBase instanceGroupBase) {
        checkInstanceGroupBase(instanceGroupBase);
        String url = String.format(INSTANCE_GROUP_ID_PATH, instanceGroupBase.getUserId(), instanceGroupBase.getId());
        // Internal Request
        InternalRequest internalRequest = this.createBodyRequest(instanceGroupBase, HttpMethodName.DELETE, url);
        return invokeHttpClient(internalRequest, InstanceGroupResponse.class);
    }

    /**
     * Get Instance Group Detail.
     *
     * @param instanceGroupBase Instance Group Base.
     * @return InstanceGroupResponse
     */
    public InstanceGroupResponse instanceGroupGet(InstanceGroupBase instanceGroupBase) {
        checkInstanceGroupBase(instanceGroupBase);
        String url = String.format(INSTANCE_GROUP_ID_PATH, instanceGroupBase.getUserId(), instanceGroupBase.getId());
        // Internal Request
        InternalRequest internalRequest = this.createBodyRequest(instanceGroupBase, HttpMethodName.GET, url);
        return invokeHttpClient(internalRequest, InstanceGroupResponse.class);
    }

    /**
     * Get Instance Group List.
     *
     * @param instanceGroupQuery Instance Group Query.
     * @return InstanceGroupResponse
     */
    public InstanceGroupListResponse instanceGroupList(InstanceGroupQuery instanceGroupQuery) {
        checkInstanceGroupQuery(instanceGroupQuery);
        String url = String.format(INSTANCE_GROUP_LIST_PATH, instanceGroupQuery.getUserId());
        // Internal Request
        InternalRequest internalRequest = this.createBodyRequest(instanceGroupQuery, HttpMethodName.GET, url);
        buildInstanceGroupQueryParams(instanceGroupQuery, internalRequest);
        return invokeHttpClient(internalRequest, InstanceGroupListResponse.class);
    }

    /**
     * Add Instance to Instance Group.
     *
     * @param mergedGroup Merged Group.
     * @return InstanceGroupResponse Instance Group Response.
     */
    public InstanceGroupResponse instanceGroupAddInstance(MergedGroup mergedGroup) {
        checkMergedGroup(mergedGroup);
        String url = String.format(IG_INSTANCE_ADD, mergedGroup.getUserId(), mergedGroup.getId());
        // Internal Request
        InternalRequest internalRequest = this.createBodyRequest(mergedGroup, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, InstanceGroupResponse.class);
    }

    /**
     * Remove Instance from Instance Group.
     *
     * @param mergedGroup Merged Group.
     * @return InstanceGroupResponse Instance Group Response.
     */
    public InstanceGroupResponse instanceGroupRemoveInstance(MergedGroup mergedGroup) {
        checkMergedGroup(mergedGroup);
        String url = String.format(IG_INSTANCE_REMOVE, mergedGroup.getUserId(), mergedGroup.getId());
        // Internal Request
        InternalRequest internalRequest = this.createBodyRequest(mergedGroup, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, InstanceGroupResponse.class);
    }

    /**
     * Get Instance List from Instance Group.
     *
     * @param igInstanceQuery Instance Group Instance Query.
     * @return InstanceGroupResponse Instance Group Response.
     */
    public IGInstanceListResponse instanceGroupGetInstanceList(IGInstanceQuery igInstanceQuery) {
        checkIGInstanceQuery(igInstanceQuery, IGInstanceQueryType.INCLUDE);
        String url = String.format(IG_INSTANCE_LIST, igInstanceQuery.getUserId());
        // Internal Request
        InternalRequest internalRequest = this.createBodyRequest(igInstanceQuery, HttpMethodName.GET, url);
        buildIGInstanceQueryParams(igInstanceQuery, internalRequest, IGInstanceQueryType.INCLUDE);
        return invokeHttpClient(internalRequest, IGInstanceListResponse.class);
    }

    /**
     * Get Instance List.
     *
     * @param igInstanceQuery Instance Group Instance Query.
     * @return InstanceGroupResponse Instance Group Response.
     */
    public IGInstanceListResponse instanceGroupQueryInstanceList(IGInstanceQuery igInstanceQuery) {
        checkIGInstanceQuery(igInstanceQuery, IGInstanceQueryType.ALL);
        String url = String.format(IG_QUERY_INSTANCE_LIST, igInstanceQuery.getUserId());
        // Internal Request
        InternalRequest internalRequest = this.createBodyRequest(igInstanceQuery, HttpMethodName.GET, url);
        buildIGInstanceQueryParams(igInstanceQuery, internalRequest, IGInstanceQueryType.ALL);
        return invokeHttpClient(internalRequest, IGInstanceListResponse.class);
    }

    /**
     * Get Filter Instance List.
     *
     * @param igInstanceQuery Instance Group Instance Query.
     * @return InstanceGroupResponse Instance Group Response.
     */
    public IGInstanceListResponse instanceGroupQueryInstanceListFilter(IGInstanceQuery igInstanceQuery) {
        checkIGInstanceQuery(igInstanceQuery, IGInstanceQueryType.FILTER);
        String url = String.format(IG_QUERY_INSTANCE_LIST_FILTER, igInstanceQuery.getUserId());
        // Internal Request
        InternalRequest internalRequest = this.createBodyRequest(igInstanceQuery, HttpMethodName.GET, url);
        buildIGInstanceQueryParams(igInstanceQuery, internalRequest, IGInstanceQueryType.FILTER);
        return invokeHttpClient(internalRequest, IGInstanceListResponse.class);
    }

    private void checkIGInstanceQuery(IGInstanceQuery igInstanceQuery, IGInstanceQueryType type) {
        // check not null
        checkNotNull(igInstanceQuery, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(igInstanceQuery.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check pageNo and pageSize
        checkNotNull(igInstanceQuery.getPageNo(), checkEmptyExceptionMessageFormat(PAGE_NO_MESSAGE_KEY));
        checkNotNull(igInstanceQuery.getPageSize(), checkEmptyExceptionMessageFormat(PAGE_SIZE_MESSAGE_KEY));

        // check serviceName
        checkStringNotEmpty(igInstanceQuery.getServiceName(), checkEmptyExceptionMessageFormat("serviceName"));
        // check typeName
        checkStringNotEmpty(igInstanceQuery.getTypeName(), checkEmptyExceptionMessageFormat("typeName"));
        // check region
        checkStringNotEmpty(igInstanceQuery.getRegion(), checkEmptyExceptionMessageFormat("region"));
        // check viewType
        checkNotNull(igInstanceQuery.getViewType(), checkEmptyExceptionMessageFormat("viewType"));

        if (IGInstanceQueryType.INCLUDE.equals(type)) {
            // check id
            checkNotNull(igInstanceQuery.getId(), checkEmptyExceptionMessageFormat("id"));
        }
        if (IGInstanceQueryType.FILTER.equals(type)) {
            // check id
            checkNotNull(igInstanceQuery.getId(), checkEmptyExceptionMessageFormat("id"));
            // check uuid
            checkStringNotEmpty(igInstanceQuery.getUuid(), checkEmptyExceptionMessageFormat("uuid"));
        }

    }

    private void buildIGInstanceQueryParams(
            IGInstanceQuery igInstanceQuery, InternalRequest internalRequest, IGInstanceQueryType type) {
        internalRequest.addParameter(PAGE_NO_MESSAGE_KEY, String.valueOf(igInstanceQuery.getPageNo()));
        internalRequest.addParameter(PAGE_SIZE_MESSAGE_KEY, String.valueOf(igInstanceQuery.getPageSize()));
        internalRequest.addParameter("serviceName", igInstanceQuery.getServiceName());
        internalRequest.addParameter("typeName", igInstanceQuery.getTypeName());
        internalRequest.addParameter("region", igInstanceQuery.getRegion());
        internalRequest.addParameter("viewType", igInstanceQuery.getViewType().toString());
        if (IGInstanceQueryType.INCLUDE.equals(type)) {
            internalRequest.addParameter("id", String.valueOf(igInstanceQuery.getId()));
        }
        if (IGInstanceQueryType.ALL.equals(type)) {
            internalRequest.addParameter("keywordType", igInstanceQuery.getKeywordType());
            internalRequest.addParameter("keyword", igInstanceQuery.getKeyword());
        }
        if (IGInstanceQueryType.FILTER.equals(type)) {
            internalRequest.addParameter("keywordType", igInstanceQuery.getKeywordType());
            internalRequest.addParameter("keyword", igInstanceQuery.getKeyword());
            internalRequest.addParameter("id", String.valueOf(igInstanceQuery.getId()));
            internalRequest.addParameter("uuid", igInstanceQuery.getUuid());
        }
    }

    private void checkInstanceGroupQuery(InstanceGroupQuery instanceGroupQuery) {
        // check not null
        checkNotNull(instanceGroupQuery, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(instanceGroupQuery.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check pageNo and pageSize
        checkNotNull(instanceGroupQuery.getPageNo(), checkEmptyExceptionMessageFormat(PAGE_NO_MESSAGE_KEY));
        checkNotNull(instanceGroupQuery.getPageSize(), checkEmptyExceptionMessageFormat(PAGE_SIZE_MESSAGE_KEY));
    }

    private void buildInstanceGroupQueryParams(InstanceGroupQuery instanceGroupQuery, InternalRequest
            internalRequest) {
        internalRequest.addParameter(PAGE_NO_MESSAGE_KEY, String.valueOf(instanceGroupQuery.getPageNo()));
        internalRequest.addParameter(PAGE_SIZE_MESSAGE_KEY, String.valueOf(instanceGroupQuery.getPageSize()));
        if (Strings.isNotEmpty(instanceGroupQuery.getName())) {
            internalRequest.addParameter("name", instanceGroupQuery.getName());
        }
        if (Strings.isNotEmpty(instanceGroupQuery.getServiceName())) {
            internalRequest.addParameter("serviceName", instanceGroupQuery.getServiceName());
        }
        if (Strings.isNotEmpty(instanceGroupQuery.getRegion())) {
            internalRequest.addParameter("region", instanceGroupQuery.getRegion());
        }
        if (Strings.isNotEmpty(instanceGroupQuery.getTypeName())) {
            internalRequest.addParameter("typeName", instanceGroupQuery.getTypeName());
        }
    }

    private void checkInstanceGroupBase(InstanceGroupBase instanceGroupBase) {
        // check not null
        checkNotNull(instanceGroupBase, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(instanceGroupBase.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check id
        checkStringNotEmpty(instanceGroupBase.getId(), checkEmptyExceptionMessageFormat("id"));
    }

    private void checkInstanceGroup(InstanceGroup instanceGroup) {
        // check not null
        checkNotNull(instanceGroup, REQUEST_NULL_ERROR_MESSAGE);
        // check id
        checkNotNull(instanceGroup.getId(), checkEmptyExceptionMessageFormat("id"));
        // check name
        checkStringNotEmpty(instanceGroup.getName(), checkEmptyExceptionMessageFormat("name"));
        // check serviceName
        checkStringNotEmpty(instanceGroup.getServiceName(), checkEmptyExceptionMessageFormat("serviceName"));
        // check typeName
        checkStringNotEmpty(instanceGroup.getTypeName(), checkEmptyExceptionMessageFormat("typeName"));
        // check region
        checkStringNotEmpty(instanceGroup.getRegion(), checkEmptyExceptionMessageFormat("region"));
    }

    private void checkMergedGroup(MergedGroup mergedGroup) {
        // check not null
        checkNotNull(mergedGroup, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(mergedGroup.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        // check region
        checkStringNotEmpty(mergedGroup.getRegion(), checkEmptyExceptionMessageFormat("region"));
        // check serviceName
        checkStringNotEmpty(mergedGroup.getServiceName(), checkEmptyExceptionMessageFormat("serviceName"));
        // check typeName
        checkStringNotEmpty(mergedGroup.getTypeName(), checkEmptyExceptionMessageFormat("typeName"));
        // check name
        checkStringNotEmpty(mergedGroup.getName(), checkEmptyExceptionMessageFormat("name"));
    }

    private void checkEventPolicy(EventPolicy eventPolicy) {
        // check not null
        checkNotNull(eventPolicy, REQUEST_NULL_ERROR_MESSAGE);
        // check userId
        checkStringNotEmpty(eventPolicy.getAccountId(), checkEmptyExceptionMessageFormat(ACCOUNT_ID_MESSAGE_KEY));
        // check serviceName
        checkStringNotEmpty(eventPolicy.getServiceName(), checkEmptyExceptionMessageFormat("serviceName"));
        // check name
        checkStringNotEmpty(eventPolicy.getName(), checkEmptyExceptionMessageFormat("name"));
        // check blockStatus
        checkNotNull(eventPolicy.getBlockStatus(), checkEmptyExceptionMessageFormat("blockStatus"));

        // check eventFilter
        EventFilter eventFilter = eventPolicy.getEventFilter();
        checkNotNull(eventFilter, checkEmptyExceptionMessageFormat("eventFilter"));
        checkNotNull(eventFilter.getEventLevel(), checkEmptyExceptionMessageFormat("eventLevel"));
        // check eventFilter.eventName
        checkNotNull(eventFilter.getEventTypeList(), checkEmptyExceptionMessageFormat("eventTypeList"));
        checkArgument(eventFilter.getEventTypeList().size() != 0,
                checkEmptyExceptionMessageFormat("eventTypeList"));

        // check resource
        EventResourceFilter resource = eventPolicy.getResource();
        checkNotNull(resource, checkEmptyExceptionMessageFormat("resource"));
        checkStringNotEmpty(resource.getRegion(), checkEmptyExceptionMessageFormat("resource.region"));
        checkStringNotEmpty(resource.getType(), checkEmptyExceptionMessageFormat("resource.type"));

        // check incidentActions
        checkNotNull(eventPolicy.getIncidentActions(), checkEmptyExceptionMessageFormat("incidentActions"));
        checkArgument(eventPolicy.getIncidentActions().size() != 0,
                checkEmptyExceptionMessageFormat("incidentActions"));
    }

    private void checkEventDataRequest(EventDataRequest request, InternalRequest internalRequest, EventType type) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        // check pageNo and pageSize
        checkNotNull(request.getPageNo(), checkEmptyExceptionMessageFormat(PAGE_NO_MESSAGE_KEY));
        checkNotNull(request.getPageSize(), checkEmptyExceptionMessageFormat(PAGE_SIZE_MESSAGE_KEY));
        // check userId
        checkStringNotEmpty(request.getAccountId(), checkEmptyExceptionMessageFormat(ACCOUNT_ID_MESSAGE_KEY));
        // check startTime and endTime
        checkStringNotEmpty(request.getStartTime(), checkEmptyExceptionMessageFormat(START_TIME_MESSAGE_KEY));
        checkStringNotEmpty(request.getEndTime(), checkEmptyExceptionMessageFormat(END_TIME_MESSAGE_KEY));
        checkUTC(request.getStartTime());
        checkUTC(request.getEndTime());

        // build request params
        internalRequest.addParameter(PAGE_NO_MESSAGE_KEY, String.valueOf(request.getPageNo()));
        internalRequest.addParameter(PAGE_SIZE_MESSAGE_KEY, String.valueOf(request.getPageSize()));
        internalRequest.addParameter(ACCOUNT_ID_MESSAGE_KEY, request.getAccountId());
        internalRequest.addParameter(START_TIME_MESSAGE_KEY, request.getStartTime());
        internalRequest.addParameter(END_TIME_MESSAGE_KEY, request.getEndTime());
        if (request.getAscending() != null) {
            internalRequest.addParameter("ascending", String.valueOf(request.getAscending()));
        }
        if (EventType.Cloud.equals(type) && Strings.isNotEmpty(request.getScope())) {
            internalRequest.addParameter(SCOPE_MESSAGE_KEY, request.getScope());
        }
        if (Strings.isNotEmpty(request.getRegion())) {
            internalRequest.addParameter("region", request.getRegion());
        }
        if (request.getEventLevel() != null && EventLevel.ALL != request.getEventLevel()) {
            internalRequest.addParameter("eventLevel", request.getEventLevel().getName());
        }
        if (EventType.Cloud.equals(type) && Strings.isNotEmpty(request.getResourceType())) {
            internalRequest.addParameter("resourceType", request.getResourceType());
        }
        if (EventType.Cloud.equals(type) && Strings.isNotEmpty(request.getResourceId())) {
            internalRequest.addParameter("resourceId", request.getResourceId());
        }
        if (Strings.isNotEmpty(request.getEventName())) {
            internalRequest.addParameter("eventName", request.getEventName());
        }
        if (Strings.isNotEmpty(request.getEventAlias())) {
            internalRequest.addParameter("eventAlias", request.getEventAlias());
        }
        if (Strings.isNotEmpty(request.getEventId())) {
            internalRequest.addParameter("eventId", request.getEventId());
        }
    }

    private void checkUTC(String time) {
        String utcPattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z$";
        checkPattern(time, utcPattern, "time should be UTC format");
    }


    /**
     * 创建http类型的站点探测任务
     *
     * @param request 请求对象
     * @return TaskResponse
     */
    public TaskResponse createHttpSiteTask(HttpTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getAddress(), checkEmptyExceptionMessageFormat("address"));
        checkStringNotEmpty(request.getMethod(), checkEmptyExceptionMessageFormat("method"));
        checkStringNotEmpty(request.getIdc(), checkEmptyExceptionMessageFormat("idc"));
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }

        String url = String.format(SITE_CREATE_HTTP_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, TaskResponse.class);
    }

    /**
     * 更新http类型的站点探测任务
     *
     * @param request 请求对象
     * @return TaskResponse
     */
    public TaskResponse updateHttpSiteTask(HttpTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));
        checkStringNotEmpty(request.getAddress(), checkEmptyExceptionMessageFormat("address"));
        checkStringNotEmpty(request.getMethod(), checkEmptyExceptionMessageFormat("method"));
        checkStringNotEmpty(request.getIdc(), checkEmptyExceptionMessageFormat("idc"));
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }

        String url = String.format(SITE_UPDATE_HTTP_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT, url);
        return invokeHttpClient(internalRequest, TaskResponse.class);
    }

    /**
     * 查询http类型的站点探测任务详情
     *
     * @param request 请求对象
     * @return HttpTaskResponse
     */
    public HttpTaskResponse getHttpSiteTask(TaskDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));

        String url = String.format(SITE_GET_HTTP_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        internalRequest.addParameter("taskId", request.getTaskId());
        return invokeHttpClient(internalRequest, HttpTaskResponse.class);
    }

    /**
     * 创建https类型的站点探测任务
     *
     * @param request 请求对象
     * @return TaskResponse
     */
    public TaskResponse createHttpsSiteTask(HttpsTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getAddress(), checkEmptyExceptionMessageFormat("address"));
        checkStringNotEmpty(request.getMethod(), checkEmptyExceptionMessageFormat("method"));
        checkStringNotEmpty(request.getIdc(), checkEmptyExceptionMessageFormat("idc"));
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }

        String url = String.format(SITE_CREATE_HTTPS_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, TaskResponse.class);
    }

    /**
     * 更新https类型的站点探测任务
     *
     * @param request 请求对象
     * @return TaskResponse
     */
    public TaskResponse updateHttpsSiteTask(HttpsTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));
        checkStringNotEmpty(request.getAddress(), checkEmptyExceptionMessageFormat("address"));
        checkStringNotEmpty(request.getMethod(), checkEmptyExceptionMessageFormat("method"));
        checkStringNotEmpty(request.getIdc(), checkEmptyExceptionMessageFormat("idc"));
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }

        String url = String.format(SITE_UPDATE_HTTPS_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT, url);
        return invokeHttpClient(internalRequest, TaskResponse.class);
    }

    /**
     * 查询https类型的站点探测任务详情
     *
     * @param request 请求对象
     * @return HttpTaskResponse
     */
    public HttpsTaskResponse getHttpsSiteTask(TaskDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));

        String url = String.format(SITE_GET_HTTPS_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        internalRequest.addParameter("taskId", request.getTaskId());
        return invokeHttpClient(internalRequest, HttpsTaskResponse.class);
    }

    /**
     * 创建ping类型的站点探测任务
     *
     * @param request 请求对象
     * @return TaskResponse
     */
    public TaskResponse createPingSiteTask(PingTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getAddress(), checkEmptyExceptionMessageFormat("address"));
        checkStringNotEmpty(request.getIdc(), checkEmptyExceptionMessageFormat("idc"));
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("timeout should not greater 0");
        }

        String url = String.format(SITE_CREATE_PING_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, TaskResponse.class);
    }

    /**
     * 更新ping类型的站点探测任务
     *
     * @param request 请求对象
     * @return TaskResponse
     */
    public TaskResponse updatePingSiteTask(PingTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));
        checkStringNotEmpty(request.getAddress(), checkEmptyExceptionMessageFormat("address"));
        checkStringNotEmpty(request.getIdc(), checkEmptyExceptionMessageFormat("idc"));
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("timeout should not greater 0");
        }
        String url = String.format(SITE_UPDATE_PING_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT, url);
        return invokeHttpClient(internalRequest, TaskResponse.class);
    }

    /**
     * 查询ping类型的站点探测任务详情
     *
     * @param request 请求对象
     * @return PingTaskResponse
     */
    public PingTaskResponse getPingSiteTask(TaskDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));

        String url = String.format(SITE_GET_PING_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        internalRequest.addParameter("taskId", request.getTaskId());
        return invokeHttpClient(internalRequest, PingTaskResponse.class);
    }

    /**
     * 创建udp类型的站点探测任务
     *
     * @param request 请求对象
     * @return TaskResponse
     */
    public TaskResponse createTcpSiteTask(TcpTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getAddress(), checkEmptyExceptionMessageFormat("address"));
        checkStringNotEmpty(request.getIdc(), checkEmptyExceptionMessageFormat("idc"));
        if (request.getPort() <= 0) {
            throw new IllegalArgumentException("port should not greater 0");
        }
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("timeout should not greater 0");
        }

        String url = String.format(SITE_CREATE_TCP_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, TaskResponse.class);
    }

    /**
     * 更新udp类型的站点探测任务
     *
     * @param request 请求对象
     * @return TaskResponse
     */
    public TaskResponse updateTcpSiteTask(TcpTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));
        checkStringNotEmpty(request.getAddress(), checkEmptyExceptionMessageFormat("address"));
        checkStringNotEmpty(request.getIdc(), checkEmptyExceptionMessageFormat("idc"));
        if (request.getPort() <= 0) {
            throw new IllegalArgumentException("port should not greater 0");
        }
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("timeout should not greater 0");
        }
        String url = String.format(SITE_UPDATE_TCP_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT, url);
        return invokeHttpClient(internalRequest, TaskResponse.class);
    }

    /**
     * 查询tcp类型的站点探测任务详情
     *
     * @param request 请求对象
     * @return TcpTaskResponse
     */
    public TcpTaskResponse getTcpSiteTask(TaskDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));

        String url = String.format(SITE_GET_TCP_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        internalRequest.addParameter("taskId", request.getTaskId());
        return invokeHttpClient(internalRequest, TcpTaskResponse.class);
    }

    /**
     * 创建udp类型的站点探测任务
     *
     * @param request 请求对象
     * @return TaskResponse
     */
    public TaskResponse createUdpSiteTask(UdpTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getAddress(), checkEmptyExceptionMessageFormat("address"));
        checkStringNotEmpty(request.getIdc(), checkEmptyExceptionMessageFormat("idc"));
        if (request.getPort() <= 0) {
            throw new IllegalArgumentException("port should not greater 0");
        }
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("timeout should not greater 0");
        }

        String url = String.format(SITE_CREATE_UDP_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, TaskResponse.class);
    }

    /**
     * 更新udp类型的站点探测任务
     *
     * @param request 请求对象
     * @return TaskResponse
     */
    public TaskResponse updateUdpSiteTask(UdpTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));
        checkStringNotEmpty(request.getAddress(), checkEmptyExceptionMessageFormat("address"));
        checkStringNotEmpty(request.getIdc(), checkEmptyExceptionMessageFormat("idc"));
        if (request.getPort() <= 0) {
            throw new IllegalArgumentException("port should not greater 0");
        }
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("timeout should not greater 0");
        }
        String url = String.format(SITE_UPDATE_UDP_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT, url);
        return invokeHttpClient(internalRequest, TaskResponse.class);
    }

    /**
     * 查询udp类型的站点探测任务详情
     *
     * @param request 请求对象
     * @return UdpTaskResponse
     */
    public UdpTaskResponse getUdpSiteTask(TaskDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));

        String url = String.format(SITE_GET_UDP_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        internalRequest.addParameter("taskId", request.getTaskId());
        return invokeHttpClient(internalRequest, UdpTaskResponse.class);
    }

    /**
     * 创建ftp类型的站点探测任务
     *
     * @param request 请求对象
     * @return TaskResponse
     */
    public TaskResponse createFtpSiteTask(FtpTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getAddress(), checkEmptyExceptionMessageFormat("address"));
        checkStringNotEmpty(request.getIdc(), checkEmptyExceptionMessageFormat("idc"));
        if (request.getPort() <= 0) {
            throw new IllegalArgumentException("port should not greater 0");
        }
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("timeout should not greater 0");
        }

        String url = String.format(SITE_CREATE_FTP_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, TaskResponse.class);
    }

    /**
     * 更新ftp类型的站点探测任务
     *
     * @param request 请求对象
     * @return TaskResponse
     */
    public TaskResponse updateFtpSiteTask(FtpTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));
        checkStringNotEmpty(request.getAddress(), checkEmptyExceptionMessageFormat("address"));
        checkStringNotEmpty(request.getIdc(), checkEmptyExceptionMessageFormat("idc"));
        if (request.getPort() <= 0) {
            throw new IllegalArgumentException("port should not greater 0");
        }
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("timeout should not greater 0");
        }
        String url = String.format(SITE_UPDATE_FTP_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT, url);
        return invokeHttpClient(internalRequest, TaskResponse.class);
    }

    /**
     * 查询ftp类型的站点探测任务详情
     *
     * @param request 请求对象
     * @return FtpTaskResponse
     */
    public FtpTaskResponse getFtpSiteTask(TaskDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));

        String url = String.format(SITE_GET_FTP_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        internalRequest.addParameter("taskId", request.getTaskId());
        return invokeHttpClient(internalRequest, FtpTaskResponse.class);
    }

    /**
     * 创建dns类型的站点探测任务
     *
     * @param request 请求对象
     * @return TaskResponse
     */
    public TaskResponse createDnsSiteTask(DnsTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getAddress(), checkEmptyExceptionMessageFormat("address"));
        checkStringNotEmpty(request.getIdc(), checkEmptyExceptionMessageFormat("idc"));
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("timeout should not greater 0");
        }

        String url = String.format(SITE_CREATE_DNS_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, TaskResponse.class);
    }

    /**
     * 更新dns类型的站点探测任务
     *
     * @param request 请求对象
     * @return TaskResponse
     */
    public TaskResponse updateDnsSiteTask(DnsTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskName(), checkEmptyExceptionMessageFormat("taskName"));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));
        checkStringNotEmpty(request.getAddress(), checkEmptyExceptionMessageFormat("address"));
        checkStringNotEmpty(request.getIdc(), checkEmptyExceptionMessageFormat("idc"));
        if (null == request.getCycle() || request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        if (request.getTimeout() <= 0) {
            throw new IllegalArgumentException("timeout should not greater 0");
        }
        String url = String.format(SITE_UPDATE_DNS_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT, url);
        return invokeHttpClient(internalRequest, TaskResponse.class);
    }

    /**
     * 查询dns类型的站点探测任务详情
     *
     * @param request 请求对象
     * @return DnsTaskResponse
     */
    public DnsTaskResponse getDnsSiteTask(TaskDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));

        String url = String.format(SITE_GET_DNS_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        internalRequest.addParameter("taskId", request.getTaskId());
        return invokeHttpClient(internalRequest, DnsTaskResponse.class);
    }


    /**
     * 查询站点监控任务列表
     *
     * @param request
     * @return PageData<TaskSummaryResponse>
     */
    public PageData<TaskSummaryResponse> getSiteTaskList(TaskSummaryRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        if (request.getPageNo() <= 0) {
            throw new IllegalArgumentException("pageNo should not greater 0");
        }
        if (request.getPageSize() <= 0) {
            throw new IllegalArgumentException("pageSize should not greater 0");
        }
        String url = String.format(SITE_GET_TASK_LIST_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        Map<String, String> params = new HashMap<String, String>();
        params.put("pageNo", String.valueOf(request.getPageNo()));
        params.put("pageSize", String.valueOf(request.getPageSize()));
        params.put("query", request.getQuery());
        internalRequest.setParameters(params);
        return invokeHttpClient(internalRequest, PageData.class);
    }

    /**
     * 删除站点监控任务
     *
     * @param request
     * @return SiteBasicResponse
     */
    public SiteBasicResponse deleteSiteTask(SiteTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));
        String url = String.format(SITE_DELETE_TASK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.DELETE, url);
        internalRequest.addParameter("taskId", request.getTaskId());
        return invokeHttpClient(internalRequest, SiteBasicResponse.class);
    }

    /**
     * 站点监控任务查询
     *
     * @param request
     * @return SiteInfoResponse
     */
    public SiteInfoResponse getSiteInfo(SiteTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));
        String url = String.format(SITE_GET_TASK_DETAIL_PATH, request.getUserId(), request.getTaskId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        return invokeHttpClient(internalRequest, SiteInfoResponse.class);
    }

    /**
     * 创建站点监控报警策略
     *
     * @param request
     */
    public void createSiteAlarmConfig(SiteAlarmConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAliasName(), checkEmptyExceptionMessageFormat("aliasName"));
        checkNotNull(request.getLevel(), "level should not be null");
        checkArgument(request.getRules().size() != 0, checkEmptyExceptionMessageFormat("rules"));
        List<SiteAlarmRule> rules = request.getRules();
        for (SiteAlarmRule rule : rules) {
            if (CollectionUtils.isEmpty(rule.getActOnIdcs()) && CollectionUtils.isEmpty(rule.getActOnIsps())) {
                checkStringNotEmpty(null, checkEmptyExceptionMessageFormat("idc or isp"));
            }
        }
        String url = String.format(SITE_CREATE_ALARM_CONFIG_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 更新站点监控报警策略
     *
     * @param request
     */
    public void updateSiteAlarmConfig(SiteAlarmConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
        checkStringNotEmpty(request.getAliasName(), checkEmptyExceptionMessageFormat("aliasName"));
        checkNotNull(request.getLevel(), "level should not be null");
        checkArgument(request.getRules().size() != 0, checkEmptyExceptionMessageFormat("rules"));
        List<SiteAlarmRule> rules = request.getRules();
        for (SiteAlarmRule rule : rules) {
            if (CollectionUtils.isEmpty(rule.getActOnIdcs()) && CollectionUtils.isEmpty(rule.getActOnIsps())) {
                checkStringNotEmpty(null, checkEmptyExceptionMessageFormat("idc or isp"));
            }
        }
        String url = String.format(SITE_UPDATE_ALARM_CONFIG_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT, url);
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 查询站点报警策略详情
     *
     * @param request
     * @return SiteAlarmConfigDetailResponse
     */
    public SiteAlarmConfigDetailResponse getSiteAlarmConfigDetail(SiteAlarmUserIdRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
        String url = String.format(SITE_GET_ALARM_CONFIG_DETAIL_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        internalRequest.addParameter("alarmName", request.getAlarmName());
        return invokeHttpClient(internalRequest, SiteAlarmConfigDetailResponse.class);
    }

    /**
     * 查询站点报警策略详情
     *
     * @param request
     * @return SiteAlarmConfigDetailResponse
     */
    public PageResultResponse<SiteAlarmConfigDetailResponse> getSiteAlarmConfigList(SiteAlarmConfigListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        if (request.getPageNo() <= 0) {
            throw new IllegalArgumentException("pageNo should not greater 0");
        }
        if (request.getPageSize() <= 0) {
            throw new IllegalArgumentException("pageSize should not greater 0");
        }
        String url = String.format(SITE_GE_TALARM_CONFIG_LIST_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        if (!StringUtils.isEmpty(request.getAliasName())) {
            internalRequest.addParameter("aliasName", request.getAliasName());
        }
        if (!StringUtils.isEmpty(request.getTaskId())) {
            internalRequest.addParameter("taskId", request.getTaskId());
        }
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        internalRequest.addParameter("actionEnabled", String.valueOf(request.isActionEnabled()));
        return invokeHttpClient(internalRequest, PageResultResponse.class);
    }

    /**
     * 屏蔽站点监控报警策略
     *
     * @param request
     */
    public void blockSiteAlarmConfig(SiteAlarmUserIdRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat("namespace"));
        String url = String.format(SITE_ALARM_BLOCK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.POST, url);
        internalRequest.addParameter("alarmName", request.getAlarmName());
        internalRequest.addParameter("namespace", request.getNamespace());
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 开启站点监控报警策略
     *
     * @param request
     */
    public void unblockSiteAlarmConfig(SiteAlarmUserIdRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat("namespace"));
        String url = String.format(SITE_ALARM_UNBLOCK_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.POST, url);
        internalRequest.addParameter("alarmName", request.getAlarmName());
        internalRequest.addParameter("namespace", request.getNamespace());
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 通过报警策略名获得站点任务信息
     *
     * @param request
     */
    public SiteInfoResponse getSiteConfigByAlarmName(SiteAlarmUserIdRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
        String url = String.format(SITE_GET_TASK_BY_ALARMNAME_PATH, request.getUserId(), request.getAlarmName());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        return invokeHttpClient(internalRequest, SiteInfoResponse.class);
    }

    /**
     * 查询整体/探测点/运营商趋势图
     *
     * @param request
     */
    public List<SiteMetricDataQueryResponse> getSiteMetricSiteData(SiteMetricDataQueryRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getMetricName(), checkEmptyExceptionMessageFormat("metricName"));
        checkStringNotEmpty(request.getStartTime(), checkEmptyExceptionMessageFormat("startTime"));
        checkStringNotEmpty(request.getEndTime(), checkEmptyExceptionMessageFormat("endTime"));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));
        checkNotNull(request.getStatistics(), checkEmptyExceptionMessageFormat("statistics"));
        if (request.getCycle() <= 0) {
            throw new IllegalArgumentException("cycle should not greater 0");
        }
        String url = String.format(SITE_GET_METRIC_DATA_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        internalRequest.addParameter("metricName", request.getMetricName());
        internalRequest.addParameter("taskId", request.getTaskId());
        internalRequest.addParameter("statistics", StringUtils.join(request.getStatistics(), ","));
        internalRequest.addParameter("startTime", request.getStartTime());
        internalRequest.addParameter("endTime", request.getEndTime());
        internalRequest.addParameter("cycle", String.valueOf(request.getCycle()));
        if (!StringUtils.isEmpty(request.getDimensions())) {
            internalRequest.addParameter("dimensions", request.getDimensions());
        }
        return invokeHttpClient(internalRequest, ListResponse.class).getResult();
    }

    /**
     * 查询整体视图
     *
     * @param request
     * @return List<SiteViewResponse>
     */
    public List<SiteViewResponse> getSiteOverallView(SiteTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));
        String url = String.format(SITE_GET_OVERALL_VIEW_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        internalRequest.addParameter("taskId", request.getTaskId());
        return invokeHttpClient(internalRequest, ListResponse.class).getResult();
    }

    /**
     * 查询分省视图
     *
     * @param request
     * @return List<SiteViewResponse>
     */
    public List<SiteViewResponse> getSiteProvincialView(SiteTaskIspRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTaskId(), checkEmptyExceptionMessageFormat("taskId"));
        checkStringNotEmpty(request.getIsp(), checkEmptyExceptionMessageFormat("isp"));
        String url = String.format(SITE_GET_PROVINCIAL_VIEW_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        internalRequest.addParameter("taskId", request.getTaskId());
        internalRequest.addParameter("isp", request.getIsp());
        return invokeHttpClient(internalRequest, ListResponse.class).getResult();
    }

    /**
     * 查询所有探测点
     *
     * @param request
     * @return Set<SiteViewResponse>
     */
    public List<SiteAgentResponse> getSiteAgentList(SiteAgentRequest request) {
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        String url = String.format(SITE_AGENT_LIST_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        return invokeHttpClient(internalRequest, ListResponse.class).getResult();
    }

    /**
     * 查询所有探测点
     *
     * @param request
     * @return List<SiteViewResponse>
     */
    public IdcIspResponse getSiteAgentListByTaskId(SiteTaskRequest request) {
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        String url = String.format(SITE_GET_AGENT_BY_TASKID_PATH, request.getUserId());
        InternalRequest internalRequest = this.createRequestWithUrl(request, HttpMethodName.GET, url);
        internalRequest.addParameter("taskId", request.getTaskId());
        return invokeHttpClient(internalRequest, IdcIspResponse.class);
    }

    /**
     * 创建自定义报警策略
     *
     * @param request 请求参数
     * @return 返回响应结果
     */
    public void createCustomAlarmConfig(CustomAlarmConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkIsTrue(request.getActionEnabled() != null, "actionEnabled should not be null");
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat("namespace"));
        checkIsTrue(request.getLevel() != null, "level should not be null");
        checkIsTrue(request.getRules() != null, "rules should not be null");
        for (CustomAlarmRule rule : request.getRules()) {
            checkCustomAlarmRule(rule);
        }
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/csm/api/v1/custom/alarm/configs/create");
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    private void checkCustomAlarmRule(CustomAlarmRule rule) {
        checkStringNotEmpty(rule.getMetricName(), checkEmptyExceptionMessageFormat("metricName"));
        checkIsTrue(rule.getCycle() > 0, "cycle need large 0");
        checkStringNotEmpty(rule.getStatistics(), checkEmptyExceptionMessageFormat("statistics"));
        checkStringNotEmpty(rule.getComparisonOperator(), checkEmptyExceptionMessageFormat("comparisonOperator"));
        checkStringNotEmpty(rule.getFunction(), checkEmptyExceptionMessageFormat("comparisonOperator"));
        checkIsTrue(rule.getFunction() == "THRESHOLD", "function should be THRESHOLD");
    }

    /**
     * 删除自定义报警策略
     *
     * @param request 请求参数
     * @return 返回响应结果
     */
    public void deleteCustomAlarmConfig(AlarmPolicyBatchListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkNotNull(request.getCustomAlarmList(), "customAlarmList should not be null");
        for (AlarmPolicyBatch config : request.getCustomAlarmList()) {
            checkStringNotEmpty(config.getUserId(), checkEmptyExceptionMessageFormat(USER_ID));
            checkStringNotEmpty(config.getScope(), checkEmptyExceptionMessageFormat(SCOPE_MESSAGE_KEY));
            checkIsTrue(config.getAlarmName() != null, "alarmName should not be null");
        }
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/csm/api/v1/custom/alarm/configs/delete");
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 更新自定义报警策略
     *
     * @param request 请求参数
     * @return 返回响应结果
     */
    public void updateCustomAlarmConfig(CustomAlarmConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID_MESSAGE_KEY));
        checkIsTrue(request.getActionEnabled() != null, "actionEnabled should not be null");
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat("namespace"));
        checkIsTrue(request.getLevel() != null, "level should not be null");
        checkIsTrue(request.getRules() != null, "rules should not be null");
        for (CustomAlarmRule rule : request.getRules()) {
            checkCustomAlarmRule(rule);
        }
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT,
                "/csm/api/v1/custom/alarm/configs/update");
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 搜索自定义报警策略
     *
     * @param request 请求参数
     * @return 返回响应结果
     */
    public ListCustomConfigResponse listCustomAlarmConfig(ListCustomAlarmConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat(USER_ID));
        if (request.getPageNo() == 0) {
            request.setPageNo(1);
        }
        if (request.getPageSize() == 0) {
            request.setPageSize(10);
        }
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET,
                "/csm/api/v1/custom/alarm/configs/list");
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", request.getUserId());
        if (null != request.getAlarmName()) {
            params.put("alarmName", request.getAlarmName());
        }
        if (null != request.getNamespace()) {
            params.put("namespace", request.getNamespace());
        }
        params.put("pageNo", String.valueOf(request.getPageNo()));
        params.put("pageSize", String.valueOf(request.getPageSize()));
        if (null != request.getActionEnabled()) {
            params.put("actionEnabled", String.valueOf(request.getActionEnabled()));
        }
        internalRequest.setParameters(params);
        return invokeHttpClient(internalRequest, ListCustomConfigResponse.class);
    }

    /**
     * 查看自定义报警策略
     *
     * @param request 请求参数
     * @return 返回响应结果
     */
    public CustomAlarmConfigResponse detailCustomAlarmConfig(DetailCustomAlarmConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat("userId"));
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat("namespace"));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET,
                "/csm/api/v1/custom/alarm/configs/detail");
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", request.getUserId());
        params.put("alarmName", request.getAlarmName());
        params.put("namespace", request.getNamespace());
        internalRequest.setParameters(params);
        ;
        return invokeHttpClient(internalRequest, CustomAlarmConfigResponse.class);
    }

    /**
     * 关闭自定义报警策略
     *
     * @param request 请求参数
     * @return 返回响应结果
     */
    public void blockCustomAlarmConfig(DetailCustomAlarmConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat("userId"));
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat("namespace"));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/csm/api/v1/custom/alarm/configs/block");
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", request.getUserId());
        params.put("alarmName", request.getAlarmName());
        params.put("namespace", request.getNamespace());
        internalRequest.setParameters(params);
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 打开定义报警策略
     *
     * @param request 请求参数
     * @return 返回响应结果
     */
    public void unblockCustomAlarmConfig(DetailCustomAlarmConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat("userId"));
        checkStringNotEmpty(request.getAlarmName(), checkEmptyExceptionMessageFormat("alarmName"));
        checkStringNotEmpty(request.getNamespace(), checkEmptyExceptionMessageFormat("namespace"));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/csm/api/v1/custom/alarm/configs/unblock");
        Map<String, String> params = new HashMap<String, String>();
        params.put("userId", request.getUserId());
        params.put("alarmName", request.getAlarmName());
        params.put("namespace", request.getNamespace());
        internalRequest.setParameters(params);
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * 创建探测任务
     *
     * @param request 请求参数
     * @return 返回响应结果
     */
    public HttpResponseWrapper<SiteOnceTaskResponse> createSiteOnceTask(String type, SiteOnceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkNotNull(request.getOnceConfig(), "onceConfig is not null");
        checkIsTrue(checkSiteOnceType(type), "type is wrong, provide HTTP/HTTPS/PING/FTP/TCP/UDP/DNS.");
        checkIsTrue(checkSiteOnceType(String.valueOf(request.getProtocolType())), "protocolType is wrong, provide HTTP/HTTPS/PING/FTP/TCP/UDP/DNS.");
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat("userId"));
        checkStringNotEmpty(request.getAddress(), checkEmptyExceptionMessageFormat("address"));
        checkIsTrue(request.getIpType() != null && (request.getIpType().equals("ipv6") || request.getIpType().equals("ipv4")), "ipType is ipv6 or ipv4");
        checkStringNotEmpty(request.getIdc(), checkEmptyExceptionMessageFormat("idc"));
        if (request.getTimeout() < 0) {
            request.setTimeout(60);
        }
        String url = String.format("/csm/api/v1/site/once/%s/taskCreate", type);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, HttpResponseWrapper.class);
    }

    private boolean checkSiteOnceType(String type) {
        if ("HTTP".equals(type) || "HTTPS".equals(type)
                || "PING".equals(type) || "FTP".equals(type)
                || "TCP".equals(type) || "UDP".equals(type)
                || "DNS".equals(type)
        ) {
            return true;
        }
        return false;
    }

    /**
     * 探测历史记录
     *
     * @param request 请求参数
     * @return 返回响应结果
     */
    public HttpResponseWrapper<SiteOnceTaskList> listSiteOnceHistory(SiteOnceTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (request.getPageNo() <= 0) {
            request.setPageNo(1);
        }
        if (request.getPageSize() <= 0) {
            request.setPageSize(10);
        }
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/csm/api/v1/site/once/taskList");

        return invokeHttpClient(internalRequest, HttpResponseWrapper.class);
    }

    /**
     * 删除探测记录
     *
     * @param request 请求参数
     * @return 返回响应结果
     */
    public HttpResponseWrapper<SiteOnceTaskResponse> deleteSiteOnceRecord(SiteOnceTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat("userId"));
        checkStringNotEmpty(request.getSiteId(), checkEmptyExceptionMessageFormat("siteId"));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/csm/api/v1/site/once/taskDelete");
        return invokeHttpClient(internalRequest, HttpResponseWrapper.class);
    }

    /**
     * 探测任务结果
     *
     * @param request 请求参数
     * @return 返回响应结果
     */
    public HttpResponseWrapper<SiteOnceTaskRequest> detailSiteOnceResult(SiteOnceTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat("userId"));
        checkStringNotEmpty(request.getSiteId(), checkEmptyExceptionMessageFormat("siteId"));
        if (request.getPageNo() <= 0) {
            request.setPageNo(1);
        }
        if (request.getPageSize() <= 0) {
            request.setPageSize(10);
        }
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/csm/api/v1/site/once/loadData");
        return invokeHttpClient(internalRequest, HttpResponseWrapper.class);
    }

    /**
     * 探测详情
     *
     * @param request 请求参数
     * @return 返回响应结果
     */
    public HttpResponseWrapper<SiteOnceGroupTask> detailSiteOnce(SiteOnceTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat("userId"));
        if (request.getPageNo() <= 0) {
            request.setPageNo(1);
        }
        if (request.getPageSize() <= 0) {
            request.setPageSize(10);
        }
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/csm/api/v1/site/once/groupTask");
        return invokeHttpClient(internalRequest, HttpResponseWrapper.class);
    }

    /**
     * 重新探测
     *
     * @param request 请求参数
     * @return 返回响应结果
     *
     */
    public HttpResponseWrapper<SiteOnceTaskResponse> againExecSiteOnce(SiteOnceTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), checkEmptyExceptionMessageFormat("userId"));
        checkStringNotEmpty(request.getSiteId(), checkEmptyExceptionMessageFormat("siteId"));
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/csm/api/v1/site/once/createFromTask");
        return invokeHttpClient(internalRequest, HttpResponseWrapper.class);
    }
    /**
     * 历史探测列表
     *
     * @param request 请求参数
     * @return 返回响应结果
     */
    public HttpResponseWrapper<SiteOnceTaskList> listSiteOnceTaskHistory(SiteOnceTaskRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST,
                "/csm/api/v1/site/once/groupTaskList");

        return invokeHttpClient(internalRequest, HttpResponseWrapper.class);
    }


    /**
     * 获取站点代理
     *
     * @param userId 用户ID
     * @param ipType IP类型，值为"ipv4"或"ipv6"
     * @return 返回响应结果
     */
    public HttpResponseWrapper<SiteOnceAgent> getSiteAgent(String userId, String ipType) {
        checkStringNotEmpty(userId, checkEmptyExceptionMessageFormat("userId"));
        checkIsTrue(ipType != null && ("ipv6".equals(ipType) || "ipv4".equals(ipType)), "ipType is ipv6 or ipv4");

        InternalRequest internalRequest = this.createBodyRequest(new EmptyRequest(), HttpMethodName.GET,
                "/csm/api/v1/site/once/siteAgent");
        internalRequest.addParameter("userId", userId);
        internalRequest.addParameter("ipType", ipType);
        return invokeHttpClient(internalRequest, HttpResponseWrapper.class);
    }


    /**
     * create application data
     * @param request
     * @return ApplicationInfoResponse
     */
    public ApplicationInfoResponse createApplicationData(ApplicationInfoRequest request) {
        // check not null
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "name"));
        checkStringNotEmpty(request.getType(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "type"));
        checkStringNotEmpty(request.getUserId(), USER_NULL_ERROR_MESSAGE);
        String url = String.format(APPLICATION_INFO_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, ApplicationInfoResponse.class);
    }

    /**
     * get application data
     * @param userId
     * @param request
     * @return ApplicationDataListResponse
     */
    public ApplicationDataListResponse getApplicationDataList(String userId, ApplicationDataListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(userId, USER_NULL_ERROR_MESSAGE);
        String url = String.format(APPLICATION_INFO_PATH, userId);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET, url);
        return invokeHttpClient(internalRequest, ApplicationDataListResponse.class);
    }

    /**
     * update application data
     * @param request
     * @return ApplicationInfoUpdateResponse
     */
    public ApplicationInfoUpdateResponse updateApplicationData(ApplicationInfoUpdateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), USER_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(String.valueOf(request.getId()), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "id"));
        checkStringNotEmpty(request.getType(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "type"));
        checkStringNotEmpty(request.getName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "name"));
        String url = String.format(APPLICATION_INFO_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT, url);
        return invokeHttpClient(internalRequest, ApplicationInfoUpdateResponse.class);
    }

    /**
     * delete application data
     * @param userId
     * @param request
     * @return ApplicationMonitorResponse
     */
    public void deleteApplicationData(String userId, ApplicationInfoDetaleRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "name"));
        checkStringNotEmpty(userId, String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userID"));
        String url = String.format(APPLICATION_INFO_DELETE_PATH, userId, request.getName());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.DELETE, url);
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * get application instance list
     * @param userId
     * @param request
     * @return ApplicationInstanceListResponse
     */
    public ApplicationInstanceListResponse getApplicationInstanceList(String userId, ApplicationInstanceListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(userId, String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userId"));
        checkStringNotEmpty(request.getRegion(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "region"));
        checkStringNotEmpty(request.getAppName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "appName"));
        if (StringUtils.isEmpty(String.valueOf(request.getPageSize())) || StringUtils.isEmpty(String.valueOf(request.getPageNo()))) {
            request.setPageSize(10);
            request.setPageNo(1);
        }
        String url = String.format(APPLICATION_INFO_LIST_PATH, userId);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, ApplicationInstanceListResponse.class);
    }

    /**
     * create application instance
     * @param request
     * @return ApplicationMonitorResponse
     */
    public ApplicationMonitorResponse createApplicationInstance(ApplicationInstanceCreateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkNotNull(request.getHostList(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "instanceList"));
        String url = String.format(APPLICATION_INSTANCE_CREATE_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, ApplicationMonitorResponse.class);
    }

    /**
     * get application created instance list
     * @param request
     * @return ApplicationInstanceListResponse
     */
    public ApplicationInstanceListResponse getApplicationInstanceCreatedList(ApplicationInstanceCreatedListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        String url = String.format(APPLICATION_INSTANCE_CREATED_LIST_PATH, request.getUserId(), request.getAppName());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET, url);
        return invokeHttpClient(internalRequest, ApplicationInstanceListResponse.class);
    }

    /**
     * delete application instance
     * @param userId
     * @param request
     * @return ApplicationMonitorResponse
     */
    public void deleteApplicationInstance(String userId, ApplicationInstanceDeleteRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(userId, String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userId"));
        checkStringNotEmpty(request.getAppName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "appName"));
        checkStringNotEmpty(request.getId(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "id"));
        String url = String.format(APPLICATION_INSTANCE_DELETE_PATH, userId, request.getAppName(), request.getId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.DELETE, url);
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * create application instance task
     * @param userId
     * @param request
     * @return ApplicationMonitorTaskResponse
     */
    public ApplicationMonitorTaskResponse createApplicationInstanceTask(String userId, ApplicationMonitorTaskInfoRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(userId, String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userID"));
        String url = String.format(APPLICATION_TASK_CREATE_PATH, userId);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, ApplicationMonitorTaskResponse.class);
    }

    /**
     * get application monitor task detail
      * @param request
     * @return ApplicationMonitorTaskResponse
     */
    public ApplicationMonitorTaskResponse getApplicationMonitorTaskDetail(ApplicationMonitorTaskDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userID"));
        checkStringNotEmpty(request.getAppName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "appName"));
        checkStringNotEmpty(request.getTaskName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "taskName"));
        String url = String.format(APPLICATION_TASK_DETAIL_PATH, request.getUserId(), request.getAppName(), request.getTaskName());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.GET, url);
        return invokeHttpClient(internalRequest, ApplicationMonitorTaskResponse.class);
    }

    /**
     * get application monitor task list
     * @param request
     * @return
     */
    public List<ApplicationMonitorTaskResponse> getApplicationMonitorTaskList(ApplicationMonitorTaskListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userID"));
        checkStringNotEmpty(request.getAppName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "appName"));
        String url = String.format(APPLICATION_TASK_LIST_PATH, request.getUserId(), request.getAppName());
        InternalRequest internalRequest = createRequestWithUrl(request, HttpMethodName.GET, url);
        if (null != request.getType() && StringUtils.isNotEmpty(request.getType())) {
            internalRequest.addParameter("type", request.getType());
        }
        return invokeHttpClient(internalRequest, ListResponse.class).getResult();
    }

    /**
     * update application monitor task
     * @param userId
     * @param request
     * @return ApplicationMonitorTaskResponse
     */
    public ApplicationMonitorTaskResponse updateApplicationMonitorTask(String userId, ApplicationMonitorTaskInfoRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(userId, String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userID"));
        checkStringNotEmpty(request.getAppName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "appName"));
        checkStringNotEmpty(request.getName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "name"));
        checkStringNotEmpty(request.getAliasName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "aliasName"));
        checkStringNotEmpty(request.getTarget(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "target"));
        if (request.getType() < 0 || request.getType() > 3) {
            throw new IllegalArgumentException("type must be in [0,1,2,3]");
        }
        String url = String.format(APPLICATION_TASK_UPDATE_PATH, userId);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT, url);
        return invokeHttpClient(internalRequest, ApplicationMonitorTaskResponse.class);

    }

    /**
     * delete application monitor task
     * @param request
     * @return ApplicationMonitorResponse
     */
    public void deleteApplicationMonitorTask(ApplicationMonitorTaskDeleteRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userID"));
        checkStringNotEmpty(request.getAppName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "appName"));
        checkStringNotEmpty(request.getName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "name"));
        String url = String.format(APPLICATION_TASK_DELETE_PATH, request.getUserId(), request.getAppName(), request.getName());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.DELETE, url);
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    /**
     * create application dimension table
     * @param request
     * @return ApplicationDimensionTableInfoResponse
     */
    public ApplicationDimensionTableInfoResponse createApplicationDimensionTable(ApplicationDimensionTableInfoRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userID"));
        checkStringNotEmpty(request.getAppName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "appName"));
        checkStringNotEmpty(request.getTableName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "tableName"));
        checkStringNotEmpty(request.getMapContentJson(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "mapContentJson"));
        String url = String.format(APPLICATION_DIMENSION_TABLE_CREATE_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, ApplicationDimensionTableInfoResponse.class);
    }

    /**
     * get application dimension table list
     * @param request
     * @return ApplicationDimensionTableListResponse
     */
    public List<ApplicationDimensionTableInfoResponse> getApplicationDimensionTableList(ApplicationDimensionTableListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userID"));
        checkStringNotEmpty(request.getAppName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "appName"));
        String url = String.format(APPLICATION_DIMENSION_TABLE_LIST_PATH, request.getUserId(), request.getAppName());
        InternalRequest internalRequest = createRequestWithUrl(request, HttpMethodName.GET, url);
        if (null != request.getSearchName() && StringUtils.isNotEmpty(request.getSearchName())) {
            internalRequest.addParameter("searchName", request.getSearchName());
        }
        return invokeHttpClient(internalRequest, ListResponse.class).getResult();
    }

    /**
     *  update application dimension table
     * @param request
     * @return ApplicationMonitorResponse
     */
    public ApplicationMonitorResponse updateApplicationDimensionTable(ApplicationDimensionTableInfoRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userID"));
        checkStringNotEmpty(request.getAppName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "appName"));
        checkStringNotEmpty(request.getTableName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "tableName"));
        checkStringNotEmpty(request.getMapContentJson(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "mapContentJson"));
        String url = String.format(APPLICATION_DIMENSION_TABLE_UPDATE_PATH, request.getUserId());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.PUT, url);
        return invokeHttpClient(internalRequest, ApplicationMonitorResponse.class);
    }

    /**
     * delete application dimension table
     * @param request
     * @return ApplicationMonitorResponse
     */
    public void deleteApplicationDimensionTable(ApplicationDimensionTableDeleteRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userID"));
        checkStringNotEmpty(request.getAppName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "appName"));
        checkStringNotEmpty(request.getTableName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "tableName"));
        String url = String.format(APPLICATION_DIMENSION_TABLE_DELETE_PATH, request.getUserId(),
                request.getAppName(), request.getTableName());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.DELETE, url);
        invokeHttpClient(internalRequest, EmptyResponse.class);
    }

    public MultiDimensionalLatestMetricsResponse getMultiDimensionalLatestMetrics(MultiDimensionalLatestMetricsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userID"));
        checkStringNotEmpty(request.getScope(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "scope"));
        checkListSizeInRange(request.getDimensions(), MAX_DIMENSIONS_SIZE, "the max size of dimensions is " + MAX_DIMENSIONS_SIZE);
        String url = String.format(MULTI_DIMENSIONAL_LATEST_METRICS_PATH, request.getUserId(), request.getScope());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, MultiDimensionalLatestMetricsResponse.class);
    }

    public TsdbMetricResult<PageResultResponse<TsdbMetricAllDataResult.AllDataMetric>> getMetricsByPartialDimensions(PartialDimensionsMetricsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userID"));
        checkStringNotEmpty(request.getScope(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "scope"));
        checkStringNotEmpty(request.getStartTime(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "startTime"));
        checkStringNotEmpty(request.getEndTime(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "endTime"));
        checkStringNotEmpty(request.getMetricName(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "metricName"));
        if (CollectionUtils.isEmpty(request.getStatistics())) {
                throw new IllegalArgumentException("param statistics should not be null");
        }
        checkListSizeInRange(request.getDimensions(), MAX_DIMENSIONS_SIZE, "the max size of dimensions is " + MAX_DIMENSIONS_SIZE);
        String url = String.format(METRICS_BY_PARTIAL_DIMENSIONS_PATH, request.getUserId(), request.getScope());
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, url);
        return invokeHttpClient(internalRequest, TsdbMetricResult.class);
    }

    public TsdbMetricAllDataResult batchGetMetricsAllDataV2(MultiDimensionalMetricsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userID"));
        checkStringNotEmpty(request.getScope(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "scope"));
        checkStringNotEmpty(request.getRegion(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "region"));
        checkStringNotEmpty(request.getStartTime(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "startTime"));
        checkStringNotEmpty(request.getEndTime(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "endTime"));
        checkIsTrue(CollectionUtils.isNotEmpty(request.getMetricNames()), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "metricNames"));
        checkIsTrue(CollectionUtils.isNotEmpty(request.getStatistics()), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "statistics"));
        checkIsTrue(CollectionUtils.isNotEmpty(request.getDimensions()), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "dimensions"));
        checkListSizeInRange(request.getDimensions(), MAX_DIMENSIONS_SIZE, "the max size of dimensions is " + MAX_DIMENSIONS_SIZE);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, BATCH_GET_METRICS_PATH);
        return invokeHttpClient(internalRequest, TsdbMetricAllDataResult.class);
    }

    public TsdbMetricAllDataResult getAllDataMetricV2(MultiDimensionalMetricsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getUserId(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "userID"));
        checkStringNotEmpty(request.getScope(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "scope"));
        checkStringNotEmpty(request.getRegion(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "region"));
        checkStringNotEmpty(request.getStartTime(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "startTime"));
        checkStringNotEmpty(request.getEndTime(), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "endTime"));
        checkIsTrue(CollectionUtils.isNotEmpty(request.getMetricNames()), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "metricNames"));
        checkIsTrue(CollectionUtils.isNotEmpty(request.getStatistics()), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "statistics"));
        checkIsTrue(CollectionUtils.isNotEmpty(request.getDimensions()), String.format(REQUEST_PARAM_NULL_ERROR_MESSAGE, "dimensions"));
        checkListSizeInRange(request.getDimensions(), MAX_DIMENSIONS_SIZE, "the max size of dimensions is " + MAX_DIMENSIONS_SIZE);
        InternalRequest internalRequest = this.createBodyRequest(request, HttpMethodName.POST, ALL_DATA_METRIC_V2_PATH);
        return invokeHttpClient(internalRequest, TsdbMetricAllDataResult.class);
    }
}
