/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.account.ScsAclUser;
import com.baidubce.services.scs.model.account.ScsAclUserListResponse;
import com.baidubce.services.scs.model.account.ScsAclUserModifyPasswordRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleAclUserModifyPassword {
    public static void main(String[] args) {
        ScsClient client = createClient();

        ScsAclUserListResponse listAclUsers = client.listAclUsers(INSTANCE_ID);

        ScsAclUser user = null;
        for (ScsAclUser aclUser : listAclUsers.getResult()) {
            if (!aclUser.getUserName().equals("default")) {
                user = aclUser;
                break;
            }
        }

        if (user == null) {
            System.out.println("No user found to modify password.");
            return;
        }

        ScsAclUserModifyPasswordRequest request = new ScsAclUserModifyPasswordRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setUserName(user.getUserName());
        request.setClientAuth("");
        client.modifyAclUserPassword(request);
    }
}
