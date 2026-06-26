/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.template.ScsSystemTemplateRequest;
import com.baidubce.services.scs.model.template.ScsSystemTemplateResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleGetSystemTemplate {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSystemTemplateRequest request = new ScsSystemTemplateRequest();
        request.setEngine("redis");
        request.setEngineVersion("6.0");
        request.setClusterType("cluster");
        ScsSystemTemplateResponse response = client.getSystemTemplate(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
