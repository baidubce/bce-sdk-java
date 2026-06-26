/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.instance.ScsInstanceChangeTagRequest;
import com.baidubce.util.JsonUtils;
import java.util.Collections;

import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
import static com.baidubce.examples.scs.ScsExampleUtil.exampleTag;
public class ExampleTagBind {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsInstanceChangeTagRequest request = new ScsInstanceChangeTagRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setChangeTags(Collections.singletonList(exampleTag()));
        System.out.println(JsonUtils.toJsonString(request));
        client.bindingTag(request);
    }
}
