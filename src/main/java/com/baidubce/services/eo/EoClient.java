/*
 * Copyright 2026 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.eo;

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
import com.baidubce.services.eo.model.handler.EoJsonResponseHandler;
import com.baidubce.services.eo.model.cache.GetPrefetchStatusRequest;
import com.baidubce.services.eo.model.cache.GetPrefetchStatusResponse;
import com.baidubce.services.eo.model.cache.GetPurgeStatusRequest;
import com.baidubce.services.eo.model.cache.GetPurgeStatusResponse;
import com.baidubce.services.eo.model.cache.PrefetchRequest;
import com.baidubce.services.eo.model.cache.PrefetchResponse;
import com.baidubce.services.eo.model.cache.PurgeRequest;
import com.baidubce.services.eo.model.cache.PurgeResponse;
import com.baidubce.services.eo.model.logmodel.GetDomainListLogRequest;
import com.baidubce.services.eo.model.logmodel.GetDomainListLogResponse;
import com.baidubce.services.eo.model.stat.GetStatMetricRequest;
import com.baidubce.services.eo.model.stat.GetStatMetricResponse;
import com.baidubce.services.eo.model.site.GetSiteConfigRequest;
import com.baidubce.services.eo.model.site.GetSiteConfigResponse;
import com.baidubce.services.eo.model.site.SetSiteConfigRequest;
import com.baidubce.services.eo.model.site.SetSiteConfigResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.util.Validate;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class EoClient extends AbstractBceClient {
    /**
     * The version information for Document service APIs as URI prefix.
     */
    private static final String VERSION = "v2";

    /**
     * The common URI prefix for EO operation.
     */
    private static final String EO = "geo";

    /**
     * The URI prefix for site operation.
     */
    private static final String SITE = "site";

    /**
     * The URI prefix for site configuration operation.
     */
    private static final String CONFIG = "config";

    /**
     * The URI prefix for cache operation.
     */
    private static final String CACHE = "cache";

    /**
     * The URI prefix for cache purge operation.
     */
    private static final String PURGE = "purge";

    /**
     * The URI prefix for cache prefetch operation.
     */
    private static final String PREFETCH = "prefetch";

    /**
     * The URI prefix for stat operation.
     */
    private static final String STAT = "stat";

    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    private static final HttpResponseHandler[] EO_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new EoJsonResponseHandler()
    };

    public EoClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new client using the client configuration to access Geo services.
     *
     * @param clientConfiguration The client configuration options controlling how this client
     *                            connects to Document services (e.g. proxy settings, retry counts, etc).
     */
    public EoClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, EO_HANDLERS);
    }

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

    /**
     * Get multiple domain URLs of logmodel files
     *
     * @param request The request containing all the options related to the statistics.
     * @return Details of statistics
     */
    public GetDomainListLogResponse getDomainListLog(GetDomainListLogRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkNotNull(request.getSite(), "The parameter site should NOT be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                EO, "log");
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, GetDomainListLogResponse.class);
    }

    /**
     * Set the configuration of a site.
     *
     * Corresponds to {PUT /v2/geo/site/{site}/config}. The request carries the
     * configuration items (e.g. cacheTtl) in its body.
     *
     * @param request The request containing the site and the configuration items to set.
     * @return The status of setting the configuration.
     */
    public SetSiteConfigResponse setSiteConfig(SetSiteConfigRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getSite(), "The parameter site should NOT be empty.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT,
                EO, SITE, request.getSite(), CONFIG);
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, SetSiteConfigResponse.class);
    }

    /**
     * Query the configuration of a site. Supports querying all / a single / multiple config items.
     *
     * Corresponds to the full-amount { GET /v2/geo/site/{site}/config}. The result is a map
     * keyed by the config item name (JSON field name), so it carries the config key together with
     * its value, e.g. {@code {"cacheTtl": [ ... ]}}.
     *
     *
     * @param request The request containing the site to query.
     * @param keys    The config item keys to keep. If empty, all items are returned.
     * @return A map of {configKey : value}.
     */
    public Map<String, Object> getSiteConfig(GetSiteConfigRequest request, String... keys) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getSite(), "The parameter site should NOT be empty.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET,
                EO, SITE, request.getSite(), CONFIG);
        GetSiteConfigResponse response = this.invokeHttpClient(internalRequest, GetSiteConfigResponse.class);

        Map<String, Object> allConfig = JsonUtils.getObjectMapper()
                .convertValue(response, new TypeReference<LinkedHashMap<String, Object>>() {
                });

        if (keys == null || keys.length == 0) {
            return allConfig;
        }

        Map<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("metadata", allConfig.get("metadata"));

        for (String key : keys) {
            if (allConfig.containsKey(key)) {
                result.put(key, allConfig.get(key));
            }
        }
        return result;
    }

    /**
     * Purge cached resources of a site.
     *
     * Corresponds to { POST /v2/geo/cache/purge}. At most 1000 url tasks or 100 directory
     * tasks can be submitted at a time.
     *
     * @param request The request containing the site and the purge task list.
     * @return The id of the submitted purge task.
     */
    public PurgeResponse purge(PurgeRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getSite(), "The parameter site should NOT be empty.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                EO, CACHE, PURGE);
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, PurgeResponse.class);
    }

    /**
     * Query the status of cache purge tasks of a site.
     *
     * Corresponds to { GET /v2/geo/cache/purge}. Returns at most 100 records per call.
     *
     * @param request The request containing the query conditions.
     * @return The purge task status details.
     */
    public GetPurgeStatusResponse getPurgeStatus(GetPurgeStatusRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getSite(), "The parameter site should NOT be empty.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET,
                EO, CACHE, PURGE);
        internalRequest.addParameter("site", request.getSite());
        if (request.getId() != null) {
            internalRequest.addParameter("id", request.getId());
        }
        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", request.getStartTime());
        }
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        if (request.getType() != null) {
            internalRequest.addParameter("type", request.getType());
        }
        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        return this.invokeHttpClient(internalRequest, GetPurgeStatusResponse.class);
    }

    /**
     * Prefetch resources into cache of a site.
     *
     * Corresponds to { POST /v2/geo/cache/prefetch}. At most 1000 url tasks can be submitted
     * at a time.
     *
     * @param request The request containing the site and the prefetch task list.
     * @return The id of the submitted prefetch task.
     */
    public PrefetchResponse prefetch(PrefetchRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getSite(), "The parameter site should NOT be empty.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                EO, CACHE, PREFETCH);
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, PrefetchResponse.class);
    }

    /**
     * Query the status of cache prefetch tasks of a site.
     *
     * Corresponds to { GET /v2/geo/cache/prefetch}. Returns at most 100 records per call.
     *
     * @param request The request containing the query conditions.
     * @return The prefetch task status details.
     */
    public GetPrefetchStatusResponse getPrefetchStatus(GetPrefetchStatusRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");
        Validate.checkStringNotEmpty(request.getSite(), "The parameter site should NOT be empty.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET,
                EO, CACHE, PREFETCH);
        internalRequest.addParameter("site", request.getSite());
        if (request.getId() != null) {
            internalRequest.addParameter("id", request.getId());
        }
        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", request.getStartTime());
        }
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", request.getEndTime());
        }
        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        return this.invokeHttpClient(internalRequest, GetPrefetchStatusResponse.class);
    }

    /**
     * Query the stat metrics of a site or domain.
     *
     * Corresponds to { POST /v2/geo/stat}.
     *
     * @param request The request containing the metrics and query conditions.
     * @return The stat metric results keyed by the metric name.
     */
    public GetStatMetricResponse getStatMetric(GetStatMetricRequest request) {
        Validate.checkNotNull(request, "The parameter request should NOT be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, EO, STAT);
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, GetStatMetricResponse.class);
    }
}
