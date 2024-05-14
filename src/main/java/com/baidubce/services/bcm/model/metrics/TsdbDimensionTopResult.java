package com.baidubce.services.bcm.model.metrics;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcm.model.Dimension;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * create by pangyangyang on 2024/04/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TsdbDimensionTopResult extends AbstractBceResponse {

    private String requestId;

    private List<TopData> topDatas;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TopData {
        private int order;
        private List<Dimension> dimensions;
    }
}
