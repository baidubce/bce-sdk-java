package com.baidubce.services.subnet.model;

import java.util.List;

import com.baidubce.model.ListResponse;

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
}
