package com.baidubce.services.bcm.model.alarm;

import com.baidubce.services.bcm.model.Dimension;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AlarmRule {
    private Long index;
    private String metric;
    private Long periodInSecond;
    private String statistics;
    private String threshold;
    private String comparisonOperator;
    private Integer evaluationPeriodCount;
    private List<Dimension> metricDimensions;
}