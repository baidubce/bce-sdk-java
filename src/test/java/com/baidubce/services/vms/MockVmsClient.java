/*
 * Copyright 2018-2019 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.vms;

import org.junit.Assert;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.vms.model.LaunchCallRequest;
import com.baidubce.services.vms.model.LaunchCallResponse;
import com.baidubce.util.JsonUtils;

/**
 * A Mock class for VmsClient's unit test, for verifying parameters and responses without a real request to server
 */
public class MockVmsClient extends VmsClient {
    public MockVmsClient(BceClientConfiguration config) {
        super(config);
    }

    protected <T extends AbstractBceResponse> T invokeHttpClient(InternalRequest request, Class<T> responseClass) {
        T response;
        try {
            response = responseClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(HttpMethodName.POST, request.getHttpMethod());
        LaunchCallRequest req;
        try {
            req = JsonUtils.loadFrom(request.getContent(), LaunchCallRequest.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals("12345678", req.getCalledNumber());
        Assert.assertEquals("39bf8b26-e763-4d45-81b7-7c3edd34fa57", req.getTemplateId());
        ((LaunchCallResponse) response).setCallId("abcd");
        ((LaunchCallResponse) response).setShowNum("1234");
        return response;
    }
}
