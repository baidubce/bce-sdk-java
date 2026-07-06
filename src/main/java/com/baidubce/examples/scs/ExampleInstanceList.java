/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.InstanceListRequest;
import com.baidubce.services.scs.model.InstanceListResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleInstanceList {
    public static void main(String[] args) {
        ScsClient client = createClient();
        InstanceListRequest request = new InstanceListRequest();
        request.setMarker("-1");
        request.setMaxKeys(10);
//        request.setInstanceIds("");
//        request.setVnetIp("");
        System.out.println(JsonUtils.toJsonString(request));
        InstanceListResponse response = client.getInstanceList(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
