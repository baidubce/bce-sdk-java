/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eipgroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.eipgroup.EipGroupClient;
import com.baidubce.services.eipgroup.EipGroupClientConfiguration;
import com.baidubce.services.eipgroup.model.CreateEipGroupRequest;
import com.baidubce.services.eipgroup.model.IdResponse;
import com.google.common.collect.Lists;

public class ExampleCreateEipGroup {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        EipGroupClientConfiguration config = new EipGroupClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipGroupClient eipGroupClient = new EipGroupClient(config); // 初始化EipGroupClient

        CreateEipGroupRequest request = new CreateEipGroupRequest();
        Billing.Reservation reservation = new Billing.Reservation();
        reservation.setReservationLength(1); // eipGroup购买时长
        reservation.setReservationTimeUnit("Month"); // eipGroup购买时间单位
        Billing billing = new Billing();
        billing.setPaymentTiming("Prepaid"); // eipGroup购买计费方式，预付费或后付费
        billing.setReservation(reservation);
        request.setBilling(billing);
        request.setName("EipGroupTest"); // eipGroup的名称
        request.setEipCount(2); // eipGroup内ip数量
        request.setBandwidthInMbps(10); // eipGroup的带宽
        request.setResourceGroupId("RESG-J7PdULjguvB"); // eipGroup要绑定的资源组

        TagModel tagModel = new TagModel();
        tagModel.setTagKey("tagK");
        tagModel.setTagValue("tagV");
        request.setTags(Lists.newArrayList(tagModel));  // eipGroup要绑定的标签

        try {
            IdResponse response = eipGroupClient.createEipGroup(request);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}

