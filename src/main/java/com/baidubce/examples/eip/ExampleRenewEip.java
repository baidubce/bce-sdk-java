/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eip;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.EipClient;
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.eip.model.PurchaseReservedEipRequest;

public class ExampleRenewEip {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipClient eipClient = new EipClient(config); // 初始化EipClient

        PurchaseReservedEipRequest request = new PurchaseReservedEipRequest();
        request.setEip("10.107.245.97"); // eip地址
        Billing billing = new Billing();
        Billing.Reservation reservation = new Billing.Reservation();
        reservation.setReservationLength(1); // eip续费时长
        reservation.setReservationTimeUnit("Month"); // eip续费时间单位
        billing.setReservation(reservation);
        try {
            eipClient.purchaseReservedEip(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}

