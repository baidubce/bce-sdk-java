package com.baidubce.services.route.model;

import java.util.List;
import com.baidubce.model.AbstractBceResponse;

public class GetRouteResponse extends AbstractBceResponse {

    /**
     * 路由表名称
     */
    private String name;

    /**
     * The id of the route table
     */
    private String routeTableId;

    /**
     * The vpc id
     */
    private String vpcId;

    /**
     * List of route rules
     */
    private List<RouteRule> routeRules;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "GetRouteResponse{" +
                "name='" + name + '\'' +
                ", routeTableId='" + routeTableId + '\'' +
                ", vpcId='" + vpcId + '\'' +
                ", routeRules=" + routeRules +
                '}';
    }
}