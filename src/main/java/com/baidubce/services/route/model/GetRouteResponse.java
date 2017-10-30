package com.baidubce.services.route.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Created by zhangjing60 on 17/8/2.
 */
public class GetRouteResponse extends AbstractBceResponse {

    /**
     * The id of the route table
     * there must be at least one parameter, either routeTableId or vpcId
     */
    private String routeTableId;

    /**
     * The vpc id
     * there must be at least one parameter, either routeTableId or vpcId
     */
    private String vpcId;

    /**
     * List of route rules
     *
     */
    private List<RouteRule> routeRules;

    public String getRouteTableId() {
        return routeTableId;
    }

    public void setRouteTableId(String routeTableId) {
        this.routeTableId = routeTableId;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public List<RouteRule> getRouteRules() {
        return routeRules;
    }

    public void setRouteRules(List<RouteRule> routeRules) {
        this.routeRules = routeRules;
    }

}
