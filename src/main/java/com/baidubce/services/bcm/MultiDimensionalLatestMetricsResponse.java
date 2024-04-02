package com.baidubce.services.bcm;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcm.model.DataPoint;
import com.baidubce.services.bcm.model.Dimension;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author gongjiaming
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiDimensionalLatestMetricsResponse extends AbstractBceResponse {

    private String requestId;

    private String code;

    private String message;

    private List<DataMetric> metrics;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataMetric {

        private String region;

        private String scope;

        private String userId;

        private String resourceId;

        private String metricName;
        /** 维度信息 */
        private List<Dimension> dimensions;
        /** 数据点，按照时间顺序排列的 */
        private List<DataPoint> dataPoints;
    }
}
