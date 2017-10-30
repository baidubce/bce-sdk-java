package com.baidubce.services.iothub.model;

import com.baidubce.model.AbstractBceResponse;

public class MqttClientStatusResponse extends AbstractBceResponse {

    private boolean online;

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
