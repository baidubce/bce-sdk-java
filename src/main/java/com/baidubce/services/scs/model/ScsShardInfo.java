package com.baidubce.services.scs.model;

/**
 * The shard info of scs instance
 */
public class ScsShardInfo {

    private String instanceId;
    private String shardId;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getShardId() {
        return shardId;
    }

    public void setShardId(String shardId) {
        this.shardId = shardId;
    }
}
