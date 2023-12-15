/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.dns;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.dns.DnsClient;
import com.baidubce.services.dns.model.UpdateLineGroupRequest;

import java.util.Arrays;

public class ExampleUpdateLineGroup {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "dns.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        DnsClient dnsClient = new DnsClient(config); // 初始化DnsClient

        String lineId = "6166"; // 线路的id
        UpdateLineGroupRequest request = new UpdateLineGroupRequest();
        request.setName("ccqLine1"); // 线路组名称
        request.setLines(Arrays.asList("yunnan.ct")); // 解析线路

        try {
            dnsClient.updateLineGroup(lineId, request, "");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
