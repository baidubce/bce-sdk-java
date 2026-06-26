/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.backup.ScsCreateBackupRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleBackupCreate {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsCreateBackupRequest request = new ScsCreateBackupRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setComment("test");
        client.createBackup(request);
    }
}
