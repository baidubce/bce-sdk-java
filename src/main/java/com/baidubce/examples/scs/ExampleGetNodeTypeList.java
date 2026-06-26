/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.instance.ScsNodeTypeResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleGetNodeTypeList {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsNodeTypeResponse response = client.getNodeTypeList();
        System.out.println(JsonUtils.toJsonString(response));
    }
}
