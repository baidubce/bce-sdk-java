/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.group.ScsGroupSetQpsRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.GROUP_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID_2;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleGroupSetQps {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsGroupSetQpsRequest request = new ScsGroupSetQpsRequest();
        request.setClusterShowId(INSTANCE_ID_2);
        request.setQpsRead(1000);
        request.setQpsWrite(1000);
        client.groupSetQps(GROUP_ID, request);
    }
}
