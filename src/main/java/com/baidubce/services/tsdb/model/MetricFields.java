package com.baidubce.services.tsdb.model;

import java.util.List;

/**
 * Metric fields.
 */
public class MetricFields {

    private String metric;

    private List<String> fields;

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }
}
