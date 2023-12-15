package com.baidubce.examples.et;

import java.util.UUID;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.et.EtClient;
import com.baidubce.services.et.EtClientConfiguration;
import com.baidubce.services.et.model.UpdateEtChannelRequest;

public class ExampleUpdateEtChannel {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // ET服务对应的域名

        EtClientConfiguration config = new EtClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtClient etClient = new EtClient(config); // 初始化ET client

        UpdateEtChannelRequest request = new UpdateEtChannelRequest();
        request.setClientToken(UUID.randomUUID().toString()); // 幂等性Token
        request.setEtId("dcphy-axibreesn6af"); // ET ID
        request.setEtChannelId("dedicatedconn-kd1ff138ypnm"); // 专线通道ID
        request.setDescription("Java sdk test ET channel update"); // 专线通道描述
        request.setName("JavaSdkTestEtChannelUpdate"); // 通道名称

        try {
            etClient.updateEtChannel(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
