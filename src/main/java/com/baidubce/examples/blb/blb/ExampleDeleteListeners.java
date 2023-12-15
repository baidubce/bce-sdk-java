/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.blb.blb;

import java.util.Arrays;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.BlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.DeleteListenerRequest;

public class ExampleDeleteListeners {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.su.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        BlbClient blbClient = new BlbClient(config); // 初始化BlbClient

        DeleteListenerRequest deleteListenerRequest = new DeleteListenerRequest();
        deleteListenerRequest.setBlbId("lb-081b7605"); // 所属LoadBalancer的标识符
        deleteListenerRequest.setPortList(Arrays.asList(81)); // 所有待释放的监听器的端口

        try {
            blbClient.deleteListener(deleteListenerRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
