package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.IkeConfig;
import com.baidubce.services.vpn.model.IpsecConfig;
import com.baidubce.services.vpn.model.UpdateVpnConnRequest;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.UUID;

public class ExampleUpdateVpnConn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        UpdateVpnConnRequest updateVpnConnRequest = new UpdateVpnConnRequest();
        updateVpnConnRequest.setVpnConnId("vpnconn-c2ugxt6evfkm");  // vpn隧道的ID
        updateVpnConnRequest.setVpnId("vpn-bwc4p652n57b");  // vpn隧道所属vpn的ID

        // 共享秘钥，8～17位字符，英文、数字和符号必须同时存在，符号仅限!@#$%^*()_
        updateVpnConnRequest.setSecretKey("abc123456!");

        // 本端网络cidr列表
        List<String> localSubnets = Lists.newArrayList("192.168.0.0/20");
        updateVpnConnRequest.setLocalSubnets(localSubnets);

        updateVpnConnRequest.setRemoteIp("180.76.121.30");  // 对端VPN网关公网IP

        // 对端网络cidr列表
        List<String> remoteSubnets = Lists.newArrayList("192.168.100.0/24");
        updateVpnConnRequest.setRemoteSubnets(remoteSubnets);

        // VPN隧道名称，大小写字母、数字以及-_/.特殊字符，必须以字母开头，长度1-65
        updateVpnConnRequest.setVpnConnName("test_vpn_conn_update");

        updateVpnConnRequest.setDescription("update vpn conn description.");    // 描述

        // IKE配置
        IkeConfig ikeConfig = new IkeConfig();
        updateVpnConnRequest.setIkeConfig(ikeConfig);

        // IPSec配置
        IpsecConfig ipsecConfig = new IpsecConfig();
        updateVpnConnRequest.setIpsecConfig(ipsecConfig);

        updateVpnConnRequest.setClientToken(UUID.randomUUID().toString());  // 幂等性Token

        try {
            vpnClient.updateVpnConn(updateVpnConnRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
