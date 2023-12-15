package com.baidubce.examples.et;

import java.util.UUID;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.et.EtClient;
import com.baidubce.services.et.EtClientConfiguration;
import com.baidubce.services.et.model.CreateEtChannelRouteResponse;
import com.baidubce.services.et.model.CreateEtChannelRouteRuleRequest;

public class ExampleCreateEtChannelRouteRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // ET服务对应的域名

        EtClientConfiguration config = new EtClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtClient etClient = new EtClient(config); // 初始化ET client

        CreateEtChannelRouteRuleRequest request = new CreateEtChannelRouteRuleRequest();
        request.setClientToken(UUID.randomUUID().toString()); // 幂等性Token
        request.setEtId("dcphy-axibreesn6af"); // ET ID
        request.setEtChannelId("dedicatedconn-nezh65s7z325"); // 专线通道ID
        request.setIpVersion(4); // IP协议类型，取值[4 | 6]，默认为4
        request.setDestAddress("192.168.0.9/32"); // 目标网段
        request.setNexthopType("etChannel"); // 下一跳类型，取值["etGateway" | "etChannel"]，分别表示专线网关、专线通道
        request.setNexthopId("dedicatedconn-nezh65s7z325"); // 下一跳实例ID
        request.setDescription("Java SDK test"); // 描述

        try {
            CreateEtChannelRouteResponse response = etClient.createEtChannelRouteRule(request);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
