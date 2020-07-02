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
 * The model for lama config.
 */
public class LamaConfigModel {

    /**
     * The list of data source.
     */
    private List<DataSourceModel> dataSources;

    /**
     * The config of pod log.
     */
    private PodLogsConfigModel podLogsConfig;

    /**
     * The config of pod metrics.
     */
    private PodMetricsConfigModel podMetricsConfig;

    public List<DataSourceModel> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<DataSourceModel> dataSources) {
        this.dataSources = dataSources;
    }

    public PodLogsConfigModel getPodLogsConfig() {
        return podLogsConfig;
    }

    public void setPodLogsConfig(PodLogsConfigModel podLogsConfig) {
        this.podLogsConfig = podLogsConfig;
    }

    public PodMetricsConfigModel getPodMetricsConfig() {
        return podMetricsConfig;
    }

    public void setPodMetricsConfig(PodMetricsConfigModel podMetricsConfig) {
        this.podMetricsConfig = podMetricsConfig;
    }

    public LamaConfigModel addDatasourceModel(DataSourceModel dataSourceModel) {
        if (CollectionUtils.isEmpty(dataSources)) {
            dataSources = new LinkedList<DataSourceModel>();
        }
        dataSources.add(dataSourceModel);
        return this;
    }

    public LamaConfigModel withPodLogsConfig(PodLogsConfigModel podLogsConfig) {
        this.setPodLogsConfig(podLogsConfig);
        return this;
    }

    public LamaConfigModel withPodMetricsConfigModel(PodMetricsConfigModel podMetricsConfig) {
        this.setPodMetricsConfig(podMetricsConfig);
        return this;
    }


}
