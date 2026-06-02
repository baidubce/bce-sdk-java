/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The request for updating Ipv6Gateway delete protection.
 */
public class UpdateDeleteProtectRequest extends AbstractBceRequest {

    /**
     * The gatewayId of the Ipv6Gateway.
     */
    @JsonIgnore
    private String gatewayId;

    /**
     * Whether to enable delete protection.
     */
    private Boolean deleteProtect;

    /**
     * An ASCII string whose length is less than 64.
     */
    @JsonIgnore
    private String clientToken;

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public Boolean getDeleteProtect() {
        return deleteProtect;
    }

    public void setDeleteProtect(Boolean deleteProtect) {
        this.deleteProtect = deleteProtect;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    @Override
    public UpdateDeleteProtectRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Configure gatewayId for the request.
     *
     * @param gatewayId The gatewayId of Ipv6Gateway
     *
     * @return UpdateDeleteProtectRequest with gatewayId
     */
    public UpdateDeleteProtectRequest withGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
        return this;
    }

    /**
     * Configure deleteProtect for the request.
     *
     * @param deleteProtect Whether to enable delete protection
     *
     * @return UpdateDeleteProtectRequest with deleteProtect
     */
    public UpdateDeleteProtectRequest withDeleteProtect(Boolean deleteProtect) {
        this.deleteProtect = deleteProtect;
        return this;
    }

    /**
     * Configure clientToken for the request.
     *
     * @param clientToken The clientToken
     *
     * @return UpdateDeleteProtectRequest with clientToken
     */
    public UpdateDeleteProtectRequest withClientToken(String clientToken) {
        this.clientToken = clientToken;
        return this;
    }
}
