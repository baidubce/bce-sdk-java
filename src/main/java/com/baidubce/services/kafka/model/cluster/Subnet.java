package com.baidubce.services.kafka.model.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subnet {

    private String subnetId;

    private String subnetUuid;

    private String name;

    private String subnetType;

    private String zone;

    private String vpcId;

    private String cidr;
}
