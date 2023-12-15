/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eipgroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.eipgroup.EipGroupClient;
import com.baidubce.services.eipgroup.EipGroupClientConfiguration;
import com.baidubce.services.eipgroup.model.MoveOutRequest;

import java.util.Arrays;

public class ExampleMoveOutEipFromGroup {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        EipGroupClientConfiguration config = new EipGroupClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipGroupClient eipGroupClient = new EipGroupClient(config); // 初始化EipGroupClient

        MoveOutRequest request = new MoveOutRequest();
        request.setId("eg-8yxeMV47"); // eipgroup的实例id
        MoveOutRequest.EipMoveOutModel moveOutModel = new MoveOutRequest.EipMoveOutModel();
        moveOutModel.setEip("100.88.9.177"); // 要移出的eip地址
        moveOutModel.setBandwidthInMbps(100); // 要移出的eip带宽
        Billing billing = new Billing();
        billing.setPaymentTiming("Postpaid"); // 要移出的eip指定为后付费
        billing.setBillingMethod("ByTraffic"); // 要移出的eip指定为按流量计费
        moveOutModel.setBilling(billing);
        request.setMoveOutEips(Arrays.asList(moveOutModel));
        try {
            eipGroupClient.moveOutEips(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}

