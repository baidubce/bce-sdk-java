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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.BceClientException;

/**
 * Default implementation of {@link BceCredentialsProvider} that chain multiple providers
 * in the following order:
 * <ul>
 *     <li>Java System Properties</li>
 *     <li>Environment Variables</li>
 * </ul>
 */
public class DefaultBceCredentialsProviderChain implements BceCredentialsProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultBceCredentialsProviderChain.class);

    private final List<BceCredentialsProvider> providers = new ArrayList<BceCredentialsProvider>();

    public DefaultBceCredentialsProviderChain() {
        providers.add(new SystemPropertyCredentialsProvider());
        providers.add(new EnvironmentVariableCredentialsProvider());
    }

    @Override
    public BceCredentials getCredentials() {
        for (BceCredentialsProvider provider : providers) {
            try {
                BceCredentials credentials = provider.getCredentials();
                if (credentials != null && credentials.getAccessKeyId() != null
                        && credentials.getSecretKey() != null) {
                    return credentials;
                }
            } catch (Exception e) {
                LOGGER.debug("Unable to load credentials from " + provider.getClass().getSimpleName()
                        + ": " + e.getMessage());
            }
        }
        throw new BceClientException("Unable to load credentials from any provider in the chain.");
    }
}
