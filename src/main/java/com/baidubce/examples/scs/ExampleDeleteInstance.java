/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsDeleteInstanceRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleDeleteInstance {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsDeleteInstanceRequest request = new ScsDeleteInstanceRequest();
        request.setInstanceId(INSTANCE_ID);
        client.deleteInstance(request);
    }
}
