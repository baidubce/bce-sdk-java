/*
 * Copyright 2023 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.et;

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.BceCredentials;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.et.model.ApplyForEtRequest;
import com.baidubce.services.et.model.ApplyForEtResponse;
import com.baidubce.services.et.model.CreateEtChannelRequest;
import com.baidubce.services.et.model.CreateEtChannelResponse;
import com.baidubce.services.et.model.CreateEtChannelRouteResponse;
import com.baidubce.services.et.model.CreateEtChannelRouteRuleRequest;
import com.baidubce.services.et.model.EnableEtChannelIpv6Request;
import com.baidubce.services.et.model.Et;
import com.baidubce.services.et.model.EtChannelIdRequest;
import com.baidubce.services.et.model.EtChannelRouteRuleIdRequest;
import com.baidubce.services.et.model.ListEtChannelRouteRulesRequest;
import com.baidubce.services.et.model.ListEtChannelRouteRulesResponse;
import com.baidubce.services.et.model.ListEtChannelsResponse;
import com.baidubce.services.et.model.ListEtRequest;
import com.baidubce.services.et.model.ListEtResponse;
import com.baidubce.services.et.model.ResubmitEtChannelRequest;
import com.baidubce.services.et.model.UpdateEtChannelRequest;
import com.baidubce.services.et.model.UpdateEtChannelRouteRuleRequest;
import com.baidubce.services.et.model.UpdateEtRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

/**
 * Provides the client for accessing the Baidu Cloud network Service Express Tunnel(ET).
 */
