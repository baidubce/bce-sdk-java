/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.ipv6Gateway;

import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.baidubce.AbstractBceClient;
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
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.ipv6Gateway.model.Billing;
import com.baidubce.services.ipv6Gateway.model.CreateEgressOnlyRuleRequest;
import com.baidubce.services.ipv6Gateway.model.CreateEgressOnlyRuleResponse;
import com.baidubce.services.ipv6Gateway.model.CreateIpv6GatewayRequest;
import com.baidubce.services.ipv6Gateway.model.CreateIpv6GatewayResponse;
import com.baidubce.services.ipv6Gateway.model.CreateRateLimitRuleRequest;
import com.baidubce.services.ipv6Gateway.model.DeleteIpv6EgressOnlyRuleRequest;
import com.baidubce.services.ipv6Gateway.model.DeleteIpv6GatewayRequest;
import com.baidubce.services.ipv6Gateway.model.DeleteIpv6RateLimitRuleRequest;
import com.baidubce.services.ipv6Gateway.model.Ipv6GatewayResponse;
import com.baidubce.services.ipv6Gateway.model.ListEgressOnlyRuleRequest;
import com.baidubce.services.ipv6Gateway.model.ListEgressOnlyRuleResponse;
import com.baidubce.services.ipv6Gateway.model.ListIpv6GatewayRequest;
import com.baidubce.services.ipv6Gateway.model.ListRateLimitRuleResponse;
import com.baidubce.services.ipv6Gateway.model.RateLimitRuleResponse;
import com.baidubce.services.ipv6Gateway.model.ResizeIpv6GatewayRequest;
import com.baidubce.services.ipv6Gateway.model.UpdateRateLimitRuleRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Strings;

/**
 * Provides the client for accessing the Ipv6Gateway Service (Ipv6Gateway).
 */
public class Ipv6GatewayClient extends AbstractBceClient {
    /**
     * Ipv6Gateway API pathVersion
     */
    private static final String VERSION = "v1";

    private static final String PREFIX = "IPv6Gateway";

    private static final String EGRESS_ONLY_RULE_PREFIX = "egressOnlyRule";

    private static final String RATE_LIMIT_RULE_PREFIX = "rateLimitRule";

    private static final String CLIENT_TOKEN_IDENTIFY = "clientToken";

