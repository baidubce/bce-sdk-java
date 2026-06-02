package com.baidubce.services.route.model;

import com.baidubce.model.AbstractBceResponse;
import java.util.List;

public class CreateRouteResponse extends AbstractBceResponse {

    /**
     * 单线路由规则ID (创建单线路由时返回)
     */
    private String routeRuleId;

    /**
     * 多线路由规则ID列表 (创建多线路由时返回)
     */
    private List<String> routeRuleIds;

    public String getRouteRuleId() {
        return routeRuleId;
    }

    public void setRouteRuleId(String routeRuleId) {
        this.routeRuleId = routeRuleId;
    }

    public List<String> getRouteRuleIds() {
        return routeRuleIds;
    }

    public void setRouteRuleIds(List<String> routeRuleIds) {
        this.routeRuleIds = routeRuleIds;
    }

    @Override
    public String toString() {
        return "CreateRouteResponse{" +
                "routeRuleId='" + routeRuleId + '\'' +
                ", routeRuleIds=" + routeRuleIds +
                '}';
    }
}