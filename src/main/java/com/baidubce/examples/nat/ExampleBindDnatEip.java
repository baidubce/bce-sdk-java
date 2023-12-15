package com.baidubce.examples.nat;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.nat.NatClient;
import com.baidubce.services.nat.NatClientConfiguration;
import com.baidubce.services.nat.model.BindDnatEipRequest;

import java.util.Arrays;

public class ExampleBindDnatEip {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        NatClientConfiguration config = new NatClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        NatClient natClient = new NatClient(config); // 初始化Client

        BindDnatEipRequest request = new BindDnatEipRequest();
        request.setNatId("nat-b58rnkn1g98h"); // 需要绑定dnat EIP的nat
        request.setDnatEips(Arrays.asList("180.76.186.174")); // 公网IP
        try {
            natClient.bindDnatEip(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
