package com.baidubce.examples.peerconn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.peerconn.PeerConnClient;
import com.baidubce.services.peerconn.PeerConnClientConfiguration;
import com.baidubce.services.peerconn.model.PeerConnIdRequest;

import java.util.UUID;

public class ExampleProcessPeerConnApply {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        PeerConnClientConfiguration config = new PeerConnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        PeerConnClient peerConnClient = new PeerConnClient(config); // 初始化Client

        // 接收对等连接申请
        PeerConnIdRequest peerConnIdRequest = new PeerConnIdRequest();
        peerConnIdRequest.setPeerConnId("peerconn-4pj6d45dv79n");  // 对等连接的ID
        peerConnIdRequest.setClientToken(UUID.randomUUID().toString()); // 幂等性Token

        try {
            peerConnClient.accept(peerConnIdRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

        // 拒绝对等连接申请
        PeerConnIdRequest peerConnIdRequest1 = new PeerConnIdRequest();
        peerConnIdRequest1.setPeerConnId("peerconn-2a2p0cftceg5");    // 对等连接的ID
        peerConnIdRequest1.setClientToken(UUID.randomUUID().toString()); // 幂等性Token

        try {
            peerConnClient.reject(peerConnIdRequest1);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
