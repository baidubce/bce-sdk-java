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
 * alarm track info
 *
 * @Author: shihuike
 * @Date: Created in 2019-08-01 17:02
 */
public class AlarmTrackInfo extends AbstractBceResponse {
    private double longitude;  // 报警经度为主
    private double latitude;   // 报警纬度为主
    private double speed;
    private Long alarmTime; // 以报警时间为主
    private String alarmUuid;
    private String alarmType;
    private List<TrackMediaInfo> trackMediaInfoList;

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

    public Long getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Long alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmUuid() {
        return alarmUuid;
    }

    public void setAlarmUuid(String alarmUuid) {
        this.alarmUuid = alarmUuid;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public List<TrackMediaInfo> getTrackMediaInfoList() {
        return trackMediaInfoList;
    }

    public void setTrackMediaInfoList(
            List<TrackMediaInfo> trackMediaInfoList) {
        this.trackMediaInfoList = trackMediaInfoList;
    }

    public static class TrackMediaInfo {
        private String mediaUuid;
        private String mediaType;

        public String getMediaUuid() {
            return mediaUuid;
        }

        public void setMediaUuid(String mediaUuid) {
            this.mediaUuid = mediaUuid;
        }

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }
    }
}
