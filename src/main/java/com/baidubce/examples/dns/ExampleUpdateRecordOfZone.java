/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.dns;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.dns.DnsClient;
import com.baidubce.services.dns.model.UpdateRecordRequest;

public class ExampleUpdateRecordOfZone {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "dns.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        DnsClient dnsClient = new DnsClient(config); // 初始化DnsClient

        String zoneName = "javaSdk.com"; // 域名名称
        String recordId = "48554"; // 记录的id
        UpdateRecordRequest request = new UpdateRecordRequest();
        request.setRr("www"); // 主机记录
        request.setType("A"); // 解析记录类型
        request.setValue("1.1.1.2"); // 记录值

        try {
            dnsClient.updateRecord(zoneName, recordId, request, "");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
