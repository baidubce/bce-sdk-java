/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.as;

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
import com.baidubce.services.as.model.asgroup.AsGroupBatchRequest;
import com.baidubce.services.as.model.asgroup.AsGroupDeleteResponse;
import com.baidubce.services.as.model.asgroup.GetAsGroupRequest;
import com.baidubce.services.as.model.asgroup.GetAsGroupResponse;
import com.baidubce.services.as.model.asgroup.GroupCreateRequest;
import com.baidubce.services.as.model.asgroup.GroupCreateResponse;
import com.baidubce.services.as.model.asgroup.ListAsGroupRequest;
import com.baidubce.services.as.model.asgroup.ListAsGroupResponse;
import com.baidubce.services.as.model.asgroup.ListAsNodeRequest;
import com.baidubce.services.as.model.asgroup.ListAsNodeResponse;
import com.baidubce.services.as.model.rule.RuleDelRequest;
import com.baidubce.services.as.model.rule.RuleListQuery;
import com.baidubce.services.as.model.rule.RuleQuery;
import com.baidubce.services.as.model.rule.RuleRequest;
import com.baidubce.services.as.model.rule.CreateRuleResult;
import com.baidubce.services.as.model.rule.RuleVOListResponse;
import com.baidubce.services.as.model.rule.RuleVOResponse;
import com.baidubce.services.as.model.task.DetachNodeRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import org.apache.directory.api.util.Strings;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkPattern;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Auto Scaling Group(asGroup).
 */
