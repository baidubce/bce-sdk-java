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
package com.baidubce.services.lps.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

/**
 * Response of batch routes calculating service.
 *
 * @author weizhijun
 * @date 2019/03/07
 */
@Data
public class RouteMatrixResponse extends AbstractBceResponse {

    /**
     * Total result of routes calculation.
     */
    private List<Distance> result;

    /**
     * Object including the calculate result of one point pair (one start point and one end point).
     */
    @Data
    public static class Distance {

        /**
         * Restriction information.
         */
        private RestrictionInfo restriction;

        /**
         * Distance information.
         */
        private DistanceInfo distance;

        /**
         * Duration information.
         */
        private DurationInfo duration;
    }

    /**
     * Object including the distance information of the route of one point pair.
     */
    @Data
    public static class DistanceInfo {

        /**
         * Description of the distance.
         * <p>
         * The unit would be meter or kilometer.
         */
        private String text;

        /**
         * Value of the distance. And the unit is meter.
         */
        private Double value;
    }

    /**
     * Object including the duration information of the route of one point pair.
     */
    @Data
    public static class DurationInfo {

        /**
         * Description of the duration.
         * <p>
         * The unit would be minute or hour.
         */
        private String text;

        /**
         * Value of the duration. And the unit is second.
         */
        private Double value;
    }
}
