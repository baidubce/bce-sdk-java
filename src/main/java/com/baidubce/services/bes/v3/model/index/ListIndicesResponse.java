package com.baidubce.services.bes.v3.model.index;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class ListIndicesResponse extends AbstractBceResponse {
    private List<IndexItem> indices;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalCount;

    @Data
    public static class IndexItem {
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
