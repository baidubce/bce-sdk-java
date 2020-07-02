/*
 * Copyright 2018-2019 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iothisk.device.model;

import static com.google.common.primitives.Bytes.concat;

/**
 * Represent the cipher message
 */
public class CipherMessage {

    /**
     * encrypted message
     */
    private byte[] encryption;

    /**
     * message signature
     */
    private byte[] signature;

    /**
     * Constructs cipher message
     *
     * @param encryption encrypted cipher message
     * @param signature message signature
     */
    public CipherMessage(byte[] encryption, byte[] signature) {
        this.encryption = encryption;
        this.signature = signature;
    }

    /**
     * Constructs cipher message
     */
    public CipherMessage() {
    }

    /**
     * Get cipher message, including encrypted message and message signature
     *
     * @return cipher message in byte array
     */
    public byte[] getBytes() {
        return concat(encryption, signature);
    }

    /**
     * Set encrypted message
     *
     * @param encryption encrypted message
     */
    public void setEncryption(byte[] encryption) {
        this.encryption = encryption;
    }

    /**
     * Get encrypted message
     *
     * @return encrypted message
     */
    public byte[] getEncryption() {
        return encryption;
    }

    /**
     * Set message signature
     *
     * @param signature message signature
     */
    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    /**
     * Get message signature
     *
     * @return message signature
     */
    public byte[] getSignature() {
        return signature;
    }

}
