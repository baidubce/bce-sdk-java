package com.baidubce.services.bcm.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class DashboardData {
    private List<DashboardMetric> metric;
    private List<DashboardMonitorObject> monitorObject;
    private String scope;
    private String subService;
    private String region;
    private ScopeValue scopeValue;
    private String resourceType;
    private String monitorType;
    private List<DashboardNamespace> namespace;
    private String product;
}
