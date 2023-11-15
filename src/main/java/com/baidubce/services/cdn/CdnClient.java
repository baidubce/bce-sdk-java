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
import com.baidubce.services.cdn.model.CdnRequest;
import com.baidubce.services.cdn.model.CdnResponse;
import com.baidubce.services.cdn.model.DescribeIpRequest;
import com.baidubce.services.cdn.model.DescribeIpResponse;
import com.baidubce.services.cdn.model.GetCacheQuotaResponse;
import com.baidubce.services.cdn.model.GetPrefetchStatusRequest;
import com.baidubce.services.cdn.model.GetPurgeStatusRequest;
import com.baidubce.services.cdn.model.GetPurgeStatusResponse;
import com.baidubce.services.cdn.model.ListDomainsRequest;
import com.baidubce.services.cdn.model.ListDomainsResponse;
import com.baidubce.services.cdn.model.PrefetchRequest;
import com.baidubce.services.cdn.model.PrefetchResponse;
import com.baidubce.services.cdn.model.PurgeRequest;
import com.baidubce.services.cdn.model.PurgeResponse;
import com.baidubce.services.cdn.model.PurgeTask;
import com.baidubce.services.cdn.model.SetDomainOriginRequest;
import com.baidubce.services.cdn.model.SetDomainHttpsConfigRequest;
import com.baidubce.services.cdn.model.domain.CheckDomainValidResponse;
import com.baidubce.services.cdn.model.DescribeIpsResponse;
import com.baidubce.services.cdn.model.domain.CommonResponse;
import com.baidubce.services.cdn.model.domain.CreateDomainRequest;
import com.baidubce.services.cdn.model.domain.CreateDomainResponse;
import com.baidubce.services.cdn.model.domain.DisableDomainResponse;
import com.baidubce.services.cdn.model.domain.EnableDomainResponse;
import com.baidubce.services.cdn.model.domain.GetDomainAccessLimitResponse;
import com.baidubce.services.cdn.model.domain.GetDomainCacheFullUrlResponse;
import com.baidubce.services.cdn.model.domain.GetDomainCacheShareResponse;
import com.baidubce.services.cdn.model.domain.GetDomainCacheTTLRequest;
import com.baidubce.services.cdn.model.domain.GetDomainCacheTTLResponse;
import com.baidubce.services.cdn.model.domain.GetDomainClientIpResponse;
import com.baidubce.services.cdn.model.domain.GetDomainCompressResponse;
import com.baidubce.services.cdn.model.domain.GetDomainConfigResponse;
import com.baidubce.services.cdn.model.domain.GetDomainCorsResponse;
import com.baidubce.services.cdn.model.domain.GetDomainErrorPageResponse;
import com.baidubce.services.cdn.model.domain.GetDomainFileTrimResponse;
import com.baidubce.services.cdn.model.domain.GetDomainHSTSResponse;
import com.baidubce.services.cdn.model.domain.GetDomainHttpHeaderResponse;
import com.baidubce.services.cdn.model.domain.GetDomainIPv6DispatchResponse;
import com.baidubce.services.cdn.model.domain.GetDomainIpACLResponse;
import com.baidubce.services.cdn.model.domain.GetDomainLogResponse;
import com.baidubce.services.cdn.model.domain.GetDomainMediaDragResponse;
import com.baidubce.services.cdn.model.domain.GetDomainMobileAccessResponse;
import com.baidubce.services.cdn.model.domain.GetDomainOCSPSwitchResponse;
import com.baidubce.services.cdn.model.domain.GetDomainOfflineModeSwitchResponse;
import com.baidubce.services.cdn.model.domain.GetDomainOriginProtocolResponse;
import com.baidubce.services.cdn.model.domain.GetDomainQUICSwitchResponse;
import com.baidubce.services.cdn.model.domain.GetDomainRangeSwitchResponse;
import com.baidubce.services.cdn.model.domain.GetDomainRefererACLResponse;
import com.baidubce.services.cdn.model.domain.GetDomainRetryOriginResponse;
import com.baidubce.services.cdn.model.domain.GetDomainSeoSwitchResponse;
import com.baidubce.services.cdn.model.domain.GetDomainTrafficLimitResponse;
import com.baidubce.services.cdn.model.domain.GetIcpResponse;
import com.baidubce.services.cdn.model.domain.DomainConfigKeysResponse;
import com.baidubce.services.cdn.model.domain.SetDomainOriginFixedIspRequest;
import com.baidubce.services.cdn.model.domain.GetDomainUaAclResponse;
import com.baidubce.services.cdn.model.domain.GetUserDomainResponse;
import com.baidubce.services.cdn.model.domain.GetUserDomainsRequest;
import com.baidubce.services.cdn.model.domain.SetDomainAccessLimitRequest;
import com.baidubce.services.cdn.model.domain.SetDomainCacheFullUrlRequest;
import com.baidubce.services.cdn.model.domain.SetDomainCacheShareRequest;
import com.baidubce.services.cdn.model.domain.SetDomainCacheTTLRequest;
import com.baidubce.services.cdn.model.domain.SetDomainClientIpRequest;
import com.baidubce.services.cdn.model.domain.SetDomainCompressRequest;
import com.baidubce.services.cdn.model.domain.SetDomainCorsRequest;
import com.baidubce.services.cdn.model.domain.SetDomainErrorPageRequest;
import com.baidubce.services.cdn.model.domain.SetDomainFileTrimRequest;
import com.baidubce.services.cdn.model.domain.SetDomainHSTSRequest;
import com.baidubce.services.cdn.model.domain.SetDomainHttpHeaderRequest;
import com.baidubce.services.cdn.model.domain.SetDomainIPv6DispatchRequest;
import com.baidubce.services.cdn.model.domain.SetDomainIpACLRequest;
import com.baidubce.services.cdn.model.domain.SetDomainMediaDragRequest;
import com.baidubce.services.cdn.model.domain.SetDomainMobileAccessRequest;
import com.baidubce.services.cdn.model.domain.SetDomainOCSPRequest;
import com.baidubce.services.cdn.model.domain.SetDomainOfflineModeRequest;
import com.baidubce.services.cdn.model.domain.SetDomainOriginProtocolRequest;
import com.baidubce.services.cdn.model.domain.SetDomainQUICRequest;
import com.baidubce.services.cdn.model.domain.SetDomainRangeSwitchRequest;
import com.baidubce.services.cdn.model.domain.SetDomainRefererACLRequest;
import com.baidubce.services.cdn.model.domain.SetDomainRetryOriginRequest;
import com.baidubce.services.cdn.model.domain.SetDomainSeoSwitchRequest;
import com.baidubce.services.cdn.model.domain.SetDomainTrafficLimitRequest;
import com.baidubce.services.cdn.model.domain.SetDomainUaAclRequest;
import com.baidubce.services.cdn.model.domain.SetRequestAuthRequest;
import com.baidubce.services.cdn.model.domain.CopyDomainTaskStatusResponse;
import com.baidubce.services.cdn.model.domain.CopyDomainTaskResponse;
import com.baidubce.services.cdn.model.domain.SetDomainOriginTimeoutRequest;
import com.baidubce.services.cdn.model.domain.CopyDomainTaskRequest;
import com.baidubce.services.cdn.model.domain.GetDomainOriginTimeoutResponse;
import com.baidubce.services.cdn.model.domain.GetDomainOriginFixedIspResponse;
import com.baidubce.services.cdn.model.domain.GetDomainUrlRulesResponse;
import com.baidubce.services.cdn.model.domain.GetDomainLimitBandwidthResponse;
import com.baidubce.services.cdn.model.domain.SetDomainUrlRulesRequest;
import com.baidubce.services.cdn.model.domain.SetDomainLimitBandwidthRequest;
import com.baidubce.services.cdn.model.domain.GetDomainOriginResponse;
import com.baidubce.services.cdn.model.handler.CdnJsonResponseHandler;
import com.baidubce.services.cdn.model.stat.GetMonth95Response;
import com.baidubce.services.cdn.model.stat.GetStatMetricResponse;
import com.baidubce.services.cdn.model.stat.GetStatMetricRequest;
import com.baidubce.services.cdn.model.stat.GetTopStatResponse;
import com.baidubce.services.cdn.model.stat.GetErrorCodeStatResponse;
import com.baidubce.services.cdn.model.stat.GetMetricStatResponse;
import com.baidubce.services.cdn.model.stat.GetIpv6StatResponse;
import com.baidubce.services.cdn.model.stat.GetMonth95Request;
import com.baidubce.services.cdn.model.stat.GetIpv6StatRequest;
import com.baidubce.services.cdn.model.stat.GetUploadStatRequest;
import com.baidubce.services.cdn.model.stat.GetIpv6RegionStatResponse;
import com.baidubce.services.cdn.model.stat.GetUploadStatResponse;
import com.baidubce.services.cdn.model.stat.GetXcdnStatMetricResponse;
import com.baidubce.services.cdn.model.stat.GetPackageUsageListRequest;
import com.baidubce.services.cdn.model.stat.GetXcdnStatMetricRequest;
import com.baidubce.services.cdn.model.stat.GetPackageUsageListResponse;
import com.baidubce.services.cdn.model.cache.GetPrefetchStatusResponse;
import com.baidubce.services.cdn.model.cache.PrefetchTask;
import com.baidubce.services.cdn.model.certificate.BatchUploadCertRequest;
import com.baidubce.services.cdn.model.certificate.GetDomainCertResponse;
import com.baidubce.services.cdn.model.certificate.SetDomainCertRequest;
import com.baidubce.services.cdn.model.certificate.GetHttpsDomainResponse;
import com.baidubce.services.cdn.model.certificate.SetDomainCertResponse;
import com.baidubce.services.cdn.model.certificate.GetHttpsDomainRequest;
import com.baidubce.services.cdn.model.dsa.GetDsaDomainListResponse;
import com.baidubce.services.cdn.model.dsa.SetDomainDsaRequest;
import com.baidubce.services.cdn.model.dsa.SetDsaRequest;
import com.baidubce.services.cdn.model.logmodel.GetDomainListLogRequest;
import com.baidubce.services.cdn.model.logmodel.GetDomainListLogResponse;
import com.baidubce.services.cdn.model.util.GetNodeListResponse;
import com.baidubce.services.cdn.model.util.GetForbiddenUrlsResponse;
import com.baidubce.services.cdn.model.util.GetForbiddenQuota;
import com.baidubce.services.cdn.model.util.GetForbiddenUrlsRequest;
import com.baidubce.services.cdn.model.util.GetForbiddenOperateHistoriesResponse;
import com.baidubce.services.cdn.model.util.SetForbiddenUrlsRequest;
import com.baidubce.services.cdn.model.util.GetForbiddenOperateHistoriesRequest;
import com.baidubce.util.DateUtils;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.util.Validate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Client for accessing CDN Services.
 * Created by sunyixing on 2016/1/9.
 * Update by changxing01 on 2019/10/15
 */

