/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.backup.ScsBackupUsageRequest;
import com.baidubce.services.scs.model.backup.ScsBackupUsageResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleBackupUsage {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsBackupUsageRequest request = new ScsBackupUsageRequest();
        request.setInstanceId(INSTANCE_ID);
        System.out.println(JsonUtils.toJsonString(request));
        ScsBackupUsageResponse response = client.getBackupUsage(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
