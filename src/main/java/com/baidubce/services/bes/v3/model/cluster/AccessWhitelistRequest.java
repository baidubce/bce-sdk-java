package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.util.List;

@Data
public class AccessWhitelistRequest extends AbstractBesV3Request {
    private String clusterId;
    private String accessType;
    private String networkType;
    private List<String> ipWhitelist;
}
