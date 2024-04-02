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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vms.model.LaunchCallRequest;
import com.baidubce.services.vms.model.LaunchCallResponse;

/**
 * JUnit test class for VmsClient
 */
public class VmsClientTest {
    private VmsClient client;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setEndpoint("https://vms.baidubce.com");
        config.setCredentials(new DefaultBceCredentials("userAk", "userSk"));
        client = new MockVmsClient(config);
    }

    @After
    public void tearDown() {
        client.shutdown();
    }

    @Test
    public void lauchcallTest() {
        LaunchCallRequest request = new LaunchCallRequest();
        request.setCalledNumber("12345678");
        request.setTemplateId("39bf8b26-e763-4d45-81b7-7c3edd34fa57");
        request.addTemplateParameter("content", "您的产品线有故障发生，请及时登录处理");
        try {
            LaunchCallResponse response = client.launchCall(request);
            Assert.assertEquals("abcd", response.getCallId());
            Assert.assertEquals("1234", response.getShowNum());
        } catch (BceServiceException e) {
            e.printStackTrace();
        }
    }
}
