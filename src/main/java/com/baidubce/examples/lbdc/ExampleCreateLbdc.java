package com.baidubce.examples.lbdc;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.lbdc.LbdcClient;
import com.baidubce.services.lbdc.model.CreateLbdcRequest;
import com.baidubce.services.lbdc.model.CreateLbdcResponse;

/**
 * @author chenchangquan
 * @date 2023/11/27
 */
public class ExampleCreateLbdc {

    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        LbdcClient lbdcClient = new LbdcClient(config); // 初始化LbdcClient

        CreateLbdcRequest createLbdcRequest = new CreateLbdcRequest();
        createLbdcRequest.setName("lbdcName"); // 创建的lbdc名称
        createLbdcRequest.setType("4Layer"); // 创建的lbdc类型，4Layer或7Layer
        createLbdcRequest.setCcuCount(2); // 创建的lbdc的ccu数量 (ccu代表集群的性能容量单位)
        CreateLbdcRequest.BillingForCreate billingForCreate = new CreateLbdcRequest.BillingForCreate();
        billingForCreate.setPaymentTiming("Prepaid"); // 创建的lbdc的计费方式，Prepaid表示预付费
        CreateLbdcRequest.Reservation reservation = new CreateLbdcRequest.Reservation();
        reservation.setReservationLength(3); // 创建的lbdc的预付费时长的长度，单位为月
        billingForCreate.setReservation(reservation);
        createLbdcRequest.setRenewReservation(reservation); // 创建的lbdc的续费时长的长度，单位为月
        createLbdcRequest.setBilling(billingForCreate);

        try {
            CreateLbdcResponse response = lbdcClient.createLbdc(createLbdcRequest, "");
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }


}
