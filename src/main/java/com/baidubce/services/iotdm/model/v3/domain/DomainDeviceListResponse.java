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

import com.baidubce.model.AbstractBceResponse;
import com.google.common.collect.Lists;

import java.util.List;

public class DomainDeviceListResponse extends AbstractBceResponse {
    /**
     * Device number in Domain .
     */
    private Integer amount;
    /**
     * Page number of Device in Domain .
     */
    private Integer pageNo;
    /**
     * Page size of Device in Domain .
     */
    private Integer pageSize;
    /**
     * List of Devices in Domain .
     */
    private List<DeviceInDomain> devices = Lists.newArrayList();


    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<DeviceInDomain> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceInDomain> devices) {
        this.devices = devices;
    }
}
