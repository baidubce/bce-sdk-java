package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class CreateClusterResponse extends AbstractBceResponse {
    private String clusterId;
    private String orderId;
    private String actionId;

}
