/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsMarkerRequest;
import com.baidubce.services.scs.model.template.ScsParamsTemplateListResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
import static com.baidubce.examples.scs.ScsExampleUtil.exampleMarkerRequest;
public class ExampleTemplateList {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsMarkerRequest request = exampleMarkerRequest();
        ScsParamsTemplateListResponse response = client.getParamsTemplateList(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
