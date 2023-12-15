package com.baidubce.examples.nat;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.nat.NatClient;
import com.baidubce.services.nat.NatClientConfiguration;
import com.baidubce.services.nat.model.UpdateDnatRuleRequest;

public class ExampleUpdateDnatRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        NatClientConfiguration config = new NatClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        NatClient natClient = new NatClient(config); // 初始化Client

        UpdateDnatRuleRequest request = new UpdateDnatRuleRequest();
        request.setNatId("nat-b58rnkn1g98h"); // dnat所属natId
        request.setRuleId(""); // dnat的规则ID
        request.setRuleName("rule2"); // DNAT规则名字
        request.setProtocol("UDP"); // 协议，支持TCP、UDP、all
        request.setPrivatePort(80); // 公网端口
        request.setPublicPort(80); // 内网端口
        request.setPrivateIpAddress("172.16.0.101"); // 内网IP

        try {
            natClient.updateDnatRule(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
