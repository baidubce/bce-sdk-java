/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotshc.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * common utilities
 */
public class CommonUtils {

    public static String getSignature(String ak, Long ts, String sk) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(ak)
                || org.apache.commons.lang3.StringUtils.isEmpty(sk)) {
            return "";
        }
        String srcStr = String.format("%s&%d", ak, ts);
        return Hex.encodeHexString(DigestUtils.md5(StringUtils.getBytesUtf8(srcStr + sk)));
    }

    public static long getCurrentTimeMinutes() {
        return System.currentTimeMillis() / 60000;
    }
}
