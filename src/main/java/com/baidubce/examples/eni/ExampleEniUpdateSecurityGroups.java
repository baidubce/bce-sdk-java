/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eni;

import java.util.Arrays;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eni.EniClient;
import com.baidubce.services.eni.EniClientConfiguration;
import com.baidubce.services.eni.model.EniUpdateSecurityGroupRequest;

public class ExampleEniUpdateSecurityGroups {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EniClientConfiguration config = new EniClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EniClient eniClient = new EniClient(config); // 初始化EniClient

        EniUpdateSecurityGroupRequest eniUpdateSecurityGroupRequest = new EniUpdateSecurityGroupRequest();
        eniUpdateSecurityGroupRequest.setEniId("eni-nm4dx9vtnh3p");
        eniUpdateSecurityGroupRequest.setSecurityGroupIds(Arrays.asList("g-vs94kdqxw078"));

        try {
            eniClient.updateEniSecurityGroup(eniUpdateSecurityGroupRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

