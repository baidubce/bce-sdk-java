package com.baidubce.services.bcm;

import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcm.model.CommonKV;
import com.baidubce.services.bcm.model.Dimension;
import com.baidubce.services.bcm.model.ListMetricDataRequest;
import com.baidubce.services.bcm.model.ListMetricDataResponse;
import com.baidubce.services.bcm.model.MetricDataRequest;
import com.baidubce.services.bcm.model.MetricDataResponse;
import com.baidubce.services.bcm.model.Page;
import com.baidubce.services.bcm.model.PushCustomMetricDataRequest;
import com.baidubce.services.bcm.model.PushMetricDataResponse;
import com.baidubce.services.bcm.model.StatisticValue;
import com.baidubce.services.bcm.model.Statistics;
import com.baidubce.services.bcm.model.alarm.AlarmConfig;
import com.baidubce.services.bcm.model.alarm.AlarmConfigActionV2;
import com.baidubce.services.bcm.model.alarm.AlarmConfigPolicyRuleV2;
import com.baidubce.services.bcm.model.alarm.AlarmConfigPolicyV2;
import com.baidubce.services.bcm.model.alarm.AlarmConfigV2;
import com.baidubce.services.bcm.model.alarm.AlarmMetric;
import com.baidubce.services.bcm.model.alarm.AlarmRule;
import com.baidubce.services.bcm.model.alarm.MonitorObject;
import com.baidubce.services.bcm.model.alarm.TargetInstance;
import com.baidubce.services.bcm.model.alarm.TargetType;
import com.baidubce.services.bcm.model.alarm.request.CommonAlarmConfigRequest;
import com.baidubce.services.bcm.model.alarm.request.CreateOrUpdateAlarmConfigRequest;
import com.baidubce.services.bcm.model.alarm.request.CreateOrUpdateAlarmConfigV2Request;
import com.baidubce.services.bcm.model.alarm.request.ListAlarmMetricsRequest;
import com.baidubce.services.bcm.model.alarm.request.ListSingleInstanceAlarmConfigsRequest;
import com.baidubce.services.bcm.model.alarm.response.CreateAlarmConfigV2Response;
import com.baidubce.services.bcm.model.application.AggrTag;
import com.baidubce.services.bcm.model.application.ApplicationDataListRequest;
import com.baidubce.services.bcm.model.application.ApplicationDataListResponse;
import com.baidubce.services.bcm.model.application.ApplicationDimensionTableDeleteRequest;
import com.baidubce.services.bcm.model.application.ApplicationDimensionTableInfoRequest;
import com.baidubce.services.bcm.model.application.ApplicationDimensionTableInfoResponse;
import com.baidubce.services.bcm.model.application.ApplicationDimensionTableListRequest;
import com.baidubce.services.bcm.model.application.ApplicationInfoDetaleRequest;
import com.baidubce.services.bcm.model.application.ApplicationInfoRequest;
import com.baidubce.services.bcm.model.application.ApplicationInfoResponse;
import com.baidubce.services.bcm.model.custom.AlarmPolicyBatch;
import com.baidubce.services.bcm.model.custom.AlarmPolicyBatchListRequest;
import com.baidubce.services.bcm.model.application.ApplicationInfoUpdateRequest;
import com.baidubce.services.bcm.model.application.ApplicationInfoUpdateResponse;
import com.baidubce.services.bcm.model.application.ApplicationInstanceCreateRequest;
import com.baidubce.services.bcm.model.application.ApplicationInstanceCreatedListRequest;
import com.baidubce.services.bcm.model.application.ApplicationInstanceDeleteRequest;
import com.baidubce.services.bcm.model.application.ApplicationInstanceListRequest;
import com.baidubce.services.bcm.model.application.ApplicationInstanceListResponse;
import com.baidubce.services.bcm.model.application.ApplicationMonitorResponse;
import com.baidubce.services.bcm.model.application.ApplicationMonitorTaskDeleteRequest;
import com.baidubce.services.bcm.model.application.ApplicationMonitorTaskDetailRequest;
import com.baidubce.services.bcm.model.application.ApplicationMonitorTaskInfoRequest;
import com.baidubce.services.bcm.model.application.ApplicationMonitorTaskListRequest;
import com.baidubce.services.bcm.model.application.ApplicationMonitorTaskResponse;
import com.baidubce.services.bcm.model.application.HostInstanceInfo;
import com.baidubce.services.bcm.model.application.LogExtractResult;
import com.baidubce.services.bcm.model.application.Metric;
import com.baidubce.services.bcm.model.custom.BatchDeleteNamespaceEventsRequest;
import com.baidubce.services.bcm.model.custom.BatchDeleteNamespaceMetricsRequest;
import com.baidubce.services.bcm.model.custom.BatchDeleteNamespacesRequest;
import com.baidubce.services.bcm.model.custom.CustomAlarmConfigResponse;
import com.baidubce.services.bcm.model.custom.CustomMonitorResponse;
import com.baidubce.services.bcm.model.custom.DetailCustomAlarmConfigRequest;
import com.baidubce.services.bcm.model.custom.EventLevelEnum;
import com.baidubce.services.bcm.model.custom.GetCustomEventRequest;
import com.baidubce.services.bcm.model.custom.GetCustomEventResponse;
import com.baidubce.services.bcm.model.custom.GetCustomMetricRequest;
import com.baidubce.services.bcm.model.custom.GetCustomMetricResponse;
import com.baidubce.services.bcm.model.custom.ListCustomAlarmConfigRequest;
import com.baidubce.services.bcm.model.custom.ListCustomConfigResponse;
import com.baidubce.services.bcm.model.custom.ListNamespaceEventsRequest;
import com.baidubce.services.bcm.model.custom.ListNamespaceEventsResponse;
import com.baidubce.services.bcm.model.custom.CustomAlarmConfigRequest;
import com.baidubce.services.bcm.model.custom.CustomAlarmRule;
import com.baidubce.services.bcm.model.custom.ListNamespaceMetricsRequest;
import com.baidubce.services.bcm.model.custom.ListNamespaceMetricsResponse;
import com.baidubce.services.bcm.model.custom.ListNamespacesRequest;
import com.baidubce.services.bcm.model.custom.ListNamespacesResponse;
import com.baidubce.services.bcm.model.custom.NamespaceEventRequest;
import com.baidubce.services.bcm.model.custom.NamespaceMetricDimension;
import com.baidubce.services.bcm.model.custom.NamespaceMetricRequest;
import com.baidubce.services.bcm.model.custom.NamespaceRequest;
import com.baidubce.services.bcm.model.dashboard.DashboardBaseRequest;
import com.baidubce.services.bcm.model.dashboard.DashboardBaseResponse;
import com.baidubce.services.bcm.model.dashboard.DashboardBillboardDataResponse;
import com.baidubce.services.bcm.model.dashboard.DashboardCreateResponse;
import com.baidubce.services.bcm.model.dashboard.DashboardData;
import com.baidubce.services.bcm.model.dashboard.DashboardDataRequest;
import com.baidubce.services.bcm.model.dashboard.DashboardDimensionsRequest;
import com.baidubce.services.bcm.model.dashboard.DashboardMetric;
import com.baidubce.services.bcm.model.dashboard.DashboardMonitorObject;
import com.baidubce.services.bcm.model.dashboard.DashboardNamespace;
import com.baidubce.services.bcm.model.dashboard.DashboardReportDataResponse;
import com.baidubce.services.bcm.model.dashboard.DashboardResponse;
import com.baidubce.services.bcm.model.dashboard.DashboardTrendDataResponse;
import com.baidubce.services.bcm.model.dashboard.DashboardTrendSeniorDataResponse;
import com.baidubce.services.bcm.model.dashboard.DashboardWidgetConfigure;
import com.baidubce.services.bcm.model.dashboard.DashboardWidgetRequest;
import com.baidubce.services.bcm.model.dashboard.MetricDimensions;
import com.baidubce.services.bcm.model.dashboard.ScopeValue;
import com.baidubce.services.bcm.model.dashboard.Style;
import com.baidubce.services.bcm.model.dashboard.SubService;
import com.baidubce.services.bcm.model.dashboard.TimeRange;
import com.baidubce.services.bcm.model.action.ActionNotification;
import com.baidubce.services.bcm.model.action.CreateAndUpdateActionRequest;
import com.baidubce.services.bcm.model.action.DeleteActionRequest;
import com.baidubce.services.bcm.model.action.ListActionsRequest;
import com.baidubce.services.bcm.model.action.ListActionsResponse;
import com.baidubce.services.bcm.model.action.ListNotifyGroupsResponse;
import com.baidubce.services.bcm.model.action.ListNotifyPartiesResponse;
import com.baidubce.services.bcm.model.action.Member;
import com.baidubce.services.bcm.model.action.NotifyRequest;
import com.baidubce.services.bcm.model.alarm.ACAlarmType;
import com.baidubce.services.bcm.model.alarm.AlarmLevel;
import com.baidubce.services.bcm.model.application.ACMonitorObject;
import com.baidubce.services.bcm.model.application.ACMonitorObjectViewModel;
import com.baidubce.services.bcm.model.application.AppAlarmRule;
import com.baidubce.services.bcm.model.application.ApplicationAlarmConfig;
import com.baidubce.services.bcm.model.application.ApplicationMetric;
import com.baidubce.services.bcm.model.application.CreateOrUpdateAlarmConfigForApplicationRequest;
import com.baidubce.services.bcm.model.application.DeleteAlarmConfigForApplicationRequest;
import com.baidubce.services.bcm.model.application.GetAlarmConfigForApplicationRequest;
import com.baidubce.services.bcm.model.application.GetMetricDataForApplicationRequest;
import com.baidubce.services.bcm.model.application.GetMetricMetaForApplicationRequest;
import com.baidubce.services.bcm.model.application.ListAlarmConfigForApplicationRequest;
import com.baidubce.services.bcm.model.application.ListAlarmMetricsForApplicationRequest;
import com.baidubce.services.bcm.model.application.LogExtractRequest;
import com.baidubce.services.bcm.model.application.MetricDataForApplication;
import com.baidubce.services.bcm.model.application.MonitorObjectType;
import com.baidubce.services.bcm.model.event.BlockStatus;
import com.baidubce.services.bcm.model.event.CloudEventResponse;
import com.baidubce.services.bcm.model.event.EventDataRequest;
import com.baidubce.services.bcm.model.event.EventFilter;
import com.baidubce.services.bcm.model.event.EventLevel;
import com.baidubce.services.bcm.model.event.EventPolicy;
import com.baidubce.services.bcm.model.event.EventPolicyResponse;
import com.baidubce.services.bcm.model.event.EventResourceFilter;
import com.baidubce.services.bcm.model.event.PlatformEventResponse;
import com.baidubce.services.bcm.model.group.IGInstanceListResponse;
import com.baidubce.services.bcm.model.group.IGInstanceQuery;
import com.baidubce.services.bcm.model.group.InstanceGroup;
import com.baidubce.services.bcm.model.group.InstanceGroupBase;
import com.baidubce.services.bcm.model.group.InstanceGroupListResponse;
import com.baidubce.services.bcm.model.group.InstanceGroupQuery;
import com.baidubce.services.bcm.model.group.InstanceGroupResponse;
import com.baidubce.services.bcm.model.group.MergedGroup;
import com.baidubce.services.bcm.model.group.MonitorResource;
import com.baidubce.services.bcm.model.group.ViewType;
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
import com.baidubce.services.bcm.model.siteonce.HttpResponseWrapper;
import com.baidubce.services.bcm.model.siteonce.SiteOnceAgent;
import com.baidubce.services.bcm.model.siteonce.SiteOnceConfig;
import com.baidubce.services.bcm.model.siteonce.SiteOnceGroupTask;
import com.baidubce.services.bcm.model.siteonce.SiteOnceProtocol;
import com.baidubce.services.bcm.model.siteonce.SiteOnceRequest;
import com.baidubce.services.bcm.model.siteonce.SiteOnceTaskList;
import com.baidubce.services.bcm.model.siteonce.SiteOnceTaskRequest;
import com.baidubce.services.bcm.model.siteonce.SiteOnceTaskResponse;
import com.baidubce.util.DateUtils;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * test class for bcm service.
 */
public class BcmClientTest {

    private static final Logger logger = LoggerFactory.getLogger(BcmClientTest.class);
    private static final String userId = "a0d04d7c20******80155ff7b6752ce4";
    private static final String ak = "ak";
    private static final String sk = "sk";
    private BcmClient bcmClient;

    @Before
    public void setUp() {
        BcmClientConfiguration config = new BcmClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("bcm.bj.baidubce.com");
        bcmClient = new BcmClient(config);
    }

    private void printResult(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = BceServiceException.class)
    public void metricData() {
        MetricDataRequest request = new MetricDataRequest();
        request.withUserId(userId)
                .withScope("BCE_BCC")
                .withDimensions("InstanceId:i-Ap1rKtcx")
                .withMetricName("vCPUUsagePercent")
                .withStatistics(new Statistics[]{Statistics.average})
                .withStartTime("2020-12-01T07:15:06Z")
                .withEndTime("2020-12-01T08:15:00Z")
                .withPeriodInSecond(60);
        MetricDataResponse response = bcmClient.getMetricData(request);
        printResult("metricData", response.getDataPoints());
    }

    @Test(expected = BceServiceException.class)
    public void listMetricData() {
        ListMetricDataRequest request = new ListMetricDataRequest();
        request.withUserId(userId)
                .withScope("BCE_BCC")
                .withDimensions("InstanceId:i-Ap1rKtcx")
                .withMetricNames(new String[]{"CpuIdlePercent", "vCPUUsagePercent"})
                .withStatistics(new Statistics[]{Statistics.average})
                .withStartTime("2020-11-20T09:40:00Z")
                .withEndTime("2020-11-20T09:50:00Z")
                .withPeriodInSecond(60);
        ListMetricDataResponse response = bcmClient.getMetricData(request);
        printResult("listMetricData", response);
    }

