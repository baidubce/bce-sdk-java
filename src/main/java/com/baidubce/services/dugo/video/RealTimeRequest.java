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

import com.baidubce.services.dugo.AbstractDuGoRequest;
import com.baidubce.services.dugo.video.model.RealDataType;
import com.baidubce.services.dugo.video.model.RealStreamType;
import com.baidubce.services.dugo.video.model.VideoType;

/**
 * real time request
 *
 * @Author: shihuike
 * @Date: Created in 2019-08-01 16:07
 */
public class RealTimeRequest extends AbstractDuGoRequest {
    private String dataType = RealDataType.BOTH.name();    // 实时监控数据类型
    private String streamType = RealStreamType.MAIN_STREAM.name();  // 实时监控码流类型
    private String videoType = VideoType.FLV.name();   // 实时监控音视频格式

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getStreamType() {
        return streamType;
    }

    public void setStreamType(String streamType) {
        this.streamType = streamType;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }
}
