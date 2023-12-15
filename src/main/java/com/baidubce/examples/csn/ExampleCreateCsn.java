package com.baidubce.examples.csn;

import java.util.UUID;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.csn.CsnClient;
import com.baidubce.services.csn.model.CreateCsnRequest;
import com.baidubce.services.csn.model.CreateCsnResponse;

public class ExampleCreateCsn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "csn.baidubce.com"; // CSN服务对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CsnClient csnClient = new CsnClient(config); // 初始化CsnClient

        CreateCsnRequest request = new CreateCsnRequest();
        request.setName("javaSdkTest"); // 云智能网的名称
        request.setDescription("java sdk test"); // 云智能网的描述
        String clientToken = UUID.randomUUID().toString(); // 幂等性Token

        try {
            CreateCsnResponse response = csnClient.createCsn(request, clientToken);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
