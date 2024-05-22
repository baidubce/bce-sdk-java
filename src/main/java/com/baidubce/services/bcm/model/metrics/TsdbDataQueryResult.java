package com.baidubce.services.bcm.model.metrics;


import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TsdbDataQueryResult extends AbstractBceResponse {
    private String region;
    private String scope;
    private String userid;
    private String resourceId;
    private String namespace;
    private String metricName;
    /* 带前缀的指标名称，为了兼容旧代码 */
    @JsonIgnore
    private String metricNameWithPrefix;

    /** 其他的维度信息 */
    private Map<String, String> dimensions;

    /** 数据点，按照时间顺序排列的 */
    private List<DataPoint> dataPoints;

    @JsonIgnore
    private String requestId;

    /**
     * 代表一个数据点
     */
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataPoint {
        private String timestamp;
        private Double average;
        private Double sum;
        private Double maximum;
        private Double minimum;
        private Integer sampleCount;
        private Object value;
    }
}
