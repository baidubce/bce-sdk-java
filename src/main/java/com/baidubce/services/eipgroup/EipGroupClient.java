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
package com.baidubce.services.eipgroup;

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.baidubce.services.eipgroup.model.EipGroupOperateRequest;
import com.baidubce.services.eipgroup.model.MoveInRequest;
import com.baidubce.services.eipgroup.model.MoveOutRequest;
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
import com.baidubce.services.eipgroup.model.BandwidthInMbpsRequest;
import com.baidubce.services.eipgroup.model.CreateEipGroupRequest;
import com.baidubce.services.eipgroup.model.EipCountRequest;
import com.baidubce.services.eipgroup.model.EipNameRequest;
import com.baidubce.services.eipgroup.model.GetEipGroupRequest;
import com.baidubce.services.eipgroup.model.GetEipGroupResponse;
import com.baidubce.services.eipgroup.model.IdResponse;
import com.baidubce.services.eipgroup.model.ListEipGroupRequest;
import com.baidubce.services.eipgroup.model.ListEipGroupResponse;
import com.baidubce.services.eipgroup.model.PurchaseReservedEipGroupRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

/**
 * Provides the client for accessing the Baidu Cloud network Service nat part.
 */
public class EipGroupClient extends AbstractBceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EipGroupClient.class);

    private static final String VERSION = "v1";
    private static final String PREFIX = "eipgroup";

    /**
     * Responsible for handling httpResponses from all network service calls.
     */
    private static final HttpResponseHandler[] HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on network.
     */
    public EipGroupClient() {
        this(new EipGroupClientConfiguration());
    }

    /**
     * Constructs a new network client using the client configuration to access network.
     *
     * @param clientConfiguration The network client configuration options controlling how this client
     *                            connects to network (e.g. proxy settings, retry counts, etc).
     */
    public EipGroupClient(EipGroupClientConfiguration clientConfiguration) {
        super(clientConfiguration, HANDLERS);
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
     * Create a eip group with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     * <p/>
     *
     * @param request The request containing all options for creating a eip group.
     * @return eip group's id newly created
     * @throws BceClientException
     */
    public IdResponse createEipGroup(CreateEipGroupRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        checkNotNull(request.getBilling(), "billing should not be empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, PREFIX);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, IdResponse.class);
    }

    /**
     * Resize the bandwidth.
     *
     * @param request The request containing all options for binding the eips to specified eip group.
     */
    public void resizeBandwidth(BandwidthInMbpsRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, PREFIX, request.getId());
        internalRequest.addParameter("resize", null);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Resize the name.
     *
     * @param request The request containing all options for binding the eips to specified eip group.
     */
    public void update(EipNameRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, PREFIX, request.getId());
        internalRequest.addParameter("update", null);
        fillPayload(internalRequest, request);
        internalRequest.addParameter("clientToken", request.getClientToken());
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Resize the count.
     *
     * @param request The request containing all options for binding the eips to specified eip group.
     */
    public void addCount(EipCountRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, PREFIX, request.getId());
        internalRequest.addParameter("resize", null);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Return a list of eip groups owned by the authenticated user.
     *
     * @param request The request containing all options for listing own's eip groups.
     * @return The response containing a list of eip groups owned by the authenticated user.
     */
    public ListEipGroupResponse listEipGroup(ListEipGroupRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, PREFIX);
        if (StringUtils.isNotBlank(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (StringUtils.isNotBlank(request.getId())) {
            internalRequest.addParameter("id", request.getId());
        }
        if (StringUtils.isNotBlank(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        if (StringUtils.isNotBlank(request.getStatus())) {
            internalRequest.addParameter("status", request.getStatus());
        }

        return invokeHttpClient(internalRequest, ListEipGroupResponse.class);
    }

    /**
     * Get the detail information of specified eip group.
     *
     * @param request The request of the eip group.
     * @return A eip group detail model for the request.
     */
    public GetEipGroupResponse getEipGroup(GetEipGroupRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getId(), "id should not be empty.");
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, PREFIX, request.getId());
        return this.invokeHttpClient(internalRequest, GetEipGroupResponse.class);
    }

    /**
     * PurchaseReserving specified eip group.
     *
     * @param request The request containing all options for purchaseReserving the eips to specified eip group.
     */
    public void purchaseReservedEipGroup(PurchaseReservedEipGroupRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getId(), "id should not be empty.");
        checkNotNull(request.getBilling(), "billing should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, PREFIX, request.getId());
        internalRequest.addParameter("purchaseReserved", null);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * release eip group.
     *
     * @param request The request containing all options for release the specified eip group.
     */
    public void releaseEipGroup(EipGroupOperateRequest request) {
        checkStringNotEmpty(request.getId(), "id should not be empty.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.DELETE, PREFIX, request.getId());
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }


    /**
     * move eip form group.
     *
     * @param request The request containing all options for move the specified eips from group.
     */
    public void moveOutEips(MoveOutRequest request) {
        checkStringNotEmpty(request.getId(), "id should not be empty.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, PREFIX, request.getId());
        internalRequest.addParameter("move_out", null);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * move eip into eipgroup.
     *
     * @param request The request containing all options for move the specified eips into group.
     */
    public void moveInEips(MoveInRequest request) {
        checkStringNotEmpty(request.getId(), "id should not be empty.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, PREFIX, request.getId());
        internalRequest.addParameter("move_in", null);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

}
