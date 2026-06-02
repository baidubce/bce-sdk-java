package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsMajorVersionListResponse extends AbstractBceResponse {
    private List<MajorVersion> rdsMajorVersionList;

    public List<MajorVersion> getRdsMajorVersionList() {
        return rdsMajorVersionList;
    }

    public void setRdsMajorVersionList(List<MajorVersion> rdsMajorVersionList) {
        this.rdsMajorVersionList = rdsMajorVersionList;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MajorVersion {
        private String dbVersion;
        private String dbType;
        private String minorVersion;
        private String rdsMinorVersion;
        private String featureDescription;
        private String orderId;
        private String instanceVersion;
        private String runtimeVersion;

        public String getDbVersion() {
            return dbVersion;
        }

        public void setDbVersion(String dbVersion) {
            this.dbVersion = dbVersion;
        }

        public String getDbType() {
            return dbType;
        }

        public void setDbType(String dbType) {
            this.dbType = dbType;
        }

        public String getMinorVersion() {
            return minorVersion;
        }

        public void setMinorVersion(String minorVersion) {
            this.minorVersion = minorVersion;
        }

        public String getRdsMinorVersion() {
            return rdsMinorVersion;
        }

        public void setRdsMinorVersion(String rdsMinorVersion) {
            this.rdsMinorVersion = rdsMinorVersion;
        }

        public String getFeatureDescription() {
            return featureDescription;
        }

        public void setFeatureDescription(String featureDescription) {
            this.featureDescription = featureDescription;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getInstanceVersion() {
            return instanceVersion;
        }

        public void setInstanceVersion(String instanceVersion) {
            this.instanceVersion = instanceVersion;
        }

        public String getRuntimeVersion() {
            return runtimeVersion;
        }

        public void setRuntimeVersion(String runtimeVersion) {
            this.runtimeVersion = runtimeVersion;
        }
    }
}
