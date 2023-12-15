/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.dns;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.dns.DnsClient;
import com.baidubce.services.dns.model.CreatePaidZoneRequest;

import java.util.Arrays;

public class ExampleCreatePaidZone {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "dns.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        DnsClient dnsClient = new DnsClient(config); // 初始化DnsClient

        CreatePaidZoneRequest request = new CreatePaidZoneRequest();
        CreatePaidZoneRequest.Billing billing = new CreatePaidZoneRequest.Billing();
        billing.setPaymentTiming("Prepaid"); // 计费方式，预付费
        CreatePaidZoneRequest.Billing.Reservation reservation = new CreatePaidZoneRequest.Billing.Reservation();
        reservation.setReservationLength(1); // 计费时长
        billing.setReservation(reservation);
        request.setNames(Arrays.asList("javaSdkPaid.com")); // 域名名称
        request.setBilling(billing);
        request.setProductVersion("discount"); // 购买的产品版本，普惠版（“discount”）、企业版（“flagship”）

        try {
            dnsClient.createPaidZone(request, "");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
