package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * Response for listing clusters.
 */
@Data
public class ListClustersResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Page page;

    @Data
    public static class Page {
        private Integer pageNo;
        private Integer pageSize;
        private Integer totalCount;
        private List<ClusterListItem> result;
    }

    @Data
    public static class ClusterListItem {
        private String clusterId;
        private String clusterName;
        private String createTime;
        private String actualStatus;
        private String runningTime;
        private String region;
        private String version;
        private ClusterHealth clusterHealth;
        private Billing billing;
        private List<Tag> tags;
        private List<ResGroup> resGroupList;
        private String innerAccessIp;
        private String accessEip;
        private String vpcId;
        private List<Subnet> subnet;
        private List<Network> network;
    }

    @Data
    public static class ClusterHealth {
        private String status;
    }

    @Data
    public static class Billing {
        private String paymentType;
    }

    @Data
    public static class ResGroup {
        private String groupId;
        private String groupName;
    }

    @Data
    public static class Subnet {
        private String availableZone;
        private String subnetName;
    }

    @Data
    public static class Network {
        private String subnetId;
    }
}
