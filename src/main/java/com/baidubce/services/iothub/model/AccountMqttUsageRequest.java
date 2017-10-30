package com.baidubce.services.iothub.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class AccountMqttUsageRequest extends AbstractBceRequest {
    private String endpointName;

    public AccountMqttUsageRequest withEndpointName(String endpointName) {
        this.endpointName = endpointName;
        return this;
    }

    public String getEndpointName() {
        return endpointName;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }

    @Override
    public AccountMqttUsageRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}