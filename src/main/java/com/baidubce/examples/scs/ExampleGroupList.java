/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.group.ScsGroupListRequest;
import com.baidubce.services.scs.model.group.ScsGroupListResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleGroupList {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsGroupListRequest request = new ScsGroupListRequest();
        request.setPageNo(1);
        request.setPageSize(10);
        ScsGroupListResponse response = client.getGroupList(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
