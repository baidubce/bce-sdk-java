package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class RecoveryClusterResponse extends AbstractBceResponse {
    private String clusterId;
    private String actionId;
}
