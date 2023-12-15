package com.baidubce.examples.lbdc;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.lbdc.LbdcClient;
import com.baidubce.services.lbdc.model.RenewLbdcRequest;

/**
 * @author chenchangquan
 * @date 2023/11/27
 */
public class ExampleRenewLbdc {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        LbdcClient lbdcClient = new LbdcClient(config); // 初始化LbdcClient

        RenewLbdcRequest renewLbdcRequest = new RenewLbdcRequest();
        RenewLbdcRequest.BillingForRenew billingForRenew = new RenewLbdcRequest.BillingForRenew();
        RenewLbdcRequest.BillingForRenew.Reservation reservation = new RenewLbdcRequest.BillingForRenew.Reservation();
        reservation.setReservationLength(5); // 续费时长，单位为月
        billingForRenew.setReservation(reservation);
        renewLbdcRequest.setBilling(billingForRenew);

        try {
            lbdcClient.renewLbdc("bgw_group-1f1b6e17", renewLbdcRequest, "");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
