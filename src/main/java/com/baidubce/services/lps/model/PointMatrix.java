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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Points matrix.
 *
 * @author weizhijun
 * @date 2019/03/07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointMatrix {

    /**
     * Start points of routes.
     * <p>
     * The format is string which is composed of coordinate string ("latitude, longitude") joined by '|' (eg.
     * 21.22345,112.11478|21.47832,112.37854)
     * And the product of the number of start points and end points must not be larger than 50.
     */
    private String origins;

    /**
     * End points of routes.
     * <p>
     * The format and limitation are the same as origins.
     */
    private String destinations;
}
