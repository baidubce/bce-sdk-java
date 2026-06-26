/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.scs.ScsClient;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleAutoScalingConfigDelete {
    public static void main(String[] args) {
        ScsClient client = createClient();
        AbstractBceResponse response = client.deleteAutoScalingConfig(INSTANCE_ID);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
