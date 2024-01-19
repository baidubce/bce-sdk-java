package com.baidubce.services.kafka.model.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Provisioned {

    private String kafkaVersion;

    private Billing billing;

    private Vpc vpc;

    private List<Subnet> subnets;

    private List<String> logicalZones;

    private List<SecurityGroup> securityGroup;

    private String vpcId;

    private List<String> subnetIds;

    private List<String> securityGroupIds;

    private boolean publicIpEnabled;

    private int publicIpBandwidth = 0;

    private boolean intranetIpEnabled;

    private List<Authentication> authentications;

    private boolean aclEnabled;

    private int numberOfBrokerNodes;

    private Integer numberOfBrokerNodesPerZone;

    private String nodeType;

    private StorageMeta storageMeta;

    private Boolean storagePolicyEnabled;

    private StoragePolicy storagePolicy;

    private ConfigMeta configMeta;

    private boolean deploySetEnabled;
}