public class AsGroupClient extends AbstractBceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsGroupClient.class);

    private static final String VERSION = "v1";
    private static final String GROUP = "group";
    private static final String RULE = "rule";
    private static final String GROUP_ID = "groupId";

    private static final String GROUP_IDS = "groupIds";
    private static final String DELETE = "delete";
    private static final String NODE = "node";
    private static final String GROUP_ID_V2 = "groupid";


    /**
     * Exception messages.
     */
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";

    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    /**
     * Responsible for handling httpResponses from all asGroup service calls.
     */
    private static final HttpResponseHandler[] as_group_handlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on asGroup.
     */
    public AsGroupClient() {
        this(new AsGroupClientConfiguration());
    }

    /**
     * Constructs a new asGroup client using the client configuration to access asGroup.
     *
     * @param clientConfiguration The as client configuration options controlling how this client
     *                            connects to asGroup (e.g. proxy settings, retry counts, etc).
     */
    public AsGroupClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, as_group_handlers);
    }

    /**
     * Creates and initializes a new request object for the specified asGroup resource. This method is responsible
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
     * Detach nodes from the specified auto scaling group
     *
     * @param detachNodeRequest The request containing all options for detaching the specified nodes.
     *                          The groupId and nodes are required.
     */
    public void detachNode(DetachNodeRequest detachNodeRequest) {
        checkNotNull(detachNodeRequest, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(detachNodeRequest.getGroupId(), checkEmptyExceptionMessageFormat(GROUP_ID));
        checkNotNull(detachNodeRequest.getNodes(), checkEmptyExceptionMessageFormat("nodes"));
        InternalRequest internalRequest =
                this.createRequest(detachNodeRequest, HttpMethodName.POST, GROUP, detachNodeRequest.getGroupId());
        internalRequest.addParameter("detachNode", null);
        fillPayload(internalRequest, detachNodeRequest);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Create a new rule
     *
     * @param ruleRequest The request containing all options for creating the specified rule.
     * @return The ruleId
     */
    public CreateRuleResult createRule(RuleRequest ruleRequest) {
        checkNotNull(ruleRequest, REQUEST_NULL_ERROR_MESSAGE);
        checkAsRuleRequest(ruleRequest);
        InternalRequest internalRequest =
                this.createRequest(ruleRequest, HttpMethodName.POST, RULE);
        fillPayload(internalRequest, ruleRequest);
        return invokeHttpClient(internalRequest, CreateRuleResult.class);
    }

    /**
     * Update the specified rule
     *
     * @param ruleRequest The request containing all options for updating the specified rule.
     */
    public void updateRule(RuleRequest ruleRequest) {
        checkNotNull(ruleRequest, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(ruleRequest.getRuleId(), checkEmptyExceptionMessageFormat("ruleId"));
        checkAsRuleRequest(ruleRequest);
        InternalRequest internalRequest =
                this.createRequest(ruleRequest, HttpMethodName.PUT, RULE, ruleRequest.getRuleId());
        fillPayload(internalRequest, ruleRequest);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Query the rule list
     *
     * @param ruleListQuery The request containing all options for querying the rule list.
     * @return The rule list
     */
    public RuleVOListResponse queryRuleList(RuleListQuery ruleListQuery) {
        checkNotNull(ruleListQuery, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(ruleListQuery, HttpMethodName.GET, RULE);
        checkAsRuleQuery(ruleListQuery, internalRequest);
        return invokeHttpClient(internalRequest, RuleVOListResponse.class);
    }

    /**
     * Query the specified rule
     *
     * @param ruleQuery The request containing all options for querying the specified rule.
     * @return The rule
     */
    public RuleVOResponse getRule(RuleQuery ruleQuery) {
        checkNotNull(ruleQuery, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(ruleQuery.getRuleId(), checkEmptyExceptionMessageFormat("ruleId"));
        InternalRequest internalRequest =
                this.createRequest(ruleQuery, HttpMethodName.GET, RULE, ruleQuery.getRuleId());
        return invokeHttpClient(internalRequest, RuleVOResponse.class);
    }

    /**
     * Delete the specified rule
     *
     * @param ruleDelRequest The request containing all options for deleting the specified rule.
     */
    public void deleteRule(RuleDelRequest ruleDelRequest) {
        checkNotNull(ruleDelRequest, REQUEST_NULL_ERROR_MESSAGE);
        checkRuleDelRequest(ruleDelRequest);
        InternalRequest internalRequest =
                this.createRequest(ruleDelRequest, HttpMethodName.POST, RULE);
        internalRequest.addParameter("delete", null);
        fillPayload(internalRequest, ruleDelRequest);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    private void checkRuleDelRequest(RuleDelRequest ruleDelRequest) {
        if ((CollectionUtils.isEmpty(ruleDelRequest.getRuleIds()) &&
                CollectionUtils.isEmpty(ruleDelRequest.getGroupIds()))) {
            throw new IllegalArgumentException("ruleIds and groupIds cannot be empty at the same time");
        }
    }

    private void checkAsRuleQuery(RuleListQuery ruleListQuery, InternalRequest internalRequest) {
        checkStringNotEmpty(ruleListQuery.getGroupId(), checkEmptyExceptionMessageFormat(GROUP_ID));
        checkNotNull(ruleListQuery.getPageNo(), checkEmptyExceptionMessageFormat("pageNo"));

        if (Strings.isNotEmpty(ruleListQuery.getGroupId())) {
            internalRequest.addParameter("groupid", ruleListQuery.getGroupId());
        }
        if (Strings.isNotEmpty(ruleListQuery.getKeyword())) {
            internalRequest.addParameter("keyword", ruleListQuery.getKeyword());
        }
        if (Strings.isNotEmpty(ruleListQuery.getKeywordType())) {
            internalRequest.addParameter("keywordType", ruleListQuery.getKeywordType());
        }
        if (Strings.isNotEmpty(ruleListQuery.getOrder())) {
            internalRequest.addParameter("order", ruleListQuery.getOrder());
        }
        if (Strings.isNotEmpty(ruleListQuery.getOrderBy())) {
            internalRequest.addParameter("orderBy", ruleListQuery.getOrderBy());
        }
        if (ruleListQuery.getPageNo() != null) {
            internalRequest.addParameter("pageNo", String.valueOf(ruleListQuery.getPageNo()));
        }
        if (ruleListQuery.getPageSize() != null) {
            internalRequest.addParameter("pageSize", String.valueOf(ruleListQuery.getPageSize()));
        }
    }

    private void checkAsRuleRequest(RuleRequest ruleRequest) {
        checkStringNotEmpty(ruleRequest.getRuleName(), checkEmptyExceptionMessageFormat("ruleName"));
        checkStringNotEmpty(ruleRequest.getGroupId(), checkEmptyExceptionMessageFormat(GROUP_ID));
        checkNotNull(ruleRequest.getState(), checkEmptyExceptionMessageFormat("state"));
        checkNotNull(ruleRequest.getType(), checkEmptyExceptionMessageFormat("type"));
        checkNotNull(ruleRequest.getActionType(), checkEmptyExceptionMessageFormat("actionType"));
        checkNotNull(ruleRequest.getActionNum(), checkEmptyExceptionMessageFormat("actionNum"));
        checkNotNull(ruleRequest.getCooldownInSec(), checkEmptyExceptionMessageFormat("cooldownInSec"));
        if (Strings.isNotEmpty(ruleRequest.getPeriodStartTime())) {
            checkUTC(ruleRequest.getPeriodStartTime());
        }
        if (Strings.isNotEmpty(ruleRequest.getPeriodEndTime())) {
            checkUTC(ruleRequest.getPeriodEndTime());
        }
    }

    private void checkUTC(String time) {
        String utcPattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z$";
        checkPattern(time, utcPattern, "time should be UTC format");
    }

    /**
     * create new  auto scaling group
     *
     * @param request The request containing all options for creating the specified asGroup.
     * @return the groupId and orderId
     */
    public GroupCreateResponse createAsGroup(GroupCreateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, GROUP);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, GroupCreateResponse.class);
    }

    public AsGroupDeleteResponse deleteAsGroup(AsGroupBatchRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkNotNull(request.getGroupIds(), checkEmptyExceptionMessageFormat(GROUP_IDS));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, GROUP, DELETE);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AsGroupDeleteResponse.class);
        AsGroupDeleteResponse response = new AsGroupDeleteResponse();
        response.setGroupIds(request.getGroupIds());
        return response;
    }


    /**
     * List detailed information of all auto scaling groups
     *
     * @param request The request containing all options for querying the asGroup list.
     * @return auto scaling group list
     */
    public ListAsGroupResponse listAsGroup(ListAsGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, GROUP);
        checkAsGroupListQuery(request, internalRequest);
        return invokeHttpClient(internalRequest, ListAsGroupResponse.class);
    }

    private void checkAsGroupListQuery(ListAsGroupRequest request, InternalRequest internalRequest) {
        checkNotNull(request.getPageNo(), checkEmptyExceptionMessageFormat("pageNo"));
        if (Strings.isNotEmpty(request.getKeyword())) {
            internalRequest.addParameter("keyword", request.getKeyword());
        }
        if (Strings.isNotEmpty(request.getKeywordType())) {
            internalRequest.addParameter("keywordType", request.getKeywordType());
        }
        if (Strings.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        if (Strings.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        if (request.getPageNo() != null) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() != null) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }
    }

    /**
     * Query detailed information about a single auto scaling group.
     *
     * @param request The request containing all options for querying the information of the specified asGroup.
     */
    public GetAsGroupResponse getAsGroupDetail(GetAsGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getGroupId(), checkEmptyExceptionMessageFormat(GROUP_ID));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, GROUP, request.getGroupId());
        return invokeHttpClient(internalRequest, GetAsGroupResponse.class);
    }

    /**
     * Query the detailed information of the nodes in the specified auto scaling group.
     *
     * @param request The request containing all options for querying the asNode list.
     * @return List of node details.
     */
    public ListAsNodeResponse listAsNode(ListAsNodeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getGroupId(), checkEmptyExceptionMessageFormat(GROUP_ID));
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, NODE);
        checkAsGroupNodeListQuery(request, internalRequest);
        internalRequest.addParameter(GROUP_ID_V2, request.getGroupId());

        return invokeHttpClient(internalRequest, ListAsNodeResponse.class);
    }

    private void checkAsGroupNodeListQuery(ListAsNodeRequest request, InternalRequest internalRequest) {
        checkNotNull(request.getPageNo(), checkEmptyExceptionMessageFormat("pageNo"));
        if (Strings.isNotEmpty(request.getKeyword())) {
            internalRequest.addParameter("keyword", request.getKeyword());
        }
        if (Strings.isNotEmpty(request.getKeywordType())) {
            internalRequest.addParameter("keywordType", request.getKeywordType());
        }
        if (Strings.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        if (Strings.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        if (request.getPageNo() != null) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() != null) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }
    }
}
