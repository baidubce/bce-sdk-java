package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.CreateSslVpnServerRequest;
import com.baidubce.services.vpn.model.CreateSslVpnServerResponse;
import com.google.common.collect.Lists;

import java.util.UUID;

public class ExampleCreateSslVpnServer {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        CreateSslVpnServerRequest createSslVpnServerRequest = new CreateSslVpnServerRequest();
        createSslVpnServerRequest.setVpnId("vpn-b1z6gjrhm1an");   // VPN的ID

        // SSL-VPN服务端实例名称，大小写字母、数字以及-_/.特殊字符，必须以字母开头，长度1-65
        createSslVpnServerRequest.setSslVpnServerName("java_sdk_test_ssl_vpn_server");

        // SSL-VPN服务端接口类型。取值[tap, tun]，默认为 tap
        createSslVpnServerRequest.setInterfaceType("tap");

        createSslVpnServerRequest.setLocalSubnets(Lists.newArrayList("192.168.50.0/24")); // 本端网络CIDR列表
        createSslVpnServerRequest.setRemoteSubnet("192.168.10.0/24"); // 客户端网络CIDR
        createSslVpnServerRequest.setClientDns("100.88.0.83");    // 客户端的DNS地址

        createSslVpnServerRequest.setClientToken(UUID.randomUUID().toString());  // 幂等性Token

        try {
            CreateSslVpnServerResponse createSslVpnServerResponse
                    = vpnClient.createSslVpnServer(createSslVpnServerRequest);
            System.out.println("createSslVpnServerResponse = " + createSslVpnServerResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
