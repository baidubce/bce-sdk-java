package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * Response for cluster capacity assessment.
 */
@Data
public class ClusterAssessmentResponse extends AbstractBceResponse {
    private String success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private List<ZoneInfo> zoneList;
        private List<PackageStatus> packageStatusList;
        private OtherConfig otherConfig;
    }

    @Data
    public static class ZoneInfo {
        private String name;
        private String sellOut;
    }

    @Data
    public static class PackageStatus {
        private String moduleType;
        private String packageVersion;
        private String diskType;
        private String diskSize;
        private String moduleNum;
    }

    @Data
    public static class OtherConfig {
        private String bosDataSize;
    }
}
