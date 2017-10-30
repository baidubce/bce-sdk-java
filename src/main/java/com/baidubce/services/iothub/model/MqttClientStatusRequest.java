package com.baidubce.services.iothub.model;

public class MqttClientStatusRequest extends BaseRequest {

    private String clientId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public MqttClientStatusRequest withClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }
}
