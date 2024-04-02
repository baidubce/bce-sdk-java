package com.baidubce.services.bcm.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardReportData {
    private String alias;
    private List<String> children;
    private Map<String, Double> metrics;
    private String name;
    private String value;
}
