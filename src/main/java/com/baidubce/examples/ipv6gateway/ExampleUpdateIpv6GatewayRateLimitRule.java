package com.baidubce.examples.ipv6gateway;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClient;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClientConfiguration;
import com.baidubce.services.ipv6Gateway.model.UpdateRateLimitRuleRequest;

public class ExampleUpdateIpv6GatewayRateLimitRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        Ipv6GatewayClientConfiguration config = new Ipv6GatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        Ipv6GatewayClient ipv6GatewayClient = new Ipv6GatewayClient(config); // 初始化Client

        UpdateRateLimitRuleRequest updateRateLimitRule = new UpdateRateLimitRuleRequest();
        updateRateLimitRule.setGatewayId("gw-5af4eb65"); // IPv6网关的Id
        updateRateLimitRule.setEgressBandwidthInMbps(8); // 限速策略的出向带宽
        updateRateLimitRule.setIngressBandwidthInMbps(6); // 限速策略的入向带宽
        updateRateLimitRule.setRateLimitRuleId("ipv6_qos-0b56ec38"); // 限速策略的Id

        try {
            ipv6GatewayClient.updateRateLimitRule(updateRateLimitRule);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
