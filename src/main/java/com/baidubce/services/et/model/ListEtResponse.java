package com.baidubce.services.et.model;

import java.util.List;

import com.baidubce.model.ListResponse;

/**
 * The response for listing ET.
 */
public class ListEtResponse extends ListResponse {
    /**
     * ET List
     */
    private List<Et> ets;

    public List<Et> getEts() {
        return ets;
    }

    public void setEts(List<Et> ets) {
        this.ets = ets;
    }

    @Override
    public String toString() {
        return "ListEtResponse{" +
                "marker=" + getMarker() + "," +
                "maxKeys=" + getMaxKeys() + "," +
                "isTruncated=" + getIsTruncated() + "," +
                "nextMarker=" + getNextMarker() + "," +
                "ets=" + ets +
                '}';
    }
}
