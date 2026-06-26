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
public class ExampleTemplateAddParams {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsParamsTemplateModifyParamsRequest request = new ScsParamsTemplateModifyParamsRequest();
        ScsParameterInfo parameterInfo = new ScsParameterInfo();
        parameterInfo.setConfName("maxmemory-policy");
        parameterInfo.setConfValue("volatile-ttl");
        parameterInfo.setConfModule(1);
        parameterInfo.setConfType(1);
        request.setParameters(Collections.singletonList(parameterInfo));
        client.templateAddParams(TEMPLATE_ID, request);
    }
}
