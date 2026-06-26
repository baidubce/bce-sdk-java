/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupDetailResponse;
import com.baidubce.util.JsonUtils;


import static com.baidubce.examples.scs.ScsExampleUtil.SYNC_GROUP_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;

public class ExampleSyncGroupDetail {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSyncGroupDetailResponse response = client.getSyncGroupDetail(SYNC_GROUP_ID);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
