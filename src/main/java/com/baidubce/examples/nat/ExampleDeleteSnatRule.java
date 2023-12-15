package com.baidubce.examples.nat;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.nat.NatClient;
import com.baidubce.services.nat.NatClientConfiguration;
import com.baidubce.services.nat.model.DeleteNatRuleRequest;

public class ExampleDeleteSnatRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        NatClientConfiguration config = new NatClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        NatClient natClient = new NatClient(config); // 初始化Client

        DeleteNatRuleRequest request = new DeleteNatRuleRequest();
        request.setNatId("nat-b58rnkn1g98h"); // 需要删除snat 的nat
        request.setRuleId("rule-zrsaybxm7nrn"); // 需要删除的snat规则ID

        try {
            natClient.deleteSnatRule(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
