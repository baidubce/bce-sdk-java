package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class DeleteBucketQuotaRequest extends GenericBucketRequest{
    public DeleteBucketQuotaRequest(String bucketName) {
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
