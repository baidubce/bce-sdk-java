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

import com.baidubce.model.AbstractBceResponse;

/**
 * Represent the response of encryption/decryption result.
 */
public class CipherResponse extends AbstractBceResponse {

    /**
     * Encryption / decryption result data.
     */
    private String data;

    /**
     * Get the encryption / decryption result data.
     *
     * @return the encryption / decryption result data
     */
    public String getData() {
        return data;
    }

    /**
     * Set the encryption / decryption result data.
     *
     * @param data encryption / decryption result data
     */
    public void setData(String data) {
        this.data = data;
    }
}
