/*
 * Copyright 2015 Baidu, Inc.
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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class CreateWaterMarkRequest extends AbstractBceRequest {
    private String bucket = null;
    private String key = null;
    private String verticalAlignment;
    private String horizontalAlignment;
    private Integer verticalOffsetInPixel;
    private Integer horizontalOffsetInPixel;

    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
    
    public CreateWaterMarkRequest withBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public CreateWaterMarkRequest withKey(String key) {
        this.key = key;
        return this;
    }

    public Integer getVerticalOffsetInPixel() {
        return verticalOffsetInPixel;
    }

    public void setVerticalOffsetInPixel(Integer verticalOffsetInPixel) {
        this.verticalOffsetInPixel = verticalOffsetInPixel;
    }

    public CreateWaterMarkRequest withVerticalOffsetInPixel(Integer verticalOffsetInPixel) {
        this.verticalOffsetInPixel = verticalOffsetInPixel;
        return this;
    }

    public Integer getHorizontalOffsetInPixel() {
        return horizontalOffsetInPixel;
    }

    public void setHorizontalOffsetInPixel(Integer horizontalOffsetInPixel) {
        this.horizontalOffsetInPixel = horizontalOffsetInPixel;
    }

    public CreateWaterMarkRequest withHorizontalOffsetInPixel(Integer horizontalOffsetInPixel) {
        this.horizontalOffsetInPixel = horizontalOffsetInPixel;
        return this;
    }

    public String getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(String verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    public CreateWaterMarkRequest withVerticalAlignment(String verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
        return this;
    }

    public String getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(String horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public CreateWaterMarkRequest withHorizontalAlignment(String horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateWaterMarkRequest {\n");
        sb.append("    bucket: ").append(bucket).append("\n");
        sb.append("    key: ").append(key).append("\n");
        sb.append("    verticalAlignment: ").append(verticalAlignment).append("\n");
        sb.append("    horizontalAlignment: ").append(horizontalAlignment).append("\n");
        sb.append("    verticalOffsetInPixel: ").append(verticalOffsetInPixel).append("\n");
        sb.append("    horizontalOffsetInPixel: ").append(horizontalOffsetInPixel).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
