/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.blb.blb;

import static com.baidubce.services.blb.model.ListenerConstant.HTTPS_LISTENER;

import java.util.Arrays;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.BlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.BlbListenerRequest;

public class ExampleCreateHttpsListener {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.su.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        BlbClient blbClient = new BlbClient(config); // 初始化BlbClient

        BlbListenerRequest blbListenerRequest = new BlbListenerRequest();
        blbListenerRequest.setType(HTTPS_LISTENER);
        blbListenerRequest.setBlbId("lb-081b7605"); // 所属LoadBalancer的标识符
        blbListenerRequest.setListenerPort(22); // 监听器的监听端口
        blbListenerRequest.setBackendPort(22); // 后端服务器的监听端口
        blbListenerRequest.setScheduler("RoundRobin"); // 负载均衡算法
        blbListenerRequest.setCertIds(Arrays.asList("cert-btq9faddgkpb")); // 监听器要加载的证书链

        try {
            blbClient.createListener(blbListenerRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
