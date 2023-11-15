package com.baidubce.services.bos.model;

import java.util.List;

/**
 * GetBucketMirroringResponse class
 */
public class GetBucketMirroringResponse extends BosResponse{
    private List<BucketMirroringConfiguration> bucketMirroringConfiguration;

    @Override
    public String toString() {
        return "GetBucketMirroringResponse{" +
                "bucketMirroringConfiguration=" + bucketMirroringConfiguration +
                '}';
    }

    public List<BucketMirroringConfiguration> getBucketMirroringConfiguration() {
        return bucketMirroringConfiguration;
    }

    public void setBucketMirroringConfiguration(List<BucketMirroringConfiguration> bucketMirroringConfiguration) {
        this.bucketMirroringConfiguration = bucketMirroringConfiguration;
    }
}
