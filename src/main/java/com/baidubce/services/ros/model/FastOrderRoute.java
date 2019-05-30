/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ros.model;

import java.util.List;

import lombok.Data;

/**
 * Fast order route info.
 *
 * @author zhangmengmeng01
 * @date 2019/05/20
 */
@Data
public class FastOrderRoute {
    private String vehicleModeId;
    private Double travelDistance;
    private Double travelTime;
    private List<Double> capacityRate;
    private List<String> routePlans;
}
