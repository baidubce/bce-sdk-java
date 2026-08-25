package com.baidubce.services.bes.v2.model.logmanage;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * Response for creating a log export task, without the result envelope.
 */
@Data
public class LogExportResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private List<Task> tasks;

    @Data
    public static class Task {
        private String logType;
        private String taskId;
    }
}
