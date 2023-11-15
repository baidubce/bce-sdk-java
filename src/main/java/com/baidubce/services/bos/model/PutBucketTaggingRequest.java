package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * PutBucketTaggingRequest class
 */
@JsonIgnoreProperties({"bucketName"})
public class PutBucketTaggingRequest extends GenericBucketRequest {
    /**
     * The BucketTag object
     */
    private List<BucketTag> tags;

    /**
     * The json format for BucketTag
     */
    private String jsonBucketTags;

    /**
     * Constructs a void Constructor for PutBucketTaggingRequest
     */
    public PutBucketTaggingRequest() {

    }

    public PutBucketTaggingRequest(String bucketName, List<BucketTag> tags) {
        super(bucketName);
        this.tags = tags;
    }

    public PutBucketTaggingRequest(String bucketName, String jsonBucketTags) {
        super(bucketName);
        this.jsonBucketTags = jsonBucketTags;
    }


    @Override
    public GenericBucketRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public List<BucketTag> getTags() {
        return tags;
    }

    public void setTags(List<BucketTag> tags) {
        this.tags = tags;
    }

    public String getJsonBucketTags() {
        return jsonBucketTags;
    }

    public void setJsonBucketTags(String jsonBucketTags) {
        this.jsonBucketTags = jsonBucketTags;
    }
}
