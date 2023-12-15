package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.BindEipRequest;

import java.util.UUID;

public class ExampleBindEip {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        BindEipRequest bindEipRequest = new BindEipRequest();
        bindEipRequest.setVpnId("vpn-bwc4p652n57b");    // vpn的ID
        bindEipRequest.setEip("100.89.0.221");  // 需要绑定的eip
        bindEipRequest.setClientToken(UUID.randomUUID().toString());  // 幂等性Token

        try {
            vpnClient.bindEip(bindEipRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
