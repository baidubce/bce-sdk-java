package com.baidubce.examples.etgateway;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.etgateway.EtGatewayClient;
import com.baidubce.services.etgateway.EtGatewayClientConfiguration;

import java.util.UUID;

public class ExampleDeleteEtGateway {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EtGatewayClientConfiguration config = new EtGatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtGatewayClient etGatewayClient = new EtGatewayClient(config);  // 初始化Client

        String etGatewayId = "dcgw-9pwwq206xj58";    // 需要释放的专线网关的id

        // 方式一
        try {
            // 没设置幂等，自动加上幂等参数
            etGatewayClient.deleteEtGateway(etGatewayId);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

        // 方式二
        String clientToken = UUID.randomUUID().toString();  // 幂等性Token
        try {
            etGatewayClient.deleteEtGateway(etGatewayId, clientToken);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
