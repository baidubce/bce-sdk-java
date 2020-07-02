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

import static com.baidubce.services.iothisk.device.utils.Message.FAIL_AES_DECRYPT;
import static com.baidubce.services.iothisk.device.utils.Message.FAIL_AES_ENCRYPT;
import static com.baidubce.services.iothisk.device.utils.Message.INVALID_AES_CIPHER_NAME;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;

/**
 * Provides AES crypto functions
 */
public class AesEncrypt {

    // default Cipher: AES/ECB/PKCS5Padding
    private static final String AES_ALGORITHM = "AES";
    private static final String AES_CTR_NO_PADDING_CIPHER = "AES/CTR/NoPadding";

    private static final Set<String> AES_MODES_WITH_IV = new HashSet<String>(
            Arrays.asList("CBC", "CFB", "CTR", "OFB", "PCBC"));
    private static final Set<String> AES_MODES_WITHOUT_IV = new HashSet<String>(
            Arrays.asList("ECB"));

    /**
     * Load secret key with provided key data and specified algorithm
     *
     * @param key specified key data in btye array
     * @param algorithm specified algorithm
     * @return secret key
     */
    public static SecretKey loadKey(byte[] key, String algorithm) {
        return new SecretKeySpec(key, algorithm);
    }

    /**
     * Encrypt message with specified secret key and cipher name.
     *
     * @param data specified message data in byte array
     * @param key specified secret key
     * @param cipherName specified cipher name
     * @return cipher message in byte array
     */
    public static byte[] encrypt(byte[] data, SecretKey key, String cipherName) {
        return encrypt(data, key, null, cipherName);
    }

    /**
     * Encrypt message with specified secret key, cipher name, init vector and cipher name.
     *
     * @param data specified message data in byte array
     * @param key specified secret key
     * @param iv specified init vector
     * @param cipherName specified cipher name
     * @return cipher message in byte array
     */
    public static byte[] encrypt(byte[] data, SecretKey key, IvParameterSpec iv, String cipherName) {
        try {
            Cipher cipher = Cipher.getInstance(cipherName);
            String mode = getModeName(cipherName);

            if (AES_MODES_WITHOUT_IV.contains(mode)) {
                cipher.init(Cipher.ENCRYPT_MODE, key);
            } else {
                iv = iv == null ? new IvParameterSpec(new byte[cipher.getBlockSize()]) : iv;
                cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            }
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new IllegalStateException(FAIL_AES_ENCRYPT, e);
        }
    }

    /**
     * Decrypt message with specified secret key, cipher name, init vector and cipher name.
     *
     * @param data specified cipher message data in byte array
     * @param key specified secret key
     * @param iv specified init vector
     * @param cipherName specified cipher name
     * @return plain message in byte array
     */
    public static byte[] decrypt(byte[] data, SecretKey key, IvParameterSpec iv, String cipherName) {
        try {
            Cipher cipher = Cipher.getInstance(cipherName);
            String mode = getModeName(cipherName);

            if (AES_MODES_WITHOUT_IV.contains(mode)) {
                cipher.init(Cipher.DECRYPT_MODE, key);
            } else {
                iv = iv == null ? new IvParameterSpec(new byte[cipher.getBlockSize()]) : iv;
                cipher.init(Cipher.DECRYPT_MODE, key, iv);
            }
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new IllegalStateException(FAIL_AES_DECRYPT, e);
        }
    }

    /**
     * Encrypt message with AES/CTR/NoPadding cipher
     *
     * @param data specified message data in byte array
     * @param key specified key in byte array
     * @param iv specified init vector
     * @return cipher message in byte array
     */
    public static byte[] encryptByCTRNoPadding(byte[] data, byte[] key, byte[] iv) {
        return encrypt(data, loadKey(key, AES_ALGORITHM), new IvParameterSpec(iv), AES_CTR_NO_PADDING_CIPHER);
    }

    /**
     * Decrypt message with AES/CTR/NoPadding cipher
     *
     * @param data specified message data in byte array
     * @param key specified key in byte array
     * @param iv specified init vector
     * @return plain message in byte array
     */
    public static byte[] decryptByCTRNoPadding(byte[] data, byte[] key, byte[] iv) {
        return decrypt(data, loadKey(key, AES_ALGORITHM), new IvParameterSpec(iv), AES_CTR_NO_PADDING_CIPHER);
    }

    private static String getModeName(String cipherName) {
        if (StringUtils.contains(cipherName, "/")) {
            String mode = StringUtils.split(cipherName, "/")[1];
            if (AES_MODES_WITHOUT_IV.contains(mode) || AES_MODES_WITH_IV.contains(mode)) {
                return mode;
            }
        }

        throw new IllegalArgumentException(INVALID_AES_CIPHER_NAME);
    }

}
