/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.instance.ScsRestartInstanceRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleRestartInstance {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsRestartInstanceRequest request = new ScsRestartInstanceRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setIsDefer(false);
        client.restartInstance(request);
    }
}
