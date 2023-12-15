package com.baidubce.examples.et;

import java.util.UUID;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.et.EtClient;
import com.baidubce.services.et.EtClientConfiguration;
import com.baidubce.services.et.model.UpdateEtRequest;

public class ExampleUpdateEt {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // ET服务对应的域名

        EtClientConfiguration config = new EtClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EtClient etClient = new EtClient(config); // 初始化ET client

        UpdateEtRequest request = new UpdateEtRequest();
        request.setClientToken(UUID.randomUUID().toString()); // 幂等性Token
        request.setEtId("dcphy-t6ewxjaekkt2"); // ET ID
        request.setName("JavaSdkTestETUpdate"); // ET 更新名字
        request.setDescription("Java sdk test ET update"); // ET 更新描述

        try {
            etClient.updateEt(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
