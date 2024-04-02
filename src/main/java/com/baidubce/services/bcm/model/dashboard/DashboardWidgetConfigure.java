package com.baidubce.services.bcm.model.dashboard;

import lombok.Data;

import java.util.List;

@Data
public class DashboardWidgetConfigure {
    private List<DashboardData> data;
    private Style style;
    private String title;
    private TimeRange timeRange;
    private String time;
    private String monitorType;
}
