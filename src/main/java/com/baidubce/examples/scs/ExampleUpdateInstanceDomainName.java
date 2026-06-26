/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsInstanceDetailRequest;
import com.baidubce.services.scs.model.ScsInstanceDetailResponse;
import com.baidubce.services.scs.model.instance.ScsInstanceModifyDomainRequest;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleUpdateInstanceDomainName {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsInstanceDetailRequest detailRequest = new ScsInstanceDetailRequest();
        detailRequest.setInstanceId(INSTANCE_ID);
        ScsInstanceDetailResponse detailResponse = client.getInstanceDetail(detailRequest);
        System.out.println(detailResponse.getDomain());
        ScsInstanceModifyDomainRequest request = new ScsInstanceModifyDomainRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setDomain("test" + detailResponse.getDomain());
        System.out.println(JsonUtils.toJsonString(request));
        client.updateInstanceDomainName(request);
    }
}
