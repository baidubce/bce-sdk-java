/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.group.ScsGroupStaleReadableRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.GROUP_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID_2;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleGroupStaleReadable {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsGroupStaleReadableRequest request = new ScsGroupStaleReadableRequest();
        request.setFollowerId(INSTANCE_ID_2);
        request.setStaleReadable(true);
        client.groupStaleReadable(GROUP_ID, request);
    }
}
