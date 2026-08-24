package com.baidubce.services.bes.v3.model.cluster;

import lombok.Data;

@Data
public class ClusterBilling {
    private String productType;
    private Integer timeLength;
    private String timeUnit;
    private String instanceId;
    private String shortId;
    private String subAccountId;
}
