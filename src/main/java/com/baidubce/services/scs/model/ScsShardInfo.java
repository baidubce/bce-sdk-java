package com.baidubce.services.scs.model;

/**
 * The shard info of scs instance
 */
public class ScsShardInfo {

    private String instanceId;
    private String flavorInGB;
    private String hashName;
    private String domain;
    private String createTime;
    private String shardId;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getFlavorInGB() {
        return flavorInGB;
    }

    public void setFlavorInGB(String flavorInGB) {
        this.flavorInGB = flavorInGB;
    }

    public String getHashName() {
        return hashName;
    }

    public void setHashName(String hashName) {
        this.hashName = hashName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getShardId() {
        return shardId;
    }

    public void setShardId(String shardId) {
        this.shardId = shardId;
    }
}
