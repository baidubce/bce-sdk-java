/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eipgroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.eipgroup.EipGroupClient;
import com.baidubce.services.eipgroup.EipGroupClientConfiguration;
import com.baidubce.services.eipgroup.model.CreateEipGroupRequest;
import com.baidubce.services.eipgroup.model.IdResponse;

public class ExampleCreateEipGroup {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        EipGroupClientConfiguration config = new EipGroupClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipGroupClient eipGroupClient = new EipGroupClient(config); // 初始化EipGroupClient

        CreateEipGroupRequest request = new CreateEipGroupRequest();
        Billing.Reservation reservation = new Billing.Reservation();
        reservation.setReservationLength(1); // eipgroup购买时长
        reservation.setReservationTimeUnit("Month"); // eipgroup购买时间单位
        Billing billing = new Billing();
        billing.setPaymentTiming("Prepaid"); // eipgroup购买计费方式，预付费或后付费
        billing.setReservation(reservation);
        request.setBilling(billing);
        request.setName("EipGroupTest"); // eipgroup的名称
        request.setEipCount(2); // eipgroup内ip数量
        request.setBandwidthInMbps(10); // eipgroup的带宽

        try {
            IdResponse response = eipGroupClient.createEipGroup(request);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}

