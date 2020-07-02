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
package com.baidubce.services.iothisk.device.crypto;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;

/**
 * Provides hash crypto operations
 */
public class HashCrypto {

    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final int PBKDF2_DERIVED_KEY_LENGTH_IN_BIT = 16 * 8;

    /**
     * HMAC_SHA56 hash function
     *
     * @param data specified message data in byte array
     * @param keyBytes specified key in byte array
     * @return hash data in byte array
     * @throws Exception NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, IllegalArgumentException
     */
    public static byte[] hmacSha256(byte[] data, byte[] keyBytes) throws Exception {
        Mac mac = Mac.getInstance(HMAC_SHA256);
        SecretKey key = new SecretKeySpec(keyBytes, HMAC_SHA256);
        mac.init(key);
        mac.reset();
        mac.update(data, 0, data.length);
        return mac.doFinal();
    }

    /**
     * PBKDF2(Password-Based Key Derivation Function) with hmac sha256 key derivation function.
     *
     * @param password specified password in byte array
     * @param salt specified salt in byte array
     * @param iteration iteration counter
     * @return derived key in byte array
     */
    public static byte[] pbkdf2WithHmacSha256(byte[] password, byte[] salt, int iteration) {
        PKCS5S2ParametersGenerator generator = new PKCS5S2ParametersGenerator(new SHA256Digest());
        generator.init(password, salt, iteration);
        return ((KeyParameter) generator.generateDerivedParameters(PBKDF2_DERIVED_KEY_LENGTH_IN_BIT)).getKey();
    }

}
