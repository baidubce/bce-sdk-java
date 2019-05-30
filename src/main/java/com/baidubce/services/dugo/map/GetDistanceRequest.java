/*
 * Copyright 2018-2019 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.dugo.map;

import com.baidubce.services.dugo.AbstractDuGoRequest;

/**
 * get the distance request model
 * Created by liuzhenxing01 on 2018/10/22.
 */
public class GetDistanceRequest extends AbstractDuGoRequest {
    private String vehicleId;
    private Long startTime;
    private Long endTime;
    private Integer isProcessed;
    private String processOption;
    private String supplementMode;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(Integer isProcessed) {
        this.isProcessed = isProcessed;
    }

    public String getProcessOption() {
        return processOption;
    }

    public void setProcessOption(String processOption) {
        this.processOption = processOption;
    }

    public String getSupplementMode() {
        return supplementMode;
    }

    public void setSupplementMode(String supplementMode) {
        this.supplementMode = supplementMode;
    }
}
