/*
 * Copyright (c) 2016 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.sts.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.Date;

public class GetSessionTokenResponse extends AbstractBceResponse {
    private String accessKeyId;

    private String secretAccessKey;

    private String sessionToken;

    private Date expiration;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Credentials{");
        sb.append("accessKeyId='").append(accessKeyId).append('\'');
        sb.append(", secretAccessKey='").append(secretAccessKey).append('\'');
        sb.append(", sessionToken='").append(sessionToken).append('\'');
        sb.append(", expiration=").append(expiration);
        sb.append('}');
        return sb.toString();
    }
}
