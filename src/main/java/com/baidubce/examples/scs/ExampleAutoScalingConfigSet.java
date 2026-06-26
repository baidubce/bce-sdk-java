/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.instance.ScsAutoScalingConfigRequest;
import com.baidubce.services.scs.model.instance.ScsMemAutoScalingConfig;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleAutoScalingConfigSet {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsMemAutoScalingConfig memSpec = new ScsMemAutoScalingConfig();
        memSpec.setMemUsageUpperThreshold(80);
        memSpec.setMemUsageDownThreshold(30);
        memSpec.setMaxNodeType("cache.n1.small");
        memSpec.setMinNodeType("cache.n1.micro");
        memSpec.setObservationWindowSizeForDown("5m");
        memSpec.setObservationWindowSizeForUpper("5m");
        ScsAutoScalingConfigRequest request = new ScsAutoScalingConfigRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setMemSpec(memSpec);
        client.setAutoScalingConfig(request);
    }
}
