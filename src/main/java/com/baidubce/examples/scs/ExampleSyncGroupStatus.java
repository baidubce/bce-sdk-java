/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupStatusResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.SYNC_GROUP_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleSyncGroupStatus {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSyncGroupStatusResponse response = client.getSyncGroupStatus(SYNC_GROUP_ID);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
