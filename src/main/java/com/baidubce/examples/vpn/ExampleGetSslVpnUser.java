package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.ListSslVpnUserRequest;
import com.baidubce.services.vpn.model.ListSslVpnUserResponse;

public class ExampleGetSslVpnUser {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        ListSslVpnUserRequest listSslVpnUserRequest = new ListSslVpnUserRequest();
        listSslVpnUserRequest.setVpnId("vpn-b1z6gjrhm1an");   // SSL-VPN的ID
        listSslVpnUserRequest.setMarker("");    // 批量获取列表的查询起始位置，是一个由系统生成的字符串
        listSslVpnUserRequest.setMaxKeys(10);   // 每页包含的最大数量，最大数量通常不超过1000，缺省值为1000
        listSslVpnUserRequest.setUserName("user1");  // SSL-VPN用户名称

        try {
            ListSslVpnUserResponse listSslVpnUserResponse = vpnClient.getSslVpnUser(listSslVpnUserRequest);
            System.out.println("listSslVpnUserResponse = " + listSslVpnUserResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
