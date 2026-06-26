/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.securitygroup.ScsSecurityGroupRequest;
import com.baidubce.services.scs.model.securitygroup.ScsSecurityGroupVpcResponse;
import com.baidubce.util.JsonUtils;
import java.util.List;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.VPC_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
import static com.baidubce.examples.scs.ScsExampleUtil.exampleSecurityGroupRequest;
public class ExampleSecurityGroupsBind {
    public static void main(String[] args) {
        ScsClient client = createClient();

        ScsSecurityGroupVpcResponse listResponse = client.listSecurityGroupByVpcId(VPC_ID);
        List<String> groupIds = new java.util.ArrayList<>();
        listResponse.getGroups().forEach(group -> {
            groupIds.add(group.getSecurityGroupId());
        });
        if (groupIds.isEmpty()) {
            System.out.println("No security group found.");
            return;
        }

        ScsSecurityGroupRequest request = exampleSecurityGroupRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setSecurityGroupIds(groupIds);
        System.out.println(JsonUtils.toJsonString(request));
        client.bindSecurityGroups(request);
    }
}
