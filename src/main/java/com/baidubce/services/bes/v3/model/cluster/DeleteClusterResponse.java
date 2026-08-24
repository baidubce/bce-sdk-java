package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class DeleteClusterResponse extends AbstractBceResponse {
    private String clusterId;
}
