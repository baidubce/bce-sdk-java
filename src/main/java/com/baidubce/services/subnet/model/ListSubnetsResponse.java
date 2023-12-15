package com.baidubce.services.subnet.model;

import com.baidubce.model.ListResponse;

import java.util.List;

/**
 * The response for CreateSubnetRequest.
 */
public class ListSubnetsResponse extends ListResponse {

    /**
     * List of subnet info
     */
    private List<Subnet> subnets;

    public List<Subnet> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<Subnet> subnets) {
        this.subnets = subnets;
    }

    @Override
    public String toString() {
        return "ListSubnetsResponse{" +
                "marker= " + getMarker() + "," +
                "nextMarker= " + getNextMarker() + "," +
                "maxKeys= " + getMaxKeys() + "," +
                "isTruncated= " + getIsTruncated() + "," +
                "subnets=" + subnets +
                '}';
    }
}
