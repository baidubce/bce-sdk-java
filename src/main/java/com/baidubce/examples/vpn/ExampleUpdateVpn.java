package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.UpdateVpnRequest;

import java.util.UUID;

public class ExampleUpdateVpn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        UpdateVpnRequest updateVpnRequest = new UpdateVpnRequest();
        updateVpnRequest.setVpnId("vpn-bwc4p652n57b");  // vpn的ID

        // VPN名称,不能取值"default",长度不超过65个字符，可由数字，字符，下划线组成
        updateVpnRequest.setVpnName("java_sdk_test_update_vpn");

        updateVpnRequest.setDescription("test update vpn description.");   // vpn的描述，不超过200字符
        updateVpnRequest.setClientToken(UUID.randomUUID().toString());  // 幂等性Token

        try {
            vpnClient.updateVpn(updateVpnRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
