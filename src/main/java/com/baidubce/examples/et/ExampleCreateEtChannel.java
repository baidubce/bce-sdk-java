package com.baidubce.examples.et;

import java.util.UUID;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.et.EtClient;
import com.baidubce.services.et.EtClientConfiguration;
import com.baidubce.services.et.model.CreateEtChannelRequest;
import com.baidubce.services.et.model.CreateEtChannelResponse;
import com.google.common.collect.Lists;

public class ExampleCreateEtChannel {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // ET服务对应的域名

        EtClientConfiguration config = new EtClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtClient etClient = new EtClient(config); // 初始化ET client

        CreateEtChannelRequest request = new CreateEtChannelRequest();
        request.setClientToken(UUID.randomUUID().toString()); // 幂等性Token
        request.setEtId("dcphy-6vtqgw1fk3mj"); // ET ID
        request.setAuthorizedUsers(Lists.newArrayList("1beb4ad4762746db96941a5ad253ac8c")); // 分配对象
        request.setDescription("Java sdk test ET channel"); // 专线通道描述
        request.setBaiduAddress("192.168.0.2/24"); // 云端网络互联IP
        request.setName("JavaSdkTestEtChannel"); // 通道名称
        request.setCustomerAddress("192.168.0.3/24"); // IDC互联IP
        request.setRouteType("static-route"); // 路由协议，当前支持”static-route“（静态）和”bgp“（动态）
        request.setVlanId(2); // VLAN ID，取值范围：0, 2-4009

        try {
            CreateEtChannelResponse response = etClient.createEtChannel(request);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
