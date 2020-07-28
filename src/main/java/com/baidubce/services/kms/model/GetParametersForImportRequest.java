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

import com.baidubce.auth.BceCredentials;

/**
 * Provides options for creating master key.
 */
public class GetParametersForImportRequest extends GenericKmsRequest {

    /**
     * master key id
     */
    private String keyId;

    /**
     * the algorithm for user encrypt local key
     */
    private String wrappingAlgorithm;

    /**
     * the pubkey spec for user encrypt local key
     */
    private String wrappingKeySpec;

    /**
     * the type of
     */
    private String publicKeyEncoding;

    public GetParametersForImportRequest() {
        this.wrappingAlgorithm = "RSAES_PKCS1_V1_5";
        this.wrappingKeySpec = "RSA_2048";
    }

    public GetParametersForImportRequest(String keyId, String publicKeyEncoding) {
        this.keyId = keyId;
        this.wrappingAlgorithm = "RSAES_PKCS1_V1_5";
        this.wrappingKeySpec = "RSA_2048";
        this.publicKeyEncoding = publicKeyEncoding;
    }

    @Override
    public GetParametersForImportRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getWrappingAlgorithm() {
        return wrappingAlgorithm;
    }

    public String getWrappingKeySpec() {
        return wrappingKeySpec;
    }

    public String getPublicKeyEncoding() {
        return publicKeyEncoding;
    }

    public void setPublicKeyEncoding(String publicKeyEncoding) {
        this.publicKeyEncoding = publicKeyEncoding;
    }
}
// vim: et tw=100 ts=4 sw=4 cc=120
