package com.baidubce.services.route.model;

/**
 * 多线路由下一跳信息
 */
public class NextHop {
    /**
     * 下一跳ID
     */
    private String nexthopId;

    /**
     * 路由类型,目前只支持专线网关类型:"dcGateway"
     */
    private String nexthopType;

    /**
     * 多线模式
     * 负载均衡取值为ecmp
     * 主备模式取值ha:active、ha:standby,分别表示主、备路由
     */
    private String pathType;

    // Getters and Setters
    public String getNexthopId() {
        return nexthopId;
    }

    public void setNexthopId(String nexthopId) {
        this.nexthopId = nexthopId;
    }

    public String getNexthopType() {
        return nexthopType;
    }

    public void setNexthopType(String nexthopType) {
        this.nexthopType = nexthopType;
    }

    public String getPathType() {
        return pathType;
    }

    public void setPathType(String pathType) {
        this.pathType = pathType;
    }

    public NextHop withNexthopId(String nexthopId) {
        this.nexthopId = nexthopId;
        return this;
    }

    public NextHop withNexthopType(String nexthopType) {
        this.nexthopType = nexthopType;
        return this;
    }

    public NextHop withPathType(String pathType) {
        this.pathType = pathType;
        return this;
    }

    @Override
    public String toString() {
        return "NextHop{" +
                "nexthopId='" + nexthopId + '\'' +
                ", nexthopType='" + nexthopType + '\'' +
                ", pathType='" + pathType + '\'' +
                '}';
    }
}