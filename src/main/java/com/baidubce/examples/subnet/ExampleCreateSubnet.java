/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.subnet;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.subnet.SubnetClient;
import com.baidubce.services.subnet.SubnetClientConfiguration;
import com.baidubce.services.subnet.model.CreateSubnetRequest;
import com.baidubce.services.subnet.model.CreateSubnetResponse;

public class ExampleCreateSubnet {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        SubnetClientConfiguration config = new SubnetClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        SubnetClient subnetClient = new SubnetClient(config); // 初始化SubnetClient

        CreateSubnetRequest createSubnetRequest = new CreateSubnetRequest();
        createSubnetRequest.setVpcId("vpc-b9ycwxxisrb7");
        createSubnetRequest.setCidr("192.168.1.0/24");
        createSubnetRequest.setName("testSubnet");
        createSubnetRequest.setZoneName("cn-bj-a");

        try {
            CreateSubnetResponse createSubnetResponse = subnetClient.createSubnet(createSubnetRequest);
            System.out.println(createSubnetResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
