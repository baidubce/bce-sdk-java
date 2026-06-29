package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Tag bound to a MongoDB instance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbTag {
    private String tagKey;

    private String tagValue;

    public MongodbTag() {
    }

    public MongodbTag(String tagKey, String tagValue) {
        this.tagKey = tagKey;
        this.tagValue = tagValue;
    }

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
