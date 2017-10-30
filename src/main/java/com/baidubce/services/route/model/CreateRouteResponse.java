package com.baidubce.services.route.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Created by zhangjing60 on 17/8/2.
 */
public class CreateRouteResponse extends AbstractBceResponse {

    /**
     * the route rule id
     */
    private String routeRuleId;

    public String getRouteRuleId() {
        return routeRuleId;
    }

    public void setRouteRuleId(String routeRuleId) {
        this.routeRuleId = routeRuleId;
    }

    @Override
    public String toString() {
        return "routeRuleId{"
                + "routeRuleId=" + routeRuleId
                + '}';
    }

}
