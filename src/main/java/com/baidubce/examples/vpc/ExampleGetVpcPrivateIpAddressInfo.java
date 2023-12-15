/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.vpc;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpc.VpcClient;
import com.baidubce.services.vpc.VpcClientConfiguration;
import com.baidubce.services.vpc.model.GetVpcPrivateAddressInfoResponse;
import com.baidubce.services.vpc.model.GetVpcPrivateIpAddressInfoRequest;

public class ExampleGetVpcPrivateIpAddressInfo {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpcClientConfiguration config = new VpcClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpcClient vpcClient = new VpcClient(config); // 初始化VpcClient

        GetVpcPrivateIpAddressInfoRequest getVpcPrivateIpAddressInfoRequest = new GetVpcPrivateIpAddressInfoRequest();
        getVpcPrivateIpAddressInfoRequest.setVpcId("vpc-xysha3j7gce1"); // vpcId
        getVpcPrivateIpAddressInfoRequest.setPrivateIpRange("192.168.0.0-192.168.0.80"); // 查询该ip区间内Ip的使用信息

        try {
            GetVpcPrivateAddressInfoResponse response =
                    vpcClient.getVpcPrivateIpAddressInfo(getVpcPrivateIpAddressInfoRequest);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

