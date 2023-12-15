/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eipgroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.eipgroup.EipGroupClient;
import com.baidubce.services.eipgroup.EipGroupClientConfiguration;
import com.baidubce.services.eipgroup.model.PurchaseReservedEipGroupRequest;

public class ExampleRenewEipGroup {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        EipGroupClientConfiguration config = new EipGroupClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipGroupClient eipGroupClient = new EipGroupClient(config); // 初始化EipGroupClient

        PurchaseReservedEipGroupRequest request = new PurchaseReservedEipGroupRequest();
        request.setId("eg-7e358ead"); // eipgroup实例id
        Billing billing = new Billing();
        Billing.Reservation reservation = new Billing.Reservation();
        reservation.setReservationLength(1); // eipgroup续费时长
        reservation.setReservationTimeUnit("Month"); // eipgroup续费时间单位
        billing.setReservation(reservation);
        request.setBilling(billing);

        try {
            eipGroupClient.purchaseReservedEipGroup(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}

