package com.baidubce.services.bes.v2.model.schedule;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Response for listing schedules.
 */
@Data
public class ScheduleListResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Result result;

    @Data
    public static class Result {
        private List<Schedule> schedules;
    }

    @Data
    public static class Schedule {
        private String clusterId;
        private String schedule;
        private String scheduleName;
        private String taskType;
        private Map<String, Object> task;
        private Integer taskRunTimes;
        private Integer taskFailTimes;
        private Long lastExecuteMillis;
        private Long modifiedMillis;
        private String scheduleId;
        private String modifiedVersion;
        private String status;
    }
}
