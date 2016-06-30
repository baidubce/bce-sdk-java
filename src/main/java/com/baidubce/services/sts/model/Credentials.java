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

import java.util.Date;

public class Credentials {
    private String accessKeyId;

    private String secretAccessKey;

    private String sessionToken;

    private Date expiration;

    public Credentials() {
    }

    public Credentials(String accessKeyId, String secretAccessKey, String sessionToken, Date expiration) {
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.sessionToken = sessionToken;
        this.expiration = expiration;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public Credentials withAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
        return this;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public Credentials withSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
        return this;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public Credentials withSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
        return this;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Credentials withExpiration(Date expiration) {
        this.expiration = expiration;
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Credentials that = (Credentials) o;

        if (accessKeyId != null ? !accessKeyId.equals(that.accessKeyId) : that.accessKeyId != null) {
            return false;
        }
        if (secretAccessKey != null ? !secretAccessKey.equals(that.secretAccessKey) : that.secretAccessKey != null) {
            return false;
        }
        if (sessionToken != null ? !sessionToken.equals(that.sessionToken) : that.sessionToken != null) {
            return false;
        }
        return !(expiration != null ? !expiration.equals(that.expiration) : that.expiration != null);

    }

    @Override
    public int hashCode() {
        int result = accessKeyId != null ? accessKeyId.hashCode() : 0;
        result = 31 * result + (secretAccessKey != null ? secretAccessKey.hashCode() : 0);
        result = 31 * result + (sessionToken != null ? sessionToken.hashCode() : 0);
        result = 31 * result + (expiration != null ? expiration.hashCode() : 0);
        return result;
    }
}
