package com.baidubce.services.rocketmq.model;

import lombok.Data;

import java.util.List;

@Data
public class Cluster {
    private String clusterId;
    private String name;
    private String region;
    private String state;
    private String arch;
    private String rocketmqVersion;
    private String payment;
    private List<String> zoneNames;
    private Boolean aclEnabled;
    private List<String> encryptionInTransit;
    private List<String> authenticationModes;
    private List<Tag> tags;
    private String createTime;
    private String expireTime;
}
