package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class SwitchDeletionProtectionResponse extends AbstractBceResponse {
    private String clusterId;
    private Boolean deletionProtection;
}
