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
package com.baidubce.services.esg;

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
import com.baidubce.services.esg.model.CreateEsgRequest;
import com.baidubce.services.esg.model.CreateEsgResponse;
import com.baidubce.services.esg.model.DeleteEsgRequest;
import com.baidubce.services.esg.model.DeleteEsgRuleRequest;
import com.baidubce.services.esg.model.EsgAction;
import com.baidubce.services.esg.model.EsgRuleOperateRequest;
import com.baidubce.services.esg.model.ListEsgRequest;
import com.baidubce.services.esg.model.ListEsgResponse;
import com.baidubce.services.esg.model.UpdateEsgRuleRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.util.Validate;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Baidu Cloud network Service enterprise security group.
 */
public class EsgClient extends AbstractBceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EsgClient.class);

    private static final String VERSION = "v1";
    private static final String ESG_PREFIX = "enterprise/security";
    private static final String ESG_RULE_PREFIX = ESG_PREFIX + "/rule";
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
    private static final String REQUEST_RULE_NULL_ERROR_MESSAGE = "request rule should not be null.";
    private static final String NAME_MESSAGE_KEY = "name";
    private static final String ESGID_MESSAGE_KEY = "enterpriseSecurityGroupId";
    private static final String INSTANCEID_MESSAGE_KEY = "instanceId";

    /**
     * Responsible for handling httpResponses from all esg network service calls.
     */
    private static final HttpResponseHandler[] ESG_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on esg.
     */
    public EsgClient() {
        this(new EsgClientConfiguration());
    }

    /**
     * Constructs a new esg client using the client configuration to access esg.
     */
    public EsgClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, ESG_HANDLERS);
    }

    /**
     * Creates and initializes a new request object for the specified bcc resource. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest The original request, as created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
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
     * Create an enterprise security group with the specified options.
     * You must fill field of clientToken,which is especially for keeping idempotent.
     *
     * @param request The request containing all options for creating a enterprise security group.
     * @return enterprise security group id newly created
     */
    public CreateEsgResponse createEsg(CreateEsgRequest request){
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, ESG_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateEsgResponse.class);
    }

    /**
     * List enterprise security group owned by the authenticated user.
     *
     * @param request The request containing all options for listing enterprise security group owned by user.
     * @return The response with list of enterprise security group
     *          which contains enterprise security group rules owned by user.
     */
    public ListEsgResponse listEsg(ListEsgRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, ESG_PREFIX);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }else if (request.getMaxKeys() <= 0){
            internalRequest.addParameter(MAX_KEYS, "1000");
        }
        if (!Strings.isNullOrEmpty(request.getInstanceId())) {
            internalRequest.addParameter(INSTANCEID_MESSAGE_KEY, request.getInstanceId());
        }
        return invokeHttpClient(internalRequest, ListEsgResponse.class);
    }

    /**
     * Delete the specified enterprise security group.
     *
     * @param request The request containing all options for deleting the specified
     *                enterprise security group owned by user.
     */
    public void deleteEsg(DeleteEsgRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEnterpriseSecurityGroupId(),
                checkEmptyExceptionMessageFormat(ESGID_MESSAGE_KEY));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, ESG_PREFIX, request.getEnterpriseSecurityGroupId());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete the specified enterprise security group.
     *
     * @param esgId The id of enterprise security group that will be deleted.
     */
    public void deleteEsg(String esgId) {
        deleteEsg(DeleteEsgRequest.builder().enterpriseSecurityGroupId(esgId).build());
    }

    /**
     * Authorize enterprise security group rules to a specified enterprise security group
     *
     * @param request The request containing all options for authorizing enterprise security group rule
     */
    public void authorizeEsgRule(EsgRuleOperateRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEnterpriseSecurityGroupId(),
                checkEmptyExceptionMessageFormat(ESGID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        if (Strings.isNullOrEmpty(request.getAction())) {
            request.setAction("authorizeRule");
        }
        if (null == request.getRules()) {
            throw new IllegalArgumentException(REQUEST_RULE_NULL_ERROR_MESSAGE);
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ESG_PREFIX,
                request.getEnterpriseSecurityGroupId());
        internalRequest.addParameter(EsgAction.authorizeRule.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete an enterprise security group rule from the specified enterprise security group
     *
     * @param request The request containing all options for deleting enterprise security group rule
     */
    public void deleteEsgRule(DeleteEsgRuleRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEnterpriseSecurityGroupRuleId(),
                checkEmptyExceptionMessageFormat(ESGID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, ESG_RULE_PREFIX,
                request.getEnterpriseSecurityGroupRuleId());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete the specified enterprise security group rule.
     *
     * @param esgRuleId The id of enterprise security group that will be deleted.
     */
    public void deleteEsgRule(String esgRuleId) {
        deleteEsgRule(DeleteEsgRuleRequest.builder().enterpriseSecurityGroupRuleId(esgRuleId).build());
    }

    /**
     * Update the specified enterprise security group rule.
     *
     * @param request The request containing all options for updating enterprise security group rule.
     */
    public void updateEsgRule(UpdateEsgRuleRequest request){
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getEnterpriseSecurityGroupRuleId(),
                checkEmptyExceptionMessageFormat(ESGID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ESG_RULE_PREFIX,
                request.getEnterpriseSecurityGroupRuleId());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
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
