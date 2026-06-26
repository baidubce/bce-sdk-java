/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.backup.ScsBackupListRequest;
import com.baidubce.services.scs.model.backup.ScsBackupListResponse;
import com.baidubce.services.scs.model.backup.ScsDeleteBackupRequest;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleBackupDelete {
    public static void main(String[] args) {
        ScsClient client = createClient();

        ScsBackupListRequest listRequest = new ScsBackupListRequest();
        listRequest.setInstanceId(INSTANCE_ID);
        ScsBackupListResponse listResponse = client.getBackupList(listRequest);

        String batchId = listResponse.getBackups().get(0).getBatchId();

        ScsDeleteBackupRequest request = new ScsDeleteBackupRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setBatchId(batchId);

        System.out.println(JsonUtils.toJsonString(request));
        client.deleteBackup(request);
    }
}
