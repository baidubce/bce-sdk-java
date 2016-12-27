/*
 * Copyright 2016 Baidu, Inc.
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
package com.baidubce.services.iotdm.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represent the request of updating device profile.
 */
public class UpdateDeviceProfileRequest extends AbstractBceRequest {

    private JsonNode attributes;
    private DeviceAttributes device;
    private DeviceOperation deviceOperation;

    public JsonNode getAttributes() {
        return attributes;
    }
    public void setAttributes(JsonNode attributes) {
        this.attributes = attributes;
    }
    public DeviceAttributes getDevice() {
        return device;
    }
    public void setDevice(DeviceAttributes device) {
        this.device = device;
    }
    public DeviceOperation getDeviceOperation() {
        return deviceOperation;
    }
    public void setDeviceOperation(DeviceOperation deviceOperation) {
        this.deviceOperation = deviceOperation;
    }

    public UpdateDeviceProfileRequest withAttributes(JsonNode attributes) {
        setAttributes(attributes);
        return this;
    }
    public UpdateDeviceProfileRequest withDevice(DeviceAttributes device) {
        setDevice(device);
        return this;
    }
    public UpdateDeviceProfileRequest withDeviceOperation(DeviceOperation deviceOperation) {
        setDeviceOperation(deviceOperation);
        return this;
    }

    @Override
    public UpdateDeviceProfileRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
