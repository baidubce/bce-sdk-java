/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.csn;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.csn.CsnClient;
import com.baidubce.services.csn.model.UpdateCsnBpRequest;

import java.util.UUID;

public class ExampleUpdateCsnBp {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "csn.baidubce.com"; // CSN服务对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CsnClient csnClient = new CsnClient(config); // 初始化CsnClient

        String csnBpId = "csnBp-0wehdvzn9hid";  // 带宽包的ID
        UpdateCsnBpRequest updateCsnBpRequest = UpdateCsnBpRequest.builder()
                .name("updateNameCsnBpTest")    // 带宽包名称
                .build();
        String clientToken = UUID.randomUUID().toString();  // 幂等性Token

        try {
            csnClient.updateCsnBp(csnBpId, updateCsnBpRequest, clientToken);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
