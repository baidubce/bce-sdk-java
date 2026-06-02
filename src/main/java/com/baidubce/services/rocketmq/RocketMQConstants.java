package com.baidubce.services.rocketmq;

import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class RocketMQConstants {
    static final String ROCKETMQ_BROKER_NAME = "rocketmq-broker";

    /**
     * Generate signature with specified headers.
     */
    static final Set<String> HEADERS_TO_SIGN = new HashSet<>(Arrays.asList("host", "x-bce-date"));

    /**
     * Responsible for handling httpResponses from all kafka service calls.
     */
    static final HttpResponseHandler[] ROCKETMQ_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
    * API Version
    * */
    static final String API_VERSION_V1 = "v1";

    /**
    * Create Cluster, POST
    * */
    static final String CLUSTER_CREATE = "/" + API_VERSION_V1 + "/clusters";

    /**
     * List Clusters, GET
     */
    static final String CLUSTER_LIST = "/" + API_VERSION_V1 + "/clusters";

    /**
     * Get Cluster, GET
     * path param: clusterId
     */
    static final String CLUSTER_GET = "/" + API_VERSION_V1 + "/clusters/%s";

    /**
     * Delete Cluster, DELETE
     * path param: clusterId
     */
    static final String CLUSTER_DELETE = "/" + API_VERSION_V1 + "/clusters/%s";

    /**
     * Operate Cluster, PUT
     * path param: clusterId
     */
    static final String CLUSTER_ACTION = "/" + API_VERSION_V1 + "/clusters/%s";

    /**
     * Get Cluster Access Endpoints, GET
     * path param: clusterId
     */
    static final String CLUSTER_GET_ACCESS_ENDPOINTS = "/" + API_VERSION_V1 + "/clusters/%s/access-endpoints";

    /**
     * List Broker Nodes, GET
     * path param: clusterId
     */
    static final String CLUSTER_LIST_BROKER_NODES = "/" + API_VERSION_V1 + "/clusters/%s/broker-nodes";

    /**
     * List Topics, GET
     * path param: clusterId
     */
    static final String CLUSTER_LIST_TOPICS = "/" + API_VERSION_V1 + "/clusters/%s/topics";

    /**
     * List Consumer Groups, GET
     * path param: clusterId
     */
    static final String CLUSTER_LIST_CONSUMER_GROUPS = "/" + API_VERSION_V1 + "/clusters/%s/consumer-groups";

    /**
     * List ACL Users, GET
     * path param: clusterId
     */
    static final String CLUSTER_LIST_ACL_USERS = "/" + API_VERSION_V1 + "/clusters/%s/users";

    /**
     * List Cluster Actions, GET
     * path param: clusterId
     */
    static final String CLUSTER_LIST_ACTIONS = "/" + API_VERSION_V1 + "/clusters/%s/actions";

    /**
     * Topic manage
     * path param: clusterId、topicName
     */
    static final String TOPIC_CREATE = "/" + API_VERSION_V1 + "/clusters/%s/topics/%s";
    static final String TOPIC_GET = "/" + API_VERSION_V1 + "/clusters/%s/topics/%s";
    static final String TOPIC_UPDATE = "/" + API_VERSION_V1 + "/clusters/%s/topics/%s";
    static final String TOPIC_DELETE= "/" + API_VERSION_V1 + "/clusters/%s/topics/%s";

    /**
     * List Queues, GET
     * path param: clusterId、topicName
     */
    static final String TOPIC_LIST_QUEUES = "/" + API_VERSION_V1 + "/clusters/%s/topics/%s/queues";

    /**
     * List Subscriptions, GET
     * path param: clusterId、topicName
     */
    static final String TOPIC_LIST_SUBSCRIPTIONS = "/" + API_VERSION_V1 + "/clusters/%s/topics/%s/subscriptions";

    /**
     * Consumer Group manage
     * path param: clusterId、consumerGroupName
     */
    static final String CONSUMER_GROUP_CREATE = "/" + API_VERSION_V1 + "/clusters/%s/consumer-groups/%s";
    static final String CONSUMER_GROUP_GET = "/" + API_VERSION_V1 + "/clusters/%s/consumer-groups/%s";
    static final String CONSUMER_GROUP_UPDATE = "/" + API_VERSION_V1 + "/clusters/%s/consumer-groups/%s";
    static final String CONSUMER_GROUP_DELETE = "/" + API_VERSION_V1 + "/clusters/%s/consumer-groups/%s";

    /**
     * List Subscriptions, GET
     * path param: clusterId、consumerGroupName
     */
    static final String CONSUMER_GROUP_LIST_SUBSCRIPTIONS = "/" + API_VERSION_V1
            + "/clusters/%s/consumer-groups/%s/subscriptions";

    /**
     * List Clients, GET
     * path param: clusterId、consumerGroupName
     */
    static final String CONSUMER_GROUP_LIST_CLIENTS = "/" + API_VERSION_V1
            + "/clusters/%s/consumer-groups/%s/clients";

    static final String CONSUMER_GROUP_LIST_CONSUME_STATE = "/" + API_VERSION_V1
            + "/clusters/%s/consumer-groups/%s/offset";

    /**
     * Reset Consumer Offset, PUT
     * path param: clusterId、consumerGroupName
     */
    static final String CONSUMER_GROUP_RESET_CONSUME_OFFSET = "/" + API_VERSION_V1
            + "/clusters/%s/consumer-groups/%s/offset";

    /**
     * ACL User manage
     * path param: clusterId、userName
     */
    static final String ACL_USER_CREATE = "/" + API_VERSION_V1 + "/clusters/%s/users/%s";
    static final String ACL_USER_GET = "/" + API_VERSION_V1 + "/clusters/%s/users/%s";
    static final String ACL_USER_UPDATE = "/" + API_VERSION_V1 + "/clusters/%s/users/%s";
    static final String ACL_USER_DELETE = "/" + API_VERSION_V1 + "/clusters/%s/users/%s";

    static final String ACL_LIST_TOPIC_USER = "/" + API_VERSION_V1 + "/clusters/%s/topics/%s/users";
    static final String ACL_LIST_CONSUMER_GROUP_USER = "/" + API_VERSION_V1 + "/clusters/%s/consumer-groups/%s/users";

    /**
     * Message Query, GET
     * path param: clusterId、messageId
     */
    static final String MESSAGE_QUERY = "/" + API_VERSION_V1 + "/clusters/%s/messages/%s";
    static final String MESSAGE_QUERY_LIST = "/" + API_VERSION_V1 + "/clusters/%s/topics/%s/messages";
    static final String MESSAGE_SEND = "/" + API_VERSION_V1 + "/clusters/%s/messages";

    /**
     * List Action Operations, GET
     * path param: clusterId、actionId
     */
    static final String ACTION_LIST_OPERATIONS = "/" + API_VERSION_V1 + "/clusters/%s/actions/%s/operations";

    /**
     * Param name
     * */
    static final String CLUSTER_NAME = "clusterName";
    static final String PAGE_NO = "pageNo";
    static final String PAGE_SIZE = "pageSize";
    static final String MARKER = "marker";
    static final String MAX_KEYS = "maxKeys";
    static final String STATE = "state";
    static final String MODE = "mode";
    static final String NAME = "name";
    static final String ROCKETMQ_VERSION = "rocketmqVersion";
    static final String PAYMENT = "payment";
    static final String TAG_KEY = "tagKey";
    static final String TAG_VALUE = "tagValue";
    static final String BEGIN_TIME = "beginTime";
    static final String END_TIME = "endTime";
    static final String START_OFFSET = "startOffset";

    static final String ACTION = "action";
    static final String ACTION_START = "startCluster";
    static final String ACTION_STOP = "stopCluster";
    static final String ACTION_INCREASE_STORAGE_SIZE = "increaseStorageSize";
    static final String ACTION_EXPAND_BROKER = "expandBroker";
    static final String ACTION_MODIFY_BROKER_NODE_TYPE = "modifyBrokerNodeType";
    static final String ACTION_RESTART_BROKER_NODE = "restartBrokerNode";

    static final String TOPIC_NAME = "topicName";

    static final String BROKER_NAMES = "brokerNames";

    static final String MESSAGE_ID = "messageId";
    static final String MESSAGE_KEY = "messageKey";

    /**
     * Exception messages.
     */
    static final String REQUEST_NULL_ERROR = "request should not be null.";
    static final String CLUSTER_NAME_EMPTY_OR_NULL_ERROR = "clusterName should not be null or empty.";
    static final String CLUSTER_ID_EMPTY_OR_NULL_ERROR = "clusterId should not be null or empty.";
    static final String TOPIC_NAME_EMPTY_OR_NULL_ERROR = "topicName should not be null or empty.";
    static final String CONSUMER_GROUP_NAME_EMPTY_OR_NULL_ERROR = "consumerGroupName should not be null or empty.";
    static final String USER_NAME_EMPTY_OR_NULL_ERROR = "username should not be null or empty.";
    static final String MESSAGE_ID_EMPTY_OR_NULL_ERROR = "messageId should not be null or empty.";
    static final String ACTION_ID_EMPTY_OR_NULL_ERROR = "actionId should not be null or empty.";
    static final String TAG_KEY_EMPTY_OR_NULL_ERROR = "tagKey should not be null or empty.";
    static final String TAG_VALUE_EMPTY_OR_NULL_ERROR = "tagValue should not be null or empty.";
    static final String MESSAGE_BODY_EMPTY_OR_NULL_ERROR = "message body should not be null or empty.";
    static final String MESSAGE_BEGIN_TIMESTAMP_INVALID_ERROR = "message begin timestamp should not be null or invalid.";
    static final String MESSAGE_END_TIMESTAMP_INVALID_ERROR = "message end timestamp should not be null or invalid.";
}
