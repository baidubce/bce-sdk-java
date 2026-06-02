package com.baidubce.services.bos.model;

public class GetBucketQuotaResponse extends BosResponse{
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
    public String toString() {
        return "GetBucketQuotaResponse{" +
                "maxObjectCount=" + maxObjectCount +
                ", maxCapacityMegaBytes=" + maxCapacityMegaBytes +
                '}';
    }

    private Integer maxObjectCount;
    private Integer maxCapacityMegaBytes;

}
