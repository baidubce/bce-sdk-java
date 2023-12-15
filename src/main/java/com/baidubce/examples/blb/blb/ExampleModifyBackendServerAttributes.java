/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.blb.blb;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.BlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.BackendServer;

public class ExampleModifyBackendServerAttributes {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.su.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        BlbClient blbClient = new BlbClient(config); // 初始化BlbClient

        String blbId = "lb-081b7605"; // 所属LoadBalancer的标识符
        List<BackendServer> backendServerList = new ArrayList<BackendServer>();
        BackendServer backendServer = new BackendServer();
        backendServer.setInstanceId("i-VfM3kz2D"); // 后端服务器标识符
        backendServer.setWeight(60); // 后端服务器权重
        backendServerList.add(backendServer);

        try {
            blbClient.modifyBackendServerAttributes(blbId, backendServerList);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
