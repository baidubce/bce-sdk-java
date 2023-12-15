/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.esg;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.esg.EsgClient;
import com.baidubce.services.esg.EsgClientConfiguration;

public class ExampleDeleteEsgRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EsgClientConfiguration config = new EsgClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EsgClient esgClient = new EsgClient(config); // 初始化EsgClient

        try {
            esgClient.deleteEsgRule("esgr-ghwpcwxv3v1x");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

