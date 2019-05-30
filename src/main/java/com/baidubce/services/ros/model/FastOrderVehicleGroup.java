/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
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
    private Integer maxRunDistance;
    private Integer maxRunTime;
    private Integer returnToDepot;
    private String departureLocationKey;
    private LocationPoint departureLocation;
    private Integer startTime;
}
