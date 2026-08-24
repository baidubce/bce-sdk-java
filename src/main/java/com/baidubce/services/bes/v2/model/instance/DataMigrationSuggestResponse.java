package com.baidubce.services.bes.v2.model.instance;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * Response for suggested data migration instances.
 */
@Data
public class DataMigrationSuggestResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private List<SuggestInfo> suggestList;
    }

    @Data
    public static class SuggestInfo {
        private String availableZone;
        private List<Instance> instanceList;
    }

    @Data
    public static class Instance {
        private String instanceId;
        private String hostIp;
    }
}
