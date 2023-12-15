/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eipgroup;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eipgroup.EipGroupClient;
import com.baidubce.services.eipgroup.EipGroupClientConfiguration;
import com.baidubce.services.eipgroup.model.BandwidthInMbpsRequest;

public class ExampleResizeBandwidthEipGroup {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "eip.bj.baidubce.com"; // 请求的服务region对应的域名

        EipGroupClientConfiguration config = new EipGroupClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EipGroupClient eipGroupClient = new EipGroupClient(config); // 初始化EipGroupClient

        BandwidthInMbpsRequest request = new BandwidthInMbpsRequest();
        request.setBandwidthInMbps(20); // eipgroup要变配的带宽值
        request.setId("eg-7e358ead"); // eipgroup实例id

        try {
            eipGroupClient.resizeBandwidth(request);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}

