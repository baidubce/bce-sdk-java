/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.acl;

import java.util.List;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.acl.AclClient;
import com.baidubce.services.acl.AclClientConfiguration;
import com.baidubce.services.acl.model.AclRule;
import com.google.common.collect.Lists;

public class ExampleAddAclRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        AclClientConfiguration config = new AclClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AclClient aclClient = new AclClient(config); // 初始化AclClient

        AclRule aclRule = new AclRule();
        aclRule.setSubnetId("sbn-6ha6gp1vczuv"); // acl规则的子网Id
        aclRule.setAction("allow"); // 规则允许或者拒绝访问
        aclRule.setDescription("acl rule"); // 描述
        aclRule.setDirection("ingress"); // 规则的方向
        aclRule.setName("acl_name"); // 规则名称
        aclRule.setPosition(9000); // 规则的端口
        aclRule.setProtocol("all"); // acl规则的协议
        aclRule.setDestinationIpAddress("192.168.0.8"); // acl规则目的地址
        aclRule.setDestinationPort("80"); // acl规则目地端口
        aclRule.setSourceIpAddress("all"); // acl规则源地址
        aclRule.setSourcePort("90"); // acl规则源端口

        List<AclRule> aclRules = Lists.newArrayList(aclRule);
        try {
            aclClient.createAcl(aclRules);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

