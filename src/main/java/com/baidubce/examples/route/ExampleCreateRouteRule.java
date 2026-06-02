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
import com.baidubce.services.route.model.NextHop;

import java.util.Arrays;

public class ExampleCreateRouteRule {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        RouteClientConfiguration config = new RouteClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        RouteClient routeClient = new RouteClient(config); // 初始化VpcClient

        // 创建单线路由规则
        CreateRouteRequest request1 = new CreateRouteRequest();
        request1.setRouteTableId("rt-q1zg3i8mx8p6");
        request1.setSourceAddress("192.168.0.0/20");
        request1.setDestinationAddress("0.0.0.0/0");
        request1.setNexthopId("nat-bdidwhwfwc6p");
        request1.setNexthopType("nat");
        request1.setDescription("单线NAT路由规则"); // 可选

        try {
            CreateRouteResponse response1 = routeClient.createRoute(request1);
            System.out.println(response1);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

        // 创建主备路由规则
        NextHop masterHop = new NextHop()
                .withNexthopId("dcgw-xxx")
                .withNexthopType("dcGateway")
                .withPathType("ha:active");

        NextHop standbyHop = new NextHop()
                .withNexthopId("dcgw-yyy")
                .withNexthopType("dcGateway")
                .withPathType("ha:standby");

        CreateRouteRequest request2 = new CreateRouteRequest();
        request2.setRouteTableId("rt-q1zg3i8mx8p6");
        request2.setSourceAddress("192.168.0.0/20");
        request2.setDestinationAddress("10.0.0.0/8");
        request2.setNextHopList(Arrays.asList(masterHop, standbyHop));
        request2.setDescription("专线主备路由规则");

        try {
            CreateRouteResponse response2 = routeClient.createRoute(request2);
            System.out.println(response2);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

        // 负载均衡多线路由规则
        NextHop hop1 = new NextHop()
                .withNexthopId("dcgw-aaa")
                .withNexthopType("dcGateway")
                .withPathType("ecmp");

        NextHop hop2 = new NextHop()
                .withNexthopId("dcgw-bbb")
                .withNexthopType("dcGateway")
                .withPathType("ecmp");

        CreateRouteRequest request3 = new CreateRouteRequest();
        request3.setRouteTableId("rt-q1zg3i8mx8p6");
        request3.setSourceAddress("192.168.0.0/20");
        request3.setDestinationAddress("172.16.0.0/12");
        request3.setNextHopList(Arrays.asList(hop1, hop2));
        request2.setDescription("负载均衡多线路由规则");

        try {
            CreateRouteResponse response3 = routeClient.createRoute(request3);
            System.out.println(response3);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
