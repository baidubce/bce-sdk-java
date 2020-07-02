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

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

/**
 * Created by chenbo14 on 2019/11/19.
 */
@Data
public class ListRouteResponse extends AbstractBceResponse {
    private Meta meta;
    private List<RouteInfo> data;

    @Data
    public static class Meta {
        private Integer total;
        private Integer pageNo;
        private Integer pageSize;
    }

    @Data
    public static class RouteInfo {
        private String userId;
        private String routeId;
        private String departureCoordinate; // 经度，纬度
        private String arrivalCoordinate;
        private List<String> routeCoordinateList;
        private Integer distance;
        private Integer duration;
    }
}
