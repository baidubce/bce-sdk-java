/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.route;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.route.RouteClient;
import com.baidubce.services.route.RouteClientConfiguration;
import com.baidubce.services.route.model.CreateRouteRequest;
import com.baidubce.services.route.model.CreateRouteResponse;

public class ExampleCreateRouteRule {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        RouteClientConfiguration config = new RouteClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        RouteClient routeClient = new RouteClient(config); // 初始化VpcClient

        CreateRouteRequest createRouteRequest = new CreateRouteRequest();
        createRouteRequest.setRouteTableId("rt-s3qmp80vq02q"); // 路由表Id
        createRouteRequest.setSourceAddress("2400:da00:e003:9b00::/88"); // 路由源地址
        createRouteRequest.setDestinationAddress("::/0"); // 路由目的地址
        createRouteRequest.setNexthopType("enic"); // 路由下一跳
        createRouteRequest.setNexthopId("eni-wjz6683jaswn"); // 路由类型
        createRouteRequest.setDescription("desc"); // 路由描述
        createRouteRequest.setIpVersion(6); // 路由 ipversion 4或者6 默认是4

        try {
            CreateRouteResponse response = routeClient.createRoute(createRouteRequest);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
