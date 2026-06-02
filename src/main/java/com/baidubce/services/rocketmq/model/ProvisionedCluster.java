package com.baidubce.services.rocketmq.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProvisionedCluster {
    private ClusterVersion version;
    private int numberOfBrokers;
    private ClusterArch arch;
    private boolean aclEnabled;
    private List<Encryption> encryptionInTransit;
    private Payment payment;
    private Billing billing;
    private List<String> zoneNames;
    private String vpcId;
    private List<String> subnetIds;
    private List<String> securityGroupIds;
    private BrokerNodeType nodeType;
    private boolean deploySetEnabled;
    private StorageType storageType;
    private int storageSize;
    private boolean publicIpEnabled;
    private int publicIpBandwidth;
}
