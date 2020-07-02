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

import static com.baidubce.services.iothisk.device.crypto.AesEncrypt.decryptByCTRNoPadding;
import static com.baidubce.services.iothisk.device.crypto.AesEncrypt.encryptByCTRNoPadding;
import static com.baidubce.services.iothisk.device.utils.ByteUtils.toBytesFromHex;
import static com.baidubce.services.iothisk.device.utils.ByteUtils.toHexStringFromBytes;
import static com.baidubce.services.iothisk.device.utils.ByteUtils.toLongFromBytes;
import static com.baidubce.services.iothisk.device.crypto.HashCrypto.hmacSha256;
import static com.baidubce.services.iothisk.device.crypto.HashCrypto.pbkdf2WithHmacSha256;
import static com.baidubce.services.iothisk.device.utils.Message.DEVICE_SE_ID_CONFLICT;
import static com.baidubce.services.iothisk.device.utils.Message.FAIL_GENERATE_ID;
import static com.baidubce.services.iothisk.device.utils.Message.FAIL_SIGN;
import static com.baidubce.services.iothisk.device.utils.Message.INVALID_CIPHER_DATA;
import static com.baidubce.services.iothisk.device.utils.Message.INVALID_DEVICE_ID;
import static com.baidubce.services.iothisk.device.utils.Message.INVALID_SIGNATURE;
import static java.lang.Math.min;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.baidubce.services.iothisk.device.model.ActiveMessage;
import com.baidubce.services.iothisk.device.model.CipherMessage;
import com.baidubce.services.iothisk.device.model.Device;
import com.baidubce.services.iothisk.device.model.DeviceKey;
import com.baidubce.services.iothisk.device.model.DeviceSdkType;
import com.baidubce.services.iothisk.device.model.PlainMessage;

/**
 * Mbed akey secure element.
 *
 * It provides mbed_akey type secure element.
 */
public class MbedAkeySe implements SecureElement {

    private final Device device;
    private final DeviceKey deviceKey;

    private final byte[] seKey;
    private final byte[] seIv;

    private static final int DEVICE_ID_BYTES_LENGTH = 64;
    private static final int CIPHER_MESSAGE_SIGNATURE_BYTE_LENGTH = 32;
    private static final int KEY_ITERATION_COUNT = 50;
    private static final int IV_ITERATION_COUNT = 25;
    private static final int TIMESTAMP_BYTE_LENGTH = 4;
    private static final int SDK_TYPE_BYTES_LENGTH = 1;

    /**
     * Construct a mbed akey secure element using the specified device and device key
     *
     * @param device specified device
     * @param deviceKey specified device key
     */
    public MbedAkeySe(Device device, DeviceKey deviceKey) {
        this.device = device;
        this.deviceKey = deviceKey;

        byte[] masterKeyBytes = toBytesFromHex(device.getMasterKey());
        this.seKey = pbkdf2WithHmacSha256(masterKeyBytes, deviceKey.getSeId().getBytes(), KEY_ITERATION_COUNT);
        this.seIv = pbkdf2WithHmacSha256(masterKeyBytes, deviceKey.getSeId().getBytes(), IV_ITERATION_COUNT);
    }

    /**
     * Generate device unique id
     *
     * @return device unique id in ascii encoding
     */
    @Override
    public String generateId() {
        String idBeforeHash = device.getDeviceCompany() + device.getDeviceType() + deviceKey.getSeId();
        byte[] masterKeyBytes = toBytesFromHex(device.getMasterKey());

        try {
            byte[] deviceIdBytes = hmacSha256(idBeforeHash.getBytes(), masterKeyBytes);
            return toHexStringFromBytes(deviceIdBytes);
        } catch (Exception e) {
            throw new IllegalStateException(FAIL_GENERATE_ID);
        }
    }

    /**
     * Encrypt and sign provided plain message
     *
     * @param plainMessage specified plain message
     * @return cipher message, including encrypted message and its signature
     */
    @Override
    public CipherMessage encryptThenSign(PlainMessage plainMessage) {
        return encryptThenSign(plainMessage, seKey);
    }

