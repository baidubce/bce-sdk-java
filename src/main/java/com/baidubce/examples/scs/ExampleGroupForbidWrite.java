/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.group.ScsGroupDetailResponse;
import com.baidubce.services.scs.model.group.ScsGroupForbidWriteRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.GROUP_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleGroupForbidWrite {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsGroupDetailResponse groupDetail = client.getGroupDetail(GROUP_ID);
        ScsGroupForbidWriteRequest request = new ScsGroupForbidWriteRequest();
        request.setForbidWriteFlag(groupDetail.getForbidWrite() == 0 ? true : false);
        client.groupForbidWrite(GROUP_ID, request);
    }
}
