/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.esg;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.esg.EsgClient;
import com.baidubce.services.esg.EsgClientConfiguration;
import com.baidubce.services.esg.model.DeleteEsgRequest;

public class ExampleDeleteEsg {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        EsgClientConfiguration config = new EsgClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        EsgClient esgClient = new EsgClient(config); // 初始化EsgClient

        // 使用DeleteEsgRequest来展示clientToken字段的使用
        DeleteEsgRequest deleteEsgRequest = new DeleteEsgRequest();
        deleteEsgRequest.setEnterpriseSecurityGroupId("esg-hqg4jf8nuurt");
        // 新增字段：clientToken用于保证请求幂等性，避免重复删除
        // 建议使用UUID或时间戳等唯一标识
        deleteEsgRequest.setClientToken("delete-esg-token-" + System.currentTimeMillis());

        try {
            esgClient.deleteEsg(deleteEsgRequest);
            System.out.println("删除企业安全组成功");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

