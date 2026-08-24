package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Response for cluster detail.
 */
@Data
public class ClusterDetailResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private String expireTime;
        private String clusterId;
        private String clusterName;
        private String adminUsername;
        private String actualStatus;
        private String desireStatus;
        private String esUrl;
        private String kibanaUrl;
        private String exporterUrl;
        private Boolean enableEsSSL;
        private Boolean enableKibanaSSL;
        private String esEip;
        private String kibanaEip;
        private List<ModuleInfoItem> modules;
        private List<InstanceInfo> instances;
        private String region;
        private String inspectAuthorized;
        private String vpc;
        private String vpcId;
        private String subnet;
        private String availableZone;
        private String securityGroup;
        private ClusterHealth clusterHealth;
        private Billing billing;
        private List<NetworkInfo> network;
        private List<Tag> tags;
        private Log log;
        private List<ResGroup> resGroupList;
        private String bdVersion;
    }

    @Data
    public static class ModuleInfoItem {
        private String type;
        private String version;
        private String slotType;
        private String slotDescription;
        private Integer actualInstanceNum;
    }

    @Data
    public static class InstanceInfo {
        private String instanceId;
        private String status;
        private String moduleType;
        private String moduleVersion;
        private String hostIp;
    }

    @Data
    public static class ClusterHealth {
        private String status;
        private String totalDiskSize;
        private String usedDiskSize;
        private NodeDiskMaxSizeInGB nodeDiskMaxSizeInGB;
        private String indexDiskSize;
    }

    @Data
    public static class NodeDiskMaxSizeInGB {
        @JsonProperty("es_node")
        private String esNode;
        @JsonProperty("es_cold_tier_node")
        private String esColdTierNode;
    }

    @Data
    public static class Billing {
        private String paymentType;
    }

    @Data
    public static class NetworkInfo {
        private String subnetId;
        private String subnet;
        private String availableZone;
    }

    @Data
    public static class Log {
        private Boolean mainLog;
        private Boolean gcLog;
        private Boolean indexSlowLog;
        private Boolean searchSlowLog;
        private Boolean auditLog;
    }

    @Data
    public static class ResGroup {
        private String groupId;
        private String groupName;
    }
}
