package com.baidubce.services.scs.model.instance;

/**
 * The redis node of scs instance.
 */
public class ScsRedisNode {

    private String uuid;
    private String nodeShowId;
    private Integer cacheInstanceType;
    private Integer isReadOnly;
    private Integer inGroup;
    private String availabilityZone;
    private String subnetId;
    private Integer status;
    private Integer weight;
    private String hashName;
    private Integer shardId;
    private Integer nodeId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNodeShowId() {
        return nodeShowId;
    }

    public void setNodeShowId(String nodeShowId) {
        this.nodeShowId = nodeShowId;
    }

    public Integer getCacheInstanceType() {
        return cacheInstanceType;
    }

    public void setCacheInstanceType(Integer cacheInstanceType) {
        this.cacheInstanceType = cacheInstanceType;
    }

    public Integer getIsReadOnly() {
        return isReadOnly;
    }

    public void setIsReadOnly(Integer isReadOnly) {
        this.isReadOnly = isReadOnly;
    }

    public Integer getInGroup() {
        return inGroup;
    }

    public void setInGroup(Integer inGroup) {
        this.inGroup = inGroup;
    }

    public String getAvailabilityZone() {
        return availabilityZone;
    }

    public void setAvailabilityZone(String availabilityZone) {
        this.availabilityZone = availabilityZone;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getHashName() {
        return hashName;
    }

    public void setHashName(String hashName) {
        this.hashName = hashName;
    }

    public Integer getShardId() {
        return shardId;
    }

    public void setShardId(Integer shardId) {
        this.shardId = shardId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }
}
