package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.DeleteVpnRequest;

public class ExampleDeleteVpn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        DeleteVpnRequest deleteVpnRequest = new DeleteVpnRequest();
        deleteVpnRequest.setVpnId("vpn-ku4cxya6nisq"); // 需要删除的vpnId

        try {
            vpnClient.deleteVpn(deleteVpnRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