public class CdnClient extends AbstractBceClient {

    /**
     * The version information for Document service APIs as URI prefix.
     */
    private static final String VERSION = "v2";

    /**
     * The common URI prefix for domain operation.
     */
    private static final String DOMAIN = "domain";

    /**
     * The common URI prefix for statistic services.
     */
    private static final String STAT = "stat";

    /**
     * The common URI prefix for cache operation.
     */
    private static final String CACHE = "cache";

    /**
     * The common URI prefix for logmodel operation.
     */
    private static final String LOG = "log";

    /**
     * The common URI prefix for utils operation.
     */
    private static final String UTILS = "utils";

    private static final String USER = "user";

    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    private static final HttpResponseHandler[] cdnHandlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new CdnJsonResponseHandler()
    };

    /**
     * Constructs a new Document client to invoke service methods on CDN.
     */
    public CdnClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new client using the client configuration to access CDN services.
     *
     * @param clientConfiguration The client configuration options controlling how this client
     *                            connects to Document services (e.g. proxy settings, retry counts, etc).
     */
    public CdnClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, cdnHandlers);
    }

    /**
     * Create a new domain acceleration.
     *
     * @param request The request containing user-defined domain information.
     * @return Result of the createDomain operation returned by the service.
     */
    public CreateDomainResponse createDomain(CreateDomainRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, DOMAIN, request.getDomain());
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CreateDomainResponse.class);
    }

    /**
     * Start an existing domain acceleration.
     *
     * @return Result of the enableDomain operation returned by the service.
     */
    public EnableDomainResponse enableDomain(String domain) {
        Validate.checkStringNotEmpty(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.POST, DOMAIN, domain);
        internalRequest.addParameter("enable", "");
        return invokeHttpClient(internalRequest, EnableDomainResponse.class);
    }

    /**
     * Disable an existing domain acceleration.
     *
     * @return Result of the disableDomain operation returned by the service.
     */
    public DisableDomainResponse disableDomain(String domain) {
        Validate.checkStringNotEmpty(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.POST, DOMAIN, domain);
        internalRequest.addParameter("disable", "");
        return invokeHttpClient(internalRequest, DisableDomainResponse.class);
    }

    /**
     * Delete an existing domain acceleration
     *
     * @return Result of the deleteDomain operation returned by the service.
     */
    public CommonResponse deleteDomain(String domain) {
        Validate.checkStringNotEmpty(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.DELETE, DOMAIN, domain);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Returns a list of all CDN domains that the authenticated sender of the request owns.
     *
     * @return All of the CDN domains owned by the authenticated sender of the request.
     */
    public ListDomainsResponse listDomains() {
        return this.listDomains(new ListDomainsRequest());
    }

    /**
     * Returns a list of all CDN domains that the authenticated sender of the request owns.
     *
     * @param request The request containing all of the options related to the listing of domains.
     * @return All of the CDN domains owned by the authenticated sender of the request.
     */
    public ListDomainsResponse listDomains(ListDomainsRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, DOMAIN);
        return invokeHttpClient(internalRequest, ListDomainsResponse.class);
    }

    /**
     * Return a list of user's all CDN domains that include domain and domain status
     * support domain name fuzzy matching filter and domain status filter
     *
     * @param status search domain status  (ALL | RUNNING | STOPPED | OPERATING)
     * @return a list of user's all CDN domains thats filter by status
     */
    public GetUserDomainResponse getUserDomains(String status) {
        return getUserDomains(new GetUserDomainsRequest().withStatus(status));
    }

    /**
     * Return a list of user's all CDN domains that include domain and domain status
     * support domain name fuzzy matching filter and domain status filter
     *
     * @param request The request containing all of the options related to the listing of domains.
     * @return a list of user's all CDN domains thats filter by status and rule
     */
    public GetUserDomainResponse getUserDomains(GetUserDomainsRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, USER, "domains");
        internalRequest.addParameter("status", request.getStatus());

        if (request.getRule() != null) {
            internalRequest.addParameter("rule", request.getRule());
        }

        return invokeHttpClient(internalRequest, GetUserDomainResponse.class);
    }

    /**
     * Query whether the domain name can be added
     * 查询域名是否可添加
     *
     * @param domain
     * @return the result of check that include isValid and fail message
     */
    public CheckDomainValidResponse checkDomainValid(String domain) {
        Validate.checkStringNotEmpty(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "valid");
        return invokeHttpClient(internalRequest, CheckDomainValidResponse.class);
    }

    /**
     * Query whether the domain name has icp
     * 查询域名是否备案
     *
     * @param domain domain
     * @return the result of check that include isValid and fail message
     */
    public GetIcpResponse getIcpStatus(String domain) {
        Validate.checkStringNotEmpty(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "icp");
        return invokeHttpClient(internalRequest, GetIcpResponse.class);
    }

    /**
     * 获取domain的能复制的配置项
     * Get the replicable configuration items of the domain
     *
     * @param domain domain
     * @return getDomainConfig of the getDomainConfig operation returned by the service.
     */
    public DomainConfigKeysResponse getDomainConfigKeys(String domain) {
        Validate.checkStringNotEmpty(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                "config_copy", "list");
        internalRequest.addParameter(DOMAIN, domain);
        return invokeHttpClient(internalRequest, DomainConfigKeysResponse.class);
    }

    /**
     * 提交配置复制任务
     * Submit a configuration replication task
     *
     * @param originDomain 提供复制配置的域名
     * @param domains 要复制的域名列表
     * @param configNames 要复制的配置列表，其中config为域名配置项对应的key
     * @return
     */
    public CopyDomainTaskResponse copyDomainConfig(String originDomain, List<String> domains,
                                                   List<String> configNames) {
        return copyDomainConfig(new CopyDomainTaskRequest()
                .withOriginDomain(originDomain).withDomains(domains).withConfigs(configNames));
    }

    /**
     * 提交配置复制任务
     * Submit a configuration replication task
     *
     * @param request The request containing all of the options related to the domain.
     * @return getDomainConfig of the getDomainConfig operation returned by the service.
     */
    public CopyDomainTaskResponse copyDomainConfig(CopyDomainTaskRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST,
                "config_copy", "submit");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CopyDomainTaskResponse.class);
    }

    /**
     * 查询当前用户配置复制任务状态
     * Query the status of the current user configuration replication task
     *
     * @param taskId taskId
     * @return getDomainConfig of the getDomainConfig operation returned by the service.
     */
    public CopyDomainTaskStatusResponse getCopyDomainStatus(String taskId) {
        Validate.checkStringNotEmpty(taskId, "TaskId should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                "config_copy", "status");
        internalRequest.addParameter("taskId", taskId);
        return invokeHttpClient(internalRequest, CopyDomainTaskStatusResponse.class);
    }


    /** ------------- 域名操作 END --------------**/

    /** ------------- 域名配置 START --------------**/

    /**
     * Get detailed information of a domain.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return getDomainConfig of the getDomainConfig operation returned by the service.
     */
    public GetDomainConfigResponse getDomainConfig(String domain) {
        Validate.checkStringNotEmpty(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        return invokeHttpClient(internalRequest, GetDomainConfigResponse.class);
    }


    /** ------ 回源配置 START -------- **/

    /**
     * Update origin of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the domain.
     * @return Result of the setDomainOrigin operation returned by the service.
     */
    public CommonResponse setDomainOrigin(SetDomainOriginRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        Validate.checkNotNull(request.getOrigin(), "Origin should NOT be null.");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("origin", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    public GetDomainOriginResponse getDomainOrigin(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("origin", "");
        return invokeHttpClient(internalRequest, GetDomainOriginResponse.class);
    }

    /**
     * Update RangeSwitch of specified domain acceleration.
     *
     * @param domain      domain's name
     * @param rangeSwitch The request containing all of the options related to the domain.
     * @return Result of the setDomainRangeSwitch operation returned by the service.
     */
    public CommonResponse setDomainRangeSwitch(String domain, boolean rangeSwitch) {
        return setDomainRangeSwitch(new SetDomainRangeSwitchRequest()
                .withDomain(domain)
                .withRangeSwitch(rangeSwitch));
    }

    /**
     * Update RangeSwitch of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the domain.
     * @return Result of the setDomainRangeSwitch operation returned by the service.
     */
    public CommonResponse setDomainRangeSwitch(SetDomainRangeSwitchRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain request should NOT be null.");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("rangeSwitch", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get RangeSwitch of specified domain acceleration.
     *
     * @param domain Name of the domain.
     * @return Detailed information about domain rangeSwitch.
     */
    public GetDomainRangeSwitchResponse getDomainRangeSwitch(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("rangeSwitch", "");
        return invokeHttpClient(internalRequest, GetDomainRangeSwitchResponse.class);
    }

    /**
     * set ClientIp config of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the domain.
     * @return Result of the setDomainClientIp operation returned by the service.
     */
    public CommonResponse setDomainClientIp(SetDomainClientIpRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getClientIp(), "ClientIp should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("clientIp", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get ClientIp config information of specified domain acceleration.
     *
     * @param domain
     * @return Result of the getDomainClientIp operation returned by the service.
     */
    public GetDomainClientIpResponse getDomainClientIp(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("clientIp", "");
        return invokeHttpClient(internalRequest, GetDomainClientIpResponse.class);
    }

    /**
     * set origin protocal config of specified domain acceleration.
     *
     * @param request
     * @return
     */
    public CommonResponse setDomainOriginProtocol(SetDomainOriginProtocolRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getOriginProtocol(), "OriginProtocol should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("originProtocol", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get origin protocol config information of specified domain acceleration.
     *
     * @param domain
     * @return
     */
    public GetDomainOriginProtocolResponse getDomainOriginProtocol(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("originProtocol", "");
        return invokeHttpClient(internalRequest, GetDomainOriginProtocolResponse.class);
    }

    /**
     * Update retry origin of specified domain acceleration.
     *
     * @param request
     * @return
     */
    public CommonResponse setDomainRetryOrigin(SetDomainRetryOriginRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        request.setDomain(null);
        internalRequest.addParameter("retryOrigin", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get retry origin config information of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Result of the getDomainRetryOrigin operation returned by the service.
     */
    public GetDomainRetryOriginResponse getDomainRetryOrigin(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("retryOrigin", "");
        return invokeHttpClient(internalRequest, GetDomainRetryOriginResponse.class);
    }

    /**
     * Configure back-to-source timeout
     * 设置回源超时
     *
     * @param request
     * @return
     */
    public CommonResponse setDomainOriginTimeout(SetDomainOriginTimeoutRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        Validate.checkNotNull(request.getOriginTimeout(), "OriginTimeout should NOT be null.");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        request.setDomain(null);
        internalRequest.addParameter("originTimeout", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get back-to-source timeout
     * 查询回源超时
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Result of the getDomainOriginTimeout operation returned by the service.
     */
    public GetDomainOriginTimeoutResponse getDomainOriginTimeout(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("originTimeout", "");
        return invokeHttpClient(internalRequest, GetDomainOriginTimeoutResponse.class);
    }

    /**
     * Update up the same carrier back-to-origin
     *
     * @param request
     * @return
     */
    public CommonResponse setDomainOriginFixedISP(SetDomainOriginFixedIspRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        Validate.checkNotNull(request.getOriginFixedISP(), "OriginFixedISP should NOT be null.");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("originFixedISP", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get the same carrier back-to-origin
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Result of the GetDomainOriginFixedIspResponse operation returned by the service.
     */
    public GetDomainOriginFixedIspResponse getDomainOriginFixedISP(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("originFixedISP", "");
        return invokeHttpClient(internalRequest, GetDomainOriginFixedIspResponse.class);
    }

    /** ------ 回源配置 END -------- **/

    /** ------ 访问配置 START -------- **/


    /**
     * Update RefererACL rules of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the update request.
     * @return Result of the setDomainRefererACL operation returned by the service.
     */
    public CommonResponse setDomainRefererACL(SetDomainRefererACLRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        Validate.checkNotNull(request.getRefererACL(), "RefererACL should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("refererACL", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get RefererACL rules of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the get refererACL.
     * @return Result of the getDomainRefererACL operation returned by the service.
     */
    public GetDomainRefererACLResponse getDomainRefererACL(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("refererACL", "");
        return invokeHttpClient(internalRequest, GetDomainRefererACLResponse.class);
    }

    /**
     * Update IpACL rules of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the update request.
     * @return Result of the setDomainIpACL operation returned by the service.
     */
    public CommonResponse setDomainIpACL(SetDomainIpACLRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        Validate.checkNotNull(request.getIpACL(), "IpACL should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("ipACL", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get IpACL rules of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the Get IpACL.
     * @return Result of the getDomainIpACL operation returned by the service.
     */
    public GetDomainIpACLResponse getDomainIpACL(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("ipACL", "");
        return invokeHttpClient(internalRequest, GetDomainIpACLResponse.class);
    }

    /**
     * set cors config of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the update request.
     * @return Result of the setDomainCors operation returned by the service.
     */
    public CommonResponse setDomainCors(SetDomainCorsRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        Validate.checkNotNull(request.getCors(), "Cors should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("cors", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get Cors config information of a domain
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Result of the getDomainCors operation returned by the service.
     */
    public GetDomainCorsResponse getDomainCors(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("cors", "");
        return invokeHttpClient(internalRequest, GetDomainCorsResponse.class);
    }

    /**
     * Set the traffic limit of specified domain acceleration.
     * each response to client
     *
     * @param request
     * @return
     */
    public CommonResponse setDomainTrafficLimit(SetDomainTrafficLimitRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        Validate.checkNotNull(request.getTrafficLimit(), "TrafficLimit should NOT be null.");
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.PUT, DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("trafficLimit", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get trafficLimit of specified domain acceleration.
     * for each response
     *
     * @param domain
     * @return
     */
    public GetDomainTrafficLimitResponse getDomainTrafficLimit(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest =
                createRequest(new CdnRequest(), HttpMethodName.GET, DOMAIN, domain, "config");
        internalRequest.addParameter("trafficLimit", "");
        return invokeHttpClient(internalRequest, GetDomainTrafficLimitResponse.class);
    }


    /**
     * Set the request authentication.
     *
     * @param request The request containing all of the options related to the update request.
     * @return Result of the SetRequestAuthRequest operation returned by the service.
     */
    public CommonResponse setDomainRequestAuth(SetRequestAuthRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        Validate.checkNotNull(request.getRequestAuth(), "RequestAuth should NOT be null.");
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.PUT, DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("requestAuth", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * set AccessLimit config of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the domain.
     * @return Result of the setDomainAccessLimit operation returned by the service.
     */
    public CommonResponse setDomainAccessLimit(SetDomainAccessLimitRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        Validate.checkNotNull(request.getAccessLimit(), "AccessLimit should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("accessLimit", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get AccessLimit config information of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Result of the getDomainAccessLimit operation returned by the service.
     */
    public GetDomainAccessLimitResponse getDomainAccessLimit(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("accessLimit", "");
        return invokeHttpClient(internalRequest, GetDomainAccessLimitResponse.class);
    }

    /**
     * Set the UA ACL of specified domain acceleration.
     *
     * @param request
     * @return
     */
    public CommonResponse setDomainUaAcl(SetDomainUaAclRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        Validate.checkNotNull(request.getUaAcl(), "UaAcl should NOT be null.");
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.PUT, DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("uaAcl", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get UA ACL of specified domain acceleration.
     *
     * @param domain
     * @return
     */
    public GetDomainUaAclResponse getDomainUaAcl(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest =
                createRequest(new CdnRequest(), HttpMethodName.GET, DOMAIN, domain, "config");
        internalRequest.addParameter("uaAcl", "");
        return invokeHttpClient(internalRequest, GetDomainUaAclResponse.class);
    }

    /** -------- 访问配置 END -------- **/

    /** -------- 缓存配置 START -------- **/

    /**
     * Update cache policies of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the update request.
     * @return Result of the setDomainCacheTTL operation returned by the service.
     */
    public CdnResponse setDomainCacheTTL(SetDomainCacheTTLRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        Validate.checkNotNull(request.getCacheTTL(), "CacheTTL should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("cacheTTL", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CdnResponse.class);
    }


    /**
     * Get cache policies of specified domain acceleration.
     *
     * @param domain Name of the domain.
     * @return Detailed information about cache policies.
     */
    public GetDomainCacheTTLResponse getDomainCacheTTL(String domain) {
        GetDomainCacheTTLRequest request = new GetDomainCacheTTLRequest().withDomain(domain);
        return getDomainCacheTTL(request);
    }

    /**
     * Get cache policies of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the domain.
     * @return Detailed information about cache policies.
     */
    public GetDomainCacheTTLResponse getDomainCacheTTL(GetDomainCacheTTLRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("cacheTTL", "");
        return invokeHttpClient(internalRequest, GetDomainCacheTTLResponse.class);
    }

    /**
     * Update cache policy of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the update request.
     * @return Result of the SetDomainCacheFullUrlRequest operation returned by the service.
     */
    public CommonResponse setDomainCacheFullUrl(SetDomainCacheFullUrlRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        Validate.checkNotNull(request.getCacheFullUrl(), "CacheFullUrl should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("cacheFullUrl", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * search domain's rule of caching filter parameter
     *
     * @param domain The request containing all of the options related to the get cache full url request.
     * @return domain's rule of cache filter parameter
     */
    public GetDomainCacheFullUrlResponse getDomainCacheFullUrl(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("cacheFullUrl", "");
        return invokeHttpClient(internalRequest, GetDomainCacheFullUrlResponse.class);
    }

    /**
     * add website error page to deal with exception.
     *
     * @param request The request containing all of the options related to the set request.
     * @return Result of the setDomainErrorPage operation returned by the service.
     */
    public CommonResponse setDomainErrorPage(SetDomainErrorPageRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        Validate.checkNotNull(request.getErrorPage(), "ErrorPage should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("errorPage", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * search domain's custom error page
     *
     * @param domain The request containing all of the options related to the get error page request.
     * @return custom error page info list
     */
    public GetDomainErrorPageResponse getDomainErrorPage(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("errorPage", "");
        return invokeHttpClient(internalRequest, GetDomainErrorPageResponse.class);
    }

    /**
     * Update MobileAccess of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the domain.
     * @return Result of the setDomainMobileAccess operation returned by the service.
     */
    public CommonResponse setDomainMobileAccess(SetDomainMobileAccessRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be null.");
        Validate.checkNotNull(request.getMobileAccess(), "MobileAccess should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("mobileAccess", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get MobileAccess of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Detailed information about domain MobileAccess.
     */
    public GetDomainMobileAccessResponse getDomainMobileAccess(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be null.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("mobileAccess", "");
        return invokeHttpClient(internalRequest, GetDomainMobileAccessResponse.class);
    }

    /**
     * Update Domain cache share of specified domain acceleration.
     *
     * @param request
     * @return
     */
    public CommonResponse setDomainCacheShare(SetDomainCacheShareRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be empty.");
        Validate.checkNotNull(request.getCacheShare(), "CacheShare should NOT be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("cacheShare", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get cache share config information of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Result of the getDomainCacheShare operation returned by the service.
     */
    public GetDomainCacheShareResponse getDomainCacheShare(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("cacheShare", "");
        return invokeHttpClient(internalRequest, GetDomainCacheShareResponse.class);
    }

    /**
     * Update Domain URI rewrite of specified domain acceleration.
     *
     * @param request
     * @return
     */
    public CommonResponse setDomainUrlRules(SetDomainUrlRulesRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be empty.");
        Validate.checkNotNull(request.getUrlRules(), "UrlRules should NOT be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        request.setDomain(null);
        internalRequest.addParameter("urlRules", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }


    /**
     * Get Domain URI rewrite of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Result of the GetDomainUrlRulesResponse operation returned by the service.
     */
    public GetDomainUrlRulesResponse getDomainUrlRules(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("urlRules", "");
        return invokeHttpClient(internalRequest, GetDomainUrlRulesResponse.class);
    }

    /** -------- 缓存配置 EDN -------- **/

    /** -------- 高级配置 START -------- **/

    /**
     * Update HttpHeader of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the domain.
     * @return Result of the setDomainHttpHeader operation returned by the service.
     */
    public CommonResponse setDomainHttpHeader(SetDomainHttpHeaderRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be empty.");
        Validate.checkNotNull(request.getHttpHeader(), "HttpHeader should NOT be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("httpHeader", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get HttpHeader of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Detailed information about domain HttpHeader.
     */
    public GetDomainHttpHeaderResponse getDomainHttpHeader(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("httpHeader", "");
        return invokeHttpClient(internalRequest, GetDomainHttpHeaderResponse.class);
    }

    /**
     * Update SeoSwitch of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the domain.
     * @return Result of the setDomainSeoSwitch operation returned by the service.
     */
    public CommonResponse setDomainSeoSwitch(SetDomainSeoSwitchRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be empty.");
        Validate.checkNotNull(request.getSeoSwitch(), "SeoSwitch should NOT be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("seoSwitch", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get SeoSwitch of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Detailed information about domain SeoSwitch.
     */
    public GetDomainSeoSwitchResponse getDomainSeoSwitch(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("seoSwitch", "");
        return invokeHttpClient(internalRequest, GetDomainSeoSwitchResponse.class);
    }

    /**
     * Update MediaDrag of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the domain.
     * @return Result of the setDomainMediaDrag operation returned by the service.
     */
    public CommonResponse setDomainMediaDrag(SetDomainMediaDragRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be empty.");
        Validate.checkNotNull(request.getMediaDragConf(), "MediaDragConf should NOT be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("mediaDrag", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get MediaDrag of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Detailed information about domain MediaDrag.
     */
    public GetDomainMediaDragResponse getDomainMediaDrag(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("mediaDrag", "");
        return invokeHttpClient(internalRequest, GetDomainMediaDragResponse.class);
    }

    /**
     * Update FileTrim of specified domain acceleration.
     *
     * @param domain   Name of the domain.
     * @param fileTrim Whether to enable page optimization
     */
    public void setDomainFileTrim(String domain, boolean fileTrim) {
        setDomainFileTrim(new SetDomainFileTrimRequest().withDomain(domain).withFileTrim(fileTrim));
    }

    /**
     * Update FileTrim of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the domain.
     * @return Result of the setDomainFileTrim operation returned by the service.
     */
    public CommonResponse setDomainFileTrim(SetDomainFileTrimRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("fileTrim", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get FileTrim of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Detailed information about domain FileTrim.
     */
    public GetDomainFileTrimResponse getDomainFileTrim(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("fileTrim", "");
        return invokeHttpClient(internalRequest, GetDomainFileTrimResponse.class);
    }

    /**
     * Update Compress of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the domain.
     * @return Result of the setDomainCompress operation returned by the service.
     */
    public CommonResponse setDomainCompress(SetDomainCompressRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be empty.");
        Validate.checkNotNull(request.getCompress(), "Compress should NOT be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("compress", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get Compress of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Detailed information about domain Compress.
     */
    public GetDomainCompressResponse getDomainCompress(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("compress", "");
        return invokeHttpClient(internalRequest, GetDomainCompressResponse.class);
    }

    /**
     * Update QUIC of specified domain acceleration.
     *
     * @param domain
     * @param quic true表示开启QUIC，false表示关闭。预启用QUIC的域名必须已经开启了HTTPS。
     * @return
     */
    public CommonResponse setDomainQUICSwitch(String domain, boolean quic) {
        return setDomainQUICSwitch(new SetDomainQUICRequest().withDomain(domain).withQuic(quic));
    }

    /**
     * Update QUIC of specified domain acceleration.
     *
     * @param request
     * @return
     */
    public CommonResponse setDomainQUICSwitch(SetDomainQUICRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getDomain(), "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("quic", "");
        attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get ipv6Dispatch config information of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Result of the getDomainClientIp operation returned by the service.
     */
    public GetDomainQUICSwitchResponse getDomainQUICSwitch(String domain) {
        Validate.checkNotNull(domain, "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("quic", "");
        return invokeHttpClient(internalRequest, GetDomainQUICSwitchResponse.class);
    }

    /**
     * Update ipv6Dispatch of specified domain acceleration.
     *
     * @param request
     * @return
     */
    public CommonResponse setDomainIPv6Dispatch(SetDomainIPv6DispatchRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getDomain(), "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("ipv6Dispatch", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get ipv6Dispatch config information of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Result of the getDomainClientIp operation returned by the service.
     */
    public GetDomainIPv6DispatchResponse getDomainIPv6Dispatch(String domain) {
        Validate.checkStringNotEmpty(domain, "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("ipv6Dispatch", "");
        return invokeHttpClient(internalRequest, GetDomainIPv6DispatchResponse.class);
    }


    /**
     * Update offline mode of specified domain acceleration.
     *
     * @param domain
     * @param offlineMode 设置离线模式与否，true/false表示开启/关闭
     * @return
     */
    public CommonResponse setDomainOfflineModeSwitch(String domain, boolean offlineMode) {
        return setDomainOfflineModeSwitch(new SetDomainOfflineModeRequest()
                .withDomain(domain).withOfflineMode(offlineMode));
    }

    /**
     * Update offline mode of specified domain acceleration.
     *
     * @param request
     * @return
     */
    public CommonResponse setDomainOfflineModeSwitch(SetDomainOfflineModeRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getDomain(), "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                        DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("offlineMode", "");
        attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get OfflineMode config information of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Result of the getDomainClientIp operation returned by the service.
     */
    public GetDomainOfflineModeSwitchResponse getDomainOfflineModeSwitch(String domain) {
        Validate.checkStringNotEmpty(domain, "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("offlineMode", "");
        return invokeHttpClient(internalRequest, GetDomainOfflineModeSwitchResponse.class);
    }

    /**
     * Update LimitBandwidth of specified domain acceleration.
     *
     * @param request
     * @return
     */
    public CommonResponse setDomainLimitBandwidth(SetDomainLimitBandwidthRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getDomain(), "Domain should NOT be empty.");
        Validate.checkNotNull(request.getLimitBandwidth(), "LimitBandwidth should NOT be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("limitBandwidth", "");
        attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get LimitBandwidth config information of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Result of the GetDomainOfflineModeSwitchResponse operation returned by the service.
     */
    public GetDomainLimitBandwidthResponse getDomainLimitBandwidth(String domain) {
        Validate.checkStringNotEmpty(domain, "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("limitBandwidth", "");
        return invokeHttpClient(internalRequest, GetDomainLimitBandwidthResponse.class);
    }

    /** -------- 高级配置 END -------- **/

    /** -------- HTTPS配置 START -------- **/

    /**
     * Set HTTPS with certain configuration.
     *
     * @param request The request containing all of the options related to the update request.
     * @return Result of the setHTTPSAcceleration operation returned by the service.
     */
    public CommonResponse setDomainHttpsConfig(SetDomainHttpsConfigRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getDomain(), "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("https", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Update HSTS rules of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the update request.
     * @return Result of the setDomainHSTS operation returned by the service.
     */
    public CommonResponse setDomainHSTS(SetDomainHSTSRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getDomain(), "Domain should NOT be empty.");
        Validate.checkNotNull(request.getHSTS(), "HSTS should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        request.setDomain(null);
        internalRequest.addParameter("hsts", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get ipv6Dispatch config information of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Result of the getDomainClientIp operation returned by the service.
     */
    public GetDomainHSTSResponse getDomainHSTS(String domain) {
        Validate.checkStringNotEmpty(domain, "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("hsts", "");
        return invokeHttpClient(internalRequest, GetDomainHSTSResponse.class);
    }


    public CommonResponse setDomainOCSPSwitch(String domain, boolean ocsp) {
        return setDomainOCSPSwitch(new SetDomainOCSPRequest().withDomain(domain).withOcsp(ocsp));
    }

    /**
     * Update OCSP of specified domain acceleration.
     *
     * @param request domain and switch
     * @return
     */
    public CommonResponse setDomainOCSPSwitch(SetDomainOCSPRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getDomain(), "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, DOMAIN,
                request.getDomain(), "config");
        internalRequest.addParameter("ocsp", "");
        attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CommonResponse.class);
    }

    /**
     * Get ipv6Dispatch config information of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Result of the getDomainClientIp operation returned by the service.
     */
    public GetDomainOCSPSwitchResponse getDomainOCSPSwitch(String domain) {
        Validate.checkStringNotEmpty(domain, "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                DOMAIN, domain, "config");
        internalRequest.addParameter("ocsp", "");
        return invokeHttpClient(internalRequest, GetDomainOCSPSwitchResponse.class);
    }

    /** -------- HTTPS配置 END -------- **/

    /** -------- 证书配置 START -------- **/

    /**
     * add/update certificate of specified domain
     *
     * @param request
     * @return
     */
    public SetDomainCertResponse setDomainCert(SetDomainCertRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getCertificate(), "Certificate should NOT be null.");
        Validate.checkStringNotEmpty(request.getHttpsEnable(), "HttpsEnable should NOT be empty.");
        Validate.checkStringNotEmpty(request.getDomain(), "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                request.getDomain(), "certificates");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, SetDomainCertResponse.class);
    }

    /**
     * Get Domain Cert detail information of specified domain acceleration.
     *
     * @param domain The request containing all of the options related to the domain.
     * @return Result of the getDomainClientIp operation returned by the service.
     */
    public GetDomainCertResponse getDomainCert(String domain) {
        Validate.checkStringNotEmpty(domain, "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.GET,
                domain, "certificates");
        return invokeHttpClient(internalRequest, GetDomainCertResponse.class);
    }

    /**
     * delete certificate of specified domain
     *
     * @param domain
     * @return
     */
    public CdnResponse deleteDomainCert(String domain) {
        Validate.checkStringNotEmpty(domain, "Domain should NOT be empty.");
        InternalRequest internalRequest = createRequest(new CdnRequest(), HttpMethodName.DELETE,
                domain, "certificates");
        return invokeHttpClient(internalRequest, CdnResponse.class);
    }


    /**
     * Get Domain Cert detail information of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the domain.
     * @return Result of the GetHttpsDomainResponse operation returned by the service.
     */
    public GetHttpsDomainResponse getHttpsDomains(GetHttpsDomainRequest request) {
        if (request == null) {
            request = new GetHttpsDomainRequest();
        }

        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
                "httpsDomains");

        if (request.getCertCommonName() != null) {
            internalRequest.addParameter("certCommonName", request.getCertCommonName());
        }

        if (request.getCertId() != null) {
            internalRequest.addParameter("certId", request.getCertId());
        }

        if (request.getCertName() != null) {
            internalRequest.addParameter("certName", request.getCertName());
        }

        if (request.getDomain() != null) {
            internalRequest.addParameter("domain", request.getDomain());
        }

        if (request.getPageNo() != null) {
            internalRequest.addParameter("pageNo", request.getPageNo().toString());
        }

        if (request.getPageSize() != null) {
            internalRequest.addParameter("pageSize", request.getPageSize().toString());
        }

        if (request.getStatus() != null) {
            internalRequest.addParameter("status", request.getStatus().toString());
        }

        return invokeHttpClient(internalRequest, GetHttpsDomainResponse.class);
    }

    /**
     * add/update certificate of specified domain
     *
     * @param request
     * @return
     */
    public SetDomainCertResponse bathUploadDomainCert(BatchUploadCertRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getCertificate(), "Certificate should NOT be null.");
        Validate.checkNotNull(request.getDomains(), "Domains should NOT be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST,
                DOMAIN, "certificate");
        internalRequest.addParameter("action", "put");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, SetDomainCertResponse.class);
    }

    /** -------- 证书配置 END -------- **/

    /** -------- 缓存管理 START -------- **/

    /**
     * Post purge request
     *
     * @param url The URL to be purged.
     * @return Result of the purge operation returned by the service.
     */
    public PurgeResponse purge(String url) {
        return purge(new PurgeRequest().addTask(new PurgeTask().withUrl(url)));
    }

    /**
     * Post purge request
     *
     * @param directory The directory to be purged.
     * @return Result of the purge operation returned by the service.
     */
    public PurgeResponse purgeDirectory(String directory) {
        return purge(new PurgeRequest().addTask(new PurgeTask().withDirectory(directory)));
    }

    /**
     * Post purge request
     *
     * @param request The request containing all of the URLs to be purged.
     * @return Result of the purge operation returned by the service.
     */
    public PurgeResponse purge(PurgeRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getTasks(), "Task should NOT be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                CACHE, "purge");
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, PurgeResponse.class);
    }

    /**
     * Get purge status with specified attributes.
     *
     * @param request The request containing the task id returned by purge operation.
     * @return Details of tasks
     */
    public GetPurgeStatusResponse getPurgeStatus(GetPurgeStatusRequest request) {
        if (request == null) {
            request = new GetPurgeStatusRequest();
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET,
                CACHE, "purge");
        if (request.getId() != null) {
            internalRequest.addParameter("id", request.getId());
        }

        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime",
                    DateUtils.formatAlternateIso8601Date(request.getStartTime()));
        }

        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime",
                    DateUtils.formatAlternateIso8601Date(request.getEndTime()));
        }

        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }

        if (request.getUrl() != null) {
            internalRequest.addParameter("url", request.getUrl());
        }

        return this.invokeHttpClient(internalRequest, GetPurgeStatusResponse.class);
    }

    /**
     * Get cache operation quota.
     *
     * @return Details of statistics
     */
    public GetCacheQuotaResponse getCacheQuota() {
        InternalRequest internalRequest = this.createRequest(new CdnRequest(), HttpMethodName.GET,
                CACHE, "quota");
        return this.invokeHttpClient(internalRequest, GetCacheQuotaResponse.class);
    }

    /**
     * Post prefetch request
     *
     * @param url The URL to be prefetched.
     * @return Result of the prefetch operation returned by the service.
     */
    public PrefetchResponse prefetch(String url) {
        return prefetch(new PrefetchRequest().addTask(new PrefetchTask().withUrl(url)));
    }

    /**
     * Post prefetch request
     *
     * @param request The request containing all of the URLs to be prefetched.
     * @return Result of the prefetch operation returned by the service.
     */
    public PrefetchResponse prefetch(PrefetchRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getTasks(), "Task should NOT be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                CACHE, "prefetch");
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, PrefetchResponse.class);
    }

    /**
     * Get prefetch status with specified attributes.
     *
     * @param request The request containing the task id returned by prefetch operation.
     * @return Details of tasks
     */
    public GetPrefetchStatusResponse getPrefetchStatus(GetPrefetchStatusRequest request) {
        if (request == null) {
            request = new GetPrefetchStatusRequest();
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET,
                CACHE, "prefetch");
        if (request.getId() != null) {
            internalRequest.addParameter("id", request.getId());
        }

        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime",
                    DateUtils.formatAlternateIso8601Date(request.getStartTime()));
        }

        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime",
                    DateUtils.formatAlternateIso8601Date(request.getEndTime()));
        }

        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }

        if (request.getUrl() != null) {
            internalRequest.addParameter("url", request.getUrl());
        }

        return this.invokeHttpClient(internalRequest, GetPrefetchStatusResponse.class);
    }

    /** -------- 缓存管理 END -------- **/

    /** -------- 日志统计接口 START -------- **/

    /**
     * Get URLs of logmodel files
     *
     * @param domain domain info
     * @param startTime startTime
     * @param endTime endTime
     * @return Details of statistics
     */
    public GetDomainLogResponse getDomainLog(String domain, Date startTime, Date endTime) {
        Validate.checkNotNull(domain, "The parameter request should NOT be null.");
        Validate.checkNotNull(startTime, "StartTime should NOT be null.");
        Validate.checkNotNull(endTime, "EndTime should NOT be null.");

        CdnRequest request = new CdnRequest();

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET,
                LOG, domain, "log");

        internalRequest.addParameter("startTime", DateUtils.formatAlternateIso8601Date(startTime));
        internalRequest.addParameter("endTime", DateUtils.formatAlternateIso8601Date(endTime));

        return this.invokeHttpClient(internalRequest, GetDomainLogResponse.class);
    }

    /**
     * Get URLs of logmodel files
     *
     * @param domain domain info
     * @param startTime startTime
     * @param endTime endTime
     * @return Details of statistics
     */
    public GetDomainLogResponse getDomainLog(String domain, String startTime, String endTime) {
        Validate.checkNotNull(domain, "The parameter request should NOT be null.");
        Validate.checkNotNull(startTime, "StartTime should NOT be null.");
        Validate.checkNotNull(endTime, "EndTime should NOT be null.");

        CdnRequest request = new CdnRequest();

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET,
                LOG, domain, "log");

        internalRequest.addParameter("startTime", startTime);
        internalRequest.addParameter("endTime", endTime);

        return this.invokeHttpClient(internalRequest, GetDomainLogResponse.class);
    }

    /**
     * Get multiple domain URLs of logmodel files
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetDomainListLogResponse getDomainListLog(GetDomainListLogRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getStartTime(), "StartTime should NOT be null.");
        Validate.checkNotNull(request.getEndTime(), "EndTime should NOT be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                LOG, "list");
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, GetDomainListLogResponse.class);
    }

    /**
     * Get statistics metric with specified attributes (stat_version_2.0).
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetMetricStatResponse getStatMetricData(GetStatMetricRequest request) {
        GetStatMetricResponse statMetricData = getStatMetricDefaultData(request);
        return JsonUtils.fromJsonString(JsonUtils.toJsonString(statMetricData), GetMetricStatResponse.class);
    }

    /**
     * Get statistics metric with specified attributes (stat_version_2.0).
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetStatMetricResponse getStatMetricDefaultData(GetStatMetricRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getMetric(), "Metric should NOT be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                STAT, "query");
        // this.attachRequestToBody(request, internalRequest);
        byte[] content;
        try {
            content = JsonUtils.toJsonString(request).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("utf-8 encoding not supported!", e);
        }
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(content.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json; charset=utf-8");
        internalRequest.setContent(RestartableInputStream.wrap(content));
        return this.invokeHttpClient(internalRequest, GetStatMetricResponse.class);
    }

    /**
     * Get statistics metric with specified attributes (stat_version_2.0).
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetMonth95Response getMonth95Data(GetMonth95Request request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getType(), "Type should NOT be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, "billing");

        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, GetMonth95Response.class);
    }

    /**
     * Get statistics metric with specified attributes (stat_version_2.0).
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetTopStatResponse getTopStatData(GetStatMetricRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getMetric(), "Metric should NOT be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                STAT, "query");
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, GetTopStatResponse.class);
    }

    /**
     * Get statistics metric with specified attributes (stat_version_2.0).
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetIpv6StatResponse getStatIpv6Data(GetIpv6StatRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                STAT, "ipv6");

        internalRequest.addParameter("stat_type", request.getStatType() == null ? "all" : request.getStatType());
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, GetIpv6StatResponse.class);
    }

    /**
     * Get statistics metric with specified attributes (stat_version_2.0).
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetIpv6RegionStatResponse getStatRegionIpv6Data(GetIpv6StatRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                STAT, "ipv6");

        internalRequest.addParameter("stat_type", "region");
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, GetIpv6RegionStatResponse.class);
    }

    /**
     * Get statistics metric with specified attributes (stat_version_2.0).
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetErrorCodeStatResponse getErrorCodeStatData(GetStatMetricRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        GetStatMetricResponse statMetricData = getStatMetricDefaultData(request.withMetric("error"));
        return JsonUtils.fromJsonString(JsonUtils.toJsonString(statMetricData), GetErrorCodeStatResponse.class);
    }

    /**
     * Get statistics metric with specified attributes (stat_version_2.0).
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetUploadStatResponse getUploadStatData(GetUploadStatRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                STAT, "upload_detail");

        internalRequest.addParameter("type", request.getType() == null ? "all" : request.getType());
        this.attachRequestToBody(request, internalRequest);

        return this.invokeHttpClient(internalRequest, GetUploadStatResponse.class);
    }

    /**
     * Get upload stat peak with specified attributes (stat_version_2.0).
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetUploadStatResponse getUploadPeakStatData(GetUploadStatRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");

        if (request.getType() != null && !"peak".equals(request.getType()) && !"95_peak".equals(request.getType())
                && !"day_peak".equals(request.getType())) {
            throw new IllegalArgumentException("Type Error");
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                STAT, "upload_peak");

        if (request.getType() != null) {
            internalRequest.addParameter("type", request.getType() == null ? "peak" : request.getType());
        }
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, GetUploadStatResponse.class);
    }

    /**
     * Get Xcdn stat with specified attributes (stat_version_2.0).
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetXcdnStatMetricResponse getXcdnStatData(GetXcdnStatMetricRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getMetric(), "Metric should NOT be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                "xcdn", STAT, "query");

        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, GetXcdnStatMetricResponse.class);
    }

    /** -------- 日志统计接口 END -------- **/

    /** -------- 工具接口 START -------- **/

    /**
     * Get the description of certain IP address.
     *
     * @param ip IP address.
     * @return Details of statistics
     */
    public DescribeIpResponse describeIp(String ip) {
        return describeIp(new DescribeIpRequest().withIp(ip));
    }

    /**
     * Get the description of certain IP address.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public DescribeIpResponse describeIp(DescribeIpRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getIp(), "IP should NOT be null.");
        Validate.checkStringNotEmpty(request.getAction(), "ACTION should NOT be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, UTILS);
        internalRequest.addParameter("action", request.getAction());
        internalRequest.addParameter("ip", request.getIp());

        return this.invokeHttpClient(internalRequest, DescribeIpResponse.class);
    }

    /**
     * Get the description of certain IP address.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public DescribeIpsResponse describeIps(DescribeIpRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkMultyParamsNotBothEmpty(request.getIps(), "IPs should NOT be null.");
        Validate.checkStringNotEmpty(request.getAction(), "ACTION should NOT be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, UTILS, "ips");
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, DescribeIpsResponse.class);
    }

    /**
     * Get the node address.
     *
     * @return Details of statistics
     */
    public GetNodeListResponse getNodeList() {
        InternalRequest internalRequest = this.createRequest(new CdnRequest(), HttpMethodName.GET,
                "nodes", "list");

        return this.invokeHttpClient(internalRequest, GetNodeListResponse.class);
    }

    /**
     * Get the quota of forbidden.
     *
     * @return ForbiddenQuota
     */
    public GetForbiddenQuota getForbiddenQuota() {
        InternalRequest internalRequest = this.createRequest(new CdnRequest(), HttpMethodName.GET,
                "firewalls", "forbidden", "quota");

        return this.invokeHttpClient(internalRequest, GetForbiddenQuota.class);
    }

    /**
     * Get the quota of forbidden.
     *
     * @return ForbiddenQuota
     */
    public GetForbiddenUrlsResponse getForbiddenUrls(GetForbiddenUrlsRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = this.createRequest(new CdnRequest(), HttpMethodName.GET,
                "firewalls", "forbidden", "urls");

        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        internalRequest.addParameter("orderBy", request.getOrderBy());
        if (request.getUrl() != null) {
            internalRequest.addParameter("url", request.getUrl());
        }

        return this.invokeHttpClient(internalRequest, GetForbiddenUrlsResponse.class);
    }

    /**
     * Get the quota of forbidden.
     *
     * @return ForbiddenQuota
     */
    public GetForbiddenOperateHistoriesResponse getForbiddenOperateHistories(
                                        GetForbiddenOperateHistoriesRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = this.createRequest(new CdnRequest(), HttpMethodName.GET,
                "firewalls", "forbidden", "operateHistories");
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        internalRequest.addParameter("orderBy", request.getOrderBy());

        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", request.getStartTime());
        }
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        if (request.getUrl() != null) {
            internalRequest.addParameter("url", request.getUrl());
        }

        return this.invokeHttpClient(internalRequest, GetForbiddenOperateHistoriesResponse.class);
    }

    /**
     * Get the description of certain IP address.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public CdnResponse setForbiddenBan(SetForbiddenUrlsRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getUrls(), "The parameter request should NOT be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                "firewalls", "forbidden", "ban");

        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, CdnResponse.class);
    }

    /**
     * Get the description of certain IP address.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public CdnResponse setForbiddenUnBan(SetForbiddenUrlsRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getUrls(), "The parameter request should NOT be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                "firewalls", "forbidden", "unban");

        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, CdnResponse.class);
    }

    /** -------- 工具接口 EDN -------- **/

    /** -------- 用量接口 START -------- **/

    /**
     * 用量查询
     * 本接口用于查看资源包详情。
     *
     * @param request
     * @return
     */
    public GetPackageUsageListResponse getPackageUsageList(GetPackageUsageListRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                "package", "usagelist");

        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, GetPackageUsageListResponse.class);
    }

    /** -------- 用量接口 EDN -------- **/

    /** -------- 动态加速接口 START -------- **/

    /**
     * Update dsa service of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the update request.
     * @return Result of the setDsa operation returned by the service.
     */
    public CdnResponse setDsa(SetDsaRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getAction(), "Action should NOT be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, "dsa");
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, CdnResponse.class);
    }

    /**
     * Get Dsa Domain List.
     *
     * @return Details of DsaDomain
     */
    public GetDsaDomainListResponse getDsaDomainList() {
        CdnRequest request = new CdnRequest();
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, "dsa", DOMAIN);
        return this.invokeHttpClient(internalRequest, GetDsaDomainListResponse.class);
    }

    /**
     * Update Dsa rules of specified domain acceleration.
     *
     * @param request The request containing all of the options related to the update request.
     * @return Result of the setDomainDsa operation returned by the service.
     */
    public void setDomainDsa(SetDomainDsaRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("dsa", "");
        this.attachRequestToBody(request, internalRequest);
        invokeHttpClient(internalRequest, CdnResponse.class);
    }

    /** -------- 动态加速接口 END -------- **/

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest    The original BCE request created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object populated with endpoint, resource path and specific
     * parameters to send.
     */
    private InternalRequest createRequest(
            AbstractBceRequest bceRequest, HttpMethodName httpMethod, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION);

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
     * put json object into http content for put or post request.
     *
     * @param request     json object of rest request
     * @param httpRequest http request object
     */
    private void attachRequestToBody(AbstractBceRequest request, InternalRequest httpRequest) {
        byte[] content;
        try {
            content = JsonUtils.toJsonString(request).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("utf-8 encoding not supported!", e);
        }
        httpRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(content.length));
        httpRequest.addHeader(Headers.CONTENT_TYPE, "application/json; charset=utf-8");
        httpRequest.setContent(RestartableInputStream.wrap(content));
    }

    private void validateAndFillRequestUrl(InternalRequest internalRequest,
                                           String type, Date startTime, Date endTime,
                                           String url, String marker) {
        if (type != null) {
            internalRequest.addParameter("type", type);
        }

        if (startTime != null) {
            internalRequest.addParameter("startTime", DateUtils.formatAlternateIso8601Date(startTime));
        }

        if (endTime != null) {
            internalRequest.addParameter("endTime", DateUtils.formatAlternateIso8601Date(endTime));
        }

        if (url != null) {
            internalRequest.addParameter("url", url);
        }

        if (marker != null) {
            internalRequest.addParameter("marker", String.valueOf(marker));
        }

    }
}
