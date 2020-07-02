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

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * alarm info list response
 *
 * @Author: shihuike
 * @Date: Created in 2019-08-01 16:57
 */
public class AlarmInfoListResponse extends AbstractBceResponse {
    private List<AlarmInfo> alarmInfoList;

    public List<AlarmInfo> getAlarmInfoList() {
        return alarmInfoList;
    }

    public void setAlarmInfoList(List<AlarmInfo> alarmInfoList) {
        this.alarmInfoList = alarmInfoList;
    }

    public static class AlarmInfo {
        private Long alarmTime;
        private String alarmType;
        private String vehicleId;
        private String vehicleName;
        private String groupName;
        private double longitude;
        private double latitude;
        private String alarmRefKey;

        public Long getAlarmTime() { return alarmTime; }

        private void setAlarmTime(Long alarmTime) { this.alarmTime = alarmTime; }

        public String getAlarmType() {
            return alarmType;
        }

        public void setAlarmType(String alarmType) {
            this.alarmType = alarmType;
        }

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getVehicleName() {
            return vehicleName;
        }

        public void setVehicleName(String vehicleName) {
            this.vehicleName = vehicleName;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public String getAlarmRefKey() {
            return alarmRefKey;
        }

        public void setAlarmRefKey(String alarmRefKey) {
            this.alarmRefKey = alarmRefKey;
        }
    }
}
