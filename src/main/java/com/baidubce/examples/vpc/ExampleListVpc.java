/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.vpc;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vpc.VpcClient;
import com.baidubce.services.vpc.VpcClientConfiguration;
import com.baidubce.services.vpc.model.ListVpcResponse;

public class ExampleListVpc {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        VpcClientConfiguration config = new VpcClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        VpcClient vpcClient = new VpcClient(config); // 初始化VpcClient

        try {
            ListVpcResponse response = vpcClient.listVpcs();
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

