/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsRecoverInstanceRequest;
import java.util.Collections;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleRecoverInstance {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsRecoverInstanceRequest request = new ScsRecoverInstanceRequest();
        request.setInstanceIds(Collections.singletonList(INSTANCE_ID));
        client.recoverInstance(request);
    }
}
