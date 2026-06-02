package com.baidubce.examples.rocketmq;

import com.baidubce.BceClientConfiguration;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.rocketmq.RocketMQClient;
import com.baidubce.services.rocketmq.RocketMQClientConfiguration;
import com.baidubce.services.rocketmq.model.BrokerNodeType;
import com.baidubce.services.rocketmq.model.ClusterArch;
import com.baidubce.services.rocketmq.model.ClusterType;
import com.baidubce.services.rocketmq.model.ClusterVersion;
import com.baidubce.services.rocketmq.model.Encryption;
import com.baidubce.services.rocketmq.model.Payment;
import com.baidubce.services.rocketmq.model.ProvisionedCluster;
import com.baidubce.services.rocketmq.model.StorageType;
import com.baidubce.services.rocketmq.model.request.CreateClusterRequest;
import com.baidubce.services.rocketmq.model.response.CreateClusterResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class ExampleCreateCluster {
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

        ProvisionedCluster cluster = new ProvisionedCluster();
        cluster.setVersion(ClusterVersion.V4_9_8);
        cluster.setNumberOfBrokers(1);
        cluster.setArch(ClusterArch.MASTER_SLAVE);
        cluster.setAclEnabled(true);
        cluster.setEncryptionInTransit(Arrays.asList(Encryption.PERMISSIVE));
        cluster.setPayment(Payment.Postpaid);
        cluster.setZoneNames(Arrays.asList("cn-bj-e"));
        cluster.setVpcId("vpc-112233445566");
        cluster.setSubnetIds(Arrays.asList("sbn-112233445566"));
        cluster.setSecurityGroupIds(Arrays.asList("g-112233445566"));
        cluster.setNodeType(BrokerNodeType.ROCKETMQ_G5_2C_8G);
        cluster.setDeploySetEnabled(true);
        cluster.setStorageType(StorageType.ssd);
        cluster.setStorageSize(100);
        cluster.setPublicIpEnabled(false);

        CreateClusterRequest request = new CreateClusterRequest();
        request.setName("test-cluster");
        request.setType(ClusterType.PROVISIONED);
        request.setProvisioned(cluster);

        CreateClusterResponse response = client.createCluster(request);
        log.info("CreateCluster response: {}", response);
    }
}
