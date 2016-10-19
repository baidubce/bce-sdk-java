package com.baidubce.services.tsdb.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * Represent the response for getting metrics from Tsdb.
 */
public class GetMetricsResponse extends AbstractBceResponse {
    
    private List<String> metrics;

    public List<String> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<String> metrics) {
        this.metrics = metrics;
    }
}
