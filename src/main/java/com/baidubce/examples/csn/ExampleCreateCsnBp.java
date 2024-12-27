/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.csn;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.csn.CsnClient;
import com.baidubce.services.csn.model.CreateCsnBpRequest;
import com.baidubce.services.csn.model.CreateCsnBpResponse;
import com.baidubce.services.tag.model.Tag;
import com.google.common.collect.Lists;

import java.util.UUID;

public class ExampleCreateCsnBp {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "csn.baidubce.com"; // CSN服务对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        CsnClient csnClient = new CsnClient(config); // 初始化CsnClient

        // 预付费购买csnBp
        // 保留信息，支付方式为后付费时不需要设置，预付费时必须设置
        CreateCsnBpRequest.Billing.Reservation reservation = CreateCsnBpRequest.Billing.Reservation.builder()
                .reservationTimeUnit("month")   // 时间单位，当前仅支持按月，取值month
                .reservationLength(1)   // 时长，[1,2,3,4,5,6,7,8,9,12,24,36]
                .build();
        CreateCsnBpRequest.Billing prepaidBilling = CreateCsnBpRequest.Billing.builder()
                .paymentTiming("Prepaid")   // 付款时间，预支付（Prepaid）和后支付（Postpaid）
                .reservation(reservation)   // 保留信息
                .build();
        // 带宽包的互通类型，取值 [ center | center-edge | edge-edge ]，分别表示云间互通、云边互通、边边互通，默认为云间互通
        String interWorkType = "center";
        CreateCsnBpRequest prepaidCreateCsnBpRequest = CreateCsnBpRequest.builder()
                .name("prepaidCsnBpTest")   // 带宽包的名称，不能为空
                .bandwidth(100)     // 带宽包的带宽值，最大值为10000
                .geographicA("China")   // 网络实例所属的区域。取值 [ China | Asia-Pacific ]，分别表示中国大陆、亚太区域
                .geographicB("China")   // 另一个网络实例所属的区域。取值 [ China | Asia-Pacific ]，分别表示中国大陆、亚太区域
                .interworkType(interWorkType)    // 带宽包的互通类型
                .billing(prepaidBilling)    // 计费信息
                .tags(Lists.newArrayList(new Tag().withTagKey("test").withTagValue("test")))    // 标签信息，可为空
                .build();
        String prepaidClientToken = UUID.randomUUID().toString();   // 幂等性Token

        try {
            CreateCsnBpResponse prepaidCsnBp = csnClient.createCsnBp(prepaidCreateCsnBpRequest, prepaidClientToken);
            System.out.println("prepaidCsnBp = " + prepaidCsnBp);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

        // 后付费购买csnBp
        CreateCsnBpRequest.Billing postpaidBilling = CreateCsnBpRequest.Billing.builder()
                .paymentTiming("Postpaid")  // 付款时间，预支付（Prepaid）和后支付（Postpaid）
                .build();
        CreateCsnBpRequest postpaidCreateCsnBpRequest = CreateCsnBpRequest.builder()
                .name("postpaidCsnBpTest")  // 带宽包的名称，不能为空
                .bandwidth(100)     // 带宽包的带宽值，最大值为10000
                .geographicA("China")   // 网络实例所属的区域。取值 [ China | Asia-Pacific ]，分别表示中国大陆、亚太区域
                .geographicB("China")   // 另一个网络实例所属的区域。取值 [ China | Asia-Pacific ]，分别表示中国大陆、亚太区域
                .interworkType(interWorkType)    // 带宽包的互通类型
                .billing(postpaidBilling)
                .tags(Lists.newArrayList(new Tag().withTagKey("test").withTagValue("test")))    // 标签信息，可为空
                .build();
        String postpaidClientToken = UUID.randomUUID().toString();  // 幂等性Token

        try {
            CreateCsnBpResponse postpaidCsnBp = csnClient.createCsnBp(postpaidCreateCsnBpRequest, postpaidClientToken);
            System.out.println("postpaidCsnBp = " + postpaidCsnBp);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
