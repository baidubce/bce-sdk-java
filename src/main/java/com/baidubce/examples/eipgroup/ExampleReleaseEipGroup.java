/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eipgroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eipgroup.EipGroupClient;
import com.baidubce.services.eipgroup.EipGroupClientConfiguration;
import com.baidubce.services.eipgroup.model.EipGroupOperateRequest;

public class ExampleReleaseEipGroup {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        EipGroupClientConfiguration config = new EipGroupClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipGroupClient eipGroupClient = new EipGroupClient(config); // 初始化EipGroupClient

        EipGroupOperateRequest request = new EipGroupOperateRequest();
        request.setId("eg-aXT5LIDG"); // eipgroup的实例id
        try {
            eipGroupClient.releaseEipGroup(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}

