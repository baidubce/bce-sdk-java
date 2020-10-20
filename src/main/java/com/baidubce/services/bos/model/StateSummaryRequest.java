package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;

/**
 * The request used to stat size, objects, files begin with the prefix
 */
public class StateSummaryRequest extends GenericBucketRequest {

    /**
     * Optional parameter restricting the response to keys which begin with the specified prefix.
     * default: null, stat the whole bucket
     * "pre" , stat the objects begin with "pre"
     */
    private String prefix;

    public StateSummaryRequest(String bucketName, String prefix) {
        super(bucketName);
        this.setPrefix(prefix);
    }

    public StateSummaryRequest(String bucketName) {
        this(bucketName, null);
    }

    public StateSummaryRequest() {

    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public StateSummaryRequest withPrefix(String prefix) {
        this.setPrefix(prefix);
        return this;
    }

    @Override
    public StateSummaryRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public StateSummaryRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
