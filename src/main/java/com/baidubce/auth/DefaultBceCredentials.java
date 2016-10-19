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
 * Default implementation of the Credentials interface that allows callers to pass in the BCE access key and secret
 * access in the constructor.
 */
public class DefaultBceCredentials implements BceCredentials {

    /**
     * The BCE access key ID.
     */
    private final String accessKeyId;

    /**
     * The BCE secret access key.
     */
    private final String secretKey;

    /**
     * Constructs a new Credentials object, with the specified access key id and secret key.
     *
     * @param accessKeyId the BCE access key id.
     * @param secretKey   the BCE secret access key.
     *
     * @throws java.lang.IllegalArgumentException The accessKeyId, secretKey should not be null or empty.
     */
    public DefaultBceCredentials(String accessKeyId, String secretKey) {
        checkNotNull(accessKeyId, "accessKeyId should not be null.");
        checkArgument(!accessKeyId.isEmpty(), "accessKeyId should not be empty.");
        checkNotNull(secretKey, "secretKey should not be null.");
        checkArgument(!secretKey.isEmpty(), "secretKey should not be empty.");

        this.accessKeyId = accessKeyId;
        this.secretKey = secretKey;
    }

    /**
     * @see com.baidubce.auth.BceCredentials#getAccessKeyId()
     */
    @Override
    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    /**
     * @see com.baidubce.auth.BceCredentials#getSecretKey()
     */
    @Override
    public String getSecretKey() {
        return this.secretKey;
    }

}
