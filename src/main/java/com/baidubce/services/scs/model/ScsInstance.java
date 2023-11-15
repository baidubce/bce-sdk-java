package com.baidubce.services.scs.model;

/**
 * scs instance
 * {@link InstanceListResponse}
 */
public class ScsInstance {

    private String instanceId;
    private String instanceName;
    private String engine;
    private String engineVersion;
    private String characterSetName;
    private Integer volumeCapacity;
    private Double memoryCapacity;
    private Double usedStorage;
    private String instanceStatus;
    private String instanceCreateTime;
    private String instanceExpireTime;

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

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
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

    public String getInstanceCreateTime() {
        return instanceCreateTime;
    }

    public void setInstanceCreateTime(String instanceCreateTime) {
        this.instanceCreateTime = instanceCreateTime;
    }

    public String getInstanceExpireTime() {
        return instanceExpireTime;
    }

    public void setInstanceExpireTime(String instanceExpireTime) {
        this.instanceExpireTime = instanceExpireTime;
    }

    @Override
    public String toString() {
        return "ScsModel{" +
                "instanceId='" + instanceId + '\'' +
                ", instanceName='" + instanceName + '\'' +
                ", engine='" + engine + '\'' +
                ", engineVersion='" + engineVersion + '\'' +
                ", characterSetName='" + characterSetName + '\'' +
                ", volumeCapacity=" + volumeCapacity +
                ", memoryCapacity=" + memoryCapacity +
                ", usedStorage=" + usedStorage +
                ", instanceStatus='" + instanceStatus + '\'' +
                ", instanceCreateTime='" + instanceCreateTime + '\'' +
                ", instanceExpireTime='" + instanceExpireTime + '\'' +
                '}';
    }
}