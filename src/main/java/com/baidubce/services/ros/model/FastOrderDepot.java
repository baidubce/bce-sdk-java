/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ros.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fast order depot info.
 *
 * @author zhangmengmeng01
 * @date 2019/05/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastOrderDepot {
    private String depotId;
    private LocationPoint location;
    private TimeWindow depotTimeWindow;
    private List<FastOrderVehicleGroup> vehicleGroups;
}
