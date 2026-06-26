/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.template.ScsParamsTemplateDetailResponse;
import com.baidubce.util.JsonUtils;


import static com.baidubce.examples.scs.ScsExampleUtil.TEMPLATE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;

public class ExampleTemplateDetail {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsParamsTemplateDetailResponse response = client.getParamsTemplateDetail(TEMPLATE_ID);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
