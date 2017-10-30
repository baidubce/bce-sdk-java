package com.baidubce.services.route.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by zhangjing60 on 17/8/2.
 */
public class GetRouteRequest extends AbstractBceRequest {


    /**
     * the API's version number
     */
    private String version;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    /**
     * Configure the version for the request
     * @param version the API version number
     * @returnGetRouteRequest with version
     */
    public GetRouteRequest withVersion(String version) {
        this.version = version;
        return this;
    }

    /**
     * configure routeTableId for the request
     * @param routeTableId  the id of the route table
     * @returnGetRouteRequest with routeTableId
     */
    public GetRouteRequest withRouteTableId(String routeTableId) {
        this.routeTableId = routeTableId;
        return this;
    }


    /**
     * configure vpcId for the request
     * @param vpcId the vpcId
     * @returnGetRouteRequest with vpcId
     */
    public GetRouteRequest withVpcId(String vpcId) {
        this.vpcId = vpcId;
        return this;
    }



    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return GetRouteRequest with credentials.
     */
    @Override
    public GetRouteRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;

    }

}
