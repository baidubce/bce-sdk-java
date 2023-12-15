/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.subnet;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.subnet.SubnetClient;
import com.baidubce.services.subnet.SubnetClientConfiguration;
import com.baidubce.services.subnet.model.GetSubnetDetailResponse;

public class ExampleGetSubnet {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        SubnetClientConfiguration config = new SubnetClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        SubnetClient subnetClient = new SubnetClient(config); // 初始化SubnetClient


        try {
            GetSubnetDetailResponse getSubnetDetailResponse = subnetClient.getSubnetDetail("sbn-qz55vemw0n40");
            System.out.println(getSubnetDetailResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
