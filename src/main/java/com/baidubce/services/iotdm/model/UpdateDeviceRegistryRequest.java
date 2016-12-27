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

/**
 * Represent the request for updating device registry.
 */
public class UpdateDeviceRegistryRequest extends AbstractBceRequest {

    private String description;
    private String parent;
    private DeviceOperation deviceOperation;
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getParent() {
        return parent;
    }
    public void setParent(String parent) {
        this.parent = parent;
    }
    public DeviceOperation getDeviceOperation() {
        return deviceOperation;
    }
    public void setDeviceOperation(DeviceOperation deviceOperation) {
        this.deviceOperation = deviceOperation;
    }

    public UpdateDeviceRegistryRequest withDescription(String description) {
        setDescription(description);
        return this;
    }
    public UpdateDeviceRegistryRequest withParent(String parent) {
        setParent(parent);
        return this;
    }
    public UpdateDeviceRegistryRequest withDeviceOperation(DeviceOperation deviceOperation) {
        setDeviceOperation(deviceOperation);
        return this;
    }

    @Override
    public UpdateDeviceRegistryRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
