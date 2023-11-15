package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetIcpResponse extends CdnResponse {

    @JsonProperty("IcpStatus")
    private String icpStatus;

    public GetIcpResponse() {
    }

    public String getIcpStatus() {
        return icpStatus;
    }

    public void setIcpStatus(String icpStatus) {
        this.icpStatus = icpStatus;
    }
}
