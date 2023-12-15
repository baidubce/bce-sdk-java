package com.baidubce.examples.nat;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.nat.NatClient;
import com.baidubce.services.nat.NatClientConfiguration;
import com.baidubce.services.nat.model.ListDnatRuleResponse;
import com.baidubce.services.nat.model.ListNatRuleRequest;

public class ExampleListDnatRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        NatClientConfiguration config = new NatClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        NatClient natClient = new NatClient(config); // 初始化Client

        ListNatRuleRequest request = new ListNatRuleRequest();
        request.setMaxKeys(10);
        request.setNatId("nat-b58rnkn1g98h"); // 需要查询dnat规则的natId

        try {
            ListDnatRuleResponse listDnatRuleResponse = natClient.listDnatRule(request);
            System.out.println(listDnatRuleResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
