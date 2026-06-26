/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;


import static com.baidubce.examples.scs.ScsExampleUtil.GROUP_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;

public class ExampleGroupDelete {
    public static void main(String[] args) {
        ScsClient client = createClient();
        client.deleteGroup(GROUP_ID);
    }
}
