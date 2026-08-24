package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class UpgradeClusterRequest extends AbstractBesV3Request {
    private String clusterId;
    private String upgradeType;
    private String targetVersion;
    private String targetKernel;
    private String operation;
    private String upgradeMode;
    private Boolean skipHealthCheck;
    private Boolean skipDeprecationCheck;
    private Boolean upgradeVisual;
    private Boolean enableDeploySet;
}
