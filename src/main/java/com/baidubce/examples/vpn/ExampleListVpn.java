package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.ListVpnRequest;
import com.baidubce.services.vpn.model.ListVpnResponse;

public class ExampleListVpn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        ListVpnRequest listVpnRequest = new ListVpnRequest();
        listVpnRequest.setVpcId("vpc-ut211bnd95cg");    // VPN所属VPC ID
        listVpnRequest.setMarker("");   // 批量获取列表的查询的起始位置，是一个由系统生成的字符串
        listVpnRequest.setMaxKeys(10);  // 每页包含的最大数量，最大数量通常不超过1000，缺省值为1000
        listVpnRequest.setEip("100.89.0.221");   // VPN绑定的eip地址

        // VPN网关类型，值“IPSec”返回IPsec-VPN网关，值“SSL”返回SSL-VPN网关，默认为空，返回所有类型的VPN网关
        listVpnRequest.setType("IPSec");

        try {
            ListVpnResponse listVpnResponse = vpnClient.listVpns(listVpnRequest);
            System.out.println("listVpnResponse = " + listVpnResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
