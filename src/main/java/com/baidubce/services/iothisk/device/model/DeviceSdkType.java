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
package com.baidubce.services.iothisk.device.model;

import static com.baidubce.services.iothisk.device.utils.Message.UNKNOWN_DEVICE_SDK_TYPE;

/**
 * Represents device sdk type.
 */
public enum DeviceSdkType {

    /**
     * NONE_RTC device sdk type
     * Message replay attack will be check by none_rtc counter method,
     * in which the counter may be an increased int value.
     */
    NONE_RTC(0),

    /**
     * RTC device sdk type
     * Message replay attack will be check by rtc counter method,
     * in which the counter may be an timestamp in seconds generated from rtc.
     */
    RTC(1),

    /**
     * NONE_COUNTER device sdk type
     * It specifies that the device will not check message counter to against replay attack.
     */
    NONE_COUNTER(2);

    /**
     * status code of device sdk type
     */
    private final int statusCode;

    /**
     * Construct device sdk type with an status code
     *
     * @param statusCode status code
     */
    DeviceSdkType(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Get status code of related device sdk type
     *
     * @return status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Parse string type status code to corresponding device sdk type
     *
     * @param value string type status code
     * @return successful return an device sdk type, otherwise an exception will be thrown
     */
    public static DeviceSdkType parse(String value) {
        try {
            int sdkTypeCode = Integer.valueOf(value);
            for (DeviceSdkType type : DeviceSdkType.values()) {
                if (type.statusCode == sdkTypeCode) {
                    return type;
                }
            }
            throw new IllegalArgumentException(UNKNOWN_DEVICE_SDK_TYPE);
        } catch (Exception e) {
            throw new IllegalArgumentException(UNKNOWN_DEVICE_SDK_TYPE);
        }
    }

}
