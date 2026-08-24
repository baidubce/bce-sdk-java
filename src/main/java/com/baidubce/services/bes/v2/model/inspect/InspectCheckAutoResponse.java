package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Response for checking whether auto inspection is enabled.
 */
@Data
public class InspectCheckAutoResponse extends AbstractBceResponse {
    private String success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private Boolean switchOn;
    }
}
