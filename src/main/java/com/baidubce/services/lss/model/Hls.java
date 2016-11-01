/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.lss.model;

public class Hls {
    private Integer segmentTimeInSecond = null;

    private Integer segmentListSize = null;

    private Boolean adaptive = null;

    /**
     * hls分片的长度
     */
    public Integer getSegmentTimeInSecond() {
        return segmentTimeInSecond;
    }

    public void setSegmentTimeInSecond(Integer segmentTimeInSecond) {
        this.segmentTimeInSecond = segmentTimeInSecond;
    }

    public Hls withSegmentTimeInSecond(Integer segmentTimeInSecond) {
        this.segmentTimeInSecond = segmentTimeInSecond;
        return this;
    }

    /**
     * m3u8文件中切片的个数
     */
    public Integer getSegmentListSize() {
        return segmentListSize;
    }

    public void setSegmentListSize(Integer segmentListSize) {
        this.segmentListSize = segmentListSize;
    }

    public Hls withSegmentListSize(Integer segmentListSize) {
        this.segmentListSize = segmentListSize;
        return this;
    }

    /**
     * 是否需要自适应码率
     */
    public Boolean getAdaptive() {
        return adaptive;
    }

    public void setAdaptive(Boolean adaptive) {
        this.adaptive = adaptive;
    }

    public Hls withAdaptive(Boolean adaptive) {
        this.adaptive = adaptive;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class Hls {\n");
        sb.append("    segmentTimeInSecond: ").append(segmentTimeInSecond).append("\n");
        sb.append("    segmentListSize: ").append(segmentListSize).append("\n");
        sb.append("    adaptive: ").append(adaptive).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
