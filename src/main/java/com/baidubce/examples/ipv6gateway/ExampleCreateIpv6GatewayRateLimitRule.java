package com.baidubce.examples.ipv6gateway;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClient;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClientConfiguration;
import com.baidubce.services.ipv6Gateway.model.CreateRateLimitRuleRequest;
import com.baidubce.services.ipv6Gateway.model.RateLimitRuleResponse;

public class ExampleCreateIpv6GatewayRateLimitRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        Ipv6GatewayClientConfiguration config = new Ipv6GatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        Ipv6GatewayClient ipv6GatewayClient = new Ipv6GatewayClient(config); // 初始化Client

        CreateRateLimitRuleRequest request = new CreateRateLimitRuleRequest();
        request.setEgressBandwidthInMbps(10); // 限速策略的出向带宽
        request.setGatewayId(""); // IPv6网关的Id
        request.setIngressBandwidthInMbps(20); // 限速策略的入向带宽
        request.setIpv6Address("2400:da00:e003:400::1"); // Ipv6的地址

        try {
            RateLimitRuleResponse rateLimitRule = ipv6GatewayClient.createRateLimitRule(request);
            System.out.println(rateLimitRule);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());

        }

    }
}
