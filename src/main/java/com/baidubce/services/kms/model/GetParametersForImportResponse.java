/*
 * Copyright 2018 Baidu, Inc.
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
package com.baidubce.services.kms.model;

/**
 * Provides options for creating master key.
 */
public class GetParametersForImportResponse extends KmsResponse {

    /**
     * master key id
     */
    private String keyId;

    /**
     * the token for import local key
     */
    private String importToken;

    /**
     * the valid deadline for import local key
     */
    private String tokenValidTill;

    /**
     * the publicKey for import local key
     */
    private String publicKey;

    public GetParametersForImportResponse() {
    }

    public GetParametersForImportResponse(String keyId, String importToken, String tokenValidTill, String publicKey) {
        this.keyId = keyId;
        this.importToken = importToken;
        this.tokenValidTill = tokenValidTill;
        this.publicKey = publicKey;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getImportToken() {
        return importToken;
    }

    public void setImportToken(String importToken) {
        this.importToken = importToken;
    }

    public String getTokenValidTill() {
        return tokenValidTill;
    }

    public void setTokenValidTill(String tokenValidTill) {
        this.tokenValidTill = tokenValidTill;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

}
// vim: et tw=100 ts=4 sw=4 cc=120
