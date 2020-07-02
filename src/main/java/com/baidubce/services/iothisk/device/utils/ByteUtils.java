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

import static com.baidubce.services.iothisk.device.utils.Message.INVALID_BYTES;
import static com.baidubce.services.iothisk.device.utils.Message.INVALID_INT;
import static com.baidubce.services.iothisk.device.utils.Message.INVALID_TIMESTAMP;
import static com.google.common.primitives.UnsignedLongs.parseUnsignedLong;
import static javax.xml.bind.DatatypeConverter.parseHexBinary;
import static javax.xml.bind.DatatypeConverter.printHexBinary;

import org.apache.commons.lang3.StringUtils;

/**
 * Provides byte array operation utilities
 */
public class ByteUtils {

    /**
     * Get byte array from hex string
     *
     * @param data specified hex string data
     * @return byte array
     */
    public static byte[] toBytesFromHex(String data) {
        // add leading 0 to even-length format required by `DatatypeConverter.parseHexBinary`
        if (data.length() % 2 == 1) {
            data = StringUtils.leftPad(data, data.length() + 1, '0');
        }
        return parseHexBinary(data);
    }

    /**
     * Get hex string from byte array
     *
     * @param data specified byte array
     * @return hex string
     */
    public static String toHexStringFromBytes(byte[] data) {
        return printHexBinary(data);
    }

    /**
     * Get byte array from int type value
     *
     * @param number specified in type value
     * @return byte array
     */
    public static byte[] toBytesFromInt(int number) {
        try {
            String hexNumber = Integer.toHexString(number);
            return toBytesFromHex(hexNumber);
        } catch (Exception e) {
            throw new IllegalArgumentException(INVALID_INT);
        }
    }

    /**
     * Get long value from byte array
     *
     * @param data specified byte array
     * @return successful long type value, otherwise an exception will be thrown
     */
    public static long toLongFromBytes(byte[] data) {
        try {
            return parseUnsignedLong(printHexBinary(data), 16);
        } catch (Exception e) {
            throw new IllegalArgumentException(INVALID_BYTES);
        }
    }

    /**
     * Get byte array from counter value
     *
     * @param counter specified counter value
     * @return successful byte value array, otherwise an exception will be thrown
     */
    public static byte[] getCounterBytes(long counter) {
        try {
            String hexCounter = Long.toHexString(counter);
            // counter represent in 4 bytes
            hexCounter = StringUtils.leftPad(hexCounter, 8, '0');
            return toBytesFromHex(hexCounter);
        } catch (Exception e) {
            throw new IllegalArgumentException(INVALID_TIMESTAMP);
        }
    }

}
