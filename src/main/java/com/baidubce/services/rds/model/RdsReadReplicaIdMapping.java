package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The mapping of topology read replica
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsReadReplicaIdMapping {

    private String appId;
    private String appIdShort;
    private String status;
    private Integer allocatedMemoryInMB;
    private Integer cpuCount;
    private Integer allocatedStorageInGB;
    private double usedStorageInGB;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppIdShort() {
        return appIdShort;
    }

    public void setAppIdShort(String appIdShort) {
        this.appIdShort = appIdShort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAllocatedMemoryInMB() {
        return allocatedMemoryInMB;
    }

    public void setAllocatedMemoryInMB(Integer allocatedMemoryInMB) {
        this.allocatedMemoryInMB = allocatedMemoryInMB;
    }

    public Integer getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(Integer cpuCount) {
        this.cpuCount = cpuCount;
    }

    public Integer getAllocatedStorageInGB() {
        return allocatedStorageInGB;
    }

    public void setAllocatedStorageInGB(Integer allocatedStorageInGB) {
        this.allocatedStorageInGB = allocatedStorageInGB;
    }

    public double getUsedStorageInGB() {
        return usedStorageInGB;
    }

    public void setUsedStorageInGB(double usedStorageInGB) {
        this.usedStorageInGB = usedStorageInGB;
    }
}
