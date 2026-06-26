/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.template.ScsCreateParamsTemplateRequest;
import com.baidubce.services.scs.model.template.ScsCreateParamsTemplateResponse;
import com.baidubce.services.scs.model.template.ScsParameterInfo;
import com.baidubce.util.JsonUtils;
import java.util.Collections;

import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleTemplateCreate {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsCreateParamsTemplateRequest request = new ScsCreateParamsTemplateRequest();
        request.setEngine("redis");
        request.setEngineVersion("6.0");
        request.setClusterType("cluster");
        request.setTemplateType(1);
        request.setName("scs-template-example");
        request.setComment("example template");

        ScsParameterInfo parameter = new ScsParameterInfo();
        parameter.setConfName("appendonly");
        parameter.setConfValue("no");
        parameter.setConfModule(1);
        parameter.setConfType(1);
        request.setParameters(Collections.singletonList(parameter));

        ScsCreateParamsTemplateResponse response = client.createParamsTemplate(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
