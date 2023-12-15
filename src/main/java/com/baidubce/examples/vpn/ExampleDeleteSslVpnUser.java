package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.DeleteSslVpnUserRequest;

import java.util.UUID;

public class ExampleDeleteSslVpnUser {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        DeleteSslVpnUserRequest deleteSslVpnUserRequest = new DeleteSslVpnUserRequest();
        deleteSslVpnUserRequest.setVpnId("vpn-b1z6gjrhm1an"); // VPN的ID
        deleteSslVpnUserRequest.setUserId("vpn-ssl-user-s6uiuv9wdf1j");   // SSL-VPN用户ID
        deleteSslVpnUserRequest.setClientToken(UUID.randomUUID().toString());  // 幂等性Token

        try {
            vpnClient.deleteSslVpnUser(deleteSslVpnUserRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
