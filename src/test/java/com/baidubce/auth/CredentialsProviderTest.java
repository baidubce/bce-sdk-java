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
package com.baidubce.auth;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.baidubce.BceClientException;

public class CredentialsProviderTest {

    private static final String ACCESS_KEY_PROPERTY = "bce.accessKeyId";
    private static final String SECRET_KEY_PROPERTY = "bce.secretAccessKey";

    @After
    public void tearDown() {
        System.clearProperty(ACCESS_KEY_PROPERTY);
        System.clearProperty(SECRET_KEY_PROPERTY);
    }

    @Test
    public void testStaticCredentialsProvider() {
        BceCredentials creds = new DefaultBceCredentials("ak", "sk");
        StaticCredentialsProvider provider = new StaticCredentialsProvider(creds);
        Assert.assertEquals(creds, provider.getCredentials());
    }

    @Test
    public void testSystemPropertyCredentialsProvider() {
        System.setProperty(ACCESS_KEY_PROPERTY, "prop-ak");
        System.setProperty(SECRET_KEY_PROPERTY, "prop-sk");

        SystemPropertyCredentialsProvider provider = new SystemPropertyCredentialsProvider();
        BceCredentials creds = provider.getCredentials();

        Assert.assertNotNull(creds);
        Assert.assertEquals("prop-ak", creds.getAccessKeyId());
        Assert.assertEquals("prop-sk", creds.getSecretKey());
    }

    @Test(expected = BceClientException.class)
    public void testSystemPropertyCredentialsProviderMissing() {
        SystemPropertyCredentialsProvider provider = new SystemPropertyCredentialsProvider();
        provider.getCredentials();
    }

    @Test
    public void testDefaultBceCredentialsProviderChain() {
        System.setProperty(ACCESS_KEY_PROPERTY, "chain-ak");
        System.setProperty(SECRET_KEY_PROPERTY, "chain-sk");

        DefaultBceCredentialsProviderChain chain = new DefaultBceCredentialsProviderChain();
        BceCredentials creds = chain.getCredentials();

        Assert.assertNotNull(creds);
        Assert.assertEquals("chain-ak", creds.getAccessKeyId());
    }
}