    @Test(expected = BceServiceException.class)
    public void testPushCustomMonitorMetricData() {
        PushCustomMetricDataRequest request = PushCustomMetricDataRequest.builder()
                .userId(userId)
                .namespace("test_wang")
                .metricName("test_api_no_dimension")
                .value(10.0)
                .timestamp(DateUtils.formatAlternateIso8601Date(new Date(System.currentTimeMillis() - 10 * 60 * 1000)))
                .build();
        PushMetricDataResponse response = bcmClient.pushCustomMonitorMetricData(request);
        printResult("pushCustomMonitorMetricData", response);

        List<Dimension> dimensions = new ArrayList<Dimension>();
        dimensions.add(new Dimension().withName("dimension1").withValue("d1"));
        dimensions.add(new Dimension().withName("dimension2").withValue("d2"));
        StatisticValue value = StatisticValue.builder()
                .maximum(1.0).minimum(2.0).sum(3.0).sampleCount(1).average(4.0).build();
        request = PushCustomMetricDataRequest.builder()
                .userId(userId)
                .namespace("test_wang")
                .metricName("test_api_time")
                .dimensions(dimensions)
                .statisticValues(value)
                .timestamp(DateUtils.formatAlternateIso8601Date(new Date(System.currentTimeMillis() - 10 * 60 * 1000)))
                .build();
        response = bcmClient.pushCustomMonitorMetricData(request);
        printResult("pushCustomMonitorMetricData", response);
    }

    @Test(expected = BceServiceException.class)
    public void testCreateNamespace() {
        NamespaceRequest request = NamespaceRequest.builder()
                .userId(userId)
                .name("Test01")
                .namespaceAlias("test")
                .build();
        CustomMonitorResponse response = bcmClient.createNamespace(request);
        printResult("createNamespace", response);
    }

    @Test(expected = BceServiceException.class)
    public void testBatchDeleteNamespaces() {
        BatchDeleteNamespacesRequest request = BatchDeleteNamespacesRequest.builder()
                .userId(userId)
                .names(Collections.singletonList("Test01"))
                .build();
        CustomMonitorResponse response = bcmClient.batchDeleteNamespaces(request);
        printResult("batchDeleteNamespaces", response);
    }

    @Test(expected = BceServiceException.class)
    public void testUpdateNamespace() {
        NamespaceRequest request = NamespaceRequest.builder()
                .userId(userId)
                .name("Test01")
                .namespaceAlias("test01")
                .build();
        AbstractBceResponse response = bcmClient.updateNamespace(request);
        printResult("updateNamespace", response);
    }

    @Test(expected = BceServiceException.class)
    public void testListNamespaces() {
        ListNamespacesRequest request = ListNamespacesRequest.builder()
                .userId(userId)
                .build();
        ListNamespacesResponse response = bcmClient.listNamespaces(request);
        printResult("listNamespaces", response);

        request = ListNamespacesRequest.builder()
                .userId(userId)
                .name("test")
                .pageNo(1)
                .pageSize(10)
                .build();
        response = bcmClient.listNamespaces(request);
        printResult("listNamespaces", response);
    }

    @Test(expected = BceServiceException.class)
    public void testCreateNamespaceMetric() {
        NamespaceMetricRequest request = NamespaceMetricRequest.builder()
                .userId(userId)
                .namespace("Test01")
                .metricName("TestMetric01")
                .metricAlias("test")
                .unit("sec")
                .cycle(60)
                .build();
        CustomMonitorResponse response = bcmClient.createNamespaceMetric(request);
        printResult("createNamespaceMetric", response);

        NamespaceMetricDimension dimension = new NamespaceMetricDimension();
        dimension.setName("dimension01");
        dimension.setAlias("dimension01");
        dimension.setOrder(1);
        NamespaceMetricRequest requestWithDimension = NamespaceMetricRequest.builder()
                .userId(userId)
                .namespace("Test01")
                .metricName("TestMetric02")
                .metricAlias("test02")
                .unit("sec")
                .cycle(60)
                .dimensions(Collections.singletonList(dimension))
                .build();
        response = bcmClient.createNamespaceMetric(requestWithDimension);
        printResult("createNamespaceMetric", response);
    }

    @Test(expected = BceServiceException.class)
    public void testBatchDeleteNamespaceMetrics() {
        BatchDeleteNamespaceMetricsRequest request = BatchDeleteNamespaceMetricsRequest.builder()
                .userId(userId)
                .namespace("Test01")
                .ids(Collections.singletonList(1714L))
                .build();
        CustomMonitorResponse response = bcmClient.batchDeleteNamespaceMetrics(request);
        printResult("batchDeleteNamespaceMetrics", response);
    }

    @Test(expected = BceServiceException.class)
    public void testUpdateNamespaceMetric() {
        NamespaceMetricRequest request = NamespaceMetricRequest.builder()
                .userId(userId)
                .namespace("Test01")
                .metricName("TestMetric01")
                .metricAlias("test01")
                .unit("sec")
                .cycle(60)
                .build();
        CustomMonitorResponse response = bcmClient.updateNamespaceMetric(request);
        printResult("updateNamespaceMetric", response);
    }

    @Test(expected = BceServiceException.class)
    public void testListNamespaceMetrics() {
        ListNamespaceMetricsRequest request = ListNamespaceMetricsRequest.builder()
                .userId(userId)
                .namespace("Test01")
                .build();
        ListNamespaceMetricsResponse response = bcmClient.listNamespaceMetrics(request);
        printResult("listNamespaceMetrics", response);

        request = ListNamespaceMetricsRequest.builder()
                .userId(userId)
                .namespace("Test01")
                .metricName("test")
                .metricAlias("test")
                .pageNo(1)
                .pageSize(10)
                .build();
        response = bcmClient.listNamespaceMetrics(request);
        printResult("listNamespaceMetrics", response);
    }

    @Test(expected = BceServiceException.class)
    public void testGetCustomMetric() {
        GetCustomMetricRequest request = GetCustomMetricRequest.builder()
                .userId(userId)
                .namespace("Test01")
                .metricName("TestMetric01")
                .build();
        GetCustomMetricResponse response = bcmClient.getCustomMetric(request);
        printResult("getCustomMetric", response);
    }

    @Test(expected = BceServiceException.class)
    public void testCreateNamespaceEvent() {
        NamespaceEventRequest request = NamespaceEventRequest.builder()
                .userId(userId)
                .namespace("Test01")
                .eventName("TestEvent01")
                .eventNameAlias("test")
                .eventLevel(EventLevelEnum.WARNING)
                .comment("test")
                .build();
        CustomMonitorResponse response = bcmClient.createNamespaceEvent(request);
        printResult("createNamespaceEvent", response);
    }

    @Test(expected = BceServiceException.class)
    public void testBatchDeleteNamespaceEvents() {
        BatchDeleteNamespaceEventsRequest request = BatchDeleteNamespaceEventsRequest.builder()
                .userId(userId)
                .namespace("Test01")
                .names(Collections.singletonList("TestEvent01"))
                .build();
        CustomMonitorResponse response = bcmClient.batchDeleteNamespaceEvents(request);
        printResult("batchDeleteNamespaceEvents", response);
    }

    @Test(expected = BceServiceException.class)
    public void testUpdateNamespaceEvent() {
        NamespaceEventRequest request = NamespaceEventRequest.builder()
                .userId(userId)
                .namespace("Test01")
                .eventName("TestEvent01")
                .eventNameAlias("test01")
                .eventLevel(EventLevelEnum.WARNING)
                .comment("test01")
                .build();
        CustomMonitorResponse response = bcmClient.updateNamespaceEvent(request);
        printResult("updateNamespaceEvent", response);
    }

    @Test(expected = BceServiceException.class)
    public void testListNamespaceEvents() {
        ListNamespaceEventsRequest request = ListNamespaceEventsRequest.builder()
                .userId(userId)
                .namespace("Test01")
                .build();
        ListNamespaceEventsResponse response = bcmClient.listNamespaceEvents(request);
        printResult("listNamespaceEvents", response);

        request = ListNamespaceEventsRequest.builder()
                .userId(userId)
                .namespace("Test01")
                .name("test")
                .eventLevel(EventLevelEnum.WARNING)
                .pageNo(1)
                .pageSize(10)
                .build();
        response = bcmClient.listNamespaceEvents(request);
        printResult("listNamespaceEvents", response);
    }

    @Test(expected = BceServiceException.class)
    public void testGetCustomEvent() {
        GetCustomEventRequest request = GetCustomEventRequest.builder()
                .userId(userId)
                .namespace("Test01")
                .eventName("TestEvent01")
                .build();
        GetCustomEventResponse response = bcmClient.getCustomEvent(request);
        printResult("getCustomEvent", response);
    }

    @Test(expected = BceServiceException.class)
    public void testCreateDashboard() {
        String config = "{\"tabs\":[{\"dimensions\":[],\"metric\":[],\"name\":\"\",\"namespace\":[],\"widgets\":[[{\"name\":\"_54382_54383\"},{\"name\":\"_54382_54384\"},{\"name\":\"_54382_54385\"}],[{\"name\":\"_54382_54386\"}]]}]}";
        DashboardBaseRequest request = DashboardBaseRequest.builder()
                .userId(userId)
                .configure(config)
                .title("yyy-java2")
                .type("common")
                .build();
        DashboardCreateResponse response = bcmClient.createDashboard(request);
        printResult("createDashboard", response);
    }

    @Test(expected = BceServiceException.class)
    public void testGetDashboard() {
        DashboardBaseRequest request = DashboardBaseRequest.builder()
                .userId(userId)
                .dashboardName("_54507")
                .build();
        DashboardCreateResponse response = bcmClient.getDashboard(request);
        printResult("getDashboard", response);
    }

    @Test(expected = BceServiceException.class)
    public void testDeleteDashboards() {
        DashboardBaseRequest request = DashboardBaseRequest.builder()
                .userId(userId)
                .dashboardName("_54508")
                .build();
        DashboardResponse response = bcmClient.deleteDashboard(request);
        printResult("deleteDashboards", response);
    }

    @Test(expected = BceServiceException.class)
    public void testUpdateDashboards() {
        String config = "{\"tabs\":[{\"dimensions\":[],\"metric\":[],\"name\":\"\",\"namespace\":[],\"widgets\":[[{\"name\":\"_54382_54383\"},{\"name\":\"_54382_54384\"},{\"name\":\"_54382_54385\"}],[{\"name\":\"_54382_54386\"}]]}]}";
        DashboardBaseRequest request = DashboardBaseRequest.builder()
                .userId(userId)
                .dashboardName("_54507")
                .title("yyy-java-update")
                .configure(config)
                .build();
        DashboardResponse response = bcmClient.updateDashboard(request);
        printResult("updateDashboards", response);
    }

    @Test(expected = BceServiceException.class)
    public void testDuplicateDashboards() {
        DashboardBaseRequest request = DashboardBaseRequest.builder()
                .userId(userId)
                .dashboardName("_54507")
                .title("yyy-java-dup")
                .build();
        DashboardResponse response = bcmClient.duplicateDashboard(request);
        printResult("duplicateDashboard", response);
    }

    @Test(expected = BceServiceException.class)
    public void createWidget() {
        DashboardBaseRequest request = DashboardBaseRequest.builder()
                .userId(userId)
                .dashboardName("_54507")
                .build();
        DashboardResponse response = bcmClient.createDashboardWidget(request);
        printResult("createWidget", response);
    }

    @Test(expected = BceServiceException.class)
    public void getWidget() {
        DashboardWidgetRequest request = DashboardWidgetRequest.builder()
                .userId(userId)
                .dashboardName("_54507")
                .widgetName("_54507_54522")
                .build();
        DashboardBaseResponse response = bcmClient.getDashboardWidget(request);
        printResult("getWidget", response);
    }

    @Test(expected = BceServiceException.class)
    public void deleteWidget() {
        DashboardWidgetRequest request = DashboardWidgetRequest.builder()
                .userId(userId)
                .dashboardName("_54507")
                .widgetName("_54382_54385")
                .build();
        DashboardBaseResponse response = bcmClient.deleteDashboardWidget(request);
        printResult("getWidget", response);
    }

    @Test(expected = BceServiceException.class)
    public void duplicateWidget() {
        DashboardWidgetRequest request = DashboardWidgetRequest.builder()
                .userId(userId)
                .dashboardName("_54507")
                .widgetName("_54507_54522")
                .build();
        DashboardBaseResponse response = bcmClient.duplicateDashboardWidget(request);
        printResult("duplicateWidget", response);
    }

    @Test(expected = BceServiceException.class)
    public void updateWidget() {
        DashboardWidgetRequest req = new DashboardWidgetRequest();
        req.setDashboardName("_54507");
        req.setUserId("453bf9588c9e488f9ba2c984129090dc");
        req.setWidgetName("_54382_54386");
        req.setTitle("bccNew");
        req.setType("trend");

        DashboardWidgetConfigure configure = new DashboardWidgetConfigure();
        configure.setTitle("bccNew");
        configure.setMonitorType("scope");

        TimeRange timeRange = new TimeRange();
        timeRange.setTimeType("dashboard");
        timeRange.setUnit("minutes");
        timeRange.setNumber(1);
        timeRange.setRelative("today()");
        configure.setTimeRange(timeRange);

        Style style = new Style();
        style.setDisplayType("line");
        style.setNullPointMode("zero");
        style.setThreshold(0);
        style.setDecimals(2);
        style.setEdit(true);
        style.setUnit("%");
        configure.setStyle(style);

        List<DashboardData> dataList = new ArrayList<DashboardData>();
        DashboardData data = new DashboardData();

        List<DashboardMetric> metrics = new ArrayList<DashboardMetric>();
        DashboardMetric metric = new DashboardMetric();
        metric.setName("CpuIdlePercent");
        metric.setUnit("%");
        metric.setAlias("CPU空闲率");
        metric.setStatistics("avg");
        metrics.add(metric);
        data.setMetric(metrics);

        List<DashboardMonitorObject> monitorObjects = new ArrayList<DashboardMonitorObject>();
        DashboardMonitorObject monitorObject = new DashboardMonitorObject();
        monitorObject.setInstanceName("zmq-as0001");
        monitorObject.setId("i-yq8qU5Qf");
        monitorObjects.add(monitorObject);
        data.setMonitorObject(monitorObjects);

        data.setScope("BCE_BCC");
        data.setSubService("linux");
        data.setRegion("bj");

        ScopeValue scopeValue = new ScopeValue();
        scopeValue.setName("BCC");
        scopeValue.setValue("BCE_BCC");
        scopeValue.setHasChildren(false);
        data.setScopeValue(scopeValue);

        data.setResourceType("Instance");
        data.setMonitorType("scope");

        List<DashboardNamespace> namespaces = new ArrayList<DashboardNamespace>();
        DashboardNamespace namespace = new DashboardNamespace();
        namespace.setNamespaceType("instance");
        namespace.setName("i-yq8qU5Qf___bj.BCE_BCC.453bf9588c9e488f9ba2c984129090dc");
        namespace.setInstanceName("zmq-as0001");
        namespace.setRegion("bj");
        namespace.setBcmService("BCE_BCC");

        List<SubService> subServices = new ArrayList<SubService>();
        SubService subService = new SubService();
        subService.setName("serviceType");
        subService.setValue("linux");
        subServices.add(subService);
        namespace.setSubService(subServices);

        namespaces.add(namespace);
        data.setNamespace(namespaces);

        data.setProduct("453bf9588c9e488f9ba2c984129090dc");

        dataList.add(data);
        configure.setData(dataList);
        req.setConfigure(configure);
        DashboardBaseResponse response = bcmClient.updateDashboardWidget(req);
        printResult("updateWidget", response);
    }

