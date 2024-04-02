package com.baidubce.services.bcm.model.custom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author guanyanyan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomAlarmRule {
    @JsonIgnoreProperties
    private Long id;
    private int index;
    private String metricName;
    private List<MetricDimensions> dimensions;
    private String statistics;
    private String threshold;
    private String comparisonOperator;
    private int cycle;
    private int count;
    private String function;
}
