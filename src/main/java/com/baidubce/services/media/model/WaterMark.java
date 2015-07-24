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

package com.baidubce.services.media.model;

public class WaterMark {
    
    private String bucket = null;
    private String key = null;
    private Integer verticalOffsetInPixel = null;
    private Integer horizontalOffsetInPixel = null;
    private String watermarkId = null;
    private String createTime = null;
    private String verticalAlignment = null;
    private String horizontalAlignment = null;

    public String getBucket() {
        return bucket;
    }
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
    public WaterMark withBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public WaterMark withKey(String key) {
        this.key = key;
        return this;
    }

    public Integer getVerticalOffsetInPixel() {
        return verticalOffsetInPixel;
    }
    public void setVerticalOffsetInPixel(Integer verticalOffsetInPixel) {
        this.verticalOffsetInPixel = verticalOffsetInPixel;
    }
    public WaterMark withVerticalOffsetInPixel(Integer verticalOffsetInPixel) {
        this.verticalOffsetInPixel = verticalOffsetInPixel;
        return this;
    }
    
    public Integer getHorizontalOffsetInPixel() {
        return horizontalOffsetInPixel;
    }
    public void setHorizontalOffsetInPixel(Integer horizontalOffsetInPixel) {
        this.horizontalOffsetInPixel = horizontalOffsetInPixel;
    }
    public WaterMark withHorizontalOffsetInPixel(Integer horizontalOffsetInPixel) {
        this.horizontalOffsetInPixel = horizontalOffsetInPixel;
        return this;
    }
    
    public String getWatermarkId() {
        return watermarkId;
    }
    public void setWatermarkId(String watermarkId) {
        this.watermarkId = watermarkId;
    }
    public WaterMark withWatermarkId(String watermarkId) {
        this.watermarkId = watermarkId;
        return this;
    }
    
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public WaterMark withCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }
    
    public String getVerticalAlignment() {
        return verticalAlignment;
    }
    public void setVerticalAlignment(String verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }
    public String getHorizontalAlignment() {
        return horizontalAlignment;
    }
    public void setHorizontalAlignment(String horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }
    
    @Override
    public String toString() {
        return "WaterMark [bucket=" + bucket + ", key=" + key + ", verticalOffsetInPixel=" + verticalOffsetInPixel
                + ", horizontalOffsetInPixel=" + horizontalOffsetInPixel + ", watermarkId=" + watermarkId
                + ", createTime=" + createTime + ", horizontalAlignment= " + horizontalAlignment
                + ", verticalAlignment= " + verticalAlignment + "]";
    }
}
