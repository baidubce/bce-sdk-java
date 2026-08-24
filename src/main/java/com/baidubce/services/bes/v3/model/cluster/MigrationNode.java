package com.baidubce.services.bes.v3.model.cluster;

import lombok.Data;

@Data
public class MigrationNode {
    private String nodeId;
    private String nodeName;
    private String status;
    private String logicalZone;
}
