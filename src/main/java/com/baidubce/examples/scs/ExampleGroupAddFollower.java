/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.group.ScsGroupFollowerRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.GROUP_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID_2;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleGroupAddFollower {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsGroupFollowerRequest request = new ScsGroupFollowerRequest();
        request.setFollowerId(INSTANCE_ID_2);
        request.setFollowerRegion("bj");
        request.setSyncMaster("sync");
        client.groupAddFollower(GROUP_ID, request);
    }
}
