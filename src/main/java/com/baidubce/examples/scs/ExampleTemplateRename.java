/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.template.ScsParamsTemplateRenameRequest;


import static com.baidubce.examples.scs.ScsExampleUtil.TEMPLATE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;


public class ExampleTemplateRename {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsParamsTemplateRenameRequest request = new ScsParamsTemplateRenameRequest();
        request.setName("scs-template-new-name");
        client.renameParamsTemplate(TEMPLATE_ID, request);
    }
}
