package com.baidubce.examples.ipv6gateway;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClient;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClientConfiguration;
import com.baidubce.services.ipv6Gateway.model.ListEgressOnlyRuleResponse;

public class ExampleListIpv6GatewayEgressOnlyRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        Ipv6GatewayClientConfiguration config = new Ipv6GatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        Ipv6GatewayClient ipv6GatewayClient = new Ipv6GatewayClient(config); // 初始化Client

        String ipv6GatewayId = "gw-5af4eb65"; // IPv6网关的Id

        try {
            ListEgressOnlyRuleResponse listEgressOnlyRuleResponse = ipv6GatewayClient.listEgressOnlyRule(ipv6GatewayId);
            System.out.println(listEgressOnlyRuleResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());

        }

    }
}
