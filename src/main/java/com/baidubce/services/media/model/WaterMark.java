/*
 * Copyright 2015-2020 Baidu, Inc.
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

package com.baidubce.services.media.model;

import lombok.Data;

/**
 * The model of watermark
 */
@Data
public class WaterMark {
    
    private String bucket = null;
    private String key = null;
    private Integer verticalOffsetInPixel = null;
    private Integer horizontalOffsetInPixel = null;
    private String watermarkId = null;
    private String createTime = null;
    private String verticalAlignment = null;
    private String horizontalAlignment = null;

    private Timeline timeline = null;
    private Integer repeated = null;
    private Boolean allowScaling = null;

    public WaterMark withBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    public WaterMark withKey(String key) {
        this.key = key;
        return this;
    }

    public WaterMark withVerticalOffsetInPixel(Integer verticalOffsetInPixel) {
        this.verticalOffsetInPixel = verticalOffsetInPixel;
        return this;
    }

    public WaterMark withHorizontalOffsetInPixel(Integer horizontalOffsetInPixel) {
        this.horizontalOffsetInPixel = horizontalOffsetInPixel;
        return this;
    }

    @Override
    public String toString() {
        return "WaterMark [bucket=" + bucket + ", key=" + key + ", verticalOffsetInPixel=" + verticalOffsetInPixel
                + ", horizontalOffsetInPixel=" + horizontalOffsetInPixel + ", watermarkId=" + watermarkId
                + ", createTime=" + createTime + ", horizontalAlignment= " + horizontalAlignment
                + ", verticalAlignment= " + verticalAlignment + ", timeline= " + timeline
                + ", repeated= " + repeated + ", allowScaling= " + allowScaling + "]";
    }
}
