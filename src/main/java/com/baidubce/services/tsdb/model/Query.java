/*
 * Copyright 2019 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
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
     * The start offset of datapoints that Tsdb return. Default: 0.
     */
    private Integer offset;
    
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

    /**
     * Optional.
     * The marker which is used to get data started from.
     * It should be the value of nextMarker of previous query request's result or null for the first query request.
     */
    private String marker;

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

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

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
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

    public Query withMarker(String marker) {
        this.marker = marker;
        return this;
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

    public Query withOffset(int offset) {
        this.offset = offset;
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
