package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/** Options for listing ObjectSets in a bucket. */
public class ListObjectSetRequest extends GenericBucketRequest {
    private String prefix;
    private String marker;
    private Integer maxKeys;

    public ListObjectSetRequest(String bucketName) {
        super(bucketName);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    @Override
    public ListObjectSetRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public ListObjectSetRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public ListObjectSetRequest withPrefix(String prefix) {
        this.setPrefix(prefix);
        return this;
    }

    public ListObjectSetRequest withMarker(String marker) {
        this.setMarker(marker);
        return this;
    }

    public ListObjectSetRequest withMaxKeys(Integer maxKeys) {
        this.setMaxKeys(maxKeys);
        return this;
    }
}
