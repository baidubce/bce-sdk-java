package com.baidubce.services.bes.v3.model.snapshot;

import lombok.Data;

import java.util.List;

@Data
public class RestoreClusterItem {
    private String clusterId;
    private String name;
    private String engine;
    private String engineType;
    private String version;
    private String kernelVersion;
    private String status;
    private String clusterHealth;
    private List<String> logicalZones;
    private Boolean available;
    private String unavailableReason;
}
