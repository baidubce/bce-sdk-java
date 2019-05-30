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

import static com.baidubce.util.Validate.checkNotNull;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashSet;

import com.baidubce.AbstractBceClient;
import com.baidubce.auth.SignOptions;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;

import com.baidubce.services.cdn.model.ListDomainsRequest;
import com.baidubce.services.cdn.model.ListDomainsResponse;
import com.baidubce.services.cdn.model.OriginPeer;
import com.baidubce.services.cdn.model.PrefetchRequest;
import com.baidubce.services.cdn.model.PrefetchResponse;
import com.baidubce.services.cdn.model.PrefetchTask;
import com.baidubce.services.cdn.model.PurgeRequest;
import com.baidubce.services.cdn.model.PurgeResponse;
import com.baidubce.services.cdn.model.PurgeTask;
import com.baidubce.services.cdn.model.RequestAuth;
import com.baidubce.services.cdn.model.SetDomainCacheFullUrlRequest;
import com.baidubce.services.cdn.model.SetDomainCacheFullUrlResponse;
import com.baidubce.services.cdn.model.SetDomainCacheTTLRequest;
import com.baidubce.services.cdn.model.SetDomainCacheTTLResponse;
import com.baidubce.services.cdn.model.SetDomainIpACLRequest;
import com.baidubce.services.cdn.model.SetDomainIpACLResponse;
import com.baidubce.services.cdn.model.SetDomainLimitRateRequest;
import com.baidubce.services.cdn.model.SetDomainLimitRateResponse;
import com.baidubce.services.cdn.model.SetDomainOriginRequest;
import com.baidubce.services.cdn.model.SetDomainOriginResponse;
import com.baidubce.services.cdn.model.SetDomainRefererACLRequest;
import com.baidubce.services.cdn.model.SetDomainRefererACLResponse;
import com.baidubce.services.cdn.model.GetPurgeStatusRequest;
import com.baidubce.services.cdn.model.GetPurgeStatusResponse;
import com.baidubce.services.cdn.model.GetStatAvgSpeedRequest;
import com.baidubce.services.cdn.model.GetStatAvgSpeedResponse;
import com.baidubce.services.cdn.model.GetStatMetricRequest;
import com.baidubce.services.cdn.model.GetStatMetricResponse;
import com.baidubce.services.cdn.model.CreateDomainRequest;
import com.baidubce.services.cdn.model.CreateDomainResponse;
import com.baidubce.services.cdn.model.DeleteDomainRequest;
import com.baidubce.services.cdn.model.DeleteDomainResponse;
import com.baidubce.services.cdn.model.EnableDomainRequest;
import com.baidubce.services.cdn.model.EnableDomainResponse;
import com.baidubce.services.cdn.model.GetCacheQuotaRequest;
import com.baidubce.services.cdn.model.GetCacheQuotaResponse;
import com.baidubce.services.cdn.model.GetDomainCacheTTLRequest;
import com.baidubce.services.cdn.model.GetDomainCacheTTLResponse;
import com.baidubce.services.cdn.model.GetPrefetchStatusRequest;
import com.baidubce.services.cdn.model.GetPrefetchStatusResponse;
import com.baidubce.services.cdn.model.GetStatFlowRequest;
import com.baidubce.services.cdn.model.GetStatFlowResponse;
import com.baidubce.services.cdn.model.GetStatHitRateRequest;
import com.baidubce.services.cdn.model.GetStatHitRateResponse;
import com.baidubce.services.cdn.model.GetStatHttpCodeRequest;
import com.baidubce.services.cdn.model.GetStatHttpCodeResponse;
import com.baidubce.services.cdn.model.GetStatPvRequest;
import com.baidubce.services.cdn.model.GetStatPvResponse;
import com.baidubce.services.cdn.model.GetStatSrcFlowRequest;
import com.baidubce.services.cdn.model.GetStatSrcFlowResponse;
import com.baidubce.services.cdn.model.GetStatTopRefererRequest;
import com.baidubce.services.cdn.model.GetStatTopRefererResponse;
import com.baidubce.services.cdn.model.GetStatTopUrlRequest;
import com.baidubce.services.cdn.model.GetStatTopUrlResponse;
import com.baidubce.services.cdn.model.GetStatUvRequest;
import com.baidubce.services.cdn.model.GetStatUvResponse;
import com.baidubce.services.cdn.model.DisableDomainRequest;
import com.baidubce.services.cdn.model.DisableDomainResponse;
import com.baidubce.services.cdn.model.GetDomainConfigRequest;
import com.baidubce.services.cdn.model.GetDomainConfigResponse;
import com.baidubce.services.cdn.model.GetDomainLogRequest;
import com.baidubce.services.cdn.model.GetDomainLogResponse;
import com.baidubce.services.cdn.model.HttpsConfig;
import com.baidubce.services.cdn.model.SetHttpsConfigRequest;
import com.baidubce.services.cdn.model.SetHttpsConfigResponse;
import com.baidubce.services.cdn.model.SetRequestAuthRequest;
import com.baidubce.services.cdn.model.SetRequestAuthResponse;
import com.baidubce.services.cdn.model.DescribeIpRequest;
import com.baidubce.services.cdn.model.DescribeIpResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.util.DateUtils;

