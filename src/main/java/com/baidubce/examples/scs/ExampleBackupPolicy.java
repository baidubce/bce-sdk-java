/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.backup.ScsBackupPolicyRequest;
import com.baidubce.services.scs.model.backup.ScsBackupPolicyResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleBackupPolicy {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsBackupPolicyRequest request = new ScsBackupPolicyRequest();
        request.setInstanceId(INSTANCE_ID);
        ScsBackupPolicyResponse response = client.getBackupPolicy(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
