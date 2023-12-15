/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.blb.appblb;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.AppBlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.ModifyBlbAttributesRequest;

public class ExampleModifyAppBlbAttributes {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.su.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AppBlbClient appBlbClient = new AppBlbClient(config); // 初始化AppBlbClient

        ModifyBlbAttributesRequest modifyBlbAttributesRequest = new ModifyBlbAttributesRequest();
        modifyBlbAttributesRequest.setBlbId("lb-9629aac9"); // 待更新的LoadBalancer的ID
        modifyBlbAttributesRequest.setName("blb_1204_1_1"); // LoadBalancer的名称

        try {
            appBlbClient.modifyBlbAttributes(modifyBlbAttributesRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
