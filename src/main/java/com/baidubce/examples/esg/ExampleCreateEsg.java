/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.esg;

import java.util.Arrays;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.esg.EsgClient;
import com.baidubce.services.esg.EsgClientConfiguration;
import com.baidubce.services.esg.model.CreateEsgRequest;
import com.baidubce.services.esg.model.CreateEsgResponse;
import com.baidubce.services.esg.model.EnterpriseSecurityGroupRule;

public class ExampleCreateEsg {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EsgClientConfiguration config = new EsgClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EsgClient esgClient = new EsgClient(config); // 初始化EsgClient

        CreateEsgRequest createEsgRequest = new CreateEsgRequest();
        createEsgRequest.setName("esgName"); // esg名称
        createEsgRequest.setDesc("desc"); // esg描述
        EnterpriseSecurityGroupRule enterpriseSecurityGroupRule = new EnterpriseSecurityGroupRule(); // esg规则
        enterpriseSecurityGroupRule.setSourceIp("all"); // 规则源IP
        enterpriseSecurityGroupRule.setDestIp("all"); // 规则目的IP
        enterpriseSecurityGroupRule.setAction("allow"); // 规则允许
        enterpriseSecurityGroupRule.setDirection("ingress"); // 规则的方向
        enterpriseSecurityGroupRule.setPriority(100); // 规则优先级
        enterpriseSecurityGroupRule.setProtocol("tcp"); // 规则协议
        enterpriseSecurityGroupRule.setPortRange("1000-3000"); // 规则端口范围
        enterpriseSecurityGroupRule.setEthertype("IPv4"); // 规则IPv4版本

        createEsgRequest.setRules(Arrays.asList(enterpriseSecurityGroupRule));
        try {
            CreateEsgResponse response = esgClient.createEsg(createEsgRequest);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

