/*
 * Copyright 2019-2020 Baidu, Inc.
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
package com.baidubce.services.ros.model.matrix;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.model.GenericAccountRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * matrix create data info
 * Created by liuzhenxing01 on 2019/5/30.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatrixCreateRequest extends GenericAccountRequest {

    private String name;
    private LocationType locationType = LocationType.bd09ll;
    private List<LbsType> lbsType;
    private List<String> vehicleType;
    private String parentMatrixId;
    private Boolean overallPlan;
    private List<Location> locations = new ArrayList<Location>();

    public MatrixCreateRequest(String name,
                               LocationType locationType,
                               List<LbsType> lbsType, List<String> vehicleType,
                               List<Location> locations) {
        this.name = name;
        this.locationType = locationType;
        this.lbsType = lbsType;
        this.vehicleType = vehicleType;
        this.locations = locations;
    }

    public enum LocationType {
        bd09ll, wgs84, gcj02, bd09mc
    }

    public enum LbsType {
        LEAST_TIME(0), LEAST_DISTANCE(1), NO_HIGHWAY(3), HIGHWAY_FIRST(4), LEAST_CONGESTION(5), LEAST_TOLL(6);

        int typeValue;

        LbsType(int typeValue) {
            this.typeValue = typeValue;
        }

        public int getTypeValue() {
            return typeValue;
        }
    }
}
