/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.route;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.route.RouteClient;
import com.baidubce.services.route.RouteClientConfiguration;

public class ExampleDeleteRouteRule {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        RouteClientConfiguration config = new RouteClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        RouteClient routeClient = new RouteClient(config); // 初始化VpcClient

        try {
            routeClient.deleteRouteRule("rr-vvhz3aiv0gz2");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
