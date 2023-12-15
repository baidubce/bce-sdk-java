package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.UnBindEipRequest;

import java.util.UUID;

public class ExampleUnBindEip {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        UnBindEipRequest unBindEipRequest = new UnBindEipRequest();
        unBindEipRequest.setVpnId("vpn-bwc4p652n57b");  // vpn的ID
        unBindEipRequest.setClientToken(UUID.randomUUID().toString());  // 幂等性Token

        try {
            vpnClient.unBindEip(unBindEipRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
