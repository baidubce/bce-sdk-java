/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsSlowLogAction;
import com.baidubce.services.scs.model.ScsSlowLogModifyRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleModifySlowLog {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSlowLogModifyRequest request = new ScsSlowLogModifyRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setAction(ScsSlowLogAction.OPEN);
        client.modifySlowLog(request);
    }
}
