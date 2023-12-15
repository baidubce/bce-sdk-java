/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.eni;

import java.util.Arrays;
import java.util.List;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eni.EniClient;
import com.baidubce.services.eni.EniClientConfiguration;
import com.baidubce.services.eni.model.CreateEniRequest;
import com.baidubce.services.eni.model.CreateEniResponse;
import com.baidubce.services.eni.model.PrivateIp;
import com.google.common.collect.Lists;

public class ExampleCreateEni {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EniClientConfiguration config = new EniClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EniClient eniClient = new EniClient(config); // 初始化EniClient

        CreateEniRequest request = new CreateEniRequest();
        request.setSubnetId("sbn-6ha6gp1vczuv"); // eni所属的子网Id
        request.setDescription("desc"); // eni的描述
        request.setName("testEni"); // eni的名称
        request.setSecurityGroupIds(Arrays.asList("g-vs94kdqxw078")); // eni的安全组列表
        List<PrivateIp> privateIpSet = Lists.newArrayList(); // Eni的Ip列表
        PrivateIp ip = new PrivateIp();
        ip.setPrimary(true); // 设置为主IP
        ip.setPrivateIpAddress("192.168.0.9"); // eni的内网IP
        privateIpSet.add(ip);

        request.setPrivateIpSet(privateIpSet);
        try {
            CreateEniResponse response = eniClient.createEni(request);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

