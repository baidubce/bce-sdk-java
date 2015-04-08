/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Specifies constants that define BCE Regions.
 */
public enum Region {

    CN_N1("bj");

    /**
     * The list of ID's representing each region.
     */
    private List<String> regionIds;

    /**
     * Constructs a new region with the specified region ID's.
     *
     * @param regionIds The list of ID's representing the BCE region.
     * @throws NullPointerException regionIds should not be null.
     * @throws IllegalArgumentException regionIds should not be empty.
     */
    private Region(String... regionIds) {
        checkNotNull(regionIds, "regionIds should not be null.");
        checkArgument(regionIds.length > 0, "regionIds should not be empty");
        this.regionIds = Arrays.asList(regionIds);
    }

    @Override
    public String toString() {
        return this.regionIds.get(0);
    }

    /**
     * Returns the BCE Region enumeration value representing the specified BCE Region ID string.
     * If specified string doesn't map to a known BCE Region, then an <code>IllegalArgumentException</code> is thrown.
     *
     * @param regionId The BCE region ID string.
     * @throws NullPointerException regionId should not be null.
     * @return The BCE Region enumeration value representing the specified BCE Region ID.
     */
    public static Region fromValue(String regionId) {
        checkNotNull(regionId, "regionId should not be null.");
        for (Region region : Region.values()) {
            List<String> regionIds = region.regionIds;
            if (regionIds != null && regionIds.contains(regionId)) {
                return region;
            }
        }
        throw new IllegalArgumentException("Cannot create region from " + regionId);
    }
}
