package com.baidubce.examples.etgateway;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.etgateway.EtGatewayClient;
import com.baidubce.services.etgateway.EtGatewayClientConfiguration;
import com.baidubce.services.etgateway.model.BindEtChannelRequest;
import com.google.common.collect.Lists;

import java.util.UUID;

public class ExampleBindEtChannel {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EtGatewayClientConfiguration config = new EtGatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtGatewayClient etGatewayClient = new EtGatewayClient(config);  // 初始化Client

        BindEtChannelRequest bindEtChannelRequest = new BindEtChannelRequest();
        bindEtChannelRequest.setEtGatewayId("dcgw-9pwwq206xj58");    // 专线网关的id
        bindEtChannelRequest.setEtId("dcphy-ejtn7ffk9rnd");   // 绑定的物理专线的id，etId和channelId必须同时存在
        bindEtChannelRequest.setChannelId("dedicatedconn-xzxm902fvz9d");  // 绑定的专线通道的id

        // 专线网关的云端网络，用户可以选本vpc网段或自定义一个或多个网段
        bindEtChannelRequest.setLocalCidrs(Lists.newArrayList("192.168.0.0/20"));

        bindEtChannelRequest.setClientToken(UUID.randomUUID().toString());  // 幂等性Token

        try {
            etGatewayClient.bindEtChannel(bindEtChannelRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
