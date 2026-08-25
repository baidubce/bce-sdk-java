package com.baidubce.services.bes.v3.model.logmanage;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.Map;

@Data
public class LogSwitchResponse extends AbstractBceResponse {
    private Boolean enabled;
    private Map<String, Boolean> types;
}
