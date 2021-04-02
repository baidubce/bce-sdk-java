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

import com.baidubce.model.AbstractBceResponse;

public class GetWaterMarkResponse extends AbstractBceResponse {
    /**
     * the auto-generated watermark Id
     **/
    private String watermarkId = null;

    /**
     * the create time of the watermark
     **/
    private String createTime = null;

    /**
     * Bos bucket
     **/
    private String bucket = null;

    /**
     * Bos key
     **/
    private String key = null;

    /**
     * vertical offset in pixel
     **/
    private Integer verticalOffsetInPixel = null;


    /**
     * horizontal offset in pixel
     **/
    private Integer horizontalOffsetInPixel = null;

    /**
     * vertical alignment, options include left, center, right
     **/
    private String verticalAlignment = null;

    /**
     * horizontal alignment, options include left, center, right
     **/
    private String horizontalAlignment = null;

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

    public String getWatermarkId() {
        return watermarkId;
    }
    
    public void setWatermarkId(String watermarkId) {
        this.watermarkId = watermarkId;
    }

    public String getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getVerticalOffsetInPixel() {
        return verticalOffsetInPixel;
    }

    public void setVerticalOffsetInPixel(Integer verticalOffsetInPixel) {
        this.verticalOffsetInPixel = verticalOffsetInPixel;
    }

    public Integer getHorizontalOffsetInPixel() {
        return horizontalOffsetInPixel;
    }

    public void setHorizontalOffsetInPixel(Integer horizontalOffsetInPixel) {
        this.horizontalOffsetInPixel = horizontalOffsetInPixel;
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

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public Integer getRepeated() {
        return repeated;
    }

    public void setRepeated(Integer repeated) {
        this.repeated = repeated;
    }

    public Boolean getAllowScaling() {
        return allowScaling;
    }

    public void setAllowScaling(Boolean allowScaling) {
        this.allowScaling = allowScaling;
    }

    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    public GetWaterMarkResponse withDx(String dx) {
        this.dx = dx;
        return this;
    }

    public String getDy() {
        return dy;
    }

    public void setDy(String dy) {
        this.dy = dy;
    }

    public GetWaterMarkResponse withDy(String dy) {
        this.dy = dy;
        return this;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public GetWaterMarkResponse withHeight(String height) {
        this.height = height;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public GetWaterMarkResponse withWidth(String width) {
        this.width = width;
        return this;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetWaterMarkResponse {\n");
        sb.append("    watermarkId: ").append(watermarkId).append("\n");
        sb.append("    createTime: ").append(createTime).append("\n");
        sb.append("    bucket: ").append(bucket).append("\n");
        sb.append("    key: ").append(key).append("\n");
        sb.append("    horizontalAlignment: ").append(horizontalAlignment).append("\n");
        sb.append("    verticalAlignment: ").append(verticalAlignment).append("\n");
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
