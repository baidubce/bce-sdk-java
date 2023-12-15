/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.route.model;

import java.util.List;

import com.baidubce.model.ListResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListRouteRuleResponse extends ListResponse {

    private List<RouteRule> routeRules;

    @Override
    public String toString() {
        return "ListRouteRuleResponse{ " +
                "marker= " + getMarker() + "," +
                "nextMarker= " + getNextMarker() + "," +
                "maxKeys= " + getMaxKeys() + "," +
                "isTruncated= " + getIsTruncated() + "," +
                "routeRules=" + routeRules + '}';
    }
}
