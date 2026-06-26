package com.baidubce.services.scs.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Request to query scs price.
 */
public class ScsPriceRequest extends AbstractBceRequest {

    private String chargeType;
    private Integer period;
    private String timeUnit;
    private Integer engine;
    private String nodeType;
    private Integer shardNum;
    private Integer cacheInstanceType;
    private String clusterType;
    private Integer replicationNum;
    private Integer instanceNum;
    private Integer diskFlavor;
    private String diskType;
    private Integer storeType;

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public Integer getEngine() {
        return engine;
    }

    public void setEngine(Integer engine) {
        this.engine = engine;
    }

    public Integer getCacheInstanceType() {
        return cacheInstanceType;
    }

    public void setCacheInstanceType(Integer cacheInstanceType) {
        this.cacheInstanceType = cacheInstanceType;
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

    public String getClusterType() {
        return clusterType;
    }

    public void setClusterType(String clusterType) {
        this.clusterType = clusterType;
    }

    public Integer getReplicationNum() {
        return replicationNum;
    }

    public void setReplicationNum(Integer replicationNum) {
        this.replicationNum = replicationNum;
    }

    public Integer getInstanceNum() {
        return instanceNum;
    }

    public void setInstanceNum(Integer instanceNum) {
        this.instanceNum = instanceNum;
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

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
