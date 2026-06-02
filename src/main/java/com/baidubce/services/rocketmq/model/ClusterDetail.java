package com.baidubce.services.rocketmq.model;

import lombok.Data;

import java.util.List;

@Data
public class ClusterDetail {
    private String clusterId;
    private String name;
    private String region;
    private String state;
    private ProvisionedCluster provisioned;
    private List<Tag> tags;
    private String createTime;
}
