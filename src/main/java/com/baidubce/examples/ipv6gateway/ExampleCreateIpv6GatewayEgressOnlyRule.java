package com.baidubce.examples.ipv6gateway;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClient;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClientConfiguration;
import com.baidubce.services.ipv6Gateway.model.CreateEgressOnlyRuleRequest;
import com.baidubce.services.ipv6Gateway.model.CreateEgressOnlyRuleResponse;

public class ExampleCreateIpv6GatewayEgressOnlyRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        Ipv6GatewayClientConfiguration config = new Ipv6GatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        Ipv6GatewayClient ipv6GatewayClient = new Ipv6GatewayClient(config); // 初始化Client

        CreateEgressOnlyRuleRequest request = new CreateEgressOnlyRuleRequest();
        request.setGatewayId("gw-5af4eb65"); // IPv6网关的Id
        request.setCidr("2400:da00:e003:400::2/128"); // 只出不进策略的CIDR
        try {
            CreateEgressOnlyRuleResponse egressOnlyRule = ipv6GatewayClient.createEgressOnlyRule(request);
            System.out.println(egressOnlyRule);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());

        }

    }
}
