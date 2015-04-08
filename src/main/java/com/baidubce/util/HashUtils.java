/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility methods for computing MD5 sums.
 */
public class HashUtils {

    /**
     * Computes the MD5 hash of the data in the given input stream and returns it as an array of bytes.
     */
    public static byte[] computeMd5Hash(InputStream is) throws NoSuchAlgorithmException, IOException {
        return computeHash(is, MessageDigest.getInstance("MD5"));
    }

    public static byte[] computeSha256Hash(InputStream is) throws NoSuchAlgorithmException, IOException {
        return computeHash(is, MessageDigest.getInstance("SHA-256"));
    }

    public static byte[] computeHash(InputStream is, MessageDigest messageDigest) throws IOException {
        try {
            byte[] buffer = new byte[16384];
            int bytesRead = -1;
            while ((bytesRead = is.read(buffer, 0, buffer.length)) != -1) {
                messageDigest.update(buffer, 0, bytesRead);
            }
            return messageDigest.digest();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
            }
        }
    }
}
