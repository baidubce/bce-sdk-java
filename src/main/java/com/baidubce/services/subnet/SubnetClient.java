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
package com.baidubce.services.subnet;

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
import com.baidubce.services.subnet.model.CreateSubnetRequest;
import com.baidubce.services.subnet.model.CreateSubnetResponse;
import com.baidubce.services.subnet.model.DeleteSubnetRequest;
import com.baidubce.services.subnet.model.GetSubnetRequest;
import com.baidubce.services.subnet.model.GetSubnetResponse;
import com.baidubce.services.subnet.model.ListSubnetsRequest;
import com.baidubce.services.subnet.model.ListSubnetsResponse;
import com.baidubce.services.subnet.model.ModifySubnetAttributesRequest;
import com.baidubce.services.subnet.model.NetworkAction;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Baidu Cloud network Service subnet part.
 */
public class SubnetClient extends AbstractBceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubnetClient.class);

    private static final String VERSION = "v1";
    private static final String SUBNET_PREFIX = "subnet";

    /**
     * Responsible for handling httpResponses from all network service calls.
     */
    private static final HttpResponseHandler[] vpc_handlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on network.
     */
    public SubnetClient() {
        this(new SubnetClientConfiguration());
    }

    /**
     * Constructs a new network client using the client configuration to access network.
     *
     * @param clientConfiguration The network client configuration options controlling how this client
     *                            connects to network (e.g. proxy settings, retry counts, etc).
     */
    public SubnetClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, vpc_handlers);
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
     *
     * @param name   The name of subnet that will be created.
     * @param vpcId  The id of vpc which this subnet belong.
     * @param cidr   The CIDR of this subnet.
     * @param zoneName
     *        the name of available zone which the subnet belong
     *        through listZones, we can get all available zone info at current region
     *        ee.g. "cn-gz-a"  "cn-gz-b"
     * @return
     */
    public CreateSubnetResponse createSubnet(String name, String vpcId, String cidr, String zoneName) {
        CreateSubnetRequest request = new CreateSubnetRequest();
        request.withName(name).withCidr(cidr).withZoneName(zoneName).withVpcId(vpcId);
        return createSubnet(request);

    }
    /**
     * Create a subnet with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     * <p/>
     *
     * @param request The request containing all options for creating subnet.
     * @return List of subnetId newly created
     * @throws BceClientException
     */
    public CreateSubnetResponse createSubnet(CreateSubnetRequest request)
            throws BceClientException {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getName(), "name should not be empty");
        checkStringNotEmpty(request.getCidr(), "cidr should not be empty");
        checkStringNotEmpty(request.getZoneName(), "zone name should not be empty");
        checkStringNotEmpty(request.getVpcId(), "vpc id should not be empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, SUBNET_PREFIX);
        if (!Strings.isNullOrEmpty(request.getClientToken())) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateSubnetResponse.class);
    }

    /**
     * Return a list of subnets owned by the authenticated user.
     *
     * @return The response containing a list of subnets owned by the authenticated user.
     */
    public ListSubnetsResponse listSubnets() {
        return this.listSubnets(new ListSubnetsRequest());
    }

    /**
     * Return a list of subnet owned by the authenticated user.
     *
     * @param request The request containing all options for listing own's subnet.
     * @return The response containing a list of subnets owned by the authenticated user.
     */
    public ListSubnetsResponse listSubnets(ListSubnetsRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, SUBNET_PREFIX);
        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        return invokeHttpClient(internalRequest, ListSubnetsResponse.class);
    }

    /**
     * Get the detail information of specified subnet.
     *
     * @param subnetId The id of the subnet.
     * @return A subnet detail model for the subnetId.
     */
    public GetSubnetResponse getSubnet(String subnetId) {
        return getSubnet(new GetSubnetRequest().withSubnetId(subnetId));
    }

    /**
     * Get the detail information of specified subnet.
     *
     * @param getSubnetRequest The request containing all options for getting the subnet info.
     * @return A subnet detail model for the subnetId.
     */
    public GetSubnetResponse getSubnet(GetSubnetRequest getSubnetRequest) {
        checkNotNull(getSubnetRequest, "request should not be null.");
        checkNotNull(getSubnetRequest.getSubnetId(), "request vpcId should not be null.");
        InternalRequest internalRequest = this.createRequest(
                getSubnetRequest, HttpMethodName.GET, SUBNET_PREFIX, getSubnetRequest.getSubnetId());
        return this.invokeHttpClient(internalRequest, GetSubnetResponse.class);
    }

    /**
     * Delete the specified subnet owned by the user.
     * <p/>
     *
     * @param subnetId The id of the subnet to delete.
     */
    public void deleteSubnet(String subnetId) {
        this.deleteSubnet(new DeleteSubnetRequest().withSubnetId(subnetId));
    }

    /**
     * Delete the specified subnet owned by the user.
     * <p/>
     *
     * @param deleteSubnetRequest the request containing all options for deleting own's subnet.
     */
    public void deleteSubnet(DeleteSubnetRequest deleteSubnetRequest) {
        checkNotNull(deleteSubnetRequest, "request should not be null.");
        checkNotNull(deleteSubnetRequest.getSubnetId(), "request subnetId should not be null.");
        if (Strings.isNullOrEmpty(deleteSubnetRequest.getClientToken())) {
            deleteSubnetRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                deleteSubnetRequest, HttpMethodName.DELETE, SUBNET_PREFIX, deleteSubnetRequest.getSubnetId());
        internalRequest.addParameter("clientToken", deleteSubnetRequest.getClientToken());
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modifying the special attribute to new value of the subnet owned by the user.
     * @param subnetId The id of the subnet
     * @param name The name of the subnet after modifying
     */
    public void modifySubnetAttributes(String subnetId, String name) {
        ModifySubnetAttributesRequest request = new ModifySubnetAttributesRequest();
        modifySubnetAttributes(request.withName(name).withSubnetId(subnetId));
    }
    /**
     * Modifying the special attribute to new value of the subnet owned by the user.
     * <p/>
     *
     * @param modifySubnetAttributesRequest The request containing all options for modifying own's subnet.
     */
    public void modifySubnetAttributes(ModifySubnetAttributesRequest modifySubnetAttributesRequest) {
        checkNotNull(modifySubnetAttributesRequest, "request should not be null.");
        checkStringNotEmpty(modifySubnetAttributesRequest.getSubnetId(), "request subnetId should not be empty.");
        checkStringNotEmpty(modifySubnetAttributesRequest.getName(), "request name should not be empty.");
        if (Strings.isNullOrEmpty(modifySubnetAttributesRequest.getClientToken())) {
            modifySubnetAttributesRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                modifySubnetAttributesRequest, HttpMethodName.PUT,
                SUBNET_PREFIX, modifySubnetAttributesRequest.getSubnetId());
        fillPayload(internalRequest, modifySubnetAttributesRequest);
        internalRequest.addParameter(NetworkAction.modifyAttribute.name(), null);
        internalRequest.addParameter("clientToken", modifySubnetAttributesRequest.getClientToken());
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }
}
