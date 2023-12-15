/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.privatezone;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.localdns.LdClient;
import com.baidubce.services.localdns.model.BindVpcRequest;

import java.util.Arrays;

public class ExampleBindVpcOnPrivateZone {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        LdClient ldClient = new LdClient(config); // 初始化LdClient

        String zoneId = "zone-eej8ps6qp5ic"; // 要关联的privateZone的id
        BindVpcRequest bindVpcRequest = new BindVpcRequest();
        bindVpcRequest.setRegion("bj"); // 关联的vpc所在区域
        bindVpcRequest.setVpcIds(Arrays.asList("vpc-4kzjwxgvx4fi")); // 关联的vpc的id列表

        try {
            ldClient.bindVpc(zoneId, bindVpcRequest, "");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
