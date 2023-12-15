package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.UpdateSslVpnUserRequest;

import java.util.UUID;

public class ExampleUpdateSslVpnUser {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        UpdateSslVpnUserRequest updateSslVpnUserRequest = new UpdateSslVpnUserRequest();
        updateSslVpnUserRequest.setVpnId("vpn-b1z6gjrhm1an"); // VPN的ID
        updateSslVpnUserRequest.setUserId("vpn-ssl-user-s6uiuv9wdf1j");   // SSL-VPN用户ID

        // 密码，8～17位字符，英文、数字和符号必须同时存在，符号仅限!@#$%^*(_
        updateSslVpnUserRequest.setPassword("1234567abc!");

        updateSslVpnUserRequest.setDescription("description-update");   // 描述
        updateSslVpnUserRequest.setClientToken(UUID.randomUUID().toString());  // 幂等性Token

        try {
            vpnClient.updateSslVpnUser(updateSslVpnUserRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
