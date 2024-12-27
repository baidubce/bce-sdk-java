package com.baidubce.services.kafka;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientException;
import com.baidubce.auth.BceCredentials;
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
import com.baidubce.services.kafka.model.acl.CreateAclRequest;
import com.baidubce.services.kafka.model.acl.CreateAclResponse;
import com.baidubce.services.kafka.model.acl.DeleteAclRequest;
import com.baidubce.services.kafka.model.acl.DeleteAclResponse;
import com.baidubce.services.kafka.model.acl.ListAclRequest;
import com.baidubce.services.kafka.model.acl.ListAclResponse;
import com.baidubce.services.kafka.model.cluster.CreateClusterRequest;
import com.baidubce.services.kafka.model.cluster.CreateClusterResponse;
import com.baidubce.services.kafka.model.cluster.DecreaseBrokerCountRequest;
import com.baidubce.services.kafka.model.cluster.DecreaseBrokerCountResponse;
import com.baidubce.services.kafka.model.cluster.DeleteClusterRequest;
import com.baidubce.services.kafka.model.cluster.DeleteClusterResponse;
import com.baidubce.services.kafka.model.cluster.ExpandBrokerDiskCapacityRequest;
import com.baidubce.services.kafka.model.cluster.ExpandBrokerDiskCapacityResponse;
import com.baidubce.services.kafka.model.cluster.GetClusterAccessEndpointsRequest;
import com.baidubce.services.kafka.model.cluster.GetClusterAccessEndpointsResponse;
import com.baidubce.services.kafka.model.cluster.GetClusterConfigurationsRequest;
import com.baidubce.services.kafka.model.cluster.GetClusterConfigurationsResponse;
import com.baidubce.services.kafka.model.cluster.GetClusterCurrentControllerRequest;
import com.baidubce.services.kafka.model.cluster.GetClusterCurrentControllerResponse;
import com.baidubce.services.kafka.model.cluster.GetClusterDetailRequest;
import com.baidubce.services.kafka.model.cluster.GetClusterDetailResponse;
import com.baidubce.services.kafka.model.cluster.GetClusterHistoryControllerRequest;
import com.baidubce.services.kafka.model.cluster.GetClusterHistoryControllerResponse;
import com.baidubce.services.kafka.model.cluster.GetClusterNodesRequest;
import com.baidubce.services.kafka.model.cluster.GetClusterNodesResponse;
import com.baidubce.services.kafka.model.cluster.IncreaseBrokerCountRequest;
import com.baidubce.services.kafka.model.cluster.IncreaseBrokerCountResponse;
import com.baidubce.services.kafka.model.cluster.ListClustersRequest;
import com.baidubce.services.kafka.model.cluster.ListClustersResponse;
import com.baidubce.services.kafka.model.cluster.ResizeClusterEipBandwidthRequest;
import com.baidubce.services.kafka.model.cluster.ResizeClusterEipBandwidthResponse;
import com.baidubce.services.kafka.model.cluster.RestartBrokerRequest;
import com.baidubce.services.kafka.model.cluster.RestartBrokerResponse;
import com.baidubce.services.kafka.model.cluster.RestartClusterRequest;
import com.baidubce.services.kafka.model.cluster.RestartClusterResponse;
import com.baidubce.services.kafka.model.cluster.StartClusterRequest;
import com.baidubce.services.kafka.model.cluster.StartClusterResponse;
import com.baidubce.services.kafka.model.cluster.StopClusterRequest;
import com.baidubce.services.kafka.model.cluster.StopClusterResponse;
import com.baidubce.services.kafka.model.cluster.SwitchClusterEipRequest;
import com.baidubce.services.kafka.model.cluster.SwitchClusterEipResponse;
import com.baidubce.services.kafka.model.cluster.SwitchClusterIntranetIpRequest;
import com.baidubce.services.kafka.model.cluster.SwitchClusterIntranetIpResponse;
import com.baidubce.services.kafka.model.cluster.UpdateAccessConfigRequest;
import com.baidubce.services.kafka.model.cluster.UpdateAccessConfigResponse;
import com.baidubce.services.kafka.model.cluster.UpdateBrokerNodeTypeRequest;
import com.baidubce.services.kafka.model.cluster.UpdateBrokerNodeTypeResponse;
import com.baidubce.services.kafka.model.cluster.UpdateKafkaConfigRequest;
import com.baidubce.services.kafka.model.cluster.UpdateKafkaConfigResponse;
import com.baidubce.services.kafka.model.cluster.UpdateSecurityGroupRequest;
import com.baidubce.services.kafka.model.cluster.UpdateSecurityGroupResponse;
import com.baidubce.services.kafka.model.cluster.UpdateStoragePolicyRequest;
import com.baidubce.services.kafka.model.cluster.UpdateStoragePolicyResponse;
import com.baidubce.services.kafka.model.config.CreateClusterConfigRequest;
import com.baidubce.services.kafka.model.config.CreateClusterConfigResponse;
import com.baidubce.services.kafka.model.config.CreateClusterConfigRevisionRequest;
import com.baidubce.services.kafka.model.config.CreateClusterConfigRevisionResponse;
import com.baidubce.services.kafka.model.config.DeleteClusterConfigRequest;
import com.baidubce.services.kafka.model.config.DeleteClusterConfigResponse;
import com.baidubce.services.kafka.model.config.GetClusterConfigRequest;
import com.baidubce.services.kafka.model.config.GetClusterConfigResponse;
import com.baidubce.services.kafka.model.config.GetClusterConfigRevisionRequest;
import com.baidubce.services.kafka.model.config.GetClusterConfigRevisionResponse;
import com.baidubce.services.kafka.model.config.ListClusterConfigRevisionsRequest;
import com.baidubce.services.kafka.model.config.ListClusterConfigRevisionsResponse;
import com.baidubce.services.kafka.model.config.ListClusterConfigsRequest;
import com.baidubce.services.kafka.model.config.ListClusterConfigsResponse;
import com.baidubce.services.kafka.model.consumer.DeleteConsumerGroupRequest;
import com.baidubce.services.kafka.model.consumer.DeleteConsumerGroupResponse;
import com.baidubce.services.kafka.model.consumer.GetSubscribedTopicOverviewRequest;
import com.baidubce.services.kafka.model.consumer.GetSubscribedTopicOverviewResponse;
import com.baidubce.services.kafka.model.consumer.ListConsumerGroupRequest;
import com.baidubce.services.kafka.model.consumer.ListConsumerGroupResponse;
import com.baidubce.services.kafka.model.consumer.ListSubscribedTopicsRequest;
import com.baidubce.services.kafka.model.consumer.ListSubscribedTopicsResponse;
import com.baidubce.services.kafka.model.consumer.ResetConsumerGroupRequest;
import com.baidubce.services.kafka.model.consumer.ResetConsumerGroupResponse;
import com.baidubce.services.kafka.model.job.CancelJobRequest;
import com.baidubce.services.kafka.model.job.CancelJobResponse;
import com.baidubce.services.kafka.model.job.GetJobDetailRequest;
import com.baidubce.services.kafka.model.job.GetJobDetailResponse;
import com.baidubce.services.kafka.model.job.GetOperationDetailRequest;
import com.baidubce.services.kafka.model.job.GetOperationDetailResponse;
import com.baidubce.services.kafka.model.job.ListJobsRequest;
import com.baidubce.services.kafka.model.job.ListJobsResponse;
import com.baidubce.services.kafka.model.job.ResumeJobRequest;
import com.baidubce.services.kafka.model.job.ResumeJobResponse;
import com.baidubce.services.kafka.model.job.StartJobRequest;
import com.baidubce.services.kafka.model.job.StartJobResponse;
import com.baidubce.services.kafka.model.job.SuspendJobRequest;
import com.baidubce.services.kafka.model.job.SuspendJobResponse;
import com.baidubce.services.kafka.model.quota.CreateQuotaRequest;
import com.baidubce.services.kafka.model.quota.CreateQuotaResponse;
import com.baidubce.services.kafka.model.quota.DeleteQuotaRequest;
import com.baidubce.services.kafka.model.quota.DeleteQuotaResponse;
import com.baidubce.services.kafka.model.quota.ListQuotasRequest;
import com.baidubce.services.kafka.model.quota.ListQuotasResponse;
import com.baidubce.services.kafka.model.quota.UpdateQuotaRequest;
import com.baidubce.services.kafka.model.quota.UpdateQuotaResponse;
import com.baidubce.services.kafka.model.topic.CreateTopicRequest;
import com.baidubce.services.kafka.model.topic.CreateTopicResponse;
import com.baidubce.services.kafka.model.topic.DeleteTopicRequest;
import com.baidubce.services.kafka.model.topic.DeleteTopicResponse;
import com.baidubce.services.kafka.model.topic.GetSubscribedGroupDetailRequest;
import com.baidubce.services.kafka.model.topic.GetSubscribedGroupDetailResponse;
import com.baidubce.services.kafka.model.topic.GetSubscribedGroupOverviewRequest;
import com.baidubce.services.kafka.model.topic.GetSubscribedGroupOverviewResponse;
import com.baidubce.services.kafka.model.topic.GetTopicDetailRequest;
import com.baidubce.services.kafka.model.topic.GetTopicDetailResponse;
import com.baidubce.services.kafka.model.topic.GetTopicPartitionDetailRequest;
import com.baidubce.services.kafka.model.topic.GetTopicPartitionDetailResponse;
import com.baidubce.services.kafka.model.topic.GetTopicPartitionOverviewRequest;
import com.baidubce.services.kafka.model.topic.GetTopicPartitionOverviewResponse;
import com.baidubce.services.kafka.model.topic.ListSubscribedGroupsRequest;
import com.baidubce.services.kafka.model.topic.ListSubscribedGroupsResponse;
import com.baidubce.services.kafka.model.topic.ListTopicConfigOptionsRequest;
import com.baidubce.services.kafka.model.topic.ListTopicConfigOptionsResponse;
import com.baidubce.services.kafka.model.topic.ListTopicPartitionsRequest;
import com.baidubce.services.kafka.model.topic.ListTopicPartitionsResponse;
import com.baidubce.services.kafka.model.topic.ListTopicRequest;
import com.baidubce.services.kafka.model.topic.ListTopicResponse;
import com.baidubce.services.kafka.model.topic.QueryTopicMessagesByStartOffsetRequest;
import com.baidubce.services.kafka.model.topic.QueryTopicMessagesByStartOffsetResponse;
import com.baidubce.services.kafka.model.topic.QueryTopicMessagesByStartTimeRequest;
import com.baidubce.services.kafka.model.topic.QueryTopicMessagesByStartTimeResponse;
import com.baidubce.services.kafka.model.topic.SendTopicMessageRequest;
import com.baidubce.services.kafka.model.topic.SendTopicMessageResponse;
import com.baidubce.services.kafka.model.topic.UpdateTopicRequest;
import com.baidubce.services.kafka.model.topic.UpdateTopicResponse;
import com.baidubce.services.kafka.model.user.CreateUserRequest;
import com.baidubce.services.kafka.model.user.CreateUserResponse;
import com.baidubce.services.kafka.model.user.DeleteUserRequest;
import com.baidubce.services.kafka.model.user.DeleteUserResponse;
import com.baidubce.services.kafka.model.user.ListUserResponse;
import com.baidubce.services.kafka.model.user.ListUsersRequest;
import com.baidubce.services.kafka.model.user.ResetUserPasswordRequest;
import com.baidubce.services.kafka.model.user.ResetUserPasswordResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;

