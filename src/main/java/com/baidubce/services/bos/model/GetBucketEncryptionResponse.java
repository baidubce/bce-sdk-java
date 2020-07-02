/*
 * Copyright 2014-2019 Baidu, Inc.
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
package com.baidubce.services.bos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Get BucketEncryption Response
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBucketEncryptionResponse extends BosResponse {

    /**
     * The Bucket Encryption
     */
    private String encryptionAlgorithm;
    private String kmsMasterKeyId;


    /**
     * Constructs a void constructor for GetBucketEncryptionResponse.
     */
    public GetBucketEncryptionResponse() {

    }

    public GetBucketEncryptionResponse(String encryptionAlgorithm, String kmsMasterKeyId) {
        this.encryptionAlgorithm = encryptionAlgorithm;
        this.kmsMasterKeyId = kmsMasterKeyId;
    }

    public String getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    public void setEncryptionAlgorithm(String encryptionAlgorithm) {
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    public String getKmsMasterKeyId() {
        return kmsMasterKeyId;
    }

    public void setKmsMasterKeyId(String kmsMasterKeyId) {
        this.kmsMasterKeyId = kmsMasterKeyId;
    }

    @Override
    public String toString() {
        return "GetBucketEncryptionResponse{"
                + "encryptionAlgorithm='" + encryptionAlgorithm + '\''
                + ", kmsMasterKeyId='" + kmsMasterKeyId + '\''
                + '}';
    }
}
