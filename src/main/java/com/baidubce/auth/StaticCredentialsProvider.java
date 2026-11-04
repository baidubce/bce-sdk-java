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

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Simple implementation of {@link BceCredentialsProvider} that just wraps a static
 * {@link BceCredentials} object.
 */
public class StaticCredentialsProvider implements BceCredentialsProvider {

    private final BceCredentials credentials;

    public StaticCredentialsProvider(BceCredentials credentials) {
        checkNotNull(credentials, "credentials should not be null.");
        this.credentials = credentials;
    }

    @Override
    public BceCredentials getCredentials() {
        return this.credentials;
    }
}