public class EtClient extends AbstractBceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(EtClient.class);

    private static final String VERSION = "v1";
    private static final String ET_PREFIX = "et";
    private static final String ET_CHANNEL_PREFIX = "channel";
    private static final String ET_CHANNEL_ROUTE_PREFIX = "route/rule";

    /**
     * Responsible for handling httpResponses from all ET network service calls.
     */
    private static final HttpResponseHandler[] ET_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on ET.
     */
    public EtClient() {
        this(new EtClientConfiguration());
    }

    /**
     * Constructs a new ET client using the client configuration to access ET.
     *
     * @param clientConfiguration The network client configuration options controlling how this client
     *                            connects to network (e.g. proxy settings, retry counts, etc).
     */
    public EtClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, ET_HANDLERS);
    }

    /**
     * Creates and initializes a new request object for the specified ET resource. This method is responsible
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

        path.add(VERSION);

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        request.setCredentials(bceRequest.getRequestCredentials());
        return request;
    }

    /**
     * The method to fill the internalRequest's content field with bceRequest.
     * Only support HttpMethodName.POST or HttpMethodName.PUT
     *
     * @param internalRequest A request object, populated with endpoint, resource path, ready for callers to populate
     *                        any additional headers or parameters, and execute.
     * @param bceRequest      The original request, as created by the user.
     */
    private void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() == HttpMethodName.POST
                || internalRequest.getHttpMethod() == HttpMethodName.PUT) {
            String strJson = JsonUtils.toJsonString(bceRequest);
            byte[] requestJson;
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
     * Apply for an ET with the specified options.
     *
     * @param request The request containing all options for creating an ET.
     * @return ET id newly created
     */
    public ApplyForEtResponse applyForEt(ApplyForEtRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getName(), "name should not be empty");
        checkStringNotEmpty(request.getIsp(), "isp should not be empty");
        checkStringNotEmpty(request.getIntfType(), "intfType should not be empty");
        checkStringNotEmpty(request.getApType(), "apType should not be empty");
        checkStringNotEmpty(request.getApAddr(), "apAddr should not be empty");
        checkStringNotEmpty(request.getUserIdc(), "userIdc should not be empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, ET_PREFIX + "/init");
        if (StringUtils.isNotBlank(request.getClientToken())) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ApplyForEtResponse.class);
    }

    /**
     * Modifying the special attribute to new value of the ET owned by the user.
     *
     * @param request The request containing all options for modifying own's ET.
     */
    public void updateEt(UpdateEtRequest request) {
        checkNotNull(request, "request should not be null.");

        checkStringNotEmpty(request.getEtId(), "request etId should not be empty.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ET_PREFIX, request.getEtId());
        if (StringUtils.isNotBlank(request.getClientToken())) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Return a list of ETs owned by the authenticated user.
     *
     * @return The response containing a list of Ets owned by the authenticated user.
     */
    public ListEtResponse listEts(ListEtRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, ET_PREFIX);
        if (StringUtils.isNotBlank(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (StringUtils.isNotBlank(request.getStatus())) {
            internalRequest.addParameter("status", request.getStatus());
        }

        return invokeHttpClient(internalRequest, ListEtResponse.class);
    }

    /**
     * Get single ET detail owned by the authenticated user.
     *
     * @param etId ET(ID)
     * @return ET detail for the etId
     */
    public Et getEtDetail(String etId) {
        checkStringNotEmpty(etId, "etId should not be empty");
        InternalRequest internalRequest = this.createRequest(new AbstractBceRequest() {
            @Override
            public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
                this.setRequestCredentials(credentials);
                return this;
            }
        }, HttpMethodName.GET, ET_PREFIX, etId);
        return this.invokeHttpClient(internalRequest, Et.class);
    }

    /**
     * Create an ET channel with the specified options. Only when the ET status is “Available”, this ET channel
     * can be created.
     *
     * @param request The request containing all options for creating an ET channel.
     * @return ET channel id newly created
     */
    public CreateEtChannelResponse createEtChannel(CreateEtChannelRequest request) {
        checkNotNull(request, "request should not be null.");

        checkStringNotEmpty(request.getEtId(), "etId should not be empty");
        checkStringNotEmpty(request.getBaiduAddress(), "baiduAddress should not be empty");
        checkStringNotEmpty(request.getName(), "name should not be empty");
        checkStringNotEmpty(request.getCustomerAddress(), "customerAddress should not be empty");
        checkStringNotEmpty(request.getRouteType(), "routeType should not be empty");
        checkNotNull(request.getVlanId(), "vlanId should not be empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, ET_PREFIX,
                request.getEtId(), ET_CHANNEL_PREFIX);
        if (StringUtils.isNotBlank(request.getClientToken())) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateEtChannelResponse.class);
    }

    /**
     * Return a list of ET channels.
     *
     * @param etId the specified ET id
     * @return The response containing a list of Et channels owned by the specified ET.
     */
    public ListEtChannelsResponse listEtChannels(String etId) {
        checkStringNotEmpty(etId, "etId should not be empty");
        InternalRequest internalRequest = this.createRequest(new AbstractBceRequest() {
            @Override
            public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
                this.setRequestCredentials(credentials);
                return this;
            }
        }, HttpMethodName.GET, ET_PREFIX, etId, ET_CHANNEL_PREFIX);
        return this.invokeHttpClient(internalRequest, ListEtChannelsResponse.class);
    }

    /**
     * Resubmit an ET channel with the specified options. Only when the ET status “Applying” or “Application
     * rejected”, this interface is available.
     *
     * @param request The request containing all options for resubmitting an ET channel.
     */
    public void resubmitEtChannel(ResubmitEtChannelRequest request) {
        checkNotNull(request, "request should not be null.");

        checkStringNotEmpty(request.getEtId(), "etId should not be empty");
        checkStringNotEmpty(request.getEtChannelId(), "etChannelId should not be empty");
        checkStringNotEmpty(request.getBaiduAddress(), "baiduAddress should not be empty");
        checkStringNotEmpty(request.getName(), "name should not be empty");
        checkStringNotEmpty(request.getCustomerAddress(), "customerAddress should not be empty");
        checkStringNotEmpty(request.getRouteType(), "routeType should not be empty");
        checkNotNull(request.getVlanId(), "vlanId should not be empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ET_PREFIX,
                request.getEtId(), ET_CHANNEL_PREFIX, request.getEtChannelId());
        internalRequest.addParameter("reCreate", null);
        if (StringUtils.isNotBlank(request.getClientToken())) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Update an ET channel with the specified options.When the tunnel status is neither “Applying” nor “Application
     * rejected”, this interface is available.
     *
     * @param request The request containing all options for updating an ET channel.
     */
    public void updateEtChannel(UpdateEtChannelRequest request) {
        checkNotNull(request, "request should not be null.");

        checkStringNotEmpty(request.getEtId(), "etId should not be empty");
        checkStringNotEmpty(request.getEtChannelId(), "etChannelId should not be empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ET_PREFIX,
                request.getEtId(), ET_CHANNEL_PREFIX, request.getEtChannelId());
        internalRequest.addParameter("modifyAttribute", null);
        if (StringUtils.isNotBlank(request.getClientToken())) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete the ET channel. When the channel status is “Available”, “Pending payment”, or
     * “Application rejected”, this interface is available.
     *
     * @param request – The request containing all options for deleting an ET channel.
     */
    public void deleteEtChannel(EtChannelIdRequest request) {
        checkNotNull(request, "request should not be null.");

        checkStringNotEmpty(request.getEtId(), "etId should not be empty");
        checkStringNotEmpty(request.getEtChannelId(), "etChannelId should not be empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, ET_PREFIX,
                request.getEtId(), ET_CHANNEL_PREFIX, request.getEtChannelId());

        if (StringUtils.isNotBlank(request.getClientToken())) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Turn on ET channel ipv6 function, which is a white list function.
     *
     * @param request - The request containing all options for turn on ET channel ipv6 function.
     */
    public void enableEtChannelIpv6(EnableEtChannelIpv6Request request) {
        checkNotNull(request, "request should not be null.");

        checkStringNotEmpty(request.getEtId(), "etId should not be empty");
        checkStringNotEmpty(request.getEtChannelId(), "etChannelId should not be empty");
        checkStringNotEmpty(request.getBaiduIpv6Address(), "baiduIpv6Address should not be empty");
        checkStringNotEmpty(request.getCustomerIpv6Address(), "customerIpv6Address should not be empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ET_PREFIX,
                request.getEtId(), ET_CHANNEL_PREFIX, request.getEtChannelId());
        internalRequest.addParameter("enableIpv6", null);
        if (StringUtils.isNotBlank(request.getClientToken())) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Turn off ET channel ipv6 function.
     *
     * @param request - The request containing all options for turn off ET channel ipv6 function.
     */
    public void disableEtChannelIpv6(EtChannelIdRequest request) {
        checkNotNull(request, "request should not be null.");

        checkStringNotEmpty(request.getEtId(), "etId should not be empty");
        checkStringNotEmpty(request.getEtChannelId(), "etChannelId should not be empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ET_PREFIX,
                request.getEtId(), ET_CHANNEL_PREFIX, request.getEtChannelId());
        internalRequest.addParameter("disableIpv6", null);
        if (StringUtils.isNotBlank(request.getClientToken())) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Create an ET channel route rule with the specified options.
     *
     * @param request The request containing all options for creating an ET channel route rule.
     * @return ET channel route rule id newly created.
     */
    public CreateEtChannelRouteResponse createEtChannelRouteRule(CreateEtChannelRouteRuleRequest request) {
        checkNotNull(request, "request should not be null.");

        checkStringNotEmpty(request.getEtId(), "etId should not be empty");
        checkStringNotEmpty(request.getEtChannelId(), "etChannelId should not be empty");
        checkStringNotEmpty(request.getDestAddress(), "destAddress should not be empty");
        checkStringNotEmpty(request.getNexthopType(), "nexthopType should not be empty");
        checkStringNotEmpty(request.getNexthopId(), "nexthopId should not be empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, ET_PREFIX,
                request.getEtId(), ET_CHANNEL_PREFIX, request.getEtChannelId(), ET_CHANNEL_ROUTE_PREFIX);
        if (StringUtils.isNotBlank(request.getClientToken())) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateEtChannelRouteResponse.class);
    }

    /**
     * Return a list of ET route rules.
     *
     * @return The response containing a list of ET route rules.
     */
    public ListEtChannelRouteRulesResponse listEtChannelRouteRules(ListEtChannelRouteRulesRequest request) {
        checkNotNull(request, "request should not be null.");

        checkStringNotEmpty(request.getEtId(), "etId should not be empty");
        checkStringNotEmpty(request.getEtChannelId(), "etChannelId should not be empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, ET_PREFIX,
                request.getEtId(), ET_CHANNEL_PREFIX, request.getEtChannelId(), ET_CHANNEL_ROUTE_PREFIX);
        if (StringUtils.isNotBlank(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (StringUtils.isNotBlank(request.getDestAddress())) {
            internalRequest.addParameter("destAddress", request.getDestAddress());
        }

        return invokeHttpClient(internalRequest, ListEtChannelRouteRulesResponse.class);
    }

    /**
     * Update an ET channel route rule's description.
     *
     * @param request The request containing all options for updating an ET channel route rule.
     */
    public void updateEtChannelRouteRule(UpdateEtChannelRouteRuleRequest request) {
        checkNotNull(request, "request should not be null.");

        checkStringNotEmpty(request.getEtId(), "etId should not be empty");
        checkStringNotEmpty(request.getEtChannelId(), "etChannelId should not be empty");
        checkStringNotEmpty(request.getRouteRuleId(), "routeRuleId should not be empty");
        checkNotNull(request.getDescription(), "description should not be null");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ET_PREFIX, request.getEtId(),
                ET_CHANNEL_PREFIX, request.getEtChannelId(), ET_CHANNEL_ROUTE_PREFIX, request.getRouteRuleId());
        if (StringUtils.isNotBlank(request.getClientToken())) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete the ET channel route rule.
     *
     * @param request – The request containing all options for deleting an ET channel route rule.
     */
    public void deleteEtRouteRule(EtChannelRouteRuleIdRequest request) {
        checkNotNull(request, "request should not be null.");

        checkStringNotEmpty(request.getEtId(), "etId should not be empty");
        checkStringNotEmpty(request.getEtChannelId(), "etChannelId should not be empty");
        checkStringNotEmpty(request.getRouteRuleId(), "routeRuleId should not be empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, ET_PREFIX,
                request.getEtId(), ET_CHANNEL_PREFIX, request.getEtChannelId(), ET_CHANNEL_ROUTE_PREFIX,
                request.getRouteRuleId());

        if (StringUtils.isNotBlank(request.getClientToken())) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }
}
