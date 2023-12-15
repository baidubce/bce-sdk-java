/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.csn;

import java.util.UUID;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.csn.CsnClient;
import com.baidubce.services.csn.model.CreatePropagationRequest;

public class ExampleCreatePropagation {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "csn.baidubce.com"; // CSN服务对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CsnClient csnClient = new CsnClient(config); // 初始化CsnClient

        String csnRtId = "csnRt-bhnis7ubpgnmyk5f"; // 云智能网路由表的ID
        CreatePropagationRequest createPropagationRequest = new CreatePropagationRequest();
        createPropagationRequest.setAttachId("tgwAttach-5ttgra63p7v4q69z"); // 网络实例在云智能网中的身份的ID
        String clientToken = UUID.randomUUID().toString(); // 幂等性Token

        try {
            csnClient.createPropagation(csnRtId, createPropagationRequest, clientToken);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
