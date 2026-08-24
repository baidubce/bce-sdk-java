package com.baidubce.services.bes.v2.model.renew;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Response for renewing a cluster.
 */
@Data
public class ClusterRenewResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private String orderId;
    }
}
