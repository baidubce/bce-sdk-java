/*
 * Copyright 2017 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdm.model.v3.rules;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;

public class DeviceRuleFormat {
    /**
     * Mode of Device Rule Format .
     */
    private ModeType mode;
    /**
     * Metric of Device Rule Format .
     */
    private String metric;
    /**
     * List of Tag of Device Rule Format .
     */
    private Map<String, String> tags;

    public ModeType getMode() {
        return mode;
    }

    public void setMode(ModeType mode) {
        this.mode = mode;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public enum ModeType {
        METRIC("metric"), // Metric type of Device Rule Format .
        FIELD("field");   // Field type of Device Rule Format .

        private String dataType;

        private ModeType(String dataType) {
            this.dataType = dataType;
        }

        @JsonValue
        public String getDataType() {
            return dataType;
        }
    }
}