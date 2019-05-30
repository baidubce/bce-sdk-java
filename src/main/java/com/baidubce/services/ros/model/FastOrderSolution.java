/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ros.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fast order solution info.
 *
 * @author zhangmengmeng01
 * @date 2019/05/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastOrderSolution {
    private Double totalTravelDistance;
    private Double totalTravelTime;
    private List<Double> maxCapacityRate;
    private List<Double> avgCapacityRate;
    private List<FastOrderRoute> routes;
}
