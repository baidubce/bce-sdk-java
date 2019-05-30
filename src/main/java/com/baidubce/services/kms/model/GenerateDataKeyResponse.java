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
 * Contains the details returned from Kms after calling the GenerateDataKey operation.
 */
public class GenerateDataKeyResponse extends KmsResponse {

    private String ciphertext;

    private String keyId;

    private String plaintext;

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

    public String getPlaintext() {
        return plaintext;
    }
}
// vim: et tw=100 ts=4 sw=4 cc=120
