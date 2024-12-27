package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Rds instance
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsInstance {

    private String instanceId;
    private String instanceName;
    private RdsEngine engine;
    private String engineVersion;
    private String characterSetName;
    private Integer volumeCapacity;
    private Double memoryCapacity;
    private Double usedStorage;
    private String instanceStatus;
    private Date instanceCreateTime;
    private Date instanceExpireTime;
    private String instanceType;
    private List<String> zoneNames = new ArrayList<String>();
    private String paymentTiming;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public RdsEngine getEngine() {
        return engine;
    }

    public void setEngine(RdsEngine engine) {
        this.engine = engine;
    }

    public String getEngineVersion() {
        return engineVersion;
    }

    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public Integer getVolumeCapacity() {
        return volumeCapacity;
    }

    public void setVolumeCapacity(Integer volumeCapacity) {
        this.volumeCapacity = volumeCapacity;
    }

    public Double getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(Double memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public Double getUsedStorage() {
        return usedStorage;
    }

    public void setUsedStorage(Double usedStorage) {
        this.usedStorage = usedStorage;
    }

    public String getInstanceStatus() {
        return instanceStatus;
    }

    public void setInstanceStatus(String instanceStatus) {
        this.instanceStatus = instanceStatus;
    }

    public Date getInstanceCreateTime() {
        return instanceCreateTime;
    }

    public void setInstanceCreateTime(Date instanceCreateTime) {
        this.instanceCreateTime = instanceCreateTime;
    }

    public Date getInstanceExpireTime() {
        return instanceExpireTime;
    }

    public void setInstanceExpireTime(Date instanceExpireTime) {
        this.instanceExpireTime = instanceExpireTime;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public List<String> getZoneNames() {
        return zoneNames;
    }

    public void setZoneNames(List<String> zoneNames) {
        this.zoneNames = zoneNames;
    }

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }
}
