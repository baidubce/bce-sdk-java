package com.baidubce.services.bes.v3.model.index;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class MonitorIndicesResponse extends AbstractBceResponse {
    private List<IndexOptionItem> indices;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalCount;

    @Data
    public static class IndexOptionItem {
        private String indexName;
        private String category;
    }
}
