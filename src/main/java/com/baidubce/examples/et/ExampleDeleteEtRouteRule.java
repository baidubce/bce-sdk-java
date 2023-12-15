package com.baidubce.examples.et;

import java.util.UUID;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.et.EtClient;
import com.baidubce.services.et.EtClientConfiguration;
import com.baidubce.services.et.model.EtChannelRouteRuleIdRequest;

public class ExampleDeleteEtRouteRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // ET服务对应的域名

        EtClientConfiguration config = new EtClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtClient etClient = new EtClient(config); // 初始化ET client

        EtChannelRouteRuleIdRequest request = new EtChannelRouteRuleIdRequest();
        request.setClientToken(UUID.randomUUID().toString()); // 幂等性Token
        request.setEtId("dcphy-axibreesn6af"); // ET ID
        request.setEtChannelId("dedicatedconn-nezh65s7z325"); // 专线通道ID
        request.setRouteRuleId("dcrr-bf5545b8-de8"); // 专线通道路由规则ID

        try {
            etClient.deleteEtRouteRule(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
