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
package com.baidubce.services.eni;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.SignOptions;
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
import com.baidubce.services.eni.model.CreateEniRequest;
import com.baidubce.services.eni.model.CreateEniResponse;
import com.baidubce.services.eni.model.DeleteEniRequest;
import com.baidubce.services.eni.model.EniAction;
import com.baidubce.services.eni.model.EniBindEipRequest;
import com.baidubce.services.eni.model.EniDetail;
import com.baidubce.services.eni.model.EniInstanceOperateRequest;
import com.baidubce.services.eni.model.EniPrivateIpBatchAddRequest;
import com.baidubce.services.eni.model.EniPrivateIpBatchOperateRequest;
import com.baidubce.services.eni.model.EniPrivateIpOperateRequest;
import com.baidubce.services.eni.model.EniStatusResponse;
import com.baidubce.services.eni.model.EniUnBindEipRequest;
import com.baidubce.services.eni.model.EniUpdateEnterpriseSecurityGroupRequest;
import com.baidubce.services.eni.model.EniUpdateRequest;
import com.baidubce.services.eni.model.EniUpdateSecurityGroupRequest;
import com.baidubce.services.eni.model.GetEniDetailRequest;
import com.baidubce.services.eni.model.ListEniRequest;
import com.baidubce.services.eni.model.ListEniResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.util.Validate;
import com.google.common.base.Strings;

/**
 * Provides the client for accessing the Baidu Cloud network Service Elastic Network Interface Card.
 */
