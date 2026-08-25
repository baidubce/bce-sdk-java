package com.baidubce.services.bes.v2.model.logmanage;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Response for log export task record, without the result envelope.
 */
@Data
public class LogExportRecordResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Integer progress;
    private String state;
    private Config config;

    @Data
    public static class Config {
        private String logType;
        private String bosBucket;
        private String bosPath;
        private String startTime;
        private String endTime;
        private String region;
    }
}
