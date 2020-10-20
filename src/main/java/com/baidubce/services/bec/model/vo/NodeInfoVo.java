/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec.model.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Node information.
 */
@Data
public class NodeInfoVo {


    /**
     * Node information.
     */
    private List<RegionInfo> regionList = new ArrayList<RegionInfo>();

    /**
     * Get node sum.
     */
    public int getNodeSum() {
        int result = 0;
        for (RegionInfo regionInfo : regionList) {
            for (CityInfo cityInfo : regionInfo.getCityList()) {
                result += cityInfo.getServiceProviderList().size();
            }
        }
        return result;
    }

    /**
     * Region information.
     */
    @Data
    public static class RegionInfo {

        /**
         * The English name of the region to which the node belongs.
         */
        private String region;

        /**
         * The Chinese name of the region to which the node belongs.
         */
        private String name;

        /**
         * Information about the city where the node is located.
         */
        private List<CityInfo> cityList = new ArrayList<CityInfo>();

    }

    /**
     * City information.
     */
    @Data
    public static class CityInfo {

        /**
         * The English name of the city to which the node belongs.
         */
        private String city;

        /**
         * The Chinese name of the city to which the node belongs.
         */
        private String name;

        /**
         * Information of the operator where the node is located.
         */
        private List<ServiceProviderInfo> serviceProviderList = new ArrayList<ServiceProviderInfo>();

    }

    /**
     * Service provider information.
     */
    @Data
    public static class ServiceProviderInfo {

        /**
         * The English name of the service provider to which the node belongs.
         */
        private String serviceProvider;

        /**
         * The Chinese name of the service provider to which the node belongs.
         */
        private String name;

    }
}
