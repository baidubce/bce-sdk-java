package com.baidubce.services.iothub.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class QueryEndpointDailyMqttUsageResponse extends AbstractBceResponse {

    private List<DailyMqttUsage> value;

    public List<DailyMqttUsage> getValue() {
        return value;
    }

    public void setValue(List<DailyMqttUsage> value) {
        this.value = value;
    }
}
