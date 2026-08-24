package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class SuggestMigrationNodesRequest extends AbstractBesV3Request {
    private String clusterId;
    private String type;
    private Integer migrateCount;
}
