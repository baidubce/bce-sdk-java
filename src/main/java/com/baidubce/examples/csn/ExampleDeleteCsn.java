package com.baidubce.examples.csn;

import java.util.UUID;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.csn.CsnClient;

public class ExampleDeleteCsn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "csn.baidubce.com"; // CSN服务对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CsnClient csnClient = new CsnClient(config); // 初始化CsnClient

        String clientToken = UUID.randomUUID().toString(); // 幂等性Token
        String csnId = "csn-2jssjbhvyd8v1gxn"; // 云智能网ID

        try {
            csnClient.deleteCsn(csnId, clientToken);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
