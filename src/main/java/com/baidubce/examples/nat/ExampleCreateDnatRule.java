package com.baidubce.examples.nat;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.nat.NatClient;
import com.baidubce.services.nat.NatClientConfiguration;
import com.baidubce.services.nat.model.CreateDnatRuleRequest;

public class ExampleCreateDnatRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        NatClientConfiguration config = new NatClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        NatClient natClient = new NatClient(config); // 初始化Client

        CreateDnatRuleRequest request = new CreateDnatRuleRequest();
        request.setNatId("nat-b58rnkn1g98h"); // natID
        request.setRuleName("ruleTest"); // 名称，由大小写字母、数字以及-_ /.特殊字符组成，必须以字母开头，长度1-65
        request.setPublicIpAddress("180.76.186.174"); // 公网IP，关联在NAT网关DNAT上的EIP或共享带宽中的IP
        request.setPrivateIpAddress("192.168.1.1"); // 内网IP
        request.setProtocol("all"); // 协议，支持TCP、UDP、all

        try {
            natClient.createDnatRule(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
