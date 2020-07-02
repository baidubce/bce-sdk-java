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
 * get media info list response
 *
 * @Author: shihuike
 * @Date: Created in 2019-08-01 17:08
 */
public class GetMediaInfoListResponse extends AbstractBceResponse {
    private String vehicleId;  // 车辆id
    private String vehicleName; // 车辆名称
    private String groupName;  // 设备组名称
    private List<MediaInfo> videoInfoList;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() { return vehicleName; }

    public void setVehicleName(String vehicleName) { this.vehicleName = vehicleName; }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<MediaInfo> getVideoInfoList() {
        return videoInfoList;
    }

    public void setVideoInfoList(
            List<MediaInfo> videoInfoList) {
        this.videoInfoList = videoInfoList;
    }

    public static class MediaInfo {
        private String mediaUuid;  // 视频id
        private int channelNum;   // 通道号
        private Long startTime;   // 视频开始时间
        private Long endTime;   // 视频结束时间
        private String fileName;  // 视频文件名
        private int fileSize;   // 文件大小
        private String alarmType;  // 报警类型
        private String status;   // 存储位置
        private String mediaType;  // 视频类型
        private String streamType;  // 码流
        private long duration; // 时长

        public String getMediaUuid() {
            return mediaUuid;
        }

        public void setMediaUuid(String mediaUuid) {
            this.mediaUuid = mediaUuid;
        }

        public int getChannelNum() {
            return channelNum;
        }

        public void setChannelNum(int channelNum) {
            this.channelNum = channelNum;
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

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public int getFileSize() {
            return fileSize;
        }

        public void setFileSize(int fileSize) {
            this.fileSize = fileSize;
        }

        public String getAlarmType() {
            return alarmType;
        }

        public void setAlarmType(String alarmType) {
            this.alarmType = alarmType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        public String getStreamType() {
            return streamType;
        }

        public void setStreamType(String streamType) {
            this.streamType = streamType;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }
    }
}
