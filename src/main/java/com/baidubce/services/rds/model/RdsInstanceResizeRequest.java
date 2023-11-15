package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The request of resize rds instance
 */
public class RdsInstanceResizeRequest extends AbstractBceRequest {

    private String instanceId;

    private Integer cpuCount;
    private Integer memoryCapacity;
    private Integer volumeCapacity;
    private Integer nodeAmount;
    private Boolean isDirectPay;

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

    public Integer getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(Integer memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public Integer getVolumeCapacity() {
        return volumeCapacity;
    }

    public void setVolumeCapacity(Integer volumeCapacity) {
        this.volumeCapacity = volumeCapacity;
    }

    public Integer getNodeAmount() {
        return nodeAmount;
    }

    public void setNodeAmount(Integer nodeAmount) {
        this.nodeAmount = nodeAmount;
    }

    public Boolean getIsDirectPay() {
        return isDirectPay;
    }

    public void setIsDirectPay(Boolean isDirectPay) {
        this.isDirectPay = isDirectPay;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
