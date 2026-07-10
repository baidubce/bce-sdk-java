package com.baidubce.services.eo;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eo.model.cache.*;
import com.baidubce.services.eo.model.logmodel.GetDomainListLogRequest;
import com.baidubce.services.eo.model.logmodel.GetDomainListLogResponse;
import com.baidubce.services.eo.model.rule.*;
import com.baidubce.services.eo.model.site.*;
import com.baidubce.services.eo.model.stat.*;
import com.baidubce.util.JsonUtils;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * Unit test for EO client.
 */
@Ignore
public class EoClientTest {
    private static final String AK = "YOUR_ACCESS_KEY";
    private static final String SK = "YOUR_SECRET_KEY";
    private static final String ENDPOINT = "GEO_ENDPOINT";
    private static final String DOMAIN = "TEST_DOMAIN";
    private static final String SITE = "TEST_SITE";

    private EoClient eoClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(ENDPOINT);
        eoClient = new EoClient(config);
    }

    /**
     * Get multiple domain URLs of logmodel files
     */
    @Test
    public void testGetDomainListLog() throws Exception {
        GetDomainListLogRequest request = new GetDomainListLogRequest()
                .withSite(SITE)
                .withStartTime("2026-03-18T16:00:00Z")
                .withEndTime("2026-03-19T11:06:21Z")
                .withPageNo(1)
                .withPageSize(20)
                .withDomainList(Arrays.asList(DOMAIN));
        GetDomainListLogResponse response = eoClient.getDomainListLog(request);
        System.out.println(response);
    }

    /**
     * Set the node cache configuration of a site.
     */
    @Test
    public void testSetSiteConfigCacheTtl() throws Exception {
        // 节点配置
        CacheTtl cacheTtl = new CacheTtl()
                .withValue("/")
                .withWeight(100)
                .withOverrideOrigin(true)
                .withTtl(259200)
                .withType("path");
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withCacheTtl(Arrays.asList(cacheTtl));
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Query all configuration items of a site.
     */
    @Test
    public void testGetSiteConfig() throws Exception {
        GetSiteConfigRequest request = new GetSiteConfigRequest()
                .withSite(SITE);
        Map<String, Object> allConfig = eoClient.getSiteConfig(request);
        System.out.println(JsonUtils.toJsonPrettyString(allConfig));
    }

    /**
     * Query a single configuration item of a site.
     */
    @Test
    public void testGetSiteConfigSingleItem() throws Exception {
        GetSiteConfigRequest request = new GetSiteConfigRequest()
                .withSite(SITE);
        // 只查节点缓存配置
        Map<String, Object> cacheTtl = eoClient.getSiteConfig(request, "cacheTtl");
        System.out.println(JsonUtils.toJsonPrettyString(cacheTtl));
    }

    /**
     * Query multiple configuration items of a site.
     */
    @Test
    public void testGetSiteConfigMultipleItems() throws Exception {
        GetSiteConfigRequest request = new GetSiteConfigRequest()
                .withSite(SITE);
        // 同时查节点缓存配置和智能加速配置
        Map<String, Object> config = eoClient.getSiteConfig(request, "cacheTtl", "isa");
        System.out.println(JsonUtils.toJsonPrettyString(config));
    }

    /**
     * Set the cacheKey configuration of a site.
     */
    @Test
    public void testSetSiteConfigCacheKey() throws Exception {
        CacheKey cacheKey = new CacheKey()
                .withQuery(true);
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withCacheKey(cacheKey);
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the offline mode configuration of a site.
     */
    @Test
    public void testSetSiteConfigOfflineMode() throws Exception {
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withOfflineMode("ON");
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the force HTTPS configuration of a site.
     */
    @Test
    public void testSetSiteConfigHttpToHttps() throws Exception {
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withHttpToHttpsEnabled("ON")
                .withHttpToHttpsCode("302");
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the HSTS configuration of a site.
     */
    @Test
    public void testSetSiteConfigHsts() throws Exception {
        Hsts hsts = new Hsts()
                .withMaxAge(60)
                .withIncludeSubDomains(true)
                .withPreload(false);
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withHsts(hsts);
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the HTTP2 configuration of a site.
     */
    @Test
    public void testSetSiteConfigHttp2() throws Exception {
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withHttp2Disable("ON");
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the HTTP3 configuration of a site.
     */
    @Test
    public void testSetSiteConfigHttp3() throws Exception {
        Http3 http3 = new Http3()
                .withEnable(true);
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withHttp3(http3);
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the max upload body size configuration of a site.
     */
    @Test
    public void testSetSiteConfigClientMaxBodySize() throws Exception {
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withClientMaxBodySize("500m");
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the page compress configuration of a site.
     */
    @Test
    public void testSetSiteConfigCompress() throws Exception {
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withCompress("ON")
                .withCompressMethodArray(Arrays.asList("gzip", "br"));
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the intelligent speed acceleration (ISA) configuration of a site.
     */
    @Test
    public void testSetSiteConfigIsa() throws Exception {
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withIsa("ON");
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the custom HTTP header configuration of a site.
     */
    @Test
    public void testSetSiteConfigHttpHeader() throws Exception {
        HttpHeader responseHeader = new HttpHeader()
                .withType("response")
                .withHeader("Cache-Control")
                .withValue("")
                .withAction("remove");
        HttpHeader originHeader = new HttpHeader()
                .withType("origin")
                .withHeader("Expires")
                .withValue("test")
                .withAction("add");
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withHttpHeader(Arrays.asList(responseHeader, originHeader));
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the status code cache configuration of a site.
     */
    @Test
    public void testSetSiteConfigCacheCodeTtl() throws Exception {
        CacheCodeTtl code404 = new CacheCodeTtl()
                .withType("code")
                .withValue("404")
                .withWeight(100)
                .withOverrideOrigin(true)
                .withTtl(10);
        CacheCodeTtl code400 = new CacheCodeTtl()
                .withType("code")
                .withValue("400")
                .withWeight(100)
                .withOverrideOrigin(true)
                .withTtl(10);
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withCacheCodeTtl(Arrays.asList(code404, code400));
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the gRPC origin configuration of a site.
     */
    @Test
    public void testSetSiteConfigGrpcOrigin() throws Exception {
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withGrpcOrigin("ON");
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the HTTP2 origin configuration of a site.
     */
    @Test
    public void testSetSiteConfigHttp2Origin() throws Exception {
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withHttp2Origin("ON");
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Purge cached resources (url purge).
     */
    @Test
    public void testPurgeUrl() throws Exception {
        PurgeRequest request = new PurgeRequest()
                .withSite(SITE)
                .addTask(new PurgeTask().withUrl("YOUR_URL_1"))
                .addTask(new PurgeTask().withUrl("YOUR_URL_2"));
        PurgeResponse response = eoClient.purge(request);
        System.out.println(response);
    }

    /**
     * Purge cached resources (directory purge).
     */
    @Test
    public void testPurgeDir() throws Exception {
        PurgeRequest request = new PurgeRequest()
                .withSite(SITE)
                .addTask(new PurgeTask().withDirectory("YOUR_DIR_1"))
                .addTask(new PurgeTask().withDirectory("YOUR_DIR_2"));
        PurgeResponse response = eoClient.purge(request);
        System.out.println(response);
    }

    /**
     * Query cache purge task status by site and type and time range.
     */
    @Test
    public void testGetPurgeStatusBySiteAndTypeAndTime() throws Exception {
        GetPurgeStatusRequest request = new GetPurgeStatusRequest()
                .withSite(SITE)
                .withType("directory")
                .withStartTime("2026-07-06T00:00:00Z")
                .withEndTime("2026-07-06T02:35:00Z");
        GetPurgeStatusResponse response = eoClient.getPurgeStatus(request);
        System.out.println(response);
    }

    /**
     * Query cache purge task status by site and id.
     */
    @Test
    public void testGetPurgeStatusBySiteAndId() throws Exception {
        GetPurgeStatusRequest request = new GetPurgeStatusRequest()
                .withSite(SITE)
                .withId("PurgeTaskId");
        GetPurgeStatusResponse response = eoClient.getPurgeStatus(request);
        System.out.println(response);
    }

    /**
     * Prefetch resources into cache.
     */
    @Test
    public void testPrefetch() throws Exception {
        PrefetchRequest request = new PrefetchRequest()
                .withSite(SITE)
                .addTask(new PrefetchTask().withUrl("YOUR_URL_1"))
                .addTask(new PrefetchTask().withUrl("YOUR_URL_2"));
        PrefetchResponse response = eoClient.prefetch(request);
        System.out.println(response);
    }

    /**
     * Query cache prefetch task status by site and time range.
     */
    @Test
    public void testGetPrefetchStatusBySiteAndTime() throws Exception {
        GetPrefetchStatusRequest request = new GetPrefetchStatusRequest()
                .withSite(SITE)
                .withStartTime("2026-07-06T00:00:00Z")
                .withEndTime("2026-07-06T02:40:00Z");
        GetPrefetchStatusResponse response = eoClient.getPrefetchStatus(request);
        System.out.println(response);
    }

    /**
     * Query cache prefetch task status by site and id.
     */
    @Test
    public void testGetPrefetchStatusBySiteAndId() throws Exception {
        GetPrefetchStatusRequest request = new GetPrefetchStatusRequest()
                .withSite(SITE)
                .withId("PrefetchTaskId");
        GetPrefetchStatusResponse response = eoClient.getPrefetchStatus(request);
        System.out.println(response);
    }

    /**
     * Query stat metrics: bandwidth peak of a site.
     */
    @Test
    public void testGetStatMetricPeak() throws Exception {
        GetStatMetricRequest request = new GetStatMetricRequest()
                .withStartTime("2026-06-01T08:56:58Z")
                .withEndTime("2026-07-01T08:57:02Z")
                .withMetrics("sum_bps", "upstream_bps", "download_bps")
                .withShowType("peak")
                .withFilter(new FilterItem()
                        .withKey("site")
                        .withOperation("equal")
                        .withValue(Arrays.asList(SITE)));
        GetStatMetricResponse response = eoClient.getStatMetric(request);
        System.out.println(response);
    }

    /**
     * Query stat metrics: the total data of sum_flow, upstream_flow and download_flow.
     */
    @Test
    public void testGetStatMetricSum() throws Exception {
        GetStatMetricRequest request = new GetStatMetricRequest()
                .withStartTime("2026-06-01T08:56:58Z")
                .withEndTime("2026-07-01T08:57:02Z")
                .withMetrics("sum_flow", "upstream_flow", "download_flow")
                .withShowType("sum")
                .withFilter(new FilterItem()
                        .withKey("site")
                        .withOperation("equal")
                        .withValue(Arrays.asList(SITE)));
        GetStatMetricResponse response = eoClient.getStatMetric(request);
        System.out.println(response);
    }

    /**
     * Query stat metrics: the data of sum_bps.
     */
    @Test
    public void testGetStatMetricSTime() throws Exception {
        GetStatMetricRequest request = new GetStatMetricRequest()
                .withStartTime("2026-06-01T08:56:58Z")
                .withEndTime("2026-07-01T08:57:02Z")
                .withMetrics("sum_bps")
                .withShowType("time")
                .withFilter(new FilterItem()
                        .withKey("site")
                        .withOperation("equal")
                        .withValue(Arrays.asList(SITE)));
        GetStatMetricResponse response = eoClient.getStatMetric(request);
        System.out.println(response);
    }

    /**
     * Query stat metrics: pv time points grouped by status code.
     */
    @Test
    public void testGetStatMetricTimeGroupByCode() throws Exception {
        GetStatMetricRequest request = new GetStatMetricRequest()
                .withStartTime("2026-06-01T08:56:58Z")
                .withEndTime("2026-07-01T08:57:02Z")
                .withMetrics("pv")
                .withShowType("time")
                .withGroup(Arrays.asList("code"))
                .withFilter(new FilterItem()
                        .withKey("site")
                        .withOperation("equal")
                        .withValue(Arrays.asList(SITE)))
                .withLimit(new LimitField().withPageSize(100));
        GetStatMetricResponse response = eoClient.getStatMetric(request);
        System.out.println(response);
    }

    /**
     * Query stat metrics: top hosts by pv.
     */
    @Test
    public void testGetStatMetricTop() throws Exception {
        GetStatMetricRequest request = new GetStatMetricRequest()
                .withStartTime("2026-06-01T08:56:58Z")
                .withEndTime("2026-07-01T08:57:02Z")
                .withMetrics("pv")
                .withShowType("top")
                .withGroup(Arrays.asList("host"))
                .withFilter(new FilterItem()
                        .withKey("site")
                        .withOperation("equal")
                        .withValue(Arrays.asList(SITE)))
                .withLimit(new LimitField().withPageSize(100));
        GetStatMetricResponse response = eoClient.getStatMetric(request);
        System.out.println(response);
    }

    /**
     * Set the rule engine (page rules) configuration of a site.
     */
    @Test
    public void testSetSiteConfigPageRules() throws Exception {
        // 规则引擎需全量设置，否则会覆盖之前的规则
        // 条件组1：path=/test（忽略大小写）且 arg test=abc
        Rule pathRule = new Rule()
                .withMatchFrom("path")
                .withOperator("inValues")
                .withValues(Arrays.asList("/test"))
                .withIgnoreCase(true);
        Rule argRule = new Rule()
                .withMatchFrom("arg")
                .withOperator("inValues")
                .withMatchKey("test")
                .withValues(Arrays.asList("abc"));
        // 条件组2（"或"关系）：path 正则匹配
        Rule regexRule = new Rule()
                .withMatchFrom("path")
                .withOperator("regex")
                .withValues("^/example/test[123]/$");

        Config config = new Config();

        PageRule pageRule = new PageRule()
                .withName("示例")
                .withStatus("ON")
                .withRules(Arrays.asList(
                        Arrays.asList(pathRule, argRule),
                        Arrays.asList(regexRule)))
                .withConfig(config);

        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withPageRules(Arrays.asList(pageRule));
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Query the rule engine (page rules) configuration of a site.
     */
    @Test
    public void testGetSiteConfigPageRules() throws Exception {
        GetSiteConfigRequest request = new GetSiteConfigRequest()
                .withSite(SITE);
        Map<String, Object> pageRules = eoClient.getSiteConfig(request, "pageRules");
        System.out.println(JsonUtils.toJsonPrettyString(pageRules));
    }

    /**
     * Set a page rule that matches remoteIsp and removes an origin request header.
     */
    @Test
    public void testSetSiteConfigPageRulesRemoteIsp() throws Exception {
        // 条件：remoteIsp in ("cm|un")，values 是单个字符串，直接传 String
        Rule ispRule = new Rule()
                .withMatchFrom("remoteIsp")
                .withOperator("inValues")
                .withValues("cm|un");

        // config：自定义 HTTP 头，删除回源请求头 test
        HttpHeader header = new HttpHeader()
                .withType("origin")
                .withHeader("test")
                .withValue("")
                .withAction("remove");
        Config config = new Config()
                .withHttpHeader(Arrays.asList(header));

        PageRule pageRule = new PageRule()
                .withName("test")
                .withStatus("ON")
                .withRules(Arrays.asList(
                        Arrays.asList(ispRule)))
                .withConfig(config);

        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withPageRules(Arrays.asList(pageRule));
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }
}
