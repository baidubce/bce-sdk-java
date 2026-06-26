/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.instance.ScsMaintainTimeRequest;
import com.baidubce.util.JsonUtils;
import java.util.Collections;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleModifyMaintainTime {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsMaintainTimeRequest request = new ScsMaintainTimeRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setStartTime("02:00");
        request.setDuration(3);
        request.setPeriod(Collections.singletonList(1));
        System.out.println(JsonUtils.toJsonString(request));
        client.modifyMaintainTime(request);
    }
}
