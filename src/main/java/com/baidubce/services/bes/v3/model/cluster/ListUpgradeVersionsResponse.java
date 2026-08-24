package com.baidubce.services.bes.v3.model.cluster;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class ListUpgradeVersionsResponse extends AbstractBceResponse {
    private String clusterId;
    private String name;
    private String engine;
    private String currentVersion;
    private String currentKernel;
    private Boolean enableDeploySet;
    private List<VersionUpgrade> versionUpgrades;
    private List<KernelUpgrade> kernelUpgrades;
}
