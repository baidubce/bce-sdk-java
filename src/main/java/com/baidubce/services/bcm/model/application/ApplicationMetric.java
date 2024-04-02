package com.baidubce.services.bcm.model.application;

import lombok.Data;

import java.util.List;

@Data
public class ApplicationMetric {
    private long id;
    private long taskId;
    private String metricName;
    private String metricAlias;
    private String metricUnit;
    private int valueFieldType;
    private String valueFieldName;
    private String valueMatchRule;

    private List<ApplicationAggrTag> aggrTags;

    private int saveInstanceData;


}