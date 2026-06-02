/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.ipv6gateway;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClient;
import com.baidubce.services.ipv6Gateway.Ipv6GatewayClientConfiguration;
import com.baidubce.services.ipv6Gateway.model.UpdateDeleteProtectRequest;

/**
 * Example for updating delete protection switch of IPv6 Gateway.
 */
public class ExampleUpdateDeleteProtectIpv6Gateway {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        Ipv6GatewayClientConfiguration config = new Ipv6GatewayClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        Ipv6GatewayClient ipv6GatewayClient = new Ipv6GatewayClient(config); // 初始化Client

        String gatewayId = "gw-5af4eb65"; // IPv6网关的ID

        // 方式1: 使用简单方法
        try {
            // 开启释放保护
            ipv6GatewayClient.updateDeleteProtect(gatewayId, true);
            System.out.println("Successfully enabled delete protection for IPv6 Gateway: " + gatewayId);
        } catch (BceClientException e) {
            System.out.println("Failed to update delete protection: " + e.getMessage());
        }

        // 方式2: 使用Request对象
        try {
            UpdateDeleteProtectRequest request = new UpdateDeleteProtectRequest();
            request.setGatewayId(gatewayId);
            request.setDeleteProtect(false); // 关闭释放保护
            
            ipv6GatewayClient.updateDeleteProtect(request);
            System.out.println("Successfully disabled delete protection for IPv6 Gateway: " + gatewayId);
        } catch (BceClientException e) {
            System.out.println("Failed to update delete protection: " + e.getMessage());
        }
    }
}
