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

import static com.baidubce.services.iothisk.device.utils.Message.EXPIRED_MESSAGE;
import static com.baidubce.services.iothisk.device.utils.Message.UNKNOWN_DEVICE_SDK_TYPE;
import static java.lang.Math.abs;

import com.baidubce.services.iothisk.device.model.DeviceSdkType;

/**
 * Provides counter utils to valid device counter according to device sdk type
 */
public class CounterUtils {

    private static final long RTC_COUNTER_INTERVAL = 120;

    /**
     * Check message counter to against replay attack.
     *
     * @param deviceSdkType device sdk type
     * @param counter message counter
     * @param currentCounter current device counter
     */
    public static void validCounter(DeviceSdkType deviceSdkType, long counter, long currentCounter) {
        switch (deviceSdkType) {
            case NONE_RTC:
                if (counter <= currentCounter) {
                    throw new IllegalArgumentException(EXPIRED_MESSAGE);
                }
                break;
            case RTC:
                if (abs(currentCounter - counter) > RTC_COUNTER_INTERVAL) {
                    throw new IllegalArgumentException(EXPIRED_MESSAGE);
                }
                break;
            case NONE_COUNTER:
                // no counter check
                break;
            default:
                throw new RuntimeException(UNKNOWN_DEVICE_SDK_TYPE);
        }
    }

}
