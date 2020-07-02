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

import static com.baidubce.services.iothisk.device.utils.Message.UNSUPPORTED_SE_TYPE;

import com.baidubce.services.iothisk.device.model.Device;
import com.baidubce.services.iothisk.device.model.DeviceKey;

/**
 * Provides secure element factory according to device se type.
 * Currently, only <code>MBED_AKEY<code/> is supported.
 */
public class SecureElementFactory {

    /**
     * Provides secure element by device contract and device key.
     *
     * @param contract specified device contract
     * @param deviceKey specified device key
     * @return successful secure element, otherwise an exception will be thrown
     */
    public static SecureElement createSe(Device contract, DeviceKey deviceKey) {
        switch (deviceKey.getSeType()) {
            case MBED_AKEY:
                return new MbedAkeySe(contract, deviceKey);
            default:
                throw new IllegalArgumentException(UNSUPPORTED_SE_TYPE);
        }
    }

}
