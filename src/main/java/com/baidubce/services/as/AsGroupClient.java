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
import com.baidubce.services.as.model.asgroup.AdjustAsGroupRequest;
import com.baidubce.services.as.model.asgroup.AdjustAsGroupResponse;
import com.baidubce.services.as.model.asgroup.AsGroupAction;
import com.baidubce.services.as.model.asgroup.DecreaseAsGroupRequest;
import com.baidubce.services.as.model.asgroup.DecreaseAsGroupResponse;
import com.baidubce.services.as.model.asgroup.GetAsGroupRequest;
import com.baidubce.services.as.model.asgroup.GetAsGroupResponse;
import com.baidubce.services.as.model.asgroup.IncreaseAsGroupRequest;
import com.baidubce.services.as.model.asgroup.IncreaseAsGroupResponse;
import com.baidubce.services.as.model.asgroup.ListAsGroupRequest;
import com.baidubce.services.as.model.asgroup.ListAsGroupResponse;
import com.baidubce.services.as.model.asgroup.ListAsNodeRequest;
import com.baidubce.services.as.model.asgroup.ListAsNodeResponse;
import com.baidubce.services.as.model.asgroup.TemplateUpdateRequest;
import com.baidubce.services.as.model.asgroup.GroupCreateResponse;
import com.baidubce.services.as.model.asgroup.GroupCreateRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
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
import static com.baidubce.util.Validate.checkIsTrue;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Auto Scaling Group(asGroup).
 */
public class AsGroupClient extends AbstractBceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsGroupClient.class);

    private static final String VERSION = "v2";
    private static final String TEMPLATE = "template";
    private static final String AS_GROUP = "asGroup";
    private static final String AS_NODE = "asNode";
    private static final String GROUP_NAME = "groupName";
    private static final String GROUP_ID = "groupId";
    private static final String GROUP_ID_LOWER= "groupid";
    private static final String MARKER = "marker";
    private static final String MAX_KEYS = "maxKeys";
    private static final String CLIENT_TOKEN = "clientToken";

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
     * The default method to generate the random String for clientToken if the optional parameter clientToken
     * is not specified by the user.
     * <p>
     * The default algorithm is using {@link UUID} to generate a random UUID,
     *
     * @return An random String generated by {@link UUID}.
     */
    private String generateClientToken() {
        return UUID.randomUUID().toString();
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
                this.createRequest(request, HttpMethodName.GET, AS_GROUP);
        if (request.getMarker() != null) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        if (request.getGroupName() != null) {
            internalRequest.addParameter(GROUP_NAME, request.getGroupName());
        }
        return invokeHttpClient(internalRequest, ListAsGroupResponse.class);
    }

    /**
     * Query detailed information about a single auto scaling group.
     *
     * @param request The request containing all options for querying the information of the specified asGroup.
     */
    public GetAsGroupResponse getAsGroup(GetAsGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, AS_GROUP, request.getGroupId());
        return invokeHttpClient(internalRequest, GetAsGroupResponse.class);
    }

    /**
     * Add nodes in the specified auto scaling group
     *
     * @param request The request containing all options for increasing the specified asGroup.
     * @return The list of newly added instance IDs and auto scaling group ID
     */
    public IncreaseAsGroupResponse increaseAsGroup(IncreaseAsGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, AS_GROUP, request.getGroupId());
        internalRequest.addParameter(AsGroupAction.increase.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, IncreaseAsGroupResponse.class);
    }


    /**
     * create new  auto scaling group
     *
     * @param request The request containing all options for creating the specified asGroup.
     * @return the groupId and orderId
     */
    public GroupCreateResponse createAsGroup(GroupCreateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, AS_GROUP, null);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, GroupCreateResponse.class);
    }

    /**
     * update the template
     *
     * @param request The request containing all options for upating the template
     * @return
     */
    public void updateTemplate(String groupId, TemplateUpdateRequest request) {
        checkStringNotEmpty(groupId, checkEmptyExceptionMessageFormat(GROUP_ID));
        checkNotNull(request);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, AS_GROUP, TEMPLATE);
        internalRequest.addParameter(GROUP_ID_LOWER, groupId);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Reduce nodes in the auto scaling group
     *
     * @param request The request containing all options for decreasing the specified asGroup.
     * @return Decrease task id
     */
    public DecreaseAsGroupResponse decreaseAsGroup(DecreaseAsGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, AS_GROUP, request.getGroupId());
        internalRequest.addParameter(AsGroupAction.decrease.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, DecreaseAsGroupResponse.class);
    }

    /**
     * Adjust the number of nodes in the auto scaling group
     *
     * @param request The request containing all options for adjusting the specified asGroup.
     * @return Adjust task id
     */
    public AdjustAsGroupResponse adjustAsGroup(AdjustAsGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getGroupId(), checkEmptyExceptionMessageFormat(GROUP_ID));
        checkIsTrue(request.getActionNum() >= 0, "The number of nodes cannot be negative");
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, AS_GROUP, request.getGroupId());
        internalRequest.addParameter(AsGroupAction.adjust.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AdjustAsGroupResponse.class);
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
                this.createRequest(request, HttpMethodName.GET, AS_NODE, request.getGroupId());
        if (request.getMarker() != null) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        return invokeHttpClient(internalRequest, ListAsNodeResponse.class);
    }
}
