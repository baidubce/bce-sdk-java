/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.blb.blb;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.BlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.ModifyBlbAttributesRequest;

public class ExampleModifyBlbAttributes {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.bj.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        BlbClient blbClient = new BlbClient(config); // 初始化BlbClient

        ModifyBlbAttributesRequest modifyBlbAttributesRequest = new ModifyBlbAttributesRequest();
        modifyBlbAttributesRequest.setBlbId("lb-77d58dc4"); // 待更新的LoadBalancer的ID
        modifyBlbAttributesRequest.setName("blb_1129_1_1"); // LoadBalancer的名称

        try {
            blbClient.modifyBlbAttributes(modifyBlbAttributesRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
