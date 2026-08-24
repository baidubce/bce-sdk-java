package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Response for the count of manual inspections completed today.
 */
@Data
public class InspectManualCountResponse extends AbstractBceResponse {
    private String success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private Integer count;
    }
}
