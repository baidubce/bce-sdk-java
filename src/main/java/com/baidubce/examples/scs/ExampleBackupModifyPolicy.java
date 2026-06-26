/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.backup.ScsBackupModifyPolicyRequest;
import com.baidubce.services.scs.model.backup.ScsBackupPolicyRequest;
import com.baidubce.services.scs.model.backup.ScsBackupPolicyResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleBackupModifyPolicy {
    public static void main(String[] args) {
        ScsClient client = createClient();

        ScsBackupPolicyRequest policyRequest = new ScsBackupPolicyRequest();
        policyRequest.setInstanceId(INSTANCE_ID);
        ScsBackupPolicyResponse policyResponse = client.getBackupPolicy(policyRequest);

        String backupTime = policyResponse.getBackupTime();
        String backupDays = policyResponse.getBackupDays();
        Integer expireDay = policyResponse.getExpireDay();
        ScsBackupModifyPolicyRequest request = new ScsBackupModifyPolicyRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setBackupTime(backupTime);
        request.setBackupDays(backupDays);
        request.setExpireDay(expireDay - 1 <= 0 ? expireDay + 1 : expireDay - 1);

        System.out.println(JsonUtils.toJsonString(request));
        client.modifyBackupPolicy(request);
    }
}
