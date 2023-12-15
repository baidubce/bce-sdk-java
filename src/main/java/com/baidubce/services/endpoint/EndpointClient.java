/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.endpoint;

import static com.baidubce.util.Validate.checkMultyParamsNotBothEmpty;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.baidubce.services.endpoint.model.CreateEndpointRequest;
import com.baidubce.services.endpoint.model.CreateEndpointResponse;
import com.baidubce.services.endpoint.model.Endpoint;
import com.baidubce.services.endpoint.model.GetEndpointRequest;
import com.baidubce.services.endpoint.model.ListEndpointRequest;
import com.baidubce.services.endpoint.model.ListEndpointResponse;
import com.baidubce.services.endpoint.model.ModifyEndpointRequest;
import com.baidubce.services.endpoint.model.ReleaseEndpointRequest;
import com.baidubce.services.endpoint.model.ServiceRequest;
import com.baidubce.services.endpoint.model.ServiceResponse;
import com.baidubce.services.endpoint.model.UpdateEnterpriseSecurityGroups;
import com.baidubce.services.endpoint.model.UpdateSecurityGroups;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

/**
 * Created by XingChunyang
 * Date: 2021/01/21.
 */

/**
 * Provides the client for accessing the Baidu Cloud network Service ENDPOINT.
 */
public class EndpointClient extends AbstractBceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(EndpointClient.class);

    private static final String VERSION = "v1";
    private static final String ENDPOINT_PREFIX = "endpoint";

    /**
     * Responsible for handling httpResponses from all network service calls.
     */
    private static final HttpResponseHandler[] endpointHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public EndpointClient() {
        this(new EndpointClientConfiguration());
    }

    /**
     * Constructs a new InstanceClient to invoke service methods on endpoint instance.
     *
     * @param clientConfiguration The BCE client configuration options.
     */
    public EndpointClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, endpointHandlers);
    }

    /**
     * Creates and initializes a new request object for the specified network resource. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest    The original request, as created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     *
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
     * @return Return a list of service owned by the authenticated user.
     */
    public ServiceResponse listService() {
        InternalRequest internalRequest =
                this.createRequest(new ServiceRequest(), HttpMethodName.GET, ENDPOINT_PREFIX, "publicService");
        return this.invokeHttpClient(internalRequest, ServiceResponse.class);
    }


    /**
     * Create an endpoint with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     *
     * @param request he request containing all options for creating a endpoint.
     *
     * @return
     */
    public CreateEndpointResponse createEndpoint(CreateEndpointRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        checkNotNull(request.getBilling(), "billing should not be empty");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, ENDPOINT_PREFIX);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateEndpointResponse.class);
    }

    /**
     * Return a list of endpoints owned by the authenticated user.
     *
     * @param request The request containing all options for listing own's endpoint.
     *
     * @return The response containing a list of endpoints owned by the authenticated user.
     */
    public ListEndpointResponse listEndpoint(ListEndpointRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getVpcId(), "vpcId should not be empty");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, ENDPOINT_PREFIX);
        if (StringUtils.isNotBlank(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        internalRequest.addParameter("vpcId", request.getVpcId());
        if (StringUtils.isNotBlank(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        if (StringUtils.isNotBlank(request.getIpAddress())) {
            internalRequest.addParameter("ipAddress", request.getIpAddress());
        }
        if (StringUtils.isNotBlank(request.getStatus())) {
            internalRequest.addParameter("status", request.getStatus());
        }
        if (StringUtils.isNotBlank(request.getSubnetId())) {
            internalRequest.addParameter("subnetId", request.getSubnetId());
        }
        if (StringUtils.isNotBlank(request.getService())) {
            internalRequest.addParameter("service", request.getService());
        }
        return invokeHttpClient(internalRequest, ListEndpointResponse.class);
    }

    /**
     * Get the detail information of specified endpoint.
     *
     * @param endpointId The id of the endpoint.
     *
     * @return A Endpoint detail model for the endpointId.
     */
    public Endpoint getEndpoint(String endpointId) {
        checkStringNotEmpty(endpointId, "endpointId should not be empty.");
        GetEndpointRequest request = new GetEndpointRequest().withEndpointId(endpointId);
        return getEndpoint(request);
    }

    /**
     * Get the detail information of specified Endpoint.
     *
     * @param request The request of the network.
     *
     * @return A Endpoint detail model for the request.
     */
    public Endpoint getEndpoint(GetEndpointRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getEndpointId(), "endpointId should not be empty.");
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, ENDPOINT_PREFIX, request.getEndpointId());
        return this.invokeHttpClient(internalRequest, Endpoint.class);
    }

    /**
     * Modifying the name of the specified endpoint.
     *
     * @param request The request containing all options for modifying the endpoint name/description;
     */
    public void modifyEndpoint(ModifyEndpointRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getEndpointId(), "endpointId should not be null.");
        checkMultyParamsNotBothEmpty(Arrays.asList(request.getName(), request.getDescription()),
                "name and description should not be all null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, ENDPOINT_PREFIX, request.getEndpointId());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Releasing specified endpoint.
     *
     * @param request The request containing all options for releasing the specified endpoint.
     */
    public void releaseEndpoint(ReleaseEndpointRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getEndpointId(), "endpointId should not be empty.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.DELETE, ENDPOINT_PREFIX, request.getEndpointId());
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * update endpoint's securitygroups
     *
     * @param updateSecurityGroups
     */
    public void updateSecurityGroups(UpdateSecurityGroups updateSecurityGroups) {
        checkNotNull(updateSecurityGroups, "request should not be null.");
        checkStringNotEmpty(updateSecurityGroups.getEndpointId(), "endpointId should not be empty.");
        if (Strings.isNullOrEmpty(updateSecurityGroups.getClientToken())) {
            updateSecurityGroups.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                updateSecurityGroups, HttpMethodName.PUT, ENDPOINT_PREFIX, updateSecurityGroups.getEndpointId());
        internalRequest.addParameter("bindSg", null);
        fillPayload(internalRequest, updateSecurityGroups);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * update endpoint's enterpriseSecurityGroups
     *
     * @param updateSecurityGroups
     */
    public void updateEnterpriseSecurityGroups(UpdateEnterpriseSecurityGroups updateSecurityGroups) {
        checkNotNull(updateSecurityGroups, "request should not be null.");
        checkStringNotEmpty(updateSecurityGroups.getEndpointId(), "endpointId should not be empty.");
        if (Strings.isNullOrEmpty(updateSecurityGroups.getClientToken())) {
            updateSecurityGroups.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                updateSecurityGroups, HttpMethodName.PUT, ENDPOINT_PREFIX, updateSecurityGroups.getEndpointId());
        internalRequest.addParameter("bindEsg", null);
        fillPayload(internalRequest, updateSecurityGroups);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }
}
