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
 * Represent the request of removing devices.
 */
public class RemoveDevicesRequest extends AbstractBceRequest {

    private boolean cleanThing;
    private DeviceOperation deviceOperation;

    public void setCleanThing(boolean cleanThing) {
        this.cleanThing = cleanThing;
    }

    public boolean isCleanThing() {
        return cleanThing;
    }

    public RemoveDevicesRequest withCleanThing(boolean cleanThing) {
        setCleanThing(cleanThing);
        return this;
    }

    public DeviceOperation getDeviceOperation() {
        return deviceOperation;
    }

    public void setDeviceOperation(DeviceOperation deviceOperation) {
        this.deviceOperation = deviceOperation;
    }

    public RemoveDevicesRequest withDeviceOperation(DeviceOperation deviceOperation) {
        setDeviceOperation(deviceOperation);
        return this;
    }

    @Override
    public RemoveDevicesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
