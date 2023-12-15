package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.GetVpnResponse;

public class ExampleGetVpn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        String vpnId = "vpn-bwc4p652n57b";  // vpn的ID

        try {
            GetVpnResponse getVpnResponse = vpnClient.getVpn(vpnId);
            System.out.println("getVpnResponse = " + getVpnResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
