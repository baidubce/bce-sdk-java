package com.baidubce.services.bes.v3.model.cluster;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class AccessWhitelistResponse extends AbstractBceResponse {
    private String clusterId;
    private List<String> publicIpWhitelist;
    private List<String> visualPublicIpWhitelist;
    private List<String> privateIpWhitelist;
    private List<String> visualPrivateIpWhitelist;
}
