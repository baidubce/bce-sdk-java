/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsInstanceDetailRequest;
import com.baidubce.services.scs.model.ScsInstanceDetailResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleInstanceDetail {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsInstanceDetailRequest request = new ScsInstanceDetailRequest();
        request.setInstanceId(INSTANCE_ID);
        ScsInstanceDetailResponse response = client.getInstanceDetail(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
