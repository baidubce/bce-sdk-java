/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.bcc;

import com.baidubce.BceClientConfiguration;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.BccClient;
import com.baidubce.services.bcc.BccClientConfiguration;
import com.baidubce.services.bcc.model.TagModel;
import com.baidubce.services.bcc.model.reversed.CreateReservedInstanceResponse;
import com.baidubce.services.bcc.model.reversed.CreateReservedInstancesRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

@Slf4j
public class ExampleCreateReservedInstance {
    public static void main(String[] args) {
        // 设置您的ak、sk和要访问的endpoint
        String endpoint = "bcc.bj.baidubce.com";
        String ak = "ak";
        String sk = "sk";

        // 设置默认配置
        BceClientConfiguration bccClientConfiguration = new BccClientConfiguration()
                .withProtocol(Protocol.HTTP)
                .withCredentials(new DefaultBceCredentials(ak, sk))
                .withEndpoint(endpoint);

        // 创建bcc client
        BccClient bccClient = new BccClient(bccClientConfiguration);

        CreateReservedInstancesRequest request = new CreateReservedInstancesRequest();
        request.setReservedInstanceName("bj-test");
        request.setScope("AZ");
        request.setZoneName("cn-bj-a");
        request.setSpec("bcc.c5.c2m8");
        request.setOfferingType("FullyPrepay");
        request.setInstanceCount(1);
        request.setReservedInstanceCount(1);
        request.setReservedInstanceTime(1);
        request.setReservedInstanceTimeUnit("month");
        request.setAutoRenewTimeUnit("month");
        request.setAutoRenewTime(1);
        request.setAutoRenew(true);

        TagModel tagModel = new TagModel();
        tagModel.setTagKey("testKey");
        tagModel.setTagValue("testValue");
        request.setTags(Collections.singletonList(tagModel));

        CreateReservedInstanceResponse response = bccClient.createReservedInstances(request);
        log.debug("CreateReservedInstanceResponse: {}", response);
    }
}
