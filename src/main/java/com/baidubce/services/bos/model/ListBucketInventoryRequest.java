package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class ListBucketInventoryRequest extends GenericBucketRequest{
    public ListBucketInventoryRequest(){
    }

    public ListBucketInventoryRequest(String bucketName) {
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
