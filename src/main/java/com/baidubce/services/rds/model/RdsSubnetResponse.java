package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * The response of rds subnet
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsSubnetResponse extends AbstractBceResponse {

    private List<RdsSubnet> subnets = new ArrayList<RdsSubnet>();

    public List<RdsSubnet> getSubnets() {
        return subnets;
    }

    public void setSubnets(List<RdsSubnet> subnets) {
        this.subnets = subnets;
    }
}
