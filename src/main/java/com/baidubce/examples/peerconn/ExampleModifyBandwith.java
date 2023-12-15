package com.baidubce.examples.peerconn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.peerconn.PeerConnClient;
import com.baidubce.services.peerconn.PeerConnClientConfiguration;
import com.baidubce.services.peerconn.model.ModifyBandwidthRequest;

import java.util.UUID;

public class ExampleModifyBandwith {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        PeerConnClientConfiguration config = new PeerConnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        PeerConnClient peerConnClient = new PeerConnClient(config); // 初始化Client

        ModifyBandwidthRequest modifyBandwidthRequest = new ModifyBandwidthRequest();
        modifyBandwidthRequest.setPeerConnId("peerconn-dtska37u3eih"); // 待升降配的对等连接ID
        modifyBandwidthRequest.setNewBandwidthInMbps(400);   // 升降级的带宽
        modifyBandwidthRequest.setClientToken(UUID.randomUUID().toString()); // 幂等性Token

        try {
            peerConnClient.modifyBandwith(modifyBandwidthRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
