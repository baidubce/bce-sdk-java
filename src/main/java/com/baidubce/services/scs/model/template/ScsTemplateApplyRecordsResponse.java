package com.baidubce.services.scs.model.template;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsTemplateApplyRecordsResponse extends AbstractBceResponse {
    private String marker;
    private Integer maxKeys;
    private String nextMarker;
    private Boolean isTruncated;
    private List<Result> result;

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

    public Boolean getIsTruncated() {
        return isTruncated;
    }

    public void setIsTruncated(Boolean truncated) {
        isTruncated = truncated;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public static class Result {
        private String cacheClusterShowId;
        private String cacheClusterName;
        private String availabilityZone;
        private Integer version;
        private String status;
        private String engine;
        private String engineVersion;
        private String clusterType;
        private String createTime;
        private String applyTime;

        public String getCacheClusterShowId() {
            return cacheClusterShowId;
        }

        public void setCacheClusterShowId(String cacheClusterShowId) {
            this.cacheClusterShowId = cacheClusterShowId;
        }

        public String getCacheClusterName() {
            return cacheClusterName;
        }

        public void setCacheClusterName(String cacheClusterName) {
            this.cacheClusterName = cacheClusterName;
        }

        public String getAvailabilityZone() {
            return availabilityZone;
        }

        public void setAvailabilityZone(String availabilityZone) {
            this.availabilityZone = availabilityZone;
        }

        public Integer getVersion() {
            return version;
        }

        public void setVersion(Integer version) {
            this.version = version;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getClusterType() {
            return clusterType;
        }

        public void setClusterType(String clusterType) {
            this.clusterType = clusterType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getApplyTime() {
            return applyTime;
        }

        public void setApplyTime(String applyTime) {
            this.applyTime = applyTime;
        }
    }
}