/**
 * Client for accessing CDN Services. 
 * Created by sunyixing on 2016/1/9.
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
     * The common URI prefix for log operation.
     */
    private static final String LOG = "log";
    
    /**
     * The common URI prefix for utils operation.
     */
    private static final String UTILS = "utils";
    
    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};
    
    private static final HttpResponseHandler[] cdnHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
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
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, DOMAIN, request.getDomain());
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, CreateDomainResponse.class);
    }
    
    /**
     * Enable an existing domain acceleration.
     * 
     * @param domain The specified domain name.
     */
    public void enableDomain(String domain) {
        enableDomain(new EnableDomainRequest().withDomain(domain));
    }
    
    /**
     * Enable an existing domain acceleration.
     * 
     * @param request The request containing user-defined domain information.
     * @return Result of the enableDomain operation returned by the service.
     */
    public EnableDomainResponse enableDomain(EnableDomainRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, DOMAIN, request.getDomain());
        internalRequest.addParameter("enable", "");
        return invokeHttpClient(internalRequest, EnableDomainResponse.class);
    }
    
    /**
     * Disable an existing domain acceleration.
     * 
     * @param domain Name of the domain.
     */
    public void disableDomain(String domain) {
        disableDomain(new DisableDomainRequest().withDomain(domain));
    }
    
    /**
     * Disable an existing domain acceleration.
     * 
     * @param request The request containing user-defined domain information.
     * @return Result of the disableDomain operation returned by the service.
     */
    public DisableDomainResponse disableDomain(DisableDomainRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, DOMAIN, request.getDomain());
        internalRequest.addParameter("disable", "");
        return invokeHttpClient(internalRequest, DisableDomainResponse.class);
    }
    
    /**
     * Delete an existing domain acceleration.
     * 
     * @param domain Name of the domain.
     */
    public void deleteDomain(String domain) {
        deleteDomain(new DeleteDomainRequest().withDomain(domain));
    }
    
    /**
     * Delete an existing domain acceleration
     * 
     * @param request The request containing user-defined domain information.
     * @return Result of the deleteDomain operation returned by the service.
     */
    public DeleteDomainResponse deleteDomain(DeleteDomainRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, DOMAIN, request.getDomain());
        return invokeHttpClient(internalRequest, DeleteDomainResponse.class);
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
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, DOMAIN);
        return invokeHttpClient(internalRequest, ListDomainsResponse.class);
    }

    /**
     * Get detailed information of a domain.
     * 
     * @param domain Name of the domain.
     * @return getDomainConfig of the getDomainConfig operation returned by the service.
     */
    public GetDomainConfigResponse getDomainConfig(String domain) {
        return getDomainConfig(new GetDomainConfigRequest().withDomain(domain));
    }
    
    /**
     * Get detailed information of a domain.
     * 
     * @param request The request containing all of the options related to the domain.
     * @return getDomainConfig of the getDomainConfig operation returned by the service.
     */
    public GetDomainConfigResponse getDomainConfig(GetDomainConfigRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, DOMAIN, request.getDomain(), "config");
        return invokeHttpClient(internalRequest, GetDomainConfigResponse.class);
    }
    
    /**
     * Update origin of specified domain acceleration.
     * 
     * @param domain Name of the domain.
     * @param peer The peer address of new origin.
     */
    public void setDomainOrigin(String domain, String peer) {
        List<OriginPeer> origin = new ArrayList<OriginPeer>();
        origin.add(new OriginPeer().withPeer(peer));
        SetDomainOriginRequest request = new SetDomainOriginRequest()
                .withDomain(domain)
                .withOrigin(origin);
        setDomainOrigin(request);
    } 
    
    /**
     * Update origin of specified domain acceleration.
     * 
     * @param request The request containing all of the options related to the domain.
     * @return Result of the setDomainOrigin operation returned by the service.
     */
    public SetDomainOriginResponse setDomainOrigin(SetDomainOriginRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("origin","");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, SetDomainOriginResponse.class);
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
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("cacheTTL","");
        return invokeHttpClient(internalRequest, GetDomainCacheTTLResponse.class);
    }
    
    /**
     * Update cache policies of specified domain acceleration.
     * 
     * @param request The request containing all of the options related to the update request.
     * @return Result of the setDomainCacheTTL operation returned by the service.
     */
    public SetDomainCacheTTLResponse setDomainCacheTTL(SetDomainCacheTTLRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("cacheTTL","");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, SetDomainCacheTTLResponse.class);
    } 
    
    /**
     * Update cache policy of specified domain acceleration.
     * 
     * @param domain Name of the domain.
     * @param setting For true, treat the full URL as unique cache id, otherwise
     *                ignore query string parameters.
     */
    public void setDomainCacheFullUrl(String domain, boolean setting) {
        SetDomainCacheFullUrlRequest request = new SetDomainCacheFullUrlRequest()
                .withDomain(domain);
        request.setCacheFullUrl(setting);
        setDomainCacheFullUrl(request);
    }
    
    /**
     * Update cache policy of specified domain acceleration.
     * 
     * @param request The request containing all of the options related to the update request.
     * @return Result of the setDomainCacheFullUrl operation returned by the service.
     */
    public SetDomainCacheFullUrlResponse setDomainCacheFullUrl(SetDomainCacheFullUrlRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("cacheFullUrl","");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, SetDomainCacheFullUrlResponse.class);
    }
    
    /**
     * Update RefererACL rules of specified domain acceleration.
     * 
     * @param request The request containing all of the options related to the update request.
     * @return Result of the setDomainRefererACL operation returned by the service.
     */
    public SetDomainRefererACLResponse setDomainRefererACL(SetDomainRefererACLRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("refererACL","");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, SetDomainRefererACLResponse.class);
    }
    
    /**
     * Update IpACL rules of specified domain acceleration.
     * 
     * @param request The request containing all of the options related to the update request.
     * @return Result of the setDomainIpACL operation returned by the service.
     */
    public SetDomainIpACLResponse setDomainIpACL(SetDomainIpACLRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("ipACL","");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, SetDomainIpACLResponse.class);
    }

    /**
     * Set the rate limit of specified domain acceleration.
     * 
     * @param domain Name of the domain.
     * @param limitRate The limit of downloading rate, in Bytes/s.
     */
    public void setDomainLimitRate(String domain, int limitRate) {
        SetDomainLimitRateRequest request = new SetDomainLimitRateRequest()
                .withDomain(domain)
                .withLimitRate(limitRate);
        setDomainLimitRate(request);
    }
    
    /**
     * Set the rate limit of specified domain acceleration.
     * 
     * @param request The request containing all of the options related to the update request.
     * @return Result of the setDomainLimitRate operation returned by the service.
     */
    public SetDomainLimitRateResponse setDomainLimitRate(SetDomainLimitRateRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("limitRate","");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, SetDomainLimitRateResponse.class);
    }
    
    /**
     * Set HTTPS with certain configuration.
     * 
     * @param domain Name of the domain.
     * @param https The configuration of HTTPS.
     */
    public void setHttpsConfig(String domain, HttpsConfig https) {
        SetHttpsConfigRequest request = new SetHttpsConfigRequest()
                .withDomain(domain)
                .withHttps(https);
        setHttpsConfig(request);
    }
    
    /**
     * Set HTTPS with certain configuration.
     * 
     * @param request The request containing all of the options related to the update request.
     * @return Result of the setHTTPSAcceleration operation returned by the service.
     */
    public SetHttpsConfigResponse setHttpsConfig(SetHttpsConfigRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = 
                createRequest(request, HttpMethodName.PUT, DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("https", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, SetHttpsConfigResponse.class);
    }
    
    /**
     * Set the request authentication.
     * 
     * @param domain Name of the domain.
     * @param requestAuth The configuration of authentication.
     */
    public void setRequestAuth(String domain, RequestAuth requestAuth) {
        SetRequestAuthRequest request = new SetRequestAuthRequest()
                .withDomain(domain)
                .withRequestAuth(requestAuth);
        setRequestAuth(request);
    }
    
    /**
     * Set the request authentication.
     * 
     * @param request The request containing all of the options related to the update request.
     * @return Result of the setHTTPSAcceleration operation returned by the service.
     */
    public SetRequestAuthResponse setRequestAuth(SetRequestAuthRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        InternalRequest internalRequest = 
                createRequest(request, HttpMethodName.PUT, DOMAIN, request.getDomain(), "config");
        internalRequest.addParameter("requestAuth", "");
        this.attachRequestToBody(request, internalRequest);
        return invokeHttpClient(internalRequest, SetRequestAuthResponse.class);
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
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, CACHE, "prefetch");
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, PrefetchResponse.class);
    }
    
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
     * @param request The request containing all of the URLs to be purged.
     * @return Result of the purge operation returned by the service.
     */
    public PurgeResponse purge(PurgeRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, CACHE, "purge");
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
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, CACHE, "purge");
        if (request.getId() != null) {
            internalRequest.addParameter("id", request.getId());
        }

        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", DateUtils.formatAlternateIso8601Date(request.getStartTime()));
        }

        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", DateUtils.formatAlternateIso8601Date(request.getEndTime()));
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
     * Get prefetch status with specified attributes.
     *
     * @param request The request containing the task id returned by prefetch operation.
     * @return Details of tasks
     */
    public GetPrefetchStatusResponse getPrefetchStatus(GetPrefetchStatusRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, CACHE, "prefetch");
        if (request.getId() != null) {
            internalRequest.addParameter("id", request.getId());
        }

        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", DateUtils.formatAlternateIso8601Date(request.getStartTime()));
        }

        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", DateUtils.formatAlternateIso8601Date(request.getEndTime()));
        }

        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }

        if (request.getUrl() != null) {
            internalRequest.addParameter("url", request.getUrl());
        }

        return this.invokeHttpClient(internalRequest, GetPrefetchStatusResponse.class);
    }
    
    /**
     * Get pv statistics with specified attributes.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetStatPvResponse getStatPv(GetStatPvRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, STAT, "pv");
        
        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", DateUtils.formatAlternateIso8601Date(request.getStartTime()));
        }
        
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", DateUtils.formatAlternateIso8601Date(request.getEndTime()));
        }
        
        if (request.getDomain() != null) {
            internalRequest.addParameter("domain", request.getDomain());
        }
        
        if (request.getPeriod() != null) {
            internalRequest.addParameter("period", String.valueOf(request.getPeriod()));
        }
        
        if (request.isWithRegion()) {
            internalRequest.addParameter("withRegion", "");
        }
        return this.invokeHttpClient(internalRequest, GetStatPvResponse.class);
    }
    
    /**
     * Get flow statistics with specified attributes.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetStatFlowResponse getStatFlow(GetStatFlowRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, STAT, "flow");
        
        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", DateUtils.formatAlternateIso8601Date(request.getStartTime()));
        }
        
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", DateUtils.formatAlternateIso8601Date(request.getEndTime()));
        }
        
        if (request.getDomain() != null) {
            internalRequest.addParameter("domain", request.getDomain());
        }
        
        if (request.getPeriod() != null) {
            internalRequest.addParameter("period", String.valueOf(request.getPeriod()));
        }
        
        if (request.isWithRegion()) {
            internalRequest.addParameter("withRegion", "");
        }
        return this.invokeHttpClient(internalRequest, GetStatFlowResponse.class);
    }
    
    /**
     * Get origin flow statistics with specified attributes.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetStatSrcFlowResponse getStatSrcFlow(GetStatSrcFlowRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, STAT, "srcflow");
        
        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", DateUtils.formatAlternateIso8601Date(request.getStartTime()));
        }
        
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", DateUtils.formatAlternateIso8601Date(request.getEndTime()));
        }
        
        if (request.getDomain() != null) {
            internalRequest.addParameter("domain", request.getDomain());
        }
        
        if (request.getPeriod() != null) {
            internalRequest.addParameter("period", String.valueOf(request.getPeriod()));
        }
        
        return this.invokeHttpClient(internalRequest, GetStatSrcFlowResponse.class);
    }
    /**
     * Get hit rate statistics with specified attributes.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetStatHitRateResponse getStatHitRate(GetStatHitRateRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, STAT, "hitrate");
        
        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", DateUtils.formatAlternateIso8601Date(request.getStartTime()));
        }
        
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", DateUtils.formatAlternateIso8601Date(request.getEndTime()));
        }
        
        if (request.getDomain() != null) {
            internalRequest.addParameter("domain", request.getDomain());
        }
        
        if (request.getPeriod() != null) {
            internalRequest.addParameter("period", String.valueOf(request.getPeriod()));
        }
        
        return this.invokeHttpClient(internalRequest, GetStatHitRateResponse.class);
    }
    
    /**
     * Get http code statistics with specified attributes.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetStatHttpCodeResponse getStatHttpCode(GetStatHttpCodeRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, STAT, "httpcode");
        
        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", DateUtils.formatAlternateIso8601Date(request.getStartTime()));
        }
        
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", DateUtils.formatAlternateIso8601Date(request.getEndTime()));
        }
        
        if (request.getDomain() != null) {
            internalRequest.addParameter("domain", request.getDomain());
        }
        
        if (request.getPeriod() != null) {
            internalRequest.addParameter("period", String.valueOf(request.getPeriod()));
        }
        
        if (request.isWithRegion()) {
            internalRequest.addParameter("withRegion", "");
        }
        
        return this.invokeHttpClient(internalRequest, GetStatHttpCodeResponse.class);
    }
    
    /**
     * Get top url statistics with specified attributes.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetStatTopUrlResponse getStatTopUrl(GetStatTopUrlRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, STAT, "topn", "url");
        
        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", DateUtils.formatAlternateIso8601Date(request.getStartTime()));
        }
        
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", DateUtils.formatAlternateIso8601Date(request.getEndTime()));
        }
        
        if (request.getDomain() != null) {
            internalRequest.addParameter("domain", request.getDomain());
        }
        
        if (request.getPeriod() != null) {
            internalRequest.addParameter("period", String.valueOf(request.getPeriod()));
        }
        
        return this.invokeHttpClient(internalRequest, GetStatTopUrlResponse.class);
    }
    
    /**
     * Get top http referer statistics with specified attributes.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetStatTopRefererResponse getStatTopReferer(GetStatTopRefererRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, STAT, "topn", "referer");
        
        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", DateUtils.formatAlternateIso8601Date(request.getStartTime()));
        }
        
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", DateUtils.formatAlternateIso8601Date(request.getEndTime()));
        }
        
        if (request.getDomain() != null) {
            internalRequest.addParameter("domain", request.getDomain());
        }
        
        if (request.getPeriod() != null) {
            internalRequest.addParameter("period", String.valueOf(request.getPeriod()));
        }
        
        return this.invokeHttpClient(internalRequest, GetStatTopRefererResponse.class);
    }
    
    /**
     * Get uv statistics with specified attributes.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetStatUvResponse getStatUv(GetStatUvRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, STAT, "uv");
        
        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", DateUtils.formatAlternateIso8601Date(request.getStartTime()));
        }
        
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", DateUtils.formatAlternateIso8601Date(request.getEndTime()));
        }
        
        if (request.getDomain() != null) {
            internalRequest.addParameter("domain", request.getDomain());
        }
        
        if (request.getPeriod() != null) {
            internalRequest.addParameter("period", String.valueOf(request.getPeriod()));
        }
        
        return this.invokeHttpClient(internalRequest, GetStatUvResponse.class);
    }
    
    /**
     * Get average speed statistics with specified attributes.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetStatAvgSpeedResponse getStatAvgSpeed(GetStatAvgSpeedRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, STAT, "avgspeed");
        
        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", DateUtils.formatAlternateIso8601Date(request.getStartTime()));
        }
        
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", DateUtils.formatAlternateIso8601Date(request.getEndTime()));
        }
        
        if (request.getDomain() != null) {
            internalRequest.addParameter("domain", request.getDomain());
        }
        
        if (request.getPeriod() != null) {
            internalRequest.addParameter("period", String.valueOf(request.getPeriod()));
        }
        
        return this.invokeHttpClient(internalRequest, GetStatAvgSpeedResponse.class);
    }
    
    /**
     * Get cache operation quota.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetCacheQuotaResponse getCacheQuota() {
        GetCacheQuotaRequest request = new GetCacheQuotaRequest();
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, CACHE, "quota");
        return this.invokeHttpClient(internalRequest, GetCacheQuotaResponse.class);
    }
    
    /**
     * Get cache operation quota.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetCacheQuotaResponse getCacheQuota(GetCacheQuotaRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, CACHE, "quota");
        return this.invokeHttpClient(internalRequest, GetCacheQuotaResponse.class);
    }
    
    /**
     * Get URLs of log files
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetDomainLogResponse getDomainLog(GetDomainLogRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, LOG, request.getDomain(), "log");
        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", DateUtils.formatAlternateIso8601Date(request.getStartTime()));
        }
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", DateUtils.formatAlternateIso8601Date(request.getEndTime()));
        }
        return this.invokeHttpClient(internalRequest, GetDomainLogResponse.class);
    }
    
    /**
     * Get the description of certain IP address.
     *
     * @param ip IP address.
     * @return Details of statistics
     */
    public DescribeIpResponse describeIp(String ip) {
        DescribeIpRequest request = new DescribeIpRequest()
                .withIp(ip);
        return describeIp(request);
    }
    
    /**
     * Get the description of certain IP address.
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public DescribeIpResponse describeIp(DescribeIpRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, UTILS);
        checkNotNull(request.getIp());
        internalRequest.addParameter("action", request.getAction());
        internalRequest.addParameter("ip", request.getIp());
        
        return this.invokeHttpClient(internalRequest, DescribeIpResponse.class);
    }
    
    /**
     * Get statistics metric with specified attributes (stat_version_2.0).
     *
     * @param request
     *            The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetStatMetricResponse getStatMetricData(GetStatMetricRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, STAT, "/query");
        // this.attachRequestToBody(request, internalRequest);
        byte[] content;
        try {
            Map<String, Object> params = request.toMap();
            // In order to be compatible with the interface's old parameter, which was not be in camel-style.
            params.put("key_type", request.getKeyType());
            content = JsonUtils.toJsonString(params).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("utf-8 encoding not supported!", e);
        }
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(content.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json; charset=utf-8");
        internalRequest.setContent(RestartableInputStream.wrap(content));
        return this.invokeHttpClient(internalRequest, GetStatMetricResponse.class);
    }
    
    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest The original BCE request created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object populated with endpoint, resource path and specific
     *         parameters to send.
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
     * @param request
     *            json object of rest request
     * @param httpRequest
     *            http request object
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
}
