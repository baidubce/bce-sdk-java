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

import static com.baidubce.services.iothisk.device.utils.ByteUtils.toBytesFromInt;
import static com.google.common.primitives.Bytes.concat;

/**
 * Represent the device active message
 */
public class ActiveMessage {

    /**
     * Device unique id, which is hex string
     */
    private String deviceId;

    /**
     * Device secure element id
     */
    private String seId;

    /**
     * Device sdk type
     */
    private DeviceSdkType sdkType;

    /**
     * Get device id
     *
     * @return device id in hex string format
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Set device id
     *
     * @param deviceId device id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * Get secure element id
     *
     * @return secure element id
     */
    public String getSeId() {
        return seId;
    }

    /**
     * Set secure element id
     *
     * @param seId secure element id
     */
    public void setSeId(String seId) {
        this.seId = seId;
    }

    /**
     * Get device sdk type
     *
     * @return device sdk type
     */
    public DeviceSdkType getSdkType() {
        return sdkType;
    }

    /**
     * Set device sdk type
     *
     * @param sdkType device sdk type
     */
    public void setSdkType(DeviceSdkType sdkType) {
        this.sdkType = sdkType;
    }

    /**
     * Get active message in byte array
     *
     * @return active message in byte array
     */
    public byte[] getBytes() {
        return concat(deviceId.getBytes(), seId.getBytes(), toBytesFromInt(sdkType.getStatusCode()));
    }

}
