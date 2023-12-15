/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.route;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.route.RouteClient;
import com.baidubce.services.route.RouteClientConfiguration;
import com.baidubce.services.route.model.ListRouteRuleReq;
import com.baidubce.services.route.model.ListRouteRuleResponse;

public class ExampleMarkerListRouteRule {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        RouteClientConfiguration config = new RouteClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        RouteClient routeClient = new RouteClient(config); // 初始化VpcClient


        ListRouteRuleReq listRouteRuleReq = new ListRouteRuleReq();
        listRouteRuleReq.setRouteTableId("rt-s3qmp80vq02q");
        try {
            ListRouteRuleResponse response = routeClient.listRouteRule(listRouteRuleReq);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
