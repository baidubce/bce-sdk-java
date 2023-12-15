/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.blb.appblb;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.blb.AppBlbClient;
import com.baidubce.services.blb.BlbClientConfiguration;
import com.baidubce.services.blb.model.SgOperateRequest;

public class ExampleUnBindSg {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.su.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AppBlbClient appBlbClient = new AppBlbClient(config); // 初始化AppBlbClient

        SgOperateRequest sgOperateRequest = new SgOperateRequest();
        sgOperateRequest.setBlbId("lb-166d3dbe"); // 所属LoadBalancer的标识符
        List<String> securityGroupIds = new ArrayList<String>(); // 解绑的普通安全组ID列表
        securityGroupIds.add("g-msrvz0w5kvuz"); // 解绑的普通安全组ID
        sgOperateRequest.setSecurityGroupIds(securityGroupIds);

        try {
            appBlbClient.unBindSg(sgOperateRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
