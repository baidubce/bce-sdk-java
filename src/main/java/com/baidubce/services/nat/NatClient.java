/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.nat;

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.AbstractBceClient;
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
import com.baidubce.services.nat.model.BindEipRequest;
import com.baidubce.services.nat.model.CreateNatRequest;
import com.baidubce.services.nat.model.CreateNatResponse;
import com.baidubce.services.nat.model.GetNatRequest;
import com.baidubce.services.nat.model.GetNatResponse;
import com.baidubce.services.nat.model.ListNatRequest;
import com.baidubce.services.nat.model.ListNatResponse;
import com.baidubce.services.nat.model.ModifyNatRequest;
import com.baidubce.services.nat.model.PurchaseReservedNatRequest;
import com.baidubce.services.nat.model.ReleaseNatRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

/**
 * Provides the client for accessing the Baidu Cloud network Service nat part.
 */
public class NatClient extends AbstractBceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(NatClient.class);

    private static final String VERSION = "v1";
    private static final String NAT_PREFIX = "nat";

    /**
     * Responsible for handling httpResponses from all network service calls.
     */
    private static final HttpResponseHandler[] natHandlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on network.
     */
    public NatClient() {
        this(new NatClientConfiguration());
    }

    /**
     * Constructs a new network client using the client configuration to access network.
     *
     * @param clientConfiguration The network client configuration options controlling how this client
     *                            connects to network (e.g. proxy settings, retry counts, etc).
     */
    public NatClient(NatClientConfiguration clientConfiguration) {
        super(clientConfiguration, natHandlers);
    }

    /**
     * Creates and initializes a new request object for the specified network resource. This method is responsible
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
     * any additional headers or parameters, and execute.
     * @param bceRequest The original request, as created by the user.
     */
    private void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
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
     * <p/>
     * The default algorithm is using {@link UUID} to generate a random UUID,
     *
     * @return An random String generated by {@link UUID}.
     */
    private String generateClientToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * Create a nat with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     * <p/>
     *
     * @param request The request containing all options for creating a nat.
     * @return nat id newly created
     * @throws BceClientException
     */
    public CreateNatResponse createNat(CreateNatRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkNotNull(request.getBilling(), "billing should not be empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, NAT_PREFIX);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateNatResponse.class);
    }

    /**
     * Return a list of nats owned by the authenticated user.
     *
     * @param request The request containing all options for listing own's nat.
     * @return The response containing a list of nats owned by the authenticated user.
     */
    public ListNatResponse listNat(ListNatRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getVpcId(), "vpcId should not be empty");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, NAT_PREFIX);
        if (StringUtils.isNotBlank(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        internalRequest.addParameter("vpcId", request.getVpcId());
        if (StringUtils.isNotBlank(request.getNatId())) {
            internalRequest.addParameter("natId", request.getNatId());
        }
        if (StringUtils.isNotBlank(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        if (StringUtils.isNotBlank(request.getIp())) {
            internalRequest.addParameter("ip", request.getIp());
        }

        return invokeHttpClient(internalRequest, ListNatResponse.class);
    }

    /**
     * Get the detail information of specified nat.
     *
     * @param natId The id of the network.
     * @return A nat detail model for the natId.
     */
    public GetNatResponse getNat(String natId) {
        checkStringNotEmpty(natId, "natId should not be empty.");
        GetNatRequest request = new GetNatRequest().withNatId(natId);
        return getNat(request);
    }

    /**
     * Get the detail information of specified nat.
     *
     * @param request The request of the network.
     * @return A nat detail model for the request.
     */
    public GetNatResponse getNat(GetNatRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getNatId(), "natId should not be empty.");
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, NAT_PREFIX, request.getNatId());
        return this.invokeHttpClient(internalRequest, GetNatResponse.class);
    }

    /**
     * Modifying the name of the specified nat.
     *
     * @param request The request containing all options for modifying the nat name;
     */
    public void modifyNat(ModifyNatRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getNatId(), "natId should not be null.");
        checkStringNotEmpty(request.getName(), "name should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, NAT_PREFIX, request.getNatId());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Binding the eips to specified nat.
     *
     * @param request The request containing all options for binding the eips to specified nat.
     */
    public void bindEip(BindEipRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getEips(), "eips should not be null.");
        checkStringNotEmpty(request.getNatId(), "natId should not be empty.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, NAT_PREFIX, request.getNatId());
        internalRequest.addParameter("bind", null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Unbinding the eips to specified nat.
     *
     * @param request The request containing all options for binding the eips to specified nat.
     */
    public void unbindEip(BindEipRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getEips(), "eips should not be null.");
        checkStringNotEmpty(request.getNatId(), "natId should not be empty.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, NAT_PREFIX, request.getNatId());
        internalRequest.addParameter("unbind", null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Releasing specified nat.
     *
     * @param request The request containing all options for releasing the eips to specified nat.
     */
    public void releaseNat(ReleaseNatRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getNatId(), "natId should not be empty.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.DELETE, NAT_PREFIX, request.getNatId());
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * PurchaseReserving specified nat.
     *
     * @param request The request containing all options for purchaseReserving the eips to specified nat.
     */
    public void purchaseReservedNat(PurchaseReservedNatRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getNatId(), "natId should not be empty.");
        checkNotNull(request.getBilling(), "billing should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, NAT_PREFIX, request.getNatId());
        internalRequest.addParameter("purchaseReserved", null);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }
}
