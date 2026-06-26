/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsParamModifyRequest;

import static com.baidubce.examples.scs.ScsExampleUtil.CLIENT_TOKEN;
import static com.baidubce.examples.scs.ScsExampleUtil.INSTANCE_ID;
import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
public class ExampleParameterModify {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsParamModifyRequest.Parameter parameter = new ScsParamModifyRequest.Parameter();
        parameter.setName("appendonly");
        parameter.setValue("no");
        ScsParamModifyRequest request = new ScsParamModifyRequest();
        request.setInstanceId(INSTANCE_ID);
        request.setClientToken(CLIENT_TOKEN);
        request.setParameter(parameter);
        client.modifyScsParameter(request);
    }
}
