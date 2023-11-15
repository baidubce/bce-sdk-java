package com.baidubce.services.kafka.model.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vpc {

    private String vpcId;

    private String name;

    private String cidr;
}
