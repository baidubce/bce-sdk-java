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
import com.baidubce.services.blb.model.EsgOperateRequest;

public class ExampleBindEsg {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "blb.su.baidubce.com"; // 请求的服务region对应的域名

        BlbClientConfiguration config = new BlbClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        BlbClient blbClient = new BlbClient(config); // 初始化BlbClient

        EsgOperateRequest esgOperateRequest = new EsgOperateRequest();
        esgOperateRequest.setBlbId("lb-081b7605"); // 所属LoadBalancer的标识符
        List<String> enterpriseSecurityGroupIds = new ArrayList<String>(); // 绑定的企业安全组ID列表
        enterpriseSecurityGroupIds.add("esg-tv9jcdnyz5ap"); // 绑定的企业安全组ID
        esgOperateRequest.setEnterpriseSecurityGroupIds(enterpriseSecurityGroupIds);

        try {
            blbClient.bindEsg(esgOperateRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
