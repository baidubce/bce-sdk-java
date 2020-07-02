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

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * The model for pod metric config.
 */
public class PodMetricsConfigModel {
    /**
     * The input config model.
     */
    private InputConfigModel input;

    /**
     * The grok config model.
     */
    private GrokConfigModel grok;

    /**
     * The metrics config model.
     */
    private List<MetricConfigModel> metricsConfig;

    public InputConfigModel getInput() {
        return input;
    }

    public void setInput(InputConfigModel input) {
        this.input = input;
    }

    public GrokConfigModel getGrok() {
        return grok;
    }

    public void setGrok(GrokConfigModel grok) {
        this.grok = grok;
    }

    public List<MetricConfigModel> getMetricsConfig() {
        return metricsConfig;
    }

    public void setMetricsConfig(List<MetricConfigModel> metricsConfig) {
        this.metricsConfig = metricsConfig;
    }

    public PodMetricsConfigModel withInputConfigModel(InputConfigModel input) {
        this.setInput(input);
        return this;
    }

    public PodMetricsConfigModel withGrokConfigModel(GrokConfigModel grok) {
        this.setGrok(grok);
        return this;
    }

    public PodMetricsConfigModel addMetricConfigModel(MetricConfigModel metricConfigModel) {
        if (CollectionUtils.isEmpty(metricsConfig)) {
            metricsConfig = new LinkedList<MetricConfigModel>();
        }
        metricsConfig.add(metricConfigModel);
        return this;

    }



}
