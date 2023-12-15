/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.privatezone;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.localdns.LdClient;
import com.baidubce.services.localdns.model.UnbindVpcRequest;

import java.util.Arrays;

public class ExampleUnBindVpcFromPrivateZone {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        LdClient ldClient = new LdClient(config); // 初始化LdClient

        String zoneId = "zone-eej8ps6qp5ic"; // 要解关联的privateZone的id
        UnbindVpcRequest unbindVpcRequest = new UnbindVpcRequest();
        unbindVpcRequest.setRegion("bj"); // 解关联的vpc所在区域
        unbindVpcRequest.setVpcIds(Arrays.asList("vpc-4kzjwxgvx4fi")); // 解关联的vpc的id列表

        try {
            ldClient.unbindVpc(zoneId, unbindVpcRequest, "");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
