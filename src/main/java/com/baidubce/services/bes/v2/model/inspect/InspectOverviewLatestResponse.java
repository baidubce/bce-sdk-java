package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Response for the latest inspection overview.
 */
@Data
public class InspectOverviewLatestResponse extends AbstractBceResponse {
    private String success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private String taskId;
        private String taskStatus;
        private Integer countSafe;
        private Integer countLowRisk;
        private Integer countHighRisk;
        private Integer countError;
        private String execTime;
    }
}
