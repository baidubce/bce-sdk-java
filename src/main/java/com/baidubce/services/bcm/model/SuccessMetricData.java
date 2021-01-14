package com.baidubce.services.bcm.model;

import java.util.List;

/**
 * Success Metric Data
 */
public class SuccessMetricData {

    String metricName;

    List<Dimension> dimensions;

    List<DataPoint> dataPoints;

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    public List<DataPoint> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(List<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }
}
