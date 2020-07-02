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


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Vehicle group info.
 *
 * @author zhangmengmeng01
 * @date 2019/05/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastOrderVehicleGroup {
    private String vehicleTypeId;
    private String vehicleModelId;
    private Integer vehicleCount;
    private Integer maxVisited;
    private Integer maxRunDistance;
    private Integer maxRunTime;
    private Integer returnToDepot;
    private String departureLocationKey;
    private LocationPoint departureLocation;
    private Integer startTime;

    public FastOrderVehicleGroup(String vehicleTypeId, String vehicleModelId, Integer vehicleCount,
                                 Integer maxRunDistance, Integer maxRunTime, Integer returnToDepot,
                                 String departureLocationKey,
                                 LocationPoint departureLocation, Integer startTime) {
        this.vehicleTypeId = vehicleTypeId;
        this.vehicleModelId = vehicleModelId;
        this.vehicleCount = vehicleCount;
        this.maxRunDistance = maxRunDistance;
        this.maxRunTime = maxRunTime;
        this.returnToDepot = returnToDepot;
        this.departureLocationKey = departureLocationKey;
        this.departureLocation = departureLocation;
        this.startTime = startTime;
    }
}
