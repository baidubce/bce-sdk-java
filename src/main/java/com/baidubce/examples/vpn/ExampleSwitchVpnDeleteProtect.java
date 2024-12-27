package com.baidubce.examples.vpn;

import java.util.UUID;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.SwitchVpnDeleteProtectRequest;

public class ExampleSwitchVpnDeleteProtect {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        SwitchVpnDeleteProtectRequest request = new SwitchVpnDeleteProtectRequest();
        request.setVpnId("vpn-9c875b4065b5");  // vpn的ID
        request.setDeleteProtect(true);  // 开启释放保护
        request.setClientToken(UUID.randomUUID().toString());  // 幂等性Token

        try {
            vpnClient.switchDeleteProtect(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
