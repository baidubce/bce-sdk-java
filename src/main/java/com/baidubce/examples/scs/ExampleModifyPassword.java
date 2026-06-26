/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.instance.ScsInstanceModifyPasswordRequest;

import java.security.GeneralSecurityException;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;

public class ExampleModifyPassword {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsInstanceModifyPasswordRequest request = new ScsInstanceModifyPasswordRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setPassword("Example123");
        try {
            client.modifyPassword(request);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Modify password failed", e);
        }
    }
}
