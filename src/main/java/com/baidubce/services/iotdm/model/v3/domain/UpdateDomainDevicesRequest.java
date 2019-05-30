/*
 * Copyright 2017 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdm.model.v3.domain;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

public class UpdateDomainDevicesRequest extends AbstractBceRequest {
    /**
     * List of Devices to be added to Domain .
     */
    private List<String> addedDevices;
    /**
     * List of Devices to be removed from Domain .
     */
    private List<String> removedDevices;

    public List<String> getAddedDevices() {
        return addedDevices;
    }

    public void setAddedDevices(List<String> addedDevices) {
        this.addedDevices = addedDevices;
    }

    public List<String> getRemovedDevices() {
        return removedDevices;
    }

    public void setRemovedDevices(List<String> removedDevices) {
        this.removedDevices = removedDevices;
    }

    public UpdateDomainDevicesRequest withAddedDevices(List<String> addedDevices) {
        setAddedDevices(addedDevices);
        return this;
    }

    public UpdateDomainDevicesRequest withRemovedDevices(List<String> removedDevices) {
        setRemovedDevices(removedDevices);
        return this;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return UpdateDomainDevicesRequest with credentials.
     */
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        setRequestCredentials(credentials);
        return this;
    }
}
