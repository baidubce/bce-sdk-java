/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupMemberRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID_3;
import static com.baidubce.examples.scs.ScsExampleUtil.SYNC_GROUP_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleSyncGroupRemoveCluster {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSyncGroupMemberRequest request = new ScsSyncGroupMemberRequest();
        request.setMemberId(INSTANCE_ID_3);
        request.setRegion("bj");
        client.removeSyncGroupCluster(SYNC_GROUP_ID, request);
    }
}
