package com.baidubce.services.bcm.model.metrics;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcm.model.Dimension;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TsdbMetricAllDataResult extends AbstractBceResponse {
    private String requestId;
    private String code;
    private String message;
    private List<AllDataMetric> metrics;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class AllDataMetric {
        private String region;
        private String scope;
        private String userId;
        private String resourceId;
        private String metricName;
        private List<Dimension> dimensions;
        private List<TsdbDataQueryResult.DataPoint> dataPoints;
    }
}
