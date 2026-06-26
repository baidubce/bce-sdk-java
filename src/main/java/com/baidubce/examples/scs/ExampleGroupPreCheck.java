/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.group.ScsGroupFollowerInfo;
import com.baidubce.services.scs.model.group.ScsGroupLeaderInfo;
import com.baidubce.services.scs.model.group.ScsGroupPreCheckRequest;
import com.baidubce.services.scs.model.group.ScsGroupPreCheckResponse;
import com.baidubce.util.JsonUtils;
import java.util.Collections;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID_2;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleGroupPreCheck {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsGroupPreCheckRequest request = new ScsGroupPreCheckRequest();
        ScsGroupLeaderInfo leader = new ScsGroupLeaderInfo();
        leader.setLeaderRegion("bj");
        leader.setLeaderId(INSTANCE_ID);

        ScsGroupFollowerInfo follower1 = new ScsGroupFollowerInfo();
        follower1.setFollowerId(INSTANCE_ID_2);
        follower1.setFollowerRegion("bj");

        request.setLeader(leader);
        request.setFollowers(Collections.singletonList(follower1));

        ScsGroupPreCheckResponse response = client.groupPreCheck(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
