/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.privatezone;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;

import com.baidubce.services.localdns.LdClient;
import com.baidubce.services.localdns.model.ListRecordRequest;
import com.baidubce.services.localdns.model.ListRecordResponse;

public class ExampleListRecordsOfPrivateZone {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "privatezone.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        LdClient ldClient = new LdClient(config); // 初始化LdClient

        String zoneId = "zone-huqh664jt2eh"; // privateZone的id

        try {
            ListRecordRequest listRecordRequest = new ListRecordRequest();
            listRecordRequest.setZoneId(zoneId);
            listRecordRequest.setMarker("");
            listRecordRequest.setMaxKeys(1000);
            ListRecordResponse response = ldClient.listRecord(listRecordRequest);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
