package com.baidubce.services.kafka.model.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecurityGroup {

    private String securityGroupId;

    private String securityGroupUuid;

    private String name;

    private String vpcId;

    private String vpcUuid;
}
