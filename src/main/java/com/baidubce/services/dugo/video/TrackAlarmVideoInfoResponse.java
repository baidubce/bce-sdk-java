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

import java.sql.Timestamp;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * track alarm video info response
 *
 * @Author: shihuike
 * @Date: Created in 2019-08-01 17:12
 */
public class TrackAlarmVideoInfoResponse extends AbstractBceResponse {
    private String vehicleId;
    private String vehicleName;
    private List<TrackPointInfo> trackPointInfoList;
    private List<AlarmTrackInfo> alarmTrackInfoList;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() { return vehicleName; }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public List<TrackPointInfo> getTrackPointInfoList() {
        return trackPointInfoList;
    }

    public void setTrackPointInfoList(
            List<TrackPointInfo> trackPointInfoList) {
        this.trackPointInfoList = trackPointInfoList;
    }

    public List<AlarmTrackInfo> getAlarmTrackInfoList() {
        return alarmTrackInfoList;
    }

    public void setAlarmTrackInfoList(List<AlarmTrackInfo> alarmTrackInfoList) {
        this.alarmTrackInfoList = alarmTrackInfoList;
    }

    public static class TrackPointInfo {
        private double longitude;  // 报警经度为主
        private double latitude;   // 报警纬度为主
        private double speed;
        private Long locTime; // 以报警时间为主

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

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public Long getLocTime() {
            return locTime;
        }

        public void setLocTime(Long locTime) {
            this.locTime = locTime;
        }
    }
}
