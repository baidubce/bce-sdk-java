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
 * Represent the request parameters of rectangular area search query.
 */
@Data
@Builder
public class PlaceSearchByBoundsParam {

    /**
     * Keyword to be searched
     */
    private String query;

    /**
     * Retrieve classification preferences
     */
    private String tag;

    /**
     * Datail level of search result
     */
    private String scope;

    /**
     * Search filter
     */
    private String filter;

    /**
     * Coordinate type
     */
    private int coordType;

    /**
     * Coordinate type of result
     */
    private String retCoordtype;

    /**
     * Number of POIs recalled in a single operation
     */
    private int pageSize;

    /**
     * Returned page number
     */
    private int pageNum;

    /**
     * Rectangular area to be searched
     */
    private String bounds;

    /**
     * Response type
     */
    private String output;
}
