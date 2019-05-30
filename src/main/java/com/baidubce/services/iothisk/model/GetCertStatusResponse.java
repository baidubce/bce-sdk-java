package com.baidubce.services.iothisk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represent the response of get cert status.
 */
public class GetCertStatusResponse extends IotPkiManageResponse {

    @JsonProperty("statusCode")
    private CertStatus status;

    public CertStatus getStatus() {
        return status;
    }

    public void setStatus(CertStatus status) {
        this.status = status;
    }

}