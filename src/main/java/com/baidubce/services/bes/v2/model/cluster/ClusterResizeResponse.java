package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Response for resize cluster.
 */
@Data
public class ClusterResizeResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private String orderId;
    }
}