    /**
     * Responsible for handling httpResponses from all service calls.
     */
    private static HttpResponseHandler[] ipv6GatewayHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public Ipv6GatewayClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new InstanceClient to invoke service methods on Ipv6Gateway instance.
     *
     * @param clientConfiguration The BCE client configuration options.
     */
    public Ipv6GatewayClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, ipv6GatewayHandlers);
    }

    /**
     * Create an Ipv6Gateway with the specified options.
     *
     * @param vpcId           specify the vpc
     * @param name            specify the name for ipv6Gateway
     * @param bandwidthInMbps specify the bandwidth in Mbps
     *
     * @return
     */
    public CreateIpv6GatewayResponse createIpv6Gateway(String vpcId, String name, int bandwidthInMbps) {
        Billing billing = new Billing();
        billing.setPaymentTiming("Postpaid");
        return createIpv6Gateway(new CreateIpv6GatewayRequest().withVpcId(vpcId).withName(name).withBandwith
                (bandwidthInMbps).withBilling(billing));
    }

    /**
     * Create an Ipv6gateway with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for creating an Ipv6Gateway.
     *
     * @return created ipv6Gateway gatewayId
     */
    public CreateIpv6GatewayResponse createIpv6Gateway(CreateIpv6GatewayRequest request) {
        checkNotNull(request.getName(), "name should not be null");
        checkNotNull(request.getVpcId(), "vpcId should not be null");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        if (null == request.getBilling()) {
            request.setBilling(generateDefaultBilling());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, null);

        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        try {
            System.out.println(JsonUtils.toJsonPrettyString(internalRequest));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return this.invokeHttpClient(internalRequest, CreateIpv6GatewayResponse.class);
    }

    /**
     * Resizing Ipv6Gateway
     *
     * @param ipv6GatewayId      ipv6Gateway to be resized
     * @param newBandwidthInMbps specify new bandwidth in Mbps for ipv6Gateway
     */
    public void resizeIpv6Gateway(String ipv6GatewayId, int newBandwidthInMbps) {
        this.resizeIpv6Gateway(
                new ResizeIpv6GatewayRequest().withIpv6Gateway(ipv6GatewayId).withBandwidth(newBandwidthInMbps));
    }

    /**
     * Resizing Ipv6Gateway
     *
     * @param request IPv6GatewayId & BandwidthInMbps must be provided
     */
    public void resizeIpv6Gateway(ResizeIpv6GatewayRequest request) {
        checkStringNotEmpty(request.getIpv6GatewayId(), "ipv6GatewayId should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, request.getIpv6GatewayId());
        internalRequest.addParameter("resize", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * delete the Ipv6Gateway
     *
     * @param gatewayId the ipv6Gateway to be deleted
     */
    public void deleteIpv6Gateway(String gatewayId) {
        this.deleteIpv6Gateway(new DeleteIpv6GatewayRequest().withGatewayId(gatewayId));
    }

    /**
     * delete the Ipv6Gateway
     *
     * @param request The request containing all options for delete Ipv6Gateway
     */
    public void deleteIpv6Gateway(DeleteIpv6GatewayRequest request) {
        checkStringNotEmpty(request.getGatewayId(), "gatewayId should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, request.getGatewayId());
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * get a list of Ipv6Gateway owned by the authenticated user and default conditions
     *
     * @return
     */
    public Ipv6GatewayResponse getIpv6Gateway(String vpcId) {
        ListIpv6GatewayRequest listIpv6GatewayRequest = new ListIpv6GatewayRequest();
        listIpv6GatewayRequest.withRequestVpcId(vpcId);
        return getIpv6Gateway(listIpv6GatewayRequest);
    }

    /**
     * get a single Ipv6Gateway owned by the authenticated user and specified conditions
     *
     * @return
     */
    public Ipv6GatewayResponse getIpv6Gateway(ListIpv6GatewayRequest request) {
        checkStringNotEmpty(request.getVpcId(), "vpcId should not be empty");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, null);
        internalRequest.addParameter("vpcId", request.getVpcId());
        return this.invokeHttpClient(internalRequest, Ipv6GatewayResponse.class);
    }

    /**
     * Add an EgressOnluRule to the Ipv6Gateway with the specified options.
     *
     * @param gatewayId specify the ipv6GatewayId to add egressOnlyRule
     * @param cidr      specify the cidr for ipv6Gateway egressOnlyRule
     *
     * @return the id of EgressOnlyRule
     */
    public CreateEgressOnlyRuleResponse craeteEgressOnlyRule(String gatewayId, String cidr) {
        CreateEgressOnlyRuleRequest createEgressOnlyRuleRequest = new CreateEgressOnlyRuleRequest();
        createEgressOnlyRuleRequest.setGatewayId(gatewayId);
        createEgressOnlyRuleRequest.setCidr(cidr);

        return createEgressOnlyRule(createEgressOnlyRuleRequest);
    }

    /**
     * Add an EgressOnluRule to the Ipv6Gateway with the specified options.
     *
     * @param request the request to create Ipv6Gateway egressOnlyRule
     *
     * @return the id of EgressOnlyRule
     */
    public CreateEgressOnlyRuleResponse createEgressOnlyRule(CreateEgressOnlyRuleRequest request) {
        checkStringNotEmpty(request.getGatewayId(), "gatewayId should not be empty");
        checkStringNotEmpty(request.getCidr(), "cidr should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.POST, request.getGatewayId(), EGRESS_ONLY_RULE_PREFIX);
        fillPayload(internalRequest, request);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        return invokeHttpClient(internalRequest, CreateEgressOnlyRuleResponse.class);
    }

    /**
     * Return a list of Ipv6Gateway's egressOnlyRule owned by the authenticated user.
     *
     * @return The response containing a list of egressOnlyRule owned by the authenticated user.
     */
    public ListEgressOnlyRuleResponse listEgressOnlyRule(String gatewayId) {
        return this.listEgressOnlyRule(new ListEgressOnlyRuleRequest().withRequestGatewayId(gatewayId));
    }

    /**
     * Return a list of Ipv6Gateway's egressOnlyRule owned by the authenticated user.
     *
     * @param request The request containing all options for listing own's egressOnlyRule.
     *
     * @return The response containing a list of the Ipv6Gateway's  egressOnlyRule owned by the authenticated user.
     */
    public ListEgressOnlyRuleResponse listEgressOnlyRule(ListEgressOnlyRuleRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getGatewayId(), "the gatewyId should not be null");
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, request.getGatewayId(), EGRESS_ONLY_RULE_PREFIX);
        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }

        return invokeHttpClient(internalRequest, ListEgressOnlyRuleResponse.class);
    }

    /**
     * delete the Ipv6Gateway's egressOnlyRule
     *
     * @param gatewayId the ipv6Gateway's egressOnlyRuleId to be deleted
     */
    public void deleteIpv6GatewayEgressOnlyRule(String gatewayId, String egressOnlyRuleId) {
        deleteIpv6GatewayEgressOnlyRule(new DeleteIpv6EgressOnlyRuleRequest().withRequestGatewayId(gatewayId)
                .wirhRequestEgressOnlyRuleId(egressOnlyRuleId));
    }

    public void deleteIpv6GatewayEgressOnlyRule(DeleteIpv6EgressOnlyRuleRequest deleteIpv6EgressOnlyRuleRequest) {
        checkNotNull(deleteIpv6EgressOnlyRuleRequest, "the deleteIpv6EgressOnlyRuleRequest should not be null");
        checkStringNotEmpty(deleteIpv6EgressOnlyRuleRequest.getEgressOnlyRuleId(), "egressOnlyRuleId should not be "
                + "empty");
        checkStringNotEmpty(deleteIpv6EgressOnlyRuleRequest.getGatewayId(), "ipv6GatewayId should not be empty");
        if (Strings.isNullOrEmpty(deleteIpv6EgressOnlyRuleRequest.getClientToken())) {
            deleteIpv6EgressOnlyRuleRequest.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(deleteIpv6EgressOnlyRuleRequest, HttpMethodName.DELETE,
                        deleteIpv6EgressOnlyRuleRequest.getGatewayId(), EGRESS_ONLY_RULE_PREFIX,
                        deleteIpv6EgressOnlyRuleRequest.getEgressOnlyRuleId());
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, deleteIpv6EgressOnlyRuleRequest.getClientToken());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * create the rateLimitRule for the Ipv6Gateway's
     */
    public RateLimitRuleResponse createRateLimitRule(CreateRateLimitRuleRequest request) {
        checkNotNull(request, "the request should not be null");
        checkStringNotEmpty(request.getGatewayId(), "the gatewayId should not be null");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST,
                        request.getGatewayId(), RATE_LIMIT_RULE_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, RateLimitRuleResponse.class);
    }

    /**
     * update the rateLimitRule for the Ipv6Gateway's
     */
    public void updateRateLimitRule(UpdateRateLimitRuleRequest request) {
        checkNotNull(request, "the request should not be null");
        checkStringNotEmpty(request.getGatewayId(), "the gatewayId should not be null");
        checkStringNotEmpty(request.getRateLimitRuleId(), "the rateLimitRuleId should not be null");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT,
                        request.getGatewayId(), RATE_LIMIT_RULE_PREFIX, request.getRateLimitRuleId());
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * delete the Ipv6Gateway's rateLimitRule
     *
     * @param gatewayId the ipv6Gateway's rateLimitRule to be deleted
     */
    public void deleteIpv6GatewayRateLimitRule(String gatewayId, String rateLimitRuleId) {
        deleteIpv6GatewayEgressOnlyRule(new DeleteIpv6RateLimitRuleRequest().withRequestGatewayId(gatewayId)
                .withRequestRateLimitRuleId(rateLimitRuleId));
    }

    public void deleteIpv6GatewayEgressOnlyRule(DeleteIpv6RateLimitRuleRequest request) {
        checkNotNull(request, "the deleteIpv6EgressOnlyRuleRequest should not be null");
        checkStringNotEmpty(request.getRateLimitRuleId(), "rateLimitRuleId should not be empty");
        checkStringNotEmpty(request.getGatewayId(), "ipv6GatewayId should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE,
                        request.getGatewayId(), RATE_LIMIT_RULE_PREFIX,
                        request.getRateLimitRuleId());
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * list ipv6Gateway's rateLimitRule
     *
     * @param gatewayId the Ipv6Gateway's id
     */
    public ListRateLimitRuleResponse listRateLimitRule(String gatewayId) {
        return listRateLimitRule(new ListEgressOnlyRuleRequest().withRequestGatewayId(gatewayId));
    }

    public ListRateLimitRuleResponse listRateLimitRule(ListEgressOnlyRuleRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getGatewayId(), "the gatewyId should not be null");
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, request.getGatewayId(), RATE_LIMIT_RULE_PREFIX);
        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }

        return invokeHttpClient(internalRequest, ListRateLimitRuleResponse.class);

    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest    The original BCE request created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     *
     * @return A new request object populated with endpoint, resource path and specific
     * parameters to send.
     */
    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION);
        path.add(PREFIX);

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
     * the method to fill the internalRequest's content field with bceRequest
     * only support HttpMethodName.POST or HttpMethodName.PUT
     *
     * @param internalRequest A request object, populated with endpoint, resource path, ready for callers to populate
     *                        any additional headers or parameters, and execute.
     * @param bceRequest      The original request, as created by the user.
     */
    protected void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() == HttpMethodName.POST
                || internalRequest.getHttpMethod() == HttpMethodName.PUT) {
            String strJson = JsonUtils.toJsonString(bceRequest);
            byte[] requestJson = null;
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
     * The default method to generate the random String for clientToken if the optional parameter clientToken
     * is not specified by the user.
     * <p>
     * The default algorithm is using {@link UUID} to generate a random UUID,
     *
     * @return An random String generated by {@link UUID}.
     */
    private String generateDefaultClientToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * The method to generate a default Billing which is Postpaid.
     *
     * @return The Billing object with Postpaid PaymentTiming.
     */
    private Billing generateDefaultBilling() {
        Billing billing = new Billing();
        billing.setPaymentTiming("Postpaid");
        return billing;
    }

}
