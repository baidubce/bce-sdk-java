package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * PutBucketTrashRequest class
 */
@JsonIgnoreProperties({"bucketName"})
public class PutBucketTrashRequest extends GenericBucketRequest {

    /**
     * The BucketTrashName
     */
    @JsonAlias({"trashDir"})
    private String trashDir;

    public PutBucketTrashRequest(){};

    public PutBucketTrashRequest(String bucketName) {
        super(bucketName);
    }


    public PutBucketTrashRequest(String bucketName, String trashDir) {
        super(bucketName);
        this.trashDir = trashDir;
    }


    @Override
    public PutBucketTrashRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public PutBucketTrashRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getTrashDir() {
        return trashDir;
    }

    public void setTrashDir(String trashDir) {
        this.trashDir = trashDir;
    }
}