/**
 * Provides the client for accessing the Baidu Message Service(kafka).
 */
public class KafkaClient extends AbstractBceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaClient.class);

    private static final String VERSION = "v2";
    private static final String CLUSTERS_PREFIX = "clusters";
    private static final String TOPICS_PREFIX = "topics";
    private static final String CONSUMER_GROUPS_PREFIX = "consumer-groups";
    private static final String USERS_PREFIX = "users";
    private static final String ACLS_PREFIX = "acls";
    private static final String ACCESS_ENDPOINTS_PREFIX = "access-endpoints";
    private static final String NODES_PREFIX = "nodes";
    private static final String OFFSETS_PREFIX = "offsets";
    private static final String PARTITONS_PREFIX = "partitions";
    private static final String JOBS_PREFIX = "jobs";
    private static final String OPERATIONS_PREFIX = "operations";
    private static final String CONFIGURATIONS_PREFIX = "configurations";
    private static final String CONFIGS_PREFIX = "configs";
    private static final String REVISIONS_PREFIX = "revisions";
    private static final String MESSAGES_PREFIX = "messages";
    private static final String QUOTAS_PREFIX = "quotas";

    private static final String PAGE_NO = "pageNo";
    private static final String PAGE_SIZE = "pageSize";
    private static final String MARKER = "marker";
    private static final String MAX_KEYS = "maxKeys";
    private static final String STATE = "state";
    private static final String MODE = "mode";
    private static final String NAME = "name";
    private static final String KAFKA_VERSION = "kafkaVersion";
    private static final String PAYMENT = "payment";
    private static final String TAG_KEY = "tagKey";
    private static final String TAG_VALUE = "tagValue";
    private static final String START_TIME = "startTime";
    private static final String START_OFFSET = "startOffset";

    /**
     * Exception messages.
     */
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    private static final String CLUSTERID_MESSAGE_KEY = "clusterId";
    private static final String CLUSTER_NAME_MESSAGE_KEY = "clusterName";
    private static final String NAME_MESSAGE_KEY = "name";
    private static final String TOPIC_NAME_MESSAGE_KEY = "topicName";
    private static final String GROUP_NAME_MESSAGE_KEY = "groupName";
    private static final String CONFIG_NAME_MESSAGE_KEY = "configName";
    private static final String JOBID_MESSAGE_KEY = "jobId";
    private static final String OPERATIONID_MESSAGE_KEY = "operationId";
    private static final String CONFIGID_MESSAGE_KEY = "configId";
    private static final String REVISIONID_MESSAGE_KEY = "revisionId";
    private static final String NODEID_MESSAGE_KEY = "nodeId";
    private static final String PARTITIONID_MESSAGE_KEY = "partitionId";

    private static final String USERNAME_MESSAGE_KEY = "username";
    private static final String PASSWORD_MESSAGE_KEY = "password";
    private static final String PATTERN_TYPE_MESSAGE_KEY = "patternType";
    private static final String RESOURCE_TYPE_MESSAGE_KEY = "resourceType";
    private static final String RESOURCE_NAME_MESSAGE_KEY = "resourceName";
    private static final String OPERATION_MESSAGE_KEY = "operation";
    private static final String QUOTA_ENTITY_TYPE_KEY = "entityType";
    private static final String QUOTA_USERNAME_KEY = "username";
    private static final String QUOTA_USER_DEFAULT_KEY = "userDefault";
    private static final String QUOTA_CLIENT_ID_KEY = "clientId";
    private static final String QUOTA_CLIENT_DEFAULT_KEY = "clientDefault";



    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    /**
     * Responsible for handling httpResponses from all kafka service calls.
     */
    private static final HttpResponseHandler[] KAFKA_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on kafka.
     */
    public KafkaClient() {
        this(new KafkaClientConfiguration());
    }

    /**
     * Constructs a new kafka client using the client configuration to access kafka.
     *
     * @param clientConfiguration The bcc client configuration options controlling how this client
     *                            connects to bcc (e.g. proxy settings, retry counts, etc).
     */
    public KafkaClient(KafkaClientConfiguration clientConfiguration) {
        super(clientConfiguration, KAFKA_HANDLERS);
    }

    /**
     * Creates and initializes a new request object for the specified kafka resource. This method is responsible
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
     * The default method to generate the random String for clientToken if the optional parameter clientToken
     * is not specified by the user.
     *
     * The default algorithm is using {@link UUID} to generate a random UUID,
     *
     * @return An random String generated by {@link UUID}.
     */
    private String generateClientToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * The encryption implement for AES-128 algorithm for BCE password encryption.
     * Only the first 16 bytes of privateKey will be used to encrypt the content.
     *
     * See more detail on
     * <a href = "https://cloud.baidu.com/doc/Kafka/s/ijwvygfra#%E5%AF%86%E7%A0%81%E5%8A%A0%E5%AF%86%E4%BC%A0%E8%BE%93%E8%A7%84%E8%8C%83">
     * BCE API doc</a>
     *
     * @param content    The content String to encrypt.
     * @param privateKey The security key to encrypt.
     *                   Only the first 16 bytes of privateKey will be used to encrypt the content.
     * @return The encrypted string of the original content with AES-128 algorithm.
     * @throws GeneralSecurityException
     */
    private String aes128WithFirst16Char(String content, String privateKey) throws GeneralSecurityException {

        byte[] crypted = null;
        SecretKeySpec skey = new SecretKeySpec(privateKey.substring(0, 16).getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skey);
        crypted = cipher.doFinal(content.getBytes());
        return new String(Hex.encodeHex(crypted, false));
    }


    /** ========================================= cluster API ======================================================== */

    /**
     * 创建集群
     * @param request
     * @return
     */
    public CreateClusterResponse createCluster(CreateClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, CLUSTERS_PREFIX);
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateClusterResponse.class);
    }

    /**
     * 释放集群
     * @param request
     * @return
     */
    public DeleteClusterResponse deleteCluster(DeleteClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.DELETE, CLUSTERS_PREFIX, request.getClusterId());
        return invokeHttpClient(internalRequest, DeleteClusterResponse.class);
    }

    /**
     * 查询集群列表
     * @param request
     * @return
     */
    public ListClustersResponse listClusters(ListClustersRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, CLUSTERS_PREFIX);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() <= 1000 && request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getClusterName())) {
            internalRequest.addParameter(CLUSTER_NAME_MESSAGE_KEY, request.getClusterName());
        }
        if (!Strings.isNullOrEmpty(request.getState())) {
            internalRequest.addParameter(STATE, request.getState());
        }
        if (!Strings.isNullOrEmpty(request.getMode())) {
            internalRequest.addParameter(MODE, request.getMode());
        }
        if (!Strings.isNullOrEmpty(request.getKafkaVersion())) {
            internalRequest.addParameter(KAFKA_VERSION, request.getKafkaVersion());
        }
        if (!Strings.isNullOrEmpty(request.getPayment())) {
            internalRequest.addParameter(PAYMENT, request.getPayment());
        }
        if (!Strings.isNullOrEmpty(request.getTagKey())) {
            if (request.getTagValue() != null) {
                internalRequest.addParameter(TAG_KEY, request.getTagKey());
                internalRequest.addParameter(TAG_VALUE, request.getTagValue());
            } else {
                throw new IllegalArgumentException("Request tagValue should not be null.");
            }
        } else if (request.getTagValue() != null) {
            throw new IllegalArgumentException("Request tagKey should not be null or empty.");
        }
        return invokeHttpClient(internalRequest, ListClustersResponse.class);
    }

    /**
     * 查询集群详情
     * @param request
     * @return
     */
    public GetClusterDetailResponse getClusterDetail(GetClusterDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId());
        return invokeHttpClient(internalRequest, GetClusterDetailResponse.class);
    }

    /**
     * 查询集群接入点
     * @param request
     * @return
     */
    public GetClusterAccessEndpointsResponse getClusterAccessEndpoints(GetClusterAccessEndpointsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), ACCESS_ENDPOINTS_PREFIX);
        return invokeHttpClient(internalRequest, GetClusterAccessEndpointsResponse.class);
    }

    /**
     * 查询集群节点列表
     * @param request
     * @return
     */
    public GetClusterNodesResponse getClusterNodes(GetClusterNodesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), NODES_PREFIX);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() <= 1000 && request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getState())) {
            internalRequest.addParameter(STATE, request.getState());
        }
        return invokeHttpClient(internalRequest, GetClusterNodesResponse.class);
    }

    /**
     * 查询集群配置信息
     * @param request
     * @return
     */
    public GetClusterConfigurationsResponse getClusterConfigurations(GetClusterConfigurationsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), CONFIGURATIONS_PREFIX);
        return invokeHttpClient(internalRequest, GetClusterConfigurationsResponse.class);
    }

    /**
     * 增加节点数量
     * @param request
     * @return
     */
    public IncreaseBrokerCountResponse increaseBrokerCount(IncreaseBrokerCountRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), "increase-broker-count");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, IncreaseBrokerCountResponse.class);
    }

    /**
     * 减少节点数量
     * @param request
     * @return
     */
    public DecreaseBrokerCountResponse decreaseBrokerCount(DecreaseBrokerCountRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), "decrease-broker-count");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, DecreaseBrokerCountResponse.class);
    }

    /**
     * 变更节点机型
     * @param request
     * @return
     */
    public UpdateBrokerNodeTypeResponse updateBrokerNodeType(UpdateBrokerNodeTypeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), "update-broker-node-type");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBrokerNodeTypeResponse.class);
    }

    /**
     * 扩容磁盘容量
     * @param request
     * @return
     */
    public ExpandBrokerDiskCapacityResponse expandBrokerDiskCapacity(ExpandBrokerDiskCapacityRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), "expand-broker-disk-capacity");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ExpandBrokerDiskCapacityResponse.class);
    }

    /**
     * 变更访问配置
     * @param request
     * @return
     */
    public UpdateAccessConfigResponse updateAccessConfig(UpdateAccessConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), "update-access-config");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateAccessConfigResponse.class);
    }

    /**
     * 启动集群
     * @param request
     * @return
     */
    public StartClusterResponse startCluster(StartClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), "start");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, StartClusterResponse.class);
    }

    /**
     * 停止集群
     * @param request
     * @return
     */
    public StopClusterResponse stopCluster(StopClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), "stop");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, StopClusterResponse.class);
    }

    /**
     * 变更公网带宽
     * @param request
     * @return
     */
    public ResizeClusterEipBandwidthResponse resizeClusterEipBandwidth(ResizeClusterEipBandwidthRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), "eip-bandwidths/resize");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ResizeClusterEipBandwidthResponse.class);
    }

    /**
     * 集群公网开关
     * @param request
     * @return
     */
    public SwitchClusterEipResponse switchClusterEip(SwitchClusterEipRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), "eips/switch");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, SwitchClusterEipResponse.class);
    }

    /**
     * 变更磁盘阈值策略
     * @param request
     * @return
     */
    public UpdateStoragePolicyResponse updateStoragePolicy(UpdateStoragePolicyRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), "update-storage-policy");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateStoragePolicyResponse.class);
    }

    /**
     * 变更集群 kafka 配置
     * @param request
     * @return
     */
    public UpdateKafkaConfigResponse updateKafkaConfig(UpdateKafkaConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), "update-kafka-config");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateKafkaConfigResponse.class);
    }

    /**
     * 变更用户安全组
     * @param request
     * @return
     */
    public UpdateSecurityGroupResponse updateSecurityGroup(UpdateSecurityGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), "security-groups");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateSecurityGroupResponse.class);
    }

    /**
     * 集群产品间转储开关
     * @param request
     * @return
     */
    public SwitchClusterIntranetIpResponse switchClusterIntranetIp(SwitchClusterIntranetIpRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), "intranet-ips/switch");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, SwitchClusterIntranetIpResponse.class);
    }

    /**
     * 获取集群当前 Controller 信息
     * @param request
     * @return
     */
    public GetClusterCurrentControllerResponse getClusterCurrentController(GetClusterCurrentControllerRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), "controller");
        return invokeHttpClient(internalRequest, GetClusterCurrentControllerResponse.class);
    }

    /**
     * 获取集群历史 Controller 信息
     * @param request
     * @return
     */
    public GetClusterHistoryControllerResponse getClusterHistoryController(GetClusterHistoryControllerRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), "controller/history");
        return invokeHttpClient(internalRequest, GetClusterHistoryControllerResponse.class);
    }

    /**
     * 重启整个 Kafka 集群服务
     * @param request
     * @return
     */
    public RestartClusterResponse restartCluster(RestartClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), "restart");
        return invokeHttpClient(internalRequest, RestartClusterResponse.class);
    }

    /**
     * 重启指定 Broker 节点服务
     * @param request
     * @return
     */
    public RestartBrokerResponse restartBroker(RestartBrokerRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getNodeId(), checkEmptyExceptionMessageFormat(NODEID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), NODES_PREFIX,
                request.getNodeId(), "restart-broker");
        return invokeHttpClient(internalRequest, RestartBrokerResponse.class);
    }

    /** ========================================= config API ======================================================== */

    /**
     * 创建集群配置
     * @param request
     * @return
     */
    public CreateClusterConfigResponse createClusterConfig(CreateClusterConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, CONFIGS_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateClusterConfigResponse.class);
    }

    /**
     * 查询集群配置列表
     * @param request
     * @return
     */
    public ListClusterConfigsResponse listClusterConfigs(ListClusterConfigsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, CONFIGS_PREFIX);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() <= 1000 && request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getConfigName())) {
            internalRequest.addParameter(CONFIG_NAME_MESSAGE_KEY, request.getConfigName());
        }
        if (!Strings.isNullOrEmpty(request.getState())) {
            internalRequest.addParameter(STATE, request.getState());
        }
        return invokeHttpClient(internalRequest, ListClusterConfigsResponse.class);
    }

    /**
     * 删除集群配置
     * @param request
     * @return
     */
    public DeleteClusterConfigResponse deleteClusterConfig(DeleteClusterConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getConfigId(), checkEmptyExceptionMessageFormat(CONFIGID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.DELETE, CONFIGS_PREFIX, request.getConfigId());
        return invokeHttpClient(internalRequest, DeleteClusterConfigResponse.class);
    }

    /**
     * 查询集群配置详情
     * @param request
     * @return
     */
    public GetClusterConfigResponse getClusterConfig(GetClusterConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getConfigId(), checkEmptyExceptionMessageFormat(CONFIGID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CONFIGS_PREFIX, request.getConfigId());
        return invokeHttpClient(internalRequest, GetClusterConfigResponse.class);
    }

    /**
     * 新增集群配置版本
     * @param request
     * @return
     */
    public CreateClusterConfigRevisionResponse createClusterConfigRevision(CreateClusterConfigRevisionRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getConfigId(), checkEmptyExceptionMessageFormat(CONFIGID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, CONFIGS_PREFIX, request.getConfigId(), REVISIONS_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateClusterConfigRevisionResponse.class);
    }

    /**
     * 查询集群配置版本列表
     * @param request
     * @return
     */
    public ListClusterConfigRevisionsResponse listClusterConfigRevisions(ListClusterConfigRevisionsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getConfigId(), checkEmptyExceptionMessageFormat(CONFIGID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CONFIGS_PREFIX, request.getConfigId(), REVISIONS_PREFIX);
        if (!Strings.isNullOrEmpty(request.getState())) {
            internalRequest.addParameter(STATE, request.getState());
        }
        return invokeHttpClient(internalRequest, ListClusterConfigRevisionsResponse.class);
    }

    /**
     * 查询集群配置版本详情
     * @param request
     * @return
     */
    public GetClusterConfigRevisionResponse getClusterConfigRevision(GetClusterConfigRevisionRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getConfigId(), checkEmptyExceptionMessageFormat(CONFIGID_MESSAGE_KEY));
        checkNotNull(request.getRevisionId(), checkEmptyExceptionMessageFormat(REVISIONID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CONFIGS_PREFIX, request.getConfigId(),
                REVISIONS_PREFIX, String.valueOf(request.getRevisionId()));
        return invokeHttpClient(internalRequest, GetClusterConfigRevisionResponse.class);
    }

    /** ========================================= topic API ======================================================== */

    /**
     * 变更主题
     * @param request
     * @return
     */
    public UpdateTopicResponse updateTopic(UpdateTopicRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTopicName(), checkEmptyExceptionMessageFormat(TOPIC_NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), TOPICS_PREFIX, request.getTopicName());
        if (request.getPartitionNum() != null || MapUtils.isNotEmpty(request.getOtherConfigs())) {
            fillPayload(internalRequest, request);
            return invokeHttpClient(internalRequest, UpdateTopicResponse.class);
        } else {
            throw new IllegalArgumentException("Request fields should not be both null or empty.");
        }
    }

    /**
     * 查询主题订阅详情
     * @param request
     * @return
     */
    public GetSubscribedGroupDetailResponse getSubscribedGroupDetail(GetSubscribedGroupDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTopicName(), checkEmptyExceptionMessageFormat(TOPIC_NAME_MESSAGE_KEY));
        checkStringNotEmpty(request.getGroupName(), checkEmptyExceptionMessageFormat(GROUP_NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), TOPICS_PREFIX, request.getTopicName(),
                CONSUMER_GROUPS_PREFIX, request.getGroupName(), "subscribe-details");
        return invokeHttpClient(internalRequest, GetSubscribedGroupDetailResponse.class);
    }

    /**
     * 查询主题分区列表
     * @param request
     * @return
     */
    public ListTopicPartitionsResponse listTopicPartitions(ListTopicPartitionsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTopicName(), checkEmptyExceptionMessageFormat(TOPIC_NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), TOPICS_PREFIX, request.getTopicName(), "partitions", "statuses");
        internalRequest.addParameter(PAGE_NO, String.valueOf(request.getPageNo()));
        if (request.getPageSize() > 0) {
            internalRequest.addParameter(PAGE_SIZE, String.valueOf(request.getPageSize()));
        }
        return invokeHttpClient(internalRequest, ListTopicPartitionsResponse.class);
    }

    /**
     * 查询主题分区详情
     * @param request
     * @return
     */
    public GetTopicPartitionDetailResponse getTopicPartitionDetail(GetTopicPartitionDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTopicName(), checkEmptyExceptionMessageFormat(TOPIC_NAME_MESSAGE_KEY));
        checkStringNotEmpty(request.getPartitionId(), checkEmptyExceptionMessageFormat(PARTITIONID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), TOPICS_PREFIX, request.getTopicName(),
                "partitions", request.getPartitionId(), "statuses");
        return invokeHttpClient(internalRequest, GetTopicPartitionDetailResponse.class);
    }

    /**
     * 查询主题列表
     * @param request
     * @return
     */
    public ListTopicResponse listTopic(ListTopicRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), TOPICS_PREFIX);
        if (!Strings.isNullOrEmpty(request.getTopicName())) {
            internalRequest.addParameter(TOPIC_NAME_MESSAGE_KEY, request.getTopicName());
        }
        return invokeHttpClient(internalRequest, ListTopicResponse.class);
    }

    /**
     * 查询主题详情
     * @param request
     * @return
     */
    public GetTopicDetailResponse getTopicDetail(GetTopicDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTopicName(), checkEmptyExceptionMessageFormat(TOPIC_NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), TOPICS_PREFIX, request.getTopicName());
        return invokeHttpClient(internalRequest, GetTopicDetailResponse.class);
    }

    /**
     * 创建主题
     * @param request
     * @return
     */
    public CreateTopicResponse createTopic(CreateTopicRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, CLUSTERS_PREFIX, request.getClusterId(), TOPICS_PREFIX);
        checkStringNotEmpty(request.getTopicName(), checkEmptyExceptionMessageFormat(TOPIC_NAME_MESSAGE_KEY));
        checkNotNull(request.getPartitionNum(), "Request partitionNum should not be null.");
        checkNotNull(request.getReplicationFactor(), "Request replicationFactor should not be null.");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateTopicResponse.class);
    }

    /**
     * 获取订阅主题的消费组列表
     * @param request
     * @return
     */
    public ListSubscribedGroupsResponse listSubscribedGroups(ListSubscribedGroupsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTopicName(), checkEmptyExceptionMessageFormat(TOPIC_NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), TOPICS_PREFIX, request.getTopicName(), "consumer-groups");
        return invokeHttpClient(internalRequest, ListSubscribedGroupsResponse.class);
    }

    /**
     * 删除主题
     * @param request
     * @return
     */
    public DeleteTopicResponse deleteTopic(DeleteTopicRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTopicName(), checkEmptyExceptionMessageFormat(TOPIC_NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.DELETE, CLUSTERS_PREFIX, request.getClusterId(), TOPICS_PREFIX, request.getTopicName());
        return invokeHttpClient(internalRequest, DeleteTopicResponse.class);
    }

    /**
     * 查询主题分区信息概览
     * @param request
     * @return
     */
    public GetTopicPartitionOverviewResponse getTopicPartitionOverview(GetTopicPartitionOverviewRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTopicName(), checkEmptyExceptionMessageFormat(TOPIC_NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), TOPICS_PREFIX,
                request.getTopicName(), PARTITONS_PREFIX, "statuses/overview");
        return invokeHttpClient(internalRequest, GetTopicPartitionOverviewResponse.class);
    }

    /**
     * 查询主题订阅关系概览
     * @param request
     * @return
     */
    public GetSubscribedGroupOverviewResponse getSubscribedGroupOverview(GetSubscribedGroupOverviewRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTopicName(), checkEmptyExceptionMessageFormat(TOPIC_NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), TOPICS_PREFIX,
                request.getTopicName(), CONSUMER_GROUPS_PREFIX, "overview");
        return invokeHttpClient(internalRequest, GetSubscribedGroupOverviewResponse.class);
    }

    /**
     * 查询主题支持的参数信息
     * @return
     */
    public ListTopicConfigOptionsResponse listTopicConfigOptions() {
        ListTopicConfigOptionsRequest request = new ListTopicConfigOptionsRequest();
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, CLUSTERS_PREFIX,
                TOPICS_PREFIX, "config-options");
        return invokeHttpClient(internalRequest, ListTopicConfigOptionsResponse.class);
    }

    /**
     * 消息发送
     * @param request
     * @return
     */
    public SendTopicMessageResponse sendTopicMessage(SendTopicMessageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTopicName(), checkEmptyExceptionMessageFormat(TOPIC_NAME_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getValue())) {
            throw new IllegalArgumentException("Value cannot be null or empty.");
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, CLUSTERS_PREFIX,
                request.getClusterId(), TOPICS_PREFIX, request.getTopicName(), MESSAGES_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, SendTopicMessageResponse.class);
    }

    /**
     * 根据时间进行消息查询
     * @param request
     * @return
     */
    public QueryTopicMessagesByStartTimeResponse queryTopicMessagesByStartTime(QueryTopicMessagesByStartTimeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTopicName(), checkEmptyExceptionMessageFormat(TOPIC_NAME_MESSAGE_KEY));
        if (request.getStartTime() <= 0) {
            throw new IllegalArgumentException("Start time must be positive.");
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), TOPICS_PREFIX,
                request.getTopicName(), MESSAGES_PREFIX, "query-by-start-time");
        if (request.getPartitionId() != null) {
            internalRequest.addParameter(PARTITIONID_MESSAGE_KEY, String.valueOf(request.getPartitionId()));
        }
        internalRequest.addParameter("startTime", String.valueOf(request.getStartTime()));
        return invokeHttpClient(internalRequest, QueryTopicMessagesByStartTimeResponse.class);
    }

    /**
     * 根据位点进行消息查询
     * @param request
     * @return
     */
    public QueryTopicMessagesByStartOffsetResponse queryTopicMessagesByStartOffset(QueryTopicMessagesByStartOffsetRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getTopicName(), checkEmptyExceptionMessageFormat(TOPIC_NAME_MESSAGE_KEY));
        if (request.getPartitionId() < 0) {
            throw new IllegalArgumentException("Partition id must be positive.");
        }
        if (request.getStartOffset() < 0) {
            throw new IllegalArgumentException("Start offset must be positive.");
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), TOPICS_PREFIX,
                request.getTopicName(), MESSAGES_PREFIX, "query-by-start-offset");
        internalRequest.addParameter(PARTITIONID_MESSAGE_KEY, String.valueOf(request.getPartitionId()));
        internalRequest.addParameter(START_OFFSET, String.valueOf(request.getStartOffset()));
        return invokeHttpClient(internalRequest, QueryTopicMessagesByStartOffsetResponse.class);
    }

    /** ========================================= consumer API ==================================================== */

    /**
     * 查询消费组订阅的主题列表
     * @param request
     * @return
     */
    public ListSubscribedTopicsResponse listSubscribedTopics(ListSubscribedTopicsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getGroupName(), checkEmptyExceptionMessageFormat(GROUP_NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), CONSUMER_GROUPS_PREFIX, request.getGroupName(), TOPICS_PREFIX);
        return invokeHttpClient(internalRequest, ListSubscribedTopicsResponse.class);
    }

    /**
     * 查询消费组列表
     * @param request
     * @return
     */
    public ListConsumerGroupResponse listConsumerGroup(ListConsumerGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), CONSUMER_GROUPS_PREFIX);
        return invokeHttpClient(internalRequest, ListConsumerGroupResponse.class);
    }

    /**
     * 删除消费组
     * @param request
     * @return
     */
    public DeleteConsumerGroupResponse deleteConsumerGroup(DeleteConsumerGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getGroupName(), checkEmptyExceptionMessageFormat(GROUP_NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.DELETE, CLUSTERS_PREFIX, request.getClusterId(), CONSUMER_GROUPS_PREFIX, request.getGroupName());
        return invokeHttpClient(internalRequest, DeleteConsumerGroupResponse.class);
    }

    /**
     * 重置消费组位点
     * @param request
     * @return
     */
    public ResetConsumerGroupResponse resetConsumerGroup(ResetConsumerGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getGroupName(), checkEmptyExceptionMessageFormat(GROUP_NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, CLUSTERS_PREFIX, request.getClusterId(), CONSUMER_GROUPS_PREFIX, request.getGroupName(), OFFSETS_PREFIX);
        checkStringNotEmpty(request.getTopicName(), checkEmptyExceptionMessageFormat(TOPIC_NAME_MESSAGE_KEY));
        if (CollectionUtils.isEmpty(request.getPartitions())) {
            throw new IllegalArgumentException("Request partitions should not be both null or empty.");
        }
        checkStringNotEmpty(request.getResetStrategy(), checkEmptyExceptionMessageFormat("resetStrategy"));
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ResetConsumerGroupResponse.class);
    }

    /**
     * 查询消费组订阅关系概览
     * @param request
     * @return
     */
    public GetSubscribedTopicOverviewResponse getSubscribedTopicOverview(GetSubscribedTopicOverviewRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getGroupName(), checkEmptyExceptionMessageFormat(GROUP_NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), CONSUMER_GROUPS_PREFIX,
                request.getGroupName(), "topics/overview");
        return invokeHttpClient(internalRequest, GetSubscribedTopicOverviewResponse.class);
    }

    /** ========================================= user API ======================================================== */

    /**
     * 创建用户
     * Create a user with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     *
     * @param request The request containing all options for creating a user.
     * @return Username newly created.
     * @throws BceClientException
     */
    public CreateUserResponse createUser(CreateUserRequest request)
            throws BceClientException {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getUsername(), checkEmptyExceptionMessageFormat(USERNAME_MESSAGE_KEY));
        checkStringNotEmpty(request.getPassword(), checkEmptyExceptionMessageFormat(PASSWORD_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, CLUSTERS_PREFIX, request.getClusterId(), USERS_PREFIX);
        BceCredentials credentials = config.getCredentials();
        if (internalRequest.getCredentials() != null) {
            credentials = internalRequest.getCredentials();
        }
        try {
            request.setPassword(this.aes128WithFirst16Char(request.getPassword(), credentials.getSecretKey()));
        } catch (GeneralSecurityException e) {
            throw new BceClientException("Encryption procedure exception", e);
        }
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateUserResponse.class);
    }

    /**
     * 删除用户
     * Delete a user by username.
     *
     * @param request The request for deleting a user.
     * @return Username deleted.
     */
    public DeleteUserResponse deleteUser(DeleteUserRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getUsername(), checkEmptyExceptionMessageFormat(USERNAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.DELETE, CLUSTERS_PREFIX, request.getClusterId(), USERS_PREFIX, request.getUsername());
        return invokeHttpClient(internalRequest, DeleteUserResponse.class);
    }

    /**
     * 重置用户密码
     * Reset a password of a user.
     *
     * @param request The request for reseting a user's password.
     * @return Username reseted password.
     * @throws BceClientException
     */
    public ResetUserPasswordResponse resetUserPassword(ResetUserPasswordRequest request)
            throws BceClientException {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getUsername(), checkEmptyExceptionMessageFormat(USERNAME_MESSAGE_KEY));
        checkStringNotEmpty(request.getPassword(), checkEmptyExceptionMessageFormat(PASSWORD_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), USERS_PREFIX, request.getUsername());
        BceCredentials credentials = config.getCredentials();
        if (internalRequest.getCredentials() != null) {
            credentials = internalRequest.getCredentials();
        }
        try {
            request.setPassword(this.aes128WithFirst16Char(request.getPassword(), credentials.getSecretKey()));
        } catch (GeneralSecurityException e) {
            throw new BceClientException("Encryption procedure exception", e);
        }
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ResetUserPasswordResponse.class);
    }

    /**
     * 查询用户列表
     * Return a list of users owned by the cluster.
     *
     * @param clusterId The id of cluster.
     * @return The response containing a list of users owned by the cluster.
     */
    @Deprecated
    public ListUserResponse listUsers(String clusterId) {
        return listUsers(ListUsersRequest.builder().clusterId(clusterId).build());
    }

    /**
     * 查询用户列表
     * Return a list of users owned by the cluster.
     *
     * @param request The request for listing all of the available specifications of user.
     * @return The response containing a list of users owned by the cluster.
     */
    public ListUserResponse listUsers(ListUsersRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), USERS_PREFIX);
        return invokeHttpClient(internalRequest, ListUserResponse.class);
    }


    /** ========================================= acl API ======================================================== */

    /**
     * 创建权限
     * @param request
     * @return
     */
    public CreateAclResponse createAcl(CreateAclRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getUsername(), checkEmptyExceptionMessageFormat(USERNAME_MESSAGE_KEY));
        checkStringNotEmpty(request.getPatternType(), checkEmptyExceptionMessageFormat(PATTERN_TYPE_MESSAGE_KEY));
        checkStringNotEmpty(request.getResourceType(), checkEmptyExceptionMessageFormat(RESOURCE_TYPE_MESSAGE_KEY));
        checkStringNotEmpty(request.getResourceName(), checkEmptyExceptionMessageFormat(RESOURCE_NAME_MESSAGE_KEY));
        if (CollectionUtils.isEmpty(request.getOperations())) {
            throw new IllegalArgumentException(checkEmptyExceptionMessageFormat(OPERATION_MESSAGE_KEY));
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, CLUSTERS_PREFIX, request.getClusterId(), ACLS_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateAclResponse.class);
    }

    /**
     * 删除权限
     * @param request
     * @return
     */
    public DeleteAclResponse deleteAcl(DeleteAclRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.DELETE, CLUSTERS_PREFIX, request.getClusterId(), ACLS_PREFIX);
        checkStringNotEmpty(request.getUsername(), checkEmptyExceptionMessageFormat(USERNAME_MESSAGE_KEY));
        checkStringNotEmpty(request.getPatternType(), checkEmptyExceptionMessageFormat(PATTERN_TYPE_MESSAGE_KEY));
        checkStringNotEmpty(request.getResourceType(), checkEmptyExceptionMessageFormat(RESOURCE_TYPE_MESSAGE_KEY));
        checkStringNotEmpty(request.getResourceName(), checkEmptyExceptionMessageFormat(RESOURCE_NAME_MESSAGE_KEY));
        checkStringNotEmpty(request.getOperation(), checkEmptyExceptionMessageFormat(OPERATION_MESSAGE_KEY));
        internalRequest.addParameter(USERNAME_MESSAGE_KEY, request.getUsername());
        internalRequest.addParameter(PATTERN_TYPE_MESSAGE_KEY, request.getPatternType());
        internalRequest.addParameter(RESOURCE_TYPE_MESSAGE_KEY, request.getResourceType());
        internalRequest.addParameter(RESOURCE_NAME_MESSAGE_KEY, request.getResourceName());
        internalRequest.addParameter(OPERATION_MESSAGE_KEY, request.getOperation());

        return invokeHttpClient(internalRequest, DeleteAclResponse.class);
    }

    /**
     * 查询权限列表
     * @param clusterId
     * @return
     */
    public ListAclResponse listAcls(String clusterId) {
        return listAcls(ListAclRequest.builder().clusterId(clusterId).build());
    }

    /**
     * 查询权限列表
     * @param request
     * @return
     */
    public ListAclResponse listAcls(ListAclRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), ACLS_PREFIX);
        if (!Strings.isNullOrEmpty(request.getUsername())) {
            internalRequest.addParameter(USERNAME_MESSAGE_KEY, request.getUsername());
        }
        if (!Strings.isNullOrEmpty(request.getPatternType())) {
            internalRequest.addParameter(PATTERN_TYPE_MESSAGE_KEY, request.getPatternType());
        }
        if (!Strings.isNullOrEmpty(request.getResourceType())) {
            internalRequest.addParameter(RESOURCE_TYPE_MESSAGE_KEY, request.getResourceType());
        }
        if (!Strings.isNullOrEmpty(request.getResourceName())) {
            internalRequest.addParameter(RESOURCE_NAME_MESSAGE_KEY, request.getResourceName());
        }
        return invokeHttpClient(internalRequest, ListAclResponse.class);
    }

    /** ========================================= job API ======================================================== */

    /**
     * 查询作业列表
     * @param request
     * @return
     */
    public ListJobsResponse listJobs(ListJobsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), JOBS_PREFIX);
        if (!Strings.isNullOrEmpty(request.getName())) {
            internalRequest.addParameter(NAME, request.getName());
        }
        return invokeHttpClient(internalRequest, ListJobsResponse.class);
    }

    /**
     * 查询作业详情
     * @param request
     * @return
     */
    public GetJobDetailResponse getJob(GetJobDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getJobId(), checkEmptyExceptionMessageFormat(JOBID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), JOBS_PREFIX, request.getJobId());
        return invokeHttpClient(internalRequest, GetJobDetailResponse.class);
    }

    /**
     * 查询操作详情
     * @param request
     * @return
     */
    public GetOperationDetailResponse getOperation(GetOperationDetailRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getJobId(), checkEmptyExceptionMessageFormat(JOBID_MESSAGE_KEY));
        checkStringNotEmpty(request.getOperationId(), checkEmptyExceptionMessageFormat(OPERATIONID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), JOBS_PREFIX, request.getJobId(),
                OPERATIONS_PREFIX, request.getOperationId());
        return invokeHttpClient(internalRequest, GetOperationDetailResponse.class);
    }

    /**
     * 启动作业
     * @param request
     * @return
     */
    public StartJobResponse startJob(StartJobRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getJobId(), checkEmptyExceptionMessageFormat(JOBID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), JOBS_PREFIX, request.getJobId(), "start");
        return invokeHttpClient(internalRequest, StartJobResponse.class);
    }

    /**
     * 取消作业
     * @param request
     * @return
     */
    public CancelJobResponse cancelJob(CancelJobRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getJobId(), checkEmptyExceptionMessageFormat(JOBID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), JOBS_PREFIX, request.getJobId(), "cancel");
        return invokeHttpClient(internalRequest, CancelJobResponse.class);
    }

    /**
     * 暂停作业
     * @param request
     * @return
     */
    public SuspendJobResponse suspendJob(SuspendJobRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getJobId(), checkEmptyExceptionMessageFormat(JOBID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), JOBS_PREFIX, request.getJobId(), "suspend");
        return invokeHttpClient(internalRequest, SuspendJobResponse.class);
    }

    /**
     * 恢复作业
     * @param request
     * @return
     */
    public ResumeJobResponse resumeJob(ResumeJobRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        checkStringNotEmpty(request.getJobId(), checkEmptyExceptionMessageFormat(JOBID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), JOBS_PREFIX, request.getJobId(), "resume");
        return invokeHttpClient(internalRequest, ResumeJobResponse.class);
    }

    /** ========================================= job API ======================================================== */

    /**
     * 查询 Quota 列表
     * @param request
     * @return
     */
    public ListQuotasResponse listQuotas(ListQuotasRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, CLUSTERS_PREFIX, request.getClusterId(), QUOTAS_PREFIX);
        if (!Strings.isNullOrEmpty(request.getEntityType())) {
            internalRequest.addParameter(QUOTA_ENTITY_TYPE_KEY, request.getEntityType());
        }
        return invokeHttpClient(internalRequest, ListQuotasResponse.class);
    }

    /**
     * 创建 Quota
     * @param request
     * @return
     */
    public CreateQuotaResponse createQuota(CreateQuotaRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.POST, CLUSTERS_PREFIX, request.getClusterId(), QUOTAS_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateQuotaResponse.class);
    }

    /**
     * 更新 Quota
     * @param request
     * @return
     */
    public UpdateQuotaResponse updateQuota(UpdateQuotaRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, CLUSTERS_PREFIX, request.getClusterId(), QUOTAS_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateQuotaResponse.class);
    }

    /**
     * 删除 Quota
     * @param request
     * @return
     */
    public DeleteQuotaResponse deleteQuota(DeleteQuotaRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterId(), checkEmptyExceptionMessageFormat(CLUSTERID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.DELETE, CLUSTERS_PREFIX, request.getClusterId(), QUOTAS_PREFIX);
        if (!Strings.isNullOrEmpty(request.getUsername())) {
            internalRequest.addParameter(QUOTA_USERNAME_KEY, request.getUsername());
        }
        if (request.getUserDefault() != null) {
            internalRequest.addParameter(QUOTA_USER_DEFAULT_KEY, String.valueOf(request.getUserDefault()));
        }
        if (!Strings.isNullOrEmpty(request.getClientId())) {
            internalRequest.addParameter(QUOTA_CLIENT_ID_KEY, request.getClientId());
        }
        if (request.getClientDefault() != null) {
            internalRequest.addParameter(QUOTA_CLIENT_DEFAULT_KEY, String.valueOf(request.getClientDefault()));
        }
        return invokeHttpClient(internalRequest, DeleteQuotaResponse.class);
    }

}
