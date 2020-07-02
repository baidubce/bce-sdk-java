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
package com.baidubce.services.ros.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fast order solution info.
 *
 * @author chenbo14
 * @date 2019/07/01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastOrderSolutionV2 {
    private Double totalTravelDistance;
    private Double totalTravelTime;
    private List<Double> maxCapacityRate;
    private List<Double> avgCapacityRate;
    private List<FastOrderRoute> routes;

    @Data
    public static class FastOrderRoute {
        private String id;
        @Deprecated
        private String vehicleModeId;
        private String vehicleModelId;
        private Double travelDistance;
        private Double travelTime;
        private List<Double> capacityRate;
        private List<RoutePlan> routePlans;
    }

    @Data
    public static class RoutePlan {
        private String serviceJobId;
        private Double arrivalTime;
        private Double departureTime;
    }
}
