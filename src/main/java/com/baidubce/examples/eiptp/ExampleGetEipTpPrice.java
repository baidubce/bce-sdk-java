/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eiptp;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eiptp.EipTpClient;
import com.baidubce.services.eiptp.model.GetEipTpPriceRequest;
import com.baidubce.services.eiptp.model.GetEipTpPriceResponse;

public class ExampleGetEipTpPrice {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipTpClient eipTpClient = new EipTpClient(config); // 初始化EipTpClient

        GetEipTpPriceRequest request = new GetEipTpPriceRequest();
        request.setReservationLength(1); // 共享流量包有效期（单位：月）
        request.setCapacity("10G"); // 共享流量包容量
        request.setDeductPolicy("FullTimeDurationPackage"); // 扣费策略：全时
        request.setPackageType("WebOutBytes"); // 线路类型：动态

        try {
            GetEipTpPriceResponse response = eipTpClient.getEipTpPrice(request);
            System.out.println("EipTp Price: " + response.getPrice());
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}