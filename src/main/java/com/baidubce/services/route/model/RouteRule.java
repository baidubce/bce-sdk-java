package com.baidubce.services.route.model;

/**
 * Created by zhangjing60 on 17/8/2.
 */
public class RouteRule {


    /**
     * the id of the route rule
     */
    private String routeRuleId;

    /**
     * the id of the route table
     */
    private String routeTableId;



    /**
     * The source address
     */

    private String sourceAddress;

    /**
     * The destination address
     */

    private String destinationAddress;

    /**
     * next hop id
     * when the nexthopType is "defaultGateway",this field can be empty
     */

    private String nexthopId;

    /**
     * route type
     * The Bcc type is "custom";
     * the VPN type is "VPN";
     * the NAT type is "NAT";
     * the local gateway type is "defaultGateway"
     * the system default type is "sys"
     * The system automatically generates a route rule of the given type for each subnet.
     * The route rule id of this type is empty, cannot be edited, can not be deleted, nor can be added
     */

    private String nexthopType;

    /**
     *  The option param to describe the route table
     */

    private String description;

    /**
     * 路由模式类型
     * - "normal": 单线路由
     * - "ecmp": 多线负载均衡路由
     * - "ha:active": 多线主备路由-主路由
     * - "ha:standby": 多线主备路由-备路由
     */
    private String pathType;

    public String getPathType() {
        return pathType;
    }

    public void setPathType(String pathType) {
        this.pathType = pathType;
    }

    public String getRouteRuleId() {
        return routeRuleId;
    }

    public void setRouteRuleId(String routeRuleId) {
        this.routeRuleId = routeRuleId;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getRouteTableId() {
        return routeTableId;
    }

    public void setRouteTableId(String routeTableId) {
        this.routeTableId = routeTableId;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getNexthopId() {
        return nexthopId;
    }

    public void setNexthopId(String nexthopId) {
        this.nexthopId = nexthopId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNexthopType() {
        return nexthopType;
    }

    public void setNexthopType(String nexthopType) {
        this.nexthopType = nexthopType;
    }

    @Override
    public String toString() {
        return "RouteRule{" +
                "routeRuleId='" + routeRuleId + '\'' +
                ", routeTableId='" + routeTableId + '\'' +
                ", sourceAddress='" + sourceAddress + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", nexthopId='" + nexthopId + '\'' +
                ", nexthopType='" + nexthopType + '\'' +
                ", pathType='" + pathType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
