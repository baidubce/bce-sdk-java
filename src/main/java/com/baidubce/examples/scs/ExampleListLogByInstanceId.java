/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.logmanage.ScsLogListRequest;
import com.baidubce.services.scs.model.logmanage.ScsLogListResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleListLogByInstanceId {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsLogListRequest request = new ScsLogListRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setFileType("redis");
        request.setStartTime("2026-01-01 00:00:00");
        request.setEndTime("2026-01-01 01:00:00");
        ScsLogListResponse response = client.listLogByInstanceId(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
