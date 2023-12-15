package com.baidubce.examples.nat;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.nat.NatClient;
import com.baidubce.services.nat.NatClientConfiguration;
import com.baidubce.services.nat.model.ListNatRequest;
import com.baidubce.services.nat.model.ListNatResponse;

public class ExampleListNat {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        NatClientConfiguration config = new NatClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        NatClient natClient = new NatClient(config); // 初始化Client

        ListNatRequest request = new ListNatRequest();
        request.setVpcId("vpcId"); // 根据vpcId查询vpc下面的nat列表

        try {
            ListNatResponse response = natClient.listNat(request);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
