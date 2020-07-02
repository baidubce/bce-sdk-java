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
package com.baidubce.services.ros.model.matrix;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * matrix location info
 * Created by liuzhenxing01 on 2019/5/30.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    private String key;
    private Double latitude;
    private Double longitude;
    private String address;

    public Location(String key, Double latitude, Double longitude) {
        this.key = key;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
