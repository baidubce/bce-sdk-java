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
package com.baidubce.services.iothisk.device.seplatform;

import com.baidubce.services.iothisk.device.model.ActiveMessage;
import com.baidubce.services.iothisk.device.model.CipherMessage;
import com.baidubce.services.iothisk.device.model.PlainMessage;

/**
 * Secure element interfaces.
 * It defines secure element basic operation interfaces, including encryption, decryption,
 * sign signature and message parsing.
 */
public interface SecureElement {

    /**
     * Generate device unique id
     *
     * @return device unique id in ascii encoding
     */
    String generateId();

    /**
     * Encrypt and sign provided plain message
     *
     * @param plainMessage specified plain message
     * @return cipher message, including encrypted message and its signature
     */
    CipherMessage encryptThenSign(PlainMessage plainMessage);

    /**
     * Encrypt plain message, and sign the signature with provided private key
     *
     * @param plainMessage specified plain message
     * @param signPrivateKey specified signature private key
     * @return cipher message, including encrypted message and its signature
     */
    CipherMessage encryptThenSign(PlainMessage plainMessage, byte[] signPrivateKey);

    /**
     * Decrypt encrypted message
     *
     * @param encryption specified encrypted message in byte array
     * @return plain message in byte array
     */
    byte[] decrypt(byte[] encryption);

    /**
     * Verify message signature.
     * If failed, an exception will be thrown.
     *
     * @param message specified message in byte array
     * @param signature specified signature in byte array
     */
    void verifySignature(byte[] message, byte[] signature);

    /**
     * Verify message and decrypt cipher message.
     *
     * @param cipherMessage specified cipher message
     * @return plain message decrypted from cipher message, if failed an exception will be thrown.
     */
    PlainMessage verifyThenDecrypt(CipherMessage cipherMessage);

    /**
     * Parse byte array cipher message.
     *
     * @param cipherMessage specified cipher message in byte array
     * @return cipher message object
     */
    CipherMessage parseCipherMessage(byte[] cipherMessage);

    /**
     * Parse byte array plain message.
     *
     * @param plainMessage specified plain message
     * @return plain message object
     */
    PlainMessage parsePlainMessage(byte[] plainMessage);

    /**
     * Parse byte array active message
     *
     * @param activeMessage specified active message
     * @return active message object
     */
    ActiveMessage parseActiveMessage(byte[] activeMessage);

    /**
     * Valid active message with secure element.
     * If failed, an exception will be thrown.
     *
     * @param activeMessage specified active message
     */
    void checkActiveMessage(ActiveMessage activeMessage);

}
