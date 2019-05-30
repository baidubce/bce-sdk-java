/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.lps.model;

import com.baidubce.model.GenericAccountRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

/**
 * Request of batch calculating the distance, duration, restrict information of routes based on the truck parameters
 * (eg. height, weight, length...) and driving tactics.
 *
 * @author weizhijun
 * @date 2019/03/07
 */
@Data
@Builder
public class RouteMatrixRequest extends GenericAccountRequest {

    /**
     * Point matrix which includes the start points and end points of routes.
     *
     * @see PointMatrix
     */
    private PointMatrix pointMatrix;

    /**
     * Type of the coordinate of the start point and end point.
     * <p>
     * Optional values are "bd09ll", "bd09mc", "gcj02", "wgs84".
     */
    private String coordType;

    /**
     * Height of the vehicle.
     * <p>
     * The unit is meter. And the value of height must be between 0 and 5.0.
     * The default value is 1.8.
     */
    private Double height;

    /**
     * Width of the vehicle.
     * <p>
     * The unit is meter. And the value of width must be between 0 and 3.0.
     * The default value is 1.9.
     */
    private Double width;

    /**
     * Total weight of the vehicle and the goods in it.
     * <p>
     * The unit is ton. And the value of weight must be between 0 and 100.
     * The default value is 2.5.
     */
    private Double weight;

    /**
     * Length of the vehicle.
     * <p>
     * The unit is meter. And the value of weight must be between 0 and 20.0.
     * The default value is 4.2.
     */
    private Double length;

    /**
     * Axle weight of the vehicle.
     * <p>
     * The unit is ton. And the value of axle weight must be between 0 and 50.
     * The default value is 2.
     */
    private Double axleWeight;

    /**
     * Axle counts of the vehicle.
     * <p>
     * The value of axle counts must be between 0 and 50.
     * The default value is 2.
     */
    private Integer axleCount;

    /**
     * If the vehicle is trailer.
     * <p>
     * 1: yes
     * 0: no
     */
    private Integer isTrailer;

    /**
     * Province which the plate of the vehicle belongs to.
     * The default value is empty string.
     */
    private String plateProvince;

    /**
     * Plate number of the vehicle.
     * The default value is empty string.
     */
    private String plateNumber;

    /**
     * Plate color of the vehicle.
     * <p>
     * 0: blue (default value)
     * 1: yellow
     * 2: black
     * 3: white
     */
    private Integer plateColor;

    /**
     * Departure time.
     * <p>
     * The format is unix timestamp in seconds.
     * And it must be the time of the next three days.
     */
    private Long departureTime;

    /**
     * Driving tactics.
     * <p>
     * Util now, the optional value of tactics has to be 0, which represents the tactics of shortest time.
     */
    private Integer tactics;

    /**
     * If return alternative routes.
     * <p>
     * 0(default value): only return one route.
     * 1: return 1 to 3 alternative routes.
     */
    private Integer alternatives;

    @JsonIgnore
    public String getOrigins() {
        return pointMatrix.getOrigins();
    }

    @JsonIgnore
    public void setOrigins(String origins) {
        pointMatrix.setOrigins(origins);
    }

    @JsonIgnore
    public String getDestinations() {
        return pointMatrix.getDestinations();
    }

    @JsonIgnore
    public void setDestinations(String destinations) {
        pointMatrix.setDestinations(destinations);
    }
}
