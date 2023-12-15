/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.privatezone;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.localdns.LdClient;
import com.baidubce.services.localdns.model.UpdateRecordRequest;

public class ExampleUpdateRecordOnPrivateZone {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        LdClient ldClient = new LdClient(config); // 初始化LdClient

        String recordId = "rc-byx2936gep0s"; // 要修改的记录id
        UpdateRecordRequest updateRecordRequest = new UpdateRecordRequest();
        updateRecordRequest.setRr("www"); // 主机记录
        updateRecordRequest.setType("A"); // 记录类型
        updateRecordRequest.setValue("1.1.1.2"); // 记录值
        try {
            ldClient.updateRecord(recordId, updateRecordRequest, "");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
