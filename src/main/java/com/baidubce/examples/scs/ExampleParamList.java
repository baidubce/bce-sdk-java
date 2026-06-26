/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsParamListRequest;
import com.baidubce.services.scs.model.ScsParamListResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleParamList {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsParamListRequest request = new ScsParamListRequest();
        request.setInstanceId(INSTANCE_ID);
        ScsParamListResponse response = client.getParamList(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
