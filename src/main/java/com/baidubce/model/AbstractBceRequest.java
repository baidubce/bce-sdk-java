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
package com.baidubce.model;

import org.apache.http.annotation.NotThreadSafe;

import com.baidubce.auth.BceCredentials;

/**
 * Base class for all BCE web service request objects.
 */
@NotThreadSafe
public abstract class AbstractBceRequest {

    /**
     * The optional credentials to use for this request - overrides the default credentials set at the client level.
     */
    private BceCredentials credentials;

    /**
     * Returns the optional credentials to use to sign this request, overriding the default credentials set at the
     * client level.
     *
     * @return The optional credentials to use to sign this request, overriding the default credentials set at the
     * client level.
     */
    public BceCredentials getRequestCredentials() {
        return this.credentials;
    }

    /**
     * Sets the optional credentials to use for this request, overriding the default credentials set at the client
     * level.
     *
     * @param credentials The optional BCE security credentials to use for this request, overriding the default
     *                    credentials set at the client level.
     */
    public void setRequestCredentials(BceCredentials credentials) {
        this.credentials = credentials;
    }

    public abstract AbstractBceRequest withRequestCredentials(BceCredentials credentials);
}
