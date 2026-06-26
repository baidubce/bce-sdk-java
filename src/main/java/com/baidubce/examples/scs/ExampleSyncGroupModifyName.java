/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupModifyNameRequest;


import static com.baidubce.examples.scs.ScsExampleUtil.SYNC_GROUP_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;


public class ExampleSyncGroupModifyName {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSyncGroupModifyNameRequest request = new ScsSyncGroupModifyNameRequest();
        request.setGroupName("sync-group-new-name");
        client.modifySyncGroupName(SYNC_GROUP_ID, request);
    }
}
