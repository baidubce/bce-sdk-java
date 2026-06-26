/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsSubnetRequest;
import com.baidubce.services.scs.model.ScsSubnetResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.VPC_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.ZONE_NAME;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleGetSubnetList {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSubnetRequest request = new ScsSubnetRequest();
        request.setVpcId(VPC_ID);
        request.setZoneName(ZONE_NAME);
        ScsSubnetResponse response = client.getSubnetList(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
