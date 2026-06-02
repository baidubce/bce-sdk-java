package com.baidubce.examples.rocketmq;

import com.baidubce.BceClientConfiguration;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.rocketmq.RocketMQClient;
import com.baidubce.services.rocketmq.RocketMQClientConfiguration;
import com.baidubce.services.rocketmq.model.request.GetClusterRequest;
import com.baidubce.services.rocketmq.model.request.ListClusterAclUserConfigsRequest;
import com.baidubce.services.rocketmq.model.request.ListClusterBrokerNodesRequest;
import com.baidubce.services.rocketmq.model.request.ListClusterConsumerGroupsRequest;
import com.baidubce.services.rocketmq.model.request.ListClusterTopicsRequest;
import com.baidubce.services.rocketmq.model.response.GetClusterResponse;
import com.baidubce.services.rocketmq.model.response.ListClusterAclUserConfigsResponse;
import com.baidubce.services.rocketmq.model.response.ListClusterBrokerNodesResponse;
import com.baidubce.services.rocketmq.model.response.ListClusterConsumerGroupsResponse;
import com.baidubce.services.rocketmq.model.response.ListClusterTopicsResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleGetCluster {
    public static void main(String[] args) {
        // 设置您的ak、sk和要访问的endpoint
        String endpoint = "rocketmq.bj.baidubce.com";
        String ak = "ak";
        String sk = "sk";
        String clusterId = "";

        // 设置默认配置
        BceClientConfiguration configuration = new RocketMQClientConfiguration()
                .withProtocol(Protocol.HTTP)
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(endpoint);

        // 创建rocketmq client
        RocketMQClient client = new RocketMQClient(configuration);

        getClusterDetail(client, clusterId);
        listClusterBrokerNodes(client, clusterId);
        listClusterTopics(client, clusterId);
        listClusterConsumerGroups(client, clusterId);
        listClusterAclUsers(client, clusterId);
    }

    static void getClusterDetail(RocketMQClient client, String clusterId) {
        GetClusterRequest request = new GetClusterRequest();
        request.setClusterId(clusterId);

        GetClusterResponse response = client.getCluster(request);
        log.info("GetClusters response: {}", response);
    }

    static void listClusterBrokerNodes(RocketMQClient client, String clusterId) {
        ListClusterBrokerNodesRequest request = new ListClusterBrokerNodesRequest();
        request.setClusterId(clusterId);

        ListClusterBrokerNodesResponse response = client.listClusterBrokerNodes(request);
        log.info("ListClusterBrokerNodes response: {}", response);
    }

    static void listClusterTopics(RocketMQClient client, String clusterId) {
        ListClusterTopicsRequest request = new ListClusterTopicsRequest();
        request.setClusterId(clusterId);

        ListClusterTopicsResponse response = client.listClusterTopics(request);
        log.info("ListClusterTopics response: {}", response);
    }

    static void listClusterConsumerGroups(RocketMQClient client, String clusterId) {
        ListClusterConsumerGroupsRequest request = new ListClusterConsumerGroupsRequest();
        request.setClusterId(clusterId);

        ListClusterConsumerGroupsResponse response = client.listClusterConsumerGroups(request);
        log.info("ListClusterConsumerGroups response: {}", response);
    }

    static void listClusterAclUsers(RocketMQClient client, String clusterId) {
        ListClusterAclUserConfigsRequest request = new ListClusterAclUserConfigsRequest();
        request.setClusterId(clusterId);

        ListClusterAclUserConfigsResponse response = client.listClusterAclUsers(request);
        log.info("ListClusterAclUsers response: {}", response);
    }
}
