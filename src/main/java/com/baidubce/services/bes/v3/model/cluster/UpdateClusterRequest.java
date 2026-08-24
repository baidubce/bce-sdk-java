package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.util.List;

@Data
public class UpdateClusterRequest extends AbstractBesV3Request {
    private String clusterId;
    private String mode;
    private List<String> logicalZones;
    private String vpcId;
    private String subnetId;
    private Boolean enableDeploySet;
    private List<NodeSpec> nodeSpecs;
    private List<String> deleteNodeTypes;
}
