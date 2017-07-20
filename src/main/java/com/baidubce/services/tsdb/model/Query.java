package com.baidubce.services.tsdb.model;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Represent the Query for querying datapoints from Tsdb.
 */
public class Query {
    
    /**
     * Required.
     * The metric name.
     */
    private String metric;

    /**
     * Optional.
     * The field name, conflict with fields parameter.
     */
    private String field;

    /**
     * Optional.
     * The field names, conflict with field parameter.
     */
    private List<String> fields;

    /**
     * Optional.
     * The tag keys.
     */
    private List<String> tags;

    /**
     * Required.
     * The filters.
     */
    private Filters filters;

    /**
     * Optional.
     * GroupBy list.
     */
    private List<GroupBy> groupBy;
    
    /**
     * Optional.
     * The limit number of datapoints that Tsdb return. When not set this field, the default number of
     * datapoints that Tsdb return is 200000.
     */
    private Integer limit;
    
    /**
     * Optional.
     * Aggregator list.
     */
    private List<Aggregator> aggregators;

    /**
     * Optional.
     * The order: Asc or Desc, default is Asc.
     */
    private String order;

    /**
     * Optional.
     * The fill for interpolation.
     */
    private Fill fill;

    public Fill getFill() {
        return fill;
    }

    public void setFill(Fill fill) {
        this.fill = fill;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Filters getFilters() {
        return filters;
    }

    public void setFilters(Filters filters) {
        this.filters = filters;
    }

    public List<GroupBy> getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(List<GroupBy> groupBy) {
        this.groupBy = groupBy;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<Aggregator> getAggregators() {
        return aggregators;
    }

    public void setAggregators(List<Aggregator> aggregators) {
        this.aggregators = aggregators;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Query withFill(Fill fill) {
        this.fill = fill;
        return this;
    }

    public Query withMetric(String metric) {
        this.metric = metric;
        return this;
    }

    public Query withField(String field) {
        this.field = field;
        return this;
    }

    public Query withFields(List<String> fields) {
        this.fields = fields;
        return this;
    }

    public Query withTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Query withFilters(Filters filters) {
        this.filters = filters;
        return this;
    }
    
    public Query withGroupBy(List<GroupBy> groupBy) {
        this.groupBy = groupBy;
        return this;
    }
    
    public Query withLimit(int limit) {
        this.limit = limit;
        return this;
    }
    
    public Query withAggregators(List<Aggregator> aggregators) {
        this.aggregators = aggregators;
        return this;
    }

    public Query withOrder(String order) {
        this.order = order;
        return this;
    }

    public Query addAggregator(Aggregator aggregator) {
        initialAggregators();
        this.aggregators.add(aggregator);
        return this;
    }

    private void initialAggregators() {
        if (aggregators == null) {
            aggregators = Lists.newArrayList();
        }
    }
    
    public Query addGroupBy(GroupBy groupBy) {
        initialGroupBy();
        this.groupBy.add(groupBy);
        return this;
    }
    
    private void initialGroupBy() {
        if (groupBy == null) {
            groupBy = Lists.newArrayList();
        }
    }
}
