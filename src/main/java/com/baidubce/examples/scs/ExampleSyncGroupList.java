/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupListRequest;
import com.baidubce.services.scs.model.syncgroup.ScsSyncGroupListResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleSyncGroupList {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsSyncGroupListRequest request = new ScsSyncGroupListRequest();
        request.setPageNo(1);
        request.setPageSize(10);
        ScsSyncGroupListResponse response = client.listSyncGroup(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
