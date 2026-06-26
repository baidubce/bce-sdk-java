/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupCreateRequest;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupCreateResponse;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupMemberInfo;
import com.baidubce.util.JsonUtils;
import java.util.ArrayList;
import java.util.List;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID_2;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleSyncGroupCreate {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSyncGroupCreateRequest request = new ScsSyncGroupCreateRequest();
        request.setSyncGroupName("sync-group-example");
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

        ScsSyncGroupCreateResponse response = client.createSyncGroup(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
