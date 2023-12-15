/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.csn;

import java.util.UUID;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.csn.CsnClient;

public class ExampleDeleteRouteRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "csn.baidubce.com"; // CSN服务对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CsnClient csnClient = new CsnClient(config); // 初始化CsnClient

        String csnRtId = "csnRt-bhnis7ubpgnmyk5f"; // 路由表的ID
        String csnRtRuleId = "bfe6294f-1058-421c-8759-0973589a4442"; // 路由条目的ID
        String clientToken = UUID.randomUUID().toString(); // 幂等性Token

        try {
            csnClient.deleteRouteRule(csnRtId, csnRtRuleId, clientToken);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
