/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.util;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;

/**
 * Utility methods for encoding and decoding base64.
 */
public class Base64Utils {
    /**
     * Convert str to Base64
     * @param str the string need encoded
     * @return the base64 code of str
     */
    public static String encode(String str) {
        String base64Str = "";

        try {
            byte[] data = str.getBytes("utf-8");
            base64Str = DatatypeConverter.printBase64Binary(data);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("failed to encode the string :" + str, e);
        }

        return base64Str;
    }

    /**
     * decode base64 string
     * @param base64str the string need decoded
     * @return Decoded string
     */
    public static String decode(String base64str) {
        String str = "";
        byte[] base64Data = DatatypeConverter.parseBase64Binary(base64str);

        try {
            str = new String(base64Data, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("failed to decode the string :" + base64str, e);
        }

        return str;
    }
}