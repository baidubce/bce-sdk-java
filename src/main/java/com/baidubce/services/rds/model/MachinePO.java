package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MachinePO {
    private Integer id;
    private Date updatedTime;
    private String instanceId;
    private String machineInstanceId;
    private String machineInstanceName;
    private String machineInstanceStatus;
    private Boolean available;
    private String instanceRole;
    private String machineType;
    private String azone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getMachineInstanceId() {
        return machineInstanceId;
    }

    public void setMachineInstanceId(String machineInstanceId) {
        this.machineInstanceId = machineInstanceId;
    }

    public String getMachineInstanceName() {
        return machineInstanceName;
    }

    public void setMachineInstanceName(String machineInstanceName) {
        this.machineInstanceName = machineInstanceName;
    }

    public String getMachineInstanceStatus() {
        return machineInstanceStatus;
    }

    public void setMachineInstanceStatus(String machineInstanceStatus) {
        this.machineInstanceStatus = machineInstanceStatus;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getInstanceRole() {
        return instanceRole;
    }

    public void setInstanceRole(String instanceRole) {
        this.instanceRole = instanceRole;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getAzone() {
        return azone;
    }

    public void setAzone(String azone) {
        this.azone = azone;
    }
}
