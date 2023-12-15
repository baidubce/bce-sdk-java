package com.baidubce.examples.peerconn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.peerconn.PeerConnClient;
import com.baidubce.services.peerconn.PeerConnClientConfiguration;
import com.baidubce.services.peerconn.model.ModifyPeerConnRequest;

import java.util.UUID;

public class ExampleModifyPeerConn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        PeerConnClientConfiguration config = new PeerConnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        PeerConnClient peerConnClient = new PeerConnClient(config); // 初始化Client

        ModifyPeerConnRequest modifyPeerConnRequest = new ModifyPeerConnRequest();
        modifyPeerConnRequest.setPeerConnId("peerconn-dtska37u3eih");  // 对等连接的ID
        modifyPeerConnRequest.setLocalIfId("qpif-v305m2suuzmq");    // 对等连接的接口ID 不可更改
        modifyPeerConnRequest.setLocalIfName("peer_conn_local_update"); // 本端接口名称
        modifyPeerConnRequest.setDescription("update peer conn description");    // 备注
        modifyPeerConnRequest.setClientToken(UUID.randomUUID().toString()); // 幂等性Token

        try {
            peerConnClient.modifyPeerConn(modifyPeerConnRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
