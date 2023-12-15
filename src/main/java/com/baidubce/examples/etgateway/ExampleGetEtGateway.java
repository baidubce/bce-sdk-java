package com.baidubce.examples.etgateway;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.etgateway.EtGatewayClient;
import com.baidubce.services.etgateway.EtGatewayClientConfiguration;
import com.baidubce.services.etgateway.model.GetEtGatewayResponse;

public class ExampleGetEtGateway {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EtGatewayClientConfiguration config = new EtGatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtGatewayClient etGatewayClient = new EtGatewayClient(config);  // 初始化Client

        String etGatewayId = "dcgw-9pwwq206xj58";    // 专线网关的id

        try {
            GetEtGatewayResponse getEtGatewayResponse = etGatewayClient.getEtGateway(etGatewayId);
            System.out.println("getEtGatewayResponse = " + getEtGatewayResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
