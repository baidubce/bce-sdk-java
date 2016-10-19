/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Default implementation of the BceTokenCredentials interface that allows callers to pass in the BCE access key id,
 * secret key and session token in the constructor.
 */
public class DefaultBceSessionCredentials extends DefaultBceCredentials implements BceSessionCredentials {
    /**
     * The BCE session token.
     */
    private final String sessionToken;

    /**
     * Constructs a new Credentials object, with the specified access key id, secret key and session token.
     *
     * @param accessKeyId the BCE access key id.
     * @param secretKey   the BCE secret access key.
     * @param sessionToken the BCE session token.
     * @throws IllegalArgumentException The accessKeyId, secretKey and sessionToken should not be null or empty.
     */
    public DefaultBceSessionCredentials(String accessKeyId, String secretKey, String sessionToken) {
        super(accessKeyId, secretKey);
        checkNotNull(sessionToken, "token should not be null.");
        checkArgument(!sessionToken.isEmpty(), "token should not be empty.");
        this.sessionToken = sessionToken;
    }

    /**
     * @see BceSessionCredentials#getSessionToken()
     */
    @Override
    public String getSessionToken() {
        return this.sessionToken;
    }

}
