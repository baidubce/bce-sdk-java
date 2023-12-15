package com.baidubce.examples.nat;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.nat.NatClient;
import com.baidubce.services.nat.NatClientConfiguration;
import com.baidubce.services.nat.model.CreateSnatRuleRequest;

import java.util.Arrays;

public class ExampleCreateSnatRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        NatClientConfiguration config = new NatClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        NatClient natClient = new NatClient(config); // 初始化Client

        CreateSnatRuleRequest request = new CreateSnatRuleRequest();
        request.setNatId("nat-b58rnkn1g98h"); // 需要创建snat 的nat
        request.setRuleName("rule2"); // 名称，由大小写字母、数字以及-_ /.特殊字符组成，必须以字母开头，长度1-65
        request.setSourceCIDR("192.168.1.0/24"); // 内网IP/网段
        request.setPublicIpsAddress(Arrays.asList("180.76.186.174")); // 公网IPs，关联在NAT网关SNAT上的EIP或共享带宽中的IPs

        try {
            natClient.createSnatRule(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
