package com.baidubce.examples.nat;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.model.Billing;
import com.baidubce.services.bcc.model.Reservation;
import com.baidubce.services.nat.NatClient;
import com.baidubce.services.nat.NatClientConfiguration;
import com.baidubce.services.nat.model.PurchaseReservedNatRequest;

public class ExampleRenewNat {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        NatClientConfiguration config = new NatClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        NatClient natClient = new NatClient(config); // 初始化Client

        PurchaseReservedNatRequest request = new PurchaseReservedNatRequest();
        request.setNatId("nat-b58rnkn1g98h"); // 需要续费的natId, 只有预付费nat才能续费
        Billing billing = new Billing(); // 续费信息
        Reservation reservation = new Reservation();
        reservation.setReservationLength(1); // 续费时长
        reservation.setReservationTimeUnit("month"); // 续费单位
        billing.setReservation(reservation);
        request.setBilling(billing);

        try {
            natClient.purchaseReservedNat(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
