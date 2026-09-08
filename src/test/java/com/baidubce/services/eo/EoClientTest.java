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

import java.util.*;

/**
 * Unit test for EO client.
 */
@Ignore
public class EoClientTest {
    private static final String AK = "YOUR_ACCESS_KEY";
    private static final String SK = "YOUR_SECRET_KEY";
    private static final String ENDPOINT = "EO_ENDPOINT";
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
                .withQuery(true)
                .withIgnoreCase(true);
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
     * Set the origin request header configuration of a site.
     */
    @Test
    public void testSetSiteConfigOriginRequest() throws Exception {
        // 回源请求头为全量接口，设置时需将已有配置一并写入
        Map<String, String> addHeaders = new LinkedHashMap<String, String>();
        addHeaders.put("test", "${uri}");
        addHeaders.put("test2", "11");
        Map<String, String> removeHeaders = new LinkedHashMap<String, String>();
        removeHeaders.put("Content-Type", "");

        OriginRequest originRequest = new OriginRequest()
                .withAddHeaders(addHeaders)
                .withRemoveHeaders(removeHeaders);
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withOriginRequest(originRequest);
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Clear the origin request header configuration of a site.
     */
    @Test
    public void testSetSiteConfigOriginRequestClear() throws Exception {
        // 清空回源请求头：传空集合
        OriginRequest originRequest = new OriginRequest()
                .withAddHeaders(Collections.<String, String>emptyMap())
                .withRemoveHeaders(Collections.<String, String>emptyMap());
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withOriginRequest(originRequest);
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the node response header configuration of a site.
     */
    @Test
    public void testSetSiteConfigClientResponse() throws Exception {
        // 节点响应头为全量接口，设置时需将已有配置一并写入
        Map<String, String> addHeaders = new LinkedHashMap<String, String>();
        addHeaders.put("test", "${uri}");
        addHeaders.put("test2", "1");
        Map<String, String> removeHeaders = new LinkedHashMap<String, String>();
        removeHeaders.put("Content-Type", "");

        ClientResponse clientResponse = new ClientResponse()
                .withAddHeaders(addHeaders)
                .withRemoveHeaders(removeHeaders);
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withClientResponse(clientResponse);
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Clear the node response header configuration of a site.
     */
    @Test
    public void testSetSiteConfigClientResponseClear() throws Exception {
        // 清空节点响应头：传空集合
        ClientResponse clientResponse = new ClientResponse()
                .withAddHeaders(Collections.<String, String>emptyMap())
                .withRemoveHeaders(Collections.<String, String>emptyMap());
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withClientResponse(clientResponse);
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the browser cache TTL configuration of a site (follow the origin).
     */
    @Test
    public void testSetSiteConfigClientCacheTtlFollow() throws Exception {
        // 浏览器缓存 TTL：遵循源站
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withClientCacheTtl(new ClientCacheTtl().withMode("follow"));
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the browser cache TTL configuration of a site (custom ttl).
     */
    @Test
    public void testSetSiteConfigClientCacheTtlCustom() throws Exception {
        // 浏览器缓存 TTL：自定义 150 秒
        ClientCacheTtl clientCacheTtl = new ClientCacheTtl()
                .withMode("custom")
                .withTtl(150);
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withClientCacheTtl(clientCacheTtl);
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the webSocket configuration of a site.
     */
    @Test
    public void testSetSiteConfigWebSocket() throws Exception {
        // webSocket 配置：开启，超时 10 秒
        WebSocket webSocket = new WebSocket()
                .withEnabled(true)
                .withTimeout(10);
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withWebSocket(webSocket);
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }


    /**
     * Set the SSL/TLS configuration of a site (all cipher suites).
     */
    @Test
    public void testSetSiteConfigSslAll() throws Exception {
        // 全部加密算法套件，无需传 sslCipherList
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withSslProtocols(Arrays.asList("TLSv1.0", "TLSv1.1", "TLSv1.2", "TLSv1.3"))
                .withSslMode("all");
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the SSL/TLS configuration of a site (custom cipher suites).
     */
    @Test
    public void testSetSiteConfigSslCustom() throws Exception {
        // 自定义加密算法套件，必须同时设置 TLSv1.2、TLSv1.3
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withSslProtocols(Arrays.asList("TLSv1.2", "TLSv1.3"))
                .withSslMode("custom")
                .withSslCipherList("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256:"
                        + "TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256");
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Turn off the SSL/TLS configuration of a site.
     */
    @Test
    public void testSetSiteConfigSslOff() throws Exception {
        // 关闭 SSL/TLS 安全配置
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .clearSslConfig();
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the OCSP configuration of a site.
     */
    @Test
    public void testSetSiteConfigOcsp() throws Exception {
        // OCSP 配置：开启
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withOcsp("OFF");
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Set the real ip configuration of a site.
     */
    @Test
    public void testSetSiteConfigRealIp() throws Exception {
        // 用户 ip 获取配置：开启并使用 True-Client-Ip
        RealIp realIp = new RealIp()
                .withEnabled(true)
                .withName("True-Client-Ip");
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withRealIp(realIp);
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Turn off the real ip configuration of a site.
     */
    @Test
    public void testSetSiteConfigRealIpOff() throws Exception {
        // 关闭用户 ip 获取
        RealIp realIp = new RealIp()
                .withEnabled(false);
        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withRealIp(realIp);
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
        // 条件组1：组内多个条件是「且」关系
        Rule pathRule = new Rule()
                .withMatchFrom("path")
                .withOperator("inValues")
                .withValues(Arrays.asList("/test"))
                .withIgnoreCase(true);
        Rule directoryRule = new Rule()
                .withMatchFrom("directory")
                .withOperator("inValues")
                .withValues(Arrays.asList("/test/"));
        Rule argRule = new Rule()
                .withMatchFrom("arg")
                .withOperator("inValues")
                .withMatchKey("test")
                .withValues(Arrays.asList("abc"));
        Rule suffixRule = new Rule()
                .withMatchFrom("suffix")
                .withOperator("inValues")
                .withValues(Arrays.asList("jpg"));
        Rule headerRule = new Rule()
                .withMatchFrom("header")
                .withOperator("inValues")
                .withMatchKey("user-agent")
                .withValues(Arrays.asList("test"));
        Rule fullUrlRule = new Rule()
                .withMatchFrom("fullUrl")
                .withOperator("inValues")
                .withValues(Arrays.asList("/test/test.txt"));
        Rule basenameRule = new Rule()
                .withMatchFrom("basename")
                .withOperator("inValues")
                .withValues(Arrays.asList("test.mp4"));
        // remoteGeos 的 values 是对象列表：中国内地用 pro，其他国家用 cty
        Map<String, String> proGeo = new LinkedHashMap<String, String>();
        proGeo.put("pro", "beijing");
        Map<String, String> ctyGeo = new LinkedHashMap<String, String>();
        ctyGeo.put("cty", "bt");
        Rule remoteGeosRule = new Rule()
                .withMatchFrom("remoteGeos")
                .withOperator("inValues")
                .withValues(Arrays.asList(proGeo, ctyGeo));
        Rule remoteAddrsRule = new Rule()
                .withMatchFrom("remoteAddrs")
                .withOperator("inValues")
                .withValues(Arrays.asList("1.2.3.4", "172.16.0.0/12"));
        // remoteIsp 的 values 是单个字符串
        Rule remoteIspRule = new Rule()
                .withMatchFrom("remoteIsp")
                .withOperator("inValues")
                .withValues("cm|un|ct");
        Rule methodRule = new Rule()
                .withMatchFrom("method")
                .withOperator("inValues")
                .withValues(Arrays.asList("GET", "POST"));
        Rule cookieRule = new Rule()
                .withMatchFrom("cookie")
                .withOperator("inValues")
                .withMatchKey("test")
                .withValues(Arrays.asList("abc"));
        Rule hostRule = new Rule()
                .withMatchFrom("host")
                .withOperator("inValues")
                .withValues(Arrays.asList("1.test.com"));

        // 条件组2：与条件组1之间是「或」关系
        Rule regexRule = new Rule()
                .withMatchFrom("path")
                .withOperator("regex")
                .withValues("^/example/test[123]/$");
        Rule argExistsRule = new Rule()
                .withMatchFrom("arg")
                .withOperator("exists")
                .withMatchKey("test");

        PageRule pageRule = new PageRule()
                .withName("示例")
                .withStatus("ON")
                .withRules(Arrays.asList(
                        Arrays.asList(pathRule, directoryRule, argRule, suffixRule, headerRule,
                                fullUrlRule, basenameRule, remoteGeosRule, remoteAddrsRule,
                                remoteIspRule, methodRule, cookieRule, hostRule),
                        Arrays.asList(regexRule, argExistsRule)))
                .withConfig(buildFullRuleConfig());

        SetSiteConfigRequest request = new SetSiteConfigRequest()
                .withSite(SITE)
                .withPageRules(Arrays.asList(pageRule));
        SetSiteConfigResponse response = eoClient.setSiteConfig(request);
        System.out.println(response);
    }

    /**
     * Build a rule engine config that covers all the supported config items.
     */
    private Config buildFullRuleConfig() {
        // 节点缓存
        CacheTtl cacheTtl = new CacheTtl()
                .withValue("/")
                .withWeight(100)
                .withOverrideOrigin(false)
                .withTtl(0)
                .withType("path");
        // 自定义 cache key
        RuleCacheKey cacheKey = new RuleCacheKey()
                .withQuery(false)
                .withIgnoreCase(true)
                .withIncludeArgs(Arrays.asList("a", "b"));
        // 浏览器缓存
        ClientCacheTtl clientCacheTtl = new ClientCacheTtl()
                .withMode("custom")
                .withTtl(150);
        // 状态码缓存
        CacheCodeTtl code404 = new CacheCodeTtl()
                .withValue("404")
                .withWeight(100)
                .withOverrideOrigin(true)
                .withTtl(10)
                .withType("code");
        CacheCodeTtl code400 = new CacheCodeTtl()
                .withValue("400")
                .withWeight(100)
                .withOverrideOrigin(true)
                .withTtl(10)
                .withType("code");
        // HTTP 头
        Map<String, String> addHeaders = new LinkedHashMap<String, String>();
        addHeaders.put("test", "${uri}");
        addHeaders.put("test2", "11");
        Map<String, String> removeHeaders = new LinkedHashMap<String, String>();
        removeHeaders.put("Content-Type", "");

        return new Config()
                .withCacheTtl(Arrays.asList(cacheTtl))
                .withCacheKey(cacheKey)
                .withClientCacheTtl(clientCacheTtl)
                .withOfflineMode("OFF")
                .withCacheCodeTtl(Arrays.asList(code404, code400))
                .withRefreshRevalidate(new RefreshRevalidate().withEnabled(true))
                .withHttpToHttpsEnabled("ON")
                .withHttpToHttpsCode("302")
                .withHsts(new Hsts().withMaxAge(-1).withIncludeSubDomains(false).withPreload(false))
                .withSslProtocols(Arrays.asList("TLSv1.0", "TLSv1.1", "TLSv1.2", "TLSv1.3"))
                .withSslMode("custom")
                .withSslCipherList("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256")
                .withOcsp("ON")
                .withIsa("ON")
                .withHttp2Disable("OFF")
                .withHttp3(new Http3().withEnable(false))
                .withWebSocket(new WebSocket().withEnabled(true).withTimeout(10))
                .withHttp2Origin("ON")
                .withClientMaxBodySize("1026")
                .withCompress("ON")
                .withCompressMethodArray(Arrays.asList("gzip", "br"))
                .withClientResponse(new ClientResponse()
                        .withAddHeaders(addHeaders)
                        .withRemoveHeaders(removeHeaders))
                .withOriginRequest(new OriginRequest()
                        .withAddHeaders(addHeaders)
                        .withRemoveHeaders(removeHeaders))
                // originConfig 与 originPool 不能同时设置，这里用 originConfig
                .withOriginConfig(Arrays.asList(new OriginConfig()
                        .withAddr("1.1.1.1")
                        .withType("IP")
                        .withHttpPort(80)
                        .withUpstreamProtocol("http")
                        .withHost("test.eo.com")))
                .withOriginTimeout(new OriginTimeout().withLoadTimeout(10).withConnectTimeout(5))
                .withOriginRedirectOptions(new OriginRedirectOptions()
                        .withEnableRedirectFollow("ON")
                        .withMaxRedirectFollowCount(2))
                .withOriginOptions(new OriginOptions().withRange("force_all").withPartSize("512k"))
                .withRealIp(new RealIp().withEnabled(true).withName("True-Client-Ip"))
                .withErrorPage(Arrays.asList(
                        new ErrorPage().withCode(400).withUrl("http://test.eo.com/400.html"),
                        new ErrorPage().withCode(403).withUrl("http://test.eo.com/403.html")))
                .withTrafficLimit(new TrafficLimit()
                        .withEnable(true)
                        .withLimitRate(1)
                        .withLimitStartHour(10)
                        .withLimitEndHour(19)
                        .withLimitRateUnit("k"))
                .withAntiHotLink(new AntiHotLink()
                        .withAntiType("typeA")
                        .withSecretKey("xxx")
                        .withNewsecretKey("xxx")
                        .withTimeout(1800)
                        .withTimestampFormat("dec")
                        .withAuthArg("auth_key"))
                .withOriginArg(new OriginArg()
                        .withIgnore(true)
                        .withArgs(Collections.<String>emptyList()))
                .withUrlRules(Arrays.asList(new UrlRules()
                        .withScheme("http")
                        .withHost("test.eo.com")
                        .withStatus(302)
                        .withQuery("OFF")
                        .withDstPath("/test/1.txt")));
    }

    @Test
    public void testGetSiteConfigPageRules() throws Exception {
        GetSiteConfigRequest request = new GetSiteConfigRequest()
                .withSite(SITE);
        Map<String, Object> pageRules = eoClient.getSiteConfig(request, "pageRules");
        System.out.println(JsonUtils.toJsonPrettyString(pageRules));
    }
}
