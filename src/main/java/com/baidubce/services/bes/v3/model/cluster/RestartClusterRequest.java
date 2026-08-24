package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.util.List;

@Data
public class RestartClusterRequest extends AbstractBesV3Request {
    private String clusterId;
    private String restartType;
    private String mode;
    private List<String> nodes;
    private Double batchCount;
    private String batchUnit;
}
