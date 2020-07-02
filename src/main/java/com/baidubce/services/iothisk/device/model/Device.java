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

/**
 * Represent the device basic information, which is part of the contact made with hisk cloud service.
 */
public class Device {

    /**
     * device secure element type,
     * corresponding to the se type name contained in the created contract with hisk cloud service.
     */
    private SeType type;

    /**
     * device company name, 3-32 ascii characters, contained by English, Chinese, digital, underline('_') and hypen('-')
     * corresponding to the device company name contained in the created contract with hisk cloud service.
     */
    private String deviceCompany;

    /**
     * device type name, 3-48 ascii characters, contained by English, Chinese, digital, underline('_') and hypen('-')
     * corresponding to the device type name contained in the created contract with hisk cloud service.
     */
    private String deviceType;

    /**
     * device master key, hex string in upper case
     * corresponding to the master key contained in the created contract with hisk cloud service.
     */
    private String masterKey;

    /**
     * device sdk type
     */
    private DeviceSdkType deviceSdkType;

    /**
     * device serial number
     */
    private String serialNumber;

    /**
     * Get device secure element type
     *
     * @return device secure element type
     */
    public SeType getType() {
        return type;
    }

    /**
     * Set device secure element type
     *
     * @param type
     */
    public void setType(SeType type) {
        this.type = type;
    }

    /**
     * Get device company name
     *
     * @return device company name
     */
    public String getDeviceCompany() {
        return deviceCompany;
    }

    /**
     * Set device company name
     *
     * @param deviceCompany device company name
     */
    public void setDeviceCompany(String deviceCompany) {
        this.deviceCompany = deviceCompany;
    }

    /**
     * Get device type name
     *
     * @return device type name
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * Set device type name
     *
     * @param deviceType device type name
     */
    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * Get device master key
     *
     * @return device master key
     */
    public String getMasterKey() {
        return masterKey;
    }

    /**
     * Set device master key
     *
     * @param masterKey device master key
     */
    public void setMasterKey(String masterKey) {
        this.masterKey = masterKey;
    }

    /**
     * Get device sdk type
     *
     * @return device sdk type
     */
    public DeviceSdkType getDeviceSdkType() {
        return deviceSdkType;
    }

    /**
     * Set device sdk type
     *
     * @param deviceSdkType device sdk type
     */
    public void setDeviceSdkType(DeviceSdkType deviceSdkType) {
        this.deviceSdkType = deviceSdkType;
    }

    /**
     * Get device serial number
     *
     * @return device serial number
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Set device serial number
     *
     * @param serialNumber serial number
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

}
