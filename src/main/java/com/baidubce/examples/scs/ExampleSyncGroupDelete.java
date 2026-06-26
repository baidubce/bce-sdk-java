/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;

import static com.baidubce.examples.scs.ScsExampleUtil.SYNC_GROUP_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleSyncGroupDelete {
    public static void main(String[] args) {
        ScsClient client = createClient();
        client.deleteSyncGroup(SYNC_GROUP_ID);
    }
}
