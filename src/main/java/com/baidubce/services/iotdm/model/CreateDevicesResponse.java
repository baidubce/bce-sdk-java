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

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * Represent the response for creating devices.
 */
public class CreateDevicesResponse extends AbstractBceResponse {

    private int amount;
    private List<String> devices;
    private List<ThingDetail> things;

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setDevices(List<String> devices) {
        this.devices = devices;
    }

    public List<String> getDevices() {
        return devices;
    }

    public void setThings(List<ThingDetail> things) {
        this.things = things;
    }

    public List<ThingDetail> getThings() {
        return things;
    }

}
