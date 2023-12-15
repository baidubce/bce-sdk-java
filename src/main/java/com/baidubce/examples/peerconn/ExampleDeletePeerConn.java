package com.baidubce.examples.peerconn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.peerconn.PeerConnClient;
import com.baidubce.services.peerconn.PeerConnClientConfiguration;
import com.baidubce.services.peerconn.model.PeerConnIdRequest;

import java.util.UUID;

public class ExampleDeletePeerConn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        PeerConnClientConfiguration config = new PeerConnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        PeerConnClient peerConnClient = new PeerConnClient(config); // 初始化Client

        PeerConnIdRequest peerConnIdRequest = new PeerConnIdRequest();
        peerConnIdRequest.setPeerConnId("id");  // 对等连接的ID
        peerConnIdRequest.setClientToken(UUID.randomUUID().toString()); // 幂等性Token

        try {
            peerConnClient.release(peerConnIdRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
