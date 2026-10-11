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
 * environment variables.
 */
public class EnvironmentVariableCredentialsProvider implements BceCredentialsProvider {

    private static final String ACCESS_KEY_ENV = "BCE_ACCESS_KEY_ID";
    private static final String SECRET_KEY_ENV = "BCE_SECRET_ACCESS_KEY";

    @Override
    public BceCredentials getCredentials() {
        String accessKey = System.getenv(ACCESS_KEY_ENV);
        String secretKey = System.getenv(SECRET_KEY_ENV);

        if (accessKey == null || secretKey == null) {
            throw new BceClientException("Unable to load credentials from environment variables "
                    + ACCESS_KEY_ENV + " and " + SECRET_KEY_ENV);
        }

        return new DefaultBceCredentials(accessKey, secretKey);
    }
}
