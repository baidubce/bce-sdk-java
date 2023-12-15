/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.esg;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.esg.EsgClient;
import com.baidubce.services.esg.EsgClientConfiguration;
import com.baidubce.services.esg.model.ListEsgRequest;
import com.baidubce.services.esg.model.ListEsgResponse;

public class ExampleEsgList {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EsgClientConfiguration config = new EsgClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EsgClient esgClient = new EsgClient(config); // 初始化EsgClient

        ListEsgRequest listEsgRequest = new ListEsgRequest();
        listEsgRequest.setMaxKeys(100); // 最大返回数量
        try {
            ListEsgResponse response = esgClient.listEsg(listEsgRequest);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

