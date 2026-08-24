package com.baidubce.services.bes.v3.model.index;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class IndexDetailResponse extends AbstractBceResponse {
    private IndexInfo index;
    private String mappings;
    private String settings;
    private String aliases;

    @Data
    public static class IndexInfo {
        private String indexName;
        private String health;
        private String status;
        private String primaryShards;
        private String replicas;
        private String documentCount;
        private String storageSize;
        private String createTime;
        private String category;
    }
}
