/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.cnap.model.deploygroup;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.mortbay.util.StringUtil;

/**
 * The model for metric config.
 */
public class MetricConfigModel {

    /**
     * The type of metric.
     * eg counter, gauge, summary, histogram
     */
    private String type;

    /**
     * The name of metric.
     */
    private String name;

    /**
     * The help of metric.
     */
    private String help;

    /**
     * The match pattern.
     */
    private String match;

    /**
     * The labels info.
     */
    private Map<String, String> labels = new HashMap<String, String>();

    /**
     * The value of metric.
     */
    private String value;

    /**
     * The retention info.
     */
    private String retention;

    /**
     * The flag which indicates cumulative.
     */
    private boolean gaugeCumulative;

    /**
     * The list histogram Bucket.
     */
    private List<String> histogramBuckets;

    /**
     * The summary quantiles.
     */
    private List<String> summaryQuantiles;

    /**
     * The datasource 0f metric.
     */
    private DataSourceModel metricFileFilter;

    /**
     * The pid info.
     */
    private String pid;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRetention() {
        return retention;
    }

    public void setRetention(String retention) {
        this.retention = retention;
    }

    public void setGaugeCumulative(boolean gaugeCumulative) {
        this.gaugeCumulative = gaugeCumulative;
    }

    public boolean getGaugeCumulative() {
        return this.gaugeCumulative;
    }

    public List<String> getHistogramBuckets() {
        return histogramBuckets;
    }

    public void setHistogramBuckets(List<String> histogramBuckets) {
        this.histogramBuckets = histogramBuckets;
    }

    public List<String> getSummaryQuantiles() {
        return summaryQuantiles;
    }

    public void setSummaryQuantiles(List<String> summaryQuantiles) {
        this.summaryQuantiles = summaryQuantiles;
    }

    public DataSourceModel getMetricFileFilter() {
        return metricFileFilter;
    }

    public void setMetricFileFilter(DataSourceModel metricFileFilter) {
        this.metricFileFilter = metricFileFilter;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public MetricConfigModel withType(String type) {
        this.setType(type);
        return this;
    }

    public MetricConfigModel withName(String name) {
        this.setName(name);
        return this;
    }

    public MetricConfigModel withHelp(String help) {
        this.setHelp(help);
        return this;
    }

    public MetricConfigModel withMatch(String match) {
        this.setMatch(match);
        return this;
    }

    public MetricConfigModel withValue(String value) {
        this.setValue(value);
        return this;
    }

    public MetricConfigModel withRetention(String retention) {
        this.setRetention(retention);
        return this;
    }

    public MetricConfigModel withGaugeCumulative(boolean gaugeCumulative) {
        this.setGaugeCumulative(gaugeCumulative);
        return this;
    }

    public MetricConfigModel addHistogramBuckets(String histogramBucket) {
        if (StringUtils.isEmpty(histogramBucket)) {
            histogramBuckets = new LinkedList<String>();
        }
        histogramBuckets.add(histogramBucket);
        return this;
    }

    public MetricConfigModel addSummaryQuantiles(String summaryQuantily) {
        if (StringUtils.isEmpty(summaryQuantily)) {
            summaryQuantiles = new LinkedList<String>();
        }
        summaryQuantiles.add(summaryQuantily);
        return this;
    }

    public MetricConfigModel withMetricFileFilter(DataSourceModel metricFileFilter) {
       this.setMetricFileFilter(metricFileFilter);
       return this;
    }

    public MetricConfigModel withPid(String pid) {
        this.setPid(pid);
        return this;
    }



}
