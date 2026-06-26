/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.logmanage.ScsLogDetailRequest;
import com.baidubce.services.scs.model.logmanage.ScsLogDetailResponse;

import static com.baidubce.examples.scs.ScsExampleUtil.LOG_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleGetLogById {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsLogDetailRequest request = new ScsLogDetailRequest();
        request.setLogId(LOG_ID);
        request.setValidSeconds(3600);
        ScsLogDetailResponse response = client.getLogById(request);
        System.out.println(response);
    }
}
