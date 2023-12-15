/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.blb.appblb;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.AppBlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.CreateBlbRequest;
import com.baidubce.services.blb.model.CreateBlbResponse;

public class ExampleCreateAppBlb {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.su.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AppBlbClient appBlbClient = new AppBlbClient(config); // 初始化AppBlbClient

        CreateBlbRequest createBlbRequest = new CreateBlbRequest();
        createBlbRequest.setName("blb_1204_1"); // LoadBalancer的名称
        createBlbRequest.setDesc("desc info"); // LoadBalancer实例的描述
        createBlbRequest.setVpcId("vpc-syx4vuhy9z0f"); // LoadBalancer实例所属VPC的ID
        createBlbRequest.setSubnetId("sbn-xgbjx1ecjf22"); // LoadBalancer实例所属子网的ID

        try {
            CreateBlbResponse createBlbResponse = appBlbClient.createBlb(createBlbRequest);
            System.out.println(createBlbResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
