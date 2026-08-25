package com.baidubce.services.bes.v3.model.logmanage;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class GetLogSwitchRequest extends AbstractBesV3Request {
    private String clusterId;
}
