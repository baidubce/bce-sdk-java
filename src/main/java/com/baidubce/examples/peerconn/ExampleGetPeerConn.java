package com.baidubce.examples.peerconn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.peerconn.PeerConnClient;
import com.baidubce.services.peerconn.PeerConnClientConfiguration;
import com.baidubce.services.peerconn.model.GetPeerConnRequest;
import com.baidubce.services.peerconn.model.GetPeerConnResponse;

public class ExampleGetPeerConn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        PeerConnClientConfiguration config = new PeerConnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        PeerConnClient peerConnClient = new PeerConnClient(config); // 初始化Client

        GetPeerConnRequest getPeerConnRequest = new GetPeerConnRequest();
        getPeerConnRequest.setPeerConnId("peerconn-dtska37u3eih"); // 对等连接的ID

        // "initiator"表示发起端"acceptor"表示接受端，同region的对等连接可以据此进行详情查询。
        // 若不设置该参数，同region则随机返回一端信息。
        getPeerConnRequest.setRole("initiator");

        try {
            GetPeerConnResponse getPeerConnResponse = peerConnClient.getPeerConn(getPeerConnRequest);
            System.out.println("getPeerConnResponse = " + getPeerConnResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
