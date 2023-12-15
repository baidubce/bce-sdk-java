package com.baidubce.services.probe.model;

import com.baidubce.model.ListResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListProbeResponse extends ListResponse {
    private List<Probe> probes;

    @Override
    public String toString() {
        return "ListProbeResponse{" +
                "marker= " + getMarker() + "," +
                "nextMarker= " + getNextMarker() + "," +
                "maxKeys= " + getMaxKeys() + "," +
                "isTruncated= " + getIsTruncated() + "," +
                "probes=" + probes +
                '}';
    }
}