    /**
     * Encrypt plain message, and sign the signature with provided private key
     *
     * @param plainMessage specified plain message
     * @param signPrivateKey specified signature private key
     * @return cipher message, including encrypted message and its signature
     */
    @Override
    public CipherMessage encryptThenSign(PlainMessage plainMessage, byte[] signPrivateKey) {
        byte[] encryption = encryptByCTRNoPadding(plainMessage.getBytes(), seKey, seIv);

        try {
            byte[] signature = hmacSha256(encryption, signPrivateKey);
            return new CipherMessage(encryption, signature);
        } catch (Exception e) {
            throw new IllegalStateException(FAIL_SIGN, e);
        }
    }

    /**
     * Decrypt encrypted message
     *
     * @param encryption specified encrypted message in byte array
     * @return plain message in byte array
     */
    @Override
    public byte[] decrypt(byte[] encryption) {
        return decryptByCTRNoPadding(encryption, seKey, seIv);
    }

    /**
     * Verify message signature.
     * If failed, an exception will be thrown.
     *
     * @param message specified message in byte array
     * @param signature specified signature in byte array
     */
    @Override
    public void verifySignature(byte[] message, byte[] signature) {
        byte[] calculatedSign = null;

        try {
            calculatedSign = hmacSha256(message, seKey);
        } catch (Exception e) {
            throw new IllegalStateException(FAIL_SIGN, e);
        }

        if (!Arrays.equals(calculatedSign, signature)) {
            throw new IllegalArgumentException(INVALID_SIGNATURE);
        }
    }

    /**
     * Verify message and decrypt cipher message.
     *
     * @param cipherMessage specified cipher message
     * @return plain message decrypted from cipher message, if failed an exception will be thrown.
     */
    @Override
    public PlainMessage verifyThenDecrypt(CipherMessage cipherMessage) {
        verifySignature(cipherMessage.getEncryption(), cipherMessage.getSignature());
        byte[] message = decrypt(cipherMessage.getEncryption());

        return parsePlainMessage(message);
    }

    /**
     * Parse byte array cipher message.
     *
     * @param cipherMessage specified cipher message in byte array
     * @return cipher message object
     */
    @Override
    public CipherMessage parseCipherMessage(byte[] cipherMessage) {
        if (ArrayUtils.getLength(cipherMessage) <= TIMESTAMP_BYTE_LENGTH + CIPHER_MESSAGE_SIGNATURE_BYTE_LENGTH) {
            throw new IllegalArgumentException(INVALID_CIPHER_DATA);
        }

        CipherMessage cipher = new CipherMessage();
        cipher.setEncryption(Arrays.copyOfRange(cipherMessage, 0,
                cipherMessage.length - CIPHER_MESSAGE_SIGNATURE_BYTE_LENGTH));
        cipher.setSignature(Arrays.copyOfRange(cipherMessage,
                cipherMessage.length - CIPHER_MESSAGE_SIGNATURE_BYTE_LENGTH, cipherMessage.length));
        return cipher;
    }

    /**
     * Parse byte array plain message.
     *
     * @param plainMessage specified plain message
     * @return plain message object
     */
    @Override
    public PlainMessage parsePlainMessage(byte[] plainMessage) {
        PlainMessage message = new PlainMessage();
        message.setMessage(getMessage(plainMessage));
        message.setCounter(getTimestamp(plainMessage));

        return message;
    }

    /**
     * Parse byte array active message
     *
     * @param activeMessage specified active message
     * @return active message object
     */
    @Override
    public ActiveMessage parseActiveMessage(byte[] activeMessage) {
        ActiveMessage message = new ActiveMessage();
        message.setDeviceId(getDeviceId(activeMessage));
        message.setSeId(getSeId(activeMessage));
        message.setSdkType(getSdkType(activeMessage));

        return message;
    }

