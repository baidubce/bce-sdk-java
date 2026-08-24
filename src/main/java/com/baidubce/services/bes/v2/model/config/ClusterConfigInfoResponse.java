package com.baidubce.services.bes.v2.model.config;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Response for cluster config info.
 */
@Data
public class ClusterConfigInfoResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private String esNodeExtraConfig;
        private String kibanaExtraConfig;
    }
}
