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
package com.baidubce.services.iothisk.device.utils;

/**
 * Provides error message
 */
public class Message {

    public static final String INVALID_CIPHER_DATA = "The data to be encrypted or decrypted is invalid.";

    public static final String INVALID_DEVICE_ID = "The device ID is invalid.";

    public static final String DEVICE_SE_ID_CONFLICT = "This device se id conflicts with the exist se id.";

    public static final String INVALID_TIMESTAMP = "The counter to be encrypted or decrypted is invalid.";

    public static final String NULL_CONTRACT = "contract should not be null";

    public static final String NULL_SERIAL_NUMBER = "serial number should not be null";

    public static final String NULL_DEVICE_SDK_TYPE = "device sdk type should not be null";

    public static final String EXPIRED_MESSAGE = "Expired message";

    public static final String UNKNOWN_DEVICE_SDK_TYPE = "Unknown device key sdk type";

    public static final String INVALID_INT = "Invalid int data";

    public static final String INVALID_BYTES = "Invalid bytes";

    public static final String UNSUPPORTED_SE_TYPE = "Unsupported se type.";

    public static final String FAIL_GENERATE_ID = "Generate device id failed.";

    public static final String FAIL_SIGN = "Calculate signature failed.";

    public static final String INVALID_SIGNATURE = "Invalid signature.";

    public static final String FAIL_AES_ENCRYPT = "AES encrypt failed";

    public static final String FAIL_AES_DECRYPT = "AES decrypt failed";

    public static final String INVALID_AES_CIPHER_NAME = "Invalid AES cipher name.";

    public static final String INVALID_SERIAL_NUMBER = "Serial number is empty or its length greater than 32.";

    public static final String INVALID_DEVICE_COMPANY =
            "Device company name must consist of Chinese or English characters, numbers, hypen \"-\", "
                    + "or the underscore \"_\", and between 3 and 32 characters in length.";

    public static final String INVALID_DEVICE_TYPE =
            "Device type name must consist of Chinese or English characters, numbers, hypen \"-\", "
                    + "or the underscore \"_\", and between 3 and 32 characters in length.";

}
