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
 * Response of truck direction service.
 *
 * @author weizhijun
 * @date 2019/03/07
 */
@Data
public class DirectionResponse extends AbstractBceResponse {
    /**
     * Result of direction.
     */
    private PathRoute result;

    /**
     * Object which contains the detail of the planned path information between two points.
     */
    @Data
    public static class PathRoute {
        /**
         * The restriction information of the path.
         * <p>
         * If the path matches multiple restriction information, then it only returns one of them.
         */
        private List<RestrictionInfo> restriction;

        /**
         * The total number of returned path.
         */
        private Integer total;

        /**
         * List of returned path.
         */
        private List<Route> routes;
    }

    /**
     * Object which contains the detail of single route between two points.
     */
    @Data
    public static class Route {

        /**
         * Location coordinate of the start point.
         */
        private Location origin;

        /**
         * Location coordinate of the end point.
         */
        private Location destination;

        /**
         * Distance between origin and destination. The unit is meter.
         */
        private Double distance;

        /**
         * Duration between origin and destination. The unit is second.
         */
        private Integer duration;

        /**
         * Tag of this planned route.
         */
        private String tag;

        /**
         * Road toll. The unit is "yuan"
         */
        private Integer toll;

        /**
         * Mileage of the tolled road. The unit is meter.
         */
        private Double tollDistance;

        /**
         * List of segmented route.
         */
        private List<Step> steps;
    }

    /**
     * Object which represents a segmented route.
     */
    @Data
    public static class Step {

        /**
         * Leg index of the step.
         */
        private Integer legIndex;

        /**
         * Angle of entry into the road.
         */
        private Integer direction;

        /**
         * Distance of the segmented route.
         */
        private Double distance;

        /**
         * Name of the segmented route.
         */
        private String roadName;

        /**
         * Type of the segmented route.
         */
        private Integer roadType;

        /**
         * Tolled distance of the segmented route.
         */
        private Double tollDistance;

        /**
         * Name of the toll station.
         */
        private String tollGateName;

        /**
         * Location coordinate of the toll station.
         */
        private Location tollGateLocation;

        /**
         * Location coordinate of start point of the segmented route.
         */
        private Location startLocation;

        /**
         * Location coordinate of end point of the segmented route.
         */
        private Location endLocation;

        /**
         * Coordinates of the segmented route.
         * eg. "117.20525509975337,31.75394188978729;117.20521518267502,31.754031256443565;"
         */
        private String path;

        /**
         * List of traffic condition the segmented route (historical traffic condition).
         */
        private List<TrafficCondition> trafficCondition;
    }

    /**
     * Traffic condition information.
     */
    @Data
    public static class TrafficCondition {

        /**
         * Road condition index.
         */
        private Integer status;

        /**
         * The number of coordinate points with the same road condition in the path from the current coordinate point.
         */
        private Integer geoCnt;

        /**
         * The same distance in the path from the current coordinate point.
         * And the unit is meter.
         */
        private Double distance;
    }
}
