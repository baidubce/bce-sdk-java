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

import lombok.Data;

/**
 * Restriction information.
 *
 * @author weizhijun
 * @date 2019/03/11
 */
@Data
public class RestrictionInfo {
    /**
     * Type of restriction.
     * <p>
     * Optional value:
     * 0: no restriction.
     * 1: restrict local vehicle.
     * 2: restrict outland vehicle.
     * 3: restrict tail number of local vehicle.
     * 4: restrict tail number of outland vehicle.
     * 5: other restrictions.
     */
    private String type;

    /**
     * The detail of restriction information.
     */
    private String info;
}
