package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class SwitchPublicAccessRequest extends AbstractBesV3Request {
    private String clusterId;
    private Boolean enabled;
    private String publicIp;
    private String accessType;
}
