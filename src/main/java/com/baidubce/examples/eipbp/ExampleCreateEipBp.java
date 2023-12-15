/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eipbp;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eipbp.EipBpClient;
import com.baidubce.services.eipbp.model.CreateEipBpRequest;
import com.baidubce.services.eipbp.model.CreateEipBpResponse;

public class ExampleCreateEipBp {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipBpClient eipBpClient = new EipBpClient(config); // 初始化EipBpClient

        CreateEipBpRequest request = new CreateEipBpRequest();
        request.setEip("10.107.245.97"); // 带宽包要绑定的eip实例的ip地址
        request.setBandwidthInMbps(10); // 带宽包带宽值
        request.setName("test"); // 带宽包名称

        try {
            CreateEipBpResponse response = eipBpClient.createEipBp(request);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}

