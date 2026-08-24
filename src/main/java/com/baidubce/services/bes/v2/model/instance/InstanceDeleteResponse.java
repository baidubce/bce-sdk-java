package com.baidubce.services.bes.v2.model.instance;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Response for delete instances.
 */
@Data
public class InstanceDeleteResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private String orderId;
    }
}
