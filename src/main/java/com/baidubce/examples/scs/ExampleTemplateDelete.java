/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;


import static com.baidubce.examples.scs.ScsExampleUtil.TEMPLATE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;

public class ExampleTemplateDelete {
    public static void main(String[] args) {
        ScsClient client = createClient();
        client.deleteParamsTemplate(TEMPLATE_ID);
    }
}
