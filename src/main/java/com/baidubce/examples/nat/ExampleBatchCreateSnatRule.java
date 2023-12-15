package com.baidubce.examples.nat;

import java.util.Arrays;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.nat.NatClient;
import com.baidubce.services.nat.NatClientConfiguration;
import com.baidubce.services.nat.model.BatchAddSnatRuleRequest;

public class ExampleBatchCreateSnatRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        NatClientConfiguration config = new NatClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        NatClient natClient = new NatClient(config); // 初始化Client

        BatchAddSnatRuleRequest batchAddSnatRuleRequest = new BatchAddSnatRuleRequest();
        batchAddSnatRuleRequest.setNatId("nat-bmnbjkvm8h24");
        BatchAddSnatRuleRequest.CreateSnatRule createSnatRule = new BatchAddSnatRuleRequest.CreateSnatRule();
        createSnatRule.setRuleName("name");
        createSnatRule.setSourceCIDR("192.168.0.0/24");
        createSnatRule.setPublicIpsAddress(Arrays.asList("100.88.0.51"));
        batchAddSnatRuleRequest.setSnatRules(Arrays.asList(createSnatRule));
        try {
            natClient.batchAddSnatRules(batchAddSnatRuleRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
