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
 * Represent the request parameters of reverse geocoding query.
 */
@Data
@Builder
public class ReverseGeocoderParam {

    /**
     * Coordinate
     */
    private String location;

    /**
     * Coordinate type
     */
    private String coordtype;

    /**
     * Coordinate type of result
     */
    private String retCoordtype;

    /**
     * Whether to recall the POI around the incoming coordinates or not
     */
    private int pois;

    /**
     * POI recall radius
     */
    private int radius;

    /**
     * Response type
     */
    private String output;

    /**
     * Call back function
     */
    private String callback;

    /**
     * Whether calls POI related services or not
     */
    private String extensionsPoi;

    /**
     * Whether to recall surrounding road data or not
     */
    private String extensionsRoad;

    /**
     * Whether to recall town data or not
     */
    private String extensionsTown;

    /**
     * Language type of recalled administrative region
     */
    private String language;

    /**
     * Whether to automatically fill administrative region
     */
    private int languageAuto;

    /**
     * Whether to access the latest version of administrative division data
     */
    private int latestAdmin;

}
