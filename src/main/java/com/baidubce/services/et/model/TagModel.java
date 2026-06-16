package com.baidubce.services.et.model;

import lombok.ToString;

@ToString
public class TagModel {
    private String tagKey;
    private String tagValue;

    public String getTagKey() {
        return tagKey;
    }

    public void setTagKey(String tagKey) {
        this.tagKey = tagKey;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }
}
