/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eip;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.EipClient;
import com.baidubce.services.eip.model.OptionalReleaseEipRequest;
import com.baidubce.services.eip.model.ReleaseEipRequest;

public class ExampleReleaseEip {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipClient eipClient = new EipClient(config); // 初始化EipClient

        ReleaseEipRequest request = new ReleaseEipRequest();
        request.setEip("10.107.245.112"); // eip地址

        try {
            // 直接删除eip
            eipClient.releaseEip(request);

            // 将eip移入回收站
            OptionalReleaseEipRequest releaseEipRequest = new OptionalReleaseEipRequest();
            releaseEipRequest.setEip("106.13.244.190"); // eip地址
            releaseEipRequest.setReleaseToRecycle(true);    // true:将eip移入回收站；false：直接删除eip
            eipClient.optionalReleaseEip(releaseEipRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}

