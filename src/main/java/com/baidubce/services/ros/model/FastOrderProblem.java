/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ros.model;

import java.util.List;

import com.baidubce.model.GenericAccountRequest;

import lombok.Data;

/**
 * Fast order problem info.
 *
 * @author zhangmengmeng01
 * @date 2019/05/20
 */
@Data
public class FastOrderProblem extends GenericAccountRequest {
    /**
     * Type of order.
     * Optional value: FAST_ORDER /  ACCURATE_ORDER
     */
    private String orderType;
    private List<FastOrderDepot> depots;
    private List<VehicleModel> vehicleModels;
    private List<FastOrderServiceJob> serviceJobs;
}
