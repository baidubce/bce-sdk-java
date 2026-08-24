package com.baidubce.services.bes.v3.model.cluster;

import lombok.Data;

@Data
public class ClusterDetailNodeSpec {
    private String type;
    private String nodeType;
    private String nodeTypeDisplayName;
    private Integer cpu;
    private Integer memory;
    private Integer nodeCount;
    private String diskType;
    private String diskTypeDisplayName;
    private Integer diskSize;
    private Integer diskCount;
    private Integer cdsExtraIo;
    private Integer diskTotalSize;
}
