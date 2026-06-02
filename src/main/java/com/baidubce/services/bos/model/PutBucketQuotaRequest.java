package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"bucketName"})
public class PutBucketQuotaRequest extends GenericBucketRequest {
    @JsonProperty("maxObjectCount")
    private Integer maxObjectCount;
    @JsonProperty("maxCapacityMegaBytes")
    private Integer maxCapacityMegaBytes;


    public PutBucketQuotaRequest(String bucketName, Integer maxObjectCount, Integer maxCapacityMegaBytes) {
        super(bucketName);
        this.maxObjectCount = maxObjectCount;
        this.maxCapacityMegaBytes = maxCapacityMegaBytes;
    }

    public Integer getMaxObjectCount() {
        return maxObjectCount;
    }

    public void setMaxObjectCount(Integer maxObjectCount) {
        this.maxObjectCount = maxObjectCount;
    }

    public Integer getMaxCapacityMegaBytes() {
        return maxCapacityMegaBytes;
    }

    public void setMaxCapacityMegaBytes(Integer maxCapacityMegaBytes) {
        this.maxCapacityMegaBytes = maxCapacityMegaBytes;
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
