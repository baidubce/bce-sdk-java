package com.baidubce.services.bcm.model.application;

import com.baidubce.services.bcm.model.DataPoint;
import lombok.Data;

import java.util.List;

@Data
public class MetricDataForApplication {
    private String namespace;
    private List<Dimension> dimensions;
    private List<DataPoint> dataPoints;

    @Data
    public static class Dimension {
        private String key;
        private String name;
    }
}