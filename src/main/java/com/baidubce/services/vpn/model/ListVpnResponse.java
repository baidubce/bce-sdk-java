package com.baidubce.services.vpn.model;

import com.baidubce.model.ListResponse;
import lombok.Data;

import java.util.List;

@Data
public class ListVpnResponse extends ListResponse {

    private List<Vpn> vpns;

    @Override
    public String toString() {
        return "ListVpnResponse{" +
                "marker=" + getMarker() + "," +
                "isTruncated=" + getIsTruncated() + "," +
                "nextMarker=" + getNextMarker() + "," +
                "maxKeys=" + getMaxKeys() + "," +
                "vpns=" + vpns +
                '}';
    }
}
