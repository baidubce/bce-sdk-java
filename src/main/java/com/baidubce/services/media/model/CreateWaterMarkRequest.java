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
    /**
     * Bos bucket
     **/
    private String bucket = null;

    /**
     * Bos key
     **/
    private String key = null;

    /**
     * vertical alignment, options include left, center, right
     **/
    private String verticalAlignment;

    /**
     * horizontal alignment, options include left, center, right
     **/
    private String horizontalAlignment;

    /**
     * vertical offset in pixel
     **/
    @Deprecated
    private Integer verticalOffsetInPixel;

    /**
     * horizontal offset in pixel
     **/
    @Deprecated
    private Integer horizontalOffsetInPixel;

    /**
     * display timeline setting
     **/
    private Timeline timeline = null;

    /**
     * display repeated times, 0 for infinite times
     **/
    private Integer repeated = null;

    /**
     * allow watermarks to scale automatically
     */
    private Boolean allowScaling = null;

    /**
     * horizontal offset in pixel or percent
     * example:
     * "100" means 100 pixel
     * "0.1" means 10%
     **/
    private String dx;

    /**
     * vertical offset in pixel or percent
     * example:
     * "100" means 100 pixel
     * "0.1" means 10%
     **/
    private String dy;

    /**
     * width of watermark in pixel or percent
     * example:
     * "100" means 100 pixel
     * "0.1" means 10%
     **/
    private String width;

    /**
     * height of watermark in pixel or percent
     * example:
     * "100" means 100 pixel
     * "0.1" means 10%
     **/
    private String height;

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

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public CreateWaterMarkRequest withTimeline(Timeline timeline) {
        this.timeline = timeline;
        return this;
    }

    public Integer getRepeated() {
        return repeated;
    }

    public void setRepeated(Integer repeated) {
        this.repeated = repeated;
    }

    public CreateWaterMarkRequest withRepeated(Integer repeated) {
        this.repeated = repeated;
        return this;
    }

    public Boolean getAllowScaling() {
        return allowScaling;
    }

    public void setAllowScaling(Boolean allowScaling) {
        this.allowScaling = allowScaling;
    }

    public CreateWaterMarkRequest withAllowScaling(Boolean allowScaling) {
        this.allowScaling = allowScaling;
        return this;
    }

    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    public CreateWaterMarkRequest withDx(String dx) {
        this.dx = dx;
        return this;
    }

    public String getDy() {
        return dy;
    }

    public void setDy(String dy) {
        this.dy = dy;
    }

    public CreateWaterMarkRequest withDy(String dy) {
        this.dy = dy;
        return this;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public CreateWaterMarkRequest withHeight(String height) {
        this.height = height;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public CreateWaterMarkRequest withWidth(String width) {
        this.width = width;
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
        sb.append("    dx: ").append(dx).append("\n");
        sb.append("    dy: ").append(dy).append("\n");
        sb.append("    width: ").append(width).append("\n");
        sb.append("    height: ").append(height).append("\n");
        sb.append("    timeline: ").append(timeline).append("\n");
        sb.append("    repeated: ").append(repeated).append("\n");
        sb.append("    allowScaling: ").append(allowScaling).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}
