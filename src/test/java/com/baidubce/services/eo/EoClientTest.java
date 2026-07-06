package com.baidubce.services.eo;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eo.model.logmodel.GetDomainListLogRequest;
import com.baidubce.services.eo.model.logmodel.GetDomainListLogResponse;
import com.baidubce.services.eo.model.site.*;
import com.baidubce.util.JsonUtils;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * Unit test for GEO client.
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
}
