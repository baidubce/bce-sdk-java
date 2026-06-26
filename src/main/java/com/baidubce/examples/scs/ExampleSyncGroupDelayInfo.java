/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupDelayInfoResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.SYNC_GROUP_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleSyncGroupDelayInfo {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSyncGroupDelayInfoResponse response = client.getSyncGroupDelayInfo(SYNC_GROUP_ID);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
