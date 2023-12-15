/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.subnet;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.subnet.SubnetClient;
import com.baidubce.services.subnet.SubnetClientConfiguration;
import com.baidubce.services.subnet.model.CreateIpReservedReq;
import com.baidubce.services.subnet.model.CreateIpReservedResponse;

public class ExampleCreateIpReserve {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        SubnetClientConfiguration config = new SubnetClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        SubnetClient subnetClient = new SubnetClient(config); // 初始化SubnetClient

        CreateIpReservedReq createIpReservedReq = new CreateIpReservedReq();
        createIpReservedReq.setSubnetId("sbn-6ha6gp1vczuv");
        createIpReservedReq.setIpCidr("192.168.0.0/30");
        createIpReservedReq.setDescription("aaa");

        try {
            CreateIpReservedResponse createIpReservedResponse = subnetClient.createIpReserved(createIpReservedReq);
            System.out.println(createIpReservedResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}
