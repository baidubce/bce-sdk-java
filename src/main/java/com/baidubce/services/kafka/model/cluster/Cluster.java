package com.baidubce.services.kafka.model.cluster;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cluster {

    private String clusterId;

    private String clusterSid;

    private String name;

    private String region;

    private String type;

    private String mode;

    private String state;

    private String kafkaVersion;

    private List<String> logicalZones;

    private String payment;

    private Boolean aclEnabled;

    private Boolean publicIpEnabled;

    private Boolean intranetIpEnabled;

    private List<String> authenticationModes;

    private List<Tag> tags;

    private String createTime;

    private String expirationTime;
}
