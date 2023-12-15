/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eipbp;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eipbp.EipBpClient;
import com.baidubce.services.eipbp.model.ResizeEipBpRequest;

public class ExampleResizeEipBp {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipBpClient eipBpClient = new EipBpClient(config); // 初始化EipBpClient

        ResizeEipBpRequest request = new ResizeEipBpRequest();
        request.setId("bw-2ede9df3"); // 带宽包实例id
        request.setBandwidthInMbps(20); // 带宽包要变配的带宽值

        try {
            eipBpClient.resizeEipBp(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}

