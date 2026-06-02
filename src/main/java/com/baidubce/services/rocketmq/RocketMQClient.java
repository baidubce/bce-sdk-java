package com.baidubce.services.rocketmq;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.SignOptions;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcc.BccClientConfiguration;
import com.baidubce.services.rocketmq.model.Message;
import com.baidubce.services.rocketmq.model.request.CreateAclUserConfigRequest;
import com.baidubce.services.rocketmq.model.request.CreateClusterRequest;
import com.baidubce.services.rocketmq.model.request.CreateConsumerGroupRequest;
import com.baidubce.services.rocketmq.model.request.CreateTopicRequest;
import com.baidubce.services.rocketmq.model.request.DeleteAclUserConfigRequest;
import com.baidubce.services.rocketmq.model.request.DeleteClusterRequest;
import com.baidubce.services.rocketmq.model.request.DeleteConsumerGroupRequest;
import com.baidubce.services.rocketmq.model.request.DeleteTopicRequest;
import com.baidubce.services.rocketmq.model.request.ExpandBrokerRequest;
import com.baidubce.services.rocketmq.model.request.GetAclUserRequest;
import com.baidubce.services.rocketmq.model.request.GetClusterAccessEndpointsRequest;
import com.baidubce.services.rocketmq.model.request.GetClusterRequest;
import com.baidubce.services.rocketmq.model.request.GetConsumerGroupRequest;
import com.baidubce.services.rocketmq.model.request.GetTopicRequest;
import com.baidubce.services.rocketmq.model.request.IncreaseStorageSizeRequest;
import com.baidubce.services.rocketmq.model.request.ListActionOperationsRequest;
import com.baidubce.services.rocketmq.model.request.ListClusterAclUserConfigsRequest;
import com.baidubce.services.rocketmq.model.request.ListClusterActionsRequest;
import com.baidubce.services.rocketmq.model.request.ListClusterBrokerNodesRequest;
import com.baidubce.services.rocketmq.model.request.ListClusterConsumerGroupsRequest;
import com.baidubce.services.rocketmq.model.request.ListClusterTopicsRequest;
import com.baidubce.services.rocketmq.model.request.ListClustersRequest;
import com.baidubce.services.rocketmq.model.request.ListConsumeStatesRequest;
import com.baidubce.services.rocketmq.model.request.ListConsumerClientsRequest;
import com.baidubce.services.rocketmq.model.request.ListConsumerGroupSubscriptionsRequest;
import com.baidubce.services.rocketmq.model.request.ListConsumerGroupUsersRequest;
import com.baidubce.services.rocketmq.model.request.ListTopicQueuesRequest;
import com.baidubce.services.rocketmq.model.request.ListTopicSubscriptionsRequest;
import com.baidubce.services.rocketmq.model.request.ListTopicUsersRequest;
import com.baidubce.services.rocketmq.model.request.ModifyBrokerNodeTypeRequest;
import com.baidubce.services.rocketmq.model.request.QueryMessageListRequest;
import com.baidubce.services.rocketmq.model.request.QueryMessageRequest;
import com.baidubce.services.rocketmq.model.request.ResetConsumeOffsetRequest;
import com.baidubce.services.rocketmq.model.request.RestartBrokerNodeRequest;
import com.baidubce.services.rocketmq.model.request.SendMessageRequest;
import com.baidubce.services.rocketmq.model.request.StartClusterRequest;
import com.baidubce.services.rocketmq.model.request.StopClusterRequest;
import com.baidubce.services.rocketmq.model.request.UpdateAclUserConfigRequest;
import com.baidubce.services.rocketmq.model.request.UpdateConsumerGroupRequest;
import com.baidubce.services.rocketmq.model.request.UpdateTopicRequest;
import com.baidubce.services.rocketmq.model.response.CreateAclUserResponse;
import com.baidubce.services.rocketmq.model.response.CreateClusterResponse;
import com.baidubce.services.rocketmq.model.response.CreateConsumerGroupResponse;
import com.baidubce.services.rocketmq.model.response.CreateTopicResponse;
import com.baidubce.services.rocketmq.model.response.DeleteAclUserConfigResponse;
import com.baidubce.services.rocketmq.model.response.DeleteClusterResponse;
import com.baidubce.services.rocketmq.model.response.DeleteConsumerGroupResponse;
import com.baidubce.services.rocketmq.model.response.DeleteTopicResponse;
import com.baidubce.services.rocketmq.model.response.ExpandBrokerResponse;
import com.baidubce.services.rocketmq.model.response.GetAclUserConfigResponse;
import com.baidubce.services.rocketmq.model.response.GetClusterAccessEndpointsResponse;
import com.baidubce.services.rocketmq.model.response.GetClusterResponse;
import com.baidubce.services.rocketmq.model.response.GetConsumerGroupResponse;
import com.baidubce.services.rocketmq.model.response.GetTopicResponse;
import com.baidubce.services.rocketmq.model.response.IncreaseStorageSizeResponse;
import com.baidubce.services.rocketmq.model.response.ListActionOperationsResponse;
import com.baidubce.services.rocketmq.model.response.ListClusterAclUserConfigsResponse;
import com.baidubce.services.rocketmq.model.response.ListClusterActionsResponse;
import com.baidubce.services.rocketmq.model.response.ListClusterBrokerNodesResponse;
import com.baidubce.services.rocketmq.model.response.ListClusterConsumerGroupsResponse;
import com.baidubce.services.rocketmq.model.response.ListClusterTopicsResponse;
import com.baidubce.services.rocketmq.model.response.ListClustersResponse;
import com.baidubce.services.rocketmq.model.response.ListConsumeStatesResponse;
import com.baidubce.services.rocketmq.model.response.ListConsumerClientsResponse;
import com.baidubce.services.rocketmq.model.response.ListConsumerGroupSubscriptionsResponse;
import com.baidubce.services.rocketmq.model.response.ListConsumerGroupUsersResponse;
import com.baidubce.services.rocketmq.model.response.ListTopicQueuesResponse;
import com.baidubce.services.rocketmq.model.response.ListTopicSubscriptionsResponse;
import com.baidubce.services.rocketmq.model.response.ListTopicUsersResponse;
import com.baidubce.services.rocketmq.model.response.ModifyBrokerNodeTypeResponse;
import com.baidubce.services.rocketmq.model.response.QueryMessageListResponse;
import com.baidubce.services.rocketmq.model.response.QueryMessageResponse;
import com.baidubce.services.rocketmq.model.response.ResetConsumeOffsetResponse;
import com.baidubce.services.rocketmq.model.response.RestartBrokerNodeResponse;
import com.baidubce.services.rocketmq.model.response.SendMessageResponse;
import com.baidubce.services.rocketmq.model.response.StartClusterResponse;
import com.baidubce.services.rocketmq.model.response.StopClusterResponse;
import com.baidubce.services.rocketmq.model.response.UpdateAclUserConfigResponse;
import com.baidubce.services.rocketmq.model.response.UpdateConsumerGroupResponse;
import com.baidubce.services.rocketmq.model.response.UpdateTopicResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.util.Validate;
import com.google.common.base.Strings;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;

