package com.baidubce.services.scs.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.scs.model.instance.ScsReplication;

/**
 * The request of change config
 */
public class ScsChangeConfigRequest extends AbstractBceRequest {

    private String instanceId;
    private String clientToken;
    private String engineVersion;
    private String nodeType;
    private Integer shardNum;
    private Boolean isDefer;
    private Integer diskFlavor;
    private String diskType;
    private String modifyMethod;
    private java.util.List<ScsReplication> replicationInfo;
    private ScsBilling billing;

    public String getEngineVersion() {
        return engineVersion;
    }

    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public Integer getShardNum() {
        return shardNum;
    }

    public void setShardNum(Integer shardNum) {
        this.shardNum = shardNum;
    }

    public Boolean getIsDefer() {
        return isDefer;
    }

    public void setIsDefer(Boolean defer) {
        isDefer = defer;
    }

    public Integer getDiskFlavor() {
        return diskFlavor;
    }

    public void setDiskFlavor(Integer diskFlavor) {
        this.diskFlavor = diskFlavor;
    }

    public String getDiskType() {
        return diskType;
    }

    public void setDiskType(String diskType) {
        this.diskType = diskType;
    }

    public String getModifyMethod() {
        return modifyMethod;
    }

    public void setModifyMethod(String modifyMethod) {
        this.modifyMethod = modifyMethod;
    }

    public java.util.List<ScsReplication> getReplicationInfo() {
        return replicationInfo;
    }

    public void setReplicationInfo(java.util.List<ScsReplication> replicationInfo) {
        this.replicationInfo = replicationInfo;
    }

    public ScsBilling getBilling() {
        return billing;
    }

    public void setBilling(ScsBilling billing) {
        this.billing = billing;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
