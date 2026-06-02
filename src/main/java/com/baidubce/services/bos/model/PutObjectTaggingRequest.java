package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

public class PutObjectTaggingRequest extends GenericObjectRequest {

    private String tagKey;
    private String tagValue;

    private String cannedTag;

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

    public String getCannedTag() {
        return this.cannedTag;
    }

    public void setCannedTag(String cannedTag) {
        this.cannedTag = cannedTag;
    }

    public PutObjectTaggingRequest() {

    }

    public PutObjectTaggingRequest(String bucketName, String key, String tagKey, String tagValue) {
        super(bucketName, key);
        this.tagKey = tagKey;
        this.tagValue = tagValue;
    }

    public PutObjectTaggingRequest(String bucketName, String key, String cannedTag) {
        super(bucketName, key);
        this.cannedTag = cannedTag;
    }


    @Override
    public PutObjectTaggingRequest withKey(String key) {
        return null;
    }

    @Override
    public PutObjectTaggingRequest withBucketName(String bucketName) {
        return null;
    }

    @Override
    public PutObjectTaggingRequest withRequestCredentials(BceCredentials credentials) {
        return null;
    }
}
