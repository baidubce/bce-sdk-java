/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec.model.vo;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * BEC metrics.
 */
@Data
public class MetricsVo extends AbstractBceResponse {

    /**
     * The list of the BEC metrics.
     */
    private List<Metric> metrics;

    /**
     * Max value.
     */
    private BigDecimal maxValue;

    /**
     * Average value.
     */
    private BigDecimal avgValue;

    /**
     * Total value.
     */
    private BigDecimal totalValue;

    @Data
    public static class Metric {

        /**
         * Timestamp.
         */
        private int timeInSecond;

        /**
         * Metric value.
         */
        private BigDecimal value;
    }
}
