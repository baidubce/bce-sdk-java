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
package com.baidubce.services.vpc;

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.baidubce.services.vpc.model.CreateVpcRequest;
import com.baidubce.services.vpc.model.CreateVpcResponse;
import com.baidubce.services.vpc.model.DeleteVpcRequest;
import com.baidubce.services.vpc.model.GetVpcPrivateAddressInfoResponse;
import com.baidubce.services.vpc.model.GetVpcPrivateIpAddressInfoRequest;
import com.baidubce.services.vpc.model.GetVpcRequest;
import com.baidubce.services.vpc.model.GetVpcResponse;
import com.baidubce.services.vpc.model.ListVpcRequest;
import com.baidubce.services.vpc.model.ListVpcResponse;
import com.baidubce.services.vpc.model.ModifyVpcAttributesRequest;
import com.baidubce.services.vpc.model.NetworkAction;
import org.apache.commons.collections.CollectionUtils;
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
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

/**
 * Provides the client for accessing the Baidu Cloud network Service vpc part.
 */
public class VpcClient extends AbstractBceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(VpcClient.class);

    private static final String VERSION = "v1";
    private static final String VPC_PREFIX = "vpc";
    private static final String VPC_PRIVATE_IP_PREFIX = "privateIpAddressInfo";

    /**
     * Responsible for handling httpResponses from all network service calls.
     */
    private static final HttpResponseHandler[] vpcHandlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on network.
     */
    public VpcClient() {
        this(new VpcClientConfiguration());
    }

    /**
     * Constructs a new network client using the client configuration to access network.
     *
     * @param clientConfiguration The network client configuration options controlling how this client
     *                            connects to network (e.g. proxy settings, retry counts, etc).
     */
    public VpcClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, vpcHandlers);
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
     * Create a vpc with the specified options.
     * @param name The name of vpc
     * @param cidr The CIDR of vpc
     * @return List of vpcId newly created
     */
    public CreateVpcResponse createVpc(String name, String cidr) {
        CreateVpcRequest request = new CreateVpcRequest();
        request.withName(name).withCidr(cidr);
        return createVpc(request);
    }

    /**
     * Create a vpc with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     * <p/>
     *
     * @param request The request containing all options for creating a vpc.
     * @return List of vpcId newly created
     * @throws BceClientException
     */
    public CreateVpcResponse createVpc(CreateVpcRequest request)
            throws BceClientException {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getName(), "name should not be empty");
        checkStringNotEmpty(request.getCidr(), "cidr should not be empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, VPC_PREFIX);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateVpcResponse.class);
    }

    /**
     * Return a list of vpcs owned by the authenticated user.
     *
     * @return The response containing a list of vpcs owned by the authenticated user.
     */
    public ListVpcResponse listVpcs() {
        return this.listVpcs(new ListVpcRequest());
    }

    /**
     * Return a list of vpcs owned by the authenticated user.
     *
     * @param request The request containing all options for listing own's vpc.
     * @return The response containing a list of vpcs owned by the authenticated user.
     */
    public ListVpcResponse listVpcs(ListVpcRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, VPC_PREFIX);
        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (request.getIsDefault() != null) {
            internalRequest.addParameter("isDefault", request.getIsDefault().toString());
        }
        return invokeHttpClient(internalRequest, ListVpcResponse.class);
    }

    /**
     * Get the detail information of specified vpc.
     *
     * @param vpcId The id of the network.
     * @return A vpc detail model for the vpcId.
     */
    public GetVpcResponse getVpc(String vpcId) {
        return getVpc(new GetVpcRequest().withVpcId(vpcId));
    }

    /**
     * Get the detail information of specified vpc.
     *
     * @param getVpcRequest The request containing all options for getting the vpc info.
     * @return A vpc detail model for the vpcId.
     */
    public GetVpcResponse getVpc(GetVpcRequest getVpcRequest) {
        checkNotNull(getVpcRequest, "request should not be null.");
        checkNotNull(getVpcRequest.getVpcId(), "request vpcId should not be null.");
        InternalRequest internalRequest = this.createRequest(
                getVpcRequest, HttpMethodName.GET, VPC_PREFIX, getVpcRequest.getVpcId());
        return this.invokeHttpClient(internalRequest, GetVpcResponse.class);
    }

    /**
     * Delete the specified vpc owned by the user.All resource in the vpc must be deleted before the vpc itself
     * can be deleted.
     *<p>
     *
     * @param vpcId The id of the vpc to delete.
     */
    public void deleteVpc(String vpcId) {
        this.deleteVpc(new DeleteVpcRequest().withVpcId(vpcId));
    }

    /**
     * Delete the specified vpc owned by the user.All resource in the vpc must be deleted before the vpc itself
     * can be deleted.
     *<p>
     *
     * @param deleteVpcRequest The request containing all options for deleting own's vpc.
     */
    public void deleteVpc(DeleteVpcRequest deleteVpcRequest) {
        checkNotNull(deleteVpcRequest, "request should not be null.");
        checkNotNull(deleteVpcRequest.getVpcId(), "request vpcId should not be null.");
        if (Strings.isNullOrEmpty(deleteVpcRequest.getClientToken())) {
            deleteVpcRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                deleteVpcRequest, HttpMethodName.DELETE, VPC_PREFIX, deleteVpcRequest.getVpcId());
        internalRequest.addParameter("clientToken", deleteVpcRequest.getClientToken());
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modifying the special attribute to new value of the vpc owned by the user.
     * @param name The name of the vpc after modifying
     * @param vpcId the id of the vpc
     */
    public void modifyInstanceAttributes(String name, String vpcId) {
        ModifyVpcAttributesRequest request = new ModifyVpcAttributesRequest();
        modifyInstanceAttributes(request.withName(name).withVpcId(vpcId));
    }

    /**
     * Modifying the special attribute to new value of the vpc owned by the user.
     *<p>
     *
     * @param modifyVpcAttributesRequest The request containing all options for modifying own's vpc.
     */
    public void modifyInstanceAttributes(ModifyVpcAttributesRequest modifyVpcAttributesRequest) {
        checkNotNull(modifyVpcAttributesRequest, "request should not be null.");
        checkStringNotEmpty(modifyVpcAttributesRequest.getVpcId(), "request vpcId should not be empty.");
        checkStringNotEmpty(modifyVpcAttributesRequest.getName(), "request name should not be empty.");
        if (Strings.isNullOrEmpty(modifyVpcAttributesRequest.getClientToken())) {
            modifyVpcAttributesRequest.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(
                modifyVpcAttributesRequest, HttpMethodName.PUT, VPC_PREFIX, modifyVpcAttributesRequest.getVpcId());
        internalRequest.addParameter(NetworkAction.modifyAttribute.name(), null);
        internalRequest.addParameter("clientToken", modifyVpcAttributesRequest.getClientToken());
        fillPayload(internalRequest, modifyVpcAttributesRequest);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Return a list of vpc private ip address info which belongs to the corresponding vpc by vpc id.
     *
     * @param request The request contains vpc id, list of private ip addresses and private ip range to be queried.
     */
    public GetVpcPrivateAddressInfoResponse getVpcPrivateIpAddressInfo(GetVpcPrivateIpAddressInfoRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getVpcId(), "request vpcId should not be empty.");
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, VPC_PREFIX, request.getVpcId(), VPC_PRIVATE_IP_PREFIX);
        if (CollectionUtils.isNotEmpty(request.getPrivateIpAddresses())) {
            StringBuilder privateIpAddresses = new StringBuilder();
            for (String privateIpAddress: request.getPrivateIpAddresses()){
                privateIpAddresses.append(privateIpAddress).append(",");
            }
            privateIpAddresses.deleteCharAt(privateIpAddresses.lastIndexOf(","));
            internalRequest.addParameter("privateIpAddresses", privateIpAddresses.toString());
        }
        if (StringUtils.isNotBlank(request.getPrivateIpRange())) {
            internalRequest.addParameter("privateIpRange", request.getPrivateIpRange());
        }
        return this.invokeHttpClient(internalRequest, GetVpcPrivateAddressInfoResponse.class);
    }
}
