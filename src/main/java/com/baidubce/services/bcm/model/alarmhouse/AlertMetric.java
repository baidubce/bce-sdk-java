package com.baidubce.services.bcm.model.alarmhouse;

import lombok.Data;

@Data
public class AlertMetric {
    private Metric metric;
    private Rule rule;
}
