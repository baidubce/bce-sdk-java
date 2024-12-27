package com.baidubce.examples.peerconn;

import java.util.UUID;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.peerconn.PeerConnClient;
import com.baidubce.services.peerconn.PeerConnClientConfiguration;
import com.baidubce.services.peerconn.model.SwitchPeerConnDeleteProtectRequest;

public class ExampleSwitchDeleteProtect {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        PeerConnClientConfiguration config = new PeerConnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        PeerConnClient peerConnClient = new PeerConnClient(config); // 初始化Client

        SwitchPeerConnDeleteProtectRequest request = new SwitchPeerConnDeleteProtectRequest();
        request.setPeerConnId("peerconn-ptvh4hbi6285");  // 对等连接的ID
        request.setDeleteProtect(false); // 开启释放保护
        request.setClientToken(UUID.randomUUID().toString()); // 幂等性Token

        try {
            peerConnClient.switchDeleteProtect(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
