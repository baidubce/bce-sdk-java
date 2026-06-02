package com.baidubce.examples.rocketmq;

import com.baidubce.BceClientConfiguration;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.rocketmq.RocketMQClient;
import com.baidubce.services.rocketmq.RocketMQClientConfiguration;
import com.baidubce.services.rocketmq.model.request.StartClusterRequest;
import com.baidubce.services.rocketmq.model.request.StopClusterRequest;
import com.baidubce.services.rocketmq.model.response.StartClusterResponse;
import com.baidubce.services.rocketmq.model.response.StopClusterResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleOperateCluster {
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

        // 启动集群
        startCluster(client, clusterId);

        // 停止集群
        //stopCluster(client, clusterId);
    }

    static void startCluster(RocketMQClient client, String clusterId) {
        // 创建请求
        StartClusterRequest request = new StartClusterRequest();
        request.setClusterId(clusterId);

        // 发送请求
        StartClusterResponse response = client.startCluster(request);
        log.info("StartCluster response: {}", response);
    }

    static void stopCluster(RocketMQClient client, String clusterId) {
        StopClusterRequest request = new StopClusterRequest();
        request.setClusterId(clusterId);

        StopClusterResponse response = client.stopCluster(request);
        log.info("StopCluster response: {}", response);
    }
}
