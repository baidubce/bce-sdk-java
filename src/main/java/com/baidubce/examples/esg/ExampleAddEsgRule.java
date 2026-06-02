/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.esg;

import java.util.Arrays;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.esg.EsgClient;
import com.baidubce.services.esg.EsgClientConfiguration;
import com.baidubce.services.esg.model.EnterpriseSecurityGroupRule;
import com.baidubce.services.esg.model.EsgRuleOperateRequest;

public class ExampleAddEsgRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EsgClientConfiguration config = new EsgClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EsgClient esgClient = new EsgClient(config); // 初始化EsgClient

        EnterpriseSecurityGroupRule enterpriseSecurityGroupRule = new EnterpriseSecurityGroupRule(); // esg规则
        // enterpriseSecurityGroupRule.setSourceIp("all"); // 规则源IP
        enterpriseSecurityGroupRule.setDestIp("all"); // 规则目的IP
        enterpriseSecurityGroupRule.setAction("allow"); // 规则允许（注意：action字段已添加@JsonIgnore，会被自动处理）
        enterpriseSecurityGroupRule.setDirection("ingress"); // 规则的方向
        enterpriseSecurityGroupRule.setPriority(100); // 规则优先级
        enterpriseSecurityGroupRule.setProtocol("tcp"); // 规则协议
        enterpriseSecurityGroupRule.setPortRange("2000-3000"); // 规则端口范围
        enterpriseSecurityGroupRule.setEthertype("IPv4"); // 规则IPv4版本
        enterpriseSecurityGroupRule.setRemark("example authorize rule1"); // 规则备注
        // 新增字段：远程IP集合，支持CIDR格式的IP地址段
        // enterpriseSecurityGroupRule.setRemoteIpSet("ips-wzz7s2sjvsaq");
        // 或者使用remoteIpGroup字段指定远程IP组：
        enterpriseSecurityGroupRule.setRemoteIpGroup("ipg-ie0hnjua54wh");

        EsgRuleOperateRequest esgRuleOperateRequest = new EsgRuleOperateRequest();
        esgRuleOperateRequest.setEnterpriseSecurityGroupId("esg-bwtif4zjhnw9");
        esgRuleOperateRequest.setRules(Arrays.asList(enterpriseSecurityGroupRule));
        try {
            esgClient.authorizeEsgRule(esgRuleOperateRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

