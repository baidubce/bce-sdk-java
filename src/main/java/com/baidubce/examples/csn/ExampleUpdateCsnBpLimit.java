/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.csn;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.csn.CsnClient;
import com.baidubce.services.csn.model.UpdateCsnBpLimitRequest;

import java.util.UUID;

public class ExampleUpdateCsnBpLimit {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "csn.baidubce.com"; // CSN服务对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CsnClient csnClient = new CsnClient(config); // 初始化CsnClient

        String csnBpId = "csnBp-jm5sb75yz4j8";  // 带宽包的ID
        UpdateCsnBpLimitRequest updateCsnBpLimitRequest = UpdateCsnBpLimitRequest.builder()
                .localRegion("bj")  // 地域带宽的本端region，该值不能改变
                .peerRegion("gz")   // 地域带宽的对端region，该值不能改变
                .bandwidth(15)  // 更新的地域带宽的带宽值
                .build();
        String clientToken = UUID.randomUUID().toString();  // 幂等性Token

        try {
            csnClient.updateCsnBpLimit(csnBpId, updateCsnBpLimitRequest, clientToken);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
