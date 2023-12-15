package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.BatchCreateSslVpnUserRequest;
import com.baidubce.services.vpn.model.BatchCreateSslVpnUserResponse;
import com.baidubce.services.vpn.model.SslVpnUser;

import java.util.Arrays;
import java.util.UUID;

public class ExampleBatchCreateSslVpnUser {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        BatchCreateSslVpnUserRequest batchCreateSslVpnUserRequest = new BatchCreateSslVpnUserRequest();
        batchCreateSslVpnUserRequest.setVpnId("vpn-b1z6gjrhm1an");    // VPN的ID

        // SSL-VPN用户列表
        SslVpnUser sslVpnUser1 = new SslVpnUser();
        sslVpnUser1.setUserName("user1");   // 用户名，大小写字母、数字以及-_/.特殊字符，必须以字母开头，长度1-65
        sslVpnUser1.setPassword("qwe123Test.1");    // 密码，8～17位字符，英文、数字和符号必须同时存在，符号仅限!@#$%^*(_
        sslVpnUser1.setDescription("desc user1");   // 描述

        SslVpnUser sslVpnUser2 = new SslVpnUser();
        sslVpnUser2.setUserName("user2");
        sslVpnUser2.setPassword("qwe123Test.2");
        sslVpnUser2.setDescription("desc user2");
        batchCreateSslVpnUserRequest.setSslVpnUsers(Arrays.asList(sslVpnUser1, sslVpnUser2));

        batchCreateSslVpnUserRequest.setClientToken(UUID.randomUUID().toString());  // 幂等性Token

        try {
            BatchCreateSslVpnUserResponse batchCreateSslVpnUserResponse
                    = vpnClient.batchCreateSslVpnUser(batchCreateSslVpnUserRequest);
            System.out.println("batchCreateSslVpnUserResponse = " + batchCreateSslVpnUserResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
