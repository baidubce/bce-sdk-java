package com.baidubce.services.scs.model.instance;

/**
 * Shard item for master-slave switch.
 */
public class ScsSwitchMasterSlaveShard {

    private String hashName;
    private String nodeShowId;

    public String getHashName() {
        return hashName;
    }

    public void setHashName(String hashName) {
        this.hashName = hashName;
    }

    public String getNodeShowId() {
        return nodeShowId;
    }

    public void setNodeShowId(String nodeShowId) {
        this.nodeShowId = nodeShowId;
    }
}
