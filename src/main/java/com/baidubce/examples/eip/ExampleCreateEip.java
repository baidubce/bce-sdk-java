/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eip;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.eip.EipClient;
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.eip.model.CreateEipRequest;
import com.baidubce.services.eip.model.CreateEipResponse;
import com.google.common.collect.Lists;

public class ExampleCreateEip {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipClient eipClient = new EipClient(config); // 初始化EipClient

        CreateEipRequest request = new CreateEipRequest();
        request.setBandwidthInMbps(1); // eip带宽
        request.setName("cdhTest"); // eip名称
        Billing billing = new Billing();
        billing.setPaymentTiming("postpaid"); // eip计费方式。预付费或后付费
        billing.setBillingMethod("ByBandwidth"); // eip后付费具体计费方案
        request.setBilling(billing);

        request.setResourceGroupId("RESG-J7PdULjguvB"); // eip要绑定的资源组

        TagModel tagModel = new TagModel();
        tagModel.setTagKey("tagK");
        tagModel.setTagValue("tagV");
        request.setTags(Lists.newArrayList(tagModel));  // eip要绑定的标签

        try {
            CreateEipResponse response = eipClient.createEip(request);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}

