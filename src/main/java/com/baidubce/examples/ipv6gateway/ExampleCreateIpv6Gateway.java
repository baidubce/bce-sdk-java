package com.baidubce.examples.ipv6gateway;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClient;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClientConfiguration;

public class ExampleCreateIpv6Gateway {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        Ipv6GatewayClientConfiguration config = new Ipv6GatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        Ipv6GatewayClient ipv6GatewayClient = new Ipv6GatewayClient(config); // 初始化Client

        String vpcId = "vpc-g7cufa91auif"; // vpc的ID
        String name = "testIpv6"; // IPv6网关的名称
        Integer bandwidthInMbps = 20; // 带宽

        // 计费信息，目前只支持后付费，默认封装

        try {
            ipv6GatewayClient.createIpv6Gateway(vpcId, name, bandwidthInMbps);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());

        }
    }
}
