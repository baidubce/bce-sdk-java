/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.acl;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.acl.AclClient;
import com.baidubce.services.acl.AclClientConfiguration;

public class ExampleDeleteAclRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        AclClientConfiguration config = new AclClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AclClient aclClient = new AclClient(config); // 初始化AclClient

        try {
            aclClient.deleteAcl("ar-ynf9u87ppu1x");
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

