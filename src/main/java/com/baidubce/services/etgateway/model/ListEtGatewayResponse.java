package com.baidubce.services.etgateway.model;

import com.baidubce.model.ListResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListEtGatewayResponse extends ListResponse {
    private List<EtGateway> etGateways;

    @Override
    public String toString() {
        return "ListEtGatewayResponse{" +
                "marker=" + getMarker() + "," +
                "isTruncated=" + getIsTruncated() + "," +
                "nextMarker=" + getNextMarker() + "," +
                "maxKeys=" + getMaxKeys() + "," +
                "etGateways=" + etGateways +
                '}';
    }
}
