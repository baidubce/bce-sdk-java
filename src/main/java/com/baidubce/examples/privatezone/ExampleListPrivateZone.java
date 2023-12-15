/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.privatezone;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.localdns.LdClient;
import com.baidubce.services.localdns.model.ListPrivateZoneResponse;

public class ExampleListPrivateZone {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        LdClient ldClient = new LdClient(config); // 初始化LdClient

        String marker = ""; // 查询的marker
        Integer maxKeys = 1000; // 查询的maxKeys

        try {
            ListPrivateZoneResponse response = ldClient.listPrivateZone(marker, maxKeys);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
