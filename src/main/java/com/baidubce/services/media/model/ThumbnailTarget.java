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

/**
 * Thumbnail job target configuration.
 */
public class ThumbnailTarget {

    /**
     * The target key prefix of thumbnails.
     */
    private String keyPrefix = null;
 
    /**
     * The file format of the thumbnails, can be jpg, png, mp4, gif or webp.
     */   
    private String format = null;

    /**
     * The display frame rate of thumbnails, only support in gif, webp, mp4 format, 0.01 - 30.0.
     */
    private Double frameRate = null;

    /**
     * The quality of gif, only support in gif format, can be medium or high.
     */
    private String gifQuality = null;

    /**
     * The sizing Policy of thumbnail, can be keep, shrinkToFit or stretch.
     */ 
    private String sizingPolicy = null;
    
    /**
     * The expected with of thumbnail, 10 - 2000.
     */
    private Integer widthInPixel = null;
    
    /**
     * The expected height of thumbnail, 10 - 2000.
     */
    private Integer heightInPixel = null;

    /**
     * Thumbnail sprite output configuration.
     */
    private SpriteOutputCfg spriteOutputCfg = null;

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public ThumbnailTarget withKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public ThumbnailTarget withFormat(String format) {
        this.format = format;
        return this;
    }

    public Double getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(Double frameRate) {
        this.frameRate = frameRate;
    }

    public ThumbnailTarget withFrameRate(Double frameRate) {
        this.frameRate = frameRate;
        return this;
    }

    public String getGifQuality() {
        return gifQuality;
    }

    public void setGifQuality(String gifQuality) {
        this.gifQuality = gifQuality;
    }

    public ThumbnailTarget withGifQuality(String gifQuality) {
        this.gifQuality = gifQuality;
        return this;
    }

    public String getSizingPolicy() {
        return sizingPolicy;
    }

    public void setSizingPolicy(String sizingPolicy) {
        this.sizingPolicy = sizingPolicy;
    }

    public ThumbnailTarget withSizingPolicy(String sizingPolicy) {
        this.sizingPolicy = sizingPolicy;
        return this;
    }

    public Integer getWidthInPixel() {
        return widthInPixel;
    }

    public void setWidthInPixel(Integer widthInPixel) {
        this.widthInPixel = widthInPixel;
    }

    public ThumbnailTarget withWidthInPixel(Integer widthInPixel) {
        this.widthInPixel = widthInPixel;
        return this;
    }

    public Integer getHeightInPixel() {
        return heightInPixel;
    }

    public void setHeightInPixel(Integer heightInPixel) {
        this.heightInPixel = heightInPixel;
    }

    public ThumbnailTarget withHeightInPixel(Integer heightInPixel) {
        this.heightInPixel = heightInPixel;
        return this;
    }

    public SpriteOutputCfg getSpriteOutputCfg() {
        return spriteOutputCfg;
    }

    public SpriteOutputCfg setSpriteOutputCfg(SpriteOutputCfg spriteOutputCfg) {
        return this.spriteOutputCfg = spriteOutputCfg;
    }

    public ThumbnailTarget withSpriteOutputCfg(SpriteOutputCfg spriteOutputCfg) {
        this.spriteOutputCfg = spriteOutputCfg;
        return this;
    }

}
