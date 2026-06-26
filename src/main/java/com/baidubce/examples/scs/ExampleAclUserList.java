/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.account.ScsAclUserListResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleAclUserList {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsAclUserListResponse response = client.listAclUsers(INSTANCE_ID);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
