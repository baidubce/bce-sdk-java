/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.bcc;

import com.baidubce.BceClientConfiguration;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bcc.BccClient;
import com.baidubce.services.bcc.BccClientConfiguration;
import com.baidubce.services.bcc.model.reversed.ModifyReservedInstancesResponse;
import com.baidubce.services.bcc.model.reversed.ModifyReservedInstancesRequest;
import com.baidubce.services.bcc.model.reversed.ModifyReservedInstancesRequest.ReservedInstance;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

@Slf4j
public class ExampleModifyReservedInstances {

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

        ModifyReservedInstancesRequest request = new ModifyReservedInstancesRequest();

        // 修改名称
        ReservedInstance modifyName = new ReservedInstance();
        modifyName.setReservedInstanceId("r-qt4ItfUP");
        modifyName.setReservedInstanceName("update-reserved-instance");
        request.setReservedInstances(Collections.singletonList(modifyName));

        ModifyReservedInstancesResponse response = bccClient.modifyReservedInstances(request);
        log.debug("ModifyReservedInstancesResponse:{}", response);
    }
}
