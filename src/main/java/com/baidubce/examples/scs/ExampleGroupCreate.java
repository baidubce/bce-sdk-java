/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.group.ScsCreateGroupResponse;
import com.baidubce.services.scs.model.group.ScsGroupCreateRequest;
import com.baidubce.services.scs.model.group.ScsGroupLeaderInfo;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleGroupCreate {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsGroupCreateRequest request = new ScsGroupCreateRequest();
        ScsGroupLeaderInfo leader = new ScsGroupLeaderInfo();
        leader.setGroupName("java-sdk-scs-group-example");
        leader.setLeaderRegion("bj");
        leader.setLeaderId(INSTANCE_ID);
        request.setLeader(leader);
        ScsCreateGroupResponse response = client.createGroup(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
