/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eipgroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eipgroup.EipGroupClient;
import com.baidubce.services.eipgroup.EipGroupClientConfiguration;
import com.baidubce.services.eipgroup.model.MoveInRequest;

import java.util.Arrays;
import java.util.List;

public class ExampleMoveEipIntoGroup {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        EipGroupClientConfiguration config = new EipGroupClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipGroupClient eipGroupClient = new EipGroupClient(config); // 初始化EipGroupClient

        MoveInRequest request = new MoveInRequest();
        request.setId("eg-8yxeMV47"); // eipgroup实例id
        List<String> eips = Arrays.asList("100.88.9.177"); // 要移入的eip的ip地址
        request.setEips(eips);
        try {
            eipGroupClient.moveInEips(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}

