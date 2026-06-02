package com.baidubce.examples.rocketmq;

import com.baidubce.BceClientConfiguration;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.rocketmq.RocketMQClient;
import com.baidubce.services.rocketmq.RocketMQClientConfiguration;
import com.baidubce.services.rocketmq.model.request.ListClustersRequest;
import com.baidubce.services.rocketmq.model.response.ListClustersResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleListClusters {
    public static void main(String[] args) {
        // 设置您的ak、sk和要访问的endpoint
        String endpoint = "rocketmq.bj.baidubce.com";
        String ak = "ak";
        String sk = "sk";

        // 设置默认配置
        BceClientConfiguration configuration = new RocketMQClientConfiguration()
                .withProtocol(Protocol.HTTP)
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(endpoint);

        // 创建rocketmq client
        RocketMQClient client = new RocketMQClient(configuration);

        ListClustersRequest request = new ListClustersRequest();

        ListClustersResponse response = client.listClusters(request);
        // System.out.println(response);
        log.info("ListClusters response: {}", response);
    }
}
