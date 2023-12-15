package com.baidubce.examples.et;

import java.util.UUID;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.et.EtClient;
import com.baidubce.services.et.EtClientConfiguration;
import com.baidubce.services.et.model.EnableEtChannelIpv6Request;

public class ExampleEnableEtChannelIpv6 {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // ET服务对应的域名

        EtClientConfiguration config = new EtClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtClient etClient = new EtClient(config); // 初始化ET client

        EnableEtChannelIpv6Request request = new EnableEtChannelIpv6Request();
        request.setClientToken(UUID.randomUUID().toString()); // 幂等性Token
        request.setEtId("dcphy-bjrtsp6ar5ug"); // ET ID
        request.setEtChannelId("dedicatedconn-xdzaphpgcqfn"); // 专线通道ID
        request.setBaiduIpv6Address("2400:DA00:E003:0000:016A:0400:0000:100/127"); // 云端网络侧IPv6互联地址
        request.setCustomerIpv6Address("2400:DA00:E003:0000:016A:0400:0000:101/127"); // IDC侧IPv6互联地址

        try {
            etClient.enableEtChannelIpv6(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
