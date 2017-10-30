package com.baidubce.services.vpc.model;

import com.baidubce.model.ListResponse;

import java.util.List;

/**
 * The response for ListVpcsRequest.
 */
public class ListVpcsResponse extends ListResponse {

    /**
     * List of vpc info
     */
    private List<Vpc> vpcs;

    public List<Vpc> getVpcs() {
        return vpcs;
    }

    public void setVpcs(List<Vpc> vpcs) {
        this.vpcs = vpcs;
    }
}
