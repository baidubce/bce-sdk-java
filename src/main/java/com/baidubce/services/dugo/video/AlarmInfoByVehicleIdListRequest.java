/*
 * Copyright 2019 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.dugo.video;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baidubce.services.dugo.AbstractDuGoRequest;

/**
 * alarm info by vehicleId list request
 *
 * @Author: shihuike
 * @Date: Created in 2019-08-01 16:44
 */
public class AlarmInfoByVehicleIdListRequest extends AbstractDuGoRequest {
    private List<String> vehicleIdList;
    private Long startTime;
    private Long endTime;
    private List<String> alarmTypeList = new ArrayList<String>();

    public List<String> getVehicleIdList() {
        return vehicleIdList;
    }

    public void setVehicleIdList(List<String> vehicleIdList) {
        this.vehicleIdList = vehicleIdList;
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

    public List<String> getAlarmTypeList() {
        return alarmTypeList;
    }

    public void setAlarmTypeList(List<String> alarmTypeList) {
        this.alarmTypeList = alarmTypeList;
    }
}
