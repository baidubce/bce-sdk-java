/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.group.ScsGroupSyncStatusResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.GROUP_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleGroupSyncStatus {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsGroupSyncStatusResponse response = client.groupSyncStatus(GROUP_ID);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
