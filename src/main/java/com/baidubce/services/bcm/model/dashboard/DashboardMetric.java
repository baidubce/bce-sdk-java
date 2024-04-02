package com.baidubce.services.bcm.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class DashboardMetric {
    private String name;
    private String unit;
    private String alias;
    private List<String> contrast;
    private List<String> timeContrast;
    private String statistics;
    private List<String> dimensions;
    private List<MetricDimensions> metricDimensions;
    private int cycle;
    private String displayName;
}
