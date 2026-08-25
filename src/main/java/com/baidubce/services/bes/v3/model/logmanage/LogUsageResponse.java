package com.baidubce.services.bes.v3.model.logmanage;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.Map;

@Data
public class LogUsageResponse extends AbstractBceResponse {
    private Long totalSizeBytes;
    private Integer totalShards;
    private Map<String, LogUsageItem> indices;

    @Data
    public static class LogUsageItem {
        private Long sizeBytes;
        private Integer shards;
        private Long docCount;
    }
}
