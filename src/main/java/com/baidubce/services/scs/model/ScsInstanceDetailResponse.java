package com.baidubce.services.scs.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Scs instance detail info
 * {@link com.baidubce.services.scs.ScsClient#getInstanceDetail(ScsInstanceDetailRequest)}
 */
public class ScsInstanceDetailResponse extends AbstractBceResponse {

    private String instanceId;
    private String instanceName;
    private String instanceStatus;
    private String clusterType;
    private String engine;
    private String engineVersion;
    private String nodeType;
    private Integer storeType;
    private Integer shardNum;
    private String vnetIp;
    private String domain;
    private String port;
    private Date instanceCreateTime;
    private Date instanceExpireTime;
    private Integer capacity;
    private Integer usedCapacity;
    private String paymentTiming;
    private List<String> zoneNames = new ArrayList<String>();
    private String vpcId;
    private List<ScsSubnet> subnets = new ArrayList<ScsSubnet>();
    private Boolean autoRenew;
    private Integer replicationNum;
    private Boolean enableSlowLog;
    private String unitPrice;
    private List<ScsProxyNode> proxyList = new ArrayList<ScsProxyNode>();
    @JsonProperty("cacheClusterInstances")
    private List<ScsShardInfo> shardInfos = new ArrayList<ScsShardInfo>();

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

    public String getInstanceStatus() {
        return instanceStatus;
    }

    public void setInstanceStatus(String instanceStatus) {
        this.instanceStatus = instanceStatus;
    }

    public String getClusterType() {
        return clusterType;
    }

    public void setClusterType(String clusterType) {
        this.clusterType = clusterType;
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

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public Integer getShardNum() {
        return shardNum;
    }

    public void setShardNum(Integer shardNum) {
        this.shardNum = shardNum;
    }

    public String getVnetIp() {
        return vnetIp;
    }

    public void setVnetIp(String vnetIp) {
        this.vnetIp = vnetIp;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getUsedCapacity() {
        return usedCapacity;
    }

    public void setUsedCapacity(Integer usedCapacity) {
        this.usedCapacity = usedCapacity;
    }

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public List<String> getZoneNames() {
        return zoneNames;
    }

    public void setZoneNames(List<String> zoneNames) {
        this.zoneNames = zoneNames;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public List<ScsSubnet> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<ScsSubnet> subnets) {
        this.subnets = subnets;
    }

    public Boolean getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(Boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public Integer getReplicationNum() {
        return replicationNum;
    }

    public void setReplicationNum(Integer replicationNum) {
        this.replicationNum = replicationNum;
    }

    public Boolean getEnableSlowLog() {
        return enableSlowLog;
    }

    public void setEnableSlowLog(Boolean enableSlowLog) {
        this.enableSlowLog = enableSlowLog;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<ScsProxyNode> getProxyList() {
        return proxyList;
    }

    public void setProxyList(List<ScsProxyNode> proxyList) {
        this.proxyList = proxyList;
    }

    public List<ScsShardInfo> getShardInfos() {
        return shardInfos;
    }

    public void setShardInfos(List<ScsShardInfo> shardInfos) {
        this.shardInfos = shardInfos;
    }
}
