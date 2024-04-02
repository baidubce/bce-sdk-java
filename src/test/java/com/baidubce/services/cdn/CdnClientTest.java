/*
 * Copyright 2016-2019 Baidu, Inc.
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
package com.baidubce.services.cdn;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.cdn.model.*;
import com.baidubce.services.cdn.model.certificate.*;
import com.baidubce.services.cdn.model.domain.*;
import com.baidubce.services.cdn.model.stat.*;
import com.baidubce.services.cdn.model.cache.GetPrefetchStatusResponse;
import com.baidubce.services.cdn.model.cache.PrefetchTask;
import com.baidubce.services.cdn.model.dsa.DSA;
import com.baidubce.services.cdn.model.dsa.DSARule;
import com.baidubce.services.cdn.model.dsa.GetDsaDomainListResponse;
import com.baidubce.services.cdn.model.dsa.SetDomainDsaRequest;
import com.baidubce.services.cdn.model.dsa.SetDsaRequest;
import com.baidubce.services.cdn.model.logmodel.GetDomainListLogRequest;
import com.baidubce.services.cdn.model.logmodel.GetDomainListLogResponse;
import com.baidubce.services.cdn.model.util.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Unit test for CDN client.
 */

public class CdnClientTest {

    private static final String AK = "4cd52fbc873947e6b88be4e54e3e099b";
    private static final String SK = "09ac6fa3a84f45c29f5411a27202bdcc";
    private static final String ENDPOINT = "http://bjyz-opencdn-rest007.bjyz.baidu.com:8899";
    private static final String DOMAIN = "test875.duanhuiyan.top";
    private static final String TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm";

    private CdnClient cdnClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(ENDPOINT);
        cdnClient = new CdnClient(config);
    }

    /**
     * create domain
     *
     * @throws Exception
     */
//    @Test
//    public void testCreateDomain() {
//        ArrayList<OriginPeer> origin = new ArrayList<OriginPeer>();
//        origin.add(new OriginPeer().withPeer("1.2.3.4"));
//        origin.add(new OriginPeer().withPeer("2.3.4.5"));
//
//        CreateDomainRequest request = new CreateDomainRequest()
//                .withDomain(DOMAIN)
//                .withOrigin(origin);
//        CreateDomainResponse response = cdnClient.createDomain(request);
//        System.out.println(response.getCname());
//    }

    /**
     * Disable an existing domain acceleration.
     */
//    @Test
//    public void testDisableDomain() {
//        cdnClient.disableDomain(DOMAIN);
//    }

    /**
     * Enable an existing domain acceleration.
     */
    @Test
    public void testEnableDomain() {
        cdnClient.enableDomain(DOMAIN);
    }

    /**
     * Delete an existing domain acceleration.
     */
//    @Test
//    public void testDeleteDomain() {
//        cdnClient.deleteDomain(DOMAIN);
//    }

    @Test
    public void testListDomains() throws Exception {
        ListDomainsResponse response = cdnClient.listDomains();
        System.out.println(response);
        Assert.assertFalse(response.getDomains().isEmpty());
        for (Domain domain : response.getDomains()) {
            System.out.println(domain.getName());
        }
    }

    /**
     * cdnClient.a list of user's all CDN domains that include domain and domain status
     * support domain name fuzzy matching filter and domain status filter
     */
    @Test
    public void getUserDomains() {
        GetUserDomainResponse result = cdnClient.getUserDomains("RUNNING");
        Assert.assertFalse(result.getDomains().isEmpty());
        for (UserDomain domain : result.getDomains()) {
            System.out.println(domain.getDomain());
        }
    }

    /**
     * Query whether the domain name can be added
     */
    @Test
    public void testCheckDomainValid() {
        CheckDomainValidResponse result = cdnClient.checkDomainValid(DOMAIN);
        System.out.println(result);

        if (!result.isValid()) {
            Assert.assertFalse(result.isValid());
            System.out.println(result.getMessage());
        }
    }

    @Test
    public void testCheckIcpStatus() {
        GetIcpResponse result = cdnClient.getIcpStatus(DOMAIN);
        System.out.println(result.getIcpStatus());
    }

    @Test
    public void testGetDomainConfigKeys() {
        DomainConfigKeysResponse response = cdnClient.getDomainConfigKeys(DOMAIN);
        System.out.println(response.getConfigs());
        Assert.assertFalse(response.getConfigs().isEmpty());
    }

    /**
     * 重点测试
     * @throws Exception
     */
