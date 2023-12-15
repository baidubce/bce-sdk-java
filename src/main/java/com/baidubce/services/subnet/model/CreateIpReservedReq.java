/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.subnet.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateIpReservedReq extends AbstractBceRequest {
    @JsonIgnore
    private String clientToken;
    private String subnetId;
    private String ipCidr;
    private Integer ipVersion = 4;
    private String description;

    public CreateIpReservedReq withSubnetId(String subnetId) {
        this.subnetId = subnetId;
        return this;
    }

    public CreateIpReservedReq withIpCidr(String ipCidr) {
        this.ipCidr = ipCidr;
        return this;
    }

    public CreateIpReservedReq withIpVersion(Integer ipVersion) {
        this.ipVersion = ipVersion;
        return this;
    }

    public CreateIpReservedReq withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
