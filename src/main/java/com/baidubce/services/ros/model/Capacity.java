/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ros.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Capacity of vehicle,including weight/volume/count.
 *
 * @author zhangmengmeng01
 * @date 2019/05/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Capacity {
    /**
     * Weight of the vehicle.
     */
    private Double weight;
    /**
     * Volume of the vehicle.
     */
    private Double volume;
    /**
     * Count of objects the vehicle can hold.
     */
    private Double count;
}
