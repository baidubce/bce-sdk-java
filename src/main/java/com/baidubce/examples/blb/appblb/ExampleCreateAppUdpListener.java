/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.blb.appblb;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.AppBlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.BlbListenerRequest;

public class ExampleCreateAppUdpListener {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.su.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AppBlbClient appBlbClient = new AppBlbClient(config); // 初始化AppBlbClient

        BlbListenerRequest blbListenerRequest = new BlbListenerRequest();
        blbListenerRequest.setType("UDP"); // 监听器协议类型
        blbListenerRequest.setBlbId("lb-166d3dbe"); // 所属LoadBalancer的标识符
        blbListenerRequest.setListenerPort(82); // 监听器的监听端口
        blbListenerRequest.setScheduler("Hash"); // 负载均衡算法
        blbListenerRequest.setHealthCheckString("\\00"); // 健康检查发送的请求字符串
        
        try {
            appBlbClient.createListener(blbListenerRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
