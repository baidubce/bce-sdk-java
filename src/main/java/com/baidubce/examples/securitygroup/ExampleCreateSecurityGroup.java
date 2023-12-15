/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.securitygroup;

import java.util.Arrays;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.BccClient;
import com.baidubce.services.bcc.BccClientConfiguration;
import com.baidubce.services.bcc.model.SecurityGroupRuleModel;
import com.baidubce.services.bcc.model.securitygroup.CreateSecurityGroupRequest;
import com.baidubce.services.bcc.model.securitygroup.CreateSecurityGroupResponse;

public class ExampleCreateSecurityGroup {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        BccClientConfiguration config = new BccClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        BccClient bccClient = new BccClient(config); // 初始化SecurityGroupClient

        CreateSecurityGroupRequest createSecurityGroupRequest = new CreateSecurityGroupRequest();
        createSecurityGroupRequest.setName("sgName"); // sg名称
        createSecurityGroupRequest.setDesc("desc"); // sg描述
        createSecurityGroupRequest.setVpcId("vpc-b9ycwxxisrb7"); // sg所属VPC
        SecurityGroupRuleModel securityGroupRuleModel = new SecurityGroupRuleModel();
        securityGroupRuleModel.setSourceIp("all"); // 规则源IP
        securityGroupRuleModel.setDestIp("all"); // 规则目的IP
        securityGroupRuleModel.setDirection("ingress"); // 规则的方向
        securityGroupRuleModel.setProtocol("tcp"); // 规则协议
        securityGroupRuleModel.setPortRange("1000-3000"); // 规则端口范围
        securityGroupRuleModel.setEthertype("IPv4"); // 规则IPv4版本

        createSecurityGroupRequest.setRules(Arrays.asList(securityGroupRuleModel));
        try {
            CreateSecurityGroupResponse response = bccClient.createSecurityGroup(createSecurityGroupRequest);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

