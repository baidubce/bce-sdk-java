/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.dns;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.dns.DnsClient;
import com.baidubce.services.dns.model.AddLineGroupRequest;

import java.util.Arrays;

public class ExampleAddLineGroup {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "dns.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        DnsClient dnsClient = new DnsClient(config); // 初始化DnsClient

        AddLineGroupRequest request = new AddLineGroupRequest();
        request.setName("ccqLine"); // 线路组名称
        request.setLines(Arrays.asList("yunnan.ct", "henan.ct")); // 解析线路


        try {
            dnsClient.addLineGroup(request, "");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
