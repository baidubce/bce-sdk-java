package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.CreateVpnConnRequest;
import com.baidubce.services.vpn.model.CreateVpnConnResponse;
import com.baidubce.services.vpn.model.IkeConfig;
import com.baidubce.services.vpn.model.IpsecConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExampleCreateVpnConn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        CreateVpnConnRequest createVpnConnRequest = new CreateVpnConnRequest();
        createVpnConnRequest.setVpnId("vpn-bwc4p652n57b");  // vpn的ID

        // 共享秘钥，8～17位字符，英文、数字和符号必须同时存在，符号仅限!@#$%^*()_
        createVpnConnRequest.setSecretKey("abc123456!");

        List<String> localSubnets = new ArrayList<String>();
        localSubnets.add("192.168.16.0/20");
        createVpnConnRequest.setLocalSubnets(localSubnets); // 本端网络cidr列表

        createVpnConnRequest.setRemoteIp("180.76.121.25");  // 对端VPN网关公网IP

        List<String> remoteSubnets = new ArrayList<String>();
        remoteSubnets.add("192.168.50.0/24");
        createVpnConnRequest.setRemoteSubnets(remoteSubnets);   // 对端网络cidr列表

        // VPN隧道名称，大小写字母、数字以及-_/.特殊字符，必须以字母开头，长度1-65
        createVpnConnRequest.setVpnConnName("java_sdk_test_conn");

        // IKE配置
        IkeConfig ikeConfig = new IkeConfig();
        createVpnConnRequest.setIkeConfig(ikeConfig);

        // IPSec配置
        IpsecConfig ipsecConfig = new IpsecConfig();
        createVpnConnRequest.setIpsecConfig(ipsecConfig);

        createVpnConnRequest.setClientToken(UUID.randomUUID().toString());  // 幂等性Token

        try {
            CreateVpnConnResponse createVpnConnResponse = vpnClient.createVpnConn(createVpnConnRequest);
            System.out.println("createVpnConnResponse = " + createVpnConnResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
