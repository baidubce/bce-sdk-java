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
        updateSecurityGroupRuleRequest.setSecurityGroupRuleId("r-icyc1fkct9wk");
        updateSecurityGroupRuleRequest.setRemark("desc");
        try {
            bccClient.updateSecurityGroupRule(updateSecurityGroupRuleRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

