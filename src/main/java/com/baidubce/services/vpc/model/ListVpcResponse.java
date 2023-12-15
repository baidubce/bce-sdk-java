package com.baidubce.services.vpc.model;

import java.util.List;

import com.baidubce.model.ListResponse;

/**
 * The response for ListVpcRequest.
 */
public class ListVpcResponse extends ListResponse {

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

    @Override
    public String toString() {
        return "ListVpcResponse{" +
                "marker= " + getMarker() + "," +
                "nextMarker= " + getNextMarker() + "," +
                "maxKeys= " + getMaxKeys() + "," +
                "isTruncated= " + getIsTruncated() + "," +
                "vpcs=" + vpcs +
                '}';
    }
}
