/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.securitygroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.BccClient;
import com.baidubce.services.bcc.BccClientConfiguration;
import com.baidubce.services.bcc.model.securitygroup.ListSecurityGroupsRequest;
import com.baidubce.services.bcc.model.securitygroup.ListSecurityGroupsResponse;

public class ExampleListSecurityGroup {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        BccClientConfiguration config = new BccClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        BccClient bccClient = new BccClient(config); // 初始化SecurityGroupClient

        ListSecurityGroupsRequest listSecurityGroupsRequest = new ListSecurityGroupsRequest();
        listSecurityGroupsRequest.setVpcId("vpc-b9ycwxxisrb7"); // 安全组的vpcId

        try {
            ListSecurityGroupsResponse response = bccClient.listSecurityGroups(listSecurityGroupsRequest);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

