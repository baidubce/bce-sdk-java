/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.template.ScsApplyParamsTemplateRequest;
import java.util.Collections;

import static com.baidubce.examples.scs.ScsExampleUtil.TEMPLATE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleTemplateApply {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsApplyParamsTemplateRequest request = new ScsApplyParamsTemplateRequest();
        request.setRebootType(1);
//        request.setParameters(Collections.emptyList());

        ScsApplyParamsTemplateRequest.CacheClusterItem cacheClusterItem = new ScsApplyParamsTemplateRequest.CacheClusterItem();
        cacheClusterItem.setCacheClusterShowId("");
        cacheClusterItem.setRegion("bj");
        request.setCacheClusterList(Collections.singletonList(cacheClusterItem));
        request.setExtra(0);
        client.applyParamsTemplate(TEMPLATE_ID, request);
    }
}
