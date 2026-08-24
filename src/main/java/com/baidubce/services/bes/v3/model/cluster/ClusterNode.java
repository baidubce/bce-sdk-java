package com.baidubce.services.bes.v3.model.cluster;

import lombok.Data;

@Data
public class ClusterNode {
    private String nodeId;
    private String nodeName;
    private String type;
    private String status;
    private String nodeType;
    private String diskType;
    private String logicalZone;
    private Integer cpu;
    private Number cpuPercent;
    private Integer memory;
    private Number memoryPercent;
    private Integer heapMemory;
    private Number heapMemoryPercent;
    private Integer diskCount;
    private Integer diskSize;
    private Number diskUsedPercent;
    private Integer cdsExtraIo;
}
