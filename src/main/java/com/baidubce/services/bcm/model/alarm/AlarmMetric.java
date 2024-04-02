package com.baidubce.services.bcm.model.alarm;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class AlarmMetric extends AbstractBceResponse {
    private String alias;
    private String name;
    private String unitCategory;
    private String unitName;
    private Integer cycle;
    private List<List<String>> metricDimensions;
    private String scope;
    private String typeName;
}
