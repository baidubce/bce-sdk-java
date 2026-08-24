package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Response for cluster operation tasks.
 */
@Data
public class ClusterTasksResponse extends AbstractBceResponse {
    private String success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private String clusterId;
        private List<AppTask> appTasks;
    }

    @Data
    public static class AppTask {
        private List<SubTask> subTasks;
        private String type;
        private Map<String, Object> detail;
        private Integer progress;
        private String status;
        @JsonProperty("start_timestamp")
        private String startTimestamp;
        @JsonProperty("end_timestamp")
        private String endTimestamp;
    }

    @Data
    public static class SubTask {
        private String type;
        private Map<String, Object> detail;
        private BigDecimal progress;
        private String status;
        private List<TaskCheckItem> taskList;
        @JsonProperty("start_timestamp")
        private String startTimestamp;
        @JsonProperty("end_timestamp")
        private String endTimestamp;
    }

    @Data
    public static class TaskCheckItem {
        private String name;
        private String type;
        private String level;
        private String status;
        private String finishTime;
        private String message;
        private List<String> failedIndices;
    }
}
