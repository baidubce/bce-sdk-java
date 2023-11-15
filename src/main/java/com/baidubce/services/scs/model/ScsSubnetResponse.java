package com.baidubce.services.scs.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * The response of scs subnet
 */
public class ScsSubnetResponse extends AbstractBceResponse {

    private List<ScsSubnet> subnets = new ArrayList<ScsSubnet>();

    public List<ScsSubnet> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<ScsSubnet> subnets) {
        this.subnets = subnets;
    }
}
