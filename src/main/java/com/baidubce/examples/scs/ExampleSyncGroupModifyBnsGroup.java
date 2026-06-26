/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupModifyBnsNameRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.SYNC_GROUP_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleSyncGroupModifyBnsGroup {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSyncGroupModifyBnsNameRequest request = new ScsSyncGroupModifyBnsNameRequest();
        request.setBnsGroup("group.example.all");
        client.modifySyncGroupBnsGroup(SYNC_GROUP_ID, request);
    }
}
