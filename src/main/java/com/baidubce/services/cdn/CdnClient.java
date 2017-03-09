/*
 * Copyright 2016 Baidu, Inc.
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
import com.baidubce.services.cdn.model.PrefetchRequest;
import com.baidubce.services.cdn.model.PrefetchResponse;
import com.baidubce.services.cdn.model.PurgeRequest;
import com.baidubce.services.cdn.model.PurgeResponse;
import com.baidubce.services.cdn.model.GetPurgeStatusRequest;
import com.baidubce.services.cdn.model.GetPurgeStatusResponse;
import com.baidubce.services.cdn.model.GetPrefetchStatusRequest;
import com.baidubce.services.cdn.model.GetPrefetchStatusResponse;
import com.baidubce.services.cdn.model.GetStatFlowRequest;
import com.baidubce.services.cdn.model.GetStatFlowResponse;

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
     * Post prefetch request
     *
     * @param request The request containing all of the urls to be prefetched.
     * @return The task id
     */
    public PrefetchResponse prefetch(PrefetchRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, CACHE, "prefetch");
        this.attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, PrefetchResponse.class);
    }

    /**
     * Post purge request
     * @param request The request containing all of the urls to be purged.
     * @return The task id
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
        return this.invokeHttpClient(internalRequest, GetStatFlowResponse.class);
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
