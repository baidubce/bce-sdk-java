/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.acl;

import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.acl.AclClient;
import com.baidubce.services.acl.AclClientConfiguration;
import com.baidubce.services.acl.model.ModifyAclRuleAttributesRequest;

public class ExampleUpdateAclRule {
    public static void main(String[] args) {
        String ak = "Your Ak";
        String sk = "Your Sk";
        String endpoint = "bcc.bj.baidubce.com"; // 请求的服务region对应的域名

        AclClientConfiguration config = new AclClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint(endpoint);
        AclClient aclClient = new AclClient(config); // 初始化AclClient

        ModifyAclRuleAttributesRequest modifyAclRuleAttributesRequest = new ModifyAclRuleAttributesRequest();
        modifyAclRuleAttributesRequest.setAclRuleId("ar-ynf9u87ppu1x");
        modifyAclRuleAttributesRequest.setDescription("new_desc");
        modifyAclRuleAttributesRequest.setName("name");
        modifyAclRuleAttributesRequest.setDestinationPort("9090");
        modifyAclRuleAttributesRequest.setSourcePort("8080");
        try {
            aclClient.modifyAclRuleAttributes(modifyAclRuleAttributesRequest);
        } catch (BceClientException e) {
            System.out.println(e.getMessage());
        }

    }
}

