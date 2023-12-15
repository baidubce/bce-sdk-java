/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.csn;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.csn.CsnClient;
import com.baidubce.services.csn.model.ListCsnBpLimitByCsnIdResponse;

public class ExampleListCsnBpLimitByCsnId {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "csn.baidubce.com"; // CSN服务对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CsnClient csnClient = new CsnClient(config); // 初始化CsnClient

        String csnId = "csn-b65m2hez6h60iwa1";  // 云智能网的ID

        try {
            ListCsnBpLimitByCsnIdResponse listCsnBpLimitByCsnIdResponse = csnClient.listCsnBpLimitByCsnId(csnId);
            System.out.println("listCsnBpLimitByCsnIdResponse = " + listCsnBpLimitByCsnIdResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
