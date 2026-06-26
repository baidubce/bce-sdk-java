/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.securitygroup.ScsSecurityGroupRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
import static com.baidubce.examples.scs.ScsExampleUtil.exampleSecurityGroupRequest;
public class ExampleSecurityGroupsUnBind {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSecurityGroupRequest request = exampleSecurityGroupRequest();
        client.unBindSecurityGroups(request);
    }
}
