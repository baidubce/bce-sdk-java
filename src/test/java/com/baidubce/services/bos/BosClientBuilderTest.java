/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.bos;

import org.junit.Assert;
import org.junit.Test;

import com.baidubce.BceClientConfiguration;
import com.baidubce.Protocol;
import com.baidubce.Region;

public class BosClientBuilderTest {

    @Test
    public void testBuild() {
        BosClient client = BosClientBuilder.create()
                .withCredentials("ak", "sk")
                .withRegion(Region.CN_S1)
                .withProtocol(Protocol.HTTPS)
                .build();

        Assert.assertNotNull(client);
        BceClientConfiguration config = client.getClientConfiguration();
        Assert.assertNotNull(config);
        Assert.assertEquals("ak", config.getCredentials().getAccessKeyId());
        Assert.assertEquals("sk", config.getCredentials().getSecretKey());
        Assert.assertEquals(Region.CN_S1, config.getRegion());
        Assert.assertEquals(Protocol.HTTPS, config.getProtocol());
    }
}
