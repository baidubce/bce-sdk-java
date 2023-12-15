package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.GetSslVpnServerResponse;

public class ExampleGetSslVpnServer {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        String vpnId = "vpn-b1z6gjrhm1an";  // VPN的ID

        try {
            GetSslVpnServerResponse getSslVpnServerResponse = vpnClient.getSslVpnServer(vpnId);
            System.out.println("getSslVpnServerResponse = " + getSslVpnServerResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
