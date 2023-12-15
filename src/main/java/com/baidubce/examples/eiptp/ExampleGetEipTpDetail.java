/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eiptp;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eiptp.EipTpClient;
import com.baidubce.services.eiptp.model.EipTpDetailResponse;

public class ExampleGetEipTpDetail {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipTpClient eipTpClient = new EipTpClient(config); // 初始化EipTpClient

        String eipTpId = "tp-c7pdMpkpbB"; // 共享流量包id

        try {
            EipTpDetailResponse response = eipTpClient.getEipTpDetail(eipTpId);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
