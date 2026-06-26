/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsRenewInstanceRequest;
import com.baidubce.services.scs.model.ScsRenewInstanceResponse;
import java.util.Collections;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleRenewInstance {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsRenewInstanceRequest request = new ScsRenewInstanceRequest();
        request.setInstanceIds(Collections.singletonList(INSTANCE_ID));
        request.setDuration(1);
        ScsRenewInstanceResponse response = client.renewInstance(request);
        System.out.println(response);
    }
}
