package com.baidubce.examples.vpn;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.vpn.VpnClient;
import com.baidubce.services.vpn.VpnClientConfiguration;
import com.baidubce.services.vpn.model.RenewVpnRequest;

import java.util.UUID;

public class ExampleRenewVpn {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpnClientConfiguration config = new VpnClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpnClient vpnClient = new VpnClient(config); // 初始化Client

        RenewVpnRequest renewVpnRequest = new RenewVpnRequest();
        renewVpnRequest.setVpnId("vpn-biqctbbdzrz2");

        // 订单信息
        Billing billing = new Billing();
        billing.setPaymentTiming("Prepaid");    // 付款时间，预支付（Prepaid）和后支付（Postpaid）
        Billing.Reservation reservation = new Billing.Reservation(); // 保留信息，支付方式为后支付时不需要设置，预支付时必须设置
        reservation.setReservationLength(1);    // 时长，[1,2,3,4,5,6,7,8,9,12,24,36]
        reservation.setReservationTimeUnit("month");    // 时间单位，month，当前仅支持按月
        billing.setReservation(reservation);
        renewVpnRequest.setBilling(billing);

        renewVpnRequest.setClientToken(UUID.randomUUID().toString());  // 幂等性Token

        try {
            vpnClient.renewVpn(renewVpnRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
