package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.DeleteSslVpnServerRequest;

import java.util.UUID;

public class ExampleDeleteSslVpnServer {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        DeleteSslVpnServerRequest deleteSslVpnServerRequest = new DeleteSslVpnServerRequest();
        deleteSslVpnServerRequest.setVpnId("vpn-b1z6gjrhm1an"); // VPN的ID
        deleteSslVpnServerRequest.setSslVpnServerId("sslvpn-2746vp5u7jvf");   // SSL-VPN服务端ID
        deleteSslVpnServerRequest.setClientToken(UUID.randomUUID().toString());  // 幂等性Token

        try {
            vpnClient.deleteSslVpnServer(deleteSslVpnServerRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