public class RocketMQClient extends AbstractBceClient {

    /**
     * Constructs a new client to invoke service methods on bcc.
     */
    public RocketMQClient() {
        this(new BccClientConfiguration());
    }

    /**
     * Constructs a new bcc client using the client configuration to access bcc.
     *
     * @param configuration The bcc client configuration options controlling how this client
     *                            connects to bcc (e.g. proxy settings, retry counts, etc.).
     */
    public RocketMQClient(BceClientConfiguration configuration) {
        super(configuration, RocketMQConstants.ROCKETMQ_HANDLERS);
    }

    /**
     * Creates a new client to invoke service methods on bcc.
     * @param configuration The bcc client configuration options controlling how this client
     * @param responseHandlers The response handlers to be used when executing requests.
     * @param isHttpAsyncPutEnabled Whether PUT method use Async manner.
     */
    public RocketMQClient(
            BceClientConfiguration configuration,
            HttpResponseHandler[] responseHandlers,
            boolean isHttpAsyncPutEnabled) {
        super(configuration, responseHandlers, isHttpAsyncPutEnabled);
    }

    /**
     * Creates and initializes a new request object.
     *
     * @param bceRequest The original request, as created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param url The Request url.
     * @return A new request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     */
    private InternalRequest createRequest(AbstractBceRequest bceRequest,
                                          HttpMethodName httpMethod,
                                          String url) {

        URI uri = HttpUtils.appendUri(this.getEndpoint(), url);
        InternalRequest request = new InternalRequest(httpMethod, uri);

        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(RocketMQConstants.HEADERS_TO_SIGN);

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
            byte[] requestJson;
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
     * Lists clusters.
     * @param request The request object to be executed.
     * @return The response object containing the result of the request.
     */
    public ListClustersResponse listClusters(ListClustersRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);

        InternalRequest internalRequest = this.createRequest(request,
                HttpMethodName.GET, RocketMQConstants.CLUSTER_LIST);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter(RocketMQConstants.MARKER, request.getMarker());
        }
        if (request.getMaxKeys() <= 1000 && request.getMaxKeys() > 0) {
            internalRequest.addParameter(RocketMQConstants.MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getClusterName())) {
            internalRequest.addParameter(RocketMQConstants.CLUSTER_NAME, request.getClusterName());
        }
        if (!Strings.isNullOrEmpty(request.getState())) {
            internalRequest.addParameter(RocketMQConstants.STATE, request.getState());
        }
        if (!Strings.isNullOrEmpty(request.getArch())) {
            internalRequest.addParameter(RocketMQConstants.MODE, request.getArch());
        }
        if (!Strings.isNullOrEmpty(request.getRocketmqVersion())) {
            internalRequest.addParameter(RocketMQConstants.ROCKETMQ_VERSION, request.getRocketmqVersion());
        }
        if (!Strings.isNullOrEmpty(request.getPayment())) {
            internalRequest.addParameter(RocketMQConstants.PAYMENT, request.getPayment());
        }
        if (!Strings.isNullOrEmpty(request.getTagKey())) {
            if (request.getTagValue() != null) {
                internalRequest.addParameter(RocketMQConstants.TAG_KEY, request.getTagKey());
                internalRequest.addParameter(RocketMQConstants.TAG_VALUE, request.getTagValue());
            } else {
                throw new IllegalArgumentException(RocketMQConstants.TAG_VALUE_EMPTY_OR_NULL_ERROR);
            }
        } else if (request.getTagValue() != null) {
            throw new IllegalArgumentException(RocketMQConstants.TAG_KEY_EMPTY_OR_NULL_ERROR);
        }