    private DashboardDataRequest getDashboardDataMultiRequest() {
        DashboardDataRequest req = new DashboardDataRequest();
        req.setTime("2023-12-08 09:10:59|2023-12-08 10:10:59");

        List<DashboardData> dataList = new ArrayList<DashboardData>();
        DashboardData data = new DashboardData();

        List<DashboardMetric> metrics = new ArrayList<DashboardMetric>();
        DashboardMetric metric = new DashboardMetric();
        metric.setName("vNicInBytes");
        metric.setUnit("Bytes");
        metric.setAlias("网卡输入流量");
        metric.setStatistics("avg");
        metric.setCycle(60);

        List<String> dimensions = new ArrayList<String>();
        dimensions.add("eth1");
        dimensions.add("eth2");
        metric.setDimensions(dimensions);

        List<MetricDimensions> metricDimensionsList = new ArrayList<MetricDimensions>();
        MetricDimensions metricDimensions = new MetricDimensions();
        metricDimensions.setName("nicName");

        List<String> values = new ArrayList<String>();
        values.add("eth1");
        values.add("eth2");
        metricDimensions.setValues(values);

        metricDimensionsList.add(metricDimensions);
        metric.setMetricDimensions(metricDimensionsList);

        metrics.add(metric);
        data.setMetric(metrics);

        List<DashboardMonitorObject> monitorObjects = new ArrayList<DashboardMonitorObject>();
        DashboardMonitorObject monitorObject = new DashboardMonitorObject();
        monitorObject.setInstanceName("prod.nmp.nn.yd1");
        monitorObject.setId("41b372b8-3acc-423c-a6b0-af5c69fd1c41");
        monitorObjects.add(monitorObject);
        data.setMonitorObject(monitorObjects);

        data.setScope("BCE_BEC");
        data.setSubService("linux");
        data.setRegion("bj");

        ScopeValue scopeValue = new ScopeValue();
        scopeValue.setName("BCC");
        scopeValue.setValue("BCE_BCC");
        scopeValue.setHasChildren(false);
        data.setScopeValue(scopeValue);

        data.setMonitorType("scope");

        List<DashboardNamespace> namespaces = new ArrayList<DashboardNamespace>();
        DashboardNamespace namespace = new DashboardNamespace();
        namespace.setNamespaceType("app");
        namespace.setName("41b372b8-3acc-423c-a6b0-af5c69fd1c41___bj.BCE_BEC.a0d04d7c20214*********f7b6752ce4");
        namespace.setInstanceName("prod.nmp.nn.yd1");
        namespace.setRegion("bj");
        namespace.setBcmService("BCE_BEC");

        List<SubService> subServices = new ArrayList<SubService>();
        SubService subService = new SubService();
        subService.setName("serviceType");
        subService.setValue("linux");
        subServices.add(subService);
        namespace.setSubService(subServices);

        namespaces.add(namespace);
        data.setNamespace(namespaces);

        data.setProduct("a0d04d7c20214*********f7b6752ce4");

        dataList.add(data);
        req.setData(dataList);
        return req;
    }

    private DashboardDataRequest getDashboardDataSingleRequest() {
        DashboardDataRequest req = new DashboardDataRequest();
        req.setTime("2023-12-08 09:10:59|2023-12-08 10:10:59");

        DashboardData data = new DashboardData();
        List<DashboardMetric> metrics = new ArrayList<DashboardMetric>();
        DashboardMetric metric = new DashboardMetric();
        metric.setName("CpuIdlePercent");
        metric.setUnit("%");
        metric.setAlias("CPU空闲率");
        metric.setStatistics("avg");
        metrics.add(metric);
        data.setMetric(metrics);

        List<DashboardMonitorObject> monitorObjects = new ArrayList<DashboardMonitorObject>();
        DashboardMonitorObject monitorObject = new DashboardMonitorObject();
        monitorObject.setInstanceName("instance-xcy9049y ");
        monitorObject.setId("i-isvkUW76");
        monitorObjects.add(monitorObject);
        data.setMonitorObject(monitorObjects);

        data.setScope("BCE_BCC");
        data.setSubService("linux");
        data.setRegion("bj");

        ScopeValue scopeValue = new ScopeValue();
        scopeValue.setName("BCC");
        scopeValue.setValue("BCE_BCC");
        scopeValue.setHasChildren(false);
        data.setScopeValue(scopeValue);

        data.setMonitorType("scope");

        List<DashboardNamespace> namespaces = new ArrayList<DashboardNamespace>();
        DashboardNamespace namespace = new DashboardNamespace();
        namespace.setNamespaceType("app");
        namespace.setName("i-isvkUW76___bj.BCE_BCC.a0d04d7c20214*********f7b6752ce4");
        namespace.setInstanceName("instance-xcy9049y ");
        namespace.setRegion("bj");
        namespace.setBcmService("BCE_BCC");

        List<SubService> subServices = new ArrayList<SubService>();
        SubService subService = new SubService();
        subService.setName("serviceType");
        subService.setValue("linux");
        subServices.add(subService);

        namespace.setSubService(subServices);
        namespaces.add(namespace);
        data.setNamespace(namespaces);

        data.setProduct("a0d04d7c20214*********f7b6752ce4");

        List<DashboardData> dataList = new ArrayList<DashboardData>();
        dataList.add(data);
        req.setData(dataList);
        return req;
    }

    @Test(expected = BceServiceException.class)
    public void getDashboardReportSingleData() {
        DashboardDataRequest req = getDashboardDataSingleRequest();
        DashboardReportDataResponse response = bcmClient.getDashboardReportData(req);
        printResult("getDashboardReportSingleData ", response);
    }

    @Test(expected = BceServiceException.class)
    public void getDashboardReportMultiData() {
        DashboardDataRequest req = getDashboardDataMultiRequest();
        DashboardReportDataResponse response = bcmClient.getDashboardReportData(req);
        printResult("getDashboardReportMultiData ", response);
    }

    @Test(expected = BceServiceException.class)
    public void getDashboardTrendSingleData() {
        DashboardDataRequest req = getDashboardDataSingleRequest();
        DashboardTrendDataResponse response = bcmClient.getDashboardTrendData(req);
        printResult("getDashboardReportSingleData ", response);
    }

    @Test(expected = BceServiceException.class)
    public void getDashboardTrendMultiData() {
        DashboardDataRequest req = getDashboardDataMultiRequest();
        DashboardTrendDataResponse response = bcmClient.getDashboardTrendData(req);
        printResult("getDashboardTrendMultiData ", response);
    }

    @Test(expected = BceServiceException.class)
    public void getDashboardGaugeChartSingleData() {
        DashboardDataRequest req = getDashboardDataSingleRequest();
        DashboardBillboardDataResponse response = bcmClient.getDashboardGaugeChartData(req);
        printResult("getDashboardGaugeChartSingleData ", response);
    }

    @Test(expected = BceServiceException.class)
    public void getDashboardGaugeChartMultiData() {
        DashboardDataRequest req = getDashboardDataMultiRequest();
        DashboardBillboardDataResponse response = bcmClient.getDashboardGaugeChartData(req);
        printResult("getDashboardGaugeChartData ", response);
    }

    @Test(expected = BceServiceException.class)
    public void getDashboardBillboardSingleData() {
        DashboardDataRequest req = getDashboardDataSingleRequest();
        DashboardBillboardDataResponse response = bcmClient.getDashboardBillboardData(req);
        printResult("getDashboardBillboardData ", response);
    }

    @Test(expected = BceServiceException.class)
    public void getDashboardBillboardMultiData() {
        DashboardDataRequest req = getDashboardDataMultiRequest();
        DashboardBillboardDataResponse response = bcmClient.getDashboardBillboardData(req);
        printResult("getDashboardBillboardData ", response);
    }

    @Test(expected = BceServiceException.class)
    public void getDashboardTrendSeniorData() {
        DashboardDataRequest req = getDashboardDataSingleRequest();
        DashboardTrendSeniorDataResponse response = bcmClient.getDashboardTrendSeniorData(req);
        printResult("getDashboardTrendSeniorData ", response);
    }

    @Test(expected = BceServiceException.class)
    public void getDashboardDimensions() {

        DashboardDimensionsRequest req = new DashboardDimensionsRequest();
        req.setUserId(userId);
        req.setDimensions("nicName");
        req.setRegion("bj");
        req.setService("BCE_BEC");
        req.setShowId("7744b3f3-ec04-459a-b3ae-4379111534ff");
        req.setMetricName("vNicInBytes");
        printResult("req", req);

        Map<String, List<String>> response = bcmClient.getDashboardDimensions(req);
        printResult("getDashboardDimensions ", response);

    }

    @Test
    public void testListNotifyGroups() {
        NotifyRequest request = NotifyRequest.builder()
                .pageNo(1)
                .pageSize(2)
                .build();
        ListNotifyGroupsResponse response = bcmClient.listNotifyGroups(request);
        printResult("testListNotifyGroups", response);
    }

    @Test
    public void testListPartyGroups() {
        NotifyRequest request = NotifyRequest.builder()
                .pageNo(1)
                .pageSize(2)
                .build();
        ListNotifyPartiesResponse response = bcmClient.listNotifyParties(request);
        printResult("testListPartyGroups", response);
    }

    @Test
    public void testCreateAction() {
        CreateAndUpdateActionRequest request = new CreateAndUpdateActionRequest();
        request.setUserId(userId);
        request.setAlias("Test_wjr");
        request.setMembers(Collections.singletonList(new Member("notifyParty",
                "56c9e0e2138c4f", "lzs")));
        request.setNotifications(Collections.singletonList(new ActionNotification("", "EMAIL")));
        bcmClient.createAction(request);
    }

    @Test
    public void testDeleteAction() {
        DeleteActionRequest request = new DeleteActionRequest();
        request.setUserId(userId);
        request.setName("86cb2e2b-7a1c-41f3-9e8d-ed71e5186623");
        bcmClient.deleteAction(request);
    }

    @Test
    public void testUpdateAction() {
        CreateAndUpdateActionRequest request = new CreateAndUpdateActionRequest();
        request.setUserId(userId);
        request.setAlias("Test_updated");
        request.setName("2bcb223c-57b2-4ebd-ba1a-abe530e3fa30");
        request.setMembers(Collections.singletonList(new Member("notifyParty",
                "56c9e0e2138c4f", "lzs")));
        request.setNotifications(Collections.singletonList(new ActionNotification("", "EMAIL")));
        bcmClient.updateAction(request);
    }

    @Test
    public void testListActions() {
        ListActionsRequest request = new ListActionsRequest();
        request.setUserId(userId);
        request.setPageNo(1);
        request.setPageSize(5);
        request.setName("name");
        ListActionsResponse response = bcmClient.listActions(request);
        printResult("testListActions", response);
    }

    @Test
    public void testLogExtract() {
        LogExtractRequest request = new LogExtractRequest();
        request.setUserId(userId);
        request.setLogExample("10.157.16.207 - - [09/Apr/2020:20:45:33 +0800] \"POST /v1/dashboard/metric/gaugechart " +
                "HTTP/1.1\" 200 117 109ms\n10.157.16.207 - - [09/Apr/2020:20:45:33 +0800] \"GET /v1/dashboard/metric" +
                "/report HTTP/1.1\" 200 117 19ms");
        request.setExtractRule("800] \"(?<method>(GET|POST|PUT|DELETE)) .*/v1/dashboard/metric/(?<widget>(" +
                "cycle|trend|report|billboard|gaugechart)) HTTP/1.1\".* (?<resTime>[0-9]+)ms");
        List<LogExtractResult> resp = bcmClient.logExtract(request);
        printResult("testLogExtract", resp);
    }


    @Test
    public void testGetMetricMetaForApplication() {
        GetMetricMetaForApplicationRequest request = new GetMetricMetaForApplicationRequest();
        request.setUserId(userId);
        request.setAppName("test14");
        request.setTaskName("79c35af26c4346ab844bcbcdde2875c3");
        request.setMetricName("log.responseTime");
        request.setInstances(Collections.singletonList("0.test14"));
        Map<String, List<String>> resp = bcmClient.getMetricMetaForApplication(request);
        printResult("testGetMetricMetaForApplication", resp);
    }

    @Test
    public void testGetMetricDataForApplication() {
        GetMetricDataForApplicationRequest request = new GetMetricDataForApplicationRequest();
        request.setUserId(userId);
        request.setAppName("zmq-log-1115");
        request.setTaskName("6d3f07e6684d47b69ca9600f7fcbbb12");
        request.setMetricName("exec.6d3f07e6684d47b69ca9600f7fcbbb12.metric1");
        request.setStartTime("2023-12-05T09:54:15Z");
        request.setEndTime("2023-12-05T10:04:15Z");
        request.setInstances(Collections.singletonList("0.zmq-log-1115"));
        request.setCycle(240);
        List<MetricDataForApplication> resp = bcmClient.getMetricDataForApplication(request);
        printResult("testGetMetricDataForApplication", resp);
    }

