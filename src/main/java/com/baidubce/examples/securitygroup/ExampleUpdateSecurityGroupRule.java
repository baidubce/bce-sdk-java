/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.securitygroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.BccClient;
import com.baidubce.services.bcc.BccClientConfiguration;
import com.baidubce.services.bcc.model.securitygroup.UpdateSecurityGroupRuleRequest;

public class ExampleUpdateSecurityGroupRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        BccClientConfiguration config = new BccClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        BccClient bccClient = new BccClient(config); // 初始化SecurityGroupClient

        UpdateSecurityGroupRuleRequest updateSecurityGroupRuleRequest = new UpdateSecurityGroupRuleRequest();
        updateSecurityGroupRuleRequest.setSecurityGroupRuleId("r-dw0phk0c6u6x");
        updateSecurityGroupRuleRequest.setRemark("Updated description"); // 更新描述信息
        updateSecurityGroupRuleRequest.setPortRange("80-8081"); // 可选，更新端口范围
        updateSecurityGroupRuleRequest.setSourceIp("192.168.1.0/24"); // 可选，更新源IP
        // updateSecurityGroupRuleRequest.setSgVersion(1L); // 可选，安全组版本号，用于版本控制
        try {
            bccClient.updateSecurityGroupRule(updateSecurityGroupRuleRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

