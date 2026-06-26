/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.examples.scs;

import com.baidubce.services.scs.ScsClient;
import com.baidubce.services.scs.model.instance.ScsPriceRequest;
import com.baidubce.services.scs.model.instance.ScsPriceResponse;
import com.baidubce.util.JsonUtils;

import static com.baidubce.examples.scs.ScsExampleUtil.createClient;
import static com.baidubce.examples.scs.ScsExampleUtil.examplePriceRequest;
public class ExampleGetCreatePrice {
    public static void main(String[] args) {
        ScsClient client = createClient();
        ScsPriceRequest request = examplePriceRequest();
        ScsPriceResponse response = client.getCreatePrice(request);
        System.out.println(JsonUtils.toJsonString(response));
    }
}
