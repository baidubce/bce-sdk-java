/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.whitelist.ScsWhiteListGroupRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
import static com.baidubce.examples.scs.ScsExampleUtil.exampleWhiteListGroupRequest;
public class ExampleWhiteListGroupCreate {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsWhiteListGroupRequest request = exampleWhiteListGroupRequest();
        client.createWhiteListGroup(request);
    }
}
