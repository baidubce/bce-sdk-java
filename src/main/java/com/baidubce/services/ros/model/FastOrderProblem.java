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
