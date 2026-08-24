package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * Response for listing inspection tasks completed in the past 7 days.
 * page is a top-level field in the actual response, not nested under result
 * (contrary to what the earlier field definition assumed).
 */
@Data
public class InspectTaskListResponse extends AbstractBceResponse {
    private String success;
    private Integer status;
    private Page page;

    @Data
    public static class Page {
        private String orderBy;
        private String order;
        private Integer pageNo;
        private Integer pageSize;
        private Integer totalCount;
        private List<TaskSummary> result;
    }

    @Data
    public static class TaskSummary {
        private String taskId;
        private String type;
        private String execTime;
        private Integer lowRiskCount;
        private Integer highRiskCount;
        private Integer errorCount;
    }
}
