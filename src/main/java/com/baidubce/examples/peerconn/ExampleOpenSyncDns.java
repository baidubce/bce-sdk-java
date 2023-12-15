package com.baidubce.examples.peerconn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.peerconn.PeerConnClient;
import com.baidubce.services.peerconn.PeerConnClientConfiguration;
import com.baidubce.services.peerconn.model.SyncDnsRequest;

import java.util.UUID;

public class ExampleOpenSyncDns {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        PeerConnClientConfiguration config = new PeerConnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        PeerConnClient peerConnClient = new PeerConnClient(config); // 初始化Client

        SyncDnsRequest syncDnsRequest = new SyncDnsRequest();
        syncDnsRequest.setPeerConnId("peerconn-dtska37u3eih"); // 开启同步dns记录的对等连接
        syncDnsRequest.setRole("initiator");    // 发起端"initiator" 接收端"acceptor"
        syncDnsRequest.setClientToken(UUID.randomUUID().toString()); // 幂等性Token

        try {
            peerConnClient.openSyncDns(syncDnsRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
