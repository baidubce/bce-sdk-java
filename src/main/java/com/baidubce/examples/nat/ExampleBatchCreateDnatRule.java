package com.baidubce.examples.nat;

import java.util.Arrays;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.nat.NatClient;
import com.baidubce.services.nat.NatClientConfiguration;
import com.baidubce.services.nat.model.BatchAddDnatRulesRequest;
import com.baidubce.services.nat.model.CreateDnatRule;

public class ExampleBatchCreateDnatRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        NatClientConfiguration config = new NatClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        NatClient natClient = new NatClient(config); // 初始化Client

        BatchAddDnatRulesRequest batchAddDnatRulesRequest = new BatchAddDnatRulesRequest();
        batchAddDnatRulesRequest.setNatId("nat-bmnbjkvm8h24");
        CreateDnatRule createDnatRule = new CreateDnatRule();
        createDnatRule.setRuleName("aa");
        createDnatRule.setProtocol("TCP");
        createDnatRule.setPrivatePort(8990);
        createDnatRule.setPrivateIpAddress("192.168.0.25");
        createDnatRule.setPublicIpAddress("100.88.10.213");
        createDnatRule.setPublicPort(8991);
        batchAddDnatRulesRequest.setRules(Arrays.asList(createDnatRule));
        try {
            natClient.batchAddDnatRules(batchAddDnatRulesRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
