package com.baidubce.services.bes.v3.model.cluster;

import lombok.Data;

import java.util.List;

@Data
public class ClusterSummary {
    private String clusterId;
    private String name;
    private String createTime;
    private String engine;
    private String engineType;
    private String version;
    private String kernelVersion;
    private String status;
    private ClusterHealth clusterHealth;
    private String region;
    private Integer logicalZonesNumber;
    private List<String> logicalZones;
    private RunningTime runningTime;
    private Boolean deletionProtection;
    private String payment;
    private String expirationTime;
    private List<Tag> tags;
    private Boolean inRecycleBin;
    private String deleteExpireTime;
    private Integer recycleCount;
    private Boolean isPrepayToPostpay;

}
