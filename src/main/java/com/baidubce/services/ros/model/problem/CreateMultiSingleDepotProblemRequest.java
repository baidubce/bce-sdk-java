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
package com.baidubce.services.ros.model.problem;

import com.baidubce.model.GenericAccountRequest;
import com.baidubce.services.ros.model.ExternalCapacity;
import com.baidubce.services.ros.model.ExternalTimeWindow;
import com.baidubce.services.ros.model.ExternalVehicleGroup;
import com.baidubce.services.ros.model.ExternalVehicleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Create MultiDepot Task Request
 *
 * @author anqi05
 * @date 2019/10/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMultiSingleDepotProblemRequest extends GenericAccountRequest {
    private String scenesType;
    private String matrixId;
    private String lbsType;
    private String commitId;
    private String distanceType;
    private List<ExternalDepot> depots;
    private List<ExternalVehicleModel> vehicleModels;
    private List<ExternalServiceJob> serviceJobs;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExternalDepot {
        private String depotId;
        private ExternalTimeWindow depotTimeWindow;
        private List<ExternalVehicleGroup> vehicleGroups;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExternalServiceJob {
        private String serviceJobId;
        private String depotId;
        private String region;
        private Double serviceStayDuration;
        private ExternalCapacity demand;
        private List<ExternalTimeWindow> serviceTimeWindows;
        private List<String> skills;
        private Integer priority;
    }
}
