/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.group.ScsGroupRenameRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.GROUP_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleUpdateGroupName {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsGroupRenameRequest request = new ScsGroupRenameRequest();
        request.setGroupName("scs-group-new-name");
        client.updateGroupName(GROUP_ID, request);
    }
}
