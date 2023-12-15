/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.securitygroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.BccClient;
import com.baidubce.services.bcc.BccClientConfiguration;
import com.baidubce.services.bcc.model.securitygroup.ListSecurityGroupsRequest;

public class ExampleDeleteSecurityGroupRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        BccClientConfiguration config = new BccClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        BccClient bccClient = new BccClient(config); // 初始化SecurityGroupClient

        ListSecurityGroupsRequest listSecurityGroupsRequest = new ListSecurityGroupsRequest();
        listSecurityGroupsRequest.setVpcId("vpc-b9ycwxxisrb7");
        try {
            bccClient.deleteSecurityGroupRule("r-2guv5ze6yx48");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

