/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.instance.ScsUpgradeVersionRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleUpgradeVersion {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsUpgradeVersionRequest request = new ScsUpgradeVersionRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setKernelVersion("5.0");
        request.setIsDefer(false);
        client.upgradeVersion(request);
    }
}
