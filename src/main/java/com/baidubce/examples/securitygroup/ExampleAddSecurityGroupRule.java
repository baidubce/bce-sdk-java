/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.securitygroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.BccClient;
import com.baidubce.services.bcc.BccClientConfiguration;
import com.baidubce.services.bcc.model.SecurityGroupRuleModel;
import com.baidubce.services.bcc.model.securitygroup.SecurityGroupRuleOperateRequest;

public class ExampleAddSecurityGroupRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        BccClientConfiguration config = new BccClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        BccClient bccClient = new BccClient(config); // 初始化SecurityGroupClient

        SecurityGroupRuleOperateRequest securityGroupRuleOperateRequest = new SecurityGroupRuleOperateRequest();
        securityGroupRuleOperateRequest.setSecurityGroupId("g-z7e2nm72eezs");

        SecurityGroupRuleModel securityGroupRuleModel = new SecurityGroupRuleModel();
        securityGroupRuleModel.setSourceIp("all"); // 规则源IP
        securityGroupRuleModel.setDestIp("all"); // 规则目的IP
        securityGroupRuleModel.setDirection("ingress"); // 规则的方向
        securityGroupRuleModel.setProtocol("tcp"); // 规则协议
        securityGroupRuleModel.setPortRange("1000-3000"); // 规则端口范围
        securityGroupRuleModel.setEthertype("IPv4"); // 规则IPv4版本

        securityGroupRuleOperateRequest.setRule(securityGroupRuleModel);
        try {
            bccClient.authorizeSecurityGroupRule(securityGroupRuleOperateRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

