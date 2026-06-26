/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.whitelist.ScsWhiteListGroupRequest;
import java.util.ArrayList;

import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
import static com.baidubce.examples.scs.ScsExampleUtil.exampleWhiteListGroupRequest;
public class ExampleWhiteListGroupModify {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsWhiteListGroupRequest request = exampleWhiteListGroupRequest();
        request.setNewGroupName("example-group-new");
        request.setClusterIpList(new ArrayList<String>(){{
            add("127.0.0.1");
            add("127.0.0.2");
        }});
        client.modifyWhiteListGroup(request);
    }
}
