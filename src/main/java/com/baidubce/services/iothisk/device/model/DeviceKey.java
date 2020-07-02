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

import com.baidubce.services.iothisk.device.seplatform.SecureElement;

/**
 * Represents device key, corresponding to specified device
 */
public class DeviceKey {

    /**
     * device unique id
     */
    private String deviceId;

    /**
     * device secure element id
     */
    private String seId;

    /**
     * device secure element type
     */
    private SeType seType;

    /**
     * device secure element crypto object
     */
    private SecureElement se;

    /**
     * Get device unique id
     *
     * @return device unique id
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Set device unique id
     *
     * @param deviceId device unique id
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
     * Get secure element type
     *
     * @return secure element type
     */
    public SeType getSeType() {
        return seType;
    }

    /**
     * Set secure element type
     *
     * @param seType secure element type
     */
    public void setSeType(SeType seType) {
        this.seType = seType;
    }

    /**
     * Get device secure element crypto object
     *
     * @return device secure element crypto object
     */
    public SecureElement getSe() {
        return se;
    }

    /**
     * Set device secure element crypto object
     *
     * @param se device secure element crypto object
     */
    public void setSe(SecureElement se) {
        this.se = se;
    }

}
