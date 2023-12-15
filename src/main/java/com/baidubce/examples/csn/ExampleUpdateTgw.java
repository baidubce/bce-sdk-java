/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.csn;

import java.util.UUID;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.csn.CsnClient;
import com.baidubce.services.csn.model.UpdateTgwRequest;

public class ExampleUpdateTgw {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "csn.baidubce.com"; // CSN服务对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CsnClient csnClient = new CsnClient(config); // 初始化CsnClient

        String csnId = "csn-2qi3430b3vqbcfgd"; // 云智能网的ID
        String tgwId = "tgw-5yvyjar8bi32dwru"; // TGW实例的ID
        UpdateTgwRequest updateTgwRequest = new UpdateTgwRequest();
        updateTgwRequest.setName("tgw_1"); // TGW的名称
        updateTgwRequest.setDescription("desc"); // TGW的描述信息
        String clientToken = UUID.randomUUID().toString(); // 幂等性Token

        try {
            csnClient.updateTgw(csnId, tgwId, updateTgwRequest, clientToken);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
