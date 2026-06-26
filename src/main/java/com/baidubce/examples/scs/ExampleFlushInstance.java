/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsFlushInstanceRequest;

import java.security.GeneralSecurityException;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleFlushInstance {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsFlushInstanceRequest request = new ScsFlushInstanceRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setDbIndex(0);
        request.setPassword("example1234");
        try {
            client.flushInstance(request);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Modify password failed", e);
        }
    }
}
