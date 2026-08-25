package com.baidubce.services.bes.v3.model.logmanage;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.util.Map;

@Data
public class UpdateLogSwitchRequest extends AbstractBesV3Request {
    private String clusterId;
    private Boolean enabled;
    private Map<String, Boolean> types;
}
