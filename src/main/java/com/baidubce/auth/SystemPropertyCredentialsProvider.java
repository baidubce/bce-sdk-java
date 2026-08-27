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

import com.baidubce.BceClientException;

/**
 * {@link BceCredentialsProvider} implementation that loads credentials from
 * Java system properties.
 */
public class SystemPropertyCredentialsProvider implements BceCredentialsProvider {

    private static final String ACCESS_KEY_PROPERTY = "bce.accessKeyId";
    private static final String SECRET_KEY_PROPERTY = "bce.secretAccessKey";

    @Override
    public BceCredentials getCredentials() {
        String accessKey = System.getProperty(ACCESS_KEY_PROPERTY);
        String secretKey = System.getProperty(SECRET_KEY_PROPERTY);

        if (accessKey == null || secretKey == null) {
            throw new BceClientException("Unable to load credentials from Java system properties "
                    + ACCESS_KEY_PROPERTY + " and " + SECRET_KEY_PROPERTY);
        }

        return new DefaultBceCredentials(accessKey, secretKey);
    }
}
