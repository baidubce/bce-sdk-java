package com.baidubce.examples.peerconn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.peerconn.PeerConnClient;
import com.baidubce.services.peerconn.PeerConnClientConfiguration;
import com.baidubce.services.peerconn.model.CreatePeerConnRequest;
import com.baidubce.services.peerconn.model.CreatePeerConnResponse;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.UUID;

public class ExampleCreatePeerConn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        PeerConnClientConfiguration config = new PeerConnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        PeerConnClient peerConnClient = new PeerConnClient(config); // 初始化Client

        CreatePeerConnRequest createPeerConnRequest = new CreatePeerConnRequest();
        createPeerConnRequest.setBandwidthInMbps(500);   // 对等连接的带宽
        createPeerConnRequest.setDescription("java sdk test peer conn.");   // 对等连接的备注
        createPeerConnRequest.setLocalIfName("peer_conn_local");    // 本端接口名称
        createPeerConnRequest.setLocalVpcId("vpc-nteqxm76n65t");  // 本端VPC的ID
        createPeerConnRequest.setPeerAccountId("PeerAccountId");    // 对端账户ID，只有在建立跨账号的对等连接时需要该字段
        createPeerConnRequest.setPeerVpcId("vpc-z9new983u7ne");  // 对等连接对端VPC的ID
        createPeerConnRequest.setPeerRegion("su");  // 对等连接的对端区域
        createPeerConnRequest.setPeerIfName("peer_conn_peer");  // 对等连接对端接口名称，只有本账号的对等连接才允许设置该字段

        // 计费信息
        Billing.Reservation reservation = new Billing.Reservation();
        reservation.setReservationLength(1);
        reservation.setReservationTimeUnit("month");
        Billing billing = new Billing();
        billing.setPaymentTiming("Prepaid");
        billing.setReservation(reservation);
        createPeerConnRequest.setBilling(billing);

        // 待创建的标签键值对列表。
        List<TagModel> tags = Lists.newArrayList(new TagModel().withTagKey("testKey").withTagValue("testValue"));
        createPeerConnRequest.setTags(tags);

        createPeerConnRequest.setClientToken(UUID.randomUUID().toString()); // 幂等性Token
        try {
            CreatePeerConnResponse createPeerConnResponse = peerConnClient.createPeerConn(createPeerConnRequest);
            System.out.println("createPeerConnResponse = " + createPeerConnResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
