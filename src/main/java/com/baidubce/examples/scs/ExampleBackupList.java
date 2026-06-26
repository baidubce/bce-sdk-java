/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.backup.ScsBackupListRequest;
import com.baidubce.services.scs.model.backup.ScsBackupListResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleBackupList {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsBackupListRequest request = new ScsBackupListRequest();
        request.setInstanceId(INSTANCE_ID);
        ScsBackupListResponse response = client.getBackupList(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
