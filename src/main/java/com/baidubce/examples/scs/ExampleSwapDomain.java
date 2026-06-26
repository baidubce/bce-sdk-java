/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsSwapDomainRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID_2;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleSwapDomain {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSwapDomainRequest request = new ScsSwapDomainRequest();
        request.setSourceInstanceId(INSTANCE_ID);
        request.setTargetInstanceId(INSTANCE_ID_2);
        client.swapDomain(request);
    }
}
