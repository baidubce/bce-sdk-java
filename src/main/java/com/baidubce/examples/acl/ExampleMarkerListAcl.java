/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.acl;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.acl.AclClient;
import com.baidubce.services.acl.AclClientConfiguration;
import com.baidubce.services.acl.model.ListAclRequest;
import com.baidubce.services.acl.model.ListAclResponse;

public class ExampleMarkerListAcl {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        AclClientConfiguration config = new AclClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AclClient aclClient = new AclClient(config); // 初始化AclClient

        ListAclRequest listAclRequest = new ListAclRequest();
        listAclRequest.setSubnetId("sbn-6ha6gp1vczuv"); // 查询该子网的acl规则
        listAclRequest.setMaxKeys(20); // 本次查询最多20条
        try {
            ListAclResponse response = aclClient.listAclRules(listAclRequest);
            System.out.println(response);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

