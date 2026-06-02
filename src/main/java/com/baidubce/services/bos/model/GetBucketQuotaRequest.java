package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"bucketName"})
public class GetBucketQuotaRequest extends GenericBucketRequest{

    public GetBucketQuotaRequest(String bucketName){
        super(bucketName);
    }

    @Override
    public GenericBucketRequest withBucketName(String bucketName) {
        return null;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        return null;
    }
}
