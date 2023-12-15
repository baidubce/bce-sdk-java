/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eip;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.EipClient;
import com.baidubce.services.eip.model.ResizeEipRequest;

public class ExampleResizeEip {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipClient eipClient = new EipClient(config); // 初始化EipClient

        ResizeEipRequest request = new ResizeEipRequest();
        request.setEip("10.107.245.112"); // eip地址
        request.setNewBandwidthInMbps(2); // eip要变配的带宽

        try {
            eipClient.resizeEip(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}

