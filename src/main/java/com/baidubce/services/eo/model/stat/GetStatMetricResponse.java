package com.baidubce.services.eo.model.stat;

import com.baidubce.services.eo.model.EoResponse;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The response of a stat metric query.
 *
 */
public class GetStatMetricResponse extends EoResponse {
    /**
     * The metric results, keyed by the metric name.
     */
    private Map<String, List<StatData>> metrics = new LinkedHashMap<String, List<StatData>>();

    /**
     * Collect a dynamic metric entry from the response body.
     *
     */
    @JsonAnySetter
    public void addMetric(String name, List<StatData> data) {
        this.metrics.put(name, data);
    }

    /**
     * @return the metric results keyed by the metric name.
     */
    @JsonAnyGetter
    public Map<String, List<StatData>> getMetrics() {
        return metrics;
    }
}
