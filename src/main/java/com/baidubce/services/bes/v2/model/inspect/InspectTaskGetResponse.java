package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * Response for the execution status and result of an inspection task.
 */
@Data
public class InspectTaskGetResponse extends AbstractBceResponse {
    private String success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private String state;
        private String type;
        private String indices;
        private Integer totalCount;
        private String execTime;
        private List<Group> groups;
    }

    @Data
    public static class Group {
        private String grade;
        private Integer itemCount;
        private List<InspectResult> results;
    }

    @Data
    public static class InspectResult {
        private String id;
        private String type;
        private String conclusion;
        private Boolean hasLowRiskAdvice;
        private Boolean notExistIndex;
    }
}
