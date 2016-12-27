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

import java.util.ArrayList;
import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Represent the request for creating devices.
 */
public class CreateDevicesRequest extends AbstractBceRequest {

    private int amount;
    private List<String> devices = new ArrayList<String>();
    private String parent;
    private String description;
    private boolean bootstrap;

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public CreateDevicesRequest withAmount(int amount) {
        setAmount(amount);
        return this;
    }

    public void setDevices(List<String> devices) {
        this.devices = devices;
    }

    public List<String> getDevices() {
        return devices;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getParent() {
        return parent;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setBootstrap(boolean bootstrap) {
        this.bootstrap = bootstrap;
    }

    public boolean isBootstrap() {
        return bootstrap;
    }

    public CreateDevicesRequest withBootstrap(boolean bootstrap) {
        setBootstrap(bootstrap);
        return this;
    }

    @Override
    public CreateDevicesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
