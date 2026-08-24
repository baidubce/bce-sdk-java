package com.baidubce.services.bes.v2.model.instance;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * Response for scale-in instance candidates.
 */
@Data
public class ScaleInInstancesResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private List<InstanceInfo> instanceInfoList;
    }

    @Data
    public static class InstanceInfo {
        private String availableZone;
        private List<InstanceList> instanceList;
    }

    @Data
    public static class InstanceList {
        private String instanceId;
        private String hostIp;
        private Boolean isMaster;
        private Boolean isEmpty;
    }
}
