/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.blb.blb;

import static com.baidubce.services.blb.model.ListenerConstant.UDP_LISTENER;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.BlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.ListListenerRequest;
import com.baidubce.services.blb.model.ListListenerResponse;

public class ExampleListUdpListener {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.su.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        BlbClient blbClient = new BlbClient(config); // 初始化BlbClient

        ListListenerRequest listListenerRequest = new ListListenerRequest();
        listListenerRequest.setType(UDP_LISTENER);
        listListenerRequest.setBlbId("lb-081b7605"); // 所属LoadBalancer的标识符
        listListenerRequest.setListenerPort(82); // 要查询的监听器端口

        try {
            ListListenerResponse listListenerResponse = blbClient.listListener(listListenerRequest);
            System.out.println(listListenerResponse);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
