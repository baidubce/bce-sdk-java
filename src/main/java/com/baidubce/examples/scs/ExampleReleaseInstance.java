/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsReleaseInstanceRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleReleaseInstance {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsReleaseInstanceRequest request = new ScsReleaseInstanceRequest();
        request.setInstanceId(INSTANCE_ID);
        client.releaseInstance(request);
    }
}
