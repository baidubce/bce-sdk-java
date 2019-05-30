/*
 * Copyright (c) 2018 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.dumap.model;

import lombok.Builder;
import lombok.Data;

/**
 * Represent the request parameters for driving route direction query.
 */
@Data
@Builder
public class DirectionDrivingParam {

    /**
     * Origin coordinate
     */
    private String origin;

    /**
     * Destination coordinate
     */
    private String destination;

    /**
     * Uid of origin POI
     */
    private String originUid;

    /**
     * Uid of destination POI
     */
    private String destinationUid;

    /**
     * Point coordinates on the way
     */
    private String waypoints;

    /**
     * Coordinate type origin and destination
     */
    private String coordType;

    /**
     * Coordinate type of result
     */
    private String retCoordtype;

    /**
     * Driving strategy type.
     */
    private int tactics;

    /**
     * Whether return alternatives or not
     */
    private String alternatives;

    /**
     * Plate number
     */
    private String plateNumber;

    /**
     * More departure time
     */
    private String extDepartureTime;

    /**
     * Head direction of the starting point
     */
    private String gpsDirection;

    /**
     * Starting accuracy
     */
    private float radius;

    /**
     * Speed
     */
    private float speed;

    /**
     * Response type
     */
    private String output;

    /**
     * Call back function
     */
    private String callback;
}
