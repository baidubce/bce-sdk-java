/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.iotshc.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * unit test for common utilities
 */
public class CommonUtilsTest {

    @Test
    public void getSignatureTest() {
        String ak = "mock-ak";
        String sk = "mock-sk";
        long ts = 1586849331;
        String signature;
        String expSignature;

        signature = CommonUtils.getSignature(ak, ts, sk);
        expSignature = "d10db216a1dc6b79ddc0371516b1d38d";
        Assert.assertEquals(expSignature, signature);

        ak = "";
        sk = null;
        signature = CommonUtils.getSignature(ak, ts, sk);
        expSignature = "";
        Assert.assertEquals(expSignature, signature);
    }
}
