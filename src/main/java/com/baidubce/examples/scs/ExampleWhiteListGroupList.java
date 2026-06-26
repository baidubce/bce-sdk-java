/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.whitelist.ScsWhiteListGroupRequest;
import com.baidubce.services.scs.model.whitelist.ScsWhiteListGroupResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
import static com.baidubce.examples.scs.ScsExampleUtil.exampleWhiteListGroupRequest;
public class ExampleWhiteListGroupList {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsWhiteListGroupRequest request = exampleWhiteListGroupRequest();
        request.setGroupName("all");
        ScsWhiteListGroupResponse response = client.getWhiteListGroup(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
