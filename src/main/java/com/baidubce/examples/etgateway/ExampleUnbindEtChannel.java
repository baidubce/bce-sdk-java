package com.baidubce.examples.etgateway;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.etgateway.EtGatewayClient;
import com.baidubce.services.etgateway.EtGatewayClientConfiguration;
import com.baidubce.services.etgateway.model.UnbindEtChannelRequest;

import java.util.UUID;

public class ExampleUnbindEtChannel {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EtGatewayClientConfiguration config = new EtGatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtGatewayClient etGatewayClient = new EtGatewayClient(config);  // 初始化Client

        UnbindEtChannelRequest unbindEtChannelRequest = new UnbindEtChannelRequest();
        unbindEtChannelRequest.setEtGatewayId("dcgw-9pwwq206xj58");  // 专线网关的id
        unbindEtChannelRequest.setClientToken(UUID.randomUUID().toString());    // 幂等性Token

        try {
            etGatewayClient.unbindEtChannel(unbindEtChannelRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