public class EniClient extends AbstractBceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EniClient.class);

    private static final String VERSION = "v1";
    private static final String ENI_PREFIX = "eni";
    private static final String CLIENT_TOKEN = "clientToken";
    private static final String MARKER = "marker";
    private static final String MAX_KEYS = "maxKeys";
    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    /**
     * Exception messages.
     */
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    private static final String NAME_MESSAGE_KEY = "name";
    private static final String SUBNET_ID_MESSAGE_KEY = "subnetId";
    private static final String ENI_ID_MESSAGE_KEY = "eniId";
    private static final String SG_AND_ESG_IDS_EMPTY_MESSAGE =
            "securityGroupIds and enterpriseSecurityGroupIds cannot be empty at the same time";
    private static final String SG_AND_ESG_IDS_BOTH_HAVE_VALUES_MESSAGE =
            "securityGroupIds and enterpriseSecurityGroupIds cannot have values at the same time";
    private static final String SECURITY_GROUP_IDS_MESSAGE = "request securityGroupIds should not be null or empty.";
    private static final String ENTERPRISE_SECURITY_GROUP_IDS_MESSAGE =
            "request enterpriseSecurityGroupIds should not be null or empty.";
    private static final String PRIVATE_IP_SET_MESSAGE = "request privateIpSet should not be null or empty.";
    private static final String PRIVATE_IP_ADDRESSES_AND_COUNT_MESSAGE =
            "request privateIpAddresses and privateIpAddressCount should not be neither null or empty.";
    private static final String PRIVATE_IP_ADDRESSES_MESSAGE =
            "request privateIpAddresses should not be null or empty.";
    private static final String VPC_ID_MESSAGE_KEY = "vpcId";
    private static final String INSTANCE_ID_MESSAGE_KEY = "instanceId";
    private static final String PRIVATE_IP_ADDRESS_MESSAGE_KEY = "privateIpAddress";
    private static final String PUBLIC_IP_ADDRESS_MESSAGE_KEY = "publicIpAddress";

    /**
     * Responsible for handling httpResponses from all eni network service calls.
     */
    private static final HttpResponseHandler[] ENI_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on eni.
     */
    public EniClient() {
        this(new EniClientConfiguration());
    }

    /**
     * Constructs a new eni client using the client configuration to access eni.
     */
    public EniClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, ENI_HANDLERS);
    }

    /**
     * Creates and initializes a new request object for the specified bcc resource. This method is responsible
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
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
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
     * Create an ENI with the specified options.
     * You could fill field of clientToken,which is especially for keeping idempotent.
     * securityGroupIds and enterpriseSecurityGroupIds cannot be empty or have values at the same time
     *
     * @param request The request containing all options for creating an ENI.
     * @return id of the newly created ENI.
     */
    public CreateEniResponse createEni(CreateEniRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));
        checkStringNotEmpty(request.getSubnetId(), checkEmptyExceptionMessageFormat(SUBNET_ID_MESSAGE_KEY));
        if (CollectionUtils.isEmpty(request.getSecurityGroupIds())
                && CollectionUtils.isEmpty(request.getEnterpriseSecurityGroupIds())){
                throw  new IllegalArgumentException(SG_AND_ESG_IDS_EMPTY_MESSAGE);
        }
        if (!CollectionUtils.isEmpty(request.getSecurityGroupIds())
                && !CollectionUtils.isEmpty(request.getEnterpriseSecurityGroupIds())){
            throw  new IllegalArgumentException(SG_AND_ESG_IDS_BOTH_HAVE_VALUES_MESSAGE);
        }
        if (CollectionUtils.isEmpty(request.getPrivateIpSet())) {
            throw new IllegalArgumentException(PRIVATE_IP_SET_MESSAGE);
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, ENI_PREFIX);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateEniResponse.class);
    }

    /**
     * Update the name or description of a specified ENI.
     *
     * @param request operate model of updating ENI
     */
    public void updateEni(EniUpdateRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEniId(), checkEmptyExceptionMessageFormat(ENI_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (Strings.isNullOrEmpty(request.getAction())) {
            request.setAction("modifyAttribute");
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ENI_PREFIX,
                request.getEniId());
        internalRequest.addParameter(EniAction.modifyAttribute.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete the specified ENI.
     *
     * @param request The request containing all options for deleting the specified ENI owned by user.
     */
    public void deleteEni(DeleteEniRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEniId(), checkEmptyExceptionMessageFormat(ENI_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, ENI_PREFIX, request.getEniId());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete the specified ENI by ID.
     *
     * @param eniId The id of ENI that will be deleted.
     */
    public void deleteEni(String eniId) {
        deleteEni(DeleteEniRequest.builder().eniId(eniId).build());
    }

    /**
     * List ENIs owned by the authenticated user.
     *
     * @param request The request containing all options for listing ENI owned by user.
     * @return The response with list of ENIs owned by user.
     */
    public ListEniResponse listEni(ListEniRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVpcId(), checkEmptyExceptionMessageFormat(VPC_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, ENI_PREFIX);
        internalRequest.addParameter(VPC_ID_MESSAGE_KEY, request.getVpcId());
        if (!Strings.isNullOrEmpty(request.getInstanceId())) {
            internalRequest.addParameter(INSTANCE_ID_MESSAGE_KEY, request.getInstanceId());
        }
        if (!Strings.isNullOrEmpty(request.getName())) {
            internalRequest.addParameter(NAME_MESSAGE_KEY, request.getName());
        }
        if (!CollectionUtils.isEmpty(request.getPrivateIpAddress())) {
            List<String> privateIpAddress = request.getPrivateIpAddress();
            StringBuilder sb = new StringBuilder();
            sb.append(privateIpAddress.get(0));
            for (int i = 1; i < privateIpAddress.size(); i++) {
                sb.append(",");
                sb.append(privateIpAddress.get(i));
            }
            internalRequest.addParameter(PRIVATE_IP_ADDRESS_MESSAGE_KEY, sb.toString());
        }
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        } else if (request.getMaxKeys() <= 0) {
            internalRequest.addParameter(MAX_KEYS, "1000");
        }
        return invokeHttpClient(internalRequest, ListEniResponse.class);
    }

    /**
     * Get single ENI detail owned by the authenticated user.
     *
     * @param request The request containing all options for get ENI detail owned by user.
     * @return the ENI detail
     */
    public EniDetail getEniDetail(GetEniDetailRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEniId(), checkEmptyExceptionMessageFormat(ENI_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, ENI_PREFIX, request.getEniId());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, EniDetail.class);
    }

    /**
     * Add the private ip of the specified ENI
     * The newly added private IPs are all secondary IPs
     *
     * @param request The request containing all options for adding the secondary ip of the specified ENI.
     */
    public void addPrivateIp(EniPrivateIpOperateRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEniId(), checkEmptyExceptionMessageFormat(ENI_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkNotNull(request.getPrivateIpAddress(), checkEmptyExceptionMessageFormat(PRIVATE_IP_ADDRESS_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                ENI_PREFIX, request.getEniId(), "privateIp");
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete the private ip of the specified ENI
     * The primary IP cannot be deleted, only the secondary IP can be deleted
     *
     * @param request The request containing all options for deleting the secondary ip of the specified ENI.
     */
    public void deletePrivateIp(EniPrivateIpOperateRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEniId(), checkEmptyExceptionMessageFormat(ENI_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getPrivateIpAddress(),
                checkEmptyExceptionMessageFormat(PRIVATE_IP_ADDRESS_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE,
                ENI_PREFIX, request.getEniId(), "privateIp", request.getPrivateIpAddress());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Batch add the private ips of the specified ENI
     * The newly added private IPs are secondary IPs
     *
     * @param request The request containing all options for batch adding the secondary ip of the specified ENI.
     */
    public void batchAddPrivateIp(EniPrivateIpBatchAddRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEniId(), checkEmptyExceptionMessageFormat(ENI_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (request.getPrivateIpAddressCount() == null
                && CollectionUtils.isEmpty(request.getPrivateIpAddresses())) {
            throw new IllegalArgumentException(PRIVATE_IP_ADDRESSES_AND_COUNT_MESSAGE);
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                ENI_PREFIX, request.getEniId(), "privateIp/batchAdd");
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Batch delete the private ips of the specified ENI
     * The primary IP cannot be deleted, only the secondary IP can be deleted
     *
     * @param request The request containing all options for batch deleting the secondary ip of the specified ENI.
     */
    public void batchDeletePrivateIp(EniPrivateIpBatchOperateRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEniId(), checkEmptyExceptionMessageFormat(ENI_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (CollectionUtils.isEmpty(request.getPrivateIpAddresses())) {
            throw new IllegalArgumentException(PRIVATE_IP_ADDRESSES_MESSAGE);
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST,
                        ENI_PREFIX, request.getEniId(), "privateIp/batchDel");
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Mount the ENI to the specified cloud host.
     *
     * @param request The request containing all options for mounting the ENI to the specified cloud host.
     */
    public void attachEniInstance(EniInstanceOperateRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEniId(), checkEmptyExceptionMessageFormat(ENI_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCE_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (Strings.isNullOrEmpty(request.getAction())) {
            request.setAction(EniAction.attach.name());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ENI_PREFIX,
                request.getEniId());
        internalRequest.addParameter(request.getAction(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * The ENI offloads the cloud host.
     *
     * @param request The request containing all options for offloading the cloud host.
     */
    public void detachEniInstance(EniInstanceOperateRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEniId(), checkEmptyExceptionMessageFormat(ENI_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCE_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (Strings.isNullOrEmpty(request.getAction())) {
            request.setAction(EniAction.detach.name());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ENI_PREFIX,
                request.getEniId());
        internalRequest.addParameter(request.getAction(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * ENI bind public IP
     *
     * @param request The request containing all options for binding public IP
     */
    public void bindEniPublicIp(EniBindEipRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEniId(), checkEmptyExceptionMessageFormat(ENI_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getPrivateIpAddress(),
                checkEmptyExceptionMessageFormat(PRIVATE_IP_ADDRESS_MESSAGE_KEY));
        checkStringNotEmpty(request.getPublicIpAddress(),
                checkEmptyExceptionMessageFormat(PUBLIC_IP_ADDRESS_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (Strings.isNullOrEmpty(request.getAction())) {
            request.setAction(EniAction.bind.name());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ENI_PREFIX,
                request.getEniId());
        internalRequest.addParameter(request.getAction(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * ENI unbind public IP
     *
     * @param request The request containing all options for unbinding public IP
     */
    public void unBindEniPublicIp(EniUnBindEipRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEniId(), checkEmptyExceptionMessageFormat(ENI_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getPublicIpAddress(),
                checkEmptyExceptionMessageFormat(PUBLIC_IP_ADDRESS_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (Strings.isNullOrEmpty(request.getAction())) {
            request.setAction(EniAction.unBind.name());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ENI_PREFIX,
                request.getEniId());
        internalRequest.addParameter(request.getAction(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Update the normal security group bound to the ENI.
     * An ENI must have at least one security group (normal security group or enterprise security group)
     * This operation is the normal security group that the ENI will eventually join,
     * and will be removed from the existing normal security group or enterprise security group.
     *
     * @param request The request containing all options for updating the common security group bound to the ENI.
     */
    public void updateEniSecurityGroup(EniUpdateSecurityGroupRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEniId(), checkEmptyExceptionMessageFormat(ENI_ID_MESSAGE_KEY));
        if (CollectionUtils.isEmpty(request.getSecurityGroupIds())) {
            throw new IllegalArgumentException(SECURITY_GROUP_IDS_MESSAGE);
        }
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (Strings.isNullOrEmpty(request.getAction())) {
            request.setAction(EniAction.bindSg.name());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ENI_PREFIX,
                request.getEniId());
        internalRequest.addParameter(request.getAction(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Update the enterprise security group bound to the ENI.
     * An ENI must have at least one security group (normal security group or enterprise security group)
     * This operation is the enterprise security group that the ENI will eventually join,
     * and will be removed from the existing normal security group or enterprise security group.
     *
     * @param request The request containing all options for updating the common security group bound to the ENI.
     */
    public void updateEniEnterpriseSecurityGroup(EniUpdateEnterpriseSecurityGroupRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEniId(), checkEmptyExceptionMessageFormat(ENI_ID_MESSAGE_KEY));
        if (CollectionUtils.isEmpty(request.getEnterpriseSecurityGroupIds())) {
            throw new IllegalArgumentException(ENTERPRISE_SECURITY_GROUP_IDS_MESSAGE);
        }
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (Strings.isNullOrEmpty(request.getAction())) {
            request.setAction(EniAction.bindEsg.name());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ENI_PREFIX,
                request.getEniId());
        internalRequest.addParameter(request.getAction(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public EniStatusResponse getEniStatus(String eniId) {
        GetEniDetailRequest request = new GetEniDetailRequest();
        request.setEniId(eniId);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, ENI_PREFIX,
                request.getEniId(), "status");
        return invokeHttpClient(internalRequest, EniStatusResponse.class);
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


}
