/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.instance.ScsAutoScalingConfigResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleAutoScalingConfig {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsAutoScalingConfigResponse response = client.getAutoScalingConfig(INSTANCE_ID);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
