package com.baidubce.examples.nat;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.model.Billing;
import com.baidubce.services.bcc.model.Reservation;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.nat.NatClient;
import com.baidubce.services.nat.NatClientConfiguration;
import com.baidubce.services.nat.model.CreateNatRequest;
import com.baidubce.services.nat.model.CreateNatResponse;

import java.util.ArrayList;
import java.util.List;

public class ExampleCreateNat {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        NatClientConfiguration config = new NatClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        NatClient natClient = new NatClient(config); // 初始化Client

        CreateNatRequest request = new CreateNatRequest(); // 创建NAT 请求参数
        Reservation reservation = new Reservation(); // 保留信息，支付方式为后支付时不需要设置，预支付时必须设置
        reservation.setReservationLength(1); // 时长
        reservation.setReservationTimeUnit("month"); // 单位
        Billing billing = new Billing();
        billing.setPaymentTiming("Prepaid"); // 预付费（Prepaid）和后付费（Postpaid）
        billing.setReservation(reservation);
        request.setBilling(billing);
        request.setName("NatTest"); // nat名字
        // NAT网关的大小，
        //  有small(最多支持绑定5个公网IP)、
        //  medium(最多支持绑定10个公网IP)、
        //  large(最多支持绑定15个公网IP)三种；该参数和cuNum只能二选一
//        request.setSpec("small");
        request.setCuNum(2); // NAT网关的CU数量，该参数和spec只能二选一
        request.setVpcId("vpc-a4sg6vsfzbra"); // 创建nat网关所在的vpcId
        List<TagModel> tags = new ArrayList<TagModel>(); // 标签信息
        tags.add(new TagModel().withTagKey("testKey").withTagValue("testValue"));
        request.setTags(tags);

        try {
            CreateNatResponse createNatResponse = natClient.createNat(request);
            System.out.println(createNatResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
