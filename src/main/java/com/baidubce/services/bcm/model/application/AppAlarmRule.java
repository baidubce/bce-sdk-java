package com.baidubce.services.bcm.model.application;

import com.baidubce.services.bcm.model.Dimension;
import com.baidubce.services.bcm.model.Statistics;
import lombok.Data;

import java.util.List;

@Data
public class AppAlarmRule {
    private String metric;
    private String metricAlias;
    private int cycle;
    private Statistics statistics;
    private double threshold;
    private String comparisonOperator;
    private int count;
    private String function;
    private int sequence;
    private List<Dimension> metricDimensions;
    private String formulaV2Alias;
    private String metricTags;
}