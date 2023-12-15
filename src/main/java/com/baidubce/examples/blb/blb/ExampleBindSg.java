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
import com.baidubce.services.blb.model.SgOperateRequest;

public class ExampleBindSg {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.su.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        BlbClient blbClient = new BlbClient(config); // 初始化BlbClient

        SgOperateRequest sgOperateRequest = new SgOperateRequest();
        sgOperateRequest.setBlbId("lb-081b7605"); // 所属LoadBalancer的标识符
        List<String> securityGroupIds = new ArrayList<String>(); // 绑定的普通安全组ID列表
        securityGroupIds.add("g-msrvz0w5kvuz"); // 绑定的普通安全组ID
        sgOperateRequest.setSecurityGroupIds(securityGroupIds);

        try {
            blbClient.bindSg(sgOperateRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
