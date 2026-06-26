/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.template.ScsParamsTemplateDeleteParamsRequest;
import java.util.Collections;

import static com.baidubce.examples.scs.ScsExampleUtil.TEMPLATE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleTemplateDeleteParams {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsParamsTemplateDeleteParamsRequest request = new ScsParamsTemplateDeleteParamsRequest();
        request.setParameters(Collections.singletonList("maxmemory-policy"));
        client.templateDeleteParams(TEMPLATE_ID, request);
    }
}
