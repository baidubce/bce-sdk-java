package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Request of Obtain the price difference for expansion in prepaid modev
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsGetPriceDifferenceRequest extends AbstractBceRequest {
    private String instanceId;
    private Integer cpuCount;
    private Integer allocatedMemoryInGB;
    private Integer allocatedStorageInGB;
    private String diskIoType;
    private String category;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Integer getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(Integer cpuCount) {
        this.cpuCount = cpuCount;
    }

    public Integer getAllocatedMemoryInGB() {
        return allocatedMemoryInGB;
    }

    public void setAllocatedMemoryInGB(Integer allocatedMemoryInGB) {
        this.allocatedMemoryInGB = allocatedMemoryInGB;
    }

    public Integer getAllocatedStorageInGB() {
        return allocatedStorageInGB;
    }

    public void setAllocatedStorageInGB(Integer allocatedStorageInGB) {
        this.allocatedStorageInGB = allocatedStorageInGB;
    }

    public String getDiskIoType() {
        return diskIoType;
    }

    public void setDiskIoType(String diskIoType) {
        this.diskIoType = diskIoType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
