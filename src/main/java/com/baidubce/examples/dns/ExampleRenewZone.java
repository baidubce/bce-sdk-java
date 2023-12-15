/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.dns;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.dns.DnsClient;
import com.baidubce.services.dns.model.RenewZoneRequest;

public class ExampleRenewZone {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "dns.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        DnsClient dnsClient = new DnsClient(config); // 初始化DnsClient

        String zoneName = "javaSdk.com"; // 域名名称
        RenewZoneRequest request = new RenewZoneRequest();
        RenewZoneRequest.Billing billing = new RenewZoneRequest.Billing();
        RenewZoneRequest.Billing.Reservation reservation = new RenewZoneRequest.Billing.Reservation();
        reservation.setReservationLength(1); // 续费时长
        billing.setReservation(reservation);
        request.setBilling(billing);

        try {
            dnsClient.renewZone(zoneName, request, "");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
