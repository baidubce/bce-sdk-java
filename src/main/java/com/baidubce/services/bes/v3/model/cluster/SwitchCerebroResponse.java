package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class SwitchCerebroResponse extends AbstractBceResponse {
    private String clusterId;
    private Boolean enabled;
}
