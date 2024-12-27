package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Request of Obtain new purchase price
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsSupportHotSwappingRequest extends AbstractBceRequest {

    private String instanceId;
    private Integer cpuCount;
    private Integer allocatedMemoryInMB;
    private Integer allocatedStorageInGB;
    private String masterAzone;
    private String backupAzone;
    private String subnetId;
    private String edgeSubnetId;


    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }

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

    public Integer getAllocatedMemoryInMB() {
        return allocatedMemoryInMB;
    }

    public void setAllocatedMemoryInMB(Integer allocatedMemoryInMB) {
        this.allocatedMemoryInMB = allocatedMemoryInMB;
    }

    public Integer getAllocatedStorageInGB() {
        return allocatedStorageInGB;
    }

    public void setAllocatedStorageInGB(Integer allocatedStorageInGB) {
        this.allocatedStorageInGB = allocatedStorageInGB;
    }

    public String getMasterAzone() {
        return masterAzone;
    }

    public void setMasterAzone(String masterAzone) {
        this.masterAzone = masterAzone;
    }

    public String getBackupAzone() {
        return backupAzone;
    }

    public void setBackupAzone(String backupAzone) {
        this.backupAzone = backupAzone;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getEdgeSubnetId() {
        return edgeSubnetId;
    }

    public void setEdgeSubnetId(String edgeSubnetId) {
        this.edgeSubnetId = edgeSubnetId;
    }
}
