package com.baidubce.services.scs.model.recycler;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.scs.model.ScsTag;

import java.util.Date;
import java.util.List;

public class ScsRecyclerInstanceListResponse extends AbstractBceResponse {
    private Boolean isTruncated;
    private String marker;
    private Integer maxKeys;
    private String nextMarker;
    private List<Result> result;

    public Boolean getIsTruncated() {
        return isTruncated;
    }

    public void setIsTruncated(Boolean truncated) {
        isTruncated = truncated;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public static class Result {
        private String instanceId;
        private String cacheClusterShowId;
        private String instanceName;
        private String instanceStatus;
        private String isolatedStatus;
        private String clusterType;
        private String nodeType;
        private String engine;
        private String engineVersion;
        private String vnetIp;
        private String domain;
        private String port;
        private Date instanceCreateTime;
        private float capacity;
        private Double usedCapacity;
        private String paymentTiming;
        private List<ScsTag> tags;
        private List<String> zoneNames;

        public String getInstanceId() {
            return instanceId;
        }

        public void setInstanceId(String instanceId) {
            this.instanceId = instanceId;
        }

        public String getCacheClusterShowId() {
            return cacheClusterShowId;
        }

        public void setCacheClusterShowId(String cacheClusterShowId) {
            this.cacheClusterShowId = cacheClusterShowId;
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

        public String getIsolatedStatus() {
            return isolatedStatus;
        }

        public void setIsolatedStatus(String isolatedStatus) {
            this.isolatedStatus = isolatedStatus;
        }

        public String getClusterType() {
            return clusterType;
        }

        public void setClusterType(String clusterType) {
            this.clusterType = clusterType;
        }

        public String getNodeType() {
            return nodeType;
        }

        public void setNodeType(String nodeType) {
            this.nodeType = nodeType;
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

        public float getCapacity() {
            return capacity;
        }

        public void setCapacity(float capacity) {
            this.capacity = capacity;
        }

        public Double getUsedCapacity() {
            return usedCapacity;
        }

        public void setUsedCapacity(Double usedCapacity) {
            this.usedCapacity = usedCapacity;
        }

        public String getPaymentTiming() {
            return paymentTiming;
        }

        public void setPaymentTiming(String paymentTiming) {
            this.paymentTiming = paymentTiming;
        }

        public List<ScsTag> getTags() {
            return tags;
        }

        public void setTags(List<ScsTag> tags) {
            this.tags = tags;
        }

        public List<String> getZoneNames() {
            return zoneNames;
        }

        public void setZoneNames(List<String> zoneNames) {
            this.zoneNames = zoneNames;
        }
    }
}
