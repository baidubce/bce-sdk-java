package com.baidubce.services.modbus.model.pullrule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PullRuleResponseWithDevice extends PullRuleResponse {
    public DeviceUnitDto getDevice() {
        return device;
    }

    public void setDevice(DeviceUnitDto device) {
        this.device = device;
    }

    private DeviceUnitDto device;
}
