package com.baidubce.services.bcm.model.application;

import lombok.Data;

@Data
public class ACMonitorObjectViewModel {
    private String monitorObjectName;
    private String monitorObjectNameView = "";
    private String metricDimensionView = "";
}