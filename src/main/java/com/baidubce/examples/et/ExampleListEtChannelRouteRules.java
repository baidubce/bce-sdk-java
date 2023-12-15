package com.baidubce.examples.et;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.et.EtClient;
import com.baidubce.services.et.EtClientConfiguration;
import com.baidubce.services.et.model.ListEtChannelRouteRulesRequest;
import com.baidubce.services.et.model.ListEtChannelRouteRulesResponse;

public class ExampleListEtChannelRouteRules {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // ET服务对应的域名

        EtClientConfiguration config = new EtClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtClient etClient = new EtClient(config); // 初始化ET client

        ListEtChannelRouteRulesRequest request = new ListEtChannelRouteRulesRequest();
        request.setEtId("dcphy-axibreesn6af"); // ET ID
        request.setEtChannelId("dedicatedconn-nezh65s7z325"); // 专线通道ID
        request.setDestAddress("192.168.0.9/32"); // 目标网段

        try {
            ListEtChannelRouteRulesResponse response = etClient.listEtChannelRouteRules(request);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
