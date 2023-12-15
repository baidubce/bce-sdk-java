package com.baidubce.examples.csn;

import java.util.UUID;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.csn.CsnClient;
import com.baidubce.services.csn.model.UpdateCsnRequest;

public class ExampleUpdateCsn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "csn.baidubce.com"; // CSN服务对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CsnClient csnClient = new CsnClient(config); // 初始化CsnClient

        UpdateCsnRequest request = new UpdateCsnRequest();
        request.setName("javaSdkTest"); // 云智能网的名称
        request.setDescription("java sdk test"); // 云智能网的描述
        String clientToken = UUID.randomUUID().toString(); // 幂等性Token
        String csnId = "csn-2jssjbhvyd8v1gxn"; // 云智能网ID

        try {
            csnClient.updateCsn(csnId, request, clientToken);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
