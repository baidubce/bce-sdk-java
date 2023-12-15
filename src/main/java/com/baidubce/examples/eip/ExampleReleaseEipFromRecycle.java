/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eip;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.EipClient;
import com.baidubce.services.eip.model.RecycleOperateEipRequest;

public class ExampleReleaseEipFromRecycle {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipClient eipClient = new EipClient(config); // 初始化EipClient

        RecycleOperateEipRequest request = new RecycleOperateEipRequest();
        request.setEip("100.88.7.75"); // eip地址
        try {
            eipClient.releaseEipFromRecycle(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}

