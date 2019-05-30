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
 * Represent the parameters of bus route direction query.
 */
@Data
@Builder
public class DirectionTransitParam {

    /**
     * Origin coordinate
     */
    private String origin;

    /**
     * Destination coordinate
     */
    private String destination;

    /**
     * Coordinate type of origin and destination
     */
    private String coordType;

    /**
     * Bus exchange strategy in city
     */
    private int tacticsIncity;

    /**
     * Cross-city exchange strategy
     */
    private int tacticsIntercity;

    /**
     * Cross-city transportation strategy
     */
    private int transTypeIntercity;

    /**
     * Coordinate type of result
     */
    private String retCoordtype;

    /**
     * Response type
     */
    private String output;

    /**
     * Number of routes returned in each page
     */
    private int pageSize;

    /**
     * Index of returned page
     */
    private int pageIndex;

    /**
     * Call back function
     */
    private String callback;
}
