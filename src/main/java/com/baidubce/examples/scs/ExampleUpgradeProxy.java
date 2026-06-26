/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.instance.ScsUpgradeProxyRequest;
import java.util.Collections;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleUpgradeProxy {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsUpgradeProxyRequest request = new ScsUpgradeProxyRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setProxyList(Collections.singletonList("proxy-xxxxxxxx"));
        request.setUpgradeType("latest");
        request.setIsDefer(false);
        client.upgradeProxy(request);
    }
}
