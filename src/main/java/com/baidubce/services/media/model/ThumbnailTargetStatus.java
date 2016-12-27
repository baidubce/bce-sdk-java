package com.baidubce.services.media.model;

import java.util.ArrayList;
import java.util.List;

public class ThumbnailTargetStatus {
    private String keyPrefix = null;
    
    private String format = null;
    
    private String sizingPolicy = null;
    
    private Integer widthInPixel = null;
    
    private Integer heightInPixel = null;

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public ThumbnailTargetStatus withKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public ThumbnailTargetStatus withFormat(String format) {
        this.format = format;
        return this;
    }

    public String getSizingPolicy() {
        return sizingPolicy;
    }

    public void setSizingPolicy(String sizingPolicy) {
        this.sizingPolicy = sizingPolicy;
    }

    public ThumbnailTargetStatus withSizingPolicy(String sizingPolicy) {
        this.sizingPolicy = sizingPolicy;
        return this;
    }

    public Integer getWidthInPixel() {
        return widthInPixel;
    }

    public void setWidthInPixel(Integer widthInPixel) {
        this.widthInPixel = widthInPixel;
    }

    public ThumbnailTargetStatus withWidthInPixel(Integer widthInPixel) {
        this.widthInPixel = widthInPixel;
        return this;
    }

    public Integer getHeightInPixel() {
        return heightInPixel;
    }

    public void setHeightInPixel(Integer heightInPixel) {
        this.heightInPixel = heightInPixel;
    }

    public ThumbnailTargetStatus withHeightInPixel(Integer heightInPixel) {
        this.heightInPixel = heightInPixel;
        return this;
    }

    private List<String> keys = new ArrayList<String>();

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }
    
    public ThumbnailTargetStatus withKeys(List<String> keys) {
        this.keys = keys;
        return this;
    }

    @Override
    public String toString() {
        return "ThumbnailTargetStatus [keyPrefix=" + keyPrefix + ", format=" + format + ", sizingPolicy="
                + sizingPolicy + ", widthInPixel=" + widthInPixel + ", heightInPixel=" + heightInPixel + ", keys="
                + keys + "]";
    }

}
