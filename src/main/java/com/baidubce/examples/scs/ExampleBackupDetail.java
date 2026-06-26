/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.backup.ScsBackupDetailRequest;
import com.baidubce.services.scs.model.backup.ScsBackupDetailResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.BACKUP_RECORD_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleBackupDetail {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsBackupDetailRequest request = new ScsBackupDetailRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setBackupRecordId(BACKUP_RECORD_ID);
        System.out.println(JsonUtils.toJsonString(request));
        ScsBackupDetailResponse response = client.getBackupDetail(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
