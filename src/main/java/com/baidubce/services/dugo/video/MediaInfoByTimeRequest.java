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
import com.baidubce.services.dugo.video.model.StreamType;

/**
 * media info by time request
 *
 * @Author: shihuike
 * @Date: Created in 2019-08-01 16:53
 */
public class MediaInfoByTimeRequest extends AbstractDuGoRequest {
    private Long startTime;  // 开始时间戳
    private Long endTime;     // 结束时间戳
    private List<Integer> statusList = new ArrayList<Integer>();   // 存储位置
    private List<String> alarmTypeList = new ArrayList<String>(); // 报警类型（默认全不选）
    private List<Integer> channelList = new ArrayList<Integer>(); // 通道号（默认全不选）
    private String streamType = StreamType.ALL.name(); // 码流（默认全选）

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

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public List<String> getAlarmTypeList() {
        return alarmTypeList;
    }

    public void setAlarmTypeList(List<String> alarmTypeList) {
        this.alarmTypeList = alarmTypeList;
    }

    public List<Integer> getChannelList() {
        return channelList;
    }

    public void setChannelList(List<Integer> channelList) {
        this.channelList = channelList;
    }

    public String getStreamType() {
        return streamType;
    }

    public void setStreamType(String streamType) {
        this.streamType = streamType;
    }
}
