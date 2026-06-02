/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.blb.blb;

import static com.baidubce.services.blb.model.ListenerConstant.HTTP_LISTENER;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.BlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.AdditionalAttributes;
import com.baidubce.services.blb.model.BlbListenerRequest;

public class ExampleUpdateHttpListener {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.su.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        BlbClient blbClient = new BlbClient(config); // 初始化BlbClient

        BlbListenerRequest blbListenerRequest = new BlbListenerRequest();
        blbListenerRequest.setType(HTTP_LISTENER);
        blbListenerRequest.setBlbId("lb-081b7605"); // 所属LoadBalancer的标识符
        blbListenerRequest.setListenerPort(90); // 监听器的监听端口
        blbListenerRequest.setBackendPort(91); // 后端服务器的监听端口
        blbListenerRequest.setxForwardFor(true); // 是否开启获取Client真实IP
        blbListenerRequest.setxForwardedProto(true); // 将监听使用的协议通过x-forwarded-proto转发给后端服务器
        blbListenerRequest.setHealthCheckHost("localhost"); // 7层健康检查请求的头部域host字段
        // HTTP类型监听器定制配置
        AdditionalAttributes additionalAttributes = new AdditionalAttributes();
        additionalAttributes.setGzipJson("off"); // gzip压缩配置，支持"on"/"off"
        blbListenerRequest.setAdditionalAttributes(additionalAttributes);

        try {
            blbClient.modifyListenerAttributes(blbListenerRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