    @Test
    public void testCreateAlarmConfigForApplication() {
        CreateOrUpdateAlarmConfigForApplicationRequest request = CreateOrUpdateAlarmConfigForApplicationRequest.builder()
                .alarmDescription("comment").appName("zmq-log-1115").alarmName("test_ut").type(ACAlarmType.INSTANCE)
                .srcName("ab3b543f41974e26ab984d94fc7b9b92").srcType("LOG").userId("453bf9588c9e488f9ba2c984129090dc")
                .insufficientCycle(0).monitorObjectType(MonitorObjectType.APP).level(AlarmLevel.MAJOR)
                .actionEnabled(true).repeatAlarmCycle(300).maxRepeatCount(1).build();
        request.setIncidentActions(Collections.singleton("624c99b5-5436-478c-8326-0efc8163c7d5"));
        ACMonitorObject monitorObject = new ACMonitorObject(4030L, null, MonitorObjectType.APP, null);
        ACMonitorObjectViewModel acMonitorObjectViewModel = new ACMonitorObjectViewModel();
        acMonitorObjectViewModel.setMonitorObjectName("ab3b543f41974e26ab984d94fc7b9b92");
        monitorObject.setMonitorObjectView(Collections.singleton(acMonitorObjectViewModel));
        request.setMonitorObject(monitorObject);
        AppAlarmRule rule = new AppAlarmRule();
        rule.setMetric("log.ab3b543f41974e26ab984d94fc7b9b92.log_metric2");
        rule.setMetricAlias("log_metric2");
        rule.setCycle(60);
        rule.setStatistics(Statistics.maximum);
        rule.setThreshold(5.0);
        rule.setComparisonOperator(">");
        rule.setCount(1);
        rule.setFunction("THRESHOLD");
        rule.setSequence(0);
        rule.setMetricDimensions(new ArrayList<Dimension>());
        request.setRules(Collections.singletonList(Collections.singletonList(rule)));
        ApplicationAlarmConfig resp = bcmClient.createAlarmConfigForApplication(request);
        printResult("testCreateAlarmConfigForApplication", resp);
    }

    @Test
    public void testUpdateAlarmConfigForApplication() {
        CreateOrUpdateAlarmConfigForApplicationRequest request = CreateOrUpdateAlarmConfigForApplicationRequest.builder()
                .alarmDescription("comment").appName("zmq-log-1115").alarmName("test_ut").type(ACAlarmType.INSTANCE)
                .srcName("ab3b543f41974e26ab984d94fc7b9b92").srcType("LOG").userId("453bf9588c9e488f9ba2c984129090dc")
                .insufficientCycle(0).monitorObjectType(MonitorObjectType.APP).level(AlarmLevel.MAJOR)
                .actionEnabled(true).repeatAlarmCycle(300).maxRepeatCount(1).build();
        request.setIncidentActions(Collections.singleton("624c99b5-5436-478c-8326-0efc8163c7d5"));
        ACMonitorObject monitorObject = new ACMonitorObject(4030L, null, MonitorObjectType.APP, null);
        ACMonitorObjectViewModel acMonitorObjectViewModel = new ACMonitorObjectViewModel();
        acMonitorObjectViewModel.setMonitorObjectName("ab3b543f41974e26ab984d94fc7b9b92");
        monitorObject.setMonitorObjectView(Collections.singleton(acMonitorObjectViewModel));
        request.setMonitorObject(monitorObject);
        AppAlarmRule rule = new AppAlarmRule();
        rule.setMetric("log.ab3b543f41974e26ab984d94fc7b9b92.log_metric2");
        rule.setMetricAlias("log_metric2");
        rule.setCycle(60);
        rule.setStatistics(Statistics.maximum);
        rule.setThreshold(5.0);
        rule.setComparisonOperator(">");
        rule.setCount(1);
        rule.setFunction("THRESHOLD");
        rule.setSequence(0);
        rule.setMetricDimensions(new ArrayList<Dimension>());
        request.setRules(Collections.singletonList(Collections.singletonList(rule)));
        ApplicationAlarmConfig resp = bcmClient.updateAlarmConfigForApplication(request);
        printResult("testUpdateAlarmConfigForApplication", resp);
    }

    @Test
    public void testListAlarmConfigForApplication() {
        ListAlarmConfigForApplicationRequest request = new ListAlarmConfigForApplicationRequest();
        request.setUserId(userId);
        request.setPageNo(1);
        Page<ApplicationAlarmConfig> resp = bcmClient.listAlarmConfigForApplication(request);
        printResult("testListAlarmConfigForApplication", resp);
    }

    @Test
    public void testDeleteAlarmConfigForApplication() {
        DeleteAlarmConfigForApplicationRequest request = new DeleteAlarmConfigForApplicationRequest();
        request.setUserId(userId);
        request.setAppName("uuu");
        request.setAlarmName("dsd");
        bcmClient.deleteAlarmConfigForApplication(request);
    }

    @Test
    public void testGetAlarmConfigForApplication() {
        GetAlarmConfigForApplicationRequest request = new GetAlarmConfigForApplicationRequest();
        request.setUserId(userId);
        request.setAlarmName("inst-test");
        request.setAppName("test_ymd_app_0918");
        ApplicationAlarmConfig resp = bcmClient.getAlarmConfigForApplication(request);
        printResult("testGetAlarmConfigForApplication", resp);
    }

    @Test
    public void testListAlarmMetricsForApplication() {
        ListAlarmMetricsForApplicationRequest request = new ListAlarmMetricsForApplicationRequest();
        request.setUserId(userId);
        request.setAppName("test_ymd_app_0918");
        request.setTaskName("46e78b2831394f738429f88265c5b25f");
        request.setSearchName("test_name");
        List<ApplicationMetric> resp = bcmClient.listAlarmMetricsForApplication(request);
        printResult("testListAlarmMetricsForApplication", resp);
    }

    @Test
    public void testCreateAlarmConfig() {
        MonitorObject monitorObject = new MonitorObject();
        monitorObject.setType(MonitorObjectType.INSTANCE);
        monitorObject.setNames(Arrays.asList("InstanceId:i-mPkY5Z**"));
        monitorObject.setTypeName("Instance");
        AlarmRule rule = new AlarmRule();
        rule.setIndex(1L);
        rule.setMetric("CPUUsagePercent");
        rule.setPeriodInSecond(60L);
        rule.setStatistics("average");
        rule.setThreshold("12345");
        rule.setComparisonOperator(">");
        rule.setEvaluationPeriodCount(1);
        List<List<AlarmRule>> rules = Collections.singletonList(Collections.singletonList(rule));
        CreateOrUpdateAlarmConfigRequest request = CreateOrUpdateAlarmConfigRequest.builder()
                .aliasName("test_alarm_policy_03")
                .level(AlarmLevel.CRITICAL)
                .monitorObject(monitorObject)
                .alarmActions(Collections.singleton("d242711b-****-****-****-b8ac175f8e7d"))
                .region("bj")
                .scope("BCE_BCC")
                .userId(userId)
                .rules(rules)
                .srcType("INSTANCE")
                .alarmDescription("这是一个测试的报警策略")
                .build();
        bcmClient.createAlarmConfig(request);
    }

    @Test
    public void testUpdateAlarmConfig() {
        MonitorObject monitorObject = new MonitorObject();
        monitorObject.setType(MonitorObjectType.INSTANCE);
        monitorObject.setNames(Arrays.asList("InstanceId:i-mPkY5Z**"));
        monitorObject.setTypeName("Instance");
        AlarmRule rule = new AlarmRule();
        rule.setIndex(1L);
        rule.setMetric("CPUUsagePercent");
        rule.setPeriodInSecond(60L);
        rule.setStatistics("average");
        rule.setThreshold("12345");
        rule.setComparisonOperator(">");
        rule.setEvaluationPeriodCount(1);
        List<List<AlarmRule>> rules = Collections.singletonList(Collections.singletonList(rule));
        CreateOrUpdateAlarmConfigRequest request = CreateOrUpdateAlarmConfigRequest.builder()
                .alarmName("fc2ba6********************30df46")
                .aliasName("test_alarm_policy_04")
                .level(AlarmLevel.CRITICAL)
                .monitorObject(monitorObject)
                .alarmActions(Collections.singleton("d242711b-****-****-****-b8ac175f8e7d"))
                .region("bj")
                .scope("BCE_BCC")
                .userId(userId)
                .rules(rules)
                .srcType("INSTANCE")
                .alarmDescription("这是一个测试的报警策略")
                .build();
        bcmClient.updateAlarmConfig(request);
    }

    @Test
    public void testDeleteAlarmConfig() {
        CommonAlarmConfigRequest request = CommonAlarmConfigRequest.builder()
                .userId(userId)
                .scope("BCE_BCC")
                .alarmName("fc2ba6********************30df46")
                .build();
        bcmClient.deleteAlarmConfig(request);
    }

    @Test
    public void testBlockAlarmConfig() {
        CommonAlarmConfigRequest request = CommonAlarmConfigRequest.builder()
                .userId(userId)
                .scope("BCE_BCC")
                .alarmName("fc2ba6********************30df46")
                .build();
        bcmClient.blockAlarmConfig(request);
    }

    @Test
    public void testUnblockAlarmConfig() {
        CommonAlarmConfigRequest request = CommonAlarmConfigRequest.builder()
                .userId(userId)
                .scope("BCE_BCC")
                .alarmName("fc2ba6********************30df46")
                .build();
        bcmClient.unblockAlarmConfig(request);
    }

    @Test
    public void testGetAlarmConfigDetail() {
        CommonAlarmConfigRequest request = CommonAlarmConfigRequest.builder()
                .userId(userId)
                .scope("BCE_BCC")
                .alarmName("fc2ba6********************30df46")
                .build();
        AlarmConfig config = bcmClient.getAlarmConfigDetail(request);
        Assert.assertEquals("fc2ba6********************30df46", config.getAlarmName());
    }

    @Test
    public void testListSingleInstanceAlarmConfigs() {
        ListSingleInstanceAlarmConfigsRequest request = ListSingleInstanceAlarmConfigsRequest.builder()
                .userId(userId)
                .scope("BCE_BCC")
                .pageNo(1)
                .pageSize(10)
                .build();
        Page<AlarmConfig> result = bcmClient.listSingleInstanceAlarmConfigs(request);
        Assert.assertTrue(result.getTotalCount() > 0);
    }

    @Test
    public void testListAlarmMetrics() {
        ListAlarmMetricsRequest request = ListAlarmMetricsRequest.builder()
                .userId(userId)
                .scope("BCE_BCC")
                .region("bj")
                .build();
        List<AlarmMetric> result = bcmClient.listAlarmMetrics(request);
        Assert.assertTrue(result.size() > 0);
    }

    @Test
    public void testCreateAlarmPolicyV2() {
        TargetInstance instance = new TargetInstance();
        instance.setRegion("bj");
        instance.setIdentifiers(Collections.singletonList(new CommonKV("InstanceId", "i-mPkY5Z**")));
        AlarmConfigPolicyRuleV2 rule = new AlarmConfigPolicyRuleV2();
        rule.setMetricName("CPUUsagePercent");
        rule.setOperator(">");
        rule.setStatistics("average");
        rule.setThreshold(12345.0);
        AlarmConfigPolicyV2 policy = new AlarmConfigPolicyV2();
        policy.setAlarmPendingPeriodCount(1);
        policy.setRules(Collections.singletonList(rule));
        AlarmConfigActionV2 action = new AlarmConfigActionV2();
        action.setName("test_yangmoda");
        CreateOrUpdateAlarmConfigV2Request request = CreateOrUpdateAlarmConfigV2Request.builder()
                .aliasName("test_alarm_policy_012")
                .resourceType("Instance")
                .alarmLevel(AlarmLevel.CRITICAL)
                .targetType(TargetType.TARGET_TYPE_MULTI_INSTANCES)
                .region("bj")
                .scope("BCE_BCC")
                .userId(userId)
                .targetInstances(Collections.singletonList(instance))
                .policies(Collections.singletonList(policy))
                .actions(Collections.singletonList(action))
                .build();
        CreateAlarmConfigV2Response result = bcmClient.createAlarmPolicyV2(request);
        Assert.assertTrue(result.getResult().getAlarmName().length() == 32);
    }

    @Test
    public void testUpdateAlarmPolicyV2() {
        TargetInstance instance = new TargetInstance();
        instance.setRegion("bj");
        instance.setIdentifiers(Collections.singletonList(new CommonKV("InstanceId", "i-mPkY5Z**")));
        AlarmConfigPolicyRuleV2 rule = new AlarmConfigPolicyRuleV2();
        rule.setMetricName("CPUUsagePercent");
        rule.setOperator(">");
        rule.setStatistics("average");
        rule.setThreshold(12345.0);
        AlarmConfigPolicyV2 policy = new AlarmConfigPolicyV2();
        policy.setAlarmPendingPeriodCount(1);
        policy.setRules(Collections.singletonList(rule));
        AlarmConfigActionV2 action = new AlarmConfigActionV2();
        action.setName("test_yangmoda");
        CreateOrUpdateAlarmConfigV2Request request = CreateOrUpdateAlarmConfigV2Request.builder()
                .alarmName("fc2ba6********************30df46")
                .aliasName("test_alarm_policy_013")
                .resourceType("Instance")
                .alarmLevel(AlarmLevel.CRITICAL)
                .targetType(TargetType.TARGET_TYPE_MULTI_INSTANCES)
                .region("bj")
                .scope("BCE_BCC")
                .userId(userId)
                .targetInstances(Collections.singletonList(instance))
                .policies(Collections.singletonList(policy))
                .actions(Collections.singletonList(action))
                .build();
        bcmClient.updateAlarmPolicyV2(request);
    }

