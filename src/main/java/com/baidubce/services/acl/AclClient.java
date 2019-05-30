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
package com.baidubce.services.acl;

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import com.baidubce.services.acl.model.AclRule;
import com.baidubce.services.acl.model.CreateAclRequest;
import com.baidubce.services.acl.model.DeleteAclRequest;
import com.baidubce.services.acl.model.GetAclRequest;
import com.baidubce.services.acl.model.GetAclResponse;
import com.baidubce.services.acl.model.ListAclRequest;
import com.baidubce.services.acl.model.ListAclResponse;
import com.baidubce.services.acl.model.ModifyAclRuleAttributesRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

/**
 * Provides the client for accessing the Baidu Cloud network Service Access Control List (ACL).
 */
public class AclClient extends AbstractBceClient {

    /**
     * ACL API pathVersion
     */
    private static final String VERSION = "v1";

    private static final String PREFIX = "acl";

    private static final String RULE_PREFIX = "rule";

    private static final String CLIENT_TOKEN_IDENTIFY = "clientToken";

    /**
     * Responsible for handling httpResponses from all service calls.
     */
    private static final HttpResponseHandler[] aclHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on network.
     */
    public AclClient() {
        this(new AclClientConfiguration());
    }

    /**
     * Constructs a new network client using the client configuration to access network.
     *
     * @param clientConfiguration The network client configuration options controlling how this client
     *                            connects to network (e.g. proxy settings, retry counts, etc).
     */
    public AclClient(AclClientConfiguration clientConfiguration) {
        super(clientConfiguration, aclHandlers);
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
     * <p/>
     * The default algorithm is using {@link UUID} to generate a random UUID,
     *
     * @return An random String generated by {@link UUID}.
     */
    private String generateClientToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * Create a acl with the specified options.
     *
     * @param aclRules The rules of acl
     */
    public void createAcl(List<AclRule> aclRules) {
        CreateAclRequest request = new CreateAclRequest();
        request.setAclRules(aclRules);
        createAcl(request);
    }

    /**
     * Create a acl with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     *
     * @param createAclRequest The request containing all options for creating a acl.
     */
    public void createAcl(CreateAclRequest createAclRequest) {
        checkNotNull(createAclRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(createAclRequest.getClientToken())) {
            createAclRequest.setClientToken(this.generateClientToken());
        }
        checkNotNull(createAclRequest.getAclRules(), "aclRules should not be null.");
        InternalRequest internalRequest =
                this.createRequest(createAclRequest, HttpMethodName.POST, PREFIX, RULE_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, createAclRequest.getClientToken());
        fillPayload(internalRequest, createAclRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Return a list of aclRules owned by the specified subnet.
     *
     * @return The response containing a list of aclRules owned by the subnet.
     */
    public ListAclResponse listAclRules(String subnetId) {
        return listAclRules(new ListAclRequest().withSubnetId(subnetId));
    }

    /**
     * Return a list of aclRules owned by the specified subnet.
     *
     * @param listAclRequest The request containing all options for listing subnet's aclRule.
     *
     * @return The response containing a list of aclRules owned by the specified subnet.
     */
    public ListAclResponse listAclRules(ListAclRequest listAclRequest) {
        checkNotNull(listAclRequest, "request should not be null.");
        checkStringNotEmpty(listAclRequest.getSubnetId(), "request subnetId should not be null");
        InternalRequest internalRequest = this.createRequest(listAclRequest, HttpMethodName.GET, PREFIX, RULE_PREFIX);
        internalRequest.addParameter("subnetId", listAclRequest.getSubnetId());
        if (listAclRequest.getMarker() != null) {
            internalRequest.addParameter("marker", listAclRequest.getMarker());
        }
        if (listAclRequest.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(listAclRequest.getMaxKeys()));
        }
        return invokeHttpClient(internalRequest, ListAclResponse.class);
    }

    /**
     * Get the detail acl information of specified vpc.
     *
     * @param vpcId The id of the network.
     *
     * @return A acl detail model for the vpcId.
     */
    public GetAclResponse getAcl(String vpcId) {
        return getAcl(new GetAclRequest().withVpcId(vpcId));
    }

    /**
     * Get the detail acl information of specified vpc.
     *
     * @param getAclRequest The request containing all options for getting the acl info.
     *
     * @return A acl detail model for the vpcId.
     */
    public GetAclResponse getAcl(GetAclRequest getAclRequest) {
        checkNotNull(getAclRequest, "request should not be null.");
        checkStringNotEmpty(getAclRequest.getVpcId(), "request vpcId should not be null");
        InternalRequest internalRequest = this.createRequest(getAclRequest, HttpMethodName.GET, PREFIX);
        internalRequest.addParameter("vpcId", getAclRequest.getVpcId());
        return invokeHttpClient(internalRequest, GetAclResponse.class);
    }

    /**
     * Delete the specified aclRule.
     * <p>
     *
     * @param aclRuleId The id of the aclRule to delete.
     */
    public void deleteAcl(String aclRuleId) {
        deleteAcl(new DeleteAclRequest().withAclRuleId(aclRuleId));
    }

    /**
     * Delete the specified aclRule.
     * <p>
     *
     * @param deleteAclRequest The request containing all options for deleting aclRule.
     */
    public void deleteAcl(DeleteAclRequest deleteAclRequest) {
        checkNotNull(deleteAclRequest, "request should not be null.");
        checkStringNotEmpty(deleteAclRequest.getAclRuleId(), "request aclRuleId should not be null.");
        if (Strings.isNullOrEmpty(deleteAclRequest.getClientToken())) {
            deleteAclRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(deleteAclRequest, HttpMethodName.DELETE, PREFIX, RULE_PREFIX,
                        deleteAclRequest.getAclRuleId());
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, deleteAclRequest.getClientToken());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modifying the special attribute to new aclRule owned by the user.
     *
     * @param aclRuleId The id of the aclRule
     * @param aclRule   the aclRule after modifying
     */
    public void modifyAclRuleAttributes(String aclRuleId, AclRule aclRule) {
        ModifyAclRuleAttributesRequest request =
                new ModifyAclRuleAttributesRequest().withAclRuleId(aclRuleId).withDescription
                        (aclRule.getDescription()).withProtocol(aclRule.getProtocol()).withSourceIpAddress(aclRule
                        .getSourceIpAddress()).withDestinationIpAddress(aclRule.getDestinationIpAddress())
                        .withSourcePort(aclRule.getSourcePort()).withDestinationPort(aclRule.getDestinationPort())
                        .withPosition(aclRule.getPosition()).withAction(aclRule.getAction());
        modifyAclRuleAttributes(request);
    }

    /**
     * Modifying the special attribute to new aclRule owned by the user.
     * <p/>
     *
     * @param modifyAclRuleAttributesRequest The request containing all options for modifying own's aclRule.
     */
    public void modifyAclRuleAttributes(ModifyAclRuleAttributesRequest modifyAclRuleAttributesRequest) {
        checkNotNull(modifyAclRuleAttributesRequest, "request should not be null.");
        checkStringNotEmpty(modifyAclRuleAttributesRequest.getAclRuleId(), "request aclRuleId should not be null.");
        if (Strings.isNullOrEmpty(modifyAclRuleAttributesRequest.getClientToken())) {
            modifyAclRuleAttributesRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(modifyAclRuleAttributesRequest, HttpMethodName.PUT, PREFIX, RULE_PREFIX,
                        modifyAclRuleAttributesRequest.getAclRuleId());
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, modifyAclRuleAttributesRequest.getClientToken());
        fillPayload(internalRequest, modifyAclRuleAttributesRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

}
