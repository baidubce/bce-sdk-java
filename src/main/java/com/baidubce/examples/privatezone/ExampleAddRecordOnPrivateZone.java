/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.privatezone;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.localdns.LdClient;
import com.baidubce.services.localdns.model.AddRecordRequest;
import com.baidubce.services.localdns.model.AddRecordResponse;

public class ExampleAddRecordOnPrivateZone {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        LdClient ldClient = new LdClient(config); // 初始化LdClient

        String zoneId = "zone-eej8ps6qp5ic"; // 要添加解析记录的privateZone的id
        AddRecordRequest addRecordRequest = new AddRecordRequest();
        addRecordRequest.setRr("www"); // 主机记录
        addRecordRequest.setType("A"); // 记录类型
        addRecordRequest.setValue("1.1.1.1"); // 记录值
        try {
            AddRecordResponse response = ldClient.addRecord(zoneId, addRecordRequest, "");
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
