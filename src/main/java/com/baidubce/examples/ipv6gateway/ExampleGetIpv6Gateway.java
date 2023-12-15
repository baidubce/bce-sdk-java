package com.baidubce.examples.ipv6gateway;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClient;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClientConfiguration;
import com.baidubce.services.ipv6Gateway.model.Ipv6GatewayResponse;

public class ExampleGetIpv6Gateway {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        Ipv6GatewayClientConfiguration config = new Ipv6GatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        Ipv6GatewayClient ipv6GatewayClient = new Ipv6GatewayClient(config); // 初始化Client

        String vpcId = "vpcId=g7cufa91auif"; // IPv6网关所属的vpc的Id

        try {
            Ipv6GatewayResponse ipv6Gateway = ipv6GatewayClient.getIpv6Gateway(vpcId);
            System.out.println(ipv6Gateway);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());

        }

    }
}
