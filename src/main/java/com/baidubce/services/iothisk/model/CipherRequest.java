/*
 * Copyright 2018 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iothisk.model;

import static com.baidubce.services.iothisk.model.EncodeType.BASE64;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represent the request for message encryption/decryption.
 */
public class CipherRequest extends AbstractBceRequest {

    /**
     * Message data requiring for encryption / decryption.
     */
    private String data;

    /**
     * Plain message encoding type, default is base64.
     */
    private EncodeType type = BASE64;

    /**
     * Cipher message encoding type, default is base64.
     */
    private EncodeType cipherEncodeType = BASE64;

    /**
     * Get the message data requiring for encryption / decryption.
     *
     * @return message data requiring for encryption / decryption
     */
    public String getData() {
        return data;
    }

    /**
     * Set the message data requiring for encryption / decryption.
     *
     * @param data message data requiring for encryption / decryption
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Get the message encoding type.
     *
     * @return message encoding type
     */
    public EncodeType getType() {
        return type;
    }

    /**
     * Set the message encoding type.
     *
     * @param encodeType message encoding type
     */
    public void setType(EncodeType encodeType) {
        this.type = encodeType;
    }

    /**
     * Get the cipher message encoding type.
     *
     * @return cipher message encoding type
     */
    public EncodeType getCipherEncodeType() {
        return cipherEncodeType;
    }

    /**
     * Set the cipher message encoding type.
     *
     * @param encodeType cipher message encoding type
     */
    public void setCipherEncodeType(EncodeType encodeType) {
        this.cipherEncodeType = encodeType;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return CipherRequest with credentials.
     */
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
