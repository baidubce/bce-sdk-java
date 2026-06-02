package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"bucketName"})
public class GetBucketInventoryRequest extends GenericBucketRequest {

    private String id;

    public GetBucketInventoryRequest(String bucketName, String id) {
        super(bucketName);
        this.id = id;
    }

    @Override
    public GenericBucketRequest withBucketName(String bucketName) {
        return null;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
