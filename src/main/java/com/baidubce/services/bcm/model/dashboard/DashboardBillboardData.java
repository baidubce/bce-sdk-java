package com.baidubce.services.bcm.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardBillboardData {
    private List<List<Double>> data;
    private Object decimals;
    private String displayName;
    private String instanceName;
    private String metricDimension;
    private String name;
    private String unit;
}
