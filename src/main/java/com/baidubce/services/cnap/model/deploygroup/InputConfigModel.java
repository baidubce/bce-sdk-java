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

/**
 * The model for input config.
 */
public class InputConfigModel {
    /**
     * The flag which indicates read all.
     */
    private boolean readall;

    /**
     * The data source of metric.
     */
    private DataSourceModel metricFileFilter;

    public boolean getReadall() {
        return readall;
    }

    public void setReadall(boolean readall) {
        this.readall = readall;
    }

    public DataSourceModel getMetricFileFilter() {
        return metricFileFilter;
    }

    public void setMetricFileFilter(DataSourceModel metricFileFilter) {
        this.metricFileFilter = metricFileFilter;
    }

    public InputConfigModel withReadall(boolean readall) {
        this.setReadall(readall);
        return this;
    }

    public InputConfigModel withMetricFileFilter(DataSourceModel metricFileFilter) {
        this.setMetricFileFilter(metricFileFilter);
        return this;
    }


}
