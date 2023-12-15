package com.baidubce.services.subnet.model;

import java.util.List;

import com.baidubce.model.ListResponse;

import lombok.Getter;
import lombok.Setter;

/**
 * The response for CreateSubnetRequest.
 */
@Getter
@Setter
public class ListIpReserveResponse extends ListResponse {

    /**
     * List of subnet info
     */
    private List<IpReserve> ipReserves;

    @Override
    public String toString() {
        return "ListIpReserveResponse{" +
                "marker= " + getMarker() + "," +
                "maxKeys= " + getMaxKeys() + "," +
                "isTruncated= " + getIsTruncated() + "," +
                "IpReserve=" + ipReserves +
                '}';
    }
}
