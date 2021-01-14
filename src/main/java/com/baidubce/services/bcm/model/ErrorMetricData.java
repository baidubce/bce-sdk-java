package com.baidubce.services.bcm.model;

import java.util.List;

/**
 * Error Metric Data
 */
public class ErrorMetricData {

    String metricName;

    List<Dimension> dimensions;

    private String message = "";

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