    /**
     * Valid active message with secure element.
     * If failed, an exception will be thrown.
     *
     * @param activeMessage specified active message
     */
    @Override
    public void checkActiveMessage(ActiveMessage activeMessage) {
        checkDeviceId(activeMessage.getDeviceId());
        checkSeId(activeMessage.getSeId());
    }

    private void checkDeviceId(String deviceId) {
        if (!StringUtils.equals(generateId(), deviceId)) {
            throw new IllegalArgumentException(INVALID_DEVICE_ID);
        }
    }

    private void checkSeId(String seId) {
        if (StringUtils.isEmpty(deviceKey.getSeId()) || StringUtils.isEmpty(seId)) {
            return;
        }

        if (!StringUtils.equals(deviceKey.getSeId(), seId)) {
            throw new IllegalArgumentException(DEVICE_SE_ID_CONFLICT);
        }
    }

    private String getSeId(byte[] activeMessage) {
        return new String(getSeIdBytes(activeMessage));
    }

    /**
     * Split active cipher data to device id/se id/sdk type
     * Active text format:
     * ----- x bytes ----| ---- n bytes -----| ---- 1 bytes ----|
     * |    device id    |      SE ID        |      sdk type    |
     * @param activeMessage the plain active message, the se id may be empty
     * @return the SE ID in string
     */
    private byte[] getSeIdBytes(byte[] activeMessage) {
        if (activeMessage.length <= DEVICE_ID_BYTES_LENGTH + SDK_TYPE_BYTES_LENGTH) {
            return ArrayUtils.EMPTY_BYTE_ARRAY;
        }
        return Arrays.copyOfRange(activeMessage, DEVICE_ID_BYTES_LENGTH, activeMessage.length - SDK_TYPE_BYTES_LENGTH);
    }

    /**
     * Split active cipher data to device id/se id/sdk type
     * Active text format:
     * ----- x bytes ----| ---- n bytes -----| ---- 1 bytes ----|
     * |    device id    |      SE ID        |      sdk type    |
     * @param activeMessage the plain active message, the se id may be empty
     * @return the sdk type
     */
    private DeviceSdkType getSdkType(byte[] activeMessage) {
        if (activeMessage.length <= DEVICE_ID_BYTES_LENGTH) {
            return DeviceSdkType.NONE_RTC;
        }

        String sdkTypeStr = toHexStringFromBytes(Arrays.copyOfRange(activeMessage,
                activeMessage.length - SDK_TYPE_BYTES_LENGTH, activeMessage.length));
        return DeviceSdkType.parse(sdkTypeStr);
    }

    /**
     * Split active cipher data to device id/se id/sdk type
     * Active text format:
     * ----- x bytes ----| ---- n bytes -----| ---- 1 bytes ----|
     * |    device id     |      SE ID        |      sdk type    |
     * @param activeMessage the plain active message
     * @return the device id in string
     */
    private String getDeviceId(byte[] activeMessage) {
        int len = min(DEVICE_ID_BYTES_LENGTH, activeMessage.length);
        return new String(activeMessage, 0, len);
    }

    /**
     * Split decrypted text to message and counter
     * decrypted text format:
     * ----- 4 bytes ----| ---- n bytes -----
     * |    counter    |      message      |
     *
     * @param plaintext the decrypted text
     * @return the list contains the message and counter
     */
    private static byte[] getMessage(byte[] plaintext) {
        return Arrays.copyOfRange(plaintext, TIMESTAMP_BYTE_LENGTH, plaintext.length);
    }

    /**
     * Split decrypted text to message and counter
     * decrypted text format:
     * ----- 4 bytes ----| ---- n bytes -----
     * |    counter    |      message      |
     *
     * @param plaintext the decrypted text
     * @return the counter in long
     */
    private static long getTimestamp(byte[] plaintext) {
        byte[] timestamp = Arrays.copyOfRange(plaintext, 0, TIMESTAMP_BYTE_LENGTH);
        return toLongFromBytes(timestamp);
    }


}