    @Test
    public void testBlockAlarmConfigV2() {
        CommonAlarmConfigRequest request = CommonAlarmConfigRequest.builder()
                .userId(userId)
                .scope("BCE_BCC")
                .alarmName("fc2ba6********************30df46")
                .build();
        bcmClient.blockAlarmConfigV2(request);
    }

    @Test
    public void testUnblockAlarmConfigV2() {
        CommonAlarmConfigRequest request = CommonAlarmConfigRequest.builder()
                .userId(userId)
                .scope("BCE_BCC")
                .alarmName("fc2ba6********************30df46")
                .build();
        bcmClient.unblockAlarmConfigV2(request);
    }

    @Test
    public void testGetAlarmPolicyDetailV2() {
        CommonAlarmConfigRequest request = CommonAlarmConfigRequest.builder()
                .userId(userId)
                .scope("BCE_BCC")
                .alarmName("fc2ba6********************30df46")
                .build();
        AlarmConfigV2 result = bcmClient.getAlarmPolicyDetailV2(request);
        Assert.assertEquals("fc2ba6********************30df46", result.getAlarmName());
    }

    @Test
    public void testGetCloudEventDataSuccess() {
        // 创建一个EventDataRequest对象并设置其属性
        EventDataRequest request = new EventDataRequest();
        request.setAccountId(userId);
        request.setStartTime("2023-10-01T00:00:00Z");
        request.setEndTime("2023-11-01T01:00:00Z");
        request.setPageNo(1);
        request.setPageSize(10);

        // 调用getCloudEventData方法
        CloudEventResponse response = bcmClient.getCloudEventData(request);
        Assert.assertTrue(response.getContent().size() > 0);
        Assert.assertEquals(1, response.getPageNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCloudEventDataFailure() {
        // 创建一个EventDataRequest对象并设置其属性
        EventDataRequest request = new EventDataRequest();
        request.setAccountId(userId);
        request.setStartTime("invalid-start-time");
        request.setEndTime("2020-01-01T01:00:00Z");
        request.setPageNo(1);
        request.setPageSize(10);

        // 调用getCloudEventData方法
        bcmClient.getCloudEventData(request);
    }

    @Test
    public void getPlatformEventData() {
        // 创建一个EventDataRequest对象并设置其属性
        EventDataRequest request = new EventDataRequest();
        request.setAccountId(userId);
        request.setStartTime("2023-10-01T00:00:00Z");
        request.setEndTime("2023-11-01T01:00:00Z");
        request.setPageNo(1);
        request.setPageSize(10);

        // getPlatformEventData
        PlatformEventResponse response = bcmClient.getPlatformEventData(request);
        Assert.assertTrue(response.getContent().size() > 0);
        Assert.assertEquals(1, response.getPageNumber());
    }

    @Test
    public void createEventPolicy() {
        EventPolicy eventPolicy = new EventPolicy();
        eventPolicy.setAccountId(userId);
        eventPolicy.setServiceName("BCE_BCC");
        eventPolicy.setName("sdk_policy");
        eventPolicy.setBlockStatus(BlockStatus.NORMAL);
        EventFilter eventFilter = new EventFilter();
        eventFilter.setEventLevel(EventLevel.ALL);
        eventFilter.setEventTypeList(new HashSet<String>() {{
            add("*");
        }});
        eventPolicy.setEventFilter(eventFilter);
        EventResourceFilter resource = new EventResourceFilter();
        resource.setRegion("bj");
        resource.setType("Instance");
        resource.setMonitorObjectType(MonitorObjectType.ALL);
        eventPolicy.setResource(resource);
        eventPolicy.setIncidentActions(new HashSet<String>() {{
            add("2fc6e953-331a-4404-8ce7-1c05975dbd9c");
        }});

        EventPolicyResponse actualResponse = bcmClient.createEventPolicy(eventPolicy);
        Assert.assertEquals("sdk_policy", actualResponse.getName());
    }

    @Test
    public void instanceGroupCreate() {
        // 创建一个MergedGroup对象并设置其属性
        MergedGroup mergedGroup = new MergedGroup();
        mergedGroup.setUserId(userId);
        mergedGroup.setRegion("bj");
        mergedGroup.setServiceName("BCE_BCC");
        mergedGroup.setTypeName("Instance");
        mergedGroup.setName("sdk_test_group");

        // 调用instanceGroupCreate方法
        InstanceGroupResponse response = bcmClient.instanceGroupCreate(mergedGroup);
        Assert.assertEquals("sdk_test_group", response.getName());
    }

    @Test
    public void instanceGroupUpdate() {
        // 创建一个InstanceGroup对象并设置其属性
        InstanceGroup instanceGroup = new InstanceGroup();
        instanceGroup.setUserId(userId);
        instanceGroup.setId(7913L);
        instanceGroup.setRegion("bj");
        instanceGroup.setServiceName("BCE_BCC");
        instanceGroup.setTypeName("Instance");
        instanceGroup.setName("sdk_test_group_update");

        // 调用instanceGroupUpdate方法
        InstanceGroupResponse response = bcmClient.instanceGroupUpdate(instanceGroup);
        Assert.assertEquals("sdk_test_group_update", response.getName());
    }

    @Test
    public void instanceGroupDelete() {
        InstanceGroupBase instanceGroupBase = new InstanceGroupBase();
        instanceGroupBase.setUserId(userId);
        instanceGroupBase.setId("7913");
        // 调用instanceGroupDelete方法
        InstanceGroupResponse instanceGroupResponse = bcmClient.instanceGroupDelete(instanceGroupBase);
        Assert.assertEquals("sdk_test_group_update", instanceGroupResponse.getName());
    }

    @Test
    public void instanceGroupGet() {
        // 创建一个InstanceGroupBase对象并设置其属性
        InstanceGroupBase instanceGroupBase = new InstanceGroupBase();
        instanceGroupBase.setUserId(userId);
        instanceGroupBase.setId("7913");

        // 调用instanceGroupGet方法
        InstanceGroupResponse response = bcmClient.instanceGroupGet(instanceGroupBase);
        printResult("instanceGroupGet", response);
        Assert.assertEquals("sdk_test_group_update", response.getName());
        Assert.assertEquals("Instance", response.getTypeName());
        Assert.assertEquals("BCE_BCC", response.getServiceName());
    }

    @Test
    public void instanceGroupList() {
        // 创建一个InstanceGroupQuery对象并设置其属性
        InstanceGroupQuery query = new InstanceGroupQuery();
        query.setUserId(userId);
        query.setServiceName("BCE_BCC");
        query.setPageNo(1);
        query.setPageSize(10);

        // 调用instanceGroupList方法
        InstanceGroupListResponse response = bcmClient.instanceGroupList(query);
        Assert.assertTrue(response.getTotalCount() > 0);
    }

    @Test
    public void instanceGroupAddInstance() {
        // 创建一个MergedGroup对象并设置其属性
        MergedGroup mergedGroup = new MergedGroup();
        mergedGroup.setId(7913L);
        mergedGroup.setUserId(userId);
        mergedGroup.setRegion("bj");
        mergedGroup.setServiceName("BCE_BCC");
        mergedGroup.setTypeName("Instance");
        mergedGroup.setName("sdk_test_group_update");
        MonitorResource monitorResource = new MonitorResource();
        monitorResource.setRegion("bj");
        monitorResource.setUserId(userId);
        monitorResource.setServiceName("BCE_BCC");
        monitorResource.setTypeName("Instance");
        monitorResource.setResourceId("InstanceId:dd0109a3-a7fe-4ffb-b2ae-3c6aa0b63705");
        mergedGroup.setResourceIdList(Collections.singletonList(monitorResource));

        // 调用instanceGroupCreate方法
        InstanceGroupResponse response = bcmClient.instanceGroupAddInstance(mergedGroup);
        // count为0，返回修改前的数量
        Assert.assertEquals(0, response.getCount());
    }

    @Test
    public void instanceGroupGetInstanceList() {
        // 创建一个IGInstanceQuery对象并设置其属性
        IGInstanceQuery query = new IGInstanceQuery();
        query.setUserId(userId);
        query.setId(7913L);
        query.setServiceName("BCE_BCC");
        query.setTypeName("Instance");
        query.setRegion("bj");
        query.setViewType(ViewType.DETAIL_VIEW);
        query.setPageNo(1);
        query.setPageSize(10);

        // 调用instanceGroupGetInstanceList方法
        IGInstanceListResponse response = bcmClient.instanceGroupGetInstanceList(query);
        Assert.assertTrue(response.getTotalCount() > 0);
    }

    @Test
    public void instanceGroupRemoveInstance() {
        // 创建一个MergedGroup对象并设置其属性
        MergedGroup mergedGroup = new MergedGroup();
        mergedGroup.setId(7913L);
        mergedGroup.setUserId(userId);
        mergedGroup.setRegion("bj");
        mergedGroup.setServiceName("BCE_BCC");
        mergedGroup.setTypeName("Instance");
        mergedGroup.setName("sdk_test_group_update");
        MonitorResource monitorResource = new MonitorResource();
        monitorResource.setRegion("bj");
        monitorResource.setUserId(userId);
        monitorResource.setServiceName("BCE_BCC");
        monitorResource.setTypeName("Instance");
        monitorResource.setResourceId("InstanceId:dd0109a3-a7fe-4ffb-b2ae-3c6aa0b63705");
        mergedGroup.setResourceIdList(Collections.singletonList(monitorResource));

        // 调用instanceGroupCreate方法
        InstanceGroupResponse response = bcmClient.instanceGroupRemoveInstance(mergedGroup);
        // count为1，返回修改前的数量
        Assert.assertEquals(1, response.getCount());
    }



    @Test
    public void instanceGroupQueryInstanceList() {
        // 创建一个IGInstanceQuery对象并设置其属性
        IGInstanceQuery query = new IGInstanceQuery();
        query.setUserId(userId);
        query.setServiceName("BCE_BCC");
        query.setTypeName("Instance");
        query.setRegion("bj");
        query.setViewType(ViewType.LIST_VIEW);
        query.setPageNo(1);
        query.setPageSize(10);
        query.setKeywordType("name");
        query.setKeyword("");


        // 调用instanceGroupGetInstanceList方法
        IGInstanceListResponse response = bcmClient.instanceGroupQueryInstanceList(query);
        Assert.assertTrue(response.getTotalCount() > 0);
    }

    @Test
    public void instanceGroupQueryInstanceListFilter() {
        // 创建一个IGInstanceQuery对象并设置其属性
        IGInstanceQuery query = new IGInstanceQuery();
        query.setUserId(userId);
        query.setServiceName("BCE_BCC");
        query.setTypeName("Instance");
        query.setRegion("bj");
        query.setViewType(ViewType.LIST_VIEW);
        query.setPageNo(1);
        query.setPageSize(10);
        query.setId(7913L);
        query.setUuid("a82e3910-0b47-450c-8d0b-fa13b4bbd864");

        // 调用instanceGroupGetInstanceList方法
        IGInstanceListResponse response = bcmClient.instanceGroupQueryInstanceListFilter(query);
        Assert.assertTrue(response.getTotalCount() > 0);
    }

    @Test
    public void testCreateCustomAlarmConfig() {

        CustomAlarmRule rule = CustomAlarmRule.builder()
                .id(1L)
                .index(1)
                .metricName("taasd")
                .cycle(60)
                .statistics("average")
                .threshold("1")
                .comparisonOperator(">")
                .count(1)
                .function("THRESHOLD")
                .build();

        List<CustomAlarmRule> rules = new ArrayList<CustomAlarmRule>();
        rules.add(rule);
        Set<String> alarms = new HashSet<String>();
        alarms.add("e3b8e777-4f35-48ed-abf6-bfcf6316ae2c");
        CustomAlarmConfigRequest request = CustomAlarmConfigRequest.builder()
                .userId("a0d04d7c20214*********f7b6752ce4")
                .actionEnabled(true)
                .alarmActions(alarms)
                .region("bj")
                .namespace("test_qsh")
                .alarmName("zsli_test_133")
                .level(com.baidubce.services.bcm.model.custom.AlarmLevel.MAJOR) // 将字符串"MAJOR"转换为枚举类型。
                .rules(rules) // 添加上面创建的规则
                .build();
        bcmClient.createCustomAlarmConfig(request);
    }

    @Test
    public void testDeleteCustomAlarmConfig() {
        List<String> alarmNames = new ArrayList<String>();
        alarmNames.add("zsli_test_133");
        AlarmPolicyBatch alarmPolicyBatch = AlarmPolicyBatch.builder()
                .scope("test_qsh")
                .userId("a0d04d7c20214*********f7b6752ce4")
                .alarmName(alarmNames)
                .build();
        List<AlarmPolicyBatch> alarmPolicyBatches = new ArrayList<AlarmPolicyBatch>();
        alarmPolicyBatches.add(alarmPolicyBatch);
        AlarmPolicyBatchListRequest request = AlarmPolicyBatchListRequest.builder()
                .customAlarmList(alarmPolicyBatches)
                .build();
        bcmClient.deleteCustomAlarmConfig(request);
    }

    @Test
    public void testUpdateCustomAlarmConfig() {
        CustomAlarmRule rule = CustomAlarmRule.builder()
                .id(1L)
                .index(1)
                .metricName("taasd")
                .cycle(60)
                .statistics("average")
                .threshold("1")
                .comparisonOperator("=")
                .count(1)
                .function("THRESHOLD")
                .build();

        List<CustomAlarmRule> rules = new ArrayList<CustomAlarmRule>();
        rules.add(rule);
        Set<String> alarms = new HashSet<String>();
        alarms.add("e3b8e777-4f35-48ed-abf6-bfcf6316ae2c");
        CustomAlarmConfigRequest request = CustomAlarmConfigRequest.builder()
                .userId("a0d04d7c20214*********f7b6752ce4")
                .actionEnabled(true)
                .alarmActions(alarms)
                .region("bj")
                .namespace("test_qsh")
                .alarmName("zsli_test_133")
                .level(com.baidubce.services.bcm.model.custom.AlarmLevel.MAJOR) // 将字符串"MAJOR"转换为枚举类型。
                .rules(rules) // 添加上面创建的规则
                .build();
        bcmClient.updateCustomAlarmConfig(request);

    }

    @Test
    public void testListCustomAlarmConfig() {
        ListCustomAlarmConfigRequest request = ListCustomAlarmConfigRequest.builder()
                .userId("a0d04d7c20214*********f7b6752ce4")
                .namespace("test_qsh")
                .pageNo(1)
                .pageSize(10)
                .build();
        ListCustomConfigResponse listCustomConfigResponse = bcmClient.listCustomAlarmConfig(request);
        printResult("listCustomConfigResponse", listCustomConfigResponse);
    }

    @Test
    public void testDetailCustomAlarmConfig() {
        DetailCustomAlarmConfigRequest request = DetailCustomAlarmConfigRequest.builder()
                .namespace("test_qsh")
                .userId("a0d04d7c20214*********f7b6752ce4")
                .alarmName("zsli_test_11")
                .build();
        CustomAlarmConfigResponse customAlarmConfigResponse = bcmClient.detailCustomAlarmConfig(request);
        printResult("DetailCustomAlarmConfig", customAlarmConfigResponse);
    }

    @Test
    public void testBlockCustomAlarmConfig() {
        DetailCustomAlarmConfigRequest request = DetailCustomAlarmConfigRequest.builder()
                .namespace("test_qsh")
                .userId("a0d04d7c20214*********f7b6752ce4")
                .alarmName("zsli_test_13")
                .build();
        bcmClient.blockCustomAlarmConfig(request);
    }

    @Test
    public void testUnblockCustomAlarmConfig() {
        DetailCustomAlarmConfigRequest request = DetailCustomAlarmConfigRequest.builder()
                .namespace("test_qsh")
                .userId("a0d04d7c20214*********f7b6752ce4")
                .alarmName("zsli_test_13")
                .build();
        bcmClient.unblockCustomAlarmConfig(request);
    }

    @Test
    public void testCreateSiteOnceTask() {
        SiteOnceRequest siteOnceRequest = SiteOnceRequest.builder()
                .userId("a0d04d7c20214*********f7b6752ce4")
                .address("www.baidu.com")
                .advancedFlag(false)
                .ipType("ipv4")
                .idc("beijing-CMNET,beijing-UNICOM,beijing-CHINANET,guangdong-CMNET,fujian-CMNET,henan-CMNET,hebei-CHINANET")
                .timeout(60)
                .protocolType(SiteOnceProtocol.HTTP)
                .taskType("NET_QUAILTY")
                .onceConfig(
                        SiteOnceConfig.builder()
                                .method("get")
                                .postContent("")
                                .build()
                )
                .build();
        HttpResponseWrapper<SiteOnceTaskResponse> siteOnceResponse = bcmClient.createSiteOnceTask("HTTP", siteOnceRequest);
        printResult("createSiteOnceTask", siteOnceResponse);
    }

    @Test
    public void testListSiteOnceHistory() {
        SiteOnceTaskRequest siteOnceTaskRequest = new SiteOnceTaskRequest();
        siteOnceTaskRequest.setPageNo(1);
        siteOnceTaskRequest.setPageSize(10);
        siteOnceTaskRequest.setUserId("a0d04d7c20214*********f7b6752ce4");
        siteOnceTaskRequest.setUrl("baidu");
        HttpResponseWrapper<SiteOnceTaskList> siteOnceTaskListHttpResponseWrapper = bcmClient.listSiteOnceHistory(siteOnceTaskRequest);
        printResult("listSiteOnceHistory", siteOnceTaskListHttpResponseWrapper);
    }

    @Test
    public void testDeleteSiteOnceRecord() {
        SiteOnceTaskRequest siteOnceTaskRequest = SiteOnceTaskRequest.builder()
                .siteId("ARFjqMGatiPFTPsZWHLllWdPQpvxuONu")
                .userId("a0d04d7c20214*********f7b6752ce4")
                .build();
        HttpResponseWrapper<SiteOnceTaskResponse> res = bcmClient.deleteSiteOnceRecord(siteOnceTaskRequest);
        printResult("listSiteOnceHistory", res);
    }

    @Test
    public void testDetailSiteOnceResult() {
        SiteOnceTaskRequest siteOnceTaskRequest = SiteOnceTaskRequest.builder()
                .siteId("AWIvVppyfHDZqTvApZVxZKySCGbohHVe")
                .userId("a0d04d7c20214*********f7b6752ce4")
                .build();
        HttpResponseWrapper<SiteOnceTaskRequest> siteOnceTaskRequestHttpResponseWrapper = bcmClient.detailSiteOnceResult(siteOnceTaskRequest);
        printResult("detailSiteOnceResult", siteOnceTaskRequestHttpResponseWrapper);
    }

    @Test
    public void testDetailSiteOnce() {
        SiteOnceTaskRequest siteOnceTaskRequest = SiteOnceTaskRequest.builder()
                .userId("a0d04d7c20214*********f7b6752ce4")
                .build();
        HttpResponseWrapper<SiteOnceGroupTask> res = bcmClient.detailSiteOnce(siteOnceTaskRequest);
        printResult("detailSiteOnce", res);
    }

    @Test
    public void testAgainExecSiteOnce() {
        SiteOnceTaskRequest siteOnceTaskRequest = SiteOnceTaskRequest.builder()
                .siteId("AWIvVppyfHDZqTvApZVxZKySCGbohHVe")
                .userId("a0d04d7c20214*********f7b6752ce4")
                .build();
        HttpResponseWrapper<SiteOnceTaskResponse> res = bcmClient.againExecSiteOnce(siteOnceTaskRequest);
        printResult("againExecSiteOnce", res);
    }

    @Test
    public void testListSiteOnceTaskHistory() {
        SiteOnceTaskRequest siteOnceTaskRequest = SiteOnceTaskRequest.builder()
                .siteId("AWIvVppyfHDZqTvApZVxZKySCGbohHVe")
                .userId("a0d04d7c20214*********f7b6752ce4")
                .build();
        HttpResponseWrapper<SiteOnceTaskList> res = bcmClient.listSiteOnceTaskHistory(siteOnceTaskRequest);
        printResult("listSiteOnceTaskHistory", res);
    }

    @Test
    public void testGetSiteAgent() {
        HttpResponseWrapper<SiteOnceAgent> siteAgent = bcmClient.getSiteAgent("a0d04d7c20214*********f7b6752ce4", "ipv4");
        printResult("getSiteAgent", siteAgent);
    }
    @Test
    public void testCreateApplicationData() {
            ApplicationInfoRequest request = ApplicationInfoRequest.builder()
                    .userId(userId)
                    .alias("test")
                    .description("test")
                    .type("BCC")
                    .name("test_name1215")
                    .build();
        ApplicationInfoResponse req = bcmClient.createApplicationData(request);
        printResult("testCreateApplicationData", req);
    }

    @Test
    public void testGetApplicationDataList() {
        ApplicationDataListRequest req = ApplicationDataListRequest.builder()
                .pageNo(1)
                .pageSize(10)
                .searchName("test").build();
        ApplicationDataListResponse response = bcmClient.getApplicationDataList(userId, req);
        printResult("testGetApplicationDataList", response);
    }

    @Test
    public void testUpdateApplicationData() {
        ApplicationInfoUpdateRequest request = ApplicationInfoUpdateRequest.builder()
                .userId(userId)
                .alias("test1215")
                .description("test1215")
                .type("BCC")
                .id(5403L)
                .name("test_name1215")
                .build();
        ApplicationInfoUpdateResponse req = bcmClient.updateApplicationData(request);
        printResult("testUpdateApplicationData", req);
    }

    @Test
    public void testDeleteApplicationData() {
        ApplicationInfoDetaleRequest req = ApplicationInfoDetaleRequest.builder()
                .name("test_name1215")
                .build();
        bcmClient.deleteApplicationData(userId, req);
    }

    @Test
    public void testGetApplicationInstanceList() {
            ApplicationInstanceListRequest req = ApplicationInstanceListRequest.builder()
                    .region("bj")
                    .pageNo(1)
                    .pageSize(10)
                    .appName("test_name1215")
                    .build();
            ApplicationInstanceListResponse response = bcmClient.getApplicationInstanceList(userId, req);
            printResult("testGetApplicationInstanceList", response);
    }

    @Test
    public void testCreateApplicationInstance() {
        HostInstanceInfo hostInstanceInfo = new HostInstanceInfo();
        hostInstanceInfo.setInstanceId("a9c862c2-db86-408b-b87f-5d679a2e****");
        hostInstanceInfo.setRegion("bj");
        ApplicationInstanceCreateRequest req = ApplicationInstanceCreateRequest.builder()
                .userId(userId)
                .appName("test_name1215")
                .build();
        req.setHostList(Collections.singletonList(hostInstanceInfo));
        ApplicationMonitorResponse response = bcmClient.createApplicationInstance(req);
        printResult("testCreateApplicationInstance", response);
    }

    @Test
    public void testGetApplicationInstanceCreatedList() {
        ApplicationInstanceCreatedListRequest req = ApplicationInstanceCreatedListRequest.builder()
                .userId(userId)
                .appName("test_name1215")
                .region("bj")
                .build();
        ApplicationInstanceListResponse response = bcmClient.getApplicationInstanceCreatedList(req);
        printResult("testGetApplicationInstanceCreatedList", response);
    }

    @Test
    public void testDeleteApplicationInstance() {
        ApplicationInstanceDeleteRequest req = ApplicationInstanceDeleteRequest.builder()
                .id("7100")
                .appName("test_name1215")
                .build();
        bcmClient.deleteApplicationInstance(userId, req);
    }

    @Test
    public void testCreateApplicationInstanceTask() {
//         create normal task
//        ApplicationMonitorTaskInfoRequest req1 = ApplicationMonitorTaskInfoRequest.builder()
//                .appName("test_name1215")
//                .type(0)
//                .cycle(60)
//                .aliasName("test-proc")
//                .target("/usr/local/sbin")
//                .description("test1215")
//                .build();
//        ApplicationMonitorTaskResponse response = bcmClient.createApplicationInstanceTask(userId, req1);
//        printResult("testCreateApplicationInstanceTask", response);
//         create log task
        ApplicationMonitorTaskInfoRequest req2 = ApplicationMonitorTaskInfoRequest.builder()
                .appName("test_name1215")
                .type(2)
                .cycle(60)
                .rate(5)
                .aliasName("test-log")
                .logExample("namespace:04b91096-a294-477d-bd11-1a7bcfb5a921\\n")
                .matchRule("namespace:(?P<namespace>[0-9a-fA-F-]+)")
                .userId(userId)
                .target("/opt/bcm-agent/log/bcm-agent.INFO")
                .description("test1215")
                .build();
        LogExtractResult logExtractResult = LogExtractResult.builder()
                        .extractFieldName("namespace")
                        .extractFieldValue("04b91096-a294-477d-bd11-1a7bcfb5a921")
                        .dimensionMapTable("test-table")
                        .build();
        Metric metric = Metric.builder().
                metricName("space")
                .saveInstanceData(1)
                .valueFieldType(0)
                .metricUnit("")
                .valueFieldName("")
                .metricAlias("")
                .build();
        AggrTag aggrTag1 = new AggrTag();
        aggrTag1.setRange("App");
        aggrTag1.setTags("");
        AggrTag aggrTag2 = new AggrTag();
        aggrTag2.setRange("App");
        aggrTag2.setTags("namespace");
        metric.setAggrTags(Arrays.asList(aggrTag1, aggrTag2));
        req2.setExtractResult(Collections.singletonList(logExtractResult));
        req2.setMetrics(Collections.singletonList(metric));
        ApplicationMonitorTaskResponse response2 = bcmClient.createApplicationInstanceTask(userId, req2);
        printResult("testCreateApplicationInstanceTask", response2);
    }

    @Test
    public void testGetApplicationMonitorTaskDetail() {
        ApplicationMonitorTaskDetailRequest req = ApplicationMonitorTaskDetailRequest.builder()
                .userId(userId)
                .appName("test_name1215")
                .taskName("7d4c1ce9a3444c8f9948311a0611****")
                .build();
        ApplicationMonitorTaskResponse response = bcmClient.getApplicationMonitorTaskDetail(req);
        printResult("testGetApplicationMonitorTaskDetail", response);
    }

    @Test
    public void testGetApplicationMonitorTaskList() {
        ApplicationMonitorTaskListRequest req = ApplicationMonitorTaskListRequest.builder()
                .userId(userId)
                .appName("test_name1215")
//                .type("2")
                .build();
        List<ApplicationMonitorTaskResponse> response = bcmClient.getApplicationMonitorTaskList(req);
        printResult("testGetApplicationMonitorTaskList", response);
    }

    @Test
    public void testUpdateApplicationMonitorTask() {
        // update normal task
//        ApplicationMonitorTaskInfoRequest req1 = ApplicationMonitorTaskInfoRequest.builder()
//                .appName("test_name1215")
//                .type(1)
//                .cycle(60)
//                .aliasName("test-proc01")
//                .target("/usr/local/sbin")
//                .description("test1207-01")
//                .build();
//        ApplicationMonitorTaskResponse response = bcmClient.updateApplicationMonitorTask(userId, req1);
//        printResult("testCreateApplicationInstanceTask", response);
        // update log task
        ApplicationMonitorTaskInfoRequest req2 = ApplicationMonitorTaskInfoRequest.builder()
                .appName("test_name1215")
                .type(2)
                .name("7d4c1ce9a3444c8f9948311a0611752c")
                .cycle(60)
                .rate(5)
                .aliasName("test-log01")
                .logExample("namespace:04b91096-a294-477d-bd11-1a7bcfb5a921\\n")
                .matchRule("namespace:(?P<namespace>[0-9a-fA-F-]+)")
                .userId(userId)
                .target("/opt/bcm-agent/log/bcm-agent.INFO")
                .description("test1207-01")
                .build();
        LogExtractResult logExtractResult = LogExtractResult.builder()
                .extractFieldName("namespace")
                .extractFieldValue("04b91096-a294-477d-bd11-1a7bcfb5a921")
                .dimensionMapTable("test-table")
                .build();
        Metric metric = Metric.builder().
                metricName("space")
                .saveInstanceData(1)
                .valueFieldType(0)
                .metricUnit("")
                .valueFieldName("")
                .metricAlias("")
                .build();
        AggrTag aggrTag1 = new AggrTag();
        aggrTag1.setRange("App");
        aggrTag1.setTags("");
        AggrTag aggrTag2 = new AggrTag();
        aggrTag2.setRange("App");
        aggrTag2.setTags("namespace");
        metric.setAggrTags(Arrays.asList(aggrTag1, aggrTag2));
        req2.setExtractResult(Collections.singletonList(logExtractResult));
        req2.setMetrics(Collections.singletonList(metric));
        ApplicationMonitorTaskResponse response2 = bcmClient.updateApplicationMonitorTask(userId, req2);
        printResult("testCreateApplicationInstanceTask", response2);
    }

    @Test
    public void testDeleteApplicationMonitorTask() {
        ApplicationMonitorTaskDeleteRequest req = ApplicationMonitorTaskDeleteRequest.builder()
                .userId(userId)
                .appName("test_name1215")
                .name("7d4c1ce9a3444c8f9948311a0611****")
                .build();
        bcmClient.deleteApplicationMonitorTask(req);
    }

    @Test
    public void testCreateApplicationDimensionTable() {
        ApplicationDimensionTableInfoRequest req = ApplicationDimensionTableInfoRequest.builder()
                .userId(userId)
                .appName("test_name1215")
                .tableName("space-table")
                .mapContentJson("a=>1")
                .build();
        ApplicationDimensionTableInfoResponse response = bcmClient.createApplicationDimensionTable(req);
        printResult("testCreateApplicationInstanceTask", response);
    }

    @Test
    public void testGetApplicationDimensionTableList() {
        ApplicationDimensionTableListRequest req = ApplicationDimensionTableListRequest.builder()
                .userId(userId)
                .appName("test_name1215")
                .searchName("test")
                .build();
        List<ApplicationDimensionTableInfoResponse> response = bcmClient.getApplicationDimensionTableList(req);
        printResult("testGetApplicationDimensionTableList", response);
    }

    @Test
    public void testUpdateApplicationDimensionTable() {
        ApplicationDimensionTableInfoRequest req = ApplicationDimensionTableInfoRequest.builder()
                .userId(userId)
                .appName("test_name1215")
                .tableName("test-table")
                .mapContentJson("a=>1\nb=>2")
                .build();
        ApplicationMonitorResponse response = bcmClient.updateApplicationDimensionTable(req);
        printResult("testUpdateApplicationDimensionTable", response);
    }

    @Test
    public void testDeleteApplicationDimensionTable() {
        ApplicationDimensionTableDeleteRequest req = ApplicationDimensionTableDeleteRequest.builder()
                .userId(userId)
                .appName("test_name1215")
                .tableName("test-table")
                .build();
        bcmClient.deleteApplicationDimensionTable(req);
    }

    @Test
    public void testCreateHttpSiteTask() {
        HttpTaskRequest request = new HttpTaskRequest();
        request.setUserId(userId);
        request.setTaskName("task_name");
        request.setAddress("www.baidu.com");
        request.setMethod("get");
        request.setCycle(60);
        request.setIdc("beijing-CMNET");
        request.setTimeout(10);
        request.setAdvanceConfig(false);
        TaskResponse httpSiteTask = bcmClient.createHttpSiteTask(request);
        printResult("createHttpSiteTask", httpSiteTask);
    }

    @Test
    public void testUpdateHttpSiteTask() {
        HttpTaskRequest request = new HttpTaskRequest();
        request.setUserId(userId);
        request.setTaskName("task_name");
        request.setAddress("www.baidu.com");
        request.setMethod("get");
        request.setCycle(60);
        request.setIdc("beijing-CMNET");
        request.setTimeout(20);
        request.setAdvanceConfig(false);
        request.setTaskId("tTeqkwJeqNTxJnoPJoltkQZCoNfKJhnS");
        TaskResponse httpSiteTask = bcmClient.updateHttpSiteTask(request);
        printResult("updateHttpSiteTask", httpSiteTask);
    }

    @Test
    public void testGetHttpSiteTask() {
        TaskDetailRequest request = new TaskDetailRequest();
        request.setUserId(userId);
        request.setTaskId("tTeqkwJeqNTxJnoPJoltkQZCoNfKJhnS");
        HttpTaskResponse httpSiteTask = bcmClient.getHttpSiteTask(request);
        printResult("getHttpSiteTask", httpSiteTask);
    }

    @Test
    public void testCreateHttpsSiteTask() {
        HttpsTaskRequest request = new HttpsTaskRequest();
        request.setUserId(userId);
        request.setTaskName("task_name");
        request.setAddress("www.baidu.com");
        request.setMethod("get");
        request.setCycle(60);
        request.setIdc("beijing-CMNET");
        request.setTimeout(10);
        request.setAdvanceConfig(false);
        TaskResponse httpsSiteTask = bcmClient.createHttpsSiteTask(request);
        printResult("createHttpsSiteTask", httpsSiteTask);
    }

    @Test
    public void testUpdateHttpsSiteTask() {
        HttpsTaskRequest request = new HttpsTaskRequest();
        request.setUserId(userId);
        request.setTaskName("task_name");
        request.setAddress("www.baidu.com");
        request.setMethod("get");
        request.setCycle(60);
        request.setIdc("beijing-CMNET");
        request.setTimeout(20);
        request.setAdvanceConfig(false);
        request.setTaskId("NuRGHuqVwJYbDVtAbVqNtqtthZqHtPTj");
        TaskResponse httpsSiteTask = bcmClient.updateHttpsSiteTask(request);
        printResult("updateHttpsSiteTask", httpsSiteTask);
    }

    @Test
    public void testGetHttpsSiteTask() {
        TaskDetailRequest request = new TaskDetailRequest();
        request.setUserId(userId);
        request.setTaskId("NuRGHuqVwJYbDVtAbVqNtqtthZqHtPTj");
        HttpsTaskResponse httpsSiteTask = bcmClient.getHttpsSiteTask(request);
        printResult("testGetHttpsSiteTask", httpsSiteTask);
    }

    @Test
    public void testCreatePingSiteTask() {
        PingTaskRequest request = new PingTaskRequest();
        request.setUserId(userId);
        request.setTaskName("task_name");
        request.setAddress("www.baidu.com");
        request.setCycle(60);
        request.setIdc("beijing-CMNET");
        request.setTimeout(10);
        request.setPacketCount(1);
        TaskResponse pingSiteTask = bcmClient.createPingSiteTask(request);
        printResult("createPingSiteTask", pingSiteTask);
    }

    @Test
    public void testUpdatePingSiteTask() {
        PingTaskRequest request = new PingTaskRequest();
        request.setUserId(userId);
        request.setTaskName("task_name");
        request.setAddress("www.baidu.com");
        request.setCycle(60);
        request.setIdc("beijing-CMNET");
        request.setTimeout(20);
        request.setPacketCount(1);
        request.setTaskId("NRPgdZXrsyWZLApUCDhINsHIBmCesxbc");
        TaskResponse pingSiteTask = bcmClient.updatePingSiteTask(request);
        printResult("updatePingSiteTask", pingSiteTask);
    }

    @Test
    public void testGetPingSiteTask() {
        TaskDetailRequest request = new TaskDetailRequest();
        request.setUserId(userId);
        request.setTaskId("NRPgdZXrsyWZLApUCDhINsHIBmCesxbc");
        PingTaskResponse pingSiteTask = bcmClient.getPingSiteTask(request);
        printResult("getPingSiteTask", pingSiteTask);
    }

    @Test
    public void testCreateTcpSiteTask() {
        TcpTaskRequest request = new TcpTaskRequest();
        request.setUserId(userId);
        request.setTaskName("task_name");
        request.setAddress("www.baidu.com");
        request.setCycle(60);
        request.setIdc("beijing-CMNET");
        request.setTimeout(10);
        request.setPort(80);
        TaskResponse tcpSiteTask = bcmClient.createTcpSiteTask(request);
        printResult("createTcpSiteTask", tcpSiteTask);
    }

    @Test
    public void testUpdateTcpSiteTask() {
        TcpTaskRequest request = new TcpTaskRequest();
        request.setUserId(userId);
        request.setTaskName("task_name");
        request.setAddress("www.baidu.com");
        request.setCycle(60);
        request.setIdc("beijing-CMNET");
        request.setTimeout(5);
        request.setPort(80);
        request.setTaskId("qOynCUQxuoUOFelnsJneFgYJRPEusjSC");
        TaskResponse tcpSiteTask = bcmClient.updateTcpSiteTask(request);
        printResult("updateTcpSiteTask", tcpSiteTask);
    }

    @Test
    public void testGetTcpSiteTask() {
        TaskDetailRequest request = new TaskDetailRequest();
        request.setUserId(userId);
        request.setTaskId("qOynCUQxuoUOFelnsJneFgYJRPEusjSC");
        TcpTaskResponse tcpSiteTask = bcmClient.getTcpSiteTask(request);
        printResult("getTcpSiteTask", tcpSiteTask);
    }

    @Test
    public void testCreateUdpSiteTask() {
        UdpTaskRequest request = new UdpTaskRequest();
        request.setUserId(userId);
        request.setTaskName("task_name");
        request.setAddress("www.baidu.com");
        request.setCycle(60);
        request.setIdc("beijing-CMNET");
        request.setTimeout(10);
        request.setPort(80);
        TaskResponse udpSiteTask = bcmClient.createUdpSiteTask(request);
        printResult("createUdpSiteTask", udpSiteTask);
    }

    @Test
    public void testUpdateUdpSiteTask() {
        UdpTaskRequest request = new UdpTaskRequest();
        request.setUserId(userId);
        request.setTaskName("task_name");
        request.setAddress("www.baidu.com");
        request.setCycle(60);
        request.setIdc("beijing-CMNET");
        request.setTimeout(5);
        request.setPort(80);
        request.setTaskId("MalUKBQOhywttnSsmnGydScxIGeqkTIV");
        TaskResponse udpSiteTask = bcmClient.updateUdpSiteTask(request);
        printResult("updateUdpSiteTask", udpSiteTask);
    }

    @Test
    public void testGetUdpSiteTask() {
        TaskDetailRequest request = new TaskDetailRequest();
        request.setUserId(userId);
        request.setTaskId("MalUKBQOhywttnSsmnGydScxIGeqkTIV");
        UdpTaskResponse dupSiteTask = bcmClient.getUdpSiteTask(request);
        printResult("getUdpSiteTask", dupSiteTask);
    }

    @Test
    public void testCreateFtpSiteTask() {
        FtpTaskRequest request = new FtpTaskRequest();
        request.setUserId(userId);
        request.setTaskName("task_name");
        request.setAddress("www.baidu.com");
        request.setCycle(60);
        request.setIdc("beijing-CMNET");
        request.setTimeout(10);
        request.setPort(80);
        TaskResponse ftpSiteTask = bcmClient.createFtpSiteTask(request);
        printResult("createFtpSiteTask", ftpSiteTask);
    }

    @Test
    public void testUpdateFtpSiteTask() {
        FtpTaskRequest request = new FtpTaskRequest();
        request.setUserId(userId);
        request.setTaskName("task_name");
        request.setAddress("www.baidu.com");
        request.setCycle(60);
        request.setIdc("beijing-CMNET");
        request.setTimeout(5);
        request.setPort(80);
        request.setTaskId("DsWUxHOaofroBypuAKDuHOZMfXMJjXta");
        TaskResponse ftpSiteTask = bcmClient.updateFtpSiteTask(request);
        printResult("updateFtpSiteTask", ftpSiteTask);
    }

    @Test
    public void testGetFtpSiteTask() {
        TaskDetailRequest request = new TaskDetailRequest();
        request.setUserId(userId);
        request.setTaskId("DsWUxHOaofroBypuAKDuHOZMfXMJjXta");
        FtpTaskResponse ftpSiteTask = bcmClient.getFtpSiteTask(request);
        printResult("getFtpSiteTask", ftpSiteTask);
    }

    @Test
    public void testCreateDnsSiteTask() {
        DnsTaskRequest request = new DnsTaskRequest();
        request.setUserId(userId);
        request.setTaskName("task_name");
        request.setAddress("www.baidu.com");
        request.setCycle(60);
        request.setIdc("beijing-CMNET");
        request.setTimeout(10);
        TaskResponse dnsSiteTask = bcmClient.createDnsSiteTask(request);
        printResult("createDnsSiteTask", dnsSiteTask);
    }

    @Test
    public void testUpdateDnsSiteTask() {
        DnsTaskRequest request = new DnsTaskRequest();
        request.setUserId(userId);
        request.setTaskName("task_name");
        request.setAddress("www.baidu.com");
        request.setCycle(60);
        request.setIdc("beijing-CMNET");
        request.setTimeout(5);
        request.setTaskId("ZvnxxdsVPSfIlNGGRnTaICtzzlCtwmGk");
        TaskResponse dnsSiteTask = bcmClient.updateDnsSiteTask(request);
        printResult("updateDnsSiteTask", dnsSiteTask);
    }

    @Test
    public void testGetDnsSiteTask() {
        TaskDetailRequest request = new TaskDetailRequest();
        request.setUserId(userId);
        request.setTaskId("ZvnxxdsVPSfIlNGGRnTaICtzzlCtwmGk");
        DnsTaskResponse dnsSiteTask = bcmClient.getDnsSiteTask(request);
        printResult("getDnsSiteTask", dnsSiteTask);
    }

    @Test
    public void testGetSiteTaskList() {
        TaskSummaryRequest request = new TaskSummaryRequest();
        request.setUserId(userId);
        request.setPageNo(1);
        request.setPageSize(10);
        request.setQuery("NAME:");
        PageData<TaskSummaryResponse> siteTaskList = bcmClient.getSiteTaskList(request);
        printResult("getSiteTaskList", siteTaskList);
    }

    @Test
    public void testDeleteSiteTask() {
        SiteTaskRequest request = new SiteTaskRequest();
        request.setUserId(userId);
        request.setTaskId("DsWUxHOaofroBypuAKDuHOZMfXMJjXta");
        SiteBasicResponse siteBasicResponse = bcmClient.deleteSiteTask(request);
        printResult("deleteSiteTask", siteBasicResponse);
    }

    @Test
    public void testGetSiteInfo() {
        SiteTaskRequest request = new SiteTaskRequest();
        request.setUserId(userId);
        request.setTaskId("NRPgdZXrsyWZLApUCDhINsHIBmCesxbc");
        SiteInfoResponse siteInfo = bcmClient.getSiteInfo(request);
        printResult("getSiteInfo", siteInfo);
    }

    @Test
    public void testCreateSiteAlarmConfig() {
        SiteAlarmConfigRequest request = new SiteAlarmConfigRequest();
        request.setUserId(userId);
        request.setTaskId("tTeqkwJeqNTxJnoPJoltkQZCoNfKJhnS");
        request.setCycle("60");
        request.setAliasName("site_alarm");
        Set<String> action = new HashSet<String>();
        action.add("1a817da0-c69b-4b88-b0f9-b6e033fac7ba");
        request.setIncidentActions(action);
        request.setInsufficientActions(new HashSet<String>());
        request.setResumeActions(new HashSet<String>());
        request.setLevel(com.baidubce.services.bcm.model.site.AlarmLevel.MAJOR);
        request.setNamespace("BCM_SITE");
        request.setRegion("bj");
        request.setActionEnabled(true);
        SiteAlarmRule rule = new SiteAlarmRule();
        rule.setComparisonOperator(">");
        rule.setCount(1);
        rule.setCycle(60);
        rule.setFunction("THRESHOLD");
        rule.setMetric("connectTime");
        rule.setMetricAlias("建连时间");
        rule.setStatistics("average");
        rule.setStatistics("average");
        rule.setThreshold("10000");
        rule.setActOnIdcs(Collections.singletonList("average"));
        request.setRules(Collections.singletonList(rule));
        bcmClient.createSiteAlarmConfig(request);
    }

    @Test
    public void testUpdateSiteAlarmConfig() {
        SiteAlarmConfigRequest request = new SiteAlarmConfigRequest();
        request.setUserId(userId);
        request.setTaskId("tTeqkwJeqNTxJnoPJoltkQZCoNfKJhnS");
        request.setAlarmName("9368c1779ea04260af99873a719f82c3");
        request.setCycle("60");
        request.setAliasName("site_alarm");
        Set<String> action = new HashSet<String>();
        action.add("1a817da0-c69b-4b88-b0f9-b6e033fac7ba");
        request.setIncidentActions(action);
        request.setInsufficientActions(new HashSet<String>());
        request.setResumeActions(new HashSet<String>());
        request.setLevel(com.baidubce.services.bcm.model.site.AlarmLevel.MAJOR);
        request.setNamespace("BCM_SITE");
        request.setRegion("bj");
        request.setActionEnabled(true);
        SiteAlarmRule rule = new SiteAlarmRule();
        rule.setComparisonOperator(">=");
        rule.setCount(1);
        rule.setCycle(60);
        rule.setFunction("THRESHOLD");
        rule.setMetric("connectTime");
        rule.setMetricAlias("建连时间");
        rule.setStatistics("average");
        rule.setStatistics("average");
        rule.setThreshold("10000");
        rule.setActOnIdcs(Collections.singletonList("average"));
        request.setRules(Collections.singletonList(rule));
        bcmClient.updateSiteAlarmConfig(request);
    }

    @Test
    public void testGetSiteAlarmConfigDetail() {
        SiteAlarmUserIdRequest request = new SiteAlarmUserIdRequest();
        request.setUserId(userId);
        request.setAlarmName("9368c1779ea04260af99873a719f82c3");
        SiteAlarmConfigDetailResponse siteAlarmConfigDetail = bcmClient.getSiteAlarmConfigDetail(request);
        printResult("getSiteAlarmConfigDetail", siteAlarmConfigDetail);
    }

    @Test
    public void testGetSiteAlarmConfigList() {
        SiteAlarmConfigListRequest request = new SiteAlarmConfigListRequest();
        request.setUserId(userId);
        request.setTaskId("tTeqkwJeqNTxJnoPJoltkQZCoNfKJhnS");
        request.setPageNo(1);
        request.setPageSize(2);
        PageResultResponse<SiteAlarmConfigDetailResponse> configList = bcmClient.getSiteAlarmConfigList(request);
        printResult("getSiteAlarmConfigList", configList);
    }

    @Test
    public void testBlockSiteAlarmConfig() {
        SiteAlarmUserIdRequest request = new SiteAlarmUserIdRequest();
        request.setUserId(userId);
        request.setAlarmName("9368c1779ea04260af99873a719f82c3");
        request.setNamespace("BCM_SITE");
        bcmClient.blockSiteAlarmConfig(request);
    }

    @Test
    public void testUnblockSiteAlarmConfig() {
        SiteAlarmUserIdRequest request = new SiteAlarmUserIdRequest();
        request.setUserId(userId);
        request.setAlarmName("9368c1779ea04260af99873a719f82c3");
        request.setNamespace("BCM_SITE");
        bcmClient.unblockSiteAlarmConfig(request);
    }

    @Test
    public void testGetSiteConfigByAlarmName() {
        SiteAlarmUserIdRequest request = new SiteAlarmUserIdRequest();
        request.setUserId(userId);
        request.setAlarmName("9368c1779ea04260af99873a719f82c3");
        SiteInfoResponse siteInfo = bcmClient.getSiteConfigByAlarmName(request);
        printResult("getSiteConfigByAlarmName", siteInfo);
    }

    @Test
    public void testGetSiteMetricSiteData() {
        SiteMetricDataQueryRequest request = new SiteMetricDataQueryRequest();
        request.setUserId(userId);
        request.setTaskId("tTeqkwJeqNTxJnoPJoltkQZCoNfKJhnS");
        request.setMetricName("success");
        request.setStartTime("2024-01-22T06:52:54Z");
        request.setEndTime("2024-01-22T07:52:54Z");
        request.setStatistics(new String[]{"average"});
        request.setCycle(60);
        List<SiteMetricDataQueryResponse> siteMetricSiteData = bcmClient.getSiteMetricSiteData(request);
        printResult("getSiteMetricSiteData", siteMetricSiteData);
    }

    @Test
    public void testGetSiteOverallView() {
        SiteTaskRequest request = new SiteTaskRequest();
        request.setUserId(userId);
        request.setTaskId("tTeqkwJeqNTxJnoPJoltkQZCoNfKJhnS");
        List<SiteViewResponse> siteOverallView = bcmClient.getSiteOverallView(request);
        printResult("getSiteOverallView", siteOverallView);
    }

    @Test
    public void testGetSiteProvincialView() {
        SiteTaskIspRequest request = new SiteTaskIspRequest();
        request.setUserId(userId);
        request.setTaskId("tTeqkwJeqNTxJnoPJoltkQZCoNfKJhnS");
        request.setIsp("CMNET");
        List<SiteViewResponse> siteProvincialView = bcmClient.getSiteProvincialView(request);
        printResult("getSiteProvincialView", siteProvincialView);
    }

    @Test
    public void testGetSiteAgentList() {
        SiteAgentRequest request = new SiteAgentRequest();
        request.setUserId(userId);
        List<SiteAgentResponse> siteAgentList = bcmClient.getSiteAgentList(request);
        printResult("getSiteAgentList", siteAgentList);
    }

    @Test
    public void testGetSiteAgentListByTaskId() {
        SiteTaskRequest request = new SiteTaskRequest();
        request.setUserId(userId);
        request.setTaskId("tTeqkwJeqNTxJnoPJoltkQZCoNfKJhnS");
        IdcIspResponse siteAgentListByTaskId = bcmClient.getSiteAgentListByTaskId(request);
        printResult("getSiteAgentListByTaskId", siteAgentListByTaskId);
    }

    @Test
    public void testgetMultiDimensionalLatestMetrics() {
        MultiDimensionalLatestMetricsRequest request = new MultiDimensionalLatestMetricsRequest();
        ArrayList<String> metrics = new ArrayList<String>();
        metrics.add("ActiveConnCount");
        ArrayList<Dimension> dimensions = new ArrayList<Dimension>();
        dimensions.add(new Dimension("BlbId", "lb-7af3ed23"));
        request.setUserId(userId);
        request.setScope("BCE_BLB");
        request.setMetricNames(metrics);
        request.setDimensions(dimensions);
        MultiDimensionalLatestMetricsResponse res = bcmClient.getMultiDimensionalLatestMetrics(request);
        printResult("getMultiDimensionalLatestMetrics", res);
    }

    @Test
    public void testgetGetMetricsByPartialDimensions() {
        PartialDimensionsMetricsRequest request = new PartialDimensionsMetricsRequest();
        ArrayList<Dimension> dimensions = new ArrayList<Dimension>();
        dimensions.add(new Dimension("BlbPortType", "TCP"));
        request.setUserId(userId);
        request.setScope("BCE_BLB");
        request.setResourceType("Blb");
        request.setMetricName("ActiveConnCount");
        request.setDimensions(dimensions);
        request.setStartTime("2024-03-20T02:21:17Z");
        request.setEndTime("2024-03-20T03:21:17Z");
        List<String> statistics = new ArrayList<String>();
        statistics.add("sum");
        statistics.add("average");
        request.setStatistics(statistics);
        request.setPageNo(2);
        request.setRegion("su");
        request.setPageSize(2);
        request.setCycle(30);
        TsdbMetricResult<PageResultResponse<TsdbMetricAllDataResult.AllDataMetric>> metricsByPartialDimensions =
                bcmClient.getMetricsByPartialDimensions(request);
        printResult("getMetricsByPartialDimensions", metricsByPartialDimensions);
    }

    @Test
    public void testgetGetMetricsByPartialDimensions_atLeastParam() {
        PartialDimensionsMetricsRequest request = new PartialDimensionsMetricsRequest();
        request.setUserId(userId);
        request.setScope("BCE_BCC");
        request.setMetricName("CpuIdlePercent");
        request.setStartTime("2024-03-20T02:21:17Z");
        request.setEndTime("2024-03-20T03:21:17Z");
        List<String> statistics = new ArrayList<String>();
        statistics.add("sum");
        statistics.add("average");
        request.setStatistics(statistics);
        TsdbMetricResult<PageResultResponse<TsdbMetricAllDataResult.AllDataMetric>> metricsByPartialDimensions =
                bcmClient.getMetricsByPartialDimensions(request);
        printResult("getMetricsByPartialDimensions", metricsByPartialDimensions);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testgetGetMetricsByPartialDimensions_dimensionOutofRange() {
        PartialDimensionsMetricsRequest request = new PartialDimensionsMetricsRequest();
        request.setUserId(userId);
        request.setScope("BCE_BCC");
        request.setMetricName("CpuIdlePercent");
        request.setStartTime("2024-03-20T02:21:17Z");
        request.setEndTime("2024-03-20T03:21:17Z");
        List<String> statistics = new ArrayList<String>();
        statistics.add("sum");
        statistics.add("average");
        request.setStatistics(statistics);
        ArrayList<Dimension> dimensions = new ArrayList<Dimension>();
        for (int i = 0; i < 101; ++i) {
            dimensions.add(new Dimension("BlbPortType", "TCP"));
        }
        request.setDimensions(dimensions);
        TsdbMetricResult<PageResultResponse<TsdbMetricAllDataResult.AllDataMetric>> metricsByPartialDimensions =
                bcmClient.getMetricsByPartialDimensions(request);
    }

    @Test
    public void testBatchGetMetricsAllDataV2() {
        MultiDimensionalMetricsRequest request = new MultiDimensionalMetricsRequest();
        request.setUserId(userId);
        request.setScope("BCE_MQ_KAFKA");
        List<String> metricNames = new ArrayList<String>();
        metricNames.add("TopicBytesInPerSec");
        metricNames.add("PartitionDeleteSizeByThresholdPerSec");
        request.setMetricNames(metricNames);
        request.setStartTime("2024-03-21T06:33:50Z");
        request.setEndTime("2024-03-21T07:33:50Z");
        List<List<Dimension>> dimensions = new ArrayList<List<Dimension>>();
        List<Dimension> d = new ArrayList<Dimension>();
        d.add(new Dimension("ClusterId", "efe456d667******b890652c93812a79"));
        d.add(new Dimension("Topic", "foo"));
        dimensions.add(d);
        request.setDimensions(dimensions);
        List<String> statistics = new ArrayList<String>();
        statistics.add("sum");
        statistics.add("average");
        request.setStatistics(statistics);
        request.setRegion("bj");
        request.setType("Topic");
        TsdbMetricAllDataResult result = bcmClient.batchGetMetricsAllDataV2(request);
        printResult("batch get metrics", result);
    }

    @Test
    public void getAllDataMetricV2() {
        MultiDimensionalMetricsRequest request = new MultiDimensionalMetricsRequest();
        request.setUserId(userId);
        request.setScope("BCE_BCC");
        List<String> metricNames = new ArrayList<String>();
        metricNames.add("CPUUsagePercent");
        metricNames.add("MemUsedPercent");
        request.setMetricNames(metricNames);
        request.setStartTime("2024-03-20T07:01:00Z");
        request.setEndTime("2024-03-20T07:05:00Z");
        List<List<Dimension>> dimensions = new ArrayList<List<Dimension>>();
        List<Dimension> d1 = new ArrayList<Dimension>();
        d1.add(new Dimension("InstanceId", "i-DMx***xX"));
        List<Dimension> d2 = new ArrayList<Dimension>();
        d2.add(new Dimension("InstanceId", "i-Y8N***md"));
        dimensions.add(d1);
        dimensions.add(d2);
        request.setDimensions(dimensions);
        List<String> statistics = new ArrayList<String>();
        statistics.add("sum");
        statistics.add("average");
        request.setStatistics(statistics);
        request.setRegion("bj");
        TsdbMetricAllDataResult result = bcmClient.getAllDataMetricV2(request);
        printResult("get add data metrics", result);
    }
}
