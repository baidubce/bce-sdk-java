/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.instance.ScsMaintainTimeResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleGetMaintainTime {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsMaintainTimeResponse response = client.getMaintainTime(INSTANCE_ID);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
