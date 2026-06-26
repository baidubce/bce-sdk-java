/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.securitygroup.ScsSecurityGroupVpcResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.VPC_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleSecurityGroupListByVpcId {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSecurityGroupVpcResponse response = client.listSecurityGroupByVpcId(VPC_ID);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
