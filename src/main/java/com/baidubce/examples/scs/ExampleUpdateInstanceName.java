/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.instance.ScsUpdateInstanceNameRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleUpdateInstanceName {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsUpdateInstanceNameRequest request = new ScsUpdateInstanceNameRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setInstanceName("scs-example-new-name");
        client.updateInstanceName(request);
    }
}
