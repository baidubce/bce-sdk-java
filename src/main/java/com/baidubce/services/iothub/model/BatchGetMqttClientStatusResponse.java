package com.baidubce.services.iothub.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.Map;

public class BatchGetMqttClientStatusResponse extends AbstractBceResponse {

    private Map<String, MqttClientStatus> value;

    public Map<String, MqttClientStatus> getValue() {
        return value;
    }

    public void setValue(Map<String, MqttClientStatus> value) {
        this.value = value;
    }
}
