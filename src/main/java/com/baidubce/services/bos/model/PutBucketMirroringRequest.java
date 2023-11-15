package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * PutBucketMirroringRequest class
 */
@JsonIgnoreProperties({"bucketName"})
public class PutBucketMirroringRequest extends GenericBucketRequest {
    /**
     * The BucketMirroringConfiguration
     */
    private List<BucketMirroringConfiguration> bucketMirroringConfiguration;

    /**
     * The json format for BucketMirroringConfiguration
     */
    private String jsonBucketMirroringConfiguration;

    /**
     * Constructs a void Constructor for PutBucketMirroringRequest
     */
    public PutBucketMirroringRequest() {

    }

    public PutBucketMirroringRequest(String bucketName,
                                     List<BucketMirroringConfiguration> bucketMirroringConfiguration) {
        super(bucketName);
        this.bucketMirroringConfiguration = bucketMirroringConfiguration;
    }

    public PutBucketMirroringRequest(String bucketName, String jsonBucketMirroringConfiguration) {
        super(bucketName);
        this.jsonBucketMirroringConfiguration = jsonBucketMirroringConfiguration;
    }


    @Override
    public PutBucketMirroringRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public PutBucketMirroringRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }


    public List<BucketMirroringConfiguration> getBucketMirroringConfiguration() {
        return bucketMirroringConfiguration;
    }

    public void setBucketMirroringConfiguration(List<BucketMirroringConfiguration> bucketMirroringConfiguration) {
        this.bucketMirroringConfiguration = bucketMirroringConfiguration;
    }

    public String getJsonBucketMirroringConfiguration() {
        return jsonBucketMirroringConfiguration;
    }

    public void setJsonBucketMirroringConfiguration(String jsonBucketMirroringConfiguration) {
        this.jsonBucketMirroringConfiguration = jsonBucketMirroringConfiguration;
    }
}