        return invokeHttpClient(internalRequest, ListClustersResponse.class);
    }

    public CreateClusterResponse createCluster(CreateClusterRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getName(), RocketMQConstants.CLUSTER_NAME_EMPTY_OR_NULL_ERROR);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, RocketMQConstants.CLUSTER_CREATE);
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, CreateClusterResponse.class);
    }

    public GetClusterResponse getCluster(GetClusterRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CLUSTER_GET, request.getClusterId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, GetClusterResponse.class);
    }

    public DeleteClusterResponse deleteCluster(DeleteClusterRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CLUSTER_DELETE, request.getClusterId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, url);

        return invokeHttpClient(internalRequest, DeleteClusterResponse.class);
    }

    public StopClusterResponse stopCluster(StopClusterRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CLUSTER_ACTION, request.getClusterId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, url);
        internalRequest.addParameter(RocketMQConstants.ACTION, RocketMQConstants.ACTION_STOP);

        return invokeHttpClient(internalRequest, StopClusterResponse.class);
    }

    public StartClusterResponse startCluster(StartClusterRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CLUSTER_ACTION, request.getClusterId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, url);
        internalRequest.addParameter(RocketMQConstants.ACTION, RocketMQConstants.ACTION_START);

        return invokeHttpClient(internalRequest, StartClusterResponse.class);
    }

    public GetClusterAccessEndpointsResponse getAccessEndpoints(GetClusterAccessEndpointsRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CLUSTER_GET_ACCESS_ENDPOINTS, request.getClusterId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, GetClusterAccessEndpointsResponse.class);
    }

    public ListClusterBrokerNodesResponse listClusterBrokerNodes(ListClusterBrokerNodesRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CLUSTER_LIST_BROKER_NODES, request.getClusterId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, ListClusterBrokerNodesResponse.class);
    }

    public ListClusterTopicsResponse listClusterTopics(ListClusterTopicsRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CLUSTER_LIST_TOPICS, request.getClusterId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, ListClusterTopicsResponse.class);
    }

    public ListClusterConsumerGroupsResponse listClusterConsumerGroups(ListClusterConsumerGroupsRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CLUSTER_LIST_CONSUMER_GROUPS, request.getClusterId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, ListClusterConsumerGroupsResponse.class);
    }

    public ListClusterAclUserConfigsResponse listClusterAclUsers(ListClusterAclUserConfigsRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CLUSTER_LIST_ACL_USERS, request.getClusterId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, ListClusterAclUserConfigsResponse.class);
    }

    public ListClusterActionsResponse listClusterAclUsers(ListClusterActionsRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CLUSTER_LIST_ACTIONS, request.getClusterId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, ListClusterActionsResponse.class);
    }

    public IncreaseStorageSizeResponse increaseStorageSize(IncreaseStorageSizeRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CLUSTER_ACTION, request.getClusterId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, url);
        internalRequest.addParameter(RocketMQConstants.ACTION, RocketMQConstants.ACTION_INCREASE_STORAGE_SIZE);
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, IncreaseStorageSizeResponse.class);
    }

    public ExpandBrokerResponse expandBroker(ExpandBrokerRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CLUSTER_ACTION, request.getClusterId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, url);
        internalRequest.addParameter(RocketMQConstants.ACTION, RocketMQConstants.ACTION_EXPAND_BROKER);
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, ExpandBrokerResponse.class);
    }

    public ModifyBrokerNodeTypeResponse modifyBrokerNodeType(ModifyBrokerNodeTypeRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CLUSTER_ACTION, request.getClusterId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, url);
        internalRequest.addParameter(RocketMQConstants.ACTION, RocketMQConstants.ACTION_MODIFY_BROKER_NODE_TYPE);
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, ModifyBrokerNodeTypeResponse.class);
    }

    public RestartBrokerNodeResponse restartBrokerNode(RestartBrokerNodeRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CLUSTER_ACTION, request.getClusterId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, url);
        internalRequest.addParameter(RocketMQConstants.ACTION, RocketMQConstants.ACTION_RESTART_BROKER_NODE);
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, RestartBrokerNodeResponse.class);
    }

    public CreateTopicResponse createTopic(CreateTopicRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.TOPIC_CREATE, request.getClusterId(), request.getTopicName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, url);
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, CreateTopicResponse.class);
    }

    public GetTopicResponse getTopic(GetTopicRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getTopicName(), RocketMQConstants.TOPIC_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.TOPIC_GET, request.getClusterId(), request.getTopicName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, GetTopicResponse.class);
    }

    public UpdateTopicResponse updateTopic(UpdateTopicRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getTopicName(), RocketMQConstants.TOPIC_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.TOPIC_UPDATE, request.getClusterId(), request.getTopicName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, url);
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, UpdateTopicResponse.class);
    }

    public DeleteTopicResponse deleteTopic(DeleteTopicRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getTopicName(), RocketMQConstants.TOPIC_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.TOPIC_DELETE, request.getClusterId(), request.getTopicName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, url);

        return invokeHttpClient(internalRequest, DeleteTopicResponse.class);
    }

    public ListTopicQueuesResponse listTopicQueue(ListTopicQueuesRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getTopicName(), RocketMQConstants.TOPIC_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.TOPIC_LIST_QUEUES, request.getClusterId(), request.getTopicName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, ListTopicQueuesResponse.class);
    }

    public ListTopicSubscriptionsResponse listTopicSubscription(ListTopicSubscriptionsRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getTopicName(), RocketMQConstants.TOPIC_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.TOPIC_LIST_SUBSCRIPTIONS,
                request.getClusterId(), request.getTopicName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, ListTopicSubscriptionsResponse.class);
    }

    public QueryMessageListResponse queryMessageList(QueryMessageListRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getTopicName(), RocketMQConstants.TOPIC_NAME_EMPTY_OR_NULL_ERROR);
        if (request.getBeginTime() == null || request.getBeginTime() <= 0) {
            throw new IllegalArgumentException(RocketMQConstants.MESSAGE_BEGIN_TIMESTAMP_INVALID_ERROR);
        }
        if (request.getEndTime() == null || request.getEndTime() <= 0) {
            throw new IllegalArgumentException(RocketMQConstants.MESSAGE_END_TIMESTAMP_INVALID_ERROR);
        }

        String url = String.format(RocketMQConstants.MESSAGE_QUERY_LIST,
                request.getClusterId(), request.getTopicName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        internalRequest.addParameter(RocketMQConstants.BEGIN_TIME, request.getBeginTime().toString());
        internalRequest.addParameter(RocketMQConstants.END_TIME, request.getEndTime().toString());
        if (request.getMessageId() != null) {
            internalRequest.addParameter(RocketMQConstants.MESSAGE_ID, request.getMessageId());
        }
        if (request.getMessageKey() != null) {
            internalRequest.addParameter(RocketMQConstants.MESSAGE_KEY, request.getMessageKey());
        }

        QueryMessageListResponse.QueryMessageListInnerResponse innerResponse =
                invokeHttpClient(internalRequest, QueryMessageListResponse.QueryMessageListInnerResponse.class);

        QueryMessageListResponse response = new QueryMessageListResponse();
        response.setMessages(new ArrayList<>());
        response.setMessage(innerResponse.getMessage());
        response.setCode(innerResponse.getCode());
        response.setRequestId(innerResponse.getRequestId());
        response.setMarker(innerResponse.getMarker());
        response.setIsTruncated(innerResponse.getIsTruncated());
        response.setMaxKeys(innerResponse.getMaxKeys());
        response.setNextMarker(innerResponse.getNextMarker());

        innerResponse.getMessages().forEach(messageInner -> {
            Message message = new Message();
            message.setMsgId(messageInner.getMsgId());
            message.setTopicName(messageInner.getTopicName());
            message.setTag(messageInner.getTag());
            message.setKey(messageInner.getKey());
            message.setStoreTime(messageInner.getStoreTime());
            message.setBody(Base64.getDecoder().decode(messageInner.getBase64Body()));
            response.getMessages().add(message);
        });

        return response;
    }

    public ListTopicUsersResponse listTopicUsers(ListTopicUsersRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getTopicName(), RocketMQConstants.TOPIC_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.ACL_LIST_TOPIC_USER,
                request.getClusterId(), request.getTopicName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, ListTopicUsersResponse.class);
    }

    public CreateConsumerGroupResponse createConsumerGroup(CreateConsumerGroupRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getConsumerGroupName(),
                RocketMQConstants.CONSUMER_GROUP_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CONSUMER_GROUP_CREATE,
                request.getClusterId(), request.getConsumerGroupName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, url);
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, CreateConsumerGroupResponse.class);
    }

    public GetConsumerGroupResponse getConsumerGroup(GetConsumerGroupRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(),
                RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getConsumerGroupName(),
                RocketMQConstants.CONSUMER_GROUP_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CONSUMER_GROUP_GET,
                request.getClusterId(), request.getConsumerGroupName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, GetConsumerGroupResponse.class);
    }

    public UpdateConsumerGroupResponse updateConsumerGroup(UpdateConsumerGroupRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getConsumerGroupName(),
                RocketMQConstants.CONSUMER_GROUP_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CONSUMER_GROUP_UPDATE,
                request.getClusterId(), request.getConsumerGroupName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, url);
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, UpdateConsumerGroupResponse.class);
    }

    public DeleteConsumerGroupResponse deleteConsumerGroup(DeleteConsumerGroupRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getConsumerGroupName(),
                RocketMQConstants.CONSUMER_GROUP_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CONSUMER_GROUP_DELETE,
                request.getClusterId(), request.getConsumerGroupName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, url);

        return invokeHttpClient(internalRequest, DeleteConsumerGroupResponse.class);
    }

    public ResetConsumeOffsetResponse resetConsumeOffset(ResetConsumeOffsetRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getConsumerGroupName(),
                RocketMQConstants.CONSUMER_GROUP_NAME_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getTopicName(), RocketMQConstants.TOPIC_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CONSUMER_GROUP_RESET_CONSUME_OFFSET,
                request.getClusterId(), request.getConsumerGroupName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, url);
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, ResetConsumeOffsetResponse.class);
    }

    public ListConsumerClientsResponse listConsumerClients(ListConsumerClientsRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getConsumerGroupName(),
                RocketMQConstants.CONSUMER_GROUP_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CONSUMER_GROUP_LIST_CLIENTS,
                request.getClusterId(), request.getConsumerGroupName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, ListConsumerClientsResponse.class);
    }

    public ListConsumerGroupSubscriptionsResponse listConsumerGroupSubscriptions(
            ListConsumerGroupSubscriptionsRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getConsumerGroupName(),
                RocketMQConstants.CONSUMER_GROUP_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CONSUMER_GROUP_LIST_SUBSCRIPTIONS,
                request.getClusterId(), request.getConsumerGroupName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, ListConsumerGroupSubscriptionsResponse.class);
    }

    public ListConsumerGroupUsersResponse listConsumerGroupUsers(ListConsumerGroupUsersRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getConsumerGroupName(),
                RocketMQConstants.CONSUMER_GROUP_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.ACL_LIST_CONSUMER_GROUP_USER,
                request.getClusterId(), request.getConsumerGroupName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, ListConsumerGroupUsersResponse.class);
    }

    public ListConsumeStatesResponse listConsumeStates(ListConsumeStatesRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getConsumerGroupName(),
                RocketMQConstants.CONSUMER_GROUP_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.CONSUMER_GROUP_LIST_CONSUME_STATE,
                request.getClusterId(), request.getConsumerGroupName());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);
        internalRequest.addParameter(RocketMQConstants.TOPIC_NAME, request.getTopicName());
        if (request.getBrokerNames() != null && !request.getBrokerNames().isEmpty()) {
            internalRequest.addParameter(RocketMQConstants.BROKER_NAMES,
                    String.join(",", request.getBrokerNames()));
        }

        return invokeHttpClient(internalRequest, ListConsumeStatesResponse.class);
    }

    public CreateAclUserResponse createAclUser(CreateAclUserConfigRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getUsername(), RocketMQConstants.USER_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.ACL_USER_CREATE, request.getClusterId(), request.getUsername());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, url);
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, CreateAclUserResponse.class);
    }

    public GetAclUserConfigResponse getAclUser(GetAclUserRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getUsername(), RocketMQConstants.USER_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.ACL_USER_GET, request.getClusterId(), request.getUsername());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, GetAclUserConfigResponse.class);
    }

    public UpdateAclUserConfigResponse updateAclUser(UpdateAclUserConfigRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getUsername(), RocketMQConstants.USER_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.ACL_USER_UPDATE, request.getClusterId(), request.getUsername());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, url);
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, UpdateAclUserConfigResponse.class);
    }

    public DeleteAclUserConfigResponse deleteAclUser(DeleteAclUserConfigRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getUsername(), RocketMQConstants.USER_NAME_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.ACL_USER_DELETE, request.getClusterId(), request.getUsername());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, url);

        return invokeHttpClient(internalRequest, DeleteAclUserConfigResponse.class);
    }

    public SendMessageResponse sendMessage(SendMessageRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getTopicName(), RocketMQConstants.TOPIC_NAME_EMPTY_OR_NULL_ERROR);
        Validate.checkNotNull(request.getBody(), RocketMQConstants.MESSAGE_BODY_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.MESSAGE_SEND, request.getClusterId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, url);

        SendMessageRequest.SendMessageInnerRequest innerRequest = new SendMessageRequest.SendMessageInnerRequest();
        innerRequest.setTopicName(request.getTopicName());
        innerRequest.setKey(request.getKey());
        innerRequest.setTag(request.getTag());
        innerRequest.setBase64Body(Base64.getEncoder().encodeToString(request.getBody()));

        fillPayload(internalRequest, innerRequest);

        return invokeHttpClient(internalRequest, SendMessageResponse.class);
    }

    public QueryMessageResponse queryMessage(QueryMessageRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getMessageId(), RocketMQConstants.MESSAGE_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.MESSAGE_QUERY, request.getClusterId(), request.getMessageId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);
        if (request.getTopicName() != null) {
            internalRequest.addParameter(RocketMQConstants.TOPIC_NAME, request.getTopicName());
        }

        QueryMessageResponse.QueryMessageInnerResponse innerResponse =
                invokeHttpClient(internalRequest, QueryMessageResponse.QueryMessageInnerResponse.class);

        QueryMessageResponse response = new QueryMessageResponse();
        response.setCode(innerResponse.getCode());
        response.setMessage(innerResponse.getMessage());
        response.setRequestId(innerResponse.getRequestId());

        Message message = new Message();
        message.setMsgId(innerResponse.getMessageDetail().getMsgId());
        message.setTopicName(innerResponse.getMessageDetail().getTopicName());
        message.setTag(innerResponse.getMessageDetail().getTag());
        message.setKey(innerResponse.getMessageDetail().getKey());
        message.setStoreTime(innerResponse.getMessageDetail().getStoreTime());
        message.setBody(Base64.getDecoder().decode(innerResponse.getMessageDetail().getBase64Body()));

        response.setMessageDetail(message);

        return response;
    }

    public ListActionOperationsResponse listActionOperations(ListActionOperationsRequest request) {
        Validate.checkNotNull(request, RocketMQConstants.REQUEST_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getClusterId(), RocketMQConstants.CLUSTER_ID_EMPTY_OR_NULL_ERROR);
        Validate.checkStringNotEmpty(request.getActionId(), RocketMQConstants.ACTION_ID_EMPTY_OR_NULL_ERROR);

        String url = String.format(RocketMQConstants.ACTION_LIST_OPERATIONS,
                request.getClusterId(), request.getActionId());
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, url);

        return invokeHttpClient(internalRequest, ListActionOperationsResponse.class);
    }

}
