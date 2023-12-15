package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.UpdateSslVpnServerRequest;
import com.google.common.collect.Lists;

import java.util.UUID;

public class ExampleUpdateSslVpnServer {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        UpdateSslVpnServerRequest updateSslVpnServerRequest = new UpdateSslVpnServerRequest();
        updateSslVpnServerRequest.setVpnId("vpn-b1z6gjrhm1an"); // VPN的ID
        updateSslVpnServerRequest.setSslVpnServerId("sslvpn-2746vp5u7jvf");   // SSL-VPN服务端ID

        // SSL-VPN服务端实例名称，大小写字母、数字以及-_/.特殊字符，必须以字母开头，长度1-65
        updateSslVpnServerRequest.setSslVpnServerName("testServer-update");

        updateSslVpnServerRequest.setLocalSubnets(Lists.newArrayList("192.168.40.0/24"));
        updateSslVpnServerRequest.setRemoteSubnet("192.168.100.0/24");
        updateSslVpnServerRequest.setClientDns("100.88.0.83");    // 客户端的DNS地址
        updateSslVpnServerRequest.setClientToken(UUID.randomUUID().toString());  // 幂等性Token

        try {
            vpnClient.updateSslVpnServer(updateSslVpnServerRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
