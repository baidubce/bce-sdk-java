/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupMemberInfo;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupPreCheckRequest;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupPreCheckResponse;
import com.baidubce.util.JsonUtils;
import java.util.ArrayList;
import java.util.List;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID_2;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleSyncGroupPreCheck {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSyncGroupPreCheckRequest request = new ScsSyncGroupPreCheckRequest();
//        request.setSyncGroupShowId("");

        List<ScsSyncGroupMemberInfo> members = new ArrayList<>();
        ScsSyncGroupMemberInfo member1 = new ScsSyncGroupMemberInfo();
        member1.setMemberId(INSTANCE_ID);
        member1.setRegion("bj");

        ScsSyncGroupMemberInfo member2 = new ScsSyncGroupMemberInfo();
        member2.setMemberId(INSTANCE_ID_2);
        member2.setRegion("bj");

        members.add(member1);
        members.add(member2);
        request.setMembers(members);
        ScsSyncGroupPreCheckResponse response = client.syncGroupPreCheck(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
