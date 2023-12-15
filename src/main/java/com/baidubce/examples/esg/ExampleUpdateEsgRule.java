/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.esg;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.esg.EsgClient;
import com.baidubce.services.esg.EsgClientConfiguration;
import com.baidubce.services.esg.model.UpdateEsgRuleRequest;

public class ExampleUpdateEsgRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EsgClientConfiguration config = new EsgClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EsgClient esgClient = new EsgClient(config); // 初始化EsgClient

        UpdateEsgRuleRequest updateEsgRuleRequest = new UpdateEsgRuleRequest();
        updateEsgRuleRequest.setEnterpriseSecurityGroupRuleId("esgr-ghwpcwxv3v1x");
        updateEsgRuleRequest.setSourceIp("all"); // 规则源IP
        updateEsgRuleRequest.setDestIp("all"); // 规则目的IP
        updateEsgRuleRequest.setAction("allow"); // 规则允许
        updateEsgRuleRequest.setDirection("ingress"); // 规则的方向
        updateEsgRuleRequest.setPriority(300); // 规则优先级
        updateEsgRuleRequest.setProtocol("tcp"); // 规则协议
        updateEsgRuleRequest.setPortRange("1000-3000"); // 规则端口范围
        updateEsgRuleRequest.setEthertype("IPv4"); // 规则IPv4版本

        try {
            esgClient.updateEsgRule(updateEsgRuleRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

