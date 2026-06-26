/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.template.ScsParameterInfo;
import com.baidubce.services.scs.model.template.ScsParamsTemplateModifyParamsRequest;

import java.util.Collections;

import static com.baidubce.examples.scs.ScsExampleUtil.TEMPLATE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;


public class ExampleTemplateModifyParams {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsParamsTemplateModifyParamsRequest request = new ScsParamsTemplateModifyParamsRequest();
        ScsParameterInfo param1 = new ScsParameterInfo();
        param1.setConfName("appendonly");
        param1.setConfValue("yes");
        param1.setConfModule(1);
        param1.setConfType(1);
        request.setParameters(Collections.singletonList(param1));
        client.templateModifyParams(TEMPLATE_ID, request);
    }
}
