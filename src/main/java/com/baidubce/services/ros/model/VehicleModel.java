/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ros.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Vehicle model info.
 *
 * @author zhangmengmeng01
 * @date 2019/05/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleModel {
    private String vehicleModelId;
    private Capacity capacity;
    private Double perDistanceUnitPrice;
    private Double fixedCost;
    private Double waitingCost;
    private Double averageVelocity;
}
