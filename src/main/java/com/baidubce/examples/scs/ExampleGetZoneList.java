/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsZoneResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleGetZoneList {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsZoneResponse response = client.getZoneList();
        System.out.println(JsonUtils.toJsonString(response));
    }
}
