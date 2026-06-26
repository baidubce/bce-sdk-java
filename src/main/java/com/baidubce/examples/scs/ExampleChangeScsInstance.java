/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsChangeConfigRequest;
import com.baidubce.services.scs.model.ScsOrderResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.CLIENT_TOKEN;
import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleChangeScsInstance {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsChangeConfigRequest request = new ScsChangeConfigRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setClientToken(CLIENT_TOKEN);
        request.setNodeType("cache.n1.micro");
        request.setShardNum(3);
        System.out.println(JsonUtils.toJsonString(request));
        ScsOrderResponse response = client.changeScsInstance(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
