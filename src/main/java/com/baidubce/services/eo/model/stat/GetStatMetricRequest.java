package com.baidubce.services.eo.model.stat;

import com.baidubce.services.eo.model.EoRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The request for querying stat metrics of a site or domain.
 *
 */
public class GetStatMetricRequest extends EoRequest {
    /**
     * The metric types.
     */
    private List<String> metrics;

    /**
     * The start of the query time range, UTC time.
     */
    private String startTime;

    /**
     * The end of the query time range, UTC time.
     */
    private String endTime;

    /**
     * The show type.
     */
    private String showType;

    /**
     * The group (aggregation) fields.
     */
    private List<String> group;

    /**
     * The filter conditions.
     */
    private List<FilterItem> filter;

    /**
     * The limit condition, usually used for paging or limiting the count.
     */
    private LimitField limit;

    /**
     * @param metrics one or more metric types.
     * @return this object
     */
    public GetStatMetricRequest withMetrics(String... metrics) {
        this.metrics = new ArrayList<String>(Arrays.asList(metrics));
        return this;
    }

    /**
     * @return metrics
     */
    public List<String> getMetrics() {
        return metrics;
    }

    /**
     * @param metrics
     */
    public void setMetrics(List<String> metrics) {
        this.metrics = metrics;
    }

    /**
     * @param startTime
     * @return this object
     */
    public GetStatMetricRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * @return startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @param endTime
     * @return this object
     */
    public GetStatMetricRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * @return endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @param showType
     * @return this object
     */
    public GetStatMetricRequest withShowType(String showType) {
        this.showType = showType;
        return this;
    }

    /**
     * @return showType
     */
    public String getShowType() {
        return showType;
    }

    /**
     * @param showType
     */
    public void setShowType(String showType) {
        this.showType = showType;
    }

    /**
     * @param group
     * @return this object
     */
    public GetStatMetricRequest withGroup(List<String> group) {
        this.group = group;
        return this;
    }

    /**
     * @return group
     */
    public List<String> getGroup() {
        return group;
    }

    /**
     * @param group
     */
    public void setGroup(List<String> group) {
        this.group = group;
    }

    /**
     * @param filterItem
     * @return this object
     */
    public GetStatMetricRequest withFilter(FilterItem filterItem) {
        if (this.filter == null) {
            this.filter = new ArrayList<FilterItem>();
        }
        this.filter.add(filterItem);
        return this;
    }

    /**
     * @return filter
     */
    public List<FilterItem> getFilter() {
        return filter;
    }

    /**
     * @param filter
     */
    public void setFilter(List<FilterItem> filter) {
        this.filter = filter;
    }

    /**
     * @param limit
     * @return this object
     */
    public GetStatMetricRequest withLimit(LimitField limit) {
        this.limit = limit;
        return this;
    }

    /**
     * @return limit
     */
    public LimitField getLimit() {
        return limit;
    }

    /**
     * @param limit
     */
    public void setLimit(LimitField limit) {
        this.limit = limit;
    }
}
