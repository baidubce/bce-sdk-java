/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eni;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eni.EniClient;
import com.baidubce.services.eni.EniClientConfiguration;
import com.baidubce.services.eni.model.EniUnBindEipRequest;

public class ExampleEniUnBindEip {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EniClientConfiguration config = new EniClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);

        EniClient eniClient = new EniClient(config); // 初始化EniClient

        EniUnBindEipRequest eniUnBindEipRequest = new EniUnBindEipRequest();
        eniUnBindEipRequest.setEniId("eni-nm4dx9vtnh3p"); // 弹性网卡Id
        eniUnBindEipRequest.setPublicIpAddress("100.88.11.97"); // 解绑的eip

        try {
            eniClient.unBindEniPublicIp(eniUnBindEipRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

