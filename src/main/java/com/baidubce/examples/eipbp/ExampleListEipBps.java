/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eipbp;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eipbp.EipBpClient;
import com.baidubce.services.eipbp.model.ListEipBpsRequest;
import com.baidubce.services.eipbp.model.ListEipBpsResponse;

public class ExampleListEipBps {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipBpClient eipBpClient = new EipBpClient(config); // 初始化EipBpClient

        ListEipBpsRequest request = new ListEipBpsRequest();
        request.setId("bw-2ede9df3"); // 要查询的带宽包id
        request.setName("test-name"); // 要查询的带宽包名称
        request.setBindType("eip"); // 要查询的带宽包所绑定的资源的类型，"eip"（弹性公网EIP）或"eipgroup"（共享带宽）
        request.setType("BandwidthPackage"); // 带宽包的类型，包括BandwidthPackage（带宽包）和AccelerationPackage（跨境加速包），默认全选
        request.setMarker("bw-IyWRnII7"); // 批量获取列表的查询的起始位置，是一个由系统生成的字符串
        request.setMaxKeys(10); // 每页包含的最大数量，最大数量不超过1000。缺省值为1000

        try {
            ListEipBpsResponse listEipBpsResponse = eipBpClient.listEipBps(request);
            System.out.println(listEipBpsResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
