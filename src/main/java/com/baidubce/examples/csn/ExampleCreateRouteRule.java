/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.csn;

import java.util.UUID;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.csn.CsnClient;
import com.baidubce.services.csn.model.CreateRouteRuleRequest;

public class ExampleCreateRouteRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "csn.baidubce.com"; // CSN服务对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CsnClient csnClient = new CsnClient(config); // 初始化CsnClient

        String csnRtId = "csnRt-bhnis7ubpgnmyk5f"; // 云智能网路由表的ID
        CreateRouteRuleRequest createRouteRuleRequest = new CreateRouteRuleRequest();
        createRouteRuleRequest.setAttachId("tgwAttach-5ttgra63p7v4q69z"); // 网络实例在云智能网中的身份的ID
        createRouteRuleRequest.setDestAddress("0.0.0.0/0"); // 路由的目的地址
        createRouteRuleRequest.setRouteType("custom"); // 路由类型
        String clientToken = UUID.randomUUID().toString();

        try {
            csnClient.createRouteRule(csnRtId, createRouteRuleRequest, clientToken);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