//    @Test
//    public void testCopyDomainConfig() throws Exception {
//        List<String> domains = new ArrayList<String>() {
//            {
//                add("my.test2.com");
//                add("my.test3.com");
//                add("my.test4.com");
//            }
//        };
//
//        List<String> configNames = new ArrayList<String>() {
//            {
//                add("originConfig");
//                add("compress");
//                add("setHttpHeader");
//            }
//        };
//        CopyDomainTaskResponse copyDomainConfigResponse = cdnClient.copyDomainConfig(DOMAIN, domains, configNames);
//        System.out.println(copyDomainConfigResponse);
//
//        String taskId = copyDomainConfigResponse.getTaskId();
//        Assert.assertNotNull(taskId);
//
//        CopyDomainTaskStatusResponse status = cdnClient.getCopyDomainStatus(taskId);
//        System.out.println(status.getStatus());
//    }

    @Test
    public void testGetDomainConfig() {
        GetDomainConfigResponse response = cdnClient.getDomainConfig(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testSetDomainOrigin() {
        SetDomainOriginRequest request = new SetDomainOriginRequest().withDomain(DOMAIN).withFollow302(true);
        ArrayList<OriginPeer> origin = new ArrayList<OriginPeer>();
        origin.add(new OriginPeer().withPeer("3.4.5.6").withHost("a.com").withWeight(50));
        origin.add(new OriginPeer().withPeer("5.4.3.2").withHost("a.com").withWeight(50));
        request.setOrigin(origin);
        CommonResponse response = cdnClient.setDomainOrigin(request);
        System.out.println(response);
    }

    @Test
    public void testGetDomainOrigin() {
        GetDomainOriginResponse response = cdnClient.getDomainOrigin(DOMAIN);
        System.out.println(response);
    }

    /**
     * Update RangeSwitch of specified domain acceleration.
     */
    @Test
    public void testSetDomainRangeSwitch() {
        cdnClient.setDomainRangeSwitch(DOMAIN, true);
    }

    /**
     * Get RangeSwitch of specified domain acceleration.
     */
    @Test
    public void testGetDomainRangeSwitch() {
        GetDomainRangeSwitchResponse response = cdnClient.getDomainRangeSwitch(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getRangeSwitch());
    }

    /**
     * set ClientIp config of specified domain acceleration.
     */
    @Test
    public void testSetDomainClientIp() {
        ClientIp clientIp = new ClientIp().withEnabled(true).withName("X-Real-IP");
        CommonResponse response = cdnClient.setDomainClientIp(new SetDomainClientIpRequest()
                .withDomain(DOMAIN).withClientIp(clientIp));
        System.out.println(response.getStatus());
    }

    @Test
    public void testGetDomainClientIp() {
        GetDomainClientIpResponse response = cdnClient.getDomainClientIp(DOMAIN);
        System.out.println(response);
    }

    /**
     * setDomainOriginProtocol
     */
    @Test
    public void testSetDomainOriginProtocol() {
        OriginProtocol originProtocol = new OriginProtocol().withValue("http");
        CommonResponse response = cdnClient.setDomainOriginProtocol(new SetDomainOriginProtocolRequest()
                .withDomain(DOMAIN)
                .withOriginProtocol(originProtocol));
        System.out.println(response);
    }

    /**
     * getDomainOriginProtocol
     */
    @Test
    public void testGetDomainOriginProtocol() {
        GetDomainOriginProtocolResponse response = cdnClient.getDomainOriginProtocol(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    /**
     * Update RetryOrigin of specified domain acceleration.
     */
    @Test
    public void testSetDomainRetryOrigin() {
        RetryOrigin retryOrigin = new RetryOrigin().addCode(500).addCode(504);
        SetDomainRetryOriginRequest request = new SetDomainRetryOriginRequest()
                .withDomain(DOMAIN)
                .withRetryOrigin(retryOrigin);
        CommonResponse response = cdnClient.setDomainRetryOrigin(request);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    /**
     * get domain RetryOrigin
     */
    @Test
    public void testGetDomainRetryOrigin() {
        GetDomainRetryOriginResponse response = cdnClient.getDomainRetryOrigin(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testSetDomainOriginTimeout(){
        SetDomainOriginTimeoutRequest request = new SetDomainOriginTimeoutRequest()
                .withDomain(DOMAIN)
                .withOriginTimeout(new OriginTimeout(6, 10));
        CommonResponse response = cdnClient.setDomainOriginTimeout(request);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetDomainOriginTimeout() {
        GetDomainOriginTimeoutResponse response = cdnClient.getDomainOriginTimeout(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testSetDomainOriginFixedISP(){
        SetDomainOriginFixedIspRequest request = new SetDomainOriginFixedIspRequest()
                .withDomain(DOMAIN)
                .withOriginFixedISP(true);
        CommonResponse response = cdnClient.setDomainOriginFixedISP(request);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetDomainOriginFixedISP() {
        GetDomainOriginFixedIspResponse response = cdnClient.getDomainOriginFixedISP(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testSetDomainRefererACL() {
        RefererACL acl = new RefererACL()
                .addBlackList("http://a.com")
                .addBlackList("http://b.com").withAllowEmpty(false);
        SetDomainRefererACLRequest request = new SetDomainRefererACLRequest()
                .withDomain(DOMAIN)
                .withRefererACL(acl);
        CommonResponse response = cdnClient.setDomainRefererACL(request);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    /**
     * Get RefererACL rules of specified domain acceleration.
     */
    @Test
    public void testGetDomainRefererACL() {
        GetDomainRefererACLResponse response = cdnClient.getDomainRefererACL(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testSetDomainIpACL() {
        IpACL acl = new IpACL()
                .addBlackList("1.2.3.4")
                .addBlackList("5.6.7.0/24");
        SetDomainIpACLRequest request = new SetDomainIpACLRequest()
                .withDomain(DOMAIN)
                .withIpACL(acl);
        CommonResponse response = cdnClient.setDomainIpACL(request);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    /**
     * Get IpACL rules of specified domain acceleration.
     */
    @Test
    public void testGetDomainIpACL() {
        GetDomainIpACLResponse response = cdnClient.getDomainIpACL(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    /**
     * set cors config of specified domain acceleration.
     */
    @Test
    public void testSetDomainCors() {
        Cors cors = new Cors().withAllow("on")
                .addOriginList("http://www.baidu.com").addOriginList("http://*.bce.com");
        CommonResponse response = cdnClient.setDomainCors(new SetDomainCorsRequest()
                .withDomain(DOMAIN).withCors(cors));
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * Get Cors config information of a domain
     */
    @Test
    public void testGetDomainCors() {
        GetDomainCorsResponse response = cdnClient.getDomainCors(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    /**
     * trafficLimit
     */
    @Test
    public void testSetDomainTrafficLimit() {
        TrafficLimit trafficLimit = new TrafficLimit().withEnable(true)
                .withLimitRate(10485760).withLimitStartHour(10).withLimitEndHour(19).withLimitRateAfter(0)
                .withTrafficLimitArg("rate").withTrafficLimitUnit("m");
        SetDomainTrafficLimitRequest request = new SetDomainTrafficLimitRequest().withDomain(DOMAIN)
                .withTrafficLimit(trafficLimit);
        CommonResponse response = cdnClient.setDomainTrafficLimit(request);
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * trafficLimit
     */
    @Test
    public void testGetDomainTrafficLimit() {
        GetDomainTrafficLimitResponse response = cdnClient.getDomainTrafficLimit(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getTrafficLimit());
    }

    @Test
    public void testSetRequestAuth() {
        RequestAuth requestAuth = new RequestAuth()
                .withType("a")
                .withKey1("secretkey1");
        SetRequestAuthRequest request = new SetRequestAuthRequest()
                .withDomain(DOMAIN)
                .withRequestAuth(requestAuth);
        CommonResponse response = cdnClient.setDomainRequestAuth(request);
        System.out.println(response);
        Assert.assertNotNull(response);
    }


    /**
     * set AccessLimit config of specified domain acceleration.
     */
    @Test
    public void testSetDomainAccessLimit() {
        AccessLimit accessLimit = new AccessLimit().withLimit(200).withEnabled(true);
        CommonResponse response = cdnClient.setDomainAccessLimit(new SetDomainAccessLimitRequest()
                .withDomain(DOMAIN).withAccessLimit(accessLimit));
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    /**
     * Get AccessLimit config information of specified domain acceleration.
     */
    @Test
    public void testGetDomainAccessLimit() {
        GetDomainAccessLimitResponse response = cdnClient.getDomainAccessLimit(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getAccessLimit());
    }

    /**
     * trafficLimit
     */
    @Test
    public void testSetDomainUaAcl() {
        UaAcl uaAcl = new UaAcl().addBlackList("MQQBrowser/5.3/Mozilla/5.0")
                .addBlackList("Mozilla/5.0 (Linux; Android 7.0");
        SetDomainUaAclRequest request = new SetDomainUaAclRequest().withDomain(DOMAIN)
                .withUaAcl(uaAcl);
        CommonResponse response = cdnClient.setDomainUaAcl(request);
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * ua acl
     */
    @Test
    public void testGetDomainUaAcl() {
        GetDomainUaAclResponse response = cdnClient.getDomainUaAcl(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getUaAcl());
    }


    @Test
    public void testSetDomainCacheTTL() {
        SetDomainCacheTTLRequest request = new SetDomainCacheTTLRequest()
                .withDomain(DOMAIN)
                .addCacheTTL(new CacheTTL().withType("suffix").withValue(".jpg").withTtl(3600))
                .addCacheTTL(new CacheTTL().withType("suffix").withValue(".png").withTtl(3600))
                .addCacheTTL(new CacheTTL().withType("path").withValue("/").withTtl(86400).withWeigth(5));
        CdnResponse response = cdnClient.setDomainCacheTTL(request);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    /**
     * Get cache policies of specified domain acceleration.
     */
    @Test
    public void getDomainCacheTTL() {
        GetDomainCacheTTLResponse response = cdnClient.getDomainCacheTTL(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getCacheTTL());
    }

    @Test
    public void testSetDomainCacheFullUrl() {
        CommonResponse response = cdnClient.setDomainCacheFullUrl(new SetDomainCacheFullUrlRequest()
                .withDomain(DOMAIN).withCacheFullUrl(false).addCacheUrlArgs("a"));
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * search domain's rule of caching filter parameter
     */
    @Test
    public void testGetDomainCacheFullUrl() {
        GetDomainCacheFullUrlResponse response = cdnClient.getDomainCacheFullUrl(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getCacheUrlArgs());
    }

    /**
     * add website error page to deal with exception.
     */
    @Test
    public void testSetDomainErrorPage() {
        ErrorPage errorPage404 = new ErrorPage(404, "/customer_404.html", 302);
        ErrorPage errorPage403 = new ErrorPage(403, "/custom_403.html");
        CommonResponse response = cdnClient.setDomainErrorPage(new SetDomainErrorPageRequest()
                .withDomain(DOMAIN).addErrorPage(errorPage404).addErrorPage(errorPage403));
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * search domain's custom error page
     */
    @Test
    public void testGetDomainErrorPage() {
        GetDomainErrorPageResponse response = cdnClient.getDomainErrorPage(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getErrorPage());
    }

    /**
     * Update MobileAccess of specified domain acceleration.
     */
    @Test
    public void testSetDomainMobileAccess() {
        MobileAccess mobileAccess = new MobileAccess().withDistinguishClient(true);
        CommonResponse response = cdnClient.setDomainMobileAccess(new SetDomainMobileAccessRequest()
                .withDomain(DOMAIN).withMobileAccess(mobileAccess));
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * Get MobileAccess of specified domain acceleration.
     */
    @Test
    public void testGetDomainMobileAccess() {
        GetDomainMobileAccessResponse response = cdnClient.getDomainMobileAccess(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getMobileAccess());
    }

    /**
     * Update CacheShare of specified domain acceleration.
     */
    @Test
    public void testSetDomainCacheShare() {
        CacheShare cacheShare = new CacheShare().withEnabled(true).withDomain("af111111.duanhuiyan.top");
        CommonResponse response = cdnClient.setDomainCacheShare(
                new SetDomainCacheShareRequest().withDomain(DOMAIN).withCacheShare(cacheShare));
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * get domain CacheShare
     */
    @Test
    public void testGetDomainCacheShare() {
        GetDomainCacheShareResponse response = cdnClient.getDomainCacheShare(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testSetDomainUrlRules() {
        UrlRule urlRule = new UrlRule().withAction("redirect")
                .withSrc("/data/(.*)").withDst("/data2222");
        CommonResponse response = cdnClient.setDomainUrlRules(
                new SetDomainUrlRulesRequest().withDomain(DOMAIN).addUrlRule(urlRule));
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    @Test
    public void testGetDomainUrlRules(){
        GetDomainUrlRulesResponse response = cdnClient.getDomainUrlRules(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getUrlRules());
    }


    /**
     * Update HttpHeader of specified domain acceleration.
     */
    @Test
    public void testSetDomainHttpHeader() {
        HttpHeader httpHeader = new HttpHeader();
        httpHeader.withType("origin").withHeader("x-auth-cn").withValue("1.1.1.1").withAction("add");
        HttpHeader httpHeader2 = new HttpHeader();
        httpHeader2.withType("response").withHeader("content-type")
                .withValue("application/octet-stream").withAction("add");
        CommonResponse response = cdnClient.setDomainHttpHeader(new SetDomainHttpHeaderRequest().withDomain(DOMAIN)
                .addHttpHeader(httpHeader).addHttpHeader(httpHeader2));
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * Get HttpHeader of specified domain acceleration.
     */
    @Test
    public void getDomainHttpHeader() {
        GetDomainHttpHeaderResponse response = cdnClient.getDomainHttpHeader(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getHttpHeader());
    }

    /**
     * Update SeoSwitch of specified domain acceleration.
     */
    @Test
    public void testSetDomainSeoSwitch() {
        SeoSwitch seoSwitch = new SeoSwitch()
                .withDiretlyOrigin("OFF").withPushRecord("OFF");

        CommonResponse response = cdnClient.setDomainSeoSwitch(new SetDomainSeoSwitchRequest()
                .withDomain(DOMAIN).withSeoSwitch(seoSwitch));
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * Get SeoSwitch of specified domain acceleration.
     */
    @Test
    public void testGetDomainSeoSwitch() {
        GetDomainSeoSwitchResponse response = cdnClient.getDomainSeoSwitch(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getSeoSwitch());
    }

    /**
     * Update MediaDrag of specified domain acceleration.
     */
    @Test
    public void testSetDomainMeiaDrag() {
        MediaDragConf mediaDragConf = new MediaDragConf();
        MediaDrag mp4 = new MediaDrag().addFileSuffix("mp4").addFileSuffix("m4a")
                .withStartArgName("startIndex").withDragMode("second");
        MediaDrag flv = new MediaDrag().withDragMode("byteAV");

        CommonResponse response = cdnClient.setDomainMediaDrag(new SetDomainMediaDragRequest()
                .withDomain(DOMAIN)
                .withMediaDragConf(mediaDragConf.withMp4(mp4).withFlv(flv)));
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * Get MediaDrag of specified domain acceleration.
     */
    @Test
    public void testGetDomainMediaDrag() {
        GetDomainMediaDragResponse response = cdnClient.getDomainMediaDrag(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getMediaDragConf());
    }

    /**
     * Update FileTrim of specified domain acceleration.
     */
    @Test
    public void testSetDomainFileTrim() {
        cdnClient.setDomainFileTrim(DOMAIN, true);
    }

    /**
     * Get FileTrim of specified domain acceleration.
     */
    @Test
    public void testGetDomainFileTrim() {
        GetDomainFileTrimResponse response = cdnClient.getDomainFileTrim(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.isFileTrim());
    }

    /**
     * Update Compress of specified domain acceleration.
     */
    @Test
    public void testSetDomainCompress() {
        Compress compress = new Compress().withAllow(true).withType("br");
        CommonResponse response = cdnClient.setDomainCompress(new SetDomainCompressRequest()
                .withDomain(DOMAIN).withCompress(compress));

        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * Get Compress of specified domain acceleration.
     */
    @Test
    public void testGetDomainCompress() {
        GetDomainCompressResponse response = cdnClient.getDomainCompress(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getCompress());
    }

    /**
     * Update QUIC of specified domain acceleration.
     */
    @Test
    public void testSetDomainQUIC() {
        CommonResponse response = cdnClient.setDomainQUICSwitch(DOMAIN, false);
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * get domain QUICSwitch
     */
    @Test
    public void testGetDomainQUICSwitch() {
        GetDomainQUICSwitchResponse response = cdnClient.getDomainQUICSwitch(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.isQuic());
    }

    /**
     * Update IPv6Dispatch of specified domain acceleration.
     */
    @Test
    public void testSetDomainIPv6Dispatch() {
        Enable enable = new Enable().withEnable(true);
        CommonResponse response = cdnClient.setDomainIPv6Dispatch(new SetDomainIPv6DispatchRequest()
                .withDomain(DOMAIN).withIPv6Dispatch(enable));
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * get domain IPv6Dispatch
     */
    @Test
    public void testGetDomainIPv6Dispatch() {
        GetDomainIPv6DispatchResponse response = cdnClient.getDomainIPv6Dispatch(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getIpv6Dispatch());
    }

    /**
     * Update OfflineMode of specified domain acceleration.
     */
    @Test
    public void testSetDomainOfflineMode() {
        CommonResponse response = cdnClient.setDomainOfflineModeSwitch(DOMAIN, false);
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * get domain OfflineMode
     */
    @Test
    public void testGetDomainOfflineMode() {
        GetDomainOfflineModeSwitchResponse response = cdnClient.getDomainOfflineModeSwitch(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.isOfflineMode());
    }

    @Test
    public void testSetDomainLimitBandwidth() {
        LimitBandwidth limitBandwidth = new LimitBandwidth()
                .withEnabled(true)
                .withActived(true)
                .withThreshold("2G")
                .withAction("deny");

        CommonResponse response = cdnClient.setDomainLimitBandwidth(new SetDomainLimitBandwidthRequest()
                .withDomain(DOMAIN).withLimitBandwidth(limitBandwidth));
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    @Test
    public void testGetDomainLimitBandwidth() {
        GetDomainLimitBandwidthResponse response = cdnClient.getDomainLimitBandwidth(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getLimitBandwidth());
    }

    /**
     * 需要重点测试，需要开通https
     */
    @Test
    public void testSetHttpsConfig() {
        HttpsConfig https = new HttpsConfig()
                .withEnabled(true)
                .withHttpsRedirectCode(302)
                .withHttpRedirectCode(302)
                .withCertId("cert-txngb20fse0h");
        SetDomainHttpsConfigRequest request = new SetDomainHttpsConfigRequest()
                .withDomain(DOMAIN)
                .withHttps(https);
        CommonResponse response = cdnClient.setDomainHttpsConfig(request);
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * 需要重点测试，需要开通https
     * Update HSTS of specified domain acceleration.
     */
    @Test
    public void testSetDomainHSTS() {
        Hsts hsts = new Hsts().withMaxAge(100);
        CommonResponse response = cdnClient.setDomainHSTS(
                new SetDomainHSTSRequest().withDomain(DOMAIN).withHSTS(hsts));
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * get domain HSTS
     */
    @Test
    public void testGetDomainHSTS() {
        GetDomainHSTSResponse response = cdnClient.getDomainHSTS(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getHSTS());
    }

    /**
     * Update OCSP of specified domain acceleration.
     */
    @Test
    public void testSetDomainOCSPSwitch() {
        CommonResponse response = cdnClient.setDomainOCSPSwitch(DOMAIN, true);
        System.out.println(response);
        Assert.assertNotNull(response.getStatus());
    }

    /**
     * get domain OCSP
     */
    @Test
    public void testGetDomainOCSPSwitch() {
        GetDomainOCSPSwitchResponse response = cdnClient.getDomainOCSPSwitch(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.isOcsp());
    }

    /**
     * Update HSTS of specified domain acceleration.
     */
    @Test
    public void testSetDomainCert() {
        Cert cert = new Cert().withCertName("test-certName")
                .withCertServerData("-----BEGIN CERTIFICATE----END CERTIFICATE-----")
                .withCertPrivateData("-----BEGIN RSA PRIVATE KEY----END RSA PRIVATE KEY-----")
                .withCertLinkData("x").withCertType(1);
        SetDomainCertRequest request = new SetDomainCertRequest().withDomain(DOMAIN)
                .withHttpsEnable("ON").withCertificate(cert);
        SetDomainCertResponse response = cdnClient.setDomainCert(request);
        System.out.println(response);
        Assert.assertNotNull(response.getCertId());
    }

    /**
     * 证书未测试
     */
    @Test
    public void testGetDomainCert() {
        GetDomainCertResponse response = cdnClient.getDomainCert(DOMAIN);
        System.out.println(response);
        Assert.assertNotNull(response.getCertCommonName());
    }

    /**
     * get domain HSTS
     */
    @Test
    public void testDelDomainCert() {
        CdnResponse response = cdnClient.deleteDomainCert(DOMAIN);
        System.out.println(response);
    }

    @Test
    public void testGetHttpsDomains() {
        GetHttpsDomainRequest request = new GetHttpsDomainRequest(1, 10);
        GetHttpsDomainResponse response = cdnClient.getHttpsDomains(request);
        System.out.println(response);
        Assert.assertNotNull(response.getRows());
    }

    /**
     * 需要证书
     */
    @Test
    public void testBathUploadDomainCert() {
        List<String> domains = new ArrayList<String>();
        domains.add("test.wonter.net");
        Cert cert = new Cert().withCertName("test-certName")
                .withCertServerData("-----BEGIN CERTIFICATE----END CERTIFICATE-----")
                .withCertPrivateData("-----BEGIN RSA PRIVATE KEY----END RSA PRIVATE KEY-----")
                .withCertLinkData("x").withCertType(1);
        BatchUploadCertRequest request = new BatchUploadCertRequest()
                .withDomains(domains).withCertificate(cert);
        cdnClient.bathUploadDomainCert(request);
    }

    @Test
    public void testPurge() {
        PurgeRequest request = new PurgeRequest()
                .addTask(new PurgeTask().withUrl("http://" + DOMAIN + "/url"))
                .addTask(new PurgeTask().withDirectory("http://" + DOMAIN + "/directory/"));
        PurgeResponse response = cdnClient.purge(request);
        System.out.println(response);
        Assert.assertTrue(response.getId().length() > 0);

        cdnClient.getPurgeStatus(new GetPurgeStatusRequest().withId(response.getId()));
    }

    /**
     * Post purge request
     */
    @Test
    public void testPurgeDirectory() {
        PurgeResponse response = cdnClient.purgeDirectory("http://" + DOMAIN + "/123/");
        System.out.println(response);
        Assert.assertTrue(response.getId().length() > 0);
    }

    /**
     * Get cache operation quota.
     */
    @Test
    public void testGetCacheQuota() {
        GetCacheQuotaResponse response = cdnClient.getCacheQuota();
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testPrefetch() {
        PrefetchRequest request = new PrefetchRequest()
                .addTask(new PrefetchTask().withUrl("http://" + DOMAIN + "/url"));
        PrefetchResponse response = cdnClient.prefetch(request);
        System.out.println(response);
        Assert.assertTrue(response.getId().length() > 0);

        this.testGetPrefetchStatus(response.getId());
    }

    public void testGetPrefetchStatus(String id) {
        GetPrefetchStatusResponse response = cdnClient.getPrefetchStatus(
                new GetPrefetchStatusRequest().withId(id));
        System.out.println(response);
        Assert.assertTrue(response.getDetails().size() > 0);
    }

    @Test
    public void testGetLogsWithTime() {
        GetDomainLogResponse response = cdnClient.getDomainLog(DOMAIN,
                new Date(new Date().getTime() - 3600 * 10000), new Date());
        System.out.println(response);
        Assert.assertNotNull(response);

        String startTime = "2022-03-04T00:00:00Z";
        String endTime = "2022-03-04T23:00:00Z";
        GetDomainLogResponse response2 = cdnClient.getDomainLog(DOMAIN, startTime, endTime);
        System.out.println(response2);
        Assert.assertNotNull(response2);
    }

    /**
     * Get multiple domain URLs of logmodel files
     */
    @Test
    public void testGetDomainListLog() throws Exception {
        GetDomainListLogRequest request = new GetDomainListLogRequest()
                .withType(2L)
                .withStartTime("2020-03-04T00:00:00Z")
                .withEndTime("2020-03-04T23:00:00Z")
                .withPageNo(1)
                .withPageSize(1000)
                .withDomains(Arrays.asList(DOMAIN));
        GetDomainListLogResponse response = cdnClient.getDomainListLog(request);
        System.out.println(response);
    }

    @Test
    public void testGetStatMetricData() throws Exception {
        /**
         * 具体metric请参考官网API接口文档：https://cloud.baidu.com/doc/CDN/s/5jwvyf8zn
         * flow: 查询流量、带宽
         * avg_speed: 查询平均速率
         * avg_speed_region: 客户端访问分布查询平均速率
         * pv: pv/qps查询
         * pv_protocol: pv/qps https协议查询
         * pv_src: 回源pv/qps查询
         */
        String metricName = "flow_region";

        GetStatMetricRequest request = new GetStatMetricRequest()
                .withMetric(metricName)
                .withProv("北京")
                .withIsp("电信")
                .withStartTime("2022-03-19T06:00:00Z")
                .withEndTime("2022-03-19T06:40:00Z")
                .withKey(Arrays.asList("jcm-test.cdn.bcebos.com"));
        GetMetricStatResponse response = cdnClient.getStatMetricData(request);
        System.out.println(response);
    }

    @Test
    public void testGetMonth95Data() throws Exception {
        GetMonth95Request request = new GetMonth95Request()
                .withType("peak95")
                .withStartTime("2022-03-19T06:00:00Z")
                .withByTime(true)
                .withEndTime("2022-03-19T06:40:00Z")
                .withDomains("jcm-test.cdn.bcebos.com");
        GetMonth95Response response = cdnClient.getMonth95Data(request);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetTopStatData() {
        GetStatMetricRequest request = new GetStatMetricRequest()
                .withMetric("top_referers")
                .withStartTime("2022-04-19T02:00:00Z")
                .withEndTime("2022-04-19T06:50:00Z")
                .withKeyType(0)
                .withKey("jcm-test.cdn.bcebos.com");

        GetTopStatResponse response = cdnClient.getTopStatData(request);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetStatIpv6Data() {
        GetIpv6StatRequest request = new GetIpv6StatRequest()
                .withStartTime("2022-03-19T06:00:00Z")
                .withEndTime("2022-03-19T06:40:00Z")
                .withPeriod(3600)
                .withKeys(Arrays.asList("jcm-test.cdn.bcebos.com"));
        GetIpv6StatResponse response = cdnClient.getStatIpv6Data(request);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetStatRegionIpv6Data() {
        GetIpv6StatRequest request = new GetIpv6StatRequest()
                .withStartTime("2022-03-19T06:00:00Z")
                .withEndTime("2022-03-19T06:40:00Z")
                .withPeriod(3600)
                .withProv("广东")
                .withIsp("电信")
                .withKeys(Arrays.asList(DOMAIN));
        GetIpv6RegionStatResponse response = cdnClient.getStatRegionIpv6Data(request);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetErrorCodeStatData() {
        GetStatMetricRequest request = new GetStatMetricRequest()
                .withStartTime("2022-04-27T11:00:00Z")
                .withEndTime("2022-04-27T13:40:00Z")
                .withPeriod(3600)
                .withKey(Arrays.asList("jcm-test.cdn.bcebos.com"));
        GetErrorCodeStatResponse response = cdnClient.getErrorCodeStatData(request);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetUploadStatData() {
        GetUploadStatRequest request = new GetUploadStatRequest()
                .withStartTime("2022-03-19T06:00:00Z")
                .withEndTime("2022-03-19T06:40:00Z")
                .withPeriod(300)
                .withDomains(Arrays.asList("jcm-test.cdn.bcebos.com"))
                .withBandwidth(true);
        GetUploadStatResponse response = cdnClient.getUploadStatData(request);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetUploadPeakStatData() {
        GetUploadStatRequest request = new GetUploadStatRequest()
                .withStartTime("2022-03-19T06:00:00Z")
                .withEndTime("2022-03-19T06:40:00Z")
                .withPeriod(300)
                .withDomains(Arrays.asList("jcm-test.cdn.bcebos.com"));
        GetUploadStatResponse response = cdnClient.getUploadPeakStatData(request);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetXcdnStatData() {
        GetXcdnStatMetricRequest request = new GetXcdnStatMetricRequest()
                .withMetric("flow")
                .withStartTime("2022-03-19T06:00:00Z")
                .withEndTime("2022-03-19T06:40:00Z")
                .withKeys(Arrays.asList(DOMAIN));
        GetXcdnStatMetricResponse response = cdnClient.getXcdnStatData(request);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testDescribeIp() {
        DescribeIpResponse response = cdnClient.describeIp("1.3.5.6");
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testDescribeIps() {
        List<String> ips = new ArrayList<String>();
        ips.add("111.6.38.1");
        ips.add("221.203.7.5");
        DescribeIpRequest describeIpRequest = new DescribeIpRequest().withIps(ips);
        DescribeIpsResponse response = cdnClient.describeIps(describeIpRequest);
        System.out.println(describeIpRequest);
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetNodeList() {
        GetNodeListResponse response = cdnClient.getNodeList();
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetForbiddenQuota() {
        GetForbiddenQuota response = cdnClient.getForbiddenQuota();
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetForbiddenUrls() {
        GetForbiddenUrlsResponse response = cdnClient.getForbiddenUrls(new GetForbiddenUrlsRequest());
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetForbiddenOperateHistories() {
        GetForbiddenOperateHistoriesResponse response =
                cdnClient.getForbiddenOperateHistories(new GetForbiddenOperateHistoriesRequest());
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testSetForbiddenBan() {
        CdnResponse response = cdnClient.setForbiddenBan(new SetForbiddenUrlsRequest()
                .withUrls(Arrays.asList(DOMAIN + "/case.xml", "https://" + DOMAIN + "/case.index")));
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testSetForbiddenUnBan() {
        CdnResponse response = cdnClient.setForbiddenUnBan(new SetForbiddenUrlsRequest()
                .withUrls(Arrays.asList("af111111.duanhuiyan.top/case.xml", "af111111.duanhuiyan.top/case.index")));
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    @Test
    public void testGetPackageUsageList() {
        GetPackageUsageListResponse response =
                cdnClient.getPackageUsageList(new GetPackageUsageListRequest(1, 200));
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    /**
     * Update dsa service of specified domain acceleration.
     */
    @Test
    public void testSetDsa() {
        CdnResponse response = cdnClient.setDsa(new SetDsaRequest().withAction("enable"));
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    /**
     * Get Dsa Domain List.
     */
    @Test
    public void testGetDsaDomainList() {
        GetDsaDomainListResponse response = cdnClient.getDsaDomainList();
        System.out.println(response);
        Assert.assertNotNull(response);
    }

    /**
     * Update Dsa rules of specified domain acceleration.
     */
    @Test
    public void testSetDomainDsa() {
        DSA dsa = new DSA().withEnabled(true)
                .addRules(new DSARule().withType("suffix").withValue(".mp4;.jpg;.php"))
                .addRules(new DSARule().withType("path").withValue("/path"))
                .addRules(new DSARule().withType("exactPath").withValue("/path/to/file.mp4"))
                .withComment("test");
        cdnClient.setDomainDsa(new SetDomainDsaRequest().withDomain(DOMAIN).withDsa(dsa));
    }









}
