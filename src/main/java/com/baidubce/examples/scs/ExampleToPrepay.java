/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.ScsOrderRequest;
import com.baidubce.services.scs.model.ScsOrderResponse;

import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
import static com.baidubce.examples.scs.ScsExampleUtil.exampleOrderRequest;
public class ExampleToPrepay {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsOrderRequest request = exampleOrderRequest();
        ScsOrderResponse response = client.toPrepay(request);
        System.out.println(response);
    }
}
