package com.baidubce.examples.csn;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.csn.CsnClient;
import com.baidubce.services.csn.model.CreateCsnBpRequest;
import com.baidubce.services.csn.model.CsnBpPriceRequest;
import com.baidubce.services.csn.model.CsnBpPriceResponse;

public class ExampleCsnBpPrice {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "csn.baidubce.com"; // CSN服务对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CsnClient csnClient = new CsnClient(config); // 初始化CsnClient

        CsnBpPriceRequest request = new CsnBpPriceRequest();
        request.setName("test_prepay");
        request.setBandwidth(10);
        request.setGeographicA("China");
        request.setGeographicB("China");

        CreateCsnBpRequest.Billing.Reservation reservation = CreateCsnBpRequest.Billing.Reservation.builder()
                .reservationTimeUnit("month")   // 时间单位，当前仅支持按月，取值month
                .reservationLength(1)   // 时长，[1,2,3,4,5,6,7,8,9,12,24,36]
                .build();
        CreateCsnBpRequest.Billing billing = CreateCsnBpRequest.Billing.builder()
                .paymentTiming("Prepaid")   // 付款时间，预支付（Prepaid）和后支付（Postpaid）
                .billingMethod("ByBandwidth")   // 计费方式
                .reservation(reservation)   // 保留信息
                .build();
        request.setBilling(billing);

        try {
            CsnBpPriceResponse response = csnClient.csnBpPrice(request); // 获取价格
            System.out.println("price = " + response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
