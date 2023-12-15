package com.baidubce.examples.peerconn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.peerconn.PeerConnClient;
import com.baidubce.services.peerconn.PeerConnClientConfiguration;
import com.baidubce.services.peerconn.model.PurchaseReservedPeerConnRequest;

import java.util.UUID;

public class ExamplePurchaseReserved {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        PeerConnClientConfiguration config = new PeerConnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        PeerConnClient peerConnClient = new PeerConnClient(config); // 初始化Client

        PurchaseReservedPeerConnRequest purchaseReservedPeerConnRequest = new PurchaseReservedPeerConnRequest();
        purchaseReservedPeerConnRequest.setPeerConnId("peerconn-wchhrady3my3");    // 续费的对等连接

        // 订单信息
        Billing.Reservation reservation = new Billing.Reservation();
        reservation.setReservationLength(1);
        reservation.setReservationTimeUnit("month");

        Billing billing = new Billing();
        billing.setReservation(reservation);
        purchaseReservedPeerConnRequest.setBilling(billing);

        purchaseReservedPeerConnRequest.setClientToken(UUID.randomUUID().toString()); // 幂等性Token

        try {
            peerConnClient.purchaseReserved(purchaseReservedPeerConnRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
