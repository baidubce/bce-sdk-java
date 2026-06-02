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
import com.baidubce.services.blb.model.AdditionalAttributes;
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
        blbListenerRequest.setxForwardFor(true); // 是否开启获取Client真实IP
        blbListenerRequest.setxForwardedProto(true); // 将监听使用的协议通过x-forwarded-proto转发给后端服务器
        blbListenerRequest.setHealthCheckHost("localhost"); // 7层健康检查请求的头部域host字段
        blbListenerRequest.setEncryptionType("tls_cipher_policy_default"); // 加密选项
        blbListenerRequest.setDualAuth(false); // 是否开启双向认证
        // HTTPS类型监听器定制配置
        AdditionalAttributes additionalAttributes = new AdditionalAttributes();
        additionalAttributes.setGzipJson("on"); // gzip压缩配置，支持"on"/"off"
        blbListenerRequest.setAdditionalAttributes(additionalAttributes);

        try {
            blbClient.createListener(blbListenerRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
